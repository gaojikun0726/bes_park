package com.efounder.JEnterprise.model.usercenter;


import com.core.common.BaseEntity;

import java.io.Serializable;


/**
 * 用户对象
 *
 * @author Alvin
 * @datetim 2018年1月15日
 */
public class ESUser implements BaseEntity<Serializable> {

    private static final long serialVersionUID = 3624947930970250778L;


    private Long f_id;//用户Id

    private String f_yhbh;//编号

    private String f_name;//用户姓名

    private String f_pass;//用户密码

    /**
     * salt码
     **/
    private String salt;

    private String f_company;//公司

    private String f_zzjgbh;//所属组织

    private String f_phone;//手机号

    private String f_email;//邮箱

    private String f_crdate;// 创建日期

    private String f_chdate;// 修改日期


    private String f_rolebh;// 角色编号
    private String f_rolemc;// 角色名称

    private String f_headimg;//头像URL


    public String getF_headimg() {
        return f_headimg;
    }

    public void setF_headimg(String f_headimg) {
        this.f_headimg = f_headimg;
    }

    public static long getSerialversionuid() {
        return serialVersionUID;
    }

    public Long getF_id() {
        return f_id;
    }

    public void setF_id(Long f_id) {
        this.f_id = f_id;
    }

    public String getF_yhbh() {
        return f_yhbh;
    }

    public void setF_yhbh(String f_yhbh) {
        this.f_yhbh = f_yhbh;
    }

    public String getF_name() {
        return f_name;
    }

    public void setF_name(String f_name) {
        this.f_name = f_name;
    }

    public String getF_pass() {
        return f_pass;
    }

    public void setF_pass(String f_pass) {
        this.f_pass = f_pass;
    }

    public String getF_company() {
        return f_company;
    }

    public void setF_company(String f_company) {
        this.f_company = f_company;
    }

    public String getF_zzjgbh() {
        return f_zzjgbh;
    }

    public void setF_zzjgbh(String f_zzjgbh) {
        this.f_zzjgbh = f_zzjgbh;
    }

    public String getF_phone() {
        return f_phone;
    }

    public void setF_phone(String f_phone) {
        this.f_phone = f_phone;
    }

    public String getF_email() {
        return f_email;
    }

    public void setF_email(String f_email) {
        this.f_email = f_email;
    }

    public String getF_crdate() {
        return f_crdate;
    }

    public void setF_crdate(String f_crdate) {
        this.f_crdate = f_crdate;
    }

    public String getF_chdate() {
        return f_chdate;
    }

    public void setF_chdate(String f_chdate) {
        this.f_chdate = f_chdate;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public String getF_rolebh() {
        return f_rolebh;
    }

    public void setF_rolebh(String f_rolebh) {
        this.f_rolebh = f_rolebh;
    }

    public String getF_rolemc() {
        return f_rolemc;
    }

    public void setF_rolemc(String f_rolemc) {
        this.f_rolemc = f_rolemc;
    }


}
