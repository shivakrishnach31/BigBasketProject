package testUtils;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.Assert;

import Tests.TestBase;

public class Helper extends TestBase {
	/* ---- Choosing Browser
	 * 
	 */
	public void chooseBrowser(){
		if (config.getProperty("browserType").equalsIgnoreCase("chrome")) {
			System.getProperty("webdriver.chrome.driver", System.getProperty("user.dir" + "\\src\\exe files\\chromedriver.exe"));
			driver = new ChromeDriver();
		}
		else if (config.getProperty("browserType").equalsIgnoreCase("ie")) {
			System.getProperty("webdriver.ie.driver", System.getProperty("user.dir"+ "\\src\\exe files\\IEDriverServer.exe"));
			driver = new InternetExplorerDriver();
		}
		else {
			driver = new FirefoxDriver();
		}
	}
	
	/* ---- Taking Screenshots
	 * 
	 */
	public void takeScreenShots(int filename) throws IOException{
		  File scrfile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		  FileUtils.copyFile(scrfile, new File(System.getProperty("user.dir")+"\\src\\Reports\\"+filename+".jpg"));
	}
	
	/* ------ Selecting city
	 * 
	 */
	public void citySelection(){
		try{
		List<WebElement> citiesList = driver.findElement(By.id(or.getProperty("citypopup_id"))).findElements(By.tagName(or.getProperty("citybutton_tag")));
	 	if (citiesList.size() > 0) {
	 		int city = r.nextInt(citiesList.size());
	 		citiesList.get(city).click();
		  
		} else {
			 Assert.fail("No such element found");
			 System.out.println("Selected some city by default");
		}
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	/* ---- Sleep Method
	 * 
	 */
	public void sleep(int seconds){
		try{
			Thread.sleep(seconds*1000);
		}catch(Exception e){
			System.out.println(e);
		}
	}
	/*
	 * ---Wait for element
	 */
	public boolean waitForElement(int sec, By by){
		while (sec > 0) {
			sleep(1);
			List<WebElement> list = driver.findElements(by);
			if (list.size() != 0) {
				return true;
			}
			sec--;
		}
		System.out.println("waiting timed out, Element is not found");
		return false;
	}
}
