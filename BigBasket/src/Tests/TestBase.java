package Tests;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.Random;

import jxl.Sheet;
import jxl.Workbook;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.BeforeSuite;

import testUtils.Helper;

public class TestBase {
	public static WebDriver driver;
	public static Properties or,config;
	public static Helper h;
	public static Random r;
	public static FileInputStream fp;
	public static Workbook wb;
	public static Sheet sheetid;
	public static Actions act;
	public static Logger log = Logger.getLogger("devpinoyLogger");
	public static A_RegistrationTest first = new A_RegistrationTest();
	public static AA_SignInTest second = new AA_SignInTest();
	public static AAA_AddToBasketTest third = new AAA_AddToBasketTest();
	public static AAAA_ItemsSortTest fourth = new AAAA_ItemsSortTest();
	public static AAAAA_ItemRateCheckTest fifth = new AAAAA_ItemRateCheckTest();
	
	
	

  @BeforeSuite
  public void beforeSuite() throws IOException, Exception {
	  or = new Properties();
	  config = new Properties();
	  or.load(new FileInputStream(System.getProperty("user.dir")+"\\src\\Config\\or.properties"));
	  config.load(new FileInputStream(System.getProperty("user.dir")+"\\src\\Config\\config.properties"));
	  h = new Helper();
	  r = new Random();
	 fp = new FileInputStream(System.getProperty("user.dir")+"\\src\\Testdata\\BigbasketTestdata.xls");
	 wb = Workbook.getWorkbook(fp);
	 sheetid = wb.getSheet(0);
	 
	 
  }
  
  
}
