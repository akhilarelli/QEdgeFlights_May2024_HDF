package testCases;

import static org.testng.Assert.fail;

import java.util.ResourceBundle;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.LoginPage;
import pageObjects.UserDashBoard;
import testBase.BaseClass;

public class TC_002_LoginTest extends BaseClass{
	
	public ResourceBundle rb;
	
	@Test(groups = {"Sanity","Master"})
	public void test_loginUser() {
		
		logger.info("Starting TC_002_LoginTest");
		
		try {
			rb = ResourceBundle.getBundle("config");
			
			
			LoginPage lp = new LoginPage(driver);
			
			
			driver.get(rb.getString("logURL"));
			logger.info("Launced Login site");
			
			lp.setUserName(rb.getString("userName"));
			logger.info("Entered USERNAME");
			
			lp.setpassword(rb.getString("password"));
			logger.info("Entered PASSWORD");
			
			lp.clickSubmit();
			logger.info("credentials SUBMITTED ");
			
			UserDashBoard udb = new UserDashBoard(driver);
			
			//returned status from method stored in boolean and verified with Assertion.
			boolean targetPage = udb.isMyAccountExist(); //returned status stored in boolean
			Assert.assertEquals(targetPage, true); //comparison between returned status and expected status
			
		}
		catch(Exception e) {
			Assert.fail();
		}
		logger.info(" Finished TC_002_LoginTest");
		
		
	}

}
