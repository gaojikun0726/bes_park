package com.efounder.JEnterprise.service.strongAndWeakElectricityIntegration.impl;

import com.core.common.ISSPReturnObject;
import com.efounder.JEnterprise.common.util.UUIDUtil;
import com.efounder.JEnterprise.mapper.strongAndWeakElectricityIntegration.HostLinkageDeviceMapper;
import com.efounder.JEnterprise.mapper.strongAndWeakElectricityIntegration.HostLinkageMapper;
import com.efounder.JEnterprise.mapper.strongAndWeakElectricityIntegration.HostLinkageRunningModeMapper;
import com.efounder.JEnterprise.mapper.strongAndWeakElectricityIntegration.deviceConfig.MachineSetMapper;
import com.efounder.JEnterprise.model.strongAndWeakElectricityIntegration.GljlModel;
import com.efounder.JEnterprise.model.strongAndWeakElectricityIntegration.HostLinkageDeviceModel;
import com.efounder.JEnterprise.model.strongAndWeakElectricityIntegration.HostLinkageModel;
import com.efounder.JEnterprise.model.strongAndWeakElectricityIntegration.HostLinkageRunningModeModel;
import com.efounder.JEnterprise.model.strongAndWeakElectricityIntegration.deviceConfig.MachineSetModel;
import com.efounder.JEnterprise.service.strongAndWeakElectricityIntegration.GljlService;
import com.efounder.JEnterprise.service.strongAndWeakElectricityIntegration.HostLinkageService;
import org.apache.shiro.SecurityUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * 主机联动（主机）
 * @author xiepufeng
 * @date 2020/2/11 15:42
 */
@Service
public class HostLinkageServiceImpl implements HostLinkageService {

    @Resource
    private HostLinkageMapper hostLinkageMapper;

    @Resource
    private HostLinkageDeviceMapper hostLinkageDeviceMapper;

    @Resource
    private GljlService gljlService;

    @Resource
    private HostLinkageRunningModeMapper hostLinkageRunningModeMapper;

    @Resource
    private MachineSetMapper machineSetMapper;

    @Override
    public ISSPReturnObject createHost(HostLinkageModel hostLinkageModel) {

        ISSPReturnObject returnObject = new ISSPReturnObject();

        if (null == hostLinkageModel) {
            returnObject.setStatus("0");
            returnObject.setMsg("参数错误！");
            return returnObject;
        }

        String  name              = hostLinkageModel.getName();
        String  pointDisplay      = hostLinkageModel.getPointDisplay();
        String  point             = hostLinkageModel.getPoint();
        String  pointState        = hostLinkageModel.getPointState();
        String  pointStateDisplay = hostLinkageModel.getPointStateDisplay();
        Integer machineSetId      = hostLinkageModel.getMachineSetId();


        if (!StringUtils.hasText(name)
                || !StringUtils.hasText(pointDisplay)
                || !StringUtils.hasText(point)
                || !StringUtils.hasText(pointState)
                || !StringUtils.hasText(pointStateDisplay)
                || machineSetId == null
                ) {

            returnObject.setStatus("0");
            returnObject.setMsg("参数错误！");
            return returnObject;

        }

        MachineSetModel machineSetModel = new MachineSetModel();

        machineSetModel.setId(machineSetId);

        List<MachineSetModel>  machineSetModelList = machineSetMapper.findList(machineSetModel);

        if (machineSetModelList == null || machineSetModelList.isEmpty())
        {
            returnObject.setStatus("0");
            returnObject.setMsg("所属机组不存在！");
            return returnObject;
        }

        HostLinkageModel hostLinkage = new HostLinkageModel();
        hostLinkage.setName(name);

        List<HostLinkageModel> hostLinkageModelList =  hostLinkageMapper.findList(hostLinkage);

        if (null != hostLinkageModelList && !hostLinkageModelList.isEmpty()) {
            returnObject.setStatus("0");
            returnObject.setMsg("主机名称重复！");
            return returnObject;
        }


        try {
            hostLinkageMapper.insert(hostLinkageModel);

            returnObject.setStatus("1");
            returnObject.setMsg("添加成功");
            returnObject.setData(hostLinkageModel);

        } catch (Exception e) {

            returnObject.setStatus("0");
            returnObject.setMsg("添加失败");

            e.printStackTrace();
        }

        // 添加管理记录
        addRecordsManager("主机联动-添加主机:"+ name);

        return returnObject;
    }

