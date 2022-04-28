package com.efounder.JEnterprise.service.app.impl;

import com.core.common.ISSPReturnObject;
import com.efounder.JEnterprise.common.constants.Constants;
import com.efounder.JEnterprise.mapper.app.AirconditioningUnitConfigMapper;
import com.efounder.JEnterprise.model.app.AirconditioningUnitConfigModel;
import com.efounder.JEnterprise.service.app.AirconditioningUnitConfigService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author wzx
 * @date 2020-5-12 13:48:58
 */

@Service
public class AirconditioningUnitConfigServiceImpl implements AirconditioningUnitConfigService {

    @Resource
    private AirconditioningUnitConfigMapper airconditioningUnitConfigMapper;

    /**
     * 添加电表配置
     * @param airconditioningUnitConfigModel
     * @return
     */
    @Override
    public ISSPReturnObject create(AirconditioningUnitConfigModel airconditioningUnitConfigModel) {

        ISSPReturnObject returnObject = new ISSPReturnObject();

        if (null == airconditioningUnitConfigModel) {
            returnObject.setStatus("0");
            returnObject.setMsg("参数错误！");
            return returnObject;
        }

        String  name        = airconditioningUnitConfigModel.getName();
        String  ktAddress = airconditioningUnitConfigModel.getAddress();

        if (!StringUtils.hasText(name) || !StringUtils.hasText(ktAddress)) {

            returnObject.setStatus("0");
            returnObject.setMsg("参数错误！");
            return returnObject;

        }

        try {
            airconditioningUnitConfigMapper.insert(airconditioningUnitConfigModel);

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
     * @param airconditioningUnitConfigModel
     * @return
     */
    @Override
    public PageInfo<Object> getPaging(Integer pageSize, Integer pageNum, AirconditioningUnitConfigModel airconditioningUnitConfigModel) {
        if (pageNum == null){
            pageNum = 1;
        }

        if (pageSize == null) {
            pageSize = Constants.PAGE_SIZE;
        }

        PageHelper.startPage(pageNum, pageSize);
        // 紧跟着的第一个select方法会被分页
        List<Object> list = airconditioningUnitConfigMapper.findList(airconditioningUnitConfigModel);
        // 用PageInfo对结果进行包装
        PageInfo<Object> page = new PageInfo<>(list);
        return page;
    }

    /**
     * 查询电表配置信息
     * @param airconditioningUnitConfigModel
     * @return
     */
    @Override
    public ISSPReturnObject query(AirconditioningUnitConfigModel airconditioningUnitConfigModel) {

        ISSPReturnObject isspReturnObject = new ISSPReturnObject();

        try {
            List<Object> list = airconditioningUnitConfigMapper.findList(airconditioningUnitConfigModel);
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
    public ISSPReturnObject update(AirconditioningUnitConfigModel airconditioningUnitConfigModel) {

        ISSPReturnObject returnObject = new ISSPReturnObject();

        if (null == airconditioningUnitConfigModel) {
            returnObject.setStatus("0");
            returnObject.setMsg("参数错误！");
            return returnObject;
        }

        Integer id          = airconditioningUnitConfigModel.getId();
        String  name        = airconditioningUnitConfigModel.getName();
        String  address = airconditioningUnitConfigModel.getAddress();

        if (null == id
                || !StringUtils.hasText(name)
                || !StringUtils.hasText(address)
                ) {

            returnObject.setStatus("0");
            returnObject.setMsg("参数错误！");
            return returnObject;

        }

        AirconditioningUnitConfigModel acm = new AirconditioningUnitConfigModel();
        acm.setId(id);

        try {
            List<Object> list = airconditioningUnitConfigMapper.findList(acm);

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
            airconditioningUnitConfigMapper.update(airconditioningUnitConfigModel);
            returnObject.setStatus("1");
        } catch (Exception e) {
            returnObject.setStatus("0");
            e.printStackTrace();
        }

        return returnObject;
    }

    /**
     * 删除电表配置信息
     * @param airconditioningUnitConfigModel
     * @return
     */
    @Override
    public ISSPReturnObject delete(AirconditioningUnitConfigModel airconditioningUnitConfigModel) {

        ISSPReturnObject returnObject = new ISSPReturnObject();

        if (null == airconditioningUnitConfigModel) {
            returnObject.setStatus("0");
            returnObject.setMsg("参数错误！");
            return returnObject;
        }

        Integer id = airconditioningUnitConfigModel.getId();

        if (null == id) {
            returnObject.setStatus("0");
            returnObject.setMsg("参数错误！");
            return returnObject;
        }

        AirconditioningUnitConfigModel chum = new AirconditioningUnitConfigModel();
        chum.setId(id);

        try {
            List<Object> list = airconditioningUnitConfigMapper.findList(chum);

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
            airconditioningUnitConfigMapper.delete(id);
            returnObject.setStatus("1");
        } catch (Exception e) {
            returnObject.setStatus("0");
            e.printStackTrace();
        }

        return returnObject;
    }

}
