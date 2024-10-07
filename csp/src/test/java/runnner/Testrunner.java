package runnner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import helper.ReportPortalUtil;

//import com.amerisourcebergen.smartsource.helper.ExtendedCucumberRunner;
//import com.github.mkolisnyk.cucumber.runner.AfterSuite;
//import com.github.mkolisnyk.cucumber.runner.ExtendedCucumber;
//import com.github.mkolisnyk.cucumber.runner.ExtendedCucumberOptions;
//import com.vimalselvam.cucumber.listener.Reporter;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

import org.junit.AfterClass;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;

import stepDef.SetupAndTearDown;
//Added for Jenkins Migration
//@RunWith(ExtendedCucumber.class)
//@RunWith(ExtendedCucumberRunner.class)
@RunWith(Cucumber.class)
/*
@ExtendedCucumberOptions(jsonReport = "target/cucumber.json",
customTemplatesPath = "templates.json", // Point to local folder with custom templates
outputFolder = "target/NewReports",
reportPrefix = "SmartSource",
detailedReport = true,
detailedAggregatedReport = true,
overviewReport = true,
featureOverviewChart = true,
knownErrorsReport = false,
knownErrorsConfig = "src/reports.config",
usageReport = false,
coverageReport = true,
overviewChartsReport = false,
retryCount = 0,
breakdownReport = false,
breakdownConfig = "src/reports.config",
screenShotLocation = "./screenshots/", //"screenshots/",
screenShotSize = "300px",
toPDF = true,
pdfPageSize = "A3",
consolidatedReport = true,
consolidatedReportConfig = "src/test/resources/config/reports_config.json")
*/

@CucumberOptions(	features = "src/features", 
					glue = { "stepDef" }, 
					tags = "@Calculator",
					plugin = {"com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:",   
							"pretty",
							   "com.epam.reportportal.cucumber.ScenarioReporter",
							   "json:target/cucumber.json",
							   "junit:target/cucumber.xml",
							   
							   },
					dryRun = false,
					monochrome = true
)

public class Testrunner {

	private static Logger log = LogManager.getLogger(Testrunner.class);	
	
	//Added for Jenkins Migration
	@AfterClass
	public static void testRunFinished() {
		
		WebDriver driver = SetupAndTearDown.getDriver();
		log.info("Going to Quit all instances of the Browser : " + driver);
		if (driver != null) {
			driver.close();
			driver.quit();
			driver = null;
			log.info("Driver : Quitting all instances of the  Browser");
		}
		log.info("Application Terminating ... ");
		//if (isNotCalledByBase(Thread.currentThread().getStackTrace())) {
		     new ReportPortalUtil().postReportPortal();
		//}
	}
	
}


/*package runnner;

import helper.ExtendedCucumberRunner;
import org.junit.runner.RunWith;
import cucumber.api.CucumberOptions;
import helper.ReportPortalUtil;
import com.github.mkolisnyk.cucumber.runner.AfterSuite;
import com.github.mkolisnyk.cucumber.runner.ExtendedCucumberOptions;

@RunWith(ExtendedCucumberRunner.class)
@ExtendedCucumberOptions(jsonReport = "target/cucumber.json",
customTemplatesPath = "src/test/resources/config/templates.json",
outputFolder = "target/NewReports",
reportPrefix = "CSP",
detailedReport = true,
detailedAggregatedReport = true,
overviewReport = true,
featureOverviewChart = true,
knownErrorsReport = false,
knownErrorsConfig = "src/test/resources/config/reports_config.json",
usageReport = false,
coverageReport = true,
overviewChartsReport = true,
retryCount = 0,
breakdownReport = false,
breakdownConfig = "src/test/resources/config/reports_config.json",
screenShotLocation = "./screenshots/",
screenShotSize = "300px",
toPDF = true,
pdfPageSize = "A3",
consolidatedReport = true,
consolidatedReportConfig = "src/test/resources/config/reports_config.json")

@CucumberOptions(	features = "src/features", 
					glue = { "stepDef" }, 
					tags = { "@BVT"},
					plugin = { "com.vimalselvam.cucumber.listener.ExtentCucumberFormatter:target/html/ExtentReport.html",
							"pretty", "html:target/site/cucumber-reports",
							"com.epam.reportportal.cucumber.ScenarioReporter",
							"json:target/cucumber.json",
							"junit:target/cucumber.xml"},
					dryRun = false,
					strict = false,
					monochrome = true
)
public class Testrunner {

	
	@AfterSuite
	public static void testRunFinished() {
		System.out.println("Application Terminating ...");
		if(ExtendedCucumberRunner.isNotCalledByBase(Thread.currentThread().getStackTrace()))
			new ReportPortalUtil().postReportPortal();
	}

}*/
