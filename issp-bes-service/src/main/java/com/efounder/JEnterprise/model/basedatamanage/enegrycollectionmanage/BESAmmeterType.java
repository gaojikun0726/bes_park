package com.efounder.JEnterprise.model.basedatamanage.enegrycollectionmanage;

import com.core.common.BaseEntity;
import com.framework.common.utils.ExcelVOAttribute;

/**
 * 电表类型
 * @author LvSihan
 *
 */
public class BESAmmeterType implements BaseEntity<String>{

	private static final long serialVersionUID = -2480898723218422107L;
    @ExcelVOAttribute(name = "类型编号", column = "A")
	private String fLxbh;
    @ExcelVOAttribute(name = "类型名称", column = "B")
    private String fLxmc;
    @ExcelVOAttribute(name = "创建日期", column = "C")
    private String fCrdate;
    @ExcelVOAttribute(name = "修改日期", column = "D")
    private String fChdate;

    public String getfLxbh() {
        return fLxbh;
    }

    public void setfLxbh(String fLxbh) {
        this.fLxbh = fLxbh == null ? null : fLxbh.trim();
    }

    public String getfLxmc() {
        return fLxmc;
    }

    public void setfLxmc(String fLxmc) {
        this.fLxmc = fLxmc == null ? null : fLxmc.trim();
    }

	public String getfCrdate() {
		return fCrdate;
	}

	public void setfCrdate(String fCrdate) {
		this.fCrdate = fCrdate;
	}

	public String getfChdate() {
		return fChdate;
	}

	public void setfChdate(String fChdate) {
		this.fChdate = fChdate;
	}

}