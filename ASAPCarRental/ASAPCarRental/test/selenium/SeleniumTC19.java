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

public class SeleniumTC19 extends ASAP_BusinessFunctions {
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
  public void testSeleniumTC19() throws Exception {
    driver.get(sAppURL);
    A_BF_Login(driver, "tan93", "Test@934");
    
    driver.findElement(By.linkText("Settings")).click();
    driver.findElement(By.name(prop.getProperty("Txt_ASAPCarRental_UTAID"))).clear();
    driver.findElement(By.name(prop.getProperty("Txt_ASAPCarRental_UTAID"))).sendKeys("");
    driver.findElement(By.name(prop.getProperty("Txt_ASAPCarRental_Firstname"))).clear();
    driver.findElement(By.name(prop.getProperty("Txt_ASAPCarRental_Firstname"))).sendKeys("");
    driver.findElement(By.name(prop.getProperty("Txt_ASAPCarRental_Lastname"))).clear();
    driver.findElement(By.name(prop.getProperty("Txt_ASAPCarRental_Lastname"))).sendKeys("");
    driver.findElement(By.name(prop.getProperty("Txt_ASAPCarRental_Email"))).clear();
    driver.findElement(By.name(prop.getProperty("Txt_ASAPCarRental_Email"))).sendKeys("");
    driver.findElement(By.name(prop.getProperty("Txt_ASAPCarRental_Age"))).clear();
    driver.findElement(By.name(prop.getProperty("Txt_ASAPCarRental_Age"))).sendKeys("");
    driver.findElement(By.name(prop.getProperty("Txt_ASAPCarRental_Phone"))).clear();
    driver.findElement(By.name(prop.getProperty("Txt_ASAPCarRental_Phone"))).sendKeys("");
    driver.findElement(By.name("address")).clear();
    driver.findElement(By.name("address")).sendKeys("");
    driver.findElement(By.name("state")).clear();
    driver.findElement(By.name("state")).sendKeys("");
    driver.findElement(By.name(prop.getProperty("Txt_ASAPCarRental_Zipcode"))).clear();
    driver.findElement(By.name(prop.getProperty("Txt_ASAPCarRental_Zipcode"))).sendKeys("");
    driver.findElement(By.cssSelector("input[type=\"submit\"]")).click();
    assertEquals("UTA ID cannot be blank.", driver.findElement(By.name(prop.getProperty("Txt_ASAPCarRental_UTAID_Error"))).getAttribute("value"));
    assertEquals("First Name cannot be blank.", driver.findElement(By.name(prop.getProperty("Txt_ASAPCarRental_Firstname_Error"))).getAttribute("value"));
    assertEquals("Last Name cannot be blank.", driver.findElement(By.name(prop.getProperty("Txt_ASAPCarRental_Lastname_Error"))).getAttribute("value"));
    assertEquals("Email cannot be blank.", driver.findElement(By.name(prop.getProperty("Txt_ASAPCarRental_Email_Error"))).getAttribute("value"));
    assertEquals("Age cannot be blank.", driver.findElement(By.name(prop.getProperty("Txt_ASAPCarRental_Age_Error"))).getAttribute("value"));
    assertEquals("Phone no. cannot be blank.", driver.findElement(By.name(prop.getProperty("Txt_ASAPCarRental_Phone_Error"))).getAttribute("value"));
    assertEquals("Zipcode cannot be blank.", driver.findElement(By.name(prop.getProperty("Txt_ASAPCarRental_Zipcode_Error"))).getAttribute("value"));
    driver.findElement(By.name(prop.getProperty("Txt_ASAPCarRental_UTAID"))).clear();
    driver.findElement(By.name(prop.getProperty("Txt_ASAPCarRental_UTAID"))).sendKeys("1001503737s");
    driver.findElement(By.name(prop.getProperty("Txt_ASAPCarRental_Firstname"))).clear();
    driver.findElement(By.name(prop.getProperty("Txt_ASAPCarRental_Firstname"))).sendKeys("Tanmay11");
    driver.findElement(By.name(prop.getProperty("Txt_ASAPCarRental_Lastname"))).clear();
    driver.findElement(By.name(prop.getProperty("Txt_ASAPCarRental_Lastname"))).sendKeys("Borkar11");
    driver.findElement(By.name(prop.getProperty("Txt_ASAPCarRental_Email"))).clear();
    driver.findElement(By.name(prop.getProperty("Txt_ASAPCarRental_Email"))).sendKeys("test");
    driver.findElement(By.name(prop.getProperty("Txt_ASAPCarRental_RegPassword"))).clear();
    driver.findElement(By.name(prop.getProperty("Txt_ASAPCarRental_RegPassword"))).sendKeys("tanmay");
    driver.findElement(By.name("conpword")).clear();
    driver.findElement(By.name("conpword")).sendKeys("tanmay");
    driver.findElement(By.name(prop.getProperty("Txt_ASAPCarRental_Age"))).clear();
    driver.findElement(By.name(prop.getProperty("Txt_ASAPCarRental_Age"))).sendKeys("17");
    driver.findElement(By.name(prop.getProperty("Txt_ASAPCarRental_Phone"))).clear();
    driver.findElement(By.name(prop.getProperty("Txt_ASAPCarRental_Phone"))).sendKeys("682256263323");
    driver.findElement(By.name(prop.getProperty("Txt_ASAPCarRental_Zipcode"))).clear();
    driver.findElement(By.name(prop.getProperty("Txt_ASAPCarRental_Zipcode"))).sendKeys("7601sd");
    driver.findElement(By.name("aacTrue")).click();
    driver.findElement(By.cssSelector("input[type=\"submit\"]")).click();
    assertEquals("Your UTA ID must be a number", driver.findElement(By.name(prop.getProperty("Txt_ASAPCarRental_UTAID_Error"))).getAttribute("value"));
    assertEquals("Password should be less than 15 and more than 8 characters in length.", driver.findElement(By.name(prop.getProperty("Txt_ASAPCarRental_RegPassword_Error"))).getAttribute("value"));
    assertEquals("Email address needs to contain @", driver.findElement(By.name(prop.getProperty("Txt_ASAPCarRental_Email_Error"))).getAttribute("value"));
    assertEquals("Phone number must be 10 digits in length", driver.findElement(By.name(prop.getProperty("Txt_ASAPCarRental_Phone_Error"))).getAttribute("value"));
    assertEquals("Zipcode should be exactly 5 in length.", driver.findElement(By.name(prop.getProperty("Txt_ASAPCarRental_Zipcode_Error"))).getAttribute("value"));
    assertEquals("Age cannot be less than 18", driver.findElement(By.name(prop.getProperty("Txt_ASAPCarRental_Age_Error"))).getAttribute("value"));
    driver.findElement(By.name(prop.getProperty("Txt_ASAPCarRental_UTAID"))).clear();
    driver.findElement(By.name(prop.getProperty("Txt_ASAPCarRental_UTAID"))).sendKeys("100150373745");
    driver.findElement(By.name(prop.getProperty("Txt_ASAPCarRental_Firstname"))).clear();
    driver.findElement(By.name(prop.getProperty("Txt_ASAPCarRental_Firstname"))).sendKeys("T");
    driver.findElement(By.name(prop.getProperty("Txt_ASAPCarRental_Lastname"))).clear();
    driver.findElement(By.name(prop.getProperty("Txt_ASAPCarRental_Lastname"))).sendKeys("B");
    driver.findElement(By.name(prop.getProperty("Txt_ASAPCarRental_Phone"))).clear();
    driver.findElement(By.name(prop.getProperty("Txt_ASAPCarRental_Phone"))).sendKeys("68225626gh");
    driver.findElement(By.name(prop.getProperty("Txt_ASAPCarRental_Email"))).clear();
    driver.findElement(By.name(prop.getProperty("Txt_ASAPCarRental_Email"))).sendKeys("test@tesssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssst.com");
    driver.findElement(By.name(prop.getProperty("Txt_ASAPCarRental_RegPassword"))).clear();
    driver.findElement(By.name(prop.getProperty("Txt_ASAPCarRental_RegPassword"))).sendKeys("abcdefghijk");
    driver.findElement(By.name("conpword")).clear();
    driver.findElement(By.name("conpword")).sendKeys("abcdefghijk");
    driver.findElement(By.name(prop.getProperty("Txt_ASAPCarRental_Zipcode"))).clear();
    driver.findElement(By.name(prop.getProperty("Txt_ASAPCarRental_Zipcode"))).sendKeys("7601g");
    driver.findElement(By.cssSelector("input[type=\"submit\"]")).click();
    assertEquals("Your UTA Id must be 10 digits long.", driver.findElement(By.name(prop.getProperty("Txt_ASAPCarRental_UTAID_Error"))).getAttribute("value"));
    assertEquals("Password should contain atleast one upper case alphabet", driver.findElement(By.name(prop.getProperty("Txt_ASAPCarRental_RegPassword_Error"))).getAttribute("value"));
    assertEquals("Your First Name must be atleast 2 in length and atmost 25.", driver.findElement(By.name(prop.getProperty("Txt_ASAPCarRental_Firstname_Error"))).getAttribute("value"));
    assertEquals("Your Last Name must be atleast 2 in length and atmost 25.", driver.findElement(By.name(prop.getProperty("Txt_ASAPCarRental_Lastname_Error"))).getAttribute("value"));
    assertEquals("Email address must be between 7 and 45 characters long", driver.findElement(By.name(prop.getProperty("Txt_ASAPCarRental_Email_Error"))).getAttribute("value"));
    assertEquals("Phone number must be a number", driver.findElement(By.name(prop.getProperty("Txt_ASAPCarRental_Phone_Error"))).getAttribute("value"));
    assertEquals("Zipcode must be a number", driver.findElement(By.name(prop.getProperty("Txt_ASAPCarRental_Zipcode_Error"))).getAttribute("value"));
    driver.findElement(By.name(prop.getProperty("Txt_ASAPCarRental_Email"))).clear();
    driver.findElement(By.name(prop.getProperty("Txt_ASAPCarRental_Email"))).sendKeys("test@test.comgh");
    driver.findElement(By.name(prop.getProperty("Txt_ASAPCarRental_RegPassword"))).clear();
    driver.findElement(By.name(prop.getProperty("Txt_ASAPCarRental_RegPassword"))).sendKeys("ABCDEFGHIJK");
    driver.findElement(By.name("conpword")).clear();
    driver.findElement(By.name("conpword")).sendKeys("ABCDEFGHIJK");
    driver.findElement(By.cssSelector("input[type=\"submit\"]")).click();
    assertEquals("Password should contain atleast one lower case alphabet", driver.findElement(By.name(prop.getProperty("Txt_ASAPCarRental_RegPassword_Error"))).getAttribute("value"));
    // ERROR: Caught exception [Error: locator strategy either id or name must be specified explicitly.]
    driver.findElement(By.name(prop.getProperty("Txt_ASAPCarRental_RegPassword"))).clear();
    driver.findElement(By.name(prop.getProperty("Txt_ASAPCarRental_RegPassword"))).sendKeys("abcDEFgh");
    driver.findElement(By.name("conpword")).clear();
    driver.findElement(By.name("conpword")).sendKeys("abcDEFgh");
    driver.findElement(By.cssSelector("input[type=\"submit\"]")).click();
    assertEquals("Password should contain atleast one number.", driver.findElement(By.name(prop.getProperty("Txt_ASAPCarRental_RegPassword_Error"))).getAttribute("value"));
    driver.findElement(By.name(prop.getProperty("Txt_ASAPCarRental_RegPassword"))).clear();
    driver.findElement(By.name(prop.getProperty("Txt_ASAPCarRental_RegPassword"))).sendKeys("Test12345");
    driver.findElement(By.name("conpword")).clear();
    driver.findElement(By.name("conpword")).sendKeys("Test12345");
    driver.findElement(By.cssSelector("input[type=\"submit\"]")).click();
    assertEquals("Password should contain atleast one special character", driver.findElement(By.name(prop.getProperty("Txt_ASAPCarRental_RegPassword_Error"))).getAttribute("value"));
    driver.findElement(By.name(prop.getProperty("Txt_ASAPCarRental_RegPassword"))).clear();
    driver.findElement(By.name(prop.getProperty("Txt_ASAPCarRental_RegPassword"))).sendKeys("Test@934");
    driver.findElement(By.name("conpword")).clear();
    driver.findElement(By.name("conpword")).sendKeys("Test@934");
    driver.findElement(By.name("aacTrue")).click();
    driver.findElement(By.cssSelector("input[type=\"submit\"]")).click();
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
