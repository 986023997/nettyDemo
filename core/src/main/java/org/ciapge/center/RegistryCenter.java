package org.ciapge.center;

import com.alibaba.fastjson.JSON;
import org.ciapge.domain.RpcProviderConfig;
import redis.clients.jedis.Jedis;

/**
 * @author 朱林
 * @description 注册中心
 * @date 2021/11/26 9:37
 */
public class RegistryCenter {
    private static Jedis jedis;

    public static void init(String host, int port) {
         jedis = new Jedis(host, port);

    }

    /**
     * @description 获取服务提供者
     * @author zhulin
     * @date 2021/11/26 11:20
     */
    public static RpcProviderConfig getProvider(String service, String alias) {
        if (jedis == null) {
            throw new RuntimeException("注册中心不存在！");
        }
        StringBuilder key = new StringBuilder();
        key.append(service).append(":").append(alias);
        String provider = jedis.get(key.toString());
        if (provider == null || provider.trim().length() <= 0) {
            return null;
        }
        return JSON.parseObject(provider,RpcProviderConfig.class);
    }

    /**
     * @description 注册服务提供者
     * @author zhulin
     * @date 2021/11/26 11:20
     */
    public static void registry(RpcProviderConfig rpcProviderConfig) {
        if (jedis == null) {
            throw new RuntimeException("注册中心不存在！");
        }
        StringBuilder key = new StringBuilder();
        key.append(rpcProviderConfig.getService()).append(":").append(rpcProviderConfig.getAlias());
        jedis.set(key.toString(), JSON.toJSONString(rpcProviderConfig));
    }
}
