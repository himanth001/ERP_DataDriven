package commonFunctions;

import java.time.Duration;

import org.openqa.selenium.By;
import org.testng.Reporter;

import config.AppUtil;

public class FunctionLibrary extends AppUtil {
	//method for login
    public static boolean verify_Login(String user,String pass) throws Throwable
    {
    	driver.get(conpro.getProperty("Url"));
    	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    	driver.findElement(By.xpath(conpro.getProperty("ObjReset"))).click();
    	driver.findElement(By.xpath(conpro.getProperty("ObjUser"))).sendKeys(user);
    	driver.findElement(By.xpath(conpro.getProperty("ObjPass"))).sendKeys(pass);
    	driver.findElement(By.xpath(conpro.getProperty("ObjLogin"))).click();
    	Thread.sleep(3000);
    	String Expected ="dashboard";
    	String Actual = driver.getCurrentUrl();
    	if(Actual.contains(Expected))
    	{
    		driver.findElement(By.xpath("(//a[text()=' Logout'])[2]")).click(); 
    		Reporter.log("Login success : "+Expected+"    "+Actual,true);
    		return true;
    	}
    	else
    	{
    		//capture error message
    		String Message = driver.findElement(By.xpath(conpro.getProperty("ObjerrorMessage"))).getText();
    		driver.findElement(By.xpath(conpro.getProperty("Objok"))).click();
    		Reporter.log(Message,true);
    		return false;
    	}
	
    	
    }
	
}
