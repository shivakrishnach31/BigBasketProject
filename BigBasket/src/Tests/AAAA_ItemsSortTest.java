package Tests;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import testUtils.Helper;

public class AAAA_ItemsSortTest extends Helper {
	ArrayList<Double> arr = new ArrayList<Double>();
	ArrayList<Double> arr1 = new ArrayList<Double>();
  
	@Test
	public void checkItemSort() throws Exception{

		// choosing browser
		h.chooseBrowser(); 
		driver.get(config.getProperty("testUrl"));
		h.citySelection();

	  	
	  	//-------------- Moving to View Complete Products list
		log.debug("opening complete list ot items to check the sorting order based its popularity,low to high and high to low prices");
	  	h.waitForElement(30,By.linkText(or.getProperty("completershop_linktext")));
	  	if (driver.findElements(By.linkText(or.getProperty("completershop_linktext"))).isEmpty()) {
			Assert.fail("Complete shot button is missing in page or Page not loaded completely");
		}
	  	
	  	((JavascriptExecutor)driver).executeScript("arguments[0].click();", driver.findElement(By.linkText(or.getProperty("completershop_linktext"))));
	  	h.sleep(15);
	  	
	  	
	  	// ---------- Storing Main product categories in list and click one randomly 
	  	
		List<WebElement> itemCategories = driver.findElement(By.className(or.getProperty("MajorCategories"))).findElements(By.tagName(or.getProperty("categoryoptions_tag")));
		int itemCategory = r.nextInt(itemCategories.size());
		if (itemCategories.isEmpty()) {
			Assert.fail("There are no Categories to display or page not loaded completely");
		}
		h.sleep(30);
		((JavascriptExecutor)driver).executeScript("arguments[0].click();", itemCategories.get(itemCategory));
		if (driver.findElements(By.id(or.getProperty("availableproducts_id"))).isEmpty()) {
			Assert.fail("No items in this category, Try next time");
		}
		h.sleep(15);
		List<WebElement> sorts = driver.findElements(By.className(or.getProperty("sub_container_class")));
		if (sorts.isEmpty()) {
			Assert.fail("No items in this category");
		}
		
		for (int i = 0; i < sorts.size(); i++) {
			driver.findElement(By.className(or.getProperty("sortdropdowncontainer"))).click();
			List<WebElement> sorts1 = driver.findElement(By.cssSelector(or.getProperty("sortddown_css"))).findElements(By.tagName(or.getProperty("sortoptions")));
			h.sleep(10);
			sorts1.get(i).click();
			h.sleep(10);
			List<WebElement> rates = driver.findElements(By.className("uiv2-rate-count-avial"));
			for(int j = 0; j < rates.size(); j++) {
				if (rates.get(j).getText().length() > 0 && driver.findElement(By.cssSelector("span.dk_label")).getText().equalsIgnoreCase("Price - Low to High")) {
					Double lowTohighRate = Double.parseDouble(rates.get(j).getText().substring(4));
					arr.add(lowTohighRate);
				} else if (rates.get(j).getText().length() > 0 && driver.findElement(By.cssSelector("span.dk_label")).getText().equalsIgnoreCase("Price - High to Low")) {
					Double highTolowRate = Double.parseDouble(rates.get(j).getText().substring(4));
					arr1.add(highTolowRate);
					}
			}
			h.takeScreenShots(i);
		}
		
		// Checking sorting order from Low to high
		log.debug("Checking soring order from Low to High");
		System.out.println(arr);
		for (int j = 0; j < arr.size()-1; j++) {
			if (arr.get(j)> arr.get(j+1)) {
				Reporter.log("Low to high not sorted properly");
				break;
			}
		}
		Reporter.log("Low to high sorted properly");
		
		// Checking sorting order from High to Low
		log.debug("Checking sorting order from High to Low");
		System.out.println(arr1);
		for (int j = 0; j < arr1.size()-1; j++) {
			if (arr1.get(j) < arr1.get(j+1)) {
				Reporter.log("High to Low not sorted properly");
				break;
			}
		}
		Reporter.log("High to low sorted properly");
		driver.close();
	}
}
