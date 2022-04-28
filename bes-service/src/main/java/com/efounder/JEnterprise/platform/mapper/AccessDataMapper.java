package com.efounder.JEnterprise.platform.mapper;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * describe:
 *
 * @author zs
 * @date 2020/11/18
 */
@Mapper
public interface AccessDataMapper {


    /**
     * 删除全部用户数据
     * @return
     */
    Integer deleteAllUser();

    /**
     * 新增用户接口数据
     * @param map
     * @return
     */
    Integer insertUserList(Map<String, Object> map);


    /**
     * 删除全部角色数据
     * @return
     */
    Integer deleteAllRole();


    /**
     * 新增角色接口数据
     * @param map
     * @return
     */
    Integer insertRoleList(Map<String, Object> map);


    /**
     * 删除全部组织机构数据
     * @return
     */
    Integer deleteAllDept();


    /**
     * 新增组织机构接口数据
     * @param map
     * @return
     */
    Integer insertDeptList(Map<String, Object> map);


    /**
     * 删除全部角色菜单关联关系数据
     * @return
     */
    Integer deleteAllRoleMenu();


    /**
     * 新增角色菜单关联关系接口数据
     * @param map
     * @return
     */
    Integer insertRoleMenuList(Map<String, Object> map);


    /**
     * 删除全部的资产信息
     * @return
     */
    Integer deleteAllAssetsByCode();

    /**
     * 根据资产类型编码删除资产信息
     * @param map 资产类型编码 categoryCode
     * @return
     */
    Integer deleteAssetsByCode(Map<String, Object> map);


    /**
     * 插入固定资产信息表
     * @param map
     * @return
     */
    Integer insertAssetsInfo(Map<String, Object> map);


    /**
     * 根据资产类型编码获取已删除的资产信息（hasDelete）
     * @param map 资产类型编码 categoryCode
     * @return
     */
    List<Map> getDeleteAssetsByCode(Map<String, Object> map);


    /**
     * 查询需要插入到设备表中的资产数据
     * @return
     */
    List<Map> queryDeviceByTypeCode();

    /**
     * 将资产数据插入到设备表中
     * @param map
     * @return
     */
    Integer insertDeviceList(Map<String, Object> map);

    /**
     * 插入设备表数据前删除原有数据
     * @return
     */
    Integer delAllDevice();


//    /**
//     * 插入空调设备表数据
//     * @param map
//     * @return
//     */
//    Integer insertConditionerData(Map<String, Object> map);
//
//    /**
//     * 插入灯光设备表数据
//     * @param map
//     * @return
//     */
//    Integer insertLightData(Map<String, Object> map);
}
