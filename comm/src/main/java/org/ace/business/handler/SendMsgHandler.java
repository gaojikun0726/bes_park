package org.ace.business.handler;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import io.netty.channel.Channel;
import io.netty.util.HashedWheelTimer;
import io.netty.util.TimerTask;
import org.ace.business.constant.DDCCmd;
import org.ace.business.constant.EDCCmd;
import org.ace.business.constant.LDCCmd;
import org.ace.business.dto.JsonMsg;
import org.ace.business.dto.SendMsg;
import org.ace.business.dto.TimeData;
import org.ace.business.dto.ddc.*;
import org.ace.business.dto.edc.*;
import org.ace.business.dto.ldc.*;
import org.ace.socketserver.util.MsgUtil;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;
import java.util.Queue;
import java.util.UUID;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

/**
 * 下位机消息发送类
 *
 * @author xiepufeng
 * @date 2020/4/15 8:26
 */
public class SendMsgHandler
{

    private static final Logger log = Logger.getLogger(SendMsgHandler.class.getName());

    public static HashedWheelTimer timer = new HashedWheelTimer();//时间轮的度刻

    // 消息队列
    public final static Map<String, Queue<JsonMsg>> msgQueue = new HashMap<>();

    /**
     * 发送数据到客户端
     *
     * @param channelID 通道id
     * @param jsonMsg   消息体
     * @return
     */
    static boolean postEvent(String channelID, JsonMsg jsonMsg)
    {

        if (channelID == null || channelID.isEmpty() || jsonMsg == null)
        {
            return false;
        }

        /**
         * 添加定时任务，清理队列中的超时任务
         *
         * 3秒后执行定时任务，检查此消息是否回复，
         * 如果队列中消息还存在则说明此消息发送后没有
         * 回复，那么就把此消息弹出消息队列，查看队列
         * 中是否有下一个消息，如果存在则发送出去
         *
         */
        TimerTask task = timeout ->
        {

            String uuid = jsonMsg.getUuid();

            Queue<JsonMsg> queue = msgQueue.get(channelID);

            if (queue == null || queue.isEmpty())
            {
                return;
            }

            JsonMsg queueMsg = queue.peek();

            if (queueMsg == null)
            {
                return;
            }

            if (uuid.equals(queueMsg.getUuid()))
            {
                // 如果该消息还没有回复，则清除该消息
                queue.poll();

                JsonMsg nextMsg = queue.peek();

                if (nextMsg == null)
                {
                    return;
                }

                postEvent(channelID, nextMsg);

            }
        };

        // 三秒后执行定时任务
        timer.newTimeout(task, 3, TimeUnit.SECONDS);

        Channel channel = MsgUtil.getChannelByTokenSN(channelID);

        if (channel == null)
        {
            log.warning("Post event failed, because the channel for event subscriber:" + channelID + " is not exists");
            return false;
        }

        Gson gson = new Gson();
        Type type = new TypeToken<JsonMsg>()
        {
        }.getType();


        String msg = gson.toJson(jsonMsg, type);

        msg = CrcUtil.addVerifyCRC(msg);

        channel.writeAndFlush(msg);

        return true;
    }

    /**
     * 发送数据到客户端
     *
     * @param channelID 通道id
     * @param jsonMsg   消息体
     * @return
     */
    static boolean sendEvent(String channelID, JsonMsg jsonMsg)
    {

        if (channelID == null || channelID.isEmpty() || jsonMsg == null)
        {
            return false;
        }

        Channel channel = MsgUtil.getChannelByTokenSN(channelID);

        if (channel == null)
        {
            log.warning("Post event failed, because the channel for event subscriber:" + channelID + " is not exists");
            return false;
        }

        Gson gson = new Gson();
        Type type = new TypeToken<JsonMsg>()
        {
        }.getType();


        String msg = gson.toJson(jsonMsg, type);

        msg = CrcUtil.addVerifyCRC(msg);

        channel.writeAndFlush(msg);

        return true;
    }

    

    /**
     * 消息入栈等待发送
     *
     * 1.判断通告是否存在，不存在则返回 false
     * 2.如果该通道没有消息队列，则创建
     * 3.如果消息队列中没有消息则直接发送消息
     * 4.把消息添加到消息队列
     * @param channelID
     * @param jsonMsg
     * @return
     */
    public static boolean pushStack(String channelID, JsonMsg jsonMsg)
    {

        if (channelID == null || channelID.isEmpty() || jsonMsg == null)
        {
            return false;
        }

        Channel channel = MsgUtil.getChannelByTokenSN(channelID);

        Queue<JsonMsg> queue = msgQueue.computeIfAbsent(channelID, k -> new ConcurrentLinkedQueue<>());

        if (channel == null)
        {
            queue.clear();
            log.warning("Post event failed, because the channel for event subscriber:" + channelID + " is not exists");
            return false;
        }

        jsonMsg.setUuid(UUID.randomUUID().toString());

        if (queue.isEmpty())
        {
            if (!SendMsgHandler.postEvent(channelID, jsonMsg))
            {
                return false;
            }
        }

        return queue.offer(jsonMsg);
    }

    /***************************************** EDC （能耗） **********************************************/

    // 远程升级
    public static boolean remoteUpgradeEDC(String channelID)
    {

        if (null == channelID || channelID.isEmpty())
        {
            return false;
        }

        SendMsg msg = new SendMsg();
        msg.setIndex(EDCCmd.REMOTE_UPGRADE);

        return pushStack(channelID, msg);
    }

