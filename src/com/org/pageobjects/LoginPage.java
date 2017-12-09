package com.org.pageobjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.LoadableComponent;
import org.testng.Assert;

import com.org.frameworklib.Constants;
import com.org.frameworklib.WebUtils;

public class LoginPage extends LoadableComponent<LoginPage> {

	@FindBy(name = "userName")
	private WebElement txtUserName;
	@FindBy(name = "password")
	private WebElement txtPassWord;
	@FindBy(name = "login")
	private WebElement btnLogin;

	public LoginPage() {
		PageFactory.initElements(WebUtils.getWebDriver(), this);
	}

	@Override
	protected void isLoaded() throws Error {
		Assert.assertTrue(WebUtils.isWebElementDisplayed(txtUserName));
	}

	@Override
	protected void load() {
		WebUtils.getWebDriver().get(Constants.globalProperties.getProperty("url"));
	}
	
	public void signIn(String username,String password)
	{
		WebUtils.setText(txtUserName, username);
		WebUtils.setText(txtPassWord, password);
		btnLogin.click();
		
	}

}
