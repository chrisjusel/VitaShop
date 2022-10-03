package it.vitashop.security.service;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import it.vitashop.exception.UserNotFoundException;
import it.vitashop.model.Customer;
import it.vitashop.model.User;
import it.vitashop.security.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;
	
	@Override
	@Transactional
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<User> user = userRepository.findByUsername(username);
		
		if(user.isPresent()) {
			return UserDetailsImpl.build(user.get());
		} else {
			throw new UsernameNotFoundException("User not found with username: " + username);
		}
	}
	
	@Transactional
	public User enable(User user) {
		log.info("Enabiling user...");
		Optional<User> userResponse = userRepository.findById(user.getId());
		if(userResponse.isPresent()) {
			log.info("Enabling user " + userResponse.get().getEmail());
			User updatedUser = userResponse.get();
			updatedUser.setActive(true);
			log.info("User enabled");
			return updatedUser;
		} else throw new UserNotFoundException("No user found");
	}

}
