package com.flight.entity;


import jakarta.persistence.CascadeType;
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
public class customer extends base{

	@Column
	private String fullname;
	@Column
	private String age;
	@Column
	private String email;
	@Column
	private String phone;
	@Column
	private String passport;
	@Column
	private String nationality;


	@OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "chair_id", referencedColumnName = "id")
    private chair chair;
	
	@ManyToOne
	@JoinColumn(name="flight_id", nullable=false)
	 private flight flight;
	
	@ManyToOne
    @JoinColumn(name="ticker_id", nullable=false)
    private ticker ticker;

	@ManyToOne
	@JoinColumn(name="parent_id", nullable=false)
	private parent_einvoice parent_einvoices;
	
}
