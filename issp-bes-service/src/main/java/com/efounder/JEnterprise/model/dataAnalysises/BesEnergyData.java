package com.efounder.JEnterprise.model.dataAnalysises;

import com.core.common.BaseEntity;

import java.util.Date;

/**
 * 能源数据表
 * 
 * @author LvSihan
 * 
 * @date 2018-08-04
 */
public class BesEnergyData implements BaseEntity<String>{
    /**
	 * 
	 */
	private static final long serialVersionUID = -460005414942736452L;

	/**
     * 标识符
     */
    private String fId;

    /**
     * 能源编号 FK(BES_ENERGY_TYPE.F_NYBH)
     */
    private String fNybh;

    /**
     * 园区编号
     */
    private String fYqbh;

    /**
     * 采集时间
     */
    private String fCjsj;

    /**
     * 数据值
     */
    private Double fData;

    /**
     * 统计类型 分:0、时：1、日：2、月：3、年：4
     */
    private String fType;

    /**
     * 总金额
     */
    private String fAllMoney;

    /**
     * 耗煤量
     */
    private String fCoalAmount;

    /**
     * 二氧化碳
     */
    private String fCo2;

    /**
     * 人均能耗
     */
    private String fPermanData;

    /**
     * 人均金额
     */
    private String fPermanMoney;

    /**
     * 单位面积能耗
     */
    private String fUnitareaData;

    /**
     * 单位面积金额
     */
    private String fUnitareaMoney;

    /**
     * 创建日期
     */
    private Date fCrdate;

    /**
     * 修改日期
     */
    private Date fChdate;
    
    private String rows;

    public String getfId() {
        return fId;
    }

    public void setfId(String fId) {
        this.fId = fId == null ? null : fId.trim();
    }

    public String getfNybh() {
        return fNybh;
    }

    public void setfNybh(String fNybh) {
        this.fNybh = fNybh == null ? null : fNybh.trim();
    }

    public String getfYqbh() {
        return fYqbh;
    }

    public void setfYqbh(String fYqbh) {
        this.fYqbh = fYqbh == null ? null : fYqbh.trim();
    }

    public String getfCjsj() {
        return fCjsj;
    }

    public void setfCjsj(String time) {
        this.fCjsj = time;
    }

    public Double getfData()
    {
        return fData;
    }

    public void setfData(Double fData)
    {
        this.fData = fData;
    }

    public String getfType() {
        return fType;
    }

    public void setfType(String fType) {
        this.fType = fType == null ? null : fType.trim();
    }

    public String getfAllMoney() {
        return fAllMoney;
    }

    public void setfAllMoney(String fAllMoney) {
        this.fAllMoney = fAllMoney == null ? null : fAllMoney.trim();
    }

    public String getfCoalAmount() {
        return fCoalAmount;
    }

    public void setfCoalAmount(String fCoalAmount) {
        this.fCoalAmount = fCoalAmount == null ? null : fCoalAmount.trim();
    }

    public String getfCo2() {
        return fCo2;
    }

    public void setfCo2(String fCo2) {
        this.fCo2 = fCo2 == null ? null : fCo2.trim();
    }

    public String getfPermanData() {
        return fPermanData;
    }

    public void setfPermanData(String fPermanData) {
        this.fPermanData = fPermanData == null ? null : fPermanData.trim();
    }

    public String getfPermanMoney() {
        return fPermanMoney;
    }

    public void setfPermanMoney(String fPermanMoney) {
        this.fPermanMoney = fPermanMoney == null ? null : fPermanMoney.trim();
    }

    public String getfUnitareaData() {
        return fUnitareaData;
    }

    public void setfUnitareaData(String fUnitareaData) {
        this.fUnitareaData = fUnitareaData == null ? null : fUnitareaData.trim();
    }

    public String getfUnitareaMoney() {
        return fUnitareaMoney;
    }

    public void setfUnitareaMoney(String fUnitareaMoney) {
        this.fUnitareaMoney = fUnitareaMoney == null ? null : fUnitareaMoney.trim();
    }

    public Date getfCrdate() {
        return fCrdate;
    }

    public void setfCrdate(Date fCrdate) {
        this.fCrdate = fCrdate;
    }

    public Date getfChdate() {
        return fChdate;
    }

    public void setfChdate(Date fChdate) {
        this.fChdate = fChdate;
    }

	public String getRows() {
		return rows;
	}

	public void setRows(String rows) {
		this.rows = rows;
	}
}