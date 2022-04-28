package com.zc.netty.polelight.server;

import com.zc.netty.polelight.config.ServerConfig;
import com.zc.netty.polelight.util.SpringUtils;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.timeout.IdleStateHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;

/**
 * nettyServer类
 */
public class NettyServer implements Runnable{
    private static final Logger log = LoggerFactory.getLogger(NettyServer.class);
    private EventLoopGroup bossGroup = null;
    private EventLoopGroup workerGroup = null;
//    private List<Integer> portList;
//    private List<String> handlerList;

    private ServerConfig serverConfig = SpringUtils.getBean(ServerConfig.class);

    /**
     * 类级的内部类，也就是静态的成员式内部类，该内部类的实例与外部类的实例
     * 没有绑定关系，而且只有被调用到才会装载，从而实现了延迟加载
     */
    private static class NettyServerHolder{
        /**
         * 静态初始化器，由JVM来保证线程安全
         */
        private static NettyServer instance = new NettyServer();
    }
    /**
     * 私有化构造方法
     */
    private NettyServer(){

    }
    public static  NettyServer getInstance(){
        return NettyServer.NettyServerHolder.instance;
//        return new NettyServer();
    }

//    public List<Integer> getPortList() {
//        return portList;
//    }
//
//    public void setPortList(List<Integer> portList) {
//        this.portList = portList;
//    }
//
//    public List<String> getHandlerList() {
//        return handlerList;
//    }
//
//    public void setHandlerList(List<String> handlerList) {
//        this.handlerList = handlerList;
//    }

    @Override
    public void run() {
        runServer();
    }

    public void runServer(){
        bossGroup = new NioEventLoopGroup();// 用来接收进来的连接
        workerGroup = new NioEventLoopGroup();//用来处理已经被接收的连接
        try {
            ServerBootstrap serverBootstrap = new ServerBootstrap();
            serverBootstrap.group(bossGroup, workerGroup)
                    .channel(NioServerSocketChannel.class); // (3)
//                    .option(ChannelOption.SO_BACKLOG, 128)//服务端接受连接的队列长度
//                    .option(ChannelOption.SO_REUSEADDR, true)//允许重复使用本地地址和端口
//                    .option(ChannelOption.SO_RCVBUF, 10 * 1024)//用于操作接收缓冲区大小
//                    .option(ChannelOption.SO_SNDBUF, 10 * 1024)//用于操作发送缓冲区大小
//                    .option(EpollChannelOption.SO_REUSEPORT, true);
//			serverBootstrap.channel(NioServerSocketChannel.class);
//			serverBootstrap.option(ChannelOption.SO_BACKLOG, 100); // 连接数so_backlog
//			serverBootstrap.option(ChannelOption.TCP_NODELAY, true); // 不延迟，消息立即发送tcp_nodelay
//			serverBootstrap.childOption(ChannelOption.SO_KEEPALIVE, true); // 长连接so_keepalive
             serverBootstrap.childHandler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    public void initChannel(SocketChannel ch) throws Exception {
                        //ByteBuf delimiter = Unpooled.copiedBuffer("\n".getBytes());
                        ChannelPipeline pipeline = ch.pipeline();
                        //心跳检测
                        pipeline.addLast(new IdleStateHandler(10, 0, 0, TimeUnit.SECONDS));
                        //pipeline.addLast(new DelimiterBasedFrameDecoder(1024, delimiter));
                        //pipeline.addLast(new DecodeHandler()).addLast(new StringEncoder());
                        pipeline.addLast(new NettyServerHandler());
//                        pipeline.addLast(new HeartbeatHandler());
                        // 超时处理：参数分别为读超时时间、写超时时间、读和写都超时时间、时间单位
//						ch.pipeline().addLast(new IdleStateHandler(10, 10, 10, TimeUnit.MINUTES));
                    }
                });
//            //  netty资源泄露，日志打印，加在这里
//            ResourceLeakDetector.setLevel(ResourceLeakDetector.Level.ADVANCED);
             Integer port = serverConfig.serverPort;
                Channel serverChannel = serverBootstrap.bind(port).sync().channel();
                log.info("启动Netty服务成功，端口号：" + port);
                serverChannel.closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally{
            log.info("退出，释放线程池资源！！");
            //退出，释放线程池资源
            workerGroup.shutdownGracefully();
            bossGroup.shutdownGracefully();
        }
    }

    public static void main(String[] args) throws Exception {
        new NettyServer().run();
    }
}
