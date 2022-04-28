package com.efounder.JEnterprise.model.basedatamanage.enegrycollectionmanage;

import com.core.common.BaseEntity;
import com.framework.common.utils.ExcelVOAttribute;

/**
 * 通信协议
 * @author LvSihan
 *
 */
public class BESProtocol implements BaseEntity<String>{

	private static final long serialVersionUID = -7497353221362947858L;

    @ExcelVOAttribute(name = "通讯协议编号", column = "A")
	private String fTxxybh;
    @ExcelVOAttribute(name = "通讯协议类型", column = "B")
    private String fType;
    @ExcelVOAttribute(name = "创建日期", column = "C")
    private String fCrdate;
    @ExcelVOAttribute(name = "修改日期", column = "D")
    private String fChdate;

    public String getfTxxybh() {
        return fTxxybh;
    }

    public void setfTxxybh(String fTxxybh) {
        this.fTxxybh = fTxxybh == null ? null : fTxxybh.trim();
    }

    public String getfType() {
        return fType;
    }

    public void setfType(String fType) {
        this.fType = fType == null ? null : fType.trim();
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