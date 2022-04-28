package com.efounder.JEnterprise.mapper.strongAndWeakElectricityIntegration.deviceConfig;

import com.efounder.JEnterprise.model.strongAndWeakElectricityIntegration.deviceConfig.CoolingTowerConfigModel;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * @author xiepufeng
 * @date 2019/12/16 14:59
 */
@Mapper
public interface CoolingTowerConfigMapper {

    void insert(CoolingTowerConfigModel coolingTowerConfigModel);

    List<Object> queryColdCooling(CoolingTowerConfigModel coolingTowerConfigModel);

    List<Object> findList(CoolingTowerConfigModel coolingTowerConfigModel);

    void update(CoolingTowerConfigModel coolingTowerConfigModel);

    void delete(Integer id);

    /**
     *
     * @Description: 查询强弱电页面展示通用配置表strongandweakelectricityintegration_commonconfig是否有配置的信息
     *
     * @auther: wanghongjie
     * @date: 9:27 2020/11/12
     * @param: [chum]
     * @return: java.util.List<java.lang.Object>
     *@param chum
     */
    List<Map<String,Object>> findlistcommonconfig(CoolingTowerConfigModel chum);
    /**
     *
     * @Description: 强弱电页面展示通用配置表  左侧的topstrongandweakelectricityintegration_commonconfig_top是否有配置的信息
     *
     * @auther: wanghongjie
     * @date: 9:34 2020/11/12
     * @param: [chum]
     * @return: java.util.List<java.util.Map<java.lang.String,java.lang.Object>>
     *
     */
    List<Map<String, Object>> findlistcommonconfig_top(CoolingTowerConfigModel chum);

    void deletecommonconfigById(Integer id);
    void deletecommonconfig_topById(Integer id);


}
