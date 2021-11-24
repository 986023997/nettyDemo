package future;

import com.sun.deploy.cache.BaseLocalApplicationProperties;

import java.util.concurrent.ConcurrentHashMap;

/**
 * @author 朱林
 * @description
 * @date 2021/11/24 17:14
 */
public class SyncWriteMap {
    public static ConcurrentHashMap<String, SyncWriteFuture> syncKey = new ConcurrentHashMap<>();
}
