package com.flight.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import java.io.Console;
import org.springframework.data.domain.Sort;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.flight.entity.location;
import com.flight.reponse.location_reponse;
import com.flight.reponse.getdata.location_data;
import com.flight.repository.location_repo;
import com.flight.request.location_request;
import com.flight.service.I_location;

@Service
public class location_service implements I_location {

	
	@Autowired
	location_repo location_reponsetory;
	
	@Override
	public ResponseEntity<location_reponse> save(location_request locattionx) {
		location locaions = new location();
		locaions.setImg(locattionx.getImg());
		locaions.setName_contry(locattionx.getContry());
		locaions.setName(locattionx.getName());
	    location_reponse reponse =  new location_reponse();
	    location_reponsetory.save(locaions);
	    reponse.setMess("create location success");
	    reponse.setStatus(true);
		return new ResponseEntity<> (reponse,HttpStatus.OK);
	}

	@Override
	public ResponseEntity<location_reponse> remove(Long id) {
	    location_reponse reponse =  new location_reponse();
	    location_reponsetory.deleteById(id);
	    reponse.setMess("delete location success");
    	reponse.setStatus(true);
		return new ResponseEntity<> (reponse,HttpStatus.OK);
	}

	public String convertUpcase(String data) {
		return data.toUpperCase();
	}
	
	@Override
	public ResponseEntity<location_data> get(int page, int limit, String key) {
		location_data result = new location_data();
		result.setPage(page);
		Pageable data = new PageRequest(page -1,limit,Sort.by("id")) {
		};
		List<location_request> data_array = new ArrayList<location_request>();
		location_reponsetory.findAll(data).getContent().forEach((e) -> {
			location_request value = new location_request();
			if(!key.equalsIgnoreCase("")) {
				if(convertUpcase(e.getName()).contains(convertUpcase(key))) {
					value.setContry(e.getName_contry());
					value.setImg(e.getImg());
					value.setId(e.getId());
					value.setName(e.getName());
					data_array.add(value);
				}
			}else {
			value.setContry(e.getName_contry());
			value.setImg(e.getImg());
			value.setId(e.getId());
			value.setName(e.getName());
			data_array.add(value);
			}
		});
		result.setList(data_array);
		result.setToltalpage((int) Math.ceil((double) (location_reponsetory.count()) / limit));
		return new ResponseEntity<>(result,HttpStatus.OK);
	}

	@Override
	public ResponseEntity<List<location_request>> get() {
		List<location_request> data_array = new ArrayList<location_request>();
		location_reponsetory.findAll().forEach((e) -> {
			location_request value = new location_request();
					value.setContry(e.getName_contry());
					value.setImg(e.getImg());
					value.setId(e.getId());
					value.setName(e.getName());
					data_array.add(value);

		});
		return new ResponseEntity<>(data_array,HttpStatus.OK);
	}




	
}
