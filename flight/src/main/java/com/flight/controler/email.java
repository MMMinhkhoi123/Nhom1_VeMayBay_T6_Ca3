package com.flight.controler;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.flight.reponse.email_reponse;
import com.flight.reponse.email_reponsev2;
import com.flight.reponse.location_reponse;

@Controller
@RequestMapping("/einvoice")
public class email {

	@GetMapping(value = "/bill")
	public String login(Model model) {
		model.addAttribute("foo", "minhkhoi hehe");
		return "einvoice";
	}
	@GetMapping(value = "/bill2")
	public String login2(Model model) {
        email_reponse data = new email_reponse();
        email_reponse data2 = new email_reponse();
        email_reponsev2 data2s = new email_reponsev2();
        List<email_reponse> value = new ArrayList<email_reponse>();
        data2s.setCode(Long.valueOf(90));
        data2s.setSumprice(9000);
        data.setAirfield("abc 55/66 hcm");
        data.setKilo(90);
        data.setLocation_go("TP HCM");
        data.setLocation_fish("TD Ha Noi");
        data.setPrice_chair(800);
        data.setPrice_ticker(900);
        data.setSeat("LB8");
        data.setTime("12/22/2003");
        data.setTrademark("vietjet");
        data.setTitle("chuyến đi");
        
        data2.setAirfield("abc 55/66 hcm");
        data2.setKilo(90);
        data2.setLocation_go("TP HCM");
        data2.setLocation_fish("TD Ha Noi");
        data2.setPrice_chair(800);
        data2.setPrice_ticker(900);
        data2.setSeat("LB8");
        data2.setTime("12/22/2003");
        data2.setTrademark("vietjet");
        data2.setTitle("chuyến về");
        value.add(data);
        value.add(data2);
        data2s.setList(value);
        model.addAttribute("code",  data2s.getCode());
        model.addAttribute("sumprice", data2s.getSumprice());
        model.addAttribute("list", data2s.getList());
		return "einvoice2";
	}
}
