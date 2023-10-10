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
public class type_chair extends base {

	@Column
	private int price;
	@Column
	private String name;
	@Column
	private int luggage; 
	
	@OneToMany(mappedBy="type_chair")
	private Set<chair> chair;
	
	
}
