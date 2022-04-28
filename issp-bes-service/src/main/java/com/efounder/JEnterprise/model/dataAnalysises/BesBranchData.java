package com.efounder.JEnterprise.model.dataAnalysises;

import com.core.common.BaseEntity;

import java.util.Date;

/**
 *支路数据表 ֧
 * 
 * @author LvSihan
 * 
 * @date 2018-08-03
 */
public class BesBranchData implements BaseEntity<String> {
    /**
	 * 
	 */
	private static final long serialVersionUID = -8974612643159877396L;

    private String fId;

    private String fZlbh;

    private String fDnbh;

    private String fCjsj;

    private Double fData;

    private String fType;

    private String fAllMoney;

    private String fCoalAmount;

    private String fCo2;
    
    private String fPermanData;
    
    private String fPermanMoney;
    
    private String fUnitareData;
    
    private String fUnitareMoney;

    private Date fCrdate;

    private Date fChdate;
    
    private String rows;

    /**
     *
     * @Description: 添加支路名称
     *
     * @auther: wanghongjie
     * @date: 13:48 2021/5/11
     * @param:
     * @return:
     *
     */
    private String fZlmc;
    /**
     *
     * @Description: 添加单位
     *
     * @auther: wanghongjie
     * @date: 17:31 2021/5/11
     * @param: []
     * @return: java.lang.String
     *
     */
    private String fUnit;

    public String getfPermanData() {
		return fPermanData;
	}

    public String getfId() {
        return fId;
    }

    public void setfId(String fId) {
        this.fId = fId == null ? null : fId.trim();
    }

    public String getfZlbh() {
        return fZlbh;
    }

    public void setfZlbh(String fZlbh) {
        this.fZlbh = fZlbh == null ? null : fZlbh.trim();
    }

    public String getfDnbh() {
        return fDnbh;
    }

    public void setfDnbh(String fDnbh) {
        this.fDnbh = fDnbh == null ? null : fDnbh.trim();
    }

    public String getfCjsj() {
        return fCjsj;
    }

    public void setfCjsj(String fCjsj) {
        this.fCjsj = fCjsj;
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
	
	public void setfPermanData(String fPermanData) {
		this.fPermanData = fPermanData;
	}

	public String getfPermanMoney() {
		return fPermanMoney;
	}

	public void setfPermanMoney(String fPermanMoney) {
		this.fPermanMoney = fPermanMoney;
	}

	public String getfUnitareData() {
		return fUnitareData;
	}

	public void setfUnitareData(String fUnitareData) {
		this.fUnitareData = fUnitareData;
	}

	public String getfUnitareMoney() {
		return fUnitareMoney;
	}

	public void setfUnitareMoney(String fUnitareMoney) {
		this.fUnitareMoney = fUnitareMoney;
	}

    public String getfZlmc() {
        return fZlmc;
    }

    public void setfZlmc(String fZlmc) {
        this.fZlmc = fZlmc;
    }

    public String getfUnit() {
        return fUnit;
    }

    public void setfUnit(String fUnit) {
        this.fUnit = fUnit;
    }
}