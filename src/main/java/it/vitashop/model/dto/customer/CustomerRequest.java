package it.vitashop.model.dto.customer;

import org.springframework.lang.NonNull;

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
}
