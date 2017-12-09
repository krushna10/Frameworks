package com.org.frameworklib;

import java.io.IOException;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

public class Reporter {

	static ExtentHtmlReporter htmlReporter;
	static ExtentReports extentReports;
	static ExtentTest module;
	static ExtentTest test;

	public static void initializeReporter(String classname) {
		htmlReporter = new ExtentHtmlReporter("TestReports//TestReport.html");
		if (extentReports == null) {
			extentReports = new ExtentReports();
		}
		extentReports.attachReporter(htmlReporter);
		if (Constants.globalProperties.getProperty("Overwrite").equalsIgnoreCase("true")) {
			htmlReporter.setAppendExisting(true);
		}
		module = extentReports.createTest(classname);
	}

	public static void initializeTest(String tcName) {
		test = module.createNode(tcName);
	}

	public static void logSteps(Status status, String step, boolean screenshot)
	{
		test.log(status, step);
		if (screenshot) {
			try {
				test.addScreenCaptureFromPath(WebUtils.takeScreenshot());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public static void writeToHtmlReporter() {
		extentReports.flush();
	}
}
