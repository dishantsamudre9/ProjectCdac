package Pages;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import base.DriverFactory;

public class SearchResultPage {

	WebDriver driver;

	public SearchResultPage(WebDriver driver) {
		this.driver = driver;

		PageFactory.initElements(driver, this);
	}

	@FindBy(className = "title")
	WebElement title;
	
	public String getTitle() {

	    WebDriverWait wait =
	            new WebDriverWait(driver, Duration.ofSeconds(5));

	    return wait.until(ExpectedConditions
	            .visibilityOf(title))
	            .getText();
	}
	public boolean isProductsPageDisplayed() {

	    try {

	        WebDriverWait wait =
	                new WebDriverWait(DriverFactory.getDriver(),
	                        Duration.ofSeconds(5));

	        wait.until(ExpectedConditions.visibilityOf(title));

	        return true;

	    } catch (Exception e) {
	        return false;
	    }
	}

}
