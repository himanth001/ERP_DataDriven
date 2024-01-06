package commonFunctions;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage {

//define repository for login elements
	@FindBy(xpath = "//button[@id='btnreset']")
	WebElement ObjResetbtn;
	@FindBy(xpath = "//input[@id='username']")
	WebElement ObjUsername;
	@FindBy(xpath = "//input[@id='password']")
	WebElement ObjPassword;
	@FindBy(xpath = "//button[@id='btnsubmit']")
	WebElement ObjLoginbtn;
//method for login
	public void loginTest(String username,String password)
	{
		ObjResetbtn.click();
		ObjUsername.sendKeys(username);
		ObjPassword.sendKeys(password);
		ObjLoginbtn.click();
	}
	
}
