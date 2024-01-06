package commonFunctions;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LogoutaPage {
	@FindBy(xpath = "(//a[starts-with(text(),' Logout')])[2]")
	WebElement ObjLogout;
	
	public void Logout_App()
	{
		ObjLogout.click();
	}
	

}
