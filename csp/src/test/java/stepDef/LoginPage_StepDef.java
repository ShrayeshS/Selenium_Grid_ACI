package stepDef;

import java.util.concurrent.TimeoutException;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import pageobjects.LoginLogOut_PageObject;

public class LoginPage_StepDef {
	LoginLogOut_PageObject loginLogOut = new LoginLogOut_PageObject();



	@Then("^User closes the browser$")
	public void closeBrowserInstance() {
		loginLogOut.closeBrowserinstance();
	}

	
	@Given("^The Browser and Application is Open$")
	public void OpenBrowser()
	{
		loginLogOut.openApplication();
	}
	
	
}
