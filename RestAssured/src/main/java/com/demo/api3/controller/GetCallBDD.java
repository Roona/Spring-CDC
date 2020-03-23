package com.demo.api3.controller;

import static com.jayway.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.demo.api3.request.model.Asset_Inquiry_Request;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
@Test

public class GetCallBDD {
	

    private static String payload = "{\n" +
        "  \"description\": \"Some Description\",\n" +
        "  \"id\": \"Some id\",\n" +
        "  \"name\": \"Some name\"\n" +
        "}";
  //RestAssured.baseURI = "http://localhost:8878/api/test3";

	
	@Test(dataProvider = "data-provider")
	public void getdata(Map<Object, Object> map) throws URISyntaxException, JsonParseException, JsonMappingException, IOException{
		
		String url = (String) map.get("url");
		String requestBody = (String) map.get("RequestBody");
		ObjectMapper mapper = new ObjectMapper();
		Asset_Inquiry_Request request = mapper.readValue(requestBody, Asset_Inquiry_Request.class);
		given()
		.baseUri(url)
		.contentType("application/json").body(request)
		.when()
		.get().then().
		
	    body("model_category", equalTo("computers"));
	
		 
		          
	}
	

    @DataProvider(name = "data-provider")
    public Object[][] dataProviderMethod() throws IOException {
    	
    	  File file = new File("C://Users//5176144//Desktop//test//TestData.xlsx");
    	  
    	 
    	    FileInputStream fis = new FileInputStream(file);

    	    XSSFWorkbook wb = new XSSFWorkbook(fis);
    	    XSSFSheet sheet = wb.getSheetAt(0);
    	    wb.close();
    	    int lastRowNum = sheet.getLastRowNum() ;
    	    int lastCellNum = sheet.getRow(0).getLastCellNum();
    	    Object[][] obj = new Object[lastRowNum][1];

    	    for (int i = 0; i < lastRowNum; i++) {
    	      Map<Object, Object> datamap = new HashMap<>();
    	      for (int j = 0; j < lastCellNum; j++) {
    	        datamap.put(sheet.getRow(0).getCell(j).toString(), sheet.getRow(i+1).getCell(j).toString());
    	      }
    	      obj[i][0] = datamap;

    	    }
    	    return  obj;
    	  }

    	


}
