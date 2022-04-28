package com.efounder.JEnterprise.commhandler;

import com.core.ApplicationContextProvider;
import com.core.common.ISSPReturnObject;
import com.core.common.constant.WebSocketEvent;
import com.core.common.util.AlarmUtil;
import com.core.common.util.DataUtil;
import com.core.common.util.G4Utils;
import com.core.common.util.LEMSUtil;
import com.efounder.JEnterprise.common.util.UUIDUtil;
import com.efounder.JEnterprise.initializer.*;
import com.efounder.JEnterprise.mapper.collectorJob.BESSubAlarmMapper;
import com.efounder.JEnterprise.mapper.dataAnalysises.BesWainingRealInfoMapper;
import com.efounder.JEnterprise.model.basedatamanage.enegrycollectionmanage.BESElectricParams;
import com.efounder.JEnterprise.model.basedatamanage.enegrycollectionmanage.BESElectric_Coll_Rlgl;
import com.efounder.JEnterprise.model.basedatamanage.eqmanage.*;
import com.efounder.JEnterprise.model.dataAnalysises.BesBranchConfAlarmConfiguration;
import com.efounder.JEnterprise.model.dataAnalysises.BesBranchConfParameter;
import com.efounder.JEnterprise.model.dataAnalysises.BesWainingInfo;
import org.ace.business.dto.ReceiveMsg;
import org.ace.business.dto.ddc.PointDataDDC;
import org.ace.business.dto.edc.AmmeterData;
import org.ace.business.dto.ldc.PointDataLDC;
import org.ace.websocket.handler.WebSocketService;
import org.apache.commons.collections.map.HashedMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Resource;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 告警处理类
 * @author wanghongjie
 * @date 2020/6/12 8:08
 */
public class AlarmHandler
{

    private static final Logger log = LoggerFactory.getLogger(AlarmHandler.class);

    private BesWainingRealInfoMapper beswainingrealinfomapper = ApplicationContextProvider.getBean(BesWainingRealInfoMapper.class);

    static ScriptEngine jse = new ScriptEngineManager().getEngineByName("JavaScript");

    // 电表缓存定义
    private static AmmeterCache ammeterCache = ApplicationContextProvider.getBean(AmmeterCache.class);

    // 采集方案采集参数关联定义缓存
    private static ElectricCollRlglCache electricCollRlglCache = ApplicationContextProvider.getBean(ElectricCollRlglCache.class);

    // 采集参数定义缓存
    private static ElectricParamsCache electricParamsCache = ApplicationContextProvider.getBean(ElectricParamsCache.class);

    ////支路报警配置缓存
    private static BranchConfAlarmConfigurationCache besBranchConfAlarmConfigurationCache = ApplicationContextProvider.getBean(BranchConfAlarmConfigurationCache.class);

    //支路报警规则
    private static BranchConfParameterCache branchConfParameterCache = ApplicationContextProvider.getBean(BranchConfParameterCache.class);

    //设备树配置定义缓存
    private static SbPzStructCache sbPzStructCache = ApplicationContextProvider.getBean(SbPzStructCache.class);

    //电表原始数据缓存缓存
    private static OriginalDataCache originalDataCache = ApplicationContextProvider.getBean(OriginalDataCache.class) ;

    //设备树节点类型定义缓存
    private static SbTreeNodeTypeCache sbTreeNodeTypeCache = ApplicationContextProvider.getBean(SbTreeNodeTypeCache.class);

    private static VirtualPointCache VirtualPointCache = ApplicationContextProvider.getBean(VirtualPointCache.class);

    private static AiPointCache aiPointCache = ApplicationContextProvider.getBean(AiPointCache.class);

    private static AoPointCache aoPointCache = ApplicationContextProvider.getBean(AoPointCache.class);

    private static DiPointCache diPointCache = ApplicationContextProvider.getBean(DiPointCache.class);

    private static DoPointCache doPointCache = ApplicationContextProvider.getBean(DoPointCache.class);

    private static DdcCache ddcCache = ApplicationContextProvider.getBean(DdcCache.class);


