package pageobjects;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

import utils.CommonFunctions;
import utils.Constants;
import utils.EncryptDecrypt_PW;
import utils.ReadProperties;

public class LoginLogOut_PageObject extends BasePage {

	By usernameField = By.id("logonuidfield");
	By passwordField = By.id("logonpassfield");
	By submitButton = By.xpath("//*[contains(@type, 'submit')] ");
	By signOut = By.xpath("//*[contains(text(),'Sign Out')]");
	By dashboard = By.xpath("//img[@alt='The Dashboard']");
	By errMsg = By.xpath("//nav[@id='navbarErrMsg']");
	By MFAtitle = By.xpath("//div[contains(text(),'Register for multi-factor authentication')]");
	By RemindMeLater = By.xpath("//span[contains(text(),'Remind me later')]");
	By RemindMeLaterPopUp = By.xpath("(//span[contains(text(),'Remind me later')])[2]");

	By backofficeUsername = By.xpath("//input[@name='j_username']");
	By backofficePassword = By.xpath("//input[@name='j_password']");
	By backofficeLoginBtn = By.xpath("//button[contains(@class,'login_btn')]");
	
	By commandCenterUsername = By.xpath("//input[@placeholder='Username']");
	By commandCenterPassword = By.xpath("//input[@placeholder='Password']");
	By commandCenterLoginBtn =By.id("submitButton");
	By cancelBtn = By.xpath("//div[text()='Cancel']");
	By CCProfile = By.xpath("//div[text()='PROFILE']");
	
	static final String CHROME = "chrome";
	static final String DOWNLOADPATH = System.getProperty("user.dir")
			+ ReadProperties.readPropertyByName("Config.properties").getProperty("downloadPath");
	static final String BROWSER = "browser";

	List<String> windowHandles = new ArrayList<>();
	static String sessionID;
	WebDriver tmpDriver;
	
	//SmartSource Login
	private static String userIDVal = null;
	private static String passwordVal = null;
	By loggedUser = By.xpath("//div[contains(@class,'universal-nav-link-right')]");
	By username = By.id("logonuidfield");
	By password = By.xpath("//*[@id='logonpassfield']");
	By loginSubmit = By.xpath("//button[@name='uidPasswordLogon']");
	By ssLogo = By.xpath("(//img[@alt='SmartSource logo'])[1]");
	

	public void applicationLogin(String username, String pwd) {

		if (CommonFunctions.isElementPresent(usernameField)) {
			login(username, pwd);
		} else if (CommonFunctions.isElementPresent(loggedUser)) {

			String user = CommonFunctions.getText(loggedUser);
			String userid = ReadProperties.readPropertyByName(Constants.C_TESTDATA).getProperty(username);
			if (!user.contains(userid)) {
				CommonFunctions.click(signOut);
				CommonFunctions.waitForFullPageLoad();
				login(username, pwd);
			}
		}
	}

	
	public void login(String username, String pwd)
	{
		//String applicationURL = ReadProperties.readPropertyByName(Constants.C_TESTDATA).getProperty("browserURL");
		String userid = ReadProperties.readPropertyByName(Constants.C_TESTDATA).getProperty(username);
		String password = ReadProperties.readPropertyByName(Constants.C_TESTDATA).getProperty(pwd);

		scenario.log(
				"---------------------------------------Start of Scenario--------------------------------------------------------------");
		scenario.log(scenario.getName());
		scenario.log(
				"-----------------------------------------------------------------------------------------------------------------------");
		logger.trace(
				"---------------------------------------Start of Scenario--------------------------------------------------------------");
		logger.trace(scenario.getName());
		logger.trace(
				"-----------------------------------------------------------------------------------------------------------------------");

		password = EncryptDecrypt_PW.decrypt(password);

		//driver.get(applicationURL);

		String browser = System.getProperty(BROWSER);

		if (browser == null || browser.isEmpty()) {
			browser = ReadProperties.readPropertyByName("Config.properties").getProperty(BROWSER);
		}

		switch (browser) {
		case "ie":
			if (!driver.findElements(dashboard).isEmpty()) {
				logger.info("Element is Present");
				driver.findElement(dashboard).click();
			} else {
				logger.info("The Dashboard is Absent");
//				CommonFunctions.waitForPageLoad();
				CommonFunctions.enterText(usernameField, userid);
				CommonFunctions.enterText(passwordField, password);
				CommonFunctions.click(submitButton);
			}
			break;
		default:

			CommonFunctions.enterText(usernameField, userid);

			CommonFunctions.enterText(passwordField, password);
			
			CommonFunctions.click(submitButton);
			break;
		}

		CommonFunctions.validate_is_exists(errMsg, Constants.C_NO);
		logger.info("Login Successful");

		sessionID = driver.getWindowHandle();
		
		if(CommonFunctions.isElementPresent(MFAtitle))
		{
			CommonFunctions.scroll_to(RemindMeLater);
			CommonFunctions.click(RemindMeLater);
			CommonFunctions.click(RemindMeLaterPopUp);
		}
	}
	
