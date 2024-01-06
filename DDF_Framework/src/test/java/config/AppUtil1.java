package config;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URI;
import java.time.Duration;
import java.util.Properties;

import org.apache.commons.exec.LogOutputStream;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Reporter;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import commonFunctions.LoginPage;
import commonFunctions.LogoutaPage;

public class AppUtil1 {
	
	
	public static WebDriver driver;
	public static Properties conpro;
	@BeforeTest
	public static void setUp() throws Throwable
	{
		conpro = new Properties();
		//load file
		conpro.load(new FileInputStream("./PropertyFiles/Enovironment.properties"));
		if(conpro.getProperty("Browser").equalsIgnoreCase("chrome"))
		{
			driver= new ChromeDriver();
			driver.manage().window().maximize();
			driver.manage().deleteAllCookies();
			driver.get(conpro.getProperty("Url"));
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
			//call login page class
			LoginPage login = PageFactory.initElements(driver, LoginPage.class);
			//call login method
			login.loginTest("admin", "master");
		}
		else if(conpro.getProperty("Browser").equalsIgnoreCase("firefox"))
		{
			driver= new ChromeDriver();
			driver.manage().deleteAllCookies();
			driver.get(conpro.getProperty("Url"));
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
			//call login page class
			LoginPage login = PageFactory.initElements(driver, LoginPage.class);
			//call login method
			login.loginTest("admin", "master");
		}
		else
		{
			Reporter.log("Browser value is not matching ",true);
		}
	}
	@AfterTest
	public static void tearDown()
	{
		LogoutaPage logout = PageFactory.initElements(driver, LogoutaPage.class);
		logout.Logout_App();
		driver.quit();
	}

}
