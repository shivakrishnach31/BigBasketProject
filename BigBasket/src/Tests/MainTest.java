package Tests;

import org.testng.annotations.Test;

import testUtils.Helper;

public class MainTest extends Helper {
  @Test
  public void mainCompleteTest() throws Exception {
	  first.BigBasketTest();
	  second.login();
	  third.pickitems();
	  fourth.checkItemSort();
	  fifth.rateCheck();
  }
}
