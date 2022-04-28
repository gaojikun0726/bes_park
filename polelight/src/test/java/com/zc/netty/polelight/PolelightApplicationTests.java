package com.zc.netty.polelight;

import com.zc.netty.polelight.protocol.modbus.ModbusCmdResolver;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class PolelightApplicationTests {

    @Test
    void contextLoads() {
        //开关状态回复数据
//        ModbusCmdResolver.commandParse("0002000000050103020002");
        //电压回复数据
        String msg = "00 01 00 00 00 07 01 03 04 00 02 06 39 ".replaceAll(" ","");
//        ModbusCmdResolver.commandParse(msg);

        String msg2 = "00 01 00 00 00 05 01 03 02 00 FF".replaceAll(" ","");
        ModbusCmdResolver.commandParse(msg2);
    }

}
