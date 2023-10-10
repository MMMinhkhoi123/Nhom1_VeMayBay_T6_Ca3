package com.flight.entity;

import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class airfield extends base {

	@Column
	private String name;
	@Column
	private String address;
	@Column
	private String contry;
	
	 @OneToMany(mappedBy="airfield")
	 private Set<flight> flights;
	 
}
