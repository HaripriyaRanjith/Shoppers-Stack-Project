package commons;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.safari.SafariDriver;

public class BaseTest {

	public static WebDriver driver;
	public static String browserUrl;
	public static String browserPath=System.getProperty("user.dir")+File.separator+"drivers"+File.separator;
	public String configPath=System.getProperty("user.dir")+File.separator+"src/main/resources/configuration.properties";

	//Method to set the browser type .Input : Browser Name
	public static WebDriver getBrowserType(String browser) throws Exception {

		switch (browser) {
		case "chrome":
			browserUrl="chromedriver.exe";
			System.setProperty("webdriver.chrome.driver", browserPath+browserUrl);
			return driver= new ChromeDriver();

		case "internetExplorer":
			browserUrl="internetExplorerdriver.exe";
			System.setProperty("webdriver.ie.driver", browserPath+browserUrl);
			return driver= new InternetExplorerDriver();

		case "safari":
			browserUrl="safaridriver.exe";
			System.setProperty("webdriver.safari.driver", browserPath+browserUrl);
			return driver= new SafariDriver();

		default :
			return null;
			
		}

	}

	//Fetch the property file values .Input : Property file name
	public String getPropertiesValue(String value) {
		Properties property = new Properties();
		try {
			property.load(new FileInputStream(configPath));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return property.getProperty(value);
	}

	//Maximize window size
	public static void setBrowser(String browserType) throws Exception {
		driver=getBrowserType(browserType);
		driver.manage().window().maximize();
	}
	
	//Browser close method
	public static void closeBrowser() {
		driver.close();
		driver.quit();
	}
	
	//driver wait method
	public static void delay(int waitTime) {
        long endTime = System.currentTimeMillis() + (waitTime * 1000);
        while (System.currentTimeMillis() < endTime) {}
    } 
	
	//Enables driver to move to a particular element
	public static void moveToElement(WebElement element) throws Exception {
		
		Thread.sleep(1000);
		Actions actions = new Actions(driver);
		actions.moveToElement(element).perform();
		
	}

}
