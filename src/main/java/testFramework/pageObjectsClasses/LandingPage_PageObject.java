package testFramework.pageObjectsClasses;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import testFramework.abstractComponents.AbstractComponent;

public class LandingPage_PageObject extends AbstractComponent{
	WebDriver driver;
	WebDriverWait wait;
	
	@FindBy(css = "[data-product_id='54']")
	WebElement addToCartButtonProductID54;
	
	@FindBy(css = "[data-product_id='26']")
	WebElement addToCartButtonProductID26;
	
	@FindBy(css = ".added_to_cart.wc-forward")
	List<WebElement> viewCartList;
	
	@FindBy(css = ".added_to_cart.wc-forward")
	WebElement viewCart1;

	public LandingPage_PageObject(WebDriver driver) {
		super(driver);
		this.driver = driver;
		wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		PageFactory.initElements(driver, this);
	}
	
	public void hoverOverAddToCartID54() {
		moveToElement(addToCartButtonProductID54);
	}
	
	public void moveToAddToCartAndClickID54() {
		moveToElementAndClick(addToCartButtonProductID54);
	}
	
	public void moveToAddToCartAndClickID26() {
		moveToElementAndClick(addToCartButtonProductID26);
	}
	
	public boolean verifyAddToCartIsNotDisplayedID54() {
		return addToCartButtonProductID54.isDisplayed();
	}
	
	public boolean verifyAddToCartIsDisplayedID54() {
		wait.until(ExpectedConditions.visibilityOf(addToCartButtonProductID54));
		return addToCartButtonProductID54.isDisplayed();
	}
	
	public boolean verifyNumViewCartDisplayed(int numberOfUniqueProducts) {
		wait.until(ExpectedConditions.visibilityOf(viewCart1));
		if (viewCartList.size() == numberOfUniqueProducts) {
			return true;
		} else {
			return false;
		}
	}
}