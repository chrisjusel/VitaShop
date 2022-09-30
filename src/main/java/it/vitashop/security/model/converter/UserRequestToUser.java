package it.vitashop.security.model.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Component;

import it.vitashop.exception.RoleNotFoundException;
import it.vitashop.model.Role;
import it.vitashop.model.Roles;
import it.vitashop.model.User;
import it.vitashop.security.model.UserRequest;
import it.vitashop.security.service.RoleService;

@Component
public class UserRequestToUser implements Converter<UserRequest, User>{

	@Autowired
	private RoleService roleService;

	@Override
	public User convert(UserRequest userDto) {
		User userRes = new User();
		userRes.setUsername(userDto.getUsername());
		userRes.setEmail(userDto.getEmail());
		userRes.setPassword(BCrypt.hashpw(userDto.getPassword(), BCrypt.gensalt()));
		/**
		 * se la lista non è vuota e dall'esterno arriva il nome di un ruolo che esiste
		 * esso viene convertito in un'oggetto di tipo ruolo ed associato all'utente
		 * che verrà restituito
		 */
		if (!userDto.getRoles().isEmpty()) {
			for (String role : userDto.getRoles()) {
				try {
					Roles roleToSave = Roles.valueOf(role);
					Role newRole = roleService.findByRoleName(roleToSave);
					userRes.getRoles().add(newRole);

				} catch (Exception e) {
					throw new RoleNotFoundException("Role " + role + " not found");
				}
			}
		} else {
			/**
			 * Se la lista di ruoli è vuota, viene impostato il ruolo di default a null
			 */
			Role newRole = roleService.findByRoleName(Roles.ROLE_USER);
			userRes.getRoles().add(newRole);
		}
		return userRes;
	}

}
