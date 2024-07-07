package com.qa.opencart.tests;

import static org.testng.Assert.*;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.constant.AppConstants;
import com.qa.opencart.errors.AppError;
import com.qa.opencart.pages.SearchResultsPage;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;

@Epic("Epic 200: Design Open Cart Account page")
@Story("US 201: Account page BRD")
@Feature("F51: Feature Account page")
public class AccountsPageTest extends BaseTest  {
	
	@Description("setup method to login and navigate to account page...")
	@BeforeClass
	public void AccSetUp() {
		accPage = logInPage.doLogIn(prop.getProperty("username"), prop.getProperty("password"));
	}
	

	@Description("Test Case:Verify the title of account page. This test ensures that the account page title matches the expected value defined in AppConstants.")
	@Severity(SeverityLevel.NORMAL)
	@Test
	public void accountPageTitleTest() {
		assertEquals(accPage.getAccPageTitle(), AppConstants.ACCOUNT_PAGE_TITLE, AppError.TITLE_NOT_FOUND);
	}
	
	
	@Description("Test Case:Verify the URL of account page. This test ensures that the account page URL matches the expected value defined in AppConstants.")
	@Severity(SeverityLevel.NORMAL)
	@Test
	public void accountPageUrltest(){
		assertTrue(accPage.getAccPageUrl().contains(AppConstants.ACCOUNT_PAGE_FRACTION_URL), AppError.URL_NOT_FOUND);
	}
	
	
	@Description("Test Case:Verify the headeras on the account page. This test checks that the headers on the account page match the expected headers defined in AppConstants.")
	@Severity(SeverityLevel.MINOR)
	@Test
	public void accPageHeadersTest() {
		List<String> accHeadersTextList = accPage.getAccHeaders();
		Assert.assertEquals(accHeadersTextList, AppConstants.ACC_PAGE_HEADER_LIST,AppError.LIST_IS_NOT_MATCHED);
	}
	
	@DataProvider
	public Object[][] getSearchData() {
		return new Object [][] {
			{"macbook" , 3 ,},
			{"imac" , 1},
			{"samsung" , 2},
			{"Airtel" , 0}
		};
	}
	
	
	@Description("Test Case:Verify search functionality on the account page. This test uses a data provider to check multiple search keywords and their expected result counts.")
	@Severity(SeverityLevel.CRITICAL)
	@Test(dataProvider = "getSearchData" )
	public void searchTest(String searchKey , int productExpCount) {
		searchResultsPage = accPage.doSearch(searchKey);
		assertEquals(searchResultsPage.getSearchResultsCount(), productExpCount ,AppError.RESULTS_COUNT_MISMATCHED);
	}

}
