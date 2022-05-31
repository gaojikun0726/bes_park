package com.efounder.JEnterprise.commhandler;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.core.ApplicationContextProvider;
import com.core.common.constant.BesNodeType;
import com.core.common.constant.WebSocketEvent;
import com.core.common.util.DataUtil;
import com.efounder.JEnterprise.initializer.*;
import com.efounder.JEnterprise.mapper.basedatamanage.eqmanage.*;
import com.efounder.JEnterprise.mapper.collectorJob.BESJobManagerMapper;
import com.efounder.JEnterprise.mapper.quartz.SysJobPlanMapper;
import com.efounder.JEnterprise.model.basedatamanage.enegrycollectionmanage.BESElectricParams;
import com.efounder.JEnterprise.model.basedatamanage.enegrycollectionmanage.BESElectric_Coll_Rlgl;
import com.efounder.JEnterprise.model.basedatamanage.eqmanage.*;
import com.efounder.JEnterprise.model.systemcenter.Interfaceconfig.ControllerStateModel;
import com.efounder.JEnterprise.model.systemcenter.Interfaceconfig.MessageMonitoringModel;
import com.efounder.JEnterprise.platform.websocket.config.SessionManager;
import com.efounder.JEnterprise.service.basedatamanage.eqmanage.impl.BESSbdyServiceImpl;
import com.efounder.JEnterprise.service.basedatamanage.eqmanage.impl.EnerEquipmentServiceImpl;
import com.efounder.JEnterprise.service.opm.PushDeviceAlarm;
import com.efounder.JEnterprise.service.systemcenter.interfaceconfig.Impl.FunctionPointStateServiceImpl;
import com.google.auto.service.AutoService;
import io.netty.channel.Channel;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;
import org.ace.business.bo.ChannelTypeState;
import org.ace.business.constant.ChannelType;
import org.ace.business.constant.Code;
import org.ace.business.constant.DDCCmd;
import org.ace.business.constant.PointType;
import org.ace.business.dto.ReceiveMsg;
import org.ace.business.dto.TimeData;
import org.ace.business.dto.ddc.*;
import org.ace.business.dto.edc.*;
import org.ace.business.dto.ldc.*;
import org.ace.business.handler.ClientMsgReceive;
import org.ace.business.handler.SendMsgHandler;
import org.ace.httpclient.OkHttpClient;
import org.ace.httpclient.exception.HttpException;
import org.ace.httpclient.request.RequestParams;
import org.ace.socketserver.util.MsgUtil;
import org.ace.websocket.handler.WebSocketService;
import org.apache.shiro.cache.Cache;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;
import org.springframework.web.socket.TextMessage;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 下位机客户端回调
 *
 * @author xiepufeng
 * @date 2020/4/21 7:16
 */
@AutoService(ClientMsgReceive.class)
public class ClientEventCallback implements ClientMsgReceive
{

    public static final String LIGHT_FAULT_VALUE = "100";

    // 客户端状态存储
    public static final Map<String, ChannelTypeState> clientStateStore = new HashMap<>();

    private static final Logger log = LoggerFactory.getLogger(ClientEventCallback.class);

    private BESJobManagerMapper besJobManagerMapper = ApplicationContextProvider.getBean(BESJobManagerMapper.class);

    private BesCollectorMapper besCollectorMapper = ApplicationContextProvider.getBean(BesCollectorMapper.class);

    private BesDdcMapper besDdcMapper = ApplicationContextProvider.getBean(BesDdcMapper.class);

    private BESAmmeterMapper besAmmeterMapper = ApplicationContextProvider.getBean(BESAmmeterMapper.class);

    private EnerEquipmentServiceImpl enerEquipmentService = ApplicationContextProvider.getBean(EnerEquipmentServiceImpl.class);

    private AlarmHandler alarmHandler = new AlarmHandler();

    private PlanningHandler planningHandler = new PlanningHandler();

    private CrossEquipmentMapper crossEquipmentMapper = ApplicationContextProvider.getBean(CrossEquipmentMapper.class);

    private BESSbdyMapper besSbdyMapper = ApplicationContextProvider.getBean(BESSbdyMapper.class);

    private BESSbdyServiceImpl besSbdyService = ApplicationContextProvider.getBean(BESSbdyServiceImpl.class);

    private BesPointStructMapper besPointStructMapper = ApplicationContextProvider.getBean(BesPointStructMapper.class);

    private FunctionPointStateServiceImpl functionPointStateService =  ApplicationContextProvider.getBean(FunctionPointStateServiceImpl.class);

    private ComPlanVariatInfoHandler comPlanVariatInfoHandler = ApplicationContextProvider.getBean(ComPlanVariatInfoHandler.class);

    // 消息监听定义缓存
    private MessageMonitoringCache messageMonitoringCache = ApplicationContextProvider.getBean(MessageMonitoringCache.class);

    // 采集器缓存定义
    private CollectorCache collectorCache = ApplicationContextProvider.getBean(CollectorCache.class);

    // 控制器缓存定义
    private DdcCache ddcCache = ApplicationContextProvider.getBean(DdcCache.class);

    // 设备树缓存定义
    private SbPzStructCache sbPzStructCache = ApplicationContextProvider.getBean(SbPzStructCache.class);

    // 电表缓存定义
    private AmmeterCache ammeterCache = ApplicationContextProvider.getBean(AmmeterCache.class);

    // 设备树节点类型定义缓存
    private SbTreeNodeTypeCache sbTreeNodeTypeCache = ApplicationContextProvider.getBean(SbTreeNodeTypeCache.class);

    // 虚点类型定义缓存
    private VirtualPointCache virtualPointCache = ApplicationContextProvider.getBean(VirtualPointCache.class);

    // ai 类型定义缓存
    private AiPointCache aiPointCache = ApplicationContextProvider.getBean(AiPointCache.class);

    // ao 类型定义缓存
    private AoPointCache aoPointCache = ApplicationContextProvider.getBean(AoPointCache.class);

    // 采集方案采集参数关联定义缓存
    private ElectricCollRlglCache electricCollRlglCache = ApplicationContextProvider.getBean(ElectricCollRlglCache.class);

    // 采集参数定义缓存
    private ElectricParamsCache electricParamsCache = ApplicationContextProvider.getBean(ElectricParamsCache.class);

    //定时同步设备的执行日志缓存
    private SyncLogCache syncLogCache = ApplicationContextProvider.getBean(SyncLogCache.class);

    private SysJobPlanMapper sysJobPlanMapper = ApplicationContextProvider.getBean(SysJobPlanMapper.class);

	   /**
     * 向中台推送报警信息
     */
    private PushDeviceAlarm pushDeviceAlarm = ApplicationContextProvider.getBean(PushDeviceAlarm.class);

	    // 客户端实时数据发布定义缓存
    private SubRealTimeDataCache subRealTimeDataCache = ApplicationContextProvider.getBean(SubRealTimeDataCache.class);


    /**
     * @param ip    控制器 ip 地址
     * @param state 在线状态 true 在线， false 离线
     */
    @Override
    public void controllerState(String ip, Boolean state)
    {

        if (!StringUtils.hasText(ip))
        {
            log.warn("ip 地址不存在");
            return;
        }

        ChannelTypeState channelTypeState = clientStateStore.get(ip);

        if (null == channelTypeState)
        {
            channelTypeState = new ChannelTypeState();
        }


        try
        {
            BesCollector edcCollector = collectorCache.getCollectorByChannelId(ip);

            ReceiveMsg<List<BESSbPzStruct>> msg = new ReceiveMsg();
            msg.setIp(ip);
            msg.setCode(0);

            // 0：离线；1：在线
            String onlineStatus = "0";

            channelTypeState.setState(state);

            if (state)
            {
                onlineStatus = "1";
                msg.setCode(1);
            }

            // 更新能耗采集器在线状态和设备树节点状态
            if (null != edcCollector)
            {
                if (state)
                {
                    // 设置控制器的时间（能耗）
                    SendMsgHandler.setControllerTimeEDC(ip, DataUtil.getTimeDataObject());
                }

                channelTypeState.setType(ChannelType.EDC);

                // 把在线状态保存到 map
                clientStateStore.put(ip, channelTypeState);

                // 更新能耗采集器在线状态
                edcCollector.setfOnlineState(onlineStatus);

                List<BESSbPzStruct> besSbPzStructs = sbPzStructCache.getCascadeSubordinate(edcCollector.getfSysName());

                // 更新缓存设备树在线状态
                String finalOnlineStatus = onlineStatus;
                besSbPzStructs.forEach(item -> item.setF_status(finalOnlineStatus));

                // 更新能耗采集器在线状态
                BesCollector besCollector = collectorCache.getCollectorByChannelId(ip);

                if (besCollector != null)
                {
                    besCollector.setfOnlineState(onlineStatus);
                }

                msg.setData(besSbPzStructs);

                // 推送消息到web客户端
                WebSocketService.postEvent(WebSocketEvent.DEVICE_STATE, msg);
//				        //推送设备离线信息到中台
//                String alertMsg = "设备离线";
//                pushDeviceAlarm.pushDeviceOfflineInfo(edcCollector.getfSysName(),alertMsg);

                // 推送第三方系统
                ControllerStateModel controllerStateModel = new ControllerStateModel();
                controllerStateModel.setState(Integer.valueOf(onlineStatus));
                controllerStateModel.setIp(ip);
                controllerStateModel.setType(ControllerStateModel.EDC);

                pushControllerState(controllerStateModel);

            }else
            {
                BesDdc ddcCollector = ddcCache.getDdcByChannelId(ip);

                if (null == ddcCollector)
                {
                    return;
                }

                // 查询设备数据信息
                BESSbPzStruct besSbPzStruct = sbPzStructCache.getCachedElement(ddcCollector.getfSysName());

                if (null == besSbPzStruct)
                {
                    return;
                }

                String type = besSbPzStruct.getF_type();

                // 判断是ddc 还是照明
                if (BesNodeType.DDC.equals(type))
                {
                    channelTypeState.setType(ChannelType.DDC);

                    if (state)
                    {
                        // 设置控制器的时间（DDC）
                        SendMsgHandler.setControllerTimeDDC(ip, DataUtil.getTimeDataObject());
                    }


                    // 推送第三方系统
                    ControllerStateModel controllerStateModel = new ControllerStateModel();
                    controllerStateModel.setState(Integer.valueOf(onlineStatus));
                    controllerStateModel.setIp(ip);
                    controllerStateModel.setType(ControllerStateModel.DDC);

                    pushControllerState(controllerStateModel);

                }else if (BesNodeType.IP_ROUTER.equals(type))
                {
                    channelTypeState.setType(ChannelType.LDC);

                    if (state)
                    {
                        // 设置IP路由器的时间（LDC）
                        SendMsgHandler.setControllerTimeLDC(ip, DataUtil.getTimeDataObject());
                    }

                    // 推送第三方系统
                    ControllerStateModel controllerStateModel = new ControllerStateModel();
                    controllerStateModel.setState(Integer.valueOf(onlineStatus));
                    controllerStateModel.setIp(ip);
                    controllerStateModel.setType(ControllerStateModel.LDC);

                    pushControllerState(controllerStateModel);
                }

                // 把在线状态保存到 map
                clientStateStore.put(ip, channelTypeState);

                // 更新控制器的在线状态
                ddcCollector.setfDdcOnlinestate(onlineStatus);

                List<BESSbPzStruct> besSbPzStructs = sbPzStructCache.getCascadeSubordinate(ddcCollector.getfSysName());

                // 更新缓存设备树在线状态
                String finalOnlineStatus = onlineStatus;
                besSbPzStructs.forEach(item -> item.setF_status(finalOnlineStatus));

                // 设置控制器在线状态
                BesDdc besDdc = ddcCache.getDdcByChannelId(ip);

                if (besDdc != null)
                {
                    besDdc.setfDdcOnlinestate(onlineStatus);
                }

                msg.setData(besSbPzStructs);

                // 推送消息到web客户端
                WebSocketService.postEvent(WebSocketEvent.DEVICE_STATE, msg);
//                //推送设备离线信息到中台
//                String alertMsg = "设备离线";
//                pushDeviceAlarm.pushDeviceOfflineInfo(ddcCollector.getfSysName(),alertMsg);
            }


        }catch (Exception e)
        {
            e.printStackTrace();
        }

    }

    /**
     * 心跳回调函数
     * 1、修正下位机在线状态
     *
     * @param ip
     */
    @Override
    public void heartbeatCallback(String ip)
    {

        if (!StringUtils.hasText(ip))
        {
            log.warn("心跳回调函数，ip 地址不存在");
            return;
        }

        ChannelTypeState channelTypeState = clientStateStore.get(ip);

        if (null == channelTypeState || !channelTypeState.getState())
        {
            controllerState(ip, true);
        }
    }

    /***************************************** EDC (能耗) *******************************************/

    // 能耗采集器远程升级响应回调
    @Override
    public void remoteUpgradeEDC(ReceiveMsg msg)
    {
        Integer index = msg.getIndex();
        String ip = msg.getIp();

        if (Code.SUCCEED.equals(msg.getCode()))
        {
            Channel channel = MsgUtil.getChannelByTokenSN(ip);
            channel.close();
        }

        if (null == index || !StringUtils.hasText(ip))
        {
            log.warn("能耗采集器远程升级响应回调，index 不存在，或者 ip 不存在");
            return;
        }

        // 获取当前 sessionId
        String sessionId = MsgSubPubHandler.pubMsg(index, ip);

        if (!StringUtils.hasText(sessionId))
        {
            log.warn("能耗采集器远程升级响应回调，sessionId 不存在");
            return;
        }

        // 推送消息到web客户端
        WebSocketService.postEvent(sessionId, WebSocketEvent.EDC, msg);
    }

    /**
     * 添加控制器响应回调
     * 1、更新数据库能耗采集器同步状态
     * 2、推送消息到web客户端
     *
     * @param msg
     */
    @Override
    public void controllerAddEDC(ReceiveMsg msg)
    {

        Integer index = msg.getIndex();
        String ip = msg.getIp();

        if (null == index || !StringUtils.hasText(ip))
        {
            log.warn("添加控制器响应回调，index 不存在，或者 ip 不存在");
            return;
        }

        // 获取当前 sessionId
        String sessionId = MsgSubPubHandler.pubMsg(index, ip);

        if (!StringUtils.hasText(sessionId))
        {
            log.warn("添加控制器响应回调，sessionId 不存在");
            return;
        }

        // 同步状态 0 未同步 1 已同步
        String syncState = "0";

        // 把添加控制器下位机返回的消息推送到前端页面
        WebSocketService.postEvent(sessionId, WebSocketEvent.EDC, msg);

        // 消息返回成功则同步成功否则同步失败
        if (Code.SUCCEED.equals(msg.getCode()))
        {
            syncState = "1";
            controllerState(ip, true);
        }

        // 根据channelId地址查询缓存数据是否存在
        BesCollector edcCollector = collectorCache.getCollectorByChannelId(msg.getIp());

        if (null == edcCollector)
        {
            return;
        }

        edcCollector.setfCollectorState(syncState);
        // 能耗采集器为在线状态
        edcCollector.setfOnlineState(syncState);

        // 更新ddc控制器的同步状态
        besCollectorMapper.updateCollector(edcCollector);
    }

    /**
     * 设置控制器的所有参数响应回调
     * 1、更新数据库能耗采集器同步状态
     * 2、推送消息到web客户端
     *
     * @param msg
     */
    @Override
    public void controllerSetEDC(ReceiveMsg msg)
    {

        Integer index = msg.getIndex();
        String ip = msg.getIp();

        if (null == index || !StringUtils.hasText(ip))
        {
            log.warn("设置控制器的所有参数响应回调，index 不存在，或者 ip 不存在");
            return;
        }

        // 获取当前 sessionId
        String sessionId = "";
        sessionId = MsgSubPubHandler.pubMsg(index, ip);

       if (StringUtils.hasText(sessionId)) {
            // 推送消息到web客户端
            WebSocketService.postEvent(sessionId, WebSocketEvent.EDC, msg);
        } else {
           //定时同步设备树任务,插入执行日志
           Date date = new Date();
           SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
           String dateTime = dateFormat.format(date);

           BesSyncLog besSyncLog = syncLogCache.getCachedElement(ip);

           if (besSyncLog != null){
               besSyncLog.setF_callback_status(msg.getCode().toString());
               besSyncLog.setF_callback_time(dateTime);

               sysJobPlanMapper.insertSyncLog(besSyncLog);
               syncLogCache.deleteOneSyncLogCache(ip);
           }

       }

        // 同步状态 0 未同步 1 已同步
        String syncState = "0";


        // 消息返回成功则同步成功否则同步失败
        if (Code.SUCCEED.equals(msg.getCode()))
        {
            syncState = "1";
            controllerState(ip, true);
        }


        // 更新在线状态（重设 ip 需要更新在线状态）
        ChannelTypeState channelTypeState = clientStateStore.get(ip);

        if (null == channelTypeState || !channelTypeState.getState())
        {
            controllerState(ip, true);
        }


        // 根据channelId地址查询缓存数据是否存在
        BesCollector edcCollector = collectorCache.getCollectorByChannelId(msg.getIp());

        if (null == edcCollector)
        {
            return;
        }

        edcCollector.setfCollectorState(syncState);
        edcCollector.setfOnlineState(syncState); // 设置能耗采集器在线

        // 更新控制器的同步状态
        besCollectorMapper.updateCollector(edcCollector);


        // 同步能耗采集器下的所有电表
        List<BESSbPzStruct> besSbPzStructs = sbPzStructCache.getCascadeSubordinate(edcCollector.getfSysName());

        for (BESSbPzStruct besSbPzStruct : besSbPzStructs)
        {
            // 过滤电表同步电表
            if (BesNodeType.AMMETER.equals(besSbPzStruct.getF_type()))
            {
                enerEquipmentService.synAmmeter(besSbPzStruct.getF_sys_name());
            }
        }

    }

    // 获取控制器的所有配置参数响应回调
    @Override
    public void controllerGetEDC(ReceiveMsg<ControllerDataEDC> msg)
    {
        Integer index = msg.getIndex();
        String ip = msg.getIp();

        if (null == index || !StringUtils.hasText(ip))
        {
            log.warn("获取控制器的所有配置参数响应回调，index 不存在，或者 ip 不存在");
            return;
        }

        // 获取当前 sessionId
        String sessionId = MsgSubPubHandler.pubMsg(index, ip);

        if (!StringUtils.hasText(sessionId))
        {
            log.warn("获取控制器的所有配置参数响应回调，sessionId 不存在");
            return;
        }

        // 推送消息到web客户端
        WebSocketService.postEvent(sessionId, WebSocketEvent.EDC, msg);
    }

    // 设置控制器的时间响应回调
    @Override
    public void controllerTimeSetEDC(ReceiveMsg msg)
    {

        Integer index = msg.getIndex();
        String ip = msg.getIp();

        if (null == index || !StringUtils.hasText(ip))
        {
            log.warn("设置控制器的时间响应回调，index 不存在，或者 ip 不存在");
            return;
        }

        // 获取当前 sessionId
        String sessionId = MsgSubPubHandler.pubMsg(index, ip);

        if (!StringUtils.hasText(sessionId))
        {
            log.warn("设置控制器的时间响应回调，sessionId 不存在");
            return;
        }

        // 推送消息到web客户端
        WebSocketService.postEvent(sessionId, WebSocketEvent.EDC, msg);
    }

    // 获取控制器的时间响应回调
    @Override
    public void controllerTimeGetEDC(ReceiveMsg<TimeData> msg)
    {
        Integer index = msg.getIndex();
        String ip = msg.getIp();

        if (null == index || !StringUtils.hasText(ip))
        {
            log.warn("获取控制器的时间响应回调，index 不存在，或者 ip 不存在");
            return;
        }

        // 获取当前 sessionId
        String sessionId = MsgSubPubHandler.pubMsg(index, ip);

        if (!StringUtils.hasText(sessionId))
        {
            log.warn("获取控制器的时间响应回调，sessionId 不存在");
            return;
        }

        // 推送消息到web客户端
        WebSocketService.postEvent(sessionId, WebSocketEvent.EDC, msg);
    }

    // 重启控制器响应回调，相当于重启复位
    @Override
    public void controllerRestartEDC(ReceiveMsg msg)
    {
        Integer index = msg.getIndex();
        String ip = msg.getIp();


        if (null == index || !StringUtils.hasText(ip))
        {
            log.warn("重启控制器响应回调，相当于重启复位响应回调，index 不存在，或者 ip 不存在");
            return;
        }

        // 获取当前 sessionId
        String sessionId = MsgSubPubHandler.pubMsg(index, ip);

        if (StringUtils.hasText(sessionId))
        {
            log.warn("重启控制器响应回调，相当于重启复位，sessionId 不存在");
            // 推送消息到web客户端
            WebSocketService.postEvent(sessionId, WebSocketEvent.EDC, msg);
        }

        if (!Code.SUCCEED.equals(msg.getCode()))
        {
            return;
        }

        // 根据channelId地址查询能耗采集器是否存在
        BesCollector edcCollector = collectorCache.getCollectorByChannelId(ip);

        if (null == edcCollector)
        {
            return;
        }

        String fIp = edcCollector.getfIpAddr();
        String fChannelId = edcCollector.getfChannelId();

        if (StringUtils.hasText(fChannelId)
                && StringUtils.hasText(fIp)
                && !fIp.equals(fChannelId))
        {

            edcCollector.setfChannelId(fIp);

            // 更新数据库
            besCollectorMapper.updateCollector(edcCollector);

            controllerState(fIp, false);
        }

        Channel channel = MsgUtil.getChannelByTokenSN(ip);
        channel.close();

    }

