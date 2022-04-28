package com.efounder.JEnterprise.platform.service;

import com.alibaba.fastjson.JSONArray;
import com.efounder.JEnterprise.model.usercenter.ESUser;

import java.util.Map;

/**
 * describe: 接口获取数据处理业务类
 *
 * @author zs
 * @date 2020/11/18
 */
public interface AccessDataService {

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
     * 处理接口获取的用户数据
     * @param jsonArray 用户数据
     * @return
     */
    Integer handleUserData(JSONArray jsonArray);

    /**
     * 设定安全的密码，生成随机的salt并经过1024次 sha-1 hash
     *
     * ESUserServiceImpl 类中的是私有方法，并且不是实现类中的方法，因此重新定义了一份这个方法
     */
    void entryptPassword(ESUser user);


    /**
     * 处理接口获取的角色数据
     * @param jsonArray 角色数据
     * @return
     */
    Integer handleRoleData(JSONArray jsonArray);


    /**
     * 处理接口获取的组织机构数据
     * @param jsonArray 组织机构数据
     * @return
     */
    Integer handleDeptData(JSONArray jsonArray);

    /**
     * 处理接口获取的角色菜单关联关系数据
     * @param jsonArray 角色菜单关联关系数据
     * @return
     */
    Integer handleRoleMenuData(JSONArray jsonArray);


    /**
     * 处理接口获取的某类型的资产信息数据
     *
     * @param jsonArray 某类型的资产信息
     * @param categoryCode 资产类型编码
     * @return
     */
     Integer handleAssetsData(JSONArray jsonArray,String categoryCode);


    /**
     * 处理接口获取的全部的资产信息数据
     *
     * @param jsonArray 某类型的资产信息
     * @return
     */
    Integer handleAllAssetsData(JSONArray jsonArray);

    /**
     * 处理该类型对应的设备信息
     * @return
     */
    Integer handleDeviceData();
}
