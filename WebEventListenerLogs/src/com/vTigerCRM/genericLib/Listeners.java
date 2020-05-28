package com.vTigerCRM.genericLib;

import java.io.File;
import java.io.IOException;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class Listeners implements ITestListener 
{

	@Override
	public void onTestFailure(ITestResult result) 
	{
		Date d = new Date();
//		String time = d.toString();
//		String[] dates = time.split(" ");
//		String day = dates[2];
//		String month = dates[1];
//		String year = dates[5];
//		String timeWs = dates[3];
//		 String dateWT = day.concat(month).concat(year).concat(timeWs).replace(":", "-");
//		 System.out.println(dateWT);
		 
		 String time = d.toString().replace(":", "-").replace(" ", "-");
		 System.out.println(time);
		 
		//Launch browser and enter URL
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("http://www.google.com");
		
		//create object of EventFiringWebDriver class		
		TakesScreenshot sh = (TakesScreenshot)driver;
		
		//call getScreenshotAs()
		File src = sh.getScreenshotAs(OutputType.FILE);
		
		String methodName = result.getMethod().getMethodName();
		
		//define destination location to store the screen shot
		File dest = new File("./ScreenShots/"+methodName+time+".png");
		
		//copy the screen shot from SRC to DEST
		//Use 3rd party tool commons io to import FileUtils class
		try {
			FileUtils.copyFile(src, dest);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	@Override
	public void onTestStart(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	

	@Override
	public void onTestSkipped(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onFinish(ITestContext context) {
		// TODO Auto-generated method stub
		
	}

}
