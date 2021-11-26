package org.ciapge.service.impl;

import org.ciapge.service.HelloService;
import org.springframework.stereotype.Service;

/**
 * @author 朱林
 * @description
 * @date 2021/11/26 13:09
 */
@Service("helloService")
public class HelloServiceImpl implements HelloService {
    @Override
    public String say(String msg) {
        return "hi,i have received you msg";
    }
}
