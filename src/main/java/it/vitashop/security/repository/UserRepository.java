package it.vitashop.security.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import it.vitashop.model.User;

public interface UserRepository extends JpaRepository<User, Long>{

	public Optional<User> findById(Long id);

	public Optional<User> findByUsername(String username);

	public boolean existsByEmail(String email);

	public boolean existsByUsername(String username);

}
