package com.efounder.task;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.core.common.ISSPReturnObject;
import com.efounder.JEnterprise.common.util.UUIDUtil;
import com.efounder.JEnterprise.domain.SysJob;
import com.efounder.JEnterprise.initializer.SbPzStructCache;
import com.efounder.JEnterprise.mapper.basedatamanage.energyinformation.BESStrategyMapper;
import com.efounder.JEnterprise.mapper.dataAnalysises.BesBranchDataMapper;
import com.efounder.JEnterprise.mapper.quartz.SysJobPlanMapper;
import com.efounder.JEnterprise.model.basedatamanage.eqmanage.BESSbPzStruct;
import com.efounder.JEnterprise.model.excelres.ExcelReturn;
import com.efounder.JEnterprise.service.basedatamanage.eqmanage.BESSbdyService;
import com.efounder.JEnterprise.service.basedatamanage.eqmanage.EnerEquipmentService;
import com.efounder.util.StringUtils;
import com.efounder.util.emailConfig.IdcEmailConfig;
import com.efounder.util.test.EmailService;
import com.framework.common.utils.ExcelUtil;
import com.google.gson.JsonObject;
import org.dom4j.Branch;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.xml.rpc.ServiceException;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.rmi.RemoteException;
import java.text.SimpleDateFormat;
import java.util.*;

import static com.framework.common.utils.xmlprocessor.utils.XmlUtils.makeWritableDirectoryIfNotExist;

/**
 * 定时任务调度测试
 *
 * @author sunzhiyuan
 * @Data 2021/1/20 10:18
 */
@Component("besTask")
public class BesExecute {

    /**
     * 文件存放位置
     */
    @Value("${data-centre.email-file-storage-location}")
    private String emailFilePath;

    /**
     * 发件人配置
     */
    @Value("${data-centre.email-account}")
    private String emailAccount;
    @Value("${data-centre.email-password}")
    private String emailPassWord;
    @Value("${data-centre.email-server-host}")
    private String emailServerHost;

    @Autowired
    private SysJobPlanMapper sysJobPlanMapper;

    @Autowired
    private BesBranchDataMapper besBranchDataMapper;

    //设备树缓存
    @Autowired
    private SbPzStructCache sbPzStructCache;

    @Autowired
    private BESSbdyService besSbdyService;

    @Autowired
    private EnerEquipmentService enerEquipmentService;

    @Autowired
    private BESStrategyMapper besStrategyMapper;

    @Autowired
    EmailService emailService;

    public void besMultipleParams(String s, Boolean b, Long l, Double d, Integer i) {
        System.out.println(StringUtils.format("执行多参方法： 字符串类型{}，布尔类型{}，长整型{}，浮点型{}，整形{}", s, b, l, d, i));
    }

    public void besParams(String params) {
        System.out.println("执行有参方法：" + params);
    }

    public void sysNoParams() {
        System.out.println("执行无参方法");
    }

    //执行时间类型的定时任务
    public void executePlanTaskInfo(SysJob sysJob) {

        System.out.println("计划任务走了***********************************:sysJob.name" + sysJob.getJobName());
        List<Map<String, Object>> infoList = new ArrayList<>();

        String planId = sysJob.getPlanId();

        Map<String, Object> planMap = sysJobPlanMapper.queryPlanModeIdAndSceneId(planId);
        if (planMap == null) {
            return;
        }

        if ("0".equals(planMap.get("f_invoke"))) {//是否执行(1执行 0不执行)
            return;
        }

        Map<String, Object> taskInfo = sysJobPlanMapper.queryTimeTaskList(planId);

        if (null == taskInfo) {
            return;
        } else {

            String taskId = (String) taskInfo.get("f_id");

            String timename = (String) taskInfo.get("f_timename");

            planMap.put("taskId", taskId);

            planMap.put("timename", timename);

            infoList.add(planMap);

            executeTimeTaskInfo(infoList);
        }
    }

