package com.efounder.JEnterprise.platform.controller;

import com.alibaba.fastjson.JSONObject;
import com.efounder.JEnterprise.platform.util.EnergyQueryType;
import com.efounder.JEnterprise.platform.util.EnergyType;
import com.efounder.JEnterprise.service.basedatamanage.workbench.EnergyTotalService;
import com.efounder.JEnterprise.service.dataAnalysises.BesHouseholdDataService;
import io.swagger.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * describe: 统计某天、某月、某年的用水量、用电量
 * 统计接口，供外部系统调用
 * @author zs
 * @date 2021/9/2
 */
@Controller
@Api(value = "EnergyTotalApiController",tags = "能耗统计接口")
@RequestMapping("/api/public")
public class EnergyTotalApiController {

    /**
     * 日志
     */
    private static final Logger logger = LoggerFactory.getLogger(EnergyTotalApiController.class);

    @Resource
    private EnergyTotalService energyTotalService;

    @Resource
    private BesHouseholdDataService householdDataService;


//    /**
//     * 获取某天的能耗统计-接口
//     * @param energyType 能耗类型
//     * @param time 能耗统计时间
//     * @return
//     */
//    @ApiOperation(value="获取某天的能耗统计", notes="获取某天的能耗统计")
//    @RequestMapping(value = "/getDailyTotalEnergy", method = RequestMethod.GET)
//    @ResponseBody
//    public JSONObject getDailyTotalEnergy(@ApiParam(value = "能耗类型，比如用电、用水等", required = false) @RequestParam(required = false) String energyType,
//                                          @ApiParam(value = "能耗统计时间，格式yyyy-MM-dd", required = false) @RequestParam(required = false) String time) {
//        JSONObject jsonObject = new JSONObject();
//        Map<String,Object> map = new HashMap<>();
//        map.put("fCjsj",time);
//        map.put("fType", EnergyTotalValues.F_TYPE_DAY);
//        map.put("fNybh",energyType);
//        List<Map> list = energyTotalService.getDailyTotalEnergy(map);
//        jsonObject.put("data", list);
//        logger.info("getDailyTotalEnergy 获取某天的能耗统计接口被调用");
//        return jsonObject;
//    }
//
//
//    /**
//     * 获取某月的能耗统计-接口
//     * @param energyType 能耗类型
//     * @param time 能耗统计时间
//     * @return
//     */
//    @ApiOperation(value="获取某月的能耗统计", notes="获取某月的能耗统计")
//    @RequestMapping(value = "/getMonthTotalEnergy", method = RequestMethod.GET)
//    @ResponseBody
//    public JSONObject getMonthTotalEnergy(@ApiParam(value = "能耗类型，比如用电、用水等", required = false) @RequestParam(required = false) String energyType,
//                                          @ApiParam(value = "能耗统计时间，格式yyyy-MM", required = false) @RequestParam(required = false) String time) {
//        JSONObject jsonObject = new JSONObject();
//        Map<String,Object> map = new HashMap<>();
//        map.put("fCjsj",time);
//        map.put("fType", EnergyTotalValues.F_TYPE_MONTH);
//        map.put("fNybh",energyType);
//        List<Map> list = energyTotalService.getMonthTotalEnergy(map);
//        jsonObject.put("data", list);
//        logger.info("getMonthTotalEnergy 获取某月的能耗统计接口被调用");
//        return jsonObject;
//    }
//
//
//    /**
//     * 获取某年的能耗统计-接口
//     * @param energyType 能耗类型
//     * @param time 能耗统计时间
//     * @return
//     */
//    @ApiOperation(value="获取某年的能耗统计", notes="获取某年的能耗统计")
//    @RequestMapping(value = "/getYearTotalEnergy", method = RequestMethod.GET)
//    @ResponseBody
//    public JSONObject getYearTotalEnergy(@ApiParam(value = "能耗类型，比如用电、用水等", required = false) @RequestParam(required = false) String energyType,
//                                          @ApiParam(value = "能耗统计时间，格式yyyy", required = false) @RequestParam(required = false) String time) {
//        JSONObject jsonObject = new JSONObject();
//        Map<String,Object> map = new HashMap<>();
//        map.put("fCjsj",time);
//        map.put("fType", EnergyTotalValues.F_TYPE_YEAR);
//        map.put("fNybh",energyType);
//        List<Map> list = energyTotalService.getYearTotalEnergy(map);
//        jsonObject.put("data", list);
//        logger.info("getYearTotalEnergy 获取某年的能耗统计接口被调用");
//        return jsonObject;
//    }


