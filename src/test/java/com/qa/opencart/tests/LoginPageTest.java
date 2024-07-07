package com.qa.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import com.qa.opencart.base.BaseTest;
import com.qa.opencart.constant.AppConstants;
import com.qa.opencart.errors.AppError;
import com.qa.opencart.listeners.AnnotationTransformer;
import com.qa.opencart.listeners.ExtentReportListener;
import com.qa.opencart.pages.AccountsPage;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Issue;
import io.qameta.allure.Owner;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;


@Epic("Epic 100: Design Open Cart application with shopping Workflow")
@Story("US 101: Design login page with open cart application")
@Feature("F50: Feature login page")

//1.Wont work for RetryLogic
//2.Its a bad practice to use it from test,So we should always prefer to use it from xml file
//@Listeners({ExtentReportListener.class , AnnotationTransformer.class}) 
public class LoginPageTest extends BaseTest {

	
	@Description("checking the login page title...")
	@Severity(SeverityLevel.MINOR)
	@Owner("Sailesh Kumar SDET")
	@Issue("Login-1234")
	@Test(priority = 1)
	public void logInPageTitleTest() {
		String logInpageTitle = logInPage.getLoginPageTitle();
		Assert.assertEquals(logInpageTitle, AppConstants.LOGIN_PAGE_TITLE, AppError.TITLE_NOT_FOUND);
	}

	
	@Description("checking the login page URL...")
	@Severity(SeverityLevel.NORMAL)
	@Owner("Sailesh Kumar SDET")
	@Test(priority = 2)
	public void logInPageUrlTest() {
		String actUrl = logInPage.getLoginPageUrl();
		Assert.assertTrue(actUrl.contains(AppConstants.LOGIN_PAGE_FRACTION_URL), AppError.URL_NOT_FOUND);
	}

	
	@Description("checking the forgot password link exists on this page...")
	@Severity(SeverityLevel.CRITICAL)
	@Owner("Sailesh Kumar SDET")
	@Test(priority = 3)
	public void forgotPasswordExistsTest() {
		boolean checkForgotPasswordLinkExist = logInPage.checkForgotPasswordLinkExist();
		Assert.assertTrue(checkForgotPasswordLinkExist, AppError.ELEMENT_NOT_FOUND);
	}

	
	@Description("checking the user is able to login successfully...")
	@Severity(SeverityLevel.BLOCKER)
	@Owner("Sailesh Kumar SDET")
	@Test(priority = 4)
	public void loginTest() {
		accPage = logInPage.doLogIn(prop.getProperty("username"), prop.getProperty("password"));
		Assert.assertEquals(accPage.getAccPageTitle(), AppConstants.ACCOUNT_PAGE_TITLE,AppError.TITLE_NOT_FOUND);
	}

}
