package com.zc.netty.polelight.protocol.modbus;

import com.zc.netty.polelight.util.StringUtil;

/**
 * describe: 命令组装工具类
 *
 * @author zs
 * @date 2021/2/6
 */
public class ModbusCmdGenerator {

    /**
     * 获取写指令
     * @param deviceAddress 设备地址
     * @param cmdType 指令类型
     * @param serial 序列号
     * @param switchWay 开关第几路
     * @return
     */
    public static String getWriteCommand(String deviceAddress,String cmdType,Integer serial,String switchWay,String switchStatus){
        String command = "";
        if(ModbusCmdType.SWITCH_CONTROL.equalsIgnoreCase(cmdType)){
            ModbusWriteSend send = new ModbusWriteSend();
            //开关控制
            //控制8路开关--变为开  "AF BC 00 00 00 06 01 06 00 AA 08 01";
            String hexSerial = getSerialHex(serial);
            send.setSerial(hexSerial);
            send.setDeviceAddress(getDeviceAddress(deviceAddress));
            send.setFunctionCode(ModbusFunctionCode.CODE_SIX);
            send.setRegisterAddress(ModbusCmdValues.SWITCH_CONTROL_ADDRESS);
            send.setSwitchWay(getHexByte(switchWay));
            send.setSwitchStatus(getHexByte(switchStatus));
            command = send.getWriteRecv();
        }
        return command;
    }


    /**
     * 根据设备地址和查询类型获取对应的读取指令
     * @param deviceAddress 设备地址
     * @param cmdType 指令类型
     * @param serial 序列号
     * @return
     */
    public static String getReadCommand(String deviceAddress,String cmdType,Integer serial){
        String command = "";
        ModbusReadSend send = new ModbusReadSend();
        if(ModbusCmdType.SWITCH_STATUS.equalsIgnoreCase(cmdType)){
            //读取开关状态： 00 01 00 00 00 06 01 03 00 9D 00 01
            String hexSerial = getSerialHex(serial);
            send.setSerial(hexSerial);
            send.setDeviceAddress(getDeviceAddress(deviceAddress));
            send.setFunctionCode(ModbusFunctionCode.CODE_THREE);
            send.setStartAddress(ModbusCmdValues.SWITCH_READ_START_ADDRESS);
            send.setAddressNum(ModbusCmdValues.SWITCH_READ_LENGTH);
            command = send.getReadRecv();
        }
        if(ModbusCmdType.READ_VOLTAGE.equalsIgnoreCase(cmdType)){
            //读取电压：00 01 00 00 00 06 01 03 00 07 00 02
            String hexSerial = getSerialHex(serial);
            send.setSerial(hexSerial);
            send.setDeviceAddress(getDeviceAddress(deviceAddress));
            send.setFunctionCode(ModbusFunctionCode.CODE_THREE);
            send.setStartAddress(ModbusCmdValues.READ_VOLTAGE_START_ADDRESS);
            send.setAddressNum(ModbusCmdValues.READ_VOLTAGE_LENGTH);
            command = send.getReadRecv();
        }
        return command;
    }

    /**
     * 获取16进制，1个字节长度的设备地址
     * @param deviceAddress 设备地址
     * @return
     */
    public static String getDeviceAddress(String deviceAddress){
        if(deviceAddress == null || "".equals(deviceAddress)){
            //设备地址为空，默认按照设备地址01读取
            return ModbusCmdValues.DEVICE_ADDRESS_DEFAULT;
        }
        String addressStr = StringUtil.fillStringWithZero(deviceAddress,2);
        return addressStr;
    }

//    /**
//     * 第几路开关
//     * @param swicthWay
//     * @return
//     */
//    public static String getSwitchWay(String swicthWay){
//        int num = Integer.parseInt(swicthWay);
//        String hex = Integer.toHexString(num);
//        String result = StringUtil.fillStringWithZero(hex,2);
//        return result;
//    }


    /**
     * 将字符串转换为16进制表示的字节（2位16进制数）
     * @param str
     * @return
     */
    public static String getHexByte(String str){
        int num = Integer.parseInt(str);
        String hex = Integer.toHexString(num);
        String result = StringUtil.fillStringWithZero(hex,2);
        return result;
    }

    /**
     * 获取16进制的序列号
     * @param serial
     * @return
     */
    public static String getSerialHex(int serial){
        String hex = Integer.toHexString(serial);
        String result = StringUtil.fillStringWithZero(hex,4);
        if(result.length() > 4){
            result = result.substring(4);
        }
        return result;
    }

}