    /**
     * 获取年能耗统计-接口
     * @param energyType 能耗类型
     * @param time 能耗统计时间
     * @return
     */
    @ApiOperation(value="获取年能耗统计", notes="获取年能耗统计")
    @RequestMapping(value = "/getYearEnergy", method = RequestMethod.GET)
    @ResponseBody
    public JSONObject getYearEnergy(@ApiParam(value = "能耗类型，比如用电、用水等", required = false) @RequestParam(required = false) String energyType,
                                         @ApiParam(value = "能耗统计时间，格式yyyy-MM-dd", required = false) @RequestParam(required = false) String time) {
        JSONObject jsonObject = new JSONObject();
        Map<String,Object> map = new HashMap<>();
        map.put("time",time);
//        map.put("fType", EnergyTotalValues.F_TYPE_YEAR);
        map.put("fNybh",energyType);
        List<Map> list = energyTotalService.getYearEnergy(map);
        jsonObject.put("data", list);
        logger.info("getYearEnergy 获取年能耗统计接口被调用");
        return jsonObject;
    }

    /**
     * 获取月能耗统计-接口
     * @param energyType 能耗类型
     * @param time 能耗统计时间
     * @return
     */
    @ApiOperation(value="获取月能耗统计", notes="获取月能耗统计")
    @RequestMapping(value = "/getMonthEnergy", method = RequestMethod.GET)
    @ResponseBody
    public JSONObject getMonthEnergy(@ApiParam(value = "能耗类型，比如用电、用水等", required = false) @RequestParam(required = false) String energyType,
                                    @ApiParam(value = "能耗统计时间，格式yyyy-MM-dd", required = false) @RequestParam(required = false) String time) {
        JSONObject jsonObject = new JSONObject();
        Map<String,Object> map = new HashMap<>();
        map.put("time",time);
//        map.put("fType", EnergyTotalValues.F_TYPE_MONTH);
        map.put("fNybh",energyType);
        List<Map> list = energyTotalService.getMonthEnergy(map);
        jsonObject.put("data", list);
        logger.info("getMonthEnergy 获取月能耗统计接口被调用");
        return jsonObject;
    }

    /**
     * 获取日能耗统计-接口
     * @param energyType 能耗类型
     * @param time 能耗统计时间
     * @return
     */
    @ApiOperation(value="获取日能耗统计", notes="获取日能耗统计")
    @RequestMapping(value = "/getDayEnergy", method = RequestMethod.GET)
    @ResponseBody
    public JSONObject getDayEnergy(@ApiParam(value = "能耗类型，比如用电、用水等", required = false) @RequestParam(required = false) String energyType,
                                     @ApiParam(value = "能耗统计时间，格式yyyy-MM-dd", required = false) @RequestParam(required = false) String time) {
        JSONObject jsonObject = new JSONObject();
        Map<String,Object> map = new HashMap<>();
        map.put("time",time);
//        map.put("fType", EnergyTotalValues.F_TYPE_MONTH);
        map.put("fNybh",energyType);
        List<Map> list = energyTotalService.getDayEnergy(map);
        jsonObject.put("data", list);
        logger.info("getDayEnergy 获取日能耗统计接口被调用");
        return jsonObject;
    }

    /**
     * 查询年度能耗趋势-接口
     * @param energyType 能耗类型
     * @param time 能耗统计时间
     * @return
     */
    @ApiOperation(value="查询年度能耗趋势", notes="查询年度能耗趋势")
    @RequestMapping(value = "/getAnnualEnergyTrend", method = RequestMethod.GET)
    @ResponseBody
    public JSONObject getAnnualEnergyTrend(@ApiParam(value = "能耗类型，比如用电、用水等", required = false) @RequestParam(required = false) String energyType,
                                   @ApiParam(value = "能耗统计时间，格式yyyy-MM-dd", required = false) @RequestParam(required = false) String time) {
        JSONObject jsonObject = new JSONObject();
        Map<String,Object> map = new HashMap<>();
        map.put("time",time);
//        map.put("fType", EnergyTotalValues.F_TYPE_MONTH);
        map.put("fNybh",energyType);
        List<Map> list = energyTotalService.getAnnualEnergyTrend(map);
        jsonObject.put("data", list);
        logger.info("getAnnualEnergyTrend 查询年度能耗趋势接口被调用");
        return jsonObject;
    }