	public void applicationLogoff() {

		CommonFunctions.click(signOut);
		//CommonFunctions.waitForPageLoad();
		scenario.log(
				"---------------------------------------End of Scenario--------------------------------------------------------------");
		scenario.log(scenario.getName());
		scenario.log(
				"-----------------------------------------------------------------------------------------------------------------------");
		logger.trace(
				"---------------------------------------End of Scenario--------------------------------------------------------------");
		logger.trace(scenario.getName());
		logger.trace(
				"-----------------------------------------------------------------------------------------------------------------------");
		CommonFunctions.deleteOldLogs();
	}

	public void reLoginToApplication(String username, String password) {
		String userid = ReadProperties.readPropertyByName(Constants.C_TESTDATA).getProperty(username);
		String passwd = ReadProperties.readPropertyByName(Constants.C_TESTDATA).getProperty(password);
		String pasword = EncryptDecrypt_PW.decrypt(passwd);
		CommonFunctions.enterText(usernameField, userid);
		CommonFunctions.enterText(passwordField, pasword);
		CommonFunctions.click(submitButton);
		if(CommonFunctions.isElementPresent(MFAtitle))
		{
			CommonFunctions.click(RemindMeLater);
			CommonFunctions.click(RemindMeLaterPopUp);
		}
	}

	public void loginIntoBackoffice(String userid, String userpassword) {

		String userid1 = ReadProperties.readPropertyByName(Constants.C_TESTDATA).getProperty(userid);
		String password1 = ReadProperties.readPropertyByName(Constants.C_TESTDATA).getProperty(userpassword);

		scenario.log("The hmc username is: " + userid1);
		scenario.log("The hmc Encrypted password is: " + password1);
		password1 = EncryptDecrypt_PW.decrypt(password1);

		scenario.log("The hmc Decrypted password is: " + password1);

		CommonFunctions.enterText(backofficeUsername, userid1);

		CommonFunctions.enterText(backofficePassword, password1);

		CommonFunctions.applyImplicitWait(30);
		CommonFunctions.click(backofficeLoginBtn);

	}

	/**
	 * Method to Login to application in incognito mode in a separate window This
	 * method will copy existing driver into a 'tmpdriver' Object and assign the
	 * instance of chrome driver having options.addArguments("--incognito"); to
	 * existing driver.
	 * 
	 * @param username
	 * @param pwd
	 * 
	 * @throws InterruptedException
	 */
	public void loginIncognitoMode(String username, String pwd) {
		ChromeOptions options = new ChromeOptions();
		DesiredCapabilities capability;
		String browserDriverPath;

		String browser = System.getProperty(BROWSER);

		if (browser != null && !browser.isEmpty()) {

			browserDriverPath = ReadProperties.readPropertyByName("Config.properties").getProperty("remoteDriverPath")
					.toLowerCase();
			System.out.println("Running on browser - " + browser);

		} else {

			browser = ReadProperties.readPropertyByName("Config.properties").getProperty(BROWSER);
			browserDriverPath = System.getProperty("user.dir") + ReadProperties.readPropertyByName("Config.properties")
					.getProperty("browserDriverPath").toLowerCase();
			System.out.println("browser environment parameter is empty. Running on browser - " + browser);

			//capability = DesiredCapabilities.chrome();
			//capability.setBrowserName(CHROME);

			options.addArguments("start-maximized");
			options.addArguments("disable-infobars");
			options.addArguments("--disable-extensions");
			options.addArguments("--disable-gpu");
			options.addArguments("--disable-dev-shm-usage");
			//options.addArguments("--no-sandbox");
			options.addArguments("--incognito");
			options.setExperimentalOption("excludeSwitches", Collections.singletonList("enable-automation"));
			options.setExperimentalOption("useAutomationExtension", false);

			Map<String, Object> prefs = new HashMap<>();
			prefs.put("credentials_enable_service", false);
			prefs.put("profile.password_manager_enabled", false);
			prefs.put("download.default_directory", DOWNLOADPATH);
			prefs.put("download.prompt_for_download", false);
			prefs.put("download.directory_upgrade", true);
			prefs.put("safebrowsing.enabled", true);
			options.setExperimentalOption("prefs", prefs);

			// options.addArguments("--window-size=1366,768");
			// options.addArguments("--headless");

			//capability.setCapability(ChromeOptions.CAPABILITY, options);
			browserDriverPath = browserDriverPath + "chromedriver.exe";

			String applicationURL = ReadProperties.readPropertyByName(Constants.C_TESTDATA).getProperty("browserURL");
			String userid = ReadProperties.readPropertyByName(Constants.C_TESTDATA).getProperty(username);
			String password = ReadProperties.readPropertyByName(Constants.C_TESTDATA).getProperty(pwd);

			password = EncryptDecrypt_PW.decrypt(password);

			tmpDriver = driver;

			driver = new ChromeDriver(options);

			driver.get(applicationURL);

			// switchHandles();

			CommonFunctions.waitForPageLoad();
			CommonFunctions.enterText(usernameField, userid);

			CommonFunctions.enterText(passwordField, password);
			CommonFunctions.click(submitButton);
		}
		// Thread.sleep(2000);
		// System.out.println(driver.getWindowHandle());
		// windowHandles.add(driver.getWindowHandle());
		// driver.close();
	}

