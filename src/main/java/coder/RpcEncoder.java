package coder;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import io.netty.util.concurrent.EventExecutorGroup;
import utils.SerializationUtil;

import javax.xml.ws.Response;

/**
 * @author 朱林
 * @description
 * @date 2021/11/23 17:11
 */
public class RpcEncoder extends MessageToByteEncoder {

    private Class<?> aClass;
    public RpcEncoder(Class responseClass) {
        this.aClass = responseClass;
    }

    @Override
    protected void encode(ChannelHandlerContext channelHandlerContext, Object in, ByteBuf out) throws Exception {
        if (aClass.isInstance(in)) {
            byte[] data = SerializationUtil.serialize(in);
            out.writeInt(data.length);
            out.writeBytes(data);
        }
    }
}
