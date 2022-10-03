package it.vitashop.security.service;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.vitashop.exception.UserNotFoundException;
import it.vitashop.model.User;
import it.vitashop.security.model.ConfirmationToken;
import it.vitashop.security.repository.UserRepository;
import it.vitashop.service.EmailSenderService;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private ConfirmationTokenService confirmationTokenService;
	
	@Autowired
	private EmailSenderService emailSenderService;

	public User save(User user) {
		log.info("Saving new user " + user.getEmail());
		User response = userRepository.save(user);
		ConfirmationToken confirmationToken = confirmationTokenService.createConfirmationToken(response);
		emailSenderService.sendSimpleMessage(user.getEmail(), confirmationToken);
		return response;
	}
	
}