	/**
	 * Method to close instance of current browser in focus tmpdriver created in
	 * loginIncognitoMode() copied back to original driver to continue execution in
	 * original window
	 */
	public void closeBrowserinstance() {
		driver.close();
		driver = tmpDriver;
		switchHandles();
	}

	public void switchHandles() {
		driver.switchTo().window(sessionID);
	}
	
	public void smartSourceLogin(String tdUserID, String tdPassword) throws InterruptedException {
		String ApplicationURL = ReadProperties.readPropertyByName(Constants.C_TESTDATA).getProperty("SmartSourceUrl");
		if(passwordVal == null) {
			userIDVal = ReadProperties.readPropertyByName(Constants.C_TESTDATA).getProperty(tdUserID);
			String passwordEnc = ReadProperties.readPropertyByName(Constants.C_TESTDATA).getProperty(tdPassword);

			//scenario.write("The username is: " + userid1);
			logger.info("The username is: " + userIDVal);
			//scenario.write("The Encrypted password is: " + password1);
			passwordVal = EncryptDecrypt_PW.decrypt(passwordEnc);
		}
		
		logger.debug(" The SmartSource URL is :: "+ ReadProperties.readPropertyByName(Constants.C_TESTDATA).getProperty("SmartSourceUrl"));
		driver.get(ApplicationURL);
		
		String browser = System.getProperty(BROWSER);

		if (browser == null || browser.isEmpty()) {
			browser = ReadProperties.readPropertyByName("Config.properties").getProperty(BROWSER);
		}
		
		//browser = ReadProperties.readPropertyByName(Constants.C_TESTDATA).getProperty("browser");
		switch (browser) {
		case "chrome":
			//CommonFunctions.waitForLoad(driver);
			//wait.until(ExpectedConditions.visibilityOfElementLocated(username));
			//CommonFunctions.waitFor_Element_ToBe_Clickable(username);
			CommonFunctions.enterText(username, userIDVal);
			logger.info("....Entered username ");
			//CommonFunctions.waitFor_Element_ToBe_Clickable(password);
			CommonFunctions.enterText(password, passwordVal);
			logger.info("....Entered password ");
			// <button type="submit" name="uidPasswordLogon" class="primary
			// align-right">Sign In</button>
			// button[@name='uidPasswordLogon']
			//driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			//CommonFunctions.click(loginSubmit); 
			//CommonFunctions.altClick(loginSubmit);
			driver.findElement(loginSubmit).click();
			logger.info("...Login submitted");
			
			//driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			break;
		case "ie":
		case "edge":	
			if (driver.findElements(dashboard).size() != 0) {
				logger.debug("Element is Present");
				driver.findElement(dashboard).click();
			} else {
				logger.debug("The Dashboard is Absent");
				CommonFunctions.enterText(username, userIDVal);
				CommonFunctions.enterText(password, passwordVal);

				CommonFunctions.click(loginSubmit);
			}
			break;
		case "firefox":
			CommonFunctions.waitForLoad(driver);
			
			CommonFunctions.waitFor_Element_ToBe_Clickable(username);
			CommonFunctions.enterText(username, userIDVal);

			CommonFunctions.enterText(password, passwordVal);
			driver.findElement(loginSubmit).click();
		}
		CommonFunctions.waitForPageLoad();
		CommonFunctions.waitFor_Element_ToBe_Clickable(ssLogo);
		logger.info("...Completed wait for SS logo visibility");
		Assert.assertTrue(CommonFunctions.isElementPresent(ssLogo));
		
	}
	
	public void loginIfRequested(String userid, String userpassword) throws InterruptedException {
		if (CommonFunctions.getTitlePage().contains("SmartSource") && CommonFunctions.isElementPresent(loginSubmit))
		{
			smartSourceLogin(userid, userpassword);
		}
	}
	
