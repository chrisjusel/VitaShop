package it.vitashop.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class Customer extends User{
	
	private String name;
	
	private String lastName;
	
	private String phoneNumber;
	
	@OneToOne
	private BillingAddress billingAddress;
	
	@OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
	private List<ShippingAddress> shippingAddresses = new ArrayList<>();
	
	@OneToOne(cascade = CascadeType.PERSIST)
	private ShoppingCart shoppingCart = new ShoppingCart();
}
