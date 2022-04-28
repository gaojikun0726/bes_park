package com.efounder.JEnterprise.zhdg.controller;

import com.alibaba.fastjson.JSONObject;
import com.efounder.JEnterprise.platform.util.InterfaceAccessUtil;
import com.efounder.JEnterprise.zhdg.config.LightPoleConfig;
import com.efounder.JEnterprise.zhdg.config.LightPoleValues;
import com.efounder.JEnterprise.zhdg.service.ISebDynamicAgreeHandleService;
import com.efounder.JEnterprise.zhdg.service.PointService;
import com.efounder.JEnterprise.zhdg.util.AjaxResult;
import io.swagger.annotations.*;
import org.apache.http.entity.StringEntity;
import org.json.JSONTokener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * describe: 智慧灯杆相关接口
 *
 * @author zs
 * @date 2021/5/21
 */
@Controller
@Api(value = "IntelliPoleApiController",tags = "智慧灯杆接口")
@RequestMapping("/api/public/pole")
public class IntelliPoleApiController {

    /**
     * 日志
     */
    private static final Logger logger = LoggerFactory.getLogger(IntelliPoleApiController.class);

    /**
     * 智慧灯杆大屏接口配置
     */
    @Resource
    private LightPoleConfig lightPoleConfig;

    /**
     * 动态协议操作接口
     * */
    @Autowired
    private ISebDynamicAgreeHandleService dynamicAgreeHandleService;

    @Resource
    private PointService pointService;


    /**
     * 获取屏幕截图-接口
     * @param paramObject 请求json数据
     * @return
     */
    @ApiOperation(value="大屏功能接口", notes="大屏功能接口")
    @RequestMapping(value = "/lightScreen/{cardNo}", method = RequestMethod.POST)
    @ResponseBody
    public JSONObject getScreenCapture(@PathVariable String cardNo, @ApiParam(value = "请求json数据", required = true) @RequestBody JSONObject paramObject) {
        logger.info("进入灯杆控制方法getScreenCapture");
        JSONObject jsonObject = transferLightScreen(cardNo,paramObject);

        //获取截图，处理图片格式
        String type = paramObject.getString("type");
        String fn = paramObject.getString("fn");
        String cardType = LightPoleValues.SCREEN_TYPE_CARD;
        String screenShot = LightPoleValues.SCREEN_FN_SCREEN_SHOT;
        if(cardType.equalsIgnoreCase(type) && screenShot.equalsIgnoreCase(fn)){
            String result = jsonObject.getString("result");
            if(result != null && !result.contains("does not exist")){
                String image = jsonObject.getString("result");
                String temp = formatCaptureImage(image);
                jsonObject.put("result",temp);
            }
        }

        return jsonObject;

    }


    /**
     * 调用大屏对应的接口
     * @param cardNo 控制卡编号
     * @param paramObject 请求参数
     * @return 返回结果
     */
    public JSONObject transferLightScreen(String cardNo,JSONObject paramObject){
        //路径写在配置文件中
        //y60-221-40903
        String path = lightPoleConfig.screenUrl+cardNo;
        StringEntity stringEntity = new StringEntity(paramObject.toJSONString(),"UTF-8");
        stringEntity.setContentType("application/json");

        JSONObject dataObject = new JSONObject();
        String result = InterfaceAccessUtil.doHttpEntity(path,stringEntity);
        logger.info("transferLightScreen方法，接收到的返回数据："+result);
        Object json = new JSONTokener(result).nextValue();
        //如果灯杆大屏连接不上，可能返回 not open 字符串
        if(json instanceof String){
            dataObject.put("result",result);
        }else{
            dataObject = JSONObject.parseObject(result);
        }
        return dataObject;
    }


    /**
     * 处理获取的截图的格式
     * @param image
     * @return
     */
    public String formatCaptureImage(String image){
        String temp = image.replace("\n","");
//        StringBuffer stringBuffer = new StringBuffer(result);
        String result = "data:image/png;base64," + temp;
        return result;
    }


    /**
     * 下发节目时，用于获取节目的下载进度
     * @return
     */
    @ApiOperation(value="获取节目的下载进度", notes="获取节目的下载进度")
    @RequestMapping(value = "/downloadProgress", method = RequestMethod.POST)
    @ResponseBody
    public JSONObject getDownloadNotification(@ApiParam(value = "下载进度回调数据", required = true) @RequestBody JSONObject obj){
        logger.info("灯杆节目下载进度："+obj.toJSONString());
        return obj;
    }


    /**
     * 屏幕开关接口
     * 使用八入八出第一个继电器
     * deviceid 灯杆设备id
     * data 0关 1开
     * 等待1.5秒 查询返回结果
     * */
    @ApiOperation("屏幕开关接口(废弃)")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "deviceid", value = "设备id", dataType = "string", paramType = "query",  required = true),
            @ApiImplicitParam(name = "data", value = "屏幕开关（0关 1开）", dataType = "string", paramType = "query",  required = true),
    })
    @PostMapping("/screenSendMsg")
    @ResponseBody
    public AjaxResult screenSendMsg(@RequestParam String deviceid, @RequestParam String data) throws Exception{
        String code = pointService.queryCodeByScreenCode(deviceid);
        Map<String, String> map = new HashMap<>();
        dynamicAgreeHandleService.sendMsg(code, data, "73", "0");
        // 等待1.5秒
        Thread.sleep(1500);
        Map<String,String> realMap = dynamicAgreeHandleService.getPointInfo(code);
        if(realMap != null){
            String relayControl1 = realMap.get("relayControl1");
            if(data.equals(relayControl1)){
                map.put("status", "1");
                map.put("msg", "屏幕开关控制成功");
            }else{
                map.put("status","0");
                map.put("msg", "屏幕开关控制失败");
            }
        }else{
            map.put("status","0");
            map.put("msg", "屏幕开关控制失败");
        }
        return AjaxResult.success(map);
    }

    /**
     * 灯杆灯亮度调整接口
     * deviceid 灯杆设备id
     * data 0-10
     * */
    @ApiOperation("灯亮度调整接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "deviceid", value = "设备id", dataType = "string", paramType = "query",  required = true),
            @ApiImplicitParam(name = "data", value = "灯亮度（0-100）", dataType = "string", paramType = "query",  required = true),
    })
    @PostMapping("/lightSendMsg")
    @ResponseBody
    public AjaxResult lightSendMsg(@RequestParam String deviceid, @RequestParam String data) throws Exception{
        Map<String, String> map = new HashMap<>();
        dynamicAgreeHandleService.sendMsg(deviceid, data, "75", "");
        // 等待1.5秒
        Thread.sleep(1500);
        Map<String,String> realMap = dynamicAgreeHandleService.getPointInfo(deviceid);
        if(realMap != null){
            String luminaireBrightness = realMap.get("luminaireBrightness");
            if(data.equals(luminaireBrightness)){
                map.put("status", "1");
                map.put("msg", "灯亮度调整成功，灯亮度："+luminaireBrightness);
            }else{
                map.put("status","0");
                map.put("msg", "灯亮度调整失败");
            }
        }else{
            map.put("status","0");
            map.put("msg", "灯亮度调整失败");
        }
        return AjaxResult.success(map);
    }

}
