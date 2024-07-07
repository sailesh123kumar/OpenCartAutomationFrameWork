package com.qa.opencart.pages;

import javax.annotation.processing.SupportedSourceVersion;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.constant.AppConstants;
import com.qa.opencart.errors.AppError;
import com.qa.opencart.utils.ElementUtil;
import com.qa.opencart.utils.StringUtils;
import com.qa.opencart.utils.TimeUtil;

public class RegistrationPage {

	private WebDriver driver;
	private ElementUtil eleUtil;

	public RegistrationPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(driver);
	}

	private By firstName = By.id("input-firstname");
	private By lastName = By.id("input-lastname");
	private By email = By.id("input-email");
	private By phone = By.id("input-telephone");
	private By password = By.id("input-password");
	private By confirm = By.id("input-confirm");

	private By subscribeYes = By.xpath("//label[normalize-space()='Yes']//input[@name='newsletter']");
	private By subscribeNo = By.xpath("//label[normalize-space()='No']//input[@name='newsletter']");

	private By checkbox = By.name("agree");
	private By continueBtn = By.xpath("//input[@value='Continue']");

	private By logout = By.linkText("Logout");
	private By registerLink = By.linkText("Register");
	private By actCreated = By.cssSelector("div[id='content'] h1");


	
	public boolean userRegister(String firstName , String lastName ,String email , String phone , String password , String subscribe) {
		
		eleUtil.doSendKeys(this.firstName, firstName, TimeUtil.DEFAULT_MEDIUM_TIME);
		eleUtil.doSendKeys(this.lastName, lastName);
		eleUtil.doSendKeys(this.email, email);
		eleUtil.doSendKeys(this.phone, phone);
		eleUtil.doSendKeys(this.password, password);
		eleUtil.doSendKeys(this.confirm, password);
		
		if (subscribe.equalsIgnoreCase("yes")) {
			eleUtil.doClick(subscribeYes);
		}
		else {
			eleUtil.doClick(subscribeNo);
		}
		
		eleUtil.doClick(checkbox);
		eleUtil.doClick(continueBtn);
		
		String successMesg = eleUtil.waitForElementVisible(actCreated, TimeUtil.DEFAULT_MEDIUM_TIME).getText();
		System.out.println(successMesg);
		
		if(successMesg.equals(AppConstants.USER_REGISTER_SUCCESS_MSG)) {
			eleUtil.doClick(logout);
			eleUtil.doClick(registerLink);
			return true;
		}
		else
		{
            System.out.println("User not Created");
			return false;
		}
		
		
		
	}

}