    /**
     * 王红杰
     * 能耗告警处理
     * @param msg
     */
    public void alarmHandle(ReceiveMsg<List<AmmeterData>> msg)
    {
        /*wanghongjie start 20200602 实时报警功能的编写*/
        List<AmmeterData> ammeterDataList = msg.getData();

        Boolean alarmYesOrNo = false;//是否有报警消息插入到实时报警表,初始值为false

        Integer rate;//变比

        for (AmmeterData ammeterData : ammeterDataList)
        {
            // 电能参数
            String electricData = ammeterData.getElectricData();//获取上传的电能参数的值
            String [] electricDatas = electricData.split(",");//
            String meterID = ammeterData.getMeterID().toString();//将获取的电表设备编号转成String
            // 实际采集的电能参数个数
            Integer collectCount = ammeterData.getCollectCount();
            if (    electricData.isEmpty()
                    || null == collectCount
                    || collectCount <= 0)
            {
                log.warn("下位机数据错误");
                continue;
            }

            if (!collectCount.equals(electricDatas.length))
            {
                log.warn("下位机数据错误");
                continue;
            }

            //获取当前电表的变比  根据上传的sbid查询
//            String f_percentage = besSubAlarmMapper.SelectAlarmParmeF_percentage(meterID);//变比
            BESAmmeter besAmmeter = ammeterCache.getCachedElementBySbid(meterID);//变比
            if (besAmmeter == null) {
                continue;
            }
            String f_percentage = besAmmeter.getfPercentage();

            if (f_percentage != null){//如果变比不为null,将变比转换成int
                rate = Integer.parseInt(f_percentage);

            }else {//否则跳出当前循环
                continue;
            }



            List<String> electricDatasList = Arrays.asList(electricDatas);//将传入的值转成List
//            List<Map<String,Object>> f_nhbhList = besSubAlarmMapper.SelectAlarmParmeF_nhbh(meterID);//查询当前电表的所有的电能参数

            //根据meterID查询采集方案编号
            BESAmmeter ammeterParam = ammeterCache.getCachedElementBySbid(meterID);

            if (ammeterParam == null)
            {
                log.warn("上位机与下位机电表一致（上位机没有对应的电能参数）");
                continue;
            }

            List<BESElectric_Coll_Rlgl> besElectricCollRlgls = electricCollRlglCache.getCachedElementByCjfabh(ammeterParam.getfCjfabh());


            if (besElectricCollRlgls == null || besElectricCollRlgls.size() == 0) {
                continue;
            }

            Map<String,Object> d = new HashMap<>();

            for (int i = 0 ; i < besElectricCollRlgls.size(); i++){//将电能参数和电能参数的值一一对应上

                BESElectric_Coll_Rlgl besElectricCollRlgl = besElectricCollRlgls.get(i);

                BESElectricParams besElectricParams = electricParamsCache.getCachedElement(besElectricCollRlgl.getfNhbh());

                if (besElectricParams == null)
                {
                    log.warn("上位机电能参数不存在（上位机没有对应的电能参数）");
                    continue;
                }

                // 小数位数（从下位机电能参数中获取小数位置参数）
                Integer decimals = Integer.parseInt(besElectricParams.getfScalingPosition());
                // 编码规则（从下位机电能参数中获取编码规则参数）
                Integer enctype = Integer.parseInt(besElectricParams.getfBmgz());
                // 是否变比
                String isRate = besElectricCollRlgl.getfIsRate();

                for (int j = 0 ; j < electricDatasList.size(); j++){
                    // 解析原始数据
                    String data = String.valueOf(DataUtil.parseRawData(electricDatasList.get(j), rate, decimals, enctype, isRate));
                    if (i == j){
                        String s1 = besElectricCollRlgl.getfNhbh();
                        d.put(s1, data);
                    }
                }

            }

            /*Map<String,Object> d = new HashMap<String,Object>();
            for (int i = 0;i<f_nhbhList.size();i++){//将电能参数和电能参数的值一一对应上
                Map<String,Object> s = f_nhbhList.get(i);

                //获取当前电表的小数点位置
                Integer decimals = Integer.parseInt((String) f_nhbhList.get(i).get("F_SCALING_POSITION"));
                //获取当前电表的编码规则
                Integer enctype = Integer.parseInt((String) f_nhbhList.get(i).get("F_BMGZ"));

                // 是否变比
                String isRate = (String) f_nhbhList.get(i).get("F_IS_RATE");

                for (int j = 0 ;j<electricDatasList.size();j++){
                    // 解析原始数据
                    String data = String.valueOf(DataUtil.parseRawData(electricDatasList.get(j), rate, decimals, enctype, isRate));
                    if (i==j){
                        String s1 = (String) s.get("F_NHBH");
                        d.put(s1, data);
                    }
                }

            }*/

            //根据id查询电表数据表,获取系统名称
            /*Map<String,Object> ammeterMap = besSubAlarmMapper.selectAmmeterMap(meterID);

            if (ammeterMap.isEmpty()) {
                continue;
            }

            String f_sys_name_old = (String) ammeterMap.get("F_SYS_NAME_OLD");*/
            String f_sys_name_old = besAmmeter.getfSysNameOld();

//            List<Map<String,Object>> alarmtTypeList = besSubAlarmMapper.selectId(f_sys_name_old);//查询支路报警类型
            List<BesBranchConfAlarmConfiguration> alarmtTypeList =  besBranchConfAlarmConfigurationCache.getCachedElement(f_sys_name_old);
            if (alarmtTypeList == null || alarmtTypeList.size() == 0){
                continue;
            }else {
                for (BesBranchConfAlarmConfiguration alarmList : alarmtTypeList ){
                    //判断当前支路报警配置信息的使能状态
                    if (alarmList.getfActive().equals("1")) {
                        continue;
                    }
                    List ammeterList = new ArrayList();
                    String name = alarmList.getfNymc();//能源名称
                    String F_ZLBH = alarmList.getfZlbh();//支路编号
//                    List<Map<String, Object>> list = besSubAlarmMapper.SelectAlarmParmeSub(alarmList.getfAlertid());//查询报警规则所用到的电表电能参数
                    List<BesBranchConfParameter> besBranchConfParameterList = branchConfParameterCache.getCachedElement(alarmList.getfAlertid());//查询报警规则所用到的电表电能参数

                    String url = "";

                    if (besBranchConfParameterList == null || besBranchConfParameterList.size() == 0 ) {
                        continue;
                    }

                    for(int j = 0; j < besBranchConfParameterList.size(); j++){//遍历报警规则用到的电能参数
                        BesBranchConfParameter dtoParmeId = besBranchConfParameterList.get(j);//将报警规则和电表的SBID获取到

                        BESAmmeter besAmmeterManage = ammeterCache.getCachedElement(dtoParmeId.getfAmmsysName());
                        if (besAmmeterManage == null) {
                            continue;
                        }
                        String fSbid = besAmmeterManage.getfSbid();
                        if (fSbid.equals(meterID)){//如果报警规则所包含的电表里面有当前电表
                            for (Map.Entry<String,Object> entry : d.entrySet()){
                                if (dtoParmeId.getfElednbh().equals(entry.getKey().toString())){
                                    ammeterList.add(entry.getValue());
                                }
                            }
                        }else {//如果报警规则所包含的电表里面没有当前电表,查询数据库中当前电能参数的值

                            if (!dtoParmeId.getfElednbh().equals("10000")) {//如果电能编号不是AI点的电能编号,则说明是电表的电能编号,这个电能编号要看之路报警逻辑默认的电能编号
//                                Map<String, Object> dtoData = besSubAlarmMapper
//                                        .SelectAlarmParmeData(dtoParmeId.getfAmmsysName(),dtoParmeId.getfElednbh());
                                Map<String, OriginalData> dtoData =  originalDataCache.getCachedElementBySysName(dtoParmeId.getfAmmsysName());
                                if (G4Utils.isNotEmpty(dtoData)) {
                                    if (dtoData.containsKey(dtoParmeId.getfElednbh())) {
                                        ammeterList.add(dtoData.get(dtoParmeId.getfElednbh()).getfData());
                                    } else {
                                        ammeterList.add(0);
                                    }

                                } else {//如果电表原始数据表中没有当前电表编号的数据,则默认添加0
                                    ammeterList.add(0);
                                }
                            } else {
                                //如果查询电表原始数据表中,根据名称和电能编号查询不到数据,则说明当前名称是DDC的AI点名称
                                // 如果是AI点的电表,则向电表数据表 bes_calculate_data 查询当前AI点的数据
//                                Map<String,Object> calculateData = besSubAlarmMapper.selectCalculateDataByAIPoint(dtoParmeId.getfAmmsysName());
                                BESSbPzStruct calculateData = sbPzStructCache.getCachedElementBySysNameOld(dtoParmeId.getfAmmsysName());
                                if (G4Utils.isNotEmpty(calculateData)) {
                                    ammeterList.add(calculateData.getF_init_val());
                                } else {
                                    ammeterList.add(0);
                                }
                            }
                        }
                    }
                    float nData = 0.0f;
                    String operator = alarmList.getfOperator();//查询配置的报警公式
                    for(int k = ammeterList.size() ; k > 0; k--){
                        String strData = String.valueOf(ammeterList.get(k - 1)) ;
                        String strOperator = "$" + k;
                        operator = operator.replace(strOperator,strData);
                    }
                    try {
                        nData = Float.parseFloat(jse.eval(operator).toString());
                    } catch (ScriptException e) {
                        e.printStackTrace();
                    }

                    Date now = new Date();
                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//可以方便地修改日期格式
                    String strTime = dateFormat.format(now);
                    String id = UUIDUtil.getRandom32BeginTimePK();
                    //要添加的报警信息
                    BesWainingInfo besWaringInfo = new BesWainingInfo();
                    besWaringInfo.setFYqbh(alarmList.getfYqbh());
                    besWaringInfo.setFGuid(id);
                    besWaringInfo.setFLoction(name + "/" + url + "(" + F_ZLBH + ")" + "(" + alarmList.getfAlertname() +")");//报警位置
                    besWaringInfo.setFAlarmName(alarmList.getfAlertname());//报警名称
                    besWaringInfo.setFActualValue(String.valueOf(nData));//实际值
                    besWaringInfo.setFATime(strTime);//报警时间
                    besWaringInfo.setFOpearation("1");//是否处理
                    besWaringInfo.setFType(String.valueOf(alarmList.getfType()));//信息类型  (1:全部  2:设备报警 3:支路参数报警 4:火警摄像机报警 5:电气火灾报警 6:市电检测报警 7:水浸监测报警 8:烟雾监测报警 9:环境温湿度报警)
                    besWaringInfo.setFLevel(alarmList.getfAlertgrade());//报警等级

                    if (alarmList.getfRangetype().equals("0")) {
                        float nomore = Float.parseFloat(alarmList.getfNomore());
                        if (nData > nomore) {
                            alarmYesOrNo = true;
                            besWaringInfo.setFPlanValue(String.valueOf(nomore));//计划值
                            besWaringInfo.setFTipInfo(besWaringInfo.getFAlarmName()+"实际参数大于计划值");//提示信息
                            //判断是否存在相同的未处理报警
                            AlarmUtil.insertWarningRealAmmeter(besWaringInfo);
                        }
                    } else if (alarmList.getfRangetype().equals("1")) {
                        float noless = Float.parseFloat(alarmList.getfNoless());
                        if(nData<noless){
                            alarmYesOrNo = true;
                            besWaringInfo.setFPlanValue(String.valueOf(noless));//计划值
                            besWaringInfo.setFTipInfo(besWaringInfo.getFAlarmName()+"实际参数小于计划值");//提示信息
                            //判断是否存在相同的未处理报警
                            AlarmUtil.insertWarningRealAmmeter(besWaringInfo);
                        }
                    } else if (alarmList.getfRangetype().equals("2")) {
                        float nomore = Float.parseFloat(alarmList.getfNomore());//最大值
                        float noless = Float.parseFloat(alarmList.getfNoless());//最小
                        if(nData>nomore||nData<noless){
                            alarmYesOrNo = true;
                            besWaringInfo.setFPlanValue(String.valueOf(noless+"--"+nomore));//计划值
                            besWaringInfo.setFTipInfo(besWaringInfo.getFAlarmName()+"实际参数不在计划值范围");//提示信息
                            //判断是否存在相同的未处理报警
                            AlarmUtil.insertWarningRealAmmeter(besWaringInfo);
                        }
                    } else {
                        float equal = Float.parseFloat(alarmList.getfEqual());//最小
                        if(equal != nData){
                            alarmYesOrNo = true;
                            besWaringInfo.setFPlanValue(alarmList.getfEqual());//计划值
                            besWaringInfo.setFTipInfo(besWaringInfo.getFAlarmName()+"实际参数不等于计划值");//提示信息
                            //判断是否存在相同的未处理报警
                            AlarmUtil.insertWarningRealAmmeter(besWaringInfo);
                        }
                    }
                }
            }
        }


        String f_operation = "1";
        Map<String,Object> msgMap = new HashedMap();

//        PageInfo<Object> page = getAlarmWarnInfoByRecoverState(f_operation);//查询前20条的报警消息
        if (alarmYesOrNo == true) {//如果有报警消息插入实时报警表,则将报警的条数传到前端页面上,并发出报警声音
            ISSPReturnObject returnObject = getNoRecoverCount(f_operation);//查询报警的条数

            msgMap.put("noRecoverCount",returnObject.getData());

            // 推送消息到web客户端
            WebSocketService.postEvent( WebSocketEvent.ALARM, msgMap);
        }

//        map.put("page", page);
//        map.put("pageSize", page.getPageSize() + "");
//        map.put("dataset", JsonMapper.toJsonString(page.getList()));
//        String pageN = null;
//        if (String.valueOf(page.getPageNum()).indexOf(",") != -1)
//            pageN = String.valueOf(page.getPageNum()).replaceAll(",", "");
//        else
//            pageN = String.valueOf(page.getPageNum());
//        map.put("pageNum", pageN);
//        map.put("f_operation", f_operation);


    }

