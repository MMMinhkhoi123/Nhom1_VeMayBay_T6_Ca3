package com.flight.service.impl;


import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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

import com.flight.entity.customer;
import com.flight.entity.parent_einvoice;
import com.flight.entity.user;
import com.flight.reponse.bill_parent_reponse;
import com.flight.reponse.bill_reponse;
import com.flight.reponse.email_reponse;
import com.flight.reponse.email_reponsev2;
import com.flight.reponse.getdata.location_data;
import com.flight.repository.chair_repo;
import com.flight.repository.customer_repo;
import com.flight.repository.parent_repo;
import com.flight.repository.ticker_repo;
import com.flight.repository.user_repo;
import com.flight.request.einvoice_request;
import com.flight.request.location_request;
import com.flight.security.GeneredJWT;
import com.flight.service.I_parent;

@Service
public class parent_service implements I_parent {

	@Autowired
	parent_repo parentreponsitory;
	
	@Autowired
	customer_repo customerrepository;
	
	@Autowired 
	emailservice emailservice;
	
	@Override
	public parent_einvoice save_lv1() {
		parent_einvoice data = new parent_einvoice();
		data.setStatus("no");
		data.setSumprice(0);
		return parentreponsitory.save(data);
	}

	@Autowired
	private GeneredJWT jwtGenerator;
	
	@Autowired
	user_repo user_repository;
	
	   int i = 0;
	public email_reponsev2 setdata_envoice(Long idbill) {
	  email_reponsev2 data2 = new email_reponsev2();
	  List<email_reponse> arraychild= new ArrayList<email_reponse>();
	  parent_einvoice value = parentreponsitory.findById(idbill).get();
	  data2.setCode(idbill);
	  data2.setSumprice(value.getSumprice());
	  List<customer> array = customerrepository.findallbyidbill(value.getId()).get();
	  array.forEach((e) -> {
		  email_reponse data1 = new email_reponse();
		  data2.setNamedata(e.getFullname());
		  data1.setAirfield(e.getFlight().getAirfield().getName());
		  data1.setKilo(e.getChair().getType_chair().getLuggage());
		  data1.setLocation_go(e.getFlight().getLocation().getName());
		  data1.setLocation_fish(e.getFlight().getLocation_complete().getName());
		  data1.setPrice_chair(e.getChair().getType_chair().getPrice());
		  data1.setPrice_ticker(e.getTicker().getPrice());
		  data1.setSeat(e.getChair().getStt());
		  data1.setTime(datevalue(e.getFlight().getDate_departure()));
		  if (i == 1) {
		  data1.setTitle("Chuyến về");
		  i= 0;
		  } else {
			  data1.setTitle("Chuyến đi");
			  i= 0;
		  }
		  data1.setTrademark(e.getFlight().getAirplane().getTrademark().getName());
		  i = i + 1;
		  arraychild.add(data1);
	  });
	  data2.setList(arraychild);
	  data2.setSumprice(value.getSumprice());
	  data2.setDateby(datevalue(value.getDate_create()));
	   return data2;
	}

  public String datevalue(Date date) {
	  return date.getHours() + ":"+ date.getMinutes() + " " + date.getDate() + "/" + date.getMonth() + "/" + date.getYear();
  }

@Autowired
ticker_repo ticker_repo;
	String exmail = "";
@Override
public void updateeinvoice(String codepay) {
	parent_einvoice x = parentreponsitory.getbill(codepay);
	customerrepository.findallbyidbill(x.getId()).get().forEach((e) -> {
		exmail = e.getEmail();
		ticker_repo.update_status_buy(e.getFlight().getTickers().getId());
	});
	if (!exmail.equalsIgnoreCase("")) {
		user data = user_repository.findonebynemail(exmail).get();
		parentreponsitory.update_user(x.getId(), data.getId());
		emailservice.sendEmail_html(exmail, "E-INVOICE FOR YOU" ,setdata_envoice(x.getId()));
		exmail = "";
	}
	parentreponsitory.update(x.getId());
}
@Autowired
chair_repo chaiRepo;

@Override
public void delete(String codepay) {
	parent_einvoice x = parentreponsitory.getbill(codepay);
	customerrepository.findallbyidbill(x.getId()).get().forEach((e) -> {
		customerrepository.deleteById(e.getId());
		chaiRepo.update_status2(e.getChair().getId());
	});
	parentreponsitory.deletebill(codepay);
}

@Override
public ResponseEntity<bill_parent_reponse> get(int page, int limit, String key) {
	List<bill_reponse> data_array = new ArrayList<bill_reponse>();

	Pageable xx = new PageRequest(page -1,limit,Sort.by("id")) {
	};
	parentreponsitory.findAll(xx).forEach((e) -> {	
		if (!key.equalsIgnoreCase("")) {
			if(convertUpcase(e.getCodepay()).contains(convertUpcase(key))) {
				bill_reponse bill_reponses = bill_reponse.builder().id(e.getId()).codepay(e.getCodepay()).date(e.getDate_create().getTime()).sumprice(e.getSumprice()).status(e.getStatus()).build();
				if (e.getIduser() != null) {
					bill_reponses.setIduser(e.getIduser());
				}
				if (e.getDate_modify() != null) {
					bill_reponses.setDate_pay(e.getDate_modify().getTime());
				}
				data_array.add(bill_reponses);
			}
		} else {
			bill_reponse bill_reponses = bill_reponse.builder().id(e.getId()).codepay(e.getCodepay()).date(e.getDate_create().getTime()).sumprice(e.getSumprice()).status(e.getStatus()).build();
			if (e.getIduser() != null) {
				bill_reponses.setIduser(e.getIduser());
			}
			if (e.getDate_modify() != null) {
				bill_reponses.setDate_pay(e.getDate_modify().getTime());
			}
			data_array.add(bill_reponses);
		}
	});
	return new ResponseEntity<>(bill_parent_reponse.builder()
			.list(data_array)
			.toltalpage((int) Math.ceil((double) (parentreponsitory.count()) / limit))
			.page(page)
			.build(),HttpStatus.OK);
}

public String convertUpcase(String data) {
	return data.toUpperCase();
}

@Override
public ResponseEntity<List<bill_reponse>> get() {
	List<bill_reponse> data_array = new ArrayList<bill_reponse>();
	parentreponsitory.findAll().forEach((e) -> {	
			bill_reponse bill_reponses = bill_reponse.builder().id(e.getId()).codepay(e.getCodepay()).date(e.getDate_create().getTime()).sumprice(e.getSumprice()).status(e.getStatus()).build();
			if (e.getIduser() != null) {
				bill_reponses.setIduser(e.getIduser());
			}
			if (e.getDate_modify() != null) {
				bill_reponses.setDate_pay(e.getDate_modify().getTime());
			}
			data_array.add(bill_reponses);
	});
	return new ResponseEntity<>(data_array,HttpStatus.OK);
}

}
