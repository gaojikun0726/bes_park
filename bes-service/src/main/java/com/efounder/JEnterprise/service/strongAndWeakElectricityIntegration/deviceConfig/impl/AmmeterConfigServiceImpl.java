package com.efounder.JEnterprise.service.strongAndWeakElectricityIntegration.deviceConfig.impl;

import com.core.common.ISSPReturnObject;
import com.efounder.JEnterprise.common.constants.Constants;
import com.efounder.JEnterprise.mapper.strongAndWeakElectricityIntegration.deviceConfig.AmmeterConfigMapper;
import com.efounder.JEnterprise.model.strongAndWeakElectricityIntegration.deviceConfig.AmmeterConfigModel;
import com.efounder.JEnterprise.service.strongAndWeakElectricityIntegration.deviceConfig.AmmeterConfigService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author xiepufeng
 * @date 2019/12/16 14:36
 */

@Service
public class AmmeterConfigServiceImpl implements AmmeterConfigService {

    @Resource
    private AmmeterConfigMapper ammeterConfigMapper;

    /**
     * 添加电表配置
     * @param ammeterConfigModel
     * @return
     */
    @Override
    public ISSPReturnObject create(AmmeterConfigModel ammeterConfigModel) {

        ISSPReturnObject returnObject = new ISSPReturnObject();

        if (null == ammeterConfigModel) {
            returnObject.setStatus("0");
            returnObject.setMsg("参数错误！");
            return returnObject;
        }

        String  name        = ammeterConfigModel.getName();
        String  cabinetName = ammeterConfigModel.getCabinetName();

        if (!StringUtils.hasText(name) || !StringUtils.hasText(cabinetName)) {

            returnObject.setStatus("0");
            returnObject.setMsg("参数错误！");
            return returnObject;

        }

        try {
            ammeterConfigMapper.insert(ammeterConfigModel);

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
     * @param ammeterConfigModel
     * @return
     */
    @Override
    public PageInfo<Object> getPaging(Integer pageSize, Integer pageNum, AmmeterConfigModel ammeterConfigModel) {
        if (pageNum == null){
            pageNum = 1;
        }

        if (pageSize == null) {
            pageSize = Constants.PAGE_SIZE;
        }

        PageHelper.startPage(pageNum, pageSize);
        // 紧跟着的第一个select方法会被分页
        List<Object> list = ammeterConfigMapper.findList(ammeterConfigModel);
        // 用PageInfo对结果进行包装
        PageInfo<Object> page = new PageInfo<>(list);
        return page;
    }

    /**
     * 查询电表配置信息
     * @param ammeterConfigModel
     * @return
     */
    @Override
    public ISSPReturnObject query(AmmeterConfigModel ammeterConfigModel) {

        ISSPReturnObject isspReturnObject = new ISSPReturnObject();

        try {
            List<Object> list = ammeterConfigMapper.findList(ammeterConfigModel);
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
    public ISSPReturnObject update(AmmeterConfigModel ammeterConfigModel) {

        ISSPReturnObject returnObject = new ISSPReturnObject();

        if (null == ammeterConfigModel) {
            returnObject.setStatus("0");
            returnObject.setMsg("参数错误！");
            return returnObject;
        }

        Integer id          = ammeterConfigModel.getId();
        String  name        = ammeterConfigModel.getName();
        String  cabinetName = ammeterConfigModel.getCabinetName();

        if (null == id
                || !StringUtils.hasText(name)
                || !StringUtils.hasText(cabinetName)
                ) {

            returnObject.setStatus("0");
            returnObject.setMsg("参数错误！");
            return returnObject;

        }

        AmmeterConfigModel acm = new AmmeterConfigModel();
        acm.setId(id);

        try {
            List<Object> list = ammeterConfigMapper.findList(acm);

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
            ammeterConfigMapper.update(ammeterConfigModel);
            returnObject.setStatus("1");
        } catch (Exception e) {
            returnObject.setStatus("0");
            e.printStackTrace();
        }

        return returnObject;
    }

    /**
     * 删除电表配置信息
     * @param ammeterConfigModel
     * @return
     */
    @Override
    public ISSPReturnObject delete(AmmeterConfigModel ammeterConfigModel) {

        ISSPReturnObject returnObject = new ISSPReturnObject();

        if (null == ammeterConfigModel) {
            returnObject.setStatus("0");
            returnObject.setMsg("参数错误！");
            return returnObject;
        }

        Integer id = ammeterConfigModel.getId();

        if (null == id) {
            returnObject.setStatus("0");
            returnObject.setMsg("参数错误！");
            return returnObject;
        }

        AmmeterConfigModel chum = new AmmeterConfigModel();
        chum.setId(id);

        try {
            List<Object> list = ammeterConfigMapper.findList(chum);

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
            ammeterConfigMapper.delete(id);
            returnObject.setStatus("1");
        } catch (Exception e) {
            returnObject.setStatus("0");
            e.printStackTrace();
        }

        return returnObject;
    }
}
