package com.efounder.JEnterprise.timer.manage;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.core.common.util.StringUtil;
import com.efounder.JEnterprise.domain.SysJob;
import com.efounder.JEnterprise.platform.util.InterfaceAccessUtil;
import com.efounder.JEnterprise.platform.util.SwitchControlObject;
import com.efounder.JEnterprise.platform.websocket.config.PduStatusData;
import com.efounder.JEnterprise.timer.service.PSysJobService;
import com.efounder.JEnterprise.zhdg.config.LightPoleConfig;
import com.efounder.JEnterprise.zhdg.controller.PduApiController;
import com.efounder.JEnterprise.zhdg.util.ThreadPool;
import com.efounder.JEnterprise.zhdg.websocket.PduThread;
import com.efounder.constants.ScheduleConstants;
import com.efounder.util.CronUtils;
import org.quartz.SchedulerException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * describe: 开关状态管理
 *
 * @author zs
 * @date 2021/12/23
 */
@Component
public class PduStatusManage {

    /**
     * 日志
     */
    private static final Logger logger = LoggerFactory.getLogger(PduApiController.class);


    @Resource
    private PSysJobService pSysJobService;

    @Resource
    private LightPoleConfig lightPoleConfig;

    @Resource
    private PduThread pduThread;



    /**
     * 计划任务存储集合
     */
//    public static Map<String, SysJob> jobMap = new ConcurrentHashMap<>();
    public static List<SysJob> jobList = new Vector<>();


    /**
     * 初始化计划任务集合
     * @param list
     */
    public static void setJobList(List<SysJob> list){
        jobList = list;
    }

    /**
     * 初始化工作
     */
    public void init(){
        initData();
        statusMonitor();
        onlineMonitor();

        //推送PDU开关状态数据
        pduThread.pushThread();
    }

    /**
     * 加载数据
     */
    public void initData (){
        SysJob job = new SysJob();
        //过滤正常状态的计划任务
        job.setStatus(ScheduleConstants.Status.NORMAL.getValue());
        List<SysJob> list = pSysJobService.selectJobList(job);
        jobList = list;
    }

    /**
     * 获取在线PDU数据：每隔1分钟执行一次
     */
    public void onlineMonitor(){
        ThreadPool.executor.execute(()->{
            while(true){
                try {
                    getOnlineData();
//                    Thread.sleep(30000);
                    Thread.sleep(60000);
                } catch (InterruptedException e) {
                    logger.error("在线状态的PDU设备数据获取方法-线程执行报错："+e.getMessage());
                }
            }
        });
    }

    /**
     * 获取在线状态的PDU设备数据，判断PDU模块是否可访问，处理缓存数据
     */
    public void getOnlineData(){
        try{
            String path = lightPoleConfig.getOnlineDataUrl;
            String result = InterfaceAccessUtil.doGetWithException(path);
            System.out.println("获取在线状态的PDU设备数据:"+result);
            if(StringUtil.isNotEmpty(result)){
                JSONObject jsonObject = JSONArray.parseObject(result);
                JSONArray ipArray = jsonObject.getJSONArray("ip");
                Iterator<Map.Entry<String,Map>> it= PduStatusData.switchStatusMap.entrySet().iterator();
                while(it.hasNext()){
                    Map.Entry<String,Map> entry=it.next();
                    String ip = entry.getKey();
                    if(!ipArray.contains(ip)){
                        //ip不在线，移除对应的缓存数据
                        PduStatusData.switchStatusMap.remove(ip);
                    }
                }

                //在线PDU数据处理，先清空，再统计现有的在线数据
                PduStatusData.pduOnlineStatusMap.clear();
                ipArray.forEach(obj ->{
                    String ip = String.valueOf(obj);
                    PduStatusData.pduOnlineStatusMap.put(ip,PduOnlineStatusEnum.on.getCode());
                });

            }else{
                //清空缓存数据
                //switchStatusMap = new ConcurrentHashMap<>();
                PduStatusData.switchStatusMap.clear();
            }
            logger.info("在线状态的PDU设备数据获取方法调用************************");
        }catch (IOException e){
            logger.error("在线状态的PDU设备数据获取方法-请求报错:"+e.getMessage());
            //清空缓存数据
            PduStatusData.switchStatusMap.clear();
        }
    }


    /**
     * PDU开关状态监测：每隔5分钟执行一次
     */
    public void statusMonitor(){
        ThreadPool.executor.execute(()->{
            while(true){
                try {
                    //避免备用服务器定时任务缓存数据不更新，每隔5分钟更新一次
                    initData();
                    statusCompare();
                    Thread.sleep(300000);
//                    Thread.sleep(60000);
                    logger.info("PDU开关状态监测方法调用************************");
                } catch (InterruptedException e) {
                    logger.error("PDU状态监测报错："+e.getMessage());
                }
            }
        });
    }


