package org.ace.socketserver.core;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import org.ace.socketserver.bo.ChannelPropertyCommon;
import org.ace.socketserver.constant.AttributeKeyConst;

/**
 * Socket Server服务启动类
 * @author xiepufeng
 */
public class SocketServer
{
    private int port = 7789;
    private String password;

    public SocketServer(int port, String password)
    {
        if (port > 0)
        {
            this.port = port;
        }

        this.password = password;
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

            // 通道属性定义（类似）
            ChannelPropertyCommon cpc = new ChannelPropertyCommon();

            // 设置端口端口
            cpc.setPort(port);
            // 设置密码
            cpc.setPassword(password);

            // 是一个启动NIO服务的辅助启动类
            ServerBootstrap b = new ServerBootstrap(); // (2)

            // 设置 ServerBootstrap 要用的 EventLoopGroup
            b.group(bossGroup, workerGroup)
                    // 设置将要被实例化的 ServerChannel类
                    .channel(NioServerSocketChannel.class) // (3)
                    .childHandler(new SocketServerChannelInitializer()) //(4) 子处理器
                    // BACKLOG用于构造服务端套接字ServerSocket对象，标识当服务器请求处理线程全满时，
                    // 用于临时存放已完成三次握手的请求的队列的最大长度。如果未设置或所设置的值小于1，
                    // Java将使用默认值50。
                    .option(ChannelOption.SO_BACKLOG, 1204) // (5)
                    //  是否启用心跳保活机制。在双方TCP套接字建立连接后（即都进入ESTABLISHED状态）
                    // 并且在两个小时左右上层没有任何数据传输的情况下，这套机制才会被激活。
                    .childOption(ChannelOption.SO_KEEPALIVE, true) // (6)
                    .childAttr(AttributeKeyConst.CHANNEL_PROPERTY_COMMON_KEY, cpc);

            System.out.println("SocketServer 启动了");

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

            System.out.println("SocketServer 关闭了");
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


    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }
}
