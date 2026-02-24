package tests;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
/*
 Test Case:
 Login into SauceDemo
 and verify Products page.
*/
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import Pages.HomePage;
import Pages.SearchResultPage;
import base.BaseTest;
import base.DriverFactory;
import listeners.TestListener;
import utils.ExcelUtil;
import utils.ScreenshotUtil;

@Listeners(TestListener.class)
public class SearchTest extends BaseTest {
	
	 // ✅ DataProvider method
    @DataProvider(name = "loginData",parallel = true)
    public Object[][] loginData() {

        return ExcelUtil.getLoginData();
    }
    @Test(dataProvider = "loginData",
    	      groups = "regression",
    	      retryAnalyzer = utils.RetryAnalyzer.class)
    	public void loginTest(String username, String password) {

    	HomePage home = new HomePage(DriverFactory.getDriver());

    	    home.login(username, password);

    	    SearchResultPage products =
    	            new SearchResultPage(DriverFactory.getDriver());
    	    
    	    

    	    if (products.isProductsPageDisplayed()) {

    	        String text = products.getTitle();

    	        System.out.println("Page Title: " + text);
    	        
    	        ScreenshotUtil.captureScreenshot(
    	                DriverFactory.getDriver(),
    	                "loginTest");

    	        Assert.assertEquals(text, "Products");

    	    } else {

    	        System.out.println("Login failed for user: " + username);

    	        Assert.fail("Login not successful");
    	    }

    	}
}