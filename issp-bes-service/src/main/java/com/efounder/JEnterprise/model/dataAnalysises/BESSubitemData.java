package com.efounder.JEnterprise.model.dataAnalysises;


import com.core.common.BaseEntity;

import java.util.List;
import java.util.Objects;
/**
 * 分项数据
 * @author LvSihan
 *
 */
public class BESSubitemData implements BaseEntity<String>{

	private static final long serialVersionUID = -8042626788288922814L;

	/**
	 * 标识
	 */
	private String fId;

	/**
	 * 分项编号
	 */
    private String fFxbh;
    /**
	 * 电能编号
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
	 * 统计类型
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
    private String fPercapitaEnergy;
    /**
     * 人均金额
     */
    private String fPercapitaMoney;
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
    private String fCrdate;
    /**
     * 修改日期 
     */
    private String fChdate;
    /**
     * 分项名称
     */
    private String fFxmc;
    /**
     * 园区编号
     */
    private String fYqbh;
    
    private String rows;
    
    /**
     * 查询多个分项编号使用
     */
    private List<String> fFxbhs;

    /**
     * 同期起始时间
     */
    private String ftbhb_sametime_start;
    
    /**
     * 同期结束时间
     */
    private String ftbhb_sametime_end;
   

	public List<String> getfFxbhs() {
		return fFxbhs;
	}

	public void setfFxbhs(List<String> fFxbhs) {
		this.fFxbhs = fFxbhs;
	}

	public String getfPercapitaEnergy() {
		return fPercapitaEnergy;
	}

	public void setfPercapitaEnergy(String fPercapitaEnergy) {
		this.fPercapitaEnergy = fPercapitaEnergy;
	}

	public String getfPercapitaMoney() {
		return fPercapitaMoney;
	}

	public void setfPercapitaMoney(String fPercapitaMoney) {
		this.fPercapitaMoney = fPercapitaMoney;
	}

	public String getfUnitareaData() {
		return fUnitareaData;
	}

	public void setfUnitareaData(String fUnitareaData) {
		this.fUnitareaData = fUnitareaData;
	}

	public String getfUnitareaMoney() {
		return fUnitareaMoney;
	}

	public void setfUnitareaMoney(String fUnitareaMoney) {
		this.fUnitareaMoney = fUnitareaMoney;
	}

	public String getfCjsj() {
		return fCjsj;
	}

	public void setfCjsj(String fCjsj) {
		this.fCjsj = fCjsj;
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


    public String getfId() {
        return fId;
    }

    public void setfId(String fId) {
        this.fId = fId == null ? null : fId.trim();
    }

    public String getfFxbh() {
        return fFxbh;
    }

    public void setfFxbh(String fFxbh) {
        this.fFxbh = fFxbh == null ? null : fFxbh.trim();
    }

    public String getfDnbh() {
        return fDnbh;
    }

    public void setfDnbh(String fDnbh) {
        this.fDnbh = fDnbh == null ? null : fDnbh.trim();
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

	public String getfFxmc() {
		return fFxmc;
	}

	public void setfFxmc(String fFxmc) {
		this.fFxmc = fFxmc;
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


	@Override
	public boolean equals(Object o)
	{
		if (this == o) return true;
		if (o == null) return false;
		BESSubitemData that = (BESSubitemData) o;
		return Objects.equals(fFxbh, that.fFxbh) &&
				Objects.equals(fDnbh, that.fDnbh) &&
				Objects.equals(fCjsj, that.fCjsj) &&
				Objects.equals(fType, that.fType);
	}


	@Override
	public int hashCode()
	{
		return Objects.hash(fFxbh, fDnbh, fCjsj, fData, fType, fYqbh);
	}
}