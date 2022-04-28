package com.efounder.JEnterprise.model.systemcenter.Interfaceconfig;

import com.core.common.BaseEntity;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * @author sunzhiyuan
 * @Data 2021/1/26 9:56
 */
public class PlanVariatInfoModel implements BaseEntity<Serializable> {

    private static final long serialVersionUID = 1L;

    /**
     * 定时计划Id
     */
    private String  f_taskId; //id

    /**
     * 任务名称
     */
    private String  f_timename;

    /**
     * 任务类型
     */
    private String  f_tasktype;

    /**
     * 具体值
     */
    private String  f_specificvalue;

    /**
     * 计划Id
     */
    private String  f_planId;

    /**
     * 模式Id
     */
    private String  f_modeId;

    /**
     *点位数据
     */
    private List<Map<String,Object>> pointList;

    public String getF_taskId() {
        return f_taskId;
    }

    public void setF_taskId(String f_taskId) {
        this.f_taskId = f_taskId;
    }

    public String getF_timename() {
        return f_timename;
    }

    public void setF_timename(String f_timename) {
        this.f_timename = f_timename;
    }

    public String getF_tasktype() {
        return f_tasktype;
    }

    public void setF_tasktype(String f_tasktype) {
        this.f_tasktype = f_tasktype;
    }

    public String getF_specificvalue() {
        return f_specificvalue;
    }

    public void setF_specificvalue(String f_specificvalue) {
        this.f_specificvalue = f_specificvalue;
    }

    public String getF_planId() {
        return f_planId;
    }

    public void setF_planId(String f_planId) {
        this.f_planId = f_planId;
    }

    public String getF_modeId() {
        return f_modeId;
    }

    public void setF_modeId(String f_modeId) {
        this.f_modeId = f_modeId;
    }

    public List<Map<String, Object>> getPointList() {
        return pointList;
    }

    public void setPointList(List<Map<String, Object>> pointList) {
        this.pointList = pointList;
    }
}
