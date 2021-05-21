package com.restassured.reports;


import static com.relevantcodes.extentreports.LogStatus.*;

public class LogStatus {

	private LogStatus() {
		//private to avoid initialization
	}
	public static void pass(String message)
	{
		ExtentManager.getExtTest().log(PASS, message);
		
	}

	public static void fail(String message)
	{
		ExtentManager.getExtTest().log(FAIL, message);
	}

	public static void fail(Exception message)
	{
		ExtentManager.getExtTest().log(FAIL, message);
	}

	public static void fail(AssertionError a)
	{
		ExtentManager.getExtTest().log(FAIL, a);
	}

	public static void info(String message)
	{
		ExtentManager.getExtTest().log(INFO, message);
	}

	public static void error(String message)
	{
		ExtentManager.getExtTest().log(ERROR, message);
	}

	public static void fatal(String message)
	{
		ExtentManager.getExtTest().log(FATAL, message);
	}

	public static void skip(String message)
	{
		ExtentManager.getExtTest().log(SKIP, message);
	}

	public static void unknown(String message)
	{
		ExtentManager.getExtTest().log(UNKNOWN, message);
	}

	public static void warning(String message)
	{
		ExtentManager.getExtTest().log(WARNING, message);
	}
	
}
