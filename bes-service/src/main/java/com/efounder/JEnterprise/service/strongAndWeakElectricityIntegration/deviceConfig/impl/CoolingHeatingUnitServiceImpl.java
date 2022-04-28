package com.efounder.JEnterprise.service.strongAndWeakElectricityIntegration.deviceConfig.impl;

import com.core.common.ISSPReturnObject;
import com.efounder.JEnterprise.common.constants.Constants;
import com.efounder.JEnterprise.mapper.strongAndWeakElectricityIntegration.deviceConfig.CoolingHeatingUnitMapper;
import com.efounder.JEnterprise.model.strongAndWeakElectricityIntegration.deviceConfig.CoolingHeatingUnitModel;
import com.efounder.JEnterprise.service.strongAndWeakElectricityIntegration.deviceConfig.CoolingHeatingUnitService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @author xiepufeng
 * @date 2019/12/16 14:36
 */

@Service
public class CoolingHeatingUnitServiceImpl implements CoolingHeatingUnitService {

    @Resource
    private CoolingHeatingUnitMapper coolingHeatingUnitMapper;

    /**
     * 添加冷热机组配置
     * @param coolingHeatingUnitModel
     * @return
     */
    @Override
    public ISSPReturnObject create(CoolingHeatingUnitModel coolingHeatingUnitModel) {

        ISSPReturnObject returnObject = new ISSPReturnObject();

        if (null == coolingHeatingUnitModel) {
            returnObject.setStatus("0");
            returnObject.setMsg("参数错误！");
            return returnObject;
        }

        String name                               = coolingHeatingUnitModel.getName();
        String currentState                       = coolingHeatingUnitModel.getCurrentState();
        String freezeSupplyWaterTemperature       = coolingHeatingUnitModel.getFreezeSupplyWaterTemperature();
        String freezeReturnWaterTemperature       = coolingHeatingUnitModel.getFreezeReturnWaterTemperature();
        String coolingSupplyWaterTemperature      = coolingHeatingUnitModel.getCoolingSupplyWaterTemperature();
        String coolingReturnWaterTemperature      = coolingHeatingUnitModel.getCoolingReturnWaterTemperature();
        String currentFlow                        = coolingHeatingUnitModel.getCurrentFlow();
        String currentStateAlias                  = coolingHeatingUnitModel.getCurrentStateAlias();
        String freezeSupplyWaterTemperatureAlias  = coolingHeatingUnitModel.getFreezeSupplyWaterTemperatureAlias();
        String freezeReturnWaterTemperatureAlias  = coolingHeatingUnitModel.getFreezeReturnWaterTemperatureAlias();
        String coolingSupplyWaterTemperatureAlias = coolingHeatingUnitModel.getCoolingSupplyWaterTemperatureAlias();
        String coolingReturnWaterTemperatureAlias = coolingHeatingUnitModel.getCoolingReturnWaterTemperatureAlias();
        String currentFlowAlias                   = coolingHeatingUnitModel.getCurrentFlowAlias();

        if (!StringUtils.hasText(name)
                || !StringUtils.hasText(currentState)
                || !StringUtils.hasText(freezeSupplyWaterTemperature)
                || !StringUtils.hasText(freezeReturnWaterTemperature)
                || !StringUtils.hasText(coolingSupplyWaterTemperature)
                || !StringUtils.hasText(coolingReturnWaterTemperature)
                || !StringUtils.hasText(currentFlow)
                || !StringUtils.hasText(currentStateAlias)
                || !StringUtils.hasText(freezeSupplyWaterTemperatureAlias)
                || !StringUtils.hasText(freezeReturnWaterTemperatureAlias)
                || !StringUtils.hasText(coolingSupplyWaterTemperatureAlias)
                || !StringUtils.hasText(coolingReturnWaterTemperatureAlias)
                || !StringUtils.hasText(currentFlowAlias)

                ) {

            returnObject.setStatus("0");
            returnObject.setMsg("参数错误！");
            return returnObject;

        }

        try {
            coolingHeatingUnitMapper.insert(coolingHeatingUnitModel);

            returnObject.setStatus("1");
            returnObject.setMsg("保存成功");

        } catch (Exception e) {

            returnObject.setStatus("0");
            returnObject.setMsg("保存失败");

            e.printStackTrace();
        }

        return returnObject;
    }

