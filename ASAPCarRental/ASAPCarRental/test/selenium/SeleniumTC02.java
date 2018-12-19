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

public class SeleniumTC02 extends ASAP_BusinessFunctions {
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
	public void testSeleniumTC02() throws Exception {
		driver.get(sAppURL);
		driver.findElement(By.name(prop.getProperty("Txt_ASAPCarRental_Password"))).clear();
		driver.findElement(By.name(prop.getProperty("Txt_ASAPCarRental_Password"))).sendKeys("Test@934");
		driver.findElement(By.cssSelector(prop.getProperty("Btn_ASAPCarRental_Login"))).click();
		assertEquals("Username cannot be blank.", driver.findElement(By.name(prop.getProperty("Txt_ASAPCarRental_Username_Error"))).getAttribute("value"));
		driver.findElement(By.name(prop.getProperty("Txt_ASAPCarRental_Username"))).clear();
		driver.findElement(By.name(prop.getProperty("Txt_ASAPCarRental_Username"))).sendKeys("gandhal");
		driver.findElement(By.name(prop.getProperty("Txt_ASAPCarRental_Password"))).clear();
		driver.findElement(By.name(prop.getProperty("Txt_ASAPCarRental_Password"))).sendKeys("TEST@934");
		driver.findElement(By.cssSelector(prop.getProperty("Btn_ASAPCarRental_Login"))).click();
		assertEquals("Username not found", driver.findElement(By.name(prop.getProperty("Txt_ASAPCarRental_Username_Error"))).getAttribute("value"));
		driver.findElement(By.name(prop.getProperty("Txt_ASAPCarRental_Username"))).clear();
		driver.findElement(By.name(prop.getProperty("Txt_ASAPCarRental_Username"))).sendKeys("gandhali");
		driver.findElement(By.cssSelector(prop.getProperty("Btn_ASAPCarRental_Login"))).click();
		assertEquals("Password cannot be empty", driver.findElement(By.name(prop.getProperty("Txt_ASAPCarRental_Password_Error"))).getAttribute("value"));
		driver.findElement(By.name(prop.getProperty("Txt_ASAPCarRental_Username"))).clear();
		driver.findElement(By.name(prop.getProperty("Txt_ASAPCarRental_Username"))).sendKeys("gandhali");
		driver.findElement(By.name(prop.getProperty("Txt_ASAPCarRental_Password"))).clear();
		driver.findElement(By.name(prop.getProperty("Txt_ASAPCarRental_Password"))).sendKeys("TEST1");
		driver.findElement(By.cssSelector(prop.getProperty("Btn_ASAPCarRental_Login"))).click();
		assertEquals("Password Incorrect", driver.findElement(By.name(prop.getProperty("Txt_ASAPCarRental_Password_Error"))).getAttribute("value"));
		driver.findElement(By.name(prop.getProperty("Txt_ASAPCarRental_Username"))).clear();
		driver.findElement(By.name(prop.getProperty("Txt_ASAPCarRental_Username"))).sendKeys("gandhali");
		driver.findElement(By.name(prop.getProperty("Txt_ASAPCarRental_Password"))).clear();
		driver.findElement(By.name(prop.getProperty("Txt_ASAPCarRental_Password"))).sendKeys("Test@123");
		driver.findElement(By.cssSelector(prop.getProperty("Btn_ASAPCarRental_Login"))).click();
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
