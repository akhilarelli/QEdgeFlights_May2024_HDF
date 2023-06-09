package testCases;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import pageObjects.AccountRegistrationPage;
import pageObjects.HomePage;
import testBase.BaseClass;

public class TC_001_AccountRegistrationTest extends BaseClass {

	// Naming convention of Methods in TestCase should be with starting 'test'

	@Test(groups = {"Regression","Master"})
	void test_account_Registration() {
		
		logger.debug("application logs");
		//logger.info("************Test case_001 starting execution");
		try {
			HomePage hp = new HomePage(driver); // driver initialization

			hp.clickRegister();

			AccountRegistrationPage accReg = new AccountRegistrationPage(driver); // driver initialization
			// accReg.setFirstName("Akhil");
			accReg.setFirstName(randomString()); // Adding Random FirstNames by using RandomStrings method
			logger.info("Name entered");
			
			accReg.setContactNumber(randomString()); // Adding Random LastNames by using RandomStrings method
			logger.info("ContactNumber entered");
			
			//accReg.setEmailId(randomString() + "@gmail.com");
			logger.info("Email entered");
			
			String pswd = randAlphaNumeric();
			accReg.setPassword(pswd);
			logger.info("password entered");

			accReg.selectfromDropDown("Male");
			logger.info("gender selected");
			
			accReg.calendardateSelection();
			logger.info("date selected");
			
			accReg.clickAgree();
			logger.info("agree checkbox clicked");

		} catch (Exception e) {
			Assert.fail();
		}

	}

}
