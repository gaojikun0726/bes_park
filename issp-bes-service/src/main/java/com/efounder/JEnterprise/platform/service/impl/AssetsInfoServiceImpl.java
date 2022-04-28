package com.efounder.JEnterprise.platform.service.impl;

import com.core.common.ISSPReturnObject;
import com.efounder.JEnterprise.platform.mapper.AssetsInfoMapper;
import com.efounder.JEnterprise.platform.service.AssetsInfoService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * describe: 资产管理
 *
 * @author zs
 * @date 2020/12/14
 */
@Service
public class AssetsInfoServiceImpl implements AssetsInfoService {

    @Resource
    private AssetsInfoMapper assetsInfoMapper;

    @Value("${opm.get_assets.category_code.meter_code}")
    public String[] meterCode;

    /**
     * 根据资产类型编码查询所有的资产编码
     *
     * @param typeCode 资产类型编码
     * @return
     */
    @Override
    public List<String> getCodeList(String typeCode) {
        return assetsInfoMapper.getCodeList(typeCode);
    }


    /**
     * 查询该资产编码是否存在于资产表中
     *
     * @param assetsCode 资产编码
     * @return
     */
    @Override
    public Integer assetsCodeExist(String assetsCode) {
        return assetsInfoMapper.assetsCodeExist(meterCode,assetsCode);
    }

    /**
     * 查询电表表中是否有重复的
     *
     * @param map 资产编码 电表id
     * @return
     */
    @Override
    public Integer querySameCode(Map<String, String> map) {
        return assetsInfoMapper.querySameCode(map);
    }

    /**
     * 校验输入的资产编码是否可用
     * @param assetsCode 资产编码
     * @param fGuid 电表id
     * @return
     */
    @Override
    public boolean verifyAssetsCode(String assetsCode, String fGuid){
        boolean result = false;
        //查询该资产编码是否存在于资产表中
        Integer num = assetsInfoMapper.assetsCodeExist(meterCode,assetsCode);
        if(num == 1){
            Map<String,String> map = new HashMap<>();
            map.put("assetsCode",assetsCode);
            if(fGuid != null && !"".equals(fGuid)){
                map.put("fGuid",fGuid);
            }
            //查询电表表中是否有重复的
            Integer sameNum = assetsInfoMapper.querySameCode(map);
            if(sameNum == 0){
                result = true;
            }
        }
        return result;
    }

    /**
     * 查询某种类型设备列表
     *
     * @param map 设备类型编码
     * @return
     */
    @Override
    public List<Map> getDeviceTypeList(Map<String, Object> map) {
        return assetsInfoMapper.getDeviceTypeList(map);
    }

    /**
     * 根据设备类型编码查询设备唯一序列号列表
     *
     * @param map 设备类型编码
     * @return
     */
    @Override
    public List<String> getDeviceSerialList(Map<String, Object> map) {
        return assetsInfoMapper.getDeviceSerialList(map);
    }

    /**
     * 查询灯杆的设备序列号是否重复
     *
     * @param map 灯杆的fGuid
     * @return
     */
    @Override
    public Integer querySameSerial(Map<String, Object> map) {
        return assetsInfoMapper.querySameSerial(map);
    }
}
