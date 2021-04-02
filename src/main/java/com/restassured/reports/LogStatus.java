package com.restassured.reports;



public class LogStatus {

	private LogStatus() {
		//private to avoid initialization
	}
	public static void pass(String message)
	{
		ExtentManager.getExtTest().log(com.relevantcodes.extentreports.LogStatus.PASS, message);
		
	}

	public static void fail(String message)
	{
		ExtentManager.getExtTest().log(com.relevantcodes.extentreports.LogStatus.FAIL, message);
	}

	public static void fail(Exception message)
	{
		ExtentManager.getExtTest().log(com.relevantcodes.extentreports.LogStatus.FAIL, message);
	}

	public static void fail(AssertionError a)
	{
		ExtentManager.getExtTest().log(com.relevantcodes.extentreports.LogStatus.FAIL, a);
	}

	public static void info(String message)
	{
		ExtentManager.getExtTest().log(com.relevantcodes.extentreports.LogStatus.INFO, message);
	}

	public static void error(String message)
	{
		ExtentManager.getExtTest().log(com.relevantcodes.extentreports.LogStatus.ERROR, message);
	}

	public static void fatal(String message)
	{
		ExtentManager.getExtTest().log(com.relevantcodes.extentreports.LogStatus.FATAL, message);
	}

	public static void skip(String message)
	{
		ExtentManager.getExtTest().log(com.relevantcodes.extentreports.LogStatus.SKIP, message);
	}

	public static void unknown(String message)
	{
		ExtentManager.getExtTest().log(com.relevantcodes.extentreports.LogStatus.UNKNOWN, message);
	}

	public static void warning(String message)
	{
		ExtentManager.getExtTest().log(com.relevantcodes.extentreports.LogStatus.WARNING, message);
	}
	
}