    // 新增一个控制器（能耗）
    public static boolean addControllerEDC(String channelID, ControllerDataEDC controllerData)
    {
        if (null == channelID || channelID.isEmpty() || null == controllerData)
        {
            return false;
        }

        SendMsg msg = new SendMsg();
        msg.setIndex(EDCCmd.CONTROLLER_ADD);
        msg.setData(controllerData);

        return pushStack(channelID, msg);
    }

    // 设置控制器的所有参数（能耗）
    public static boolean setControllerEDC(String channelID, ControllerDataEDC controllerData)
    {
        if (null == channelID || channelID.isEmpty() || null == controllerData)
        {
            return false;
        }

        SendMsg msg = new SendMsg();
        msg.setIndex(EDCCmd.CONTROLLER_PARAM_SET);

        msg.setData(controllerData);

        return pushStack(channelID, msg);
    }

    // 获取控制器的所有配置参数（能耗）
    public static boolean getControllerEDC(String channelID)
    {
        if (null == channelID || channelID.isEmpty())
        {
            return false;
        }

        SendMsg msg = new SendMsg();
        msg.setIndex(EDCCmd.CONTROLLER_PARAM_GET);

        return pushStack(channelID, msg);
    }

    // 设置控制器的时间（能耗）
    public static boolean setControllerTimeEDC(String channelID, TimeData timeData)
    {
        if (null == channelID || channelID.isEmpty() || null == timeData)
        {
            return false;
        }

        SendMsg msg = new SendMsg();
        msg.setIndex(EDCCmd.CONTROLLER_TIME_SET);

        msg.setData(timeData);

        msg.setUuid(UUID.randomUUID().toString());

        return sendEvent(channelID, msg);
    }

    // 获取控制器的时间（能耗）
    public static boolean getControllerTimeEDC(String channelID)
    {
        if (null == channelID || channelID.isEmpty())
        {
            return false;
        }

        SendMsg msg = new SendMsg();
        msg.setIndex(EDCCmd.CONTROLLER_TIME_GET);

        return pushStack(channelID, msg);
    }

    // 重启控制器，相当于重启复位（能耗）
    public static boolean restartControllerEDC(String channelID)
    {
        if (null == channelID || channelID.isEmpty())
        {
            return false;
        }

        SendMsg msg = new SendMsg();
        msg.setIndex(EDCCmd.CONTROLLER_RESTART);

        return pushStack(channelID, msg);
    }

    // 重置控制器，恢复出厂设置，并重启（能耗）
    public static boolean resetControllerEDC(String channelID)
    {
        if (null == channelID || channelID.isEmpty())
        {
            return false;
        }

        SendMsg msg = new SendMsg();
        msg.setIndex(EDCCmd.CONTROLLER_RESET);

        return pushStack(channelID, msg);
    }

    // 删除一个控制器，并删除和它相关的电表（能耗）
    public static boolean deleteControllerEDC(String channelID)
    {
        if (null == channelID || channelID.isEmpty())
        {
            return false;
        }

        SendMsg msg = new SendMsg();
        msg.setIndex(EDCCmd.CONTROLLER_DELETE);

        return pushStack(channelID, msg);
    }

    // 新增加一个电表信息（能耗）
    public static boolean addAmmeterEDC(String channelID, AmmeterCollectParamData ammeterCollectParam)
    {
        if (null == channelID || channelID.isEmpty())
        {
            return false;
        }

        SendMsg msg = new SendMsg();
        msg.setIndex(EDCCmd.AMMETER_ADD);

        msg.setData(ammeterCollectParam);

        return pushStack(channelID, msg);
    }

    /**
     * 删除一个电表（能耗）
     *
     * @param channelID 客户端ip
     * @param meterID   模块的识别码
     * @return
     */
    public static boolean deleteAmmeterEDC(String channelID, Integer meterID)
    {
        if (null == channelID || channelID.isEmpty() || null == meterID)
        {
            return false;
        }

        SendMsg msg = new SendMsg();
        msg.setIndex(EDCCmd.AMMETER_DELETE);


        AmmeterData ammeterData = new AmmeterData();

        ammeterData.setMeterID(meterID);

        msg.setData(ammeterData);

        return pushStack(channelID, msg);
    }

    // 设置一个电表的所有参数
    public static boolean setAmmeterEDC(String channelID, AmmeterCollectParamData ammeterCollectParamData)
    {
        if (null == channelID || channelID.isEmpty() || null == ammeterCollectParamData)
        {
            return false;
        }

        SendMsg msg = new SendMsg();
        msg.setIndex(EDCCmd.AMMETER_SET);

        msg.setData(ammeterCollectParamData);

        return pushStack(channelID, msg);
    }

    /**
     * 获取一个电表的所有配置信息
     *
     * @param channelID 客户端ip
     * @param meterID   模块的识别码
     * @return
     */
    public static boolean getAmmeterEDC(String channelID, Integer meterID)
    {
        if (null == channelID || channelID.isEmpty() || null == meterID)
        {
            return false;
        }

        SendMsg msg = new SendMsg();
        msg.setIndex(EDCCmd.AMMETER_GET);


        AmmeterData ammeterData = new AmmeterData();

        ammeterData.setMeterID(meterID);

        msg.setData(ammeterData);

        return pushStack(channelID, msg);
    }

