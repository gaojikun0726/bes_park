package com.efounder.JEnterprise.platform.controller;

import com.alibaba.fastjson.JSONArray;
import com.core.common.ISSPReturnObject;
import com.efounder.JEnterprise.platform.service.ShareAmmeterService;
import com.efounder.JEnterprise.platform.util.ConstantValue;
import com.efounder.JEnterprise.platform.util.List2Map;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * describe: 公摊仪表控制器
 *
 * @author zs
 * @date 2020/11/9
 */
@Controller
@RequestMapping("/platform/share")
public class ShareAmmeterController {


    /**
     * 返回页面路径前缀
     */
    private final String prefix = "/view/platform/share";


    @Resource
    private ShareAmmeterService shareAmmeterService;

    /**
     * 访问页面
     * @return
     */
    @RequestMapping("/view")
    public String view(){
        return prefix + "/shareAmmeter";
    }


    /**
     * 查询公摊仪表列表
     * @param request
     * @return
     */
    @RequestMapping("/queryList")
    @ResponseBody
    public Map queryList(HttpServletRequest request){
        //仪表名称
        String meterName = request.getParameter("meter_name");
        //仪表类型
        String meterType = request.getParameter("meter_type");
        //仪表归属类型
        String meterArea = request.getParameter("meter_area");
        //排序字段
        String field = request.getParameter("field");
        //排序顺序
        String order = request.getParameter("order");

        String pageNumber = request.getParameter("pageNumber");
        String pageSize = request.getParameter("pageSize");
        if(StringUtils.isEmpty(pageNumber)){
            pageNumber = String.valueOf(ConstantValue.PAGE_NUM);
        }
        if(StringUtils.isEmpty(pageSize)){
            pageSize = String.valueOf(ConstantValue.PAGE_SIZE);
        }
        Map<String,Object> map = new HashMap<>();
        map.put("meterName",meterName);
        map.put("meterType",meterType);
        map.put("meterArea",meterArea);
        map.put("field",field);
        map.put("order",order);
        return shareAmmeterService.queryList(map,Integer.valueOf(pageNumber),Integer.valueOf(pageSize));
    }


    /**
     * 配置公摊仪表
     * @param meterIds 仪表id
     * @return
     */
    @RequestMapping(value = "/shareMeterConfig")
    @ResponseBody
    public ISSPReturnObject shareMeterConfig(@RequestBody String[] meterIds){
        return shareAmmeterService.shareMeterConfig(meterIds);
    }

    /**
     * 配置普通仪表
     * @param meterIds 仪表id
     * @return
     */
    @RequestMapping(value = "/plainMeterConfig")
    @ResponseBody
    public ISSPReturnObject plainMeterConfig(@RequestBody String[] meterIds){
        return shareAmmeterService.plainMeterConfig(meterIds);
    }

    /**
     * 查询是否全部是公摊仪表或普通仪表
     * @param meterIds 仪表id
     * @param meterState 仪表状态
     * @return
     */
    @RequestMapping(value = "/queryMeterState")
    @ResponseBody
    public ISSPReturnObject queryMeterState(String meterIds,String meterState){
        JSONArray jsonArray = JSONArray.parseArray(meterIds);
        return shareAmmeterService.queryMeterState(jsonArray,meterState);
    }


    /**
     * 查询仪表类型数据
     * @return
     */
    @RequestMapping("/queryAmmeterType")
    @ResponseBody
    public ISSPReturnObject queryAmmeterType(){
        ISSPReturnObject isspReturnObject = new ISSPReturnObject();
        List<Map> list = shareAmmeterService.queryAmmeterType();
        Map map = List2Map.list2Map(list,"value","name");
        isspReturnObject.setStatus("1");
        isspReturnObject.setList(list);
        isspReturnObject.setMap(map);
        return isspReturnObject;
    }
}
