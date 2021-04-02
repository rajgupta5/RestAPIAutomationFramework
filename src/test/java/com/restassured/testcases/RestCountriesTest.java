package com.restassured.testcases;

import static io.restassured.RestAssured.given;

import java.io.File;
import java.io.IOException;
import java.util.Hashtable;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.google.common.io.Files;
import com.restassured.constants.Constants;

import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.response.Response;


public class RestCountriesTest extends BaseTest{

	

	/*
	 * There should be a test case matching this test name in RUNMANAGER and TESTDATA sheet
	 * If there are multiple data lines in TESTDATA sheet, it will treated as iteration
	 * Mark Yes in RUNMANAGER and TESTDATA to run this test case
	 */
	
	
	@Test
	public void getCountryDetailsTest(Hashtable<String, String> data) throws IOException {
		/*
		 * Replacing the name parameter in the endpoint with the data from excel sheet.
		 * Data providers return a hastable and the column name is used as a key to get the value
		 */
		
		
		Response response=	given()
				.filter(new RequestLoggingFilter(captor)) //This line is mandatory to log the request details to extent report
				.log()
				.all()
				.get(Constants.BASEURL+Constants.COUNTRYDETAILSBYNAME_ENDPOINT.replace("{name}", data.get("countryName")));


		/*
		 * Writing the request and response to extent report
		 *  
		 */
		writeRequestAndResponseInReport(writer.toString(), response.prettyPrint());
		
		//Asserting the country code in the response using jsonPath. 
		//Expected value is from TESTDATA sheet and column expectedCountryCapital
		Assert.assertEquals(response.jsonPath().get("[0].capital"), data.get("expectedCountryCapital"));
		
		//Writing the response to an log file
		Files.write(response.asByteArray(), 
				new File(Constants.RESPONSETXTPATH+data.get("TestCaseName")+data.get("countryName")+".txt"));
		
	}

}
