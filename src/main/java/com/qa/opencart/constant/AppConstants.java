package com.qa.opencart.constant;

import java.util.Arrays;
import java.util.List;

public class AppConstants {
	
	
	public static final String CONFIG_FILE_PATH = ".\\src\\resources\\config\\config.properties";
	public static final String CONFIG_QA_FILE_PATH = ".\\src\\resources\\config\\qa.config.properties";
	public static final String CONFIG_UAT_FILE_PATH = ".\\src\\resources\\config\\uat.config.properties";
	public static final String CONFIG_DEV_FILE_PATH = ".\\src\\resources\\config\\dev.config.properties";
	public static final String CONFIG_STAGE_FILE_PATH = ".\\src\\resources\\config\\stage.config.properties";
	
	
	public static final String LOGIN_PAGE_TITLE ="Account Login";
	public static final String ACCOUNT_PAGE_TITLE ="My Account";
	
	public static final String LOGIN_PAGE_FRACTION_URL ="route=account/login";
	public static final String ACCOUNT_PAGE_FRACTION_URL ="route=account/account";
	
	public static final List<String> ACC_PAGE_HEADER_LIST = Arrays.asList("My Account","My Orders","My Affiliate Account","Newsletter");


	public static final Object USER_REGISTER_SUCCESS_MSG = "Your Account Has Been Created!";
	public static final String REGISTER_SHEET_NAME = "register";
	public static final String PRODUCT_IMAGES_SHEET = "productimages";


	
	

}
