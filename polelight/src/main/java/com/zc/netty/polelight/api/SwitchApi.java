package com.zc.netty.polelight.api;

import com.alibaba.fastjson.JSONObject;
import com.zc.netty.polelight.handle.ChannelManager;
import com.zc.netty.polelight.handle.CommandHandle;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.Set;

/**
 * describe: 开关相关接口
 *
 * @author zs
 * @date 2021/11/19
 */
@Controller
@RequestMapping("/api/pole/pdu")
public class SwitchApi {

    @Resource
    private CommandHandle commandHandle;


    /**
     * 开关控制
     * @param ip IP地址
//     * @param port 端口号
     * @param controlObject 控制对象，显示屏、音柱、摄像头等
     * @param switchStatus 开关控制：开或关
     * @return
     */
    @RequestMapping(value = "/switchControl", method = RequestMethod.GET)
    @ResponseBody
    public JSONObject switchControl(
            @RequestParam String ip,
//            @RequestParam String port,
            @RequestParam String controlObject,
            @RequestParam String switchStatus) {
        //下发命令，返回下发结果
        JSONObject jsonObject = commandHandle.switchControl(ip,controlObject,switchStatus);

        return jsonObject;
    }


    /**
     * 获取控制对象的开关状态
     * @param ip IP地址
//     * @param port 端口号
     * @param controlObject 控制对象，显示屏、音柱、摄像头等
     * @return
     */
    @RequestMapping(value = "/switchStatus", method = RequestMethod.GET)
    @ResponseBody
    public JSONObject switchStatus(
            @RequestParam String ip,
//            @RequestParam String port,
            @RequestParam String controlObject){
        //下发命令，返回下发结果
        JSONObject jsonObject = commandHandle.getSwitchStatus(ip,controlObject);

        return jsonObject;
    }


    /**
     * 获取在线状态的PDU设备数据
     * @return
     */
    @RequestMapping(value = "/getOnlineData", method = RequestMethod.GET)
    @ResponseBody
    public JSONObject getOnlineData(){
        JSONObject jsonObject = new JSONObject();
        Set set = ChannelManager.channelMap.keySet();
        jsonObject.put("ip",set);
        jsonObject.put("result",true);
        return jsonObject;
    }

}
