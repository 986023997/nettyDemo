package org.ciapge.config.spring.bean;

import io.netty.channel.ChannelFuture;
import org.ciapge.center.RegistryCenter;
import org.ciapge.config.spring.config.ConsumerConfig;
import org.ciapge.domain.RpcProviderConfig;
import org.springframework.beans.factory.FactoryBean;

/**
 * @author 朱林
 * @description
 * @date 2021/11/25 22:42
 */
public class ConsumerBean<T> extends ConsumerConfig<T> implements FactoryBean<T> {

    private ChannelFuture channelFuture;
    private RpcProviderConfig rpcProviderConfig;

    @Override
    public T getObject() throws Exception {
        // 判断提供服务的信息是否存在，若不存在去注册中心获取
        if (rpcProviderConfig == null) {
            rpcProviderConfig = RegistryCenter.getProvider(getService(), getAlias());
        }
        // 获取获取provider的信息之后，
        // 生成代理对象返回
        Request request = new Request();


        return

    }

    @Override
    public Class<?> getObjectType() {
        try {
            return Class.forName(getService(),true, Thread.currentThread().getContextClassLoader());
        } catch (ClassNotFoundException e) {
            return null;
        }
    }

    @Override
    public boolean isSingleton() {
        return true;
    }
}
