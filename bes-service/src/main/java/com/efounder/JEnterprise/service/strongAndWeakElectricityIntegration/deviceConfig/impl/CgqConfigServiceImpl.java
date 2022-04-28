package com.efounder.JEnterprise.service.strongAndWeakElectricityIntegration.deviceConfig.impl;

import com.core.common.ISSPReturnObject;
import com.efounder.JEnterprise.common.constants.Constants;
import com.efounder.JEnterprise.mapper.strongAndWeakElectricityIntegration.deviceConfig.CgqConfigMapper;
import com.efounder.JEnterprise.model.strongAndWeakElectricityIntegration.deviceConfig.CgqConfigModel;
import com.efounder.JEnterprise.service.strongAndWeakElectricityIntegration.deviceConfig.CgqConfigService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author wzx
 * @date 2020-8-10 17:55:48
 */

@Service
public class CgqConfigServiceImpl implements CgqConfigService {

    @Resource
    private CgqConfigMapper cgqConfigMapper;

    /**
     * 添加传感器配置
     * @param cgqConfigModel
     * @return
     */
    @Override
    public ISSPReturnObject create(CgqConfigModel cgqConfigModel) {

        ISSPReturnObject returnObject = new ISSPReturnObject();

        if (null == cgqConfigModel) {
            returnObject.setStatus("0");
            returnObject.setMsg("参数错误！");
            return returnObject;
        }

        String  name        = cgqConfigModel.getName();
        String  cgqType = cgqConfigModel.getCgqType();

        if (!StringUtils.hasText(name) || !StringUtils.hasText(cgqType)) {

            returnObject.setStatus("0");
            returnObject.setMsg("参数错误！");
            return returnObject;

        }

        try {
            cgqConfigMapper.insert(cgqConfigModel);

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
     * @param cgqConfigModel
     * @return
     */
    @Override
    public PageInfo<Object> getPaging(Integer pageSize, Integer pageNum, CgqConfigModel cgqConfigModel) {
        if (pageNum == null){
            pageNum = 1;
        }

        if (pageSize == null) {
            pageSize = Constants.PAGE_SIZE;
        }

        PageHelper.startPage(pageNum, pageSize);
        // 紧跟着的第一个select方法会被分页
        List<Object> list = cgqConfigMapper.findList(cgqConfigModel);
        // 用PageInfo对结果进行包装
        PageInfo<Object> page = new PageInfo<>(list);
        return page;
    }

    /**
     * 查询传感器配置信息
     * @param cgqConfigModel
     * @return
     */
    @Override
    public ISSPReturnObject query(CgqConfigModel cgqConfigModel) {

        ISSPReturnObject isspReturnObject = new ISSPReturnObject();

        try {
            List<Object> list = cgqConfigMapper.findList(cgqConfigModel);
            isspReturnObject.setData(list);
            isspReturnObject.setStatus("1");
        } catch (Exception e) {
            isspReturnObject.setStatus("0");
            e.printStackTrace();
        }

        return isspReturnObject;
    }
    /**
     * 根据传感器类型查询传感器配置信息
     * @param cgqConfigModel
     * @return
     */
    @Override
    public ISSPReturnObject queryByType(String cgqType,CgqConfigModel cgqConfigModel) {

        ISSPReturnObject isspReturnObject = new ISSPReturnObject();

        try {
            List<Object> list = cgqConfigMapper.findListByType(cgqType,cgqConfigModel);
            isspReturnObject.setData(list);
            isspReturnObject.setStatus("1");
        } catch (Exception e) {
            isspReturnObject.setStatus("0");
            e.printStackTrace();
        }

        return isspReturnObject;
    }

    /**
     * 更新传感器配置信息
     * */
    @Override
    public ISSPReturnObject update(CgqConfigModel cgqConfigModel) {

        ISSPReturnObject returnObject = new ISSPReturnObject();

        if (null == cgqConfigModel) {
            returnObject.setStatus("0");
            returnObject.setMsg("参数错误！");
            return returnObject;
        }

        Integer id          = cgqConfigModel.getId();
        String  name        = cgqConfigModel.getName();
        String  cgqType     = cgqConfigModel.getCgqType();

        if (null == id
                || !StringUtils.hasText(name)
                || !StringUtils.hasText(cgqType)
                ) {

            returnObject.setStatus("0");
            returnObject.setMsg("参数错误！");
            return returnObject;

        }

        CgqConfigModel acm = new CgqConfigModel();
        acm.setId(id);

        try {
            List<Object> list = cgqConfigMapper.findList(acm);

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
            cgqConfigMapper.update(cgqConfigModel);
            returnObject.setStatus("1");
        } catch (Exception e) {
            returnObject.setStatus("0");
            e.printStackTrace();
        }

        return returnObject;
    }

    /**
     * 删除传感器配置信息
     * @param cgqConfigModel
     * @return
     */
    @Override
    public ISSPReturnObject delete(CgqConfigModel cgqConfigModel) {

        ISSPReturnObject returnObject = new ISSPReturnObject();

        if (null == cgqConfigModel) {
            returnObject.setStatus("0");
            returnObject.setMsg("参数错误！");
            return returnObject;
        }

        Integer id = cgqConfigModel.getId();

        if (null == id) {
            returnObject.setStatus("0");
            returnObject.setMsg("参数错误！");
            return returnObject;
        }

        CgqConfigModel chum = new CgqConfigModel();
        chum.setId(id);

        try {
            List<Object> list = cgqConfigMapper.findList(chum);

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
            cgqConfigMapper.delete(id);
            returnObject.setStatus("1");
        } catch (Exception e) {
            returnObject.setStatus("0");
            e.printStackTrace();
        }

        return returnObject;
    }
}
