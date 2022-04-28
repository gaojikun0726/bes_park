package org.ace.TftpServer;

/**
 * @Author: wanghongjie
 * @Description:
 * @Date: Created in 9:10 2020/11/4
 * @Modified By:
 */
public class DATAPacket extends TFtpPacket {
    private int block;
    private byte[] data;

    public DATAPacket() {
        setOperateCode(TFtpPacket.DATA);
    }

    /**
     * @return
     */
    public int getBlock() {
        return block;
    }

    /**
     * @return
     */
    public byte[] getData() {
        return data;
    }

    /**
     * @param block
     */
    public void setBlock(int block) {
        this.block = block;
    }

    /**
     * @param data
     */
    public void setData(byte[] data) {
        this.data = data;
    }

    public String toString() {
        return "Data packet:[block="+block+"][data size="+(data==null?0:data.length)+"]";
    }
}