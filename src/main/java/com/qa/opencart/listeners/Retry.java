package com.qa.opencart.listeners;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class Retry implements IRetryAnalyzer {
	private int count = 0;
	private static int maxTry = 3;
	
	@Override
	public boolean retry(ITestResult iTestResult) {
		
		if (!iTestResult.isSuccess()) { // Check if test not succeed
			if (count < maxTry) { // Check if maxtry count is reached
				count++; // Increase the maxTry count by 1
				iTestResult.setStatus(ITestResult.FAILURE); // Mark test as failed
				return true; // Tells TestNG to re-run the test
			} else {
				iTestResult.setStatus(ITestResult.FAILURE); // If maxCount reached,test marked as failed
			}
		} else {
			iTestResult.setStatus(ITestResult.SUCCESS); // If test passes, TestNG marks it as passed
		}
		return false;
	}


  //  private int retryCount = 0; // Tracks the current retry count
 //   private static final int MAX_RETRIES = 3; // Maximum number of retries

    /**
     * Determines whether a failed test should be retried.
     * 
     * @param iTestResult The result of the test method execution.
     * @return true if the test should be retried, false otherwise.
     */
 //   @Override
  //  public boolean retry(ITestResult iTestResult) {
   //     if (retryCount < MAX_RETRIES) {
     //       retryCount++;
       //     return true; // Retry the test
  //      }
 //       return false; // Do not retry
 //   }


	

}