    public void executeTimeTaskInfo(List<Map<String, Object>> infoList) {

        for (int i = 0; i < infoList.size(); i++) {

            Map<String, Object> map = infoList.get(i);

            String modeId = (String) map.get("f_modeInfoId");

            List<Map<String, Object>> pointList = sysJobPlanMapper.selectPointInfomationByModeId(modeId);

            if (pointList == null) {
                return;
            }

            JudgePointType(pointList, map);
        }
    }

    //因为会出现并发情况 存储时间数据 所以不能单个的存储数据
    public void JudgePointType(List<Map<String, Object>> pointList, Map map) {
        Map<String, Object> typeDiPointMap = new HashMap<>();

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        String F_CRDATE = simpleDateFormat.format(new Date());

        for (int i = 0; i < pointList.size(); i++) {

            String pointId = (String) pointList.get(i).get("f_pointId");

            BESSbPzStruct sbPzStruct = sbPzStructCache.getCachedElementById(pointId);
            if (sbPzStruct == null) {
                continue;
            }

            Integer planId = Integer.valueOf((String) map.get("f_id"));

            String taskId = (String) map.get("taskId");

            String timename = (String) map.get("timename");

            typeDiPointMap.put("F_CRDATE", F_CRDATE);

            typeDiPointMap.put("F_PLANID", planId);

            typeDiPointMap.put("F_TASKID", taskId);

            typeDiPointMap.put("F_TIMENAME", timename);

            typeDiPointMap.put("F_GUID", "0");

            typeDiPointMap.put("F_STRUCT_ID", sbPzStruct.getF_id());

            typeDiPointMap.put("F_SYS_NAME_OLD", sbPzStruct.getF_sys_name_old());

            typeDiPointMap.put("F_SYS_NAME", sbPzStruct.getF_sys_name());

            typeDiPointMap.put("F_INIT_VAL", sbPzStruct.getF_init_val());

            InsertDiPointHistoryInfo(typeDiPointMap);

        }
    }

    public void InsertDiPointHistoryInfo(Map<String, Object> typeDiPointMap) {

        String F_ID = UUIDUtil.getRandom32BeginTimePK();

        typeDiPointMap.put("F_ID", F_ID);

        sysJobPlanMapper.InsertPointHistoryInfo(typeDiPointMap);

    }

    //执行定时同步设备树
    public void executeTimeTaskSyncInfo(SysJob sysJob) throws UnsupportedEncodingException, RemoteException, ServiceException {

        System.out.println("定时同步设备树走了***********************************:sysJob.name" + sysJob.getJobName());
        List<Map<String, Object>> infoList = new ArrayList<>();

        String syncId = sysJob.getSyncId();

        Map<String, Object> syncMap = sysJobPlanMapper.selectTimeTaskSyncInfomation(syncId);
        if (syncMap == null) {
            return;
        }

        if ("0".equals(syncMap.get("f_status"))) {//是否执行(1执行 0不执行)
            return;
        }

        List<Map<String, Object>> sbList = sysJobPlanMapper.queryTimeTaskSyncSbList(syncId);

        if (null == sbList) {
            return;
        } else {
            for (Map<String, Object> sbInfo : sbList) {
                ISSPReturnObject returnObject = new ISSPReturnObject();

                String fPointType = sbInfo.get("f_point_type").toString();
                String fSysName = sbInfo.get("f_point_name").toString();

                //点位类型 2:DDC  3:IP路由器  26:能耗采集
                //判断点位类型 DDC和IP路由器用的一个同步接口 synchronizeDDC
                if ("3".equals(fPointType) || "2".equals(fPointType)) {
                    JSONObject object = new JSONObject();
                    object.put("old_f_sys_name", fSysName);
                    object.put("f_type", fPointType);

                    returnObject = besSbdyService.synchronizeDDC(object);

                } else if ("26".equals(fPointType)) {
                    //能耗采集用的同步接口 synEnergyCollector
                    returnObject = enerEquipmentService.synEnergyCollector(fSysName);

                }

                System.out.println("定时同步设备树任务: " + sysJob.getJobName() + "------------>" + fSysName + returnObject.getMsg());
            }
        }
    }

