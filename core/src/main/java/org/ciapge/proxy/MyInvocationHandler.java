package org.ciapge.proxy;

import com.sun.org.slf4j.internal.Logger;
import com.sun.org.slf4j.internal.LoggerFactory;
import io.netty.channel.Channel;
import org.ciapge.bean.Request;
import org.ciapge.bean.Response;
import org.ciapge.future.SyncWrite;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;


/**
 * @author 朱林
 * @description
 * @date 2021/11/26 9:53
 */
public class MyInvocationHandler implements InvocationHandler {

    private static final Logger log = LoggerFactory.getLogger(MyInvocationHandler.class);
    private Request request;

    public MyInvocationHandler(Request request) {
        this.request = request;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Channel channel = null;
        if (request == null || (channel = request.getChannel()) == null) {
            log.error("不存在该服务提供者！");
            return null;
        }
        String methodName = method.getName();
        Class<?>[] paramTypes = method.getParameterTypes();
        if ("toString".equals(methodName) && paramTypes.length == 0) {
            return request.toString();
        } else if ("hashCode".equals(methodName) && paramTypes.length == 0) {
            return request.hashCode();
        } else if ("equals".equals(methodName) && paramTypes.length == 1) {
            return request.equals(args[0]);
        }
        request.setMethod(methodName);
        request.setArgs(args);
        request.setArgTypes(paramTypes);
        // 远程调用
        Response response = new SyncWrite().syncWrite(request, channel, 5000);
        return response.getResult();
    }
}
