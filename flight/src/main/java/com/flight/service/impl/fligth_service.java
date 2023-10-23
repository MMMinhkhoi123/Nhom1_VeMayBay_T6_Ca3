package com.flight.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.flight.entity.airfield;
import com.flight.entity.airplane;
import com.flight.entity.flight;
import com.flight.entity.location;
import com.flight.reponse.flight_reponse;
import com.flight.reponse.getdata.airfield_data;
import com.flight.reponse.getdata.flight_data;
import com.flight.repository.airfield_repo;
import com.flight.repository.airplane_repo;
import com.flight.repository.flight_repo;
import com.flight.repository.location_repo;
import com.flight.request.flight_request;
import com.flight.service.I_flight;

@Service
public class fligth_service implements I_flight{

	
	@Autowired
	airplane_repo airplane_reponsetory;
	
	@Autowired
	location_repo location_reponsetory;
	
	@Autowired
	airfield_repo airfield_reponsetory;
	
	@Autowired 
	ticker_service tickerservice;
	
	@Autowired 
	flight_repo flight_reponsetory;
	
	
	@Override
	public ResponseEntity<flight_reponse> save(flight_request flights) {
		Date date_departure = new Date(flights.getDate_departure());
		Date date_complete = new Date(flights.getDate_complete());
		airfield airfield = airfield_reponsetory.findById(flights.getId_airfield()).get();
	    location location_departure = location_reponsetory.findById(flights.getId_location_departure()).get();
	    location location_complete = location_reponsetory.findById(flights.getId_location_complete()).get();
		airplane airplane = airplane_reponsetory.findById(flights.getId_airplane()).get();
		flight flight = new flight();
		flight.setAirfield(airfield);
		flight.setAirplane(airplane);
		flight.setLocation(location_departure);
		flight.setLocation_complete(location_complete);
		flight.setDate_departure(date_departure);
		flight.setDate_complete(date_complete);
		flight.setTimemove(flights.getTimemove());
		flight created =  flight_reponsetory.save(flight);
		//set status airplane
		airplane_reponsetory.update_action("action", flights.getId_airplane());
		//create ticker
		tickerservice.save(created.getId(), airplane.getSize(),flights.getPrice());
		flight_reponse reponse = new flight_reponse();
		reponse.setMess("create flight success");
		reponse.setStatus(true);
	    return new ResponseEntity<>(reponse,HttpStatus.OK);
	}

	@Override
	public ResponseEntity<flight_reponse> update(flight_request flights) {
		Date date_departure = new Date(flights.getDate_departure());
		Date date_complete = new Date(flights.getDate_complete());
		airfield airfield = airfield_reponsetory.findById(flights.getId_airfield()).get();
	    location location_departure = location_reponsetory.findById(flights.getId_location_departure()).get();
	    location location_complete = location_reponsetory.findById(flights.getId_location_complete()).get();
		airplane airplane = airplane_reponsetory.findById(flights.getId_airplane()).get();
		flight flight = new flight();
		flight.setAirfield(airfield);
		flight.setAirplane(airplane);
		flight.setLocation(location_departure);
		flight.setLocation_complete(location_complete);
		flight.setDate_departure(date_departure);
		flight.setDate_complete(date_complete);
		flight.setId(flights.getId());
		flight.setTimemove(flights.getTimemove());
		flight_reponsetory.save(flight);
		tickerservice.update(flights.getId(),airplane.getSize(),flights.getPrice(),flights.getSale());
		flight_reponse reponse = new flight_reponse();
		reponse.setMess("update flight success");
		reponse.setStatus(true);
	    return new ResponseEntity<>(reponse,HttpStatus.OK);
	}

	@Override
	public ResponseEntity<flight_data> get(int page, int limit) {
		flight_data result = new flight_data();
		result.setPage(page);
		Pageable data = new PageRequest(page -1,limit,Sort.by("id")) {
		};
		List<flight_request> data_array  = new ArrayList<flight_request>();
		flight_reponsetory.findAll(data).get().forEach((e) -> {
			flight_request value  = new flight_request();
				setdata(value,e);
				value.setPrice(e.getTickers().getPrice());
				data_array.add(value);	
		});
		result.setList(data_array);
		result.setToltalpage((int) Math.ceil((double) (flight_reponsetory.count()) / limit));
		return new ResponseEntity<>(result,HttpStatus.OK);
	}
	
	public void setdata(flight_request value,flight e) {
		value.setDate_complete(e.getDate_complete().getTime());
		value.setDate_departure(e.getDate_departure().getTime());
		value.setId(e.getId());
		value.setId_airfield(e.getAirfield().getId());
		value.setId_airplane(e.getAirplane().getId());
		value.setId_location_departure(e.getLocation().getId());
		value.setId_location_complete(e.getLocation_complete().getId());
		value.setIdtick(e.getTickers().getId());
		value.setTimemove(e.getTimemove());
	}

	@Override
	public ResponseEntity<List<flight_request>> get() {
		List<flight_request> data_array  = new ArrayList<flight_request>();
		flight_reponsetory.findAll().forEach((e) -> {
			flight_request value  = new flight_request();
				setdata(value,e);
				value.setPrice(e.getTickers().getPrice());
				data_array.add(value);	
		});
		return new ResponseEntity<>(data_array,HttpStatus.OK);
	}

	@Override
	public void delete(Long Id) {
		tickerservice.delete(Id);
		flight_reponsetory.deleteById(Id);
	}

}
