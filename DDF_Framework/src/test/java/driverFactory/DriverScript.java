package driverFactory;

import org.openqa.selenium.support.PageFactory;
import org.testng.Reporter;
import org.testng.annotations.Test;

import commonFunctions.SupplierPage;
import config.AppUtil;
import config.AppUtil1;
import utilities.ExcelFileUtil;

public class DriverScript extends AppUtil1{

	String inputpath = "./FileInput/SupplierData.xlsx";
	String outputpath = "./FileOutput/PomResults.xlsx";
	boolean res = false;
	@Test
	public void startTest() throws Throwable
	{
		//create object for excelfileutil class
		ExcelFileUtil xl = new ExcelFileUtil(inputpath);
		//count no.of rows in supplier sheet
		int rc = xl.rowCount("supplier");
		Reporter.log("No.of rows are : "+rc,true);
		for(int i=1;i<=rc;i++)
		{
			String Suppliername = xl.getCellData("supplier", i, 0);
			String Address = xl.getCellData("supplier", i, 1);
			String City = xl.getCellData("supplier", i, 2);
			String Country = xl.getCellData("supplier", i, 3);
		    String ContactPerson = xl.getCellData("supplier", i, 4);
		    String PhoneNumber = xl.getCellData("supplier", i, 5);
		    String Email = xl.getCellData("supplier", i, 6);
		    String Mobilenumber = xl.getCellData("supplier", i, 7);
		    String Notes = xl.getCellData("supplier", i, 8);
		    //call add supplier page class
		    SupplierPage sp = PageFactory.initElements(driver, SupplierPage.class);
		    res = sp.add_Supplier(Suppliername, Address, City, Country, ContactPerson, PhoneNumber, Email, Mobilenumber, Notes);
		    if(res)
		    {
		    	xl.setcellData("supplier", i, 9, "Pass", outputpath);
		    }
		    else
		    {
		    	xl.setcellData("supplier", i, 9, "Fail", outputpath);
		    }
		}
		
	}
	
	
}
