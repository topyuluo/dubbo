package org.apache.dubbo.demo.provider;

import com.alibaba.dubbo.config.annotation.Service;
import org.apache.dubbo.demo.WorldService;

/**
 * @author: pan.mw
 * @date: 2020/12/2
 * @since: JDK 1.8
 * @version: v1.0
 */
@Service
public class WorldServiceImpl implements WorldService {
    @Override
    public String world() {
        return "world";
    }
}
