package it.vitashop.security.model;

import java.util.Date;
import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import it.vitashop.model.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class ConfirmationToken {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long tokenId;

	private String confirmationToken;

	@Temporal(TemporalType.TIMESTAMP)
	private Date creationDate;

	@OneToOne(targetEntity = User.class)
	@JoinColumn
	private User user;

	public ConfirmationToken(User user) {
		this.user = user;
		creationDate = new Date();
		confirmationToken = UUID.randomUUID().toString();
	}
}