    /**
     * 新增一个电表的采集方案
     *
     * @param channelID        客户端ip
     * @param collectParamData
     * @return
     */
    public static boolean addAmmeterCollectionSchemeEDC(String channelID, CollectParamData collectParamData)
    {
        if (null == channelID || channelID.isEmpty() || null == collectParamData)
        {
            return false;
        }

        SendMsg msg = new SendMsg();
        msg.setIndex(EDCCmd.AMMETER_COLLECTION_SCHEME_ADD);

        msg.setData(collectParamData);

        return pushStack(channelID, msg);
    }

    /**
     * 设置一个电表的采集方案
     *
     * @param channelID        客户端ip
     * @param collectParamData
     * @return
     */
    public static boolean setAmmeterCollectionSchemeEDC(String channelID, CollectParamData collectParamData)
    {
        if (null == channelID || channelID.isEmpty() || null == collectParamData)
        {
            return false;
        }

        SendMsg msg = new SendMsg();
        msg.setIndex(EDCCmd.AMMETER_COLLECTION_SCHEME_SET);

        msg.setData(collectParamData);

        return pushStack(channelID, msg);
    }

    /**
     * 删除一个电表的采集方案
     *
     * @param channelID     客户端ip
     * @param electricParam
     * @return
     */
    public static boolean deleteAmmeterCollectionSchemeEDC(String channelID, ElectricParam electricParam)
    {
        if (null == channelID || channelID.isEmpty() || null == electricParam)
        {
            return false;
        }

        // 删除采集方案参数有 meterID electricID 电表序列号, 能耗参数 id
        Integer meterID = electricParam.getMeterID();
        Integer electricID = electricParam.getElectricID();

        if (null == meterID || null == electricID)
        {
            return false;
        }

        SendMsg msg = new SendMsg();
        msg.setIndex(EDCCmd.AMMETER_COLLECTION_SCHEME_DELETE);

        msg.setData(electricParam);

        return pushStack(channelID, msg);
    }

    /**
     * 获取电表实时数据
     *
     * @param channelID 客户端ip
     * @param meterID   模块的识别码
     * @return
     */
    public static boolean getAmmeterRealtimeDataEDC(String channelID, Integer meterID)
    {
        if (null == channelID || channelID.isEmpty() || null == meterID)
        {
            return false;
        }

        SendMsg msg = new SendMsg();
        msg.setIndex(EDCCmd.AMMETER_REALTIME_DATA_GET);


        AmmeterData ammeterData = new AmmeterData();

        ammeterData.setMeterID(meterID);

        msg.setData(ammeterData);

        return pushStack(channelID, msg);
    }

    /**
     * 获取历史数据
     *
     * @param channelID 客户端ip
     * @param meterID   模块的识别码
     * @param selectDay 选择那一天
     * @return
     */
    public static boolean getAmmeterHistoryDataEDC(String channelID, Integer meterID, Integer selectDay)
    {
        if (null == channelID || channelID.isEmpty() || null == meterID || null == selectDay)
        {
            return false;
        }

        SendMsg msg = new SendMsg();
        msg.setIndex(EDCCmd.AMMETER_HISTORY_DATA_GET);

        AmmeterDataHistory ammeterData = new AmmeterDataHistory();

        ammeterData.setMeterID(meterID);
        ammeterData.setselectDay(selectDay);

        msg.setData(ammeterData);

        return pushStack(channelID, msg);
    }


    /***************************************** DDC （楼控） **********************************************/


    // 新增一个控制器（DDC）
    public static boolean addControllerDDC(String channelID, ControllerDataDDC controllerData)
    {
        if (null == channelID || channelID.isEmpty() || null == controllerData)
        {
            return false;
        }

        SendMsg msg = new SendMsg();
        msg.setIndex(DDCCmd.CONTROLLER_ADD);
        msg.setData(controllerData);

        return pushStack(channelID, msg);
    }

    // 设置控制器的所有参数（能耗）
    public static boolean setControllerDDC(String channelID, ControllerDataDDC controllerData)
    {
        if (null == channelID || channelID.isEmpty() || null == controllerData)
        {
            return false;
        }

        SendMsg msg = new SendMsg();
        msg.setIndex(DDCCmd.CONTROLLER_PARAM_SET);

        msg.setData(controllerData);

        return pushStack(channelID, msg);
    }

    // 删除一个控制器，并删除和它相关的模块和点（DDC）
    public static boolean deleteControllerDDC(String channelID)
    {
        if (null == channelID || channelID.isEmpty())
        {
            return false;
        }

        SendMsg msg = new SendMsg();
        msg.setIndex(DDCCmd.CONTROLLER_DELETE);

        return pushStack(channelID, msg);
    }

    // 获取控制器的所有配置参数（能耗）
    public static boolean getControllerDDC(String channelID)
    {
        if (null == channelID || channelID.isEmpty())
        {
            return false;
        }

        SendMsg msg = new SendMsg();
        msg.setIndex(DDCCmd.CONTROLLER_PARAM_GET);

        return pushStack(channelID, msg);
    }

    // 远程升级（DDC）
    public static boolean upgradeRemoteDDC(String channelID)
    {

        if (null == channelID || channelID.isEmpty())
        {
            return false;
        }

        SendMsg msg = new SendMsg();
        msg.setIndex(DDCCmd.REMOTE_UPGRADE);

        return pushStack(channelID, msg);
    }

