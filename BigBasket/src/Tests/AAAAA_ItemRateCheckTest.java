package Tests;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import testUtils.Helper;

public class AAAAA_ItemRateCheckTest extends Helper {
	@Test
	public void rateCheck() {
		
		/*
		 * -------Choosing Browser
		 */
		
		h.chooseBrowser();
		driver.get(config.getProperty("testUrl"));
		h.citySelection();
		
		/*
		 * --------Clicking 1 category from left panel randomly
		 */
		
		h.waitForElement(30, By.className(or.getProperty("leftpanelcategories_class")));
		List<WebElement> leftpanelcategories = driver.findElement(By.id(or.getProperty("leftpanelmenu_id"))).findElements(By.className(or.getProperty("leftpanelcategories_class")));

		if(leftpanelcategories.isEmpty()) {
			Assert.fail("No Categories are available to select");
			log.debug("Checking single item rate everywhere task has been terminated due to inavailability of any Category");
		}
		
		int category = r.nextInt(leftpanelcategories.size());
		((JavascriptExecutor)driver).executeScript("arguments[0].click();", leftpanelcategories.get(category));
	  	h.sleep(10);
	  	
	  	/*
	  	 * ----- Storing all the items in a list and getting one random single item rate to a String
	  	 */
	  	
		List<WebElement> availableItems = driver.findElements(By.id(or.getProperty("availableproducts_id")));
		
		if (availableItems.isEmpty()) {
			Assert.fail("No items are avilable");
		}
		
		int item = r.nextInt(availableItems.size());
		h.sleep(30);
	  	String itemRate = availableItems.get(item).findElement(By.cssSelector(or.getProperty("ratevalue_css"))).getText();
	  
		if (itemRate.isEmpty()) {
			Assert.fail("No items are available");
		}
		
		itemRate = itemRate.substring(4);
		Reporter.log(itemRate);
		((JavascriptExecutor)driver).executeScript("arguments[0].click();", availableItems.get(item).findElement(By.cssSelector(or.getProperty("itemname_css"))));
		h.sleep(10);
		String itemRate2 = driver.findElement(By.className(or.getProperty("itemprice"))).getText();
		itemRate2 = itemRate2.substring(4);
		Reporter.log(itemRate2);
		
		/*
		 * ------- Comparing same item value in two differant pages 
		 */
		
		if (Double.parseDouble(itemRate) != Double.parseDouble(itemRate2)) {
			Reporter.log("Rates not matched across");
		}
		
		Reporter.log("Rates matched exactly");
		driver.close();
	}
}
