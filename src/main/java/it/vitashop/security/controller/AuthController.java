/**
 * Questo controller espone i metodi di login e registrazione
 */
package it.vitashop.security.controller;

import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import it.vitashop.exception.AccountNotEnabledException;
import it.vitashop.model.User;
import it.vitashop.security.jwt.JwtUtils;
import it.vitashop.security.model.LoginRequest;
import it.vitashop.security.model.LoginResponse;
import it.vitashop.security.model.UserRequest;
import it.vitashop.security.service.UserDetailsImpl;
import it.vitashop.security.service.UserService;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/auth")
@Slf4j
//@CrossOrigin(origins = "http://localhost:4200")
public class AuthController {

	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	private UserService userService;

	@Autowired
	private ConversionService conversionService;

	@Autowired
	JwtUtils jwtUtils;

	/**
	 * In questo metodo viene generato e restituito il token all'interno del model
	 * di risposta creato in precedenza
	 * 
	 * @param loginRequest
	 * @return
	 */
	@PostMapping("/login")
	public ResponseEntity<?> authenticateUser(@RequestBody LoginRequest loginRequest) {
		log.info("New '" + new Object() {
		}.getClass().getEnclosingMethod().getName() + "' request to " + this.getClass().getName());
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

		SecurityContextHolder.getContext().setAuthentication(authentication);
		String token = jwtUtils.generateJwtToken(authentication);

		UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
		if (userDetails.isEnabled()) {
			List<String> roles = userDetails.getAuthorities().stream().map(item -> item.getAuthority())
					.collect(Collectors.toList());

			LoginResponse loginResponse = new LoginResponse();

			loginResponse.setToken(token);
			loginResponse.setRoles(roles);

			return ResponseEntity.ok(loginResponse);
		} else
			throw new AccountNotEnabledException("You have to activate your account first");
	}

	/**
	 * Salvataggio di un nuovo utente sul db
	 * 
	 * @param userDto
	 * @return
	 */
	@PostMapping("/register")
	public ResponseEntity<User> save(@RequestBody UserRequest userRequest) {
		log.info("New '" + new Object() {
		}.getClass().getEnclosingMethod().getName() + "' request to " + this.getClass().getName());
		User user = conversionService.convert(userRequest, User.class);
		User res = userService.save(user);
		return new ResponseEntity<User>(res, HttpStatus.OK);
	}

	@GetMapping
	public ResponseEntity<Principal> getUserDetails(Principal principal) {
		return new ResponseEntity<>(principal, HttpStatus.OK);
	}
}
