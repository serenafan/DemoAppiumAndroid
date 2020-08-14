package com.utilities;

import org.testng.Assert;

import com.base.BaseClass;

public class CheckPoint extends BaseClass {
	public static void validationResult(String expectText, String actualText)
	{
		if(actualText.equalsIgnoreCase(expectText)){
			Assert.assertEquals(expectText, actualText);
			logger.info(actualText+ " is displayed as expected");
		}
		
		else {
			Assert.assertEquals(expectText, actualText);
			logger.info(actualText+ " is not displayed as expected");
		}
		
		
	}
	


}
