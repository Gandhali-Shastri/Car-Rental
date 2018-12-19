package selenium;

import java.util.regex.Pattern;
import java.io.FileInputStream;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import org.junit.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import org.openqa.selenium.*;
import java.util.Properties;
import java.io.FileInputStream;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import functions.ASAP_BusinessFunctions;

public class SeleniumTC18 extends ASAP_BusinessFunctions {
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
	public void testCases() throws Exception {
		driver.get(sAppURL);
		WebDriverWait myWaitVar = new WebDriverWait(driver, 10);
		myWaitVar.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(prop.getProperty("Txt_Submit"))));
		A_BF_Login(driver, "ggs8562", "Test@123");
		
		driver.findElement(By.linkText(prop.getProperty("Link_Update"))).click();
		driver.findElement(By.name(prop.getProperty("Txt_Uta_Id"))).clear();
		driver.findElement(By.name(prop.getProperty("Txt_Uta_Id"))).sendKeys("");
		driver.findElement(By.name(prop.getProperty("Txt_First_Name"))).clear();
		driver.findElement(By.name(prop.getProperty("Txt_First_Name"))).sendKeys("@");
		driver.findElement(By.name(prop.getProperty("Txt_Last_Name"))).clear();
		driver.findElement(By.name(prop.getProperty("Txt_Last_Name"))).sendKeys("");
		driver.findElement(By.name(prop.getProperty("Txt_Email"))).clear();
		driver.findElement(By.name(prop.getProperty("Txt_Email"))).sendKeys("@@");
		driver.findElement(By.cssSelector(prop.getProperty("Txt_Submit"))).click();
		try {
			assertEquals("UTA ID cannot be blank.", driver.findElement(By.xpath(prop.getProperty("Txt_Car_NameError"))).getAttribute("value"));
		} catch (Error e) {
			verificationErrors.append(e.toString());
		}
		try {
			assertEquals("Your First Name must be atleast 2 in length and atmost 25.", driver.findElement(By.xpath(prop.getProperty("Txt_CapacityError"))).getAttribute("value"));
		} catch (Error e) {
			verificationErrors.append(e.toString());
		}
		try {
			assertEquals("Last Name cannot be blank.", driver.findElement(By.xpath(prop.getProperty("Txt_WeekDayError"))).getAttribute("value"));
		} catch (Error e) {
			verificationErrors.append(e.toString());
		}
		try {
			assertEquals("Email address must be between 7 and 45 characters long", driver.findElement(By.xpath(prop.getProperty("Txt_WeekEndError"))).getAttribute("value"));
		} catch (Error e) {
			verificationErrors.append(e.toString());
		}
		try {
			assertEquals("Password cannot be blank.", driver.findElement(By.xpath(prop.getProperty("Txt_WeekError"))).getAttribute("value"));
		} catch (Error e) {
			verificationErrors.append(e.toString());
		}
		driver.findElement(By.name(prop.getProperty("Txt_Uta_Id"))).clear();
		driver.findElement(By.name(prop.getProperty("Txt_Uta_Id"))).sendKeys("asdfghjklo");
		driver.findElement(By.name(prop.getProperty("Txt_First_Name"))).clear();
		driver.findElement(By.name(prop.getProperty("Txt_First_Name"))).sendKeys("@");
		driver.findElement(By.name(prop.getProperty("Txt_Last_Name"))).clear();
		driver.findElement(By.name(prop.getProperty("Txt_Last_Name"))).sendKeys("");
		driver.findElement(By.name(prop.getProperty("Txt_Email"))).clear();
		driver.findElement(By.name(prop.getProperty("Txt_Email"))).sendKeys("@@");
		driver.findElement(By.name(prop.getProperty("Txt_Password"))).clear();
		driver.findElement(By.name(prop.getProperty("Txt_Password"))).sendKeys("ggs8562");
		driver.findElement(By.name(prop.getProperty("Txt_Confirm_Password"))).clear();
		driver.findElement(By.name(prop.getProperty("Txt_Confirm_Password"))).sendKeys("ggs8562");
		driver.findElement(By.cssSelector(prop.getProperty("Txt_Submit"))).click();
		try {
			assertEquals("Your UTA ID must be a number", driver.findElement(By.xpath(prop.getProperty("Txt_Car_NameError"))).getAttribute("value"));
		} catch (Error e) {
			verificationErrors.append(e.toString());
		}
		driver.findElement(By.name(prop.getProperty("Txt_Uta_Id"))).clear();
		driver.findElement(By.name(prop.getProperty("Txt_Uta_Id"))).sendKeys("10013086");
		driver.findElement(By.cssSelector(prop.getProperty("Txt_Submit"))).click();
		try {
			assertEquals("Your UTA Id must be 10 digits long.", driver.findElement(By.xpath(prop.getProperty("Txt_Car_NameError"))).getAttribute("value"));
		} catch (Error e) {
			verificationErrors.append(e.toString());
		}
		driver.findElement(By.name(prop.getProperty("Txt_Uta_Id"))).clear();
		driver.findElement(By.name(prop.getProperty("Txt_Uta_Id"))).sendKeys("1001308637");
		driver.findElement(By.name(prop.getProperty("Txt_First_Name"))).clear();
		driver.findElement(By.name(prop.getProperty("Txt_First_Name"))).sendKeys("");
		driver.findElement(By.name(prop.getProperty("Txt_Last_Name"))).clear();
		driver.findElement(By.name(prop.getProperty("Txt_Last_Name"))).sendKeys("");
		driver.findElement(By.name(prop.getProperty("Txt_Email"))).clear();
		driver.findElement(By.name(prop.getProperty("Txt_Email"))).sendKeys("@@");
		driver.findElement(By.name(prop.getProperty("Txt_Password"))).clear();
		driver.findElement(By.name(prop.getProperty("Txt_Password"))).sendKeys("Test@123");
		driver.findElement(By.name(prop.getProperty("Txt_Confirm_Password"))).clear();
		driver.findElement(By.name(prop.getProperty("Txt_Confirm_Password"))).sendKeys("Test@123");
		driver.findElement(By.cssSelector(prop.getProperty("Txt_Submit"))).click();
		try {
			assertEquals("First Name cannot be blank.", driver.findElement(By.xpath(prop.getProperty("Txt_CapacityError"))).getAttribute("value"));
		} catch (Error e) {
			verificationErrors.append(e.toString());
		}
		driver.findElement(By.name(prop.getProperty("Txt_Uta_Id"))).clear();
		driver.findElement(By.name(prop.getProperty("Txt_Uta_Id"))).sendKeys("1001308637");
		driver.findElement(By.name(prop.getProperty("Txt_First_Name"))).clear();
		driver.findElement(By.name(prop.getProperty("Txt_First_Name"))).sendKeys("@");
		driver.findElement(By.name(prop.getProperty("Txt_Last_Name"))).clear();
		driver.findElement(By.name(prop.getProperty("Txt_Last_Name"))).sendKeys("a");
		driver.findElement(By.name(prop.getProperty("Txt_Email"))).clear();
		driver.findElement(By.name(prop.getProperty("Txt_Email"))).sendKeys("@@");
		driver.findElement(By.name(prop.getProperty("Txt_Password"))).clear();
		driver.findElement(By.name(prop.getProperty("Txt_Password"))).sendKeys("Test@123");
		driver.findElement(By.name(prop.getProperty("Txt_Confirm_Password"))).clear();
		driver.findElement(By.name(prop.getProperty("Txt_Confirm_Password"))).sendKeys("Test@123");
		driver.findElement(By.cssSelector(prop.getProperty("Txt_Submit"))).click();
		try {
			assertEquals("Your Last Name must be atleast 2 in length and atmost 25.", driver.findElement(By.xpath(prop.getProperty("Txt_WeekDayError"))).getAttribute("value"));
		} catch (Error e) {
			verificationErrors.append(e.toString());
		}
		driver.findElement(By.name(prop.getProperty("Txt_Uta_Id"))).clear();
		driver.findElement(By.name(prop.getProperty("Txt_Uta_Id"))).sendKeys("1001308637");
		driver.findElement(By.name(prop.getProperty("Txt_First_Name"))).clear();
		driver.findElement(By.name(prop.getProperty("Txt_First_Name"))).sendKeys("@");
		driver.findElement(By.name(prop.getProperty("Txt_Last_Name"))).clear();
		driver.findElement(By.name(prop.getProperty("Txt_Last_Name"))).sendKeys("");
		driver.findElement(By.name(prop.getProperty("Txt_Email"))).clear();
		driver.findElement(By.name(prop.getProperty("Txt_Email"))).sendKeys("nayaknameetyahho.com");
		driver.findElement(By.name(prop.getProperty("Txt_Password"))).clear();
		driver.findElement(By.name(prop.getProperty("Txt_Password"))).sendKeys("Test@123");
		driver.findElement(By.name(prop.getProperty("Txt_Confirm_Password"))).clear();
		driver.findElement(By.name(prop.getProperty("Txt_Confirm_Password"))).sendKeys("Test@123");
		driver.findElement(By.cssSelector(prop.getProperty("Txt_Submit"))).click();
		try {
			assertEquals("Email address needs to contain @", driver.findElement(By.xpath(prop.getProperty("Txt_WeekEndError"))).getAttribute("value"));
		} catch (Error e) {
			verificationErrors.append(e.toString());
		}
		driver.findElement(By.name(prop.getProperty("Txt_Email"))).clear();
		driver.findElement(By.name(prop.getProperty("Txt_Email"))).sendKeys("nayaknamee@tyahho");
		driver.findElement(By.cssSelector(prop.getProperty("Txt_Submit"))).click();
		Thread.sleep(10000);
		try {
			assertEquals("Invalid domain name", driver.findElement(By.xpath(prop.getProperty("Txt_WeekEndError"))).getAttribute("value"));
		} catch (Error e) {
			verificationErrors.append(e.toString());
		}
		driver.findElement(By.name(prop.getProperty("Txt_Password"))).clear();
		driver.findElement(By.name(prop.getProperty("Txt_Password"))).sendKeys("ggs856212");
		driver.findElement(By.cssSelector(prop.getProperty("Txt_Submit"))).click();
		try {
			assertEquals("Password Should not be same as user name", driver.findElement(By.xpath(prop.getProperty("Txt_WeekError"))).getAttribute("value"));
		} catch (Error e) {
			verificationErrors.append(e.toString());
		}
		driver.findElement(By.name(prop.getProperty("Txt_Password"))).clear();
		driver.findElement(By.name(prop.getProperty("Txt_Password"))).sendKeys("nameet12");
		driver.findElement(By.cssSelector(prop.getProperty("Txt_Submit"))).click();
		try {
			assertEquals("Password should contain atleast one upper case alphabet", driver.findElement(By.xpath(prop.getProperty("Txt_WeekError"))).getAttribute("value"));
		} catch (Error e) {
			verificationErrors.append(e.toString());
		}
		driver.findElement(By.name(prop.getProperty("Txt_Password"))).clear();
		driver.findElement(By.name(prop.getProperty("Txt_Password"))).sendKeys("NAMEET12");
		driver.findElement(By.cssSelector(prop.getProperty("Txt_Submit"))).click();
		try {
			assertEquals("Password should contain atleast one lower case alphabet", driver.findElement(By.xpath(prop.getProperty("Txt_WeekError"))).getAttribute("value"));
		} catch (Error e) {
			verificationErrors.append(e.toString());
		}
		driver.findElement(By.name(prop.getProperty("Txt_Password"))).clear();
		driver.findElement(By.name(prop.getProperty("Txt_Password"))).sendKeys("NAMeetqweqwe");
		driver.findElement(By.cssSelector(prop.getProperty("Txt_Submit"))).click();
		try {
			assertEquals("Password should contain atleast one number.", driver.findElement(By.xpath(prop.getProperty("Txt_WeekError"))).getAttribute("value"));
		} catch (Error e) {
			verificationErrors.append(e.toString());
		}
		driver.findElement(By.name(prop.getProperty("Txt_Password"))).clear();
		driver.findElement(By.name(prop.getProperty("Txt_Password"))).sendKeys("Nameet12");
		driver.findElement(By.cssSelector(prop.getProperty("Txt_Submit"))).click();
		try {
			assertEquals("Password should contain atleast one special character", driver.findElement(By.xpath(prop.getProperty("Txt_WeekError"))).getAttribute("value"));
		} catch (Error e) {
			verificationErrors.append(e.toString());
		}
		driver.findElement(By.xpath(".//*[@id='wrapper']/div[1]/div[2]/nav/div/a[5]")).click();
		driver.findElement(By.name("username")).clear();
		driver.findElement(By.name("username")).sendKeys("ggs8562");
		driver.findElement(By.name("password")).clear();
		driver.findElement(By.name("password")).sendKeys("Test@123");
		driver.findElement(By.cssSelector(prop.getProperty("Txt_Submit"))).click();
		driver.findElement(By.linkText("Update User")).click();
		driver.findElement(By.name(prop.getProperty("Txt_Uta_Id"))).clear();
		driver.findElement(By.name(prop.getProperty("Txt_Uta_Id"))).sendKeys("1001548562");
		driver.findElement(By.name(prop.getProperty("Txt_First_Name"))).clear();
		driver.findElement(By.name(prop.getProperty("Txt_First_Name"))).sendKeys("Gandhali");
		driver.findElement(By.name(prop.getProperty("Txt_Email"))).clear();
		driver.findElement(By.name(prop.getProperty("Txt_Email"))).sendKeys("gandh1ali@gmail.com");
		driver.findElement(By.name(prop.getProperty("Txt_Password"))).clear();
		driver.findElement(By.name(prop.getProperty("Txt_Password"))).sendKeys("Test@123");
		driver.findElement(By.name(prop.getProperty("Txt_Confirm_Password"))).clear();
		driver.findElement(By.name(prop.getProperty("Txt_Confirm_Password"))).sendKeys("Test@123");
		driver.findElement(By.name(prop.getProperty("Txt_Last_Name"))).clear();
		driver.findElement(By.name(prop.getProperty("Txt_Last_Name"))).sendKeys("Shastri");
		driver.findElement(By.cssSelector(prop.getProperty("Txt_Submit"))).click();
		driver.findElement(By.linkText(prop.getProperty("Txt_Logout"))).click();
		driver.get(baseUrl + "/ASAPCarRental/");
		driver.findElement(By.name("username")).clear();
		driver.findElement(By.name("username")).sendKeys("ggs8562");
		driver.findElement(By.name("password")).clear();
		driver.findElement(By.name("password")).sendKeys("Test@123");
		driver.findElement(By.cssSelector(prop.getProperty("Txt_Submit"))).click();
		driver.findElement(By.linkText("Manage Reservation")).click();
		driver.findElement(By.name("checkReservation")).click();
		try {
			assertEquals("Please enter reservation id", driver.findElement(By.name("firstNameerror")).getAttribute("value"));
		} catch (Error e) {
			verificationErrors.append(e.toString());
		}
		driver.findElement(By.linkText(prop.getProperty("Txt_Logout"))).click();
		driver.findElement(By.xpath(prop.getProperty("Txt_Login_Username"))).clear();
		driver.findElement(By.xpath(prop.getProperty("Txt_Login_Username"))).sendKeys("ggs8562");
		driver.findElement(By.xpath(prop.getProperty("Txt_Login_Password"))).clear();
		driver.findElement(By.xpath(prop.getProperty("Txt_Login_Password"))).sendKeys("Test@123");
		driver.findElement(By.cssSelector(prop.getProperty("Txt_Submit"))).click();
		driver.findElement(By.linkText("Manage Reservation")).click();
		driver.findElement(By.xpath(".//*[@id='wrapper']/form/table/tbody/tr[1]/td[2]/input")).clear();
		driver.findElement(By.xpath(".//*[@id='wrapper']/form/table/tbody/tr[1]/td[2]/input")).sendKeys("14");
		driver.findElement(By.name("checkReservation")).click();
		driver.findElement(By.linkText(prop.getProperty("Txt_Logout"))).click();
		driver.findElement(By.xpath(prop.getProperty("Txt_Login_Username"))).clear();
		driver.findElement(By.xpath(prop.getProperty("Txt_Login_Username"))).sendKeys("ggs8562");
		driver.findElement(By.xpath(prop.getProperty("Txt_Login_Password"))).clear();
		driver.findElement(By.xpath(prop.getProperty("Txt_Login_Password"))).sendKeys("Test@123");
		driver.findElement(By.cssSelector(prop.getProperty("Txt_Submit"))).click();
		driver.findElement(By.linkText("Manage Reservation")).click();
		driver.findElement(By.xpath(".//*[@id='wrapper']/form/table/tbody/tr[1]/td[2]/input")).clear();
		driver.findElement(By.xpath(".//*[@id='wrapper']/form/table/tbody/tr[1]/td[2]/input")).sendKeys("67");
		driver.findElement(By.name("checkReservation")).click();
		driver.findElement(By.xpath(".//*[@id='wrapper']/div[2]/form/table[2]/tbody/tr/td[5]/input")).click();
		driver.findElement(By.linkText(prop.getProperty("Txt_Logout"))).click();
		driver.get(baseUrl + "/ASAPCarRental/index.jsp");
		driver.findElement(By.name("username")).clear();
		driver.findElement(By.name("username")).sendKeys("ggs8562");
		driver.findElement(By.name("password")).clear();
		driver.findElement(By.name("password")).sendKeys("Test@123");
		driver.findElement(By.cssSelector(prop.getProperty("Txt_Submit"))).click();
		driver.findElement(By.cssSelector("input.btn-submit.m-t-0")).click();

		try {
			assertEquals("End date cannot be blank", driver.findElement(By.xpath(prop.getProperty("Txt_End_DateError"))).getAttribute("value"));
		} catch (Error e) {
			verificationErrors.append(e.toString());
		}
		driver.findElement(By.id("input-start")).clear();
		driver.findElement(By.id("input-start")).sendKeys("09/11/2018");
		driver.findElement(By.id("input-end")).clear();
		driver.findElement(By.id("input-end")).sendKeys("08/11/2018");
		driver.findElement(By.cssSelector("input.btn-submit.m-t-0")).click();

		driver.findElement(By.id("input-start")).clear();
		driver.findElement(By.id("input-start")).sendKeys("13/11/2018");
		driver.findElement(By.id("input-end")).clear();
		driver.findElement(By.id("input-end")).sendKeys("18/11/2018");
		driver.findElement(By.cssSelector("input.btn-submit.m-t-0")).click();


		driver.findElement(By.cssSelector("input.btn-submit.m-t-0")).click();
		driver.findElement(By.linkText(prop.getProperty("Txt_Logout"))).click();
		driver.get(baseUrl + "/ASAPCarRental/index.jsp");
		driver.findElement(By.xpath(prop.getProperty("Txt_Login_Username"))).clear();
		driver.findElement(By.xpath(prop.getProperty("Txt_Login_Username"))).sendKeys("ggs8562");
		driver.findElement(By.xpath(prop.getProperty("Txt_Login_Password"))).clear();
		driver.findElement(By.xpath(prop.getProperty("Txt_Login_Password"))).sendKeys("Test@123");
		driver.findElement(By.cssSelector(prop.getProperty("Txt_Submit"))).click();
		driver.findElement(By.xpath(prop.getProperty("Txt_StartXpath"))).clear();
		driver.findElement(By.xpath(prop.getProperty("Txt_StartXpath"))).sendKeys("11/29/2018");
		driver.findElement(By.xpath(prop.getProperty("Txt_EndXpath"))).clear();
		driver.findElement(By.xpath(prop.getProperty("Txt_EndXpath"))).sendKeys("12/01/2019");
		driver.findElement(By.cssSelector("input.btn-submit.m-t-0")).click();
		driver.findElement(By.linkText(prop.getProperty("Txt_Logout"))).click();
		driver.get("http://localhost:8080/ASAPCarRental/index.jsp");
		driver.findElement(By.name("username")).clear();
		driver.findElement(By.name("username")).sendKeys("ggs8562");
		driver.findElement(By.name("password")).clear();
		driver.findElement(By.name("password")).sendKeys("Test@123");
		driver.findElement(By.cssSelector(prop.getProperty("Txt_Submit"))).click();
		driver.findElement(By.linkText("Add Car")).click();
		driver.findElement(By.name(prop.getProperty("Txt_Car_Name"))).clear();
		driver.findElement(By.name(prop.getProperty("Txt_Car_Name"))).sendKeys("$1c1");
		driver.findElement(By.cssSelector(prop.getProperty("Txt_Submit"))).click();
		try {
			assertEquals("Invalid name format(Please enter only letters)", driver.findElement(By.xpath(prop.getProperty("Txt_Car_NameError"))).getAttribute("value"));
		} catch (Error e) {
			verificationErrors.append(e.toString());
		}
		driver.findElement(By.name(prop.getProperty("Txt_Car_Name"))).clear();
		driver.findElement(By.name(prop.getProperty("Txt_Car_Name"))).sendKeys("");
		driver.findElement(By.cssSelector(prop.getProperty("Txt_Submit"))).click();
		try {
			assertEquals("Name cannot be empty!", driver.findElement(By.xpath(prop.getProperty("Txt_Car_NameError"))).getAttribute("value"));
		} catch (Error e) {
			verificationErrors.append(e.toString());
		}
		driver.findElement(By.cssSelector(prop.getProperty("Txt_Submit"))).click();
		try {
			assertEquals("Capacity of car must be entered.", driver.findElement(By.xpath(prop.getProperty("Txt_CapacityError"))).getAttribute("value"));
		} catch (Error e) {
			verificationErrors.append(e.toString());
		}
		try {
			assertEquals("Price of car must be entered.", driver.findElement(By.xpath(prop.getProperty("Txt_WeekDayError"))).getAttribute("value"));
		} catch (Error e) {
			verificationErrors.append(e.toString());
		}
		try {
			assertEquals("Price of car must be entered.", driver.findElement(By.xpath(prop.getProperty("Txt_WeekEndError"))).getAttribute("value"));
		} catch (Error e) {
			verificationErrors.append(e.toString());
		}
		try {
			assertEquals("Price of car must be entered.", driver.findElement(By.xpath(prop.getProperty("Txt_WeekError"))).getAttribute("value"));
		} catch (Error e) {
			verificationErrors.append(e.toString());
		}
		try {
			assertEquals("This field cannot be empty", driver.findElement(By.xpath(prop.getProperty("Txt_GpsError"))).getAttribute("value"));
		} catch (Error e) {
			verificationErrors.append(e.toString());
		}
		try {
			assertEquals("This field cannot be empty", driver.findElement(By.xpath(prop.getProperty("Txt_siriusError"))).getAttribute("value"));
		} catch (Error e) {
			verificationErrors.append(e.toString());
		}
		try {
			assertEquals("This field cannot be empty", driver.findElement(By.xpath(prop.getProperty("Txt_siriusError"))).getAttribute("value"));
		} catch (Error e) {
			verificationErrors.append(e.toString());
		}
		driver.findElement(By.cssSelector(prop.getProperty("Txt_Submit"))).click();
		driver.findElement(By.name(prop.getProperty("Txt_WeekDay"))).clear();
		driver.findElement(By.name(prop.getProperty("Txt_WeekDay"))).sendKeys("hundred");
		driver.findElement(By.name(prop.getProperty("Txt_WeekEnd"))).clear();
		driver.findElement(By.name(prop.getProperty("Txt_WeekEnd"))).sendKeys("fifty");
		driver.findElement(By.name(prop.getProperty("Txt_Week"))).clear();
		driver.findElement(By.name(prop.getProperty("Txt_Week"))).sendKeys("ninety");
		driver.findElement(By.name(prop.getProperty("Txt_Gps"))).clear();
		driver.findElement(By.name(prop.getProperty("Txt_Gps"))).sendKeys("seven");
		driver.findElement(By.name(prop.getProperty("Txt_OnStar"))).clear();
		driver.findElement(By.name(prop.getProperty("Txt_OnStar"))).sendKeys("five");
		driver.findElement(By.name(prop.getProperty("Txt_sirius"))).clear();
		driver.findElement(By.name(prop.getProperty("Txt_sirius"))).sendKeys("eight");
		driver.findElement(By.name(prop.getProperty("Txt_Capacity"))).clear();
		driver.findElement(By.name(prop.getProperty("Txt_Capacity"))).sendKeys("five");
		driver.findElement(By.name(prop.getProperty("Txt_Car_Name"))).clear();
		driver.findElement(By.name(prop.getProperty("Txt_Car_Name"))).sendKeys("standard");
		driver.findElement(By.cssSelector(prop.getProperty("Txt_Submit"))).click();
		try {
			assertEquals("Capacity must only be numbers", driver.findElement(By.xpath(prop.getProperty("Txt_CapacityError"))).getAttribute("value"));
		} catch (Error e) {
			verificationErrors.append(e.toString());
		}
		try {
			assertEquals("Price must only be numbers", driver.findElement(By.xpath(prop.getProperty("Txt_WeekDayError"))).getAttribute("value"));
		} catch (Error e) {
			verificationErrors.append(e.toString());
		}
		try {
			assertEquals("Price must only be numbers", driver.findElement(By.xpath(prop.getProperty("Txt_WeekEndError"))).getAttribute("value"));
		} catch (Error e) {
			verificationErrors.append(e.toString());
		}
		try {
			assertEquals("Price must only be numbers", driver.findElement(By.xpath(prop.getProperty("Txt_WeekError"))).getAttribute("value"));
		} catch (Error e) {
			verificationErrors.append(e.toString());
		}
		try {
			assertEquals("Price must only be numbers", driver.findElement(By.xpath(prop.getProperty("Txt_GpsError"))).getAttribute("value"));
		} catch (Error e) {
			verificationErrors.append(e.toString());
		}
		try {
			assertEquals("Price must only be numbers", driver.findElement(By.xpath(prop.getProperty("Txt_siriusError"))).getAttribute("value"));
		} catch (Error e) {
			verificationErrors.append(e.toString());
		}
		try {
			assertEquals("Price must only be numbers", driver.findElement(By.xpath(prop.getProperty("Txt_siriusError"))).getAttribute("value"));
		} catch (Error e) {
			verificationErrors.append(e.toString());
		}
		driver.findElement(By.xpath(".//*[@id='wrapper']/div[1]/div[2]/nav/div/a[5]")).click();
		driver.findElement(By.xpath(prop.getProperty("Txt_Login_Username"))).clear();
		driver.findElement(By.xpath(prop.getProperty("Txt_Login_Username"))).sendKeys("ggs8562");
		driver.findElement(By.xpath(prop.getProperty("Txt_Login_Password"))).clear();
		driver.findElement(By.xpath(prop.getProperty("Txt_Login_Password"))).sendKeys("Test@123");
		driver.findElement(By.cssSelector(prop.getProperty("Txt_Submit"))).click();
		driver.findElement(By.linkText("Add Car")).click();
		driver.findElement(By.name(prop.getProperty("Txt_Car_Name"))).clear();
		driver.findElement(By.name(prop.getProperty("Txt_Car_Name"))).sendKeys("Testcase");
		driver.findElement(By.name(prop.getProperty("Txt_Capacity"))).clear();
		driver.findElement(By.name(prop.getProperty("Txt_Capacity"))).sendKeys("5");
		driver.findElement(By.name(prop.getProperty("Txt_WeekDay"))).clear();
		driver.findElement(By.name(prop.getProperty("Txt_WeekDay"))).sendKeys("51");
		driver.findElement(By.name(prop.getProperty("Txt_WeekEnd"))).clear();
		driver.findElement(By.name(prop.getProperty("Txt_WeekEnd"))).sendKeys("55");
		driver.findElement(By.name(prop.getProperty("Txt_Week"))).clear();
		driver.findElement(By.name(prop.getProperty("Txt_Week"))).sendKeys("390");
		driver.findElement(By.name(prop.getProperty("Txt_Gps"))).clear();
		driver.findElement(By.name(prop.getProperty("Txt_Gps"))).sendKeys("7");
		driver.findElement(By.name(prop.getProperty("Txt_sirius"))).clear();
		driver.findElement(By.name(prop.getProperty("Txt_sirius"))).sendKeys("5");
		driver.findElement(By.name(prop.getProperty("Txt_OnStar"))).clear();
		driver.findElement(By.name(prop.getProperty("Txt_OnStar"))).sendKeys("7");
		driver.findElement(By.cssSelector(prop.getProperty("Txt_Submit"))).click();
		driver.findElement(By.linkText(prop.getProperty("Txt_Logout"))).click();
		System.out.println("yes");
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
