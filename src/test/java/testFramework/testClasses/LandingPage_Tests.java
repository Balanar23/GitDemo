package testFramework.testClasses;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import testFramework.testComponents.BaseTest;
import testFramework.testComponents.Retry;

public class LandingPage_Tests extends BaseTest {
	
	@BeforeMethod(alwaysRun = true)
	public void goToCatalon() {
		driver.navigate().to("https://cms.demo.katalon.com/");
	}
	
	@Test(groups = "AcceptanceTests")
	public void verifyHoverAddToCartID54(){
		assertFalse(landingPage.verifyAddToCartIsNotDisplayedID54());
		landingPage.hoverOverAddToCartID54();
		assertTrue(landingPage.verifyAddToCartIsDisplayedID54());
		landingPage.moveToAddToCartAndClickID54();
		assertTrue(landingPage.verifyNumViewCartDisplayed(1));
	}
	
	@Test(retryAnalyzer = Retry.class)
	public void verifyTwoViewCart() {
		landingPage.moveToAddToCartAndClickID54();
		landingPage.moveToAddToCartAndClickID26();
		assertTrue(landingPage.verifyNumViewCartDisplayed(2));
	}
	
	
}