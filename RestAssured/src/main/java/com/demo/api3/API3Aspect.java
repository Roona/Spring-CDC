package com.demo.api3;

import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.demo.api3.request.model.Asset_Inquiry_Model;
import com.demo.api3.request.model.Asset_Inquiry_Request;
import com.demo.api3.response.model.Asset_Inquiry_FS_Response;



@Aspect
@Component

public class API3Aspect {
//get request path variable	
	private Logger mylog = Logger.getLogger(this.getClass());
	Asset_Inquiry_Model asset_inquiry_model = new Asset_Inquiry_Model();
	
	int randomServerPort=8979;
	 final String baseUrl = "http://localhost:"+randomServerPort+"/validateData/test1";
		RestTemplate rt = new RestTemplate();

		public String status="ready";
		public String apiName="PdsmAssetInquiry";
	 


	
	 @Before("execution(* com.demo.api3.controller.API3Controller.assetInquiryPV(..)) && args(request)")
	    public void mockSpotify(JoinPoint joinPoint,Object request) throws Throwable {
		  
		  Asset_Inquiry_FS_Response asset_inquiry_FS_response = new Asset_Inquiry_FS_Response();
		  if (status == "ready" && apiName  == "PdsmAssetInquiry"){
		  if(request instanceof  String){
				
				 asset_inquiry_model.setSerial_number(request.toString());
			 }
			 if(request instanceof  Asset_Inquiry_Request){
				 Asset_Inquiry_Request asset_inquiry_request = (Asset_Inquiry_Request) request;
				 asset_inquiry_model.setAsset_inquiry_request(asset_inquiry_request);
			
				 System.out.println("API2 Gateway captured PDSM Asset Inquiry Request for serial number " +asset_inquiry_request.getSerial_number());
			 }
		
			//System.out.println("In Spotify controller");
			asset_inquiry_FS_response.setLocation_code("CANEQ1");
		    asset_inquiry_FS_response.setModel_category("computers");
		    asset_inquiry_model.setAsset_inquiry_response(asset_inquiry_FS_response);
		    asset_inquiry_model.setApiname(apiName);
		    asset_inquiry_model.setIfname("DepotRepair");
		    mylog.info("Called Data validation API with mock data for interface Depot Repair");
		    sendDataassetInquiry();
		    asset_inquiry_model = new Asset_Inquiry_Model();
		  }
	    }
		

	
	  public void sendDataassetInquiry(){
			 
			 asset_inquiry_model.setApiname("PDSM Asset Inquiry");
			 asset_inquiry_model.setStatus("Completed");
			 asset_inquiry_model.setVersion("1.0");
			  ResponseEntity<String> response =  rt.postForEntity(baseUrl,asset_inquiry_model,String.class);
			 
		 }
		    }
	

