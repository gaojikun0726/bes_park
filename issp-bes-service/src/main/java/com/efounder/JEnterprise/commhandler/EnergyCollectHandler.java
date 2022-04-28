package com.efounder.JEnterprise.commhandler;

import com.core.ApplicationContextProvider;
import com.core.common.util.DataUtil;
import com.efounder.JEnterprise.common.util.UUIDUtil;
import com.efounder.JEnterprise.initializer.*;
import com.efounder.JEnterprise.mapper.collectorJob.BESJobManagerMapper;
import com.efounder.JEnterprise.model.basedatamanage.enegrycollectionmanage.BESElectricParams;
import com.efounder.JEnterprise.model.basedatamanage.enegrycollectionmanage.BESElectric_Coll_Rlgl;
import com.efounder.JEnterprise.model.basedatamanage.eqmanage.BESAmmeter;
import com.efounder.JEnterprise.model.basedatamanage.eqmanage.BesCollector;
import com.efounder.JEnterprise.model.basedatamanage.eqmanage.OriginalData;
import com.efounder.JEnterprise.model.systemcenter.Interfaceconfig.MessageMonitoringModel;
import com.efounder.JEnterprise.platform.service.BigDataInterfaceService;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;
import org.ace.business.dto.ReceiveMsg;
import org.ace.business.dto.edc.AmmeterData;
import org.ace.httpclient.OkHttpClient;
import org.ace.httpclient.exception.HttpException;
import org.ace.httpclient.request.RequestParams;
import org.apache.shiro.cache.Cache;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 能耗采集处理类
 *
 * @author xiepufeng
 * @date 2020/6/12 7:56
 */
public class EnergyCollectHandler
{
    /**
     * 能源类型：电
     */
    public static final String ENERGY_TYPE_ELECTRIC = "01000";

    private static final Logger log = LoggerFactory.getLogger(EnergyCollectHandler.class);

    public static Map<String, Map<String, Map<String, Object>>> monitoringDataTemp = new HashMap<>();

    private static BESJobManagerMapper besJobManagerMapper = ApplicationContextProvider.getBean(BESJobManagerMapper.class);

    // 消息监听定义缓存
    private static MessageMonitoringCache messageMonitoringCache = ApplicationContextProvider.getBean(MessageMonitoringCache.class);

    // 采集器缓存定义
    private static CollectorCache collectorCache = ApplicationContextProvider.getBean(CollectorCache.class);

    // 电表缓存定义
    private static AmmeterCache ammeterCache = ApplicationContextProvider.getBean(AmmeterCache.class);

    // 采集方案采集参数关联定义缓存
    private static ElectricCollRlglCache electricCollRlglCache = ApplicationContextProvider.getBean(ElectricCollRlglCache.class);

    // 采集参数定义缓存
    private static ElectricParamsCache electricParamsCache = ApplicationContextProvider.getBean(ElectricParamsCache.class);

    // 电表原始数据缓存缓存
    private static OriginalDataCache originalDataCache = ApplicationContextProvider.getBean(OriginalDataCache.class);

    /**
     * 大数据系统接口
     */
    private static BigDataInterfaceService bigDataInterfaceService = ApplicationContextProvider.getBean(BigDataInterfaceService.class);


