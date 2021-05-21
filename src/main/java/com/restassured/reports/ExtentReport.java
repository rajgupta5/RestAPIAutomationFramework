package com.restassured.reports;

import java.io.File;

import com.relevantcodes.extentreports.ExtentReports;
import com.restassured.constants.Constants;


public final class ExtentReport {

	public static ExtentReports report=null;

	//To avoid external initialization
	private ExtentReport() {
		report=new ExtentReports(Constants.EXTENTREPORTPATH);
		report.loadConfig(new File(Constants.EXTENTCONFIGFILEPATH));
	}

	public static void initialize()
	{
		new ExtentReport();
	}

}
