package com.flight.request;

public class flight_request {

	private Long date_departure;
	private Long date_complete;
	private Long id;
	private Long id_airfield;
	private Long id_airplane;
	private Long id_location_complete;
	private Long id_location_departure;
	private int price;
	private String timemove;
	
	public String getTimemove() {
		return timemove;
	}
	public void setTimemove(String timemove) {
		this.timemove = timemove;
	}
	private int sale;
	private Long idtick;
	public flight_request(Long date_departure, Long date_complete, Long id, Long id_airfield, Long id_airplane,
			Long id_location_complete, Long id_location_departure, int price, int sale, Long idtick) {
		super();
		this.date_departure = date_departure;
		this.date_complete = date_complete;
		this.id = id;
		this.id_airfield = id_airfield;
		this.id_airplane = id_airplane;
		this.id_location_complete = id_location_complete;
		this.id_location_departure = id_location_departure;
		this.price = price;
		this.sale = sale;
		this.idtick = idtick;
	}
	public Long getDate_departure() {
		return date_departure;
	}
	public void setDate_departure(Long date_departure) {
		this.date_departure = date_departure;
	}
	public Long getDate_complete() {
		return date_complete;
	}
	public void setDate_complete(Long date_complete) {
		this.date_complete = date_complete;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getId_airfield() {
		return id_airfield;
	}
	public void setId_airfield(Long id_airfield) {
		this.id_airfield = id_airfield;
	}
	public Long getId_airplane() {
		return id_airplane;
	}
	public void setId_airplane(Long id_airplane) {
		this.id_airplane = id_airplane;
	}
	public Long getId_location_complete() {
		return id_location_complete;
	}
	public void setId_location_complete(Long id_location_complete) {
		this.id_location_complete = id_location_complete;
	}
	public Long getId_location_departure() {
		return id_location_departure;
	}
	public void setId_location_departure(Long id_location_departure) {
		this.id_location_departure = id_location_departure;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public int getSale() {
		return sale;
	}
	public void setSale(int sale) {
		this.sale = sale;
	}
	public Long getIdtick() {
		return idtick;
	}
	public void setIdtick(Long idtick) {
		this.idtick = idtick;
	}
	public flight_request() {
		super();
	}
	
	
	
}