    @Override
    public ISSPReturnObject queryHost(HostLinkageModel hostLinkageModel) {

        ISSPReturnObject isspReturnObject = new ISSPReturnObject();

        try {
            List<HostLinkageModel> list = hostLinkageMapper.findList(hostLinkageModel);
            isspReturnObject.setData(list);
            isspReturnObject.setStatus("1");
        } catch (Exception e) {
            isspReturnObject.setStatus("0");
            e.printStackTrace();
        }

        return isspReturnObject;
    }

    @Override
    public ISSPReturnObject deleteHost(HostLinkageModel hostLinkageModel) {

        ISSPReturnObject returnObject = new ISSPReturnObject();

        if (null == hostLinkageModel) {
            returnObject.setStatus("0");
            returnObject.setMsg("参数错误！");
            return returnObject;
        }

        Integer id = hostLinkageModel.getId();

        if (null == id) {
            returnObject.setStatus("0");
            returnObject.setMsg("参数错误！");
            return returnObject;
        }

        HostLinkageModel hl = new HostLinkageModel();
        hl.setId(id);

        try {
            List<HostLinkageModel> list = hostLinkageMapper.findList(hl);

            if (null == list || list.isEmpty()){

                returnObject.setStatus("0");
                returnObject.setMsg("实体不存在！");
                return returnObject;
            }

        } catch (Exception e) {
            returnObject.setStatus("0");
            e.printStackTrace();
        }

        HostLinkageDeviceModel hdm = new HostLinkageDeviceModel();
        hdm.setHostId(id);

        try {
            List<Object> list = hostLinkageDeviceMapper.findList(hdm);

            if (null != list && !list.isEmpty()) {
                returnObject.setStatus("0");
                returnObject.setMsg("该主机已经被设备关联，请删除关联设备，再进行此操作");
                return returnObject;
            }

        } catch (Exception e) {
            returnObject.setStatus("0");
            e.printStackTrace();
        }

        try {
            hostLinkageMapper.delete(id);
            returnObject.setStatus("1");
        } catch (Exception e) {
            returnObject.setStatus("0");
            e.printStackTrace();
        }

        addRecordsManager("主机联动-删除主机信息:" + id);

        return returnObject;
    }

    @Override
    public ISSPReturnObject updateHost(HostLinkageModel hostLinkageModel) {

        ISSPReturnObject returnObject = new ISSPReturnObject();

        if (null == hostLinkageModel) {
            returnObject.setStatus("0");
            returnObject.setMsg("参数错误！");
            return returnObject;
        }

        Integer id               = hostLinkageModel.getId();
        String  name             = hostLinkageModel.getName();
        String  pointDisplay     = hostLinkageModel.getPointDisplay();
        String  point            = hostLinkageModel.getPoint();
        String pointState        = hostLinkageModel.getPointState();
        String pointStateDisplay = hostLinkageModel.getPointStateDisplay();

        if (null == id
                || !StringUtils.hasText(name)
                || !StringUtils.hasText(pointDisplay)
                || !StringUtils.hasText(point)
                || !StringUtils.hasText(pointState)
                || !StringUtils.hasText(pointStateDisplay)
                ) {

            returnObject.setStatus("0");
            returnObject.setMsg("参数错误！");
            return returnObject;

        }

        HostLinkageModel hostLinkageModel1 = new HostLinkageModel();
        hostLinkageModel1.setName(name);

        List<HostLinkageModel> hostLinkageModels = hostLinkageMapper.findList(hostLinkageModel1);

        if (hostLinkageModels != null && !hostLinkageModels.isEmpty())
        {
            if (hostLinkageModels.size() > 1)
            {
                returnObject.setStatus("0");
                returnObject.setMsg("主机名称不能重复！");
                return returnObject;
            }else
            {
                if (!hostLinkageModels.get(0).getId().equals(id))
                {
                    returnObject.setStatus("0");
                    returnObject.setMsg("主机名称不能重复！");
                    return returnObject;
                }
            }
        }

        HostLinkageModel hl = new HostLinkageModel();
        hl.setId(id);

        try {
            List<HostLinkageModel> list = hostLinkageMapper.findList(hl);

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
            hostLinkageMapper.update(hostLinkageModel);
            returnObject.setStatus("1");
        } catch (Exception e) {
            returnObject.setStatus("0");
            e.printStackTrace();
        }

        addRecordsManager("主机联动-修改主机:" + name);

        return returnObject;
    }

