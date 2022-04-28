package com.efounder.JEnterprise.config.shiro.vo;

import com.efounder.JEnterprise.common.constants.Constants;
import com.efounder.JEnterprise.model.auth.PermissionMenu;

import java.util.ArrayList;
import java.util.List;

public enum PermissionEnumUtil {

    管理首页("管理首页", "fa-home", "glsy", null, "index", 1, 1), 
    
    站内新闻("站内新闻", "", "znxy", null, null, 1, 10),
    新闻发布("新闻发布", "", "znxy_xwfb", "znxy", "news/add", 2, 11),
    新闻列表("新闻列表", "", "znxy_xwlb", "znxy", "news/list",2, 12),
    默认数据库("默认数据库", "", "znxy_mrsjk", "znxy", "news/list", 2, 13),
    数据库1("数据库1", "", "znxy_sjk1", "znxy", "news/list1", 2, 14),
    数据库2("数据库2", "", "znxy_sjk2", "znxy", "news/list2", 2, 15),
    
    系统配置("系统配置", "fa-gear", "xtpz", null, "view/sysconfig/setconfig" , 1, 20),
    
    地区城市管理("地区城市管理", "fa-map-marker", "dccsgl", null, null , 1, 50),
    添加城市("添加城市", null, "dccsgl_tjcs", "dccsgl", "view/city/add2" , 2, 51),
    城市列表("城市列表", null, "dccsgl_cslb", "dccsgl", "view/city/list" , 2, 51),
    
    账号管理("账号管理", "fa-user", "chgl", null, "view/user/user_list" , 1, 60),

    
    数据统计("数据统计", "fa-pie-chart", "sjtj",  null, null , 1, 120),
    用户统计("用户统计", "fa-user", "sjtj_yhtj",  "sjtj", "view/data/statIncome_data" , 2, 121),
    客户统计("客户统计", "fa-group", "sjtj_khtj",  "sjtj", "view/data/statIncome_data" , 2, 122),
    收入统计("收入统计", "fa-line-chart", "sjtj_sytj",  "sjtj", "view/data/statIncome_data" , 2, 123),
    佣金统计("佣金统计", "fa-pie-chart", "sjtj_yjtj",  "sjtj", "view/data/statIncome_data" , 2, 124),
    
    
    ;

    private PermissionEnumUtil(String name, String cssClass, String key, String parentKey, String url, Integer lev, Integer sort) {
        this.name = name;
        this.cssClass = cssClass;
        this.key = key;
        this.lev=lev;
        this.sort = sort;
        this.url = url;
        this.hide = Constants.STATUS_VALID;
        this.parentKey = parentKey;
    }
    
    public static List<PermissionMenu> getPermissions(){
        List<PermissionMenu> list = new ArrayList<>();
        PermissionEnumUtil[] pers = PermissionEnumUtil.values();
        PermissionMenu per = null;
        for (PermissionEnumUtil p : pers) {
            per = new PermissionMenu();
            per.setName(p.getName());
            per.setCssClass(p.getCssClass());
            per.setKey(p.getKey());
            per.setHide(p.getHide());
            per.setUrl(p.getUrl());
            per.setLev(p.getLev());
            per.setSort(p.getSort());
            per.setParentKey(p.getParentKey());
            list.add(per);
        }
        return list;
    }

    private String name;
    private String cssClass;
    private String key;
    private Integer hide;
    private String url;
    private Integer lev;
    private Integer sort;
    private String parentKey;

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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Integer getLev() {
        return lev;
    }

    public void setLev(Integer lev) {
        this.lev = lev;
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

}
