package com.utilities;

import org.testng.Assert;

import com.base.BaseClass;

public class CheckPoint extends BaseClass {
	public static void validationResult(String actualText, String expectText)
	{
		if(actualText.equalsIgnoreCase(expectText)){
			Assert.assertEquals(actualText,expectText);
			logger.info(actualText+ " is displayed as expected");
		}
		
		else {
			Assert.assertEquals(actualText,expectText);
			logger.info(actualText+ " is not displayed as expected");
		}
		
		
	}
	


}
