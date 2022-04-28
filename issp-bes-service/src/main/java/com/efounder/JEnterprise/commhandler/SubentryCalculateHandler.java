package com.efounder.JEnterprise.commhandler;

import com.core.ApplicationContextProvider;
import com.core.common.enums.StatisticalTypeEnum;
import com.efounder.JEnterprise.common.util.UUIDUtil;
import com.efounder.JEnterprise.mapper.dataAnalysises.BESSubitemDataMapper;
import com.efounder.JEnterprise.model.basedatamanage.energyinformation.BESSubitem_Branch_Rlgl;
import com.efounder.JEnterprise.model.dataAnalysises.BESSubitemData;

import java.util.List;
import java.util.Map;

/**
 *  计算分项数据
 * @author xiepufeng
 * @date 2020/6/19 10:21
 */
public class SubentryCalculateHandler
{

    private static BESSubitemDataMapper besSubitemDataMapper = ApplicationContextProvider.getBean(BESSubitemDataMapper.class);

    /**
     * 保存分项数据
     */
    public static void saveCalculateData(
            List<BESSubitem_Branch_Rlgl> fFxbhList,
            String fCjsj,
            Double data,
            StatisticalTypeEnum typeEnum,
            String fYqbh)
    {

        if (null == fFxbhList
                || fFxbhList.isEmpty()
                || null == fCjsj
                || null == data
                || null == fYqbh
                || null == typeEnum)
        {
            return;
        }
        BESSubitemData besSubitemData = new BESSubitemData();

        besSubitemData.setfCjsj(fCjsj);
        besSubitemData.setfType(typeEnum.getCode().toString());
        besSubitemData.setfData(data);
        besSubitemData.setfYqbh(fYqbh);

        try {
            for (BESSubitem_Branch_Rlgl besSubitem_branch_rlgl : fFxbhList) {

                String fFxbh = besSubitem_branch_rlgl.getfFxbh();
                besSubitemData.setfFxbh(fFxbh);

                /**
                 * 能耗存库
                 * 方案一：首先查询该记录是否存在，不存则新增数据，存在则更新数据
                 * 方案二：首先更新数据，更新的数据不存在则新增（√）
                 */
                // String count = besSubitemDataMapper.queryGradeExists(besSubitemData).getRows();

                try
                {
                    if (!besSubitemDataMapper.updateGradeData(besSubitemData))
                    {
                        besSubitemData.setfId(UUIDUtil.getRandom32BeginTimePK());
                        besSubitemDataMapper.saveGradeData(besSubitemData);

                    }
                }catch (Exception e)
                {
                    e.printStackTrace();
                }
            }

        }catch (Exception e)
        {
            e.printStackTrace();
        }

    }
}
