package com.efounder.JEnterprise.mapper.strongAndWeakElectricityIntegration.deviceConfig;

import com.efounder.JEnterprise.model.strongAndWeakElectricityIntegration.deviceConfig.ChilledWaterModel;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface ChilledWaterMapper {

    List<Object> findList(ChilledWaterModel chilledWaterModel);
    List<Object> queryColdCooling(ChilledWaterModel chilledWaterModel);

    void insert(ChilledWaterModel chilledWaterModel);

    void update(ChilledWaterModel chilledWaterModel);

    void delete(Integer id);
    List<Object>  detailedinformation(String f_equipment_id);
    List<Object> electricMeterNnumber();

    /**
     *
     * @Description: 根据冷冻水冷却水的id查询变频器运行状态
     *
     * @auther: wanghongjie
     * @date: 15:02 2020/11/11
     * @param: [coldWarmWaterID]
     * @return: java.lang.String
     *
     */
    String queryColdWarmWaterWithF_BPYXZT(String coldWarmWaterID);

    /**
     *
     * @Description: 查询强弱电页面展示通用配置表strongandweakelectricityintegration_commonconfig是否有配置的信息
     *
     * @auther: wanghongjie
     * @date: 9:27 2020/11/12
     * @param: [chum]
     * @return: java.util.List<java.lang.Object>
     *
     * @param chum
     */
    List<Map<String,Object>> findlistcommonconfig(ChilledWaterModel chum);
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
    List<Map<String, Object>> findlistcommonconfig_top(ChilledWaterModel chum);

    void deletecommonconfigById(Integer id);
    void deletecommonconfig_topById(Integer id);
}
