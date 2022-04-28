package org.ace.websocket.core;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import org.ace.websocket.bo.ChannelPropertyCommon;
import org.ace.websocket.constant.AttributeKeyConst;

public class WebSocketServer
{
    private int port = 7788;
    private String path = "/comm";
    private String password = "comm&ace";

    public WebSocketServer(int port, String path, String password)
    {
        if (port > 0)
        {
            this.port = port;
        }

        if (path != null && !path.trim().isEmpty())
        {
            this.path = path;
        }

        if (password != null && !password.trim().isEmpty())
        {
            this.password = password;
        }
    }

    public void run() throws Exception
    {

        // NioEventLoopGroup是用来处理IO操作的多线程事件循环器
        // boss用来接收进来的连接
        EventLoopGroup bossGroup = new NioEventLoopGroup(); // (1)
        // 用来处理已经被接收的连接;
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        try
        {

            ChannelPropertyCommon cpc = new ChannelPropertyCommon();

            cpc.setPort(port);
            cpc.setPath(path);
            cpc.setPassword(password);

            // 是一个启动NIO服务的辅助启动类
            ServerBootstrap b = new ServerBootstrap(); // (2)

            b.group(bossGroup, workerGroup)
             .channel(NioServerSocketChannel.class) // (3)
             .childHandler(new WebSocketChannelInitializer(path)) //(4) 子处理器
             .option(ChannelOption.SO_BACKLOG, 128) // (5)
             .childOption(ChannelOption.SO_KEEPALIVE, true) // (6)
             .childOption(ChannelOption.SO_REUSEADDR, true) // (7)
             .childAttr(AttributeKeyConst.CHANNEL_PROPERTY_COMMON_KEY, cpc);

            System.out.println("WebSocketServer 启动了");

            // 绑定端口，开始接收进来的连接
            ChannelFuture f = b.bind(port).sync(); // (7)

            // 等待服务器  socket 关闭 。
            // 在这个例子中，这不会发生，但你可以优雅地关闭你的服务器。
            f.channel().closeFuture().sync();

        }
        finally
        {
            workerGroup.shutdownGracefully();
            bossGroup.shutdownGracefully();

            System.out.println("WebSocketServer 关闭了");
        }

    }

    public int getPort()
    {
        return port;
    }

    public void setPort(int port)
    {
        this.port = port;
    }

    public String getPath()
    {
        return path;
    }

    public void setPath(String path)
    {
        this.path = path;
    }

    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

}
