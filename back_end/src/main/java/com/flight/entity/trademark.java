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
public class trademark extends base {

	@Column
	private String name;
	@Column
	private String img;
	
	@OneToMany(mappedBy="trademark")
    private Set<airplane> airplane;
}
