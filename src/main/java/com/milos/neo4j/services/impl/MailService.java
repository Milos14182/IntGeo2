package com.milos.neo4j.services.impl;

import java.util.HashMap;
import java.util.Map;

import javax.mail.internet.MimeMessage;

import org.apache.velocity.app.VelocityEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;
import org.springframework.ui.velocity.VelocityEngineUtils;

import com.milos.neo4j.data.ContactData;

@Service
public class MailService {

    @Autowired
    private VelocityEngine velocityEngine;
    @Autowired
    private JavaMailSender mailSender;

    public void sentMailSender(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    public void setVelocityEngine(VelocityEngine velocityEngine) {
        this.velocityEngine = velocityEngine;
    }

    public void contactUs(final ContactData contactData) {
        MimeMessagePreparator preparator = new MimeMessagePreparator() {

            public void prepare(MimeMessage mimeMessage) throws Exception {
                MimeMessageHelper message = new MimeMessageHelper(mimeMessage);
                message.setTo("stojanovic.milos14182@gmail.com");
                message.setFrom(contactData.getEmail());
                Map<String, Object> model = new HashMap<String, Object>();
                model.put("contactData", contactData);
                String text = VelocityEngineUtils.mergeTemplateIntoString(velocityEngine, "contactUsMail.vm",
                        "UTF-8", model);
                message.setSubject("Contact");
                message.setText(text, true);
            }
        };
        this.mailSender.send(preparator);
    }
}
