package com.pageObjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class RegisterPage {
	AndroidDriver<AndroidElement> ldriver;
	
public RegisterPage(AndroidDriver<AndroidElement> driver) {
	ldriver=driver;
	PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}

    //Locators of elements:

    //Find register with email button
    @AndroidFindBy(id="com.freshii.freshii:id/txtRegisterWithEmail")
    WebElement EmailRegisterBtn;
    
    //Find firstname field
    @AndroidFindBy(id="com.freshii.freshii:id/edtFirstName")
    WebElement firstnameField;
    
    //Find lastname field
    @AndroidFindBy(id="com.freshii.freshii:id/edtLastName")
    WebElement lastnameField;
	
    //Find email field
    @AndroidFindBy(id="com.freshii.freshii:id/edtEmail")
    WebElement emailField;
    
    //Find password
    @AndroidFindBy(id="com.freshii.freshii:id/edtPassword")
    WebElement passWdField;
    
    //Find submit
    @AndroidFindBy(id="com.freshii.freshii:id/btnRegister")
    WebElement submitBtn;
    
    //Find toolbar title
    @AndroidFindBy(xpath="//*[@text='add payment method']")
    WebElement toolBarTitle;
    
    //Find email error msg
    @AndroidFindBy(xpath="//*[@text='please enter a valid email']")
    WebElement emailErrorMsg;
    
    //Find password error msg
    @AndroidFindBy(xpath="//*[@text='please enter a valid password (minimum 8 characters with at least 1 capital letter and 1 number)']")
    WebElement passWdErrMsg;
    
    
    //Actions: 
	
	//Click register with email  
	public void registerWithEmail () 
	{
		WebDriverWait wait = new WebDriverWait(ldriver, 5);
		wait.until(ExpectedConditions.elementToBeClickable(EmailRegisterBtn));
		EmailRegisterBtn.click();
	}
	
	//Enter First Name
	public void EnterFirstName(String firstName)
	{
		WebDriverWait wait = new WebDriverWait(ldriver, 5);
		wait.until(ExpectedConditions.elementToBeClickable(firstnameField));
		firstnameField.sendKeys(firstName);
	}
	
	//Enter last name
	public void EnterLastName(String lastName)
	{
		WebDriverWait wait = new WebDriverWait(ldriver, 5);
		wait.until(ExpectedConditions.elementToBeClickable(lastnameField));
		lastnameField.sendKeys(lastName);
	}
	
	//Enter email
	public void EnterEmail(String email)
	{
		WebDriverWait wait = new WebDriverWait(ldriver, 5);
		wait.until(ExpectedConditions.elementToBeClickable(emailField));
		emailField.sendKeys(email);
	}
	
	//Enter password
	public void EnterPasswd(String password)
	{
		WebDriverWait wait = new WebDriverWait(ldriver, 5);
		wait.until(ExpectedConditions.elementToBeClickable(passWdField));
		passWdField.sendKeys(password);
	}
	
	//Enter button
	public void clickSubmitBtn()
	{
		WebDriverWait wait = new WebDriverWait(ldriver, 5);
		wait.until(ExpectedConditions.elementToBeClickable(submitBtn));
		submitBtn.click();
	
	}
	
	//Get toolbar title text
	public String getToolBarTitle()
	{
		WebDriverWait wait = new WebDriverWait(ldriver, 5);
		wait.until(ExpectedConditions.elementToBeClickable(toolBarTitle));
		return toolBarTitle.getText();
	
	}
	
	//Get email error msg
	public String getEmailErrMsg()
	{
		WebDriverWait wait = new WebDriverWait(ldriver, 5);
		wait.until(ExpectedConditions.elementToBeClickable(emailErrorMsg));
		return emailErrorMsg.getText();
	
	}
	
	//Get password error msg
	public String getPassWdErrMsg()
	{
		WebDriverWait wait = new WebDriverWait(ldriver, 5);
		wait.until(ExpectedConditions.elementToBeClickable(passWdErrMsg));
		return passWdErrMsg.getText();
	
	}
	
		
		
		
}
	