    // 设置控制器的时间（DDC）
    public static boolean setControllerTimeDDC(String channelID, TimeData timeData)
    {
        if (null == channelID || channelID.isEmpty() || null == timeData)
        {
            return false;
        }

        SendMsg msg = new SendMsg();
        msg.setIndex(DDCCmd.CONTROLLER_TIME_SET);

        msg.setData(timeData);

        msg.setUuid(UUID.randomUUID().toString());

        return sendEvent(channelID, msg);
    }

    // 获取控制器的时间（DDC）
    public static boolean getControllerTimeDDC(String channelID)
    {
        if (null == channelID || channelID.isEmpty())
        {
            return false;
        }

        SendMsg msg = new SendMsg();
        msg.setIndex(DDCCmd.CONTROLLER_TIME_GET);

        return pushStack(channelID, msg);
    }

    // 重启控制器，相当于重启复位（DDC）
    public static boolean restartControllerDDC(String channelID)
    {
        if (null == channelID || channelID.isEmpty())
        {
            return false;
        }

        SendMsg msg = new SendMsg();
        msg.setIndex(DDCCmd.CONTROLLER_RESTART);

        return pushStack(channelID, msg);
    }

    // 重置控制器，恢复出厂设置，并重启（DDC）
    public static boolean resetControllerDDC(String channelID)
    {
        if (null == channelID || channelID.isEmpty())
        {
            return false;
        }

        SendMsg msg = new SendMsg();
        msg.setIndex(DDCCmd.CONTROLLER_RESET);

        return pushStack(channelID, msg);
    }

    // 新增加一个模块
    public static boolean addModuleDDC(String channelID, ModuleParamDDC moduleParam)
    {
        if (null == channelID || channelID.isEmpty() || null == moduleParam)
        {
            return false;
        }

        SendMsg msg = new SendMsg();
        msg.setIndex(DDCCmd.MODULE_ADD);
        msg.setData(moduleParam);

        return pushStack(channelID, msg);
    }

    // 设置一个模块的所有参数
    public static boolean setModuleDDC(String channelID, ModuleParamDDC moduleParam)
    {
        if (null == channelID || channelID.isEmpty() || null == moduleParam)
        {
            return false;
        }

        SendMsg msg = new SendMsg();
        msg.setIndex(DDCCmd.MODULE_PARAM_SET);
        msg.setData(moduleParam);

        return pushStack(channelID, msg);
    }

    // 删除一个模块，并删除和模块相关的点
    public static boolean deleteModuleDDC(String channelID, Integer id)
    {
        if (null == channelID || channelID.isEmpty() || null == id)
        {
            return false;
        }

        SendMsg msg = new SendMsg();
        msg.setIndex(DDCCmd.MODULE_DELETE);

        ModuleParamDDC moduleParam = new ModuleParamDDC();
        moduleParam.setId(id);

        msg.setData(moduleParam);

        return pushStack(channelID, msg);
    }

    // 获取一个模块的所有配置信息
    public static boolean getModuleDDC(String channelID, Integer id)
    {
        if (null == channelID || channelID.isEmpty() || null == id)
        {
            return false;
        }

        SendMsg msg = new SendMsg();
        msg.setIndex(DDCCmd.MODULE_PARAM_GET);

        ModuleParamDDC moduleParam = new ModuleParamDDC();

        moduleParam.setId(id);

        msg.setData(moduleParam);

        return pushStack(channelID, msg);
    }

    // 获取一个模块的错误代码
    public static boolean getModuleErrorCodeDDC(String channelID, ModuleParamDDC moduleParam)
    {
        if (null == channelID || channelID.isEmpty() || null == moduleParam)
        {
            return false;
        }

        SendMsg msg = new SendMsg();
        msg.setIndex(DDCCmd.MODULE_ERROR_CODE_GET);
        msg.setData(moduleParam);

        return pushStack(channelID, msg);
    }

    // 批量获取模块的错误代码
    public static boolean getModuleErrorCodeDDC_ALL(String channelID, ModuleParamDDC moduleParam)
    {
        if (null == channelID || channelID.isEmpty() || null == moduleParam)
        {
            return false;
        }

        SendMsg msg = new SendMsg();
        msg.setIndex(DDCCmd.MODULE_ERROR_CODE_GET_ALL);
        msg.setData(moduleParam);

        return pushStack(channelID, msg);
    }

    // 新增加一个逻辑点
    public static boolean addPointDDC(String channelID, PointParamDDC pointParam)
    {
        if (null == channelID || channelID.isEmpty() || null == pointParam)
        {
            return false;
        }

        SendMsg msg = new SendMsg();
        msg.setIndex(DDCCmd.POINT_ADD);
        msg.setData(pointParam);

        return pushStack(channelID, msg);
    }

    // 设置一个逻辑点的所有参数
    public static boolean setPointDDC(String channelID, PointParamDDC pointParam)
    {
        if (null == channelID || channelID.isEmpty() || null == pointParam)
        {
            return false;
        }

        SendMsg msg = new SendMsg();
        msg.setIndex(DDCCmd.POINT_PARAM_SET);
        msg.setData(pointParam);

        return pushStack(channelID, msg);
    }

    // 设置一个逻辑点的值
    public static boolean setPointValueDDC(String channelID, PointDataDDC pointData)
    {
        if (null == channelID || channelID.isEmpty() || null == pointData)
        {
            return false;
        }

        SendMsg msg = new SendMsg();
        msg.setIndex(DDCCmd.POINT_VALUE_SET);
        msg.setData(pointData);

        return pushStack(channelID, msg);
    }

