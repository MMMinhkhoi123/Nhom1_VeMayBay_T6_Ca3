package com.flight.entity;

import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class airplane extends base {

	@Column
	private String name;
	@Column
	private String status;
	
	@Column 
	private int size;
	
	@OneToMany(mappedBy="airplane")
	private Set<chair> chair;
	
	@ManyToOne
	@JoinColumn(name="trademark_id", nullable=false)
	private trademark trademark;
	
	@OneToMany(mappedBy="airplane")
	private Set<flight> items;
	
}
