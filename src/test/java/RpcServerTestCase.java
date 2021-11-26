import org.ciapge.server.RpcServer;

/**
 * @author 朱林
 * @description
 * @date 2021/11/25 11:15
 */

public class RpcServerTestCase {


    public static void main(String[] args) {
        RpcServer rpcServer = new RpcServer();
        new Thread(rpcServer).start();
    }

}