    /**
     * PDU开关状态与计划任务设置状态做对比
     * 将获取到的实时状态数据与计划任务中设置的状态值作比较，如果在当前时间与设置值不符，下发命令控制状态
     */
    public void statusCompare(){
        int num = PduStatusData.switchStatusMap.size();
        if(num == 0){
            //避免备用服务器没有缓存数据一直执行定时任务
            logger.info("PDU状态没有缓存数据,不继续执行判断：PduStatusData.switchStatusMap.size()=" + num);
            return;
        }
            Map<String,SysJob> map = new ConcurrentHashMap<>();

            //遍历计划任务
            jobList.forEach(sysJob -> {
                String group = sysJob.getJobGroup();
//                String cron = sysJob.getCronExpression();
//                //下次执行时间
//                Date nextDate = CronUtils.getNextExecution(cron);
                map.put(group,sysJob);

            });

        //分别计算大灯、小灯的计划任务与实际状态对比
         compare(map,JobTypeEnum.bigLightSwitchOn,JobTypeEnum.bigLightSwitchOff,SwitchControlObject.big_light);

        compare(map,JobTypeEnum.smallLightSwitchOn,JobTypeEnum.smallLightSwitchOff,SwitchControlObject.small_light);
        //分别计算-显示屏-的计划任务与实际状态对比
        compare(map,JobTypeEnum.screenSwitchOn,JobTypeEnum.screenSwitchOff,SwitchControlObject.screen);
    }

    /**
     * 将各种类型的计划任务与实际状态对比，执行具体的指令
     * @param map 集合
     * @param on 开标志
     * @param off 关标志
     * @param controlObject 控制对象
     */
    public void compare(Map<String,SysJob> map, JobTypeEnum on, JobTypeEnum off, SwitchControlObject controlObject){
        SysJob onJob = map.get(on.name());
        SysJob offJob = map.get(off.name());
        if(onJob == null || offJob == null){
            logger.info("没有配置完整的开关计划任务类型:"+on.name()+","+off.name());
            return;
        }
        String onCorn = onJob.getCronExpression();
        String offCorn = offJob.getCronExpression();

        //以下计算的假设是：每个任务执行的时间间隔是相等的，都是间隔一天
        //下次执行时间
        Date onNextTime = CronUtils.getNextExecution(onCorn);
        Date offNextTime = CronUtils.getNextExecution(offCorn);

        Date date = new Date();

        //上次执行时间
        Date onBeforeTime = getDateByDays(onNextTime,-1);
        Date offBeforeTime = getDateByDays(offNextTime,-1);

        int onCount = 0;
        int offCount = 0;
        //获取控制对象的开关状态
        for (Map statusMap : PduStatusData.switchStatusMap.values()) {
            String status = String.valueOf(statusMap.get(controlObject.name()));
            if(SwitchStatusType.switchOn.getCode().equals(status)){
                onCount++;
            }
            if(SwitchStatusType.switchOFF.getCode().equals(status)){
                offCount++;
            }
        }

        if(onBeforeTime.after(offBeforeTime)){
            if(date.after(onBeforeTime) && date.before(offNextTime)){
                //上开<当前时间<下关
                //获取当前状态，如果状态不符合：开，执行开计划任务【相比直接下发指令，可以保留执行记录】
//                if(onCount == 0 && offCount == PduStatusData.switchStatusMap.size()){
                    //如果全是关，就执行
                if(offCount > 0){
                    //如果存在关的，就执行开的定时任务
                    try {
                        logger.info("执行开的定时任务："+on.name() +",onCount="+onCount+",offCount="+offCount);
                        logger.info("PduStatusData.switchStatusMap:"+PduStatusData.switchStatusMap);
                        pSysJobService.run(onJob);
                    } catch (SchedulerException e) {
                        logger.error("额外执行计划任务-开指令-报错："+e.getMessage());
                    }
                }
            }
        }else{
            if(date.after(offBeforeTime) && date.before(onNextTime)){
                //上关<当前时间<下开
                //获取当前状态，如果状态不符合：关，执行关计划任务【相比直接下发指令，可以保留执行记录】
//                if(offCount == 0 && onCount == PduStatusData.switchStatusMap.size()){
                    //如果全是开，就执行
                if(onCount > 0){
                    //如果存在开的，就执行关的定时任务
                    try {
                        logger.info("执行关的定时任务："+off.name() +",onCount="+onCount+",offCount="+offCount);
                        logger.info("PduStatusData.switchStatusMap:"+PduStatusData.switchStatusMap);
                        pSysJobService.run(offJob);
                    } catch (SchedulerException e) {
                        logger.error("额外执行计划任务-关指令-报错："+e.getMessage());
                    }
                }
            }
        }
    }



    /**
     * 获取新的时间，加上days天数
     * @return
     */
    public Date getDateByDays(Date date ,int days){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DATE,days);
        return calendar.getTime();
    }


    /**
     * 获取两个时间的最大值
     * @param one
     * @param two
     * @return
     */
    public Date getMaxDate(Date one,Date two){
        if(one.after(two)){
            return one;
        }
        return two;
    }

}
