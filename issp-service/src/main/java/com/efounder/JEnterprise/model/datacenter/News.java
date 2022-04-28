package com.efounder.JEnterprise.model.datacenter;

import com.core.common.BaseEntity;

import java.util.Date;

/**
 * 
 * 类名称：News
 * 类描述：新闻对象
 * 创建人：wujf
 * 修改人：wujf
 * 修改时间：2018年1月2日 下午6:46:39
 * 修改备注：
 * @version 1.0.0 
 *
 */
public class News implements BaseEntity<String> {

    private static final long serialVersionUID = 3624947930970250778L;

    private String id;

    private String title;// 新闻标题

    private String description;// 新闻内容

    private String address;// 新闻发生地址

    private Date newsTime;// 新闻发生时间

    private Date createTime;// 新闻发布时间

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Date getNewsTime() {
        return newsTime;
    }

    public void setNewsTime(Date newsTime) {
        this.newsTime = newsTime;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

}