    // todo 明天继续
    public static void ammeterDataHandle(ReceiveMsg<List<AmmeterData>> msg)
    {

        List<AmmeterData> ammeterData = msg.getData();

        if (null == ammeterData || ammeterData.isEmpty())
        {
            log.warn("下位机数据异常（电表数据不存在）");
            return;
        }

        String ip = msg.getIp();

        if (!StringUtils.hasText(ip))
        {
            log.warn("下位机数据异常（ip 地址不存在）");
            return;
        }

        //  能耗采集器参数
        BesCollector collector;

        try
        {
            // 根据channelId地址查询表 bes_collector 能耗采集器是否存在
            collector = collectorCache.getCollectorByChannelId(ip);
        }catch (Exception e)
        {
            log.warn("根据  " + ip+ "查询能耗采集器参数，数据库发生异常");
           e.printStackTrace();
           return;
        }

        // 判断能耗采集器是否存在
        if (null == collector)
        {
            log.warn("当前 " + ip+ "没有配置能耗采集器");
            return;
        }

        // 园区编号（从能耗采集器参数中获取园区编号）
        String parkId = collector.getfParkYqbh();

        // 解析后的能耗数据（只包括纳入能耗统计的数据）
        List<Map<String, Object>> dataList = new ArrayList<>();

        //解析后的能耗数据（向大数据推送的用电量数据）
        List<Map<String, Object>> bigList = new ArrayList<>();

        // 解析后的能耗数据（全部数据）
        List<Map<String, Object>> allDataList = new ArrayList<>();

        // 遍历下位机上传的电表数据
        for (AmmeterData ammeterDatum : ammeterData)
        {

            // 下位机电能数据
            String electricData = ammeterDatum.getElectricData();

            // 实际采集的电能参数个数
            Integer collectCount = ammeterDatum.getCollectCount();

            if (null == electricData
                    || electricData.isEmpty()
                    || null == collectCount
                    || collectCount <= 0)
            {
                log.warn("下位机数据异常（电能参数不存在）");
                continue;
            }

            // 分解下位机电能数据，保存到数组
            String[] electricDataArray = electricData.split(",");

            // 判断下位机的电能数据个数与实际电能数据个数是否一致（下位机参数 collectCount 定义个数与电能数据个数不一致）
            if (!collectCount.equals(electricDataArray.length))
            {
                log.warn("下位机数据异常（下位机参数 collectCount 定义个数与电能数据个数不一致）");
                continue;
            }

            // 下位机 meterID （对应上位机 sbId）
            Integer meterID = ammeterDatum.getMeterID();

            // 判断下位机 meterID 是否存在
            if (null == meterID)
            {
                log.warn("下位机数据异常（电表参数 meterID 不存在）");
                continue;
            }

            BESAmmeter ammeterParam = ammeterCache.getCachedElementBySbid(String.valueOf(meterID));

            if (ammeterParam == null)
            {
                log.warn("上位机与下位机电表一致（上位机没有对应的电能参数）");
                continue;
            }

            List<BESElectric_Coll_Rlgl> besElectricCollRlgls = electricCollRlglCache.getCachedElementByCjfabh(ammeterParam.getfCjfabh());

            if (null == besElectricCollRlgls || besElectricCollRlgls.isEmpty())
            {
                log.warn("上位机电能参数不存在（上位机没有对应的电能参数）");
                continue;
            }

            // 变比（从下位机电表参数中获取参数变比）
            Integer rate = Integer.parseInt(ammeterParam.getfPercentage());

            // 格式化下位机上传的时间
            String dateTime = DataUtil.formatDate(ammeterDatum.getDateYear(),
                    ammeterDatum.getDateMonth(),
                    ammeterDatum.getDateDay(),
                    ammeterDatum.getTimeHour(),
                    ammeterDatum.getTimeMinute(),
                    ammeterDatum.getTimeSecond()
            );

            // 电表系统名称（从下位机电表参数中获取电表系统名称）
            String ammeterName = ammeterParam.getfSysName();

            // 推送第三方电表参数
            String fSysNameOld = ammeterParam.getfSysNameOld();

            // 1、遍历电能数据 2、解析原始能耗数据
            for (int i = 0; i < electricDataArray.length; i++)
            {

                BESElectric_Coll_Rlgl besElectricCollRlgl = besElectricCollRlgls.get(i);

                if (besElectricCollRlgl == null)
                {
                    log.warn("上位机电能参数不存在（上位机没有对应的电能参数）");
                    break;
                }

                BESElectricParams besElectricParams = electricParamsCache.getCachedElement(besElectricCollRlgl.getfNhbh());

                if (besElectricParams == null)
                {
                    log.warn("上位机电能参数不存在（上位机没有对应的电能参数）");
                    break;
                }

                // 小数位数（从下位机电能参数中获取小数位置参数）
                Integer decimals = Integer.parseInt(besElectricParams.getfScalingPosition());
                // 编码规则（从下位机电能参数中获取编码规则参数）
                Integer enctype = Integer.parseInt(besElectricParams.getfBmgz());
                // 是否纳入能耗统计的标志
                Integer statistical = Integer.parseInt(besElectricCollRlgl.getfStatisticalParam());

                // 电能编号（电能参数）
                String electricId = besElectricParams.getfDnbh();
                // 能源编号（能源类型）
                String fNybh = besElectricParams.getfNybh();

                // 是否变比
                String isRate = besElectricCollRlgl.getfIsRate();

                    // 解析原始数据
                Double data = DataUtil.parseRawData(electricDataArray[i], rate, decimals, enctype, isRate);

                Map<String, Object> dataMap = new HashMap<>();
                String id = UUIDUtil.getRandom32BeginTimePK();

                //向大数据推送用电量数据
                Map<String,Object> bigMap = new HashMap<>();
                if(ENERGY_TYPE_ELECTRIC.equals(fNybh)){
                    bigMap.put("id",id);
                    bigMap.put("dbsys_name",ammeterName);
                    bigMap.put("electric_data",data);
                    bigMap.put("collect_time",dateTime);
                    bigMap.put("park_no",parkId);
                }
                // id
                dataMap.put("id", id);
                // 电表系统名称
                dataMap.put("meteruuid", ammeterName);

                // 推送第三方电表参数
                dataMap.put("fSysNameOld", fSysNameOld);
                // 电能数据
                dataMap.put("data", data);
                // 采集时间
                dataMap.put("l_time", dateTime);
                // 电能编号（电能参数）
                dataMap.put("enectric_id", electricId);
                // 园区编号
                dataMap.put("parkid", parkId);
                // 能源编号（能源类型）
                dataMap.put("fNybh", fNybh);

                if (statistical.equals(1) && !Objects.equals(data, 0.0))
                {
                    if(!dataMap.isEmpty()){
                        bigList.add(bigMap);
                    }
                    dataList.add(dataMap);
                }

                allDataList.add(dataMap);

                // 加入电表原始数据缓存缓存
                OriginalData originalData = new OriginalData();

                originalData.setfData(data);
                originalData.setfCjsj(dateTime);
                originalData.setfDnbh(electricId);
                originalData.setfDbsysName(ammeterName);
                originalData.setfType(fNybh);

                originalDataCache.putOneOriginalDataCache(originalData);
            }

        }

        if (!dataList.isEmpty())
        {
            // 插入电表数据表
            besJobManagerMapper.insertCalculateData(dataList);
            // 保存电表差值数据到能耗监控差值数据表数据表
            saveMonitoringData(dataList, ip);

            // 推送第三方电表数据
            pushAmmeterData(dataList);
        }
        if(!bigList.isEmpty()){
            log.info("调用推送数据方法：bigDataInterfaceService.pushElectricData(bigList)");
            //推送到大数据系统
            bigDataInterfaceService.pushElectricData(bigList);
        }

        if (!allDataList.isEmpty())
        {
            // 插入电表原始数据表 TODO 电能参数类型没有存储（电能参数表没有字段区分是什么类型）
            besJobManagerMapper.insertEnectricData(allDataList);
        }

    }

