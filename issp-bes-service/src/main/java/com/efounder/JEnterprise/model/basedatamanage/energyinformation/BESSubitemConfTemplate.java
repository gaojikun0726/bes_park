package com.efounder.JEnterprise.model.basedatamanage.energyinformation;

import com.core.common.BaseEntity;

/**
 * 分项配置模板
 * @author wanghongjie
 *
 */
public class BESSubitemConfTemplate implements BaseEntity<String>{

	private static final long serialVersionUID = 2901799121648015004L;
	
//	private String fGuid;

	private String fFxbh;

    private String fFxmc;

    private String fPfxbh;

    private String fJs;

    private String fNybh;

//    private String fZzjgbh;
//
//    private String fCrdate;
//
//    private String fChdate;
//
//    private String fYqbh;
//
//    private String fJl;
//
//    private String fUserName;
//
//    private String fZzjgId;

    /**
     *
     * @Description: 新增建筑信息主键id
     *
     * @auther: wanghongjie
     * @date: 15:08 2021/5/25
     * @param:
     * @return:
     *
     */
//    private String fBudingId;
    private String fSubitemCode;

//    public String getfYqbh() {
//        return fYqbh;
//    }
//
//    public void setfYqbh(String fYqbh) {
//        this.fYqbh = fYqbh;
//    }

    public String getfFxbh() {
        return fFxbh;
    }

    public void setfFxbh(String fFxbh) {
        this.fFxbh = fFxbh == null ? null : fFxbh.trim();
    }

    public String getfFxmc() {
        return fFxmc;
    }

    public void setfFxmc(String fFxmc) {
        this.fFxmc = fFxmc == null ? null : fFxmc.trim();
    }

    public String getfPfxbh() {
        return fPfxbh;
    }

    public void setfPfxbh(String fPfxbh) {
        this.fPfxbh = fPfxbh == null ? null : fPfxbh.trim();
    }

    public String getfJs() {
        return fJs;
    }

    public void setfJs(String fJs) {
        this.fJs = fJs == null ? null : fJs.trim();
    }

    public String getfNybh() {
        return fNybh;
    }

    public void setfNybh(String fNybh) {
        this.fNybh = fNybh == null ? null : fNybh.trim();
    }

//    public String getfZzjgbh() {
//        return fZzjgbh;
//    }
//
//    public void setfZzjgbh(String fZzjgbh) {
//        this.fZzjgbh = fZzjgbh == null ? null : fZzjgbh.trim();
//    }
//
//	public String getfCrdate() {
//		return fCrdate;
//	}
//
//	public void setfCrdate(String fCrdate) {
//		this.fCrdate = fCrdate;
//	}
//
//	public String getfChdate() {
//		return fChdate;
//	}
//
//	public void setfChdate(String fChdate) {
//		this.fChdate = fChdate;
//	}
//
//	public String getfGuid() {
//		return fGuid;
//	}
//
//	public void setfGuid(String fGuid) {
//		this.fGuid = fGuid;
//	}
//
//    public String getfJl() {
//        return fJl;
//    }
//
//    public void setfJl(String fJl) {
//        this.fJl = fJl;
//    }
//
//    public String getfUserName() {
//        return fUserName;
//    }
//
//    public void setfUserName(String fUserName) {
//        this.fUserName = fUserName;
//    }
//
//    public String getfZzjgId() {
//        return fZzjgId;
//    }
//
//    public void setfZzjgId(String fZzjgId) {
//        this.fZzjgId = fZzjgId;
//    }
//
//    public String getfBudingId() {
//        return fBudingId;
//    }
//
//    public void setfBudingId(String fBudingId) {
//        this.fBudingId = fBudingId;
//    }
//

    public String getfSubitemCode() {
        return fSubitemCode;
    }

    public void setfSubitemCode(String fSubitemCode) {
        this.fSubitemCode = fSubitemCode;
    }
}