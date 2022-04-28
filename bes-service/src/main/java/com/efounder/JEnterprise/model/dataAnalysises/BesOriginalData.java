package com.efounder.JEnterprise.model.dataAnalysises;

import com.core.common.BaseEntity;

import java.util.Date;

/**
 * 电表原始数据表
 * 
 * @author wcyong
 * 
 * @date 2018-11-01
 */
public class BesOriginalData implements BaseEntity<String> {
    /**
     * 标识符
     */
    private String fId;

    /**
     * 电表系统名称 FK(BES_AMMETER. F_SYS_NAME)
     */
    private String fDbsysName;

    /**
     * 电能编号 FK(BES_ELECTRIC_PARAMS. F_NHBH)
     */
    private String fDnbh;

    /**
     * 原始数据
     */
    private String fData;

    /**
     * 采集时间
     */
    private Date fCjsj;

    /**
     * 数据类型（比如：能耗、电压、电流）
     */
    private String fType;

    /**
     * 创建日期
     */
    private Date fCrdate;

    /**
     * 修改日期
     */
    private Date fChdate;

	/**
	 * 开始日期
	 */
	private String time_start;

	/**
	 * 终止日期
	 */
	private String time_end;

	/**
	 * 支路ids 二级
	 */
	private String two_zlids;

	/**
	 * 支路ids 三级
	 */
	private String three_zlids;

	/**
	 * 支路ids 所有选中支路
	 */
	private String zlids;

    public String getfId() {
        return fId;
    }

    public void setfId(String fId) {
        this.fId = fId == null ? null : fId.trim();
    }

    public String getfDbsysName() {
        return fDbsysName;
    }

    public void setfDbsysName(String fDbsysName) {
        this.fDbsysName = fDbsysName == null ? null : fDbsysName.trim();
    }

    public String getfDnbh() {
        return fDnbh;
    }

    public void setfDnbh(String fDnbh) {
        this.fDnbh = fDnbh == null ? null : fDnbh.trim();
    }

    public String getfData() {
        return fData;
    }

    public void setfData(String fData) {
        this.fData = fData == null ? null : fData.trim();
    }

    public Date getfCjsj() {
        return fCjsj;
    }

    public void setfCjsj(Date fCjsj) {
        this.fCjsj = fCjsj;
    }

    public String getfType() {
        return fType;
    }

    public void setfType(String fType) {
        this.fType = fType == null ? null : fType.trim();
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

	public String getTime_start() {
		return time_start;
	}

	public void setTime_start(String time_start) {
		this.time_start = time_start;
	}

	public String getTime_end() {
		return time_end;
	}

	public void setTime_end(String time_end) {
		this.time_end = time_end;
	}

	public String getTwo_zlids() {
		return two_zlids;
	}

	public void setTwo_zlids(String two_zlids) {
		this.two_zlids = two_zlids;
	}

	public String getThree_zlids() {
		return three_zlids;
	}

	public void setThree_zlids(String three_zlids) {
		this.three_zlids = three_zlids;
	}

    public String getZlids()
    {
        return zlids;
    }

    public void setZlids(String zlids)
    {
        this.zlids = zlids;
    }
}