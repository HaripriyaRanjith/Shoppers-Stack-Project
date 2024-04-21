package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import commons.BaseTest;

public class HomePage extends BaseTest{

	@FindBy(xpath = "//*[@id='root']/div[2]")
	private static WebElement welcomePageText;

	@FindBy(id = "loginBtn")
	public static WebElement loginButton;

	@FindBy(xpath  = "//BUTTON[@aria-label='Account settings']/descendant::DIV[.='H']")
	public static WebElement mySettings;

	@FindBy(xpath = "//*[normalize-space(.)='Logout']")
	public static WebElement logoutButton;

	//@FindBy(xpath = "//DIV[normalize-space(.)='Voucher']")
	@FindBy(xpath = "//*[@id='root']/section[5]/article/div[2]/div[1]/div[3]/a")
	public static WebElement voucherButton;

	public HomePage(WebDriver webdriver) {
		driver=webdriver;
	}

	//Launch the browser with respect to the type of browser 
	//Input : Browser Type
	public static void launchBrowser(String browserType) throws Exception {
		setBrowser(browserType);
	}

	//Launch the browser and application / Verify the welcome Page
	//Input : application URL
	public static void launchApplication(String browser,String appUrl) throws Exception {

		launchBrowser(browser);
		PageFactory.initElements(driver,HomePage.class);
		driver.get(appUrl);
		delay(5);
		welcomePageText.isDisplayed();
		loginButton.click();
	}

	public static void userLogout() {
		mySettings.click();
		delay(5);
		logoutButton.click();

	}

	//Navigate to voucher page
	public static void navigateToVoucherPage() throws Exception {
		moveToElement(voucherButton);
		delay(4);
		voucherButton.click();
	}
}
