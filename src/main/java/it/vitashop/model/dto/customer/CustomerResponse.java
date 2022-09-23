package it.vitashop.model.dto.customer;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CustomerResponse {

	private Long id;
	private String username;
	private String name;
	private String lastName;
	private String phoneNUmber;
}
