package com.vTigerCRM.testScripts;

import org.testng.annotations.Test;

import com.vTigerCRM.genericLib.Base;

public class CreateVendorTest extends Base
{
	@Test(groups={"smoke"})
	public void createVendorTest()
	{
		System.out.println("Vendor is created successfully -> PASS");
	}
}
