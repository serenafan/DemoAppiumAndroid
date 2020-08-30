package com.pageObjects;

import org.openqa.selenium.By;

import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class LandingPage {
	AndroidDriver<AndroidElement> ldriver;
	
public LandingPage(AndroidDriver<AndroidElement> driver) {
	ldriver=driver;
	PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}

    //Find allow location access button
    @AndroidFindBy(id="com.android.permissioncontroller:id/permission_allow_foreground_only_button")
    WebElement allowLocationAccess;

    //Find home icon
	@AndroidFindBy(id="com.freshii.freshii:id/imgHome")
    WebElement homeIcon;
	
	//Find Register/Login icon
	@AndroidFindBy(xpath="//*[@text='register / login']")
    WebElement registerLoginIcon;
	
	
	//Click allow 
	public void clickAllow () {
		WebDriverWait wait = new WebDriverWait(ldriver, 10);
		wait.until(ExpectedConditions.elementToBeClickable(allowLocationAccess));
		allowLocationAccess.click();
		
	}
	
	
	//Click home icon
	public void clickHomeIcon () throws InterruptedException {
		retryingFindClick(By.id("com.freshii.freshii:id/imgHome"));
	}
	
	//Click register/Login icon
	public void clickRegisterLoginBtn () {
		WebDriverWait wait = new WebDriverWait(ldriver, 10);
		wait.until(ExpectedConditions.elementToBeClickable(registerLoginIcon));
		registerLoginIcon.click();
		
	}

	//dealing with StaleElementReferenceException error 
	public void retryingFindClick(By by) throws InterruptedException {
		    int attempts = 0;
		    while(attempts < 5) {
		        try {
		           Thread.sleep(10000);
		           ldriver.findElement(by).click();
		            break;
		        } catch(StaleElementReferenceException e) {
		        }
		        attempts++;
		    }
		    
		}

}
