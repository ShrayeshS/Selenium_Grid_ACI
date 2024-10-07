package stepDef;

import java.util.concurrent.TimeoutException;

import io.cucumber.java.en.When;
import pageobjects.BasePage;
import pageobjects.LandingPage_PageObject;
import utils.Constants;
import utils.ReadProperties;

public class LandingPage_StepDef extends BasePage {
	 
	LandingPage_PageObject landingPage = new LandingPage_PageObject();
	@When("^user opens the application and clears all existing values$")
	public void clearValues() throws InterruptedException, TimeoutException {
		String applicationURL = ReadProperties.readPropertyByName(Constants.C_TESTDATA).getProperty("browserURL");
		driver.get(applicationURL);		
		landingPage.clearValues();
	}
	@When("^enters \"([^\"]*)\" and \"([^\"]*)\"$")
	public void enterValues(String value1, String value2) throws InterruptedException, TimeoutException {
		landingPage.enterValues(value1, value2);
	}
	@When("^user performs \"([^\"]*)\" and validates \"([^\"]*)\"$")
	public void mathematic_Operations(String operation, String output) throws InterruptedException, TimeoutException {
		landingPage.mathematicOperations(operation);
		landingPage.validateOutput(output);
	}
		
}