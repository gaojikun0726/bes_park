package com.efounder.util.test;

import com.sun.mail.util.MailSSLSocketFactory;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.util.Properties;

/**
 * @Author: wanghongjie
 * @Description:
 * @Date: Created in 16:29 2022/4/22
 * @Modified By:
 */
public class ComplexMail {
    public static void main(String[] args) throws Exception {

        Properties prop=new Properties();
        prop.setProperty("mail.host","smtp.qq.com");///设置QQ邮件服务器
        prop.setProperty("mail.transport.protocol","smtp");///邮件发送协议
        prop.setProperty("mail.smtp.auth","true");//需要验证用户密码

        //QQ邮箱需要设置SSL加密
        MailSSLSocketFactory sf=new MailSSLSocketFactory();
        sf.setTrustAllHosts(true);
        prop.put("mail.smtp.ssl.enable","true");
        prop.put("mail.smtp.ssl.socketFactory",sf);

        //使用javaMail发送邮件的5个步骤

        //1.创建定义整个应用程序所需要的环境信息的session对象
        Session session=Session.getDefaultInstance(prop, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("XXXXXqq.com","授权码");
            }
        });

        //开启session的debug模式，这样可以查看到程序发送Email的运行状态
        session.setDebug(true);

        //2.通过session得到transport对象
        Transport ts=session.getTransport();

        //3.使用邮箱的用户名和授权码连上邮件服务器
        ts.connect("smtp.qq.com","XXXXX@qq.com","授权码");

        //4.创建邮件：写文件
        //注意需要传递session
        MimeMessage message=new MimeMessage(session);
        //指明邮件的发件人
        message.setFrom(new InternetAddress("XXXXXX@qq.com"));
        //指明邮件的收件人
        message.setRecipient(Message.RecipientType.TO,new InternetAddress("XXXXXXXX@qq.com"));
        //邮件标题
        message.setSubject("邮件主题");

        //邮件的文本内容
        //=================================准备图片数据=======================================
        MimeBodyPart image=new MimeBodyPart();
        //图片需要经过数据化的处理
        DataHandler dh=new DataHandler(new FileDataSource("图片路径"));
        //在part中放入这个处理过图片的数据
        image.setDataHandler(dh);
        //给这个part设置一个ID名字
        image.setContentID("bz.jpg");

        //准备正文的数据
        MimeBodyPart text=new MimeBodyPart();
        text.setContent("我的图片：<img src='cid:bz.jpg'>","text/html;charset=UTF-8");

        //=================================准备附件数据
        MimeBodyPart body= new MimeBodyPart();
        body.setDataHandler(new DataHandler(new FileDataSource("附件路径")));
        body.setFileName("1.txt");

        //描述数据关系
        MimeMultipart mm=new MimeMultipart();
        mm.addBodyPart(text);
        mm.addBodyPart(body);
        mm.addBodyPart(image);
        // mm.setSubType("related");如果只发送图片，则关系为related
        mm.setSubType("mixed");

        //设置到消息中，保存修改
        message.setContent(mm);
        message.saveChanges();

        //5.发送邮件
        ts.sendMessage(message,message.getAllRecipients());

        //6.关闭连接
        ts.close();

    }
}
