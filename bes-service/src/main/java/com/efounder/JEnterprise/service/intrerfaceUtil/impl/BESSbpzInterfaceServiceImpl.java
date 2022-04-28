package com.efounder.JEnterprise.service.intrerfaceUtil.impl;

import com.alibaba.fastjson.JSONObject;
import com.core.common.ISSPReturnObject;
import com.core.common.security.ISSPSecurityObject;
import com.core.common.security.LimitParamObject;
import com.efounder.JEnterprise.collectorJob.BESSbtreeThread;
import com.efounder.JEnterprise.config.shiro.vo.Principal;
import com.efounder.JEnterprise.initializer.*;
import com.efounder.JEnterprise.mapper.interfaceUtil.BESSbpzInterfaceMapper;
import com.efounder.JEnterprise.mapper.systemcenter.interfaceconfig.DeviceFunctionMapper;
import com.efounder.JEnterprise.model.basedatamanage.eqmanage.BESAmmeter;
import com.efounder.JEnterprise.model.basedatamanage.eqmanage.BESSbPzStruct;
import com.efounder.JEnterprise.model.systemcenter.Interfaceconfig.*;
import com.efounder.JEnterprise.model.usercenter.ESUser;
import com.efounder.JEnterprise.service.basedatamanage.eqmanage.impl.BESSbdyServiceImpl;
import com.efounder.JEnterprise.service.intrerfaceUtil.BESSbpzInterfaceService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.cache.Cache;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.*;

@Service("BESSbpzInterfaceService")
public class BESSbpzInterfaceServiceImpl implements BESSbpzInterfaceService
{

    private static final Logger log = LoggerFactory.getLogger(BESSbpzInterfaceServiceImpl.class);
    @Autowired
    private BESSbpzInterfaceMapper besSbpzInterfaceMapper;

    @Resource
    private DeviceTypeCache deviceTypeCache;

    @Resource
    private DeviceConfigurationCache deviceConfigurationCache;

    @Resource
    private DeviceFunctionCache deviceFunctionCache;

    @Resource
    private DeviceFunctionPointCache deviceFunctionPointCache;

    @Resource
    private BESSbdyServiceImpl besSbdyService;

    @Resource
    private AmmeterCache ammeterCache;

    @Resource
    private DeviceFunctionMapper deviceFunctionMapper;

    @Resource
    private SbPzStructCache sbPzStructCache;

    /**
     * 获取设备类型信息
     *
     * @return
     */
    @Override
    public ISSPReturnObject getDeviceTypes()
    {
        ISSPReturnObject result = new ISSPReturnObject();

        Collection<DeviceTypeModel> deviceTypeModels = deviceTypeCache.getDeviceTypeCache().values();

        if (deviceTypeModels == null || deviceTypeModels.isEmpty())
        {
            result.setStatus("0");
            return result;
        }

        result.setData(deviceTypeModels);
        result.setStatus("1");
        return result;
    }

    /**
     * 获取设备信息
     *
     * @return
     */
    @Override
    public ISSPReturnObject getDevices()
    {
        ISSPReturnObject result = new ISSPReturnObject();

        Collection<DeviceConfigurationModel> deviceConfigurations = deviceConfigurationCache.getDeviceConfigurationCache().values();

        if (deviceConfigurations == null || deviceConfigurations.isEmpty())
        {
            result.setStatus("0");
            return result;
        }

        result.setData(deviceConfigurations);
        result.setStatus("1");
        return result;
    }

    /**
     * 获取设备功能信息
     *
     * @return
     */
    @Override
    public ISSPReturnObject getDeviceFunctions()
    {
        ISSPReturnObject result = new ISSPReturnObject();

        Collection<DeviceFunctionModel> deviceFunctionModels = deviceFunctionCache.getDeviceFunctionCache().values();

        if (deviceFunctionModels == null || deviceFunctionModels.isEmpty())
        {
            result.setStatus("0");
            return result;
        }

        result.setData(deviceFunctionModels);
        result.setStatus("1");
        return result;
    }

    /**
     * 获取功能值
     * @return
     */
    @Override
    public ISSPReturnObject getFunctionValues() {

        ISSPReturnObject returnObject = new ISSPReturnObject();

        List<DeviceFunctionValueModel> deviceFunctionValueModelList = deviceFunctionMapper.findAllFunctionValue();

        if (deviceFunctionValueModelList == null || deviceFunctionValueModelList.isEmpty())
        {
            returnObject.setMsg("无数据");
            returnObject.setStatus("0");
            return returnObject;
        }

        returnObject.setData(deviceFunctionValueModelList);
        returnObject.setStatus("1");

        return returnObject;
    }