    // 重置控制器响应回调，恢复出厂设置，并重启
    @Override
    public void controllerResetEDC(ReceiveMsg msg)
    {
        Integer index = msg.getIndex();
        String ip = msg.getIp();

        if (null == index || !StringUtils.hasText(ip))
        {
            log.warn("重置控制器响应回调，恢复出厂设置，并重启，index 不存在，或者 ip 不存在");
            return;
        }

        // 获取当前 sessionId
        String sessionId = MsgSubPubHandler.pubMsg(index, ip);

        if (StringUtils.hasText(sessionId))
        {
            log.warn("重置控制器响应回调，恢复出厂设置，并重启，sessionId 不存在");
            // 推送消息到web客户端
            WebSocketService.postEvent(sessionId, WebSocketEvent.EDC, msg);
        }

        // 消息返回成功则同步成功否则同步失败
        if (!Code.SUCCEED.equals(msg.getCode()))
        {
            return;
        }

        // 根据channelId地址查询能耗采集器是否存在
        BesCollector edcCollector = collectorCache.getCollectorByChannelId(ip);

        if (null == edcCollector)
        {
            return;
        }

        String fIp = edcCollector.getfIpAddr();
        String fChannelId = edcCollector.getfChannelId();

        if (StringUtils.hasText(fChannelId)
                && StringUtils.hasText(fIp)
                && !fIp.equals(fChannelId))
        {
            edcCollector.setfChannelId(fIp);
            controllerState(fChannelId, false);
        }

        // 同步状态 0 未同步 1 已同步
        edcCollector.setfCollectorState("0");

        // 更新控制器的同步状态
        besCollectorMapper.updateCollector(edcCollector);

        Channel channel = MsgUtil.getChannelByTokenSN(ip);
        channel.close();

        List<BESSbPzStruct> besSbPzStructs = sbPzStructCache.getCascadeSubordinate(edcCollector.getfSysName());

        for (BESSbPzStruct besSbPzStruct : besSbPzStructs)
        {
            // 过滤电表
            if (BesNodeType.AMMETER.equals(besSbPzStruct.getF_type()))
            {
                BESAmmeter besAmmeter = ammeterCache.getCachedElement(besSbPzStruct.getF_sys_name());
                besAmmeter.setfAmmeterState("0");
                // 更新电表同步状态
                besAmmeterMapper.updateByPrimaryKeySelective(besAmmeter);
            }
        }
    }

    // 删除一个控制器响应回调，并删除和它相关的电表
    @Override
    public void controllerDeleteEDC(ReceiveMsg msg)
    {
        Integer index = msg.getIndex();
        String ip = msg.getIp();

        if (null == index || !StringUtils.hasText(ip))
        {
            log.warn("删除一个控制器响应回调，并删除和它相关的电表回调，index 不存在，或者 ip 不存在");
            return;
        }

        // 获取当前 sessionId
        String sessionId = MsgSubPubHandler.pubMsg(index, ip);

        if (!StringUtils.hasText(sessionId))
        {
            log.warn("删除一个控制器响应回调，并删除和它相关的电表回调，sessionId 不存在");
            return;
        }

        // 推送消息到web客户端
        WebSocketService.postEvent(sessionId, WebSocketEvent.EDC, msg);

    }

    // 新增加一个电表信息响应回调
    @Override
    public void ammeterAddEDC(ReceiveMsg<AmmeterCollectParamData> msg)
    {
        Integer index = msg.getIndex();
        String ip = msg.getIp();

        if (null == index || !StringUtils.hasText(ip))
        {
            log.warn("新增加一个电表信息响应回调，index 不存在，或者 ip 不存在");
            return;
        }

        BESAmmeter besAmmeter = new BESAmmeter();

        Integer meterID = msg.getData().getMeterID();

        besAmmeter.setfSbid(String.valueOf(meterID));

        // 1 已同步； 0 未同步
        if (Code.SUCCEED.equals(msg.getCode()))
        {
            besAmmeter.setfAmmeterState("1");

            // 新增电表采集方案
            BESAmmeter besAmmeter1 = ammeterCache.getCachedElementBySbid(String.valueOf(meterID));

            if (null == besAmmeter1)
            {
                return;
            }

            String collectMethodID = besAmmeter1.getfCjfabh(); // 采集方案编号

            List<BESElectric_Coll_Rlgl> electricDataInfo = electricCollRlglCache.getCachedElementByCjfabh(collectMethodID);

            for (int i = 0; i < electricDataInfo.size(); i++)
            {

                // 采集参数
                CollectParamData collectParamData = new CollectParamData();

                collectParamData.setMeterID(meterID);

                // 采集方案
                CollectMethod collectMethod = new CollectMethod();

                collectMethod.setCollectMethodID(Integer.parseInt(collectMethodID));
                collectMethod.setCollectCount(i + 1);

                collectParamData.setElectricDataCollectMethod(collectMethod);

                // 电能参数
                ElectricParam electricParam = new ElectricParam();

                BESElectric_Coll_Rlgl electricData = electricDataInfo.get(i);

                BESElectricParams electricParams = electricParamsCache.getCachedElement(electricData.getfNhbh());

                try
                {
                    electricParam.setUnitType(electricParams.getfUnit()); // 单位类型
                    electricParam.setPointLocation(Integer.parseInt(electricParams.getfScalingPosition())); // 小数点位置 解析规则
                    electricParam.setDataEncodeType(Integer.parseInt(electricParams.getfBmgz())); // 编码规则 BCD编码，或者是10进制编码
                    electricParam.setElectricName(electricParams.getfDnmc()); // 能耗名称
                    electricParam.setOffsetAddr(Long.parseLong(electricParams.getfPydz())); // 寄存器偏移地址
                    electricParam.setDataLength(Integer.parseInt(electricParams.getfDataLength())); // 数据长度 字节数
                    electricParam.setElectricID(Integer.parseInt(electricParams.getfDnbh())); // 能耗参数 id
                    electricParam.setDataType(Integer.parseInt(electricParams.getfDataType())); // 数据类型
                    electricParam.setCodeSeq(Integer.parseInt(electricParams.getfCodeSeq())); // 解码顺序
                    electricParam.setMeterID(meterID);

                } catch (Exception e)
                {
                    log.warn("类型转换异常");
                    e.printStackTrace();
                }

                collectParamData.setElectricDataInfo(electricParam);

                // 添加电能参数到下位机
                SendMsgHandler.addAmmeterCollectionSchemeEDC(ip, collectParamData);
            }

        } else
        {
            besAmmeter.setfAmmeterState("0");
        }


        besAmmeterMapper.updateBySbid(besAmmeter);

        // 获取当前 sessionId
        String sessionId = MsgSubPubHandler.pubMsg(index, ip);

        if (StringUtils.hasText(sessionId))
        {
            // 推送消息到web客户端
            WebSocketService.postEvent(sessionId, WebSocketEvent.EDC, msg);
        }
    }

    // 删除一个电表响应回调
    @Override
    public void ammeterDeleteEDC(ReceiveMsg<AmmeterCollectParamData> msg)
    {
        Integer index = msg.getIndex();
        String ip = msg.getIp();

        if (null == index || !StringUtils.hasText(ip))
        {
            log.warn("删除一个电表响应回调，index 不存在，或者 ip 不存在");
            return;
        }

        // 获取当前 sessionId
        String sessionId = MsgSubPubHandler.pubMsg(index, ip);

        if (StringUtils.hasText(sessionId))
        {
//            log.warn("删除一个电表响应回调，sessionId 不存在");
//            return;

            // 推送消息到web客户端
            WebSocketService.postEvent(sessionId, WebSocketEvent.EDC, msg);
        }


    }

    // 设置一个电表的所有参数响应回调
    @Override
    public void ammeterSetEDC(ReceiveMsg<AmmeterCollectParamData> msg)
    {
        Integer index = msg.getIndex();
        String ip = msg.getIp();

        if (null == index || !StringUtils.hasText(ip))
        {
            log.warn("设置一个电表的所有参数响应回调，index 不存在，或者 ip 不存在");
            return;
        }
        Integer meterID = msg.getData().getMeterID();

        BESAmmeter besAmmeter = ammeterCache.getCachedElementBySbid(String.valueOf(meterID));

        // 1 已同步； 0 未同步
        if (Code.SUCCEED.equals(msg.getCode()))
        {
            besAmmeter.setfAmmeterState("1");

        } else
        {
            besAmmeter.setfAmmeterState("0");
        }

        besAmmeterMapper.updateBySbid(besAmmeter);

        // 获取当前 sessionId
        String sessionId = MsgSubPubHandler.pubMsg(index, ip);

        if (StringUtils.hasText(sessionId))
        {
            // 推送消息到web客户端
            WebSocketService.postEvent(sessionId, WebSocketEvent.EDC, msg);
        }
    }

    // 获取一个电表的所有配置信息响应回调
    @Override
    public void ammeterGetEDC(ReceiveMsg<AmmeterCollectParamData> msg)
    {
        Integer index = msg.getIndex();
        String ip = msg.getIp();

        if (null == index || !StringUtils.hasText(ip))
        {
            log.warn("获取一个电表的所有配置信息响应回调，index 不存在，或者 ip 不存在");
            return;
        }

        // 获取当前 sessionId
        String sessionId = MsgSubPubHandler.pubMsg(index, ip);

        if (StringUtils.hasText(sessionId))
        {
//            log.warn("获取一个电表的所有配置信息响应回调，sessionId 不存在");
//            return;
            // 推送消息到web客户端
            WebSocketService.postEvent(sessionId, WebSocketEvent.EDC, msg);
        }


    }

    // 新增一个电表的采集方案
    @Override
    public void ammeterCollectionSchemeAddEDC(ReceiveMsg<ElectricParam> msg)
    {

    }

    // 设置一个电表的采集方案
    @Override
    public void ammeterCollectionSchemeSetEDC(ReceiveMsg<ElectricParam> msg)
    {

    }

    // 删除一个电表的采集方案
    @Override
    public void ammeterCollectionSchemeDeleteEDC(ReceiveMsg<ElectricParam> msg)
    {

    }

    // 获取电表实时数据响应回调
    @Override
    public void ammeterRealtimeDataGetEDC(ReceiveMsg<AmmeterData> msg)
    {
        Integer index = msg.getIndex();
        String ip = msg.getIp();

        if (null == index || !StringUtils.hasText(ip))
        {
            log.warn("获取电表实时数据响应回调，index 不存在，或者 ip 不存在");
            return;
        }

        // 获取当前 sessionId
        String sessionId = MsgSubPubHandler.pubMsg(index, ip);

        if (!StringUtils.hasText(sessionId))
        {
            log.warn("获取电表实时数据响应回调，sessionId 不存在");
            return;
        }

        // 推送消息到web客户端
        WebSocketService.postEvent(sessionId, WebSocketEvent.EDC, msg);
    }

    // 获取历史数据响应回调
    @Override
    public void ammeterHistoryDataGetEDC(ReceiveMsg<AmmeterHistoryData> msg)
    {
        Integer index = msg.getIndex();
        String ip = msg.getIp();

        if (null == index || !StringUtils.hasText(ip))
        {
            log.warn("获取历史数据响应回调，index 不存在，或者 ip 不存在");
            return;
        }

        // 获取当前 sessionId
        String sessionId = MsgSubPubHandler.pubMsg(index, ip);

        if (!StringUtils.hasText(sessionId))
        {
            log.warn("获取历史数据响应回调，sessionId 不存在");
            return;
        }

        // 推送消息到web客户端
        WebSocketService.postEvent(sessionId, WebSocketEvent.EDC, msg);
    }

    // 接收告警数据
    @Override
    public void alarmDataReceiveEDC(ReceiveMsg<List<AlarmData>> msg)
    {

    }

    // 接收电表实时数据 todo
    @Override
    public void ammeterRealtimeDataReceiveEDC(ReceiveMsg<List<AmmeterData>> msg)
    {
        if (null == msg)
        {
            log.warn("接收电表实时数据, 下位机上传的数据能耗数据不存在");
            return;
        }

        List<AmmeterData> data = msg.getData();

        if (data == null || data.isEmpty())
        {
            log.warn("接收电表实时数据, 参数 data 不存在");
            return;
        }


        EnergyCollectHandler.ammeterDataHandle(msg);
        // todo 待优化（添加缓存配置减少查表）
        alarmHandler.alarmHandle(msg);//报警

    }

    // 接收电表断点续传数据 todo
    @Override
    public void ammeterResumeDataReceiveEDC(ReceiveMsg<List<AmmeterData>> msg)
    {
        if (null == msg)
        {
            return;
        }

        List<AmmeterData> data = msg.getData();

        if (data == null || data.isEmpty())
        {
            log.warn("接收电表断点续传数据, 参数 data 不存在; 错误码：" + msg.getCode());
            return;
        }

        Collections.sort(data); // 根据时间对电表数据排序
        EnergyCollectHandler.ammeterDataHandle(msg);
        // todo 待优化（添加缓存配置减少查表）
        alarmHandler.alarmHandle(msg);//报警
    }

    /***************************************** DDC （楼控） **********************************************/

    // 新增一个控制器返回回调（DDC）
    @Override
    public void controllerAddDDC(ReceiveMsg msg)
    {
        Integer index = msg.getIndex();
        String ip = msg.getIp();

        if (null == index || !StringUtils.hasText(ip))
        {
            log.warn("新增一个ddc 控制器返回回调，index 不存在，或者 ip 不存在");
            return;
        }

        // 获取当前 sessionId
        String sessionId = MsgSubPubHandler.pubMsg(index, ip);

        if (!StringUtils.hasText(sessionId))
        {
            log.warn("新增一个ddc 控制器返回回调，sessionId 不存在");
            return;
        }

        // 同步状态 0 未同步 1 已同步
        String syncState = "0";

        // 把添加控制器下位机返回的消息推送到前端页面
        WebSocketService.postEvent(sessionId, WebSocketEvent.DDC, msg);

        // 消息返回成功则同步成功否则同步失败
        if (Code.SUCCEED.equals(msg.getCode()))
        {
            syncState = "1";
            // 手动更新在线状态
            controllerState(ip, true);
        }

        // 根据channelId地址查询缓存数据是否存在
        BesDdc besDdc = ddcCache.getDdcByChannelId(msg.getIp());

        if (null == besDdc)
        {
            return;
        }

        besDdc.setfDdcState(syncState);
        besDdc.setfDdcOnlinestate("1");

        // 更新ddc控制器的同步状态
        besCollectorMapper.updateDDC(besDdc);
    }

    // 设置控制器的所有参数回调（DDC）
    @Override
    public void controllerSetDDC(ReceiveMsg msg)
    {
        Integer index = msg.getIndex();
        String ip = msg.getIp();

        if (null == index || !StringUtils.hasText(ip))
        {
            log.warn("设置控制器的所有参数回调，index 不存在，或者 ip 不存在");
            return;
        }

        controllerState(ip, true);

        // 获取当前 sessionId
        String sessionId = MsgSubPubHandler.pubMsg(index, ip);

        if (StringUtils.hasText(sessionId))
        {
            // 把添加控制器下位机返回的消息推送到前端页面
            WebSocketService.postEvent(sessionId, WebSocketEvent.DDC, msg);
        } else {
            //定时同步设备树任务,插入执行日志
            Date date = new Date();
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String dateTime = dateFormat.format(date);

            BesSyncLog besSyncLog = syncLogCache.getCachedElement(ip);

            if (besSyncLog != null){
                besSyncLog.setF_callback_status(msg.getCode().toString());
                besSyncLog.setF_callback_time(dateTime);

                sysJobPlanMapper.insertSyncLog(besSyncLog);
                syncLogCache.deleteOneSyncLogCache(ip);
            }

        }

        // 同步状态 0 未同步 1 已同步
        String syncState = "0";


        // 消息返回成功则同步成功否则同步失败
        if (Code.SUCCEED.equals(msg.getCode()))
        {
            syncState = "1";
        }

        // 根据channelId地址查询缓存数据是否存在
        BesDdc besDdc = ddcCache.getDdcByChannelId(msg.getIp());

        if (null == besDdc)
        {
            return;
        }

        besDdc.setfDdcState(syncState);
        besDdc.setfDdcOnlinestate("1");

        // 更新ddc控制器的同步状态
        besCollectorMapper.updateDDC(besDdc);

        if (!syncState.equals("1")){
            return;
        }


        List<BESSbPzStruct>  besSbPzStructs = sbPzStructCache.getCascadeSubordinate(besDdc.getfSysName());

        if (null == besSbPzStructs || besSbPzStructs.isEmpty())
        {
            return;
        }

        for (BESSbPzStruct besSbPzStruct : besSbPzStructs)
        {
            String type = besSbPzStruct.getF_type();
            String sysName = besSbPzStruct.getF_sys_name();
            String sysNameOld = besSbPzStruct.getF_sys_name_old();

            if (BesNodeType.MODULE.equals(type))
            {

                Map<String,Object> selectBesModule = crossEquipmentMapper.selectBesModule(sysNameOld);
                JSONObject object = new JSONObject();
                object.put("f_psys_name", besSbPzStruct.getF_psys_name());
                object.put("f_sbid_module",selectBesModule.get("F_SBID"));
                object.put("old_f_sys_name", sysNameOld);
                object.put("f_addr_module",selectBesModule.get("F_ADDR"));
                object.put("f_node_attribution",besSbPzStruct.getF_node_attribution());
                besSbdyService.synchronizeModule(object);
                continue;
            }

            if (BesNodeType.VPOINT.equals(type))
            {
                besSbdyService.synVirtualPoint(sysName);
            }
        }

    }

    // 删除一个控制器，并删除和它相关的模块和点回调（DDC）
    @Override
    public void controllerDeleteDDC(ReceiveMsg msg)
    {
        Integer index = msg.getIndex();
        String ip = msg.getIp();

        if (null == index || !StringUtils.hasText(ip))
        {
            log.warn("删除一个控制器，并删除和它相关的模块和点回调，index 不存在，或者 ip 不存在");
            return;
        }

        // 获取当前 sessionId
        String sessionId = MsgSubPubHandler.pubMsg(index, ip);

        if (!StringUtils.hasText(sessionId))
        {
            log.warn("删除一个控制器，并删除和它相关的模块和点回调，sessionId 不存在");
            return;
        }

        // 推送消息到web客户端
        WebSocketService.postEvent(sessionId, WebSocketEvent.DDC, msg);
    }

    // 获取控制器的所有配置参数回调（DDC）
    @Override
    public void controllerGetDDC(ReceiveMsg<ControllerDataDDC> controllerData)
    {
        Integer index = controllerData.getIndex();
        String ip = controllerData.getIp();

        if (null == index || !StringUtils.hasText(ip))
        {
            log.warn("获取控制器的所有配置参数回调，index 不存在，或者 ip 不存在");
            return;
        }

        // 获取当前 sessionId
        String sessionId = MsgSubPubHandler.pubMsg(index, ip);

        if (!StringUtils.hasText(sessionId))
        {
            log.warn("获取控制器的所有配置参数回调，sessionId 不存在");
            return;
        }

        // 推送消息到web客户端
        WebSocketService.postEvent(sessionId, WebSocketEvent.DDC, controllerData);
    }

    // 远程升级回调（DDC）
    @Override
    public void remoteUpgradeDDC(ReceiveMsg msg)
    {

        Integer index = msg.getIndex();
        String ip = msg.getIp();

        if (null == index || !StringUtils.hasText(ip))
        {
            log.warn("远程升级回调，index 不存在，或者 ip 不存在");
            return;
        }

        // 获取当前 sessionId
        String sessionId = MsgSubPubHandler.pubMsg(index, ip);

        if (!StringUtils.hasText(sessionId))
        {
            log.warn("远程升级回调，sessionId 不存在");
            return;
        }

        if (!Code.SUCCEED.equals(msg.getCode()))
        {
            log.warn("远程升级回调（DDC）, Code 错误码：" + msg.getCode());
            return;
        }

        // 推送消息到web客户端
        WebSocketService.postEvent(sessionId, WebSocketEvent.DDC, msg);
    }

    // 设置控制器的时间回调（DDC）
    @Override
    public void controllerTimeSetDDC(ReceiveMsg msg)
    {
        Integer index = msg.getIndex();
        String ip = msg.getIp();

        if (null == index || !StringUtils.hasText(ip))
        {
            log.warn("设置控制器的时间回调，index 不存在，或者 ip 不存在");
            return;
        }

        // 获取当前 sessionId
        String sessionId = MsgSubPubHandler.pubMsg(index, ip);

        if (!StringUtils.hasText(sessionId))
        {
            log.warn("设置控制器的时间回调，sessionId 不存在");
            return;
        }

        // 推送消息到web客户端
        WebSocketService.postEvent(sessionId, WebSocketEvent.DDC, msg);
    }

