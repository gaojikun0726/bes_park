package com.efounder.JEnterprise.model.basedatamanage.eqmanage;

import com.core.common.BaseEntity;

/**
 * @author sunzhiyuan
 * @Date:2020/9/2 16:32
 */
//场景信息表
public class BeSceneInfo implements BaseEntity<String> {

    //主键
    private String f_id;

    //名称
    private String f_name;

    //0 代表根节点 1主节点（控制场景） 2 主节点(采集场景)
    private String f_type;

    //场景名称
    private String f_scenename;

    //场景别名
    private String f_scenenickname;

    //场景描述
    private String f_discription;

    //父节点Id
    private String f_pId;

    //是否使能
    private String f_active;

    //使用次数
    private String f_usenum;

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

    public String getF_scenename() {
        return f_scenename;
    }

    public void setF_scenename(String f_scenename) {
        this.f_scenename = f_scenename;
    }

    public String getF_scenenickname() {
        return f_scenenickname;
    }

    public void setF_scenenickname(String f_scenenickname) {
        this.f_scenenickname = f_scenenickname;
    }

    public String getF_discription() {
        return f_discription;
    }

    public void setF_discription(String f_discription) {
        this.f_discription = f_discription;
    }

    public String getF_pId() {
        return f_pId;
    }

    public void setF_pId(String f_pId) {
        this.f_pId = f_pId;
    }

    public String getF_active() {
        return f_active;
    }

    public void setF_active(String f_active) {
        this.f_active = f_active;
    }

    public String getF_usenum() {
        return f_usenum;
    }

    public void setF_usenum(String f_usenum) {
        this.f_usenum = f_usenum;
    }
}
