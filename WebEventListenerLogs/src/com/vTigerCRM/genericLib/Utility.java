package com.vTigerCRM.genericLib;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.ui.Select;

import freemarker.template.SimpleDate;

/**
 * This class contains utility methods like select, actions, switch to window etc.
 * @author Manjunath
 *
 */
public class Utility 
{
	public static final long IMPLICIT_WAIT = 20; 
	public static final long EXPLICIT_WAIT = 20;
	
	/**
	 * This method is used to select an option by value from Select Dropdown
	 * @param element
	 * @param value
	 */
	public static void select(WebElement element, String value)
	{
		Select sel = new Select(element);
		sel.selectByValue(value);
	}
	
	public static String getScreenShot(WebDriver driver, String screenShotName) throws IOException
	{
		String dateName = new SimpleDateFormat("ddMMMMyyyyhhmmss").format(new Date());
		EventFiringWebDriver efd = new EventFiringWebDriver(driver);
		File src = efd.getScreenshotAs(OutputType.FILE);
		String destpath = System.getProperty("user.dir")+"/ScreenShots/" + screenShotName + dateName + ".png";
		File dest = new File(destpath);
		FileUtils.copyFile(src, dest);
		return destpath;
	}
	
	
	
}