    /**
     * 查询月环比能耗数据-接口
     * @param energyType 能耗类型
     * @param time 能耗统计时间
     * @return
     */
    @ApiOperation(value="查询月环比能耗数据", notes="查询月环比能耗数据")
    @RequestMapping(value = "/getMonthLinkData", method = RequestMethod.GET)
    @ResponseBody
    public JSONObject getMonthLinkData(@ApiParam(value = "能耗类型，比如用电、用水等", required = false) @RequestParam(required = false) String energyType,
                                       @ApiParam(value = "能耗统计时间，格式yyyy-MM-dd", required = false) @RequestParam(required = false) String time) {
        JSONObject jsonObject = new JSONObject();
        Map<String,Object> map = new HashMap<>();
        map.put("time",time);
//        map.put("fType", EnergyTotalValues.F_TYPE_MONTH);
        map.put("fNybh",energyType);
        List<Map> list = energyTotalService.getMonthLinkData(map);
        jsonObject.put("data", list);
        logger.info("getMonthLinkData 查询月环比能耗数据接口被调用");
        return jsonObject;
    }


    /**
     * 查询月同比能耗数据-接口
     * @param energyType 能耗类型
     * @param time 能耗统计时间
     * @return
     */
    @ApiOperation(value="查询月同比能耗数据", notes="查询月同比能耗数据")
    @RequestMapping(value = "/getSameMonthComparedData", method = RequestMethod.GET)
    @ResponseBody
    public JSONObject getSameMonthComparedData(@ApiParam(value = "能耗类型，比如用电、用水等", required = false) @RequestParam(required = false) String energyType,
                                               @ApiParam(value = "能耗统计时间，格式yyyy-MM-dd", required = false) @RequestParam(required = false) String time) {
        JSONObject jsonObject = new JSONObject();
        Map<String,Object> map = new HashMap<>();
        map.put("time",time);
//        map.put("fType", EnergyTotalValues.F_TYPE_MONTH);
        map.put("fNybh",energyType);
        List<Map> list = energyTotalService.getSameMonthComparedData(map);
        jsonObject.put("data", list);
        logger.info("getSameMonthComparedData 查询月同比能耗数据接口被调用");
        return jsonObject;
    }



    /**
     * 查询分时能耗统计-接口
     * @param energyType 能耗类型
     * @param time 能耗统计时间
     * @return
     */
    @ApiOperation(value="查询分时能耗统计", notes="查询分时能耗统计")
    @RequestMapping(value = "/getLiveEnergyData", method = RequestMethod.GET)
    @ResponseBody
    public JSONObject getLiveEnergyData(@ApiParam(value = "能耗类型，比如用电、用水等", required = false) @RequestParam(required = false) String energyType,
                                               @ApiParam(value = "能耗统计时间，格式yyyy-MM-dd", required = false) @RequestParam(required = false) String time) {
        JSONObject jsonObject = new JSONObject();
        Map<String,Object> map = new HashMap<>();
        map.put("time",time);
//        map.put("fType", EnergyTotalValues.F_TYPE_MONTH);
        map.put("fNybh",energyType);
        List<Map> list = energyTotalService.getLiveEnergyData(map);
        jsonObject.put("data", list);
        logger.info("getLiveEnergyData 查询分时能耗统计接口被调用");
        return jsonObject;
    }



