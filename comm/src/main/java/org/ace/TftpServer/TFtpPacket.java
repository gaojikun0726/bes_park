package org.ace.TftpServer;

/**
 * @Author: wanghongjie
 * @Description:
 * @Date: Created in 9:08 2020/11/4
 * @Modified By:
 */
public class TFtpPacket {

        public static final int UNKNOWN = 0;  //unknown
        public static final int RRQ = 1;    //read request
        public static final int WRQ = 2;    //write request
        public static final int DATA = 3;  //data
        public static final int ACK = 4;    //Acknowledgment
        public static final int ERROR = 5;  //error

        private int operateCode = UNKNOWN;

        public int getOperateCode() {
            return operateCode;
        }

        /**
         * @param i
         */
        public void setOperateCode(int i) {
            operateCode = i;
        }

    }