    public static void ammeterDataHandle(List<Map> dataList, String ip)
    {

        if (null == dataList || dataList.isEmpty() || !StringUtils.hasText(ip))
        {
            log.warn("ip:" + ip + "接收ddc点能耗数据，没有能耗数据");
            return;
        }

        List<Map<String, Object>> data = new ArrayList<>();

        // 当前时间
        String nowTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());

        for (Map dataMap : dataList)
        {
            String f_sys_name = (String) dataMap.get("F_SYS_NAME");
            //推送第三方电表参数
            String f_sys_name_old = (String) dataMap.get("F_SYS_NAME_OLD");
            String f_energy_type = (String) dataMap.get("F_ENERGY_TYPE");
            Double f_date = (Double) dataMap.get("F_DATE");

            if (null ==f_sys_name || null == f_energy_type || null == f_date)
            {
                log.warn("接收ddc点能耗数据，参数错误，系统名称或者能源编号或者能耗数据不存在");
                continue;
            }

            BESAmmeter ammeterInfo = ammeterCache.getCachedElement(f_sys_name);

            if (null == ammeterInfo)
            {
                log.warn("接收ddc点能耗数据，根据系统名称没有查询到相应的电表信息，请查看虚点和实点电表配置是否有问题");
                continue;
            }

            // 园区编号
            String f_yqbh = ammeterInfo.getfYqbh();

            Map<String, Object> datum = new HashMap<>();

            datum.put("id", UUIDUtil.getRandom32BeginTimePK());
            datum.put("meteruuid", f_sys_name);
            // 推送第三方电表参数
            datum.put("fSysNameOld", f_sys_name_old);
            datum.put("data", f_date);
            datum.put("l_time", nowTime);
            datum.put("parkid", f_yqbh);
            datum.put("fNybh", f_energy_type);

            data.add(datum);

        }

        try
        {
            besJobManagerMapper.insertCalculateData(data);
        }catch (Exception e)
        {
            log.warn("接收ddc点能耗数据，保存电表数据表的数据发生异常");
            e.printStackTrace();
            return;
        }

        saveMonitoringData(data, ip);

