package com.flight.request;

public class airfield_request {

	private String  name;
	private String adrress;
	private String contry;
	private Long id;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAdrress() {
		return adrress;
	}
	public void setAdrress(String adrress) {
		this.adrress = adrress;
	}
	public String getContry() {
		return contry;
	}
	public void setContry(String contry) {
		this.contry = contry;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public airfield_request(String name, String adrress, String contry, Long id) {
		super();
		this.name = name;
		this.adrress = adrress;
		this.contry = contry;
		this.id = id;
	}
	public airfield_request() {
		super();
	}
	
}