	public void checkLoggedIn(String username, String pwd)
	{
		if (CommonFunctions.isElementPresent(submitButton))
		{
			login(username, pwd);
		}
	}
	
	public void openApplication()
	{	
		String applicationURL = ReadProperties.readPropertyByName(Constants.C_TESTDATA).getProperty("browserURL");
//		if(!driver.getCurrentUrl().equalsIgnoreCase(applicationURL))
//		{
		driver.get(applicationURL);
		
//		sessionID = driver.getWindowHandle();
//		
//		}
	}
	
	public void openCCApplication(String username, String pwd)
	{	
		String applicationURL = ReadProperties.readPropertyByName(Constants.C_TESTDATA).getProperty("commandCenterURL");
		String userid = ReadProperties.readPropertyByName(Constants.C_TESTDATA).getProperty(username);
		String password = ReadProperties.readPropertyByName(Constants.C_TESTDATA).getProperty(pwd);
		password = EncryptDecrypt_PW.decrypt(password);
		if(!driver.getCurrentUrl().equalsIgnoreCase(applicationURL))
		{
			String url[]=applicationURL.split("@");
			url[0]=url[0].concat(userid+":"+password+"@");
			String ApplicationURL = url[0].concat(url[1]);
			
		driver.get(ApplicationURL);
		
		sessionID = driver.getWindowHandle();
		
		}
	}
	
  
    public void loginCC(String username, String pwd)
	{
		String userid = ReadProperties.readPropertyByName(Constants.C_TESTDATA).getProperty(username);
		String password = ReadProperties.readPropertyByName(Constants.C_TESTDATA).getProperty(pwd);

		scenario.log(
				"---------------------------------------Start of Scenario--------------------------------------------------------------");
		scenario.log(scenario.getName());
		scenario.log(
				"-----------------------------------------------------------------------------------------------------------------------");
		logger.trace(
				"---------------------------------------Start of Scenario--------------------------------------------------------------");
		logger.trace(scenario.getName());
		logger.trace(
				"-----------------------------------------------------------------------------------------------------------------------");

		password = EncryptDecrypt_PW.decrypt(password);

		//driver.get(applicationURL);

		String browser = System.getProperty(BROWSER);

		if (browser == null || browser.isEmpty()) {
			browser = ReadProperties.readPropertyByName("Config.properties").getProperty(BROWSER);
		}

		switch (browser) {
		case "ie":
			if (!driver.findElements(dashboard).isEmpty()) {
				logger.info("Element is Present");
				driver.findElement(dashboard).click();
			} else {
				logger.info("The Dashboard is Absent");
//				CommonFunctions.waitForPageLoad();
				CommonFunctions.enterText(commandCenterUsername, userid);
				CommonFunctions.enterText(commandCenterPassword, password);
				CommonFunctions.click(commandCenterPassword);
			}
			break;
		default:

			CommonFunctions.enterText(commandCenterUsername, userid);

			CommonFunctions.enterText(commandCenterPassword, password);
			
			CommonFunctions.click(commandCenterLoginBtn);
			break;
		}

		CommonFunctions.validate_is_exists(errMsg, Constants.C_NO);
		logger.info("Login Successful");

		sessionID = driver.getWindowHandle();
		
	}
    
    public void applicationLogoffCC() throws InterruptedException {

    	CommonFunctions.click(CCProfile);
		CommonFunctions.click(signOut);
		//CommonFunctions.waitForPageLoad();
		scenario.log(
				"---------------------------------------End of Scenario--------------------------------------------------------------");
		scenario.log(scenario.getName());
		scenario.log(
				"-----------------------------------------------------------------------------------------------------------------------");
		logger.trace(
				"---------------------------------------End of Scenario--------------------------------------------------------------");
		logger.trace(scenario.getName());
		logger.trace(
				"-----------------------------------------------------------------------------------------------------------------------");
		CommonFunctions.deleteOldLogs();
		CommonFunctions.waitForParticularTime("60000");
	}
    
    public void clickCancelOnDND()
    {
    	if(CommonFunctions.isElementPresent(cancelBtn))
		{
			CommonFunctions.click(cancelBtn);
		}
    }
    public void openApplicationWithURL(String page)
	{	
    	String applicationURL = null;
    	if(page.contains("Catalog Search"))
    	{
	      applicationURL = ReadProperties.readPropertyByName(Constants.C_TESTDATA).getProperty("td_catalogBrowserURL");
    	}
    	else if(page.contains("PDP"))
    	{
  	      applicationURL = ReadProperties.readPropertyByName(Constants.C_TESTDATA).getProperty("td_PDPBrowserURL");
      	}
		if(!driver.getCurrentUrl().equalsIgnoreCase(applicationURL))
		{
		driver.get(applicationURL);
		
		sessionID = driver.getWindowHandle();
		
		}
	}
}