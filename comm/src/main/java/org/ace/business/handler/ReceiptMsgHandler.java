package org.ace.business.handler;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import org.ace.business.constant.DDCCmd;
import org.ace.business.constant.EDCCmd;
import org.ace.business.constant.JsonAttr;
import org.ace.business.constant.LDCCmd;
import org.ace.business.dto.JsonMsg;
import org.ace.business.dto.ReceiveMsg;
import org.ace.business.dto.TimeData;
import org.ace.business.dto.ddc.*;
import org.ace.business.dto.edc.*;
import org.ace.business.dto.ldc.*;
import org.ace.socketserver.bo.ChannelProperty;
import org.ace.socketserver.constant.AttributeKeyConst;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Queue;
import java.util.ServiceLoader;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import static org.ace.socketserver.util.MsgUtil.respond;

/**
 * 下位机消息响应回调类
 *
 * @author xiepufeng
 * @date 2020/4/16 11:24
 */
public class ReceiptMsgHandler
{

    static ClientMsgReceive clientMsgReceive;

    // 创建两个线程池原因：以访高耗时任务阻塞低耗时任务
    // 执行低耗时任务
    public static ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(
            0,
            Runtime.getRuntime().availableProcessors() * 2,
            60L,
            TimeUnit.SECONDS, new LinkedBlockingDeque<>());

    // 执行高耗时任务
    private static ThreadPoolExecutor threadPoolExecutorTakeLong = new ThreadPoolExecutor(
            0,
            Runtime.getRuntime().availableProcessors() * 40,
            60L,
            TimeUnit.SECONDS, new LinkedBlockingDeque<>());

    static
    {
        ServiceLoader<ClientMsgReceive> load = ServiceLoader.load(ClientMsgReceive.class);

        for (ClientMsgReceive item : load)
        {
            clientMsgReceive = item;
            break;
        }
    }

    private static <T> ReceiveMsg<T> formatReceiveMsg(JsonObject jsonObject, Type type)
    {
        if (null == jsonObject || jsonObject.isJsonNull() || null == type)
        {
            return null;
        }

        Gson gson = new Gson();

        return gson.fromJson(jsonObject, type);
    }


