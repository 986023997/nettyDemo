package org.ciapge.server;

import org.ciapge.bean.Request;
import org.ciapge.bean.Response;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.ReferenceCountUtil;
import org.springframework.context.ApplicationContext;

import java.lang.reflect.Method;

/**
 * @author 朱林
 * @description
 * @date 2021/11/23 17:03
 */
public class MyChannelHandler extends ChannelInboundHandlerAdapter {

    private ApplicationContext applicationContext;

    public MyChannelHandler(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object obj) throws Exception {
        Request msg = (Request) obj;

        //
        String service = msg.getService();
        Class<?> serviceClass = Thread.currentThread().getContextClassLoader().loadClass(service);
        Method method = serviceClass.getMethod(msg.getMethod(), msg.getArgTypes());
        Object result = method.invoke(applicationContext.getBean(serviceClass), msg.getArgs());

        //反馈
        Response request = new Response();
        request.setRequestId(msg.getRequestId());
        request.setResult(result);
        ctx.writeAndFlush(request);
        //释放
        ReferenceCountUtil.release(msg);
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
       ctx.flush();
    }
}
