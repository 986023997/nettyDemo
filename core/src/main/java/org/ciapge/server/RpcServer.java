package org.ciapge.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import lombok.SneakyThrows;
import org.ciapge.domain.LocalServerInfo;
import org.ciapge.utils.NetUtil;
import org.springframework.context.ApplicationContext;


/**
 * @author 朱林
 * @description
 * @date 2021/11/23 16:53
 */
public class RpcServer implements Runnable {
    private ApplicationContext applicationContext;

    public RpcServer() {
    }

    public RpcServer(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    private ChannelFuture channelFuture;

    @SneakyThrows
    @Override
    public void run() {
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        try {
            ServerBootstrap b = new ServerBootstrap();
            b.group(bossGroup, workerGroup)
                    .channel(NioServerSocketChannel.class)
                    .option(ChannelOption.SO_BACKLOG, 128)
                    .childHandler(new MyChannelInitializer(applicationContext));

            int port = 22201;
            while (NetUtil.isPortUsing(port)) {
                port++;
            }
        LocalServerInfo.LOCAL_HOST = NetUtil.getHost();
        LocalServerInfo.LOCAL_PORT = port;
        channelFuture = b.bind(port).sync();
        channelFuture.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            workerGroup.shutdownGracefully();
            bossGroup.shutdownGracefully();

        }

    }

    public boolean isActiveSocketServer() {
        try {
            if (channelFuture != null) {
                return channelFuture.channel().isActive();
            } else {
                return false;
            }
        } catch (Exception e) {
            return false;
        }
    }
}