    /**
     * 获取功能点状态
     *
     * @return
     */
    @Override
    public ISSPReturnObject getFunctionPointStates(String deviceId, Integer deviceFunctionId)
    {
        ISSPReturnObject result = new ISSPReturnObject();

        if (deviceId == null && deviceFunctionId == null)
        {
            Cache<Integer, DeviceFunctionPointModel>  cacheData = deviceFunctionPointCache.getDeviceFunctionPointCache();

            if (cacheData == null)
            {
                result.setStatus("0");
                return result;
            }

            Collection<DeviceFunctionPointModel> data = cacheData.values();

            List<FunctionPointStateModel> functionPointStateModels = new ArrayList<>();

            data.forEach(item ->
            {
                FunctionPointStateModel functionPointStateModel = new FunctionPointStateModel();

                functionPointStateModel.setSysName(item.getSysName());
                functionPointStateModel.setValue(item.getValue());
                functionPointStateModel.setDeviceId(item.getDeviceId());
                functionPointStateModel.setDeviceFunctionId(item.getDeviceFunctionId());

                functionPointStateModels.add(functionPointStateModel);
            });

            result.setData(functionPointStateModels);
            result.setStatus("1");
            return result;
        }

        if (deviceId != null && deviceFunctionId == null)
        {
            List<DeviceFunctionPointModel> data = deviceFunctionPointCache.getByDeviceId(deviceId);

            if (data == null || data.isEmpty())
            {
                result.setStatus("0");
                return result;
            }

            List<FunctionPointStateModel> functionPointStateModels = new ArrayList<>();

            data.forEach(item -> {

                FunctionPointStateModel functionPointStateModel = new FunctionPointStateModel();

                functionPointStateModel.setSysName(item.getSysName());
                functionPointStateModel.setValue(item.getValue());
                functionPointStateModel.setDeviceId(item.getDeviceId());
                functionPointStateModel.setDeviceFunctionId(item.getDeviceFunctionId());

                functionPointStateModels.add(functionPointStateModel);

            });

            result.setData(functionPointStateModels);
            result.setStatus("1");
            return result;
        }

        if (deviceId == null)
        {

            List<DeviceFunctionPointModel> data = deviceFunctionPointCache.getByDeviceFunctionId(deviceFunctionId);

            if (data == null || data.isEmpty())
            {
                result.setStatus("0");
                return result;
            }

            List<FunctionPointStateModel> functionPointStateModels = new ArrayList<>();

            data.forEach(item -> {

                FunctionPointStateModel functionPointStateModel = new FunctionPointStateModel();

                functionPointStateModel.setSysName(item.getSysName());
                functionPointStateModel.setValue(item.getValue());
                functionPointStateModel.setDeviceId(item.getDeviceId());
                functionPointStateModel.setDeviceFunctionId(item.getDeviceFunctionId());

                functionPointStateModels.add(functionPointStateModel);

            });

            result.setData(functionPointStateModels);
            result.setStatus("1");
            return result;
        }


        DeviceFunctionPointModel data = deviceFunctionPointCache.getByDeviceIdAndDeviceFunctionId(deviceId, deviceFunctionId);

        if (data == null)
        {
            result.setStatus("0");
            return result;
        }

        List<FunctionPointStateModel> functionPointStateModels = new ArrayList<>();

        FunctionPointStateModel functionPointStateModel = new FunctionPointStateModel();

        functionPointStateModel.setSysName(data.getSysName());
        functionPointStateModel.setValue(data.getValue());
        functionPointStateModel.setDeviceId(data.getDeviceId());
        functionPointStateModel.setDeviceFunctionId(data.getDeviceFunctionId());


        functionPointStateModels.add(functionPointStateModel);

        result.setData(functionPointStateModels);
        result.setStatus("1");
        return result;
    }

    /**
     * 设备功能控制
     *
     * @param deviceId
     * @param deviceFunctionId
     * @param value
     * @return
     */
    @Override
    public ISSPReturnObject setDeviceControl(String deviceId, Integer deviceFunctionId, String value)
    {

        ISSPReturnObject result = new ISSPReturnObject();

        if (deviceId == null || deviceFunctionId == null)
        {
            result.setStatus("0");
            result.setMsg("参数错误");
            return result;
        }

        DeviceConfigurationModel deviceConfigurationModel = deviceConfigurationCache.getCachedElement(deviceId);

        if (deviceConfigurationModel == null)
        {
            result.setStatus("0");
            result.setMsg("设备不存在");
            return result;
        }

        DeviceFunctionModel deviceFunctionModel = deviceFunctionCache.getCachedElement(deviceFunctionId);

        if (deviceFunctionModel == null)
        {
            result.setStatus("0");
            result.setMsg("设备功能不存在");
            return result;
        }

        DeviceFunctionPointModel deviceFunctionPointModel = deviceFunctionPointCache.getByDeviceIdAndDeviceFunctionId(deviceId, deviceFunctionId);

        if (deviceFunctionPointModel == null)
        {
            result.setStatus("0");
            log.warn("设备功能控制: 功能点未配置");
            return result;
        }

        JSONObject obj = new JSONObject();

        obj.put("f_sys_name", deviceFunctionPointModel.getSysName());

        try
        {
            obj.put("f_init_val", Double.valueOf(value));
        }catch (NumberFormatException e)
        {
            e.printStackTrace();
            result.setStatus("0");
            result.setMsg("参数错误");
            return result;
        }

        ISSPReturnObject isspReturnObject = besSbdyService.sbdy_put_up_point(obj);
        result.setStatus(isspReturnObject.getStatus());
        return result;

    }

