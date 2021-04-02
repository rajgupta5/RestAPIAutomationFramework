package com.restassured.testcases;

import static io.restassured.RestAssured.given;

import java.io.IOException;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.restassured.constants.Constants;
import com.restassured.requests.pojo.PostProductRequest;
import com.restassured.responses.pojo.PostProductResponse;
import com.restassured.utils.RandomUtils;

import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class BestBuyAPITests extends BaseTest{

	/*
	 * Please make sure you have followed these instruction to run the server on your localhost to execute these test
	 * https://github.com/BestBuy/api-playground
	 * ******************************************
	 * 	git clone https://github.com/bestbuy/api-playground/
	 *	cd api-playground
	 *	npm install
	 *	npm start
	 *  *****************************************
	 *  Load https://localhost:3030 on your browser
	 *  
	 */


	/*
	 * There should be a test case matching this test name in RUNMANAGER and TESTDATA sheet
	 * If there are multiple data lines in TESTDATA sheet, it will treated as iteration
	 * Mark Yes in RUNMANAGER and TESTDATA to run this test case
	 */

	@Test
	public void getProducts(Hashtable<String , String> data) {
		
		Response response=	given()
				.filter(new RequestLoggingFilter(captor)) //This line is mandatory to log the request details to extent report
				.log()
				.all()
				.get(Constants.BASEURL_BESTBUY+Constants.BESTBUY_GETPRODUCTS_ENDPOINT.replace("{limit}", data.get("limit")));

		/*
		 * Writing the request and response to extent report
		 * This statement needs to repeated in all your tests to log the req and response in extent report.
		 * 
		 * 
		 */
		writeRequestAndResponseInReport(writer.toString(), response.prettyPrint());

		//Asserting status code
		response.then().statusCode(200);

		//Asserting the list size in the response
		Assert.assertEquals(response.jsonPath().getList("data").size(), Integer.parseInt(data.get("limit")));
	}



	@Test
	public void postProductWithoutPOJO(Hashtable<String,String> data) {
		/*	sample request body
		 *   {
		 *   "name": "string",
			  "type": "string",
			  "price": 0,
			  "shipping": 0,
			  "upc": "string",
			  "description": "string",
			  "manufacturer": "string",
			  "model": "string",
			  "url": "string",
			  "image": "string",
			  "manufacturer":"string"
			}
		 */

		Map<String, Object> mapobj = new HashMap<String,Object>();
		mapobj.put("name",data.get("name")); //getting name value from excel as user input
		mapobj.put("type",data.get("type"));
		mapobj.put("price",Integer.parseInt(RandomUtils.generateRandomNumericString(2))); //Generate a random 2 digit number on fly
		mapobj.put("shipping",Integer.parseInt(RandomUtils.generateRandomNumericString(2)));
		mapobj.put("upc",RandomUtils.generateRandomString(3));
		mapobj.put("description",RandomUtils.generateRandomString(10));
		mapobj.put("model",RandomUtils.generateRandomString(3));
		mapobj.put("url",RandomUtils.generateRandomString(6));
		mapobj.put("image",RandomUtils.generateRandomString(3));
		mapobj.put("manufacturer",RandomUtils.generateRandomString(4));

		Response response=	given()
				.filter(new RequestLoggingFilter(captor)) //This line is mandatory to log the request details to extent report
				.header("Content-Type","application/json")
				.contentType(ContentType.JSON)
				.log()
				.all()
				.body(mapobj) //passing mapobj in request body
				.post(Constants.BASEURL_BESTBUY+Constants.BESTBUY_POSTPRODUCT_ENDPOINT); //posting request

		writeRequestAndResponseInReport(writer.toString(), response.prettyPrint());

		//Assert status code
		response.then().statusCode(201);

		
		Assert.assertEquals(response.jsonPath().get("name"), data.get("name"));


	}


	@Test
	public void postProductWithPojo(Hashtable<String,String> data) {

		/*
		 * Create a pojo class for the request
		 * Create a object and init all class variables using constructor
		 * Pass that in the body of the request
		 */
		
		PostProductRequest obj=new PostProductRequest(data.get("name"), data.get("type"), 
				Integer.parseInt(RandomUtils.generateRandomNumericString(2)), 
				Integer.parseInt(RandomUtils.generateRandomNumericString(2)),
				RandomUtils.generateRandomString(3), RandomUtils.generateRandomString(10),
				RandomUtils.generateRandomString(3), RandomUtils.generateRandomString(6), 
				RandomUtils.generateRandomString(4), RandomUtils.generateRandomString(3));



		Response response=	given()
				.filter(new RequestLoggingFilter(captor)) //This line is mandatory to log the request details to extent report
				.header("Content-Type","application/json")
				.contentType(ContentType.JSON)
				.log()
				.all()
				.body(obj) //passing obj in request body
				.post(Constants.BASEURL_BESTBUY+Constants.BESTBUY_POSTPRODUCT_ENDPOINT); //posting request


		writeRequestAndResponseInReport(writer.toString(), response.prettyPrint());


		//Assert status code
		response.then().statusCode(201);

		PostProductResponse resobj= response.as(PostProductResponse.class); 

		//Assertion using pojo getter method
		Assert.assertEquals(resobj.getName(), data.get("name"));

	}
	
	@Test
	public void postProductByReadingRequestFromFile(Hashtable<String,String> data) throws IOException {
		
		//reading the request body from a json file directly and passing to body as a string
		Response response=	given()
				.filter(new RequestLoggingFilter(captor)) //This line is mandatory to log the request details to extent report
				.header("Content-Type","application/json")
				.contentType(ContentType.JSON)
				.log()
				.all()
				.body(generateStringFromResource(Constants.REQUEST_JSON_FOLDER_PATH+"request_post_product.json"))
				.post(Constants.BASEURL_BESTBUY+Constants.BESTBUY_POSTPRODUCT_ENDPOINT);
		
		writeRequestAndResponseInReport(writer.toString(), response.prettyPrint());
		
		//Assert status code
				response.then().statusCode(201);

				PostProductResponse resobj= response.as(PostProductResponse.class); 

				System.out.println(resobj.toString());
				//Assertion using pojo getter method
				Assert.assertEquals(resobj.getName(), data.get("name"));
	}


}
