package com.qa.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.constant.AppConstants;
import com.qa.opencart.errors.AppError;
import com.qa.opencart.utils.CSVUtil;
import com.qa.opencart.utils.ExcelUtil;
import com.qa.opencart.utils.StringUtils;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;


@Epic("Epic 400: Design Open Cart Registration page")
@Story("US 401: Registration page BRD")
@Feature("F53: Feature Registration page")
public class RegistrationPageTest extends BaseTest {
	
	@Description("setup method to login and navigate to registration page...")
	@BeforeClass
	public void regSetUp() {
		registrationPage = logInPage.navigateToRegisterPage();
	}

	@DataProvider
	public Object[][] userRegTestData() {
		return new Object[][] 
			{ 
				{ "kavi", "selenium", "9988776655", "kavi@123", "yes" },
				{ "sail", "selenium", "9988776655", "sail@123", "yes" },
				{ "kumar", "selenium", "9988776655", "kumar@123", "no" } 
		    };
	}
	
	
	@DataProvider
	public Object[][] userRegTestDataFromSheet(){
		Object[][] testData = ExcelUtil.getTestData(AppConstants.REGISTER_SHEET_NAME);
		return testData;
	}
	
	@DataProvider
	public Object[][] userRegTestDataFromCSV(){
		Object[][] testData = CSVUtil.csvData("Latestcsv");
		return testData;
		
	}
	
	@Description("Test Case:Verify user Rergistration. This test check if a user can register successfully using data from the provided datasheet.")
	@Severity(SeverityLevel.BLOCKER)
	@Test(dataProvider = "userRegTestDataFromCSV")
	public void registrationTest(String firstName, String lastName, String phone, String password, String subscribe) {
		boolean flag = registrationPage.userRegister(firstName, lastName,StringUtils.getRandomEmailId(), phone, password, subscribe);
		Assert.assertTrue(flag,AppError.USER_REG_NOT_DONE);

	}

}
