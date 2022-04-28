package com.efounder.datareported.service;

import com.efounder.JEnterprise.initializer.SubitemConfigCache;
import com.efounder.JEnterprise.mapper.basedatamanage.energydataReport.BESDatecenterMapper;
import com.efounder.JEnterprise.mapper.basedatamanage.energydataReport.BesBudingInformationMapper;
import com.efounder.JEnterprise.mapper.basedatamanage.energyinformation.BESSubitemConfMapper;
import com.efounder.JEnterprise.mapper.dataAnalysises.BESSubitemDataMapper;
import com.efounder.JEnterprise.model.basedatamanage.energydataReport.BESDatecenterType;
import com.efounder.JEnterprise.model.basedatamanage.energydataReport.BesBudingInformation;
import com.efounder.JEnterprise.model.basedatamanage.energyinformation.BESSubitemConf;
import com.efounder.JEnterprise.model.dataAnalysises.BESSubitemData;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author xiepufeng
 * @date 2020/11/11 11:22
 */
@Service
public class BasicDataHandler
{
    @Resource
    private BESDatecenterMapper besDatecenterMapper;

    @Resource
    private BesBudingInformationMapper besBudingInformationMapper;

    @Resource
    private SubitemConfigCache subitemConfigCache;

    @Resource
    private BESSubitemDataMapper besSubitemDataMapper;

    @Resource
    private BESSubitemConfMapper besSubitemConfMapper;



    /**
     * 获取数据中心基本信息
     *
     * @return
     */
    public BESDatecenterType getDataCentreInfo()
    {
        List<BESDatecenterType> besDateCenterTypes = besDatecenterMapper.getDateCenterList();

        if (besDateCenterTypes == null || besDateCenterTypes.isEmpty())
        {
            return null;
        }

        // 目前只支持一个数据中心
        for (BESDatecenterType besDateCenterType : besDateCenterTypes)
        {
            return besDateCenterType;
        }

        return null;
    }

    /**
     * 获取建筑信息根据数据中心id
     *
     * @return
     */
    public List<BesBudingInformation> getBuildingInfoList(BESDatecenterType besDatecenterType)
    {
        if (besDatecenterType == null || besDatecenterType.getF_DATACENTER_ID() == null)
        {
            return null;
        }

        return besBudingInformationMapper.queryBuildingByCenterIdList(besDatecenterType.getF_DATACENTER_ID());
    }

    /**
     * 获取分项能耗数据
     *
     * @param date
     * @return key (建筑节点编号) value （key （分项编号） value （能耗数据））
     */
    public Map<String, Map<String, Double>> getEnergyData(List<BesBudingInformation> besBuildingInfoList, Date date)
    {


        if (besBuildingInfoList == null || besBuildingInfoList.isEmpty() || date == null)
        {
            return null;
        }

        // key 建筑节点编号 value （key 分项编号， 能耗数据）
        Map<String, Map<String, Double>> budingSubitemMap = new LinkedHashMap<>();

        Map<String, BESSubitemData>  besSubitemDataMap = getSubitemEnergyData(date);

        if (besSubitemDataMap == null || besSubitemDataMap.isEmpty())
        {
            return null;
        }

        besBuildingInfoList.forEach(besBudingInformation -> {

            String budingId = besBudingInformation.getfBudingId();
            String budingCode = besBudingInformation.getfBudingCode();

            List<BESSubitemConf> besSubitemConfs = subitemConfigCache.getCachedElementByBudingId(budingId);

            if (besSubitemConfs == null || besSubitemConfs.isEmpty())
            {
                return;
            }

            besSubitemConfs.sort(Comparator.comparing(o -> String.valueOf(o.getfSubitemCode())));

            besSubitemConfs.forEach(besSubitemConf -> {

                Map<String, Double> dataMap = budingSubitemMap.computeIfAbsent(budingCode, k -> new HashMap<>());

                Double value = 0.0;

                BESSubitemData besSubitemData = besSubitemDataMap.get(besSubitemConf.getfFxbh());

                if (besSubitemData != null)
                {
                    value = besSubitemData.getfData();
                }

                dataMap.put(besSubitemConf.getfSubitemCode(), value);

            });

        });



        return budingSubitemMap;
    }


    /**
     * 获取上一个小时分项能耗数据
     * @param date
     * @return key 支路编号 value 支路能耗数据
     */
    public Map<String, BESSubitemData> getSubitemEnergyData(Date date)
    {
        if (date == null)
        {
            return null;
        }

        Calendar calendar = Calendar.getInstance();

        calendar.setTime(date);
        calendar.add(Calendar.HOUR_OF_DAY, -1);

        String time = new SimpleDateFormat("YYYY-MM-dd HH:00:00").format(calendar.getTime());

        List<BESSubitemData>  subitemData = besSubitemDataMapper.getSubitemDataByCjsjAndType(time, "0");

        if (subitemData == null || subitemData.isEmpty())
        {
            return null;
        }

        Map<String, BESSubitemData> data = new HashMap<>();

        subitemData.forEach(item -> {
            data.put(item.getfFxbh(), item);
        });

        return data;

    }


}
