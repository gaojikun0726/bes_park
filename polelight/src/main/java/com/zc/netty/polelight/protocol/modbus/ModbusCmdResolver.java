package com.zc.netty.polelight.protocol.modbus;

import com.alibaba.fastjson.JSONObject;
import com.zc.netty.polelight.model.MessageType;
import com.zc.netty.polelight.util.NumberSystemConvert;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

/**
 * describe: modbus协议--接收指令解析类
 *
 * @author zs
 * @date 2021/2/5
 */
public class ModbusCmdResolver {

    private static final Logger log = LogManager.getLogger(ModbusCmdResolver.class);

    // todo 考虑错误码的情况  00 00 00 00 00 03 01 84 03

    public static JSONObject commandParse(String msg){
        JSONObject jsonObject = new JSONObject();
        if(msg.length() < ModbusCmdValues.RECV_CMD_MIN_LENGTH){
            jsonObject.put("msg", MessageType.ERROR_MESSAGE);
            return jsonObject;
        }
        String serial = msg.substring(0,4);
        String remainLength = msg.substring(8,12);
        String deviceAddress = msg.substring(12,14);
        String functionCode = msg.substring(14,16);
        jsonObject.put("serial",serial);
        jsonObject.put("functionCode",functionCode);

        if(msg.length() <= ModbusCmdValues.RECV_CMD_NORMAL_MIN_LENGTH){
            //分析错误码
            log.error("查询指令返回错误结果："+msg+",功能码为："+functionCode);
        }else{
            String dataLength = msg.substring(16,18);
            String readData = msg.substring(18);
            //按照功能码分析
            if(ModbusFunctionCode.CODE_THREE.equals(functionCode)){
                if(readData.length() == ModbusCmdValues.SWITCH_READ_BYTES * ModbusCmdValues.BYTE_LENGTH){
                    //读取2个字节，读取的是开关状态
                    String stateData = readData.substring(2);
                    List<String> list = handleOneBitData(stateData);
                    jsonObject.put("data",list);
                    jsonObject.put("type",ModbusCmdType.SWITCH_STATUS);
                }

                if(readData.length() == ModbusCmdValues.READ_VOLTAGE_BYTES * ModbusCmdValues.BYTE_LENGTH){
                    //读取4个字节，读取的是电压
                    List<String> list = handleFourBytesData(readData);
                    jsonObject.put("data",list);
                    jsonObject.put("type",ModbusCmdType.READ_VOLTAGE);
                }
            }

            if(ModbusFunctionCode.CODE_SIX.equals(functionCode)){
                //开关控制
                String controlCmd = readData.substring(4);
                jsonObject.put("statusControl",controlCmd);
                jsonObject.put("type",ModbusCmdType.SWITCH_CONTROL);
            }
        }
        return jsonObject;
    }

    /**
     * 解析读取的数据,4个字节作为一个数据，比如32位浮点数
     * @return
     */
    public static List<String> handleFourBytesData(String data){
        List<String> list = new ArrayList<>();
        for(int i = 0; i < data.length(); ){
            int len = 8;
            String item = data.substring(i,i+len);

            Float num = NumberSystemConvert.convertHexToFloat(item);
            list.add(String.valueOf(num));

            i += len;
        }

        return list;
    }

    /**
     * 解析数据：1位表示一个数据，通常是状态量
     * @return
     */
    public static List<String> handleOneBitData(String data){
        List<String> list = new ArrayList<>();
       //00 01 00 00 00 07 01 03 04 00 00 00 00
        for(int i = 0; i < data.length(); ){
            int len = 2;
            String item = data.substring(i,i+len);

            String bit = NumberSystemConvert.convertHexToBit(item);
            char[] array = bit.toCharArray();
            for(char c : array){
                list.add(String.valueOf(c));
            }

            i += len;
        }

        return list;
    }
}
