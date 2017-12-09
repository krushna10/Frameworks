package com.org.frameworklib;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

public class WebUtils {

	static boolean isDisplayed;
	public static ThreadLocal<WebDriver> webdriver;
	
	public static void initWebDriver() throws InterruptedException
	{
		WebDriver webDriver;
		if(Constants.globalProperties.getProperty("Browser").equalsIgnoreCase("FF")
				|| Constants.globalProperties.getProperty("Browser").equalsIgnoreCase("Firefox"))
		{
		//System.setProperty("webdriver.gecko.driver", "Drivers//geckodriver.exe");
		webDriver = new FirefoxDriver();
		webdriver.set(webDriver);
		}else if(Constants.globalProperties.getProperty("Browser").equalsIgnoreCase("Chrome"))
		{
			System.setProperty("webdriver.chrome.driver", "Drivers//chromedriver.exe");
			webDriver = new ChromeDriver();
			webdriver.set(webDriver);
		}
		
		Thread.sleep(2000);
	}
	
	public static WebDriver getWebDriver()
	{
		return webdriver.get();
	}
	public static boolean isWebElementDisplayed(WebElement webelement)
	{
		try
		{
		isDisplayed = webelement.isDisplayed();
		}catch(Exception e)
		{
			isDisplayed = false;
		}
		return isDisplayed;
	}
	
	
	public static void setText(WebElement element,String text)
	{
		if(isWebElementDisplayed(element)){
			element.clear();
			element.sendKeys(text);
		}
	}
	
	public static void selectDropDown(WebElement element,String value)
	{
		Select select = new Select(element);
		select.selectByValue(value);
	}

	public static String takeScreenshot() throws IOException {
		File src= ((TakesScreenshot)getWebDriver()).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(src, new File("TestReports//Screenshots"));
		return "TestReports//Screenshots//"+src.getName();
	}
}
