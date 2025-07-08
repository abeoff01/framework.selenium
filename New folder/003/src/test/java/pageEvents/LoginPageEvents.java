package pageEvents;

import org.testng.Assert;

import base.BaseTest;
import pageObjects.LoginPageElements;
import utils.ElementFetch;

public class LoginPageEvents extends BaseTest {
	ElementFetch elem = new ElementFetch();

	public void verifyLoginPage() {

		Assert.assertTrue(elem.getListWebElement("XPATH", LoginPageElements.submitButton).size() > 0,
				"element not found");
	}

	public void enterCredentials() {

		elem.getWebElement("XPATH", LoginPageElements.emailText).sendKeys("hello man");
		elem.getWebElement("XPATH", LoginPageElements.passwordText).sendKeys("fuckoff");
	}

}
