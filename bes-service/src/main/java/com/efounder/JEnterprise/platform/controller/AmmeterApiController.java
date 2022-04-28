package com.efounder.JEnterprise.platform.controller;

import com.alibaba.fastjson.JSONObject;
import com.efounder.JEnterprise.platform.service.IOpmPositionInfoService;
import com.efounder.JEnterprise.service.basedatamanage.enegrycollectionmanage.BESAmmeterTypeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * describe: 电表等仪表相关接口
 *
 * @author zs
 * @date 2020/10/29
 */
@Controller
@Api(value = "AmmeterApiController",tags = "仪表接口")
@RequestMapping("/api/public")
public class AmmeterApiController {

    /**
     * 日志
     */
    private static final Logger logger = LoggerFactory.getLogger(AmmeterApiController.class);

    /**
     * 电表类型业务类
     */
    @Resource
    private BESAmmeterTypeService ammeterTypeService;



    /**
     * 区域位置（房间）信息业务类
     */
    @Resource
    private IOpmPositionInfoService positionInfoService;


    /**
     * 获取仪表类型列表-接口
     * @param lastTime 上次请求时间
     * @return
     */
    @ApiOperation(value="获取仪表类型列表", notes="获取仪表类型列表")
    @RequestMapping(value = "/getAmmeterTypeList", method = RequestMethod.GET)
    @ResponseBody
    public JSONObject getAmmeterTypeList(@ApiParam(value = "上次请求时间", required = false) @RequestParam(required = false) Long lastTime) {
        JSONObject jsonObject = new JSONObject();
        Map<String,Object> map = new HashMap<>();
        map.put("lastTime",lastTime);
        List<Map> list = ammeterTypeService.queryAmmeterTypeList(map);
        jsonObject.put("data", list);
        logger.info("getAmmeterTypeList 获取仪表类型列表接口被调用");
        return jsonObject;

    }


    /**
     * 获取仪表信息列表-接口
     * @param lastTime 上次请求时间
     * @return
     */
    @ApiOperation(value="获取仪表信息列表", notes="获取仪表信息列表")
    @RequestMapping(value = "/getAmmeterList", method = RequestMethod.GET)
    @ResponseBody
    public JSONObject getAmmeterList(@ApiParam(value = "上次请求时间", required = false) @RequestParam(required = false) Long lastTime) {
        JSONObject jsonObject = new JSONObject();
        Map<String,Object> map = new HashMap<>();
        map.put("lastTime",lastTime);
        List<Map<String,Object>> list = positionInfoService.queryAmmeterList(map);
        jsonObject.put("data", list);
        logger.info("getAmmeterList 获取仪表信息列表接口被调用");
        return jsonObject;
    }

    /**
     * 获取当天的仪表抄表数据-接口
     * @param syncTimeStamp 时间戳
     * @return
     */
    @ApiOperation(value="获取当天的仪表抄表数据", notes="获取当天的仪表抄表数据")
    @RequestMapping(value = "/getAmmeterCurrentDataOld", method = RequestMethod.GET)
    @ResponseBody
    public JSONObject getAmmeterCurrentData(@ApiParam(value = "时间戳", required = false) @RequestParam(required = false) Long syncTimeStamp,
                                         @ApiParam(value = "仪表主键", required = false) @RequestParam(required = false)  List<String> meterGuid) {
        JSONObject jsonObject = new JSONObject();
        Map<String,Object> map = new HashMap<>();
        map.put("syncTimeStamp",syncTimeStamp);
        if(meterGuid != null && meterGuid.size() > 0){
            map.put("meterGuid",meterGuid);
        }
        List<Map> list = positionInfoService.queryMeterDayData(map);
        jsonObject.put("data", list);
        logger.info("getAmmeterCurrentData 获取当天的仪表抄表数据接口被调用");
        return jsonObject;
    }

    /**
     * 获取仪表抄表数据及用量-接口
     * @param lastTime 上次请求时间
     * @return
     */
    @ApiOperation(value="获取仪表抄表数据及用量", notes="获取仪表抄表数据及用量")
    @RequestMapping(value = "/getRoomAmmeterDataOld", method = RequestMethod.GET)
    @ResponseBody
    public JSONObject getRoomAmmeterData(@ApiParam(value = "上次请求时间", required = false) @RequestParam(required = false) Long lastTime) {
        JSONObject jsonObject = new JSONObject();
        Map<String,Object> map = new HashMap<>();
        map.put("lastTime",lastTime);
        List<Map> list = positionInfoService.getReadDataList(map);
        jsonObject.put("data", list);
        logger.info("getRoomAmmeterData 获取仪表抄表数据及用量接口被调用");
        return jsonObject;
    }


    /**
     * 新方法
     * 获取仪表抄表数据及用量-接口
     * @param lastTime 上次请求时间
     * @return
     */
    @ApiOperation(value="获取仪表抄表数据及用量(新)", notes="获取仪表抄表数据及用量(新)")
    @RequestMapping(value = "/getRoomAmmeterData", method = RequestMethod.GET)
    @ResponseBody
    public JSONObject getRoomAmmeterDataNew(@ApiParam(value = "上次请求时间", required = false) @RequestParam(required = false) Long lastTime) {
        JSONObject jsonObject = new JSONObject();
        Map<String,Object> map = new HashMap<>();
        map.put("lastTime",lastTime);
        List<Map> list = positionInfoService.getReadDataListNew(map);
        jsonObject.put("data", list);
        logger.info("getRoomAmmeterDataNew 获取仪表抄表数据及用量接口-新方法被调用");
        return jsonObject;
    }


    /**
     * 新方法
     * 获取当天的仪表抄表数据-接口
     * @param syncTimeStamp 时间戳
     * @return
     */
    @ApiOperation(value="获取当天的仪表抄表数据(新)", notes="获取当天的仪表抄表数据(新)")
    @RequestMapping(value = "/getAmmeterCurrentData", method = RequestMethod.GET)
    @ResponseBody
    public JSONObject getAmmeterCurrentDataNew(@ApiParam(value = "时间戳", required = false) @RequestParam(required = false) Long syncTimeStamp,
                                            @ApiParam(value = "仪表主键", required = false) @RequestParam(required = false)  List<String> meterGuid) {
        JSONObject jsonObject = new JSONObject();
        Map<String,Object> map = new HashMap<>();
        map.put("syncTimeStamp",syncTimeStamp);
        if(meterGuid != null && meterGuid.size() > 0){
            map.put("meterGuid",meterGuid);
        }
        List<Map> list = positionInfoService.queryMeterDayDataNew(map);
        jsonObject.put("data", list);
        logger.info("getAmmeterCurrentDataNew 获取当天的仪表抄表数据接口被调用");
        return jsonObject;
    }

}
