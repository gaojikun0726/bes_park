package org.ace.business.constant;

/**
 * 下位机消息结果定义
 * @author xiepufeng
 * @date 2020/4/16 14:23
 */
public class Code
{
    public final static Integer SUCCEED = 0; // 成功
	
	public final static Integer DATA_LENGTH = 1; // 数据长度错误

    public final static Integer ERR_CRC = 2; // crc 校验错误

    public final static Integer ERR_PARAM = 3; // 参数错误

    public final static Integer ERR_UNKNOWN = 4; // 未知错误
}
