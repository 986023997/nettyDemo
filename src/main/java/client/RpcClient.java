package client;

import bean.Request;
import bean.Response;
import coder.RpcDecoder;
import coder.RpcEncoder;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.UnknownHostException;

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

    @Override
    public void run() {
        NioEventLoopGroup group = new NioEventLoopGroup();
        Bootstrap bootstrap = new Bootstrap();
        try {
            bootstrap.group(group)
                    .channel(NioSocketChannel.class)
                    .option(ChannelOption.SO_BACKLOG,128)
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            ch.pipeline().addLast(new RpcDecoder(Response.class)
                                    ,new RpcEncoder(Request.class)
                                    ,new MyClientChannelHandler());
                        }
                    });

            ChannelFuture future = bootstrap.connect(new InetSocketAddress(InetAddress.getLocalHost(), 8090)).sync();
            this.future = future;
            future.channel().close().sync();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
