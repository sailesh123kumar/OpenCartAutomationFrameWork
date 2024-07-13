package com.qa.opencart.factory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.safari.SafariDriver;

import com.qa.opencart.constant.AppConstants;
import com.qa.opencart.errors.AppError;
import com.qa.opencart.exceptions.BrowserException;
import com.qa.opencart.exceptions.FrameWorkException;

public class DriverFactory {

	// WebDriver driver;
	Properties prop;
	OptionsManager optionsManager;
	public static String highlight;

	public static ThreadLocal<WebDriver> tlocal = new ThreadLocal<WebDriver>();

	/**
	 * This is used to init the driver on the basis of given browser name.
	 * 
	 * @param prop
	 * @return
	 */

	public WebDriver initDriver(Properties prop) {

		String browserName = prop.getProperty("browser");
		String url = prop.getProperty("url");
		highlight = prop.getProperty("highlight");

		System.out.println("browser name is : " + browserName);
		optionsManager = new OptionsManager(prop);

		switch (browserName.trim().toLowerCase()) {
		case "chrome":
			tlocal.set(new ChromeDriver(optionsManager.getChromeOptions()));
			break;

		case "firefox":
			tlocal.set(new FirefoxDriver(optionsManager.getFirefoxOptions()));
			break;

		case "edge":
			tlocal.set(new EdgeDriver(optionsManager.getEdgeOptions()));
			break;

		case "safari":
			tlocal.set(new SafariDriver());
			break;

		default:
			System.out.println("Please pass the right browser name... :" + browserName);
			throw new BrowserException(AppError.BROWSER_NOT_FOUND);
		}

		getDriver().manage().deleteAllCookies();
		getDriver().manage().window().maximize();
		getDriver().get(url);

		return getDriver();
	}

	/**
	 * get the local thread copy of driver
	 * 
	 * @return
	 */
	public static WebDriver getDriver() {
		return tlocal.get();
	}

	/**
	 * This method is used to init the properties from the .properties file
	 * 
	 * @return this return properties (prop)
	 */
	public Properties initProp() {

		prop = new Properties();
		FileInputStream ip = null;

		// mvn clean install -Denv="qa"
		String envName = System.getProperty("env");
		System.out.println("running test suite on env ---> " + envName);

		if (envName == null) {
			System.out.println("enviroment name is not given hence running it in qa enviroment...");
			try {
				ip = new FileInputStream(AppConstants.CONFIG_QA_FILE_PATH);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		} else {

			try {
				switch (envName.trim().toLowerCase()) {

				case "prod":
					ip = new FileInputStream(AppConstants.CONFIG_FILE_PATH);
					break;
				case "qa":
					ip = new FileInputStream(AppConstants.CONFIG_QA_FILE_PATH);
					break;
				case "uat":
					ip = new FileInputStream(AppConstants.CONFIG_UAT_FILE_PATH);
					break;
				case "stage":
					ip = new FileInputStream(AppConstants.CONFIG_STAGE_FILE_PATH);
					break;
				case "dev":
					ip = new FileInputStream(AppConstants.CONFIG_DEV_FILE_PATH);
					break;
				default:
					System.out.println("Please pass the right environment :" + envName);
					throw new FrameWorkException("====WRONG ENVIROMENT PASS!====");
				}

			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		}
		try {
			prop.load(ip);
		} catch (IOException e) {
			e.printStackTrace();
		}

		return prop;
	}

	public static String getScreenshot(String methodName) {

		// Get the driver instance
		TakesScreenshot screenshotTaker = (TakesScreenshot) getDriver();

		// Take the screenshot and save it to a temporary location
		File srcFile = screenshotTaker.getScreenshotAs(OutputType.FILE);

		// Define the path for the screenshots folder
		String screenshotsDirPath = System.getProperty("user.dir") + "/screenshots";

		// Create the screenshots folder if it doesn't exist
		File screenshotsDir = new File(screenshotsDirPath);
		if (!screenshotsDir.exists()) {
			if (screenshotsDir.mkdirs()) {
				System.out.println("Folder 'screenshots' created successfully at: " + screenshotsDirPath);
			} else {
				System.out.println("Failed to create the folder 'screenshots' at: " + screenshotsDirPath);
			}
		}

		// Define the destination path for the screenshot
		String screenshotPath = screenshotsDirPath + "/" + methodName + "_" + System.currentTimeMillis() + ".png";
		File destination = new File(screenshotPath);

		// Copy the screenshot to the destination path
		try {
			FileHandler.copy(srcFile, destination);
		} catch (IOException e) {
			e.printStackTrace();
		}

		return destination.getAbsolutePath();
	}

}