    // 获取控制器的时间回调（DDC）
    @Override
    public void controllerTimeGetDDC(ReceiveMsg<TimeData> msg)
    {
        Integer index = msg.getIndex();
        String ip = msg.getIp();

        if (null == index || !StringUtils.hasText(ip))
        {
            log.warn("获取控制器的时间回调，index 不存在，或者 ip 不存在");
            return;
        }

        // 获取当前 sessionId
        String sessionId = MsgSubPubHandler.pubMsg(index, ip);

        if (!StringUtils.hasText(sessionId))
        {
            log.warn("获取控制器的时间回调，sessionId 不存在");
            return;
        }

        // 推送消息到web客户端
        WebSocketService.postEvent(sessionId, WebSocketEvent.DDC, msg);
    }

    // 重启控制器，相当于重启复位回调（DDC）
    @Override
    public void controllerRestartDDC(ReceiveMsg msg)
    {
        Integer index = msg.getIndex();
        String ip = msg.getIp();

        if (null == index || !StringUtils.hasText(ip))
        {
            log.warn("重启DDC控制器，index 不存在，或者 ip 不存在");
            return;
        }

        // 获取当前 sessionId
        String sessionId = MsgSubPubHandler.pubMsg(index, ip);

        if (StringUtils.hasText(sessionId))
        {
            log.warn("重启DDC控制器，sessionId 不存在");
            // 推送消息到web客户端
            WebSocketService.postEvent(sessionId, WebSocketEvent.DDC, msg);
        }

        if (!Code.SUCCEED.equals(msg.getCode()))
        {
            log.warn("重启DDC控制器（DDC）, Code 错误码：" + msg.getCode());
            return;
        }

        // 根据channelId地址查询缓存数据是否存在
        BesDdc besDdc = ddcCache.getDdcByChannelId(ip);

        if (null == besDdc)
        {
            return;
        }

        String fIp = besDdc.getfIpAddr();
        String fChannelId = besDdc.getfChannelId();

        if (StringUtils.hasText(fChannelId)
                && StringUtils.hasText(fIp)
                && !fIp.equals(fChannelId))
        {
            besDdc.setfChannelId(fIp);

            besDdcMapper.updateDDCChannelId(besDdc);

            // 手动更新在线状态
            controllerState(fIp, false);
        }

        Channel channel = MsgUtil.getChannelByTokenSN(ip);
        channel.close();
    }

    // 重置控制器，恢复出厂设置，并重启回调（DDC）
    @Override
    public void controllerResetDDC(ReceiveMsg msg)
    {
        Integer index = msg.getIndex();
        String ip = msg.getIp();

        if (null == index || !StringUtils.hasText(ip))
        {
            log.warn("重置DDC控制器，index 不存在，或者 ip 不存在");
            return;
        }

        // 获取当前 sessionId
        String sessionId = MsgSubPubHandler.pubMsg(index, ip);

        if (StringUtils.hasText(sessionId))
        {
            log.warn("重置DDC控制器，sessionId 不存在");
            // 推送消息到web客户端
            WebSocketService.postEvent(sessionId, WebSocketEvent.DDC, msg);
        }

        // 消息返回成功则同步成功否则同步失败
        if (!Code.SUCCEED.equals(msg.getCode()))
        {
            return;
        }

        // 根据channelId地址查询缓存数据是否存在
        BesDdc besDdc = ddcCache.getDdcByChannelId(msg.getIp());

        if (null == besDdc)
        {
            return;
        }

        String fIp = besDdc.getfIpAddr();
        String fChannelId = besDdc.getfChannelId();

        if (StringUtils.hasText(fChannelId)
                && StringUtils.hasText(fIp)
                && !fIp.equals(fChannelId))
        {

            besDdc.setfChannelId(fIp);
            // 手动更新在线状态
            controllerState(fChannelId, false);
        }
        // 同步状态 0 未同步 1 已同步
        besDdc.setfDdcState("0");
        besDdcMapper.updateDDCChannelId(besDdc);

        Channel channel = MsgUtil.getChannelByTokenSN(ip);
        channel.close();

        List<BESSbPzStruct> besSbPzStructs = sbPzStructCache.getCascadeSubordinate(besDdc.getfSysName());

        if (null == besSbPzStructs || besSbPzStructs.isEmpty())
        {
            return;
        }

        for (BESSbPzStruct besSbPzStruct : besSbPzStructs)
        {
            String sbid = besSbPzStruct.getF_id();
            String type = besSbPzStruct.getF_type();
            String state = "0";

            if (BesNodeType.MODULE.equals(type))
            {
                BesModule besModule = new BesModule();
                besModule.setfStructId(sbid);
                besModule.setfModuleState(state);
                besPointStructMapper.updateByPrimaryKeySelectModule(besModule);
                continue;
            }

            if (BesNodeType.VPOINT.equals(type))
            {
                BesPointStruct besPointStruct = new BesPointStruct();
                besPointStruct.setfSbid(sbid);
                besPointStruct.setfSyncState(state);
                besPointStructMapper.updateByPrimaryKeySelectVpoint(besPointStruct);
                continue;
            }

            if (BesNodeType.AI.equals(type))
            {
                BesPointStruct besPointStruct = new BesPointStruct();
                besPointStruct.setfStructId(sbid);
                besPointStruct.setfAiState(state);
                besPointStructMapper.updateByPrimaryKeySelectAIPoint(besPointStruct);

                continue;
            }

            if (BesNodeType.AO.equals(type))
            {
                BesPointStruct besPointStruct = new BesPointStruct();
                besPointStruct.setfStructId(sbid);
                besPointStruct.setfAoState(state);
                besPointStructMapper.updateByPrimaryKeySelectAOPoint(besPointStruct);

                continue;

            }

            if (BesNodeType.DI.equals(type))
            {

                BesPointStruct besPointStruct = new BesPointStruct();
                besPointStruct.setfStructId(sbid);
                besPointStruct.setfDiState(state);
                besPointStructMapper.updateByPrimaryKeySelectDIPoint(besPointStruct);

                continue;

            }


            if (BesNodeType.DO.equals(type))
            {
                BesPointStruct besPointStruct = new BesPointStruct();
                besPointStruct.setfStructId(sbid);
                besPointStruct.setfDoState(state);
                besPointStructMapper.updateByPrimaryKeySelectDOPoint(besPointStruct);
            }
        }

    }

    // 新增加一个模块回调（DDC）
    @Override
    public void moduleAddDDC(ReceiveMsg<ModuleParamDDC> msg)
    {
        Integer index = msg.getIndex();
        String ip = msg.getIp();

        if (null == index || !StringUtils.hasText(ip))
        {
            log.warn("新增加一个模块回调, index 不存在，或者 ip 不存在");
            return;
        }

        // 获取当前 sessionId
        String sessionId = MsgSubPubHandler.pubMsg(index, ip);

        if (StringUtils.hasText(sessionId))
        {
            log.warn("新增加一个模块回调, sessionId 不存在");
            // 把添加控制器下位机返回的消息推送到前端页面
            WebSocketService.postEvent(sessionId, WebSocketEvent.DDC, msg);
        }

        // 消息返回成功则同步成功否则同步失败
        if (!Code.SUCCEED.equals(msg.getCode()))
        {
            log.warn("新增加一个模块回调（DDC）, Code 错误码：" + msg.getCode());
            return;
        }

        // 同步状态 0 未同步 1 已同步
        String syncState = "1";

        Integer id = msg.getData().getId();

        // 根据模块的id查询表 bes_module 模块是否存在
        Map<String, Object> module = besJobManagerMapper.getModuleById(String.valueOf(id));
        String sbid = String.valueOf(module.get("F_SBID"));

        BesModule besModule = new BesModule();
        besModule.setfModuleState(syncState);
        //DDC在线状态
        besModule.setfOnlineState("1");
        besModule.setfSbid(sbid);
        // 更新模块的同步状态
        besDdcMapper.updateModule(besModule);
    }

    // 设置一个模块的所有参数回调（DDC）
    @Override
    public void moduleSetDDC(ReceiveMsg<ModuleParamDDC> msg)
    {
        Integer index = msg.getIndex();
        String ip = msg.getIp();
        if (null == index || !StringUtils.hasText(ip))
        {
            log.warn("设置一个模块的所有参数回调（DDC）, index 不存在，或者 ip 不存在");
            return;
        }

        // 获取当前 sessionId
        String sessionId = MsgSubPubHandler.pubMsg(index, ip);

        if (StringUtils.hasText(sessionId))
        {
            log.warn("设置一个模块的所有参数回调（DDC）, sessionId 不存在");
            // 把添加控制器下位机返回的消息推送到前端页面
            WebSocketService.postEvent(sessionId, WebSocketEvent.DDC, msg);
        }

        // 消息返回成功则同步成功否则同步失败
        if (!Code.SUCCEED.equals(msg.getCode()))
        {
            log.warn("设置一个模块的所有参数回调（DDC）, Code 错误码：" + msg.getCode());
            return;
        }

        // 同步状态 0 未同步 1 已同步
        String syncState = "1";

        Integer id = msg.getData().getId();

        String f_struct_id = String.valueOf(id);

        // 根据模块的id查询表 bes_module 模块是否存在
        Map<String, Object> module = besJobManagerMapper.getModuleById(f_struct_id);

        if (null == module || module.isEmpty())
        {
            return;
        }
        String sbid = String.valueOf(module.get("F_SBID"));

        BesModule besModule = new BesModule();
        besModule.setfModuleState(syncState);
        //DDC在线状态
        besModule.setfOnlineState("1");
        besModule.setfSbid(sbid);
        // 更新模块的同步状态
        besDdcMapper.updateModule(besModule);

        //同步模块下的点位
        String moduleName = (String) module.get("F_SYS_NAME_OLD");//模块名称

        String pointName;//点的名称
        JSONObject object = new JSONObject();
        String tabName;//表名
        Map<String,Object> pointMap;

        //同步模块下的子节点,不管子节点是否同步成功
        List<Map<String, Object>> pointMapLists = crossEquipmentMapper.pointMapList(moduleName);

        for (Map<String, Object> pointMapList : pointMapLists) {

            pointName = (String) pointMapList.get("F_SYS_NAME_OLD");
            object.put("f_node_type", pointMapList.get("F_TYPE"));
            object.put("old_f_sys_name", pointName);
            object.put("f_id", pointMapList.get("F_ID"));

            String type = String.valueOf(pointMapList.get("F_TYPE")) ;

            switch (type)
            {
                case BesNodeType.DO:
                {
                    tabName = "BES_DIGIT_OUPUT";
                    //查询点位的信息
                    pointMap = crossEquipmentMapper.pointMap(tabName, pointName);
                    if (pointMap != null && !pointMap.isEmpty()) {
                        object.put("tabname", tabName);
                        besSbdyService.synchronizePoint(object);
                    }
                    break;
                }
                case BesNodeType.DI:
                {
                    tabName = "BES_DIGIT_INPUT";
                    pointMap = crossEquipmentMapper.pointMap(tabName, pointName);
                    if (pointMap != null && !pointMap.isEmpty()) {
                        object.put("tabname", tabName);
                        besSbdyService.synchronizePoint(object);
                    }

                    break;
                }
                case BesNodeType.AO:
                {
                    tabName = "BES_ANALOG_OUPUT";
                    pointMap = crossEquipmentMapper.pointMap(tabName, pointName);
                    if (pointMap != null && !pointMap.isEmpty()) {
                        object.put("tabname", tabName);
                        besSbdyService.synchronizePoint(object);
                    }
                    break;
                }
                case BesNodeType.AI:
                {
                    tabName = "BES_ANALOG_INPUT";
                    pointMap = crossEquipmentMapper.pointMap(tabName, pointName);
                    if (pointMap != null && !pointMap.isEmpty()) {
                        object.put("tabname", tabName);
                        besSbdyService.synchronizePoint(object);
                    }
                }
            }

        }
    }

    // 删除一个模块，并删除和模块相关的点回调（DDC）
    @Override
    public void moduleDeleteDDC(ReceiveMsg<ModuleParamDDC> msg)
    {
        Integer index = msg.getIndex();
        String ip = msg.getIp();

        if (null == index || !StringUtils.hasText(ip))
        {
            log.warn("删除一个模块，并删除和模块相关的点回调, index 不存在，或者 ip 不存在");
            return;
        }

        // 获取当前 sessionId
        String sessionId = MsgSubPubHandler.pubMsg(index, ip);

        if (!StringUtils.hasText(sessionId))
        {
            log.warn("删除一个模块，并删除和模块相关的点回调, sessionId 不存在");
            return;
        }

        // 推送消息到web客户端
        WebSocketService.postEvent(sessionId, WebSocketEvent.DDC, msg);
    }

    // 获取一个模块的所有配置信息回调（DDC）
    @Override
    public void moduleGetDDC(ReceiveMsg<ModuleParamDDC> msg)
    {
        Integer index = msg.getIndex();
        String ip = msg.getIp();

        if (null == index || !StringUtils.hasText(ip))
        {
            log.warn("获取控制器的所有配置参数回调（DDC），index 不存在，或者 ip 不存在");
            return;
        }

        // 获取当前 sessionId
        String sessionId = MsgSubPubHandler.pubMsg(index, ip);

        if (!StringUtils.hasText(sessionId))
        {
            log.warn("获取控制器的所有配置参数回调（DDC），sessionId 不存在");
            return;
        }

        // 推送消息到web客户端
        WebSocketService.postEvent(sessionId, WebSocketEvent.DDC, msg);
    }

    // 接收DDC实点更新的点信息回调（DDC）
    @Override
    public void realPointDataReceiveDDC(ReceiveMsg<List<PointDataDDC>> msg)
    {
        Integer index = msg.getIndex();
        String ip = msg.getIp();

        if (null == index || !StringUtils.hasText(ip))
        {
            log.warn("接收DDC实点更新的点信息回调，index 不存在，或者 ip 不存在");
            return;
        }

        List<PointDataDDC> pointList = msg.getData();

        if (null == pointList || pointList.isEmpty())
        {
            log.warn("接收DDC实点更新的点信息回调, 参数 data 不存在");
            return;
        }

        //根据ip查询当前DDC控制器点是否存在
        BesDdc besDdc = ddcCache.getDdcByChannelId(ip);

        if (besDdc == null)
        {
            log.warn("接收DDC实点更新的点信息回调，DDC控制器不存在");
            return;
        }

        Integer id;//id
        Integer value;//实时值

        List<PointDataResponse> pointDataResponseList = new ArrayList<>();

        for (PointDataDDC pointData : pointList)
        {
            id = pointData.getId();
            value = pointData.getValue();

            if (null == id || null == value)
            {
                continue;
            }

            PointDataResponse pointDataResponse1 = new PointDataResponse();

            pointDataResponse1.setId(id);
            pointDataResponse1.setValue(String.valueOf(value));

            BESSbPzStruct besSbPzStruct = sbPzStructCache.getCachedElementById(String.valueOf(id));

            if (null == besSbPzStruct)
            {
                continue;
            }

            pointDataResponse1.setAlias(besSbPzStruct.getF_nick_name());
            pointDataResponse1.setName(besSbPzStruct.getF_sys_name());
            pointDataResponse1.setSysNameOld(besSbPzStruct.getF_sys_name_old());
            pointDataResponse1.setUnit(besSbPzStruct.getUnit());
            pointDataResponseList.add(pointDataResponse1);


            if (BesNodeType.AO.equals(besSbPzStruct.getF_type()) || BesNodeType.AI.equals(besSbPzStruct.getF_type()))
            {

                Integer accuracyNum = Integer.parseInt(besSbPzStruct.getF_accuracy()); ;//获取精度
                Double valueDouble = value/Math.pow(10, accuracyNum);//获取精度转换后的实时值
                String values = subZeroAndDot(String.valueOf(valueDouble));
                pointDataResponse1.setValue(values);

                Double value1 = Double.parseDouble(values);

                // todo 待优化（添加缓存减少查表）
                // comPlanVariatInfoHandler.planVariatInfoCache(id,value1);

                // 实时数据不在存表
                // besPointStructMapper.updatePointValue(tabName, String.valueOf(id), values);

                planningHandler.planingHandler(id,values);
                // 更新缓存
                besSbPzStruct.setF_init_val(values);

            }else
            {

                String values = String.valueOf(value);

                Double value1 = Double.parseDouble(values);

                // todo 待优化（添加缓存减少查表）
                // comPlanVariatInfoHandler.planVariatInfoCache(id,value1);

                // 实时数据不在存表
                // besPointStructMapper.updatePointValue(tabName, String.valueOf(id), String.valueOf(value));

                planningHandler.planingHandler(id,values);
                // 更新缓存
                besSbPzStruct.setF_init_val(values);

            }
        }


        alarmHandler.DDCalarmHandle(msg);//报警

        ReceiveMsg<List<PointDataResponse>> msg1 = new ReceiveMsg<>();
        msg1.setData(pointDataResponseList);
        msg1.setCode(msg.getCode());
        msg1.setIndex(msg.getIndex());
        msg1.setIp(msg.getIp());
        // 推送消息到web客户端
        // WebSocketService.postEvent(WebSocketEvent.DDC, msg1);

        distributePostEvent(WebSocketEvent.DDC, msg1);

        functionPointStateService.stateReceiptApiHandle(pointDataResponseList);

        //向第三个系统推送数据
        SessionManager.sessionMap.forEach((sessionId, wbSession)->{
            String names = SessionManager.sessionIdNameDataMap.get(sessionId);
            JSONArray jsonArray = (JSONArray) JSONArray.parse(names);
            if(jsonArray == null){
                //没有请求数据不推送
                return;
            }
            List<Map> list = new ArrayList<>();

            pointDataResponseList.forEach(pointDataResponse -> {
                String name = pointDataResponse.getName();
                String val = pointDataResponse.getValue();
                String unit = pointDataResponse.getUnit();
                if(jsonArray.contains(name)){
                    Map<String,String> map = new HashMap<>();
                    map.put("name",name);
                    map.put("value",val);
                    map.put("unit",unit);
                    list.add(map);
                }
            });

            if(list.isEmpty()){
                //没有数据不推送
                return;
            }
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("list",list);
            TextMessage textMessage = new TextMessage(jsonObject.toString());
            try {
                wbSession.sendMessage(textMessage);
//                log.info("向前端推送实时数据："+jsonObject.toString());
            } catch (IOException e) {
                log.error("向前端推送实时数据报错:"+e.getMessage());
            }
        });
    }

    // 获取一个模块的错误代码回调（DDC）
    @Override
    public void moduleErrorCodeGetDDC(ReceiveMsg<ErrorCodeDDC> msg)
    {

        Integer index = msg.getIndex();
        String ip = msg.getIp();
        String onlineStatus;

        BesDdc besDdc = ddcCache.getDdcByChannelId(ip);

        if (besDdc == null)
        {
            log.warn("获取一个模块的错误代码回调（DDC）, 当前控制器不存在 ip:" + ip);
            return;
        }

        ReceiveMsg<List<BESSbPzStruct>> msgList = new ReceiveMsg();

        if (null == index || !StringUtils.hasText(ip))
        {
            log.warn("获取一个模块的错误代码回调（DDC）, index 不存在，或者 ip 不存在");
            return;
        }

        if (null == msg.getData() || null == msg.getData().getErrorCode())
        {
            log.warn("获取一个模块的错误代码回调（DDC）, id 不存在");
            return;
        }
        Integer id = msg.getData().getId();

        BESSbPzStruct besSbPzStruct = sbPzStructCache.getCachedElementById(String.valueOf(id));

        if (besSbPzStruct == null)
        {
            log.warn("获取一个模块的错误代码回调（DDC）, 设备树节点不存在");
            return;
        }


        msgList.setIndex(index);
        msgList.setIp(ip);
        msgList.setCode(1);

        if (msg.getData().getErrorCode().equals(0)) {//在线

            onlineStatus = "1";
        } else {//离线
            onlineStatus = "0";
        }
        // 更新设备树状态

        List<BESSbPzStruct> besSbPzStructs = sbPzStructCache.getCascadeSubordinate(besSbPzStruct.getF_sys_name());

        // 更新缓存设备树在线状态
        String finalOnlineStatus = onlineStatus;
        besSbPzStructs.forEach(item -> item.setF_status(finalOnlineStatus));

        msgList.setData(besSbPzStructs);

        // 推送消息到web客户端
        WebSocketService.postEvent(WebSocketEvent.DDC, msgList);

    }

