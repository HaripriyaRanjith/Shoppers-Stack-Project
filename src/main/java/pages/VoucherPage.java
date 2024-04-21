package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import commons.BaseTest;
import commons.ReportConfig;

public class VoucherPage extends BaseTest{

	public VoucherPage(WebDriver webdriver) {
		driver=webdriver;
	}

	@FindBy(id = "email")
	public static WebElement voucherEmail;

	@FindBy(id = "password")
	public static WebElement voucherPassword;

	@FindBy(xpath = "//BUTTON[normalize-space(.)='login']")
	private static WebElement loginButton;

	@FindBy(xpath = "//A[contains(@class,'active')]")
	private static WebElement createCouponLink;

	@FindBy(xpath = "//DIV[normalize-space(.)='Email *']/descendant::INPUT")
	private static WebElement email;

	@FindBy(xpath = "//DIV[normalize-space(.)='Full Name *']/descendant::INPUT")
	private static WebElement fullName;

	@FindBy(xpath = "//DIV[normalize-space(.)='Cost *']/descendant::INPUT")
	private static WebElement cost;

	@FindBy(xpath = "//*[@id='startDate']")
	private static WebElement startDate;

	@FindBy(xpath = "//*[@id='endDate']")
	private static WebElement endDate;

	@FindBy(xpath = "//LI[normalize-space(.)='ShoppingKart']")
	private static WebElement applicationName;
	
	@FindBy(xpath = "//*[@id='demo-simple-select']")
	private static WebElement applicationNameDropdown;
	
	@FindBy(xpath = "//*[@placeholder='Number of vouchers to generate']")
	private static WebElement voucherCount;

	@FindBy(xpath = "//BUTTON[normalize-space(.)='Generate']")
	private static WebElement generateButton;

	@FindBy(xpath = "//*[@placeholder='Enter email or full name']")
	private static WebElement couponSearch;

	@FindBy(xpath = "//BUTTON[normalize-space(.)='Search']")
	private static WebElement couponSearchButton;

	@FindBy(xpath = "//DIV[contains(@class,'MuiDataGrid-row MuiDataGrid-row--lastVisible')]/descendant::DIV[@data-field='voucherCode']")
	private static WebElement voucherCode;

	@FindBy(xpath = "//*[contains(@class,'btnPrimary')]")
	private static WebElement logoutButton;

	@FindBy(xpath = "//*[@id='root']/section[1]/article/div[2]/a[2]")
	private static WebElement viewCouponsLink;

	@FindBy(xpath = "(//*[@data-field='createrEmail'])[2]")
	private static WebElement creatorEmail;

	@FindBy(xpath = "//*[@id='details-button']")
	public static WebElement advancedSettings;

	@FindBy(xpath = "//*[@id='proceed-link']")
	public static WebElement unsafeLink;

	public static String voucherPageLogin(String userName,String userpassword) throws Exception {

		PageFactory.initElements(driver,VoucherPage.class);

		driver.navigate().to("https://voucher.shoppersstack.com/");
		advancedSettings.click();
		unsafeLink.click();
		voucherEmail.sendKeys(userName);
		voucherPassword.sendKeys(userpassword);
		ReportConfig.attachReport("INFO","Voucher Login Page","In Voucher Login Page");
		loginButton.click();

		return driver.getTitle();
	}

	public static void createCoupon(String mail,String name,String voucherCost,String sDate,String eDate,String count) throws Exception {

		createCouponLink.click();
		email.sendKeys(mail);
		fullName.sendKeys(name);
		cost.sendKeys(voucherCost);
		startDate.sendKeys(sDate);
		endDate.sendKeys(eDate);
		delay(3);
		applicationNameDropdown.click();
		applicationName.click();
		voucherCount.sendKeys(count);
		ReportConfig.attachReport("INFO","Coupon details","All Coupon details provided");
		generateButton.click();

	}

	public static String[] viewCoupon(String mail) {
		delay(3);
		viewCouponsLink.click();
		couponSearch.sendKeys(mail);
		couponSearchButton.click();
		return new String[] {voucherCode.getText(),creatorEmail.getText()};
	}

	public static void logoutVoucherPage() {
		logoutButton.click();
	}

}