    // 设置逻辑点的值，根据点的名字
    public static boolean setPointValueByNameDDC(String channelID, PointDataDDC pointData)
    {
        if (null == channelID || channelID.isEmpty() || null == pointData)
        {
            return false;
        }

        SendMsg msg = new SendMsg();
        msg.setIndex(DDCCmd.POINT_VALUE_BY_NAME_SET);
        msg.setData(pointData);

        return pushStack(channelID, msg);
    }

    // 删除一个逻辑点
    public static boolean deletePointDDC(String channelID, Integer id)
    {
        if (null == channelID || channelID.isEmpty() || null == id)
        {
            return false;
        }

        SendMsg msg = new SendMsg();
        msg.setIndex(DDCCmd.POINT_DELETE);

        PointParamDDC pointParam = new PointParamDDC();

        pointParam.setId(id);

        msg.setData(pointParam);

        return pushStack(channelID, msg);
    }

    // 获取一个逻辑点的所有配置参数
    public static boolean getPointParamDDC(String channelID, Integer id)
    {
        if (null == channelID || channelID.isEmpty() || null == id)
        {
            return false;
        }

        SendMsg msg = new SendMsg();
        msg.setIndex(DDCCmd.POINT_PARAM_GET);

        PointParamDDC pointParam = new PointParamDDC();

        pointParam.setId(id);

        msg.setData(pointParam);

        return pushStack(channelID, msg);
    }

    // 获取一个逻辑点的报警信息
    public static boolean getPointAlarmDataDDC(String channelID, Integer id)
    {
        if (null == channelID || channelID.isEmpty() || null == id)
        {
            return false;
        }

        SendMsg msg = new SendMsg();
        msg.setIndex(DDCCmd.POINT_ALARM_DATA_GET);

        PointParamDDC pointParam = new PointParamDDC();

        pointParam.setId(id);

        msg.setData(pointParam);

        return pushStack(channelID, msg);
    }

    // 增加一个场景
    public static boolean addSceneDDC(String channelID, SceneParamDDC sceneParam)
    {
        if (null == channelID || channelID.isEmpty() || null == sceneParam)
        {
            return false;
        }

        SendMsg msg = new SendMsg();
        msg.setIndex(DDCCmd.SCENE_ADD);

        msg.setData(sceneParam);

        return pushStack(channelID, msg);
    }

    // 设置一个场景的所有参数
    public static boolean setSceneDDC(String channelID, ControlParamDDC controlParamDDC)
    {
        if (null == channelID || channelID.isEmpty() || null == controlParamDDC)
        {
            return false;
        }

        SendMsg msg = new SendMsg();
        msg.setIndex(DDCCmd.SCENE_PARAM_SET);

        msg.setData(controlParamDDC);

        return pushStack(channelID, msg);
    }

    // 删除一个场景
    public static boolean deleteSceneDDC(String channelID, Integer id)
    {
        if (null == channelID || channelID.isEmpty() || null == id)
        {
            return false;
        }

        SendMsg msg = new SendMsg();
        msg.setIndex(DDCCmd.SCENE_DELETE);

        SceneDataDDC sceneData = new SceneDataDDC();

        sceneData.setId(id);

        msg.setData(sceneData);

        return pushStack(channelID, msg);
    }

    // 获取一个场景的配置信息
    public static boolean getSceneDDC(String channelID, Integer id)
    {
        if (null == channelID || channelID.isEmpty() || null == id)
        {
            return false;
        }

        SendMsg msg = new SendMsg();
        msg.setIndex(DDCCmd.SCENE_PARAM_GET);

        SceneDataDDC sceneData = new SceneDataDDC();

        sceneData.setId(id);

        msg.setData(sceneData);

        return pushStack(channelID, msg);
    }

    //获取该场景下的模式信息(单取数据)
    public static boolean getSceneAndModeDDC(String channelID,Integer sceneId,Integer modeId){

        if (null == channelID || channelID.isEmpty() || null == sceneId || null == modeId)
        {
            return false;
        }

        SceneModeParamDDC sceneModeParamDDC = new SceneModeParamDDC();

        sceneModeParamDDC.setModeId(modeId);

        sceneModeParamDDC.setSceneId(sceneId);

        SendMsg msg = new SendMsg();

        // msg.setIp();
        msg.setData(sceneModeParamDDC);

        msg.setIndex(DDCCmd.SCENE_MODE_PARAM_GET);

        return pushStack(channelID,msg);
    }

    //删除单个场景下的模式信息(单删数据)
    public static boolean deleteSceneAndModeDDC(String channelID,Integer sceneId,Integer modeId){

        if (null == channelID || channelID.isEmpty() || null == sceneId || null == modeId)
        {
            return false;
        }

        SceneModeParamDDC sceneModeParamDDC = new SceneModeParamDDC();

        sceneModeParamDDC.setModeId(modeId);

        sceneModeParamDDC.setSceneId(sceneId);

        SendMsg msg = new SendMsg();

        msg.setData(sceneModeParamDDC);

        msg.setIndex(DDCCmd.SCENE_MODE_PARAM_DELETE);

        return pushStack(channelID,msg);
    }

    // 增加一条计划
    public static boolean addPlanDDC(String channelID, PlanParamDDC planParam)
    {
        if (null == channelID || channelID.isEmpty() || null == planParam)
        {
            return false;
        }

        SendMsg msg = new SendMsg();
        msg.setIndex(DDCCmd.PLAN_ADD);

        msg.setData(planParam);

        return pushStack(channelID, msg);
    }