    /**
     * 查询分页数据
     * @param pageSize
     * @param pageNum
     * @param coolingHeatingUnitModel
     * @return
     */
    @Override
    public PageInfo<Object> getPaging(Integer pageSize, Integer pageNum, CoolingHeatingUnitModel coolingHeatingUnitModel) {
        if (pageNum == null){
            pageNum = 1;
        }

        if (pageSize == null) {
            pageSize = Constants.PAGE_SIZE;
        }

        PageHelper.startPage(pageNum, pageSize);
        // 紧跟着的第一个select方法会被分页
        List<Object> list = coolingHeatingUnitMapper.findList(coolingHeatingUnitModel);
        // 用PageInfo对结果进行包装
        PageInfo<Object> page = new PageInfo<>(list);
        return page;
    }

    /**
     * 查询冷热机组配置信息
     * @param coolingHeatingUnitModel
     * @return
     */
    @Override
    public ISSPReturnObject query(CoolingHeatingUnitModel coolingHeatingUnitModel) {

        ISSPReturnObject isspReturnObject = new ISSPReturnObject();

        try {
            List<Object> list = coolingHeatingUnitMapper.findList(coolingHeatingUnitModel);
            isspReturnObject.setData(list);
            isspReturnObject.setStatus("1");
        } catch (Exception e) {
            isspReturnObject.setStatus("0");
            e.printStackTrace();
        }

        return isspReturnObject;
    }

    /**
     * 更新冷热机组配置信息
     * */
    @Override
    public ISSPReturnObject update(CoolingHeatingUnitModel coolingHeatingUnitModel) {

        ISSPReturnObject returnObject = new ISSPReturnObject();

        if (null == coolingHeatingUnitModel) {
            returnObject.setStatus("0");
            returnObject.setMsg("参数错误！");
            return returnObject;
        }

        Integer id                                 = coolingHeatingUnitModel.getId();
        String  name                               = coolingHeatingUnitModel.getName();
        String  currentState                       = coolingHeatingUnitModel.getCurrentState();
        String  freezeSupplyWaterTemperature       = coolingHeatingUnitModel.getFreezeSupplyWaterTemperature();
        String  freezeReturnWaterTemperature       = coolingHeatingUnitModel.getFreezeReturnWaterTemperature();
        String  coolingSupplyWaterTemperature      = coolingHeatingUnitModel.getCoolingSupplyWaterTemperature();
        String  coolingReturnWaterTemperature      = coolingHeatingUnitModel.getCoolingReturnWaterTemperature();
        String  currentFlow                        = coolingHeatingUnitModel.getCurrentFlow();
        String  currentStateAlias                  = coolingHeatingUnitModel.getCurrentStateAlias();
        String  freezeSupplyWaterTemperatureAlias  = coolingHeatingUnitModel.getFreezeSupplyWaterTemperatureAlias();
        String  freezeReturnWaterTemperatureAlias  = coolingHeatingUnitModel.getFreezeReturnWaterTemperatureAlias();
        String  coolingSupplyWaterTemperatureAlias = coolingHeatingUnitModel.getCoolingSupplyWaterTemperatureAlias();
        String  coolingReturnWaterTemperatureAlias = coolingHeatingUnitModel.getCoolingReturnWaterTemperatureAlias();
        String  currentFlowAlias                   = coolingHeatingUnitModel.getCurrentFlowAlias();

        if (null == id
                || !StringUtils.hasText(name)
                || !StringUtils.hasText(currentState)
                || !StringUtils.hasText(freezeSupplyWaterTemperature)
                || !StringUtils.hasText(freezeReturnWaterTemperature)
                || !StringUtils.hasText(coolingSupplyWaterTemperature)
                || !StringUtils.hasText(coolingReturnWaterTemperature)
                || !StringUtils.hasText(currentFlow)
                || !StringUtils.hasText(currentStateAlias)
                || !StringUtils.hasText(freezeSupplyWaterTemperatureAlias)
                || !StringUtils.hasText(freezeReturnWaterTemperatureAlias)
                || !StringUtils.hasText(coolingSupplyWaterTemperatureAlias)
                || !StringUtils.hasText(coolingReturnWaterTemperatureAlias)
                || !StringUtils.hasText(currentFlowAlias)

                ) {

            returnObject.setStatus("0");
            returnObject.setMsg("参数错误！");
            return returnObject;

        }

        CoolingHeatingUnitModel chum = new CoolingHeatingUnitModel();
        chum.setId(id);

        try {
            List<Object> list = coolingHeatingUnitMapper.findList(chum);

            if (null == list || list.isEmpty()){

                returnObject.setStatus("0");
                returnObject.setMsg("参数错误！");
                return returnObject;
            }

        } catch (Exception e) {
            returnObject.setStatus("0");
            e.printStackTrace();
        }

        try {
            coolingHeatingUnitMapper.update(coolingHeatingUnitModel);
            returnObject.setStatus("1");
        } catch (Exception e) {
            returnObject.setStatus("0");
            e.printStackTrace();
        }

        return returnObject;
    }

