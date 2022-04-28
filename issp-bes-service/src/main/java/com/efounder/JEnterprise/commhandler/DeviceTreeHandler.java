package com.efounder.JEnterprise.commhandler;

import com.core.ApplicationContextProvider;
import com.efounder.JEnterprise.mapper.basedatamanage.eqmanage.BESSbdyMapper;
import com.efounder.JEnterprise.model.basedatamanage.eqmanage.BESSbPzStruct;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * @author xiepufeng
 * @date 2020/7/1 10:10
 */
public class DeviceTreeHandler
{

    private static BESSbdyMapper besSbdyMapper = ApplicationContextProvider.getBean(BESSbdyMapper.class);


    // 更新设备树节点状态
    public static List<BESSbPzStruct> updateDeviceTreeNodeState(String sysName, String state)
    {
        if (!StringUtils.hasText(sysName) || !StringUtils.hasText(state))
        {
            return null;
        }

        List<BESSbPzStruct> besSbPzStructs = besSbdyMapper.searchChildren(sysName);

        if (null == besSbPzStructs || besSbPzStructs.isEmpty())
        {
            return null;
        }

        for (BESSbPzStruct besSbPzStruct : besSbPzStructs)
        {
            besSbPzStruct.setF_status(state);
        }

        besSbdyMapper.batchUpdateNodeStateBySysName(besSbPzStructs, state);

        return besSbPzStructs;
    }
}
