package com.efounder.JEnterprise.platform.service.impl;

import com.core.common.constant.BesNodeType;
import com.efounder.JEnterprise.initializer.SbPzStructCache;
import com.efounder.JEnterprise.mapper.collectorJob.BESCalculateDataMapper;
import com.efounder.JEnterprise.model.basedatamanage.eqmanage.BESSbPzStruct;
import com.efounder.JEnterprise.platform.mapper.BESCalculateDataDailyMapper;
import com.efounder.JEnterprise.platform.service.CalculateDataDailyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 电表数据业务处理（表：bes_calculate_data_daily）
 */
@Service
public class CalculateDataDailyServiceImpl implements CalculateDataDailyService {

    @Autowired
    private BESCalculateDataMapper besCalculateDataMapper;

    @Autowired
    private BESCalculateDataDailyMapper besCalculateDataDailyMapper;

    @Autowired
    private SbPzStructCache sbPzStructCache;

    /**
     * 每天一点开始执行
     * 每天保存每个电表一条数据
     * 1.从电表数据表中获取昨天各个电表最新数据；
     * 2.批量保存到数据表中（bes_calculate_data_daily）。
     */
    @Scheduled(cron = "0 0 1 * * ?")
    public void saveCalculateDataDaily() {

        List<Map<String, Object>> ammeterData = getAmmeterData();

         /* 批量插入电表数据*/
        besCalculateDataDailyMapper.insertBulkAmmeterData(ammeterData);
    }

    /**
     * 获取昨天的每个电表最新采集时间数据
     * @return
     */
    public List<Map<String, Object>> getAmmeterData() {

        List<Map<String, Object>> resultData = new ArrayList<>();

        String startTime = getStartTime();
        String endTime = getEndTime();

        List<Map<String, Object>> ammeterData =  besCalculateDataMapper.queryAmmeterDataByTime(startTime, endTime);

        if (ammeterData == null || ammeterData.isEmpty()) {
            return resultData;
        }

        return pickLastTimeAmmeterData(ammeterData);
    }

    /**
     * 根据采集时间挑选出每个电表最大采集数据数据
     * @param ammeterDataList
     * @return
     */
    private List<Map<String, Object>> pickLastTimeAmmeterData(List<Map<String, Object>> ammeterDataList) {

        List<Map<String, Object>> resultData = new ArrayList<>();

        if (ammeterDataList == null || ammeterDataList.isEmpty()) {
            return resultData;
        }

        Map<String, Map<String, Object>> maxDateDateMap = new HashMap<>();

        ammeterDataList.forEach(ammeterDataMap -> {

            String sysName = (String) ammeterDataMap.get("dbsysName");

            BESSbPzStruct besSbPzStruct = sbPzStructCache.getCachedElement(sysName);

            if (besSbPzStruct == null || !BesNodeType.AMMETER.equals(besSbPzStruct.getF_type())) {
                return;
            }

            Map<String, Object> screenMap =  maxDateDateMap.get(sysName);

            Date cjsjNew = (Date) ammeterDataMap.get("cjsj");

            if (cjsjNew == null) return;

            if (screenMap == null || screenMap.isEmpty()) {
                maxDateDateMap.put(sysName, ammeterDataMap);
                return;
            }

            Date cjsj = (Date) screenMap.get("cjsj");

            if (cjsjNew.after(cjsj)) {
                maxDateDateMap.put(sysName, ammeterDataMap);
            }
        });

        resultData.addAll(maxDateDateMap.values());

        return resultData;
    }


    /**
     * 获取开始时间
     * 获取当前日期昨天23:00:00点日期时间字符串
     * @return
     */
    private static String getStartTime() {
        Calendar calendar= Calendar.getInstance();
        calendar.add(Calendar.DATE, -1);
        SimpleDateFormat dateFormat= new SimpleDateFormat("yyyy-MM-dd 23:00:00");
        return dateFormat.format(calendar.getTime());
    }

    /**
     * 获取结束时间
     * 获取当前日期今天00:00:00日期时间字符串
     * @return
     */
    private static String getEndTime() {
        Calendar calendar= Calendar.getInstance();
        SimpleDateFormat dateFormat= new SimpleDateFormat("yyyy-MM-dd 00:00:00");
        return dateFormat.format(calendar.getTime());
    }


}
