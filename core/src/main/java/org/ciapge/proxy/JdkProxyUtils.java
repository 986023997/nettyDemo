package org.ciapge.proxy;

import org.ciapge.bean.Request;

import java.lang.reflect.Proxy;

/**
 * @author 朱林
 * @description
 * @date 2021/11/26 9:50
 */
public class JdkProxyUtils {

    public static <T> T getProxy(Request request, Class<T> classInterface) {
        MyInvocationHandler invocationHandler =   new MyInvocationHandler(request);
       return (T) Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader(), new Class[]{classInterface}, invocationHandler);
    }
}
