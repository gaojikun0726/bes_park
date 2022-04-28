package com.efounder.JEnterprise.model.basedatamanage.energyinformation;

import com.core.common.BaseEntity;

import java.io.Serializable;
/**
 * 支路配置
 * @author LvSihan
 *
 */
public class BESBranchConf implements BaseEntity<Serializable>{

	private static final long serialVersionUID = 7437226641008789755L;

	private String fZlbh;//支路编号

    private String fZlmc;//支路名称

    private String fPzlbh;//父支路编号

    private String fJs;//级数

    private String fRealBh;//实际编号：CAD图纸上的编号

    private String fLocation;//所属位置

    private String fRatedPower;//额定功率
    
    private String fEquipment;//设备

	private String fEquipmentSet;//设备集

    private String fNybh;//能源编号

    private String fZzjgbh;//组织机构编号
    
	private String fYqbh; //园区编号

    private String fCrdate;//创建日期
    
    private String keywords;//fZlbh,fZlmc关键字

    private String fJl;//是否级联 (0:是  1:否)

    private String fUserName;//用户名称

    private String fZzjgId;//组织机构id
    
    
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

	private String fChdate;//修改日期

    public String getfZlbh() {
        return fZlbh;
    }

    public void setfZlbh(String fZlbh) {
        this.fZlbh = fZlbh == null ? null : fZlbh.trim();
    }

    public String getfZlmc() {
        return fZlmc;
    }

    public void setfZlmc(String fZlmc) {
        this.fZlmc = fZlmc == null ? null : fZlmc.trim();
    }

    public String getfPzlbh() {
        return fPzlbh;
    }

    public void setfPzlbh(String fPzlbh) {
        this.fPzlbh = fPzlbh == null ? null : fPzlbh.trim();
    }

    public String getfJs() {
        return fJs;
    }

    public void setfJs(String fJs) {
        this.fJs = fJs == null ? null : fJs.trim();
    }

    public String getfRealBh() {
        return fRealBh;
    }

    public void setfRealBh(String fRealBh) {
        this.fRealBh = fRealBh == null ? null : fRealBh.trim();
    }

    public String getfLocation() {
        return fLocation;
    }

    public void setfLocation(String fLocation) {
        this.fLocation = fLocation == null ? null : fLocation.trim();
    }

    public String getfRatedPower() {
        return fRatedPower;
    }

    public void setfRatedPower(String fRatedPower) {
        this.fRatedPower = fRatedPower == null ? null : fRatedPower.trim();
    }

    public String getfNybh() {
        return fNybh;
    }

    public void setfNybh(String fNybh) {
        this.fNybh = fNybh == null ? null : fNybh.trim();
    }

    public String getfZzjgbh() {
        return fZzjgbh;
    }

    public void setfZzjgbh(String fZzjgbh) {
        this.fZzjgbh = fZzjgbh == null ? null : fZzjgbh.trim();
    }
    
    public String getfYqbh() {
		return fYqbh;
	}

	public void setfYqbh(String fYqbh) {
		this.fYqbh = fYqbh == null ? null : fYqbh.trim();;
	}

	public String getKeywords() {
		return keywords;
	}

	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}

    public String getfEquipment() {
		return fEquipment;
	}

	public void setfEquipment(String fEquipment) {
		this.fEquipment = fEquipment;
	}

	public String getfEquipmentSet() {
		return fEquipmentSet;
	}

	public void setfEquipmentSet(String fEquipmentSet) {
		this.fEquipmentSet = fEquipmentSet;
	}

    public String getfJl() {
        return fJl;
    }

    public void setfJl(String fJl) {
        this.fJl = fJl;
    }

    public String getfUserName() {
        return fUserName;
    }

    public void setfUserName(String fUserName) {
        this.fUserName = fUserName;
    }

    public String getfZzjgId() {
        return fZzjgId;
    }

    public void setfZzjgId(String fZzjgId) {
        this.fZzjgId = fZzjgId;
    }
}