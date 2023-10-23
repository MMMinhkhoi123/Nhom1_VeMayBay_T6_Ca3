package com.flight.payconfig;

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
import java.util.Optional;
import java.util.TimeZone;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/pay")
public class paymentConttroler {

	@GetMapping("/sup") 
	public ResponseEntity<?> sup(@RequestParam("vnp_Amount") Optional<Integer> amount, @RequestParam("vnp_ResponseCode") Optional<String> code) {
		String x = "http://localhost:8080/vnpay_jsp/vnpay_return.jsps?vnp_Amount=100000000&vnp_BankCode=VNPAY&vnp_CardType=QRCODE&vnp_OrderInfo=Thanh+toan+don+hang%3A85716486&vnp_PayDate=20231007204937&vnp_ResponseCode=15&vnp_TmnCode=LUVWX1FQ&vnp_TransactionNo=0&vnp_TransactionStatus=02&vnp_TxnRef=85716486&vnp_SecureHash=1ac0845fe8361ae45ceb3dd747443e7d2f8affd284fec5550e9de82dc43839dd97253b4481baf462f79eb1adeb37b5ffe287bc693172e5b58b7c8624327beb29";
        String y = "http://localhost:8080/vnpay_jsp/vnpay_return.jsps?vnp_Amount=100000000&vnp_BankCode=NCB&vnp_BankTranNo=VNP14135467&vnp_CardType=ATM&vnp_OrderInfo=Thanh+toan+don+hang%3A17695187&vnp_PayDate=20231007211334&vnp_ResponseCode=00&vnp_TmnCode=LUVWX1FQ&vnp_TransactionNo=14135467&vnp_TransactionStatus=00&vnp_TxnRef=17695187&vnp_SecureHash=efff184a9e271bdaba137b3461ef1d47715e5b3f71bbbc29c1a319b7ce3a6244fb5a37ec3576bf6d1360187f7dae7b3ec41d4bbc1ae6c542bc61f0addbeaefd9";
		System.out.print(code.get());
        return null;
	}
	
	@GetMapping("/create-payment")
	public ResponseEntity<?> createpayment() {
		 String vnp_Version = "2.1.0";
	        String vnp_Command = "pay";
	        String orderType = "other";
	        long amount = 1000000*100;
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
        
        payDTO dto = new payDTO();
        dto.setStatus("OK");
        dto.setMess("successly");
        dto.setUrl(paymentUrl);
        
        return ResponseEntity.status(HttpStatus.OK).body(dto);
	}
}
