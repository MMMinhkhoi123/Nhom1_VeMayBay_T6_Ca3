package com.flight.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.flight.entity.chair;
import com.flight.entity.customer;
import com.flight.entity.parent_einvoice;
import com.flight.entity.ticker;
import com.flight.reponse.customer_reponse;
import com.flight.reponse.customer_reponses;
import com.flight.reponse.payment_reponse;
import com.flight.reponse.getdata.airplane_datav;
import com.flight.reponse.getdata.customer_data;
import com.flight.repository.chair_repo;
import com.flight.repository.customer_repo;
import com.flight.repository.parent_repo;
import com.flight.repository.ticker_repo;
import com.flight.request.customer_request;
import com.flight.service.I_customer;

@Service
public class customer_service implements I_customer {

	@Autowired
	customer_repo customer_reponsetory;
	
	@Autowired
	ticker_repo tickreponsetory;
	
	@Autowired
	chair_repo chair_reponsetory;
	
	@Autowired
	parent_repo parentreponsetory;
	
	@Autowired 
	parent_service parent_service;
	
	@Autowired
	pay_service payservice;
	
	int pricesum = 0;
	
	@Override
	public ResponseEntity<customer_reponse> save(List<customer_request> customerz) {
		pricesum  = 0;
		parent_einvoice parnent = parent_service.save_lv1();
		customerz.forEach((itemx) -> {
			customer data = new customer();
			data.setParent_einvoices(parnent);
			data.setFullname(itemx.getFullname());
			data.setAge(itemx.getAge());
			data.setEmail(itemx.getEmail());
			data.setPassport(itemx.getPassport());
			data.setNationality(itemx.getNationality());
			data.setPhone(itemx.getTel());
			ticker tick = tickreponsetory.findById(itemx.getIdtick()).get();
			data.setTicker(tick);
			data.setFlight(tick.getFlight());
			chair chair = chair_reponsetory.findById(itemx.getSeat()).get();
			data.setChair(chair);
			chair_reponsetory.update_status(itemx.getSeat());
			pricesum = pricesum +  tick.getPrice()+ chair.getType_chair().getPrice();
			customer created = customer_reponsetory.save(data);
		});
		parentreponsetory.update_price(parnent.getId(), pricesum);
		customer_reponse datareponse = new customer_reponse();
		datareponse.setIdbill(parnent.getId());
		datareponse.setMess("Đang chờ thanh toán");
		datareponse.setPrice(pricesum);
		payment_reponse data = payservice.urlpay(pricesum);
		datareponse.setUrl(data.getUrl());
		parentreponsetory.update_codepay(parnent.getId(), data.getCodebill());
	  return new ResponseEntity<>( datareponse,HttpStatus.OK);
	}

	@Override
	public void update_status(Long id) {
		customer_reponsetory.update_status(id);
	}

	@Override
	public customer_request get(Long idcus) {
		customer_request data = new customer_request();
		customer cus = customer_reponsetory.findById(idcus).get();
		data.setAge(cus.getAge());
		data.setEmail(cus.getEmail());
		data.setFullname(cus.getFullname());
		data.setId(cus.getId());
		data.setNationality(cus.getNationality());
		data.setPassport(cus.getPassport());
		data.setTel(cus.getPhone());
		return data;
	}

	@Override
	public ResponseEntity<customer_data> gets(int page, int limit, String key) {
		customer_data result = new customer_data();
		result.setPage(page);
		Pageable datapage = new PageRequest(page -1,limit,Sort.by("id")) {
		};
		List<customer_reponses> list = new ArrayList<customer_reponses>();
		customer_reponsetory.findAll(datapage).forEach((e) -> {
			customer_reponses sa = new customer_reponses();
			if(!key.equalsIgnoreCase("")) {
				if(e.getPhone().contains(key)) {
					setdata(sa,e);
					list.add(sa);
				}
			}else {
				setdata(sa,e);
			list.add(sa);
			}
		});
		result.setList(list);
		result.setToltalpage((int) Math.ceil((double) (customer_reponsetory.count()) / limit));
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
	
	
	public void setdata(customer_reponses sa, customer e) {
		sa.setEmail(e.getEmail());
		sa.setFullname(e.getFullname());
		sa.setId(e.getId());
		sa.setIdbill(e.getParent_einvoices().getId());
		sa.setIdchair(e.getChair().getId());
		sa.setIdflight(e.getFlight().getId());
		sa.setIdticker(e.getTicker().getId());
		sa.setNationality(e.getNationality());
		sa.setPassport(e.getPassport());
		sa.setPhone(e.getPhone());
		sa.setNamelocation_departure(e.getFlight().getLocation().getName());
		sa.setNamelocation_complete(e.getFlight().getLocation_complete().getName());
		sa.setDate_departure(e.getFlight().getDate_departure().getTime());
		sa.setPricetick(e.getTicker().getPrice());
		sa.setPricechair(e.getChair().getType_chair().getPrice());
		sa.setStatus(e.getFlight().getStatus());
		sa.setAge(e.getAge());
	}

}
