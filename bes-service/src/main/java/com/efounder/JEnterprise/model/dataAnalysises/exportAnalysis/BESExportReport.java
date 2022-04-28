package com.efounder.JEnterprise.model.dataAnalysises.exportAnalysis;

import com.core.common.BaseEntity;
import lombok.Data;

/**
* @author  杨超--YangChao
* @version 创建时间：2018年12月12日 下午6:02:28
* @parameter 
* @version 1.0
*/
@Data
public class BESExportReport implements BaseEntity<String> {

    /**
     * 园区编号
     */
    private String fYqbh;

    /**
     * 开始时间
     */
    private String startTime;

    /**
     * 结束时间
     */
    private String endTime;

    /**
     * 时间类型
     */
    private String timeType;

    /**
     * 能耗类型
     */
    private String energyType;

    /**
     * 统计类型
     */
    private String statisticsType;
    /**
     * 报表类型
     */
    private String exportType;

    /**
     * 支路编号
     */
    private String fZlbh;

    /**
     * 支路名称
     */
    private String fZlmc;

    /**
     * 数据
     */
    private String fData;

    /**
     * 时间
     */
    private String fTime;

    /**
     * 父级支路名称
     */
    private String fPzlmc;

    /**
     * 动态查询条件
     */
    private String f_cjsj;


}
