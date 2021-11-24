package future;

import bean.Response;

import java.util.concurrent.Future;

/**
 * @author 朱林
 * @description
 * @date 2021/11/24 17:17
 */
public interface WriteFuture<T> extends Future<T> {

    void setResponse(T t);
    String getRequestId();
}
