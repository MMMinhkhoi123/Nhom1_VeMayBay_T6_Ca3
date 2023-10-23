package com.flight.service.impl;

import java.io.Console;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.flight.entity.airplane;
import com.flight.entity.chair;
import com.flight.entity.type_chair;
import com.flight.reponse.chair_reponse;
import com.flight.reponse.getdata.chair_data;
import com.flight.repository.airplane_repo;
import com.flight.repository.chair_repo;
import com.flight.repository.type_chair_repo;
import com.flight.request.orther.sizetypechair;
import com.flight.service.I_chair;

@Service
public class chair_service implements I_chair {

	@Autowired
	chair_repo chairreponsetory;
	
	@Autowired 
	airplane_repo airplanereposetory;
	
	@Autowired
	type_chair_repo typechairreponsetory;
	
	public void common(Optional<List<sizetypechair>> list_type, int size, Long idplane) {
		airplane airplane = airplanereposetory.findById(idplane).get();
		if(list_type.isPresent()) {
			list_type.get().forEach((item) -> {
				for(int i = 0;i < item.getSize(); i++ ) {
					chair chairs = new chair();
					type_chair type_chair = new type_chair();
					type_chair = typechairreponsetory.findById(item.getIdtype()).get();
					chairs.setStatus("empty");
					chairs.setAirplane(airplane);
					chairs.setType_chair(type_chair);
					if(i >= item.getSize()/2) {
						chairs.setSide("right");
						chairs.setStt("R"+item.getIdtype()+i);
					}else {
						chairs.setSide("left");
						chairs.setStt("L"+item.getIdtype()+i);
					}
				  chairreponsetory.save(chairs);
				}
			});
		}
	}
	
	
	
	
	@Override
	public void create_chair(Optional<List<sizetypechair>> list_type, int size, Long idplane) {
		common(list_type, size, idplane);
	}
	@Override
	public void update_chair(Optional<List<sizetypechair>> list_type, int size, Long idplane) {
		  chairreponsetory.deletebyidplane(idplane);
		  common(list_type, size, idplane);
	}
	@Override
	public void delete_chair(Long idplane) {
		 chairreponsetory.deletebyidplane(idplane);
	}


	@Override
	public chair_reponse get(Long idchair) {
		chair chair =  chairreponsetory.findById(idchair).get();
		chair_reponse data = new chair_reponse();
		data.setIdchair(idchair);
		data.setStt(chair.getStt());
		data.setNametype(chair.getType_chair().getName());
		data.setPrice(chair.getType_chair().getPrice());
		return data;
	}

	
}