    // 修改一条计划的所有参数
    public static boolean setPlanParamDDC(String channelID, PlanParamDDC planParam)
    {
        if (null == channelID || channelID.isEmpty() || null == planParam)
        {
            return false;
        }

        SendMsg msg = new SendMsg();
        msg.setIndex(DDCCmd.PLAN_PARAM_SET);

        msg.setData(planParam);

        return pushStack(channelID, msg);
    }

    // 删除一条计划
    public static boolean deletePlanDDC(String channelID, Integer id)
    {
        if (null == channelID || channelID.isEmpty() || null == id)
        {
            return false;
        }

        SendMsg msg = new SendMsg();
        msg.setIndex(DDCCmd.PLAN_DELETE);

        PlanParamDDC planParam = new PlanParamDDC();

        planParam.setId(id);

        msg.setData(planParam);

        return pushStack(channelID, msg);
    }

    // 获取一条计划的所有参数
    public static boolean getPlanParamDDC(String channelID, Integer id)
    {
        if (null == channelID || channelID.isEmpty() || null == id)
        {
            return false;
        }

        SendMsg msg = new SendMsg();
        msg.setIndex(DDCCmd.PLAN_PARAM_GET);

        PlanParamDDC planParam = new PlanParamDDC();

        planParam.setId(id);

        msg.setData(planParam);

        return pushStack(channelID, msg);
    }


    /***************************************** LDC （照明） **********************************************/


    // 新增一个IP路由器（LDC）
    public static boolean addControllerLDC(String channelID, ControllerDataLDC controllerData)
    {
        if (null == channelID || channelID.isEmpty() || null == controllerData)
        {
            return false;
        }

        SendMsg msg = new SendMsg();
        msg.setIndex(LDCCmd.CONTROLLER_ADD);
        msg.setData(controllerData);

        return pushStack(channelID, msg);
    }

    // 设置IP路由器的所有参数（LDC）
    public static boolean setControllerLDC(String channelID, ControllerDataLDC controllerData)
    {
        if (null == channelID || channelID.isEmpty() || null == controllerData)
        {
            return false;
        }

        SendMsg msg = new SendMsg();
        msg.setIndex(LDCCmd.CONTROLLER_PARAM_SET);

        System.out.println();

        msg.setData(controllerData);

        return pushStack(channelID, msg);
    }

    // 删除一个IP路由器，并删除和它相关的模块和点（LDC）
    public static boolean deleteControllerLDC(String channelID)
    {
        if (null == channelID || channelID.isEmpty())
        {
            return false;
        }

        SendMsg msg = new SendMsg();
        msg.setIndex(LDCCmd.CONTROLLER_DELETE);

        return pushStack(channelID, msg);
    }

    // 获取IP路由器的所有配置参数（LDC）
    public static boolean getControllerLDC(String channelID)
    {
        if (null == channelID || channelID.isEmpty())
        {
            return false;
        }

        SendMsg msg = new SendMsg();
        msg.setIndex(LDCCmd.CONTROLLER_PARAM_GET);

        return pushStack(channelID, msg);
    }

    // 远程升级（LDC）
    public static boolean upgradeRemoteLDC(String channelID)
    {

        if (null == channelID || channelID.isEmpty())
        {
            return false;
        }

        SendMsg msg = new SendMsg();
        msg.setIndex(LDCCmd.REMOTE_UPGRADE);

        return pushStack(channelID, msg);
    }

    // 设置IP路由器的时间（LDC）
    public static boolean setControllerTimeLDC(String channelID, TimeData timeData)
    {
        if (null == channelID || channelID.isEmpty() || null == timeData)
        {
            return false;
        }

        SendMsg msg = new SendMsg();
        msg.setIndex(LDCCmd.CONTROLLER_TIME_SET);

        msg.setData(timeData);

        msg.setUuid(UUID.randomUUID().toString());

        return sendEvent(channelID, msg);
    }

    // 获取IP路由器的时间（LDC）
    public static boolean getControllerTimeLDC(String channelID)
    {
        if (null == channelID || channelID.isEmpty())
        {
            return false;
        }

        SendMsg msg = new SendMsg();
        msg.setIndex(LDCCmd.CONTROLLER_TIME_GET);

        return pushStack(channelID, msg);
    }

    // 重启IP路由器，相当于重启复位（LDC）
    public static boolean restartControllerLDC(String channelID)
    {
        if (null == channelID || channelID.isEmpty())
        {
            return false;
        }

        SendMsg msg = new SendMsg();
        msg.setIndex(LDCCmd.CONTROLLER_RESTART);

        return pushStack(channelID, msg);
    }

    // 重置IP路由器，恢复出厂设置，并重启（LDC）
    public static boolean resetControllerLDC(String channelID)
    {
        if (null == channelID || channelID.isEmpty())
        {
            return false;
        }

        SendMsg msg = new SendMsg();
        msg.setIndex(LDCCmd.CONTROLLER_RESET);

        return pushStack(channelID, msg);
    }

    // 新增加一个模块
    public static boolean addModuleLDC(String channelID, ModuleParamLDC moduleParam)
    {
        if (null == channelID || channelID.isEmpty() || null == moduleParam)
        {
            return false;
        }

        SendMsg msg = new SendMsg();
        msg.setIndex(LDCCmd.MODULE_ADD);
        msg.setData(moduleParam);

        return pushStack(channelID, msg);
    }

