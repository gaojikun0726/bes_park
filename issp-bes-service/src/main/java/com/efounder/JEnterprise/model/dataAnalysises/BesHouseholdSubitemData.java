package com.efounder.JEnterprise.model.dataAnalysises;

import com.core.common.BaseEntity;

import java.util.Date;
import java.util.List;

/**
 * 
 * 
 * @author LvSihan
 * 
 * @date 2018-08-07
 */
public class BesHouseholdSubitemData implements BaseEntity<String>{
    /**
	 * 
	 */
	private static final long serialVersionUID = -3327800958068807088L;

	/**
     * 标识符
     */
    private String fId;

    /**
     * 分户编号
     */
    private String fFhbh;

    /**
     * 分项编号
     */
    private String fFxbh;

    /**
     * 能耗数据
     */
    private Double fData;

    /**
     * 采集时间
     */
    private String fCjsj;

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
    
    /**
     * 查询多个分户编号使用
     */
    private List<String> fFhbhs;
    
    /**
     * 同期起始时间
     */
    private String ftbhb_sametime_start;
    
    /**
     * 同期结束时间
     */
    private String ftbhb_sametime_end;
    
    private String rows;
    
    /**
     * 分户名称
     */
    private String fFhmc;
    
	/**
	 * 开始采集时间
	 */
   private String fCjsj_start;
   /**
    * 结束采集时间
    */
   private String fCjsj_end; 
   /**
    * 分项名称
    */
   private String fFxmc;
   
   
    

    public String getfFxmc() {
    	return fFxmc;
    }

    public void setfFxmc(String fFxmc) {
    	this.fFxmc = fFxmc;
    }

	public String getfCjsj_start() {
		return fCjsj_start;
	}

	public void setfCjsj_start(String fCjsj_start) {
		this.fCjsj_start = fCjsj_start;
	}
	
	public String getfCjsj_end() {
		return fCjsj_end;
	}
	
	public void setfCjsj_end(String fCjsj_end) {
		this.fCjsj_end = fCjsj_end;
	}

	public String getfFhmc() {
		return fFhmc;
	}

	public void setfFhmc(String fFhmc) {
		this.fFhmc = fFhmc;
	}

	public String getfId() {
        return fId;
    }

    public void setfId(String fId) {
        this.fId = fId == null ? null : fId.trim();
    }

    public String getfFhbh() {
        return fFhbh;
    }

    public void setfFhbh(String fFhbh) {
        this.fFhbh = fFhbh == null ? null : fFhbh.trim();
    }

    public String getfFxbh() {
        return fFxbh;
    }

    public void setfFxbh(String fFxbh) {
        this.fFxbh = fFxbh == null ? null : fFxbh.trim();
    }

    public Double getfData()
    {
        return fData;
    }

    public void setfData(Double fData)
    {
        this.fData = fData;
    }

    public String getfCjsj() {
        return fCjsj;
    }

    public void setfCjsj(String fCjsj) {
        this.fCjsj = fCjsj;
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

	public List<String> getfFhbhs() {
		return fFhbhs;
	}

	public void setfFhbhs(List<String> fFhbhs) {
		this.fFhbhs = fFhbhs;
	}

	public String getFtbhb_sametime_start() {
		return ftbhb_sametime_start;
	}

	public void setFtbhb_sametime_start(String ftbhb_sametime_start) {
		this.ftbhb_sametime_start = ftbhb_sametime_start;
	}

	public String getFtbhb_sametime_end() {
		return ftbhb_sametime_end;
	}

	public void setFtbhb_sametime_end(String ftbhb_sametime_end) {
		this.ftbhb_sametime_end = ftbhb_sametime_end;
	}
	
	
}