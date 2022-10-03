package it.vitashop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Component;

import it.vitashop.security.model.ConfirmationToken;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class EmailSenderService {

	@Autowired
	private MailSender mailSender;
	
	@Value("${it.vitashop.hostname}")
	private String hostname;
	
	public void sendSimpleMessage(String to, ConfirmationToken confirmationToken) {
		log.info("Sending confirmation email to " + to + " with token " + confirmationToken.getConfirmationToken());
		SimpleMailMessage message = new SimpleMailMessage();
		message.setFrom("contatti@scientify.it");
		message.setTo(to);
		message.setSubject("Confirmation mail");
		message.setText("Here is your confirmation token\n"
				+ hostname + "/auth/verify?confirm=" + confirmationToken.getConfirmationToken());
		mailSender.send(message);
	}
}
