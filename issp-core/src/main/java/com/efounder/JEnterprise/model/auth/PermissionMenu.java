package com.efounder.JEnterprise.model.auth;

import com.core.common.BaseEntity;

/**
 * 
 * 类名称：Permission
 * 类描述：菜单对象
 * 创建人：wujf
 * 修改人：wujf
 * 修改时间：2018年1月2日 下午6:46:58
 * 修改备注：
 * @version 1.0.0 
 *
 */
public class PermissionMenu implements BaseEntity<String> {

    private static final long serialVersionUID = -7141829387338999544L;

    private String id;

    /** 菜单名称 **/
    private String name;

    /** 菜单样式图标名称 **/
    private String cssClass;

    /** 菜单编码 **/
    private String key;

    /** 菜单是否显示 **/
    private Integer hide;
    
    /** 菜单级别，最多三级 **/
    private Integer lev;

    /** URL **/
    private String url;

    /** 显示顺序 **/
    private Integer sort;

    /** 父菜单编码 **/
    private String parentKey;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCssClass() {
        return cssClass;
    }

    public void setCssClass(String cssClass) {
        this.cssClass = cssClass;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public Integer getHide() {
        return hide;
    }

    public void setHide(Integer hide) {
        this.hide = hide;
    }
    
    public Integer getLev() {
        return lev;
    }

    public void setLev(Integer lev) {
        this.lev = lev;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public String getParentKey() {
        return parentKey;
    }

    public void setParentKey(String parentKey) {
        this.parentKey = parentKey;
    }
    
    /** fullSreen 全屏配置**/
    private String fullScreen;
    public String getFullScreen() {
		return fullScreen;
	}

	public void setFullScreen(String fullScreen) {
		this.fullScreen = fullScreen;
	}

	/** openType 页面打开方式**/
    private String openType;
    public String getOpenType() {
		return openType;
	}

	public void setOpenType(String openType) {
		this.openType = openType;
	}

	/** formParam 页面参数 key=value;key=value;key=value;**/
    private String formParam;

	public String getFormParam() {
		return formParam;
	}

	public void setFormParam(String formParam) {
		this.formParam = formParam;
	}
	
	/**
	 * tab页是否可关闭；1:可以关闭；0:不可关闭
	 */
	private String tabCloseable;

	public String getTabCloseable() {
		return tabCloseable;
	}

	public void setTabCloseable(String tabCloseable) {
		this.tabCloseable = tabCloseable;
	}
	
	private String gnbh;

	public String getGnbh() {
		return gnbh;
	}

	public void setGnbh(String gnbh) {
		this.gnbh = gnbh;
	}

}
