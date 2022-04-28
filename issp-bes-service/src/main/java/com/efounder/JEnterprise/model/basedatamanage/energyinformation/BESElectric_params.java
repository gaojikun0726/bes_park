package com.efounder.JEnterprise.model.basedatamanage.energyinformation;

import com.core.common.BaseEntity;

/**
 * @Author: wanghongjie
 * @Description:
 * @Date: Created in 15:12 2020/5/29
 * @Modified By:
 */
public class BESElectric_params implements BaseEntity<String> {
    private static final long serialVersionUID = -5038006023734657412L;

    private String fDnbh;

    private String fDnmc;

    public String getfDnbh() {
        return fDnbh;
    }

    public void setfDnbh(String fDnbh) {
        this.fDnbh = fDnbh;
    }

    public String getfDnmc() {
        return fDnmc;
    }

    public void setfDnmc(String fDnmc) {
        this.fDnmc = fDnmc;
    }
}
