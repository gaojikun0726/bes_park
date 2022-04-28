package com.efounder.JEnterprise.hikvision;


import com.alibaba.fastjson.JSONObject;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiSort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 海康视频监控
 *
 * @author LvSihan
 * @date 2019年7月23日
 * @version 1.0
 */
@RestController
@Api(value="HikvidionController",tags={"海康设备调试配置"})
@ApiSort(value = 1)
public class HikvidionController {


    @Autowired
    private ArtemisPostTest artemisPostTest;


    @ApiOperation(value="海康视频连接", notes="海康")
    @RequestMapping(value = "/api/v1.0/texthikvi", method = RequestMethod.GET)
    public JSONObject texthikvi(){
        JSONObject dataJObject = new JSONObject();
        try {
            String StringeResult = artemisPostTest.callPostStringApi();
            dataJObject.put("data", StringeResult);
            dataJObject.put("status", "1");
            dataJObject.put("msg", "成功");
            System.out.println("StringeResult结果示例: "+StringeResult);
//        artemisPostTest.callPostImgStringApi();
        } catch (Exception e) {
            dataJObject.put("status", "0");
            dataJObject.put("msg", "失败");
            e.printStackTrace();
        }
        return dataJObject;
    }

    @ApiOperation(value="获取监控点资源", notes="海康")
    @RequestMapping(value = "/api/v1.0/getid", method = RequestMethod.GET)
    public JSONObject getid(){
        JSONObject dataJObject = new JSONObject();
        try {
            String StringeResult = artemisPostTest.getid();
            dataJObject.put("data", StringeResult);
            dataJObject.put("status", "1");
            dataJObject.put("msg", "成功");
            System.out.println("StringeResult结果示例: "+StringeResult);
//        artemisPostTest.callPostImgStringApi();
        } catch (Exception e) {
            dataJObject.put("status", "0");
            dataJObject.put("msg", "失败");
            e.printStackTrace();
        }
        return dataJObject;
    }


    @ApiOperation(value="获取监控视频链接", notes="海康")
    @RequestMapping(value = "/api/v1.0/getRtmp", method = RequestMethod.GET)
    public JSONObject getRtmp(@ApiParam(value = "监控器唯一标识", required = false) @RequestParam String id){
        JSONObject dataJObject = new JSONObject();
        try {
            String StringeResult = artemisPostTest.rtmpPostImgStringApi(id);
            dataJObject.put("data", StringeResult);
            dataJObject.put("status", "1");
            dataJObject.put("msg", "成功");
            System.out.println("StringeResult结果示例: "+StringeResult);
        } catch (Exception e) {
            dataJObject.put("status", "0");
            dataJObject.put("msg", "失败");
            e.printStackTrace();
        }
        return dataJObject;
    }

    @ApiOperation(value="获取报警回放视频链接", notes="海康")
    @RequestMapping(value = "/api/v1.0/getMp4", method = RequestMethod.GET)
    public JSONObject getMp4(@ApiParam(value = "监控器唯一标识", required = false) @RequestParam String id,
                             @ApiParam(value = "开始时间", required = false) @RequestParam String beginTime,
                             @ApiParam(value = "结束时间", required = false) @RequestParam String endTime){
        JSONObject dataJObject = new JSONObject();
        try {
            String StringeResult = artemisPostTest.getMp4(id,beginTime,endTime);
            dataJObject.put("data", StringeResult);
            dataJObject.put("status", "1");
            dataJObject.put("msg", "成功");
            System.out.println("StringeResult结果示例: "+StringeResult);
        } catch (Exception e) {
            dataJObject.put("status", "0");
            dataJObject.put("msg", "失败");
            e.printStackTrace();
        }
        return dataJObject;
    }


    @ApiOperation(value="获取火警警报", notes="海康")
    @RequestMapping(value = "/api/v1.0/getpolice", method = RequestMethod.GET)
    public JSONObject getpolice(){
        JSONObject dataJObject = new JSONObject();
        try {
            String StringeResult = artemisPostTest.callPostStringApi();
            dataJObject.put("data", StringeResult);
            dataJObject.put("status", "1");
            dataJObject.put("msg", "成功");
            System.out.println("StringeResult结果示例: "+StringeResult);
        } catch (Exception e) {
            dataJObject.put("status", "0");
            dataJObject.put("msg", "失败");
            e.printStackTrace();
        }
        return dataJObject;
    }


    @ApiOperation(value="按事件类型订阅事件", notes="海康")
    @RequestMapping(value = "/api/v1.0/getsubscribe", method = RequestMethod.GET)
    public JSONObject getsubscribe(){
        JSONObject dataJObject = new JSONObject();
        try {
            String StringeResult = artemisPostTest.getsubscribe();
            dataJObject.put("data", StringeResult);
            dataJObject.put("status", "1");
            dataJObject.put("msg", "成功");
            System.out.println("StringeResult结果示例: "+StringeResult);
        } catch (Exception e) {
            dataJObject.put("status", "0");
            dataJObject.put("msg", "失败");
            e.printStackTrace();
        }
        return dataJObject;
    }


    @ApiOperation(value="查询事件订阅信息", notes="海康")
    @RequestMapping(value = "/api/v1.0/getAllsubscribe", method = RequestMethod.GET)
    public JSONObject getAllsubscribe(){
        JSONObject dataJObject = new JSONObject();
        try {
            String StringeResult = artemisPostTest.getAllsubscribe();
            dataJObject.put("data", StringeResult);
            dataJObject.put("status", "1");
            dataJObject.put("msg", "成功");
            System.out.println("StringeResult结果示例: "+StringeResult);
        } catch (Exception e) {
            dataJObject.put("status", "0");
            dataJObject.put("msg", "失败");
            e.printStackTrace();
        }
        return dataJObject;
    }


    @ApiOperation(value="按事件类型取消订阅", notes="海康")
    @RequestMapping(value = "/api/v1.0/deletesubscribe", method = RequestMethod.GET)
    public JSONObject deletesubscribe(@ApiParam(value = "取消订阅事件代码", required = false) @RequestParam String id){
        JSONObject dataJObject = new JSONObject();
        try {
            String StringeResult = artemisPostTest.deletesubscribe(id);
            dataJObject.put("data", StringeResult);
            dataJObject.put("status", "1");
            dataJObject.put("msg", "成功");
            System.out.println("StringeResult结果示例: "+StringeResult);
        } catch (Exception e) {
            dataJObject.put("status", "0");
            dataJObject.put("msg", "失败");
            e.printStackTrace();
        }
        return dataJObject;
    }


    @ApiOperation(value="获取联动事件列表", notes="海康")
    @RequestMapping(value = "/api/v1.0/getsubscribelinkage", method = RequestMethod.GET)
    public JSONObject getsubscribelinkage(){
        JSONObject dataJObject = new JSONObject();
        try {
            String StringeResult = artemisPostTest.getsubscribelinkage();
            dataJObject.put("data", StringeResult);
            dataJObject.put("status", "1");
            dataJObject.put("msg", "成功");
            System.out.println("StringeResult结果示例: "+StringeResult);
        } catch (Exception e) {
            dataJObject.put("status", "0");
            dataJObject.put("msg", "失败");
            e.printStackTrace();
        }
        return dataJObject;
    }

}
