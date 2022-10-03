package it.vitashop.security.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import it.vitashop.model.User;
import it.vitashop.security.model.ConfirmationToken;

public interface ConfirmationTokenRepository extends JpaRepository<ConfirmationToken, Long> {

	Optional<ConfirmationToken> findByConfirmationToken(String confirmationToken);

	@Query("select u from User u inner join ConfirmationToken c on u.id = c.user.id where c.confirmationToken = :confirmationToken")
	Optional<User> findUserByConfirmationToken(String confirmationToken);
}
