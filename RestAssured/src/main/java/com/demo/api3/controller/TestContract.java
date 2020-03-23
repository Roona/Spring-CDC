package com.demo.api3.controller;

import org.assertj.core.api.BDDAssertions;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.stubrunner.junit.StubRunnerRule;
import org.springframework.cloud.contract.stubrunner.spring.AutoConfigureStubRunner;
import org.springframework.cloud.contract.stubrunner.spring.StubRunnerProperties;
import org.springframework.cloud.contract.wiremock.AutoConfigureWireMock;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import com.demo.api3.API3AppApplication;
import com.github.tomakehurst.wiremock.client.WireMock;
@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(classes={API3AppApplication.class})
@AutoConfigureWireMock(port=8878)
@AutoConfigureStubRunner(ids="com.demo:API3:+:stubs:8090",
stubsMode = StubRunnerProperties.StubsMode.LOCAL)
public class TestContract {

	@Rule
	public StubRunnerRule stubRunnerRule = new StubRunnerRule().downloadStub("com.demo", "API3");
	
	@Test
	public void test_should_return_response(){
		int randomServerPort=8878;
		 final String baseUrl2 = "http://localhost:"+randomServerPort+"/api/hello";
		 String testUrl;
		WireMock.stubFor(WireMock.get(WireMock.urlEqualTo("/api/hello"))
				 .willReturn(WireMock.aResponse().withStatus(201)));
		RestTemplate rt = new RestTemplate();
		ResponseEntity<String> response =  rt.getForEntity(baseUrl2,String.class);
		BDDAssertions.then(response.getStatusCodeValue()).isEqualTo(201);
		
	}
	
	@Test
	public void test_should_return_response_integration(){
		int randomServerPort=8090;
		 final String baseUrl2 = "http://localhost:"+randomServerPort+"/api/hello";
		 String testUrl;
	
		RestTemplate rt = new RestTemplate();
		ResponseEntity<String> response =  rt.getForEntity(baseUrl2,String.class);
		BDDAssertions.then(response.getStatusCodeValue()).isEqualTo(200);
		
	}
	
	
	@Test
	public void test_should_return_response_integration_real(){
		int randomServerPort=8870;
		 final String baseUrl2 = "http://localhost:"+randomServerPort+"/api/hello";
		 String testUrl;
	
		RestTemplate rt = new RestTemplate();
		ResponseEntity<String> response =  rt.getForEntity(baseUrl2,String.class);
		BDDAssertions.then(response.getStatusCodeValue()).isEqualTo(200);
		
	}
}
