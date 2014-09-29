package Tests;

import java.util.List;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import testUtils.Helper;

public class AAA_AddToBasketTest extends Helper{
  
  @Test
  public void pickitems() throws Exception  
  	{
	  h.chooseBrowser();
	  driver.get(config.getProperty("testUrl"));
	  //driver.manage().window().maximize();
	  
	  /*--------- Selecting Location -------------------
	   * 
	   */
	  h.citySelection();

		
	  /*
	   *------- Selection random Category from left panel through list--------------- 
	   */
	  
	  log.debug("Selecting a item category from left navigation panel, to add 3 items to basket");
	  
	  h.waitForElement(30, By.className(or.getProperty("leftpanelcategories_class")));
	  List<WebElement> leftpanelcategories = driver.findElement(By.id(or.getProperty("leftpanelmenu_id"))).findElements(By.className(or.getProperty("leftpanelcategories_class")));
	  if  (leftpanelcategories.isEmpty()) {
		  Assert.fail("No Categories are available to select");
		  log.debug("Adding items to basket task has been terminated due to inavailability of any Category");
	  }

	  Random r = new Random();
	  int category = r.nextInt(leftpanelcategories.size());
	  ((JavascriptExecutor)driver).executeScript("arguments[0].click();", leftpanelcategories.get(category));
	  	
	  /*
	   * --- Adding 3 different items randamly to the basket
	   */
	  
	  log.debug("Adding items to basket");
	  for (int i = 0; i < 3; i++) {
		  h.waitForElement(30, By.cssSelector(or.getProperty("itemstoselect_css")));
		  List<WebElement> availableItems = driver.findElements(By.cssSelector(or.getProperty("itemstoselect_css")));
		  if (availableItems.size() > 0) {
			 int selectItem = r.nextInt(availableItems.size());
			 ((JavascriptExecutor)driver).executeScript("arguments[0].click();", availableItems.get(selectItem));
		  } else {
			  h.waitForElement(30,By.id(or.getProperty("availableproducts_id")));
			  List<WebElement> productscontainer = driver.findElements(By.id(or.getProperty("availableproducts_id")));//findElements(By.cssSelector(or.getProperty("itemstoselect_css")));
			  if (productscontainer.isEmpty()) {
				 Reporter.log("No more items are available. Sorry for the inconvenience");
			  } else if (availableItems.isEmpty()) {
			  		Reporter.log("Unable to select items. May be Out of stock or page loading issue");
			  		}
		}
		  
	  }
	  
	  /*
	   * ---- Checking the items added to basket
	   */
	  
	  log.debug("Checking for the items added to basket");
	  h.sleep(30);
	  driver.findElement(By.cssSelector(or.getProperty("itemsinbasket_css"))).click();
	  h.waitForElement(30,By.className(or.getProperty("itemscount")));
	  List<WebElement> itemscount = driver.findElements(By.className(or.getProperty("itemscount")));
	  List<WebElement> itemsQuantity = driver.findElements(By.cssSelector(or.getProperty("itemsquantity")));
	  if (itemscount.isEmpty() || itemsQuantity.isEmpty()) {
		  Assert.fail("Information box about the items in basket is missing");
	  }

	  log.debug("Adding items to basket!");
	  if (itemsQuantity.size() !=0) {
		  Reporter.log(" You've selected 3 items, and showing"+" "+itemsQuantity.size()+" in Basket!!");
	  }
	  driver.close();
  	}
}
