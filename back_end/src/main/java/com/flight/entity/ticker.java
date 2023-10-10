package com.flight.entity;

import java.util.List;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class ticker extends base {
	
	@Column
	private int price;
	@Column
	private int price_sale;
	@Column
	private int quality;
	@Column
	private int status;
	
	
	@OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "flight_id", referencedColumnName = "id")
	private flight flight;

    @OneToMany(mappedBy="ticker")
    private Set<customer> customer;

}