    /**
     *
     * @Description: 查询报警的条数
     *
     * @auther: wanghongjie
     * @date: 15:33 2020/6/3
     * @param: [f_operation]
     * @return: com.core.common.ISSPReturnObject
     *
     */
    public ISSPReturnObject getNoRecoverCount(String f_operation) {
        ISSPReturnObject returnObject = new ISSPReturnObject();
        try {
            Map<String, String> map = beswainingrealinfomapper.getNoRecoverCount(f_operation);
            returnObject.setData(map.get("F_RECOVER_COUNT"));
            returnObject.setStatus("1");
        } catch (Exception e) {
            returnObject.setStatus("0");
        }
        return returnObject;
    }

    /**
     *
     * @Description: DDC告警处理
     *
     * @auther: wanghongjie
     * @date: 10:02 2020/7/27
     * @param: [msg]
     * @return: void
     *
     */
    public <T>void DDCalarmHandle(ReceiveMsg<List<T>> msg){
        Map<String,Object> PointMap = new HashMap<>();
        String tabName = "";

        List<T> pointList = msg.getData();

        Integer alarmValue = null;//闭合或者断开
        Integer alarmFaultValue = null;//故障
        Boolean alarmYesOrNo = false;//是否有报警消息插入到实时报警表,初始值为false

        for (T pointData : pointList){

            Integer pointId;
            Integer pointValue;
            String ip = msg.getIp();

            PointMap.put("IP",ip);


            if (pointData instanceof PointDataDDC)
            {
                pointId = ((PointDataDDC)pointData).getId();//获取点位的id
                pointValue = ((PointDataDDC)pointData).getValue();//获取点位的value
            }else if (pointData instanceof PointDataLDC)
            {
                pointId = ((PointDataLDC)pointData).getId();//获取点位的id
                pointValue = ((PointDataLDC)pointData).getValue();//获取点位的value
            }else
            {
                continue;
            }

            if (pointId.equals(0)) {
                continue;
            }
            //根据id查询是否有这个点位
//            Map<String,Object> sbpzStructPointMap = besSubAlarmMapper.sbpzStructPointMap(pointId);

            BESSbPzStruct sbpzStructPointMap = sbPzStructCache.getCachedElementById(String.valueOf(pointId));

            if (null == sbpzStructPointMap){

                continue;
            }

//            tabName = (String) sbpzStructPointMap.get("F_NODE_TABLE");
            BESSbTreeNodeType sbTreeNodeType = sbTreeNodeTypeCache.getCachedElement(sbpzStructPointMap.getF_type());
            tabName = sbTreeNodeType.getF_node_table();

            if (tabName == null || tabName.isEmpty()) {
                continue;
            }

            if (tabName.equals("BES_VPOINT")){
                //查询虚点点位在相应表中的数据
//                PointMap= besSubAlarmMapper.selectVPointMap(String.valueOf(pointId));
                BesVirtualPoint besVirtualPoint = VirtualPointCache.getCachedElementBySbId(String.valueOf(pointId));
                if (besVirtualPoint == null) {
                    continue;
                }
                PointMap.put("F_NODE_TYPE",besVirtualPoint.getF_node_type());
                PointMap.put("F_CLOSE_STATE",besVirtualPoint.getF_close_state());
                PointMap.put("F_ENABLED",besVirtualPoint.getF_enabled());
                PointMap.put("F_ALARM_ENABLE",besVirtualPoint.getF_alarm_enable());
                PointMap.put("F_ALARM_TYPE",besVirtualPoint.getF_alarm_type());
                PointMap.put("F_ALARM_PRIORITY",besVirtualPoint.getF_alarm_priority());
                PointMap.put("F_FAULT_STATE",besVirtualPoint.getF_fault_state());
                PointMap.put("F_ENERGYSTATICS",besVirtualPoint.getF_energystatics());
                PointMap.put("F_SYS_NAME_OLD",besVirtualPoint.getF_sys_name_old());
                PointMap.put("F_ACCURACY",besVirtualPoint.getF_accuracy());
                PointMap.put("F_HIGH_LIMIT",besVirtualPoint.getF_high_limit());
                PointMap.put("F_LOW_LIMIT",besVirtualPoint.getF_low_limit());
                PointMap.put("F_NICK_NAME",besVirtualPoint.getF_nick_name());
                PointMap.put("F_YQBH",besVirtualPoint.getF_yqbh());

            }else {
                //查询实点点位在相应表中的数据
//                PointMap= besSubAlarmMapper.selectPointMap(tabName,String.valueOf(pointId));
                if (tabName.equals("BES_ANALOG_INPUT")) {
                    BesAiPoint besAiPoint = aiPointCache.getCachedElementBySbId(String.valueOf(pointId));
                    if (besAiPoint == null) {
                        continue;
                    }
                    PointMap.put("F_NODE_TYPE",besAiPoint.getF_node_type());
                    PointMap.put("F_ENABLED",besAiPoint.getF_enabled());
                    PointMap.put("F_ALARM_ENABLE",besAiPoint.getF_alarm_enable());
                    PointMap.put("F_ALARM_TYPE",besAiPoint.getF_alarm_type());
                    PointMap.put("F_ALARM_PRIORITY",besAiPoint.getF_alarm_priority());
                    PointMap.put("F_ENERGYSTATICS",besAiPoint.getF_energystatics());
                    PointMap.put("F_SYS_NAME_OLD",besAiPoint.getF_sys_name_old());
                    PointMap.put("F_ACCURACY",besAiPoint.getF_accuracy());
                    PointMap.put("F_HIGH_LIMIT",besAiPoint.getF_high_limit());
                    PointMap.put("F_LOW_LIMIT",besAiPoint.getF_low_limit());
                    PointMap.put("F_NICK_NAME",besAiPoint.getF_nick_name());
                    PointMap.put("F_YQBH",besAiPoint.getF_yqbh());
                } else if (tabName.equals("BES_ANALOG_OUPUT")) {
                    BesAoPoint besAoPoint = aoPointCache.getCachedElementBySbId(String.valueOf(pointId));
                    if (besAoPoint == null) {
                        continue;
                    }
                    PointMap.put("F_NODE_TYPE",besAoPoint.getfNodeType());
                    PointMap.put("F_ENABLED",besAoPoint.getfEnabled());
                    PointMap.put("F_ALARM_ENABLE",besAoPoint.getfAlarmEnable());
                    PointMap.put("F_ALARM_TYPE",besAoPoint.getfAlarmType());
                    PointMap.put("F_ALARM_PRIORITY",besAoPoint.getfAlarmPriority());
                    PointMap.put("F_SYS_NAME_OLD",besAoPoint.getfSysNameOld());
                    PointMap.put("F_ACCURACY",besAoPoint.getfAccuracy());
                    PointMap.put("F_HIGH_LIMIT",besAoPoint.getfHighLimit());
                    PointMap.put("F_LOW_LIMIT",besAoPoint.getfLowLimit());
                    PointMap.put("F_NICK_NAME",besAoPoint.getfNickName());
                    PointMap.put("F_YQBH",besAoPoint.getfYqbh());
                } else if (tabName.equals("BES_DIGIT_INPUT")) {
                    BesDiPoint besDiPoint = diPointCache.getCachedElementBySbId(String.valueOf(pointId));
                    if (besDiPoint == null) {
                        continue;
                    }
                    PointMap.put("F_NODE_TYPE",besDiPoint.getfNodeType());
                    PointMap.put("F_CLOSE_STATE",besDiPoint.getfCloseState());
                    PointMap.put("F_ENABLED",besDiPoint.getfEnabled());
                    PointMap.put("F_ALARM_ENABLE",besDiPoint.getfAlarmEnable());
                    PointMap.put("F_ALARM_TYPE",besDiPoint.getfAlarmType());
                    PointMap.put("F_ALARM_PRIORITY",besDiPoint.getfAlarmPriority());
                    PointMap.put("F_SYS_NAME_OLD",besDiPoint.getfSysNameOld());
                    PointMap.put("F_FAULT_STATE",besDiPoint.getfFaultState());
                    PointMap.put("F_NICK_NAME",besDiPoint.getfNickName());
                    PointMap.put("F_YQBH",besDiPoint.getfYqbh());
                } else if (tabName.equals("BES_DIGIT_OUPUT")) {
                    BesDoPoint besDoPoint = doPointCache.getCachedElementBySbId(String.valueOf(pointId));
                    if (besDoPoint == null) {
                        continue;
                    }
                    PointMap.put("F_NODE_TYPE",besDoPoint.getfNodeType());
                    PointMap.put("F_CLOSE_STATE",besDoPoint.getfCloseState());
                    PointMap.put("F_ENABLED",besDoPoint.getfEnabled());
                    PointMap.put("F_ALARM_ENABLE",besDoPoint.getfAlarmEnable());
                    PointMap.put("F_ALARM_TYPE",besDoPoint.getfAlarmType());
                    PointMap.put("F_ALARM_PRIORITY",besDoPoint.getfAlarmPriority());
                    PointMap.put("F_SYS_NAME_OLD",besDoPoint.getfSysNameOld());
                    PointMap.put("F_FAULT_STATE",besDoPoint.getfFaultState());
                    PointMap.put("F_NICK_NAME",besDoPoint.getfNickName());
                    PointMap.put("F_YQBH",besDoPoint.getfYqbh());
                }

            }

            if (null == PointMap || PointMap.isEmpty()){
                continue;

            }

            if (PointMap.get("F_NODE_TYPE").equals("7") || PointMap.get("F_NODE_TYPE").equals("6")
                    || PointMap.get("F_NODE_TYPE").equals("13") || PointMap.get("F_NODE_TYPE").equals("12")){//VDO,VDI,DI,DO点

                if (PointMap.get("F_CLOSE_STATE").equals("2")) {//闭合断开都不选的情况下,默认为1
                    alarmValue = 1;
                }else if (PointMap.get("F_CLOSE_STATE").equals("1")){//断开报警
                    alarmValue = 0;
                }else if (PointMap.get("F_CLOSE_STATE").equals("0")){//闭合报警
                    alarmValue = 255;
                }

                if (PointMap.get("F_ENABLED").equals("1") && PointMap.get("F_ALARM_ENABLE").equals("1")
                        && !PointMap.get("F_ALARM_TYPE").equals("0")){//使能状态是使能,报警使能状态是使能,报警类型是报警状态
                    //暂时不考虑增强报警,只编写标准报警
                    if (PointMap.get("F_ALARM_TYPE").equals("1")){//标准报警

                        if (PointMap.get("F_CLOSE_STATE").equals("2")) {//闭合状态都不选择的情况下
                            if (PointMap.get("F_FAULT_STATE").equals("1")) {//故障状态选择是的情况下,对是否符合故障报警做判断
                                if (alarmValue.equals(pointValue)) {
                                    alarmHandling(PointMap,pointId,pointValue,tabName);
                                }
                            }
                        } else if (pointValue.equals(alarmValue)){

                            alarmHandling(PointMap,pointId,pointValue,tabName);
                        }

                        if (PointMap.get("F_FAULT_STATE").equals("1")){
                            alarmFaultValue = 100;
                            if (pointValue.equals(alarmFaultValue)){

                                alarmHandling(PointMap,pointId,pointValue,tabName);
                            }
                        }

                    }else if (PointMap.get("F_ALARM_TYPE").equals("2")){//增强报警
                        continue;
                    }

                }else {
                    continue;
                }

            }else if(PointMap.get("F_NODE_TYPE").equals("5") || PointMap.get("F_NODE_TYPE").equals("4")
                    || PointMap.get("F_NODE_TYPE").equals("11") || PointMap.get("F_NODE_TYPE").equals("10")){//VAO,VAI,AI,AO点

                //如果点位的能耗统计开启,则走能耗报警逻辑
                if (PointMap.get("F_NODE_TYPE").equals("4") || PointMap.get("F_NODE_TYPE").equals("10")) {
                    if (PointMap.get("F_ENERGYSTATICS").equals("0")) {

                            DIPointAlarmHandle(pointId,(String) PointMap.get("F_SYS_NAME_OLD"),alarmYesOrNo,pointValue);

                    }
                }

                if (PointMap.get("F_ENABLED").equals("1") && PointMap.get("F_ALARM_ENABLE").equals("1")
                        && !PointMap.get("F_ALARM_TYPE").equals("0")) {//使能状态是使能,报警使能状态是使能,报警类型是报警状态

                    if (PointMap.get("F_ALARM_TYPE").equals("1")){

                        //AO,AI点的值需要进行精度换算
                        Integer accuracyNum = Integer.valueOf((String) PointMap.get("F_ACCURACY"));//获取精度
                        Double valueDouble = pointValue/Math.pow(10, accuracyNum);//获取精度转换后的实时值

                        float nomore = Float.parseFloat(String.valueOf(PointMap.get("F_HIGH_LIMIT")));//最大值
                        float noless = Float.parseFloat(String.valueOf(PointMap.get("F_LOW_LIMIT")));//最小

                        if (valueDouble > nomore || valueDouble < noless){//当前值大于高限或者小于底限的时候

                            alarmHandling(PointMap,pointId,pointValue,tabName);
                        }
                    }
                }
            }

        }
    }

