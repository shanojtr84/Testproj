package com.testing.proj.Test_proj;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;

import org.testng.Assert;


/**
 * Unit test for simple App.
 */
public class AppTest 
    
{
	public static By findIcon 	= By.xpath("//*[@class='android.support.v7.app.ActionBar$Tab'][1]");
	public static AppiumDriver driver = null;
	 //   public static AppiumServiceBuilder service;
	  
	    public static AppiumDriverLocalService service=null;
	    public static String AppiumNodeFilePath ="/usr/local/bin/node";
	    public static String AppiumJavaScriptServerFile = "/Applications/Appium.app/Contents/Resources/app/node_modules/appium/build/lib/main.js";
	@BeforeTest
	 //   public static void setup(String pVer, String pName, String appLocation, String dName)
	    public static void setup() throws IOException {

	 	   stopAppiumServer();
	      startAppiumServer();
	    	
	    		File appDir = new File("resources");
	    		File app = new File(appDir, "Officeworks.apk");
	    	
	    	  DesiredCapabilities capabilities = new DesiredCapabilities();
	          capabilities.setCapability("platformName", "Android");
	          capabilities.setCapability("deviceName", "0715f7fcee8c1e04");
	          capabilities.setCapability("automationName", "UIAutomator2");
	          capabilities.setCapability("app", app.getAbsolutePath());
	          capabilities.setCapability("appPackage", "au.com.officeworks.mobile");
	          capabilities.setCapability("appActivity", "au.com.officeworks.mobile.ui.main.SplashScreenActivity");
	          capabilities.setCapability("noReset", true);

	          
	          										
	          
	          driver = new AndroidDriver(new URL("http://0.0.0.0:4723/wd/hub"), capabilities);

	        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

	         
	    }
 	public static void startAppiumServer() throws IOException {

  		System.out.println("Starting Appium Server ......");

  		service =  AppiumDriverLocalService.buildService(new AppiumServiceBuilder().usingDriverExecutable(new File(AppiumNodeFilePath)).withAppiumJS(
  				new File(AppiumJavaScriptServerFile)));
  		
  		service.start();

  		System.out.println("Appium Server Started !!");

  	}
  	public static void stopAppiumServer() throws IOException {

  		System.out.println("Checking If Appium Server is stopped ?");
  		try {
  			service.stop();
  			System.out.println("Appium Server is now Stopped!!");

  		} catch (NullPointerException e) {
  			

  			System.out.println("Appium Server is already Stopped !!");
  		}

  	}
  	
  	
  	@Test
  	public void testsample() throws InterruptedException {
  		
  		
  		driver.findElement(findIcon).click();
  		
  	}
  	
  	
  	
    @AfterTest
    public static void teardown() throws IOException {
        driver.quit();
        stopAppiumServer();
    }
}
