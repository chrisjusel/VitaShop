package it.vitashop.security.model;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserRequest {

	private String username;
	private String password;
	private String email;
	private List<String> roles = new ArrayList<>();
}
