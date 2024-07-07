package com.qa.opencart.utils;

public class StringUtils {
	
	
	public static String getRandomEmailId() {
		String emailId = "openkart" + System.currentTimeMillis() + "@gmail.com";
		System.out.println("user email id is : " +emailId);
		return emailId;
	}

}
