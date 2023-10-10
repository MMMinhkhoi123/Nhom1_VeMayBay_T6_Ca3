package com.flight.service.impl;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.flight.payconfig.config;
import com.flight.reponse.payment_reponse;
import com.flight.repository.parent_repo;
import com.flight.service.I_payment;
@Service
public class pay_service implements I_payment {

	@Autowired
	parent_repo parentreponse;
	
	@Override
	public payment_reponse urlpay(int sum) {
		
		payment_reponse x = new payment_reponse();
		
		    String vnp_Version = "2.1.0";
	        String vnp_Command = "pay";
	        String orderType = "other";
	        long amount = sum*100;
	        String bankCode = "NCB";
	        String vnp_TxnRef = config.getRandomNumber(8);
	        String vnp_IpAddr = "170.0.0.1";
	        String vnp_TmnCode = config.vnp_TmnCode;
	        Map<String, String> vnp_Params = new HashMap<>();
	        vnp_Params.put("vnp_Version", vnp_Version);
	        vnp_Params.put("vnp_Command", vnp_Command);
	        vnp_Params.put("vnp_TmnCode", vnp_TmnCode);
	        vnp_Params.put("vnp_Amount", String.valueOf(amount));
	        vnp_Params.put("vnp_CurrCode", "VND");
	        
	        if (bankCode != null && !bankCode.isEmpty()) {
	            vnp_Params.put("vnp_BankCode", bankCode);
	        }
	        vnp_Params.put("vnp_TxnRef", vnp_TxnRef);
	        vnp_Params.put("vnp_OrderInfo", "Thanh toan don hang:" + vnp_TxnRef);
	        vnp_Params.put("vnp_OrderType", orderType);
	        vnp_Params.put("vnp_Locale", "vn");

	        vnp_Params.put("vnp_ReturnUrl", config.vnp_ReturnUrl);
	        vnp_Params.put("vnp_IpAddr", vnp_IpAddr);

	        Calendar cld = Calendar.getInstance(TimeZone.getTimeZone("Etc/GMT+7"));
	        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
	        String vnp_CreateDate = formatter.format(cld.getTime());
	        vnp_Params.put("vnp_CreateDate", vnp_CreateDate);
	        
	        cld.add(Calendar.MINUTE, 15);
	        String vnp_ExpireDate = formatter.format(cld.getTime());
	        vnp_Params.put("vnp_ExpireDate", vnp_ExpireDate);
	        List fieldNames = new ArrayList(vnp_Params.keySet());
	        Collections.sort(fieldNames);
	        StringBuilder hashData = new StringBuilder();
	        StringBuilder query = new StringBuilder();
	        Iterator itr = fieldNames.iterator();
	        while (itr.hasNext()) {
	            String fieldName = (String) itr.next();
	            String fieldValue = (String) vnp_Params.get(fieldName);
	            if ((fieldValue != null) && (fieldValue.length() > 0)) {
	                //Build hash data
	                hashData.append(fieldName);
	                hashData.append('=');
	                try {
						hashData.append(URLEncoder.encode(fieldValue, StandardCharsets.US_ASCII.toString()));
						   //Build query
		                query.append(URLEncoder.encode(fieldName, StandardCharsets.US_ASCII.toString()));
		                query.append('=');
		                query.append(URLEncoder.encode(fieldValue, StandardCharsets.US_ASCII.toString()));
					} catch (UnsupportedEncodingException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
	                if (itr.hasNext()) {
	                    query.append('&');
	                    hashData.append('&');
	                }
	            }
	        }
	        String queryUrl = query.toString();
	        String vnp_SecureHash = config.hmacSHA512(config.secretKey, hashData.toString());
	        queryUrl += "&vnp_SecureHash=" + vnp_SecureHash;
	        String paymentUrl = config.vnp_PayUrl + "?" + queryUrl;
	     x.setCodebill(vnp_TxnRef);
	     x.setUrl(paymentUrl);
	     return x;
	}


}
