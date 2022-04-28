package com.efounder.JEnterprise.service.strongAndWeakElectricityIntegration.deviceConfig.impl;

import com.core.common.ISSPReturnObject;
import com.efounder.JEnterprise.common.constants.Constants;
import com.efounder.JEnterprise.common.util.UUIDUtil;
import com.efounder.JEnterprise.mapper.strongAndWeakElectricityIntegration.HostLinkageMapper;
import com.efounder.JEnterprise.mapper.strongAndWeakElectricityIntegration.deviceConfig.MachineSetMapper;
import com.efounder.JEnterprise.model.strongAndWeakElectricityIntegration.GljlModel;
import com.efounder.JEnterprise.model.strongAndWeakElectricityIntegration.HostLinkageModel;
import com.efounder.JEnterprise.model.strongAndWeakElectricityIntegration.deviceConfig.MachineSetModel;
import com.efounder.JEnterprise.service.strongAndWeakElectricityIntegration.GljlService;
import com.efounder.JEnterprise.service.strongAndWeakElectricityIntegration.deviceConfig.MachineSetService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.shiro.SecurityUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * 机组
 * @author xiepufeng
 * @date 2020/11/24 17:21
 */
@Service
public class MachineSetServiceImpl implements MachineSetService
{

    @Resource
    private MachineSetMapper machineSetMapper;

    @Resource
    private HostLinkageMapper hostLinkageMapper;

    @Resource
    private GljlService gljlService;

    @Override
    public ISSPReturnObject create(MachineSetModel machineSetModel)
    {
        ISSPReturnObject returnObject = new ISSPReturnObject();

        if (null == machineSetModel) {
            returnObject.setStatus("0");
            returnObject.setMsg("参数错误！");
            return returnObject;
        }

        String name = machineSetModel.getName();

        if (!StringUtils.hasText(name)) {

            returnObject.setStatus("0");
            returnObject.setMsg("参数错误！");
            return returnObject;

        }

        List<MachineSetModel> machineSetModelList =  machineSetMapper.findList(machineSetModel);

        if (null != machineSetModelList && !machineSetModelList.isEmpty()) {
            returnObject.setStatus("0");
            returnObject.setMsg("机组名称重复！");
            return returnObject;
        }


        try {
            machineSetMapper.insert(machineSetModel);

            returnObject.setStatus("1");
            returnObject.setMsg("添加成功");
            returnObject.setData(machineSetModel);

        } catch (Exception e) {

            returnObject.setStatus("0");
            returnObject.setMsg("添加失败");

            e.printStackTrace();
        }

        // 添加管理记录
        addRecordsManager("主机联动-添加机组:"+ name);

        return returnObject;
    }

    @Override
    public ISSPReturnObject query(MachineSetModel machineSetModel)
    {
        ISSPReturnObject isspReturnObject = new ISSPReturnObject();

        try {
            List<MachineSetModel> list = machineSetMapper.findList(machineSetModel);
            isspReturnObject.setData(list);
            isspReturnObject.setStatus("1");
        } catch (Exception e) {
            isspReturnObject.setStatus("0");
            e.printStackTrace();
        }

        return isspReturnObject;
    }

    @Override
    public ISSPReturnObject update(MachineSetModel machineSetModel)
    {
        ISSPReturnObject returnObject = new ISSPReturnObject();

        if (null == machineSetModel) {
            returnObject.setStatus("0");
            returnObject.setMsg("参数错误！");
            return returnObject;
        }

        Integer id   = machineSetModel.getId();
        String  name = machineSetModel.getName();

        if (null == id || !StringUtils.hasText(name)) {

            returnObject.setStatus("0");
            returnObject.setMsg("参数错误！");
            return returnObject;

        }
        MachineSetModel machineSetModel1 = new MachineSetModel();
        machineSetModel1.setName(name);

        List<MachineSetModel> machineSetModelList = machineSetMapper.findList(machineSetModel1);

        if (machineSetModelList != null && !machineSetModelList.isEmpty())
        {
            if (machineSetModelList.size() > 1)
            {
                returnObject.setStatus("0");
                returnObject.setMsg("机组名称不能重复！");
                return returnObject;
            }else
            {
                if (!machineSetModelList.get(0).getId().equals(id))
                {
                    returnObject.setStatus("0");
                    returnObject.setMsg("机组名称不能重复！");
                    return returnObject;
                }
            }
        }

        MachineSetModel ms = new MachineSetModel();
        ms.setId(id);

        try {
            List<MachineSetModel> list = machineSetMapper.findList(ms);

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
            machineSetMapper.update(machineSetModel);
            returnObject.setStatus("1");
        } catch (Exception e) {
            returnObject.setStatus("0");
            e.printStackTrace();
        }

        addRecordsManager("主机联动-修改机组:" + name);

        return returnObject;
    }

    @Override
    public ISSPReturnObject delete(MachineSetModel machineSetModel)
    {

        ISSPReturnObject returnObject = new ISSPReturnObject();

        if (null == machineSetModel) {
            returnObject.setStatus("0");
            returnObject.setMsg("参数错误！");
            return returnObject;
        }

        Integer id = machineSetModel.getId();

        if (null == id) {
            returnObject.setStatus("0");
            returnObject.setMsg("参数错误！");
            return returnObject;
        }

        MachineSetModel ms = new MachineSetModel();
        ms.setId(id);

        try {
            List<MachineSetModel> list = machineSetMapper.findList(ms);

            if (null == list || list.isEmpty()){

                returnObject.setStatus("0");
                returnObject.setMsg("实体不存在！");
                return returnObject;
            }

        } catch (Exception e) {
            returnObject.setStatus("0");
            e.printStackTrace();
        }

        HostLinkageModel hdm = new HostLinkageModel();
        hdm.setMachineSetId(id);

        try {
            List<HostLinkageModel> list = hostLinkageMapper.findList(hdm);

            if (null != list && !list.isEmpty()) {
                returnObject.setStatus("0");
                returnObject.setMsg("该主机已经被主机关联，请删除关联主机，再进行此操作");
                return returnObject;
            }

        } catch (Exception e) {
            returnObject.setStatus("0");
            e.printStackTrace();
        }

        try {
            machineSetMapper.delete(id);
            returnObject.setStatus("1");
        } catch (Exception e) {
            returnObject.setStatus("0");
            e.printStackTrace();
        }

        addRecordsManager("主机联动-删除机组信息:" + id);

        return returnObject;
    }

    @Override
    public PageInfo<MachineSetModel> queryPage(String keywords, Integer pageNum) {
        if (pageNum == null)
            pageNum = 1;
        PageHelper.startPage(pageNum, Constants.PAGE_SIZE);
        List<MachineSetModel> list = machineSetMapper.queryPage(keywords);
        return new PageInfo<>(list);
    }


    /**
     * 添加管理记录
     */
    public void addRecordsManager(String msg)
    {
        if (!StringUtils.hasText(msg))
        {
            return;
        }

        //将操作记录到管理记录中 start
        GljlModel gljlModel =new GljlModel();
        String user = SecurityUtils.getSubject().getPrincipal().toString();//获取当前登录用户
        Date now = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");// 可以方便地修改日期格式
        String currentTime = dateFormat.format(now);
        // 自动生成guid
        gljlModel.setF_id(UUIDUtil.getRandom32BeginTimePK());
        gljlModel.setF_username(user);
        //0是维护，1是通信
        gljlModel.setF_type("0");
        gljlModel.setF_gltime(currentTime);
        gljlModel.setF_remark(msg);
        gljlService.addGljlxx(gljlModel);
        //将操作记录到管理记录中 end

    }
}
