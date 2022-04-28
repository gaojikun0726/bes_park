package com.efounder.JEnterprise.model.basedatamanage.enegrycollectionmanage;

import com.core.common.BaseEntity;
/**
 * 采集参数
 * @author LvSihan
 *
 */
public class BESElectricParams implements BaseEntity<String>{

	private static final long serialVersionUID = -3945497702037199059L;

	private String fDnbh;

    private String fDnmc;

    private String fNybh;

    private String fPydz;

    private String fBmgz;

//    private String fBmgzMc;

    private String fUnit;

    private String fScalingPosition;
    
    private String fDataLength;//数据长度

    private String fYqbh;

    private String fRemark;

    private String fCrdate;
    
	private String fChdate;

	private String fStatisticalParam;

	private String fIsRate; // 是否变比

	private String fDataType; // 数据类型

	private String fCodeSeq; // 编码规则


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

    public String getfDnbh() {
        return fDnbh;
    }

    public void setfDnbh(String fDnbh) {
        this.fDnbh = fDnbh == null ? null : fDnbh.trim();
    }

    public String getfDnmc() {
        return fDnmc;
    }

    public void setfDnmc(String fDnmc) {
        this.fDnmc = fDnmc == null ? null : fDnmc.trim();
    }

    public String getfNybh() {
        return fNybh;
    }

    public void setfNybh(String fNybh) {
        this.fNybh = fNybh == null ? null : fNybh.trim();
    }

    public String getfPydz() {
        return fPydz;
    }

    public void setfPydz(String fPydz) {
        this.fPydz = fPydz == null ? null : fPydz.trim();
    }

    public String getfBmgz() {
        return fBmgz;
    }

    public void setfBmgz(String fBmgz) {
        this.fBmgz = fBmgz == null ? null : fBmgz.trim();
    }

    public String getfUnit() {
        return fUnit;
    }

    public void setfUnit(String fUnit) {
        this.fUnit = fUnit == null ? null : fUnit.trim();
    }

    public String getfScalingPosition() {
        return fScalingPosition;
    }

    public void setfScalingPosition(String fScalingPosition) {
        this.fScalingPosition = fScalingPosition == null ? null : fScalingPosition.trim();
    }

    public String getfYqbh() {
        return fYqbh;
    }

    public void setfYqbh(String fYqbh) {
        this.fYqbh = fYqbh == null ? null : fYqbh.trim();
    }

    public String getfRemark() {
        return fRemark;
    }

    public void setfRemark(String fRemark) {
        this.fRemark = fRemark == null ? null : fRemark.trim();
    }

	public String getfStatisticalParam() {
		return fStatisticalParam;
	}

	public void setfStatisticalParam(String fStatisticalParam) {
		this.fStatisticalParam = fStatisticalParam;
	}

	public String getfDataLength() {
		return fDataLength;
	}

	public void setfDataLength(String fDataLength) {
		this.fDataLength = fDataLength;
	}

    public String getfDataType()
    {
        return fDataType;
    }

    public void setfDataType(String fDataType)
    {
        this.fDataType = fDataType;
    }

    public String getfCodeSeq()
    {
        return fCodeSeq;
    }

    public void setfCodeSeq(String fCodeSeq)
    {
        this.fCodeSeq = fCodeSeq;
    }

    public String getfIsRate()
    {
        return fIsRate;
    }

    public void setfIsRate(String fIsRate)
    {
        this.fIsRate = fIsRate;
    }


    /*    public String getfBmgzMc()
    {
        return fBmgzMc;
    }

    public void setfBmgzMc(String fBmgzMc)
    {
        this.fBmgzMc = fBmgzMc;
    }*/
}