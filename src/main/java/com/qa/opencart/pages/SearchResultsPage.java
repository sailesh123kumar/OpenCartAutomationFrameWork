package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.utils.ElementUtil;
import com.qa.opencart.utils.TimeUtil;

public class SearchResultsPage {

	private WebDriver driver;
	private ElementUtil eleUtil;
	
	public SearchResultsPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(driver);
	}
	
	private By searchResult= By.cssSelector("div.product-thumb");
	
	
	public int getSearchResultsCount() {
		int searchResultCount = eleUtil.waitForVisibilityofElementsLocated(searchResult,TimeUtil.DEFAULT_MEDIUM_TIME).size();
		System.out.println("product search result count : " +searchResultCount);
		return searchResultCount;
	}
	
	public ProductInfoPage selectProduct(String productName) {
		By selectProduct= By.linkText(productName);
		eleUtil.doClick(selectProduct, TimeUtil.DEFAULT_MEDIUM_TIME);
		return new ProductInfoPage(driver);
	}

}





