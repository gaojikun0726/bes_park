package com.efounder.JEnterprise.platform.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.efounder.JEnterprise.common.util.salt.Digests;
import com.efounder.JEnterprise.common.util.salt.Encodes;
import com.efounder.JEnterprise.initializer.DeviceConfigurationCache;
import com.efounder.JEnterprise.model.usercenter.ESUser;
import com.efounder.JEnterprise.platform.mapper.AccessDataMapper;
import com.efounder.JEnterprise.platform.service.AccessDataService;
import com.efounder.JEnterprise.service.usercenter.ESUserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * describe:
 *
 * @author zs
 * @date 2020/11/18
 */
@Service
public class AccessDataServiceImpl implements AccessDataService {

    public static final int HASH_INTERATIONS = 1024;
    private static final int SALT_SIZE = 8;

    @Resource
    private ESUserService esUserService;

    @Resource
    private AccessDataMapper accessDataMapper;

    /**
     * 设备信息的缓存类
     */
    @Resource
    private DeviceConfigurationCache deviceConfigurationCache;
    /**
     * 删除全部数据
     *
     * @return
     */
    @Override
    public Integer deleteAllUser() {
        return accessDataMapper.deleteAllUser();
    }

    /**
     * 新增用户接口数据
     *
     * @param map
     * @return
     */
    @Override
    public Integer insertUserList(Map<String, Object> map) {
        return accessDataMapper.insertUserList(map);
    }

    /**
     * 处理接口获取的用户数据
     *
     * @param jsonArray 用户数据
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Integer handleUserData(JSONArray jsonArray) {
        List<ESUser> list = new ArrayList<>();
        for(Object object : jsonArray){
            JSONObject jsonObject = (JSONObject) object;
            Long id = jsonObject.getLong("id");
            String username = jsonObject.getString("username");
            String password = jsonObject.getString("password");
            String realName = jsonObject.getString("realName");
            String headUrl = jsonObject.getString("headUrl");
            String email = jsonObject.getString("email");
            String mobile = jsonObject.getString("mobile");
            String deptId = jsonObject.getString("deptId");
            String deptName = jsonObject.getString("deptName");
            ESUser esUser = new ESUser();
            esUser.setF_id(id);
            esUser.setF_yhbh(username);
            esUser.setF_pass(password);
            esUser.setF_headimg(headUrl);
            esUser.setF_zzjgbh(deptId);
            esUser.setF_company(deptName);
            esUser.setF_email(email);
            esUser.setF_phone(mobile);
            esUser.setF_name(realName);
            entryptPassword(esUser);
            list.add(esUser);
        }
        Map<String,Object> map = new HashMap<>();
        map.put("list",list);
        //删除数据
        accessDataMapper.deleteAllUser();
        //新增数据
        Integer num = accessDataMapper.insertUserList(map);
        return num;
    }

    /**
     * 设定安全的密码，生成随机的salt并经过1024次 sha-1 hash
     */
    @Override
    public void entryptPassword(ESUser user) {
        byte[] salt = Digests.generateSalt(SALT_SIZE);
        user.setSalt(Encodes.encodeHex(salt));

        byte[] hashPassword = Digests.sha1((user.getF_yhbh()+user.getF_pass()).getBytes(), salt, HASH_INTERATIONS);
        user.setF_pass(Encodes.encodeHex(hashPassword));
    }

    /**
     * 处理接口获取的角色数据
     *
     * @param jsonArray 角色数据
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Integer handleRoleData(JSONArray jsonArray) {
        Map<String, Object> map = new HashMap<>();
        map.put("list",jsonArray);
        //删除数据
        accessDataMapper.deleteAllRole();
        //保存数据
        Integer num = accessDataMapper.insertRoleList(map);
        return num;
    }

    /**
     * 处理接口获取的组织机构数据
     *
     * @param jsonArray 组织机构数据
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Integer handleDeptData(JSONArray jsonArray) {
        Map<String, Object> map = new HashMap<>();
        map.put("list",jsonArray);
        //删除数据
        accessDataMapper.deleteAllDept();
        //保存数据
        Integer num = accessDataMapper.insertDeptList(map);
        return num;
    }

    /**
     * 处理接口获取的角色菜单关联关系数据
     *
     * @param jsonArray 角色菜单关联关系数据
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Integer handleRoleMenuData(JSONArray jsonArray) {
        Map<String, Object> map = new HashMap<>();
        map.put("list",jsonArray);
        //删除数据
        accessDataMapper.deleteAllRoleMenu();
        //保存数据
        Integer num = accessDataMapper.insertRoleMenuList(map);
        return num;
    }

    /**
     * 处理接口获取的某类型的资产信息
     *
     * @param jsonArray 某类型的资产信息
     * @param categoryCode 资产类型编码
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Integer handleAssetsData(JSONArray jsonArray,String categoryCode) {
        Map<String, Object> map = new HashMap<>();
        map.put("categoryCode",categoryCode);
        //删除该类型的资产数据
        Integer deleteNum = accessDataMapper.deleteAssetsByCode(map);
        map.put("list",jsonArray);
        //插入该类型的资产数据
        Integer num = accessDataMapper.insertAssetsInfo(map);
        return num;
    }

    /**
     * 处理接口获取的全部的资产信息数据
     *
     * @param jsonArray 某类型的资产信息
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Integer handleAllAssetsData(JSONArray jsonArray) {
        Map<String, Object> map = new HashMap<>();
        map.put("list",jsonArray);
        //删除全部的资产数据
        Integer deleteNum = accessDataMapper.deleteAllAssetsByCode();
        //插入资产数据
        Integer num = accessDataMapper.insertAssetsInfo(map);
        return num;
    }

    /**
     * 处理该类型对应的设备信息
     *
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Integer handleDeviceData() {
        List<Map> list = accessDataMapper.queryDeviceByTypeCode();
        Map<String, Object> map = new HashMap<>();
        map.put("list",list);
        //删除数据
        accessDataMapper.delAllDevice();
        //插入数据
        Integer num = accessDataMapper.insertDeviceList(map);
        //将数据加载到缓存中【设备信息接口是从缓存中取的数据，每次数据变动都需要更新缓存】
        deviceConfigurationCache.loadCache();
        return num;
    }

}
