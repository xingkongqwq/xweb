package io.github.xingkongqwq.xweb.core;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;
import java.util.Objects;

public final class HttpServer {
    private final Logger logger = LogManager.getLogger(this.getClass());
    private final EventLoopGroup boss = new NioEventLoopGroup(1);
    private final EventLoopGroup work = new NioEventLoopGroup(1);
    private static HttpServer hs = null;
    private HashMap<String,Class<?>> routers;
    private HttpServer(){}
    public static HttpServer getServer(){
        if(Objects.isNull(hs)) hs = new HttpServer();
        return hs;
    }
    public HttpServer addRouter(String url,Class<?> routerClass){
        routers.put(url,routerClass);
        return this;
    }
    public HttpServer delRouter(String url){
        routers.remove(url);
        return this;
    }
    public HttpServer replaceRouter(Class<?> routerClass1,Class<?> routerClass2){
        for (HashMap.Entry<String, Class<?>> entry : routers.entrySet()) {
            if(entry.getValue().equals(routerClass1)){
                entry.setValue(routerClass2);
            }
        }
        return this;
    }
    public HttpServer run() throws InterruptedException {
        ServerBootstrap sb = new ServerBootstrap();
        sb.group(boss, work)
                .channel(NioServerSocketChannel.class)
                .childHandler(new HttpChannelInitializer());

        ChannelFuture cf = sb.bind(8888).sync();
        cf.addListener(future -> {
            if (future.isSuccess()) {
                logger.info("http is ready");
            }
        });
        cf.channel().closeFuture().sync();
        return this;
    }
}
