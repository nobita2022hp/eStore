package com.estore.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;

@Configuration
public class GmailConfig {

    @Bean
    public JavaMailSender getJavaMailSender(){
        var sender = new JavaMailSenderImpl();
        sender.setDefaultEncoding("utf-8");
        sender.setHost("smtp.gmail.com");
        sender.setPort(587);
        sender.setUsername("henrylarkson102@gmail.com");
        sender.setPassword("vinhconan");

        var props = sender.getJavaMailProperties();
        props.setProperty("mail.transport.protocol", "smtp");
        props.setProperty("mail.smtp.auth", "true");
        props.setProperty("mail.smtp.starttls.enable", "true");
        props.setProperty("mail.debug", "true");
        return sender;
    }
}