    /**
     * 获取电表信息
     * @return
     */
    @Override
    public ISSPReturnObject getAmmeterInfo()
    {

        ISSPReturnObject result = new ISSPReturnObject();

        Cache<String, BESAmmeter>  besAmmeterCache = ammeterCache.getAmmeterCache();

        if (besAmmeterCache == null)
        {
            result.setStatus("0");
            return result;
        }

        Collection<BESAmmeter> besAmmeters = besAmmeterCache.values();

        if (besAmmeters == null || besAmmeters.isEmpty())
        {
            result.setStatus("0");
            return result;
        }

        List<Map<String, Object>> data = new ArrayList<>();

        besAmmeters.forEach(item ->
        {
            Map<String, Object> dataMap = new HashMap<>();

            dataMap.put("code", item.getfSysNameOld());
            dataMap.put("alias", item.getfNickName());
            dataMap.put("type", item.getfBlxbh());
            dataMap.put("typeName", item.getfBlxmc());
            data.add(dataMap);
        });

        result.setStatus("1");
        result.setData(data);

        return result;
    }


    @Override
    public List<Map<String, Object>> getSbdyList()
    {
        Principal principa = (Principal) SecurityUtils.getSubject().getPrincipal();
        ESUser user = principa.getUser();
        // 获取当前用户的用户名
        String username = user.getF_yhbh();
        String qxbh = "0008";
        String pscol = "";
        String yhbh = username;
        String pibzw = "1";
        LimitParamObject paramObject = new LimitParamObject();
        paramObject.setQxbh(qxbh);
        paramObject.setPscol(pscol);
        paramObject.setYhbh(yhbh);
        paramObject.setPibzw(pibzw);
        String checkDataLimit = ISSPSecurityObject.CheckDataLimit(paramObject);
        //查询照明系统第一级IP路由器
        List<Map<String, Object>> DdcInfoList = besSbpzInterfaceMapper.getDdcInfo(checkDataLimit);
        //根据第一级的sysname查询第二级，支线/干线/模块
        for (Map<String, Object> DdcInfo : DdcInfoList)
        {
            List<Map<String, Object>> secondLevelList = besSbpzInterfaceMapper.getSingleLevelList(String.valueOf(DdcInfo.get("F_SYS_NAME")), checkDataLimit);
            //根据第二级的sysname查询第三级，模块/逻辑点
            for (Map<String, Object> secondLevel : secondLevelList)
            {
                List<Map<String, Object>> thirdLevelList = besSbpzInterfaceMapper.getSingleLevelList(String.valueOf(secondLevel.get("F_SYS_NAME")), checkDataLimit);
                for (Map<String, Object> thirdLevel : thirdLevelList)
                {
                    List<Map<String, Object>> fourthLevelList = besSbpzInterfaceMapper.getSingleLevelList(String.valueOf(thirdLevel.get("F_SYS_NAME")), checkDataLimit);
                    thirdLevel.put("fourthLevelList", fourthLevelList);
                }
                secondLevel.put("thirdLevelList", thirdLevelList);
            }
            DdcInfo.put("secondLevelList", secondLevelList);
        }
        return DdcInfoList;
    }

    @Override
    public List<Map<String, Object>> getKtpzList()
    {
        Principal principa = (Principal) SecurityUtils.getSubject().getPrincipal();
        ESUser user = principa.getUser();
        // 获取当前用户的用户名
        String username = user.getF_yhbh();
        String qxbh = "0008";
        String pscol = "";
        String yhbh = username;
        String pibzw = "1";
        LimitParamObject paramObject = new LimitParamObject();
        paramObject.setQxbh(qxbh);
        paramObject.setPscol(pscol);
        paramObject.setYhbh(yhbh);
        paramObject.setPibzw(pibzw);
        String checkDataLimit = ISSPSecurityObject.CheckDataLimit(paramObject);
        //查询照明系统第一级IP路由器
        List<Map<String, Object>> KtpzInfoList = besSbpzInterfaceMapper.getKtpzInfo(checkDataLimit);
        return KtpzInfoList;
    }

