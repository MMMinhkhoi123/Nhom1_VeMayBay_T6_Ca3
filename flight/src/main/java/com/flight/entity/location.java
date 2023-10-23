package com.flight.entity;

import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
@NoArgsConstructor
public class location extends base {

	@NonNull
	@Column
	private String name;
	@NonNull
	@Column
	private String img;
	@NonNull
	@Column
	private String name_contry;
	
	@OneToMany(mappedBy="location")
    private Set<flight> flights;
    
    @OneToMany(mappedBy="location_complete")
    private Set<flight> flights_complate;
	
}
