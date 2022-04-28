package com.efounder.JEnterprise.model.basedatamanage.eqmanage;

import com.core.common.BaseEntity;

import java.io.Serializable;

/**
 * bes_node_config_setting表
 * 节点配置设置表
 * @author yangjx
 * @date 20191026
 * @version 1.0
 */
public class BesNodeConfig implements BaseEntity<Serializable>{
    private static final long serialVersionUID = 2791868760896480844L;
    /**
     * 系统名称
     */
    private String f_sys_name;
    /**
     * 提示描述
     */
    private String f_desc;
    /**
     * 提示对应值
     */
    private int f_value;
    /**
     * 创建人
     */
    private String f_createuser;
    /**
     * 创建时间
     */
    private String f_createdate;

    public String getF_sys_name() {
        return f_sys_name;
    }
    public void setF_sys_name(String f_sys_name) {
        this.f_sys_name = f_sys_name;
    }
    public String getF_desc() {
        return f_desc;
    }
    public void setF_desc(String f_desc) {
        this.f_desc = f_desc;
    }
    public int getF_value() {
        return f_value;
    }
    public void setF_value(int f_value) {
        this.f_value = f_value;
    }
    public String getF_createuser() {
        return f_createuser;
    }
    public void setF_createuser(String f_createuser) {
        this.f_createuser = f_createuser;
    }
    public String getF_createdate() {
        return f_createdate;
    }
    public void setF_createdate(String f_createdate) {
        this.f_createdate = f_createdate;
    }
    @Override
    public String toString() {
        return "BesNodeConfig [f_sys_name=" + f_sys_name + ", f_desc=" + f_desc + ", f_value=" + f_value
                + ", f_createuser=" + f_createuser + ", f_createdate=" + f_createdate + "]";
    }


}