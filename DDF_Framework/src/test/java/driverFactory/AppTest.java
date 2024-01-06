package driverFactory;

import java.io.File;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.Reporter;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import commonFunctions.FunctionLibrary;
import config.AppUtil;
import utilities.ExcelFileUtil;

public class AppTest extends AppUtil {
	String inputpath = "./FileInput/LoginData.xlsx";
	String outputpath = "./FileOutput/DataDrivenResults.xlsx";
	ExtentReports report;
	ExtentTest test;
	@Test
	public void startTest() throws Throwable
	{
		//define path to generate reports
		report = new ExtentReports("./target/Reports/Login.html");
		boolean res = false;
		//create object for Excelfileutil class
		ExcelFileUtil xl = new ExcelFileUtil(inputpath);
		//count no of rows in login sheet
		int rc = xl.rowCount("Login");
		Reporter.log("No of rows in Login Sheet:"+rc);
		//iterate all rows in login sheet
		for(int i=1;i<=rc;i++)
		{
			test = report.startTest("Login Test");
			test.assignAuthor("Himanth");
			//read username and password cell data
			String username = xl.getCellData("Login", i, 0);
			String password = xl.getCellData("Login", i, 1);
			//call login method
			res = FunctionLibrary.verify_Login(username, password);
			if(res)
			{
				//if res is true write login success into results cell
				xl.setcellData("Login", i, 2, "Login success", outputpath);
				//write pass into the status cell
				xl.setcellData("Login", i, 3, "pass", outputpath);
				test.log(LogStatus.PASS, "Login Success");
				
			}
			else
			{
				//take screenshot for fail test
				File screen = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
				//copy file into local system
				FileUtils.copyFile(screen, new File("./Screenshot/Iteration/"+i+"LoginPage.png"));
				//if res is false write login fail into results cell
				xl.setcellData("Login", i, 2, "Login fail", outputpath);
				//write as fail into the status cell
				xl.setcellData("Login", i, 3, "Fail", outputpath);
				test.log(LogStatus.FAIL, "Login Fail");
			}
			report.endTest(test);
			report.flush();
			
		}
	}
	

}
