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

import com.flight.entity.trademark;
import com.flight.reponse.trademark_reponse;
import com.flight.reponse.getdata.airfield_data;
import com.flight.reponse.getdata.trademark_data;
import com.flight.repository.trademark_reponsetory;
import com.flight.request.airfield_request;
import com.flight.request.trademark_request;
import com.flight.service.I_trademark;

@Service
public class trademark_service implements I_trademark {

	@Autowired
	trademark_reponsetory trademark_reponsesitory;

	@Override
	public ResponseEntity<trademark_reponse> save(trademark_request trademarks) {
		trademark trademark = new trademark();
		trademark.setImg(trademarks.getImg());
		trademark.setName(trademarks.getName());
	    trademark_reponse  reponse =  new trademark_reponse();
	    trademark_reponsesitory.save(trademark);
	    reponse.setMess("create trademark success");
	    reponse.setStatus(true);
		return new ResponseEntity<> (reponse,HttpStatus.OK);
	}

	@Override
	public ResponseEntity<trademark_reponse> remove(Long id) {
		 trademark_reponse  reponse =  new trademark_reponse();
		 trademark_reponsesitory.deleteById(id);
		 reponse.setMess("delete trademark success");
	     reponse.setStatus(true);
		return new ResponseEntity<> (reponse,HttpStatus.OK);
	}


	public String convertUpcase(String data) {
		return data.toUpperCase();
	}
	
	@Override
	public ResponseEntity<trademark_data> get(int page, int limit, String key) {
		trademark_data result = new trademark_data();
		result.setPage(page);
		Pageable data = new PageRequest(page -1,limit,Sort.by("id")) {
		};
		List<trademark_request> data_array = new ArrayList<trademark_request>();
		trademark_reponsesitory.findAll(data).getContent().forEach((e) -> {
			trademark_request value = new trademark_request();
			if(!key.equalsIgnoreCase("")) {
				if(convertUpcase(e.getName()).contains(convertUpcase(key))) {
					value.setImg(e.getImg());
			    	value.setName(e.getName());
			    	value.setId(e.getId());
					data_array.add(value);
				}
			} else {
			value.setImg(e.getImg());
	    	value.setName(e.getName());
	    	value.setId(e.getId());
			 data_array.add(value);
			}
		});
		result.setList(data_array);
		result.setToltalpage((int) Math.ceil((double) (trademark_reponsesitory.count()) / limit));
		return new ResponseEntity<>(result,HttpStatus.OK);
	}

	
	
	
	@Override
	public ResponseEntity<List<trademark_request>> get() {
		List<trademark_request> data_array = new ArrayList<trademark_request>();
		trademark_reponsesitory.findAll().forEach((e) -> {
			trademark_request value = new trademark_request();
			value.setImg(e.getImg());
	    	value.setName(e.getName());
	    	value.setId(e.getId());
			data_array.add(value);
		});
		return new ResponseEntity<>(data_array,HttpStatus.OK);
	}
	
	
}
