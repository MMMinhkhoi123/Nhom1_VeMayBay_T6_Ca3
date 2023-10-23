package com.flight.entity;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
public class user  extends base {

	@Column
	private String fullname;
	@Column
	private String phone;
	@Column(unique = true)
	private String email;
	@Column
	private String password;
	
	@ManyToMany
	@JoinTable(
	name = "users_roles", 
    joinColumns = @JoinColumn(name = "user_id"), 
	inverseJoinColumns = @JoinColumn(name = "role_id"))
	private Set<role> roles = new HashSet<role>();
	
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "jwt_id", referencedColumnName = "id")
    private auth_jwt auth;  
}