    // 批量获取模块的错误代码回调（DDC）
    @Override
    public void moduleErrorCodeGetDDC_ALL(ReceiveMsg<List<ErrorCodeDDC>> msg)
    {

        Integer index = msg.getIndex();
        String ip = msg.getIp();
        String onlineStatus = null;
        ReceiveMsg<List<BESSbPzStruct>> msgList = new ReceiveMsg();

        if (null == index || !StringUtils.hasText(ip))
        {
            log.warn("批量接收一个模块的错误代码（DDC）, index 不存在，或者 ip 不存在");
            return;
        }

        List<ErrorCodeDDC> errorCodeDDC = msg.getData();
        if (null == errorCodeDDC || errorCodeDDC.isEmpty())
        {
            log.warn("批量接收一个模块的错误代码, 参数 data 不存在");
            return;
        }

        //根据ip查询当前DDC控制器点是否存在
        BesDdc besDdc = ddcCache.getDdcByChannelId(ip);

        if (besDdc == null){
            log.warn("批量接收一个模块的错误代码，DDC控制器不存在");
            return;
        }

        for (ErrorCodeDDC errorCode : errorCodeDDC) {
            Integer id = errorCode.getId();
            //查找当前id所对应的模块名称

            BESSbPzStruct besSbPzStruct = sbPzStructCache.getCachedElementById(String.valueOf(id));

            if(besSbPzStruct == null) {
                continue;
            }

            msgList.setIp(ip);
            msgList.setIndex(index);

            if (errorCode.getErrorCode().equals(0)) {//在线

                onlineStatus = "1";
            } else {//离线
                onlineStatus = "0";
            }
            // 更新设备树状态
            List<BESSbPzStruct> besSbPzStructs = sbPzStructCache.getCascadeSubordinate(besSbPzStruct.getF_sys_name());

            // 更新缓存设备树在线状态
            String finalOnlineStatus = onlineStatus;
            besSbPzStructs.forEach(item -> item.setF_status(finalOnlineStatus));

            msgList.setData(besSbPzStructs);

            // 推送消息到web客户端
            WebSocketService.postEvent(WebSocketEvent.DDC, msgList);
        }

//        Integer index = msg.getIndex();
//        String ip = msg.getIp();
//        String onlineStatus;
//
//        BesDdc besDdc = ddcCache.getDdcByChannelId(ip);
//
//        if (besDdc == null)
//        {
//            log.warn("获取一个模块的错误代码回调（DDC）, 当前控制器不存在 ip:" + ip);
//            return;
//        }
//
//        ReceiveMsg<List<BESSbPzStruct>> msgList = new ReceiveMsg();
//
//        if (null == index || !StringUtils.hasText(ip))
//        {
//            log.warn("获取一个模块的错误代码回调（DDC）, index 不存在，或者 ip 不存在");
//            return;
//        }
//
//        if (null == msg.getData() || null == msg.getData().getErrorCode())
//        {
//            log.warn("获取一个模块的错误代码回调（DDC）, id 不存在");
//            return;
//        }
//        Integer id = msg.getData().getId();
//
//        BESSbPzStruct besSbPzStruct = sbPzStructCache.getCachedElementById(String.valueOf(id));
//
//        if (besSbPzStruct == null)
//        {
//            log.warn("获取一个模块的错误代码回调（DDC）, 设备树节点不存在");
//            return;
//        }
//
//
//        msgList.setIndex(index);
//        msgList.setIp(ip);
//        msgList.setCode(1);
//
//        if (msg.getData().getErrorCode().equals(0)) {//在线
//
//            onlineStatus = "1";
//        } else {//离线
//            onlineStatus = "0";
//        }
//        // 更新设备树状态
//
//        List<BESSbPzStruct> besSbPzStructs = sbPzStructCache.getCascadeSubordinate(besSbPzStruct.getF_sys_name());
//
//        // 更新缓存设备树在线状态
//        String finalOnlineStatus = onlineStatus;
//        besSbPzStructs.forEach(item -> item.setF_status(finalOnlineStatus));
//
//        msgList.setData(besSbPzStructs);
//
//        // 推送消息到web客户端
//        WebSocketService.postEvent(WebSocketEvent.DDC, msgList);

    }

    // 新增加一个逻辑点回调（DDC）
    @Override
    public void pointAddDDC(ReceiveMsg<PointParamDDC> msg)
    {
        Integer index = msg.getIndex();
        String ip = msg.getIp();

        if (null == index || !StringUtils.hasText(ip))
        {
            log.warn("新增加一个逻辑点回调接收到的消息, index 不存在，或者 ip 不存在");
            return;
        }

        Integer id;

        if (null == msg.getData() || null == msg.getData().getId())
        {
            log.warn("新增加一个逻辑点回调接收到的消息, id 不存在");
            return;
        }

        id = msg.getData().getId();


        BESSbPzStruct besSbPzStruct = sbPzStructCache.getCachedElementById(String.valueOf(id));

        if (besSbPzStruct == null)
        {
            log.warn("新增加一个逻辑点回调接收到的消息, 根据 id 没有查出设备树节点信息");
            return;
        }

        String nodeType = besSbPzStruct.getF_type();
        String structId = String.valueOf(id);
        String sysName = besSbPzStruct.getF_sys_name();

        Integer code = msg.getCode();

        if (Code.SUCCEED.equals(code))
        {
            JSONObject obj = new JSONObject();

            switch (nodeType)
            {
                case BesNodeType.AI: // AI 点
                {

                    obj.put("f_id", structId);
                    obj.put("f_ai_state", "1");
                    besSbdyMapper.updateAIPointTable(obj);

                    break;
                }
                case BesNodeType.AO: // AO 点
                {
                    obj.put("f_id", structId);
                    obj.put("f_ao_state", "1");
                    besSbdyMapper.updateAOPointTable(obj);
                    break;
                }
                case BesNodeType.DI: // DI 点
                {
                    obj.put("f_id", structId);
                    obj.put("f_di_state", "1");
                    besSbdyMapper.updateDIPointTable(obj);
                    break;
                }
                case BesNodeType.DO: // DO 点
                {
                    obj.put("f_id", structId);
                    obj.put("f_do_state", "1");
                    besSbdyMapper.updateDOPointTable(obj);
                    break;
                }
                case BesNodeType.VPOINT: // 虚点
                {
                    obj.put("name", sysName);
                    obj.put("syncState", "1");
                    besSbdyMapper.updateVirtualPointInfo(obj);
                    break;
                }
            }
        }

        // 获取当前 sessionId
        String sessionId = MsgSubPubHandler.pubMsg(index, ip);

        if (!StringUtils.hasText(sessionId))
        {
            log.warn("新增加一个逻辑点回调接收到的消息, sessionId 不存在");
            return;
        }

        // 推送消息到web客户端
        WebSocketService.postEvent(sessionId, WebSocketEvent.DDC, msg);

    }

    // 设置一个逻辑点的所有参数回调（DDC）
    @Override
    public void pointParamSetDDC(ReceiveMsg<PointParamDDC> msg)
    {
        Integer index = msg.getIndex();
        String ip = msg.getIp();

        if (null == index || !StringUtils.hasText(ip))
        {
            log.warn("设置一个逻辑点的所有参数回调接收到的消息, index 不存在，或者 ip 不存在");
            return;
        }

        Integer id;

        if (null == msg.getData() || null == msg.getData().getId())
        {
            log.warn("设置一个逻辑点的所有参数回调接收到的消息, id 不存在");
            return;
        }

        id = msg.getData().getId();


        BESSbPzStruct besSbPzStruct = sbPzStructCache.getCachedElementById(String.valueOf(id));

        if (besSbPzStruct == null)
        {
            log.warn("设置一个逻辑点的所有参数回调接收到的消息, 根据 id 没有查出设备树节点信息");
            return;
        }

        String nodeType = String.valueOf(besSbPzStruct.getF_type());
        String structId = String.valueOf(id);
        String sysName = besSbPzStruct.getF_sys_name();

        Integer code = msg.getCode();

        if (Code.SUCCEED.equals(code))
        {
            JSONObject obj = new JSONObject();

            switch (nodeType)
            {
                case BesNodeType.AI: // AI 点
                {

                    obj.put("f_id", structId);
                    obj.put("f_ai_state", "1");
                    besSbdyMapper.updateAIPointTable(obj);

                    break;
                }
                case BesNodeType.AO: // AO 点
                {
                    obj.put("f_id", structId);
                    obj.put("f_ao_state", "1");
                    besSbdyMapper.updateAOPointTable(obj);
                    break;
                }
                case BesNodeType.DI: // DI 点
                {
                    obj.put("f_id", structId);
                    obj.put("f_di_state", "1");
                    besSbdyMapper.updateDIPointTable(obj);
                    break;
                }
                case BesNodeType.DO: // DO 点
                {
                    obj.put("f_id", structId);
                    obj.put("f_do_state", "1");
                    besSbdyMapper.updateDOPointTable(obj);
                    break;
                }
                case BesNodeType.VPOINT: // 虚点
                {
                    obj.put("name", sysName);
                    obj.put("syncState", "1");
                    besSbdyMapper.updateVirtualPointInfo(obj);
                    break;
                }
            }
        }

        // 获取当前 sessionId
        String sessionId = MsgSubPubHandler.pubMsg(index, ip);

        if (!StringUtils.hasText(sessionId))
        {
            log.warn("设置一个逻辑点的所有参数回调接收到的消息, sessionId 不存在");
            return;
        }

        // 推送消息到web客户端
        WebSocketService.postEvent(sessionId, WebSocketEvent.DDC, msg);
    }

    // 设置一个逻辑点的值回调（DDC）
    @Override
    public void pointValueSetDDC(ReceiveMsg<PointDataDDC> msg)
    {
        Integer value;

        Integer index = msg.getIndex();
        String ip = msg.getIp();

        if (null == index || !StringUtils.hasText(ip))
        {
            log.warn("设置一个逻辑点的值回调，index 不存在，或者 ip 不存在");
            return;
        }

        // 获取当前 sessionId
        String sessionId = MsgSubPubHandler.pubMsg(index, ip);

        if (!StringUtils.hasText(sessionId))
        {
            log.warn("设置一个逻辑点的值回调，sessionId 不存在");
            return;
        }

        // 推送消息到web客户端
        WebSocketService.postEvent(sessionId, WebSocketEvent.DDC, msg);

        Integer code = msg.getCode();

        if (!Code.SUCCEED.equals(code))
        {
            log.warn("设置一个逻辑点的值失败，返回错误码：" + code);
            return;
        }

        Integer id = msg.getData().getId();

        BESSbPzStruct besSbPzStruct = sbPzStructCache.getCachedElementById(String.valueOf(id));

        if (besSbPzStruct == null)
        {
            log.warn("设置一个逻辑点的值回调， 设备树点位不存在");
            return;
        }

        BESSbTreeNodeType besSbTreeNodeType = sbTreeNodeTypeCache.getCachedElement(besSbPzStruct.getF_type());

        if (BesNodeType.VPOINT.equals(besSbPzStruct.getF_type())) {

            value = msg.getData().getValue();

            //更改实点点位的实时值 实时值不在更新数据表
            // besSbdyMapper.updateVPointByid("BES_VPOINT",String.valueOf(id),value);

            ReceiveMsg<List<PointDataDDC>> msgs = new ReceiveMsg<>();

            msgs.setCode(0);
            msgs.setIndex(DDCCmd.VIRTUAL_POINT_DATA_RECEIVE);
            msgs.setIp(ip);

            List<PointDataDDC> pointDataList = new ArrayList<>();

            PointDataDDC pointDataDDC = new PointDataDDC();

            pointDataDDC.setId(id);
            pointDataDDC.setValue(value);

            pointDataList.add(pointDataDDC);

            msgs.setData(pointDataList);

            virtualPointDataReceiveDDC(msgs);

        } else {
            Integer workMode = msg.getData().getWorkMode();

            besSbdyMapper.updatePointByWorkMode(besSbTreeNodeType.getF_node_table(), String.valueOf(workMode),String.valueOf(id));

            value = msg.getData().getValue();

            //更改实点点位的实时值
            // 实时值不在更新表
            // besSbdyMapper.updatePointByid(besSbTreeNodeType.getF_node_table(), String.valueOf(id),value);

            ReceiveMsg<List<PointDataDDC>> msgs = new ReceiveMsg<>();

            msgs.setCode(0);
            msgs.setIndex(DDCCmd.REAL_POINT_DATA_RECEIVE);
            msgs.setIp(ip);

            List<PointDataDDC> pointDataList = new ArrayList<>();

            PointDataDDC pointDataDDC = new PointDataDDC();

            pointDataDDC.setId(id);
            pointDataDDC.setValue(value);

            pointDataList.add(pointDataDDC);

            msgs.setData(pointDataList);

            realPointDataReceiveDDC(msgs);
        }

    }

    // 设置逻辑点的值，根据点的名字（DDC）(未使用)
    @Override
    public void pointValueByNameSetDDC(ReceiveMsg<PointParamDDC> msg)
    {
        Map<String,Object> pointMapByid;
        Integer value;
        Double values ;

        Integer index = msg.getIndex();
        String ip = msg.getIp();

        if (null == index || !StringUtils.hasText(ip))
        {
            log.warn("设置一个逻辑点的值回调，index 不存在，或者 ip 不存在");
            return;
        }

        // 获取当前 sessionId
        String sessionId = MsgSubPubHandler.pubMsg(index, ip);

        if (!StringUtils.hasText(sessionId))
        {
            log.warn("设置一个逻辑点的值回调，sessionId 不存在");
            return;
        }

        // 推送消息到web客户端
        WebSocketService.postEvent(sessionId, WebSocketEvent.DDC, msg);

        Integer code = msg.getCode();

        if (!Code.SUCCEED.equals(code))
        {
            log.warn("根据名称设置一个逻辑点的值失败，返回错误码：" + code);
            return;
        }

        Integer id = msg.getData().getId();

        //根据id查询当前点位的表名
        String tabName = crossEquipmentMapper.selectTableName(id);


        if (tabName.equals("BES_VPOINT")) {
            //查询虚点的点位信息
            pointMapByid = crossEquipmentMapper.selectVPointMapByID(id);

            if (pointMapByid.get("F_NODE_TYPE").equals("5")) { //VAO点位

                value = (Integer) pointMapByid.get("F_INIT_VAL");

                Double f_accuracy = Double.valueOf(String.valueOf(pointMapByid.get("F_ACCURACY"))) ;//获取精度

                values = value / Math.pow(10, f_accuracy);//获取精度转换后的实时值
                value = Integer.parseInt(new java.text.DecimalFormat("0").format(values));

            } else {

                value = (Integer) pointMapByid.get("F_INIT_VAL");
            }

            ReceiveMsg<List<PointDataDDC>> msgs = new ReceiveMsg<>();

            msgs.setCode(0);
            msgs.setIndex(DDCCmd.VIRTUAL_POINT_DATA_RECEIVE);
            msgs.setIp(ip);

            List<PointDataDDC> pointDataList = new ArrayList<>();

            PointDataDDC pointDataDDC = new PointDataDDC();

            pointDataDDC.setId(id);
            pointDataDDC.setValue(value);

            pointDataList.add(pointDataDDC);

            msgs.setData(pointDataList);

            virtualPointDataReceiveDDC(msgs);

        } else {

            //查询实点的点位信息
            pointMapByid = crossEquipmentMapper.selectPointMapByID(tabName,id);
            if (tabName.equals("BES_ANALOG_OUPUT")) {

                Double f_accuracy = Double.valueOf(String.valueOf(pointMapByid.get("F_ACCURACY"))) ;//获取精度
                value = (Integer) pointMapByid.get("F_INIT_VAL");
                values = value / Math.pow(10, f_accuracy);//获取精度转换后的实时值
                value = Integer.parseInt(new java.text.DecimalFormat("0").format(values));
            } else {

                value = (Integer) pointMapByid.get("F_INIT_VAL");
            }


            ReceiveMsg<List<PointDataDDC>> msgs = new ReceiveMsg<>();

            msgs.setCode(0);
            msgs.setIndex(DDCCmd.REAL_POINT_DATA_RECEIVE);
            msgs.setIp(ip);

            List<PointDataDDC> pointDataList = new ArrayList<>();

            PointDataDDC pointDataDDC = new PointDataDDC();

            pointDataDDC.setId(id);

            pointDataDDC.setValue(value);

            pointDataList.add(pointDataDDC);

            msgs.setData(pointDataList);

            realPointDataReceiveDDC(msgs);
        }
    }

    // 删除一个逻辑点回调（DDC）
    @Override
    public void pointDeleteDDC(ReceiveMsg<PointParamDDC> msg)
    {
        Integer index = msg.getIndex();
        String ip = msg.getIp();

        if (null == index || !StringUtils.hasText(ip))
        {
            log.warn("index 不存在，或者 ip 不存在");
            return;
        }

        // 获取当前 sessionId
        String sessionId = MsgSubPubHandler.pubMsg(index, ip);

        if (!StringUtils.hasText(sessionId))
        {
            log.warn("sessionId 不存在");
            return;
        }

        // 推送消息到web客户端
        WebSocketService.postEvent(sessionId, WebSocketEvent.DDC, msg);
    }

    // 获取一个逻辑点的所有配置参数回调（DDC）
    @Override
    public void pointParamGetDDC(ReceiveMsg<PointParamDDC> msg)
    {
        Integer index = msg.getIndex();
        String ip = msg.getIp();

        if (null == index || !StringUtils.hasText(ip))
        {
            log.warn("获取一个逻辑点的所有配置参数回调，index 不存在，或者 ip 不存在");
            return;
        }

        // 获取当前 sessionId
        String sessionId = MsgSubPubHandler.pubMsg(index, ip);

        if (!StringUtils.hasText(sessionId))
        {
            log.warn("获取一个逻辑点的所有配置参数回调，sessionId 不存在");
            return;
        }

        // 推送消息到web客户端
        WebSocketService.postEvent(sessionId, WebSocketEvent.DDC, msg);
    }

    // 获取一个逻辑点的报警信息回调（DDC）
    @Override
    public void pointAlarmDataGetDDC(ReceiveMsg<AlarmPointDataDDC> msg)
    {
        // 未使用，告警逻辑由上位机实现
    }

