package com.efounder.JEnterprise.model.basedatamanage.eqmanage;

import com.core.common.BaseEntity;

/**
 * @author sunzhiyuan
 * @Data 2020/9/29 9:41
 */
public class BesPlanInfo implements BaseEntity<String> {

    //主键Id
    private String f_id;

    //计划名称
    private String f_name;

    //计划类型
    private String f_type;

    //子节点
    private String f_pId;

    public String getF_id() {
        return f_id;
    }

    public void setF_id(String f_id) {
        this.f_id = f_id;
    }

    public String getF_name() {
        return f_name;
    }

    public void setF_name(String f_name) {
        this.f_name = f_name;
    }

    public String getF_type() {
        return f_type;
    }

    public void setF_type(String f_type) {
        this.f_type = f_type;
    }

    public String getF_pId() {
        return f_pId;
    }

    public void setF_pId(String f_pId) {
        this.f_pId = f_pId;
    }
}
