package voucherTest;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import commons.BaseTest;
import commons.ReportConfig;
import commons.excelUtil;
import pages.HomePage;
import pages.LoginPage;
import pages.VoucherPage;

public class GenerateVoucherTest extends BaseTest {

	public String browserType=getPropertiesValue("browserType");
	public String applicationUrl=getPropertiesValue("applicationURL");
	public String userEmail=getPropertiesValue("userEmail");
	public String userPassword=getPropertiesValue("password");
	public String testDataName=getPropertiesValue("testData");
	public String testDataSheetName=getPropertiesValue("testDataSheetName2");

	@BeforeClass 
	public void loginApplication() throws Exception {
		HomePage.launchApplication(browserType,applicationUrl);
		ReportConfig.attachReport("INFO","Launch Application","Shoppers stack application is being launched"); String
		pageTitle=LoginPage.userLogin(userEmail,userPassword);
		if(pageTitle.equalsIgnoreCase("ShoppersStack | Home")) {
			ReportConfig.attachReport("PASS","Login", "User logged in successfully");
		}
		else
		{ 
			ReportConfig.attachReport("FAIL","Login", "User login failed");
		}
	}

	@Test(dataProvider ="TestData")
	public void generateVoucher(String name,String cost,String startDate,String endDate,String count) throws Exception {

		//Navigate to voucher page
		HomePage.navigateToVoucherPage();
		ReportConfig.attachReport("INFO","Navigation to Voucher Page","In Voucher Page");

		//Voucher Page Login
		String pageTitle=VoucherPage.voucherPageLogin(userEmail, userPassword);
		if(pageTitle.equalsIgnoreCase("Voucher App")) {
			ReportConfig.attachReport("PASS","Voucher Page Login", "User logged in successfully");
		}else {
			ReportConfig.attachReport("FAIL","Voucher Page Login", "User login unsuccessful");
		}

		//Voucher code generation
		VoucherPage.createCoupon(userEmail, name, cost, startDate, endDate, count);
		String []couponDetails=VoucherPage.viewCoupon(userEmail);

		if(couponDetails[1].contentEquals(userEmail)) {
			ReportConfig.attachReport("PASS","Voucher Creation", "Voucher created successfully");
			ReportConfig.attachReport("PASS","Voucher Code", "Voucher Code : "+couponDetails[0]);
		}else {
			ReportConfig.attachReport("FAIL","Voucher Creation", "Voucher created failed");

		}

	}

	@AfterClass
	public void quitBrowser() throws Exception {

		VoucherPage.logoutVoucherPage();
		ReportConfig.attachReport("INFO","User Logout", "User logged out successfully");
		ReportConfig.finalizeReport();
		closeBrowser();

	}

	@DataProvider(name = "TestData")
	public Object[][] getData(){
		return excelUtil.getTestData(testDataName, testDataSheetName);
	}

}
