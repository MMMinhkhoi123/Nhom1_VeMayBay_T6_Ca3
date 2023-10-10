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
public class location extends base {

	@Column
	private String name;
	@Column
	private String img;
	@Column
	private String name_contry;
	
	@OneToMany(mappedBy="location")
    private Set<flight> flights;
    
    @OneToMany(mappedBy="location_complete")
    private Set<flight> flights_complate;
	
}
