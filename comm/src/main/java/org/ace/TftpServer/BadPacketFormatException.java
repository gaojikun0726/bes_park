package org.ace.TftpServer;

/**
 * @Author: wanghongjie
 * @Description:
 * @Date: Created in 9:09 2020/11/4
 * @Modified By:
 */
public class BadPacketFormatException extends Exception {

    public BadPacketFormatException() {
        super();
    }

    public BadPacketFormatException(String msg) {
        super(msg);
    }

}