    @Override
    public List<Map<String, Object>> getKtpz(String ktName)
    {
        Principal principa = (Principal) SecurityUtils.getSubject().getPrincipal();
        ESUser user = principa.getUser();
        // 获取当前用户的用户名
        String username = user.getF_yhbh();
        String qxbh = "0008";
        String pscol = "";
        String yhbh = username;
        String pibzw = "1";
        LimitParamObject paramObject = new LimitParamObject();
        paramObject.setQxbh(qxbh);
        paramObject.setPscol(pscol);
        paramObject.setYhbh(yhbh);
        paramObject.setPibzw(pibzw);
        String checkDataLimit = ISSPSecurityObject.CheckDataLimit(paramObject);

        List<Map<String, Object>> KtpzList = new ArrayList<>();
        if (("").equals(ktName) || null == ktName || ktName.equals("null"))
        {
            KtpzList = besSbpzInterfaceMapper.getKtpzInfo(checkDataLimit);
        } else
        {
            KtpzList = besSbpzInterfaceMapper.getKtpzList(ktName, checkDataLimit);
        }
        return getNext(KtpzList);
    }

    @Override
    public List<Map<String, Object>> getWkqpzList()
    {
        Principal principa = (Principal) SecurityUtils.getSubject().getPrincipal();
        ESUser user = principa.getUser();
        // 获取当前用户的用户名
        String username = user.getF_yhbh();
        String qxbh = "0008";
        String pscol = "";
        String yhbh = username;
        String pibzw = "1";
        LimitParamObject paramObject = new LimitParamObject();
        paramObject.setQxbh(qxbh);
        paramObject.setPscol(pscol);
        paramObject.setYhbh(yhbh);
        paramObject.setPibzw(pibzw);
        String checkDataLimit = ISSPSecurityObject.CheckDataLimit(paramObject);
        //查询照明系统第一级IP路由器
        List<Map<String, Object>> WkqpzInfoList = besSbpzInterfaceMapper.getWkqpzInfo(checkDataLimit);
        return WkqpzInfoList;
    }

    @Override
    public List<Map<String, Object>> getWkqpz(String wkqName)
    {
        Principal principa = (Principal) SecurityUtils.getSubject().getPrincipal();
        ESUser user = principa.getUser();
        // 获取当前用户的用户名
        String username = user.getF_yhbh();
        String qxbh = "0008";
        String pscol = "";
        String yhbh = username;
        String pibzw = "1";
        LimitParamObject paramObject = new LimitParamObject();
        paramObject.setQxbh(qxbh);
        paramObject.setPscol(pscol);
        paramObject.setYhbh(yhbh);
        paramObject.setPibzw(pibzw);
        String checkDataLimit = ISSPSecurityObject.CheckDataLimit(paramObject);

        List<Map<String, Object>> WkqpzList = new ArrayList<>();
        if (("").equals(wkqName) || null == wkqName || wkqName.equals("null"))
        {
            WkqpzList = besSbpzInterfaceMapper.getWkqpzInfo(checkDataLimit);
        } else
        {
            WkqpzList = besSbpzInterfaceMapper.getWkqpzList(wkqName, checkDataLimit);
        }
        return getNext(WkqpzList);
    }

    @Override
    public List<Map<String, Object>> getSingleLevel(String fSysName)
    {
        Principal principa = (Principal) SecurityUtils.getSubject().getPrincipal();
        ESUser user = principa.getUser();
        // 获取当前用户的用户名
        String username = user.getF_yhbh();
        String qxbh = "0008";
        String pscol = "";
        String yhbh = username;
        String pibzw = "1";
        LimitParamObject paramObject = new LimitParamObject();
        paramObject.setQxbh(qxbh);
        paramObject.setPscol(pscol);
        paramObject.setYhbh(yhbh);
        paramObject.setPibzw(pibzw);
        String checkDataLimit = ISSPSecurityObject.CheckDataLimit(paramObject);

        List<Map<String, Object>> singleLevelList = new ArrayList<>();
        if (("").equals(fSysName) || null == fSysName || fSysName.equals("null"))
        {
            //查询照明系统第一级IP路由器
            singleLevelList = besSbpzInterfaceMapper.getDdcInfo(checkDataLimit);
        } else
        {
            singleLevelList = besSbpzInterfaceMapper.getSingleLevelList(fSysName, checkDataLimit);
        }
        return getNext(singleLevelList);
    }


    @Override
    public List<Map<String, Object>> getPLevel(String f_PSYS_NAME)
    {
        List<Map<String, Object>> pLevelList = new ArrayList<>();
        if (!("").equals(f_PSYS_NAME) || null != f_PSYS_NAME || !f_PSYS_NAME.equals("null"))
        {
            //查询照明系统第一级IP路由器
            pLevelList = besSbpzInterfaceMapper.getPLevel(f_PSYS_NAME);
        } else
        {
        }
        return getNext(pLevelList);
    }

    public List<Map<String, Object>> getNext(List<Map<String, Object>> singleLevelList)
    {
        for (Map<String, Object> singleLevel : singleLevelList)
        {
            List<Map<String, Object>> nextLevel = besSbpzInterfaceMapper.getNext(String.valueOf(singleLevel.get("F_SYS_NAME")));
            if (nextLevel.size() == 0)
            {
                singleLevel.put("F_NEXTLEVEL", "0");//"0"表示没有下一级
                getStatus(singleLevel);
            } else
            {
                singleLevel.put("F_NEXTLEVEL", "1");//"1"表示有下一级
            }
        }
        return singleLevelList;
    }

