package selenium;

import java.util.regex.Pattern;
import java.util.concurrent.TimeUnit;
import org.junit.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

import functions.ASAP_BusinessFunctions;

import java.util.Properties;
import java.io.FileInputStream;

public class SeleniumTC05 extends ASAP_BusinessFunctions {
	private String baseUrl;
	private boolean acceptNextAlert = true;
	private StringBuffer verificationErrors = new StringBuffer();
	public static String sAppURL, sSharedUIMapPath;

	@Before
	public void setUp() throws Exception {
		System.setProperty("webdriver.firefox.marionette", "C:\\GeckoSelenium\\geckodriver.exe");
		driver = new FirefoxDriver();
		baseUrl = "http://localhost:8080";
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		prop = new Properties();
		prop.load(new FileInputStream("./Configuration/ASAP_Configurations.properties"));
		sAppURL = prop.getProperty("sAppURL");
		sSharedUIMapPath = prop.getProperty("sSharedUIMap");
		prop.load(new FileInputStream(sSharedUIMapPath));

	}
	@Test
	public void testSeleniumTC05() throws Exception {
		driver.get(sAppURL);
		new Select(driver.findElement(By.id("userType"))).selectByVisibleText("Admin");
		driver.findElement(By.name(prop.getProperty("Txt_ASAPCarRental_UTAID"))).clear();
		driver.findElement(By.name(prop.getProperty("Txt_ASAPCarRental_UTAID"))).sendKeys("1001583762");
		driver.findElement(By.name(prop.getProperty("Txt_ASAPCarRental_Firstname"))).clear();
		driver.findElement(By.name(prop.getProperty("Txt_ASAPCarRental_Firstname"))).sendKeys("Wasiq");
		driver.findElement(By.name(prop.getProperty("Txt_ASAPCarRental_Lastname"))).clear();
		driver.findElement(By.name(prop.getProperty("Txt_ASAPCarRental_Lastname"))).sendKeys("Abbasi");
		driver.findElement(By.name("male")).click();
		driver.findElement(By.name(prop.getProperty("Txt_ASAPCarRental_Email"))).clear();
		driver.findElement(By.name(prop.getProperty("Txt_ASAPCarRental_Email"))).sendKeys("wasiq.ali.zia@gmail.com");
		driver.findElement(By.name(prop.getProperty("Txt_ASAPCarRental_RegUsername"))).clear();
		driver.findElement(By.name(prop.getProperty("Txt_ASAPCarRental_RegUsername"))).sendKeys("wasiqaliabbasi5");
		driver.findElement(By.name(prop.getProperty("Txt_ASAPCarRental_RegPassword"))).clear();
		driver.findElement(By.name(prop.getProperty("Txt_ASAPCarRental_RegPassword"))).sendKeys("Test@123");
		driver.findElement(By.name(prop.getProperty("Txt_ASAPCarRental_ConfirmPassword"))).clear();
		driver.findElement(By.name(prop.getProperty("Txt_ASAPCarRental_ConfirmPassword"))).sendKeys("Test@123");
		
		driver.findElement(By.xpath(prop.getProperty("Btn_ASAPCarRental_Register"))).click();
		
		A_BF_Login(driver, "wasiqaliabbasi5", "Test@123");
		
		assertTrue(isElementPresent(By.linkText(prop.getProperty("Link_ASAPCarRental_Logout"))));
		driver.findElement(By.linkText(prop.getProperty("Link_ASAPCarRental_Logout"))).click();
	}

	@After
	public void tearDown() throws Exception {
		driver.quit();
		String verificationErrorString = verificationErrors.toString();
		if (!"".equals(verificationErrorString)) {
			fail(verificationErrorString);
		}
	}

	private boolean isElementPresent(By by) {
		try {
			driver.findElement(by);
			return true;
		} catch (NoSuchElementException e) {
			return false;
		}
	}

	private boolean isAlertPresent() {
		try {
			driver.switchTo().alert();
			return true;
		} catch (NoAlertPresentException e) {
			return false;
		}
	}

	private String closeAlertAndGetItsText() {
		try {
			Alert alert = driver.switchTo().alert();
			String alertText = alert.getText();
			if (acceptNextAlert) {
				alert.accept();
			} else {
				alert.dismiss();
			}
			return alertText;
		} finally {
			acceptNextAlert = true;
		}
	}
}