    @Override
    public ISSPReturnObject createDevice(HostLinkageDeviceModel hostLinkageDeviceModel) {

        ISSPReturnObject returnObject = new ISSPReturnObject();

        if (null == hostLinkageDeviceModel) {
            returnObject.setStatus("0");
            returnObject.setMsg("参数错误！");
            return returnObject;
        }

        Integer hostId        = hostLinkageDeviceModel.getHostId();
        String  name          = hostLinkageDeviceModel.getName();
        String  pointDisplay  = hostLinkageDeviceModel.getPointDisplay();
        String  point         = hostLinkageDeviceModel.getPoint();
        Integer  isFaultPoint = hostLinkageDeviceModel.getIsFaultPoint();


        if (null == hostId
                || !StringUtils.hasText(name)
                || !StringUtils.hasText(pointDisplay)
                || !StringUtils.hasText(point)
                || isFaultPoint == null
                ) {

            returnObject.setStatus("0");
            returnObject.setMsg("参数错误！");
            return returnObject;

        }

        HostLinkageModel hl = new HostLinkageModel();
        hl.setId(hostId);

        try {
            List<HostLinkageModel> list = hostLinkageMapper.findList(hl);

            if (null == list || list.isEmpty()){

                returnObject.setStatus("0");
                returnObject.setMsg("主机不存在！");
                return returnObject;
            }

        } catch (Exception e) {
            returnObject.setStatus("0");
            e.printStackTrace();
        }


        List<Object> hostLinkageDeviceModelList =  hostLinkageDeviceMapper.findList(hostLinkageDeviceModel);

        if (null != hostLinkageDeviceModelList && !hostLinkageDeviceModelList.isEmpty()) {
            returnObject.setStatus("0");
            returnObject.setMsg("设备名称重复！");
            return returnObject;
        }


        try {
            hostLinkageDeviceMapper.insert(hostLinkageDeviceModel);

            returnObject.setStatus("1");
            returnObject.setMsg("添加成功");
            returnObject.setData(hostLinkageDeviceModel);

        } catch (Exception e) {

            returnObject.setStatus("0");
            returnObject.setMsg("添加失败");

            e.printStackTrace();
        }

        addRecordsManager("主机联动-添加设备:" + name);

        return returnObject;
    }

    @Override
    public ISSPReturnObject queryDevice(HostLinkageDeviceModel hostLinkageDeviceModel) {

        ISSPReturnObject isspReturnObject = new ISSPReturnObject();

        try {
            List<Object> list = hostLinkageDeviceMapper.findList(hostLinkageDeviceModel);
            isspReturnObject.setData(list);
            isspReturnObject.setStatus("1");
        } catch (Exception e) {
            isspReturnObject.setStatus("0");
            e.printStackTrace();
        }

        return isspReturnObject;
    }

