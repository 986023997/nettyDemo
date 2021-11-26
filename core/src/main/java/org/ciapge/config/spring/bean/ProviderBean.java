package org.ciapge.config.spring.bean;

import org.ciapge.center.RegistryCenter;
import org.ciapge.config.spring.config.ProviderConfig;
import org.ciapge.domain.RpcProviderConfig;
import org.ciapge.domain.LocalServerInfo;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * @author 朱林
 * @description
 * @date 2021/11/25 22:42
 */
public class ProviderBean extends ProviderConfig implements ApplicationContextAware {

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        final RpcProviderConfig rpcProviderConfig = new RpcProviderConfig();
        rpcProviderConfig.setAlias(getAlias());
        rpcProviderConfig.setService(getService());
        rpcProviderConfig.setRef(getRef());
        rpcProviderConfig.setHost(LocalServerInfo.LOCAL_HOST);
        rpcProviderConfig.setPort(LocalServerInfo.LOCAL_PORT);
        // 注册到注册中心
        RegistryCenter.registry(rpcProviderConfig);
    }
}
