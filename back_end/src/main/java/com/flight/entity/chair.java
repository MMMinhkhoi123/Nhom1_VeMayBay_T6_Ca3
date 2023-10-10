package com.flight.entity;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class chair extends base {

	@Column
	private String stt;
	@Column
	private String status;
	@Column
	private String side;
	
	@ManyToOne
    @JoinColumn(name="airplane_id", nullable=false)
    private airplane airplane;
	
	@ManyToOne
    @JoinColumn(name="typechair_id", nullable=false)
    private type_chair type_chair;
	
	@OneToOne(mappedBy = "chair")
	 private customer customer;

	
	
	
}
