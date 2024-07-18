package com.qa.opencart.base;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.asserts.SoftAssert;

import com.beust.jcommander.Parameter;
import com.qa.opencart.factory.DriverFactory;
import com.qa.opencart.pages.AccountsPage;
import com.qa.opencart.pages.LoginPage;
import com.qa.opencart.pages.ProductInfoPage;
import com.qa.opencart.pages.RegistrationPage;
import com.qa.opencart.pages.SearchResultsPage;

import io.qameta.allure.Step;

public class BaseTest {
	
	
	DriverFactory df;
	WebDriver driver;
	protected Properties prop;
	protected LoginPage logInPage;
	protected AccountsPage accPage;
	protected SearchResultsPage searchResultsPage;
	protected ProductInfoPage productInfoPage;
	protected RegistrationPage registrationPage;
	protected SoftAssert softassert;
	
	@Step("setup for the test initializing browser : {0}")
	@Parameters({"browser","browserversion","testname"})
	@BeforeTest
	//public void setUp(@Optional("firefox") String browserName) { //This will make firefox as default browser
	public void setUp(@Optional String browserName , @Optional String browserVersion , @Optional String testName) {
		df = new DriverFactory();
		prop = df.initProp();
		
		if(browserName!=null) {
			prop.setProperty("browser", browserName);
			prop.setProperty("browserversion", browserVersion);
			prop.setProperty("testname", testName);
			
			System.out.println("Browser:"+browserName +"=BrowserVersion:"+browserVersion+"=TestName:"+testName);
		}
		
		driver = df.initDriver(prop);
		logInPage = new LoginPage(driver);
		softassert = new SoftAssert();
	}
	
	@AfterTest
	public void tearDown() {
		driver.quit();
	}

}
