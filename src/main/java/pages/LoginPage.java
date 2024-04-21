package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import commons.BaseTest;

public class LoginPage extends BaseTest{

	public LoginPage(WebDriver webdriver) {
		driver=webdriver;
	}

	@FindBy(id = "Email")
	public static WebElement userId;

	@FindBy(id = "Password")
	public static WebElement userPassword;

	@FindBy(id = "Login")
	public static WebElement loginButton;

	public static String userLogin(String username,String password) throws Exception {

		PageFactory.initElements(driver,LoginPage.class);
		userId.sendKeys(username);
		userPassword.sendKeys(password);
		loginButton.click();
		delay(5);
		String pageTitle=driver.getTitle();
		return pageTitle;
	}

}