    // 设置一个模块的所有参数
    public static boolean setModuleLDC(String channelID, ModuleParamLDC moduleParam)
    {
        if (null == channelID || channelID.isEmpty() || null == moduleParam)
        {
            return false;
        }

        SendMsg msg = new SendMsg();
        msg.setIndex(LDCCmd.MODULE_PARAM_SET);
        msg.setData(moduleParam);

        return pushStack(channelID, msg);
    }

    // 删除一个模块，并删除和模块相关的点
    public static boolean deleteModuleLDC(String channelID, Integer id)
    {
        if (null == channelID || channelID.isEmpty() || null == id)
        {
            return false;
        }

        SendMsg msg = new SendMsg();
        msg.setIndex(LDCCmd.MODULE_DELETE);

        ModuleParamLDC moduleParam = new ModuleParamLDC();
        moduleParam.setId(id);

        msg.setData(moduleParam);

        return pushStack(channelID, msg);
    }

    // 获取一个模块的所有配置信息
    public static boolean getModuleLDC(String channelID, Integer id)
    {
        if (null == channelID || channelID.isEmpty() || null == id)
        {
            return false;
        }

        SendMsg msg = new SendMsg();
        msg.setIndex(LDCCmd.MODULE_PARAM_GET);

        ModuleParamLDC moduleParam = new ModuleParamLDC();

        moduleParam.setId(id);

        msg.setData(moduleParam);

        return pushStack(channelID, msg);
    }

    // 获取一个模块的错误代码
    public static boolean getModuleErrorCodeLDC(String channelID, ModuleParamLDC moduleParam)
    {
        if (null == channelID || channelID.isEmpty() || null == moduleParam)
        {
            return false;
        }

        SendMsg msg = new SendMsg();
        msg.setIndex(LDCCmd.MODULE_ERROR_CODE_GET);
        msg.setData(moduleParam);

        return pushStack(channelID, msg);
    }

    // 新增加一个逻辑点
    public static boolean addPointLDC(String channelID, PointParamLDC pointParam)
    {
        if (null == channelID || channelID.isEmpty() || null == pointParam)
        {
            return false;
        }

        SendMsg msg = new SendMsg();
        msg.setIndex(LDCCmd.POINT_ADD);
        msg.setData(pointParam);

        return pushStack(channelID, msg);
    }

    // 设置一个逻辑点的所有参数
    public static boolean setPointLDC(String channelID, PointParamLDC pointParam)
    {
        if (null == channelID || channelID.isEmpty() || null == pointParam)
        {
            return false;
        }

        SendMsg msg = new SendMsg();
        msg.setIndex(LDCCmd.POINT_PARAM_SET);
        msg.setData(pointParam);

        return pushStack(channelID, msg);
    }


    // 设置一个逻辑点的值
    public static boolean setPointValueLDC(String channelID, PointDataLDC pointData)
    {
        if (null == channelID || channelID.isEmpty() || null == pointData)
        {
            return false;
        }

        SendMsg msg = new SendMsg();
        msg.setIndex(LDCCmd.POINT_VALUE_SET);
        msg.setData(pointData);

        return pushStack(channelID, msg);
    }

    // 设置逻辑点的值，根据点的名字
    public static boolean setPointValueByNameLDC(String channelID, PointDataLDC pointData)
    {
        if (null == channelID || channelID.isEmpty() || null == pointData)
        {
            return false;
        }

        SendMsg msg = new SendMsg();
        msg.setIndex(LDCCmd.POINT_VALUE_BY_NAME_SET);
        msg.setData(pointData);

        return pushStack(channelID, msg);
    }

    // 删除一个逻辑点
    public static boolean deletePointLDC(String channelID, Integer id)
    {
        if (null == channelID || channelID.isEmpty() || null == id)
        {
            return false;
        }

        SendMsg msg = new SendMsg();
        msg.setIndex(LDCCmd.POINT_DELETE);

        PointParamLDC pointParam = new PointParamLDC();

        pointParam.setId(id);

        msg.setData(pointParam);

        return pushStack(channelID, msg);
    }

    // 获取一个逻辑点的所有配置参数
    public static boolean getPointParamLDC(String channelID, Integer id)
    {
        if (null == channelID || channelID.isEmpty() || null == id)
        {
            return false;
        }

        SendMsg msg = new SendMsg();
        msg.setIndex(LDCCmd.POINT_PARAM_GET);

        PointParamLDC pointParam = new PointParamLDC();

        pointParam.setId(id);

        msg.setData(pointParam);

        return pushStack(channelID, msg);
    }

    // 获取一个逻辑点的报警信息
    public static boolean getPointAlarmDataLDC(String channelID, Integer id)
    {
        if (null == channelID || channelID.isEmpty() || null == id)
        {
            return false;
        }

        SendMsg msg = new SendMsg();
        msg.setIndex(LDCCmd.POINT_ALARM_DATA_GET);

        PointParamLDC pointParam = new PointParamLDC();

        pointParam.setId(id);

        msg.setData(pointParam);

        return pushStack(channelID, msg);
    }

    // 增加一个控制场景
    public static boolean addControlSceneLDC(String channelID, ControlParamLDC controlParamLDC)
    {
        if (null == channelID || channelID.isEmpty() || null == controlParamLDC)
        {
            return false;
        }

        SendMsg msg = new SendMsg();
        msg.setIndex(LDCCmd.SCENE_ADD);

        msg.setData(controlParamLDC);

        return pushStack(channelID, msg);
    }

