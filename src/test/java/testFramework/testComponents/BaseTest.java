package testFramework.testComponents;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.ObjectUtils.Null;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import testFramework.pageObjectsClasses.LandingPage_PageObject;
import testFramework.pageObjectsClasses.MyAccount_PageObject;

public class BaseTest {
	private static final int TypeReference = 0;
	private static final int List = 0;
	private static final int HashMap = 0; 
	private static final int String = 0;
	public WebDriver driver;
	public LandingPage_PageObject landingPage;
	public MyAccount_PageObject myAccountsPage;
	
	public void initializeDriver() throws IOException {
		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream(System.getProperty("user.dir") + "\\src\\main\\java\\testFramework\\resources\\GlobalData.properties");
		prop.load(fis);
		
		String browser = System.getProperty("browser")!=null ? System.getProperty("browser") : prop.getProperty("browser");
		//String browser = prop.getProperty("browser");
		
		if(browser.contains("chrome")) {
			ChromeOptions options = new ChromeOptions();
			System.setProperty("WebDriver.chrome.driver", "C:\\Users\\Marko\\Desktop\\selenium\\chromedriver.exe");
			if(browser.contains("headless")) {
			options.addArguments("headless");
			}
			driver = new ChromeDriver(options);
			driver.manage().window().setSize(new Dimension(1440, 900));
		} else if (browser.equalsIgnoreCase("edge")){
			System.setProperty("WebDriver.edge.driver", "C:\\Users\\Marko\\Desktop\\selenium\\msedgedriver.exe");
			driver = new EdgeDriver();
		} else if (browser.equalsIgnoreCase("firefox")) {
			//firefox code
		}
		driver.manage().window().maximize();
	}
	
	@BeforeTest(alwaysRun = true)
	public void pleaseWork() throws IOException {
		initializeDriver();
		landingPage = new LandingPage_PageObject(driver);
		myAccountsPage = new MyAccount_PageObject(driver);
	}
	
	@AfterTest(alwaysRun = true)
	public void closeDriver() {
		driver.quit();
	}
	
	public String getScreenshot(String testCaseName, WebDriver driver) throws IOException {
		TakesScreenshot ts = (TakesScreenshot)driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		File file = new File(System.getProperty("user.dir") + "//reports//" + testCaseName + ".png");
		FileUtils.copyFile(source, file);
		return System.getProperty("user.dir") + "//reports//" + testCaseName + ".png";
	}
	
	public List<HashMap<String, String>> getJsonToHashMap(String filePath) throws IOException {
		String jsonContent = FileUtils.readFileToString(new File(filePath), StandardCharsets.UTF_8);
		
		ObjectMapper mapper = new ObjectMapper();
		List<HashMap<String, String>> data = mapper.readValue(jsonContent, new TypeReference<List<HashMap<String,String>>>() {
		});
		return data;
	}

}