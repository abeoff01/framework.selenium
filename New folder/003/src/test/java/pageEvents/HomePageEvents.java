package pageEvents;

import org.testng.Assert;

import base.BaseTest;
import pageObjects.HomePageElements;
import utils.ElementFetch;

public class HomePageEvents extends BaseTest{
	
	
	
	ElementFetch elem = new ElementFetch();

	public void startHereButtonClick() {
		
		if (elem.getWebElement("XPATH", HomePageElements.startHere) != null) {
			elem.getWebElement("XPATH", HomePageElements.startHere).click();
		} else {
		    Assert.fail("Element not found: loginBtn");
		}
		
	}
	
	
}