    /**
     * 查询能耗统计数据-接口
     * @param energyType 能耗类型
     * @param time 能耗统计时间
     * @return
     */
    @ApiOperation(value="查询能耗统计数据", notes="查询能耗统计数据")
    @RequestMapping(value = "/getEnergyData", method = RequestMethod.GET)
    @ResponseBody
    public JSONObject getEnergyData(
            @ApiParam(value = "查询数据类型，比如年度统计、月度统计、日统计、月同比、月环比等", required = false) @RequestParam(required = false) EnergyQueryType queryType,
            @ApiParam(value = "能耗类型，比如用电、用水等", required = false) @RequestParam(required = false) EnergyType energyType,
                                        @ApiParam(value = "能耗统计时间，格式yyyy-MM-dd", required = false) @RequestParam(required = false) String time) {
        JSONObject jsonObject = new JSONObject();
        Map<String,Object> map = new HashMap<>();
        map.put("time",time);
//        map.put("fType", EnergyTotalValues.F_TYPE_MONTH);
        map.put("fNybh",energyType.getCode());
//        EnergyTotalValues.dataType dataType1 = EnergyTotalValues.dataType.day;
        List list;
        switch(queryType){
            case year:
                list = energyTotalService.getYearEnergy(map);
                break;
            case month:
                list = energyTotalService.getMonthEnergy(map);
                break;
            case day:
                list = energyTotalService.getDayEnergy(map);
                break;
            case hour:
                list = energyTotalService.getLiveEnergyData(map);
                break;
            case year_trend:
                list = energyTotalService.getAnnualEnergyTrend(map);
                break;
            case month_link_ratio:
                list = energyTotalService.getMonthLinkData(map);
                break;
            case month_same_ratio:
                list = energyTotalService.getSameMonthComparedData(map);
                break;
            case company_month_rank:
                map.put("count",10);
                list = energyTotalService.getCompanyMonthRank(map);
                break;
            case building_by_day:
                list = energyTotalService.getBuildingEnergyByDay(map);
                break;
            case week_by_day:
                list = energyTotalService.getWeekByDayEnergy(map);
                break;
            default:
                list = new ArrayList();
                break;
        }
//        List<Map> list = energyTotalService.getLiveEnergyData(map);
        jsonObject.put("data", list);
        logger.info("getEnergyData 查询能耗统计数据接口被调用");
        return jsonObject;
    }




    /**
     * 获取各单位能耗统计排行-接口
     * @param energyType 能耗类型
     * @param time 能耗统计时间
     * @return
     */
    @ApiOperation(value="获取各单位能耗统计排行", notes="获取各单位能耗统计排行")
    @RequestMapping(value = "/getCompanyMonthRank", method = RequestMethod.GET)
    @ResponseBody
    public JSONObject getCompanyMonthRank(@ApiParam(value = "能耗类型，比如用电、用水等", required = false) @RequestParam(required = false) String energyType,
                                        @ApiParam(value = "能耗统计时间，格式yyyy-MM-dd", required = false) @RequestParam(required = false) String time,
                                           @ApiParam(value = "统计单位个数", required = false) @RequestParam(required = false) Integer count) {
        JSONObject jsonObject = new JSONObject();
        Map<String,Object> map = new HashMap<>();
        map.put("time",time);
        map.put("fNybh",energyType);
        map.put("count",count);
        List<Map> list = energyTotalService.getCompanyMonthRank(map);
        jsonObject.put("data", list);
        logger.info("getCompanyMonthRank 获取各单位能耗统计排行接口被调用");
        return jsonObject;
    }



//    /**
//     * 获取分户用能趋势统计分析数据-接口
//     * @param energyType 能耗类型
////     * @param time 能耗统计时间
//     * @return
//     */
//    @ApiOperation(value="获取分户用能趋势统计分析数据", notes="获取某年的能耗统计")
//    @RequestMapping(value = "/getHouseholdDataCommon", method = RequestMethod.GET)
//    @ResponseBody
//    public ISSPReturnObject getHouseholdDataCommon(
//            @ApiParam(value = "查询类型，比如时=0、天=1、月=2、年=3", required = false) @RequestParam(required = false) String queryType,
//            @ApiParam(value = "能耗类型，比如用电、用水等", required = false) @RequestParam(required = false) String energyType,
//                                         @ApiParam(value = "开始时间，格式yyyy-MM-dd HH:mm:ss", required = false) @RequestParam(required = false) String startTime,
//                                             @ApiParam(value = "结束时间，格式yyyy-MM-dd HH:mm:ss", required = false) @RequestParam(required = false) String endTime) {
////        JSONObject jsonObject = new JSONObject();
//        Map<String,String> map = new HashMap<>();
//        map.put("fCjsj_start",startTime);
//        map.put("fCjsj_end",endTime);
//        map.put("fType", queryType);
//        map.put("fNybh",energyType);
//        map.put("fFhbh","04");
//
//        BesHouseholdData besHouseholdData = new BesHouseholdData();
//        ISSPReturnObject isspReturnObject = householdDataService.getHouseholdDataCommon(besHouseholdData,map);
//        logger.info("getHouseholdDataCommon 获取分户用能趋势统计分析数据");
//        return isspReturnObject;
//    }

}
