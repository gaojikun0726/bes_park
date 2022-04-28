package com.efounder.JEnterprise.model.dataAnalysises;

import com.core.common.BaseEntity;
import lombok.Data;

import java.util.List;

/**
 * 能源绩效考核实体类
 * 
 * @author liuzhen
 * 
 * @date 2018-11-13
 */
@Data
public class BesEnergyPerformance implements BaseEntity<String>{
    
	
	 /**
     * 分户名称
     */
    private String fFhmc;
	/**
     * 计划序号
     */
    private String fPlanGuid;

    /**
     * 分户编号 FK(BES_HOUSEHOLD _CONF.F_FHBH)
     */
    private String fPlanFhbh;
    
   
    /**
	 * 计划总能耗(Kwh)
	 */
    private String fPlanAllEnegry;
    /**
     * 计划总金额(元)
     */
    private String fPlanAllMoney;

	/**
     * 计划人均能耗(Kwh)
     */
    private String fPlanPermanData;

    /**
     * 计划人均金额(元)
     */
    private String fPlanPermanMoney;

    /**
     * 计划二氧化碳(ppm)
     */
    private String fPlanCo2;

    /**
     * 计划单位面积能耗(Kwh)
     */
    private String fPlanUnitareaData;

    /**
     * 计划单位面积金额(元)
     */
    private String fPlanUnitareaMoney;

    /**
     * 计划耗煤量(吨)
     */
    private String fPlanCoalAmount;
    
    
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
     * 计划年份
     */
    private String fPlanYear;

    /**
     * 计划月份
     */
    private String fPlanMonth;
    
    /**
     * 查询多个分户编号使用
     */
    private List<String> fFhbhs;
    
    /**
   	 * 开始采集时间
   	 */
   private String fCjsj_start;
   /**
    * 结束采集时间
    */
   private String fCjsj_end;

    /**
     * 开始采集时间年月
     */
    private int fCjsj_start_year;
    private int fCjsj_start_month;
    /**
     * 结束采集时间年月
     */
    private int fCjsj_end_year;
    private int fCjsj_end_month;
   /**
    * 节能百分比
    */
   private String energyPercentage;
    
    
}