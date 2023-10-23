package com.flight.entity;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class auth_jwt extends base {

	@Column
	private String accToken;

	@Column
	private Long expiration;
	
	@OneToOne(mappedBy = "auth")
	private user user;
	
}
