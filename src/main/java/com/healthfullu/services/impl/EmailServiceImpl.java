package com.healthfullu.services.impl;

import java.io.UnsupportedEncodingException;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.healthfullu.data.model.Email;
import com.healthfullu.data.model.User;
import com.healthfullu.services.EmailService;
import com.healthfullu.services.PropertyService;

@Service("emailService")
public class EmailServiceImpl implements EmailService {

	@Autowired
	private PropertyService propertyService;

	@Override
	public void notifyPasswordReset(User user, String password) {
	}

	@Override
	public void sendEmail(Email email) {
		String[] recipients = new String[] { email.getTo() };
		try {
			InternetAddress addressFrom = new InternetAddress(email.getFrom(),
					email.getSenderName(), "UTF-8");
			InternetAddress[] addressTo = new InternetAddress[recipients.length];
			for (int i = 0; i < recipients.length; i++) {
				addressTo[i] = new InternetAddress(recipients[i],
						email.getReceiverName(), "UTF-8");
			}
			sendOff(addressFrom, addressTo, email.getSubject(), email.getBody());
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void sendEmails(Email[] email) {
		// TODO: to be implemented.
	}

	private void sendOff(InternetAddress addressFrom,
			InternetAddress[] addressTo, String subject, String message) {
		Properties props = System.getProperties();
		props.put("mail.smtp.host",
				propertyService.getProperty(PropertyService.EMAIL_SERVER_IP));
		props.put("mail.smtp.starttls.enable", "true");
		Session session = Session.getDefaultInstance(props, null);
		session.setDebug(false);

		try {
			MimeMessage msg = new MimeMessage(session);
			msg.setHeader("Content-Type", "text/html; charset=UTF-8");
			msg.setFrom(addressFrom);
			msg.setRecipients(Message.RecipientType.TO, addressTo);
			msg.setSubject(subject, "UTF-8");
			msg.setContent(message, "text/html; charset=UTF-8");

			// Send the message
			Transport.send(msg);
		} catch (MessagingException me) {
			throw new RuntimeException(me);
		}
	}
}