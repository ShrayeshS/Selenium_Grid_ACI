package pageobjects;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;

import io.cucumber.java.Scenario;
import stepDef.SetupAndTearDown;
import utils.Constants;

public class BasePage extends Constants {

	public static WebDriver driver;
	public static Scenario scenario;
	public static Logger logger ;
	
	public BasePage() {
		driver = SetupAndTearDown.driver;
		scenario=SetupAndTearDown.scenario;
		logger=LogManager.getLogger(BasePage.class);
	}
	
	
}
