package com.efounder.JEnterprise.model.dataAnalysises;

import com.core.common.BaseEntity;
import lombok.Data;

/**
 * 报警信息实体类
 * 
 * @author liuzhen
 * 
 * @date 2018-11-29
 */
@Data
public class BesWainingInfo implements BaseEntity<String>{

    /**
     * 主键id
     */
   private  String fGuid;

    /**
     * 报警位置
     */
   private  String  fLoction;

    /**
     * 报警名称
     */
    private  String  fAlarmName;

    /**
     * 实际值
     */
    private  String  fActualValue;

    /**
     * 计划值
     */
    private  String  fPlanValue;

    /**
     * 提示信息
     */
    private  String  fTipInfo;

    /**
     * 报警时间
     */
    private String fATime;
    /**
     * 是否处理
     */
    private String  fOpearation;

    /**
     * 报警类型
     */
    private  String  fType;

    /**
     * 报警等级
     */
    private String fLevel;


    /**
     * 园区编号
     */
    private  String  fYqbh;

    /**
     * 检索时开始时间
     */
    private String startTime;

    /**
     * 检索时结束时间
     */
    private String endTime;

    /**
     * 类型名称
     */
    private String typeName;

    /**
     * 等级名称
     */
    private  String levelName;

    /**
     * 行号
     */
    private String row;


  /**
   * 每个类型个数
   */
  private String  typeCount;

 /**
  * 同期开始时间
  */
 private String sameStartTime;

 /**
  * 同期结束时间
  */
 private String sameEndTime;
}