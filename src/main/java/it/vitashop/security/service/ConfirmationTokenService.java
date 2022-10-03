package it.vitashop.security.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.vitashop.exception.ConfirmationException;
import it.vitashop.exception.ConfirmationTokenNotFoundException;
import it.vitashop.model.User;
import it.vitashop.security.model.ConfirmationToken;
import it.vitashop.security.repository.ConfirmationTokenRepository;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ConfirmationTokenService {

	@Autowired
	private ConfirmationTokenRepository confirmationTokenRepository;

	@Autowired
	private UserDetailsServiceImpl userDetailsServiceImpl;

	public ConfirmationToken createConfirmationToken(User user) {
		log.info("Generating confirmation token...");
		ConfirmationToken confirmationToken = new ConfirmationToken(user);
		log.info("Confirmation token generated");
		return confirmationTokenRepository.save(confirmationToken);
	}

	private Optional<ConfirmationToken> findByConfirmationToken(String confirmationToken) {
		log.info("Recovering user with confirmation token: " + confirmationToken);
		Optional<ConfirmationToken> confirmationTokenResult = confirmationTokenRepository
				.findByConfirmationToken(confirmationToken);

		if (confirmationTokenResult.isPresent()) {
			log.info("User with confirmation token " + confirmationToken + " recovered");
			return confirmationTokenResult;
		} else {
			throw new ConfirmationTokenNotFoundException("No confirmation tokens are found");
		}
	}

	public boolean confirmToken(String confirmationToken) {
		log.info("Confirming user with token " + confirmationToken);
		ConfirmationToken confirmationTokenResult = findByConfirmationToken(confirmationToken).get();
		Optional<User> user = confirmationTokenRepository.findUserByConfirmationToken(confirmationToken);
		if (user.isPresent()) {
			userDetailsServiceImpl.enable(user.get());
			return true;
		} else {
			throw new ConfirmationException("Confirmation failed");
		}
	}
}
