package org.ciapge.config.spring.bean;

import org.ciapge.center.RegistryCenter;
import org.ciapge.config.spring.config.ServerConfig;
import org.ciapge.domain.LocalServerInfo;
import org.ciapge.server.RpcServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * @author 朱林
 * @description
 * @date 2021/11/25 22:43
 */
public class ServerBean extends ServerConfig implements ApplicationContextAware {

    private static final Logger logger = LoggerFactory.getLogger(ServerBean.class);

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        //启动注册中心
        logger.info("启动注册中心 ...");
        RegistryCenter.init(host, port);
        logger.info("启动注册中心完成 {} {}", host, port);

        //初始化服务端
        logger.info("初始化生产端服务 ...");
        RpcServer serverSocket = new RpcServer(applicationContext);
        Thread thread = new Thread(serverSocket);
        thread.start();
        while (!serverSocket.isActiveSocketServer()) {
            try {
                Thread.sleep(500);
            } catch (InterruptedException ignore) {
            }
        }
        logger.info("初始化生产端服务完成 {} {}", LocalServerInfo.LOCAL_HOST, LocalServerInfo.LOCAL_PORT);
    }
}
