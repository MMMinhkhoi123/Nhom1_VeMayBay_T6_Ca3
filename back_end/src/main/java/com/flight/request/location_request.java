package com.flight.request;

public class location_request {

	private String name;
	private String img;
	private String contry;
	private Long id;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getImg() {
		return img;
	}
	public void setImg(String img) {
		this.img = img;
	}
	public String getContry() {
		return contry;
	}
	public void setContry(String contry) {
		this.contry = contry;
	}
	public location_request(String name, String img, String contry) {
		super();
		this.name = name;
		this.img = img;
		this.contry = contry;
	}
	public location_request() {
		super();
	}
	
}
