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

package org.apache.dubbo.common.extension;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Marker for extension interface
 * <p/>
 * Changes on extension configuration file <br/>
 * Use <code>Protocol</code> as an example, its configuration file 'META-INF/dubbo/com.xxx.Protocol' is changed from: <br/>
 * <pre>
 *     com.foo.XxxProtocol
 *     com.foo.YyyProtocol
 * </pre>
 * <p>
 * to key-value pair <br/>
 * <pre>
 *     xxx=com.foo.XxxProtocol
 *     yyy=com.foo.YyyProtocol
 * </pre>
 * <br/>
 * The reason for this change is:
 * <p>
 * If there's third party library referenced by static field or by method in extension implementation, its class will
 * fail to initialize if the third party library doesn't exist. In this case, dubbo cannot figure out extension's id
 * therefore cannot be able to map the exception information with the extension, if the previous format is used.
 * <p/>
 * For example:
 * <p>
 * Fails to load Extension("mina"). When user configure to use mina, dubbo will complain the extension cannot be loaded,
 * instead of reporting which extract extension implementation fails and the extract reason.
 * </p>
 *
 * java spi : 当服务的提供者，提供了服务接口的一种实现之后，在jar包的META-INF/services 路径下，同时创建一个以服务接口命名的文件
 * 该文件里就是实现该服务接口的具体实现类， 而当外部程序装配这个模块的时候，就能通过 META-INF/services下的文件找到这个接口的具体实现类
 * 并装载实例化，从而完成调用。 而不在需要代码指定。可以根据文件的内容进行动态的切换
 * jdk 提供了两种方式： Service.prodiver()  ServiceLoader.load()
 *
 * Dubbo 的 spi 并没有使用java 的 spi  ，自定义实现 。
 *   - 通过 ExtensionLoader  实现
 *   - 改进了 jdk spi 的如下问题：
 *      - jdk标准的spi 会一行实例化所有的实现，如果有扩展实现初始化很耗时，但如果没用上也加载，会很浪费资源
 *      - 如果扩展点加载失败，连扩展点的名称都拿不到
 *      - 增加了对扩展点ioc aop 的支持，一个扩展点可以直接setter注入其它扩展点
 *
 * 约定：
 *  - 在扩展类的包内， 放置扩展点配置文件， META-INFO/dubbo 接口全限定名，内容为： 配置名 = 扩展实现类全限定名， 多个实现类用换行符分割
 *
 *
 * 扩展点自动包装：
 *  - 自动包装扩展点 Wrapper 类 。 ExtensionLoader 在加载扩展点时，如果加载到的扩展点有拷贝构造函数，则判定扩展点 Warpper类
 *
 *  Wrapper 类同样实现了扩展点接口，但是 Wrpper 不是扩展点的真正实现，它的用途主要是是用于从 ExtensionLoader 中返回 ，实际上返回的是
 *  Wrapper类的实例， Wrapper特有的   扩展点的Wrapper 类可以有多个，也可以根据需要新增。
 *
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
public @interface SPI {

    /**
     * default extension name
     */
    String value() default "";

}