    //定时发送报表
    public void executeStrategy(String strategyId) throws UnsupportedEncodingException, RemoteException, ServiceException {
        Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        System.out.println("定时发送报表走了***********************************: strategyId " + strategyId + dateFormat.format(date));


        //查询配置策略信息
        Map<String, Object> strategyInfo = besStrategyMapper.queryStrategyInfo(strategyId);

        //获取时间范围
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Calendar c = Calendar.getInstance();

        String nowStart = "";
        String nowEnd = "";
        String lastStart = "";
        String lastEnd = "";
        String f_range = "";

        if ("1".equals(strategyInfo.get("f_range"))) {
            f_range = "本天";

            //本天
            nowStart = format.format(c.getTime()) + " 00:00:00";
            nowEnd = format.format(c.getTime()) + " 23:59:59";

            //昨天
            c.add(Calendar.DATE, -1);
            lastStart = format.format(c.getTime()) + " 00:00:00";
            ;
            lastEnd = format.format(c.getTime()) + " 23:59:59";

        } else if ("2".equals(strategyInfo.get("f_range"))) {
            f_range = "本周";

            //本周
            c.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
            nowStart = format.format(c.getTime()) + " 00:00:00";

            Calendar ca = Calendar.getInstance();
            ca.setFirstDayOfWeek(Calendar.MONDAY);
            ca.set(Calendar.DAY_OF_WEEK, ca.getFirstDayOfWeek() + 6); // Sunday
            nowEnd = format.format(ca.getTime()) + " 23:59:59";

            //上周
            Calendar cal = Calendar.getInstance();
            cal.add(Calendar.DATE, -7);
            cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
            lastStart = format.format(cal.getTime()) + " 00:00:00";

            cal.add(Calendar.DATE, 7);
            cal.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
            lastEnd = format.format(cal.getTime()) + " 23:59:59";

        } else if ("3".equals(strategyInfo.get("f_range"))) {
            f_range = "本月";
            //本月第一天
            c.set(Calendar.DAY_OF_MONTH, 1);//设置为1号,当前日期既为本月第一天
            nowStart = format.format(c.getTime()) + " 00:00:00";
            //本月最后一天
            Calendar ca = Calendar.getInstance();
            ca.set(Calendar.DAY_OF_MONTH, ca.getActualMaximum(Calendar.DAY_OF_MONTH));
            nowEnd = format.format(ca.getTime()) + " 23:59:59";

            //上月
            Calendar cal = Calendar.getInstance();
            cal.add(Calendar.MONTH, -1);    //得到前一个月
            cal.set(Calendar.DAY_OF_MONTH, 1);//设置为1号,当前日期既为本月第一天
            lastStart = format.format(cal.getTime()) + " 00:00:00";

            Calendar cale = Calendar.getInstance();
            cale.add(Calendar.MONTH, -1);    //得到前一个月
            cale.set(Calendar.DAY_OF_MONTH, cale.getActualMaximum(Calendar.DAY_OF_MONTH));
            lastEnd = format.format(cale.getTime()) + " 23:59:59";

        } else if ("4".equals(strategyInfo.get("f_range"))) {
            f_range = "本季";
            //本季度开始时间
            int currentMonth = c.get(Calendar.MONTH) + 1;
            if (currentMonth >= 1 && currentMonth <= 3) {
                c.set(Calendar.MONTH, 0);
            } else if (currentMonth >= 4 && currentMonth <= 6) {
                c.set(Calendar.MONTH, 3);
            } else if (currentMonth >= 7 && currentMonth <= 9) {
                c.set(Calendar.MONTH, 4);
            } else if (currentMonth >= 10 && currentMonth <= 12) {
                c.set(Calendar.MONTH, 9);
            }
            c.set(Calendar.DATE, 1);
            nowStart = format.format(c.getTime()) + " 00:00:00";

            //本季度结束时间
            Calendar ca = Calendar.getInstance();
            //计算季度数：由于月份从0开始，即1月份的Calendar.MONTH值为0,所以计算季度的第三个月份只需 月份 / 3 * 3 + 2
            ca.set(Calendar.MONTH, (((int) ca.get(Calendar.MONTH)) / 3) * 3 + 2);
            ca.set(Calendar.DAY_OF_MONTH, ca.getActualMaximum(Calendar.DAY_OF_MONTH));
            nowEnd = format.format(ca.getTime()) + " 23:59:59";

            //上季度开始时间
            Calendar cal = Calendar.getInstance();
            int lastMonth = cal.get(Calendar.MONTH) - 2;
            if (lastMonth >= 1 && lastMonth <= 3) {
                cal.set(Calendar.MONTH, 0);
            } else if (lastMonth >= 4 && lastMonth <= 6) {
                cal.set(Calendar.MONTH, 3);
            } else if (lastMonth >= 7 && lastMonth <= 9) {
                cal.set(Calendar.MONTH, 4);
            } else if (lastMonth >= 10 && lastMonth <= 12) {
                cal.set(Calendar.MONTH, 9);
            }
            cal.set(Calendar.DATE, 1);
            lastStart = format.format(cal.getTime()) + " 00:00:00";

            //上季度结束时间
            Calendar cale = Calendar.getInstance();
            //计算季度数：由于月份从0开始，即1月份的Calendar.MONTH值为0,所以计算季度的第三个月份只需 月份 / 3 * 3 + 2
            cale.set(Calendar.MONTH, (((int) cale.get(Calendar.MONTH) - 2) / 3) * 3 + 2);
            cale.set(Calendar.DAY_OF_MONTH, cale.getActualMaximum(Calendar.DAY_OF_MONTH));
            lastEnd = format.format(cale.getTime()) + " 23:59:59";

        } else if ("5".equals(strategyInfo.get("f_range"))) {
            f_range = "本年";
            //本年开始时间
            c.set(c.get(Calendar.YEAR), 0, 1);//开始时间日期
            nowStart = format.format(c.getTime()) + " 00:00:00";
            //本年结束时间
            Calendar ca = Calendar.getInstance();
            ca.set(ca.get(Calendar.YEAR), 11, ca.getActualMaximum(Calendar.DAY_OF_MONTH));//结束日期
            nowEnd = format.format(ca.getTime()) + " 23:59:59";

            //去年开始时间
            Calendar cal = Calendar.getInstance();
            cal.add(Calendar.YEAR, -1); //年份减1
            cal.set(cal.get(Calendar.YEAR), 0, 1);
            lastStart = format.format(cal.getTime()) + " 00:00:00";

            //去年结束时间
            Calendar cale = Calendar.getInstance();
            cale.add(Calendar.YEAR, -1); //年份减1
            cale.set(cale.get(Calendar.YEAR), 11, cale.getActualMaximum(Calendar.DAY_OF_MONTH));//结束日期
            lastEnd = format.format(cale.getTime()) + " 23:59:59";

        }

        // 创建工具类.
        ExcelUtil<Object> util = new ExcelUtil<Object>(Object.class);

        List<Map<String, Object>> branchData = new ArrayList<>();
        List<Object> alias = new ArrayList<>();
        List<Object> names = new ArrayList<>();

        names.add("f_level");
        names.add("f_zlmc");
        names.add("f_range");
        names.add("fData");
        names.add("yData");

        alias.add("等级");
        alias.add("支路名称");
        alias.add("时间颗粒度");
        alias.add("能耗量");
        alias.add("上次能耗量");

        List<Map<String, Object>> departmentData = new ArrayList<>();
        List<Object> departmentAlias = new ArrayList<>();
        List<Object> departmentNames = new ArrayList<>();

        departmentNames.add("f_level");
        departmentNames.add("f_zlmc");
        departmentNames.add("f_range");
        departmentNames.add("fData");
        departmentNames.add("peopleData");
        departmentNames.add("yData");

        departmentAlias.add("等级");
        departmentAlias.add("支路名称");
        departmentAlias.add("时间颗粒度");
        departmentAlias.add("能耗量");
        departmentAlias.add("人均能耗量");
        departmentAlias.add("上次能耗量");


        // 临时文件名
        String branchFile =  "branch" + System.currentTimeMillis();
        String departmentFile =  "department" + System.currentTimeMillis();
        // sheet页名称
        String FileName = "sheet";
        // 导出excel地址
        /*String path = System.getProperty("user.dir");//获取项目的路径
        String FilePath = path+"\\BESWebapp\\src\\main\\webapp\\WEB-INF\\file\\DDCprgram\\";//获取文件的上级目录的路径,为了拼接编译好的bin文件*/
        //String FilePath = System.getProperty("user.dir") + "\\" + file + ".xls";

        //判断目录是否存在，不存在则创建
        if (!makeWritableDirectoryIfNotExist(emailFilePath))
        {
            return;
        }
        String branchFilePath = emailFilePath + "\\" + branchFile + ".xls";
        String departmentFilePath = emailFilePath + "\\" + departmentFile + ".xls";

        // 导出方法
        if ("1".equals(strategyInfo.get("f_pId"))) { //默认 支路和部门都有

            //支路数据
            branchData = besStrategyMapper.queryBranchData(strategyId, nowStart, nowEnd, lastStart, lastEnd);
            if (branchData != null && branchData.size() > 0){
                for (Map<String,Object> dataMap : branchData){
                    dataMap.put("f_range",f_range);
                }
            }
            //生成支路excel
            ExcelReturn resBranch = util.resListDynamic(FileName,branchFilePath,branchData,alias,names);

            //发送邮件
            IdcEmailConfig branchMailInfo=new IdcEmailConfig();
            branchMailInfo.setContent("报表信息");
            branchMailInfo.setFromAddress(emailAccount);
            branchMailInfo.setMailServerhost(emailServerHost);
            branchMailInfo.setPassword(emailPassWord);
            branchMailInfo.setSubject("报表信息");
            branchMailInfo.setToAddress(strategyInfo.get("f_email").toString());
            branchMailInfo.setFilePath(branchFilePath);

            //部门数据
            departmentData = this.queryAllDepInfoByStrategyId(strategyId, "0", nowStart, nowEnd, lastStart, lastEnd);
            if (departmentData != null && departmentData.size() > 0){
                for (Map<String,Object> dataMap : departmentData){
                    dataMap.put("f_range",f_range);
                }
            }
            //生成支路excel
            ExcelReturn resDepartment = util.resListDynamic(FileName,departmentFilePath,departmentData,departmentAlias,departmentNames);

            //发送邮件
            IdcEmailConfig departmentMailInfo=new IdcEmailConfig();
            departmentMailInfo.setContent("报表信息");
            departmentMailInfo.setFromAddress(emailAccount);
            departmentMailInfo.setMailServerhost(emailServerHost);
            departmentMailInfo.setPassword(emailPassWord);
            departmentMailInfo.setSubject("报表信息");
            departmentMailInfo.setToAddress(strategyInfo.get("f_email").toString());
            departmentMailInfo.setFilePath(branchFilePath);

            try {
                emailService.init(branchMailInfo);
                emailService.init(departmentMailInfo);
            } catch (Exception e) {
                e.printStackTrace();
            }
            try {

                emailService.sendEmail(branchMailInfo,dateFormat.format(date));
                emailService.sendEmail(departmentMailInfo,dateFormat.format(date));
                emailService.closeEmail();

            } catch (Exception e) {
                e.printStackTrace();
            }

        } else if ("2".equals(strategyInfo.get("f_pId"))) { //层级,只有支路
            //支路数据
            branchData = besStrategyMapper.queryBranchData(strategyId, nowStart, nowEnd, lastStart, lastEnd);
            if (branchData != null && branchData.size() > 0){
                for (Map<String,Object> dataMap : branchData){
                    dataMap.put("f_range",f_range);
                }
            }
            //生成支路excel
            ExcelReturn resBranch = util.resListDynamic(FileName,branchFilePath,branchData,alias,names);

            //发送邮件
            IdcEmailConfig branchMailInfo=new IdcEmailConfig();
            branchMailInfo.setContent("报表信息");
            branchMailInfo.setFromAddress(emailAccount);
            branchMailInfo.setMailServerhost(emailServerHost);
            branchMailInfo.setPassword(emailPassWord);
            branchMailInfo.setSubject("报表信息");
            branchMailInfo.setToAddress(strategyInfo.get("f_email").toString());
            branchMailInfo.setFilePath(branchFilePath);

            try {
                emailService.init(branchMailInfo);
            } catch (Exception e) {
                e.printStackTrace();
            }
            try {

                emailService.sendEmail(branchMailInfo,dateFormat.format(date));
                emailService.closeEmail();

            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if ("3".equals(strategyInfo.get("f_pId"))) { //只有部门
            //部门数据
            departmentData = this.queryAllDepInfoByStrategyId(strategyId, "0", nowStart, nowEnd, lastStart, lastEnd);
            //生成支路excel
            ExcelReturn resDepartment = util.resListDynamic(FileName,departmentFilePath,departmentData,departmentAlias,departmentNames);


            //发送邮件
            IdcEmailConfig departmentMailInfo=new IdcEmailConfig();
            departmentMailInfo.setContent(strategyInfo.get("f_name").toString());
            departmentMailInfo.setFromAddress(emailAccount);
            departmentMailInfo.setMailServerhost(emailServerHost);
            departmentMailInfo.setPassword(emailPassWord);
            departmentMailInfo.setSubject("报表信息");
            departmentMailInfo.setToAddress(strategyInfo.get("f_email").toString());
            departmentMailInfo.setFilePath(branchFilePath);

            try {
                emailService.init(departmentMailInfo);
            } catch (Exception e) {
                e.printStackTrace();
            }
            try {

                emailService.sendEmail(departmentMailInfo,dateFormat.format(date));
                emailService.closeEmail();

            } catch (Exception e) {
                e.printStackTrace();
            }


        }




    }


    //组织部门数据
    private List<Map<String, Object>>  queryAllDepInfoByStrategyId(String strategyId, String fType, String time_start, String time_end, String last_time_start, String last_time_end) {
        //获取参数
        //时间颗粒 fType
        String nhlx = "01000";
//        Double allDou = 0.00;
//        Double allPredou = 0.00;
//        Double allNUmber = 0.00;

        //根据id取得所有部门列表
        List<Map<String, Object>> list = besBranchDataMapper.queryAllDepByStrategy(strategyId);
        for (Map m : list) {
            //根据部门列表获取所有支路，电表数据
            Double dou = 0.00;
            Double predou = 0.00;
            Double peopleData = 0.00;
            List<Map<String, Object>> branchList = besBranchDataMapper.queryAllBranchByDepList(m.get("f_department_id").toString());
            List<Map<String, Object>> ammeterList = besBranchDataMapper.queryAllAmmeterByDepList(m.get("f_department_id").toString());
            List<Map<String, Object>> allList = new ArrayList<>();
            allList.addAll(branchList);
            allList.addAll(ammeterList);
            if (allList.size() > 0) {

                //根据电表和支路查询数据
                List<Map<String, Object>> dataList = besBranchDataMapper.queryDepDataByList(fType, nhlx, time_start, time_end, branchList, ammeterList);
                //根据系数修改数据
                a:
                for (Map dataMap : dataList) {
                    b:
                    for (Map zlxs : allList) {
                        if (zlxs.get("bh").toString().equals(dataMap.get("F_ZLBH").toString())) {
//                            dataMap.put("F_DATA",Double.parseDouble(dataMap.get("F_DATA").toString())*Double.parseDouble(m.get("xs").toString()));
                            dou = dou + Double.parseDouble(dataMap.get("F_DATA").toString()) * Double.parseDouble(zlxs.get("xs").toString());
                            continue a;
                        }
                    }
                }
//                    allDou = allDou +dou;
                //环比数据
                List<Map<String, Object>> preDataList = besBranchDataMapper.queryDepDataByList(fType, nhlx, last_time_start, last_time_end, branchList, ammeterList);
                //根据系数修改数据
                a:
                for (Map dataMap : preDataList) {
                    b:
                    for (Map zlxs : allList) {
                        if (zlxs.get("bh").toString().equals(dataMap.get("F_ZLBH").toString())) {
//                            dataMap.put("F_DATA",Double.parseDouble(dataMap.get("F_DATA").toString())*Double.parseDouble(m.get("xs").toString()));
                            predou = predou + Double.parseDouble(dataMap.get("F_DATA").toString()) * Double.parseDouble(zlxs.get("xs").toString());

                            continue a;
                        }
                    }
                }

                dou = getTwoDecimal(dou);
                predou = getTwoDecimal(predou);
//                    allPredou = allPredou+predou;
                m.put("fData", dou);
                m.put("F_CJSJ", time_end);
                m.put("F_TYPE", fType);
                m.put("f_zlbh(1)", m.get("f_department_id").toString());
                m.put("yData", predou + "kwh");
                //人均
                if ("0".equals(m.get("F_NUMBER").toString())) {
                    m.put("peopleData", "0kwh");
                } else {
//                        allNUmber =  allNUmber+Double.parseDouble(m.get("F_NUMBER").toString());
                    peopleData = dou / Double.parseDouble(m.get("F_NUMBER").toString());
                    peopleData = getTwoDecimal(peopleData);
                    m.put("peopleData", peopleData + "kwh");
                }
            } else {
                m.put("fData", "0");
                m.put("F_CJSJ", time_end);
                m.put("F_TYPE", fType);
                m.put("f_zlbh(1)", m.get("f_department_id").toString());
                m.put("yData", "0kwh");
                //人均
                m.put("peopleData", "0kwh");
            }

        }
//        for () {
//
//        }

        // 排序器
        Comparator<Map<String, Object>> comparator = new Comparator<Map<String, Object>>() {
            @Override
            public int compare(Map<String, Object> o1, Map<String, Object> o2) {
                int o1RoomNo = Integer.parseInt(o1.get("f_level").toString());
                int o2RoomNo = Integer.parseInt(o2.get("f_level").toString());

                if (o1RoomNo > o2RoomNo) {
                    return 1;
                } else if (o1RoomNo < o2RoomNo) {
                    return -1;
                } else {
                    // 若是相同房间号，则比较时间
                    if (o1.get("fData") == null || o2.get("fData") == null) {
                        return 1;
                    }

                    Double o1Data = Double.parseDouble(o1.get("fData").toString());
                    Double o2Data = Double.parseDouble(o2.get("fData").toString());


                    if (o1Data < o2Data) {
                        return 1;
                    } else if (o1Data > o2Data) {
                        return -1;
                    }
                    return 0;
                }
            }
        };

        list.sort(comparator);
        for (Map map : list) {
            Double fData = Double.parseDouble(map.get("fData").toString());
            map.put("fData", fData + "kwh");
        }
        return list;
    }


    @PostConstruct
    public void testMethods() {
        List<Map<String, Object>> list = queryAllDepInfoByStrategyId("11", "0", "2022-04-18 00:00:00", "2022-04-24 23:59:59", "2022-04-11 23:59:59", "2022-04-17 23:59:59");
    }

    private Double getTwoDecimal(Double dou) {
        BigDecimal two = new BigDecimal(dou);
        dou = two.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
        return dou;
    }

}
