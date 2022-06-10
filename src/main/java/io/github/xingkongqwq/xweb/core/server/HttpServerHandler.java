package io.github.xingkongqwq.xweb.core.server;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.*;
import io.netty.util.CharsetUtil;

public class HttpServerHandler extends SimpleChannelInboundHandler<HttpObject> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, HttpObject msg) {
        if (msg instanceof HttpRequest) {
            HttpRequest request = (HttpRequest) msg;
            String uri = request.uri();
            System.out.println(uri);
            if (uri.contains("favicon.ico")) {
                return;
            }
            ByteBuf content = Unpooled.copiedBuffer("Hello,我是服务器", CharsetUtil.UTF_8);
            FullHttpResponse httpResponse
                    = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.OK, content);
            httpResponse.headers().set(HttpHeaderNames.CONTENT_TYPE, "text/plain;charset=utf-8");
            httpResponse.headers().set(HttpHeaderNames.CONTENT_LENGTH, content.readableBytes());
            ctx.writeAndFlush(httpResponse);
        }
    }
}
