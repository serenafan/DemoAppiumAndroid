package com.utilities;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

public class ReadConfig 
{
	// Properties Class
	Properties pro; 
	
	// Constructor
	public ReadConfig()
	{
		// Creating File object
		File src = new File("./Configuration/config.properties");

		try 
		{
			// Open FileInputStream and Read data 
			FileInputStream fis = new FileInputStream(src);
			pro = new Properties();
			pro.load(fis); // Load config.properties file
		} 
		catch (Exception e) 
		{	
			// If config.properties file is not available it will throw an exception
			System.out.println("Exception is " + e.getMessage());
		}
	}
	
	// Methods to read data from config.properties 

	//get user name
	public String getUsername1() // new 
	{
		String username1=pro.getProperty("username1");
		return username1;
	}
	//get password
	public String getPassword() 
	{
	String password=pro.getProperty("password");
	return password;
	}
	
	//get app apk or app
	public String getAppSrc()
	{
		String appsrc=pro.getProperty("appsrc");
		return appsrc;
	}
	//get simulator device name
	public String getDeviceName()
	{
		String deviceName=pro.getProperty("devceName");
		return deviceName;
	}
	
	
}
