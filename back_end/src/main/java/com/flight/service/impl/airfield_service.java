package com.flight.service.impl;


import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.flight.entity.airfield;
import com.flight.reponse.airflied_reponse;
import com.flight.reponse.getdata.airfield_data;
import com.flight.repository.airfield_repo;
import com.flight.request.airfield_request;
import com.flight.service.I_airfield;

@Service
public class airfield_service implements I_airfield{

	
	@Autowired
	airfield_repo airfield_reponsetory;
	
	@Override
	public ResponseEntity<airflied_reponse> save(airfield_request airfields) {
		airfield airfieldx = new airfield();
		airfieldx.setAddress(airfields.getAdrress());
		airfieldx.setContry(airfields.getContry());
		airfieldx.setName(airfields.getName());
		airflied_reponse reponse = new airflied_reponse();
		airfield_reponsetory.save(airfieldx);
		reponse.setMess("create airfield success");
		reponse.setStatus(true);
	    return new ResponseEntity<> (reponse,HttpStatus.OK);
	}

	@Override
	public ResponseEntity<airflied_reponse> remove(Long id) {
		airflied_reponse reponse = new airflied_reponse();
		airfield_reponsetory.deleteById(id);
		reponse.setMess("delete airfield success");
    	reponse.setStatus(true);
		return new ResponseEntity<> (reponse,HttpStatus.OK);
	}
	
	@Override
	public ResponseEntity<airfield_data> get(int page, int limit, String key) {
		airfield_data result = new airfield_data();
		result.setPage(page);
		Pageable data = new PageRequest(page -1,limit,Sort.by("id")) {
		};
		List<airfield_request> data_array = new ArrayList<airfield_request>();
		airfield_reponsetory.findAll(data).getContent().forEach((e) -> {
			airfield_request value = new airfield_request();
			if(!key.equalsIgnoreCase("")) {
				if(convertUpcase(e.getName()).contains(convertUpcase(key))) {
					value.setAdrress(e.getAddress());
					value.setContry(e.getContry());
					value.setName(e.getName());
					value.setId(e.getId());
					data_array.add(value);
				}
			}else {
			value.setAdrress(e.getAddress());
			value.setContry(e.getContry());
			value.setName(e.getName());
			value.setId(e.getId());
			data_array.add(value);
			}
		});
		result.setList(data_array);
		result.setToltalpage((int) Math.ceil((double) (airfield_reponsetory.count()) / limit));
		return new ResponseEntity<>(result,HttpStatus.OK);
	}
	
	public String convertUpcase(String data) {
		return data.toUpperCase();
	}
	
	@Override
	public ResponseEntity<List<airfield_request>> get() {
		List<airfield_request> data_array = new ArrayList<airfield_request>();
		airfield_reponsetory.findAll().forEach((e) -> {
			airfield_request value = new airfield_request();
					value.setAdrress(e.getAddress());
					value.setContry(e.getContry());
					value.setName(e.getName());
					value.setId(e.getId());
					data_array.add(value);
		});
		return new ResponseEntity<>(data_array,HttpStatus.OK);
	}

}