    @Override
    public ISSPReturnObject deleteDevice(HostLinkageDeviceModel hostLinkageDeviceModel) {

        ISSPReturnObject returnObject = new ISSPReturnObject();

        if (null == hostLinkageDeviceModel) {
            returnObject.setStatus("0");
            returnObject.setMsg("参数错误！");
            return returnObject;
        }

        Integer id = hostLinkageDeviceModel.getId();

        if (null == id) {
            returnObject.setStatus("0");
            returnObject.setMsg("参数错误！");
            return returnObject;
        }

        HostLinkageDeviceModel hdm = new HostLinkageDeviceModel();
        hdm.setId(id);

        try {
            List<Object> list = hostLinkageDeviceMapper.findList(hdm);

            if (null == list || list.isEmpty()){

                returnObject.setStatus("0");
                returnObject.setMsg("实体不存在！");
                return returnObject;
            }

        } catch (Exception e) {
            returnObject.setStatus("0");
            e.printStackTrace();
        }


        try {
            hostLinkageDeviceMapper.delete(id);
            returnObject.setStatus("1");
        } catch (Exception e) {
            returnObject.setStatus("0");
            e.printStackTrace();
        }

        addRecordsManager("主机联动-删除设备信息:" + id);

        return returnObject;
    }

    @Override
    public ISSPReturnObject updateDevice(HostLinkageDeviceModel hostLinkageDeviceModel) {

        ISSPReturnObject returnObject = new ISSPReturnObject();

        if (null == hostLinkageDeviceModel) {
            returnObject.setStatus("0");
            returnObject.setMsg("参数错误！");
            return returnObject;
        }

        Integer id            = hostLinkageDeviceModel.getId();
        String  name          = hostLinkageDeviceModel.getName();
        String  pointDisplay  = hostLinkageDeviceModel.getPointDisplay();
        String  point         = hostLinkageDeviceModel.getPoint();
        Integer  isFaultPoint = hostLinkageDeviceModel.getIsFaultPoint();


        if (null == id
                || !StringUtils.hasText(name)
                || !StringUtils.hasText(pointDisplay)
                || !StringUtils.hasText(point)
                || isFaultPoint == null
                ) {

            returnObject.setStatus("0");
            returnObject.setMsg("参数错误！");
            return returnObject;

        }

        HostLinkageDeviceModel hdm = new HostLinkageDeviceModel();
        hdm.setId(id);

        try {
            List<Object> list = hostLinkageDeviceMapper.findList(hdm);

            if (null == list || list.isEmpty()){

                returnObject.setStatus("0");
                returnObject.setMsg("参数错误！");
                return returnObject;
            }

        } catch (Exception e) {
            returnObject.setStatus("0");
            e.printStackTrace();
        }

        if (isFaultPoint.equals(0))
        {
            hostLinkageDeviceModel.setFaultFineValue("");
        }

        try {
            hostLinkageDeviceMapper.update(hostLinkageDeviceModel);
            returnObject.setStatus("1");
        } catch (Exception e) {
            returnObject.setStatus("0");
            e.printStackTrace();
        }

        addRecordsManager("主机联动-修改设备:" + name);

        return returnObject;
    }

    /**
     * 查询运行模式
     * @return
     */
    @Override
    public ISSPReturnObject queryJoinPoint(HostLinkageRunningModeModel hostLinkageRunningModeModel)
    {
        ISSPReturnObject isspReturnObject = new ISSPReturnObject();

        try {
            List<Object> list = hostLinkageRunningModeMapper.findList(hostLinkageRunningModeModel);
            isspReturnObject.setData(list);
            isspReturnObject.setStatus("1");
        } catch (Exception e) {
            isspReturnObject.setStatus("0");
            e.printStackTrace();
        }

        return isspReturnObject;
    }

