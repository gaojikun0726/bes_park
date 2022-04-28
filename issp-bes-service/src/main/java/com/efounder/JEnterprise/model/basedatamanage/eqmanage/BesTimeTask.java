package com.efounder.JEnterprise.model.basedatamanage.eqmanage;

import com.core.common.BaseEntity;

/**
 * @author sunzhiyuan
 * @Data 2020/12/29 18:12
 * 定时任务配置表
 */
public class BesTimeTask implements BaseEntity<String> {

    private String f_id; //id

    private String f_timename; //任务名称

    private String f_tasktype; //任务类型

    private String f_specificvalue; //具体值

    private String f_pId; //父Id

    private String f_cronstartexpre; //生成的开始时间的表达式

    private String f_jobId;//任务Id

    private String f_modeId; //模式ID

    public String getF_id() {
        return f_id;
    }

    public void setF_id(String f_id) {
        this.f_id = f_id;
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

    public String getF_pId() {
        return f_pId;
    }

    public void setF_pId(String f_pId) { this.f_pId = f_pId; }

    public String getF_cronstartexpre() {
        return f_cronstartexpre;
    }

    public void setF_cronstartexpre(String f_cronstartexpre) {
        this.f_cronstartexpre = f_cronstartexpre;
    }

    public String getF_modeId() { return f_modeId; }

    public void setF_modeId(String f_modeId) { this.f_modeId = f_modeId; }

    public String getF_jobId() {
        return f_jobId;
    }

    public void setF_jobId(String f_jobId) {
        this.f_jobId = f_jobId;
    }
}