    // 接收DDC的全部点信息回调（DDC）
    @Override
    public void pointDataAllReceiveDDC(ReceiveMsg<List<PointDataDDC>> msg)
    {

        Integer index = msg.getIndex();
        String ip = msg.getIp();

        if (null == index || !StringUtils.hasText(ip))
        {
            log.warn("接收DDC的全部点信息回调，index 不存在，或者 ip 不存在");
            return;
        }

        List<PointDataDDC> pointList = msg.getData();

        if (null == pointList || pointList.isEmpty())
        {
            log.warn("接收DDC的全部点信息回调, 参数 data 不存在");
            return;
        }

        //根据ip查询当前DDC控制器点是否存在
        BesDdc besDdc = ddcCache.getDdcByChannelId(ip);

        if (besDdc == null){
            log.warn("接收DDC的全部点信息回调，DDC控制器不存在");
            return;
        }

        Integer id;//id
        Integer value;//实时值

        List<Map> dataList = new ArrayList<>();

        List<PointDataResponse> pointDataResponseList = new ArrayList<>();

        for (PointDataDDC pointData : pointList)
        {
            id = pointData.getId();
            value = pointData.getValue();

            if (null == id || null == value)
            {
                continue;
            }

            PointDataResponse pointDataResponse = new PointDataResponse();

            pointDataResponse.setId(id);

            //根据id查询当前点信息
            BESSbPzStruct besSbPzStruct = sbPzStructCache.getCachedElementById(String.valueOf(id));

            if (null == besSbPzStruct)
            {
                continue;
            }

            if (BesNodeType.VPOINT.equals(besSbPzStruct.getF_type()))
            {
                BesVirtualPoint besVirtualPoint = virtualPointCache.getCachedElement(besSbPzStruct.getF_sys_name());

                if (null == besVirtualPoint)
                {
                    continue;
                }

                Integer pointType = Integer.parseInt(besVirtualPoint.getF_node_type());

                pointDataResponse.setAlias(besVirtualPoint.getF_nick_name());
                pointDataResponse.setName(besVirtualPoint.getF_sys_name());
                pointDataResponse.setSysNameOld(besVirtualPoint.getF_sys_name_old());
                pointDataResponse.setUnit(besVirtualPoint.getF_engineer_unit());

                if (pointType == PointType.POINT_TYPE_VIRTUAL_AI
                        || pointType == PointType.POINT_TYPE_VIRTUAL_AO)
                {
                    Integer accuracyNum = Integer.parseInt(besVirtualPoint.getF_accuracy());//获取精度
                    Double valueDouble = value/Math.pow(10, accuracyNum);//获取精度转换后的实时值
                    String values = subZeroAndDot(String.valueOf(valueDouble));
                    pointDataResponse.setValue(values);

                    // Double value1 = Double.parseDouble(values);

                    // todo 待优化（添加缓存减少查表）
                    // comPlanVariatInfoHandler.planVariatInfoCache(id,value1);

                    // 修改相应的虚点位表的实时数据
                    // 不在保存实时数据到数据库
                    // besPointStructMapper.updateVPointValue(String.valueOf(id), values);
                    planningHandler.planingHandler(id,values);
                    // 更新缓存
                    besSbPzStruct.setF_init_val(values);

                    if (pointType == PointType.POINT_TYPE_VIRTUAL_AI && "0".equals(besVirtualPoint.getF_energystatics()))
                    {

                        Integer savePeriod = besDdc.getfSavePeriod();
                        Integer recordUploadPeriod = besVirtualPoint.getRecordUploadPeriod();

                        if (recordUploadPeriod == null) {
                            besVirtualPoint.setRecordUploadPeriod(1);
                        }

                        if (savePeriod == null || savePeriod.equals(recordUploadPeriod)) {
                            Map<String, Object> energyData = new HashMap<>();

                            energyData.put("F_ENERGY_TYPE", besVirtualPoint.getF_energy_type());
                            energyData.put("F_SYS_NAME", besVirtualPoint.getF_sys_name());
                            energyData.put("F_SYS_NAME_OLD", besVirtualPoint.getF_sys_name_old());
                            energyData.put("F_DATE", valueDouble);

                            dataList.add(energyData);

                            besVirtualPoint.setRecordUploadPeriod(1);

                        }else if (besVirtualPoint.getRecordUploadPeriod() < savePeriod){
                            besVirtualPoint.setRecordUploadPeriod(1 + besVirtualPoint.getRecordUploadPeriod());

                        }else {
                            besVirtualPoint.setRecordUploadPeriod(1);
                        }

                    }


                } else {

                    pointDataResponse.setValue(String.valueOf(value));

                    String values = String.valueOf(value);

                    // Double value1 = Double.parseDouble(values);

                    // todo 待优化（添加缓存减少查表）
                    // comPlanVariatInfoHandler.planVariatInfoCache(id,value1);

                    // 修改相应的虚点位表的实时数据
					// 不在保存实时数据到数据库
                    // besPointStructMapper.updateVPointValue(String.valueOf(id), String.valueOf(value));
                    planningHandler.planingHandler(id,values);
					// 更新缓存
					besSbPzStruct.setF_init_val(values);
                }
                pointDataResponseList.add(pointDataResponse);

            }else
            {

                pointDataResponse.setAlias(besSbPzStruct.getF_nick_name());
                pointDataResponse.setName(besSbPzStruct.getF_sys_name());
                pointDataResponse.setSysNameOld(besSbPzStruct.getF_sys_name_old());
                pointDataResponse.setUnit(besSbPzStruct.getUnit());

                if (BesNodeType.AI.equals(besSbPzStruct.getF_type()) || BesNodeType.AO.equals(besSbPzStruct.getF_type()))
                {

                    Integer accuracyNum = Integer.parseInt(besSbPzStruct.getF_accuracy()) ;//获取精度
                    Double valueDouble = value/Math.pow(10, accuracyNum);//获取精度转换后的实时值
                    String values = subZeroAndDot(String.valueOf(valueDouble));
                    pointDataResponse.setValue(values);

                    // Double value1 = Double.parseDouble(values);

                    // todo 待优化（添加缓存减少查表）
                    // comPlanVariatInfoHandler.planVariatInfoCache(id,value1);

                    // 实时数据不在存储到数据库
                    // besPointStructMapper.updatePointValue(tabName, String.valueOf(id), values);
                    planningHandler.planingHandler(id,values);
                    // 更新缓存
                    besSbPzStruct.setF_init_val(values);

                    BesAiPoint besAiPoint = null;
                    BesAoPoint besAoPoint = null;
                    if (BesNodeType.AI.equals(besSbPzStruct.getF_type()))
                    {

                        besAiPoint = aiPointCache.getCachedElementBySbId(String.valueOf(id));

                        String energystatics = besAiPoint.getF_energystatics();

                        if ("0".equals(energystatics))
                        {
                            Integer savePeriod = besDdc.getfSavePeriod();
                            Integer recordUploadPeriod = besAiPoint.getRecordUploadPeriod();

                            if (recordUploadPeriod == null) {
                                besAiPoint.setRecordUploadPeriod(1);
                            }

                            if (savePeriod == null || savePeriod.equals(recordUploadPeriod)) {

                                Map<String, Object> energyData = new HashMap<>();

                                energyData.put("F_ENERGY_TYPE", besAiPoint.getF_energy_type());
                                energyData.put("F_SYS_NAME", besAiPoint.getF_sys_name());
                                energyData.put("F_SYS_NAME_OLD", besAiPoint.getF_sys_name_old());
                                energyData.put("F_DATE", valueDouble);

                                dataList.add(energyData);

                                besAiPoint.setRecordUploadPeriod(1);

                            }else if (besAiPoint.getRecordUploadPeriod() < savePeriod){
                                besAiPoint.setRecordUploadPeriod(1 + besAiPoint.getRecordUploadPeriod());
                            }else {
                                besAiPoint.setRecordUploadPeriod(1);
                            }

                        }

                    } else {
                        besAoPoint = aoPointCache.getCachedElementBySbId(String.valueOf(id));

                        String energystatics = besAoPoint.getfEnergystatics();

                        if ("0".equals(energystatics))
                        {
                            Integer savePeriod = besDdc.getfSavePeriod();
                            Integer recordUploadPeriod = besAoPoint.getRecordUploadPeriod();

                            if (recordUploadPeriod == null) {
                                besAoPoint.setRecordUploadPeriod(1);
                            }

                            if (savePeriod == null || savePeriod.equals(recordUploadPeriod)) {

                                Map<String, Object> energyData = new HashMap<>();

                                energyData.put("F_ENERGY_TYPE", besAoPoint.getfEnergyType());
                                energyData.put("F_SYS_NAME", besAoPoint.getfSysName());
                                energyData.put("F_SYS_NAME_OLD", besAoPoint.getfSysNameOld());
                                energyData.put("F_DATE", valueDouble);

                                dataList.add(energyData);

                                besAoPoint.setRecordUploadPeriod(1);

                            }else if (besAoPoint.getRecordUploadPeriod() < savePeriod){
                                besAoPoint.setRecordUploadPeriod(1 + besAoPoint.getRecordUploadPeriod());
                            }else {
                                besAoPoint.setRecordUploadPeriod(1);
                            }

                        }
                    }






                } else {

                    pointDataResponse.setValue(String.valueOf(value));

                    String values = String.valueOf(value);

                    // Double value1 = Double.parseDouble(values);

                    // todo 待优化（添加缓存减少查表）
                    // comPlanVariatInfoHandler.planVariatInfoCache(id,value1);

                    // 实时数据不在更新数据库
                    // besPointStructMapper.updatePointValue(tabName, String.valueOf(id), String.valueOf(value));

                    planningHandler.planingHandler(id,values);
                    // 更新缓存
                    besSbPzStruct.setF_init_val(values);

                }
                pointDataResponseList.add(pointDataResponse);
            }
        }

        // 存储虚点能耗数据
        if (!dataList.isEmpty()) {
            EnergyCollectHandler.ammeterDataHandle(dataList, ip);
        }

        alarmHandler.DDCalarmHandle(msg);//报警

        // 推送消息到web客户端
        ReceiveMsg<List<PointDataResponse>> msg1 = new ReceiveMsg<>();
        msg1.setData(pointDataResponseList);
        msg1.setCode(msg.getCode());
        msg1.setIndex(msg.getIndex());
        msg1.setIp(msg.getIp());

        // WebSocketService.postEvent(WebSocketEvent.DDC, msg1);
        distributePostEvent(WebSocketEvent.DDC, msg1);

        functionPointStateService.stateReceiptApiHandle(pointDataResponseList);
    }

    // 接收虚点信息回调（DDC）
    @Override
    public void virtualPointDataReceiveDDC(ReceiveMsg<List<PointDataDDC>> msg)
    {
        Integer index = msg.getIndex();
        String ip = msg.getIp();

        if (null == index || !StringUtils.hasText(ip))
        {
            log.warn("接收虚点信息回调，index 不存在，或者 ip 不存在");
            return;
        }

        List<PointDataDDC> pointList = msg.getData();

        if (null == pointList || pointList.isEmpty())
        {
            log.warn("接收虚点信息回调, 参数 data 不存在");
            return;
        }

        //根据ip查询当前DDC控制器点是否存在
        BesDdc besDdc = ddcCache.getDdcByChannelId(ip);

        if (null == besDdc)
        {
            log.warn("接收虚点信息回调，DDC控制器不存在");
            return;
        }

        Integer id;//id
        Integer value;//实时值

        List<PointDataResponse> pointDataResponseList = new ArrayList<>();

        for (PointDataDDC pointData : pointList)
        {
            id = pointData.getId();
            value = pointData.getValue();

            if (null == id || null == value)
            {
                continue;
            }

            PointDataResponse pointDataResponse1 = new PointDataResponse();

            pointDataResponse1.setId(id);

            BesVirtualPoint besVirtualPoint = virtualPointCache.getCachedElementBySbId(String.valueOf(id));

            if (null == besVirtualPoint)
            {
                continue;
            }

            BESSbPzStruct besSbPzStruct = sbPzStructCache.getCachedElement(besVirtualPoint.getF_sys_name());

            if (null == besSbPzStruct)
            {
                continue;
            }

            pointDataResponse1.setAlias(besVirtualPoint.getF_nick_name());
            pointDataResponse1.setName(besVirtualPoint.getF_sys_name());
            pointDataResponse1.setSysNameOld(besVirtualPoint.getF_sys_name_old());
            pointDataResponse1.setUnit(besVirtualPoint.getF_engineer_unit());

            Integer pointType = Integer.parseInt(besVirtualPoint.getF_node_type());


            if (pointType == PointType.POINT_TYPE_VIRTUAL_AI
                    || pointType == PointType.POINT_TYPE_VIRTUAL_AO)
            {
                Integer accuracyNum = Integer.parseInt(besVirtualPoint.getF_accuracy()) ;//获取精度
                Double valueDouble = value / Math.pow(10, accuracyNum);//获取精度转换后的实时值
                String values = subZeroAndDot(String.valueOf(valueDouble));
                pointDataResponse1.setValue(values);

                Double value1 = Double.parseDouble(values);

                // todo 待优化（添加缓存减少查表）
                // comPlanVariatInfoHandler.planVariatInfoCache(id,value1);

                // 实时数据不在更新数据库
                // besPointStructMapper.updateVPointValue(String.valueOf(id), values);
                planningHandler.planingHandler(id,values);
                // 更新缓存数据
                besSbPzStruct.setF_init_val(values);

            } else {

                pointDataResponse1.setValue(String.valueOf(value));

                String values = String.valueOf(value);

                Double value1 = Double.parseDouble(values);

                // todo 待优化（添加缓存减少查表）
                // comPlanVariatInfoHandler.planVariatInfoCache(id,value1);

                // 实时数据不在更新数据库
                // besPointStructMapper.updateVPointValue(String.valueOf(id), String.valueOf(value));
                planningHandler.planingHandler(id,values);
                // 更新缓存数据
                besSbPzStruct.setF_init_val(values);
            }

            pointDataResponseList.add(pointDataResponse1);
        }

        // todo
        alarmHandler.DDCalarmHandle(msg);//报警

        // 推送消息到web客户端
        ReceiveMsg<List<PointDataResponse>> msg1 = new ReceiveMsg<>();
        msg1.setData(pointDataResponseList);
        msg1.setCode(msg.getCode());
        msg1.setIndex(msg.getIndex());
        msg1.setIp(msg.getIp());

        // WebSocketService.postEvent(WebSocketEvent.DDC, msg1);
        distributePostEvent(WebSocketEvent.DDC, msg1);

        functionPointStateService.stateReceiptApiHandle(pointDataResponseList);
    }

    // 增加一个场景回调（DDC）
    @Override
    public void sceneAddDDC(ReceiveMsg<SceneDataDDC> msg)
    {

    }

    // 设置一个模块的所有参数回调（DDC）
    @Override
    public void sceneParamSetDDC(ReceiveMsg<SceneDataDDC> msg)
    {
        Integer index = msg.getIndex();
        String ip = msg.getIp();

        if (null == index || !StringUtils.hasText(ip))
        {
            log.warn("接收场景信息回调，index 不存在，或者 ip 不存在");
            return;
        }

        // 获取当前 sessionId
        String sessionId = MsgSubPubHandler.pubMsg(index, ip);

        if (!StringUtils.hasText(sessionId))
        {
            log.warn("获取一个场景的所有配置参数回调（DDC），sessionId 不存在");
            return;
        }

        // 推送消息到web客户端
        WebSocketService.postEvent(sessionId, WebSocketEvent.DDC, msg);
    }

    // 删除一个场景回调（DDC）
    @Override
    public void sceneDeleteDDC(ReceiveMsg<SceneDataDDC> msg)
    {
        Integer index = msg.getIndex();
        String ip = msg.getIp();

        if (null == index || !StringUtils.hasText(ip))
        {
            log.warn("删除场景信息回调，index 不存在，或者 ip 不存在");
            return;
        }

        // 获取当前 sessionId
        String sessionId = MsgSubPubHandler.pubMsg(index, ip);

        if (!StringUtils.hasText(sessionId))
        {
            log.warn("删除一个场景的所有配置参数回调（DDC），sessionId 不存在");
            return;
        }

        // 推送消息到web客户端
        WebSocketService.postEvent(sessionId, WebSocketEvent.DDC, msg);
    }

    //获取一个场景下的模式信息的回调
    @Override
    public void controlParamDDC(ReceiveMsg<SceneDataDDC> msg) {

        Integer index = msg.getIndex();
        String ip = msg.getIp();

        if (null == index || !StringUtils.hasText(ip))
        {
            log.warn("接收DDC场景模式点信息回调，index 不存在，或者 ip 不存在");
            return;
        }

        SceneDataDDC sceneDataDDC = msg.getData();

        if (null == sceneDataDDC)
        {
            log.warn("接收DDC场景模式点信息回调, 参数 data 不存在");
            return;
        }

        // 获取当前 sessionId
        String sessionId = MsgSubPubHandler.pubMsg(index, ip);
        // 推送消息到web客户端
        WebSocketService.postEvent(sessionId,WebSocketEvent.DDC, msg);
    }

    //删除一个场景模式(DDC)
    @Override
    public void sceneModeParamDeleteDDC(ReceiveMsg<SceneParamDDC> msg){

        Integer index = msg.getIndex();
        String ip = msg.getIp();

        if (null == index || !StringUtils.hasText(ip))
        {
            log.warn("删除DDC场景模式点信息回调，index 不存在，或者 ip 不存在");
            return;
        }

        SceneParamDDC sceneParamDDC = msg.getData();

        if (null == sceneParamDDC)
        {
            log.warn("删除DDC场景模式点信息回调, 参数 data 不存在");
            return;
        }

        // 获取当前 sessionId
        String sessionId = MsgSubPubHandler.pubMsg(index, ip);

        // 推送消息到web客户端
        WebSocketService.postEvent(sessionId,WebSocketEvent.DDC, msg);
    }

    // 获取一个场景的配置信息回调（DDC）
    @Override
    public void sceneParamGetDDC(ReceiveMsg<SceneParamDDC> msg)
    {


    }

    // 增加一条计划回调（DDC）
    @Override
    public void planAddDDC(ReceiveMsg<PlanParamDDC> msg)
    {

        Integer index = msg.getIndex();
        String ip = msg.getIp();

        if (null == index || !StringUtils.hasText(ip))
        {
            log.warn("新增DDC计划信息回调，index 不存在，或者 ip 不存在");
            return;
        }

        PlanParamDDC planParamDDC = msg.getData();

        if (null == planParamDDC)
        {
            log.warn("新增DDC计划信息回调, 参数 data 不存在");
            return;
        }

        // 获取当前 sessionId
        String sessionId = MsgSubPubHandler.pubMsg(index, ip);

        // 推送消息到web客户端
        WebSocketService.postEvent(sessionId, WebSocketEvent.DDC, msg);
    }

    // 修改一条计划的所有参数回调（DDC）
    @Override
    public void planParamSetDDC(ReceiveMsg<PlanParamDDC> msg)
    {

        Integer index = msg.getIndex();

        String ip = msg.getIp();

        if (null == index || !StringUtils.hasText(ip))
        {
            log.warn("同步DDC计划信息回调，index 不存在，或者 ip 不存在");
            return;
        }

        PlanParamDDC planParamDDC = msg.getData();

        if (null == planParamDDC)
        {
            log.warn("同步DDC计划信息回调, 参数 data 不存在");
            return;
        }

        // 获取当前 sessionId
        String sessionId = MsgSubPubHandler.pubMsg(index, ip);

        // 推送消息到web客户端
        WebSocketService.postEvent(sessionId, WebSocketEvent.DDC, msg);
    }

    // 删除一条计划回调（DDC）
    @Override
    public void planDeleteDDC(ReceiveMsg<PlanParamDDC> msg)
    {

        Integer index = msg.getIndex();
        String ip = msg.getIp();

        if (null == index || !StringUtils.hasText(ip))
        {
            log.warn("删除DDC计划信息回调，index 不存在，或者 ip 不存在");
            return;
        }

//        PlanParamDDC planParamDDC = msg.getData();
//
//        if (null == planParamDDC)
//        {
//            log.warn("删除DDC计划信息回调, 参数 data 不存在");
//            return;
//        }

        // 获取当前 sessionId
        String sessionId = MsgSubPubHandler.pubMsg(index, ip);

        // 推送消息到web客户端
        WebSocketService.postEvent(sessionId, WebSocketEvent.DDC, msg);
    }

    // 获取一条计划的所有参数回调（DDC）
    @Override
    public void planParamGetDDC(ReceiveMsg<PlanParamDDC> msg)
    {

        Integer index = msg.getIndex();
        String ip = msg.getIp();
        if (null == index || !StringUtils.hasText(ip))
        {
            log.warn("接收DDC计划信息回调，index 不存在，或者 ip 不存在");
            return;
        }

        PlanParamDDC planParamDDC = msg.getData();

        if (null == planParamDDC)
        {
            log.warn("接收DDC计划信息回调, 参数 data 不存在");
            return;
        }

        // 获取当前 sessionId
        String sessionId = MsgSubPubHandler.pubMsg(index, ip);

        // 推送消息到web客户端
        WebSocketService.postEvent(sessionId, WebSocketEvent.DDC, msg);
    }


    /*************************************** LDC（照明）*****************************************/

    // 新增一个IP路由器返回回调（LDC）
    @Override
    public void controllerAddLDC(ReceiveMsg msg)
    {
        Integer index = msg.getIndex();
        String ip = msg.getIp();

        if (null == index || !StringUtils.hasText(ip))
        {
            log.warn("新增一个IP路由器返回回调，index 不存在，或者 ip 不存在");
            return;
        }

        // 获取当前 sessionId
        String sessionId = MsgSubPubHandler.pubMsg(index, ip);

        if (!StringUtils.hasText(sessionId))
        {
            log.warn("新增一个IP路由器返回回调，sessionId 不存在");
            return;
        }

        // 同步状态 0 未同步 1 已同步
        String syncState = "0";

        // 把添加控制器下位机返回的消息推送到前端页面
        WebSocketService.postEvent(sessionId, WebSocketEvent.LDC, msg);

        // 消息返回成功则同步成功否则同步失败
        if (Code.SUCCEED.equals(msg.getCode()))
        {
            syncState = "1";

            controllerState(ip, true);
        }

        // 根据channelId地址查询缓存数据是否存在
        BesDdc besDdc = ddcCache.getDdcByChannelId(msg.getIp());

        if (null == besDdc)
        {
            return;
        }

        besDdc.setfDdcState(syncState);
        besDdc.setfDdcOnlinestate("1");

        // 更新 ip 路由器的同步状态
        besCollectorMapper.updateDDC(besDdc);
    }

    // 设置IP路由器的所有参数回调（LDC）
    @Override
    public void controllerSetLDC(ReceiveMsg msg)
    {
        Integer index = msg.getIndex();
        String ip = msg.getIp();

        if (null == index || !StringUtils.hasText(ip))
        {
            log.warn("设置IP路由器的所有参数回调，index 不存在，或者 ip 不存在");
            return;
        }

        controllerState(ip, true);

        // 获取当前 sessionId
        String sessionId = MsgSubPubHandler.pubMsg(index, ip);

        if (StringUtils.hasText(sessionId))
        {
            // 把添加控制器下位机返回的消息推送到前端页面
            WebSocketService.postEvent(sessionId, WebSocketEvent.LDC, msg);

        } else {
            //定时同步设备树任务,插入执行日志
            Date date = new Date();
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String dateTime = dateFormat.format(date);

            BesSyncLog besSyncLog = syncLogCache.getCachedElement(ip);

            if (besSyncLog != null){
                besSyncLog.setF_callback_status(msg.getCode().toString());
                besSyncLog.setF_callback_time(dateTime);

                sysJobPlanMapper.insertSyncLog(besSyncLog);
                syncLogCache.deleteOneSyncLogCache(ip);
            }

        }


        // 消息返回成功则同步成功否则同步失败
        if (!Code.SUCCEED.equals(msg.getCode()))
        {
            return;
        }

        // 同步状态 0 未同步 1 已同步
        String syncState = "1";

        // 根据channelId地址查询缓存数据是否存在
        BesDdc besDdc = ddcCache.getDdcByChannelId(ip);

        if (null == besDdc)
        {
            return;
        }

        besDdc.setfDdcState(syncState);
        besDdc.setfDdcOnlinestate("1");

        // 更新ddc控制器的同步状态
        besCollectorMapper.updateDDC(besDdc);

        List<BESSbPzStruct>  besSbPzStructs = sbPzStructCache.getCascadeSubordinate(besDdc.getfSysName());

        if (null == besSbPzStructs || besSbPzStructs.isEmpty())
        {
            return;
        }

        for (BESSbPzStruct besSbPzStruct : besSbPzStructs)
        {
            String type = besSbPzStruct.getF_type();
            String sysName = besSbPzStruct.getF_sys_name();
            String sysNameOld = besSbPzStruct.getF_sys_name_old();

            if (BesNodeType.MODULE.equals(type))
            {

                Map<String,Object> selectBesModule = crossEquipmentMapper.selectBesModule(sysNameOld);
                JSONObject object = new JSONObject();
                object.put("f_psys_name", besSbPzStruct.getF_psys_name());
                object.put("f_sbid_module",selectBesModule.get("F_SBID"));
                object.put("old_f_sys_name", sysNameOld);
                object.put("f_addr_module",selectBesModule.get("F_ADDR"));
                object.put("f_node_attribution",besSbPzStruct.getF_node_attribution());
                besSbdyService.synchronizeModule(object);
                continue;
            }

            if (BesNodeType.VPOINT.equals(type))
            {
                besSbdyService.synVirtualPoint(sysName);
            }
        }
    }