    public void getStatus(Map<String, Object> singleLevel)
    {
	/*	List<BESSbPzStruct> list = BESSbtreeThread.getSbtreeList();
		for(BESSbPzStruct sbtreeInfo : list) {
			if(sbtreeInfo.getF_sys_name().equals(String.valueOf(singleLevel.get("F_SYS_NAME")))) {
				singleLevel.put("F_INIT_VAL", sbtreeInfo.getF_init_val());
				singleLevel.put("F_STATUS", sbtreeInfo.getF_status());
			}
		}*/

        BESSbPzStruct besSbPzStruct = BESSbtreeThread.realTimeData.get(String.valueOf(singleLevel.get("F_SYS_NAME")));

        if (null != besSbPzStruct)
        {
            singleLevel.put("F_INIT_VAL", besSbPzStruct.getF_init_val());
            singleLevel.put("F_STATUS", besSbPzStruct.getF_status());
        }

    }

    @Override
    public List<Map<String, Object>> getMonitoring()
    {
        //数据权限开始
        Principal principa = (Principal) SecurityUtils.getSubject().getPrincipal();
        ESUser user = principa.getUser();
        // 获取当前用户的用户名
        String username = user.getF_yhbh();
        String qxbh = "0009";
        String pscol = "";
        String yhbh = username;
        String pibzw = "1";
        LimitParamObject paramObject = new LimitParamObject();
        paramObject.setQxbh(qxbh);
        paramObject.setPscol(pscol);
        paramObject.setYhbh(yhbh);
        paramObject.setPibzw(pibzw);
        String checkDataLimit = ISSPSecurityObject.CheckDataLimit(paramObject);
        //数据权限结束
        //根据用户数据权限查询监控界面数据
        List<Map<String, Object>> singleLevelList = besSbpzInterfaceMapper.getMonitoring(checkDataLimit);
        for (Map<String, Object> singleLevel : singleLevelList)
        {
            if ("single".equals(singleLevel.get("F_BUTTON_TYPE")))
            {//单控按钮
                getStatus(singleLevel);
            } else
            {//多控制按钮
                String[] f_sys_name = singleLevel.get("F_SYS_NAME").toString().split(",");
                int[] status = new int[f_sys_name.length];
                //List<BESSbPzStruct> list = BESSbtreeThread.getSbtreeList();
                for (int i = 0; i < f_sys_name.length; i++)
                {
				/*	for(BESSbPzStruct sbtreeInfo : list) {
						if(sbtreeInfo.getF_sys_name().equals(f_sys_name[i])) {
							singleLevel.put("F_STATUS", sbtreeInfo.getF_status());
							String f_init_val = sbtreeInfo.getF_init_val().toString();
							if(null != f_init_val && !"null".equals(f_init_val) && !"".equals(f_init_val)) {
								status[i] = Integer.valueOf(sbtreeInfo.getF_init_val());
							}else {
								status[i] = 0;
							}
						}
					}*/

                    BESSbPzStruct besSbPzStruct = BESSbtreeThread.realTimeData.get(f_sys_name[i]);

                    if (null != besSbPzStruct)
                    {
                        singleLevel.put("F_STATUS", besSbPzStruct.getF_status());
                        String f_init_val = besSbPzStruct.getF_init_val().toString();
                        if (null != f_init_val && !"null".equals(f_init_val) && !"".equals(f_init_val))
                        {
                            status[i] = Integer.valueOf(besSbPzStruct.getF_init_val());
                        } else
                        {
                            status[i] = 0;
                        }

                    }

                }
                int minIndex = status[0];//定义最大值为该数组的第一个数
                int maxIndex = status[0];//定义最大值为该数组的第一个数
                for (int i = 0; i < status.length; i++)
                {
                    if (maxIndex < status[i])
                    {
                        maxIndex = status[i];
                    }
                    if (minIndex > status[i])
                    {
                        minIndex = status[i];
                    }
                }
                if (minIndex == maxIndex && minIndex == 0)
                {
                    singleLevel.put("F_INIT_VAL", "0");
                } else if (minIndex == maxIndex && maxIndex == 255)
                {
                    singleLevel.put("F_INIT_VAL", "255");
                } else
                {
                    singleLevel.put("F_INIT_VAL", "0");
                }
            }
        }
        return singleLevelList;
    }

    @Override
    public List<Map<String, Object>> getFreshairconfigList()
    {
        Principal principa = (Principal) SecurityUtils.getSubject().getPrincipal();
        ESUser user = principa.getUser();
        // 获取当前用户的用户名
        String username = user.getF_yhbh();
        String qxbh = "0008";
        String pscol = "";
        String yhbh = username;
        String pibzw = "1";
        LimitParamObject paramObject = new LimitParamObject();
        paramObject.setQxbh(qxbh);
        paramObject.setPscol(pscol);
        paramObject.setYhbh(yhbh);
        paramObject.setPibzw(pibzw);
        String checkDataLimit = ISSPSecurityObject.CheckDataLimit(paramObject);
        //查询照明系统第一级IP路由器
        List<Map<String, Object>> KtpzInfoList = besSbpzInterfaceMapper.getFreshairconfigList(checkDataLimit);
        return KtpzInfoList;
    }

