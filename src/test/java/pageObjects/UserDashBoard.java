package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.internal.WebElementToJsonConverter;
import org.openqa.selenium.support.FindBy;
import org.testng.annotations.Test;

public class UserDashBoard extends BasePage{
	
	public UserDashBoard(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}
	
	@FindBy(xpath = "//*[contains(text(),'User Dashboard')]")
	WebElement msgHeading;
	
	
	/*isMyAccoutnExist return status of webElement wether it is displayed or not
	 * */
	@Test
	public boolean isMyAccountExist() {
		
		try {
			
		return(msgHeading.isDisplayed()); //returns status
		
		}catch(Exception e) {
			
			return(false);
		}
	}

}
