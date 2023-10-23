package com.flight.entity;

import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
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
public class trademark extends base {

	@NonNull
	@Column
	private String name;
	
	@NonNull
	@Column
	private String img;
	
	@OneToMany(mappedBy="trademark")
    private Set<airplane> airplane;
}