    /**
     *
     * @Description: DI点判断是否把它当成电表统计,如果当成电表统计并且在支路报警配置当中添加当前AI点,则对当前AI的值做出是否符合报警
     *
     * @auther: wanghongjie
     * @date: 13:42 2020/10/9
     * @param: [pointId, alarmYesOrNo, pointValue]
     * @return: void
     *
     */
    private void DIPointAlarmHandle(Integer pointId,String f_sys_name_old,Boolean alarmYesOrNo,Integer pointValue) {

//        List<Map<String,Object>> alarmtTypeList = besSubAlarmMapper.selectId(String.valueOf(f_sys_name_old));//查询支路报警类型
        List<BesBranchConfAlarmConfiguration> alarmtTypeList =  besBranchConfAlarmConfigurationCache.getCachedElement(f_sys_name_old);
        if (alarmtTypeList == null || alarmtTypeList.size() == 0){
            return;
        }else {
            for (BesBranchConfAlarmConfiguration alarmList : alarmtTypeList ){
                //判断当前支路报警配置信息的使能状态
                if (alarmList.getfActive().equals("1")) {
                    continue;
                }
                List ammeterList = new ArrayList();
                String name = alarmList.getfNymc();//能源名称
                String F_ZLBH = alarmList.getfZlbh();//支路编号
//                List<Map<String, Object>> list = besSubAlarmMapper
//                        .SelectAlarmParmeSub((String) alarmList.get("F_ALERTID"));//查询报警规则所用到的电表电能参数

                List<BesBranchConfParameter> list = branchConfParameterCache.getCachedElement(alarmList.getfAlertid());//查询报警规则所用到的电表电能参数

                if (list == null) {
                    continue;
                }
                String url = "";

                for(int j = 0;j < list.size(); j++){//遍历报警规则用到的电能参数
                    BesBranchConfParameter dtoParmeId = list.get(j);//将报警规则和电表的SBID获取到

                    if (list.get(j).getfAmmsysName().equals(String.valueOf(f_sys_name_old))){//如果报警规则所包含的电表里面有当前电表

                        ammeterList.add(pointValue);

                    }else {//如果报警规则所包含的电表里面没有当前电表,查询数据库中当前电能参数的值

                        if (!dtoParmeId.getfElednbh().equals("10000")) {//如果电能编号不是AI点的电能编号,则说明是电表的电能编号
//                            Map<String, Object> dtoData = besSubAlarmMapper
//                                    .SelectAlarmParmeData(dtoParmeId.getfAmmsysName(),dtoParmeId.getfElednbh());


                            Map<String, OriginalData> dtoData =  originalDataCache.getCachedElementBySysName(dtoParmeId.getfAmmsysName());
                            if (G4Utils.isNotEmpty(dtoData)) {
                                if (dtoData.containsKey(dtoParmeId.getfElednbh())) {
                                    ammeterList.add(dtoData.get(dtoParmeId.getfElednbh()).getfData());
                                } else {
                                    ammeterList.add(0);
                                }
                            }else {//如果电表原始数据表中没有当前电表编号的数据,则默认添加0
                                ammeterList.add(0);
                            }
                        } else {
                            //如果查询电表原始数据表中,根据名称和电能编号查询不到数据,则说明当前名称是DDC的AI点名称
                            // 如果是AI点的电表,则向电表数据表 bes_calculate_data 查询当前AI点的数据
//                            Map<String,Object> calculateData = besSubAlarmMapper.selectCalculateDataByAIPoint((String)dtoParmeId.get("F_AMMSYS_NAME"));
                            BESSbPzStruct calculateData = sbPzStructCache.getCachedElementBySysNameOld(dtoParmeId.getfAmmsysName());
                            if (G4Utils.isNotEmpty(calculateData)) {
                                ammeterList.add(calculateData.getF_init_val());
                            } else {
                                ammeterList.add(0);
                            }
                        }

                    }

                }
                float nData = 0.0f;
                String operator = alarmList.getfOperator();//查询配置的报警公式
                for(int k = ammeterList.size() ; k > 0; k--){
                    String strData = String.valueOf(ammeterList.get(k - 1)) ;
                    String strOperator = "$" + k;
                    operator = operator.replace(strOperator,strData);
                }
                try {
                    nData = Float.parseFloat(jse.eval(operator).toString());
                } catch (ScriptException e) {
                    e.printStackTrace();
                }

                Date now = new Date();
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//可以方便地修改日期格式
                String strTime = dateFormat.format(now);
                String id = UUIDUtil.getRandom32BeginTimePK();
                //要添加的报警信息
                BesWainingInfo besWaringInfo = new BesWainingInfo();
                besWaringInfo.setFYqbh(alarmList.getfYqbh());
                besWaringInfo.setFGuid(id);
                besWaringInfo.setFLoction(name + "/" + url + "(" + F_ZLBH + ")" + "(" + alarmList.getfAlertname()+ ")");//报警位置
                besWaringInfo.setFAlarmName(alarmList.getfAlertname());//报警名称
                besWaringInfo.setFActualValue(String.valueOf(nData));//实际值
                besWaringInfo.setFATime(strTime);//报警时间
                besWaringInfo.setFOpearation("1");//是否处理
                besWaringInfo.setFType(alarmList.getfType());//信息类型  (1:全部  2:设备报警 3:支路参数报警 4:火警摄像机报警 5:电气火灾报警 6:市电检测报警 7:水浸监测报警 8:烟雾监测报警 9:环境温湿度报警)
                besWaringInfo.setFLevel(alarmList.getfAlertgrade());//报警等级

                if (alarmList.getfRangetype().equals("0")) {
                    float nomore = Float.parseFloat(alarmList.getfNomore());
                    if (nData > nomore) {
                        alarmYesOrNo = true;
                        besWaringInfo.setFPlanValue(String.valueOf(nomore));//计划值
                        besWaringInfo.setFTipInfo(besWaringInfo.getFAlarmName()+"实际参数大于计划值");//提示信息
                        //					calculateCurrentEnergyThread.besWainingRealInfoMapper.insertWarningInfo(besWaringInfo);
                        //判断是否存在相同的未处理报警
                        AlarmUtil.insertWarningRealAmmeter(besWaringInfo);
                        LEMSUtil.pushAlarm(alarmList.getfYqbh());
                    }
                } else if (alarmList.getfRangetype().equals("1")) {
                    float noless = Float.parseFloat(alarmList.getfNoless());
                    if(nData < noless){
                        alarmYesOrNo = true;
                        besWaringInfo.setFPlanValue(String.valueOf(noless));//计划值
                        besWaringInfo.setFTipInfo(besWaringInfo.getFAlarmName()+"实际参数小于计划值");//提示信息
                        //					calculateCurrentEnergyThread.besWainingRealInfoMapper.insertWarningInfo(besWaringInfo);
                        //判断是否存在相同的未处理报警
                        AlarmUtil.insertWarningRealAmmeter(besWaringInfo);
                        LEMSUtil.pushAlarm(alarmList.getfYqbh());
                    }
                } else if (alarmList.getfRangetype().equals("2")) {
                    float nomore = Float.parseFloat(alarmList.getfNomore());//最大值
                    float noless = Float.parseFloat(alarmList.getfNoless());//最小
                    if(nData>nomore||nData<noless){
                        alarmYesOrNo = true;
                        besWaringInfo.setFPlanValue(String.valueOf(noless+"--"+nomore));//计划值
                        besWaringInfo.setFTipInfo(besWaringInfo.getFAlarmName()+"实际参数不在计划值范围");//提示信息
                        //					calculateCurrentEnergyThread.besWainingRealInfoMapper.insertWarningInfo(besWaringInfo);
                        //判断是否存在相同的未处理报警
                        AlarmUtil.insertWarningRealAmmeter(besWaringInfo);
                        LEMSUtil.pushAlarm(alarmList.getfYqbh());
                    }
                } else {
                    float equal = Float.parseFloat(alarmList.getfEqual());//最小
                    if(equal != nData){
                        alarmYesOrNo = true;
                        besWaringInfo.setFPlanValue(alarmList.getfEqual());//计划值
                        besWaringInfo.setFTipInfo(besWaringInfo.getFAlarmName()+"实际参数不等于计划值");//提示信息
                        //					calculateCurrentEnergyThread.besWainingRealInfoMapper.insertWarningInfo(besWaringInfo);
                        //判断是否存在相同的未处理报警
                        AlarmUtil.insertWarningRealAmmeter(besWaringInfo);
                        LEMSUtil.pushAlarm(alarmList.getfYqbh());
                    }
                }

            }
        }
        String f_operation = "1";
        Map<String,Object> msgMap = new HashedMap();

//        PageInfo<Object> page = getAlarmWarnInfoByRecoverState(f_operation);//查询前20条的报警消息
        if (alarmYesOrNo == true) {//如果有报警消息插入实时报警表,则将报警的条数传到前端页面上,并发出报警声音
            ISSPReturnObject returnObject = getNoRecoverCount(f_operation);//查询报警的条数

            msgMap.put("noRecoverCount",returnObject.getData());

            // 推送消息到web客户端
            WebSocketService.postEvent( WebSocketEvent.ALARM, msgMap);
        }
    }

