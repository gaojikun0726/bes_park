package com.efounder.JEnterprise.model.strongAndWeakElectricityIntegration;

import lombok.Data;

@Data
public class AmmeterVo {
    /**
     * 名称
     */
    private String f_name;
    /**
     * 当前瞬时能耗(kW)
     */
    private String f_dqssnh;
    /**
     * 当前硬件总电能(kW·h)
     */
    private String f_dqyjzdn;
    /**
     * 本月累计电能(kW·h)
     */
    private String f_byljdn;
    /**
     * 本月抄表底数(kW·h)
     */
    private String f_bycbds;
    /**
     * 当前实际总电能(kW·h)
     */
    private String f_dqsjzdn;
    /**
     * 校对底数(kW·h)
     */
    private String f_jdds;
    /**
     * 最近一次校对时间
     */
    private String f_zjycjdsj;
}
