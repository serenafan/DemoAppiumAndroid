package com.utilities;

import java.io.IOException;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.base.BaseClass;



public class AppiumListener extends BaseClass implements ITestListener {
	public ExtentHtmlReporter htmlReporter;
	public ExtentHtmlReporter htmlReporter1;
	public ExtentReports extent;
	public ExtentReports extent1;
	public ExtentTest test;
	public ExtentTest test1;


	public void onTestStart(ITestResult result) {
	}
		
	

	public void onTestSuccess(ITestResult result) {
    	System.out.println("Passed Test");
		String feature = result.getMethod().getRealClass().getName()+ ":" + result.getMethod().getMethodName();
		feature = feature.substring(feature.lastIndexOf(".") + 1);	
		test=extent.createTest(feature); // create new entry in the report
		//test=extent.createTest(result.getName()); // create new entry in the report
		test.log(Status.PASS, "Test Case PASSED IS " + feature);
		//Get screenshot
    	String screenshotPath=screenshot(result.getMethod().getMethodName());
		//Attach screenshot to extent report
		try {
			test.addScreenCaptureFromPath(screenshotPath);// adding screen shot	
		} catch (IOException e) {
				e.printStackTrace();
		}
		
		test1=extent1.createTest(feature); // create new entry in the report
		//test=extent.createTest(result.getName()); // create new entry in the report
		test1.log(Status.PASS, "Test Case PASSED IS " + feature);

		try {
			test1.addScreenCaptureFromPath(screenshotPath);// adding screen shot	
		} catch (IOException e) {
				e.printStackTrace();
		}
    }
		
	

	public void onTestFailure(ITestResult result) {
    	System.out.println("Failed Test");
		String feature = result.getMethod().getRealClass().getName()+ ":" + result.getMethod().getMethodName();
		feature = feature.substring(feature.lastIndexOf(".") + 1);	
		test=extent.createTest(feature); // create new entry in the report
		test.log(Status.FAIL, "Test Case FAILED IS " + feature);
		test.log(Status.FAIL, "TEST CASE FAILED IS " + result.getThrowable());
    	//Get screenshot
    	String screenshotPath= screenshot(result.getMethod().getMethodName());
		//Attach screenshot to extent report
		try {
			test.addScreenCaptureFromPath(screenshotPath);// adding screen shot	
		} catch (IOException e) {
				e.printStackTrace();
		}
		
		test1=extent1.createTest(feature); // create new entry in the report
		test1.log(Status.FAIL, "Test Case FAILED IS " + feature);
		test1.log(Status.FAIL, "TEST CASE FAILED IS " + result.getThrowable());
		try {
			test1.addScreenCaptureFromPath(screenshotPath);// adding screen shot	
		} catch (IOException e) {
				e.printStackTrace();
		}
    }
		


	public void onTestSkipped(ITestResult result) {
		 System.out.println("Skipped Test");
		String feature = result.getMethod().getRealClass().getName()+ ":" + result.getMethod().getMethodName();
		feature = feature.substring(feature.lastIndexOf(".") + 1);	
		test=extent.createTest(feature); // create new entry in the report
		//test=extent.createTest(result.getName()); // create new entry in the report
		test.log(Status.SKIP, "Test Case SKIIPED  IS " + feature);
		//Get screenshot
    	String screenshotPath=screenshot(result.getMethod().getMethodName());
		//Attach screenshot to extent report
		try {
			test.addScreenCaptureFromPath(screenshotPath);// adding screen shot	
		} catch (IOException e) {
				e.printStackTrace();
		}
		
		test1=extent1.createTest(feature); // create new entry in the report
		//test=extent.createTest(result.getName()); // create new entry in the report
		test1.log(Status.SKIP, "Test Case SKIIPED  IS " + feature);
		try {
			test1.addScreenCaptureFromPath(screenshotPath);// adding screen shot	
		} catch (IOException e) {
				e.printStackTrace();
		}
		
	}
		
	

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	public void onStart(ITestContext context) {
		String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());//time stamp
		String repName="Freshii_ExtentReport-"+timeStamp+".html"; // Report name and time stamp in html format
		
		htmlReporter=new ExtentHtmlReporter(System.getProperty("user.dir")+ "\\ExtentReports\\"+repName);//specify location of the report
		
		htmlReporter.config().setDocumentTitle("Automation Report"); // Tile of report - given by the user
		htmlReporter.config().setReportName("Functional Testing"); // name of the report - given by the user
		//htmlReporter.config().setTheme(Theme.DARK); //
	//	htmlReporter.config().setTheme(Theme.STANDARD); 
		String css = ".r-img {width: 50%;}";
	    htmlReporter.config().setCSS(css);			
		extent=new ExtentReports();
		extent.attachReporter(htmlReporter);
		// System info can be any info defined by the User that comes in the report
		// populate the common details
		extent.setSystemInfo("Host name","localhost");
		extent.setSystemInfo("Environment","QA");
		extent.setSystemInfo("user","Serena");
		extent.setSystemInfo("Device","Android Simulator");
		extent.setSystemInfo("Android version", "Android 10");
		
		
		String repName1="Freshii_ExtentReport.html"; // Report name and time stamp in html format
		htmlReporter1=new ExtentHtmlReporter(System.getProperty("user.dir")+ "\\ExtentReports\\"+repName1);//specify location of the report
		htmlReporter1.config().setDocumentTitle("Automation Report"); // Tile of report - given by the user
		htmlReporter1.config().setReportName("Functional Testing"); // name of the report - given by the user
		//htmlReporter.config().setTheme(Theme.DARK); //
	//	htmlReporter.config().setTheme(Theme.STANDARD); 
	    htmlReporter1.config().setCSS(css);			
		extent1=new ExtentReports();
		extent1.attachReporter(htmlReporter1);
		// System info can be any info defined by the User that comes in the report
		// populate the common details
		extent1.setSystemInfo("Host name","localhost");
		extent1.setSystemInfo("Environment","QA");
		extent1.setSystemInfo("user","Serena");
		extent1.setSystemInfo("Device","Android Simulator");
		extent1.setSystemInfo("Android version", "Android 10");
	}
		
	

	public void onFinish(ITestContext context) {
		{
			
			extent.flush();
			extent1.flush();
			
		}
		
	}
	

}
