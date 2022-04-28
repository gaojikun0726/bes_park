package com.efounder.JEnterprise.model.basedatamanage.enegrycollectionmanage;

import com.core.common.BaseEntity;
import com.framework.common.utils.ExcelVOAttribute;

/**
 * 通信波特率表
 * 
 * @author suhang
 * 
 * @date 2018-07-27
 */
public class BesRate implements BaseEntity<String> {
    /**
	 * 
	 */
	private static final long serialVersionUID = 3012477460205737914L;

	/**
     * 通信波特率编号
     */
    @ExcelVOAttribute(name = "通信波特率编号", column = "A")
    private String fRateBh;

    /**
     * 通信波特率
     */
    @ExcelVOAttribute(name = "通信波特率", column = "B")
    private String fCommRate;

    /**
     * 创建日期
     */
    @ExcelVOAttribute(name = "创建日期", column = "C")
    private String fCrdate;

    /**
     * 修改日期
     */
    @ExcelVOAttribute(name = "修改日期", column = "D")
    private String fChdate;

    public String getfRateBh() {
        return fRateBh;
    }

    public void setfRateBh(String fRateBh) {
        this.fRateBh = fRateBh == null ? null : fRateBh.trim();
    }

    public String getfCommRate() {
        return fCommRate;
    }

    public void setfCommRate(String fCommRate) {
        this.fCommRate = fCommRate == null ? null : fCommRate.trim();
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