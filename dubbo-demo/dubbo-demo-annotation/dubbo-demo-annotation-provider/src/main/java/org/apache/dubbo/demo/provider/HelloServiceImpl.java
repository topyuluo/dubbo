package org.apache.dubbo.demo.provider;

import org.apache.dubbo.config.annotation.Service;
import org.apache.dubbo.demo.HelloService;

/**
 * @author: pan.mw
 * @date: 2020/12/2
 * @since: JDK 1.8
 * @version: v1.0
 */

@Service
public class HelloServiceImpl implements HelloService {
    @Override
    public String hello() {
        return "hello";
    }

    public String hello1(){
        return "hello1";
    }

    public String hello2(){
        return "hello2";
    }
}
