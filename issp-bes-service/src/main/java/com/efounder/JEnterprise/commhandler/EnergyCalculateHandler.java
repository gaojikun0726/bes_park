package com.efounder.JEnterprise.commhandler;

import com.core.ApplicationContextProvider;
import com.core.common.enums.StatisticalTypeEnum;
import com.efounder.JEnterprise.common.util.UUIDUtil;
import com.efounder.JEnterprise.mapper.dataAnalysises.BesEnergyDataMapper;
import com.efounder.JEnterprise.model.dataAnalysises.BesEnergyData;

/**
 *  计算总能耗(按小时、天、月、年)
 *  TODO 后期可以加入能耗费用的计算
 * @author xiepufeng
 * @date 2020/6/18 14:54
 */
public class EnergyCalculateHandler
{

    private static BesEnergyDataMapper besEnergyDataMapper  = ApplicationContextProvider.getBean(BesEnergyDataMapper.class);

    /*public static void energyCalculateHandle(Map<String, Object> dataMap)
    {

        if (null == dataMap || dataMap.isEmpty())
        {
            return;
        }

        String fNybh = (String) dataMap.get("fNybh");
        Double value = (Double) dataMap.get("data");
        String parkId = (String) dataMap.get("parkid");

        String timeHour = DataUtil.formatDate((String) dataMap.get("l_time"), StatisticalTypeEnum.HOUR);
        String timeDay = DataUtil.formatDate((String) dataMap.get("l_time"), StatisticalTypeEnum.DAY);
        String timeMonth = DataUtil.formatDate((String) dataMap.get("l_time"), StatisticalTypeEnum.MONTH);
        String timeYear = DataUtil.formatDate((String) dataMap.get("l_time"), StatisticalTypeEnum.YEAR);

        saveEnergyCalculate(fNybh, value, parkId, timeHour, StatisticalTypeEnum.HOUR);
        saveEnergyCalculate(fNybh, value, parkId, timeDay, StatisticalTypeEnum.DAY);
        saveEnergyCalculate(fNybh, value, parkId, timeMonth, StatisticalTypeEnum.MONTH);
        saveEnergyCalculate(fNybh, value, parkId, timeYear, StatisticalTypeEnum.YEAR);

    }*/


    /**
     *  按小时、天、月、年计算总能耗
     * @param fNybh 能源编号
     * @param data 能耗值
     * @param parkId 园区编号
     * @param time 时间
     * @param typeEnum 统计类型
     */
    public static void saveEnergyCalculate(String fNybh,
                                            Double data,
                                            String parkId,
                                            String time,
                                            StatisticalTypeEnum typeEnum)
    {
        if (null == fNybh
                || null == data
                || null == time
                || null == typeEnum
                || null == parkId)
        {
            return;
        }

        BesEnergyData besEnergyData = new BesEnergyData();
        besEnergyData.setfNybh(fNybh);
        besEnergyData.setfCjsj(time);
        besEnergyData.setfType(typeEnum.getCode().toString());
        besEnergyData.setfData(data);
        besEnergyData.setfYqbh(parkId);

        // String count = besEnergyDataMapper.queryEnergyExists(besEnergyData).getRows();

        try
        {
            if (!besEnergyDataMapper.updateEnergyData(besEnergyData))
            {

                //新建记录并写入
                besEnergyData.setfId(UUIDUtil.getRandom32BeginTimePK());
                besEnergyDataMapper.saveEnergyData(besEnergyData);

            }
        }catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
