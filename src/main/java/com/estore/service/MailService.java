package com.estore.service;

import com.estore.bean.MailInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import java.io.File;
import java.util.Arrays;

@Service
public class MailService {

    private final Logger logger = LoggerFactory.getLogger(MailService.class);

    @Autowired
    private JavaMailSender sender;

    public void send(MailInfo mailInfo) throws MessagingException {
        var msg = sender.createMimeMessage();
        var helper = new MimeMessageHelper(msg, true, "utf-8");
        helper.setFrom(mailInfo.getFrom());
        helper.setTo(mailInfo.getTo());
        helper.setSubject(mailInfo.getSubject());
        helper.setText(mailInfo.getBody());
        helper.setReplyTo(mailInfo.getFrom());

        if (mailInfo.getCc() != null) {
            helper.setCc(mailInfo.getCc());
        }

        if (mailInfo.getBcc() != null) {
            helper.setBcc(mailInfo.getBcc());
        }

        String files = mailInfo.getFiles();
        if (files != null) {
            var paths = files.split(";");
            Arrays.stream(paths).forEach(p -> {
                var f = new File(p);
                try {
                    helper.addAttachment(f.getName(), f);
                } catch (MessagingException e) {
                   logger.error(e.getLocalizedMessage());
                }
            });
        }

        sender.send(msg);
    }
}
