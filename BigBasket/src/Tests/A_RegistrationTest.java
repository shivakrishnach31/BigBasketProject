package Tests;

import java.awt.Image;
import java.awt.image.RenderedImage;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.testng.Reporter;
import org.testng.annotations.Test;

import com.asprise.util.ocr.OCR;

import java.net.URL;

import javax.imageio.ImageIO;

import testUtils.Helper;

public class A_RegistrationTest extends Helper {
	@Test
	public void BigBasketTest() throws IOException, Exception {
 	  
		/*Choosing Browser
	  	Based on the browser available in your computer and Opening Test Web site by choosing random city
		 */
		h.chooseBrowser();
		log.debug("Chosen Browser and Opened");
		driver.get(config.getProperty("testUrl"));
		log.debug(config.getProperty("testUrl")+"   "+"has been opened");
		h.citySelection();
		log.debug("Selected a city randomly");
 	  
 	  
	  
		/* ---------SignUP-------------- 
		 *
		 */
		log.debug("Filling registration form by getting required data from Excel sheet");
		h.waitForElement(15, By.linkText(or.getProperty("Register_linktext")));
		driver.findElement(By.linkText(or.getProperty("Register_linktext"))).click();
		driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
		driver.findElement(By.id(or.getProperty("email_id"))).sendKeys(sheetid.getCell(0,0).getContents());
		driver.findElement(By.id(or.getProperty("mobile_id"))).sendKeys(sheetid.getCell(0,1).getContents());
		driver.findElement(By.id(or.getProperty("pwd_id"))).sendKeys(sheetid.getCell(0,2).getContents());
		driver.findElement(By.id(or.getProperty("confirm_pwd_id"))).sendKeys(sheetid.getCell(0,3).getContents());
		((JavascriptExecutor)driver).executeScript("arguments[0].click();", driver.findElement(By.cssSelector(or.getProperty("salutation_id"))));
		//driver.findElement(By.id(or.getProperty("salutation_id"))).click();
		//List<WebElement> Salutations = driver.findElement(By.id(or.getProperty("salutation_ddown_id"))).findElements(By.tagName(or.getProperty("salutation_options_tag")));
		//System.out.println(Salutations.size());
		driver.findElement(By.className(or.getProperty("salutation_ddown_listitems"))).findElement(By.linkText(sheetid.getCell(0, 4).getContents())).click();
		driver.findElement(By.id(or.getProperty("firstname_id"))).sendKeys(sheetid.getCell(0,5).getContents());
		driver.findElement(By.id(or.getProperty("lastname_id"))).sendKeys(sheetid.getCell(0,6).getContents());
		driver.findElement(By.id(or.getProperty("dob_id"))).sendKeys(sheetid.getCell(0,7).getContents());
		driver.findElement(By.id(or.getProperty("landline_id"))).sendKeys(sheetid.getCell(0,8).getContents());
		driver.findElement(By.id(or.getProperty("addr_id"))).sendKeys(sheetid.getCell(0,9).getContents());
		driver.findElement(By.id(or.getProperty("addr2_id"))).sendKeys(sheetid.getCell(0,10).getContents());
		driver.findElement(By.id(or.getProperty("area_id"))).sendKeys(sheetid.getCell(0,11).getContents());
		driver.findElement(By.id(or.getProperty("apartment_id"))).sendKeys(sheetid.getCell(0,12).getContents());
		driver.findElement(By.id(or.getProperty("landmark_id"))).sendKeys(sheetid.getCell(0,13).getContents());
		driver.findElement(By.id(or.getProperty("zip_id"))).sendKeys(sheetid.getCell(0,14).getContents());
		String imageUrl = driver.findElement(By.className(or.getProperty("captcha_class"))).getAttribute(or.getProperty("captcha_url"));
		Reporter.log("Captcha Url is"+"  "+imageUrl);
		URL url = new URL(imageUrl);
		Image img = ImageIO.read(url);
		String s = new OCR().recognizeCharacters((RenderedImage)img);
		Reporter.log("Characters on Captcha image are"+"  "+s+ "    "+"and Length of Captcha is" +s.length());
		driver.findElement(By.id(or.getProperty("captcha_text_id"))).sendKeys(s);
		if (driver.findElement(By.id(or.getProperty("tnc_id"))).isSelected() == false)
		{
			driver.findElement(By.id(or.getProperty("tnc_id"))).click();
		}
		else
		{
			Reporter.log("you have already accepted terms & conditions");
		}
		driver.close();
	}
}
