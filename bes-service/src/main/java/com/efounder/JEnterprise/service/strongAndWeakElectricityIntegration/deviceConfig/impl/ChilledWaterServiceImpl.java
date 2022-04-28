package com.efounder.JEnterprise.service.strongAndWeakElectricityIntegration.deviceConfig.impl;

import com.core.common.ISSPReturnObject;
import com.efounder.JEnterprise.common.constants.Constants;
import com.efounder.JEnterprise.mapper.strongAndWeakElectricityIntegration.deviceConfig.ChilledWaterMapper;
import com.efounder.JEnterprise.model.strongAndWeakElectricityIntegration.deviceConfig.ChilledWaterModel;
import com.efounder.JEnterprise.service.strongAndWeakElectricityIntegration.deviceConfig.ChilledWaterService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service
public class ChilledWaterServiceImpl implements ChilledWaterService {

    @Resource
    private ChilledWaterMapper chilledWaterMapper;

    /**
     * 查询分页数据
     * @param pageSize
     * @param pageNum
     * @param chilledWaterModel
     * @return
     */
    @Override
    public PageInfo<Object> getPaging(Integer pageSize, Integer pageNum, ChilledWaterModel chilledWaterModel) {
        if (pageNum==null){
            pageNum=1;
        }
        if (pageSize==null){
            pageSize=Constants.PAGE_SIZE;
        }
        PageHelper.startPage(pageNum,pageSize);
        // 紧跟着的第一个select方法会被分页
        List<Object> list=chilledWaterMapper.findList(chilledWaterModel);
        PageInfo<Object> page = new PageInfo<>(list);
        return page;
    }

    /**
     * 查询冷冻水配置信息  回显
     * @param chilledWaterModel
     * @return
     */
    @Override
    public ISSPReturnObject query(ChilledWaterModel chilledWaterModel) {
        ISSPReturnObject isspReturnObject=new ISSPReturnObject();
        try {
            if (chilledWaterModel.getF_TYPE_ID()==null){
                chilledWaterModel.setF_TYPE_ID("1");
                List<Object> list=chilledWaterMapper.findList(chilledWaterModel);
                isspReturnObject.setData(list);
                isspReturnObject.setStatus("1");
            }else {
                List<Object> list=chilledWaterMapper.findList(chilledWaterModel);
                isspReturnObject.setData(list);
                isspReturnObject.setStatus("1");
            }
        }catch (Exception e){
            isspReturnObject.setStatus("0");
            e.printStackTrace();
        }
        return isspReturnObject;
    }

