package com.efounder.JEnterprise.service.systemcenter.interfaceconfig.Impl;

import com.efounder.JEnterprise.commhandler.PointDataResponse;
import com.efounder.JEnterprise.initializer.DeviceFunctionPointCache;
import com.efounder.JEnterprise.initializer.MessageMonitoringCache;
import com.efounder.JEnterprise.mapper.basedatamanage.eqmanage.BESSbdyMapper;
import com.efounder.JEnterprise.model.systemcenter.Interfaceconfig.DeviceFunctionPointModel;
import com.efounder.JEnterprise.model.systemcenter.Interfaceconfig.FunctionPointStateModel;
import com.efounder.JEnterprise.model.systemcenter.Interfaceconfig.MessageMonitoringModel;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;
import org.ace.httpclient.OkHttpClient;
import org.ace.httpclient.exception.HttpException;
import org.ace.httpclient.request.RequestParams;
import org.apache.shiro.cache.Cache;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author xiepufeng
 * @date 2020/12/19 8:24
 */
@Service
public class FunctionPointStateServiceImpl
{
    @Resource
    private BESSbdyMapper besSbdyMapper;

    @Resource
    private DeviceFunctionPointCache deviceFunctionPointCache;

    @Resource
    private MessageMonitoringCache messageMonitoringCache;

    /**
     * 设备状态回执处理
     *
     * @param data
     */
    public void stateReceiptApiHandle(List<PointDataResponse> data)
    {
        if (data == null || data.isEmpty())
        {
            return;
        }

        // 设备功能点位缓存
        Cache<Integer, DeviceFunctionPointModel> deviceFunctionPointModelCache = deviceFunctionPointCache.getDeviceFunctionPointCache();

        if (deviceFunctionPointModelCache == null)
        {
            return;
        }

        Collection<DeviceFunctionPointModel> deviceFunctionPointModels = deviceFunctionPointModelCache.values();

        if (deviceFunctionPointModels == null || deviceFunctionPointModels.isEmpty())
        {
            return;
        }

        Map<String, PointDataResponse> dataMap = data.stream().collect(Collectors.toMap(PointDataResponse::getSysNameOld, datum -> datum, (key1, key2) -> key2));

        List<FunctionPointStateModel> functionPointStateModels = new ArrayList<>();

        // 遍历设备功能点
        deviceFunctionPointModels.forEach(item ->
        {

            String deviceId = item.getDeviceId();
            Integer deviceFunctionId = item.getDeviceFunctionId();
            String sysNameOld = item.getSysName();

            PointDataResponse pointDataResponse = dataMap.get(sysNameOld);

            if (pointDataResponse == null)
            {
                return;
            }

            String value = pointDataResponse.getValue();

            item.setValue(value);

            FunctionPointStateModel functionPointStateModel = new FunctionPointStateModel();

            functionPointStateModel.setDeviceId(deviceId);
            functionPointStateModel.setDeviceFunctionId(deviceFunctionId);
            functionPointStateModel.setValue(value);
            functionPointStateModel.setSysName(sysNameOld);

            functionPointStateModels.add(functionPointStateModel);

        });


        // 消息监听定义对象
        Cache<Integer, MessageMonitoringModel> messageMonitoringModelCache = messageMonitoringCache.getMessageMonitoringCache();

        if (messageMonitoringModelCache == null)
        {
            return;
        }

        Collection<MessageMonitoringModel> messageMonitoringModels = messageMonitoringModelCache.values();


        messageMonitoringModels.forEach(item ->
        {
            if (!MessageMonitoringModel.REALTIME_STATE.equals(item.getEventType()))
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
                        .data(new RequestParams("data", functionPointStateModels))
                        .post(new Callback()
                        {
                            @Override
                            public void onFailure(Call call, IOException e)
                            {
                                System.out.println("api接口推送实时数据失败，失败原因：" + e.getMessage());
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
}
