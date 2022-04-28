package org.ace.TftpServer;

import java.io.UnsupportedEncodingException;

/**
 * @Author: wanghongjie
 * @Description:
 * @Date: Created in 9:07 2020/11/4
 * @Modified By:
 */
public final class TFtpPacketUtil {
    private TFtpPacketUtil() {

    }

    public static TFtpPacket decode(byte[] buf) throws BadPacketFormatException, UnsupportedEncodingException {
        if (buf==null || buf.length<4) {
            throw new BadPacketFormatException("packet too small");
        }
        int operateCode = buf[1];
        if (operateCode<TFtpPacket.RRQ || operateCode>TFtpPacket.ERROR) {
            throw new BadPacketFormatException("unknown operate code");
        }
        switch (operateCode) {
            case TFtpPacket.RRQ:
            case TFtpPacket.WRQ:
                return decodeRWPacket(buf);
            case TFtpPacket.DATA:
                return decodeDATAPacket(buf);
            case TFtpPacket.ACK:
                return decodeACKPacket(buf);
            case TFtpPacket.ERROR:
                return decodeERRORPacket(buf);
        }
        return null;
    }

    /**
     * 2 bytes       2 bytes string 1 byte
     * -----------------------------------------
     * | Opcode | ErrorCode | ErrMsg | 0 |
     * -----------------------------------------
     */
    private static TFtpPacket decodeERRORPacket(byte[] buf) throws BadPacketFormatException {
        if (buf.length<4) {
            throw new BadPacketFormatException("ack packet must >= 4 bytes");
        }
        ERRORPacket packet = new ERRORPacket();
        packet.setOperateCode(TFtpPacket.ERROR);
        packet.setErrorCode(buf[2]<<8+buf[3]);
        int end = 4;
        int i = 4;
        for (;i<buf.length;i++) {
            if (buf[i]==0) {
                end = i;
                break;
            }
        }
        if (i!=buf.length) {
            String str = new String(buf,4,end-4);
            packet.setErrorMessage(str);
        }
        return packet;
    }


    /**
     * 2 bytes 2 bytes
     * ---------------------
     * | Opcode | Block # |
     * ---------------------
     * @param buf
     * @return
     */
    private static TFtpPacket decodeACKPacket(byte[] buf) throws BadPacketFormatException {
        displayBytes(buf);
        if (buf.length<4) {
            throw new BadPacketFormatException("ack packet must be 4 bytes");
        }
        ACKPacket packet = new ACKPacket();
        packet.setOperateCode(TFtpPacket.ACK);
        //System.out.println("buf[2]="+buf[2]+" buf[3]="+buf[3]+" total="+(buf[2]*256+buf[3]));
        packet.setBlock(byte2int(buf[2])*256+byte2int(buf[3]));
        //System.out.println("block:"+packet.getBlock());
        return packet;
    }


    /**
     * 2 bytes   2 bytes   n bytes
     * ----------------------------------
     * | Opcode | Block # | Data |
     * ----------------------------------
     * @param buf
     * @return
     */
    private static TFtpPacket decodeDATAPacket(byte[] buf) throws BadPacketFormatException  {
        if (buf.length<4) {
            throw new BadPacketFormatException("bad data packet");
        }
        DATAPacket packet = new DATAPacket();
        packet.setOperateCode(TFtpPacket.DATA);
        packet.setBlock(byte2int(buf[2])*256+byte2int(buf[3]));
        //System.out.println("decode data packet,block:"+packet.getBlock());
        byte[] data = new byte[buf.length-4];
        System.arraycopy(buf,4,data,0,data.length);
        packet.setData(data);
        return packet;
    }

    private static int byte2int(byte b) {
        if ((b&0x80)!=0) {
            return 128+(b&0x7f);
        }
        else {
            return b;
        }
    }


