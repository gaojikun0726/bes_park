package com.efounder.JEnterprise.commhandler;

import com.core.ApplicationContextProvider;
import com.core.common.enums.StatisticalTypeEnum;
import com.core.common.util.DataUtil;
import com.efounder.JEnterprise.common.util.UUIDUtil;
import com.efounder.JEnterprise.initializer.BranchAmmeteRrlglCache;
import com.efounder.JEnterprise.initializer.BranchConfigCache;
import com.efounder.JEnterprise.initializer.HouseholdBranchRlglCache;
import com.efounder.JEnterprise.initializer.SubitemBranchRlglCache;
import com.efounder.JEnterprise.mapper.collectorJob.BESCalculateDataMapper;
import com.efounder.JEnterprise.mapper.dataAnalysises.BesBranchDataMapper;
import com.efounder.JEnterprise.model.basedatamanage.energyinformation.BESBranchConf;
import com.efounder.JEnterprise.model.basedatamanage.energyinformation.BESBranch_Ammeter_Rlgl;
import com.efounder.JEnterprise.model.basedatamanage.energyinformation.BESSubitem_Branch_Rlgl;
import com.efounder.JEnterprise.model.basedatamanage.energyinformation.BesHouseholdBranchRlgl;
import com.efounder.JEnterprise.model.dataAnalysises.BesBranchData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Map;

/**
 * 支路计量处理
 * @author xiepufeng
 * @date 2020/6/12 7:50
 */
public class BranchCalculateHandler
{

    private static final BesBranchDataMapper besBranchDataMapper = ApplicationContextProvider.getBean(BesBranchDataMapper.class);

    private static final BranchConfigCache branchConfigCache = ApplicationContextProvider.getBean(BranchConfigCache.class);

    private static final BranchAmmeteRrlglCache branchAmmeteRrlglCache = ApplicationContextProvider.getBean(BranchAmmeteRrlglCache.class);

    private static final HouseholdBranchRlglCache householdBranchRlglCache = ApplicationContextProvider.getBean(HouseholdBranchRlglCache.class);

    private static final SubitemBranchRlglCache subitemBranchRlglCache = ApplicationContextProvider.getBean(SubitemBranchRlglCache.class);


    public static void branchCalculateHandle(Map<String, Object> dataMap)
    {
        if (null == dataMap || dataMap.isEmpty())
        {
            return;
        }


        String sysName = (String) dataMap.get("meteruuid");

        Double value = (Double) dataMap.get("data");
        String f_dnbh = (String) dataMap.get("fNybh");
        String parkId = (String) dataMap.get("parkid");

        String timeHour = DataUtil.formatDate((String) dataMap.get("l_time"), StatisticalTypeEnum.HOUR);
        String timeDay = DataUtil.formatDate((String) dataMap.get("l_time"), StatisticalTypeEnum.DAY);
        String timeMonth = DataUtil.formatDate((String) dataMap.get("l_time"), StatisticalTypeEnum.MONTH);
        String timeYear = DataUtil.formatDate((String) dataMap.get("l_time"), StatisticalTypeEnum.YEAR);


        List<BESBranch_Ammeter_Rlgl> besBranchAmmeterRlgls = branchAmmeteRrlglCache.getCachedElementByfDbsysName(sysName);

        if (besBranchAmmeterRlgls == null || besBranchAmmeterRlgls.isEmpty())
        {
            return;
        }

        try {

            for (BESBranch_Ammeter_Rlgl besBranchAmmeterRlgl : besBranchAmmeterRlgls) {

                String f_zlbh = besBranchAmmeterRlgl.getfZlbh();
                String f_operator = besBranchAmmeterRlgl.getfOperator();

                if ("1".equals(f_operator))
                {
                    value = - value;
                }

                BESBranchConf besBranchConf = branchConfigCache.getCachedElement(f_zlbh);

                if (besBranchConf != null && !StringUtils.hasText(besBranchConf.getfPzlbh()))
                {
                    // 计算总能耗
                    EnergyCalculateHandler.saveEnergyCalculate(f_dnbh, value, parkId, timeHour, StatisticalTypeEnum.HOUR);
                    EnergyCalculateHandler.saveEnergyCalculate(f_dnbh, value, parkId, timeDay, StatisticalTypeEnum.DAY);
                    EnergyCalculateHandler.saveEnergyCalculate(f_dnbh, value, parkId, timeMonth, StatisticalTypeEnum.MONTH);
                    EnergyCalculateHandler.saveEnergyCalculate(f_dnbh, value, parkId, timeYear, StatisticalTypeEnum.YEAR);
                }

                saveCalculateData(f_zlbh, f_dnbh, timeHour, StatisticalTypeEnum.HOUR, value, parkId); // 按小时计算
                saveCalculateData(f_zlbh, f_dnbh, timeDay, StatisticalTypeEnum.DAY, value, parkId); // 按天计算
                saveCalculateData(f_zlbh, f_dnbh, timeMonth, StatisticalTypeEnum.MONTH, value, parkId); // 按月计算
                saveCalculateData(f_zlbh, f_dnbh, timeYear, StatisticalTypeEnum.YEAR, value, parkId); // 按年计算

            }

        }catch (Exception e)
        {
            e.printStackTrace();
        }



    }

    /**
     * 保存支路数据
     */
    private static void saveCalculateData(
            String f_zlbh,
            String f_dnbh,
            String time,
            StatisticalTypeEnum typeEnum,
            Double value,
            String f_yqbh)
    {

        if (null == f_zlbh
                || null == f_dnbh
                || null == time
                || null == typeEnum
                || null == f_yqbh
                || null == value)
        {
            return;
        }

        BesBranchData besBranchData = new BesBranchData();
        besBranchData.setfZlbh(f_zlbh);
        besBranchData.setfCjsj(time);
        besBranchData.setfType(typeEnum.getCode().toString());
        besBranchData.setfData(value);

        /**
         * 能耗存库
         * 方案一：首先查询该记录是否存在，不存则新增数据，存在则更新数据
         * 方案二：首先更新数据，更新的数据不存在则新增（√）
         */
        // String count = besBranchDataMapper.queryBranchExists(besBranchData).getRows();

        try
        {
            if (!besBranchDataMapper.updateBranchData(besBranchData))
            {
                besBranchData.setfId(UUIDUtil.getRandom32BeginTimePK());
                besBranchData.setfDnbh(f_dnbh);

                besBranchDataMapper.saveBranchData(besBranchData);

            }

        }catch (Exception e)
        {
            e.printStackTrace();
        }


        // 获取所有的分项编号
        List<BESSubitem_Branch_Rlgl> subentryNumbers = subitemBranchRlglCache.getCachedElementByfZlbh(f_zlbh);

        // 保存分项能耗数据
        SubentryCalculateHandler.saveCalculateData(subentryNumbers, time, value, typeEnum, f_yqbh);

        // 获取所有的分户编号
        List<BesHouseholdBranchRlgl> householdNumbers = householdBranchRlglCache.getCachedElementByfZlbh(f_zlbh);

        // 保存分户能耗数据
        HouseholdCalculateHandler.saveCalculateData(householdNumbers, time, value, typeEnum, f_yqbh);

        // 保存分户分项数据
        HouseholdSubentryCalculateHandler.saveCalculateData(householdNumbers, subentryNumbers,  time, value, typeEnum, f_yqbh);
    }
}
