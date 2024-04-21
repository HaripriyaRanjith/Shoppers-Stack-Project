package pages;

import java.util.List;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import commons.BaseTest;

public class ProductsSearchPage extends BaseTest{
	
	public ProductsSearchPage(WebDriver webdriver) {
		driver=webdriver;
	}
	
	@FindBy(xpath = "(//*[@class='cat_box__jl5G7']/div/div[3]/div/span[2])")
	static List<WebElement> productDescription;
	
	@FindBy(xpath = "(//*[@class='cat_box__jl5G7']/div/div[3]/div[2]/button)")
	static List<WebElement> addToCartButton;
	
	@FindBy(id = "search")
	static WebElement searchSpace;
	
	@FindBy(id = "searchBtn")
	static WebElement searchButton;
	
	public static void invokeElements() {
		PageFactory.initElements(driver,ProductsSearchPage.class);
	}
	
	public static String addProductToCart(String productName,String productDesc) throws Exception {
		
		searchSpace.clear();
		searchSpace.sendKeys(productName);
		searchButton.click();
		delay(2);
		String status="No product";
		
		for (int i = 0; i <productDescription.size() ; i++) {
			moveToElement(productDescription.get(i));
			if((productDescription.get(i).getText().trim()).equalsIgnoreCase(productDesc)) {
				moveToElement(addToCartButton.get(i));
				addToCartButton.get(i).click();
				delay(2);
				status= "added to cart";
				break;
			}
		}
		return status;
		
	}

}