    /**
     * RRQ,WRQ packet format
     *  2 bytes    string   1 byte string 1 byte
     *  ------------------------------------------------
     *  | Opcode | Filename | 0 | Mode | 0 |
     *  ------------------------------------------------
     * @param buf
     * @return
     */
    private static TFtpPacket decodeRWPacket(byte[] buf) throws BadPacketFormatException, UnsupportedEncodingException {
        RWPacket packet = new RWPacket();
        packet.setOperateCode((int)buf[1]);
        int start = 2;
        int end = 2;
        int i;
        for (i=start;i<buf.length;i++) {
            if (buf[i]==0) {
                end = i;
                break;
            }
        }
        if (i==buf.length) {
            throw new BadPacketFormatException();
        }
        packet.setFileName(new String(buf,start,end-start,"GBK"));
        start = end+1;
        end = start;
        for (i=start;i<buf.length;i++) {
            if (buf[i]==0) {
                end = i;
                break;
            }
        }
        if (i==buf.length) {
            throw new BadPacketFormatException();
        }
        packet.setMode(new String(buf,start,end-start));
        return packet;
    }


    public static byte[] encode(TFtpPacket packet) {
        int operateCode = packet.getOperateCode();
        switch (operateCode) {
            case TFtpPacket.RRQ:
            case TFtpPacket.WRQ:
                return encodeRWPacket((RWPacket)packet);
            case TFtpPacket.DATA:
                return encodeDATAPacket((DATAPacket)packet);
            case TFtpPacket.ACK:
                return encodeACKPacket((ACKPacket)packet);
            case TFtpPacket.ERROR:
                return encodeERRORPacket((ERRORPacket)packet);

        }
        return null;
    }


    /**
     * @param packet
     * @return
     */
    private static byte[] encodeERRORPacket(ERRORPacket packet) {
        byte[] messageBytes = null;
        if (packet.getErrorMessage()!=null) {
            messageBytes = packet.getErrorMessage().getBytes();
        }
        int size = messageBytes==null?0:messageBytes.length;
        size += 2+2+1;
        byte[] buf = new byte[size];
        buf[1] = (byte)(TFtpPacket.ERROR&0xff);
        int errorCode = packet.getErrorCode();
        buf[2] = (byte)((errorCode>>8)&0xff);
        buf[3] = (byte)(errorCode&0xff);
        if (messageBytes!=null) {
            System.arraycopy(messageBytes,0,buf,4,messageBytes.length);
        }
        return buf;
    }


    /**
     * @param packet
     * @return
     */
    private static byte[] encodeACKPacket(ACKPacket packet) {
        byte[] buf = new byte[4];
        buf[1] = (byte)(TFtpPacket.ACK&0xff);
        int block = packet.getBlock();
        buf[2] = (byte)((block>>8)&0xff);
        buf[3] = (byte)(block&0xff);
        return buf;
    }


    /**
     * @param packet
     * @return
     */
    private static byte[] encodeDATAPacket(DATAPacket packet) {
        byte[] data = packet.getData();
        int size = 2+2;
        if (data!=null) {
            size += data.length;
        }
        byte[] buf = new byte[size];
        System.out.println("encode data packet,buf[0]="+Integer.toHexString(buf[0]));
        buf[1] = (byte)(TFtpPacket.DATA&0xff);
        int block = packet.getBlock();
        buf[2] = (byte)((block>>8)&0xff);
        buf[3] = (byte)(block&0xff);
        if (data!=null) {
            System.arraycopy(data,0,buf,4,data.length);
        }
        return buf;
    }


    /**
     * @param packet
     * @return
     */
    private static byte[] encodeRWPacket(RWPacket packet) {
        int operateCode = packet.getOperateCode();
        int size = 2;
        String fileName = packet.getFileName();
        String mode = packet.getMode();
        byte[] nameBytes = fileName.getBytes();//fileName must not null
        size += nameBytes.length+1;
        byte[] modeBytes = null;
        if (mode!=null) {
            modeBytes = mode.getBytes();
            size += modeBytes.length+1;
        }
        byte[] buf = new byte[size];
        buf[1] = (byte)(operateCode&0xff);
        System.arraycopy(nameBytes,0,buf,2,nameBytes.length);
        if (modeBytes!=null) {
            int pos = 2+nameBytes.length+1;
            System.arraycopy(modeBytes,0,buf,pos,modeBytes.length);
        }
        return buf;
    }

    static int count = 0;

    public static void displayBytes(byte[] buf) {
        count++;
        if (buf==null) {
            System.out.println(count+":"+"null");
            return ;
        }
        StringBuffer sb = new StringBuffer();
        int len = buf.length>10?10:buf.length;
        for (int i=0;i<len;i++) {
            sb.append(Integer.toHexString(buf[i]));
        }
//        System.out.println(count+":"+sb.toString());
    }

}