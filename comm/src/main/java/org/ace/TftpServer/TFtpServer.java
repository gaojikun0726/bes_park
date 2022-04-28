package org.ace.TftpServer;

import org.ace.websocket.handler.WebSocketService;

import java.io.*;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.net.SocketException;
import java.util.HashMap;
import java.util.Timer;


/**
 * @Author: wanghongjie
 * @Description:
 * @Date: Created in 8:56 2020/11/4
 * @Modified By:
 */
public class TFtpServer extends Thread {
    public static final int DEFAULT_PORT = 9010;

    private HashMap map = new HashMap();

    private File root;

    private int port = DEFAULT_PORT;

    private DatagramSocket ds;

    private Timer timer = new Timer(true);

    public TFtpServer() {
        this(".");
    }

    public TFtpServer(String rootPath, int port) {
        this(rootPath);
        this.port = port;
    }

    public TFtpServer(String rootPath) {
        this.root = new File(rootPath);
        try {
            this.root = this.root.getCanonicalFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void run() {
        try {
            ds = new DatagramSocket(new InetSocketAddress(port));
        } catch (SocketException e) {
            System.out.println("error when create datagram socket:"
                    + e.getMessage());
            return;
        }
        System.out.println("root path:" + root.getPath());
        byte[] buf = new byte[516];
        DatagramPacket packet = new DatagramPacket(buf, 516);
        while (true) {
            try {
                ds.receive(packet);
//                System.out.println("recv packet:");
                TFtpPacketUtil.displayBytes(packet.getData());//
                process(packet);
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
    }

    protected void process(DatagramPacket packet) {
        try {
            byte[] buf = new byte[packet.getLength()];
            System.arraycopy(packet.getData(), 0, buf, 0, buf.length);
            TFtpPacket tfp = TFtpPacketUtil.decode(buf);
            InetSocketAddress address = new InetSocketAddress(packet
                    .getAddress(), packet.getPort());
            String key = address.getHostName() + ":" + address.getPort();
            System.out.println("key=" + key);
            System.out.println("packet:" + tfp);
            if (tfp.getOperateCode() == TFtpPacket.RRQ
                    || tfp.getOperateCode() == TFtpPacket.WRQ) {
                Client client = new Client(address);
                boolean result = client.doAccept((RWPacket) tfp);
                if (result) {
                    map.put(key, client);
                }
            } else if (tfp.getOperateCode() == TFtpPacket.DATA) {
                Client client = (Client) map.get(key);
                if (client == null) {
                    return;
                }
                boolean result = client.doProcess((DATAPacket) tfp);
                if (result) {
                    map.remove(key);
                    client.destroy();
                }
            } else if (tfp.getOperateCode() == TFtpPacket.ACK) {
                Client client = (Client) map.get(key);
                if (client == null) {
                    return;
                }
                boolean result = client.doProcess((ACKPacket) tfp);
                if (result) {
                    map.remove(key);
                    client.destroy();
                }
            } else if (tfp.getOperateCode() == TFtpPacket.ERROR) {
                System.out.println(tfp);
                Client client = (Client) map.remove(key);
                // 推送消息到web客户端
                String msg = ("系统找不到指定文件,请确认文件名称");
                WebSocketService.postEvent(WebSocketEvent.RemoteUpgrade, msg);
//                client.destroy();
            }
        } catch (BadPacketFormatException | UnsupportedEncodingException e) {
            e.printStackTrace();
            System.out.println("recv unknown packet.");
        }
    }

    protected void send(InetSocketAddress address, byte[] data) {
        DatagramPacket packet = new DatagramPacket(data, data.length, address
                .getAddress(), address.getPort());
        try {
            ds.send(packet);
            System.out.println("send packet:");
            TFtpPacketUtil.displayBytes(data);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    class Client {
        public static final int DEFAULT_DATA_SIZE = 512;

        public static final int DEFAULT_TIME_OUT = 1000;

        private InetSocketAddress address;

        private String fileName;

        private int block;

        private boolean checked;//上次发送的包已收到回应

        private RandomAccessFile raf;

        private boolean accepted = false;

        private byte[] buf = new byte[DEFAULT_DATA_SIZE];

        private byte[] data;

        private int times = 0;//每几次重发

        private ResendTask task;


        public Client(InetSocketAddress address) {
            this.address = address;
        }

        public void destroy() {
            if (task != null) {
                task.cancel();
            }
            if (raf != null) {
                try {
                    raf.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                raf = null;
            }
        }


        public boolean doAccept(RWPacket packet) {
            this.fileName = packet.getFileName();
            if (accepted) {
                return true;
            }
            if (packet.getOperateCode() == TFtpPacket.RRQ) {
                try {
                    File file = new File(root, fileName);
                    System.out.println(file.getPath() + " " + file.exists());
                    //如果本地磁盘下没有客户端想要的文件名字,则结束当前传输
                    if (!file.exists()) {

                    }
                    raf = new RandomAccessFile(file, "r");
                    data = new byte[DEFAULT_DATA_SIZE];
                    int size = raf.read(data);

                    if (size == -1) {//如果文件里面的内容为空,则走异常处理
                        throw new FileNotFoundException();
                    }
                    DATAPacket dp = new DATAPacket();
                    dp.setBlock(1);
                    if (size != DEFAULT_DATA_SIZE) {
                        byte[] buf = new byte[size];
                        System.arraycopy(data, 0, buf, 0, size);
                        data = buf;
                    }
                    dp.setData(data);
                    data = TFtpPacketUtil.encode(dp);
                    send(address, data);
                    block = 1;
                    task = new ResendTask(address, data);
                    timer.schedule(task, DEFAULT_TIME_OUT);
                } catch (FileNotFoundException e) {
                    System.out
                            .println("file:" + e.getMessage() + " not found.");
                    ERRORPacket ep = new ERRORPacket();
                    ep.setErrorCode(ERRORPacket.FILE_NOT_FOUND_CODE);
                    ep.setErrorMessage(e.getMessage());
                    byte[] data = TFtpPacketUtil.encode(ep);
                    send(address, data);
                    return false;
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                File file = new File(root, fileName);
                if (file.exists()) {
                    ERRORPacket ep = new ERRORPacket();
                    ep.setErrorCode(ERRORPacket.FILE_EXIST_CODE);
                    ep.setErrorMessage("file:" + fileName + " has exist");
                    byte[] data = TFtpPacketUtil.encode(ep);
                    send(address, data);
                    return false;
                }
                try {
                    file.createNewFile();
                    raf = new RandomAccessFile(file, "rwd");
                } catch (IOException e) {
                    System.out.println("create file:" + fileName + " failed.");
                    ERRORPacket ep = new ERRORPacket();
                    ep.setErrorCode(ERRORPacket.NOT_DEFINED_CODE);
                    byte[] data = TFtpPacketUtil.encode(ep);
                    send(address, data);
                    return false;
                }
                ACKPacket ap = new ACKPacket();
                ap.setBlock(0);
                data = TFtpPacketUtil.encode(ap);
                send(address, data);
                task = new ResendTask(address, data);
                timer.schedule(task, DEFAULT_TIME_OUT);
            }
            accepted = true;
            return accepted;
        }

        public boolean doProcess(ACKPacket packet) {
            if (task != null) {
                task.cancel();
                task = null;
            }
            if (packet.getBlock() == block) {
                try {
                    if (raf == null
                            || raf.length() <= block * DEFAULT_DATA_SIZE) {
                        return true;
                    }
                    raf.seek(block * DEFAULT_DATA_SIZE);
                    int size = raf.read(buf);
                    data = buf;
                    if (size < DEFAULT_DATA_SIZE) {
                        data = new byte[size];
                        System.arraycopy(buf, 0, data, 0, size);
                        raf.close();
                        raf = null;
                    }
                    DATAPacket dp = new DATAPacket();
                    block++;
                    dp.setBlock(block);
                    dp.setData(data);
                    data = TFtpPacketUtil.encode(dp);
                    send(address, data);
                    TFtpPacketUtil.displayBytes(data);
                    task = new ResendTask(address, data);
                    timer.schedule(task, DEFAULT_TIME_OUT);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return false;
        }


        public boolean doProcess(DATAPacket packet) {
            if (task != null) {
                task.cancel();
                task = null;
            }
            byte[] data = packet.getData();
            if (packet.getBlock() != block + 1) {
                return false;
            }
            block++;
            ACKPacket ap = new ACKPacket();
            ap.setBlock(block);
            if (data != null) {
                try {
                    raf.write(data);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (data == null || data.length < DEFAULT_DATA_SIZE) {
                try {
                    raf.close();
                    raf = null;
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            data = TFtpPacketUtil.encode(ap);
            send(address, data);
            task = new ResendTask(address, data);
            timer.schedule(task, DEFAULT_TIME_OUT);
            return packet.getData() == null
                    || packet.getData().length < DEFAULT_DATA_SIZE;
        }

    }

    class ResendTask extends java.util.TimerTask {
        private InetSocketAddress address;

        private byte[] data;

        private int times = 1;

        public ResendTask(InetSocketAddress address, byte[] data) {
            this.address = address;
            this.data = data;
        }

        public ResendTask(InetSocketAddress address, byte[] data,int times) {
            this.address = address;
            this.data = data;
            this.times = times;
        }

        public void run() {
            send(address, data);
            if (times < 3) {//最多发3次
                timer.schedule(new ResendTask(address,data,times+1), (int) (Math.pow(2, times+1))
                        * Client.DEFAULT_TIME_OUT);
            }
        }

        public byte[] getData() {
            return data;
        }
    }

    public static void main(String[] args) {
        new TFtpServer("d://").start();
    }

    /**
     * @return
     */
    public File getRoot() {
        return root;
    }

    /**
     * @return
     */
    public int getPort() {
        return port;
    }

    /**
     * @param i
     */
    public void setPort(int i) {
        port = i;
    }

}
