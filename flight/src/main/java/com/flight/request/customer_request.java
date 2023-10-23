package com.flight.request;

public class customer_request {

	private  Long id;
	private String nationality;
	private String passport;
	private String email;
	private String fullname;
	private String age;
	private String statuspayment;
	private Long idtick;
	private String tel;
	private Long seat;
	
	


	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public Long getSeat() {
		return seat;
	}
	public void setSeat(Long seat) {
		this.seat = seat;
	}
	public Long getIdtick() {
		return idtick;
	}
	public void setIdtick(Long idtick) {
		this.idtick = idtick;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNationality() {
		return nationality;
	}
	public void setNationality(String nationality) {
		this.nationality = nationality;
	}
	public String getPassport() {
		return passport;
	}
	public void setPassport(String passport) {
		this.passport = passport;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getFullname() {
		return fullname;
	}
	public void setFullname(String fullname) {
		this.fullname = fullname;
	}
	public String getAge() {
		return age;
	}
	public void setAge(String age) {
		this.age = age;
	}
	public String getStatuspayment() {
		return statuspayment;
	}
	public void setStatuspayment(String statuspayment) {
		this.statuspayment = statuspayment;
	}
	public customer_request(Long id, String nationality, String passport, String email, String fullname, String age,
			String statuspayment, Long idtick, Long seat) {
		super();
		this.id = id;
		this.nationality = nationality;
		this.passport = passport;
		this.email = email;
		this.fullname = fullname;
		this.age = age;
		this.statuspayment = statuspayment;
		this.idtick = idtick;
		this.seat = seat;
	}
	public customer_request() {
		super();
	}
	
	
}
