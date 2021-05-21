package com.restassured.testcases;

import static io.restassured.RestAssured.given;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.io.StringWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

import com.restassured.enums.ConfigProperties;
import com.restassured.services.Endpoints;
import com.restassured.utils.ConfigReaderUtil;
import com.sun.tools.jxc.ConfigReader;
import org.apache.commons.io.output.WriterOutputStream;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import com.restassured.constants.Constants;
import com.restassured.reports.ExtentReport;
import com.restassured.reports.LogStatus;
import com.restassured.utils.TestUtils;

import io.restassured.RestAssured;
import io.restassured.config.EncoderConfig;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class BaseTest {
	
	protected StringWriter writer;
	protected PrintStream captor;
	private Endpoints endPoints;
	private Map<String, Object> scenarioContext;


	public Endpoints getEndPoints() {
		return endPoints;
	}

	public void setContext(String key, Object value) {
		scenarioContext.put(key, value);
	}

	public Object getContext(String key){
		return scenarioContext.get(key);
	}

	public Boolean isContains(String key){
		return scenarioContext.containsKey(key);
	}

	@BeforeSuite
	public void setUpSuite() {
		writer = new StringWriter();
		captor = new PrintStream(new WriterOutputStream(writer), true);
		scenarioContext = new HashMap<>();
		setContext("userId", ConfigReaderUtil.get(ConfigProperties.USERID));
		setContext("username", ConfigReaderUtil.get(ConfigProperties.USERNAME));
		setContext("password", ConfigReaderUtil.get(ConfigProperties.PASSWORD));
		endPoints = new Endpoints(ConfigReaderUtil.get(ConfigProperties.URL), captor);
		ExtentReport.initialize();
	}


	@AfterSuite
	public void afterSuite() throws IOException  {
		ExtentReport.report.flush();
		File htmlFile = new File(Constants.EXTENTREPORTPATH);
		Desktop.getDesktop().browse(htmlFile.toURI()
		);

	}


	@BeforeMethod
	public void setUp() {

	}

	
	

	protected void performOAuth() {

		Response response=given().header("Content-Type", "application/json").
				config(RestAssured.config()
						.encoderConfig(EncoderConfig.encoderConfig()
								.encodeContentTypeAs("x-www-form-urlencoded", ContentType.URLENC)))
				.contentType("application/x-www-form-urlencoded; charset=UTF-8")
				.formParam("username", Constants.USERNAME)
				.formParam("grant_type",  Constants.GRANT_TYPE)
				.formParam("client_id", Constants.CLIENT_ID)
				.formParam("password",  Constants.PASSWORD)
				.formParam("client_secret", TestUtils.decode(Constants.CLIENT_SECRET))
				.post(Constants.BASEURL+Constants.AUTH_ENDPOINT);
		response.then().statusCode(200);

		System.out.println("OAUTH is success");
	}

	protected void formatAPIAndLogInReport(String content) {

		String prettyPrint = content.replace("\n", "<br>");
		LogStatus.info("<pre>" + prettyPrint + "</pre>");

	}



	public String generateStringFromResource(String path) throws IOException {
		String temp="";
		try {
			temp= new String(Files.readAllBytes(Paths.get(path)));
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return temp;

	}
	
	public void writeRequestAndResponseInReport(String request,String response) {
		LogStatus.info("---- Request ---");
		formatAPIAndLogInReport(request);
		LogStatus.info("---- Response ---");
		formatAPIAndLogInReport(response);
	}
	
}