    // 删除一个IP路由器，并删除和它相关的模块和点回调（LDC）
    @Override
    public void controllerDeleteLDC(ReceiveMsg msg)
    {
        Integer index = msg.getIndex();
        String ip = msg.getIp();

        if (null == index || !StringUtils.hasText(ip))
        {
            log.warn("删除一个IP路由器，index 不存在，或者 ip 不存在");
            return;
        }

        // 获取当前 sessionId
        String sessionId = MsgSubPubHandler.pubMsg(index, ip);

        if (!StringUtils.hasText(sessionId))
        {
            log.warn("删除一个IP路由器，sessionId 不存在");
            return;
        }

        // 推送消息到web客户端
        WebSocketService.postEvent(sessionId, WebSocketEvent.LDC, msg);
    }

    // 获取IP路由器的所有配置参数回调（LDC）
    @Override
    public void controllerGetLDC(ReceiveMsg<ControllerDataLDC> controllerData)
    {
        Integer index = controllerData.getIndex();
        String ip = controllerData.getIp();

        if (null == index || !StringUtils.hasText(ip))
        {
            log.warn("获取IP路由器的所有配置参数回调，index 不存在，或者 ip 不存在");
            return;
        }

        // 获取当前 sessionId
        String sessionId = MsgSubPubHandler.pubMsg(index, ip);

        if (!StringUtils.hasText(sessionId))
        {
            log.warn("获取IP路由器的所有配置参数回调，sessionId 不存在");
            return;
        }

        // 推送消息到web客户端
        WebSocketService.postEvent(sessionId, WebSocketEvent.LDC, controllerData);
    }

    // 远程升级回调（LDC）
    @Override
    public void remoteUpgradeLDC(ReceiveMsg msg)
    {

        Integer index = msg.getIndex();
        String ip = msg.getIp();

        if (null == index || !StringUtils.hasText(ip))
        {
            log.warn("远程升级回调，index 不存在，或者 ip 不存在");
            return;
        }

        // 获取当前 sessionId
        String sessionId = MsgSubPubHandler.pubMsg(index, ip);

        if (!StringUtils.hasText(sessionId))
        {
            log.warn("远程升级回调，sessionId 不存在");
            return;
        }

        if (!Code.SUCCEED.equals(msg.getCode()))
        {
            log.warn("远程升级回调（LDC）, Code 错误码：" + msg.getCode());
            return;
        }

        // 推送消息到web客户端
        WebSocketService.postEvent(sessionId, WebSocketEvent.LDC, msg);
    }

    // 设置IP路由器的时间回调（LDC）
    @Override
    public void controllerTimeSetLDC(ReceiveMsg msg)
    {
        Integer index = msg.getIndex();
        String ip = msg.getIp();

        if (null == index || !StringUtils.hasText(ip))
        {
            log.warn("设置IP路由器的时间回调，index 不存在，或者 ip 不存在");
            return;
        }

        // 获取当前 sessionId
        String sessionId = MsgSubPubHandler.pubMsg(index, ip);

        if (!StringUtils.hasText(sessionId))
        {
            log.warn("设置IP路由器的时间回调，sessionId 不存在");
            return;
        }

        // 推送消息到web客户端
        WebSocketService.postEvent(sessionId, WebSocketEvent.LDC, msg);
    }

    // 获取IP路由器的时间回调（LDC）
    @Override
    public void controllerTimeGetLDC(ReceiveMsg<TimeData> msg)
    {
        Integer index = msg.getIndex();
        String ip = msg.getIp();

        if (null == index || !StringUtils.hasText(ip))
        {
            log.warn("获取IP路由器的时间回调，index 不存在，或者 ip 不存在");
            return;
        }

        // 获取当前 sessionId
        String sessionId = MsgSubPubHandler.pubMsg(index, ip);

        if (!StringUtils.hasText(sessionId))
        {
            log.warn("获取IP路由器的时间回调，sessionId 不存在");
            return;
        }

        // 推送消息到web客户端
        WebSocketService.postEvent(sessionId, WebSocketEvent.LDC, msg);
    }

    // 重启IP路由器，相当于重启复位回调（LDC）
    @Override
    public void controllerRestartLDC(ReceiveMsg msg)
    {
        Integer index = msg.getIndex();
        String ip = msg.getIp();

        if (null == index || !StringUtils.hasText(ip))
        {
            log.warn("重启IP路由器，index 不存在，或者 ip 不存在");
            return;
        }

        // 获取当前 sessionId
        String sessionId = MsgSubPubHandler.pubMsg(index, ip);

        if (StringUtils.hasText(sessionId))
        {
            log.warn("重启IP路由器，sessionId 不存在");
            // 推送消息到web客户端
            WebSocketService.postEvent(sessionId, WebSocketEvent.LDC, msg);
        }

        if (!Code.SUCCEED.equals(msg.getCode()))
        {
            return;
        }


        // 根据channelId地址查询缓存数据是否存在
        BesDdc besDdc = ddcCache.getDdcByChannelId(ip);

        if (null == besDdc)
        {
            return;
        }

        String fIp = besDdc.getfIpAddr();
        String fChannelId = besDdc.getfChannelId();

        if (StringUtils.hasText(fChannelId)
                && StringUtils.hasText(fIp)
                && !fIp.equals(fChannelId))
        {

            besDdc.setfChannelId(fIp);

            besDdcMapper.updateDDCChannelId(besDdc);

            // 手动更新在线状态
            controllerState(fIp, false);
        }

        Channel channel = MsgUtil.getChannelByTokenSN(ip);
        channel.close();
    }

    // 重置IP路由器，恢复出厂设置，并重启回调（LDC）
    @Override
    public void controllerResetLDC(ReceiveMsg msg)
    {
        Integer index = msg.getIndex();
        String ip = msg.getIp();

        if (null == index || !StringUtils.hasText(ip))
        {
            log.warn("重置IP路由器，index 不存在，或者 ip 不存在");
            return;
        }

        // 获取当前 sessionId
        String sessionId = MsgSubPubHandler.pubMsg(index, ip);

        if (StringUtils.hasText(sessionId))
        {
            log.warn("重置IP路由器，sessionId 不存在");
            // 推送消息到web客户端
            WebSocketService.postEvent(sessionId, WebSocketEvent.LDC, msg);
        }

        // 消息返回成功则同步成功否则同步失败
        if (!Code.SUCCEED.equals(msg.getCode()))
        {
            return;
        }

        // 根据channelId地址查询缓存数据是否存在
        BesDdc besDdc = ddcCache.getDdcByChannelId(msg.getIp());

        if (null == besDdc)
        {
            return;
        }

        String fIp = besDdc.getfIpAddr();
        String fChannelId = besDdc.getfChannelId();

        if (StringUtils.hasText(fChannelId)
                && StringUtils.hasText(fIp)
                && !fIp.equals(fChannelId))
        {

            besDdc.setfChannelId(fIp);
            // 手动更新在线状态
            controllerState(fChannelId, false);
        }
        // 同步状态 0 未同步 1 已同步
        besDdc.setfDdcState("0");
        besDdcMapper.updateDDCChannelId(besDdc);

        Channel channel = MsgUtil.getChannelByTokenSN(ip);
        channel.close();

        List<BESSbPzStruct> besSbPzStructs = sbPzStructCache.getCascadeSubordinate(besDdc.getfSysName());

        if (null == besSbPzStructs || besSbPzStructs.isEmpty())
        {
            return;
        }

        for (BESSbPzStruct besSbPzStruct : besSbPzStructs)
        {
            String sbid = besSbPzStruct.getF_id();
            String type = besSbPzStruct.getF_type();
            String state = "0";

            if (BesNodeType.MODULE.equals(type))
            {
                BesModule besModule = new BesModule();
                besModule.setfStructId(sbid);
                besModule.setfModuleState(state);
                besPointStructMapper.updateByPrimaryKeySelectModule(besModule);
                continue;
            }

            if (BesNodeType.VPOINT.equals(type))
            {
                BesPointStruct besPointStruct = new BesPointStruct();
                besPointStruct.setfSbid(sbid);
                besPointStruct.setfSyncState(state);
                besPointStructMapper.updateByPrimaryKeySelectVpoint(besPointStruct);
                continue;
            }

            if (BesNodeType.AI.equals(type))
            {

                BesPointStruct besPointStruct = new BesPointStruct();
                besPointStruct.setfStructId(sbid);
                besPointStruct.setfAiState(state);
                besPointStructMapper.updateByPrimaryKeySelectAIPoint(besPointStruct);

                continue;
            }

            if (BesNodeType.AO.equals(type))
            {
                BesPointStruct besPointStruct = new BesPointStruct();
                besPointStruct.setfStructId(sbid);
                besPointStruct.setfAoState(state);
                besPointStructMapper.updateByPrimaryKeySelectAOPoint(besPointStruct);

                continue;

            }

            if (BesNodeType.DI.equals(type))
            {
                BesPointStruct besPointStruct = new BesPointStruct();
                besPointStruct.setfStructId(sbid);
                besPointStruct.setfDiState(state);
                besPointStructMapper.updateByPrimaryKeySelectDIPoint(besPointStruct);

                continue;

            }


            if (BesNodeType.DO.equals(type))
            {
                BesPointStruct besPointStruct = new BesPointStruct();
                besPointStruct.setfStructId(sbid);
                besPointStruct.setfDoState(state);
                besPointStructMapper.updateByPrimaryKeySelectDOPoint(besPointStruct);
            }
        }
    }

    // 新增加一个模块回调（LDC）
    @Override
    public void moduleAddLDC(ReceiveMsg<ModuleParamLDC> msg)
    {
        Integer index = msg.getIndex();
        String ip = msg.getIp();


        if (null == index || !StringUtils.hasText(ip))
        {
            log.warn("新增加一个模块回调, index 不存在，或者 ip 不存在");
            return;
        }

        // 获取当前 sessionId
        String sessionId = MsgSubPubHandler.pubMsg(index, ip);

        if (StringUtils.hasText(sessionId))
        {
            log.warn("新增加一个模块回调, sessionId 不存在");
            // 把添加控制器下位机返回的消息推送到前端页面
            WebSocketService.postEvent(sessionId, WebSocketEvent.LDC, msg);
        }

        // 同步状态 0 未同步 1 已同步
        String syncState = "1";

        // 消息返回成功则同步成功否则同步失败
        if (!Code.SUCCEED.equals(msg.getCode()))
        {
            log.warn("新增加一个模块回调（LDC）, Code 错误码：" + msg.getCode());
            return;
        }
        Integer id = msg.getData().getId();
        // 根据模块的id查询表 bes_module 模块是否存在
        Map<String, Object> module = besJobManagerMapper.getModuleById(String.valueOf(id));
        String sbid = String.valueOf(module.get("F_SBID"));

        BesModule besModule = new BesModule();
        besModule.setfModuleState(syncState);
        //DDC在线状态
        besModule.setfOnlineState("1");
        besModule.setfSbid(sbid);
        // 更新模块的同步状态
        besDdcMapper.updateModule(besModule);
    }

    // 设置一个模块的所有参数回调（LDC）
    @Override
    public void moduleSetLDC(ReceiveMsg<ModuleParamLDC> msg)
    {

        Integer index = msg.getIndex();
        String ip = msg.getIp();

        if (null == index || !StringUtils.hasText(ip))
        {
            log.warn("设置一个模块的所有参数回调（LDC）, index 不存在，或者 ip 不存在");
            return;
        }

        // 获取当前 sessionId
        String sessionId = MsgSubPubHandler.pubMsg(index, ip);

        if (StringUtils.hasText(sessionId))
        {
            log.warn("设置一个模块的所有参数回调（LDC）, sessionId 不存在");
            // 把添加控制器下位机返回的消息推送到前端页面
            WebSocketService.postEvent(sessionId, WebSocketEvent.LDC, msg);
        }

        // 同步状态 0 未同步 1 已同步
        String syncState = "1";

        // 消息返回成功则同步成功否则同步失败
        if (!Code.SUCCEED.equals(msg.getCode()))
        {
            log.warn("设置一个模块的所有参数回调（LDC）, Code 错误码：" + msg.getCode());
            return;
        }

        Integer id = msg.getData().getId();
        String f_struct_id = String.valueOf(id);

        // 根据模块的id查询表 bes_module 模块是否存在
        Map<String, Object> module = besJobManagerMapper.getModuleById(f_struct_id);

        if (null == module || module.isEmpty())
        {
            return;
        }

        String sbid = String.valueOf(module.get("F_SBID"));

        BesModule besModule = new BesModule();
        besModule.setfModuleState(syncState);
        //DDC在线状态
        besModule.setfOnlineState("1");
        besModule.setfSbid(sbid);
        // 更新模块的同步状态
        besDdcMapper.updateModule(besModule);

        //同步模块下的点位
        String moduleName = (String) module.get("F_SYS_NAME_OLD");//模块名称

        String pointName;//点的名称
        JSONObject object = new JSONObject();
        String tabName;//表名
        Map<String,Object> pointMap;

        //同步模块下的子节点,不管子节点是否同步成功
        List<Map<String, Object>> pointMapLists = crossEquipmentMapper.pointMapList(moduleName);

        for (Map<String, Object> pointMapList : pointMapLists) {

            pointName = (String) pointMapList.get("F_SYS_NAME_OLD");
            object.put("f_node_type", pointMapList.get("F_TYPE"));
            object.put("old_f_sys_name", pointName);
            object.put("f_id", pointMapList.get("F_ID"));

            String type = String.valueOf(pointMapList.get("F_TYPE")) ;

            switch (type)
            {
                case BesNodeType.DO:
                {
                    tabName = "BES_DIGIT_OUPUT";
                    //查询点位的信息
                    pointMap = crossEquipmentMapper.pointMap(tabName, pointName);
                    if (pointMap != null && !pointMap.isEmpty()) {
                        object.put("tabname", tabName);
                        besSbdyService.synchronizePoint(object);
                    }
                    break;
                }
                case BesNodeType.DI:
                {
                    tabName = "BES_DIGIT_INPUT";
                    pointMap = crossEquipmentMapper.pointMap(tabName, pointName);
                    if (pointMap != null && !pointMap.isEmpty()) {
                        object.put("tabname", tabName);
                        besSbdyService.synchronizePoint(object);
                    }

                    break;
                }
                case BesNodeType.AO:
                {
                    tabName = "BES_ANALOG_OUPUT";
                    pointMap = crossEquipmentMapper.pointMap(tabName, pointName);
                    if (pointMap != null && !pointMap.isEmpty()) {
                        object.put("tabname", tabName);
                        besSbdyService.synchronizePoint(object);
                    }
                    break;
                }
                case BesNodeType.AI:
                {
                    tabName = "BES_ANALOG_INPUT";
                    pointMap = crossEquipmentMapper.pointMap(tabName, pointName);
                    if (pointMap != null && !pointMap.isEmpty()) {
                        object.put("tabname", tabName);
                        besSbdyService.synchronizePoint(object);
                    }
                }
            }

        }
        object.put("f_id",pointMapLists.get(0));
//        //发送完成所有点的同步后,发送这个命令
//        besSbdyService.synchronizePointElse(object);
    }

    // 删除一个模块，并删除和模块相关的点回调（LDC）
    @Override
    public void moduleDeleteLDC(ReceiveMsg<ModuleParamLDC> msg)
    {
        Integer index = msg.getIndex();
        String ip = msg.getIp();

        if (null == index || !StringUtils.hasText(ip))
        {
            log.warn("删除一个模块（LDC），并删除和模块相关的点回调, index 不存在，或者 ip 不存在");
            return;
        }

        // 获取当前 sessionId
        String sessionId = MsgSubPubHandler.pubMsg(index, ip);

        if (!StringUtils.hasText(sessionId))
        {
            log.warn("删除一个模块（LDC），并删除和模块相关的点回调, sessionId 不存在");
            return;
        }

        // 推送消息到web客户端
        WebSocketService.postEvent(sessionId, WebSocketEvent.LDC, msg);
    }

    // 获取一个模块的所有配置信息回调（LDC）
    @Override
    public void moduleGetLDC(ReceiveMsg<ModuleParamLDC> msg)
    {
        Integer index = msg.getIndex();
        String ip = msg.getIp();

        if (null == index || !StringUtils.hasText(ip))
        {
            log.warn("获取控制器的所有配置参数回调（LDC），index 不存在，或者 ip 不存在");
            return;
        }

        // 获取当前 sessionId
        String sessionId = MsgSubPubHandler.pubMsg(index, ip);

        if (!StringUtils.hasText(sessionId))
        {
            log.warn("获取控制器的所有配置参数回调（LDC），sessionId 不存在");
            return;
        }

        // 推送消息到web客户端
        WebSocketService.postEvent(sessionId, WebSocketEvent.LDC, msg);
    }

    // 接收LDC实点更新的点信息回调（LDC）
    @Override
    public void realPointDataReceiveLDC(ReceiveMsg<List<PointDataLDC>> msg)
    {
        Integer index = msg.getIndex();
        String ip = msg.getIp();

        if (null == index || !StringUtils.hasText(ip))
        {
            log.warn("接收LDC实点更新的点信息回调，index 不存在，或者 ip 不存在");
            return;
        }

        List<PointDataLDC> pointList = msg.getData();

        if (null == pointList || pointList.isEmpty())
        {
            log.warn("接收LDC实点更新的点信息回调, 参数 data 不存在");
            return;
        }

        //根据ip查询当前DDC控制器点是否存在
        BesDdc besDdc = ddcCache.getDdcByChannelId(ip);

        if (besDdc == null)
        {
            log.warn("接收LDC实点更新的点信息回调，DDC控制器不存在");
            return;
        }

        String tabName;//表名
        Integer id;//id
        Integer value;//实时值
        Map<String,Object> pointMap;

        List<PointDataResponse> pointDataResponseList = new ArrayList<>();

        for (PointDataLDC pointData : pointList)
        {
            id = pointData.getId();
            value = pointData.getValue();

            if (null == id || null == value)
            {
                continue;
            }

            PointDataResponse pointDataResponse1 = new PointDataResponse();

            pointDataResponse1.setId(id);

            BESSbPzStruct besSbPzStruct = sbPzStructCache.getCachedElementById(String.valueOf(id));

            if (null == besSbPzStruct)
            {
                continue;
            }

            pointDataResponse1.setAlias(besSbPzStruct.getF_nick_name());
            pointDataResponse1.setName(besSbPzStruct.getF_sys_name());
            pointDataResponse1.setSysNameOld(besSbPzStruct.getF_sys_name_old());
            pointDataResponse1.setUnit(besSbPzStruct.getUnit());
            pointDataResponseList.add(pointDataResponse1);

            if (BesNodeType.AO.equals(besSbPzStruct.getF_type()) || BesNodeType.AI.equals(besSbPzStruct.getF_type()))
            {

                Integer accuracyNum = Integer.parseInt(besSbPzStruct.getF_accuracy()); ;//获取精度
                Double valueDouble = value/Math.pow(10, accuracyNum);//获取精度转换后的实时值
                String values = subZeroAndDot(String.valueOf(valueDouble));
                pointDataResponse1.setValue(values);

                Double value1 = Double.parseDouble(values);

                // todo 待优化（添加缓存减少查表）
                // comPlanVariatInfoHandler.planVariatInfoCache(id,value1);

                // 实时数据不在存表
                // besPointStructMapper.updatePointValue(tabName, String.valueOf(id), values);

                planningHandler.planingHandler(id,values);
                // 更新缓存
                besSbPzStruct.setF_init_val(values);

            }else
            {
                pointDataResponse1.setValue(String.valueOf(value));

                String values = String.valueOf(value);

                Double value1 = Double.parseDouble(values);

                // todo 待优化（添加缓存减少查表）
                // comPlanVariatInfoHandler.planVariatInfoCache(id,value1);

                // 实时数据不在存表
                // besPointStructMapper.updatePointValue(tabName, String.valueOf(id), String.valueOf(value));
                planningHandler.planingHandler(id,values);
                // 更新缓存
                //点位系统名称
                String sysname = besSbPzStruct.getF_sys_name();
                //点位缓存值
                String cacheValue = besSbPzStruct.getF_init_val();
                //点位当前值
                //推送逻辑：更新缓存之前比较缓存值和当前数据
                //如果当前值等于100（故障），但是缓存数据不是100（说明状态是刚刚变化的），就推送数据
                if(LIGHT_FAULT_VALUE.equals(values) && !cacheValue.equals(values)){
                    String alertMsg = "照明设备故障";
                    pushDeviceAlarm.pushDeviceOfflineInfo(sysname,alertMsg);
                }
                besSbPzStruct.setF_init_val(values);
            }
        }


        alarmHandler.DDCalarmHandle(msg);//报警

        ReceiveMsg<List<PointDataResponse>> msg1 = new ReceiveMsg<>();
        msg1.setData(pointDataResponseList);
        msg1.setCode(msg.getCode());
        msg1.setIndex(msg.getIndex());
        msg1.setIp(msg.getIp());
        // 推送消息到web客户端
        // WebSocketService.postEvent(WebSocketEvent.LDC, msg1);

        distributePostEvent(WebSocketEvent.LDC, msg1);

        functionPointStateService.stateReceiptApiHandle(pointDataResponseList);
    }

