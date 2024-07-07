package com.qa.opencart.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.constant.AppConstants;
import com.qa.opencart.utils.ElementUtil;
import com.qa.opencart.utils.TimeUtil;

public class AccountsPage {

	private WebDriver driver;
	private ElementUtil eleUtil;

	public AccountsPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(driver);
	}

	private By logoutLink = By.linkText("Logout");
	private By headers = By.cssSelector("div#content h2");
	private By search = By.name("search");
	private By searchBtn = By.cssSelector("div#search button");

	public String getAccPageTitle() {
		String title = eleUtil.waitForTitleTobe(AppConstants.ACCOUNT_PAGE_TITLE, TimeUtil.DEFAULT_TIME);
		System.out.println("page title is : " + title);
		return title;
	}

	public String getAccPageUrl() {
		String url = eleUtil.waitForURLContains(AppConstants.ACCOUNT_PAGE_FRACTION_URL, TimeUtil.DEFAULT_TIME);
		System.out.println("page url is : " + url);
		return url;
	}

	public boolean isLogOutLinkExist() {
		return eleUtil.doIsDisplayed(logoutLink);
	}

	public boolean issearchExist() {
		return eleUtil.doIsDisplayed(search);
	}

	public List<String> getAccHeaders() {
		
		List<WebElement> headersList = eleUtil.waitForVisibilityofElementsLocated(headers, TimeUtil.DEFAULT_TIME);
		List<String> headersTextList = new ArrayList<String>();
		for (WebElement e : headersList) {
			String text = e.getText();
			headersTextList.add(text);
		}		
		
		return headersTextList;

	}

	public SearchResultsPage doSearch(String searchkey) {
		System.out.println("searching.... : " + searchkey);
		if (issearchExist()) {
			eleUtil.doSendKeys(search, searchkey);
			eleUtil.doClick(searchBtn);
			return new SearchResultsPage(driver);
		} else {
			System.out.println("search field is not present on the page");
			return null;
		}

	}

}