    /**
     * 删除冷热机组配置信息
     * @param coolingHeatingUnitModel
     * @return
     */
    @Override
    public ISSPReturnObject delete(CoolingHeatingUnitModel coolingHeatingUnitModel) {

        ISSPReturnObject returnObject = new ISSPReturnObject();
        List<Map<String,Object>> listcommonconfig = null;
        List<Map<String,Object>> listcommonconfig_top = null;

        if (null == coolingHeatingUnitModel) {
            returnObject.setStatus("0");
            returnObject.setMsg("参数错误！");
            return returnObject;
        }

        Integer id = coolingHeatingUnitModel.getId();

        if (null == id) {
            returnObject.setStatus("0");
            returnObject.setMsg("参数错误！");
            return returnObject;
        }

        CoolingHeatingUnitModel chum = new CoolingHeatingUnitModel();
        chum.setId(id);

        try {
            List<Object> list = coolingHeatingUnitMapper.findList(chum);

            if (null == list || list.isEmpty()){

                returnObject.setStatus("0");
                returnObject.setMsg("参数错误！");
                return returnObject;
            }

            //查询强弱电页面展示通用配置表strongandweakelectricityintegration_commonconfig和
            // 强弱电页面展示通用配置表  左侧的topstrongandweakelectricityintegration_commonconfig_top是否有配置的信息
            listcommonconfig = coolingHeatingUnitMapper.findlistcommonconfig(chum);
            listcommonconfig_top = coolingHeatingUnitMapper.findlistcommonconfig_top(chum);

            
        } catch (Exception e) {
            returnObject.setStatus("0");
            e.printStackTrace();
        }


        try {
            coolingHeatingUnitMapper.delete(id);
            if (listcommonconfig.size() > 0) {
                coolingHeatingUnitMapper.deletecommonconfigById(id);
            }
            if (listcommonconfig_top.size() > 0) {
                coolingHeatingUnitMapper.deletecommonconfig_topById(id);
            }
            returnObject.setStatus("1");
        } catch (Exception e) {
            returnObject.setStatus("0");
            e.printStackTrace();
        }

        return returnObject;
    }

    @Override
    public ISSPReturnObject coolingHeatingUnit(CoolingHeatingUnitModel coolingHeatingUnitModel){
        ISSPReturnObject isspReturnObject=new ISSPReturnObject();
        try {
            //if (coolingHeatingUnitModel.getElectric_meter_number()==null){
                // coolingTowerConfigModel.setId(1);
               // List<Object> list=coolingHeatingUnitMapper.findList(coolingHeatingUnitModel);
                //isspReturnObject.setData(list);
                //isspReturnObject.setStatus("1");
            //}else {
                List<Object> list=coolingHeatingUnitMapper.coolingHeatingUnit(coolingHeatingUnitModel);
                isspReturnObject.setData(list);
                isspReturnObject.setStatus("1");
           // }
        }catch (Exception e){
            isspReturnObject.setStatus("0");
            e.printStackTrace();
        }
        return isspReturnObject;
    }

}
