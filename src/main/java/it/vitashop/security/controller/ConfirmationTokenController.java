package it.vitashop.security.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import it.vitashop.exception.ConfirmationException;
import it.vitashop.security.service.ConfirmationTokenService;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/auth")
@Slf4j
public class ConfirmationTokenController {

	@Autowired
	private ConfirmationTokenService confirmationTokenService;

	@GetMapping("/verify")
	public ResponseEntity<?> confirmToken(@RequestParam String confirm) {
		log.info("New '" + new Object() {
		}.getClass().getEnclosingMethod().getName() + "' request to " + this.getClass().getName());
		if (confirmationTokenService.confirmToken(confirm)) {
			return new ResponseEntity<>(HttpStatus.ACCEPTED);
		} else {
			throw new ConfirmationException("Confirmation failed");
		}
	}
}
