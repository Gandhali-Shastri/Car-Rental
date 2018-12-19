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

public class SeleniumTC20 extends ASAP_BusinessFunctions {
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
  public void testSeleniumTC21() throws Exception {
    driver.get(sAppURL);
    A_BF_Login(driver, "ggs8561", "Test@123");
    
    driver.findElement(By.xpath(prop.getProperty("Lnk_Home_RevokeUser"))).click();
    driver.findElement(By.name(prop.getProperty("Btn_Revoke_Search"))).click();
    assertEquals("Please enter username", driver.findElement(By.xpath(prop.getProperty("Txt_Revoke_UsernameError"))).getAttribute("value"));
    driver.findElement(By.name(prop.getProperty("Txt_Revoke_Usename"))).clear();
    driver.findElement(By.name(prop.getProperty("Txt_Revoke_Usename"))).sendKeys("gandhali");
    driver.findElement(By.name(prop.getProperty("Btn_Revoke_Search"))).click();
    driver.findElement(By.xpath(prop.getProperty("Lnk_Home_RevokeUser"))).click();
    driver.findElement(By.name(prop.getProperty("Txt_Revoke_Usename"))).clear();
    driver.findElement(By.name(prop.getProperty("Txt_Revoke_Usename"))).sendKeys("gaydtdu");
    driver.findElement(By.name(prop.getProperty("Btn_Revoke_Search"))).click();
    driver.findElement(By.linkText(prop.getProperty("Lnk_Home_UpdateUser"))).click();
    driver.findElement(By.name(prop.getProperty("Txt_Update_Username"))).clear();
    driver.findElement(By.name(prop.getProperty("Txt_Update_Username"))).sendKeys("ggs8561");
    driver.findElement(By.xpath(prop.getProperty("Btn_Update_Search"))).click();
    driver.findElement(By.name(prop.getProperty("Txt_Update_Age"))).clear();
    driver.findElement(By.name(prop.getProperty("Txt_Update_Age"))).sendKeys("19");
    driver.findElement(By.name(prop.getProperty("Txt_Update_Password"))).clear();
    driver.findElement(By.name(prop.getProperty("Txt_Update_Password"))).sendKeys("Test@123");
    driver.findElement(By.xpath(prop.getProperty("Btn_Update_UpdateUser"))).click();
    assertEquals("19", driver.findElement(By.name(prop.getProperty("Txt_Update_Age"))).getAttribute("value"));
    driver.findElement(By.xpath(prop.getProperty("Btn_Update_UpdateUser"))).click();
    driver.findElement(By.name(prop.getProperty("Txt_Update_Username"))).clear();
    driver.findElement(By.name(prop.getProperty("Txt_Update_Username"))).sendKeys("ggs8562");
    driver.findElement(By.xpath(prop.getProperty("Btn_Update_Search"))).click();
    driver.findElement(By.name(prop.getProperty("Txt_Update_Password"))).clear();
    driver.findElement(By.name(prop.getProperty("Txt_Update_Password"))).sendKeys("Test@123");
    driver.findElement(By.name(prop.getProperty("Txt_Update_UtaId"))).clear();
    driver.findElement(By.name(prop.getProperty("Txt_Update_UtaId"))).sendKeys("1001548562");
    driver.findElement(By.xpath(prop.getProperty("Btn_Update_Search"))).click();
    assertEquals("1001548562", driver.findElement(By.name(prop.getProperty("Txt_Update_UtaId"))).getAttribute("value"));
    driver.findElement(By.name(prop.getProperty("Txt_Update_Username"))).clear();
    driver.findElement(By.name(prop.getProperty("Txt_Update_Username"))).sendKeys("tanmay");
    driver.findElement(By.xpath(prop.getProperty("Btn_Update_Search"))).click();
    driver.findElement(By.name(prop.getProperty("Txt_Update_Age"))).clear();
    driver.findElement(By.name(prop.getProperty("Txt_Update_Age"))).sendKeys("");
    driver.findElement(By.name(prop.getProperty("Txt_Update_Phone"))).clear();
    driver.findElement(By.name(prop.getProperty("Txt_Update_Phone"))).sendKeys("");
    driver.findElement(By.name(prop.getProperty("Txt_Update_State"))).clear();
    driver.findElement(By.name(prop.getProperty("Txt_Update_State"))).sendKeys("");
    driver.findElement(By.name("aacTrue")).click();
    driver.findElement(By.xpath(prop.getProperty("Btn_Update_UpdateUser"))).click();
    assertEquals("Age cannot be blank.", driver.findElement(By.xpath(prop.getProperty("Txt_Update_AgeError"))).getAttribute("value"));
    assertEquals("Phone no. cannot be blank.", driver.findElement(By.xpath(prop.getProperty("Txt_Update_PhoneError"))).getAttribute("value"));
    driver.findElement(By.name(prop.getProperty("Txt_Update_Age"))).clear();
    driver.findElement(By.name(prop.getProperty("Txt_Update_Age"))).sendKeys("17");
    driver.findElement(By.xpath(prop.getProperty("Btn_Update_UpdateUser"))).click();
    assertEquals("Age cannot be less than 18", driver.findElement(By.xpath(prop.getProperty("Txt_Update_AgeError"))).getAttribute("value"));
    driver.findElement(By.name(prop.getProperty("Txt_Update_Phone"))).clear();
    driver.findElement(By.name(prop.getProperty("Txt_Update_Phone"))).sendKeys("285697145");
    driver.findElement(By.xpath(prop.getProperty("Btn_Update_UpdateUser"))).click();
    assertEquals("Phone number must be 10 digits in length", driver.findElement(By.xpath(prop.getProperty("Txt_Update_PhoneError"))).getAttribute("value"));
    driver.findElement(By.name(prop.getProperty("Txt_Update_Phone"))).clear();
    driver.findElement(By.name(prop.getProperty("Txt_Update_Phone"))).sendKeys("test123345");
    driver.findElement(By.xpath(prop.getProperty("Btn_Update_UpdateUser"))).click();
    assertEquals("Phone number must be a number", driver.findElement(By.xpath(prop.getProperty("Txt_Update_PhoneError"))).getAttribute("value"));
    driver.findElement(By.name(prop.getProperty("Txt_Update_Password"))).clear();
    driver.findElement(By.name(prop.getProperty("Txt_Update_Password"))).sendKeys("test");
    driver.findElement(By.name("aacTrue")).click();
    driver.findElement(By.xpath(prop.getProperty("Btn_Update_UpdateUser"))).click();
    assertEquals("Password should be less than 15 and more than 8 characters in length.", driver.findElement(By.xpath(prop.getProperty("Txt_Update_PasswordError"))).getAttribute("value"));
    driver.findElement(By.name(prop.getProperty("Txt_Update_Password"))).clear();
    driver.findElement(By.name(prop.getProperty("Txt_Update_Password"))).sendKeys("tanmay982");
    driver.findElement(By.xpath(prop.getProperty("Btn_Update_UpdateUser"))).click();
    assertEquals("Password Should not be same as user name", driver.findElement(By.xpath(prop.getProperty("Txt_Update_PasswordError"))).getAttribute("value"));
    driver.findElement(By.name(prop.getProperty("Txt_Update_Password"))).clear();
    driver.findElement(By.name(prop.getProperty("Txt_Update_Password"))).sendKeys("test9341");
    driver.findElement(By.name("aacTrue")).click();
    driver.findElement(By.xpath(prop.getProperty("Btn_Update_UpdateUser"))).click();
    assertEquals("Password should contain atleast one upper case alphabet", driver.findElement(By.xpath(prop.getProperty("Txt_Update_PasswordError"))).getAttribute("value"));
    driver.findElement(By.name(prop.getProperty("Txt_Update_Password"))).clear();
    driver.findElement(By.name(prop.getProperty("Txt_Update_Password"))).sendKeys("TEST@934");
    driver.findElement(By.xpath(prop.getProperty("Btn_Update_UpdateUser"))).click();
    assertEquals("Password should contain atleast one lower case alphabet", driver.findElement(By.xpath(prop.getProperty("Txt_Update_PasswordError"))).getAttribute("value"));
    driver.findElement(By.name(prop.getProperty("Txt_Update_Password"))).clear();
    driver.findElement(By.name(prop.getProperty("Txt_Update_Password"))).sendKeys("Test@ter");
    driver.findElement(By.xpath(prop.getProperty("Btn_Update_UpdateUser"))).click();
    assertEquals("Password should contain atleast one number.", driver.findElement(By.xpath(prop.getProperty("Txt_Update_PasswordError"))).getAttribute("value"));
    driver.findElement(By.name(prop.getProperty("Txt_Update_Password"))).clear();
    driver.findElement(By.name(prop.getProperty("Txt_Update_Password"))).sendKeys("Test9345");
    driver.findElement(By.xpath(prop.getProperty("Btn_Update_UpdateUser"))).click();
    assertEquals("Password should contain atleast one special character", driver.findElement(By.xpath(prop.getProperty("Txt_Update_PasswordError"))).getAttribute("value"));
    driver.findElement(By.name(prop.getProperty("Txt_Update_Password"))).clear();
    driver.findElement(By.name(prop.getProperty("Txt_Update_Password"))).sendKeys("Test@934");
    driver.findElement(By.name("aacTrue")).click();
    driver.findElement(By.name(prop.getProperty("Txt_Update_Age"))).clear();
    driver.findElement(By.name(prop.getProperty("Txt_Update_Age"))).sendKeys("27");
    driver.findElement(By.xpath(prop.getProperty("Btn_Update_UpdateUser"))).click();
    assertEquals("", driver.findElement(By.xpath(prop.getProperty("Txt_Update_PasswordError"))).getAttribute("value"));
    driver.findElement(By.linkText("Logout")).click();
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
