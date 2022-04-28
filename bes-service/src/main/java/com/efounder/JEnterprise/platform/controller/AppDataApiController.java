package com.efounder.JEnterprise.platform.controller;

import com.alibaba.fastjson.JSONObject;
import com.efounder.JEnterprise.platform.service.AppDataApiService;
import com.efounder.JEnterprise.platform.util.EnergyQueryType;
import com.efounder.JEnterprise.platform.util.EnergyType;
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
import org.springframework.web.bind.annotation.CrossOrigin;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * describe: APP数据接口
 *
 * @author zs
 * @date 2021/11/3
 */
@Controller
@Api(value = "AppDataApiController",tags = "APP数据接口")
@RequestMapping("/api/public/app")
@CrossOrigin
public class AppDataApiController {

    /**
     * 日志
     */
    private static final Logger logger = LoggerFactory.getLogger(AppDataApiController.class);


    @Resource
    private AppDataApiService appDataApiService;


    /**
     * 根据支路查询能耗数据-接口
     * @param energyType 能耗类型
     * @param time 能耗统计时间
     * @return
     */
    @ApiOperation(value="根据支路查询能耗数据", notes="根据支路查询能耗数据")
    @RequestMapping(value = "/getBranchEnergyData", method = RequestMethod.GET)
    @ResponseBody
    public JSONObject getBranchEnergyData(
            @ApiParam(value = "查询数据类型，比如年度统计、月度统计、日统计等", required = true) @RequestParam(required = true) EnergyQueryType queryType,
            @ApiParam(value = "能耗类型，比如用电、用水等", required = true) @RequestParam(required = true) EnergyType energyType,
            @ApiParam(value = "能耗统计时间，格式yyyy-MM-dd", required = true) @RequestParam(required = true) String time,
            @ApiParam(value="支路id",required = true) @RequestParam(required = true) String branchId) {
        JSONObject jsonObject = new JSONObject();
        Map<String,Object> map = new HashMap<>();
        map.put("time",time);
        map.put("fNybh",energyType.getCode());
        map.put("branchId",branchId);
        List list;
        switch(queryType){
            case year:
                list = appDataApiService.getBranchYearEnergy(map);
                break;
            case month:
                list = appDataApiService.getBranchMonthEnergy(map);
                break;
            case day:
                list = appDataApiService.getBranchDayEnergy(map);
                break;
            default:
                list = new ArrayList();
                break;
        }
        jsonObject.put("data", list);
        logger.info("getBranchEnergyData 根据支路查询能耗数据接口被调用");
        return jsonObject;
    }

    /**
     * 根据支路查询月排行数据-接口
     * @param energyType 能耗类型
     * @param time 能耗统计时间
     * @return
     */
    @ApiOperation(value="根据支路查询月排行数据", notes="根据支路查询月排行数据")
    @RequestMapping(value = "/getBranchMonthRankData", method = RequestMethod.GET)
    @ResponseBody
    public JSONObject getBranchMonthRankData(
            @ApiParam(value = "能耗类型，比如用电、用水等", required = true) @RequestParam(required = true) EnergyType energyType,
            @ApiParam(value = "能耗统计时间，格式yyyy-MM-dd", required = true) @RequestParam(required = true) String time,
            @ApiParam(value="支路id数组",required = true) @RequestParam(required = true) String[] branchId) {
        JSONObject jsonObject = new JSONObject();
        Map<String,Object> map = new HashMap<>();
        map.put("time",time);
        map.put("fNybh",energyType.getCode());
        map.put("array",branchId);
        map.put("rankNum",branchId.length);
        List<Map> list = appDataApiService.getBranchMonthRankData(map);
        jsonObject.put("data", list);
        logger.info("getBranchMonthRankData 根据支路查询能耗数据接口被调用");
        return jsonObject;
    }

    /**
     * 查询园区月排行数据-接口
     * @param energyType 能耗类型
     * @param time 能耗统计时间
     * @return
     */
    @ApiOperation(value="查询园区月排行数据", notes="查询园区月排行数据")
    @RequestMapping(value = "/getParkBranchRankData", method = RequestMethod.GET)
    @ResponseBody
    public JSONObject getParkBranchRankData(
            @ApiParam(value = "能耗类型，比如用电、用水等", required = true) @RequestParam EnergyType energyType,
            @ApiParam(value = "能耗统计时间，格式yyyy-MM-dd", required = true) @RequestParam String time,
            @ApiParam(value="统计排行个数",required = true) @RequestParam Integer rankNum,
            @ApiParam(value="支路父节点id",required = true) @RequestParam String branchParentId
            ) {
        JSONObject jsonObject = new JSONObject();
        Map<String,Object> map = new HashMap<>();
        map.put("time",time);
        map.put("fNybh",energyType.getCode());
        map.put("rankNum",rankNum);
        map.put("branchParentId",branchParentId);
        List<Map> list = appDataApiService.getParkBranchMonthRank(map);
        jsonObject.put("data", list);
        logger.info("getParkBranchRankData 查询园区月排行数据接口被调用");
        return jsonObject;
    }

}
