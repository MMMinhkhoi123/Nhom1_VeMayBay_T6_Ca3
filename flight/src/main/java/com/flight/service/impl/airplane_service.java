package com.flight.service.impl;

import java.io.Console;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.flight.entity.airplane;
import com.flight.entity.chair;
import com.flight.entity.trademark;
import com.flight.reponse.airplane_reponse;
import com.flight.reponse.getdata.airfield_data;
import com.flight.reponse.getdata.airplane_datav;
import com.flight.reponse.getdata.airplane_date;
import com.flight.reponse.getdata.chair_data;
import com.flight.reponse.getdata.location_data;
import com.flight.repository.airplane_repo;
import com.flight.repository.chair_repo;
import com.flight.repository.trademark_reponsetory;
import com.flight.request.airplane_request;
import com.flight.request.orther.sizetypechair;
import com.flight.service.I_airplane;

@Service
public class airplane_service implements I_airplane {

	
	@Autowired
	airplane_repo airplanereponsetory;
	
	@Autowired 
	chair_service chairservice;
	
	@Autowired
	trademark_reponsetory trademarkreponse;
	
	@Autowired
	chair_repo chairRepository;

	int x  = 0;
	
	@Override
	public ResponseEntity<airplane_reponse> save(airplane_request airplanex) {
		trademark trademark = trademarkreponse.findById(airplanex.getId_trademark()).get();
		airplane airplane = new airplane();
		airplane.setName(airplanex.getName());
		airplane.setStatus("stop");
		airplane.setId(airplane.getId());
		airplane.setTrademark(trademark);
		airplanex.getArray_type().get().forEach((item) -> {
			x = x + item.getSize();
		});
		airplane.setSize(x);
	    airplane created = airplanereponsetory.save(airplane);
		chairservice.create_chair(airplanex.getArray_type(),x, created.getId());
		x = 0;
		airplane_reponse reponse = new airplane_reponse();
		reponse.setMess("create airplane success");
		reponse.setStatus(true);
	    return new ResponseEntity<> (reponse,HttpStatus.OK);
	}

	@Override
	public ResponseEntity<airplane_reponse> update(airplane_request airplanex) {		
		trademark trademark = trademarkreponse.findById(airplanex.getId_trademark()).get();
		airplane airplane = new airplane();
		airplane.setName(airplanex.getName());
		airplane.setStatus("stop");
		airplane.setId(airplanex.getId());
		airplane.setTrademark(trademark);
		airplanex.getArray_type().get().forEach((item) -> {
			x = x + item.getSize();
		});
		airplane.setSize(x);
	    airplanereponsetory.save(airplane);
	    x = 0;
		chairservice.update_chair(airplanex.getArray_type(), x, airplanex.getId());
		airplane_reponse reponse = new airplane_reponse();
		reponse.setMess("update airplane success");
		reponse.setStatus(true);
	    return new ResponseEntity<> (reponse,HttpStatus.OK);
	}

	int full = 0;
	
	@Override
	public ResponseEntity<airplane_reponse> remove(Long id) {
		 chairservice.delete_chair(id);
		 airplanereponsetory.deleteById(id);
		airplane_reponse reponse = new airplane_reponse();
		reponse.setMess("delete airplane success");
		reponse.setStatus(true);
	    return new ResponseEntity<> (reponse,HttpStatus.OK);
	}

	Boolean check = false;
	
	public String convertUpcase(String data) {
		return data.toUpperCase();
	}
	
	@Override
	public ResponseEntity<airplane_datav> get(int page, int limit, String key) {
		airplane_datav result = new airplane_datav();
		result.setPage(page);
		Pageable datapage = new PageRequest(page -1,limit,Sort.by("id")) {
		};
		// duyet danh sach may bay da dc phan trang
		List<airplane_date> databig = new ArrayList<airplane_date>();
		airplanereponsetory.findAll(datapage).forEach((item) -> {
			airplane_date data_reponse = new airplane_date();
			if(!key.equalsIgnoreCase("")) {
				if(convertUpcase(item.getName()).contains(convertUpcase(key))) {
					setdata_step1(item, data_reponse);
					List<sizetypechair> datam = new ArrayList<sizetypechair>();
					List<chair_data> array = new ArrayList<chair_data>();
					run_extra(item.getId(), datam, array);
					setdata_step2(datam,array,data_reponse);
					full = 0;	
					databig.add(data_reponse);	
					}
				} else { 
					setdata_step1(item, data_reponse);
					List<sizetypechair> datam = new ArrayList<sizetypechair>();
					List<chair_data> array = new ArrayList<chair_data>();
					run_extra(item.getId(), datam, array);
					setdata_step2(datam,array,data_reponse);
					full = 0;	
					databig.add(data_reponse);
					
				}
		});
		result.setList(databig);
		result.setToltalpage((int) Math.ceil((double) (airplanereponsetory.count()) / limit));
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
	
	
	public void setdata_step1( airplane item , airplane_date data_reponse ) {
		data_reponse.setId_trademark(item.getTrademark().getId());
		data_reponse.setId(item.getId());
		data_reponse.setSize(item.getSize());
		data_reponse.setName(item.getName());
		data_reponse.setStatus(item.getStatus());
		data_reponse.setName_trademark(trademarkreponse.findById(item.getTrademark().getId()).get().getName());	
	}
	
	public void setdata_step2(List<sizetypechair> datam,List<chair_data> array, airplane_date data_reponse) {
		data_reponse.setTypechair(datam);
		data_reponse.setArray_list(array);
		data_reponse.setFull(full);
		data_reponse.setEmpty(data_reponse.getSize() - full);
	}

	public void run_extra(Long idairplane,List<sizetypechair> datam,List<chair_data> array) {
		chairRepository.findbyidairplane(idairplane).get().forEach((e) -> {
			check = false;
			chair_data data  = new chair_data();
			sizetypechair datas = new sizetypechair();
			datas.setIdtype(e.getType_chair().getId());
			datas.setSize(chairRepository.findchairsize(idairplane,e.getType_chair().getId()).get().size());
			datam.forEach((datax) -> {
				if(datax.getIdtype() == datas.getIdtype()) {
					check = true;
				}
				} );
			if(check == false) {
				datam.add(datas);
			}
			data.setId(e.getId());
			data.setSide(e.getSide());
			data.setStatus(e.getStatus());
			data.setStt(e.getStt());
			data.setTypechair(e.getType_chair().getId());
			array.add(data);
			if(!e.getStatus().equalsIgnoreCase("empty")) {
				full = full + 1;
			}
		});

	}

	@Override
	public ResponseEntity<List<airplane_date>> get() {
		List<airplane_date> databig = new ArrayList<airplane_date>();
		airplanereponsetory.findAll().forEach((item) -> {
			airplane_date data_reponse = new airplane_date();
					setdata_step1(item, data_reponse);
					List<sizetypechair> datam = new ArrayList<sizetypechair>();
					List<chair_data> array = new ArrayList<chair_data>();
					run_extra(item.getId(), datam, array);
					setdata_step2(datam,array,data_reponse);
					full = 0;	
					databig.add(data_reponse);			
		});
		return new ResponseEntity<>(databig, HttpStatus.OK);
	}
	

	
}
