package com.efounder.util.test;

//import com.ruoyi.common.utils.StringUtils;
//import com.ruoyi.project.system.domain.IdcEmailConfig;

import com.efounder.util.StringUtils;
import com.efounder.util.emailConfig.IdcEmailConfig;
import org.springframework.stereotype.Service;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

/**
 * 邮件发送工具
 * @author GaoGuangChao
 * @create 2020-11-18 17:21
 */
@Service
public class EmailService {
    private static Transport transport;
    private static Session session;
    /*
    * 启动邮件推送服务
    * */
    public static void init(IdcEmailConfig mailInfo) throws Exception {//mes代表发出去的字符串
        // 1. 创建参数配置, 用于连接邮件服务器的参数配置
        Properties props = new Properties();                                // 参数配置
        props.setProperty("mail.transport.protocol", "smtp");               // 使用的协议（JavaMail规范要求）
        props.setProperty("mail.smtp.host", mailInfo.getMailServerhost());  // 发件人的邮箱的 SMTP 服务器地址
        props.setProperty("mail.smtp.auth", "true");                        // 需要请求认证

        // PS: 某些邮箱服务器要求 SMTP 连接需要使用 SSL 安全认证 (为了提高安全性, 邮箱支持SSL连接, 也可以自己开启),
        //     如果无法连接邮件服务器, 仔细查看控制台打印的 log, 如果有有类似 “连接失败, 要求 SSL 安全连接” 等错误,
        //     打开下面 /* ... */ 之间的注释代码, 开启 SSL 安全连接。
        /*
        // SMTP 服务器的端口 (非 SSL 连接的端口一般默认为 25, 可以不添加, 如果开启了 SSL 连接,
        //                  需要改为对应邮箱的 SMTP 服务器的端口, 具体可查看对应邮箱服务的帮助,
        //                  QQ邮箱的SMTP(SLL)端口为465或587, 其他邮箱自行去查看)
        final String smtpPort = "465";
        props.setProperty("mail.smtp.port", smtpPort);
        props.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.setProperty("mail.smtp.socketFactory.fallback", "false");
        props.setProperty("mail.smtp.socketFactory.port", smtpPort);
        */

        // 2. 根据配置创建会话对象, 用于和邮件服务器交互
        session = Session.getInstance(props);
        // 设置为debug模式, 可以查看详细的发送 log
        session.setDebug(true);
        // 4. 根据 Session 获取邮件传输对象
         transport = session.getTransport();

        // 5. 使用 邮箱账号 和 密码 连接邮件服务器, 这里认证的邮箱必须与 message 中的发件人邮箱一致, 否则报错
        //
        //    PS_01: 成败的判断关键在此一句, 如果连接服务器失败, 都会在控制台输出相应失败原因的 log,
        //           仔细查看失败原因, 有些邮箱服务器会返回错误码或查看错误类型的链接, 根据给出的错误
        //           类型到对应邮件服务器的帮助网站上查看具体失败原因。
        //
        //    PS_02: 连接失败的原因通常为以下几点, 仔细检查代码:
        //           (1) 邮箱没有开启 SMTP 服务;
        //           (2) 邮箱密码错误, 例如某些邮箱开启了独立密码;
        //           (3) 邮箱服务器要求必须要使用 SSL 安全连接;
        //           (4) 请求过于频繁或其他原因, 被邮件服务器拒绝服务;
        //           (5) 如果以上几点都确定无误, 到邮件服务器网站查找帮助。
        //
        //    PS_03: 仔细看log, 认真看log, 看懂log, 错误原因都在log已说明。
        transport.connect(mailInfo.getFromAddress(), mailInfo.getPassword());
    }
    /*
    * 关闭邮件推送服务
    * */
    public static void closeEmail(){
        // 7. 关闭连接
        try {
            transport.close();
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
    /*
    * 发送邮件
    * */
    public static void sendEmail(IdcEmailConfig mailInfo,String time) throws Exception{
        if(StringUtils.isNull(session)){
            init(mailInfo);
        }
        // 3. 创建一封邮件
        MimeMessage message = createMimeMessage(session, mailInfo,time);
        // 6. 发送邮件, 发到所有的收件地址, message.getAllRecipients() 获取到的是在创建邮件对象时添加的所有收件人, 抄送人, 密送人
        transport.sendMessage(message, message.getAllRecipients());
    }
    /**
     * 创建一封只包含文本的简单邮件
     *
     * @param session 和服务器交互的会话
     * @return
     * @throws Exception
     */
    public static   MimeMessage createMimeMessage(Session session, IdcEmailConfig mailInfo,String time) throws Exception {
        // 1. 创建一封邮件
        MimeMessage message = new MimeMessage(session);

        // 2. From: 发件人(发件人地址、昵称)
        message.setFrom(new InternetAddress(mailInfo.getFromAddress(), mailInfo.getFromPersonal(), "UTF-8"));

        // 3. To: 收件人（收件人地址昵称）
        message.setRecipient(MimeMessage.RecipientType.TO, new InternetAddress(mailInfo.getToAddress(), mailInfo.getToPersonal(), "UTF-8"));

        // 4. Subject: 邮件主题（邮件标题）
        message.setSubject(mailInfo.getSubject(), "UTF-8");

        // 5. Content: 邮件正文（可以使用html标签）
        message.setContent(mailInfo.getContent(), "text/html;charset=UTF-8");


        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date =  sdf.parse(time);
        // 6. 设置发件时间
        message.setSentDate(date);

        // 7. 保存设置
//        message.saveChanges();

        //=================================准备图片数据=======================================
//        MimeBodyPart image=new MimeBodyPart();
//        //图片需要经过数据化的处理
//        DataHandler dh=new DataHandler(new FileDataSource("图片路径"));
//        //在part中放入这个处理过图片的数据
//        image.setDataHandler(dh);
//        //给这个part设置一个ID名字
//        image.setContentID("bz.jpg");
//
//        //准备正文的数据
//        MimeBodyPart text=new MimeBodyPart();
//        text.setContent("我的图片：<img src='cid:bz.jpg'>","text/html;charset=UTF-8");

        //=================================准备附件数据
        MimeBodyPart body= new MimeBodyPart();
        body.setDataHandler(new DataHandler(new FileDataSource(mailInfo.getFilePath())));
        body.setFileName(mailInfo.getFileName());

        //描述数据关系
        MimeMultipart mm=new MimeMultipart();
//        mm.addBodyPart(text);
        mm.addBodyPart(body);
//        mm.addBodyPart(image);
        // mm.setSubType("related");如果只发送图片，则关系为related
        mm.setSubType("mixed");

        //设置到消息中，保存修改
        message.setContent(mm);
        message.saveChanges();

        return message;
    }

    public static void main(String[] args){


        IdcEmailConfig mailInfo=new IdcEmailConfig();
        mailInfo.setContent("测试报表信息");
        mailInfo.setFromAddress("2856527022@qq.com");
        mailInfo.setMailServerhost("smtp.qq.com");
        mailInfo.setPassword("bzslwebaegohdehg");
        mailInfo.setSubject("报表信息");
        mailInfo.setToAddress("2856527022@qq.com");

        try {
            init(mailInfo);
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
//            sendEmail(mailInfo);
//            sendEmail(mailInfo);
//            sendEmail(mailInfo);
            sendEmail(mailInfo,"2022-04-22 17:26:00");
            sendEmail(mailInfo,"2022-04-22 17:27:00");
            sendEmail(mailInfo,"2022-04-22 17:28:00");
            closeEmail();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
