package com.efounder.JEnterprise.commhandler;

import com.core.ApplicationContextProvider;
import com.core.common.enums.StatisticalTypeEnum;
import com.efounder.JEnterprise.common.util.UUIDUtil;
import com.efounder.JEnterprise.mapper.dataAnalysises.BesHouseholdSubitemDataMapper;
import com.efounder.JEnterprise.model.basedatamanage.energyinformation.BESSubitem_Branch_Rlgl;
import com.efounder.JEnterprise.model.basedatamanage.energyinformation.BesHouseholdBranchRlgl;
import com.efounder.JEnterprise.model.dataAnalysises.BesHouseholdSubitemData;

import java.util.List;

/**
 * 计算分户分项数据
 * @author xiepufeng
 * @date 2020/6/23 15:36
 */
public class HouseholdSubentryCalculateHandler
{

    private static BesHouseholdSubitemDataMapper besHouseholdSubitemDataMapper = ApplicationContextProvider.getBean(BesHouseholdSubitemDataMapper.class);

    /**
     * 保存分户分项数据
     */
    public static void saveCalculateData(
            List<BesHouseholdBranchRlgl> fFhbhList,
            List<BESSubitem_Branch_Rlgl> fFxbhList,
            String fCjsj,
            Double data,
            StatisticalTypeEnum typeEnum,
            String fYqbh)
    {

        if (null == fFhbhList
                || fFhbhList.isEmpty()
                || null == fFxbhList
                || fFxbhList.isEmpty()
                || null == fCjsj
                || null == data
                || null == fYqbh
                || null == typeEnum)
        {
            return;
        }

        BesHouseholdSubitemData besHouseholdSubitemData = new BesHouseholdSubitemData();

        besHouseholdSubitemData.setfCjsj(fCjsj); // 采集时间
        besHouseholdSubitemData.setfType(typeEnum.getCode().toString()); // 统计类型
        besHouseholdSubitemData.setfData(data); // 支路能耗数据


        try {

            for (BesHouseholdBranchRlgl besHouseholdBranchRlgl : fFhbhList) {

                String fFhbh = besHouseholdBranchRlgl.getfFhbh(); // 分户编号

                for (BESSubitem_Branch_Rlgl besSubitem_branch_rlgl : fFxbhList) {

                    String fFxbh = besSubitem_branch_rlgl.getfFxbh(); // 分项编号

                    besHouseholdSubitemData.setfFhbh(fFhbh); // 设置分户编号

                    besHouseholdSubitemData.setfFxbh(fFxbh); // 设置分项编号

                    /**
                     * 能耗存库
                     * 方案一：首先查询该记录是否存在，不存则新增数据，存在则更新数据
                     * 方案二：首先更新数据，更新的数据不存在则新增（√）
                     */

                    // List<Map<String, String>> queryList = besHouseholdSubitemDataMapper.queryHouseholdGradeExists(fFhbh, fCjsj, typeEnum.getCode().toString(), fFxbh);

                    try
                    {
                        if (!besHouseholdSubitemDataMapper.updateHouseholdGradeData(besHouseholdSubitemData))
                        {
                            besHouseholdSubitemData.setfId(UUIDUtil.getRandom32BeginTimePK());
                            besHouseholdSubitemDataMapper.saveHouseholdGradeData(besHouseholdSubitemData);
                        }
                    }catch (Exception e)
                    {
                        e.printStackTrace();
                    }
                }
            }

        }catch (Exception e)
        {
            e.printStackTrace();
        }


    }
}
