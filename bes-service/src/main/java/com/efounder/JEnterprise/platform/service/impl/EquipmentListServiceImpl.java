package com.efounder.JEnterprise.platform.service.impl;

import com.core.common.ISSPReturnObject;
import com.efounder.JEnterprise.platform.mapper.EquipmentListMapper;
import com.efounder.JEnterprise.platform.model.EquipmentList;
import com.efounder.JEnterprise.platform.service.EquipmentListService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 设备列表
 * @author zs
 */
@Service
public class EquipmentListServiceImpl implements EquipmentListService {

    @Resource
    private EquipmentListMapper equipmentListMapper;


    /**
     * 查询列表
     *
     * @param map        参数
     * @param pageNumber 页码
     * @param pageSize   页大小
     * @return
     */
    @Override
    public Map queryList(Map<String, Object> map, Integer pageNumber, Integer pageSize) {
        Map<String,Object> result = new HashMap<>(2);

        PageHelper.startPage(pageNumber,pageSize);
        List<EquipmentList> list = equipmentListMapper.queryList(map);
        PageInfo<EquipmentList> page = new PageInfo<>(list);

        result.put("rows",list);
        result.put("total",page.getTotal());
        result.put("code",0);
        result.put("data",list);
        result.put("count",page.getTotal());
        return result;
    }

    /**
     * 新增或修改设备信息
     *
     * @param map 设备信息
     * @return
     */
    @Override
    public ISSPReturnObject addOrEditEquipment(Map map) {
        ISSPReturnObject isspReturnObject = new ISSPReturnObject();
        if(map == null){
            isspReturnObject.setStatus("0");
            return isspReturnObject;
        }
        String deviceId = map.get("device_id") == null ?  "" : map.get("device_id").toString();
        Integer num = 0;
        if("".equals(deviceId)){
            //新增
            num = equipmentListMapper.insertEquipmentList(map);
        }else{
            //修改
            num = equipmentListMapper.updateEquipmentList(map);
        }
        if(num == 1){
            isspReturnObject.setStatus("1");
        }else{
            isspReturnObject.setStatus("0");
        }
        return isspReturnObject;
    }

    /**
     * 根据id查询单个设备的信息
     *
     * @param deviceId 设备id
     * @return
     */
    @Override
    public EquipmentList queryEquipment(String deviceId) {
        return equipmentListMapper.queryEquipment(deviceId);
    }

    /**
     * 根据设备id数组删除多条设备信息
     *
     * @param deviceIds 设备id数组
     * @return
     */
    @Override
    public ISSPReturnObject deleteManyEquipment(String[] deviceIds) {
        ISSPReturnObject isspReturnObject = new ISSPReturnObject();
        isspReturnObject.setStatus("0");
        if(deviceIds != null && deviceIds.length > 0){
            Map map = new HashMap();
            map.put("array",deviceIds);
            Integer num = equipmentListMapper.deleteEquipmentListByIds(map);
            if(num > 0){
                isspReturnObject.setStatus("1");
            }
        }
        return isspReturnObject;
    }
}
