package com.flight.entity;

import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Null;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
@NoArgsConstructor
public class airfield extends base {

	@NonNull
	@Column
	private String name;
	@NonNull
	@Column
	private String address;
	@NonNull
	@Column
	private String contry;
	
	 @OneToMany(mappedBy="airfield")
	 private Set<flight> flights;
	 
}
