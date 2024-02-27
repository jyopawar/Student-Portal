package com.student.studentportal.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import jakarta.mail.internet.MimeMessage;

@Component
public class EmailUtils {

	@Autowired
	private JavaMailSender javaMail;

	public boolean sendMail(String subject, String body, String to) {

		boolean isSent = false;

		try {

			MimeMessage mimeMsg = javaMail.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(mimeMsg);
			helper.setTo(to);
			helper.setSubject(subject);
			helper.setText(body, true);
			javaMail.send(mimeMsg);
			isSent = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return isSent;

	}

}
