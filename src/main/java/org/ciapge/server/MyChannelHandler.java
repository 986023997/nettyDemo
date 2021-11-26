package org.ciapge.server;

import org.ciapge.bean.Request;
import org.ciapge.bean.Response;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.ReferenceCountUtil;

/**
 * @author 朱林
 * @description
 * @date 2021/11/23 17:03
 */
public class MyChannelHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object obj) throws Exception {
        Request msg = (Request) obj;
        //反馈
        Response request = new Response();
        request.setRequestId(msg.getRequestId());
        request.setResult(msg.getParam() + " 请求成功，反馈结果请接受处理｛公众号：bugstack虫洞栈 博客栈：https://bugstack.cn｝。");
        ctx.writeAndFlush(request);
        //释放
        ReferenceCountUtil.release(msg);
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
       ctx.flush();
    }
}
