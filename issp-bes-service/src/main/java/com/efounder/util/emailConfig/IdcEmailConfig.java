package com.efounder.util.emailConfig;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;


/**
 * 邮件配置对象 idc_email_config
 *
 * @author gaoguangchao
 * @date 2020-11-19
 */
public class IdcEmailConfig extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private String emailId;
    //服务器名称，类似163的： smtp.163.com;发件人邮箱的 SMTP 服务器地址, 必须准确, 不同邮件服务器地址不同, 一般(只是一般, 绝非绝对)格式为: smtp.xxx.com
    /** 服务器地址 */
    private String mailServerhost;

    /** 发件人地址 */
    private String fromAddress;
    //邮件安全密码，网易的是客户端授权密码;某些邮箱服务器为了增加邮箱本身密码的安全性，给 SMTP 客户端设置了独立密码（有的邮箱称为“授权码”）,
    //对于开启了独立密码的邮箱, 这里的邮箱密码必需使用这个独立密码（授权码）。
    /** 授权码或独立密码 */
    private String password;

    /** 发件人昵称 */
    private String fromPersonal;

    /** 收件人昵称 */
    private String toPersonal;

    /** 邮件标题 */
    private String subject;

    private String toAddress;//收件人地址

    private String content;//发送内容

    private String filePath; //附件地址

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getToAddress() {
        return toAddress;
    }

    public void setToAddress(String toAddress) {
        this.toAddress = toAddress;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setEmailId(String emailId)
    {
        this.emailId = emailId;
    }

    public String getEmailId()
    {
        return emailId;
    }
    public void setMailServerhost(String mailServerhost)
    {
        this.mailServerhost = mailServerhost;
    }

    public String getMailServerhost()
    {
        return mailServerhost;
    }
    public void setFromAddress(String fromAddress)
    {
        this.fromAddress = fromAddress;
    }

    public String getFromAddress()
    {
        return fromAddress;
    }
    public void setPassword(String password)
    {
        this.password = password;
    }

    public String getPassword()
    {
        return password;
    }
    public void setFromPersonal(String fromPersonal)
    {
        this.fromPersonal = fromPersonal;
    }

    public String getFromPersonal()
    {
        return fromPersonal;
    }
    public void setToPersonal(String toPersonal)
    {
        this.toPersonal = toPersonal;
    }

    public String getToPersonal()
    {
        return toPersonal;
    }
    public void setSubject(String subject)
    {
        this.subject = subject;
    }

    public String getSubject()
    {
        return subject;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
                .append("emailId", getEmailId())
                .append("mailServerhost", getMailServerhost())
                .append("fromAddress", getFromAddress())
                .append("password", getPassword())
                .append("fromPersonal", getFromPersonal())
                .append("toPersonal", getToPersonal())
                .append("subject", getSubject())
                .toString();
    }
}