    public static void handler(ChannelHandlerContext ctx, JsonObject jsonObject)
    {
        if (null == ctx || null == jsonObject || jsonObject.isJsonNull())
        {
            return;
        }

        boolean indexNameHas = jsonObject.has(JsonAttr.INDEX);

        if (!indexNameHas)
        {
            return;
        }

        int index = jsonObject.get(JsonAttr.INDEX).getAsInt();

        // 设置 channel 属性
        Channel channel = ctx.channel();

        ChannelProperty channelProperty = channel.attr(AttributeKeyConst.CHANNEL_PROPERTY_KEY).get();

        if (channelProperty == null)
        {
            return;
        }

        String ip = channelProperty.getTokenSN();

        // 设置ip地址到消息中
        jsonObject.addProperty(JsonAttr.IP, ip);

        // 对接收到的消息集中分发处理
        switch (index)
        {

            /***************************************** EDC (能耗) *******************************************/

            case EDCCmd.REMOTE_UPGRADE: // 远程升级

                if (null != clientMsgReceive)
                {
                    Type type = new TypeToken<ReceiveMsg>()
                    {
                    }.getType();

                    ReceiveMsg msg = formatReceiveMsg(jsonObject, type);

                    threadPoolExecutor.execute(() ->
                    {
                        clientMsgReceive.remoteUpgradeEDC(msg);
                        msgReceiptHandler(msg);
                    });
                }

                break;

            case EDCCmd.CONTROLLER_ADD: // 新增一个控制器（能耗）

                if (null != clientMsgReceive)
                {
                    Type type = new TypeToken<ReceiveMsg>()
                    {
                    }.getType();

                    ReceiveMsg msg = formatReceiveMsg(jsonObject, type);

                    threadPoolExecutor.execute(() ->
                    {
                        clientMsgReceive.controllerAddEDC(msg);
                    });
                }

                break;

            case EDCCmd.CONTROLLER_PARAM_SET: // 设置控制器的所有参数（能耗）

                if (null != clientMsgReceive)
                {
                    Type type = new TypeToken<ReceiveMsg>()
                    {
                    }.getType();

                    ReceiveMsg msg = formatReceiveMsg(jsonObject, type);

                    threadPoolExecutor.execute(() ->
                    {
                        clientMsgReceive.controllerSetEDC(msg);
                        msgReceiptHandler(msg);
                    });
                }

                break;

            case EDCCmd.CONTROLLER_PARAM_GET: // 获取控制器的所有配置参数（能耗）

                if (null != clientMsgReceive)
                {

                    Type type = new TypeToken<ReceiveMsg<ControllerDataEDC>>()
                    {
                    }.getType();

                    ReceiveMsg<ControllerDataEDC> msg = formatReceiveMsg(jsonObject, type);

                    threadPoolExecutor.execute(() ->
                    {
                        clientMsgReceive.controllerGetEDC(msg);
                        msgReceiptHandler(msg);
                    });

                }

                break;

            case EDCCmd.CONTROLLER_TIME_SET: // 设置控制器的时间（能耗）

                if (null != clientMsgReceive)
                {

                    Type type = new TypeToken<ReceiveMsg>()
                    {
                    }.getType();

                    ReceiveMsg msg = formatReceiveMsg(jsonObject, type);

                    threadPoolExecutor.execute(() ->
                    {
                        clientMsgReceive.controllerTimeSetEDC(msg);
                    });

                }

                break;

            case EDCCmd.CONTROLLER_TIME_GET: // 获取控制器的时间（能耗）

                if (null != clientMsgReceive)
                {

                    Type type = new TypeToken<ReceiveMsg<TimeData>>()
                    {
                    }.getType();

                    ReceiveMsg<TimeData> msg = formatReceiveMsg(jsonObject, type);

                    threadPoolExecutor.execute(() ->
                    {
                        clientMsgReceive.controllerTimeGetEDC(msg);
                        msgReceiptHandler(msg);
                    });

                }

                break;

            case EDCCmd.CONTROLLER_RESTART: // 重启控制器，相当于重启复位（能耗）

                if (null != clientMsgReceive)
                {

                    Type type = new TypeToken<ReceiveMsg>()
                    {
                    }.getType();

                    ReceiveMsg msg = formatReceiveMsg(jsonObject, type);

                    threadPoolExecutor.execute(() ->
                    {
                        clientMsgReceive.controllerRestartEDC(msg);
                        msgReceiptHandler(msg);
                    });

                }

                break;

            case EDCCmd.CONTROLLER_RESET: // 重置控制器，恢复出厂设置，并重启（能耗）

                if (null != clientMsgReceive)
                {

                    Type type = new TypeToken<ReceiveMsg>()
                    {
                    }.getType();

                    ReceiveMsg msg = formatReceiveMsg(jsonObject, type);

                    threadPoolExecutor.execute(() ->
                    {
                        clientMsgReceive.controllerResetEDC(msg);
                        msgReceiptHandler(msg);
                    });

                }

                break;

            case EDCCmd.CONTROLLER_DELETE: // 删除一个控制器，并删除和它相关的电表（能耗）

                if (null != clientMsgReceive)
                {

                    Type type = new TypeToken<ReceiveMsg>()
                    {
                    }.getType();

                    ReceiveMsg msg = formatReceiveMsg(jsonObject, type);

                    threadPoolExecutor.execute(() ->
                    {
                        clientMsgReceive.controllerDeleteEDC(msg);
                        msgReceiptHandler(msg);
                    });

                }

                break;

            case EDCCmd.AMMETER_ADD: // 新增加一个电表信息（能耗）

                if (null != clientMsgReceive)
                {

                    Type type = new TypeToken<ReceiveMsg<AmmeterCollectParamData>>()
                    {
                    }.getType();

                    ReceiveMsg<AmmeterCollectParamData> msg = formatReceiveMsg(jsonObject, type);

                    threadPoolExecutor.execute(() ->
                    {
                        clientMsgReceive.ammeterAddEDC(msg);
                        msgReceiptHandler(msg);
                    });

                }

                break;

            case EDCCmd.AMMETER_DELETE: // 删除一个电表（能耗）

                if (null != clientMsgReceive)
                {

                    Type type = new TypeToken<ReceiveMsg<AmmeterCollectParamData>>()
                    {
                    }.getType();

                    ReceiveMsg<AmmeterCollectParamData> msg = formatReceiveMsg(jsonObject, type);

                    threadPoolExecutor.execute(() ->
                    {
                        clientMsgReceive.ammeterDeleteEDC(msg);
                        msgReceiptHandler(msg);
                    });

                }

                break;

            case EDCCmd.AMMETER_SET: // 设置一个电表的所有参数（能耗）

                if (null != clientMsgReceive)
                {

                    Type type = new TypeToken<ReceiveMsg<AmmeterCollectParamData>>()
                    {
                    }.getType();

                    ReceiveMsg<AmmeterCollectParamData> msg = formatReceiveMsg(jsonObject, type);

                    threadPoolExecutor.execute(() ->
                    {
                        clientMsgReceive.ammeterSetEDC(msg);
                        msgReceiptHandler(msg);
                    });

                }

                break;

            case EDCCmd.AMMETER_GET: // 获取一个电表的所有配置信息（能耗）

                if (null != clientMsgReceive)
                {

                    Type type = new TypeToken<ReceiveMsg<AmmeterCollectParamData>>()
                    {
                    }.getType();

                    ReceiveMsg<AmmeterCollectParamData> msg = formatReceiveMsg(jsonObject, type);

                    threadPoolExecutor.execute(() ->
                    {
                        clientMsgReceive.ammeterGetEDC(msg);
                        msgReceiptHandler(msg);
                    });

                }

                break;

            case EDCCmd.AMMETER_COLLECTION_SCHEME_ADD: // 新增一个电表的采集方案（能耗）

                if (null != clientMsgReceive)
                {

                    Type type = new TypeToken<ReceiveMsg<ElectricParam>>()
                    {
                    }.getType();

                    ReceiveMsg<ElectricParam> msg = formatReceiveMsg(jsonObject, type);

                    threadPoolExecutor.execute(() ->
                    {
                        clientMsgReceive.ammeterCollectionSchemeAddEDC(msg);
                        msgReceiptHandler(msg);
                    });

                }

                break;

            case EDCCmd.AMMETER_COLLECTION_SCHEME_SET: // 设置一个电表的采集方案（能耗）

                if (null != clientMsgReceive)
                {

                    Type type = new TypeToken<ReceiveMsg<ElectricParam>>()
                    {
                    }.getType();

                    ReceiveMsg<ElectricParam> msg = formatReceiveMsg(jsonObject, type);

                    threadPoolExecutor.execute(() ->
                    {
                        clientMsgReceive.ammeterCollectionSchemeSetEDC(msg);
                        msgReceiptHandler(msg);
                    });

                }

                break;

            case EDCCmd.AMMETER_COLLECTION_SCHEME_DELETE: // 删除一个电表的采集方案（能耗）

                if (null != clientMsgReceive)
                {

                    Type type = new TypeToken<ReceiveMsg<ElectricParam>>()
                    {
                    }.getType();

                    ReceiveMsg<ElectricParam> msg = formatReceiveMsg(jsonObject, type);

                    threadPoolExecutor.execute(() ->
                    {
                        clientMsgReceive.ammeterCollectionSchemeDeleteEDC(msg);
                        msgReceiptHandler(msg);
                    });

                }

                break;

            case EDCCmd.AMMETER_REALTIME_DATA_GET: // 获取实时数据（能耗）

                if (null != clientMsgReceive)
                {

                    Type type = new TypeToken<ReceiveMsg<AmmeterData>>()
                    {
                    }.getType();

                    ReceiveMsg<AmmeterData> msg = formatReceiveMsg(jsonObject, type);

                    threadPoolExecutor.execute(() ->
                    {
                        clientMsgReceive.ammeterRealtimeDataGetEDC(msg);
                        msgReceiptHandler(msg);
                    });

                }

                break;

            case EDCCmd.AMMETER_HISTORY_DATA_GET: // 获取历史数据（能耗）

                if (null != clientMsgReceive)
                {

                    Type type = new TypeToken<ReceiveMsg<AmmeterHistoryData>>()
                    {
                    }.getType();

                    ReceiveMsg<AmmeterHistoryData> msg = formatReceiveMsg(jsonObject, type);

                    threadPoolExecutor.execute(() ->
                    {
                        clientMsgReceive.ammeterHistoryDataGetEDC(msg);
                        msgReceiptHandler(msg);
                    });

                }

                break;

            case EDCCmd.ALARM_DATA_RECEIVE: // 接收报警数据（能耗）

                if (null != clientMsgReceive)
                {

                    Type type = new TypeToken<ReceiveMsg<List<AlarmData>>>()
                    {
                    }.getType();

                    ReceiveMsg<List<AlarmData>> msg = formatReceiveMsg(jsonObject, type);

                    threadPoolExecutor.execute(() -> clientMsgReceive.alarmDataReceiveEDC(msg));

                }

                // 给客户端发送响应数据包（告警可以不回复）
                // respond(ctx, index);

                break;

            case EDCCmd.AMMETER_REALTIME_DATA_RECEIVE: // 接收电表实时数据（能耗）

                if (null != clientMsgReceive)
                {

                    Type type = new TypeToken<ReceiveMsg<List<AmmeterData>>>()
                    {
                    }.getType();

                    ReceiveMsg<List<AmmeterData>> msg = formatReceiveMsg(jsonObject, type);

                    if (msg.getCode() == null)
                    {
                        // 给客户端发送响应数据包
                        respond(ctx, index);
                    }

                    threadPoolExecutorTakeLong.execute(() -> clientMsgReceive.ammeterRealtimeDataReceiveEDC(msg));

                }

                break;

            case EDCCmd.AMMETER_RESUME_DATA_RECEIVE: // 接收断点续传数据（能耗）

                if (null != clientMsgReceive)
                {

                    Type type = new TypeToken<ReceiveMsg<List<AmmeterData>>>()
                    {
                    }.getType();

                    ReceiveMsg<List<AmmeterData>> msg = formatReceiveMsg(jsonObject, type);

                    if (msg.getCode() == null)
                    {
                        // 给客户端发送响应数据包
                        respond(ctx, index);
                    }

                    threadPoolExecutorTakeLong.execute(() -> clientMsgReceive.ammeterResumeDataReceiveEDC(msg));

                }

                break;

            /***************************************** DDC（楼控） *******************************************/

            case DDCCmd.CONTROLLER_ADD: // 新增一个控制器（DDC）

                if (null != clientMsgReceive)
                {
                    Type type = new TypeToken<ReceiveMsg>()
                    {
                    }.getType();

                    ReceiveMsg msg = formatReceiveMsg(jsonObject, type);

                    threadPoolExecutor.execute(() ->
                    {
                        clientMsgReceive.controllerAddDDC(msg);
                        msgReceiptHandler(msg);
                    });
                }

                break;

            case DDCCmd.CONTROLLER_PARAM_SET: // 设置控制器的所有参数（DDC）

                if (null != clientMsgReceive)
                {
                    Type type = new TypeToken<ReceiveMsg>()
                    {
                    }.getType();

                    ReceiveMsg msg = formatReceiveMsg(jsonObject, type);

                    threadPoolExecutor.execute(() ->
                    {
                        clientMsgReceive.controllerSetDDC(msg);
                        msgReceiptHandler(msg);
                    });
                }

                break;

            case DDCCmd.CONTROLLER_DELETE: // 删除一个控制器，并删除和它相关的模块和点（DDC）

                if (null != clientMsgReceive)
                {

                    Type type = new TypeToken<ReceiveMsg>()
                    {
                    }.getType();

                    ReceiveMsg msg = formatReceiveMsg(jsonObject, type);

                    threadPoolExecutor.execute(() ->
                    {
                        clientMsgReceive.controllerDeleteDDC(msg);
                        msgReceiptHandler(msg);
                    });

                }

                break;

            case DDCCmd.CONTROLLER_PARAM_GET: // 获取控制器的所有配置参数（DDC）

                if (null != clientMsgReceive)
                {

                    Type type = new TypeToken<ReceiveMsg<ControllerDataDDC>>()
                    {
                    }.getType();

                    ReceiveMsg<ControllerDataDDC> msg = formatReceiveMsg(jsonObject, type);

                    threadPoolExecutor.execute(() ->
                    {
                        clientMsgReceive.controllerGetDDC(msg);
                        msgReceiptHandler(msg);
                    });

                }

                break;

            case DDCCmd.REMOTE_UPGRADE: // 远程升级（DDC）

                if (null != clientMsgReceive)
                {
                    Type type = new TypeToken<ReceiveMsg>()
                    {
                    }.getType();

                    ReceiveMsg msg = formatReceiveMsg(jsonObject, type);

                    threadPoolExecutor.execute(() ->
                    {
                        clientMsgReceive.remoteUpgradeDDC(msg);
                        msgReceiptHandler(msg);
                    });
                }

                break;


            case DDCCmd.CONTROLLER_TIME_SET: // 设置控制器的时间（DDC）

                if (null != clientMsgReceive)
                {

                    Type type = new TypeToken<ReceiveMsg>()
                    {
                    }.getType();

                    ReceiveMsg msg = formatReceiveMsg(jsonObject, type);

                    threadPoolExecutor.execute(() ->
                    {
                        clientMsgReceive.controllerTimeSetDDC(msg);
                    });

                }

                break;

            case DDCCmd.CONTROLLER_TIME_GET: // 获取控制器的时间（DDC）

                if (null != clientMsgReceive)
                {

                    Type type = new TypeToken<ReceiveMsg<TimeData>>()
                    {
                    }.getType();

                    ReceiveMsg<TimeData> msg = formatReceiveMsg(jsonObject, type);

                    threadPoolExecutor.execute(() ->
                    {
                        clientMsgReceive.controllerTimeGetDDC(msg);
                        msgReceiptHandler(msg);
                    });

                }

                break;

            case DDCCmd.CONTROLLER_RESTART: // 重启控制器，相当于重启复位（DDC）

                if (null != clientMsgReceive)
                {

                    Type type = new TypeToken<ReceiveMsg>()
                    {
                    }.getType();

                    ReceiveMsg msg = formatReceiveMsg(jsonObject, type);

                    threadPoolExecutor.execute(() ->
                    {
                        clientMsgReceive.controllerRestartDDC(msg);
                        msgReceiptHandler(msg);
                    });

                }

                break;

            case DDCCmd.CONTROLLER_RESET: // 重置控制器，恢复出厂设置，并重启（DDC）

                if (null != clientMsgReceive)
                {

                    Type type = new TypeToken<ReceiveMsg>()
                    {
                    }.getType();

                    ReceiveMsg msg = formatReceiveMsg(jsonObject, type);

                    threadPoolExecutor.execute(() ->
                    {
                        clientMsgReceive.controllerResetDDC(msg);
                        msgReceiptHandler(msg);
                    });

                }

                break;

            case DDCCmd.MODULE_ADD: // 新增加一个模块（DDC）

                if (null != clientMsgReceive)
                {

                    Type type = new TypeToken<ReceiveMsg<ModuleParamDDC>>()
                    {
                    }.getType();

                    ReceiveMsg<ModuleParamDDC> msg = formatReceiveMsg(jsonObject, type);

                    threadPoolExecutor.execute(() ->
                    {
                        clientMsgReceive.moduleAddDDC(msg);
                        msgReceiptHandler(msg);
                    });

                }

                break;

            case DDCCmd.MODULE_PARAM_SET: // 设置一个模块的所有参数（DDC）

                if (null != clientMsgReceive)
                {

                    Type type = new TypeToken<ReceiveMsg<ModuleParamDDC>>()
                    {
                    }.getType();

                    ReceiveMsg<ModuleParamDDC> msg = formatReceiveMsg(jsonObject, type);

                    threadPoolExecutor.execute(() ->
                    {
                        clientMsgReceive.moduleSetDDC(msg);
                        msgReceiptHandler(msg);
                    });

                }

                break;

            case DDCCmd.MODULE_DELETE: // 删除一个模块，并删除和模块相关的点（DDC）

                if (null != clientMsgReceive)
                {

                    Type type = new TypeToken<ReceiveMsg<ModuleParamDDC>>()
                    {
                    }.getType();

                    ReceiveMsg<ModuleParamDDC> msg = formatReceiveMsg(jsonObject, type);

                    threadPoolExecutor.execute(() ->
                    {
                        clientMsgReceive.moduleDeleteDDC(msg);
                        msgReceiptHandler(msg);
                    });

                }

                break;

            case DDCCmd.MODULE_PARAM_GET: // 获取一个模块的所有配置信息（DDC）

                if (null != clientMsgReceive)
                {

                    Type type = new TypeToken<ReceiveMsg<ModuleParamDDC>>()
                    {
                    }.getType();

                    ReceiveMsg<ModuleParamDDC> msg = formatReceiveMsg(jsonObject, type);

                    threadPoolExecutor.execute(() ->
                    {
                        clientMsgReceive.moduleGetDDC(msg);
                        msgReceiptHandler(msg);
                    });

                }

                break;

            case DDCCmd.REAL_POINT_DATA_RECEIVE: // 接收DDC实点更新的点信息（DDC）

                if (null != clientMsgReceive)
                {

                    Type type = new TypeToken<ReceiveMsg<List<PointDataDDC>>>()
                    {
                    }.getType();

                    ReceiveMsg<List<PointDataDDC>> msg = formatReceiveMsg(jsonObject, type);

                    if (msg.getCode() == null || msg.getCode() == 0)
                    {
                        // 给客户端发送响应数据包
                        respond(ctx, index);
                    }

                    threadPoolExecutor.execute(() -> clientMsgReceive.realPointDataReceiveDDC(msg));

                }

                break;

            case DDCCmd.MODULE_ERROR_CODE_GET: // 接收一个模块的错误代码（DDC）

                if (null != clientMsgReceive)
                {

                    Type type = new TypeToken<ReceiveMsg<ErrorCodeDDC>>()
                    {
                    }.getType();

                    ReceiveMsg<ErrorCodeDDC> msg = formatReceiveMsg(jsonObject, type);

                    if (msg.getCode() == null || msg.getCode() == 0)
                    {
                        // 给客户端发送响应数据包
                        respond(ctx, index);
                    }

                    threadPoolExecutor.execute(() -> clientMsgReceive.moduleErrorCodeGetDDC(msg));

                }

                break;

            case DDCCmd.MODULE_ERROR_CODE_GET_ALL: // 批量接收模块的错误代码（DDC）

                if (null != clientMsgReceive)
                {

                    Type type = new TypeToken<ReceiveMsg<List<ErrorCodeDDC>>>()
                    {
                    }.getType();

                    ReceiveMsg<List<ErrorCodeDDC>> msg = formatReceiveMsg(jsonObject, type);

                    if (msg.getCode() == null || msg.getCode() == 0)
                    {
                        // 给客户端发送响应数据包
                        respond(ctx, index);
                    }

                    threadPoolExecutor.execute(() -> clientMsgReceive.moduleErrorCodeGetDDC_ALL(msg));

                }

                break;

            case DDCCmd.POINT_ADD: // 新增加一个逻辑点（DDC）

                if (null != clientMsgReceive)
                {

                    Type type = new TypeToken<ReceiveMsg<PointParamDDC>>()
                    {
                    }.getType();

                    ReceiveMsg<PointParamDDC> msg = formatReceiveMsg(jsonObject, type);

                    threadPoolExecutor.execute(() ->
                    {
                        clientMsgReceive.pointAddDDC(msg);
                        msgReceiptHandler(msg);
                    });

                }

                break;

            case DDCCmd.POINT_PARAM_SET: // 设置一个逻辑点的所有参数（DDC）

                if (null != clientMsgReceive)
                {

                    Type type = new TypeToken<ReceiveMsg<PointParamDDC>>()
                    {
                    }.getType();

                    ReceiveMsg<PointParamDDC> msg = formatReceiveMsg(jsonObject, type);

                    threadPoolExecutor.execute(() ->
                    {
                        clientMsgReceive.pointParamSetDDC(msg);
                        msgReceiptHandler(msg);
                    });

                }

                break;

            case DDCCmd.POINT_VALUE_SET:  // 设置一个逻辑点的值（DDC）

                if (null != clientMsgReceive)
                {

                    Type type = new TypeToken<ReceiveMsg<PointDataDDC>>()
                    {
                    }.getType();

                    ReceiveMsg<PointDataDDC> msg = formatReceiveMsg(jsonObject, type);

                    threadPoolExecutor.execute(() ->
                    {
                        clientMsgReceive.pointValueSetDDC(msg);
                        msgReceiptHandler(msg);
                    });

                }

                break;

            case DDCCmd.POINT_VALUE_BY_NAME_SET:  // 设置逻辑点的值，根据点的名字（DDC）

                if (null != clientMsgReceive)
                {

                    Type type = new TypeToken<ReceiveMsg<PointParamDDC>>()
                    {
                    }.getType();

                    ReceiveMsg<PointParamDDC> msg = formatReceiveMsg(jsonObject, type);

                    threadPoolExecutor.execute(() ->
                    {
                        clientMsgReceive.pointValueByNameSetDDC(msg);
                        msgReceiptHandler(msg);
                    });

                }

                break;

            case DDCCmd.POINT_DELETE:  // 删除一个逻辑点（DDC）

                if (null != clientMsgReceive)
                {

                    Type type = new TypeToken<ReceiveMsg<PointParamDDC>>()
                    {
                    }.getType();

                    ReceiveMsg<PointParamDDC> msg = formatReceiveMsg(jsonObject, type);

                    threadPoolExecutor.execute(() ->
                    {
                        clientMsgReceive.pointDeleteDDC(msg);
                        msgReceiptHandler(msg);
                    });

                }

                break;

            case DDCCmd.POINT_PARAM_GET:  // 获取一个逻辑点的所有配置参数（DDC）

                if (null != clientMsgReceive)
                {

                    Type type = new TypeToken<ReceiveMsg<PointParamDDC>>()
                    {
                    }.getType();

                    ReceiveMsg<PointParamDDC> msg = formatReceiveMsg(jsonObject, type);

                    threadPoolExecutor.execute(() ->
                    {
                        clientMsgReceive.pointParamGetDDC(msg);
                        msgReceiptHandler(msg);
                    });

                }

                break;

            case DDCCmd.POINT_ALARM_DATA_GET:  // 获取一个逻辑点的报警信息（DDC 未使用，告警逻辑由上位机实现）

                if (null != clientMsgReceive)
                {

                    Type type = new TypeToken<ReceiveMsg<AlarmPointDataDDC>>()
                    {
                    }.getType();

                    ReceiveMsg<AlarmPointDataDDC> msg = formatReceiveMsg(jsonObject, type);

                    threadPoolExecutor.execute(() ->
                    {
                        clientMsgReceive.pointAlarmDataGetDDC(msg);
                        msgReceiptHandler(msg);
                    });

                }

                break;

            case DDCCmd.POINT_DATA_ALL_RECEIVE:  // 接收DDC的全部点信息（DDC）

                if (null != clientMsgReceive)
                {

                    Type type = new TypeToken<ReceiveMsg<List<PointDataDDC>>>()
                    {
                    }.getType();

                    ReceiveMsg<List<PointDataDDC>> msg = formatReceiveMsg(jsonObject, type);

                    if (msg.getCode() == null || msg.getCode() == 0)
                    {
                        // 给客户端发送响应数据包
                        respond(ctx, index);
                    }

                    threadPoolExecutorTakeLong.execute(() -> clientMsgReceive.pointDataAllReceiveDDC(msg));

                }


                break;

            case DDCCmd.VIRTUAL_POINT_DATA_RECEIVE:  // 接收虚点信息（DDC）

                if (null != clientMsgReceive)
                {

                    Type type = new TypeToken<ReceiveMsg<List<PointDataDDC>>>()
                    {
                    }.getType();

                    ReceiveMsg<List<PointDataDDC>> msg = formatReceiveMsg(jsonObject, type);

                    if (msg.getCode() == null || msg.getCode() == 0)
                    {
                        // 给客户端发送响应数据包
                        respond(ctx, index);
                    }

                    threadPoolExecutor.execute(() -> clientMsgReceive.virtualPointDataReceiveDDC(msg));
                }

                break;

            case DDCCmd.SCENE_ADD:  // 增加一个场景（DDC）

                if (null != clientMsgReceive)
                {

                    Type type = new TypeToken<ReceiveMsg<SceneDataDDC>>()
                    {
                    }.getType();

                    ReceiveMsg<SceneDataDDC> msg = formatReceiveMsg(jsonObject, type);

                    threadPoolExecutor.execute(() ->
                    {
                        clientMsgReceive.sceneAddDDC(msg);
                        msgReceiptHandler(msg);
                    });

                }

                break;

            case DDCCmd.SCENE_PARAM_SET:  // 设置一个场景的所有参数（DDC）

                if (null != clientMsgReceive)
                {

                    Type type = new TypeToken<ReceiveMsg<SceneDataDDC>>()
                    {
                    }.getType();

                    ReceiveMsg<SceneDataDDC> msg = formatReceiveMsg(jsonObject, type);

                    threadPoolExecutor.execute(() ->
                    {
                        clientMsgReceive.sceneParamSetDDC(msg);
                        msgReceiptHandler(msg);
                    });

                }

                break;

            case DDCCmd.SCENE_DELETE:  // 删除一个场景（DDC）

                if (null != clientMsgReceive)
                {

                    Type type = new TypeToken<ReceiveMsg<SceneDataDDC>>()
                    {
                    }.getType();

                    ReceiveMsg<SceneDataDDC> msg = formatReceiveMsg(jsonObject, type);

                    threadPoolExecutor.execute(() ->
                    {
                        clientMsgReceive.sceneDeleteDDC(msg);
                        msgReceiptHandler(msg);
                    });

                }

                break;

            case DDCCmd.SCENE_PARAM_GET:  // 获取一个场景的配置信息（DDC）

                if (null != clientMsgReceive)
                {

                    Type type = new TypeToken<ReceiveMsg<SceneParamDDC>>()
                    {
                    }.getType();

                    ReceiveMsg<SceneParamDDC> msg = formatReceiveMsg(jsonObject, type);

                    threadPoolExecutor.execute(() ->
                    {
                        clientMsgReceive.sceneParamGetDDC(msg);
                        msgReceiptHandler(msg);
                    });

                }

                break;

            case DDCCmd.SCENE_MODE_PARAM_GET:  // 获取场景下的单个模式信息

                if (null != clientMsgReceive)
                {

                    Type type = new TypeToken<ReceiveMsg<SceneDataDDC>>()
                    {
                    }.getType();

                    ReceiveMsg<SceneDataDDC> msg = formatReceiveMsg(jsonObject, type);

                    threadPoolExecutor.execute(() ->
                    {
                        clientMsgReceive.controlParamDDC(msg);
                        msgReceiptHandler(msg);
                    });

                }

                break;

            case DDCCmd.SCENE_MODE_PARAM_DELETE:  // 删除场景下的单个模式信息

                if (null != clientMsgReceive)
                {

                    Type type = new TypeToken<ReceiveMsg<SceneParamDDC>>()
                    {
                    }.getType();

                    ReceiveMsg<SceneParamDDC> msg = formatReceiveMsg(jsonObject, type);

                    threadPoolExecutor.execute(() ->
                    {
                        clientMsgReceive.sceneModeParamDeleteDDC(msg);
                        msgReceiptHandler(msg);
                    });

                }

                break;

            case DDCCmd.PLAN_ADD:  // 增加一条计划（DDC）

                if (null != clientMsgReceive)
                {

                    Type type = new TypeToken<ReceiveMsg<PlanParamDDC>>()
                    {
                    }.getType();

                    ReceiveMsg<PlanParamDDC> msg = formatReceiveMsg(jsonObject, type);

                    threadPoolExecutor.execute(() ->
                    {
                        clientMsgReceive.planAddDDC(msg);
                        msgReceiptHandler(msg);
                    });

                }

                break;

            case DDCCmd.PLAN_PARAM_SET:  // 修改一条计划的所有参数（DDC）

                if (null != clientMsgReceive)
                {

                    Type type = new TypeToken<ReceiveMsg<PlanParamDDC>>()
                    {
                    }.getType();

                    ReceiveMsg<PlanParamDDC> msg = formatReceiveMsg(jsonObject, type);

                    threadPoolExecutor.execute(() ->
                    {
                        clientMsgReceive.planParamSetDDC(msg);
                        msgReceiptHandler(msg);
                    });

                }

                break;

            case DDCCmd.PLAN_DELETE:  // 删除一条计划（DDC）

                if (null != clientMsgReceive)
                {

                    Type type = new TypeToken<ReceiveMsg<PlanParamDDC>>()
                    {
                    }.getType();

                    ReceiveMsg<PlanParamDDC> msg = formatReceiveMsg(jsonObject, type);

                    threadPoolExecutor.execute(() ->
                    {
                        clientMsgReceive.planDeleteDDC(msg);
                        msgReceiptHandler(msg);
                    });

                }

            case DDCCmd.PLAN_PARAM_GET:  // 获取一条计划的所有参数（DDC）

                if (null != clientMsgReceive)
                {

                    Type type = new TypeToken<ReceiveMsg<PlanParamDDC>>()
                    {
                    }.getType();

                    ReceiveMsg<PlanParamDDC> msg = formatReceiveMsg(jsonObject, type);

                    threadPoolExecutor.execute(() ->
                    {
                        clientMsgReceive.planParamGetDDC(msg);
                        msgReceiptHandler(msg);
                    });

                }

                break;

            /***************************************** LDC（照明） *******************************************/

            case LDCCmd.CONTROLLER_ADD: // 新增一个IP路由器（LDC）

                if (null != clientMsgReceive)
                {
                    Type type = new TypeToken<ReceiveMsg>()
                    {
                    }.getType();

                    ReceiveMsg msg = formatReceiveMsg(jsonObject, type);

                    threadPoolExecutor.execute(() ->
                    {
                        clientMsgReceive.controllerAddLDC(msg);
                        msgReceiptHandler(msg);
                    });
                }

                break;

            case LDCCmd.CONTROLLER_PARAM_SET: // 设置IP路由器的所有参数（LDC）

                if (null != clientMsgReceive)
                {
                    Type type = new TypeToken<ReceiveMsg>()
                    {
                    }.getType();

                    ReceiveMsg msg = formatReceiveMsg(jsonObject, type);

                    threadPoolExecutor.execute(() ->
                    {
                        clientMsgReceive.controllerSetLDC(msg);
                        msgReceiptHandler(msg);
                    });
                }

                break;

            case LDCCmd.CONTROLLER_DELETE: // 删除一个IP路由器，并删除和它相关的模块和点（LDC）

                if (null != clientMsgReceive)
                {

                    Type type = new TypeToken<ReceiveMsg>()
                    {
                    }.getType();

                    ReceiveMsg msg = formatReceiveMsg(jsonObject, type);

                    threadPoolExecutor.execute(() ->
                    {
                        clientMsgReceive.controllerDeleteLDC(msg);
                        msgReceiptHandler(msg);
                    });

                }

                break;

            case LDCCmd.CONTROLLER_PARAM_GET: // 获取IP路由器的所有配置参数（LDC）

                if (null != clientMsgReceive)
                {

                    Type type = new TypeToken<ReceiveMsg<ControllerDataLDC>>()
                    {
                    }.getType();

                    ReceiveMsg<ControllerDataLDC> msg = formatReceiveMsg(jsonObject, type);

                    threadPoolExecutor.execute(() ->
                    {
                        clientMsgReceive.controllerGetLDC(msg);
                        msgReceiptHandler(msg);
                    });

                }

                break;

            case LDCCmd.REMOTE_UPGRADE: // 远程升级（LDC）

                if (null != clientMsgReceive)
                {
                    Type type = new TypeToken<ReceiveMsg>()
                    {
                    }.getType();

                    ReceiveMsg msg = formatReceiveMsg(jsonObject, type);

                    threadPoolExecutor.execute(() ->
                    {
                        clientMsgReceive.remoteUpgradeLDC(msg);
                        msgReceiptHandler(msg);
                    });
                }

                break;


            case LDCCmd.CONTROLLER_TIME_SET: // 设置IP路由器的时间（LDC）

                if (null != clientMsgReceive)
                {

                    Type type = new TypeToken<ReceiveMsg>()
                    {
                    }.getType();

                    ReceiveMsg msg = formatReceiveMsg(jsonObject, type);

                    threadPoolExecutor.execute(() ->
                    {
                        clientMsgReceive.controllerTimeSetLDC(msg);
                    });

                }

                break;

            case LDCCmd.CONTROLLER_TIME_GET: // 获取IP路由器的时间（LDC）

                if (null != clientMsgReceive)
                {

                    Type type = new TypeToken<ReceiveMsg<TimeData>>()
                    {
                    }.getType();

                    ReceiveMsg<TimeData> msg = formatReceiveMsg(jsonObject, type);

                    threadPoolExecutor.execute(() ->
                    {
                        clientMsgReceive.controllerTimeGetLDC(msg);
                        msgReceiptHandler(msg);
                    });

                }

                break;

            case LDCCmd.CONTROLLER_RESTART: // 重启IP路由器，相当于重启复位（LDC）

                if (null != clientMsgReceive)
                {

                    Type type = new TypeToken<ReceiveMsg>()
                    {
                    }.getType();

                    ReceiveMsg msg = formatReceiveMsg(jsonObject, type);

                    threadPoolExecutor.execute(() ->
                    {
                        clientMsgReceive.controllerRestartLDC(msg);
                        msgReceiptHandler(msg);
                    });

                }

                break;

            case LDCCmd.CONTROLLER_RESET: // 重置IP路由器，恢复出厂设置，并重启（LDC）

                if (null != clientMsgReceive)
                {

                    Type type = new TypeToken<ReceiveMsg>()
                    {
                    }.getType();

                    ReceiveMsg msg = formatReceiveMsg(jsonObject, type);

                    threadPoolExecutor.execute(() ->
                    {
                        clientMsgReceive.controllerResetLDC(msg);
                        msgReceiptHandler(msg);
                    });

                }

                break;

            case LDCCmd.MODULE_ADD: // 新增加一个模块（LDC）

                if (null != clientMsgReceive)
                {

                    Type type = new TypeToken<ReceiveMsg<ModuleParamLDC>>()
                    {
                    }.getType();

                    ReceiveMsg<ModuleParamLDC> msg = formatReceiveMsg(jsonObject, type);

                    threadPoolExecutor.execute(() ->
                    {
                        clientMsgReceive.moduleAddLDC(msg);
                        msgReceiptHandler(msg);
                    });

                }

                break;

            case LDCCmd.MODULE_PARAM_SET: // 设置一个模块的所有参数（LDC）

                if (null != clientMsgReceive)
                {

                    Type type = new TypeToken<ReceiveMsg<ModuleParamLDC>>()
                    {
                    }.getType();

                    ReceiveMsg<ModuleParamLDC> msg = formatReceiveMsg(jsonObject, type);

                    threadPoolExecutor.execute(() ->
                    {
                        clientMsgReceive.moduleSetLDC(msg);
                        msgReceiptHandler(msg);
                    });

                }

                break;

            case LDCCmd.MODULE_DELETE: // 删除一个模块，并删除和模块相关的点（LDC）

                if (null != clientMsgReceive)
                {

                    Type type = new TypeToken<ReceiveMsg<ModuleParamLDC>>()
                    {
                    }.getType();

                    ReceiveMsg<ModuleParamLDC> msg = formatReceiveMsg(jsonObject, type);

                    threadPoolExecutor.execute(() ->
                    {
                        clientMsgReceive.moduleDeleteLDC(msg);
                        msgReceiptHandler(msg);
                    });

                }

                break;

            case LDCCmd.MODULE_PARAM_GET: // 获取一个模块的所有配置信息（LDC）

                if (null != clientMsgReceive)
                {

                    Type type = new TypeToken<ReceiveMsg<ModuleParamLDC>>()
                    {
                    }.getType();

                    ReceiveMsg<ModuleParamLDC> msg = formatReceiveMsg(jsonObject, type);

                    threadPoolExecutor.execute(() ->
                    {
                        clientMsgReceive.moduleGetLDC(msg);
                        msgReceiptHandler(msg);
                    });

                }

                break;

            case LDCCmd.REAL_POINT_DATA_RECEIVE: // 接收LDC实点更新的点信息（LDC）

                if (null != clientMsgReceive)
                {

                    Type type = new TypeToken<ReceiveMsg<List<PointDataLDC>>>()
                    {
                    }.getType();

                    ReceiveMsg<List<PointDataLDC>> msg = formatReceiveMsg(jsonObject, type);

                    if (msg.getCode() == null)
                    {
                        // 给客户端发送响应数据包
                        respond(ctx, index);
                    }

                    threadPoolExecutor.execute(() -> clientMsgReceive.realPointDataReceiveLDC(msg));

                }

                break;

            case LDCCmd.MODULE_ERROR_CODE_GET: // 接收一个模块的错误代码（LDC）

                if (null != clientMsgReceive)
                {

                    Type type = new TypeToken<ReceiveMsg<ErrorCodeLDC>>()
                    {
                    }.getType();

                    ReceiveMsg<ErrorCodeLDC> msg = formatReceiveMsg(jsonObject, type);

                    if (msg.getCode() == null)
                    {
                        // 给客户端发送响应数据包
                        respond(ctx, index);
                    }

                    threadPoolExecutor.execute(() -> clientMsgReceive.moduleErrorCodeGetLDC(msg));

                }

                break;

            case LDCCmd.MODULE_ERROR_CODE_GET_ALL: // 批量接收一个模块的错误代码（LDC）

                if (null != clientMsgReceive)
                {

                    Type type = new TypeToken<ReceiveMsg<List<ErrorCodeLDC>>>()
                    {
                    }.getType();

                    ReceiveMsg<List<ErrorCodeLDC>> msg = formatReceiveMsg(jsonObject, type);

                    if (msg.getCode() == null)
                    {
                        // 给客户端发送响应数据包
                        respond(ctx, index);
                    }

                    threadPoolExecutor.execute(() -> clientMsgReceive.moduleErrorCodeGetLDCALL(msg));

                }

                break;

            case LDCCmd.POINT_ADD: // 新增加一个逻辑点（LDC）

                if (null != clientMsgReceive)
                {

                    Type type = new TypeToken<ReceiveMsg<PointParamLDC>>()
                    {
                    }.getType();

                    ReceiveMsg<PointParamLDC> msg = formatReceiveMsg(jsonObject, type);

                    threadPoolExecutor.execute(() ->
                    {
                        clientMsgReceive.pointAddLDC(msg);
                        msgReceiptHandler(msg);
                    });

                }

                break;

            case LDCCmd.POINT_PARAM_SET: // 设置一个逻辑点的所有参数（LDC）

                if (null != clientMsgReceive)
                {

                    Type type = new TypeToken<ReceiveMsg<PointParamLDC>>()
                    {
                    }.getType();

                    ReceiveMsg<PointParamLDC> msg = formatReceiveMsg(jsonObject, type);

                    threadPoolExecutor.execute(() ->
                    {
                        clientMsgReceive.pointParamSetLDC(msg);
                        msgReceiptHandler(msg);
                    });

                }

                break;

            case LDCCmd.POINT_VALUE_SET:  // 设置一个逻辑点的值（LDC）

                if (null != clientMsgReceive)
                {

                    Type type = new TypeToken<ReceiveMsg<PointDataLDC>>()
                    {
                    }.getType();

                    ReceiveMsg<PointDataLDC> msg = formatReceiveMsg(jsonObject, type);

                    threadPoolExecutor.execute(() ->
                    {
                        clientMsgReceive.pointValueSetLDC(msg);
                        msgReceiptHandler(msg);
                    });

                }

                break;

            case LDCCmd.POINT_VALUE_BY_NAME_SET:  // 设置逻辑点的值，根据点的名字（LDC）

                if (null != clientMsgReceive)
                {

                    Type type = new TypeToken<ReceiveMsg<PointParamLDC>>()
                    {
                    }.getType();

                    ReceiveMsg<PointParamLDC> msg = formatReceiveMsg(jsonObject, type);

                    threadPoolExecutor.execute(() ->
                    {
                        clientMsgReceive.pointValueByNameSetLDC(msg);
                        msgReceiptHandler(msg);
                    });

                }

                break;

            case LDCCmd.POINT_DELETE:  // 删除一个逻辑点（LDC）

                if (null != clientMsgReceive)
                {

                    Type type = new TypeToken<ReceiveMsg<PointParamLDC>>()
                    {
                    }.getType();

                    ReceiveMsg<PointParamLDC> msg = formatReceiveMsg(jsonObject, type);

                    threadPoolExecutor.execute(() ->
                    {
                        clientMsgReceive.pointDeleteLDC(msg);
                        msgReceiptHandler(msg);
                    });

                }

                break;

            case LDCCmd.POINT_PARAM_GET:  // 获取一个逻辑点的所有配置参数（LDC）

                if (null != clientMsgReceive)
                {

                    Type type = new TypeToken<ReceiveMsg<PointParamLDC>>()
                    {
                    }.getType();

                    ReceiveMsg<PointParamLDC> msg = formatReceiveMsg(jsonObject, type);

                    threadPoolExecutor.execute(() ->
                    {
                        clientMsgReceive.pointParamGetLDC(msg);
                        msgReceiptHandler(msg);
                    });

                }

                break;

            case LDCCmd.POINT_ALARM_DATA_GET:  // 获取一个逻辑点的报警信息（LDC 未使用，告警逻辑由上位机实现）

                if (null != clientMsgReceive)
                {

                    Type type = new TypeToken<ReceiveMsg<AlarmPointDataLDC>>()
                    {
                    }.getType();

                    ReceiveMsg<AlarmPointDataLDC> msg = formatReceiveMsg(jsonObject, type);

                    threadPoolExecutor.execute(() ->
                    {
                        clientMsgReceive.pointAlarmDataGetLDC(msg);
                        msgReceiptHandler(msg);
                    });

                }

                break;

            case LDCCmd.POINT_DATA_ALL_RECEIVE:  // 接收LDC的全部点信息（LDC）

                if (null != clientMsgReceive)
                {

                    Type type = new TypeToken<ReceiveMsg<List<PointDataLDC>>>()
                    {
                    }.getType();

                    ReceiveMsg<List<PointDataLDC>> msg = formatReceiveMsg(jsonObject, type);

                    if (msg.getCode() == null)
                    {
                        // 给客户端发送响应数据包
                        respond(ctx, index);
                    }

                    threadPoolExecutorTakeLong.execute(() -> clientMsgReceive.pointDataAllReceiveLDC(msg));
                }

                break;

            case LDCCmd.VIRTUAL_POINT_DATA_RECEIVE:  // 接收虚点信息（LDC）

                if (null != clientMsgReceive)
                {

                    Type type = new TypeToken<ReceiveMsg<List<PointDataLDC>>>()
                    {
                    }.getType();

                    ReceiveMsg<List<PointDataLDC>> msg = formatReceiveMsg(jsonObject, type);

                    if (msg.getCode() == null)
                    {
                        // 给客户端发送响应数据包
                        respond(ctx, index);
                    }

                    threadPoolExecutor.execute(() -> clientMsgReceive.virtualPointDataReceiveLDC(msg));

                }


                break;

            case LDCCmd.SCENE_ADD:  // 增加一个场景（LDC）

                if (null != clientMsgReceive)
                {

                    Type type = new TypeToken<ReceiveMsg<SceneDataLDC>>()
                    {
                    }.getType();

                    ReceiveMsg<SceneDataLDC> msg = formatReceiveMsg(jsonObject, type);

                    threadPoolExecutor.execute(() ->
                    {
                        clientMsgReceive.sceneAddLDC(msg);
                        msgReceiptHandler(msg);
                    });

                }

                break;

            case LDCCmd.SCENE_PARAM_SET:  // 设置一个场景的所有参数（LDC）

                if (null != clientMsgReceive)
                {

                    Type type = new TypeToken<ReceiveMsg<SceneDataLDC>>()
                    {
                    }.getType();

                    ReceiveMsg<SceneDataLDC> msg = formatReceiveMsg(jsonObject, type);

                    threadPoolExecutor.execute(() ->
                    {
                        clientMsgReceive.sceneParamSetLDC(msg);
                        msgReceiptHandler(msg);
                    });

                }

                break;

            case LDCCmd.SCENE_DELETE: //删除一个场景（LDC）

                if (null != clientMsgReceive)
                {

                    Type type = new TypeToken<ReceiveMsg<SceneDataLDC>>()
                    {
                    }.getType();

                    ReceiveMsg<SceneDataLDC> msg = formatReceiveMsg(jsonObject, type);

                    threadPoolExecutor.execute(() ->
                    {
                        clientMsgReceive.sceneDeleteLDC(msg);
                        msgReceiptHandler(msg);
                    });

                }

                break;

            case LDCCmd.SCENE_MODE_PARAM_DELETE: //删除一个场景模式(LDC)

                if (null != clientMsgReceive)
                {

                    Type type = new TypeToken<ReceiveMsg<SceneModeParamLDC>>()
                    {
                    }.getType();

                    ReceiveMsg<SceneModeParamLDC> msg = formatReceiveMsg(jsonObject, type);

                    threadPoolExecutor.execute(() ->
                    {
                        clientMsgReceive.sceneModeParamDeleteLDC(msg);
                        msgReceiptHandler(msg);
                    });

                }

                break;

            case LDCCmd.SCENE_PARAM_GET:  // 获取一个场景的配置信息（LDC）

                if (null != clientMsgReceive)
                {

                    Type type = new TypeToken<ReceiveMsg<SceneParamLDC>>()
                    {
                    }.getType();

                    ReceiveMsg<SceneParamLDC> msg = formatReceiveMsg(jsonObject, type);

                    threadPoolExecutor.execute(() ->
                    {
                        clientMsgReceive.sceneParamGetLDC(msg);
                        msgReceiptHandler(msg);
                    });

                }

                break;

            case LDCCmd.PLAN_ADD:  // 增加一条计划（LDC）

                if (null != clientMsgReceive)
                {

                    Type type = new TypeToken<ReceiveMsg<PlanParamLDC>>()
                    {
                    }.getType();

                    ReceiveMsg<PlanParamLDC> msg = formatReceiveMsg(jsonObject, type);

                    threadPoolExecutor.execute(() ->
                    {
                        clientMsgReceive.planAddLDC(msg);
                        msgReceiptHandler(msg);
                    });

                }

                break;

            case LDCCmd.PLAN_PARAM_SET:  // 修改一条计划的所有参数（LDC）

                if (null != clientMsgReceive)
                {

                    Type type = new TypeToken<ReceiveMsg<PlanParamLDC>>()
                    {
                    }.getType();

                    ReceiveMsg<PlanParamLDC> msg = formatReceiveMsg(jsonObject, type);

                    threadPoolExecutor.execute(() ->
                    {
                        clientMsgReceive.planParamSetLDC(msg);
                        msgReceiptHandler(msg);
                    });

                }

                break;

            case LDCCmd.PLAN_DELETE:  // 删除一条计划（LDC）

                if (null != clientMsgReceive)
                {

                    Type type = new TypeToken<ReceiveMsg<PlanParamLDC>>()
                    {
                    }.getType();

                    ReceiveMsg<PlanParamLDC> msg = formatReceiveMsg(jsonObject, type);

                    threadPoolExecutor.execute(() ->
                    {
                        clientMsgReceive.planDeleteLDC(msg);
                        msgReceiptHandler(msg);
                    });

                }

            case LDCCmd.PLAN_PARAM_GET:  // 获取一条计划的所有参数（LDC）

                if (null != clientMsgReceive)
                {

                    Type type = new TypeToken<ReceiveMsg<PlanParamLDC>>()
                    {
                    }.getType();

                    ReceiveMsg<PlanParamLDC> msg = formatReceiveMsg(jsonObject, type);

                    threadPoolExecutor.execute(() ->
                    {
                        clientMsgReceive.planParamGetLDC(msg);
                        msgReceiptHandler(msg);
                    });

                }

                break;

            case LDCCmd.SCENE_MODE_PARAM_GET: //获取场景数据和场景下的一条模式数据

                if (null != clientMsgReceive)

                {
                    Type type = new TypeToken<ReceiveMsg<ControlParamLDC>>()
                    {
                    }.getType();

                    ReceiveMsg<ControlParamLDC> msg = formatReceiveMsg(jsonObject, type);

                    if (msg.getCode() == null)
                    {
                        // 给客户端发送响应数据包
                        respond(ctx, index);
                    }

                    threadPoolExecutor.execute(() ->
                    {
                        clientMsgReceive.controlParamLDC(msg);
                        msgReceiptHandler(msg);
                    });
                }
                break;

            default:
                break;
        }
    }

    /**
     * 消息回执处理
     * 1.根据通道id获取消息队列
     * 2.获取队列中的消息
     * 3.如果消息是队列中的回复消息，则弹出该消息队列
     * 4.如果队列中有下一个消息则直接发送
     * @param msg
     */
    public static void msgReceiptHandler(JsonMsg msg)
    {
        if (msg == null)
        {
            return;
        }

        String uuid = msg.getUuid();
        String ip = msg.getIp();

        if (uuid == null || uuid.isEmpty() || ip == null || ip.isEmpty())
        {
            return;
        }

        Queue<JsonMsg> queue = SendMsgHandler.msgQueue.get(ip);

        if (queue == null || queue.isEmpty())
        {
            return;
        }

        JsonMsg queueMsg = queue.peek(); // 返回队首元素，但是不删除。

        if (uuid.equals(queueMsg.getUuid()))
        {
            queue.poll(); // 从队首删除并返回该元素

            JsonMsg nextMsg = queue.peek();

            if (nextMsg == null)
            {
                return;
            }

            SendMsgHandler.postEvent(ip, nextMsg);
        }else
        {
            SendMsgHandler.postEvent(ip, queueMsg);
        }

    }
}
