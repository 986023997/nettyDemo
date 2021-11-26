import org.ciapge.bean.Request;
import org.ciapge.bean.Response;
import org.ciapge.client.RpcClient;
import com.alibaba.fastjson.JSON;
import org.ciapge.future.SyncWrite;
import io.netty.channel.ChannelFuture;

/**
 * @author 朱林
 * @description
 * @date 2021/11/25 11:15
 */

public class RpcClientTestCase {
    private static ChannelFuture future;

    public static void main(String[] args) {
        System.out.println("hi 微信公众号：bugstack虫洞栈");
        RpcClient client = new RpcClient();
        new Thread(client).start();

        while (true) {
            try {
                //获取future，线程有等待处理时间
                if (null == future) {
                    future = client.getFuture();
                    Thread.sleep(500);
                    continue;
                }
                //构建发送参数
                Request request = new Request();
                request.setParam("查询｛bugstack虫洞栈｝用户信息");
                SyncWrite s = new SyncWrite();
                Response response = s.syncWrite(request, future.channel(), 3000);
                System.out.println("调用结果：" + JSON.toJSON(response));
                Thread.sleep(1000);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
