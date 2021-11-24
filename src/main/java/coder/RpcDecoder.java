package coder;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import io.netty.util.concurrent.EventExecutorGroup;
import utils.SerializationUtil;

import java.util.List;

/**
 * @author 朱林
 * @description
 * @date 2021/11/23 17:08
 */
public class RpcDecoder extends ByteToMessageDecoder {
    private Class<?> aClass;

    public RpcDecoder(Class requestClass) {
        this.aClass = requestClass;
    }

    @Override
    protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf in, List<Object> out) throws Exception {
        if (in.readableBytes() < 4) {
            return;
        }
        in.markReaderIndex();
        int dataLength = in.readInt();
        if (in.readableBytes() < dataLength) {
            in.resetReaderIndex();
            return;
        }
        byte[] data = new byte[dataLength];
        in.readBytes(data);
        out.add(SerializationUtil.deserialize(data, aClass));
    }
}
