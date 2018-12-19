package selenium;

import java.util.regex.Pattern;
import java.io.FileInputStream;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import org.junit.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

import functions.ASAP_BusinessFunctions;

public class SeleniumTC07 extends ASAP_BusinessFunctions {
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
	public void testTC1HappyPath() throws Exception {
		driver.get(sAppURL);
		
		A_BF_Login(driver, "tanmay", "Test@934");
		
		driver.findElement(By.name(prop.getProperty("Txt_UserHome_capacity"))).clear();
		driver.findElement(By.name(prop.getProperty("Txt_UserHome_capacity"))).sendKeys("4");
		driver.findElement(By.id(prop.getProperty("Txt_UserHome_startdate"))).clear();
		driver.findElement(By.id(prop.getProperty("Txt_UserHome_startdate"))).sendKeys("12/20/2018");
		driver.findElement(By.id(prop.getProperty("Txt_UserHome_enddate"))).clear();
		driver.findElement(By.id(prop.getProperty("Txt_UserHome_enddate"))).sendKeys("01/05/2019");
		driver.findElement(By.cssSelector(prop.getProperty("Btn_UserHome_Search"))).click();
		try {
			assertEquals("", driver.findElement(By.xpath(prop.getProperty("start_Date_cannot_be_blank"))).getAttribute("value"));
		} catch (Error e) {
			verificationErrors.append(e.toString());
		}
		try {
			assertEquals("", driver.findElement(By.xpath(prop.getProperty("time_start_empty"))).getAttribute("value"));
		} catch (Error e) {
			verificationErrors.append(e.toString());
		}

		driver.findElement(By.xpath(prop.getProperty("Btn_UserHome_Select"))).click();
		driver.findElement(By.xpath(prop.getProperty("Txt_Payment_card_num"))).clear();
		driver.findElement(By.xpath(prop.getProperty("Txt_Payment_card_num"))).sendKeys("4123456789123456");
		driver.findElement(By.name(prop.getProperty("Txt_Payment_card_name"))).clear();
		driver.findElement(By.name(prop.getProperty("Txt_Payment_card_name"))).sendKeys("ggs");
		driver.findElement(By.xpath(prop.getProperty("Txt_Payment_cvv"))).clear();
		driver.findElement(By.xpath(prop.getProperty("Txt_Payment_cvv"))).sendKeys("111");
		driver.findElement(By.xpath(prop.getProperty("Btn_Payment_Payment"))).click();
		driver.findElement(By.xpath(prop.getProperty("Lnk_Index_Logout"))).click();
		driver.findElement(By.name(prop.getProperty("Txt_Index_Password"))).clear();
		driver.findElement(By.name(prop.getProperty("Txt_Index_Password"))).sendKeys("Test@934");
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
