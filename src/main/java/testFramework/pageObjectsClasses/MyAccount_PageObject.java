package testFramework.pageObjectsClasses;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import testFramework.abstractComponents.AbstractComponent;

public class MyAccount_PageObject extends AbstractComponent{
	WebDriver driver;
	WebDriverWait wait;
	
	@FindBy(id = "rememberme")
	WebElement rememberMeCheckbox;
	
	@FindBy(id = "username")
	WebElement usernameBox;
	
	@FindBy(id = "password")
	WebElement passwordBox;
	
	@FindBy(css = "[name='login']")
	WebElement loginButton;
	
	@FindBy(xpath = "//li/strong")
	WebElement errorAnnouncement;

	public MyAccount_PageObject(WebDriver driver) {
		super(driver);
		this.driver = driver;
		wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		PageFactory.initElements(driver, this);
	}
	
	public boolean verifyIfRememberMeIsChecked() {
		return rememberMeCheckbox.isSelected();
	}
	
	public void clickOnRememberMeCheckbox() {
		rememberMeCheckbox.click();
	}
	
	public void inputUsername(String username) {
		wait.until(ExpectedConditions.visibilityOf(usernameBox)).sendKeys(username);
	}
	
	public void inputPassword(String password) {
		wait.until(ExpectedConditions.visibilityOf(passwordBox)).sendKeys(password);
	}
	
	public void clickOnLogginButton() {
		wait.until(ExpectedConditions.elementToBeClickable(loginButton)).click();
	}
	
	public String getErrorValidationText() {
		return wait.until(ExpectedConditions.visibilityOf(errorAnnouncement)).getText();
	}
	
	public void loginToMyAccount(String username, String password) {
		inputUsername(username);
		inputPassword(password);
		clickOnLogginButton();
	}

}