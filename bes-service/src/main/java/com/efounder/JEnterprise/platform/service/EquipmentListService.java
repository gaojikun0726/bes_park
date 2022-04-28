package com.efounder.JEnterprise.platform.service;

import com.core.common.ISSPReturnObject;
import com.efounder.JEnterprise.platform.model.EquipmentList;

import java.util.Map;

/**
 * 设备列表
 * @author zs
 */
public interface EquipmentListService {


    /**
     * 查询列表
     * @param map 参数
     * @param pageNumber 页码
     * @param pageSize 页大小
     * @return
     */
    Map queryList(Map<String,Object> map, Integer pageNumber, Integer pageSize);

    /**
     * 新增或修改设备信息
     * @param map 设备信息
     * @return
     */
    ISSPReturnObject addOrEditEquipment(Map map);


    /**
     * 根据id查询单个设备的信息
     * @param deviceId 设备id
     * @return
     */
    EquipmentList queryEquipment(String deviceId);

    /**
     * 根据设备id数组删除多条设备信息
     * @param deviceIds 设备id数组
     * @return
     */
    ISSPReturnObject deleteManyEquipment(String[] deviceIds);
}
