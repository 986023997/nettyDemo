package server;

import bean.Request;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.concurrent.EventExecutorGroup;

/**
 * @author 朱林
 * @description
 * @date 2021/11/23 17:03
 */
public class MyChannelHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        if (!(msg instanceof Request)) {
            super.channelRead(ctx, msg);
        }
        // todo 读取到请求

        // 根据请求获取到本地对象，执行本地方法获取执行结构，封装为结果进行返回


    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        super.channelReadComplete(ctx);
    }
}
