package com.vTigerCRM.genericLib;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;


/**
 * This class contains configuration annotations for launching the browser, login, logout and close the browser
 * @author Manjunath N
 *
 */
public class Base
{
	public FileLib lib = new FileLib();
	public WebDriver driver = null;
	public WebDriver staticDriver = null;
	public WebDriverWait wait = null;
	
	//WebEvent Logs
	public static EventFiringWebDriver e_driver;
	public static MyWebEventListener myEventListener;
	
	
	@BeforeClass
	public void configBC()
	{
		/* Launch browser */
		if(lib.getPropertyKeyValue("browser").equals("chrome"))
		{
			driver = new ChromeDriver();
			staticDriver = driver;
			System.out.println("Browser Launched Successfully  --> PASS");
		}
		else if(lib.getPropertyKeyValue("browser").equals("firefox"))
		{
			driver = new FirefoxDriver();
			staticDriver = driver;
			System.out.println("Browser Launched Successfully  --> PASS");
		}
		/* Maximize browser */
		driver.manage().window().maximize();
		
		/* Enter URL */
		driver.get(lib.getPropertyKeyValue("url"));
		
		/* Wait statements */
		driver.manage().timeouts().implicitlyWait(Utility.IMPLICIT_WAIT, TimeUnit.SECONDS);
		wait = new WebDriverWait(driver, Utility.EXPLICIT_WAIT);
		
		/* Verify Login Page */
		String loginTitle = driver.getTitle();
		if(loginTitle.contains("vtiger CRM 5 - Commercial Open Source CRM"))
		{
			System.out.println("Login Page is displayed --> PASS");
		}
		else
		{
			System.out.println("Login Page not is displayed --> FAIL");
		}
		
		//webevent listener 
				e_driver = new EventFiringWebDriver(driver);
				myEventListener = new MyWebEventListener();//custom class created by me
				e_driver.register(myEventListener);//register myEventListener with EventFiringWebDriver object reference 
				driver = e_driver;
		
	}
	
	@BeforeMethod
	public void configBM()
	{
		/* Login */
		driver.findElement(By.name("user_name")).sendKeys(lib.getPropertyKeyValue("username"));
		driver.findElement(By.name("user_password")).sendKeys(lib.getPropertyKeyValue("password"));
		driver.findElement(By.id("submitButton")).click();
		
		if(driver.getTitle().contains("Home"))
		{
			System.out.println("Home Page is displayed --> PASS");
		}
		else
		{
			System.out.println("Home Page not is displayed --> FAIL");
		}
	}
	
	@AfterMethod
	public void configAM(ITestResult result) throws IOException
	{
		/* Sign out */
		Actions act = new Actions(driver);
		act.moveToElement(driver.findElement(By.xpath("//img[contains(@src,'user.PNG')]"))).perform();
		driver.findElement(By.linkText("Sign Out")).click();
		
		if(driver.getTitle().contains("vtiger CRM 5 - Commercial Open Source CRM"))
		{
			System.out.println("Logout is successful --> PASS");
		}
		else
		{
			System.out.println("Logout is not successful --> FAIL");
		}	
	}
	
	@AfterClass
	public void configAC()
	{
		/* Close Browser */
		driver.quit();
		System.out.println(driver);
		if(driver == null)
		{
			System.out.println("Browser is closed successfully --> Pass");
		}
		else
		{
			System.out.println("Broser is not closed successfully --> FAIL");
		}
	}	
}