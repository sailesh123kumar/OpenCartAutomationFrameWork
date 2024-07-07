package com.qa.opencart.pages;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.utils.ElementUtil;
import com.qa.opencart.utils.TimeUtil;

public class ProductInfoPage {

	private WebDriver driver;
	private ElementUtil eleUtil;

	public ProductInfoPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(driver);
	}
	
	private By productHeader = By.cssSelector("div#content h1");
	private By productImages = By.cssSelector(".thumbnails a");
	private By productMetaData = By.xpath("(//div[@id='content']//ul[@class='list-unstyled'])[1]/li");
	private By priceData = By.xpath("(//div[@id='content']//ul[@class='list-unstyled'])[2]/li");
	
	
	private Map<String, String> productMap;
	
	public String getProductHeader() {
		String header = eleUtil.doGetText(productHeader);
		System.out.println("product header ===> " +header);
		return header;
		
	}
	
	public int getProductImagesCount() {
		int productImagesCount = eleUtil.waitForVisibilityofElementsLocated(productImages, TimeUtil.DEFAULT_MEDIUM_TIME).size();
	    System.out.println(productImagesCount);
	    return productImagesCount;
	}
	
	
	public Map<String, String> getProductInfoMap() {
		//productMap = new HashMap<String, String>();  //Random order
		//productMap = new LinkedHashMap<String, String>();  //Insertion order
		productMap = new TreeMap<String, String>();  //Key sorted order
		productMap.put("productName", getProductHeader());
		productMap.put("productImagesCount", String.valueOf(getProductImagesCount()));
		getProductMetaData();
		getProductPriceData();
		return productMap;
	}
	
	private void getProductMetaData() {
		List<WebElement> metaList = eleUtil.getElements(productMetaData);
		for (WebElement e : metaList) {
			String text = e.getText();
			String[] meta = text.split(":");
			
			String metaKey = meta[0].trim();
			String metavalue = meta[1].trim();
			productMap.put(metaKey, metavalue);
		}
		
	}
	
	
	private void getProductPriceData() {
		
		List<WebElement> priceList = eleUtil.getElements(priceData);
		String productPrice = priceList.get(0).getText();
		String excludeTaxPrice = priceList.get(1).getText().split(":")[1].trim();
		productMap.put("Product Price", excludeTaxPrice);
		productMap.put("Exclude Tax Price", productPrice);
	}

}
