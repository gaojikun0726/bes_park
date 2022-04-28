package com.efounder.JEnterprise.model.dataAnalysises;

import com.core.common.BaseEntity;
import lombok.Data;

/**
 * 分户数据表
 * 
 * @author liuzhen
 * 
 * @date 2018-11-08
 */
@Data
public class BesHouseholdPlanData implements BaseEntity<String>{


	/**
     * 序号
     */
    private String fGuid;

    /**
     * 分户编号 FK(BES_HOUSEHOLD _CONF.F_FHBH)
     */
    private String fFhbh;

    /**
     * 节能名称
     */
    private String fEfficientName;

    /**
     * 月份
     */
    private String fPmonth;
    
    /**
	 * 总能耗(Kwh)
	 */
    private String fAllEnegry;
    /**
     * 总金额(元)
     */
    private String fAllMoney;

	/**
     * 人均能耗(Kwh)
     */
    private String fPermanData;

    /**
     * 人均金额(元)
     */
    private String fPermanMoney;

    /**
     * 二氧化碳(ppm)
     */
    private String fCo2;

    /**
     * 单位面积能耗(Kwh)
     */
    private String fUnitareaData;

    /**
     * 单位面积金额(元)
     */
    private String fUnitareaMoney;

    /**
     * 耗煤量(吨)
     */
    private String fCoalAmount;

    /**
     * 使能
     */
    private String fEnable;

    /**
     * 分户名称
     */
    private String fFhmc;
    
    /**
     * 年份
     */
    private String fPyear;
    /**
     * 分户人数
     */
    private String fPersonNums;
    
    /**
     * 分户人数
     */
    private String fArea;
    
    /**
     * 报警方式
     */
    private String fAlertType;
    
    /**
     * 能源名称
     */
    private String fNymc;
    
    /**
     * 单价
     */
    private String fPrice;
    
    /**
     * 能源耗煤量
     */
    private String fECoalAmount;
    /**
     * 能源co2
     */
    private String fECo2;
    
    /**
     * 能源编号
     */
    private String fNybh;
    
	private String rows;

	
    
}