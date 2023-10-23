package com.flight.entity;

import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class type_chair extends base {
	@Column
	@Min(value = 1000, message = "giá quá bé")
	@Max(value = 990000, message = "giá quá lớn")
	private int price;
	@Column
	@NotNull(message = "không bõ trống tên loại ghế")
	private String name;
	@Column
	private int luggage; 
	@OneToMany(mappedBy="type_chair")
	private Set<chair> chair;

}