    //增加一个采集场景
    public static boolean addCollectSceneLDC(String channelID, CollectParamLDC collectParamLDC)
    {
        if (null == channelID || channelID.isEmpty() || null == collectParamLDC)
        {
            return false;
        }

        SendMsg msg = new SendMsg();
        msg.setIndex(LDCCmd.SCENE_ADD);

        msg.setData(collectParamLDC);

        return pushStack(channelID, msg);
    }

    // 设置一个控制场景的所有参数
    public static boolean setControlSceneLDC(String channelID,ControlParamLDC controlParamLDC)
    {
        if (null == channelID || channelID.isEmpty() || null == controlParamLDC)
        {
            return false;
        }

        SendMsg msg = new SendMsg();

        msg.setIndex(LDCCmd.SCENE_PARAM_SET);

        msg.setData(controlParamLDC);

        return pushStack(channelID, msg);
    }


    // 设置一个采集场景的所有参数
    public static boolean setCollectSceneLDC(String channelID,CollectParamLDC collectParamLDC)
    {
        if (null == channelID || channelID.isEmpty() || null == collectParamLDC)
        {
            return false;
        }

        SendMsg msg = new SendMsg();
        msg.setIndex(LDCCmd.SCENE_PARAM_SET);

        msg.setData(collectParamLDC);

        return pushStack(channelID, msg);
    }


    // 删除一个场景
    public static boolean deleteSceneLDC(String channelID, Integer id)
    {
        if (null == channelID || channelID.isEmpty() || null == id)
        {
            return false;
        }

        SendMsg msg = new SendMsg();

        msg.setIndex(LDCCmd.SCENE_DELETE);

        SceneDataLDC sceneData = new SceneDataLDC();

        sceneData.setId(id);

        msg.setData(sceneData);

        return pushStack(channelID, msg);
    }

    // 删除一个场景
    public static boolean getSceneLDC(String channelID, Integer id)
    {
        if (null == channelID || channelID.isEmpty() || null == id)
        {
            return false;
        }

        SendMsg msg = new SendMsg();
        msg.setIndex(LDCCmd.SCENE_PARAM_GET);

        SceneDataLDC sceneData = new SceneDataLDC();

        sceneData.setId(id);

        msg.setData(sceneData);

        return pushStack(channelID, msg);
    }

    //获取该场景下的模式信息(单取数据)
    public static boolean getSceneAndModeLDC(String channelID,Integer sceneId,Integer modeId){

        if (null == channelID || channelID.isEmpty() || null == sceneId || null == modeId)
        {
            return false;
        }

        SceneModeParamLDC sceneModeParamLDC = new SceneModeParamLDC();

        sceneModeParamLDC.setModeId(modeId);

        sceneModeParamLDC.setSceneId(sceneId);

        SendMsg msg = new SendMsg();

       // msg.setIp();
        msg.setData(sceneModeParamLDC);

        msg.setIndex(LDCCmd.SCENE_MODE_PARAM_GET);

        return pushStack(channelID,msg);
    }

    //删除单个场景下的模式信息(单删数据)
    public static boolean deleteSceneAndModeLDC(String channelID,Integer sceneId,Integer modeId){

        if (null == channelID || channelID.isEmpty() || null == sceneId || null == modeId)
        {
            return false;
        }

        SceneModeParamLDC sceneModeParamLDC = new SceneModeParamLDC();

        sceneModeParamLDC.setModeId(modeId);

        sceneModeParamLDC.setSceneId(sceneId);

        SendMsg msg = new SendMsg();

        msg.setData(sceneModeParamLDC);

        msg.setIndex(LDCCmd.SCENE_MODE_PARAM_DELETE);

        return pushStack(channelID,msg);
    }

    // 增加一条计划
    public static boolean addPlanLDC(String channelID, PlanParamLDC planParam)
    {
        if (null == channelID || channelID.isEmpty() || null == planParam)
        {
            return false;
        }

        SendMsg msg = new SendMsg();
        msg.setIndex(LDCCmd.PLAN_ADD);

        msg.setData(planParam);

        return pushStack(channelID, msg);
    }

    // 修改一条计划的所有参数
    public static boolean setPlanParamLDC(String channelID, PlanParamLDC planParam)
    {
        if (null == channelID || channelID.isEmpty() || null == planParam)
        {
            return false;
        }

        SendMsg msg = new SendMsg();
        msg.setIndex(LDCCmd.PLAN_PARAM_SET);

        msg.setData(planParam);

        return pushStack(channelID, msg);
    }

    // 删除一条计划
    public static boolean deletePlanLDC(String channelID, Integer id)
    {
        if (null == channelID || channelID.isEmpty() || null == id)
        {
            return false;
        }

        SendMsg msg = new SendMsg();
        msg.setIndex(LDCCmd.PLAN_DELETE);

        PlanParamLDC planParam = new PlanParamLDC();

        planParam.setId(id);

        msg.setData(planParam);

        return pushStack(channelID, msg);
    }

    // 获取一条计划的所有参数
    public static boolean getPlanParamLDC(String channelID, Integer id)
    {
        if (null == channelID || channelID.isEmpty() || null == id)
        {
            return false;
        }

        SendMsg msg = new SendMsg();
        msg.setIndex(LDCCmd.PLAN_PARAM_GET);

        PlanParamLDC planParam = new PlanParamLDC();

        planParam.setId(id);

        msg.setData(planParam);

        return pushStack(channelID, msg);
    }
}
