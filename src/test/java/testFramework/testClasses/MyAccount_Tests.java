package testFramework.testClasses;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertNotEquals;
import static org.testng.Assert.assertTrue;

import java.io.IOException;
import java.util.HashMap;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import testFramework.testComponents.BaseTest;



public class MyAccount_Tests extends BaseTest{
	
	@BeforeMethod(alwaysRun = true)
	public void goToMyAccountPage() {
		driver.navigate().to("https://cms.demo.katalon.com/");
		landingPage.clickOnMyAccount();
	}
	
	@Test(groups = {"AcceptanceTests"})
	public void verifyMyAccountPageExists() {
		assertNotEquals("Page not found – Katalon Shop", driver.getTitle());
		assertFalse(driver.getTitle().contains("Page not found – Katalon Shop"));
	}
	
	@Test
	public void verifyRememberMeCheckbox() {
		assertFalse(myAccountsPage.verifyIfRememberMeIsChecked());
		myAccountsPage.clickOnRememberMeCheckbox();
		assertTrue(myAccountsPage.verifyIfRememberMeIsChecked());
	}
	
	@Test(dataProvider = "incorrectlogInCredentials")
	public void invalidLogIn(HashMap<String, String> input) {
		myAccountsPage.loginToMyAccount(input.get("username"), input.get("password"));
		assertTrue(myAccountsPage.getErrorValidationText().equalsIgnoreCase("error"));
	}
	
	@DataProvider
	public Object[][] incorrectlogInCredentials() throws IOException {
		
		/*HashMap<String, String> map1 = new HashMap<String, String>();
		map1.put("username", "Balanar");
		map1.put("password", "Balanar23");
		HashMap<String, String> map2 = new HashMap<String, String>();
		map2.put("username", "Micko");
		map2.put("password", "Mile28");
		HashMap<String, String> map3 = new HashMap<String, String>();
		map3.put("username", "Pera");
		map3.put("password", "PeraLera");*/
		
		java.util.List<HashMap<String, String>> data = getJsonToHashMap(System.getProperty("user.dir") + "\\src\\test\\java\\testFramework\\data\\data.json");
		return new Object[][] {{data.get(0)},{data.get(1)},{data.get(2)}};
		
		//return new Object[][] { {"Balanar", "Balanar23"} , {"Micko", "Mile28"}, {"Pera", "PeraLera"} };
	}

}