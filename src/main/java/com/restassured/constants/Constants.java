package com.restassured.constants;

public class Constants {
	
	private Constants() {
		
	}

	public static final String EXCELPATH = System.getProperty("user.dir")+"/src/test/resources/testdata.xlsx";
	public static final String TESTDATASHEETNAME = "TestData";
	public static final String EXTENTREPORTPATH = System.getProperty("user.dir")+"/ExtentReports/index.html";
	
	public static final String EXTENTCONFIGFILEPATH = System.getProperty("user.dir")
			+"/src/test/resources/extentreport.xml";
	
	public static final String SCHEMAVALIDATIONJSONPATH =  System.getProperty("user.dir")+
			"/src/test/resources/jsonsforschemavalidations/countrydetails_response_schema.json";
	
	public static final String RESPONSETXTPATH="./output/responses/";
	
	public static final String RUNMANAGERSHEET= "RUNMANAGER";
	public static final String JSONSLOCATION=System.getProperty("user.dir")+"/src/test/resources/jsons";
	public static final String BASEURL = "https://restcountries.eu";
	public static final String BASEURL_BESTBUY = "http://localhost:3030";
	public static final String USERNAME ="";
	public static final String CLIENT_SECRET="";
	public static final String GRANT_TYPE= "";
	public static final String CLIENT_ID= "";
	public static final String PASSWORD= "";
	public static final String FSI_LANGUAGE="";

	//Endpoints
	public static final String COUNTRYDETAILSBYNAME_ENDPOINT = "/rest/v2/name/{name}";
	public static final String BESTBUY_GETPRODUCTS_ENDPOINT = "/products?$limit={limit}";
	public static final String BESTBUY_POSTPRODUCT_ENDPOINT ="/products";
	public static final String AUTH_ENDPOINT = "";
	
	//Request xml paths
	public static final String REQUEST_JSON_FOLDER_PATH =  System.getProperty("user.dir")+
			"/src/test/resources/jsonsforrequestbody/";


}
