package it.vitashop.model.dto.customer;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.lang.NonNull;

import it.vitashop.model.Role;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CustomerRequest {

	@NonNull
	private String username;
	
	@NonNull
	private String password;
	
	@NonNull
	private String email;
	
	@NonNull
	private String name;
	
	@NonNull
	private String lastName;
	
	@NonNull
	private String phoneNumber;
	
	@NonNull
	private List<String> roles = new ArrayList<>();

}
