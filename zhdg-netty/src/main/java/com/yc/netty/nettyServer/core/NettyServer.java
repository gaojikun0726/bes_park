package com.yc.netty.nettyServer.core;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import io.netty.handler.timeout.IdleStateHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * @CkassName: Server
 * @Author: YangChao
 * @Date: 2019/10/23 9:48
 * @Descruotuib:
 * @Version: 1.0
 **/
@Component
@Slf4j
public class NettyServer {
    @Value("${neetServer.port}")
    private int port ;

    public NettyServer(){
        
    }

    public NettyServer(int port) {
        this.port = port;
    }

    public void start() {
        EventLoopGroup boosGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();

        try {
            ServerBootstrap sb = new ServerBootstrap();
            sb.group(boosGroup, workerGroup)
                    .channel(NioServerSocketChannel.class)
                    .handler(new LoggingHandler(LogLevel.INFO))
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        protected void initChannel(SocketChannel ch) throws Exception {
                            //检测连接有效性（心跳）,此处功能：5秒内read()未被调用则触发一次useEventTrigger()方法
                            ch.pipeline().addLast(new IdleStateHandler(15, 0, 0, TimeUnit.SECONDS));
                            ch.pipeline().addLast(new ServerHandler());  //ServerHandler实现了业务逻辑

                        }
                    })
                    //服务端接受连接的队列长度，如果队列已满，客户端连接将被拒绝
                    .option(ChannelOption.SO_BACKLOG, 1000)
                    //Socket参数，连接保活，默认值为False。启用该功能时，TCP会主动探测空闲连接的有效性。
                    // 可以将此功能视为TCP的心跳机制，需要注意的是：默认的心跳间隔是7200s即2小时。Netty默认关闭该功能。
                    .childOption(ChannelOption.SO_KEEPALIVE, true);
            //绑定端口，开始接收进来的连接
            ChannelFuture future = sb.bind(port).sync(); //绑定服务器，等待绑定完成，调用sync()的原因是当前线程阻塞

            System.out.println("nettyServer启动成功:" + port);
            future.channel().closeFuture().sync();  //关闭channel和块，直到它被关闭
        } catch (Exception e) {
            boosGroup.shutdownGracefully();   //关闭EventLoopGroup，释放所有资源（包括所有创建的线程）
            workerGroup.shutdownGracefully();
        }
    }


    public static void main(String[] args) throws Exception {

        int port;
        if(args.length > 0) {
            port = Integer.parseInt(args[0]);
        } else {
            port = 60010;
        }
        new NettyServer(port).start();

    }

}
