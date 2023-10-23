package com.flight.entity;

import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "parent_bill")
@Getter
@Setter
public class parent_einvoice extends  base {
	@Column
	private int sumprice;
	@Column
	private String status;
	private Long iduser;
	private String codepay;
	@OneToMany(mappedBy="parent_einvoices")
	private Set<customer> customers;
	
	  
}
