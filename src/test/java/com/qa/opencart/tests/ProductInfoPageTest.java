package com.qa.opencart.tests;

import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.constant.AppConstants;
import com.qa.opencart.errors.AppError;
import com.qa.opencart.utils.ExcelUtil;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;

@Epic("Epic 300: Design Open Cart product info page")
@Story("US 301: Produc information page BRD")
@Feature("F52: Feature product info page")

public class ProductInfoPageTest extends BaseTest {
	
	@BeforeClass
	@Description("setup method to login and navigate to product information page...")
	public void productInfoPageSetUp() {
		accPage = logInPage.doLogIn(prop.getProperty("username"), prop.getProperty("password"));
	}
	
	@DataProvider
	public Object[][] getProductData() {
		return new Object[][]
		{
			{"macbook","MacBook Pro"},
			{"imac","iMac"},
			{"samsung","Samsung SyncMaster 941BW"},
			{"samsung","Samsung Galaxy Tab 10.1"},
			{"canon","Canon EOS 5D"}
			
		};
	}
	
	
	@Description("Test Case:Verify the product Header. This test ensures that the product header matches the expected product name...")
	@Severity(SeverityLevel.CRITICAL)
	@Test(dataProvider = "getProductData")
	public void productHeaderTest(String searchKey , String product) {
		
	 searchResultsPage = accPage.doSearch(searchKey);
	 productInfoPage = searchResultsPage.selectProduct(product);
	 Assert.assertEquals(productInfoPage.getProductHeader(),product,AppError.HEADER_NOT_FOUND);
	 
	}
	
	@DataProvider
	public Object[][] getProductDataWithImages() {
		return new Object[][]
		{
			{"macbook","MacBook Pro","4"},
			{"imac","iMac","3"},
			{"samsung","Samsung SyncMaster 941BW","1"},
			{"samsung","Samsung Galaxy Tab 10.1","7"},
			{"canon","Canon EOS 5D","3"}
			
		};
	}
	

	@DataProvider
	public Object[][] getProductDataWithImagesfromsheet() {
		Object[][] testData = ExcelUtil.getTestData(AppConstants.PRODUCT_IMAGES_SHEET);
		return testData;
	}
	

	//getProductDataWithImagesfromsheet if we use this method convert imagecount as string
	@Description("Test Case:Verify the product ImagesCount. This test ensures that the number of images on each product matches the expected count")
	@Severity(SeverityLevel.CRITICAL)
	@Test(dataProvider = "getProductDataWithImagesfromsheet")
	public void productImagesCount(String searchKey, String product , String imagesCount) {
		searchResultsPage = accPage.doSearch(searchKey);
		productInfoPage = searchResultsPage.selectProduct(product);
		Assert.assertEquals(productInfoPage.getProductImagesCount(), Integer.parseInt(imagesCount) , AppError.IMAGES_NOT_MATCHED);
	}
	
	@Description("Test Case:Verify the product information. This test checks multiple product information fields using soft assertions")
	@Severity(SeverityLevel.BLOCKER)
	@Test
	public void productInfoTest() {
		searchResultsPage = accPage.doSearch("macbook");
		productInfoPage = searchResultsPage.selectProduct("MacBook Pro");
		Map<String, String> productInfoMap = productInfoPage.getProductInfoMap();
		System.out.println("========Product Info Data========");
		System.out.println(productInfoMap);
		
		/*
		Assert.assertEquals(productInfoMap.get("Availability"), "In Stock");
		//For instance : ("Brand"), "Apple11") ; Immediately further lines will get fail
		Assert.assertEquals(productInfoMap.get("Brand"), "Apple");     
		Assert.assertEquals(productInfoMap.get("Exclude Tax Price"), "$2,000.00");
		Assert.assertEquals(productInfoMap.get("Product Code"), "Product 18");
		Assert.assertEquals(productInfoMap.get("Product Price"), "$2,000.00");
		Assert.assertEquals(productInfoMap.get("Reward Points"), "800");
		Assert.assertEquals(productInfoMap.get("productImagesCount"), "4");
		Assert.assertEquals(productInfoMap.get("productName"), "MacBook Pro");
		*/
		
		
		//Test with multiple assertion should use soft assert
		//Generall we shold follow 1 method 1 test if multiple comes to test use soft assert
		
		softassert.assertEquals(productInfoMap.get("Availability"), "In Stock");
		//For instance : ("Brand"), "Apple11") ; Immediately further lines will get fail
		softassert.assertEquals(productInfoMap.get("Brand"), "Apple");     
		softassert.assertEquals(productInfoMap.get("Exclude Tax Price"), "$2,000.00");
		softassert.assertEquals(productInfoMap.get("Product Code"), "Product 18");
		softassert.assertEquals(productInfoMap.get("Product Price"), "$2,000.00");
		softassert.assertEquals(productInfoMap.get("Reward Points"), "800");
		softassert.assertEquals(productInfoMap.get("productImagesCount"), "4");
		softassert.assertEquals(productInfoMap.get("productName"), "MacBook Pro");
		
		softassert.assertAll();  //Failure info
		//Eventhough test get fail but still it execute all softassert
		
		
	}
	
	// hard assert (Assert) vs soft Assert (verify)
	// Assert ---> method (static)
	// SoftAssert ---> method (non static)
	
	//Single Assertion - hard Assertion
	//multiple assertion - soft assertion
	
	

}
