package org.ciapge.server;

import org.ciapge.bean.Request;
import org.ciapge.bean.Response;
import org.ciapge.coder.RpcDecoder;
import org.ciapge.coder.RpcEncoder;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;
import org.springframework.context.ApplicationContext;


/**
 * @author 朱林
 * @description
 * @date 2021/11/25 13:40
 */
public class MyChannelInitializer extends ChannelInitializer<SocketChannel> {

    private ApplicationContext applicationContext;

    public MyChannelInitializer(ApplicationContext applicationContext) {
        this.applicationContext  = applicationContext;
    }

    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ch.pipeline().addLast(new RpcDecoder(Request.class),new RpcEncoder(Response.class),new MyChannelHandler(applicationContext));
//        ch.pipeline().addLast();
    }
}