    @Override
    public ISSPReturnObject queryColdCooling(ChilledWaterModel chilledWaterModel) {
        ISSPReturnObject isspReturnObject=new ISSPReturnObject();
        try {
            if (chilledWaterModel.getF_TYPE_ID()==null){
                chilledWaterModel.setF_TYPE_ID("1");
                List<Object> list=chilledWaterMapper.queryColdCooling(chilledWaterModel);
                isspReturnObject.setData(list);
                isspReturnObject.setStatus("1");
            }else {
                List<Object> list=chilledWaterMapper.queryColdCooling(chilledWaterModel);
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
     * 添加冷冻水配置
     * @param chilledWaterModel
     * @return
     */
    @Override
    public ISSPReturnObject creat(ChilledWaterModel chilledWaterModel) {
        ISSPReturnObject returnObject = new ISSPReturnObject();

        if (null == chilledWaterModel) {
            returnObject.setStatus("0");
            returnObject.setMsg("参数错误！");
            return returnObject;
        }

        String F_NAME= chilledWaterModel.getF_NAME();


        if (!StringUtils.hasText(F_NAME)
        ) {

            returnObject.setStatus("0");
            returnObject.setMsg("参数错误！");
            return returnObject;

        }

        try {
            chilledWaterMapper.insert(chilledWaterModel);

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
     * 修改冷冻水配置
     * */
    @Override
    public ISSPReturnObject update(ChilledWaterModel chilledWaterModel) {
        ISSPReturnObject returnObject=new ISSPReturnObject();
        if (chilledWaterModel==null){
            returnObject.setStatus("0");
            returnObject.setMsg("参数错误");
            return returnObject;
        }
        try {
            chilledWaterMapper.update(chilledWaterModel);
            returnObject.setStatus("1");
            returnObject.setMsg("修改成功");
        }catch (Exception e){
            returnObject.setStatus("0");
            returnObject.setMsg("修改失败");
            e.printStackTrace();
        }
        return returnObject;
    }

    /**
     * 删除冷冻水配置信息
     * @param chilledWaterModel
     * @return
     */
    @Override
    public ISSPReturnObject delete(ChilledWaterModel chilledWaterModel) {
        ISSPReturnObject returnObject = new ISSPReturnObject();
        List<Map<String,Object>> listcommonconfig = null;
        List<Map<String,Object>> listcommonconfig_top = null;
        if (null == chilledWaterModel) {
            returnObject.setStatus("0");
            returnObject.setMsg("参数错误！");
            return returnObject;
        }

        Integer id = chilledWaterModel.getF_ID();

        if (null == id) {
            returnObject.setStatus("0");
            returnObject.setMsg("参数错误！");
            return returnObject;
        }

        ChilledWaterModel chum = new ChilledWaterModel();
        chum.setF_ID(id);

        try {
            List<Object> list = chilledWaterMapper.findList(chum);

            if (null == list || list.isEmpty()){

                returnObject.setStatus("0");
                returnObject.setMsg("参数错误！");
                return returnObject;
            }

            //查询强弱电页面展示通用配置表strongandweakelectricityintegration_commonconfig和
            // 强弱电页面展示通用配置表  左侧的topstrongandweakelectricityintegration_commonconfig_top是否有配置的信息
            listcommonconfig = chilledWaterMapper.findlistcommonconfig(chum);
            listcommonconfig_top = chilledWaterMapper.findlistcommonconfig_top(chum);

        } catch (Exception e) {
            returnObject.setStatus("0");
            e.printStackTrace();
        }


        try {
            chilledWaterMapper.delete(id);
            if (listcommonconfig.size() > 0) {
                chilledWaterMapper.deletecommonconfigById(id);
            }
            if (listcommonconfig_top.size() > 0) {
                chilledWaterMapper.deletecommonconfig_topById(id);
            }
            returnObject.setStatus("1");
        } catch (Exception e) {
            returnObject.setStatus("0");
            e.printStackTrace();
        }

        return returnObject;
    }

    /**
     * 根据传过来的id查询所属的字段值
     *
     * @param f_equipment_id
     * @return
     * */
    @Override
    public ISSPReturnObject detailedinformation(String f_equipment_id) {
        ISSPReturnObject returnObject=new ISSPReturnObject();
        if (f_equipment_id==null){
            returnObject.setStatus("0");
            returnObject.setMsg("参数错误");
        }
        try {
            List<Object> list=chilledWaterMapper.detailedinformation(f_equipment_id);
            returnObject.setData(list);
            returnObject.setStatus("1");
            returnObject.setMsg("查询成功");
        }catch (Exception e){
            returnObject.setStatus("0");
            returnObject.setMsg("查询失败");
            e.printStackTrace();
        }
        return returnObject;
    }

    /**
     * 获取获取电表配置列表
     */
    @Override
    public ISSPReturnObject electricMeterNnumber() {
        ISSPReturnObject returnObject = new ISSPReturnObject();
        try {
            List<Object> list = chilledWaterMapper.electricMeterNnumber();
            returnObject.setList(list);
            returnObject.setMsg("查询电表配置列表成功");
            returnObject.setStatus("1");
        } catch (Exception e) {
            returnObject.setMsg("查询电表配置列表失败");
            returnObject.setStatus("0");
        }
        return returnObject;
    }

    /**
     *
     * @Description: 根据冷冻水冷却水的id查询变频器运行状态
     *
     * @auther: wanghongjie
     * @date: 14:59 2020/11/11
     * @param: [coldWarmWaterID]
     * @return: java.lang.String
     *
     */
    @Override
    public String queryColdWarmWaterWithF_BPYXZT(String coldWarmWaterID) {
        String f_bpyxzt = chilledWaterMapper.queryColdWarmWaterWithF_BPYXZT(coldWarmWaterID);
        return f_bpyxzt;
    }
}