    @Override
    public List<Map<String, Object>> getFreshairconfig(String windName)
    {
        Principal principa = (Principal) SecurityUtils.getSubject().getPrincipal();
        ESUser user = principa.getUser();
        // 获取当前用户的用户名
        String username = user.getF_yhbh();
        String qxbh = "0008";
        String pscol = "";
        String yhbh = username;
        String pibzw = "1";
        LimitParamObject paramObject = new LimitParamObject();
        paramObject.setQxbh(qxbh);
        paramObject.setPscol(pscol);
        paramObject.setYhbh(yhbh);
        paramObject.setPibzw(pibzw);
        String checkDataLimit = ISSPSecurityObject.CheckDataLimit(paramObject);

        List<Map<String, Object>> KtpzList = new ArrayList<>();
        if (("").equals(windName) || null == windName || windName.equals("null"))
        {
            KtpzList = besSbpzInterfaceMapper.getFreshairconfigList(checkDataLimit);
        } else
        {
            KtpzList = besSbpzInterfaceMapper.getFreshairconfig(windName, checkDataLimit);
        }
        return getNext(KtpzList);
    }

    @Override
    public List<Map<String, Object>> getAlloffList(String systemName)
    {
        Principal principa = (Principal) SecurityUtils.getSubject().getPrincipal();
        ESUser user = principa.getUser();
        // 获取当前用户的用户名
        String username = user.getF_yhbh();
        String qxbh = "0008";
        String pscol = "";
        String yhbh = username;
        String pibzw = "1";
        LimitParamObject paramObject = new LimitParamObject();
        paramObject.setQxbh(qxbh);
        paramObject.setPscol(pscol);
        paramObject.setYhbh(yhbh);
        paramObject.setPibzw(pibzw);
        String checkDataLimit = ISSPSecurityObject.CheckDataLimit(paramObject);
        List<Map<String, Object>> KtpzList = new ArrayList<>();
        Map<String, Object> AOmap = besSbpzInterfaceMapper.getAOoffList(systemName);
        Map<String, Object> DOmap = besSbpzInterfaceMapper.getDOoffList(systemName);
        Map<String, Object> AImap = besSbpzInterfaceMapper.getAIoffList(systemName);
        Map<String, Object> DImap = besSbpzInterfaceMapper.getDIoffList(systemName);
        Map<String, Object> POmap = besSbpzInterfaceMapper.getPOoffList(systemName);
        if (AOmap != null) KtpzList.add(AOmap);
        if (DOmap != null) KtpzList.add(DOmap);
        if (AImap != null) KtpzList.add(AImap);
        if (DImap != null) KtpzList.add(DImap);
        if (POmap != null) KtpzList.add(POmap);
        return getNext(KtpzList);
    }


    /**
     * 根据灯光名称获取灯光配置信息
     *
     * @param lightName
     * @return
     */
    @Override
    public List<Map<String, Object>> getLight(String lightName)
    {
        Principal principa = (Principal) SecurityUtils.getSubject().getPrincipal();
        ESUser user = principa.getUser();
        // 获取当前用户的用户名
        String username = user.getF_yhbh();
        String qxbh = "0008";
        String pscol = "";
        String yhbh = username;
        String pibzw = "1";
        LimitParamObject paramObject = new LimitParamObject();
        paramObject.setQxbh(qxbh);
        paramObject.setPscol(pscol);
        paramObject.setYhbh(yhbh);
        paramObject.setPibzw(pibzw);
        String checkDataLimit = ISSPSecurityObject.CheckDataLimit(paramObject);

        List<Map<String, Object>> lightList;
        if ("".equals(lightName) || lightName == null || "null".equals(lightName))
        {
            lightList = besSbpzInterfaceMapper.getLightList(checkDataLimit);
        } else
        {
            lightList = besSbpzInterfaceMapper.getLight(lightName, checkDataLimit);
        }
        return getNext(lightList);
    }

    /**
     * 获取所有灯光配置信息
     *
     * @return
     */
    @Override
    public List<Map<String, Object>> getLightList()
    {
        Principal principa = (Principal) SecurityUtils.getSubject().getPrincipal();
        ESUser user = principa.getUser();
        // 获取当前用户的用户名
        String username = user.getF_yhbh();
        String qxbh = "0008";
        String pscol = "";
        String yhbh = username;
        String pibzw = "1";
        LimitParamObject paramObject = new LimitParamObject();
        paramObject.setQxbh(qxbh);
        paramObject.setPscol(pscol);
        paramObject.setYhbh(yhbh);
        paramObject.setPibzw(pibzw);
        String checkDataLimit = ISSPSecurityObject.CheckDataLimit(paramObject);
        //查询所有的灯光配置信息
        List<Map<String, Object>> lightList = besSbpzInterfaceMapper.getLightList(checkDataLimit);
        return lightList;
    }


