package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import commons.BaseTest;

public class CartPage extends BaseTest{

	public CartPage(WebDriver webdriver) {
		driver=webdriver;
	}

	@FindBy(id = "cart")
	static WebElement cartIcon;

	@FindBy(id = "buynow_fl")
	static WebElement buynowButton;

	public static String cartProducts() throws Exception {

		PageFactory.initElements(driver,CartPage.class);
		cartIcon.click();
		return driver.getTitle();
	}

	public static void proceedToBuy() throws Exception {
		buynowButton.click();
		delay(5);
	}




}
