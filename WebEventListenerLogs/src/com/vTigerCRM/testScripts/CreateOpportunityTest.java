package com.vTigerCRM.testScripts;

import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.vTigerCRM.genericLib.Base;

@Listeners(com.vTigerCRM.genericLib.Listeners.class)
public class CreateOpportunityTest extends Base
{
	@Test
	public void createOpportunityTest()
	{
		System.out.println("Opportunity is created successfully -> PASS");
		Assert.assertTrue(false);
	}
}