    // 获取一个模块的错误代码回调（LDC）
    @Override
    public void moduleErrorCodeGetLDC(ReceiveMsg<ErrorCodeLDC> msg)
    {
        Integer index = msg.getIndex();
        String ip = msg.getIp();
        String onlineStatus;
        ReceiveMsg<List<BESSbPzStruct>> msgList = new ReceiveMsg();

        BesDdc besDdc = ddcCache.getDdcByChannelId(ip);

        if (besDdc == null)
        {
            log.warn("获取一个模块的错误代码回调（DDC）, 当前控制器不存在 ip:" + ip);
            return;
        }

        if (null == index || !StringUtils.hasText(ip))
        {
            log.warn("获取一个模块的错误代码回调（LDC）, index 不存在，或者 ip 不存在");
            return;
        }

        if (null == msg.getData() || null == msg.getData().getErrorCode())
        {
            log.warn("获取一个模块的错误代码回调（LDC）, data 不存在");
            return;
        }
        Integer id = msg.getData().getId();

        if (id == null) {
            log.warn("获取一个模块的错误代码回调（LDC）, id 不存在");
            return;
        }
        BESSbPzStruct besSbPzStruct = sbPzStructCache.getCachedElementById(String.valueOf(id));

        if (besSbPzStruct == null)
        {
            log.warn("获取一个模块的错误代码回调（LDC）, 设备树节点不存在");
            return;
        }

        msgList.setIp(ip);
        msgList.setIndex(index);

        if (msg.getData().getErrorCode().equals(0)) {//在线

            onlineStatus = "1";
        } else {//离线
            onlineStatus = "0";
        }
        // 更新设备树状态

        // 更新设备树状态

        List<BESSbPzStruct> besSbPzStructs = sbPzStructCache.getCascadeSubordinate(besSbPzStruct.getF_sys_name());

        // 更新缓存设备树在线状态
        String finalOnlineStatus = onlineStatus;
        besSbPzStructs.forEach(item -> item.setF_status(finalOnlineStatus));

        msgList.setData(besSbPzStructs);

        // 推送消息到web客户端
        WebSocketService.postEvent(WebSocketEvent.LDC, msgList);
    }

    //批量接收一个模块的错误代码
    @Override
    public void moduleErrorCodeGetLDCALL(ReceiveMsg<List<ErrorCodeLDC>> msg) {

        Integer index = msg.getIndex();
        String ip = msg.getIp();
        String onlineStatus = null;
        ReceiveMsg<List<BESSbPzStruct>> msgList = new ReceiveMsg();

        if (null == index || !StringUtils.hasText(ip))
        {
            log.warn("批量接收一个模块的错误代码（LDC）, index 不存在，或者 ip 不存在");
            return;
        }

        List<ErrorCodeLDC> errorCodeLDC = msg.getData();
        if (null == errorCodeLDC || errorCodeLDC.isEmpty())
        {
            log.warn("批量接收一个模块的错误代码, 参数 data 不存在");
            return;
        }

        //根据ip查询当前DDC控制器点是否存在
        BesDdc besDdc = ddcCache.getDdcByChannelId(ip);

        if (besDdc == null){
            log.warn("批量接收一个模块的错误代码，DDC控制器不存在");
            return;
        }

        for (ErrorCodeLDC errorCode : errorCodeLDC) {
            Integer id = errorCode.getId();
            //查找当前id所对应的模块名称

            BESSbPzStruct besSbPzStruct = sbPzStructCache.getCachedElementById(String.valueOf(id));

            if(besSbPzStruct == null) {
                continue;
            }

            msgList.setIp(ip);
            msgList.setIndex(index);

            if (errorCode.getErrorCode().equals(0)) {//在线

                onlineStatus = "1";
            } else {//离线
                onlineStatus = "0";
            }
            // 更新设备树状态
            List<BESSbPzStruct> besSbPzStructs = sbPzStructCache.getCascadeSubordinate(besSbPzStruct.getF_sys_name());

            // 更新缓存设备树在线状态
            String finalOnlineStatus = onlineStatus;
            besSbPzStructs.forEach(item -> item.setF_status(finalOnlineStatus));

            msgList.setData(besSbPzStructs);

            // 推送消息到web客户端
            WebSocketService.postEvent(WebSocketEvent.LDC, msgList);
        }

    }

    // 新增加一个逻辑点回调（LDC）
    @Override
    public void pointAddLDC(ReceiveMsg<PointParamLDC> msg)
    {
        Integer index = msg.getIndex();
        String ip = msg.getIp();
        Integer code = msg.getCode();

        if (null == index || !StringUtils.hasText(ip))
        {
            log.warn("新增加一个逻辑点回调（LDC）, index 不存在，或者 ip 不存在");
            return;
        }

        Integer id;

        if (null == msg.getData() || null == msg.getData().getId())
        {
            log.warn("新增加一个逻辑点回调（LDC）, id 不存在");
            return;
        }

        // 消息返回成功则同步成功否则同步失败
        if (!Code.SUCCEED.equals(msg.getCode()))
        {
            log.warn("新增加一个逻辑点回调（LDC）, Code 错误码：" + msg.getCode());
            return;
        }

        id = msg.getData().getId();

        BESSbPzStruct besSbPzStruct = sbPzStructCache.getCachedElementById(String.valueOf(id));

        if (null == besSbPzStruct)
        {
            log.warn("新增加一个逻辑点回调（LDC）, 根据 id 没有查出设备树节点信息");
            return;
        }

        String nodeType = besSbPzStruct.getF_type();
        String structId = String.valueOf(id);
        String sysName = besSbPzStruct.getF_sys_name();

        if (Code.SUCCEED.equals(code))
        {
            JSONObject obj = new JSONObject();

            switch (nodeType)
            {
                case BesNodeType.AI: // AI 点
                {

                    obj.put("f_id", structId);
                    obj.put("f_ai_state", "1");
                    besSbdyMapper.updateAIPointTable(obj);

                    break;
                }
                case BesNodeType.AO: // AO 点
                {
                    obj.put("f_id", structId);
                    obj.put("f_ao_state", "1");
                    besSbdyMapper.updateAOPointTable(obj);
                    break;
                }
                case BesNodeType.DI: // DI 点
                {
                    obj.put("f_id", structId);
                    obj.put("f_di_state", "1");
                    besSbdyMapper.updateDIPointTable(obj);
                    break;
                }
                case BesNodeType.DO: // DO 点
                {
                    obj.put("f_id", structId);
                    obj.put("f_do_state", "1");
                    besSbdyMapper.updateDOPointTable(obj);
                    break;
                }
                case BesNodeType.VPOINT: // 虚点
                {
                    obj.put("name", sysName);
                    obj.put("syncState", "1");
                    besSbdyMapper.updateVirtualPointInfo(obj);
                    break;
                }
            }
        }

        // 获取当前 sessionId
        String sessionId = MsgSubPubHandler.pubMsg(index, ip);

        if (!StringUtils.hasText(sessionId))
        {
            log.warn("新增加一个逻辑点回调（LDC）, sessionId 不存在");
            return;
        }

        // 推送消息到web客户端
        WebSocketService.postEvent(sessionId, WebSocketEvent.LDC, msg);
    }

    // 设置一个逻辑点的所有参数回调（LDC）
    @Override
    public void pointParamSetLDC(ReceiveMsg<PointParamLDC> msg)
    {
        Integer index = msg.getIndex();
        String ip = msg.getIp();
        Integer code = msg.getCode();

        if (null == index || !StringUtils.hasText(ip))
        {
            log.warn("设置一个逻辑点的所有参数回调（LDC）, index 不存在，或者 ip 不存在");
            return;
        }

        Integer id;

        if (null == msg.getData() || null == msg.getData().getId())
        {
            log.warn("设置一个逻辑点的所有参数回调（LDC）, id 不存在");
            return;
        }

        // 消息返回成功则同步成功否则同步失败
        if (!Code.SUCCEED.equals(msg.getCode()))
        {
            log.warn("设置一个逻辑点的所有参数回调（LDC）, Code 错误码：" + msg.getCode());
            return;
        }

        id = msg.getData().getId();


        BESSbPzStruct besSbPzStruct = sbPzStructCache.getCachedElementById(String.valueOf(id));

        if (null == besSbPzStruct)
        {
            log.warn("设置一个逻辑点的所有参数回调（LDC）, 根据 id 没有查出设备树节点信息");
            return;
        }

        String nodeType = String.valueOf(besSbPzStruct.getF_type());
        String structId = String.valueOf(id);
        String sysName = besSbPzStruct.getF_sys_name();

        if (Code.SUCCEED.equals(code))
        {
            JSONObject obj = new JSONObject();

            switch (nodeType)
            {
                case BesNodeType.AI: // AI 点
                {

                    obj.put("f_id", structId);
                    obj.put("f_ai_state", "1");
                    besSbdyMapper.updateAIPointTable(obj);

                    break;
                }
                case BesNodeType.AO: // AO 点
                {
                    obj.put("f_id", structId);
                    obj.put("f_ao_state", "1");
                    besSbdyMapper.updateAOPointTable(obj);
                    break;
                }
                case BesNodeType.DI: // DI 点
                {
                    obj.put("f_id", structId);
                    obj.put("f_di_state", "1");
                    besSbdyMapper.updateDIPointTable(obj);
                    break;
                }
                case BesNodeType.DO: // DO 点
                {
                    obj.put("f_id", structId);
                    obj.put("f_do_state", "1");
                    besSbdyMapper.updateDOPointTable(obj);
                    break;
                }
                case BesNodeType.VPOINT: // 虚点
                {
                    obj.put("name", sysName);
                    obj.put("syncState", "1");
                    besSbdyMapper.updateVirtualPointInfo(obj);
                    break;
                }
            }
        }

        // 获取当前 sessionId
        String sessionId = MsgSubPubHandler.pubMsg(index, ip);

        if (!StringUtils.hasText(sessionId))
        {
            log.warn("设置一个逻辑点的所有参数回调（LDC）, sessionId 不存在");
            return;
        }

        // 推送消息到web客户端
        WebSocketService.postEvent(sessionId, WebSocketEvent.LDC, msg);
    }

    // 设置一个逻辑点的值回调（LDC）
    @Override
    public void pointValueSetLDC(ReceiveMsg<PointDataLDC> msg) {
        Integer index = msg.getIndex();
        String ip = msg.getIp();
        Integer value;

        if (null == index || !StringUtils.hasText(ip)) {
            log.warn("设置一个逻辑点的值回调（LDC），index 不存在，或者 ip 不存在");
            return;
        }

        // 获取当前 sessionId
        String sessionId = MsgSubPubHandler.pubMsg(index, ip);

        if (!StringUtils.hasText(sessionId)) {
            log.warn("设置一个逻辑点的值回调（LDC），sessionId 不存在");
            return;
        }
        // 推送消息到web客户端
        WebSocketService.postEvent(sessionId, WebSocketEvent.LDC, msg);

        // 消息返回成功则同步成功否则同步失败
        if (!Code.SUCCEED.equals(msg.getCode())) {
            log.warn("设置一个逻辑点的值回调（LDC）, Code 错误码：" + msg.getCode());
            return;
        }

        Integer id = msg.getData().getId();

        BESSbPzStruct besSbPzStruct = sbPzStructCache.getCachedElementById(String.valueOf(id));

        if (besSbPzStruct == null)
        {
            log.warn("设置一个逻辑点的值回调， 设备树点位不存在");
            return;
        }

        BESSbTreeNodeType besSbTreeNodeType = sbTreeNodeTypeCache.getCachedElement(besSbPzStruct.getF_type());

        if (BesNodeType.VPOINT.equals(besSbPzStruct.getF_type())) {
            //查询虚点的点位信息

            value = msg.getData().getValue();

            //更改虚点点位的实时值
            // 实时值不在更新表
            // besSbdyMapper.updateVPointByid(tabName,String.valueOf(id),value);

            ReceiveMsg<List<PointDataDDC>> msgs = new ReceiveMsg<>();

            msgs.setCode(0);
            msgs.setIndex(DDCCmd.VIRTUAL_POINT_DATA_RECEIVE);
            msgs.setIp(ip);

            List<PointDataDDC> pointDataList = new ArrayList<>();

            PointDataDDC pointDataDDC = new PointDataDDC();

            pointDataDDC.setId(id);
            pointDataDDC.setValue(value);

            pointDataList.add(pointDataDDC);

            msgs.setData(pointDataList);

            virtualPointDataReceiveDDC(msgs);

        } else {

            Integer workMode = msg.getData().getWorkMode();

            besSbdyMapper.updatePointByWorkMode(besSbTreeNodeType.getF_node_table(), String.valueOf(workMode), String.valueOf(id));

            value = msg.getData().getValue();

            //更改实点点位的实时值
            // 实时值不在更新表
            // besSbdyMapper.updatePointByid(tabName,String.valueOf(id),value);

            ReceiveMsg<List<PointDataDDC>> msgs = new ReceiveMsg<>();

            msgs.setCode(0);
            msgs.setIndex(DDCCmd.REAL_POINT_DATA_RECEIVE);
            msgs.setIp(ip);

            List<PointDataDDC> pointDataList = new ArrayList<>();

            PointDataDDC pointDataDDC = new PointDataDDC();

            pointDataDDC.setId(id);
            pointDataDDC.setValue(value);

            pointDataList.add(pointDataDDC);

            msgs.setData(pointDataList);

            realPointDataReceiveDDC(msgs);
        }
    }

    // 设置一个逻辑点的值回调根据名称（LDC）(未使用)
    @Override
    public void pointValueByNameSetLDC(ReceiveMsg<PointParamLDC> msg)
    {
        Integer index = msg.getIndex();
        String ip = msg.getIp();
        Map<String,Object> pointMapByid;
        Integer value;
        Double values ;

        if (null == index || !StringUtils.hasText(ip))
        {
            log.warn("设置一个逻辑点的值回调根据名称（LDC），index 不存在，或者 ip 不存在");
            return;
        }

        // 获取当前 sessionId
        String sessionId = MsgSubPubHandler.pubMsg(index, ip);

        if (!StringUtils.hasText(sessionId))
        {
            log.warn("设置一个逻辑点的值回调根据名称（LDC），sessionId 不存在");
            return;
        }

        // 推送消息到web客户端
        WebSocketService.postEvent(sessionId, WebSocketEvent.LDC, msg);

        // 消息返回成功则同步成功否则同步失败
        if (!Code.SUCCEED.equals(msg.getCode()))
        {
            log.warn("设置一个逻辑点的值回调根据名称（LDC）, Code 错误码：" + msg.getCode());
            return;
        }

        Integer id = msg.getData().getId();
        //根据id查询当前点位的表名
        String tabName = crossEquipmentMapper.selectTableName(id);
        if (tabName.equals("BES_VPOINT")) {
            //查询虚点的点位信息
            pointMapByid = crossEquipmentMapper.selectVPointMapByID(id);

            if (pointMapByid.get("F_NODE_TYPE").equals("5")) {//VAO点位

                value = (Integer) pointMapByid.get("F_INIT_VAL");

                Double f_accuracy = Double.valueOf(String.valueOf(pointMapByid.get("F_ACCURACY"))) ;//获取精度

                values = value * Math.pow(10, f_accuracy);//获取精度转换后的实时值
                value = Integer.parseInt(new java.text.DecimalFormat("0").format(values));

            } else {

                value = (Integer) pointMapByid.get("F_INIT_VAL");
            }

            ReceiveMsg<List<PointDataDDC>> msgs = new ReceiveMsg<>();

            msgs.setCode(0);
            msgs.setIndex(DDCCmd.VIRTUAL_POINT_DATA_RECEIVE);
            msgs.setIp(ip);

            List<PointDataDDC> pointDataList = new ArrayList<>();

            PointDataDDC pointDataDDC = new PointDataDDC();

            pointDataDDC.setId(id);
            pointDataDDC.setValue(value);

            pointDataList.add(pointDataDDC);

            msgs.setData(pointDataList);

            virtualPointDataReceiveDDC(msgs);

        } else {

            //查询实点的点位信息
            pointMapByid = crossEquipmentMapper.selectPointMapByID(tabName,id);
            if (tabName.equals("BES_ANALOG_OUPUT")) {

                Double f_accuracy = Double.valueOf(String.valueOf(pointMapByid.get("F_ACCURACY"))) ;//获取精度
                value = (Integer) pointMapByid.get("F_INIT_VAL");
                values = value * Math.pow(10, f_accuracy);//获取精度转换后的实时值
                value = Integer.parseInt(new java.text.DecimalFormat("0").format(values));
            } else {

                value = (Integer) pointMapByid.get("F_INIT_VAL");
            }

            ReceiveMsg<List<PointDataDDC>> msgs = new ReceiveMsg<>();

            msgs.setCode(0);
            msgs.setIndex(DDCCmd.REAL_POINT_DATA_RECEIVE);
            msgs.setIp(ip);

            List<PointDataDDC> pointDataList = new ArrayList<>();

            PointDataDDC pointDataDDC = new PointDataDDC();

            pointDataDDC.setId(id);
            pointDataDDC.setValue(value);

            pointDataList.add(pointDataDDC);

            msgs.setData(pointDataList);

            realPointDataReceiveDDC(msgs);
        }
    }

    // 删除一个逻辑点回调（LDC）
    @Override
    public void pointDeleteLDC(ReceiveMsg<PointParamLDC> msg)
    {
        Integer index = msg.getIndex();
        String ip = msg.getIp();

        if (null == index || !StringUtils.hasText(ip))
        {
            log.warn("删除一个逻辑点回调（LDC），或者 ip 不存在");
            return;
        }

        // 获取当前 sessionId
        String sessionId = MsgSubPubHandler.pubMsg(index, ip);

        if (!StringUtils.hasText(sessionId))
        {
            log.warn("删除一个逻辑点回调（LDC），sessionId 不存在");
            return;
        }

        // 推送消息到web客户端
        WebSocketService.postEvent(sessionId, WebSocketEvent.LDC, msg);
    }

    // 获取一个逻辑点的所有配置参数回调（LDC）
    @Override
    public void pointParamGetLDC(ReceiveMsg<PointParamLDC> msg)
    {
        Integer index = msg.getIndex();
        String ip = msg.getIp();

        if (null == index || !StringUtils.hasText(ip))
        {
            log.warn("获取一个逻辑点的所有配置参数回调（LDC），index 不存在，或者 ip 不存在");
            return;
        }

        // 获取当前 sessionId
        String sessionId = MsgSubPubHandler.pubMsg(index, ip);

        if (!StringUtils.hasText(sessionId))
        {
            log.warn("获取一个逻辑点的所有配置参数回调（LDC），sessionId 不存在");
            return;
        }

        // 推送消息到web客户端
        WebSocketService.postEvent(sessionId, WebSocketEvent.LDC, msg);
    }

    // 获取一个逻辑点的报警信息回调（LDC）
    @Override
    public void pointAlarmDataGetLDC(ReceiveMsg<AlarmPointDataLDC> msg)
    {
        // 未使用，告警逻辑由上位机实现
    }

