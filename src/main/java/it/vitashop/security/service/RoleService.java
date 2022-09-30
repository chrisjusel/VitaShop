/**
 * Classe di servizio per il salvataggio ed il recupero di un ruolo
 */
package it.vitashop.security.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.vitashop.model.Role;
import it.vitashop.model.Roles;
import it.vitashop.security.repository.RoleRepository;

@Service
public class RoleService {

	@Autowired
	private RoleRepository roleRepository;
	
	public void save(Role role) {
		roleRepository.save(role);
	}
	
	public Role findByRoleName(Roles roleName) {
		Optional<Role> role = roleRepository.findByRoleName(roleName);
		if(role.isPresent())
			return role.get();
		else
			return null;
	}
}
