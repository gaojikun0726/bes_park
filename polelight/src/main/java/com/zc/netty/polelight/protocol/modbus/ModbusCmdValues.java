package com.zc.netty.polelight.protocol.modbus;

/**
 * describe:
 *
 * @author zs
 * @date 2021/2/6
 */
public class ModbusCmdValues {

    /**
     * 1个字节读取的数据长度
     */
    public static final int ONE_BYTE_DATA_LENGTH = 2;

    /**
     * 全部数据读取长度,11个数据，十六进制，【一个数据2个字节，一个寄存器地址1个字节】
     * 总共22个的寄存器地址，十六进制是16，22个字节
     */
    public static final String ALL_DATA_LENGTH = "0016";

    /**
     * 读取设备状态的读取地址长度，2个地址，4个字节
     */
    public static final String READ_STATE_DATA_LENGTH = "0002";


    /**
     * 读取设备状态的读取的数据长度，单位字节
     */
    public static final int READ_ALL_DATA_READ_BYTES = 22;

    /**
     * 读取设备状态的读取的数据长度，单位字节
     */
    public static final int READ_STATE_READ_BYTES = 4;

    /**
     * 默认的起始地址，十六进制，2个字节
     */
    public static final String START_ADDRESS = "0064";


    /**
     * 读取设备状态的起始地址，十六进制，2个字节
     */
    public static final String READ_STATE_START_ADDRESS = "0006";

    /**
     * 接收指令最小长度（接收指令字符串的长度）,7个字节，14个长度
     * 从序列号到设备地址
     */
    public static final int RECV_CMD_MIN_LENGTH = 14;

    /**
     * 接收指令正常情况下的最小长度，9个字节，18个长度
     * 从序列号到数据长度
     */
    public static final int RECV_CMD_NORMAL_MIN_LENGTH = 18;


    /**
     * 默认的设备地址
     */
    public static final String DEVICE_ADDRESS_DEFAULT = "01";

    /**
     * 开关状态：关
     */
    public static final String SWITCH_OFF = "00";

    /**
     * 开关状态：开
     */
    public static final String SWITCH_ON = "01";

    /**
     * 开关控制寄存器地址
     */
    public static final String SWITCH_CONTROL_ADDRESS = "00AA";

    /**
     * 读取开关状态：起始寄存器地址
     */
    public static final String SWITCH_READ_START_ADDRESS = "009D";

    /**
     * 读取开关状态：读取一个地址长度
     */
    public static final String SWITCH_READ_LENGTH = "00 01";

    /**
     * 读取电压：起始寄存器地址
     */
    public static final String READ_VOLTAGE_START_ADDRESS = "00 07";

    /**
     * 读取电压：读取两个地址长度
     */
    public static final String READ_VOLTAGE_LENGTH = "00 02";


    /**
     * 读取开关状态：读取两个字节
     */
    public static final int SWITCH_READ_BYTES = 2;

    /**
     * 读取电压：读取四个字节
     */
    public static final int READ_VOLTAGE_BYTES = 4;

    /**
     * 一个字节两个十六进制数
     */
    public static final int BYTE_LENGTH = 2;
}
