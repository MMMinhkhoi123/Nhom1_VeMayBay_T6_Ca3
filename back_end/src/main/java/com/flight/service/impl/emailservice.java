package com.flight.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import com.flight.reponse.email_reponse;
import com.flight.reponse.email_reponsev2;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;


@Service
public class emailservice {

	@Autowired
	private JavaMailSender mailsender;
	
	@Autowired
	private TemplateEngine tyEngine;
	public void sendEmail(String to, String subject, String body) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject(subject);
        message.setText(body);
        mailsender.send(message);
    }
	
	public void sendEmail_html(String to, String subject, email_reponsev2 array) {
        Context ctx = new Context();
        ctx.setVariable("code",  array.getCode());
        ctx.setVariable("sumprice", array.getSumprice());
        ctx.setVariable("list", array.getList());
        ctx.setVariable("names", array.getNamedata());
        ctx.setVariable("datebuy", array.getDateby());
        MimeMessage mess = mailsender.createMimeMessage();
        
        // true = mui mess
        try {
        	
			MimeMessageHelper hepler = new MimeMessageHelper(mess, false, "UTF-8");
			
			hepler.setTo(to);
			hepler.setSubject(subject);
			hepler.setText("Helo cac ban", false);

	        String htmlContent = tyEngine.process("einvoice2", ctx);
	        
	        htmlContent.replace("${foo}","minhkhoi" );
	        mess.setContent(htmlContent, "text/html; charset=utf-8");
	        hepler.setText(htmlContent, true); // true = isHtml
			mailsender.send(mess);
			
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
    }
	
}