        // 推送第三方电表数据
        pushAmmeterData(data);
    }

    /**
     * 保存电表差值数据到能耗监控差值数据表数据表
     *
     * @param newData
     * @param ip
     */
    public static void saveMonitoringData(List<Map<String, Object>> newData, String ip)
    {
        if (null == newData || newData.isEmpty() || !StringUtils.hasText(ip))
        {
            return;
        }

        // 获取上一次缓存的能耗数据
        Map<String, Map<String, Object>> beforeData = monitoringDataTemp.get(ip);

        /*if (null == beforeData || beforeData.isEmpty())
        {
            Set<String> sysNames = new HashSet<>();
            newData.forEach(datum -> {
                sysNames.add((String) datum.get("meteruuid"));
            });


            List<Map<String, Object>> oldData = besJobManagerMapper.queryEnergyByAmmeterNames(sysNames);

            // 如果缓存中不存在则从数据库中获取最后一次存储的数据。
            if (oldData != null && !oldData.isEmpty())
            {
                if (null == beforeData)
                {
                    beforeData = new HashMap<>();
                }

                for (Map<String, Object> oldDatum : oldData)
                {
                    String f_dbsys_name = (String) oldDatum.get("F_DBSYS_NAME");
                    Double f_data = (Double) oldDatum.get("F_DATA");

                    Map<String, Object> tempDatum = new HashMap<>();

                    tempDatum.put("data", f_data);

                    beforeData.put(f_dbsys_name, tempDatum);
                }
            }
        }*/


        // 如果缓存数据不存在则把新的数据放入缓存，然后退出
        if (null == beforeData || beforeData.isEmpty())
        {
            Map<String, Map<String, Object>> tempData = new HashMap<>();

            List<Map<String, Object>> newDataTemp = new ArrayList<>();

            newData.forEach((item) -> {

                String sysName = (String) item.get("meteruuid");

                if (!tempData.containsKey(sysName))
                {
                    tempData.put(sysName, item);
                }
                else
                {
                    newDataTemp.add(item);
                }


            });
            monitoringDataTemp.put(ip, tempData);

            if (newDataTemp.isEmpty())
            {
                return;
            }

            beforeData = tempData;

            newData = newDataTemp;
        }


        // 差值数据
        List<Map<String, Object>> data = new ArrayList<>();

        Map<String, Map<String, Object>> tempData = new HashMap<>();

        for (int i = 0; i < newData.size(); i++)
        {
            Map<String, Object> newDataMap = newData.get(i);

            String sysName = (String)newDataMap.get("meteruuid");

             tempData.put(sysName, newDataMap);

            Map<String, Object> beforeDataMap = beforeData.get(sysName);

            if (beforeDataMap == null || beforeDataMap.isEmpty())
            {
                beforeData.put(sysName, newDataMap);
                log.warn("差值运算，系统名称" + sysName + "缓存中不存在");
                continue;
            }

            Double dataNew = (Double) newDataMap.get("data");
            Double dataBefore = (Double) beforeDataMap.get("data");

            BigDecimal dataNew1 = new BigDecimal(String.valueOf(dataNew));
            BigDecimal dataBefore2 = new BigDecimal(String.valueOf(dataBefore));

            Double betweenValue = dataNew1.subtract(dataBefore2).doubleValue();

            if (betweenValue < 0)
            {
                log.warn("差值运算，数据错误，能耗值不能是负数");
                continue;
            }

            Map<String, Object> dataMap = new HashMap<>();

            dataMap.put("id", UUIDUtil.getRandom32BeginTimePK());
            dataMap.put("meteruuid", sysName);
            dataMap.put("data", betweenValue);
            dataMap.put("l_time", newDataMap.get("l_time"));
            dataMap.put("parkid", newDataMap.get("parkid"));
            dataMap.put("fNybh", newDataMap.get("fNybh"));

            data.add(dataMap);

            // 支路计算统计
            BranchCalculateHandler.branchCalculateHandle(dataMap);

            // 计算总能耗
            // EnergyCalculateHandler.energyCalculateHandle(dataMap);

            beforeData.put(sysName, newDataMap);

        }

        // 新的能耗数据放入缓存中
         monitoringDataTemp.put(ip, tempData);

        // 保存能耗监控差值数据表数据
        besJobManagerMapper.insertMonitoringData(data);

    }

    // 推送电表数据（第三方系统）
    public static void pushAmmeterData(List<Map<String, Object>> dataList)
    {
        if (dataList == null || dataList.isEmpty())
        {
            return;
        }

        List<Map<String, Object>> data = new ArrayList<>();

        dataList.forEach(item ->
        {
            Map<String, Object> dataMap = new HashMap<>();

            dataMap.put("code", item.get("fSysNameOld"));
            dataMap.put("value", item.get("data"));
            dataMap.put("time", item.get("l_time"));
            data.add(dataMap);
        });

        Cache<Integer, MessageMonitoringModel> messageMonitoringModelCache = messageMonitoringCache.getMessageMonitoringCache();

        if (messageMonitoringModelCache == null)
        {
            return;
        }

        Collection<MessageMonitoringModel> messageMonitoringModels = messageMonitoringModelCache.values();


        messageMonitoringModels.forEach(item ->
        {
            if (!MessageMonitoringModel.AMMETER_DATA.equals(item.getEventType()))
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
                        .data(new RequestParams("data", data))
                        .post(new Callback()
                        {
                            @Override
                            public void onFailure(Call call, IOException e)
                            {
                                System.out.println("api接口推送电表数据失败，失败原因：" + e.getMessage());
                            }

                            @Override
                            public void onResponse(Call call, Response response)
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
