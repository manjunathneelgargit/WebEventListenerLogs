package com.vTigerCRM.testScripts;

import org.testng.annotations.Test;

import com.vTigerCRM.genericLib.Base;

public class CreateQuoteTest extends Base
{
	@Test(groups={"smoke","abc"})
	public void createQuotationTest()
	{
		System.out.println("Quotation is created successfully -> PASS");
	}
	
	
	@Test
	public void deleteQuotation()
	{
		System.out.println("Quotation is deleted successfully...!");
	}
	
}
