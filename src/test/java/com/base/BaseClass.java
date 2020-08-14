package com.base;

import java.io.File;



import java.io.IOException;
import java.net.ServerSocket;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;

import com.utilities.ReadConfig;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.service.local.AppiumDriverLocalService;

public class BaseClass 
{
	ReadConfig  readconfig = new ReadConfig(); // Creating object
	
   // Integrating data from ReadConfig
	public String username1=readconfig.getUsername1();
	public String password=readconfig.getPassword(); 
	public String app=readconfig.getAppSrc();
	public String device=readconfig.getDeviceName();
	
	//Initiating android driver
	public static AndroidDriver<AndroidElement> driver; 
	
	//Initiating appium service
	public static AppiumDriverLocalService service;
	
	public static Logger logger;
	
	//Kill all nodes
	public static void killAllNodes() throws IOException, InterruptedException {
		Runtime.getRuntime().exec("taskkill /F /IM node.exe");
		Thread.sleep(3000);
		
	}

	//Start appium server
	public static AppiumDriverLocalService startServer() throws IOException, InterruptedException {

		boolean flag=checkIfServerIsRunning(4723);
		if (!flag) {
			service=AppiumDriverLocalService.buildDefaultService();
			service.start();
			
		}
		return service;
 
	}

	//check if server is running
	public static boolean checkIfServerIsRunning(int port)  {
		
		boolean isRunning =false;
		ServerSocket  serverSocket;
		try {
			serverSocket= new ServerSocket(port);
			serverSocket.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			isRunning =true;
		} finally {
			serverSocket=null;
		}
		return isRunning;
		
	}
	
	//Start emulator 
	public static void startEmulator() throws IOException, InterruptedException {
		Runtime.getRuntime().exec(System.getProperty("user.dir")+"\\src\\test\\java\\com\\utilities\\StartSimulator.bat");
		Thread.sleep(20000);
	}
	
	@Parameters({ "deviceType" })
	@BeforeMethod
	//Initiate android driver
	public  void Capabilities(String deviceType) throws IOException, InterruptedException {
	    killAllNodes();
	    startServer();
	    startEmulator();
	    //Initialize logger
	    logger=Logger.getLogger("Freshii");// Project Name 
	    PropertyConfigurator.configure("log4j.properties"); // Added Logger
	    logger.setLevel(Level.DEBUG); // to get the debug log
	    logger.debug("Debug logging has started ");

	    //Create desired capabilities and initialize android driver 
		DesiredCapabilities cap = new DesiredCapabilities();
		if (deviceType.equals("emulator")) {
			cap.setCapability(MobileCapabilityType.AUTOMATION_NAME,"device");
		}
		else if (deviceType.equals("realdevice")) {
			cap.setCapability(MobileCapabilityType.DEVICE_NAME, "Android Device");
		}
		cap.setCapability(MobileCapabilityType.AUTOMATION_NAME,"uiautomator2");
		cap.setCapability(MobileCapabilityType.APP,app );
		driver = new AndroidDriver<AndroidElement>(new URL("http://127.0.0.1:4723/wd/hub"), cap);
		
		//Define implicite wait for each page
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
	}
	
	@AfterMethod
	public void quitserver() {
		service.stop();

		
	}
	
	
	public String  screenshot(String testMethodName)
	{
		File source =( (TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());//time stamp
		String destination  = System.getProperty("user.dir") + "/Screenshots/" + testMethodName + "-" + timeStamp+ ".png" ;
		try
		{
			FileUtils.copyFile(source, new File(destination));
		}
		catch (IOException e) {
			e.printStackTrace();}
		
		return destination;
	}
	
	public static String randomestring(int num) 
	{
		String generatedString1 = RandomStringUtils.randomAlphabetic(num); // generate random char string with the specified values passed 
		return (generatedString1);										 
	}

	public static String randomeNum(int num ) 
	{
		String generatedString2 = RandomStringUtils.randomNumeric(num);// generate random digits with the specified values passed
		return (generatedString2);
	}
	
}