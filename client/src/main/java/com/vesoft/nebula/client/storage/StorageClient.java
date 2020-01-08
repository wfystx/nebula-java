/* Copyright (c) 2019 vesoft inc. All rights reserved.
 *
 * This source code is licensed under Apache 2.0 License,
 * attached with Common Clause Condition 1.0, found in the LICENSES directory.
 */

package com.vesoft.nebula.client.storage;

import com.google.common.base.Optional;
import com.vesoft.nebula.client.meta.MetaClientImpl;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public interface StorageClient extends AutoCloseable {

    public int put(int space, String key, int value);

    public int get(int space, String key);

    public int cas(int space, String key, int expected , int value);
}
