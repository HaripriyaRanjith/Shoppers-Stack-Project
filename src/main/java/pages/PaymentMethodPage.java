package pages;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import commons.BaseTest;
import commons.ReportConfig;

public class PaymentMethodPage extends BaseTest {
	
	public PaymentMethodPage(WebDriver webdriver) {
		driver=webdriver;
	}

	@FindBy(xpath  = "//*[@value='Net Banking'] ")
	static WebElement netBanking;
	
	@FindBy(xpath  = "//*[@class='payment_actionBtn__2KSX+']/button")
	static WebElement proceedButton;
	
	@FindBy(xpath  = "//*[@class='PrivateSwitchBase-input css-1m9pwf3' and @value='IDHC']")
	static WebElement IDHC;
	
	@FindBy(xpath  = "//*[@class='PrivateSwitchBase-input css-1m9pwf3' and @value='ICD']")
	static WebElement ICD;
	
	@FindBy(xpath  = "//*[@class='PrivateSwitchBase-input css-1m9pwf3' and @value='TBI']")
	static WebElement TBI;
	
	@FindBy(xpath  = "//*[@class='payment_actionBtn__2KSX+']/button[@type='submit']")
	static WebElement submitButton;
	
	@FindBy(id  = "User ID")
	static WebElement userIdTextbox;
	
	@FindBy(id  = "Password")
	static WebElement userPasswordTextbox;
	
	@FindBy(id  = "Submit")
	static WebElement userLoginSubmitButton;
	
	@FindBy(xpath  = "//*[@id='root']/div[2]/div[2]/div/button")
	static WebElement paynowButton;
	
	//@FindBy(xpath  = "//H1[normalize-space(.)='Order Confirmed']")
	@FindBy(xpath  = "//*[@id='root']/div[4]/h1")
	static WebElement orderConfirmedText;
	
	//@FindBy(xpath  = "//B[text()]")
	@FindBy(xpath  = "//*[@id='root']/div[4]/div[2]/p/b")
	static WebElement orderNumberText;
	
	@FindBy(xpath  = "//*[@id='root']/section[2]/div[3]/iframe")
	static WebElement iframeNetbanking;
	
	@FindBy(xpath  = "//*[@id='root']/div[2]/div/iframe")
	static WebElement iframeNetbankingLogin;
	
	@FindBy(xpath  = "//*[@id='root']/section/div[1]/iframe")
	static WebElement iframeNetbankingPayment;
	
	@FindBy(xpath  = "//*[@id='root']/section/article/iframe[1]")
	static WebElement iframeNetbankingPayment2;
	
	
	
	public static String[] selectPaymentMethod(String userId,String password) throws Exception {
		
		PageFactory.initElements(driver,PaymentMethodPage.class);
		delay(3);
		ReportConfig.attachReport("INFO", "In Payment Page", "Payment method Selection");
		
		netBanking.click();
		moveToElement(proceedButton);
		proceedButton.click();
		delay(3);
		
		driver.switchTo().frame(iframeNetbanking);
		
		ICD.click();
		ReportConfig.attachReport("INFO", "Net Banking Page", "Net Banking Selection");
		
		submitButton.click();
		driver.switchTo().frame(iframeNetbankingLogin);
		delay(3);
		userIdTextbox.sendKeys(userId);
		moveToElement(userPasswordTextbox);
		userPasswordTextbox.sendKeys(password);
		delay(3);
		
		ReportConfig.attachReport("INFO", "Net Banking Details", "Net Banking Details Provided");
		userLoginSubmitButton.click();
		delay(5);
		driver.switchTo().frame(iframeNetbankingPayment);
		driver.switchTo().frame(iframeNetbankingPayment2);
		paynowButton.click();
		
		delay(10);
		driver.navigate().to("https://www.shoppersstack.com/place-order");
		String orderConfirmation=orderConfirmedText.getText();
		String orderNumber=orderNumberText.getText();
		
		return new String[] {orderConfirmation,orderNumber};
		
	}


}
