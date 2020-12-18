/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.dubbo.registry;

import org.apache.dubbo.common.Node;
import org.apache.dubbo.common.URL;

/**
 * Registry. (SPI, Prototype, ThreadSafe)
 *
 * @see org.apache.dubbo.registry.RegistryFactory#getRegistry(URL)
 * @see org.apache.dubbo.registry.support.AbstractRegistry
 *
 * <o>
 *     表示一个拥有注册中心能力的节点， 其中 reExportRegister 和 reExportUnRegister  都是委托给RegistryService中的相应方法
 *
 *     每个对应的registry 实现类 都有对应的Factory实现
 *     每个对应的 registryFactory 工厂负责创建对应的registry对象
 *
 * </o>
 */
public interface Registry extends Node, RegistryService {
    default void reExportRegister(URL url) {
        register(url);
    }

    default void reExportUnregister(URL url) {
        unregister(url);
    }
}