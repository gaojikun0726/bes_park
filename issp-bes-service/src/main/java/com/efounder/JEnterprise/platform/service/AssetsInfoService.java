package com.efounder.JEnterprise.platform.service;

import java.util.List;
import java.util.Map;

/**
 * describe: 资产管理
 *
 * @author zs
 * @date 2020/12/14
 */
public interface AssetsInfoService {

    /**
     * 根据资产类型编码查询所有的资产编码
     * @param typeCode 资产类型编码
     * @return
     */
    List<String> getCodeList(String typeCode);


    /**
     * 查询该资产编码是否存在于资产表中
     * @param assetsCode 资产编码
     * @return
     */
    Integer assetsCodeExist(String assetsCode);

    /**
     * 查询电表表中是否有重复的
     * @param map 资产编码 电表id
     * @return
     */
    Integer querySameCode(Map<String,String> map);


    /**
     * 校验输入的资产编码是否可用
     * @param assetsCode 资产编码
     * @param fGuid 电表id
     * @return
     */
    boolean verifyAssetsCode(String assetsCode, String fGuid);


    /**
     * 查询某种类型设备列表
     * @param map 设备类型编码
     * @return
     */
    List<Map> getDeviceTypeList(Map<String,Object> map);

    /**
     * 根据设备类型编码查询设备唯一序列号列表
     * @param map 设备类型编码
     * @return
     */
    List<String> getDeviceSerialList(Map<String,Object> map);


    /**
     * 查询灯杆的设备序列号是否重复
     * @param map 灯杆的fGuid
     * @return
     */
    Integer querySameSerial(Map<String,Object> map);
}
