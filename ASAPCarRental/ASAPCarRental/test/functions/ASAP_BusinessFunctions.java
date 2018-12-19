package functions;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

import java.util.Properties;


public class ASAP_BusinessFunctions {
	public static WebDriver driver;
	public static Properties prop;

	public void A_BF_Login(WebDriver driver, String susername, String spassword ) {
		driver.findElement(By.name(prop.getProperty("Txt_ASAPCarRental_Username"))).clear();
		driver.findElement(By.name(prop.getProperty("Txt_ASAPCarRental_Username"))).sendKeys(susername);
		driver.findElement(By.name(prop.getProperty("Txt_ASAPCarRental_Password"))).clear();
		driver.findElement(By.name(prop.getProperty("Txt_ASAPCarRental_Password"))).sendKeys(spassword);
		driver.findElement(By.cssSelector(prop.getProperty("Btn_ASAPCarRental_Login"))).click();
	}
}
