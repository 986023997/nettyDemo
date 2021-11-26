package org.ciapge.server;

import org.ciapge.bean.Request;
import org.ciapge.bean.Response;
import org.ciapge.coder.RpcDecoder;
import org.ciapge.coder.RpcEncoder;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;


/**
 * @author 朱林
 * @description
 * @date 2021/11/23 16:53
 */
public class RpcServer implements Runnable {

    @Override
    public void run() {
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        try {
            ServerBootstrap b = new ServerBootstrap();
            b.group(bossGroup, workerGroup)
                    .channel(NioServerSocketChannel.class)
                    .option(ChannelOption.SO_BACKLOG, 128)
//                    .childHandler(new ChannelInitializer<SocketChannel>(){
//                        @Override
//                        public void initChannel(SocketChannel ch){
//                            ch.pipeline().addLast(
//                                    new RpcDecoder(Request.class));
//                            ch.pipeline().addLast(
//                                    new RpcEncoder(Response.class));
//                            ch.pipeline().addLast(new MyServerHandler());
//                        }
//                    });
                    .childHandler(new MyChannelInitializer());

                ChannelFuture future = b.bind(8090).sync();
                future.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            workerGroup.shutdownGracefully();
            bossGroup.shutdownGracefully();

        }

    }
}
