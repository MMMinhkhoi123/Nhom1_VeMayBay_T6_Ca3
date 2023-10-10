package com.flight.reponse.getdata;

import java.util.List;

import com.flight.request.flight_request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ticker_data  {
	private Long id;
	private int quality;
	private Long idflight;
	private int status;
	private int price;
	private Long trademark;
	private String timemove;
	private Long time_departure;
	private Long time_complete;
	private Long location_departure;
	private List<chair_data> listchair;
	private Long location_complete;
	private Long id_airfield;
	private List<Long> typechair;
	private Long id_airplane;
	
	
}