    /**
     *
     * @Description: 报警统一处理
     *
     * @auther: wanghongjie
     * @date: 11:55 2020/7/27
     * @param: [pointMap, pointId, pointValue,tabName]
     * @return: void
     *
     */
    public void alarmHandling(Map<String, Object> PointMap, Integer pointId, Integer pointValue,String tabName){
        String alarmName        = "";//报警名称
        String moduleName       = "";//模块名称
        String ip               = "";//ip地址
        String DDCName          = "";//DDC名称
        Boolean alarmYesOrNo    = false;//是否有报警消息插入到实时报警表,初始值为false
        String pointName        = (String) PointMap.get("F_NICK_NAME");//点位名称
        String leave            = "";
        //要添加的报警信息
        BesWainingInfo besWaringInfo = new BesWainingInfo();

        //查询点位所属的模块名称
        if (tabName.equals("BES_VPOINT")){
            moduleName = "虚点";
        }else {
//            moduleName = besSubAlarmMapper.selectModuleName(pointId);
            BESSbPzStruct sbpzStructPointMap = sbPzStructCache.getCachedElementById(String.valueOf(pointId));
            if (sbpzStructPointMap == null) {
                return;
            }
            moduleName = sbpzStructPointMap.getF_sys_name_old();
        }

        //查询点位所属的控制器
//        Map<String,Object> DDCPointMap = besSubAlarmMapper.selectDDCPointMap(pointId);
        BesDdc DDCPointMap = ddcCache.getDdcByChannelId((String) PointMap.get("IP"));

        if (DDCPointMap == null ){
            return;
        }else {
            ip = DDCPointMap.getfChannelId();
            DDCName = DDCPointMap.getfSysNameOld();
        }

        Date now = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//可以方便地修改日期格式
        String strTime = dateFormat.format(now);
        String id = UUIDUtil.getRandom32BeginTimePK();
        besWaringInfo.setFYqbh((String) PointMap.get("F_YQBH"));
        besWaringInfo.setFGuid(id);
        besWaringInfo.setFLoction("控制器" + ":" + DDCName + "," + moduleName +"(" +ip + ")" + "(" +PointMap.get("F_SYS_NAME_OLD") + ")");//报警位置(控制器:   ,模块:    (ip))
        besWaringInfo.setFATime(strTime);//报警时间
        besWaringInfo.setFOpearation("1");//是否处理
        besWaringInfo.setFType("2");//信息类型  1:全部  2:设备报警  3:支路参数报警

        if (PointMap.get("F_ALARM_PRIORITY").equals("0"))  {
            leave = "3";
        } else if  (PointMap.get("F_ALARM_PRIORITY").equals("1"))  {
            leave = "2";
        } else if  (PointMap.get("F_ALARM_PRIORITY").equals("2"))  {
            leave = "1";
        }
            besWaringInfo.setFLevel(leave);//报警等级

        if (PointMap.get("F_NODE_TYPE").equals("7") || PointMap.get("F_NODE_TYPE").equals("6")
                || PointMap.get("F_NODE_TYPE").equals("13") || PointMap.get("F_NODE_TYPE").equals("12")){//VDO,VDI,DI,DO点
            if(pointValue.equals(255)){
                alarmName = "闭合报警";
            }else if (pointValue.equals(0)){
                alarmName = "断开报警";
            }else {
                if (PointMap.get("F_FAULT_STATE").equals("1")) {//如果故障报警选择是的情况下,那么下位机上来的数据返回的是故障状态做报警提示,故障报警选择否的情况下,则不对数据做报警判断
                    alarmName = "故障报警";
                } else {
                    return;
                }

            }
            alarmYesOrNo = true;
            besWaringInfo.setFActualValue(String.valueOf(pointValue));//实际值
            besWaringInfo.setFPlanValue(String.valueOf(pointValue));//计划值
            besWaringInfo.setFAlarmName(alarmName);//计划值
            besWaringInfo.setFTipInfo(pointName + "(" + alarmName + ")");//提示信息

            //判断是否存在相同的未处理报警
            AlarmUtil.insertWarningRealAmmeter(besWaringInfo);

        }else if (PointMap.get("F_NODE_TYPE").equals("5") || PointMap.get("F_NODE_TYPE").equals("4")
                || PointMap.get("F_NODE_TYPE").equals("11") || PointMap.get("F_NODE_TYPE").equals("10")){//VAO,VAI,AI,AO点

            //AO,AI点的值需要进行精度换算
            Integer accuracyNum = Integer.valueOf((String) PointMap.get("F_ACCURACY") );//获取精度
            Double valueDouble = pointValue/Math.pow(10, accuracyNum);//获取精度转换后的实时值

            float nomore = Float.parseFloat(String.valueOf(PointMap.get("F_HIGH_LIMIT")));//最大值
            float noless = Float.parseFloat(String.valueOf(PointMap.get("F_LOW_LIMIT")));//最小

            besWaringInfo.setFActualValue(String.valueOf(valueDouble));//实际值

            if(valueDouble > nomore ){
                alarmYesOrNo = true;
                besWaringInfo.setFPlanValue(String.valueOf(noless+"--"+nomore));//计划值
                besWaringInfo.setFAlarmName("高限报警");
                besWaringInfo.setFTipInfo(pointName+"(实际参数大于计划值)");//提示信息

                //判断是否存在相同的未处理报警
                AlarmUtil.insertWarningRealAmmeter(besWaringInfo);
            }else if (valueDouble < noless ){
                alarmYesOrNo = true;
                besWaringInfo.setFPlanValue(String.valueOf(noless+"--"+nomore));//计划值
                besWaringInfo.setFAlarmName("低限报警");
                besWaringInfo.setFTipInfo(pointName+"(实际参数小于计划值)");//提示信息

                //判断是否存在相同的未处理报警
                AlarmUtil.insertWarningRealAmmeter(besWaringInfo);
            }
        }

        String f_operation = "1";
        Map<String,Object> msgMap = new HashedMap();

        if (alarmYesOrNo == true) {//如果有报警消息插入实时报警表,则将报警的条数传到前端页面上,并发出报警声音
            ISSPReturnObject returnObject = getNoRecoverCount(f_operation);//查询报警的条数

            msgMap.put("noRecoverCount", returnObject.getData());

            // 推送消息到web客户端
            WebSocketService.postEvent(WebSocketEvent.ALARM, msgMap);
        }
    }
}
