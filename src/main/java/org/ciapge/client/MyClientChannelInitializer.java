package org.ciapge.client;

import org.ciapge.bean.Request;
import org.ciapge.bean.Response;
import org.ciapge.coder.RpcDecoder;
import org.ciapge.coder.RpcEncoder;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;

/**
 * @author 朱林
 * @description
 * @date 2021/11/25 13:47
 */
public class MyClientChannelInitializer extends ChannelInitializer<SocketChannel> {
    @Override
    protected void initChannel(SocketChannel ch) throws Exception {

    }
}
