package org.ciapge.client;

import org.ciapge.bean.Request;
import org.ciapge.bean.Response;
import org.ciapge.coder.RpcDecoder;
import org.ciapge.coder.RpcEncoder;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

/**
 * @author 朱林
 * @description
 * @date 2021/11/23 17:30
 */
public class RpcClient implements Runnable  {
    private ChannelFuture future;
    public ChannelFuture getFuture() {
        return future;
    }
    public void setFuture(ChannelFuture future) {
        this.future = future;
    }

    private String host;
    private int port;

    public RpcClient() {
    }

    public RpcClient(String host, int port) {
        this.host = host;
        this.port = port;
    }

    @Override
    public void run() {


        EventLoopGroup workerGroup = new NioEventLoopGroup();
        try {
            Bootstrap b = new Bootstrap();
            b.group(workerGroup);
            b.channel(NioSocketChannel.class);
            b.option(ChannelOption.AUTO_READ, true);
            b.handler(new ChannelInitializer<SocketChannel>() {
                @Override
                public void initChannel(SocketChannel ch) throws Exception {
                    ch.pipeline().addLast(
                            new RpcDecoder(Response.class),
                            new RpcEncoder(Request.class),
                            new MyClientHandler());
                }
            });
            ChannelFuture f = b.connect(host, port).sync();
            this.future = f;
            f.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            workerGroup.shutdownGracefully();
        }
    }


}
