package com.efounder.JEnterprise.service.strongAndWeakElectricityIntegration.deviceConfig.impl;

import com.core.common.ISSPReturnObject;
import com.efounder.JEnterprise.common.constants.Constants;
import com.efounder.JEnterprise.mapper.strongAndWeakElectricityIntegration.deviceConfig.CoolingTowerConfigMapper;
import com.efounder.JEnterprise.model.strongAndWeakElectricityIntegration.deviceConfig.CoolingTowerConfigModel;
import com.efounder.JEnterprise.service.strongAndWeakElectricityIntegration.deviceConfig.CoolingTowerConfigService;
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
public class CoolingTowerConfigServiceImpl implements CoolingTowerConfigService {

    @Resource
    private CoolingTowerConfigMapper coolingTowerConfigMapper;

    /**
     * 添加电表配置
     * @param coolingTowerConfigModel
     * @return
     */
    @Override
    public ISSPReturnObject create(CoolingTowerConfigModel coolingTowerConfigModel) {

        ISSPReturnObject returnObject = new ISSPReturnObject();

        if (null == coolingTowerConfigModel) {
            returnObject.setStatus("0");
            returnObject.setMsg("参数错误！");
            return returnObject;
        }

        String  name = coolingTowerConfigModel.getName();

        if (!StringUtils.hasText(name)) {

            returnObject.setStatus("0");
            returnObject.setMsg("参数错误！");
            return returnObject;

        }

        try {
            coolingTowerConfigMapper.insert(coolingTowerConfigModel);

            returnObject.setStatus("1");
            returnObject.setMsg("保存成功");

        } catch (Exception e) {

            returnObject.setStatus("0");
            returnObject.setMsg("保存失败");

            e.printStackTrace();
        }

        return returnObject;
    }

    @Override
    public ISSPReturnObject queryTownCooling(CoolingTowerConfigModel coolingTowerConfigModel) {
        ISSPReturnObject isspReturnObject=new ISSPReturnObject();
        try {
            if (coolingTowerConfigModel.getId()==null){
                // coolingTowerConfigModel.setId(1);
                List<Object> list=coolingTowerConfigMapper.queryColdCooling(coolingTowerConfigModel);
                isspReturnObject.setData(list);
                isspReturnObject.setStatus("1");
            }else {
                List<Object> list=coolingTowerConfigMapper.queryColdCooling(coolingTowerConfigModel);
                isspReturnObject.setData(list);
                isspReturnObject.setStatus("1");
            }
        }catch (Exception e){
            isspReturnObject.setStatus("0");
            e.printStackTrace();
        }
        return isspReturnObject;
    }

    /**
     * 查询分页数据
     * @param pageSize
     * @param pageNum
     * @param coolingTowerConfigModel
     * @return
     */
    @Override
    public PageInfo<Object> getPaging(Integer pageSize, Integer pageNum, CoolingTowerConfigModel coolingTowerConfigModel) {
        if (pageNum == null){
            pageNum = 1;
        }

        if (pageSize == null) {
            pageSize = Constants.PAGE_SIZE;
        }

        PageHelper.startPage(pageNum, pageSize);
        // 紧跟着的第一个select方法会被分页
        List<Object> list = coolingTowerConfigMapper.findList(coolingTowerConfigModel);
        // 用PageInfo对结果进行包装
        PageInfo<Object> page = new PageInfo<>(list);
        return page;
    }

    /**
     * 查询电表配置信息
     * @param coolingTowerConfigModel
     * @return
     */
    @Override
    public ISSPReturnObject query(CoolingTowerConfigModel coolingTowerConfigModel) {

        ISSPReturnObject isspReturnObject = new ISSPReturnObject();

        try {
            List<Object> list = coolingTowerConfigMapper.findList(coolingTowerConfigModel);
            isspReturnObject.setData(list);
            isspReturnObject.setStatus("1");
        } catch (Exception e) {
            isspReturnObject.setStatus("0");
            e.printStackTrace();
        }

        return isspReturnObject;
    }

    /**
     * 更新电表配置信息
     * */
    @Override
    public ISSPReturnObject update(CoolingTowerConfigModel coolingTowerConfigModel) {

        ISSPReturnObject returnObject = new ISSPReturnObject();

        if (null == coolingTowerConfigModel) {
            returnObject.setStatus("0");
            returnObject.setMsg("参数错误！");
            return returnObject;
        }

        Integer id          = coolingTowerConfigModel.getId();
        String  name        = coolingTowerConfigModel.getName();

        if (null == id
                || !StringUtils.hasText(name)
        ) {

            returnObject.setStatus("0");
            returnObject.setMsg("参数错误！");
            return returnObject;

        }

        CoolingTowerConfigModel acm = new CoolingTowerConfigModel();
        acm.setId(id);

        try {
            List<Object> list = coolingTowerConfigMapper.findList(acm);

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
            coolingTowerConfigMapper.update(coolingTowerConfigModel);
            returnObject.setStatus("1");
        } catch (Exception e) {
            returnObject.setStatus("0");
            e.printStackTrace();
        }

        return returnObject;
    }

    /**
     * 删除电表配置信息
     * @param coolingTowerConfigModel
     * @return
     */
    @Override
    public ISSPReturnObject delete(CoolingTowerConfigModel coolingTowerConfigModel) {

        ISSPReturnObject returnObject = new ISSPReturnObject();
        List<Map<String,Object>> listcommonconfig = null;
        List<Map<String,Object>> listcommonconfig_top = null;

        if (null == coolingTowerConfigModel) {
            returnObject.setStatus("0");
            returnObject.setMsg("参数错误！");
            return returnObject;
        }

        Integer id = coolingTowerConfigModel.getId();

        if (null == id) {
            returnObject.setStatus("0");
            returnObject.setMsg("参数错误！");
            return returnObject;
        }

        CoolingTowerConfigModel chum = new CoolingTowerConfigModel();
        chum.setId(id);

        try {
            List<Object> list = coolingTowerConfigMapper.findList(chum);

            if (null == list || list.isEmpty()){

                returnObject.setStatus("0");
                returnObject.setMsg("参数错误！");
                return returnObject;
            }
            //查询强弱电页面展示通用配置表strongandweakelectricityintegration_commonconfig和
            // 强弱电页面展示通用配置表  左侧的topstrongandweakelectricityintegration_commonconfig_top是否有配置的信息
            listcommonconfig = coolingTowerConfigMapper.findlistcommonconfig(chum);
            listcommonconfig_top = coolingTowerConfigMapper.findlistcommonconfig_top(chum);

        } catch (Exception e) {
            returnObject.setStatus("0");
            e.printStackTrace();
        }


        try {
            coolingTowerConfigMapper.delete(id);
            if (listcommonconfig.size() > 0) {
                coolingTowerConfigMapper.deletecommonconfigById(id);
            }
            if (listcommonconfig_top.size() > 0) {
                coolingTowerConfigMapper.deletecommonconfig_topById(id);
            }
            returnObject.setStatus("1");
        } catch (Exception e) {
            returnObject.setStatus("0");
            e.printStackTrace();
        }

        return returnObject;
    }
}
