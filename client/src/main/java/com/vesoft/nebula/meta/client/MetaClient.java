/* Copyright (c) 2019 vesoft inc. All rights reserved.
 *
 * This source code is licensed under Apache 2.0 License,
 * attached with Common Clause Condition 1.0, found in the LICENSES directory.
 */

package com.vesoft.nebula.meta.client;

import com.vesoft.nebula.Client;
import com.vesoft.nebula.HostAddr;
import com.vesoft.nebula.meta.IdName;

import java.util.List;
import java.util.Map;

public interface MetaClient extends Client {

    /**
     * Must be used to initialize meta client
     *
     * @return boolean
     */
    public void init();

    /**
     * connect to server
     *
     * @return boolean
     */
    public boolean connect();

    public List<IdName> listSpaces();

    public List<HostAddr> getPart(int spaceId, int partId);

    public List<HostAddr> getPart(String spaceName, int partId);

    public Map<Integer, Map<Integer, List<HostAddr>>> getParts();

    public Integer getTagId(int spaceId, String tagName);

    public Integer getTagId(String spaceName, String tagName);

    public Integer getEdgeType(int spaceId, String edgeName);

    public Integer getEdgeType(String spaceName, String edgeName);

    /**
     * Get tag schema with specified version.
     *
     * @param spaceName the space's name
     * @param tagName   the tag's name
     * @param version   the tag's version
     * @return
     */
    public Map<String, Class> getTagSchema(String spaceName, String tagName, long version);

    /**
     * Get edge schema with specified version.
     *
     * @param spaceName the space's name
     * @param edgeName  the edge's name
     * @param version   the edge's version
     * @return
     */
    public Map<String, Class> getEdgeSchema(String spaceName, String edgeName, long version);

}

