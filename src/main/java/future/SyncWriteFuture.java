package future;

import bean.Response;
import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.GenericFutureListener;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * @author 朱林
 * @description
 * @date 2021/11/24 17:14
 */
public class SyncWriteFuture implements WriteFuture<Response> {

    private CountDownLatch latch = new CountDownLatch(1);
    private Response response;
    private String requestId;

    @Override
    public boolean cancel(boolean mayInterruptIfRunning) {
        return false;
    }

    @Override
    public boolean isCancelled() {
        return false;
    }

    @Override
    public boolean isDone() {
        return false;
    }

    @Override
    public Response get() throws InterruptedException, ExecutionException {
        latch.await();
        return response;

    }

    @Override
    public Response get(long timeout, TimeUnit unit) throws InterruptedException, ExecutionException, TimeoutException {
        if (latch.await(timeout, unit)) {
            return response;
        }
        return null;
    }

    @Override
    public void setResponse(Response response) {
        latch.countDown();
        response = response;
    }


    @Override
    public String getRequestId() {
        return requestId;
    }




}