    // 接收LDC的全部点信息回调（LDC）
    @Override
    public void pointDataAllReceiveLDC(ReceiveMsg<List<PointDataLDC>> msg)
    {
        Integer index = msg.getIndex();
        String ip = msg.getIp();

        if (null == index || !StringUtils.hasText(ip))
        {
            log.warn("接收LDC虚点更新的点信息回调，index 不存在，或者 ip 不存在");
            return;
        }

        List<PointDataLDC> pointList = msg.getData();

        if (null == pointList || pointList.isEmpty())
        {
            log.warn("接收LDC的全部点信息回调, 参数 data 不存在");
            return;
        }

        //根据ip查询当前DDC控制器点是否存在
        BesDdc besDdc = ddcCache.getDdcByChannelId(ip);

        if (besDdc == null){
            log.warn("接收LDC的全部点信息回调，LDC控制器不存在");
            return;
        }

        Integer id;//id

        List<Map> dataList = new ArrayList<>();

        List<PointDataResponse> pointDataResponseList = new ArrayList<>();

        for (PointDataLDC pointData : pointList)
        {
            id = pointData.getId();
            Integer value = pointData.getValue();

            if (null == id || null == value)
            {
                continue;
            }

            PointDataResponse pointDataResponse = new PointDataResponse();

            pointDataResponse.setId(id);

            //根据id查询点位所属的点位表名
            BESSbPzStruct besSbPzStruct = sbPzStructCache.getCachedElementById(String.valueOf(id));

            if (null == besSbPzStruct)
            {
                continue;
            }

            if (BesNodeType.VPOINT.equals(besSbPzStruct.getF_type()))
            {
                BesVirtualPoint besVirtualPoint = virtualPointCache.getCachedElement(besSbPzStruct.getF_sys_name());

                if (null == besVirtualPoint)
                {
                    continue;
                }

                Integer pointType = Integer.valueOf(besVirtualPoint.getF_node_type());

                pointDataResponse.setAlias(besVirtualPoint.getF_nick_name());
                pointDataResponse.setName(besVirtualPoint.getF_sys_name());
                pointDataResponse.setSysNameOld(besVirtualPoint.getF_sys_name_old());
                pointDataResponse.setUnit(besVirtualPoint.getF_engineer_unit());

                if (pointType == PointType.POINT_TYPE_VIRTUAL_AI
                        || pointType == PointType.POINT_TYPE_VIRTUAL_AO)
                {
                    Integer accuracyNum = Integer.parseInt(besVirtualPoint.getF_accuracy());//获取精度
                    Double valueDouble = value / Math.pow(10, accuracyNum);//获取精度转换后的实时值
                    String values = subZeroAndDot(String.valueOf(valueDouble));
                    pointDataResponse.setValue(values);

                    // 不在保存实时数据到数据库
                    // besPointStructMapper.updateVPointValue(String.valueOf(id), values);
                    planningHandler.planingHandler(id,values);
                    // 更新缓存
                    besSbPzStruct.setF_init_val(values);

                    if (pointType == PointType.POINT_TYPE_VIRTUAL_AI && "0".equals(besVirtualPoint.getF_energystatics()))
                    {
                        Integer savePeriod = besDdc.getfSavePeriod();
                        Integer recordUploadPeriod = besVirtualPoint.getRecordUploadPeriod();

                        if (recordUploadPeriod == null) {
                            besVirtualPoint.setRecordUploadPeriod(1);
                        }

                        if (savePeriod == null || savePeriod.equals(recordUploadPeriod)) {
                            Map<String, Object> energyData = new HashMap<>();

                            energyData.put("F_ENERGY_TYPE", besVirtualPoint.getF_energy_type());
                            energyData.put("F_SYS_NAME", besVirtualPoint.getF_sys_name());
                            energyData.put("F_SYS_NAME_OLD", besVirtualPoint.getF_sys_name_old());
                            energyData.put("F_DATE", valueDouble);

                            dataList.add(energyData);

                            besVirtualPoint.setRecordUploadPeriod(1);

                        }else if (besVirtualPoint.getRecordUploadPeriod() < savePeriod){
                            besVirtualPoint.setRecordUploadPeriod(1 + besVirtualPoint.getRecordUploadPeriod());

                        }else {
                            besVirtualPoint.setRecordUploadPeriod(1);
                        }

                    }


                } else {

                    // 不在保存实时数据到数据库
                    // besPointStructMapper.updateVPointValue(String.valueOf(id), String.valueOf(value));
                    // 更新缓存
                    planningHandler.planingHandler(id,String.valueOf(value));
                    besSbPzStruct.setF_init_val(String.valueOf(value));

                    pointDataResponse.setValue(String.valueOf(value));
                }

                pointDataResponseList.add(pointDataResponse);

            } else
            {
                pointDataResponse.setAlias(besSbPzStruct.getF_nick_name());
                pointDataResponse.setName(besSbPzStruct.getF_sys_name());
                pointDataResponse.setSysNameOld(besSbPzStruct.getF_sys_name_old());
                pointDataResponse.setUnit(besSbPzStruct.getUnit());

                if (BesNodeType.AI.equals(besSbPzStruct.getF_type()) || BesNodeType.AO.equals(besSbPzStruct.getF_type()))
                {

                    Integer accuracyNum = Integer.parseInt(besSbPzStruct.getF_accuracy());//获取精度
                    Double valueDouble = value / Math.pow(10, accuracyNum);//获取精度转换后的实时值
                    String values = subZeroAndDot(String.valueOf(valueDouble));
                    pointDataResponse.setValue(values);

                    // 实时数据不在存储到数据库
                    // besPointStructMapper.updatePointValue(tabName, String.valueOf(id), values);
                    planningHandler.planingHandler(id,values);
                    // 更新缓存
                    besSbPzStruct.setF_init_val(values);

                    if (BesNodeType.AI.equals(besSbPzStruct.getF_type()))
                    {
                        BesAiPoint besAiPoint = aiPointCache.getCachedElement(besSbPzStruct.getF_sys_name());

                        String energystatics = besAiPoint.getF_energystatics();

                        if ("0".equals(energystatics))
                        {
                            Integer savePeriod = besDdc.getfSavePeriod();
                            Integer recordUploadPeriod = besAiPoint.getRecordUploadPeriod();

                            if (recordUploadPeriod == null) {
                                besAiPoint.setRecordUploadPeriod(1);
                            }

                            if (savePeriod == null || savePeriod.equals(recordUploadPeriod)) {

                                Map<String, Object> energyData = new HashMap<>();

                                energyData.put("F_ENERGY_TYPE", besAiPoint.getF_energy_type());
                                energyData.put("F_SYS_NAME", besAiPoint.getF_sys_name());
                                energyData.put("F_SYS_NAME_OLD", besAiPoint.getF_sys_name_old());
                                energyData.put("F_DATE", valueDouble);

                                dataList.add(energyData);

                                besAiPoint.setRecordUploadPeriod(1);

                            }else if (besAiPoint.getRecordUploadPeriod() < savePeriod){
                                besAiPoint.setRecordUploadPeriod(1 + besAiPoint.getRecordUploadPeriod());
                            }else {
                                besAiPoint.setRecordUploadPeriod(1);
                            }

                        }
                    }


                } else {

                    // 实时数据不在更新数据库
                    // besPointStructMapper.updatePointValue(tabName, String.valueOf(id), String.valueOf(value));

                    pointDataResponse.setValue(String.valueOf(value));

                    planningHandler.planingHandler(id,String.valueOf(value));
                    // 更新缓存
                    besSbPzStruct.setF_init_val(String.valueOf(value));
                }

                pointDataResponseList.add(pointDataResponse);
            }

        }

        // 存储虚点能耗数据
        if(!dataList.isEmpty()) {
            EnergyCollectHandler.ammeterDataHandle(dataList, ip);
        }

        alarmHandler.DDCalarmHandle(msg);//报警

        // 推送消息到web客户端
        ReceiveMsg<List<PointDataResponse>> msg1 = new ReceiveMsg<>();
        msg1.setData(pointDataResponseList);
        msg1.setCode(msg.getCode());
        msg1.setIndex(msg.getIndex());
        msg1.setIp(msg.getIp());

        // WebSocketService.postEvent(WebSocketEvent.LDC, msg1);

        distributePostEvent(WebSocketEvent.LDC, msg1);

        functionPointStateService.stateReceiptApiHandle(pointDataResponseList);
    }

    // 接收虚点信息回调（LDC）
    @Override
    public void virtualPointDataReceiveLDC(ReceiveMsg<List<PointDataLDC>> msg)
    {
        Integer index = msg.getIndex();
        String ip = msg.getIp();

        if (null == index || !StringUtils.hasText(ip))
        {
            log.warn("接收虚点信息回调，index 不存在，或者 ip 不存在");
            return;
        }

        List<PointDataLDC> pointList = msg.getData();

        if (null == pointList || pointList.isEmpty())
        {
            log.warn("接收虚点信息回调, 参数 data 不存在");
            return;
        }

        //根据ip查询当前DDC控制器点是否存在
        BesDdc besDdc = ddcCache.getDdcByChannelId(ip);

        if (besDdc == null){
            log.warn("接收虚点信息回调，DDC控制器不存在");
            return;
        }

        Integer id;//id
        Integer value;//实时值

        List<PointDataResponse> pointDataList = new ArrayList<>();

        for (PointDataLDC pointData : pointList)
        {
            id = pointData.getId();
            value = pointData.getValue();

            if (null == id || null == value)
            {
                continue;
            }

            PointDataResponse pointData1 = new PointDataResponse();

            pointData1.setId(id);

            BesVirtualPoint besVirtualPoint = virtualPointCache.getCachedElementBySbId(String.valueOf(id));

            if (null == besVirtualPoint)
            {
                continue;
            }

            BESSbPzStruct besSbPzStruct = sbPzStructCache.getCachedElement(besVirtualPoint.getF_sys_name());

            if (null == besSbPzStruct)
            {
                continue;
            }

            pointData1.setAlias(besVirtualPoint.getF_nick_name());
            pointData1.setName(besVirtualPoint.getF_sys_name());
            pointData1.setSysNameOld(besVirtualPoint.getF_sys_name_old());
            pointData1.setUnit(besVirtualPoint.getF_engineer_unit());

            Integer pointType = Integer.valueOf(besVirtualPoint.getF_node_type());


            if (pointType == PointType.POINT_TYPE_VIRTUAL_AI
                    || pointType == PointType.POINT_TYPE_VIRTUAL_AO)
            {
                Integer accuracyNum = Integer.parseInt(besVirtualPoint.getF_accuracy()) ;//获取精度
                Double valueDouble = value / Math.pow(10, accuracyNum);//获取精度转换后的实时值
                String values = subZeroAndDot(String.valueOf(valueDouble));
                pointData1.setValue(values);

                // 实时数据不在更新数据库
                // besPointStructMapper.updateVPointValue(String.valueOf(id), values);

                // 更新缓存数据
                besSbPzStruct.setF_init_val(values);

            }else {

                // 实时数据不在更新数据库
                // besPointStructMapper.updateVPointValue(String.valueOf(id), String.valueOf(value));

                // 更新缓存数据
                besSbPzStruct.setF_init_val(String.valueOf(value));

                pointData1.setValue(String.valueOf(value));
            }

            pointDataList.add(pointData1);
        }

        alarmHandler.DDCalarmHandle(msg);//报警

        // 推送消息到web客户端
        ReceiveMsg<List<PointDataResponse>> msg1 = new ReceiveMsg<>();
        msg1.setData(pointDataList);
        msg1.setCode(msg.getCode());
        msg1.setIndex(msg.getIndex());
        msg1.setIp(msg.getIp());

        // WebSocketService.postEvent(WebSocketEvent.LDC, msg1);
        distributePostEvent(WebSocketEvent.LDC, msg1);

        functionPointStateService.stateReceiptApiHandle(pointDataList);
    }

    // 增加一个场景回调（LDC）
    @Override
    public void sceneAddLDC(ReceiveMsg<SceneDataLDC> msg)
    {
        Integer index = msg.getIndex();
        String ip = msg.getIp();

        if (null == index || !StringUtils.hasText(ip))
        {
            log.warn("接收新增场景信息回调，index 不存在，或者 ip 不存在");
            return;
        }

        // 获取当前 sessionId
        String sessionId = MsgSubPubHandler.pubMsg(index, ip);

        if (!StringUtils.hasText(sessionId))
        {
            log.warn("接收一个场景的所有配置参数回调（LDC），sessionId 不存在");
            return;
        }

        // 推送消息到web客户端
        WebSocketService.postEvent(sessionId, WebSocketEvent.LDC, msg);
    }

    // 设置一个场景的所有参数（LDC）
    @Override
    public void sceneParamSetLDC(ReceiveMsg<SceneDataLDC> msg)
    {
        Integer index = msg.getIndex();
        String ip = msg.getIp();

        if (null == index || !StringUtils.hasText(ip))
        {
            log.warn("接收场景信息回调，index 不存在，或者 ip 不存在");
            return;
        }

        // 获取当前 sessionId
        String sessionId = MsgSubPubHandler.pubMsg(index, ip);

        if (!StringUtils.hasText(sessionId))
        {
            log.warn("获取一个场景的所有配置参数回调（LDC），sessionId 不存在");
            return;
        }

        // 推送消息到web客户端
        WebSocketService.postEvent(sessionId, WebSocketEvent.LDC, msg);
    }

    // 删除一个场景（LDC）
    @Override
    public void sceneDeleteLDC(ReceiveMsg<SceneDataLDC> msg)
    {

        Integer index = msg.getIndex();
        String ip = msg.getIp();

        if (null == index || !StringUtils.hasText(ip))
        {
            log.warn("删除场景信息回调，index 不存在，或者 ip 不存在");
            return;
        }

        // 获取当前 sessionId
        String sessionId = MsgSubPubHandler.pubMsg(index, ip);

        if (!StringUtils.hasText(sessionId))
        {
            log.warn("删除一个场景的所有配置参数回调（LDC），sessionId 不存在");
            return;
        }

        // 推送消息到web客户端
        WebSocketService.postEvent(sessionId, WebSocketEvent.LDC, msg);
    }

    // 获取一个场景的配置信息回调（LDC）
    @Override
    public void sceneParamGetLDC(ReceiveMsg<SceneParamLDC> msg)
    {

    }

    // 增加一条计划回调（LDC）
    @Override
    public void planAddLDC(ReceiveMsg<PlanParamLDC> msg)
    {

        Integer index = msg.getIndex();
        String ip = msg.getIp();

        if (null == index || !StringUtils.hasText(ip))
        {
            log.warn("新增LDC计划信息回调，index 不存在，或者 ip 不存在");
            return;
        }

        PlanParamLDC planParamLDC = msg.getData();

        if (null == planParamLDC)
        {
            log.warn("新增LDC计划信息回调, 参数 data 不存在");
            return;
        }

        // 获取当前 sessionId
        String sessionId = MsgSubPubHandler.pubMsg(index, ip);

        // 推送消息到web客户端
        WebSocketService.postEvent(sessionId, WebSocketEvent.LDC, msg);

    }

    // 修改一条计划的所有参数回调（LDC）
    @Override
    public void planParamSetLDC(ReceiveMsg<PlanParamLDC> msg)
    {

        Integer index = msg.getIndex();

        String ip = msg.getIp();

        if (null == index || !StringUtils.hasText(ip))
        {
            log.warn("同步LDC计划信息回调，index 不存在，或者 ip 不存在");
            return;
        }

        PlanParamLDC planParamLDC = msg.getData();

        if (null == planParamLDC)
        {
            log.warn("同步LDC计划信息回调, 参数 data 不存在");
            return;
        }

        // 获取当前 sessionId
        String sessionId = MsgSubPubHandler.pubMsg(index, ip);

        // 推送消息到web客户端
        WebSocketService.postEvent(sessionId, WebSocketEvent.LDC, msg);

    }

    // 删除一条计划回调（LDC）
    @Override
    public void planDeleteLDC(ReceiveMsg<PlanParamLDC> msg)
    {

        Integer index = msg.getIndex();
        String ip = msg.getIp();

        if (null == index || !StringUtils.hasText(ip))
        {
            log.warn("删除LDC计划信息回调，index 不存在，或者 ip 不存在");
            return;
        }

//        PlanParamLDC planParamLDC = msg.getData();
//
//        if (null == planParamLDC)
//        {
//            log.warn("删除LDC计划信息回调, 参数 data 不存在");
//            return;
//        }

        // 获取当前 sessionId
        String sessionId = MsgSubPubHandler.pubMsg(index, ip);

        // 推送消息到web客户端
        WebSocketService.postEvent(sessionId, WebSocketEvent.LDC, msg);

    }

    // 获取一条计划的所有参数回调（LDC）
    @Override
    public void planParamGetLDC(ReceiveMsg<PlanParamLDC> msg)
    {
        Integer index = msg.getIndex();
        String ip = msg.getIp();
        if (null == index || !StringUtils.hasText(ip))
        {
            log.warn("接收LDC计划信息回调，index 不存在，或者 ip 不存在");
            return;
        }

        PlanParamLDC planParamLDC = msg.getData();

        if (null == planParamLDC)
        {
            log.warn("接收LDC计划信息回调, 参数 data 不存在");
            return;
        }

        // 获取当前 sessionId
        String sessionId = MsgSubPubHandler.pubMsg(index, ip);

        // 推送消息到web客户端
        WebSocketService.postEvent(sessionId, WebSocketEvent.LDC, msg);
    }

    //获取一个场景下的模式信息的回调
    @Override
    public void controlParamLDC(ReceiveMsg<ControlParamLDC> msg) {

        Integer index = msg.getIndex();
        String ip = msg.getIp();

        if (null == index || !StringUtils.hasText(ip))
        {
            log.warn("接收LDC场景模式点信息回调，index 不存在，或者 ip 不存在");
            return;
        }

        ControlParamLDC controlParamLDC = msg.getData();

        if (null == controlParamLDC)
        {
            log.warn("接收LDC场景模式点信息回调, 参数 data 不存在");
            return;
        }

        // 获取当前 sessionId
        String sessionId = MsgSubPubHandler.pubMsg(index, ip);
        // 推送消息到web客户端
        WebSocketService.postEvent(sessionId,WebSocketEvent.LDC, msg);
    }

    //新增一个场景下的模式信息的回调
//    @Override
//    public void sceneModeParamAddLDC(ReceiveMsg<SceneModeParamLDC> msg){
//        Integer index = msg.getIndex();
//        String ip = msg.getIp();
//
//        if (null == index || !StringUtils.hasText(ip))
//        {
//            log.warn("新增LDC场景模式点信息回调，index 不存在，或者 ip 不存在");
//            return;
//        }
//
//        SceneModeParamLDC sceneModeParamLDC = msg.getData();
//
//        if (null == sceneModeParamLDC)
//        {
//            log.warn("新增LDC场景模式点信息回调, 参数 data 不存在");
//            return;
//        }
//
//        // 获取当前 sessionId
//        String sessionId = MsgSubPubHandler.pubMsg(index, ip);
//
//        // 推送消息到web客户端
//        WebSocketService.postEvent(sessionId,WebSocketEvent.LDC, msg);
//    }

    //删除一个场景模式(LDC)
    @Override
    public void sceneModeParamDeleteLDC(ReceiveMsg<SceneModeParamLDC> msg){

        Integer index = msg.getIndex();
        String ip = msg.getIp();

        if (null == index || !StringUtils.hasText(ip))
        {
            log.warn("删除LDC场景模式点信息回调，index 不存在，或者 ip 不存在");
            return;
        }

        SceneModeParamLDC sceneModeParamLDC = msg.getData();

        if (null == sceneModeParamLDC)
        {
            log.warn("删除LDC场景模式点信息回调, 参数 data 不存在");
            return;
        }

        // 获取当前 sessionId
        String sessionId = MsgSubPubHandler.pubMsg(index, ip);

        // 推送消息到web客户端
        WebSocketService.postEvent(sessionId,WebSocketEvent.LDC, msg);
    }

    /**
     * 使用正则表达式去掉多余的.与0
     * @param s
     * @return
     */
    public static String subZeroAndDot(String s){
        if(s.indexOf(".") > 0){
            s = s.replaceAll("0+?$", "");//去掉多余的0
            s = s.replaceAll("[.]$", "");//如最后一位是.则去掉
        }
        return s;
    }


    // 推送控制器在线状态（第三方系统）
    public void pushControllerState(ControllerStateModel controllerStateModel)
    {
        if (controllerStateModel == null)
        {
            return;
        }

        Cache<Integer, MessageMonitoringModel> messageMonitoringModelCache = messageMonitoringCache.getMessageMonitoringCache();

        if (messageMonitoringModelCache == null)
        {
            return;
        }

        Collection<MessageMonitoringModel> messageMonitoringModels = messageMonitoringModelCache.values();

        messageMonitoringModels.forEach(item ->
        {
            if (!MessageMonitoringModel.CONTROLLER_STATE.equals(item.getEventType()))
            {
                return;
            }

            String httpUrl = item.getHttpCallback();

            if(!StringUtils.hasText(httpUrl))
            {
                return;
            }

            OkHttpClient httpClient = new OkHttpClient();

            try
            {
                httpClient
                        .url(httpUrl)
                        .data(new RequestParams("data", controllerStateModel))
                        .post(new Callback()
                        {
                            @Override
                            public void onFailure(Call call, IOException e)
                            {
                                System.out.println("api接口推送控制器状态失败，失败原因：" + e.getMessage());
                            }

                            @Override
                            public void onResponse(Call call, Response response) throws IOException
                            {
                            }
                        });
            } catch (HttpException e)
            {
                e.printStackTrace();
            }

        });

    }

    /**
     * 实时数据分发到客户端
     * @param msg
     */
    public void distributePostEvent(String event, ReceiveMsg<List<PointDataResponse>> msg)
    {
        if (event == null || msg == null)
        {
            return;
        }

        List<PointDataResponse> pointDataResponseList = msg.getData();

        Map<String, List<PointDataResponse>> resultMap = new HashMap<>();

        for (int i = 0; i < pointDataResponseList.size(); i++)
        {
            PointDataResponse pointDataResponse = pointDataResponseList.get(i);
            String name = pointDataResponse.getName();
            Set<String> sessionIds = subRealTimeDataCache.getSubRealTimeDataCacheByEvent(name);

            if(sessionIds == null) continue;

            sessionIds.forEach(sessionId ->{
                List<PointDataResponse> pointDataResponseList1 = resultMap.computeIfAbsent(sessionId, k -> new ArrayList<>());
                pointDataResponseList1.add(pointDataResponse);
            });
        }

        resultMap.forEach((sessionId, pointDataResponseList1) -> {
            msg.setData(pointDataResponseList1);
            WebSocketService.postEvent(sessionId, event, msg);
        });

    }

}
