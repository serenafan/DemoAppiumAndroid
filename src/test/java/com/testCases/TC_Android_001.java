package com.testCases;

import java.io.IOException;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.base.BaseClass;
import com.pageObjects.LandingPage;
import com.pageObjects.RegisterPage;
import com.utilities.CheckPoint;
import com.utilities.getDataFromExcel;



public class TC_Android_001 extends BaseClass{

	@Test(dataProvider="getData")
	public void SignUpTest (String testCaseType, String firstName, String lastName, String email, String password1, String expectedResult ) throws InterruptedException
	{
		logger.info("Start Sign Up test");
		
		//Navigating to landing page 
		LandingPage landingpage=new LandingPage(driver);
		logger.info("Navigating to landing page ");
		
		//Click allow access button
		landingpage.clickAllow();
		logger.info("Click allow access button");
		
		//Click home icon
		landingpage.clickHomeIcon();
		logger.info("Click home icon");
		
		//Click register buton
		landingpage.clickRegisterLoginBtn();
		logger.info("Click register buton");
		
		//Navigating to register/login page
		RegisterPage registerPg= new RegisterPage(driver);
		
		//Select register with email 
		registerPg.registerWithEmail();
		logger.info("Select register with email ");
		
		//Enter first name
		registerPg.EnterFirstName(firstName);
		logger.info("Enter first name: "+firstName);
		
		//Enter last name
		registerPg.EnterLastName(lastName);
		logger.info("Enter last name: "+lastName);
		
		//Enter email
		registerPg.EnterEmail(randomestring(2)+email);
		logger.info("Enter email:"+randomestring(2)+email);
		
		//Enter password
		registerPg.EnterPasswd(password1);
		logger.info("Enter password: "+ password1);
		
		//Click submit button
		registerPg.clickSubmitBtn();
		logger.info("Click submit");
		
		
		//Here to verify if actual result is displayed as expected result
		if(testCaseType.equals("Test case1: positive sign up flow"))
		{
			CheckPoint.validationResult(registerPg.getToolBarTitle(),expectedResult);
		}
		else if (testCaseType.equals("Test case2: invalid email flow"))
		{
			CheckPoint.validationResult(registerPg.getEmailErrMsg(), expectedResult);
		}
		else 
		{
			CheckPoint.validationResult(registerPg.getPassWdErrMsg(), expectedResult);
		}
			
		}
		
		
	
	@DataProvider
	public Object[][] getData() throws IOException {
		String path= System.getProperty("user.dir")+"\\src\\test\\java\\com\\testData\\TC_Android_001_TestData.xlsx";
		return getDataFromExcel.getExcelData(path);
		
	}
}
