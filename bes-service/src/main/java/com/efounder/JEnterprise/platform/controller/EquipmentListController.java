package com.efounder.JEnterprise.platform.controller;


import com.core.common.ISSPReturnObject;
import com.efounder.JEnterprise.platform.model.EquipmentList;
import com.efounder.JEnterprise.platform.service.EquipmentListService;
import com.efounder.JEnterprise.platform.util.ConstantValue;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * 设备列表
 * @author zs
 */
@Controller
@RequestMapping("/equipmentList")
public class EquipmentListController {

    /**
     * 返回页面路径前缀
     */
    private final String prefix = "/view/platform/equipment";

    /**
     * 业务接口实例
     */
    @Resource
    private EquipmentListService equipmentListService;

    @RequestMapping("/frame")
    public String frame(){
        return prefix + "/frame";
    }

    @RequestMapping("/view")
    public String view(){
        return prefix + "/equipmentList";
    }


    /**
     * 查询设备列表
     * @param request
     * @return
     */
    @RequestMapping("/queryList")
    @ResponseBody
    public Map queryList(HttpServletRequest request){
        //设备编号
        String deviceCode = request.getParameter("device_code");
        //设备名称
        String deviceName = request.getParameter("device_name");
        //设备类型编号
        String deviceTypeCode = request.getParameter("device_type_code");
        //设备类型名称
        String deviceTypeName = request.getParameter("device_type_name");
        //设备状态
        String deviceStatus = request.getParameter("device_status");

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
        map.put("deviceName",deviceName);
        map.put("deviceCode",deviceCode);
        map.put("deviceTypeCode",deviceTypeCode);
        map.put("deviceTypeName",deviceTypeName);
        map.put("deviceStatus",deviceStatus);
        map.put("field",field);
        map.put("order",order);
        return equipmentListService.queryList(map,Integer.valueOf(pageNumber),Integer.valueOf(pageSize));
    }


    /**
     *  新增区域信息
     * @param map 区域信息
     * @return
     */
    @RequestMapping(value = "/addOrEditEquipment",method = RequestMethod.POST)
    @ResponseBody
    public ISSPReturnObject addOrEditEquipment(@RequestBody Map map){

        return equipmentListService.addOrEditEquipment(map);
    }

    /**
     * 根据id查询单个设备的信息
     * @param deviceId 设备id
     * @return
     */
    @RequestMapping(value = "/queryEquipment")
    @ResponseBody
    public EquipmentList queryEquipment(@RequestParam String deviceId){
        return equipmentListService.queryEquipment(deviceId);
    }


    /**
     * 根据设备id数组删除多条设备信息
     * @param deviceIds 设备id
     * @return
     */
    @RequestMapping(value = "/deleteManyEquipment")
    @ResponseBody
    public ISSPReturnObject deleteManyEquipment(@RequestBody String[] deviceIds){
        return equipmentListService.deleteManyEquipment(deviceIds);
    }

}
