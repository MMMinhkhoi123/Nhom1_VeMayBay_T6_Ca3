package com.flight.request;

import java.util.List;
import java.util.Optional;

import com.flight.request.orther.sizetypechair;

public class airplane_request {
	
	private String name;
	Optional<List<sizetypechair>> array_type;
	private Long id;
	private Long id_trademark;
	public Long getId_trademark() {
		return id_trademark;
	}
	public void setId_trademark(Long id_trademark) {
		this.id_trademark = id_trademark;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Optional<List<sizetypechair>> getArray_type() {
		return array_type;
	}
	public void setArray_type(Optional<List<sizetypechair>> array_type) {
		this.array_type = array_type;
	}	
	
}
