package utils;

import java.net.URL;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import io.cucumber.java.Scenario;

public class DriverFactory {

	private static WebDriver driver;

	private static String remoteExecutionFlag = ReadProperties.readPropertyByName("Config.properties")
			.getProperty("remoteExecutionFlag");
	private static Scenario scenario = stepDef.SetupAndTearDown.scenario;

	private static String sauceLabURL = ReadProperties.readPropertyByName("Config.properties").getProperty("remoteUrl");
	private static String hubUrl = ReadProperties.readPropertyByName("Config.properties").getProperty("hubUrl");
	private static final String DOWNLOADPATH = System.getProperty("user.dir")
			+ ReadProperties.readPropertyByName("Config.properties").getProperty("downloadPath");
	private static Logger log = LogManager.getLogger(DriverFactory.class);

	public static WebDriver createDriver() throws Exception {
		String browser = null;
		String browserDriverPath = ReadProperties.readPropertyByName("Config.properties")
				.getProperty("browserDriverPath").toLowerCase();
		String remoteDriverPath = ReadProperties.readPropertyByName("Config.properties").getProperty("remoteDriverPath")
				.toLowerCase();

		if (remoteExecutionFlag.equalsIgnoreCase("yes")) {
			try {
				browser = System.getProperty("browser").toUpperCase();
			} catch (Exception e) {
			}
		}
		;

		if (browser == null) {
			browser = ReadProperties.readPropertyByName("Config.properties").getProperty("browser").toUpperCase();
		}

		switch (browser) {

		case "CHROME":
			ChromeOptions options = new ChromeOptions();
			options.addArguments("start-maximized");
			options.addArguments("disable-infobars");
			options.addArguments("--disable-extensions");
			options.addArguments("--disable-gpu");
			options.addArguments("--disable-dev-shm-usage");
			// Commented this line to remove orphaned chrome driver instances
			// options.addArguments("--no-sandbox");
			options.setExperimentalOption("excludeSwitches", Collections.singletonList("enable-automation"));
			options.setExperimentalOption("useAutomationExtension", false);
			options.addArguments("--remote-allow-origins=*");
			options.setPageLoadStrategy(PageLoadStrategy.EAGER);

			// To remove save password notification
			Map<String, Object> prefs = new HashMap<>();
			prefs.put("credentials_enable_service", false);
			prefs.put("profile.password_manager_enabled", false);
			prefs.put("download.default_directory", DOWNLOADPATH);
			prefs.put("download.prompt_for_download", false);
			prefs.put("download.directory_upgrade", true);
			prefs.put("safebrowsing.enabled", true);
			options.setExperimentalOption("prefs", prefs);

			if (remoteExecutionFlag.equalsIgnoreCase("yes")) {
				// Chaya:Added for Jenkins Migration (Below 2 statements)
				remoteDriverPath = remoteDriverPath + "chromedriver.exe";
				System.setProperty("webdriver.chrome.driver", remoteDriverPath);
				options.addArguments("--window-size=1600,900");
				options.addArguments("--headless");
				options.setCapability(ChromeOptions.CAPABILITY, options);
				options.merge(options);
				driver = new RemoteWebDriver(new URL(hubUrl), options);
				System.out.print("driver.manage().window().getSize::" + driver.manage().window().getSize());
				driver.manage().window().maximize();
				return driver;
			} else {
				options.setCapability(ChromeOptions.CAPABILITY, options);
				options.merge(options);
				driver = new ChromeDriver();
				driver.get("chrome://settings/clearBrowserData");
				Dimension d = new Dimension(1600, 900);
				driver.manage().window().setSize(d);
				driver.manage().window().maximize();
			}
			break;

		case "FIREFOX":

			FirefoxOptions firefoxOptions = new FirefoxOptions();
			firefoxOptions.addArguments("--window-size=1600,900");
			firefoxOptions.addArguments("--start-maximized");

			if (remoteExecutionFlag.equalsIgnoreCase("yes")) {
				remoteDriverPath = remoteDriverPath + "geckodriver.exe";
				System.setProperty("webdriver.gecko.driver", remoteDriverPath);
				firefoxOptions.addArguments("-headless");

				driver = new RemoteWebDriver(new URL(hubUrl), firefoxOptions);
				log.info("\ndriver.manage().window().getSize::" + driver.manage().window().getSize());
			} else {
				browserDriverPath = browserDriverPath + "geckodriver.exe";
				log.info("\n browserDriverPath = " + browserDriverPath);
				System.setProperty("webdriver.gecko.driver", browserDriverPath);
				driver = new FirefoxDriver(firefoxOptions);
				log.info("\ndriver.manage().window().getSize::" + driver.manage().window().getSize());
			}
			break;

		case "EDGE":
			MutableCapabilities sauceOptions = new MutableCapabilities();
			EdgeOptions edgeOptions = new EdgeOptions();

			sauceOptions.setCapability("browserName", "edge");
			sauceOptions.setCapability("screenResolution", "1680x1050");
			sauceOptions.setCapability("tunnelName", "csp_tunnel");
			sauceOptions.setCapability("name", "CSP-" + scenario.getName());
			sauceOptions.setCapability("build", "CSP");
			edgeOptions.setCapability("platformName", "Windows 10");
			edgeOptions.setCapability("browserVersion", "latest");
			edgeOptions.setCapability("sauce:options", sauceOptions);
			if (remoteExecutionFlag.equalsIgnoreCase("yes")) {
				driver = new RemoteWebDriver(new URL(sauceLabURL), edgeOptions);
				driver.manage().window().maximize();
				log.info("\ndriver.manage().window().getSize::" + driver.manage().window().getSize());
			} else {
				driver = new EdgeDriver();
				Dimension dimension = new Dimension(1600, 900);
				driver.manage().window().setSize(dimension);
				driver.manage().window().maximize();
				log.info("\ndriver.manage().window().getSize::" + driver.manage().window().getSize());
			}
			break;
		}
		return driver;
	}
}