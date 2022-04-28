package com.efounder.JEnterprise.model.dataAnalysises;

import com.core.common.BaseEntity;

import java.util.Date;
import java.util.List;

/**
 * 分户数据表
 * 
 * @author LvSihan
 * 
 * @date 2018-08-06
 */
public class BesHouseholdData implements BaseEntity<String>{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1730741782835619534L;

	/**
     * 标识符
     */
    private String fId;

    /**
     * 分户编号 FK(BES_HOUSEHOLD _CONF.F_FHBH)
     */
    private String fFhbh;

    /**
     * 电能编号 FK(BES_ELECTRIC_PARAMS.F_DNBH)
     */
    private String fDnbh;

    /**
     * 采集时间
     */
    private String fCjsj;
    
    /**
	 * 开始采集时间
	 */
    private String fCjsj_start;
    /**
     * 结束采集时间
     */
    private String fCjsj_end;

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
    private Double fAllMoney;

    /**
     * 耗煤量
     */
    private Double fCoalAmount;

    /**
     * 二氧化碳
     */
    private Double fCo2;

    /**
     * 人均能耗
     */
    private Double fPermanData;

    /**
     * 人均金额
     */
    private Double fPermanMoney;

    /**
     * 单位面积能耗
     */
    private Double fUnitareaData;

    /**
     * 单位面积金额
     */
    private Double fUnitareaMoney;

    /**
     * 创建日期
     */
    private Date fCrdate;

    /**
     * 修改日期
     */
    private Date fChdate;
    /**
     * 园区编号
     */
    private String fYqbh;
    
    /**
     * 分户名称
     */
    private String fFhmc;
    
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

	private String fFhlx;//分户单位(分户的单位)

    /**
     * 人数
     */
    private String fPersonNums;

	
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

	public List<String> getfFhbhs() {
		return fFhbhs;
	}

	public void setfFhbhs(List<String> fFhbhs) {
		this.fFhbhs = fFhbhs;
	}

    public String getfYqbh() {
		return fYqbh;
	}

	public void setfYqbh(String fYqbh) {
		this.fYqbh = fYqbh;
	}

	public String getRows() {
		return rows;
	}

	public void setRows(String rows) {
		this.rows = rows;
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

    public Double getfAllMoney()
    {
        return fAllMoney;
    }

    public void setfAllMoney(Double fAllMoney)
    {
        this.fAllMoney = fAllMoney;
    }

    public Double getfCoalAmount()
    {
        return fCoalAmount;
    }

    public void setfCoalAmount(Double fCoalAmount)
    {
        this.fCoalAmount = fCoalAmount;
    }

    public Double getfCo2()
    {
        return fCo2;
    }

    public void setfCo2(Double fCo2)
    {
        this.fCo2 = fCo2;
    }

    public Double getfPermanData()
    {
        return fPermanData;
    }

    public void setfPermanData(Double fPermanData)
    {
        this.fPermanData = fPermanData;
    }

    public Double getfPermanMoney()
    {
        return fPermanMoney;
    }

    public void setfPermanMoney(Double fPermanMoney)
    {
        this.fPermanMoney = fPermanMoney;
    }

    public Double getfUnitareaData()
    {
        return fUnitareaData;
    }

    public void setfUnitareaData(Double fUnitareaData)
    {
        this.fUnitareaData = fUnitareaData;
    }

    public Double getfUnitareaMoney()
    {
        return fUnitareaMoney;
    }

    public void setfUnitareaMoney(Double fUnitareaMoney)
    {
        this.fUnitareaMoney = fUnitareaMoney;
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

	
	public String getfFhmc() {
		return fFhmc;
	}

	public void setfFhmc(String fFhmc) {
		this.fFhmc = fFhmc;
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

    public String getfFhlx() {
        return fFhlx;
    }

    public void setfFhlx(String fFhlx) {
        this.fFhlx = fFhlx;
    }

    public String getfPersonNums() {
        return fPersonNums;
    }

    public void setfPersonNums(String fPersonNums) {
        this.fPersonNums = fPersonNums;
    }
}