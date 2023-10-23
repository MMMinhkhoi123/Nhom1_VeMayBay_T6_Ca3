package com.flight.controler;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.flight.service.impl.parent_service;
import com.flight.service.impl.pay_service;

@Controller
@RequestMapping("/payment")
public class payment {
	
	@Autowired
	parent_service parentservice;
	
	
	@GetMapping("/pay-end")
	public String payment(Model model,@RequestParam("vnp_Amount") Optional<Integer> amount, @RequestParam("vnp_ResponseCode") Optional<String> code,@RequestParam("vnp_TxnRef") Optional<String> codebill) {
		if (code.get().equalsIgnoreCase("00")) {
			parentservice.updateeinvoice(codebill.get());
			model.addAttribute("status", 1);
		} else {
			parentservice.delete(codebill.get());
			model.addAttribute("status", 0);
		}
		return "pay";
	}
}