    /**
     *  添加关联点
     * */
    @Override
    public ISSPReturnObject createJoinPoint(HostLinkageRunningModeModel hostLinkageRunningModeModel)
    {
        ISSPReturnObject isspReturnObject = new ISSPReturnObject();

        if (hostLinkageRunningModeModel == null
                || hostLinkageRunningModeModel.getJoinPoint() == null
                || hostLinkageRunningModeModel.getMachineSetId() == null
                )
        {
            isspReturnObject.setStatus("0");
            isspReturnObject.setMsg("参数错误");
            return isspReturnObject;
        }

        Integer type = hostLinkageRunningModeModel.getType();

        if (HostLinkageRunningModeModel.RUN_MODEL_TYPE.equals(type))
        {
            addRecordsManager("主机联动-创建运行模式:" + hostLinkageRunningModeModel.getJoinPoint());
        }else if (HostLinkageRunningModeModel.START_STOP_TYPE.equals(type))
        {
            addRecordsManager("主机联动-添加关联群控启停:" + hostLinkageRunningModeModel.getJoinPoint());
        }else if (HostLinkageRunningModeModel.START_STOP_STATE_TYPE.equals(type))
        {
            addRecordsManager("主机联动-添加关联群控启停状态:" + hostLinkageRunningModeModel.getJoinPoint());
        }else
        {
            isspReturnObject.setStatus("0");
            isspReturnObject.setMsg("参数错误");
            return isspReturnObject;
        }


        MachineSetModel machineSetModel = new MachineSetModel();

        machineSetModel.setId(hostLinkageRunningModeModel.getMachineSetId());

        List<MachineSetModel>  machineSetModelList = machineSetMapper.findList(machineSetModel);

        if (machineSetModelList == null || machineSetModelList.isEmpty())
        {
            isspReturnObject.setStatus("0");
            isspReturnObject.setMsg("所属机组不存在！");
            return isspReturnObject;
        }


        try {
            hostLinkageRunningModeMapper.insert(hostLinkageRunningModeModel);
            isspReturnObject.setStatus("1");
        } catch (Exception e) {
            isspReturnObject.setStatus("0");
            e.printStackTrace();
        }

        return isspReturnObject;
    }

    /**
     *  更新关联点
     * */
    @Override
    public ISSPReturnObject updateJoinPoint(HostLinkageRunningModeModel hostLinkageRunningModeModel)
    {

        ISSPReturnObject isspReturnObject = new ISSPReturnObject();

        if (hostLinkageRunningModeModel == null)
        {
            isspReturnObject.setStatus("0");
            isspReturnObject.setMsg("参数错误");
            return isspReturnObject;
        }


        Integer type = hostLinkageRunningModeModel.getType();

        if (hostLinkageRunningModeModel.getJoinPoint() == null
                || hostLinkageRunningModeModel.getId() == null
                || type == null
                || hostLinkageRunningModeModel.getMachineSetId() == null
                )
        {
            isspReturnObject.setStatus("0");
            isspReturnObject.setMsg("参数错误");
            return isspReturnObject;
        }

        if (type.equals(HostLinkageRunningModeModel.RUN_MODEL_TYPE))
        {
            addRecordsManager("主机联动-修改运行模式:" + hostLinkageRunningModeModel.getJoinPoint());
        }else if (type.equals(HostLinkageRunningModeModel.START_STOP_TYPE))
        {
            addRecordsManager("主机联动-更新关联群控启停:" + hostLinkageRunningModeModel.getJoinPoint());

        }else if (HostLinkageRunningModeModel.START_STOP_STATE_TYPE.equals(type))
        {
            addRecordsManager("主机联动-更新关联群控启停状态:" + hostLinkageRunningModeModel.getJoinPoint());
        }

        MachineSetModel machineSetModel = new MachineSetModel();

        machineSetModel.setId(hostLinkageRunningModeModel.getMachineSetId());

        List<MachineSetModel>  machineSetModelList = machineSetMapper.findList(machineSetModel);

        if (machineSetModelList == null || machineSetModelList.isEmpty())
        {
            isspReturnObject.setStatus("0");
            isspReturnObject.setMsg("所属机组不存在！");
            return isspReturnObject;
        }

        try {
            hostLinkageRunningModeMapper.update(hostLinkageRunningModeModel);
            isspReturnObject.setStatus("1");
        } catch (Exception e) {
            isspReturnObject.setStatus("0");
            e.printStackTrace();
        }


        return isspReturnObject;
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
