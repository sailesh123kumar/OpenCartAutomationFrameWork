package com.qa.opencart.pages;

import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.constant.AppConstants;
import com.qa.opencart.utils.ElementUtil;
import com.qa.opencart.utils.TimeUtil;

import io.qameta.allure.Step;

public class LoginPage {

	private WebDriver driver;
	private ElementUtil eleUtil;

	// 1.Page object : By Locators
	
	private By emailId = By.id("input-email");
	private By password = By.id("input-password");
	private By logInBtn = By.xpath("//input[@value='Login']");
	private By forgorPwdLink = By.linkText("Forgotten Password");
	private By registerLink = By.linkText("Register");

	// 2.public const.. of the page
	
	public LoginPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(driver);
	}
	
	
	// 3. public page actions/methods
	@Step("Getting the Login Page title...")
	public String getLoginPageTitle() {
		String title = eleUtil.waitForTitleTobe(AppConstants.LOGIN_PAGE_TITLE, TimeUtil.DEFAULT_TIME);
		System.out.println("page title is : "+title);
		return title;
	}
	
	@Step("Getting the Login Page URL...")
	public String getLoginPageUrl() {
		String url = eleUtil.waitForURLContains(AppConstants.LOGIN_PAGE_FRACTION_URL, TimeUtil.DEFAULT_TIME);
		System.out.println("page url is : "+url);
		return url;
	}
	
	@Step("Getting the state of forgot password link exists...")
	public boolean checkForgotPasswordLinkExist() {
		return eleUtil.doIsDisplayed(forgorPwdLink);
	}
	
	
	@Step("Log in to the application with username: {0} and password: {1}")
	public AccountsPage doLogIn(String userName , String Password) {
		
		eleUtil.doSendKeys(emailId, userName, TimeUtil.DEFAULT_MEDIUM_TIME);
		eleUtil.doSendKeys(password, Password);
		eleUtil.doClick(logInBtn);
		return new AccountsPage(driver);  //TestDriven Development Approach + zigzag chaining/Page Chaining
		
	}
	
	@Step("Navigating to the register page...")
	public RegistrationPage navigateToRegisterPage() {
		eleUtil.doClick(registerLink, TimeUtil.DEFAULT_MEDIUM_TIME);
		return new RegistrationPage(driver);
	}
	
	



}
