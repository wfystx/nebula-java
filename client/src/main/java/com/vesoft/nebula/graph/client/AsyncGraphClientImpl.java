/* Copyright (c) 2019 vesoft inc. All rights reserved.
 *
 * This source code is licensed under Apache 2.0 License,
 * attached with Common Clause Condition 1.0, found in the LICENSES directory.
 */

package com.vesoft.nebula.graph.client;

import static com.google.common.base.Preconditions.checkArgument;

import com.facebook.thrift.TException;
import com.facebook.thrift.protocol.TCompactProtocol;
import com.facebook.thrift.protocol.TProtocol;
import com.facebook.thrift.transport.TSocket;
import com.facebook.thrift.transport.TTransport;
import com.google.common.base.Optional;
import com.google.common.collect.Lists;
import com.google.common.net.HostAndPort;
import com.google.common.net.InetAddresses;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.ListeningExecutorService;
import com.google.common.util.concurrent.MoreExecutors;
import com.vesoft.nebula.graph.AuthResponse;
import com.vesoft.nebula.graph.ErrorCode;
import com.vesoft.nebula.graph.ExecutionResponse;
import com.vesoft.nebula.graph.GraphService;

import java.util.List;
import java.util.Objects;
import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.Executors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AsyncGraphClientImpl implements AsyncGraphClient {

    private static final Logger LOGGER = LoggerFactory.getLogger(AsyncGraphClientImpl.class);

    private ListeningExecutorService threadPool =
            MoreExecutors.listeningDecorator(Executors.newCachedThreadPool());

    private final List<HostAndPort> addresses;
    private final int connectionRetry;
    private final int executionRetry;
    private final int timeout;
    private long sessionID;
    private TTransport transport = null;
    private GraphService.Client client;

    /**
     * The Constructor of Graph Client.
     *
     * @param addresses       The addresses of graph services.
     * @param timeout         The timeout of RPC request.
     * @param connectionRetry The number of retries when connection failure.
     * @param executionRetry  The number of retries when execution failure.
     */
    public AsyncGraphClientImpl(List<HostAndPort> addresses, int timeout, int connectionRetry,
                                int executionRetry) {
        checkArgument(timeout > 0);
        checkArgument(connectionRetry > 0);
        addresses.forEach(address -> {
            String host = address.getHost();
            int port = address.getPort();
            if (!InetAddresses.isInetAddress(host) || (port <= 0 || port >= 65535)) {
                throw new IllegalArgumentException(String.format("%s:%d is not a valid address",
                        host, port));
            }
        });

        this.addresses = addresses;
        this.timeout = timeout;
        this.connectionRetry = connectionRetry;
        this.executionRetry = executionRetry;
    }

    /**
     * The Constructor of Graph Client.
     *
     * @param addresses The addresses of graph services.
     */
    public AsyncGraphClientImpl(List<HostAndPort> addresses) {
        this(addresses, DEFAULT_TIMEOUT_MS, DEFAULT_CONNECTION_RETRY_SIZE,
                DEFAULT_EXECUTION_RETRY_SIZE);
    }

    /**
     * The Constructor of Graph Client.
     *
     * @param host The host of graph services.
     * @param port The port of graph services.
     */
    public AsyncGraphClientImpl(String host, int port) {
        this(Lists.newArrayList(HostAndPort.fromParts(host, port)), DEFAULT_TIMEOUT_MS,
                DEFAULT_CONNECTION_RETRY_SIZE, DEFAULT_EXECUTION_RETRY_SIZE);
    }

    /**
     * Switch Graph Space
     *
     * @param space The space name
     * @return The ErrorCode of status, 0 is succeeded.
     */
    @Override
    public ListenableFuture<Optional<Integer>> switchSpace(String space) {
        return execute(String.format("USE %s", space));
    }

    /**
     * Connect to the Graph Services.
     *
     * @param username The user's name, default is user.
     * @param password The user's password, default is password.
     * @return The ErrorCode of status, 0 is succeeded.
     */
    @Override
    public int connect(String username, String password) {
        int retry = connectionRetry;
        while (retry-- != 0) {
            Random random = new Random(System.currentTimeMillis());
            int position = random.nextInt(addresses.size());
            HostAndPort address = addresses.get(position);
            transport = new TSocket(address.getHost(), address.getPort(), timeout);
            TProtocol protocol = new TCompactProtocol(transport);

            try {
                transport.open();
                client = new GraphService.Client(protocol);
                AuthResponse result = client.authenticate(username, password);
                if (result.getError_code() == ErrorCode.E_BAD_USERNAME_PASSWORD) {
                    LOGGER.error("User name or password error");
                    return ErrorCode.E_BAD_USERNAME_PASSWORD;
                }

                if (result.getError_code() != ErrorCode.SUCCEEDED) {
                    LOGGER.error(String.format("Connect address %s failed : %s",
                            address.toString(), result.getError_msg()));
                } else {
                    sessionID = result.getSession_id();
                    return ErrorCode.SUCCEEDED;
                }
            } catch (TException tte) {
                LOGGER.error("Connect failed: " + tte.getMessage());
            }
        }
        return ErrorCode.E_FAIL_TO_CONNECT;
    }

    /**
     * Execute the query sentence.
     *
     * @param statement The query sentence.
     * @return The ErrorCode of status, 0 is succeeded.
     */
    public ListenableFuture<Optional<Integer>> execute(String statement) {
        return threadPool.submit(new Callable<Optional<Integer>>() {
            @Override
            public Optional<Integer> call() throws Exception {
                if (!checkTransportOpened(transport)) {
                    return Optional.of(ErrorCode.E_DISCONNECTED);
                }
                int retry = executionRetry;
                while (retry-- > 0) {
                    try {
                        ExecutionResponse executionResponse =
                                client.execute(sessionID, statement);
                        if (executionResponse.getError_code() != ErrorCode.SUCCEEDED) {
                            LOGGER.error("execute error: " + executionResponse.getError_msg());
                        }
                        return Optional.of(executionResponse.getError_code());
                    } catch (TException e) {
                        LOGGER.error("Thrift rpc call failed: " + e.getMessage());
                        return Optional.of(ErrorCode.E_RPC_FAILURE);
                    }
                }
                return Optional.of(ErrorCode.E_RPC_FAILURE);
            }
        });
    }

    /**
     * Execute the query sentence which will return a ResultSet.
     *
     * @param statement The query sentence.
     * @return The ErrorCode of status, 0 is succeeded.
     */
    @Override
    public ListenableFuture<Optional<ResultSet>> executeQuery(String statement) throws
            ConnectionException, NGQLException, TException {
        return threadPool.submit(new Callable<Optional<ResultSet>>() {
            @Override
            public Optional<ResultSet> call() throws Exception {
                if (!checkTransportOpened(transport)) {
                    LOGGER.error("Thrift rpc call failed");
                    throw new ConnectionException();
                }
                ExecutionResponse executionResponse = client.execute(sessionID, statement);
                int code = executionResponse.getError_code();
                if (code == ErrorCode.SUCCEEDED) {
                    return Optional.of(new ResultSet(executionResponse.getColumn_names(),
                            executionResponse.getRows()));
                } else {
                    LOGGER.error("Execute error: " + executionResponse.getError_msg());
                    throw new NGQLException(code);
                }
            }
        });

    }

    private boolean checkTransportOpened(TTransport transport) {
        return !Objects.isNull(transport) && transport.isOpen();
    }

    @Override
    public void close() throws Exception {

    }
}
