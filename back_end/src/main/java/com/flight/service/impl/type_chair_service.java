package com.flight.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.flight.entity.type_chair;
import com.flight.reponse.type_chair_reponse;
import com.flight.reponse.getdata.typechair_data;
import com.flight.repository.type_chair_repo;
import com.flight.request.type_chair_request;
import com.flight.service.I_type_chair;

@Service
public class type_chair_service implements I_type_chair {

	
	@Autowired
	type_chair_repo type_chaireponsetory;
	
	@Override
	public ResponseEntity<type_chair_reponse> save(type_chair_request chair) {
		type_chair tyeChair  = new type_chair();
		tyeChair.setLuggage(chair.getLuggage());
	    tyeChair.setName(chair.getName());
	    tyeChair.setPrice(chair.getPrice());
	    type_chair_reponse reponse = new type_chair_reponse();
	    type_chaireponsetory.save(tyeChair);
	    reponse.setMess("create type chair success");
	    reponse.setStatus(true);
	    return new ResponseEntity<> (reponse,HttpStatus.OK);
	}

	@Override
	public ResponseEntity<type_chair_reponse> remove(Long id) {
		type_chair_reponse reponse = new type_chair_reponse();
		  type_chaireponsetory.deleteById(id);
		reponse.setMess("delete type chair success");
	    reponse.setStatus(true);
		return new ResponseEntity<> (reponse,HttpStatus.OK);
	}

	@Override
	public ResponseEntity<List<typechair_data>> get() {
		List<typechair_data> list_reponse = new ArrayList<typechair_data>();
		type_chaireponsetory.findAll().forEach((e) -> {
			typechair_data data = new typechair_data();
			data.setId(e.getId());
			data.setLuggage(e.getLuggage());
			data.setName(e.getName());
			data.setPrice(e.getPrice());
			list_reponse.add(data);
		});
		return new ResponseEntity<>(list_reponse, HttpStatus.OK);
	}


}
