package com.yc.netty.nettyServer.core;

import com.yc.netty.nettyServer.handle.StringUtil;
import com.yc.netty.nettyServer.handle.ThreadPool;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufUtil;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;
import io.netty.util.ReferenceCountUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.net.InetSocketAddress;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @CkassName: ServerHandler
 * @Author: YangChao
 * @Date: 2019/10/23 9:49
 * @Descruotuib:
 * @Version: 1.0
 **/
@Component
public class ServerHandler extends ChannelHandlerAdapter {
    private static final Logger log = LoggerFactory.getLogger(ServerHandler.class);

    // 用来临时保留没有处理过的请求报文
    ByteBuf tempMsg = Unpooled.buffer();

    @Autowired
    private com.yc.netty.nettyServer.handle.HttpReceive HttpReceive;
    public static ServerHandler sh;

    @PostConstruct
    public void init() {
        sh = this;
        sh.HttpReceive = this.HttpReceive;
    }



    // 定义Map放click链接
    private static Map<String, NioSocketChannel> ctxMap = new ConcurrentHashMap<String, NioSocketChannel>();

    // 定义清空实时数据Clean
    private static Map<NioSocketChannel, String> cleanMap = new ConcurrentHashMap<NioSocketChannel, String>();


    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        if (evt instanceof IdleStateEvent) {
            IdleStateEvent event = (IdleStateEvent) evt;
            if (event.state() == IdleState.READER_IDLE) {
                log.info("5秒没有接收到客户端的信息了");
                log.info("关闭这个不活跃的客户端，回收任务");
                // 向后端发送清空本连接缓存和修改点位状态指令
                String DeviceId = cleanMap.get((NioSocketChannel) ctx.channel());
                InetSocketAddress ipSocket = (InetSocketAddress) ctx.channel().remoteAddress();
                String clientIp = ipSocket.getAddress().getHostAddress();
                nettyCleanSend(DeviceId, clientIp);
                ctx.channel().close();
            }
        } else {
            super.userEventTriggered(ctx, evt);
        }
    }

    //每个信息入站都会调用
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        try {
            ByteBuf buf = (ByteBuf) msg;
            String data = ByteBufUtil.hexDump(buf).toUpperCase();
            if (StringUtil.isNull(data)) {
                ReferenceCountUtil.release(msg);
            } else {
                // 截取设备id作为唯一识别
                String DeviceId = data.substring(6, 30);
                if (ctxMap.containsKey(DeviceId)) {
                    ctxMap.replace(DeviceId, (NioSocketChannel) ctx.channel());
                } else {
                    ctxMap.put(DeviceId, (NioSocketChannel) ctx.channel());
                }
                if (cleanMap.containsKey(DeviceId)) {
                    cleanMap.replace((NioSocketChannel) ctx.channel(), DeviceId);
                } else {
                    cleanMap.put((NioSocketChannel) ctx.channel(), DeviceId);
                }
                dataProcess(ctx, msg);// 数据处理
            }
        } finally {
            ReferenceCountUtil.release(msg);
        }
        ctx.flush();
    }

    // 连接被建立并准备进行通信时被调用--初次连接调用
    @Override
    public void channelActive(final ChannelHandlerContext ctx) throws Exception {
        //log.info("连接初步建立");
    }


    //读操作时捕获到异常时调用
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }

    /**
     * 通道断开时调用【可能是设备离线】
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
//        super.channelInactive(ctx);
        //链接关闭，向平台发送通知
        NioSocketChannel channel = (NioSocketChannel) ctx.channel();
        String deviceId = cleanMap.get(channel);
        InetSocketAddress ipSocket =  channel.remoteAddress();
        String clientIp = ipSocket.getAddress().getHostAddress();
        nettyCleanSend(deviceId, clientIp);
    }

    //下发指令,服务器端向客户端下发数据
    public void sendMsg(String DeviceId, String msg) throws Exception {
        String data = hchxy(msg);//先合成和检验
        NioSocketChannel ctx = (NioSocketChannel) ctxMap.get(DeviceId);
        // 数据加密发送
        ByteBuf bufff = Unpooled.buffer();//netty需要用ByteBuf传输
        bufff.writeBytes(StringUtil.hexString2Bytes(sendfilter(data)));
        ctx.writeAndFlush(bufff);
        log.info("下发指令码打印:" + sendfilter(data));
    }

    /**
     * @Description: 数据处理
     * @author YangChao
     * @date 2019/11/18 15:47
     */
    private void dataProcess(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf buf = (ByteBuf) msg;
        log.info("--数据处理--");
        log.info("收到了一次数据包，长度是：" + buf.readableBytes());
        // 合并报文
        ByteBuf message = null;
        int tmpMsgSize = tempMsg.readableBytes();
        // 如果暂存有上一次余下的请求报文，则合并
        if (tmpMsgSize > 0) {
            message = Unpooled.buffer();
            message.writeBytes(tempMsg);
            message.writeBytes(buf);
            log.info("合并：上一数据包余下的长度为：" + tmpMsgSize + ",合并后长度为:" + message.readableBytes());
        } else {
            message = buf;
        }
        String data = ByteBufUtil.hexDump(message).toUpperCase();
        InetSocketAddress ipSocket = (InetSocketAddress) ctx.channel().remoteAddress();
        String clientIp = ipSocket.getAddress().getHostAddress();
        String DeviceId = data.substring(6, 30);// 设备id
        dataUnpack(clientIp, DeviceId, data);// 解包,以及剔除和校验不对的包
    }

    /**
     * @Description: 解包, 以及剔除和校验不对的包
     * @author YangChao
     * @date 2019/11/18 15:57
     */
    private void dataUnpack(String clientIp, String DeviceId, String str) throws Exception {
        String data = rdatafilter(str);// 解密
        boolean flag = jchxy(data); // 验证和校验
        if (flag) {
            String orderCode = StringUtil.hexToDecimal(str.substring(30, 32));
            log.info("连接IP: " + clientIp + "--指令码：" + orderCode + "--设备id：" + DeviceId + "--字符串设备id显示：" + StringUtil.hexStringToString(DeviceId));
            log.info("打印接收到正常数据:" + data);
            /** 返回响应码 **/
            resCode(DeviceId, data);
            // 下发到数据处理类进行数据处理
            nettyDataSend(clientIp, DeviceId, data);
        } else {
            log.error("和校验失败，打印解密错误数据：" + data);
        }

    }

    /**
     * @Description: 返回响应码处理
     * @author YangChao
     * @date 2020/3/24 11:44
     */
    private void resCode(String DeviceId, String data) throws Exception {
        // 1.截取除指令码之外的头和尾
        String dataHead = data.substring(0, 30);// 头
        String dataTail = data.substring(data.length() - 4, data.length()); // 尾
        String Receivecode = StringUtil.hexToDecimal(data.substring(30, 32));// 接收10进制响应码 +1 为返回响应码
        int resCode = 0;
        switch (Receivecode) {
            case "151":
                resCode = 152;
                break;
            case "171":
                resCode = 172;
                break;
            default:
                break;
        }
        // 十进制转十六进制
        String strHex = Integer.toHexString(resCode);// 返回码
        StringBuilder sb = new StringBuilder();
        sb.append(dataHead).append(strHex).append(dataTail);
        sendMsg(DeviceId, sb.toString());
    }

    /**
     * @Description: 发送加密
     * @author YangChao
     * @date 2019/11/18 16:22
     */
    private String sendfilter(String str) {
        StringBuilder sb = new StringBuilder();
        sb.append("C0");
        String len[] = StringUtil.StrToArrayTwo(str);
        for (int i = 0; i < len.length; i++) {
            if (len[i] == "C0") {
                sb.append("DBDC");
            } else if (len[i] == "DB") {
                sb.append("DBDD");
            } else {
                sb.append(len[i]);
            }
        }
        sb.append("C0");
        return sb.toString();
    }

    /**
     * @Description: 接收解密
     * @author YangChao
     * @date 2019/11/18 16:22
     */
    private String rdatafilter(String str) {
        StringBuilder sb = new StringBuilder();
        String len[] = StringUtil.StrToArrayTwo(str);
        for (int i = 0; i < len.length; i++) {
            if ("DB".equals(len[i])) {
                if ("DC".equals(len[i + 1])) {
                    sb.append("C0");
                    i++;
                } else if ("DD".equals(len[i + 1])) {
                    sb.append("DB");
                    i++;
                }
            } else {
                sb.append(len[i]);
            }
        }
        return sb.toString();
    }

    /**
     * @Description: 检查和校验(boolean)
     * @author YangChao
     * @date 2019/11/27 16:13
     */
    private boolean jchxy(String str) {
        boolean flag = false;
        try {
            String len[] = StringUtil.StrToArrayTwo(str);
            String jyh = str.substring(str.length() - 4, str.length() - 2);
            String zzjyh = StringUtil.add0forstr(StringUtil.hex2Binary(jyh));
            String jjyyhh = zzjyh.substring(zzjyh.length() - 8, zzjyh.length());
            int in = 0;
            for (int i = 0; i < len.length - 2; i++) {
                in = Integer.parseInt(StringUtil.hexToDecimal(len[i])) + in;
            }
            String jshjy = StringUtil.add0forstr(Integer.toBinaryString(in));
            String jshjy1 = jshjy.substring(jshjy.length() - 8, jshjy.length());
            if (jjyyhh.equals(jshjy1)) {
                flag = true;
            } else {
                flag = false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.error("异常数据:" + str);
        }
        return flag;
    }

    /**
     * @Description: 合成和效验
     * @author YangChao
     * @date 2019/11/27 16:17
     */
    private String hchxy(String str) {
        String len[] = StringUtil.StrToArrayTwo(str);
        int in = 0;
        for (int i = 0; i < len.length - 2; i++) {
            in = Integer.parseInt(StringUtil.hexToDecimal(len[i])) + in;
        }
        String hcjy = StringUtil.add0forstr(Integer.toHexString(in));
        String hcjyh = hcjy.substring(hcjy.length() - 2, hcjy.length());
        str = str.substring(2, str.length() - 4) + hcjyh;
        return str;
    }

    /**
     * @Description: netty下发数据
     * @author YangChao
     * @date 2020/3/26 11:08
     */
    public void nettyDataSend(String clientIp, String DeviceId, String data) {
        ThreadPool.executor.execute(() -> {
            //模拟任务内部处理耗时
            try {
                sh.HttpReceive.httpGetJson(clientIp, DeviceId, data);
            } catch (Exception e) {
                e.printStackTrace();
            }
            log.info(Thread.currentThread().getName()+ "线程池下发数据处理完毕");
        });
    }

    //@Scheduled(initialDelay = 1000, fixedRate = 300000)
    //public void main() throws Exception{
    //    System.err.println("10S");
    //    String clientIp = "172.16.12.36";
    //    String DeviceId = "323032303035303730303234";
    //    String data = "C06500323032303035303730303234AB230000000000000000000000050000008BFD01000000000300FF00790000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000003038CE5E00000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000E6C0";
    //    sh.HttpReceive.httpGetJson(clientIp, DeviceId, data);
    //}


    /**
    * @Description: netty向后端下发清空指令,修改状态
    *
    */
    public void nettyCleanSend(String DeviceId, String clientIp) {

        ThreadPool.executor.execute(() -> {
            try {
                sh.HttpReceive.httpCleanSend( DeviceId, clientIp );
            } catch (Exception e) {
                e.printStackTrace();
            }
            log.info(Thread.currentThread().getName()+ "线程池下发数据处理完毕");
        });

    }


}
