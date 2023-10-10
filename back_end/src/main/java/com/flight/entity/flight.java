package com.flight.entity;

import java.util.Date;
import java.util.Set;

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
public class flight extends base {
	
	@Column
   private String status;
	@Column
    private Date  date_departure;
	@Column
    private Date  date_complete;
	@Column
	private String timemove;

	@ManyToOne
    @JoinColumn(name="airplane_id", nullable=false)
	private airplane airplane;
    
    @OneToMany(mappedBy="flight")
    private Set<customer> customer;
    
    @ManyToOne
    @JoinColumn(name="location_id", nullable=false)
    private location location;
    
    @ManyToOne
    @JoinColumn(name="location_cl_id", nullable=false)
    private location location_complete;
    
    @ManyToOne
    @JoinColumn(name="airfield_id", nullable=false)
    private airfield airfield;
    

    @OneToOne(mappedBy = "flight")
    private ticker tickers;


    
    
    
}