    /**
     * 根据可调光灯光名称获取可调光灯光配置信息
     *
     * @param lightName
     * @return
     */
    @Override
    public List<Map<String, Object>> getTgLight(String lightName)
    {
        Principal principa = (Principal) SecurityUtils.getSubject().getPrincipal();
        ESUser user = principa.getUser();
        // 获取当前用户的用户名
        String username = user.getF_yhbh();
        String qxbh = "0008";
        String pscol = "";
        String yhbh = username;
        String pibzw = "1";
        LimitParamObject paramObject = new LimitParamObject();
        paramObject.setQxbh(qxbh);
        paramObject.setPscol(pscol);
        paramObject.setYhbh(yhbh);
        paramObject.setPibzw(pibzw);
        String checkDataLimit = ISSPSecurityObject.CheckDataLimit(paramObject);

        List<Map<String, Object>> lightList;
        if ("".equals(lightName) || lightName == null || "null".equals(lightName))
        {
            lightList = besSbpzInterfaceMapper.getTgLightList(checkDataLimit);
        } else
        {
            lightList = besSbpzInterfaceMapper.getTgLight(lightName, checkDataLimit);
        }
        return getNext(lightList);
    }

    /**
     * 获取所有可调光灯光配置信息
     *
     * @return
     */
    @Override
    public List<Map<String, Object>> getTgLightList()
    {
        Principal principa = (Principal) SecurityUtils.getSubject().getPrincipal();
        ESUser user = principa.getUser();
        // 获取当前用户的用户名
        String username = user.getF_yhbh();
        String qxbh = "0008";
        String pscol = "";
        String yhbh = username;
        String pibzw = "1";
        LimitParamObject paramObject = new LimitParamObject();
        paramObject.setQxbh(qxbh);
        paramObject.setPscol(pscol);
        paramObject.setYhbh(yhbh);
        paramObject.setPibzw(pibzw);
        String checkDataLimit = ISSPSecurityObject.CheckDataLimit(paramObject);
        //查询所有的灯光配置信息
        List<Map<String, Object>> lightList = besSbpzInterfaceMapper.getTgLightList(checkDataLimit);
        return lightList;
    }

    /**
     * 根据插座名称获取插座配置信息
     *
     * @param socketName
     * @return
     */
    @Override
    public List<Map<String, Object>> getSocket(String socketName)
    {
        Principal principa = (Principal) SecurityUtils.getSubject().getPrincipal();
        ESUser user = principa.getUser();
        // 获取当前用户的用户名
        String username = user.getF_yhbh();
        String qxbh = "0008";
        String pscol = "";
        String yhbh = username;
        String pibzw = "1";
        LimitParamObject paramObject = new LimitParamObject();
        paramObject.setQxbh(qxbh);
        paramObject.setPscol(pscol);
        paramObject.setYhbh(yhbh);
        paramObject.setPibzw(pibzw);
        String checkDataLimit = ISSPSecurityObject.CheckDataLimit(paramObject);

        List<Map<String, Object>> socketList;
        if ("".equals(socketName) || socketName == null || "null".equals(socketName))
        {
            socketList = besSbpzInterfaceMapper.getSocketList(checkDataLimit);
        } else
        {
            socketList = besSbpzInterfaceMapper.getSocket(socketName, checkDataLimit);
        }
        return getNext(socketList);
    }

    /**
     * 获取所有插座配置信息
     *
     * @return
     */
    @Override
    public List<Map<String, Object>> getSocketList()
    {
        Principal principa = (Principal) SecurityUtils.getSubject().getPrincipal();
        ESUser user = principa.getUser();
        // 获取当前用户的用户名
        String username = user.getF_yhbh();
        String qxbh = "0008";
        String pscol = "";
        String yhbh = username;
        String pibzw = "1";
        LimitParamObject paramObject = new LimitParamObject();
        paramObject.setQxbh(qxbh);
        paramObject.setPscol(pscol);
        paramObject.setYhbh(yhbh);
        paramObject.setPibzw(pibzw);
        String checkDataLimit = ISSPSecurityObject.CheckDataLimit(paramObject);
        //查询所有的灯光配置信息
        List<Map<String, Object>> socketList = besSbpzInterfaceMapper.getSocketList(checkDataLimit);
        return socketList;
    }

