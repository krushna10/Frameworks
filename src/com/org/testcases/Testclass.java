package com.org.testcases;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.org.frameworklib.Constants;
import com.org.frameworklib.Reporter;
import com.org.pageobjects.LoginPage;

public class Testclass {

	LoginPage login;
	@BeforeClass
	public void init()
	{
		Reporter.initializeReporter(this.getClass().getName());
	}
	
	@Test
	public void TC_01()
	{
		try{
		login = new LoginPage();
		login.get();
		Reporter.logSteps(Status.PASS, "Log step 1", false);
		Reporter.logSteps(Status.FAIL, "Log step 2", false);
		login.signIn(Constants.testDataMap.get("username"), Constants.testDataMap.get("password"));
	}catch(Exception e)
		{
		Reporter.logSteps(Status.FAIL, e.getMessage(), false);
		}
	}
}
