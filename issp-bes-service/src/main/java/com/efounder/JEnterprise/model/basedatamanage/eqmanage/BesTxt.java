package com.efounder.JEnterprise.model.basedatamanage.eqmanage;

import com.core.common.BaseEntity;

/**
 * @Author: wanghongjie
 * @Description:可编程的对象
 * @Date: Created in 16:16 2020/4/23
 * @Modified By:
 */
public class BesTxt implements BaseEntity<String> {
    private static final Long serialVersionUID = -1039810717659922908L;
    private String id;
    private String guid;
    private String txt;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getGuid() {
        return guid;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }

    public String getTxt() {
        return txt;
    }

    public void setTxt(String txt) {
        this.txt = txt;
    }
}