    /**
     * 根据组合式空调机组名称获取组合式空调机组配置信息
     *
     * @param airconditioningUnitName
     * @return
     */
    @Override
    public List<Map<String, Object>> getAirconditioningUnit(String airconditioningUnitName)
    {
        Principal principa = (Principal) SecurityUtils.getSubject().getPrincipal();
        ESUser user = principa.getUser();
        // 获取当前用户的用户名
        String username = user.getF_yhbh();
        String qxbh = "0008";
        String pscol = "";
        String yhbh = username;
        String pibzw = "1";
        LimitParamObject paramObject = new LimitParamObject();
        paramObject.setQxbh(qxbh);
        paramObject.setPscol(pscol);
        paramObject.setYhbh(yhbh);
        paramObject.setPibzw(pibzw);
        String checkDataLimit = ISSPSecurityObject.CheckDataLimit(paramObject);

        List<Map<String, Object>> airconditioningUnitList;
        if ("".equals(airconditioningUnitName) || airconditioningUnitName == null || "null".equals(airconditioningUnitName))
        {
            airconditioningUnitList = besSbpzInterfaceMapper.getAirconditioningUnitList(checkDataLimit);
        } else
        {
            airconditioningUnitList = besSbpzInterfaceMapper.getAirconditioningUnit(airconditioningUnitName, checkDataLimit);
        }
        return getNext(airconditioningUnitList);
    }

    /**
     * 获取所有组合式空调机组配置信息
     *
     * @return
     */
    @Override
    public List<Map<String, Object>> getAirconditioningUnitList()
    {
        Principal principa = (Principal) SecurityUtils.getSubject().getPrincipal();
        ESUser user = principa.getUser();
        // 获取当前用户的用户名
        String username = user.getF_yhbh();
        String qxbh = "0008";
        String pscol = "";
        String yhbh = username;
        String pibzw = "1";
        LimitParamObject paramObject = new LimitParamObject();
        paramObject.setQxbh(qxbh);
        paramObject.setPscol(pscol);
        paramObject.setYhbh(yhbh);
        paramObject.setPibzw(pibzw);
        String checkDataLimit = ISSPSecurityObject.CheckDataLimit(paramObject);
        //查询所有的灯光配置信息
        List<Map<String, Object>> airconditioningUnitList = besSbpzInterfaceMapper.getAirconditioningUnitList(checkDataLimit);
        return airconditioningUnitList;
    }

    /**
     * 获取设备实时值
     * @param fSysName
     * @return
     */
    @Override
    public ISSPReturnObject getRealTime(String fSysName) {

        ISSPReturnObject result = new ISSPReturnObject();

        if (!StringUtils.hasText(fSysName))
        {
            result.setStatus("0");
            result.setMsg("参数错误：系统名称不存在！");
            return result;
        }

        BESSbPzStruct besSbPzStruct = sbPzStructCache.getCachedElement(fSysName);

        if (besSbPzStruct == null)
        {
            result.setStatus("0");
            result.setMsg("当前设备不存在！");
            return result;
        }

        Map<String, String> data = new HashMap<>();

        data.put("value", besSbPzStruct.getF_init_val());

        result.setData(data);
        result.setStatus("1");

        return result;
    }

    /**
     * 查询所有
     *
     * @return
     */
    @Override
    public List<Map<String, Object>> getDdclList()
    {
        Principal principa = (Principal) SecurityUtils.getSubject().getPrincipal();
        ESUser user = principa.getUser();
        // 获取当前用户的用户名
        String username = user.getF_yhbh();
        String qxbh = "0008";
        String pscol = "";
        String yhbh = username;
        String pibzw = "1";
        LimitParamObject paramObject = new LimitParamObject();
        paramObject.setQxbh(qxbh);
        paramObject.setPscol(pscol);
        paramObject.setYhbh(yhbh);
        paramObject.setPibzw(pibzw);
        String checkDataLimit = ISSPSecurityObject.CheckDataLimit(paramObject);
        //查询照明系统第一级IP路由器
        List<Map<String, Object>> KtpzInfoList = besSbpzInterfaceMapper.getDdclInfo(checkDataLimit);
        return KtpzInfoList;
    }

    /**
     * 单个查询
     *
     * @param CLName
     * @return
     */
    @Override
    public List<Map<String, Object>> getDdcl(String CLName)
    {
        Principal principa = (Principal) SecurityUtils.getSubject().getPrincipal();
        ESUser user = principa.getUser();
        // 获取当前用户的用户名
        String username = user.getF_yhbh();
        String qxbh = "0008";
        String pscol = "";
        String yhbh = username;
        String pibzw = "1";
        LimitParamObject paramObject = new LimitParamObject();
        paramObject.setQxbh(qxbh);
        paramObject.setPscol(pscol);
        paramObject.setYhbh(yhbh);
        paramObject.setPibzw(pibzw);
        String checkDataLimit = ISSPSecurityObject.CheckDataLimit(paramObject);

        List<Map<String, Object>> DdclList = new ArrayList<>();
        if (("").equals(CLName) || null == CLName || CLName.equals("null"))
        {
            DdclList = besSbpzInterfaceMapper.getDdclInfo(checkDataLimit);
        } else
        {
            DdclList = besSbpzInterfaceMapper.getDdclList(CLName, checkDataLimit);
        }
        return getNext(DdclList);
    }
}
