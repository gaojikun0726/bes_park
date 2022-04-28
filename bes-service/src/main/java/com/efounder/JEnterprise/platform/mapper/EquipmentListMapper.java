package com.efounder.JEnterprise.platform.mapper;

import com.efounder.JEnterprise.platform.model.EquipmentList;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * 设备列表
 * @author zs
 */
@Mapper
public interface EquipmentListMapper {


    /**
     * 查询列表
     *
     * @param map 参数
     * @return
     */
    List<Map> queryListMap(Map<String, Object> map);

    /**
     * 查询列表
     *
     * @param map 参数
     * @return
     */
    List<EquipmentList> queryList(Map<String, Object> map);

    /**
     * 新增设备记录
     * @param map 设备信息
     * @return
     */
    Integer insertEquipmentList(Map map);

    /**
     * 修改设备信息
     * @param map 设备信息
     * @return
     */
    Integer updateEquipmentList(Map map);

    /**
     * 根据id删除设备记录
     * @param map 设备id
     * @return
     */
    Integer deleteEquipmentListById(Map map);

    /**
     * 根据id集合删除多条设备记录
     * @param map 设备id集合
     * @return
     */
    Integer deleteEquipmentListByIds(Map map);


    /**
     * 根据id查询单个设备的信息
     * @param deviceId 设备id
     * @return
     */
    EquipmentList queryEquipment(String deviceId);


}
