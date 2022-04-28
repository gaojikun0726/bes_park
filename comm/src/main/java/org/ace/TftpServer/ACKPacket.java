package org.ace.TftpServer;

/**
 * @Author: wanghongjie
 * @Description:
 * @Date: Created in 9:11 2020/11/4
 * @Modified By:
 */
public class ACKPacket extends TFtpPacket {
    private int block;

    public ACKPacket() {
        setOperateCode(TFtpPacket.ACK);
    }

    /**
     * @return
     */
    public int getBlock() {
        return block;
    }

    /**
     * @param i
     */
    public void setBlock(int i) {
        block = i;
    }

    public String toString() {
        return "Ack packet:[block="+block+"]";
    }

}
