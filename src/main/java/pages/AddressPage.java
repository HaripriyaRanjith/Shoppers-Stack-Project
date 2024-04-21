package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import commons.BaseTest;

public class AddressPage extends BaseTest {

	public AddressPage(WebDriver webdriver) {
		driver=webdriver;
	}

	@FindBy(xpath  = "(//*[@name='address'])[1]")
	static WebElement addressButton;
	
	@FindBy(className  = "selectaddress_proceed__qiGsK")
	static WebElement proceedButton;
	
	public static void selectAddress() throws Exception {
		
		PageFactory.initElements(driver,AddressPage.class);
		addressButton.click();
		moveToElement(proceedButton);
		proceedButton.click();
	}

}
