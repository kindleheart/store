package com.kindleheart.store.utils;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.Message.RecipientType;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;


public class MailTest {

    public static void main(String[] args) throws MessagingException {
        //服务器的设置
        Properties props = new Properties();
        props.setProperty("mail.host", "localhost");//设置传输协议
        props.setProperty("mail.smtp.auth", "true");//权限验证

        //邮箱帐号密码
        Authenticator authenticator = new Authenticator() {
            @Override
            public PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("aaa@store.com","123");
            }
        };

        //1、与服务器建立连接：Session
        Session session = Session.getDefaultInstance(props, authenticator);

        //2、编写邮件：Message
        Message message = new MimeMessage(session);
        //2.1、发件人
        message.setFrom(new InternetAddress("aaa@store.com"));
        //2.2、收件人 , to:收件人、cc ：抄送、bcc：暗送（密送）。
        message.setRecipient(RecipientType.TO, new InternetAddress("bbb@store.com"));
        //2.3、主题
        message.setSubject("测试");
        //2.4、正文
        String str = "哈哈，我发送了";
        message.setContent(str, "text/html;charset=UTF-8");

        //3、发送
        Transport.send(message);
    }

}