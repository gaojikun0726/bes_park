package org.ace.TftpServer;

/**
 * @Author: wanghongjie
 * @Description:
 * @Date: Created in 9:10 2020/11/4
 * @Modified By:
 */
public class ERRORPacket extends TFtpPacket {
    /**
     * 错误码
     * Value Meaning
     * 0 未定义，请参阅错误信息（如果提示这种信息的话）
     * 1 文件未找到
     * 2 访问非法
     * 3 磁盘满或超过分配的配额
     * 4 非法的TFTP操作
     * 5 未知的传输ID
     * 6 文件已经存在
     * 7 没有类似的用户
     */
    public static final int NOT_DEFINED_CODE = 0;
    public static final int FILE_NOT_FOUND_CODE = 1;
    public static final int ILLEGAL_ACCESS_CODE = 2;
    public static final int DISK_FULL_CODE = 3;
    public static final int ILLEGAL_OPERATE_CODE = 4;
    public static final int UNKNOWN_ID_CODE = 5;
    public static final int FILE_EXIST_CODE = 6;
    public static final int USER_NOT_EXIST_CODE = 7;


    private int errorCode;
    private String errorMessage;

    public ERRORPacket() {
        setOperateCode(TFtpPacket.ERROR);
    }

    /**
     * @return
     */
    public int getErrorCode() {
        return errorCode;
    }

    /**
     * @return
     */
    public String getErrorMessage() {
        return errorMessage;
    }

    /**
     * @param i
     */
    public void setErrorCode(int i) {
        errorCode = i;
    }

    /**
     * @param string
     */
    public void setErrorMessage(String string) {
        errorMessage = string;
    }

    public String toString() {
        return "Error packet:[errorCode="+errorCode+"][errorMessage="+errorMessage+"]";
    }
}
