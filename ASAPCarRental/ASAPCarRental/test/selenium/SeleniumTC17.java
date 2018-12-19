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

import java.io.FileInputStream;
import java.util.Properties;

public class SeleniumTC17 extends ASAP_BusinessFunctions {
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
	public void testTC11UpdateUserHappyPath() throws Exception {
		driver.get(sAppURL);
		A_BF_Login(driver, "tanmay", "Test@934");

		driver.findElement(By.xpath(prop.getProperty("Lnk_userReservation_Update"))).click();
		driver.findElement(By.name(prop.getProperty("Txt_UserUpdate_Email"))).clear();
		driver.findElement(By.name(prop.getProperty("Txt_UserUpdate_Email"))).sendKeys("test@test.com");
		String Txt_UserUpdate_Emailid = driver.findElement(By.name(prop.getProperty("Txt_UserUpdate_Email"))).getAttribute("value");
		driver.findElement(By.name(prop.getProperty("Txt_UserUpdate_password"))).clear();
		driver.findElement(By.name(prop.getProperty("Txt_UserUpdate_password"))).sendKeys("Test@934");
		driver.findElement(By.name(prop.getProperty("Txt_UserUpdate_con_password"))).clear();
		driver.findElement(By.name(prop.getProperty("Txt_UserUpdate_con_password"))).sendKeys("Test@934");
		String pwd = driver.findElement(By.name(prop.getProperty("Txt_UserUpdate_password"))).getAttribute("value");
		driver.findElement(By.name(prop.getProperty("Txt_UserUpdate_age"))).clear();
		driver.findElement(By.name(prop.getProperty("Txt_UserUpdate_age"))).sendKeys("25");
		driver.findElement(By.cssSelector(prop.getProperty("Btn_Submit"))).click();
		driver.findElement(By.xpath(prop.getProperty("Lnk_Index_Logout"))).click();
		driver.findElement(By.name(prop.getProperty("Txt_Index_Password"))).clear();
		driver.findElement(By.name(prop.getProperty("Txt_Index_Password"))).sendKeys("Test@934");
		driver.findElement(By.name(prop.getProperty("Txt_Index_Username"))).clear();
		driver.findElement(By.name(prop.getProperty("Txt_Index_Username"))).sendKeys("tanmay");
		driver.findElement(By.cssSelector(prop.getProperty("Btn_Submit"))).click();
		driver.findElement(By.xpath(prop.getProperty("Lnk_userReservation_Update"))).click();
		try {
			assertEquals(Txt_UserUpdate_Emailid, driver.findElement(By.name(prop.getProperty("Txt_UserUpdate_Email"))).getAttribute("value"));
		} catch (Error e) {
			verificationErrors.append(e.toString());
		}
		driver.findElement(By.xpath(prop.getProperty("Lnk_Index_Logout"))).click();
		driver.findElement(By.name(prop.getProperty("Txt_Index_Username"))).clear();
		driver.findElement(By.name(prop.getProperty("Txt_Index_Username"))).sendKeys("tanmay");
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
