package testBase;

import java.io.File;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.concurrent.TimeUnit;
import java.util.logging.LogManager;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import freemarker.template.SimpleDate;
import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseClass {
	/*
	 * All Re-usable methods placed in this BaseClass
	 * */
	//public WebDriver driver; //created to use in setUp() method and used to initialization of webdriver.
	
	public static WebDriver driver;  
	/*driver is made static(single driver instantiated only once) because driver instantiated initially without static and this driver again instantiated in
	onTestFailure() in 'ExtentReportManager' where new object created for BaseClass so that new driver starts here so that totally 
	we have multiple drivers leads to execution failure. 
	To overcome multiple drivers instantiation we made driver static*/
	
	public Logger logger; //created variable for Logger this records every event
	
	//Add 'ResourceBundle' class to call config.properties file data to use here
	
	public ResourceBundle rb; //import using util package
	
	//Executes before starting of every Testcase
	@BeforeClass(groups = {"Master","Sanity","Regression"})
	@Parameters("browser")
	public void setup(String br) {
		
		rb = ResourceBundle.getBundle("config"); //loading config.props file
		
	    logger = org.apache.logging.log4j.LogManager.getLogger(this.driver);//logs every Test case events
	    
	    //to close message "browser is controlled by Automated software"
	    //ChromeOptions options = new ChromeOptions();
	    //options.setExperimentalOption("excludeSwitches", new String[] {"enable-automation"});
		
	    if(br.equals("chrome")) {
	    	WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver(); //17th line used here to instantiate driver
	    }else if(br.equals("edge")) {
	    	WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver(); //17th line used here to instantiate driver
	    }else {
	    	driver = new FirefoxDriver();
	    }
	    
	   
		
		driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
		
		driver.get(rb.getString("regUrl")); //appUrl from config.props file is called here without hard-coding
		//driver.get("http://flights.qedgetech.com/");
		driver.manage().window().maximize();
		
	}
	
	//Executes after completion of every Testcase
	@AfterClass(groups = {"Master","Sanity","Regression"})
	public void tearDown() {
		//driver.quit();
	}
	
	//To generate Random Alphabets such as strings
	//used for FirstName, LastName
	public String randomString() {
		
		String randString = RandomStringUtils.randomNumeric(10);
		return (randString);
	}
	
	//To generate Random Numerics
	//used for phoneNumbers
	public String randomNumerics() {
		
		String randNumeric = RandomStringUtils.randomNumeric(10);
		return (randNumeric);
	}
	
	//used for Password generation
	public String randAlphaNumeric() {
		
		String randStr = RandomStringUtils.randomAlphabetic(4);
		String randNum = RandomStringUtils.randomNumeric(3);
		
		return (randStr+"@"+randNum);
	}
	
	public String captureScreenshort(String tname){
		
		String timeStamp = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date()); //to capture screenshot with time, date
		
		TakesScreenshot takeScreenshot = (TakesScreenshot)driver;
		File source = takeScreenshot.getScreenshotAs(OutputType.FILE); //file type
		String destination = System.getProperty("user.dir")+ "\\screenshorts\\" + tname +"_"+ timeStamp +".png"; 
		//loading screenshot into project directory screenshots folder with 'fileName' and timestamp and file extension
		
		try {
			FileUtils.copyDirectory(source, new File(destination));
			
		}catch(Exception e) {
			e.getMessage();
		}
		
		return destination;
		
	}
	

}
