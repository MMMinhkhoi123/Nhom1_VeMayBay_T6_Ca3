package com.flight.entity;

import java.util.Set;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class role extends base{
	
	@Column
	private String name;
	
	@ManyToMany(mappedBy = "roles")
	Set<user> users;
}
