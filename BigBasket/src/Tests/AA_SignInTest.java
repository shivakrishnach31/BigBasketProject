package Tests;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import testUtils.Helper;

public class AA_SignInTest extends Helper {
  @Test
  public void login() throws InterruptedException{
	  /*
	   * -----Signing in with Valid credentials
	   */
	  
	  h.chooseBrowser();
	  driver.get(config.getProperty("testUrl"));
	  h.citySelection();
		//	  List<WebElement> list = driver.findElement(By.id("ftv-city-popup")).findElements(By.tagName("button"));
		//	  int location = r.nextInt(list.size());
		//	  list.get(location).click();
	  h.sleep(10);
	  log.debug("User signing in with the valid credential");
	  driver.findElement(By.cssSelector(or.getProperty("login_css"))).click();
	  driver.findElement(By.id(or.getProperty("login_button_intooltip_id"))).click();
	  driver.findElement(By.id(or.getProperty("username"))).sendKeys(config.getProperty("username_value"));
	  driver.findElement(By.id(or.getProperty("password"))).sendKeys(config.getProperty("password_value"));
	  driver.findElement(By.id(or.getProperty("login_submit_button"))).click();
	  h.waitForElement(20, By.cssSelector(or.getProperty("welcomeuser_css")));
	  if (driver.findElements(By.linkText(or.getProperty("Register_linktext"))).size() > 0) {
		Assert.fail("Invalid Username or Password sumbitted");
		log.debug("Invalid Username or Password sumbitted");
	  }
	  else {
		Reporter.log("Login successfull");
		log.debug("Login successful");
	  }
	  driver.close();
  }
}
