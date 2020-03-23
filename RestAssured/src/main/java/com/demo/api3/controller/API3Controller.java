package com.demo.api3.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.api3.request.model.Asset_Inquiry_Request;
import com.demo.api3.response.model.Asset_Inquiry_FS_Response;



@RestController
@RequestMapping("/api")
public class API3Controller {

	Logger logger = LoggerFactory.getLogger(API3Controller.class);

	@PostMapping(value = "/test2")
	public Asset_Inquiry_FS_Response assetInquiryBO(
			@RequestBody String serial_number)
			throws IOException {
		Asset_Inquiry_FS_Response asset_inquiry_FS_response = new Asset_Inquiry_FS_Response();
		asset_inquiry_FS_response.setSerial_number(serial_number);
		asset_inquiry_FS_response.setLocation_code("CANEQ");
	    asset_inquiry_FS_response.setModel_category("computers");
		return asset_inquiry_FS_response;
	}

	@RequestMapping(path = "/test3")
	public Asset_Inquiry_FS_Response assetInquiryPV(@RequestBody Asset_Inquiry_Request asset_inquiry_request)
			 {
	
		Asset_Inquiry_FS_Response asset_inquiry_FS_response = new Asset_Inquiry_FS_Response();
		asset_inquiry_FS_response.setSerial_number(asset_inquiry_request.getSerial_number());
		asset_inquiry_FS_response.setLocation_code("CANEQ");
	    asset_inquiry_FS_response.setModel_category("computers");
		return asset_inquiry_FS_response;
	}
/*	
	@RequestMapping(path = "/test3")
	public void mockDataGenerator()
			 {
	
		Asset_Inquiry_FS_Response asset_inquiry_FS_response = new Asset_Inquiry_FS_Response();
		asset_inquiry_FS_response.setSerial_number(asset_inquiry_request.getSerial_number());
		asset_inquiry_FS_response.setLocation_code("CANEQ");
	    asset_inquiry_FS_response.setModel_category("computers");
		return asset_inquiry_FS_response;
	}*/
	
@RequestMapping("/hello")
	
	public String hellonew(){
	
		
	
		return "Hello" ;
		
	}

}
