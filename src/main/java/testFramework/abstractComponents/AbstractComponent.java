package testFramework.abstractComponents;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AbstractComponent {
	WebDriver driver;
	Actions actions;
	
	@FindBy(css = ".page_item.page-item-10")
	WebElement myAccountHeader;
	
	public AbstractComponent(WebDriver driver) {
		this.driver = driver;
		actions = new Actions(driver);
		PageFactory.initElements(driver, this);
	}
	
	public void moveToElement(WebElement targetElement) {
		actions.moveToElement(targetElement).perform();
	}
	
	public void moveToElementAndClick(WebElement targetElement) {
		actions.moveToElement(targetElement).click().build().perform();
	}
	
	public void clickOnMyAccount() {
		myAccountHeader.click();
	}
}