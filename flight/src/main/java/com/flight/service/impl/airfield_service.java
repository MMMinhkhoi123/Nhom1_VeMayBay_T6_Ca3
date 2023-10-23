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
		int status = airfields.getId() != null ? 1 : 0;
		String mess = airfields.getId() != null ? "Cập nhật thành công" : "Tạo thành công";
		airfield airfieldx = new airfield(
				airfields.getName(),
				airfields.getAdrress(),
				airfields.getContry());

		airfieldx.setId(airfields.getId() != null ? airfields.getId() : null);
		airfield_reponsetory.save(airfieldx);
	    return new ResponseEntity<> (airflied_reponse.builder()
	    		.mess(mess)
	    		.status(status)
	    		.build()
	    	,HttpStatus.OK);
	}

	@Override
	public ResponseEntity<airflied_reponse> remove(Long id) {
		 airfield_reponsetory.deleteById(id);
    	 return new ResponseEntity<> (airflied_reponse.builder()
 	    		.mess("Xóa Thành công")
 	    		.status(2)
 	    		.build()
 	    	,HttpStatus.OK);
 	}
	
	@Override
	public ResponseEntity<airfield_data> get(int page, int limit, String key) {
		airfield_data result = new airfield_data();
		result.setPage(page);
		Pageable data = new PageRequest(page -1,limit,Sort.by("id")) {};
		List<airfield_request> data_array = new ArrayList<airfield_request>();
		airfield_reponsetory.findAll(data).getContent().forEach((e) -> {
			if(!key.equalsIgnoreCase("")) {
				if(convertUpcase(e.getName()).contains(convertUpcase(key))) {
					data_array.add(airfield_request.builder()
							.adrress(e.getAddress())
							.contry(e.getContry())
							.name(e.getName())
							.id(e.getId())
							.build());
				}
			}else {
				data_array.add(airfield_request.builder()
						.adrress(e.getAddress())
						.contry(e.getContry())
						.name(e.getName())
						.id(e.getId())
						.build());
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
					data_array.add(airfield_request.builder().contry(e.getContry()).adrress(e.getAddress()).name(e.getName()).id(e.getId()).build());
		});
		return new ResponseEntity<>(data_array,HttpStatus.OK);
	}

}
