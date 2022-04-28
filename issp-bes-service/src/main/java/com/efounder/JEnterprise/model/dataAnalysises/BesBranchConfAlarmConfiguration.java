package com.efounder.JEnterprise.model.dataAnalysises;

import com.core.common.BaseEntity;

/**
 * @Author: wanghongjie
 * @Description:
 * @Date: Created in 16:29 2021/6/3
 * @Modified By:支路报警配置
 */
public class BesBranchConfAlarmConfiguration implements BaseEntity<String> {

    private String fAlertid;//ID

    private String fAmmsysName;//电表名称

    private String fNymc;//能源名称

    private String fZlbh;//支路编号

    private String fAlertname;//支路报警名称

    private String fOperator;//公式

    private String fAlertgrade;//报警等级

    private String fRangetype;//范围类型0最高阈值；1最小阈值；2阈值范围；3准确值

    private String fNomore;//最大值

    private String fNoless;//最小值

    private String fEqual;//准确值

    private String fActive;//使能状态

    private String fType;//报警类型

    private String fYqbh;//园区编号

    public String getfAlertid() {
        return fAlertid;
    }

    public void setfAlertid(String fAlertid) {
        this.fAlertid = fAlertid;
    }

    public String getfAmmsysName() {
        return fAmmsysName;
    }

    public void setfAmmsysName(String fAmmsysName) {
        this.fAmmsysName = fAmmsysName;
    }

    public String getfNymc() {
        return fNymc;
    }

    public void setfNymc(String fNymc) {
        this.fNymc = fNymc;
    }

    public String getfZlbh() {
        return fZlbh;
    }

    public void setfZlbh(String fZlbh) {
        this.fZlbh = fZlbh;
    }

    public String getfAlertname() {
        return fAlertname;
    }

    public void setfAlertname(String fAlertname) {
        this.fAlertname = fAlertname;
    }

    public String getfOperator() {
        return fOperator;
    }

    public void setfOperator(String fOperator) {
        this.fOperator = fOperator;
    }

    public String getfAlertgrade() {
        return fAlertgrade;
    }

    public void setfAlertgrade(String fAlertgrade) {
        this.fAlertgrade = fAlertgrade;
    }

    public String getfRangetype() {
        return fRangetype;
    }

    public void setfRangetype(String fRangetype) {
        this.fRangetype = fRangetype;
    }

    public String getfNomore() {
        return fNomore;
    }

    public void setfNomore(String fNomore) {
        this.fNomore = fNomore;
    }

    public String getfNoless() {
        return fNoless;
    }

    public void setfNoless(String fNoless) {
        this.fNoless = fNoless;
    }

    public String getfEqual() {
        return fEqual;
    }

    public void setfEqual(String fEqual) {
        this.fEqual = fEqual;
    }

    public String getfActive() {
        return fActive;
    }

    public void setfActive(String fActive) {
        this.fActive = fActive;
    }

    public String getfType() {
        return fType;
    }

    public void setfType(String fType) {
        this.fType = fType;
    }

    public String getfYqbh() {
        return fYqbh;
    }

    public void setfYqbh(String fYqbh) {
        this.fYqbh = fYqbh;
    }
}
