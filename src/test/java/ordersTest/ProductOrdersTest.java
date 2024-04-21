package ordersTest;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import commons.BaseTest;
import commons.ReportConfig;
import commons.excelUtil;
import pages.AddressPage;
import pages.CartPage;
import pages.HomePage;
import pages.LoginPage;
import pages.PaymentMethodPage;
import pages.ProductsSearchPage;

public class ProductOrdersTest extends BaseTest{

	public String browserType=getPropertiesValue("browserType");
	public String applicationUrl=getPropertiesValue("applicationURL");
	public String userEmail=getPropertiesValue("userEmail");
	public String userPassword=getPropertiesValue("password");
	public String netbankingPassword=getPropertiesValue("netbankingPassword");
	public String testDataName=getPropertiesValue("testData");
	public String testDataSheetName=getPropertiesValue("testDataSheetName");


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

	@Test(dataProvider ="TestData",priority = 1)
	public void addProductsToCart(String Products,String ProductDescription) throws Exception {

		//Adding the products to cart
		ProductsSearchPage.invokeElements();

		String cartStatus=ProductsSearchPage.addProductToCart(Products, ProductDescription);
		if(cartStatus.equalsIgnoreCase("added to cart")) {
			ReportConfig.attachReport("PASS",Products+" added to cart",Products + " : "+ProductDescription +"Added to cart successfully");
		}else {
			ReportConfig.attachReport("FAIL",Products+" not added",Products + " : "+ProductDescription +"Product not added to cart/ Product is not available");
		}
	}

	@Test(priority = 2)
	public void checkOut() throws Exception {
		
		//Proceed to check out
		String pageTitle=CartPage.cartProducts();
		if(pageTitle.equalsIgnoreCase("ShoppersStack | Cart")) {
			ReportConfig.attachReport("PASS","Cart Page Navigation","Navigated to cart page");
		}else {
			ReportConfig.attachReport("FAIL","Cart Page Navigation","Not in cart page");
		}

		CartPage.proceedToBuy();
		ReportConfig.attachReport("INFO","In address Page","Delivery Address selection");

		//Select addressDetails
		AddressPage.selectAddress();

		//Payment details
		String[] orderDetails=PaymentMethodPage.selectPaymentMethod(userEmail,netbankingPassword);

		if(orderDetails[0].equalsIgnoreCase("Order Confirmed")) {
			ReportConfig.attachReport("PASS","Order Confirmed","Order placed successfully");
			ReportConfig.attachReport("PASS","Order Number","Order Number : "+orderDetails[1]);
		}else {
			ReportConfig.attachReport("FAIL","Order failed","Order placement unsuccessful");
		}

	}

	@DataProvider(name = "TestData")
	public Object[][] getData(){
		return excelUtil.getTestData(testDataName, testDataSheetName);
	}

	@AfterClass
	public void quitBrowser() throws Exception {

		HomePage.userLogout();
		ReportConfig.attachReport("INFO","User Logout", "User logged out successfully");
		ReportConfig.finalizeReport();
		closeBrowser();

	}


}
