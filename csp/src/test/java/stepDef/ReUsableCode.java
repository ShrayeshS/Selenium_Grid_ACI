package stepDef;

import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.cucumber.java.Scenario;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import java.util.concurrent.TimeoutException;
import utils.CommonFunctions;
import utils.EncryptDecrypt_PW;
import utils.ReadProperties;

public class ReUsableCode {

	public WebDriver driver;
	public Scenario scenario;
	public static String orderID;
	public static String TTText;
	public String ProdTile_count;
	public static String PONumber;
	public static String numberSpinnerValue;
	public static int receiptQty = 0;
	public static String heroIndicatorPrice;
	String[] arrOfStr;
	long dummyOrderNumber;
	int buyNowOrderCount = 0;
	
	public static final String TESTDATA = "Testdata.properties";
	By POErrorMsg=By.xpath("//span[contains(@class,'poError')]");
	By blankNumber=By.xpath("//div[contains(text(),'Blank Number')]");
	By blankNumberText=By.xpath("//input[contains(@placeholder,'Enter Blank #')]");
	By criticalAlertMsg=By.xpath("//*[contains(@class,'showDismiss')]");


	public static int getReceiptQty() {
		return receiptQty;
	}

	public static void setReceiptQty(int receiptQty) {
		ReUsableCode.receiptQty = receiptQty;
	}

	public ReUsableCode() {
		driver = SetupAndTearDown.driver;
		scenario = SetupAndTearDown.scenario;
	}

	// ######################################################################################
	// ############################ CSP Methods
	// ##########################################
	// ######################################################################################

	@Given("^the user is loging into ABC Order with \"(.*?)\" and \"(.*?)\"$")
	public void the_user_is_loging_into_ABC_Order_with_and(String userid, String password) throws Throwable {
		setReceiptQty(0);
		String ApplicationURL = ReadProperties.readPropertyByName("Testdata.properties").getProperty("browserURL");
		String loginUsername = ReadProperties.readPropertyByName("OR.properties").getProperty("username");
		String loginUserPassword = ReadProperties.readPropertyByName("OR.properties").getProperty("password");
		String LoginSubmit = ReadProperties.readPropertyByName("OR.properties").getProperty("submit");
		String userid1 = ReadProperties.readPropertyByName("Testdata.properties").getProperty(userid);
		String password1 = ReadProperties.readPropertyByName("Testdata.properties").getProperty(password);
		password1 = EncryptDecrypt_PW.decrypt(password1);

		System.out.println(
				"CSP URL:: " + ReadProperties.readPropertyByName("Testdata.properties").getProperty("browserURL"));
		driver.get(ApplicationURL);

		String browser = ReadProperties.readPropertyByName("Config.properties").getProperty("browser");
		switch (browser) {
		case "ie":
			if (driver.findElements(By.xpath("//img[@alt='The Dashboard']")).size() != 0) {
				System.out.println("Element is Present");
				driver.findElement(By.xpath("//img[@alt='The Dashboard']")).click();
			} else {
				System.out.println("The Dashboard is Absent");

				CommonFunctions.enterText(driver, "xpath", loginUsername, userid1);
				// Common_Functions.waitForParticularTime("5000");
				CommonFunctions.enterText(driver, "xpath", loginUserPassword, password1);
				CommonFunctions.waitForParticularTime("5000");
				CommonFunctions.click(driver, "xpath", LoginSubmit, "click");
			}
			break;
		default:
			CommonFunctions.enterText(driver, "xpath", loginUsername, userid1);
			CommonFunctions.waitForParticularTime("3000");
			CommonFunctions.enterText(driver, "xpath", loginUserPassword, password1);
			CommonFunctions.waitForParticularTime("5000");
			CommonFunctions.click(driver, "xpath", LoginSubmit, "click");
			break;
		}

		// WebElement myDasboard = driver.findElement(By.xpath("//img[@alt='The
		// Dashboard']"));

	}

	@When("^the user clicks the \"([^\"]*)\"$")
	public void the_user_clicks_the(String arg1) throws Throwable {
		// Write code here that turns the phrase above into concrete actions
		String Xpath = ReadProperties.readPropertyByName("OR.properties").getProperty(arg1);
		/*
		 * if(arg1.equalsIgnoreCase("quickadd_add_button")){ WebElement Element =
		 * driver.findElement(By.xpath(Xpath)); Actions action = new Actions(driver);
		 * action.doubleClick(Element); System.out.println( "User clicked on " +arg1); }
		 * else{
		 */
		/*
		 * // if ("ReceiveToteDatePicker_ApplyBtn".equalsIgnoreCase(arg1)) { //
		 * CommonFunctions.scrollToPageBottom(); // }
		 */
		CommonFunctions.waitForParticularTime("2000");
		CommonFunctions.click(driver, "xpath", Xpath, "click");

	}

	@When("^closes the \"([^\"]*)\"$")
	public void closes_the(String arg1) throws Throwable {
		CommonFunctions.closeAdditionalScreens();
	}

	@Then("^the user chooses \"([^\"]*)\" on \"([^\"]*)\"$")
	public void the_users_chooses_on(String arg1, String arg2) throws Throwable {
		// Write code here that turns the phrase above into concrete actions
		String Data = ReadProperties.readPropertyByName("Testdata.properties").getProperty(arg1);
		String Xpath2 = ReadProperties.readPropertyByName("OR.properties").getProperty(arg2);
		String Xpath = Xpath2 + "[contains(text(),'" + Data + "')]";
		System.out.println("User clicked on " + arg1);
		CommonFunctions.click(driver, "xpath", Xpath, "click");
	}

	@Given("^ReLogin with Health Systems credentials userid \"(.*?)\" and passsword \"(.*?)\"$")
	public void relogin_with_Health_Systems_credentials_userid_and_passsword(String userid, String password)
			throws Throwable {
		// Write code here that turns the phrase above into concrete actions

		String loginUsername = ReadProperties.readPropertyByName("OR.properties").getProperty("Username");
		String loginUserPassword = ReadProperties.readPropertyByName("OR.properties").getProperty("UserPassword");
		String LoginSubmit = ReadProperties.readPropertyByName("OR.properties").getProperty("Login");
		String userid1 = ReadProperties.readPropertyByName("Testdata.properties").getProperty(userid);
		String password1 = ReadProperties.readPropertyByName("Testdata.properties").getProperty(password);
		// Common_Functions.waitForParticularTime("5000");

		CommonFunctions.enterText(driver, "xpath", loginUsername, userid1);
		// Common_Functions.waitForParticularTime("5000");
		CommonFunctions.enterText(driver, "xpath", loginUserPassword, password1);
		CommonFunctions.waitForParticularTime("5000");
		CommonFunctions.click(driver, "xpath", LoginSubmit, "click");
	}

	@And("^the user selects the \"(.*?)\"$")
	public void the_user_selects_the(String arg1) throws Throwable {

		String customer = ReadProperties.readPropertyByName("Testdata.properties").getProperty(arg1);
		String select_customer = "(//a[contains(@href,'" + customer + "')])[2]";

		CommonFunctions.enterText(driver, "xpath", "//input[contains(@id,'searchB2B')]", customer);
		CommonFunctions.click(driver, "xpath", "//button[contains(@class,'searchB2B btn')]", "click");
		CommonFunctions.waitForParticularTime("3000");
		CommonFunctions.click(driver, "xpath", select_customer, "click");
	}

	@Then("^the user will click on \"([^\"]*)\" and clicks on \"([^\"]*)\"$")
	public void the_user_will_click_on_and_clicks_on(String arg1, String arg2) throws Throwable {
		String xpath1 = ReadProperties.readPropertyByName("OR.properties").getProperty(arg1);
		String xpath2 = ReadProperties.readPropertyByName("OR.properties").getProperty(arg2);
		CommonFunctions.waitForParticularTime("2000");
		CommonFunctions.click(driver, "xpath", xpath1, "click");
		CommonFunctions.waitForParticularTime("2000");
		CommonFunctions.click(driver, "xpath", xpath2, "click");
	}

	@Then("^the user will click on open_credits_downloadBtn for invoice \"([^\"]*)\" and clicks on \"([^\"]*)\"$")
	public void the_user_will_click_on_open_credits_downloadBtn_and_clicks_on_DetailedInvoice(String arg1, String arg2)
			throws Throwable {
		String invoice = ReadProperties.readPropertyByName("Testdata.properties").getProperty(arg1);
		String xpath2;
		String xpath1 = "//*[text()='" + invoice + "']/ancestor::li//div[contains(@class,'svg-icon svg-export')]";

		if (arg2.equalsIgnoreCase("Detailedinvoice")) {
			xpath2 = "//*[text()='" + invoice + "']/ancestor::li//a[text()='Detailed invoice']";
		} else
			xpath2 = "//*[text()='" + invoice + "']/ancestor::li//a[text()='Statement']";

		CommonFunctions.waitForParticularTime("2000");
		CommonFunctions.click(driver, "xpath", xpath1, "click");
		CommonFunctions.waitForParticularTime("2000");
		CommonFunctions.click(driver, "xpath", xpath2, "click");
	}

	@Then("^enters a \"([^\"]*)\" on \"([^\"]*)\"$")
	public void enters_a_on(String arg1, String arg2) throws Throwable {
		String Testdata_arg = ReadProperties.readPropertyByName("Testdata.properties").getProperty(arg1);
		String xpath_productsearchtxt = ReadProperties.readPropertyByName("OR.properties").getProperty(arg2);
		CommonFunctions.waitForParticularTime("4000");
		if ((arg1.equalsIgnoreCase("td_suspect_quantity"))) {
			driver.findElement(By.xpath(xpath_productsearchtxt)).sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE));
			CommonFunctions.enterText(driver, "xpath", xpath_productsearchtxt, Testdata_arg);
		} else {

			driver.findElement(By.xpath(xpath_productsearchtxt)).sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE));
			CommonFunctions.enterText(driver, "xpath", xpath_productsearchtxt, Testdata_arg);
			CommonFunctions.waitForParticularTime("2000");
		}
	}

	@Then("^the user will click \"([^\"]*)\" on \"([^\"]*)\"$")
	public void the_user_will_click_on(String arg1, String arg2) throws Throwable {
		String xpath1 = ReadProperties.readPropertyByName("OR.properties").getProperty(arg1);
		String xpath2 = ReadProperties.readPropertyByName("OR.properties").getProperty(arg2);
		CommonFunctions.waitForParticularTime("2000");
		CommonFunctions.click(driver, "xpath", xpath1, "click");
		CommonFunctions.waitForParticularTime("2000");
		CommonFunctions.click(driver, "xpath", xpath2, "click");
	}

	@Given("^the user will click on \"(.*?)\" and enters \"(.*?)\"$")
	public void the_user_will_click_on_and_enters(String arg1, String arg2) throws Throwable {
		// Write code here that turns the phrase above into concrete actions
		String StrElement1 = ReadProperties.readPropertyByName("OR.properties").getProperty(arg1);
		String StrElement2 = ReadProperties.readPropertyByName("Testdata.properties").getProperty(arg2);

		driver.findElement(By.xpath(StrElement1)).sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE));
		CommonFunctions.enterText(driver, "xpath", StrElement1, StrElement2);

	}

	@When("^the user validates \"([^\"]*)\" on \"([^\"]*)\"$")
	public void the_user_validates_on(String arg1, String arg2) throws Throwable {
		// Write code here that turns the phrase above into concrete actions
		String XpathHeader = ReadProperties.readPropertyByName("OR.properties").getProperty(arg2);
		String Testdata_arg1 = ReadProperties.readPropertyByName("Testdata.properties").getProperty(arg1);
		// String Xpath = XpathHeader+"[contains(text(),'"+Testdata_arg1+"')]";
		CommonFunctions.waitForParticularTime("2000");
		WebElement Element = driver.findElement(By.xpath(XpathHeader));
		greenHighLight(Element);
		if ((arg2.equalsIgnoreCase("cart_details_qunatityfield"))) {
			String Actualmsg = Element.getAttribute("value");
			System.out.println("Text present:" + Actualmsg);

			if (Actualmsg.contains(Testdata_arg1)) {
				System.out.println("Text Validation Passed: " + Actualmsg);
			} else {
				System.err.println("Text Validation Failed" + Actualmsg);
				Assert.assertEquals(Testdata_arg1, Actualmsg);

			}
		} else {
			String Actualmsg = Element.getText();
			System.out.println("Text present:" + Actualmsg);

			if (Actualmsg.contains(Testdata_arg1)) {
				System.out.println("Text Validation Passed: " + Actualmsg);
			} else {
				System.err.println("Text Validation Failed" + Actualmsg);
				Assert.assertEquals(Testdata_arg1, Actualmsg);

			}
		}
	}

	@When("^the user validates that the \"([^\"]*)\" matches with \"([^\"]*)\"$")
	public void the_user_validates_that_the_ToteQty_matches_with_ReceiptQty(String arg1, String arg2) throws Throwable {
		// Write code here that turns the phrase above into concrete actions
		String ToteQtyXpath = ReadProperties.readPropertyByName("OR.properties").getProperty(arg1);
		String ReceiptQtyXpath = ReadProperties.readPropertyByName("OR.properties").getProperty(arg2);
		String XpathRows = ReadProperties.readPropertyByName("OR.properties").getProperty("ReceiveToteDetails_Rows");
		int cnt, toteQty, ReceiptQty;

		if (driver.findElements(By.xpath(XpathRows)).size() > 0) {

			cnt = driver.findElements(By.xpath(XpathRows)).size();

			for (int i = 1; i <= cnt; i++) {

				String sToteQtyXpath = "(" + ToteQtyXpath + ")[" + (i + 1) + "]";
				System.out.println("sToteQtyXpath " + sToteQtyXpath);

				WebElement wToteQty = driver.findElement(By.xpath(sToteQtyXpath));
				greenHighLight(wToteQty);

				String stoteQty = wToteQty.getText();
				System.out.println("stoteQty " + stoteQty);
				toteQty = Integer.parseInt(stoteQty);
				System.out.println("toteQty " + toteQty);

				String sReceiptQtyXpath = "(" + ReceiptQtyXpath + ")[" + (i) + "]";
				System.out.println("sReceiptQtyXpath " + sReceiptQtyXpath);

				WebElement wReceiptQty = driver.findElement(By.xpath(sReceiptQtyXpath));
				greenHighLight(wReceiptQty);

				String sReceiptQty = wReceiptQty.getAttribute("value");
				System.out.println("sReceiptQty " + sReceiptQty);

				if (sReceiptQty.isEmpty()) {
					continue;
				}

				ReceiptQty = Integer.parseInt(sReceiptQty);
				System.out.println("ReceiptQty " + ReceiptQty);

				if (toteQty == ReceiptQty) {

					System.out.println("ReceiptQty " + ReceiptQty + " matches with toteQty " + toteQty);

				} else {
					System.out.println("ReceiptQty " + ReceiptQty + " does not matche with toteQty " + toteQty);
					Assert.assertEquals("ReceiptQty does not matche with toteQty",
							"ReceiptQty " + ReceiptQty + " toteQty " + toteQty);
				}

			}

		}

	}

	@When("^capture the order number \"([^\"]*)\"$")
	public String capture_the_order_number(String arg1) throws Throwable {
		// Write code here that turns the phrase above into concrete actions
		String OrderNumber = ReadProperties.readPropertyByName("OR.properties").getProperty(arg1);
		String xpath = driver.findElement(By.xpath(OrderNumber)).getText();
		System.out.println("Order Number is Captured : " + OrderNumber);
		String[] tokens = xpath.split(" ");
		System.out.println("tokens: " + tokens[2]);
		xpath = tokens[2];
		orderID = xpath;
		System.out.println("Order Number is Captured : " + orderID);
		// scenario.write("order Number is :Login Success ");
		// Scenario scenario = null;
		// Scenario scenario = null;
		// String ordernumber =orderid.capture_the_order_number();
		scenario.log("order Number is : " + xpath);
		// ReadProperties.display(scenario, "123");

		return xpath;
	}

	@When("^capture the manual C(\\d+) order number \"([^\"]*)\"$")
	public String capture_the_manual_C_order_number(int arg1, String arg2) throws Throwable {
		// Write code here that turns the phrase above into concrete actions

		String OrderNumber = ReadProperties.readPropertyByName("OR.properties").getProperty(arg2);
		String xpath = driver.findElement(By.xpath(OrderNumber)).getText();
		System.out.println("Order Number is Captured : " + OrderNumber);
		String[] tokens = xpath.split(" ");
		System.out.println("tokens: " + tokens[3]);
		xpath = tokens[3];
		orderID = xpath;
		System.out.println("Order Number is Captured : " + orderID);
		// scenario.write("order Number is :Login Success ");
		// Scenario scenario = null;
		// Scenario scenario = null;
		// String ordernumber =orderid.capture_the_order_number();
		scenario.log("order Number is : " + xpath);
		// ReadProperties.display(scenario, "123");

		return xpath;

	}

	@Then("^the user enters order number on \"([^\"]*)\"$")
	public void the_user_enters_order_number_on(String arg1) throws Throwable {
		// Write code here that turns the phrase above into concrete actions
		String xapth_productsearchtxt = ReadProperties.readPropertyByName("OR.properties").getProperty(arg1);
		CommonFunctions.waitForParticularTime("2000");
		CommonFunctions.enterText(driver, "xpath", xapth_productsearchtxt, orderID);
	}

	@When("^the user validated if \"([^\"]*)\" is enabled \"([^\"]*)\"$")
	public void the_user_validated_if_is_enabled(String arg1, String arg2) throws Throwable {
		// Write code here that turns the phrase above into concrete actions
		String Xpath = ReadProperties.readPropertyByName("OR.properties").getProperty(arg1);
		WebElement element = driver.findElement(By.xpath(Xpath));

		if (arg2.equalsIgnoreCase("yes")) {

			if (element.isEnabled()) {
				System.out.println("Element " + arg1 + " is Enabled: Passed");
				greenHighLight(element);

			} else {
				System.out.println("Element not enabled: " + arg1 + " - Not as Expected");
				Assert.assertEquals("Element is not enabled", Xpath);
			}
		} else if (arg2.equalsIgnoreCase("no")) {

			// if (!(element.isEnabled()) ||
			// Boolean.valueOf(element.getAttribute("disabled")))
			if (Boolean.valueOf(element.getAttribute("disabled"))) {

				System.out.println("Element " + arg1 + " is Disabled: Passed");

				greenHighLight(element);
			} else {
				System.out.println("Element is enabled: " + arg1 + " - Not as Expected");
				Assert.assertEquals("Element is enabled", Xpath);
			}
		}
	}

	@When("^the user validates that value of field \"([^\"]*)\" is blank$")
	public void the_user_validates_that_value_of_field_is_blank(String arg1) throws Throwable {
		// Write code here that turns the phrase above into concrete actions
		String Xpath = ReadProperties.readPropertyByName("OR.properties").getProperty(arg1);

		if (driver.findElements(By.xpath(Xpath)).size() > 0) {

			List<WebElement> element = driver.findElements(By.xpath(Xpath));

			for (int i = 0; i < element.size(); i++) {
				greenHighLight(element.get(i));

				String txt = element.get(i).getText();

				if (txt.isEmpty() || txt.equalsIgnoreCase("")) {

					System.out.println("Element " + arg1 + " has blank value ");

				} else {
					System.out.println("Element " + arg1 + " does not have blank value. Xpath - " + Xpath);
					Assert.assertEquals("Element does not have blank value", arg1);
				}

			}
		} else {
			System.out.println("Element " + arg1 + " does not exist. Xpath - " + Xpath);
			Assert.assertEquals("Element does not exist", Xpath);
		}

	}

	@When("^the user validates that date of field \"([^\"]*)\" is \"([^\"]*)\" date for entry \"([^\"]*)\"$")
	public void the_user_validates_that_date_of_field_is_todays_date_for_entry(String arg1, String arg2, String arg3)
			throws Throwable {
		// Write code here that turns the phrase above into concrete actions
		String Xpath = ReadProperties.readPropertyByName("OR.properties").getProperty(arg1);
		String Testdata;

		if (arg2.equalsIgnoreCase("yesterdays")) {
			DateFormat dtf = new SimpleDateFormat("MM/dd/yyyy");
			Testdata = dtf.format(yesterday()).toString();
			System.out.println("yesterday's date : " + Testdata);
		}

		else if (arg2.equalsIgnoreCase("todays")) {
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MM/dd/yyyy");
			LocalDateTime now = LocalDateTime.now();
			Testdata = dtf.format(now).toString();
			System.out.println("today's date : " + Testdata);
		} else {
			Testdata = ReadProperties.readPropertyByName("Testdata.properties").getProperty(arg2);
			System.out.println("Testdata : " + Testdata);
		}

		if (driver.findElements(By.xpath(Xpath)).size() > 0) {

			List<WebElement> element = driver.findElements(By.xpath(Xpath));

			if (arg3.equalsIgnoreCase("all")) {
				for (int i = 0; i < element.size(); i++) {
					greenHighLight(element.get(i));

					String date = element.get(i).getAttribute("value");

					if (date.equalsIgnoreCase(Testdata)) {

						System.out.println("Date " + arg1 + " has today's date -" + Testdata);

					} else {
						System.out.println("Element " + arg1 + " does not have " + arg2 + " date. Actual - " + date);
						Assert.assertEquals("Date is not today's date", arg1);
					}

				}
			} else {

				int i = Integer.parseInt(arg3) - 1;
				greenHighLight(element.get(i));

				String date = element.get(i).getAttribute("value");
				System.out.println("date " + date + " Xpath: " + Xpath + " Size " + i);

				if (date.equalsIgnoreCase(Testdata)) {

					System.out.println("Date " + arg1 + " has today's date -" + Testdata);

				} else {
					System.out.println("Element " + arg1 + " does not have " + arg2 + " date. Actual - " + date);
					Assert.assertEquals("Date is not today's date", arg1);
				}
			}

		} else {
			System.out.println("Element " + arg1 + " does not exist. Xpath - " + Xpath);
			Assert.assertEquals("Element does not exist", Xpath);
		}

	}

	@Then("^user validates if number of rows \"(.*?)\" equals value in field \"(.*?)\"$")
	public void user_validates_if_number_of_rows_equals_value_in_field(String arg1, String arg2) throws Throwable {

		String RowXpath = ReadProperties.readPropertyByName("OR.properties").getProperty(arg1);
		String ValueXpath = ReadProperties.readPropertyByName("OR.properties").getProperty(arg2);

		List<WebElement> elements = driver.findElements(By.xpath(RowXpath));
		int count = elements.size();
		System.out.println("Rows count -" + count);

		String value = driver.findElement(By.xpath(ValueXpath)).getText();

		if (Integer.parseInt(value) == count)
			System.out.println("Elements count -" + count + " matched with expected value -" + value);
		else {
			System.out.println("Elements count -" + count + " did not match with expected value -" + value);
			Assert.assertEquals("expected value -" + value, "Elements count -" + count);
		}

	}

	@Then("^the user enters text \"([^\"]*)\" on field \"([^\"]*)\" for \"([^\"]*)\" entry$")
	public void the_user_enters_a_text_on_field_for_entry(String arg1, String arg2, String arg3) throws Throwable {
		String Testdata;

		String xpath_txtbox = ReadProperties.readPropertyByName("OR.properties").getProperty(arg2);
		List<WebElement> elements;
		if (arg3.equals("SharedProductA")) {
			elements = receiptQty(arg2, arg3);
		} else {

			elements = driver.findElements(By.xpath(xpath_txtbox));
		}

		if (arg1.equalsIgnoreCase("blank")) {
			Testdata = "";
		} else if (arg1.equalsIgnoreCase("td_ReceiptQty")) {
			Testdata = ReadProperties.readPropertyByName("Testdata.properties").getProperty(arg1);
		} else {
			Testdata = arg1;
		}
		switch (arg3.toLowerCase()) {
		case "all":
			for (int i = 0; i < elements.size(); i++) {
				greenHighLight(elements.get(i));
				if (Testdata != "blank") {
					elements.get(i).clear();
					elements.get(i).sendKeys(Testdata);
					System.out.println("Text " + Testdata + " entered for field " + arg2 + " entry " + (i + 1));
				}
			}

			break;
		case "sharedproducta":
		case "sharedproductb":
			greenHighLight(elements.get(0));
			if (Testdata != "blank") {
				elements.get(0).clear();
				elements.get(0).sendKeys(Testdata);
				System.out.println("Text " + Testdata + " entered for field " + arg2 + " entry " + arg3);
			}
			break;
		default:

			int j = Integer.parseInt(arg3);
			greenHighLight(elements.get(j - 1));
			elements.get(j - 1).clear();
			/*
			 * elements.get(i - 1).sendKeys(Keys.HOME, Keys.chord(Keys.SHIFT, Keys.END));
			 */
			elements.get(j - 1).click();
			if (Testdata != "blank") {
				elements.get(j - 1).sendKeys(Testdata);
				System.out.println("Text " + Testdata + " entered for field " + arg2 + " entry " + j);

			}
		}
		CommonFunctions.waitForParticularTime("2000");

	}

	@Then("^the user enters \"([^\"]*)\" date on field \"([^\"]*)\" for \"([^\"]*)\" entry$")
	public void the_user_enters_date_on_field_for_entry(String arg1, String arg2, String arg3) throws Throwable {
		String Testdata;
		String xpath_txtbox = ReadProperties.readPropertyByName("OR.properties").getProperty(arg2);

		if (arg1.equalsIgnoreCase("yesterdays")) {
			DateFormat dtf = new SimpleDateFormat("MM/dd/yyyy");
			Testdata = dtf.format(yesterday()).toString();
			System.out.println("yesterday's date : " + Testdata);
		}

		else if (arg1.equalsIgnoreCase("todays")) {
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MM/dd/yyyy");
			LocalDateTime now = LocalDateTime.now();
			Testdata = dtf.format(now).toString();
			System.out.println("today's date : " + Testdata);

		} else if (arg1.equalsIgnoreCase("tomorrows")) {
			DateFormat dtf = new SimpleDateFormat("MM/dd/yyyy");
			Testdata = dtf.format(tomorrow()).toString();
			System.out.println("tomorrows date : " + Testdata);

		} else if (arg1.equalsIgnoreCase("within2years")) {
			DateFormat dtf = new SimpleDateFormat("MM/dd/yyyy");
			Testdata = dtf.format(withintwoyears()).toString();
			System.out.println("tomorrows date : " + Testdata);

		} else
			Testdata = ReadProperties.readPropertyByName("Testdata.properties").getProperty(arg1);

		List<WebElement> elements = driver.findElements(By.xpath(xpath_txtbox));

		if (arg3.equalsIgnoreCase("all")) {

			for (int i = 0; i < elements.size(); i++) {

				elements.get(i).clear();
				elements.get(i).sendKeys(Testdata);
				System.out.println("Date " + Testdata + " entered for field " + arg2 + " entry " + i);
			}

		} else {
			int i = Integer.parseInt(arg3);
			elements.get(i - 1).clear();
			// elements.get(i-1).click();
			// elements.get(i-1).sendKeys(Testdata);

			if (arg2.equalsIgnoreCase("ReceiveToteDetails_HistReceiptDate")) {
				elements.get(i - 1).click();
			}

			elements.get(i - 1).sendKeys(Keys.HOME, Keys.chord(Keys.SHIFT, Keys.END), Testdata);
			System.out.println("Date " + Testdata + " entered for field " + arg2 + " entry " + (i - 1));
		}

		CommonFunctions.waitForParticularTime("2000");

	}

	private Date yesterday() {
		final Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, -1);
		return cal.getTime();
	}

	private Date tomorrow() {
		final Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, +1);
		return cal.getTime();
	}

	private Date withintwoyears() {
		final Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, -690);
		return cal.getTime();
	}

	@When("^user does mouse hover on \"(.*?)\" and validate text \"(.*?)\" on \"(.*?)\"$")
	public void user_does_mouse_hover_on_and_validate_text(String arg1, String arg2, String arg3) throws Throwable {
		// Write code here that turns the phrase above into concrete actions
		String StrElement = ReadProperties.readPropertyByName("OR.properties").getProperty(arg1);
		String strText = ReadProperties.readPropertyByName("Testdata.properties").getProperty(arg2);
		String StrToolTipElement = ReadProperties.readPropertyByName("OR.properties").getProperty(arg3);
		WebElement element = driver.findElement(By.xpath(StrElement));

		Actions toolAct = new Actions(driver);
		toolAct.moveToElement(element).build().perform();
		WebElement toolTipElement = driver.findElement(By.xpath(StrToolTipElement));
		String toolTipText = toolTipElement.getText();
		System.out.println(toolTipText);

		if (strText.equalsIgnoreCase(toolTipText)) {
			System.out.println("ToolTip Text validated - " + toolTipText);
		} else {
			System.out.println("ToolTip Text validation failed Actual- " + toolTipText + ". Expected -" + strText);
			Assert.assertEquals("ToolTip Text validation failed -" + toolTipText, " Expected -" + strText);
		}

	}

	@When("^the user validates quantity \"(.*?)\" on tote \"(.*?)\" under invoice page$")
	public void the_user_validates_quantity_on_tote_under_invoice_page(String arg1, String arg2) throws Throwable {
		// Write code here that turns the phrase above into concrete actions

		String Testdata_qty = ReadProperties.readPropertyByName("Testdata.properties").getProperty(arg1);
		String Testdata_tote = ReadProperties.readPropertyByName("Testdata.properties").getProperty(arg2);

		String XpathHeader = "//div[@title='" + Testdata_tote
				+ "']/ancestor::div[contains(@id,'invoiceLine')]//input[contains(@class,'invoiceQuantitySelector')]";

		CommonFunctions.waitForParticularTime("2000");
		WebElement Element = driver.findElement(By.xpath(XpathHeader));

		greenHighLight(Element);
		String ActualValue = Element.getAttribute("value");
		System.out.println("Text present:" + ActualValue);

		if (ActualValue.contains(Testdata_qty)) {
			System.out.println("Tote Quantity Validation Passed: " + ActualValue);
		} else {
			System.err.println("Tote Quantity Validation Passed: " + ActualValue);
			Assert.assertEquals(Testdata_qty, ActualValue);

		}
	}

	// ################################################################################################################
	// ################################################################################################################

	@Given("^Select \"(.*?)\"$")
	public void select(String Facility) throws Throwable {
		// Write code here that turns the phrase above into concrete actions
		String Xpath_facilitydropdown = ReadProperties.readPropertyByName("OR.properties").getProperty(Facility);
		CommonFunctions.waitForParticularTime("2000");
		CommonFunctions.click(driver, "xpath", Xpath_facilitydropdown, "click");
		CommonFunctions.waitForParticularTime("2000");
		System.out.println("Facility Selected...");

	}

	@When("^Enter \"(.*?)\" and \"(.*?)\"$")
	public void enter_and(String arg1, String arg2) throws Throwable {
		String xapth_productsearchtxt = ReadProperties.readPropertyByName("OR.properties").getProperty(arg1);
		String Testdata_arg = ReadProperties.readPropertyByName("Testdata.properties").getProperty(arg2);
		CommonFunctions.waitForParticularTime("2000");
		driver.findElement(By.xpath(xapth_productsearchtxt)).sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE));
		CommonFunctions.enterText(driver, "xpath", xapth_productsearchtxt, Testdata_arg);
		CommonFunctions.waitForParticularTime("2000");

	}

	@And("^click on \"(.*?)\" and then click on \"(.*?)\"  using mouse-hover-action$")
	public void click_on_and_then_click_on_using_mouse_hover_action(String arg1, String arg2) throws Throwable {
		// Write code here that turns the phrase above into concrete actions
		String StrElement1 = ReadProperties.readPropertyByName("OR.properties").getProperty(arg1);
		String StrElement2 = ReadProperties.readPropertyByName("OR.properties").getProperty(arg2);

		if ((arg1.equalsIgnoreCase("CopyOrderContainer")) || (arg1.equalsIgnoreCase("NewCopyOrder"))) {

			StrElement1 = StrElement1 + PONumber + "'])[1]";
		}

		WebElement element1 = driver.findElement(By.xpath(StrElement1));
		WebElement element2 = driver.findElement(By.xpath(StrElement2));

		// added by Vipin - Start
		scrollToElementByOffset(element2, -200);
		// added by Vipin - End
		CommonFunctions.waitForParticularTime("2000");
		Actions action = new Actions(driver);
		action.moveToElement(element1).click(element2).build().perform();
		System.out.println("Element Clicked using mouse over");
		CommonFunctions.waitForParticularTime("2000");

	}

	@And("^Element verification \"(.*?)\" on the page condition \"(.*?)\"$")
	public void Element_verification(String arg1, String arg2) throws Throwable {
		// Write code here that turns the phrase above into concrete actions
		String Xpath_facilitydropdown = ReadProperties.readPropertyByName("OR.properties").getProperty(arg1);
		CommonFunctions.waitForParticularTime("5000");
		if (arg2.equalsIgnoreCase("no")) {
			if (driver.findElements(By.xpath(Xpath_facilitydropdown)).size() != 0) {
				Assert.assertEquals(arg1, ">0");
			}
		}
		if (arg2.equalsIgnoreCase("yes")) {
			if (driver.findElements(By.xpath(Xpath_facilitydropdown)).size() != 0) {
				Assert.assertEquals(arg1, "<0");
			}
		}

	}

	/*
	 * @Given("^Add \"(.*?)\"$") public void add1(String arg1) throws Throwable {
	 * String Xpath_facilitydropdown =
	 * ReadProperties.readPropertyByName("OR.properties").getProperty(arg1);
	 * Common_Functions.click(driver, "xpath", Xpath_facilitydropdown, "click");
	 * Common_Functions.waitForParticularTime("2000"); }
	 */

	@Given("^Validate error message \"(.*?)\"$")
	public void validate_error_message(String arg1) throws Throwable {
		// Write code here that turns the phrase above into concrete actions
		// String
		// Xpath_facilitydropdown=ReadProperties.readPropertyByName("OR.properties").getProperty(arg1);
		// Common_Functions.validateProperty(driver, "xpath",
		// Xpath_facilitydropdown, "text", "", value);
		String ponum = driver.findElement(By.xpath("//div[contains(@class,'editable-text__ErrorText-y3zf09-0')]"))
				.getText();
		System.out.println(ponum);

	}

	@And("^Validate text message \"(.*?)\" on \"(.*?)\"$")
	public void validate_text_message(String arg1, String arg2) throws Throwable {
		String XpathHeader = ReadProperties.readPropertyByName("OR.properties").getProperty(arg2);
		String Testdata_arg1 = ReadProperties.readPropertyByName("Testdata.properties").getProperty(arg1);
		// String Xpath = XpathHeader+"[contains(text(),'"+Testdata_arg1+"')]";
		CommonFunctions.waitForParticularTime("7000");
		WebElement Element = driver.findElement(By.xpath(XpathHeader));

		greenHighLight(Element);
		String Actualmsg = Element.getText();
		System.out.println("Text present:" + Actualmsg);

		if (Actualmsg.contains(Testdata_arg1)) {
			System.out.println("Text Validation Passed: " + Actualmsg);
		} else {
			System.err.println("Text Validation Failed" + Actualmsg);
			Assert.assertEquals(Testdata_arg1, Actualmsg);

		}
	}

	// Modified by Vipin
	@And("^Validate text \"(.*?)\" on \"(.*?)\"$")
	public void validate_text(String arg1, String arg2) throws Throwable {
		String XpathHeader = ReadProperties.readPropertyByName("OR.properties").getProperty(arg2);
		String Testdata_arg1;

		if (arg1.equalsIgnoreCase("numberspinnervalue")) {
			Testdata_arg1 = numberSpinnerValue;
		} else {
			Testdata_arg1 = ReadProperties.readPropertyByName("Testdata.properties").getProperty(arg1);
		}

		String Xpath = "(" + XpathHeader + "[text()='" + Testdata_arg1 + "'])[last()]";
		// String Xpath = "(" + XpathHeader + "[text()='" + Testdata_arg1 +
		// "'])[2]";

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(Xpath)));
		CommonFunctions.waitForParticularTime("2000");
		WebElement Element = driver.findElement(By.xpath(Xpath));

		greenHighLight(Element);
		String Actualmsg = Element.getText();
		Actualmsg = Actualmsg.toLowerCase();
		Testdata_arg1 = Testdata_arg1.toLowerCase();

		if (Actualmsg.contains(Testdata_arg1.trim())) {
			System.out.println("Text Validation Passed: " + Actualmsg);
		} else {
			System.err.println("Text Validation Failed: " + Xpath);
			Assert.assertEquals(Testdata_arg1, Actualmsg);

		}
	}

	@When("^Navigates to RCS page$")
	public void navigates_to_RCS_page() throws Throwable {
		// Write code here that turns the phrase above into concrete actions
		CommonFunctions.waitForParticularTime("5000");
		driver.switchTo().frame("x2branding");
		System.out.println("switchedframe");
		CommonFunctions.scrollToPageBottom();

	}

	@When("^Scroll down$")
	public void scrolldown() throws Throwable {
		// Write code here that turns the phrase above into concrete actions
		CommonFunctions.waitForParticularTime("2000");
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("window.scrollTo(0, document.body.scrollHeight)");
		CommonFunctions.waitForParticularTime("2000");
//		CommonFunctions.scrollToPageBottom();

	}

	@When("^Scroll up$")
	public void scrollup() throws Throwable {
		// Write code here that turns the phrase above into concrete actions
		CommonFunctions.waitForParticularTime("2000");
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("window.scrollTo(0, 0)");
		CommonFunctions.waitForParticularTime("2000");
//		CommonFunctions.scrollToPageTop();

	}

	@Given("^Add \"(.*?)\"$")
	public void add(String arg1) throws Throwable {
		String Xpath_facilitydropdown = ReadProperties.readPropertyByName("OR.properties").getProperty(arg1);
		CommonFunctions.click(driver, "xpath", Xpath_facilitydropdown, "click");
		CommonFunctions.waitForParticularTime("2000");
	}

	@Given("^Waiting Time \"(.*?)\" miliseconds$")
	public void wait(String arg1) throws Throwable {
		CommonFunctions.waitForParticularTime(arg1);
	}

	@Given("^Selects \"(.*?)\" for \"(.*?)\"$")
	public void selects_for(String arg1, String arg2) throws Throwable {
		// Write code here that turns the phrase above into concrete actions

		// Write code here that turns the phrase above into concrete actions
		String choosefile = ReadProperties.readPropertyByName("OR.properties").getProperty(arg1);
		String choosefilepath = ReadProperties.readPropertyByName("Testdata.properties").getProperty(arg2);
		CommonFunctions.click(driver, "xpath", choosefile, "click");
		CommonFunctions.waitForParticularTime("5000");
		CommonFunctions.uploadFile(choosefilepath);
	}

	@Given("^Validate contract color and \"(.*?)\"$")
	public void validate_contract_color_and(String arg1) throws Throwable {
		// Write code here that turns the phrase above into concrete actions
		String StrElement2 = ReadProperties.readPropertyByName("OR.properties").getProperty(arg1);
		WebElement element2 = driver.findElement(By.xpath(StrElement2));
		String color = driver.findElement(By.xpath(
				"//div[@class='contract-indicators__PrimaryContract-gs91ft-3 jeKqyt contract-indicators__BaseContract-gs91ft-1 fHOjbW']"))
				.getCssValue("background-color");
		// contarct.getCssValue("color");
		System.out.println("contarct color:" + color);
		String hex = Color.fromString(color).asHex();
		System.out.println(hex);
		Assert.assertEquals("#00539b", hex);

		Actions tooltip = new Actions(driver);
		// WebElement
		// element=driver.findElement(By.xpath("//div[@class='simple-contract-tooltip-provider__ContractIndicatorWrapper-s6uxzia-0
		// hBTFgj']"));
		tooltip.clickAndHold(element2).perform();
		String ContarctText = element2.getText();
		System.out.println("ContarctText: " + ContarctText);

	}

	@Given("^Click \"(.*?)\" and edit \"(.*?)\"$")
	public void click_and_edit(String arg1, String arg2) throws Throwable {
		// Write code here that turns the phrase above into concrete actions
		String StrElement1 = ReadProperties.readPropertyByName("OR.properties").getProperty(arg1);
		String StrElement2 = ReadProperties.readPropertyByName("OR.properties").getProperty(arg2);
		WebElement facility = driver.findElement(By.xpath(StrElement1));
		// WebElement
		// facility=driver.findElement(By.xpath("//div[@class='editable-text__EditableText-y3zf09-1
		// fjtrcr']"));
		CommonFunctions.waitForParticularTime("4000");
		facility.click();
		CommonFunctions.waitForParticularTime("8000");
		Robot robot = new Robot();
		robot.keyPress(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_A);
		robot.keyPress(KeyEvent.VK_BACK_SPACE);

		CommonFunctions.waitForParticularTime("5000");
		robot.keyRelease(KeyEvent.VK_A);
		robot.keyRelease(KeyEvent.VK_CONTROL);
		robot.keyRelease(KeyEvent.VK_BACK_SPACE);

		Actions action = new Actions(driver);
		action.moveToElement(facility).click().sendKeys(StrElement2).build().perform();

	}

	@When("^Set \"(.*?)\" and Select \"(.*?)\" is \"(.*?)\" and \"(.*?)\" is \"(.*?)\"$")
	public void set_and_Select_is_and_is(String arg1, String arg2, String arg3, String arg4, String arg5)
			throws Throwable {

		// Write code here that turns the phrase above into concrete actions
		String DateRange = ReadProperties.readPropertyByName("OR.properties").getProperty(arg1);
		String StartDate = ReadProperties.readPropertyByName("OR.properties").getProperty(arg2);
		String EndDate = ReadProperties.readPropertyByName("OR.properties").getProperty(arg4);
		ReadProperties.readPropertyByName("OR.properties").getProperty(arg3);
		ReadProperties.readPropertyByName("OR.properties").getProperty(arg5);
		CommonFunctions.waitForParticularTime("3000");
		CommonFunctions.click(driver, "xpath", DateRange, "click");
		CommonFunctions.enterText(driver, "xpath", StartDate, arg3);
		CommonFunctions.enterText(driver, "xpath", EndDate, arg5);
		CommonFunctions.scrollToPageBottom();
	}

	// added by sindoora
	@Given("^Selects \"(.*?)\" on \"(.*?)\"$")
	public void selects_on(String arg1, String arg2) throws Throwable {
		// Write code here that turns the phrase above into concrete actions
		String Xpath_Facility_DDt = ReadProperties.readPropertyByName("OR.properties").getProperty(arg2);
		String Testdata_arg1 = ReadProperties.readPropertyByName("Testdata.properties").getProperty(arg1);

		String Xpath_facilitydropdown = "" + Xpath_Facility_DDt + "'" + Testdata_arg1 + "')]";
		// added by Vipin - Start
		if (arg2.equalsIgnoreCase("Facility_DD")) {
			WebElement ElementActionPerform = driver.findElement(By.xpath(Xpath_facilitydropdown));
			scrollToElementByOffset(ElementActionPerform, -200);
		}

		// added by Vipin - End
		CommonFunctions.click(driver, "xpath", Xpath_facilitydropdown, "click");
		CommonFunctions.waitForParticularTime("2000");
		CommonFunctions.scrollToPageTop();
		System.out.println("Facility Selected...");

	}

	@When("^Scroll page up$")
	public void scroll_page_up() throws Throwable {
		// Write code here that turns the phrase above into concrete actions
		CommonFunctions.scrollToPageTop();
	}

	@When("^Select an activeorder \"(.*?)\" from \"(.*?)\"$")
	public void select_an_activeorder_from(String arg1, String arg2) throws Throwable {
		// Write code here that turns the phrase above into concrete actions

		// needs change - Vipin
		String activeorder = ReadProperties.readPropertyByName("OR.properties").getProperty(arg2);
		String OrderName = ReadProperties.readPropertyByName("Testdata.properties").getProperty(arg1);
		String order = "(" + activeorder + "'" + OrderName + "'])[1]";
		// "//div[contains(@class,'active-order-list-item')]/div/div[2]/div[contains(text(),'"+OrderName+"')]";
		CommonFunctions.waitForParticularTime("2000");
		// modified by Vipin
		// List<WebElement> ElementActionPerform =
		// driver.findElements(By.xpath(order));
		// scrollToElementByOffset(ElementActionPerform.get(0), -200);

		// Common_Functions.waitForParticularTime("2000");
		// ElementActionPerform.get(0).click();
		CommonFunctions.click(driver, "xpath", order, "click");

		System.out.println("Select an order from active order");
	}

	@When("^Click FinAlternative \"(.*?)\" on order details page$")
	public void click_FinAlternative_on_order_details_page(String arg1) throws Throwable {
		// Write code here that turns the phrase above into concrete actions

		// Updated by Vipin
		String OrderID = ReadProperties.readPropertyByName("Testdata.properties").getProperty(arg1);
		String findalternatives = "//div[contains(text(),'" + OrderID
				+ "')]/ancestor::div[contains(@class,'rt-tr-group')]/descendant::div[contains(text(),'Find Alternatives')]";
		CommonFunctions.waitForParticularTime("6000");

		// added by Vipin - Start
		WebElement ElementActionPerform = driver.findElement(By.xpath(findalternatives));
		scrollToElementByOffset(ElementActionPerform, -200);
		// added by Vipin - End

		CommonFunctions.click(driver, "xpath", findalternatives, "click");

	}

	// added by Vipin - Start
	private WebElement scrollToElementByOffset(WebElement element, int offset) {
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("window.scrollTo(" + element.getLocation().getX() + ","
				+ (element.getLocation().getY() + offset) + ");");

		return element;
	}
	// added by Vipin - End

	@And("Validate order \"(.*?)\" is submitted")
	public void Validate_Order_Submission(String arg1) throws Throwable {
		// Write code here that turns the phrase above into concrete actions
		String OrderName = ReadProperties.readPropertyByName("Testdata.properties").getProperty(arg1);
		String SubmitOrder = "//div[contains(text(),'Order " + OrderName + "')]";
		if (driver.findElement(By.xpath(SubmitOrder)).isDisplayed()) {
			System.out.println("Order " + arg1 + "Submitted");
		} else {
			System.out.println("Order " + arg1 + "Not Submitted");
		}
	}

	@Given("^Enter \"(.*?)\" on \"(.*?)\"$")
	public void enter_on(String arg1, String arg2) throws Throwable {
		// Write code here that turns the phrase above into concrete actions
		String Xpath_Edit = ReadProperties.readPropertyByName("OR.properties").getProperty(arg2);
		WebElement Edit_Name = driver.findElement(By.xpath(Xpath_Edit));
		String TestdataText = ReadProperties.readPropertyByName("Testdata.properties").getProperty(arg1);
		System.out.println("name:" + TestdataText);
		Actions actions = new Actions(driver);
		actions.moveToElement(Edit_Name);
		actions.click();
		actions.sendKeys(Keys.CONTROL + "a" + Keys.CONTROL);
		actions.sendKeys(Keys.BACK_SPACE).sendKeys(TestdataText);
		actions.build().perform();
		CommonFunctions.waitForParticularTime("3000");

	}

	@Given("^Remove text on \"(.*?)\"$")
	public void Remove_on(String arg1) throws Throwable {
		// Write code here that turns the phrase above into concrete actions
		String Xpath_Edit = ReadProperties.readPropertyByName("OR.properties").getProperty(arg1);
		WebElement Edit_Name = driver.findElement(By.xpath(Xpath_Edit));

		Actions actions = new Actions(driver);
		actions.moveToElement(Edit_Name);
		actions.click();
		actions.sendKeys(Keys.CONTROL + "a" + Keys.CONTROL);
		actions.sendKeys(Keys.BACK_SPACE).sendKeys("");
		actions.build().perform();
		CommonFunctions.waitForParticularTime("3000");
	}

	// added by Sindoora
	@When("^Click \"(.*?)\" on \"(.*?)\"$")
	public void click_on(String arg1, String arg2) throws Throwable {
		// Write code here that turns the phrase above into concrete actions
		String element = ReadProperties.readPropertyByName("OR.properties").getProperty(arg2);
		String element1 = ReadProperties.readPropertyByName("Testdata.properties").getProperty(arg1);
		String Xpath_element = "" + element + ",'" + element1 + "')]";
		CommonFunctions.waitForParticularTime("2000");
		CommonFunctions.click(driver, "xpath", Xpath_element, "click");

	}

	// added by sindoora 3/20/18
	@Given("^Search for fileimport \"(.*?)\" and open \"(.*?)\"$")
	public void search_for_fileimport_and_open(String arg1, String arg2) throws Throwable {
		// Write code here that turns the phrase above into concrete actions
		String Xpath_Filename = ReadProperties.readPropertyByName("OR.properties").getProperty(arg2);
		String Testdata_arg1 = ReadProperties.readPropertyByName("Testdata.properties").getProperty(arg1);

		String Xpath_Importfile = "" + Xpath_Filename + "'" + Testdata_arg1 + "']";

		WebElement element1 = driver.findElement(By.xpath(Xpath_Importfile));
		Actions action = new Actions(driver);
		action.moveToElement(element1).click(element1).build().perform();

		// Common_Functions.click(driver, "xpath", Xpath_Importfile, "click");
		CommonFunctions.waitForParticularTime("5000");
		System.out.println("Facility Selected...");

	}

	@When("^Click an \"(.*?)\" from \"(.*?)\"$")
	public void click_an_from(String arg1, String arg2) throws Throwable {
		// Write code here that turns the phrase above into concrete actions
		// Write code here that turns the phrase above into concrete actions
		String Xpath_Filename = ReadProperties.readPropertyByName("OR.properties").getProperty(arg2);
		String Testdata_arg1 = ReadProperties.readPropertyByName("Testdata.properties").getProperty(arg1);
		String Xpath_Importfile = "" + Xpath_Filename + "'" + Testdata_arg1 + "')]";
		CommonFunctions.click(driver, "xpath", Xpath_Importfile, "click");
		CommonFunctions.waitForParticularTime("5000");
		System.out.println("Facility Selected...");
	}

	// addded by Sindoora for NOISH Indicatorcan be used for tooltip related
	// scenarios
	@When("^Click and hold \"(.*?)\" and validate \"(.*?)\"$")
	public void click_and_hold_and_validate(String arg1, String arg2) throws Throwable {
		// Write code here that turns the phrase above into concrete actions
		String Xpath_Indicator = ReadProperties.readPropertyByName("OR.properties").getProperty(arg1);
		String Testdata_arg1 = ReadProperties.readPropertyByName("Testdata.properties").getProperty(arg2);

		WebElement element1 = driver.findElement(By.xpath(Xpath_Indicator));
		Actions tooltip = new Actions(driver);
		// Common_Functions.waitForParticularTime("5000");
		tooltip.moveToElement(element1).clickAndHold(element1).build().perform();
		String TooltipText = element1.getText();
		TTText = TooltipText;
		System.out.println("ContarctText: " + TooltipText);
		Assert.assertEquals(Testdata_arg1, TTText);
		System.out.println("Tooltip Validation passed");
		CommonFunctions.waitForParticularTime("5000");

	}

	@Then("^Click on AMU element \"(.*?)\" and validate tooltip sum \"(.*?)\"$")
	public void click_on_AMU_and_validate_Tooltip(String arg1, String arg2) throws Throwable {
		// Write code here that turns the phrase above into concrete actions
		// This function is specific to the the AMU functionality
		String Xpath_Indicator = ReadProperties.readPropertyByName("OR.properties").getProperty(arg1);
		String Testdata_arg1 = ReadProperties.readPropertyByName("Testdata.properties").getProperty(arg2);

		int TestData = Integer.parseInt(Testdata_arg1);

		WebElement element1 = driver.findElement(By.xpath(Xpath_Indicator));
		scrollToElementByOffset(element1, -200);
		Actions tooltip = new Actions(driver);
		tooltip.moveToElement(element1).clickAndHold(element1).build().perform();
		String TooltipText = element1.getText();
		TTText = TooltipText;
		System.out.println("Toot tip Text: " + TTText);

		String[] parts = TTText.split("\\n");
		int len = parts.length;
		int iter = len / 2;
		System.out.println("Length : " + len + ", Iter: " + iter);
		int sum = 0;
		int j = 2;

		for (int i = 1; i <= iter; i++) {

			System.out.println(parts[j]);

			String[] amu = parts[j].split(": ");
			String temp = amu[1].trim();
			int numb = Integer.parseInt(temp);
			sum = sum + numb;
			j = j + 2;
		}

		System.out.println("Sum Total: " + sum);
		Assert.assertEquals(TestData, sum);
		// Common_Functions.waitForParticularTime("5000");
	}

	@Given("^Scroll over element \"(.*?)\"$")
	public void Scrollover(String Facility) throws Throwable {
		// Write code here that turns the phrase above into concrete actions
		String Xpath_facilitydropdown = ReadProperties.readPropertyByName("OR.properties").getProperty(Facility);
		CommonFunctions.waitForParticularTime("2000");
		WebElement e = driver.findElement(By.xpath(Xpath_facilitydropdown));
		// This will scroll the page till the element is found
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView();", e);
		CommonFunctions.waitForParticularTime("1000");
		System.out.println("Scrollover...");

	}

	@Given("^Verify check box \"(.*?)\" is selected$")
	public void verify_check_box_is_selected(String arg1) throws Throwable {
		// Write code here that turns the phrase above into concrete actions
		String Xpath_facilitydropdown = ReadProperties.readPropertyByName("OR.properties").getProperty(arg1);
		CommonFunctions.waitForParticularTime("2000");

		if (driver.findElement(By.xpath(Xpath_facilitydropdown)).isSelected()) {
			System.out.println("Checkbox is selected");
		} else {
			System.out.println("Checkbox is not selected");
		}
	}

	@When("^Verify checkbox \"([^\"]*)\" is enabled \"([^\"]*)\"$")
	public void verify_checkbox_is_enabled(String arg1, String arg2) throws Throwable {
		// Write code here that turns the phrase above into concrete actions
		String data = ReadProperties.readPropertyByName("Testdata.properties").getProperty(arg1);
		String Xpath = "//div[contains(text(),'" + data
				+ "')]/ancestor::div[contains(@class,'account-with-checkbox__AccountRowWrapper')]//input";
		CommonFunctions.waitForParticularTime("2000");
		driver.findElement(By.xpath(Xpath));
		if (arg2.equalsIgnoreCase("yes")) {
			if (driver.findElement(By.xpath(Xpath)).isEnabled()) {
				System.out.println("Checkbox is enabled for: " + data);
			} else {
				System.out.println("Checkbox is not enabled for: " + data);
				Assert.assertEquals("Checkbox should be enabled", data);
			}
		}

		if (arg2.equalsIgnoreCase("no")) {
			if (driver.findElement(By.xpath(Xpath)).isEnabled()) {
				System.out.println("Checkbox is enabled for: " + data);
				Assert.assertEquals("Checkbox not be enabled", data);
			} else {
				System.out.println("Checkbox is not enabled for: " + data);
			}
		}
	}

	// Added by Vipin
	@Then("^Verify radio button \"(.*?)\" is selected \"(.*?)\"$")
	public void verify_radiobutton_is_selected(String arg1, String arg2) throws Throwable {
		// Write code here that turns the phrase above into concrete actions

		String data = ReadProperties.readPropertyByName("Testdata.properties").getProperty(arg1);
		String Xpath = "//div[contains(text(),'" + data + "')]/preceding::input[1][@type='radio']";

		CommonFunctions.waitForParticularTime("2000");

		if (arg2.equalsIgnoreCase("yes")) {
			if (driver.findElement(By.xpath(Xpath)).isSelected()) {
				System.out.println("Radio button is selected for: " + data);
			} else {
				System.out.println("Radio button is not selected for: " + data);
				Assert.assertEquals("Radio button should be selected", arg1);
			}
		}

		if (arg2.equalsIgnoreCase("no")) {
			if (driver.findElement(By.xpath(Xpath)).isSelected()) {
				System.out.println("Radio button is selected for: " + data);
				Assert.assertEquals("Radio button should not be selected", arg1);
			} else {
				System.out.println("Radio button is not selected for: " + data);
			}
		}

	}

	// Added by Vipin
	@Then("^Verify radio button \"(.*?)\" is enabled \"(.*?)\"$")
	public void verify_radiobutton_is_enabled(String arg1, String arg2) throws Throwable {

		String data = ReadProperties.readPropertyByName("Testdata.properties").getProperty(arg1);
		String Xpath = "//div[contains(text(),'" + data + "')]/preceding::input[1][@type='radio']";

		CommonFunctions.waitForParticularTime("2000");

		if (arg2.equalsIgnoreCase("yes")) {
			if (driver.findElement(By.xpath(Xpath)).isEnabled()) {
				System.out.println("Radio button is enabled for: " + data);
			} else {
				System.out.println("Radio button is not enabled for: " + data);
				Assert.assertEquals("Radio button should be enabled", data);
			}
		}

		if (arg2.equalsIgnoreCase("no")) {
			if (driver.findElement(By.xpath(Xpath)).isEnabled()) {
				System.out.println("Radio button is enabled for: " + data);
				Assert.assertEquals("Radio button should not be enabled", data);
			} else {
				System.out.println("Radio button is not enabled for: " + data);
			}
		}

	}

	@And("^Select \"(.*?)\" radio button$")
	public void select_radio_button(String arg1) throws Throwable {

		String data = ReadProperties.readPropertyByName("Testdata.properties").getProperty(arg1);
		String Xpath = "//div[contains(text(),'" + data + "')]/preceding::label[1][contains(@class,'RadioButton')]";
		String Xpath1 = "//input[contains(@value,'" + data + "')]/ancestor::label[1][contains(@class,'RadioButton')]";
		String Xpath2 = "//div[contains(text(),'" + data
				+ "')]/preceding::label[1][contains(@class,'RadioButton')]/input";
		String Xpath3 = "//span[contains(@class,'fonts__Body')][text()='" + data + "']";
		String Xpath4 = "//div[contains(text(),'" + data + "')]/ancestor::label[1][contains(@class,'RadioButton')]";
		int count = 0;

		if (driver.findElements(By.xpath(Xpath1)).size() > 0) {

			count = driver.findElements(By.xpath(Xpath1)).size();
			Xpath = Xpath1;

		} else if (driver.findElements(By.xpath(Xpath3)).size() > 0) {

			count = driver.findElements(By.xpath(Xpath3)).size();
			Xpath = Xpath3;

		} else if (driver.findElements(By.xpath(Xpath4)).size() > 0) {

			count = driver.findElements(By.xpath(Xpath4)).size();
			Xpath = Xpath4;

		} else if (driver.findElements(By.xpath(Xpath)).size() > 0) {

			String Attrname = driver.findElement(By.xpath(Xpath2)).getAttribute("name");
			if (Attrname.equalsIgnoreCase("orderTypes")) {
				Xpath = "//div[contains(text(),'" + data + "')]/ancestor::label[1][contains(@class,'RadioButton')]";
			}

			count = driver.findElements(By.xpath(Xpath)).size();

		}

		else {
			System.out.println("Radio button " + data + " not Selected. Xpath1" + Xpath1 + " \n Xpath" + Xpath);
			Assert.assertEquals("Radio Button should be clicked", data);
		}

		if (count > 0) {

			for (int i = 1; i <= count; i++) {

				if (driver.findElement(By.xpath("(" + Xpath + ")[" + i + "]")).isDisplayed()) {
					Xpath = "(" + Xpath + ")[" + i + "]";
					CommonFunctions.click(driver, "xpath", Xpath, "click");
					break;
				}

			}

		}

		CommonFunctions.waitForParticularTime("5000");
		System.out.println("Radio button " + data + " Selected...");

	}

	// Added by Vipin
	@When("^Validate if \"(.*?)\" Exists \"(.*?)\"$")
	public void validate_is_exists(String arg1, String arg2) throws Throwable {
		// Write code here that turns the phrase above into concrete actions
		String Xpath = ReadProperties.readPropertyByName("OR.properties").getProperty(arg1);

		if ((arg1.equalsIgnoreCase("CopyOrderContainer")) || (arg1.equalsIgnoreCase("NewCopyOrder"))) {

			Xpath = Xpath + PONumber + "'])[1]";
		}

		if (arg2.equalsIgnoreCase("no")) {
			// System.out.println("Checking if element "+arg1+" does not
			// exist");

			if (driver.findElements(By.xpath(Xpath)).size() > 0) {

				System.out.println("Element " + arg1 + " exists - Not expected");
				Assert.assertEquals("Element should not exist", arg1);
				WebElement Element = driver.findElement(By.xpath(Xpath));

				greenHighLight(Element);
			}
			System.out.println("Element " + arg1 + " does not exists - expected");
		}

		if (arg2.equalsIgnoreCase("yes")) {
			// System.out.println("Checking if element does exist");

			if (driver.findElements(By.xpath(Xpath)).size() > 0) {

				System.out.println("Element " + arg1 + " exists - expected");
				WebElement Element = driver.findElement(By.xpath(Xpath));
				greenHighLight(Element);

			} else {
				Assert.assertEquals("Element should exist", arg1);
				System.out.println("Element " + arg1 + " exists - expected");
			}
		}
	}

	// Added by Vipin
	@And("^Click \"(.*?)\" from Recent Orders \"(.*?)\"$")
	public void Click_Order_from_RecentOrders(String arg1, String arg2) throws Throwable {
		// Write code here that turns the phrase above into concrete actions
		String Xpath_Filename = ReadProperties.readPropertyByName("OR.properties").getProperty(arg2);
		String Testdata_arg1 = ReadProperties.readPropertyByName("Testdata.properties").getProperty(arg1);
		String Xpath = "" + Xpath_Filename + "'" + Testdata_arg1 + "']";
		List<WebElement> ElementActionPerform = driver.findElements(By.xpath(Xpath));
		// scrollToElementByOffset(ElementActionPerform.get(0), -200);
		CommonFunctions.waitForParticularTime("2000");
		ElementActionPerform.get(0).click();
		// Common_Functions.click(driver, "xpath", Xpath, "click");
		CommonFunctions.waitForParticularTime("5000");
		System.out.println("PO " + Testdata_arg1 + " Selected...");
	}

	// Added by Vipin
	@Given("^Select Account \"(.*?)\" from Dashboard \"(.*?)\"$")
	public void Select_Account_From_Dashboard(int arg1, String arg2) throws Throwable {
		// Write code here that turns the phrase above into concrete actions
		String Xpath = ReadProperties.readPropertyByName("OR.properties").getProperty(arg2);
		List<WebElement> ElementActionPerform = driver.findElements(By.xpath(Xpath));
		CommonFunctions.waitForParticularTime("2000");
		ElementActionPerform.get(arg1 - 1).click();
		// Common_Functions.click(driver, "xpath", Xpath, "click");
		CommonFunctions.waitForParticularTime("5000");
		System.out.println("Account " + arg1 + " Selected...");
	}

	// Added by Vipin
	@And("^press Enter Key for \"(.*?)\"$")
	public void press_enter_key(String arg1) throws Throwable {
		// Write code here that turns the phrase above into concrete actions
		String Xpath = ReadProperties.readPropertyByName("OR.properties").getProperty(arg1);
		driver.findElement(By.xpath(Xpath)).sendKeys(Keys.ENTER);
		CommonFunctions.waitForParticularTime("5000");
		System.out.println("Data entered in " + arg1);
	}

	// Added by Mohit
	@Then("^Match the products in Order Details page$")
	public void match_the_products_in_Order_Details_page() throws Throwable {

		int chkbox_product_count = driver.findElements(By.xpath("//div[contains(text(),'EA')]")).size();
		// int actProdCount=chkbox_product_count-2;
		System.out.println("total checkboxes on the page: " + chkbox_product_count);
		String page_product_count = driver
				.findElement(By.xpath("(//div[contains(@class,'order-details-summary__StyledH2')])[1]")).getText();
		System.out.println("total product count from the page(left bottom corner): " + page_product_count);
		String product_count = String.valueOf(chkbox_product_count);
		System.out.println("total product count: " + product_count);
		if (page_product_count.equalsIgnoreCase(product_count)) {
			System.out.println("product number matches");
		} else {
			System.err.println("product number doesn't match");
			// Assert.assertEquals(Testdata_arg1, Actualmsg);

		}

	}

	// Added to validate button is enabled or disabled

	// Added by sindoora to login to HMC
	@Given("^Login to HMC with user \"(.*?)\" and password \"(.*?)\"$")
	public void login_to_HMC_with_user_and_password(String arg1, String arg2) throws Throwable {
		// Write code here that turns the phrase above into concrete actions
		String ApplicationURL = ReadProperties.readPropertyByName("Testdata.properties").getProperty("HMC_URL");
		String loginUsername = ReadProperties.readPropertyByName("OR.properties").getProperty("HMC_Username");
		String loginUserPassword = ReadProperties.readPropertyByName("OR.properties").getProperty("HMC_Password");
		String LoginSubmit = ReadProperties.readPropertyByName("OR.properties").getProperty("HMC_Login");
		String userid1 = ReadProperties.readPropertyByName("Testdata.properties").getProperty("HMC_User");
		String password1 = ReadProperties.readPropertyByName("Testdata.properties").getProperty("HMC_Password");

		System.out.println(
				"HMC URL:: " + ReadProperties.readPropertyByName("Testdata.properties").getProperty("HMC_URL"));
		driver.get(ApplicationURL);
		ReadProperties.readPropertyByName("Config.properties").getProperty("browser");
		CommonFunctions.enterText(driver, "xpath", loginUsername, userid1);
		CommonFunctions.waitForParticularTime("3000");
		CommonFunctions.enterText(driver, "xpath", loginUserPassword, password1);
		CommonFunctions.waitForParticularTime("5000");
		CommonFunctions.click(driver, "xpath", LoginSubmit, "click");
	}

	// Validate attribute value
	@Given("^Validate attribute value \"(.*?)\" on \"(.*?)\"$")
	public void validate_attribute_value_on(String arg1, String arg2) throws Throwable {
		// Write code here that turns the phrase above into concrete actions
		String XpathHeader = ReadProperties.readPropertyByName("OR.properties").getProperty(arg2);
		String Testdata_arg1 = ReadProperties.readPropertyByName("Testdata.properties").getProperty(arg1);
		CommonFunctions.waitForParticularTime("2000");
		WebElement Element = driver.findElement(By.xpath(XpathHeader));

		greenHighLight(Element);
		String Actualmsg = Element.getAttribute("value");
		System.out.println("Text present:" + Actualmsg);

		if (Actualmsg.contains(Testdata_arg1)) {
			System.out.println("Text Validation Passed: " + Actualmsg);
		} else {
			System.err.println("Text Validation Failed" + Actualmsg);
			Assert.assertEquals(Testdata_arg1, Actualmsg);

		}
	}

	@When("^Mouse hover on \"(.*?)\" and validate \"(.*?)\" on \"(.*?)\"$")
	public void mouse_hover_on_and_validate_on(String arg1, String arg2, String arg3) throws Throwable {
		// Write code here that turns the phrase above into concrete actions
		String StrElement1 = ReadProperties.readPropertyByName("OR.properties").getProperty(arg1);
		String StrElement2 = ReadProperties.readPropertyByName("Testdata.properties").getProperty(arg2);
		String StrElement3 = ReadProperties.readPropertyByName("OR.properties").getProperty(arg3);
		WebElement element1 = driver.findElement(By.xpath(StrElement1));

		Actions action = new Actions(driver);
		action.moveToElement(element1).clickAndHold(element1).build().perform();
		WebElement element2 = driver.findElement(By.xpath(StrElement3));
		String text = element2.getText();
		System.out.println("ContarctText: " + text);
		Assert.assertEquals(StrElement2, text);
		System.out.println("Tooltip Validation passed");
		CommonFunctions.waitForParticularTime("5000");
	}

	@When("^Mouse hover on element \"(.*?)\"$")
	public void mouse_hover_on_element(String arg1) throws Throwable {
		// Write code here that turns the phrase above into concrete actions
		String StrElement1 = ReadProperties.readPropertyByName("OR.properties").getProperty(arg1);
		WebElement element1 = driver.findElement(By.xpath(StrElement1));

		Actions action = new Actions(driver);
		action.moveToElement(element1).build().perform();

		CommonFunctions.waitForParticularTime("3000");
	}

	@Given("^Search \"(.*?)\" click \"(.*?)\" and expand \"(.*?)\" on chooose account modal$")
	public void search_click_and_expand_on_chooose_account_modal(String arg1, String arg2, String arg3)
			throws Throwable {
		// Write code here that turns the phrase above into concrete actions
		String Xpath_FacilityName = ReadProperties.readPropertyByName("OR.properties").getProperty(arg2);
		String Xpath_Expandbtn = ReadProperties.readPropertyByName("OR.properties").getProperty(arg3);
		String Testdata_Facilityname = ReadProperties.readPropertyByName("Testdata.properties").getProperty(arg1);
		String Xpath_facilitydropdown = "" + Xpath_FacilityName + "'" + Testdata_Facilityname + "')]" + Xpath_Expandbtn
				+ "";

		CommonFunctions.click(driver, "xpath", Xpath_facilitydropdown, "click");
		CommonFunctions.waitForParticularTime("2000");
		CommonFunctions.scrollToPageTop();
		System.out.println("Facility Selected...");
	}

	@Given("^Get the count of \"(.*?)\"$")
	public void get_the_count_of(String arg1) throws Throwable {
		// Write code here that turns the phrase above into concrete actions
		String Xpath_FacilityName = ReadProperties.readPropertyByName("OR.properties").getProperty(arg1);

		int ProdTile = driver.findElements(By.xpath(Xpath_FacilityName)).size();
		// int actProdCount=chkbox_product_count-2;
		String ProdTile_count = String.valueOf(ProdTile);
		System.out.println("total tiles on the page: " + ProdTile_count);
	}

	@When("^Compare \"(.*?)\" and validate$")
	public void compare_and_validate(String arg1) throws Throwable {
		// Write code here that turns the phrase above into concrete actions
		String Xpath_FacilityName = ReadProperties.readPropertyByName("OR.properties").getProperty(arg1);

		int Acc2_ProdTile = driver.findElements(By.xpath(Xpath_FacilityName)).size();
		String Acc2_ProdTile_Count = String.valueOf(Acc2_ProdTile);
		System.out.println("total tiles on the page: " + Acc2_ProdTile);
		if (!(Acc2_ProdTile_Count.equalsIgnoreCase(ProdTile_count))) {
			System.out.println("Sort validation passed");
		} else {
			System.err.println("product tiles matched");
		}
	}

	// Alphabetical Sort
	@When("^Get \"(.*?)\" and click on \"(.*?)\" and select \"(.*?)\" and validate$")
	public void get_and_click_on_and_select_and_validate(String arg1, String arg2, String arg3) throws Throwable {
		// Write code here that turns the phrase above into concrete actions
		String Xpath_FacilityName = ReadProperties.readPropertyByName("OR.properties").getProperty(arg1);
		String SortBy = ReadProperties.readPropertyByName("OR.properties").getProperty(arg2);
		String Decending = ReadProperties.readPropertyByName("OR.properties").getProperty(arg3);
		ArrayList<String> obtainedList = new ArrayList<>();
		List<WebElement> elementList = driver.findElements(By.xpath(Xpath_FacilityName));
		for (WebElement we : elementList) {
			obtainedList.add(we.getText());
			System.out.println("firt" + obtainedList);
		}
		CommonFunctions.waitForParticularTime("5000");
		CommonFunctions.click(driver, "xpath", SortBy, "click");
		CommonFunctions.waitForParticularTime("5000");
		CommonFunctions.click(driver, "xpath", Decending, "click");
		CommonFunctions.waitForParticularTime("5000");

		ArrayList<String> sortedList = new ArrayList<>();
		List<WebElement> AfetsortList = driver.findElements(By.xpath(Xpath_FacilityName));

		for (WebElement s : AfetsortList) {
			sortedList.add(s.getText());
			System.out.println("sortedlist" + sortedList);
		}
		Collections.sort(sortedList);
		if (!(sortedList.equals(obtainedList))) {
			System.out.println("sort validation passed");
		} else {
			System.out.println("sort validation failed");
		}
	}

	// Added by sindoora to validate icon color
	@When("^Validate if \"(.*?)\" icon color is \"(.*?)\"$")
	public void validate_if_icon_color_is(String arg1, String arg2) throws Throwable {
		// Write code here that turns the phrase above into concrete actions
		String Exclamation = ReadProperties.readPropertyByName("OR.properties").getProperty(arg1);
		String TD_BackColor = ReadProperties.readPropertyByName("Testdata.properties").getProperty(arg2);

		String color = driver.findElement(By.xpath(Exclamation)).getCssValue("background-color");

		System.out.println("Background color:" + color);
		String hex = Color.fromString(color).asHex();
		System.out.println("Hexadecimal value" + hex);

		if (TD_BackColor.equalsIgnoreCase(hex)) {
			System.out.println("Background color validation passed");
		}

		else {
			System.err.println("Background color validation failed");

		}
	}

	@Given("^Set dynamic text on textfield \"(.*?)\"$")
	public String set_dynamic_text(String arg1) throws Throwable {

		String Xpath = ReadProperties.readPropertyByName("OR.properties").getProperty(arg1);

		String timeStamp = new java.text.SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
		String temp = "Auto" + timeStamp;
		PONumber = temp.replace(".", "");
		String txtPONumber = PONumber;
		System.out.println("PO number " + PONumber);
		System.out.println("Xpath " + Xpath);

		WebElement textelement = driver.findElement(By.xpath(Xpath));
		textelement.click();
		textelement.sendKeys(txtPONumber);

		CommonFunctions.waitForParticularTime("2000");

		return PONumber;
	}

	// Scroll to the product By.id for More generic alternatives
	@When("^Scroll to \"(.*?)\"$")
	public void scroll_to(String arg1) throws Throwable {
		// Write code here that turns the phrase above into concrete actions
		String Xpath_facilitydropdown = ReadProperties.readPropertyByName("Testdata.properties").getProperty(arg1);
		CommonFunctions.waitForParticularTime("2000");
		WebElement e = driver.findElement(By.id(Xpath_facilitydropdown));
		// This will scroll the page till the element is found
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView();", e);
		CommonFunctions.waitForParticularTime("1000");
		System.out.println("Scrollover...");
	}

	// Login with data table use for Roles and restrictions temporary
	@Given("^Login with Roles credentials username \"(.*?)\" and password \"(.*?)\"$")
	public void login_with_Roles_credentials_username_and_password(String arg1, String arg2) throws Throwable {
		// Write code here that turns the phrase above into concrete actions
		String ApplicationURL = ReadProperties.readPropertyByName("Testdata.properties").getProperty("browserURL");
		String loginUsername = ReadProperties.readPropertyByName("OR.properties").getProperty("Username");
		String loginUserPassword = ReadProperties.readPropertyByName("OR.properties").getProperty("UserPassword");
		String LoginSubmit = ReadProperties.readPropertyByName("OR.properties").getProperty("Login");
		System.out.println(
				"Browser Name:: " + ReadProperties.readPropertyByName("Testdata.properties").getProperty("browserURL"));
		driver.get(ApplicationURL);

		String browser = ReadProperties.readPropertyByName("Config.properties").getProperty("browser");
		switch (browser) {
		case "chrome":
			CommonFunctions.enterText(driver, "xpath", loginUsername, arg1);
			CommonFunctions.waitForParticularTime("3000");
			CommonFunctions.enterText(driver, "xpath", loginUserPassword, arg2);
			CommonFunctions.waitForParticularTime("5000");
			CommonFunctions.click(driver, "xpath", LoginSubmit, "click");
			break;
		case "ie":
			if (driver.findElements(By.xpath("//img[@alt='The Dashboard']")).size() != 0) {
				System.out.println("Element is Present");
				driver.findElement(By.xpath("//img[@alt='The Dashboard']")).click();
			} else {
				System.out.println("The Dashboard is Absent");
				CommonFunctions.enterText(driver, "xpath", loginUsername, arg1);
				// Common_Functions.waitForParticularTime("5000");
				CommonFunctions.enterText(driver, "xpath", loginUserPassword, arg2);
				CommonFunctions.waitForParticularTime("5000");
				CommonFunctions.click(driver, "xpath", LoginSubmit, "click");
			}
			break;
		}

	}

	// Refresh the page
	@Given("^Page Refresh$")
	public void Page_Refresh() throws Throwable {

		driver.navigate().refresh();
		CommonFunctions.waitForParticularTime("5000");

	}

	// Move to element and validate if element exists
	@When("^Move to \"(.*?)\" and then validate \"(.*?)\" Exists \"(.*?)\"$")
	public void move_to_and_then_validate_Exists(String arg1, String arg2, String arg3) throws Throwable {
		// Write code here that turns the phrase above into concrete actions
		// Write code here that turns the phrase above into concrete actions
		String StrElement1 = ReadProperties.readPropertyByName("OR.properties").getProperty(arg1);
		String StrElement2 = ReadProperties.readPropertyByName("OR.properties").getProperty(arg2);

		WebElement element1 = driver.findElement(By.xpath(StrElement1));

		Actions action = new Actions(driver);
		action.moveToElement(element1).clickAndHold(element1).build().perform();
		CommonFunctions.waitForParticularTime("4000");
		if (arg3.equalsIgnoreCase("no")) {
			// System.out.println("Checking if element "+arg1+" does not
			// exist");

			if (driver.findElements(By.xpath(StrElement2)).size() > 0) {

				System.out.println("Element " + arg2 + " exists - Not expected");
				Assert.assertEquals("Element should not exist", arg1);
				WebElement Element = driver.findElement(By.xpath(StrElement2));
				greenHighLight(Element);

			}
			System.out.println("Element " + arg2 + " does not exists - expected");
		}

		if (arg3.equalsIgnoreCase("yes")) {
			// System.out.println("Checking if element does exist");

			if (driver.findElements(By.xpath(StrElement2)).size() > 0) {

				System.out.println("Element " + arg2 + " exists - expected");
				WebElement Element = driver.findElement(By.xpath(StrElement2));
				greenHighLight(Element);
			} else {
				Assert.assertEquals("Element should exist", arg2);
				System.out.println("Element " + arg2 + " does not exists - not expected");
			}
		}
	}

	@Then("^Verify check box \"(.*?)\" is selected \"(.*?)\"$")
	public void verify_check_box_is_selected(String arg1, String arg2) throws Throwable {
		// Write code here that turns the phrase above into concrete actions
		String Xpath = ReadProperties.readPropertyByName("OR.properties").getProperty(arg1);

		if (Xpath == null) {
			String data = ReadProperties.readPropertyByName("Testdata.properties").getProperty(arg1);
			Xpath = "(//div[contains(text(),'" + data + "')]/preceding::label[1][contains(@class,'Checkbox')])[1]";
		}

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		wait.until(ExpectedConditions.numberOfElementsToBe(By.xpath(Xpath), 1));

		WebElement Element = driver.findElement(By.xpath(Xpath));

		greenHighLight(Element);

		// the original Xpath for Checkbox should be a Label
		String Xpath1 = Xpath + "/preceding-sibling::input[1]";

		if (arg2.equalsIgnoreCase("yes")) {

			if (driver.findElement(By.xpath(Xpath1)).isSelected()) {
				System.out.println("Checkbox " + arg1 + " is selected");
			} else {
				System.out.println("Checkbox " + arg1 + " is not selected");
				Assert.assertEquals("Checkbox " + arg1 + " should be selected",
						"Checkbox not selected. xpath -> " + Xpath);
			}
		} else if (arg2.equalsIgnoreCase("no")) {

			if (!(driver.findElement(By.xpath(Xpath1)).isSelected())) {
				System.out.println("Checkbox " + arg1 + " is not selected");
			} else {
				System.out.println("Checkbox " + arg1 + " is selected");
				Assert.assertEquals("Checkbox " + arg1 + " should not be selected",
						"Checkbox is selected. xpath -> " + Xpath);
			}

		}

	}

	@Then("^Validate if number of elements \"(.*?)\" equals \"(.*?)\"$")
	public void Validate_number_of_elements(String arg1, String arg2) throws Throwable {

		String data = ReadProperties.readPropertyByName("Testdata.properties").getProperty(arg2);
		String Xpath = ReadProperties.readPropertyByName("OR.properties").getProperty(arg1);

		List<WebElement> elements = driver.findElements(By.xpath(Xpath));
		int count = elements.size();
		System.out.println("Elements count -" + count);

		if (Integer.parseInt(data) == count)
			System.out.println("Elements count -" + count + " matched with expected value -" + data);
		else {
			System.out.println("Elements count -" + count + " did not match with expected value -" + data);
			Assert.assertEquals("expected value -" + data, "Elements count -" + count);
		}

	}

	@Then("^Verify filtered results RowElement \"(.*?)\" Element to Verify \"(.*?)\" with text \"(.*?)\"$")
	public void verify_filtered_results_RowElement_Element_to_Verify_with_text(String arg1, String arg2, String arg3)
			throws Throwable {

		String Xpath_RowElement = ReadProperties.readPropertyByName("OR.properties").getProperty(arg1);
		String Xpath_Element = ReadProperties.readPropertyByName("OR.properties").getProperty(arg2);
		String ElementText = ReadProperties.readPropertyByName("Testdata.properties").getProperty(arg3);

		if (!(arg3.equalsIgnoreCase("na")))
			Xpath_Element = Xpath_Element + "[contains(text(),'" + ElementText + "')]";

		// System.out.println(Xpath_Element);

		List<WebElement> RowElements = driver.findElements(By.xpath(Xpath_RowElement));
		int RowElementsCnt = RowElements.size();

		List<WebElement> Elements = driver.findElements(By.xpath(Xpath_Element));
		int ElementsCnt = Elements.size();

		if (RowElementsCnt == ElementsCnt) {

			for (int i = 1; i <= ElementsCnt; i++) {

				String Xpath_Element1 = "(" + Xpath_Element + ")[" + i + "]";
				WebElement Element = driver.findElement(By.xpath(Xpath_Element1));

				greenHighLight(Element);

			}
			System.out.println("Filter for element " + arg2 + "-" + ElementText + " applied successfully. Occurence - "
					+ ElementsCnt);
		} else
			Assert.assertEquals("Filter should be applied",
					"RowElement count " + RowElementsCnt + " and Element count " + ElementsCnt + " doesn't match");

	}

	@Then("^Validate if file type \"(.*?)\" is downloaded at \"(.*?)\"$")
	public void validate_if_file_type_is_downloaded_at(String arg1, String arg2) throws Throwable {
		// Write code here that turns the phrase above into concrete actions
		String Filepath = ReadProperties.readPropertyByName("Testdata.properties").getProperty(arg2);
		String Filetype = ReadProperties.readPropertyByName("Testdata.properties").getProperty(arg1);
		Assert.assertTrue(isFileDownloaded_Ext(Filepath, Filetype));
		CommonFunctions.waitForParticularTime("3000");
		System.out.println("file download success");
		CommonFunctions.waitForParticularTime("3000");
		Assert.assertTrue(isFileDeleted(Filepath, Filetype));
		// File file = new File(Filepath);
		// file.delete();
		// if (file.delete()) {
		// System.out.println("Success: " + file.getName().contains(Filetype) +
		// " is deleted!");
		// } else {
		// System.out.println("Failed: Delete operation is failed.");
		// }
	}

	@And("^Select \"(.*?)\" Checkbox$")
	public void select_Checkbox(String arg1) throws Throwable {

		String Xpath = ReadProperties.readPropertyByName("OR.properties").getProperty(arg1);
		String data = ReadProperties.readPropertyByName("Testdata.properties").getProperty(arg1);

		if (Xpath == null) {

			if (data == null) {
				Xpath = "(//label[contains(@class,'Checkbox')])[" + arg1 + "]";
			} else
				Xpath = "(//div[contains(text(),'" + data + "')]/preceding::label[1][contains(@class,'Checkbox')])[1]";

		}

		if (driver.findElements(By.xpath(Xpath)).size() > 0) {

			// Common_Functions.click(driver, "xpath", Xpath, "click");
			WebElement e = driver.findElement(By.xpath(Xpath));
			JavascriptExecutor js = (JavascriptExecutor) driver;
			// js.executeScript("arguments[0].scrollIntoView();", e);
			js.executeScript("arguments[0].click()", e);
			System.out.println("Checkbox " + arg1 + " Selected..." + Xpath);

		} else {
			System.out.println("Checkbox " + arg1 + " not Selected. Xpath - " + Xpath);
			Assert.assertEquals("Checkbox should be clicked", data);
		}

		// System.out.println("Checkbox " + data + " Selected...");
	}

	public static boolean isFileDeleted(String dirPath, String ext) {

		boolean flag = false;
		File dir = new File(dirPath);
		File[] files = dir.listFiles();
		if (files == null || files.length == 0) {
			flag = false;
		}

		for (int i = 1; i < files.length; i++) {
			if (files[i].getName().contains(ext)) {
				files[i].getName();
				System.out.println();
				files[i].delete();
				flag = true;
			}
		}
		return flag;
	}

	private boolean isFileDownloaded_Ext(String dirPath, String ext) {
		boolean flag = false;
		File dir = new File(dirPath);
		File[] files = dir.listFiles();
		if (files == null || files.length == 0) {
			flag = false;
		}

		for (int i = 1; i < files.length; i++) {
			if (files[i].getName().contains(ext)) {
				flag = true;
			}
		}
		return flag;
	}

	@When("^Wait until element \"(.*?)\" present for \"(.*?)\" seconds$")
	public void wait_until_element_present_for_seconds(String arg1, int arg2) throws Throwable {
		// Write code here that turns the phrase above into concrete actions
		String Xpath = ReadProperties.readPropertyByName("OR.properties").getProperty(arg1);
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(arg2));
		wait.until(ExpectedConditions.numberOfElementsToBe(By.xpath(Xpath), 1));
	}

	@When("^Number Spinner element \"(.*?)\"- \"(.*?)\" by value \"(.*?)\"$")
	public String number_spinner_element_by_value(String arg1, String arg2, int arg3) throws Throwable {

		String Xpath = ReadProperties.readPropertyByName("OR.properties").getProperty(arg1);
		String existingVal = driver.findElement(By.xpath(Xpath)).getAttribute("value");

		int value, newValue;
		numberSpinnerValue = Integer.toString(arg3);

		if (arg2.equalsIgnoreCase("increment")) {

			value = Integer.parseInt(existingVal);
			newValue = value + arg3;
			numberSpinnerValue = Integer.toString(newValue);

		} else if (arg2.equalsIgnoreCase("decrement")) {

			value = Integer.parseInt(existingVal);
			newValue = value - arg3;
			numberSpinnerValue = Integer.toString(newValue);

		}
		driver.findElement(By.xpath(Xpath)).clear();
		driver.findElement(By.xpath(Xpath)).sendKeys(numberSpinnerValue);
		System.out.println("Number Spinner updated to -" + numberSpinnerValue);

		return numberSpinnerValue;
	}

	// Added by Vipin
	@When("^Validate element \"(.*?)\" with text \"(.*?)\" Exists \"(.*?)\"$")
	public void validate_element_with_text_exists(String arg1, String arg2, String arg3) throws Throwable {
		// Write code here that turns the phrase above into concrete actions
		String Xpath;
		String Testdata;
		StringBuilder sb = new StringBuilder("invoice_");

		if (arg2.equalsIgnoreCase("yesterdays")) {
			DateFormat dtf = new SimpleDateFormat("MM/dd/yyyy");
			Testdata = dtf.format(yesterday()).toString();
			System.out.println("yesterday's date : " + Testdata);
		}

		else if (arg2.equalsIgnoreCase("todays")) {
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MM/dd/yyyy");
			LocalDateTime now = LocalDateTime.now();
			Testdata = dtf.format(now).toString();
			System.out.println("today's date : " + Testdata);
		} else {
			Testdata = ReadProperties.readPropertyByName("Testdata.properties").getProperty(arg2);
			System.out.println("Testdata : " + Testdata);
		}

		if (arg1.contains("ReceiveInvoiceDetails_ModelAcceptInvoices")) {
			sb.append(Testdata);
			Xpath = "//*[contains(@id,'" + sb.toString() + "')]";
		} else if (arg1.contains("ReceiveToteDetails_ModelAcceptInvoices")) {
			Xpath = ReadProperties.readPropertyByName("OR.properties").getProperty(arg1);
			Xpath = Xpath + "/*[contains(text(),'" + Testdata + "')]";
		} else {
			Xpath = ReadProperties.readPropertyByName("OR.properties").getProperty(arg1);
			Xpath = Xpath + "[contains(text(),'" + Testdata + "')]";
		}

		// ReceiveToteDetails_ModelAcceptInvoices

		/*
		 * if (arg3.equalsIgnoreCase("no")) { // System.out.println(
		 * "Checking if element "+arg1+" does not // exist");
		 * 
		 * if (driver.findElements(By.xpath(Xpath)).size() > 0) {
		 * 
		 * System.out.println("Element " + arg1 + " with data " + arg2 +
		 * "exists - Not expected"); Assert.assertEquals( "Element should not exist",
		 * arg1); WebElement Element = driver.findElement(By.xpath(Xpath));
		 * 
		 * if (driver instanceof JavascriptExecutor) { ((JavascriptExecutor)
		 * driver).executeScript("arguments[0].style.border='2px solid green'",
		 * Element); } } System.out.println("Element " + arg1 + " with data " + arg2 +
		 * "does not exists - expected"); }
		 * 
		 * if (arg3.equalsIgnoreCase("yes")) { System.out.println(
		 * "Checking if element does exist");
		 * 
		 * if (driver.findElements(By.xpath(Xpath)).size() > 0) {
		 * 
		 * System.out.println("Element " + arg1 + "with data " + arg2 +
		 * " exists - expected"); List<WebElement> Element =
		 * driver.findElements(By.xpath(Xpath));
		 * 
		 * if (driver instanceof JavascriptExecutor) { ((JavascriptExecutor)
		 * driver).executeScript("arguments[0].style.border='2px solid green'",
		 * Element.get(0)); }
		 * 
		 * } else { System.out.println( "Element " + arg1 + "with data " + arg2 +
		 * "does not exist -Not expected. Xpath - " + Xpath);
		 * Assert.assertEquals("Element should exist", arg1);
		 * 
		 * } }
		 */
		/* Changes by Macdaline */

		switch (arg3.toLowerCase()) {
		case "yes":

			WebElement Element = driver.findElement(By.xpath(Xpath));
			greenHighLight(Element);

			try {

				if (Element.isDisplayed())

				{
					System.out.println("Element " + arg1 + "with data " + arg2 + " exists - expected");
				}

			} catch (Exception e) {
				System.out.println(
						"Element " + arg1 + "with data " + arg2 + "does not exist -Not expected. Xpath - " + Xpath);
				e.getMessage().toString();
			}
			break;
		case "no":
			try {
				if (driver.findElement(By.xpath(Xpath)).isDisplayed() == false) {
					System.out.println("Element " + arg1 + " with data " + arg2 + "does not exists - expected");
				}
			} catch (Exception e) {
				System.out.println(
						"Element " + arg1 + "with data " + arg2 + "does exist -Not expected. Xpath - " + Xpath);
				e.getMessage().toString();
			}
			break;
		default:
			System.out.println("Check Exists Value.Exists value should be either be Yes or No");
		}
	}

	@Given("^Open Equallevel \"([^\"]*)\" and enter Url \"([^\"]*)\" sharedsecret \"([^\"]*)\" and enter ABC credentials \"([^\"]*)\" and passsword \"([^\"]*)\"$")
	public void open_Equallevel_and_enter_Url_sharedsecret_and_enter_ABC_credentials_and_passsword(String EqualLevelURL,
			String EQlevelUrl, String secretid, String userid, String password) throws Throwable {
		// Write code here that turns the phrase above into concrete actions
		String EquallevelURL = ReadProperties.readPropertyByName("Testdata.properties").getProperty("EqualLevelURL");
		String URL = ReadProperties.readPropertyByName("OR.properties").getProperty("EnterURL");
		String Sharedsecret = ReadProperties.readPropertyByName("OR.properties").getProperty("Sharedsecret");
		String loginUsername = ReadProperties.readPropertyByName("OR.properties").getProperty("POR_Username");
		String loginUserPassword = ReadProperties.readPropertyByName("OR.properties").getProperty("POR_Password");
		String LoginSubmit = ReadProperties.readPropertyByName("OR.properties").getProperty("Login");
		String Secretid = ReadProperties.readPropertyByName("Testdata.properties").getProperty(secretid);
		String EqLUrl = ReadProperties.readPropertyByName("Testdata.properties").getProperty(EQlevelUrl);
		String userid1 = ReadProperties.readPropertyByName("Testdata.properties").getProperty(userid);
		String password1 = ReadProperties.readPropertyByName("Testdata.properties").getProperty(password);

		System.out.println(
				"HS URL:: " + ReadProperties.readPropertyByName("Testdata.properties").getProperty("EqualLevelURL"));
		driver.get(EquallevelURL);

		ReadProperties.readPropertyByName("Config.properties").getProperty("browser");

		CommonFunctions.enterText(driver, "xpath", URL, EqLUrl);
		// Common_Functions.waitForParticularTime("5000");
		CommonFunctions.enterText(driver, "xpath", Sharedsecret, Secretid);
		CommonFunctions.waitForParticularTime("5000");
		String beforeclick = driver.getCurrentUrl();
		System.out.println("Punchout URL:" + beforeclick);

		CommonFunctions.click(driver, "xpath", LoginSubmit, "click");
		CommonFunctions.waitForParticularTime("10000");
		swtichToWindow(2);
		System.out.println("punchout page:" + driver.getCurrentUrl());

		CommonFunctions.waitForParticularTime("5000");
		driver.switchTo().frame("punchout_frame");
		System.out.println("switchedframe");
		CommonFunctions.scrollToPageBottom();
		CommonFunctions.enterText(driver, "xpath", loginUsername, userid1);
		CommonFunctions.enterText(driver, "xpath", loginUserPassword, password1);
		CommonFunctions.click(driver, "xpath", LoginSubmit, "click");
		CommonFunctions.waitForParticularTime("6000");
	}

	public void swtichToWindow(int index) {

		String windowID = null;
		Set<String> windowIDs = driver.getWindowHandles();
		Iterator<String> iter = windowIDs.iterator();

		for (int i = 1; i <= index; i++) {
			windowID = iter.next();
		}
		driver.switchTo().window(windowID);
	}

	@When("^Get tote id \"([^\"]*)\" click \"([^\"]*)\" and validate \"([^\"]*)\"$")
	public void get_tote_id_click_and_validate(String arg1, String arg2, String arg3) throws Throwable {
		// Write code here that turns the phrase above into concrete actions
		String CurrentTote = ReadProperties.readPropertyByName("OR.properties").getProperty(arg1);
		String NextBtn = ReadProperties.readPropertyByName("OR.properties").getProperty(arg2);
		String NextTote = ReadProperties.readPropertyByName("OR.properties").getProperty(arg3);
		CommonFunctions.waitForParticularTime("2000");
		WebElement Element = driver.findElement(By.xpath(CurrentTote));
		driver.findElement(By.xpath(NextTote));
		greenHighLight(Element);

		String Actualtote = Element.getText();
		System.out.println("Current Tote ID:" + Actualtote);
		CommonFunctions.click(driver, "xpath", NextBtn, "click");
		String Nexttoteid = Element.getText();
		System.out.println("Next Tote ID:" + Nexttoteid);

		if (!(Actualtote.contains(Nexttoteid))) {
			System.out.println("Tote Id's are different: " + Actualtote + Nexttoteid);
		} else {
			System.err.println("Tote Id's are same" + Actualtote + Nexttoteid);
			// System.out.println("Tote Id's are same" + Actualtote
			// +Nexttoteid);
			Assert.assertEquals(Nexttoteid, Actualtote);

		}
	}

	// public static void setDateRange1(WebDriver driver, Properties OR, String
	// start, String end)
	// throws TimeoutException, InterruptedException {

	@When("^Select the start date from \"([^\"]*)\" and the end date to \"([^\"]*)\"$")
	public void select_the_start_date_from_and_the_end_date_to(String arg1, String arg2) throws Throwable {

		String stDate = ReadProperties.readPropertyByName("Testdata.properties").getProperty(arg1);
		String startdate[] = stDate.split("/");
		System.out.println("Start date[0]>>>>" + startdate[0]);
		System.out.println("Start date[1]>>>>" + startdate[1]);
		System.out.println("Start date[2]>>>>" + startdate[2]);

		String st_date_year_xpath = ReadProperties.readPropertyByName("OR.properties").getProperty("yearlist");

		if (driver.findElements(By.xpath("//select[@name='year']")).size() > 0) {
			st_date_year_xpath = "//select[@name='year']";
		}

		List<WebElement> yearlist = driver.findElements(By.xpath(st_date_year_xpath));

		Select year = new Select(yearlist.get(0));
		year.selectByVisibleText(startdate[2]);

		String st_date_month_xpath = ReadProperties.readPropertyByName("OR.properties").getProperty("monthlist");

		if (driver.findElements(By.xpath("//select[@name='month']")).size() > 0) {
			st_date_month_xpath = "//select[@name='month']";
		}

		List<WebElement> monthlist = driver.findElements(By.xpath(st_date_month_xpath));

		Select month = new Select(monthlist.get(0));
		month.selectByIndex(Integer.parseInt(startdate[0]) - 1);

		String st_date_day_xpath = ReadProperties.readPropertyByName("OR.properties").getProperty("CalenderLeft");

		if (driver.findElements(By.xpath(
				"//div[contains(@class,'daterangeField__monthLabels')][text()='FROM']/parent::div//div[contains(@class,'DayPicker-Day') and not(contains(@class,'disabled'))]"))
				.size() > 0) {
			st_date_day_xpath = "//div[contains(@class,'daterangeField__monthLabels')][text()='FROM']/parent::div//div[contains(@class,'DayPicker-Day') and not(contains(@class,'disabled'))]";
		}

		List<WebElement> data = driver.findElements(By.xpath(st_date_day_xpath));

		System.out.println("print the data size>>>" + data.size());
		for (int i = 0; i < data.size(); i++) {

			if (data.get(i).getText().equalsIgnoreCase(startdate[1])) {
				System.out.println("print the extracted date>>>" + data.get(i).getText());
				data.get(i).click();
				break;
			}
		}

		String endDate = ReadProperties.readPropertyByName("Testdata.properties").getProperty(arg2);

		String enddate[] = endDate.split("/");

		String end_date_year_xpath = ReadProperties.readPropertyByName("OR.properties").getProperty("yearlist");

		if (driver.findElements(By.xpath("//select[@name='year']")).size() > 0) {
			end_date_year_xpath = "//select[@name='year']";
		}

		List<WebElement> yearlist2 = driver.findElements(By.xpath(end_date_year_xpath));
		Select year2 = new Select(yearlist2.get(1));
		year2.selectByVisibleText(enddate[2]);

		String end_date_month_xpath = ReadProperties.readPropertyByName("OR.properties").getProperty("monthlist");

		if (driver.findElements(By.xpath("//select[@name='month']")).size() > 0) {
			end_date_month_xpath = "//select[@name='month']";
		}

		List<WebElement> monthlist2 = driver.findElements(By.xpath(end_date_month_xpath));
		Select month2 = new Select(monthlist2.get(1));
		month2.selectByIndex(Integer.parseInt(enddate[0]) - 1);

		String end_date_day_xpath = ReadProperties.readPropertyByName("OR.properties").getProperty("CalenderRight");

		if (driver.findElements(By.xpath(
				"//div[contains(@class,'daterangeField__monthLabels')][text()='TO']/parent::div//div[contains(@class,'DayPicker-Day') and not(contains(@class,'disabled'))]"))
				.size() > 0) {
			end_date_day_xpath = "//div[contains(@class,'daterangeField__monthLabels')][text()='TO']/parent::div//div[contains(@class,'DayPicker-Day') and not(contains(@class,'disabled'))]";
		}

		List<WebElement> data1 = driver.findElements(By.xpath(end_date_day_xpath));
		for (int i = 0; i < data1.size(); i++) {
			if (data1.get(i).getText().equalsIgnoreCase(enddate[1])) {
				data1.get(i).click();
				break;
			}
		}
		// String
		// applybtn_xpath=ReadProperties.readPropertyByName("OR.properties").getProperty("Applybtn1");
		// driver.findElement(By.xpath(applybtn_xpath)).click();
	}

	@And("^Validate value exist for field \"(.*?)\"$")
	public void Validate_value_exist_for_field(String arg1) throws Throwable {

		String Xpath = ReadProperties.readPropertyByName("OR.properties").getProperty(arg1);

		if (driver.findElements(By.xpath(Xpath)).size() > 0) {

			WebElement e = driver.findElement(By.xpath(Xpath));

			greenHighLight(e);

			String txt = e.getText();

			if (txt.isEmpty()) {

				System.out.println("Field " + arg1 + " found with null value. text -" + txt + ". Xpath - " + Xpath);
				Assert.assertEquals("Field with value should exist", arg1);
			} else {
				System.out.println("Field " + arg1 + " found on page with text - " + txt);
			}

		} else {
			System.out.println("Field " + arg1 + " not found on page. Xpath - " + Xpath);
			Assert.assertEquals("Field with value should exist", arg1);
		}

	}

	/**********
	 * added new code for Zephyr - 19318 by Macdaline Part 1 and Part2
	 *************************************/
	@Then("^the user validates \"([^\"]*)\" is equal to \"([^\"]*)\" for \"([^\"]*)\" entry$")
	public void the_User_Validates_A_Value_On_Field_For_Entry(String field1, String field2, String entry)
			throws Throwable {

		List<WebElement> elementToteIdQty = toteIdQty(field1, entry);
		List<WebElement> elementReceiptQty = receiptQty(field2, entry);

		switch (entry.toLowerCase()) {
		case "all":

			for (int j = 0; j < elementReceiptQty.size(); j++) {
				Thread.sleep(2000);
				try {

					Assert.assertEquals(elementToteIdQty.get(j).getText(), elementReceiptQty.get(j).getText());
					System.out.println(
							"Validation Passed.Value of Field " + field1 + " is " + elementToteIdQty.get(j).getText()
									+ "is equal to field " + field2 + "is " + elementReceiptQty.get(j).getText());
				} catch (Exception e) {
					System.out.println(
							"Validation Failed.Value of Field " + field1 + " is " + elementToteIdQty.get(j).getText()
									+ "is equal to field " + field2 + "is " + elementReceiptQty.get(j).getText());
					Assert.assertEquals(elementToteIdQty.get(j).getText(), elementReceiptQty.get(j).getText());
					e.getMessage().toString();

				}

			}
			break;
		case "sharedproducta":
		case "sharedproductb":
			greenHighLight(elementToteIdQty.get(0));

			greenHighLight(elementReceiptQty.get(0));

			try {

				Assert.assertEquals(elementToteIdQty.get(0).getText(), elementReceiptQty.get(0).getText());
				System.out.println("Validation Passed.Value of Invoice qty " + elementToteIdQty.get(0).getText()
						+ "is equal to ReceiptQty" + elementReceiptQty.get(0).getText());
			} catch (Exception e) {
				System.out.println("Validation Failed.Value of Invoice qty " + elementToteIdQty.get(0).getText()
						+ "is not equal to ReceiptQty" + elementReceiptQty.get(0).getText());
				Assert.assertEquals(elementToteIdQty.get(0).getText(), elementReceiptQty.get(0).getText());
				e.getMessage().toString();

			}
			break;
		default:
			int i = Integer.parseInt(entry);
			try {

				Assert.assertEquals(elementToteIdQty.get(i - 1).getText(), elementReceiptQty.get(i - 1).getText());
				System.out.println(
						"Validation Passed.Value of Field " + field1 + " is " + elementToteIdQty.get(i - 1).getText()
								+ "is equal to field " + field2 + "is " + elementReceiptQty.get(i - 1).getText());
			} catch (Exception e) {
				System.out.println(
						"Validation Failed.Value of Field " + field1 + " is " + elementToteIdQty.get(i - 1).getText()
								+ "is equal to field " + field2 + "is " + elementReceiptQty.get(i - 1).getText());

				Assert.assertEquals(elementToteIdQty.get(i - 1).getText(), elementReceiptQty.get(i - 1).getText());
				e.getMessage().toString();

			}
			break;
		}

		CommonFunctions.waitForParticularTime("2000");

	}

	@And("^enters \"([^\"]*)\" plus \"([^\"]*)\" on field \"([^\"]*)\" for Old \"([^\"]*)\"$")
	public void enter_Value_On_Field_For_Old(String field1, int value, String field2, String productNo) {

		setReceiptQty(0);
		int toteIdQtyValue = 0;
		receiptQty = value;

		List<WebElement> elementToteIdQty = toteIdQty(field1, productNo);
		List<WebElement> elementReceiptQty = receiptQty(field2, productNo);
		toteIdQtyValue = Integer.parseInt(elementToteIdQty.get(0).getText()) + value;

		elementReceiptQty.get(0).clear();
		elementReceiptQty.get(0).sendKeys(String.valueOf(toteIdQtyValue));

	}

	@And("^Validate \"([^\"]*)\" has a link and green check mark$")
	public void validate_Link_And_GreenCheckMark_On_Field(String toteID) {
		String sharedToteId = ReadProperties.readPropertyByName("Testdata.properties").getProperty(toteID);
		String xpathForToteIDField = "*[contains(@class,'fa fa-check tabResultLineEntry__greenCheck_')]/parent::div[contains(@title,'"
				+ sharedToteId + "')]";
		List<WebElement> toteLinkText = driver.findElements(By.linkText(sharedToteId));
		List<WebElement> toteXpath = driver.findElements(By.xpath(xpathForToteIDField));

		for (WebElement e : toteLinkText) {

			if (e.isDisplayed() == false) {
				System.out.println("Link Validation Success.No Link is Present");
				for (WebElement ele : toteXpath) {
					greenHighLight(ele);
					if (ele.isDisplayed()) {
						System.out.println("Green Check mark Validation Success.Green Check mark is Present");
					} else {
						System.out.println("Green Check mark Validation Failed.Green Check mark is not Present");
					}
				}

			} else {
				System.out.println("Validation Failed.Link is Present");
			}

		}

	}

	@Then("^the user validates a quantity is present on field \"([^\"]*)\" for \"([^\"]*)\" entry$")
	public void the_User_Validates_Value_Is_Present_On_Field(String field, String entry)
			throws TimeoutException, InterruptedException {

		String xpathTextbox = ReadProperties.readPropertyByName("OR.properties").getProperty(field);
		List<WebElement> textBoxElements = driver.findElements(By.xpath(xpathTextbox));

		switch (entry.toLowerCase()) {
		case "all":
			for (int j = 0; j < textBoxElements.size(); j++) {

				try {
					if ((textBoxElements.get(j).getAttribute("value")).isEmpty() == false) {
						System.out.println("Validation Success.Value is present on Field " + field);
					}
				} catch (Exception e) {
					System.out.println("Validation Failed.No Value is present on Field " + field);
					e.getMessage().toString();
				}
			}
			break;
		default:
			int i = Integer.parseInt(entry);
			try {
				if (textBoxElements.get(i - 1).getAttribute("value").isEmpty() == false) {
					System.out.println("Validation Success.Value is present on Field " + field);
				}
			} catch (Exception e) {
				System.out.println("Validation Failed.No Value is present on Field " + field);
				e.getMessage().toString();
			}
			break;
		}

		CommonFunctions.waitForParticularTime("2000");
	}

	@And("^Validate status \"([^\"]*)\" on Invoice \"([^\"]*)\" Exists \"([^\"]*)\"$")
	public void validate_Status_Is_Displayed_Against(String status, String value, String exists) {
		String testDataValue = ReadProperties.readPropertyByName("Testdata.properties").getProperty(value);
		StringBuilder sb = new StringBuilder("invoice_");
		sb.append(testDataValue);
		String xpathForStatus = "//span[contains(@id,'" + sb + "')]"
				+ ReadProperties.readPropertyByName("OR.properties").getProperty("ReceiveToteDetails_HistoryStatus");

		String actualStatus = driver.findElement(By.xpath(xpathForStatus)).getText();
		String expectedStatus = ReadProperties.readPropertyByName("Testdata.properties").getProperty(status);
		switch (exists.toLowerCase()) {
		case "yes":
			greenHighLight(driver.findElement(By.xpath(xpathForStatus)));

			try {

				Assert.assertEquals(expectedStatus, actualStatus);
				System.out
						.println("Validation Passed.Status " + actualStatus + " found on page for  - " + testDataValue);
			} catch (Exception e) {
				System.out.println(
						"Validation Failed.Status " + actualStatus + " not found on page for -" + testDataValue);
				Assert.assertEquals(expectedStatus, actualStatus);
				e.getMessage().toString();
			}
			break;
		case "no":
			try {
				Assert.assertNotEquals(expectedStatus, actualStatus);
				System.out.println("Validation Passed.Status " + actualStatus + " should not exist on page for  - "
						+ testDataValue);
			} catch (Exception e) {
				System.out
						.println("Validation Failed.Status " + actualStatus + " found on page for  - " + testDataValue);
				Assert.assertNotEquals(expectedStatus, actualStatus);
			}
			break;
		default:
			System.out.println("Check Exists Value.Exists value should be either be Yes or No");
		}

	}

	@Then("^the user validates \"([^\"]*)\" is present on field \"([^\"]*)\" for \"([^\"]*)\" entry$")
	public void the_User_Validats_Is_Present_On_Field(String value, String field, String entry)
			throws InterruptedException {
		String expectedValue;
		List<WebElement> receiptQtyElement = receiptQty(field, entry);
		List<WebElement> invoiceQtyElement = toteIdQty("ReceiveToteDetails_InvoiceQty", entry);
		if (value.equals("blank")) {
			expectedValue = "";
		} else if (value.equals("TotalReceiptQty")) {
			expectedValue = String.valueOf(Integer.parseInt(invoiceQtyElement.get(0).getText()) + getReceiptQty());
		} else {
			expectedValue = value;
		}

		switch (entry.toLowerCase()) {
		case "sharedproducta":
		case "sharedproductb":
			greenHighLight(receiptQtyElement.get(0));

			try {

				Assert.assertEquals(expectedValue, receiptQtyElement.get(0).getText());
				System.out.println("Validation Passed.Value " + expectedValue + " is equal to "
						+ receiptQtyElement.get(0).getText() + "on field " + field);
			} catch (Exception e) {
				System.out.println("Validation Passed.Value " + expectedValue + " is equal to "
						+ receiptQtyElement.get(0).getText() + "on field " + field);
				Assert.assertEquals(expectedValue, receiptQtyElement.get(0).getText());
				e.getMessage().toString();

			}
			break;
		case "all":
			for (int j = 0; j < receiptQtyElement.size(); j++) {
				greenHighLight(receiptQtyElement.get(j));
			}

			for (int i = 0; i < receiptQtyElement.size(); i++) {

				try {

					Assert.assertEquals(expectedValue, receiptQtyElement.get(i).getText());
					System.out.println("Validation Passed.Value " + expectedValue + " is equal to "
							+ receiptQtyElement.get(i).getText() + "on field " + field);
				} catch (Exception e) {
					System.out.println("Validation Passed.Value " + expectedValue + " is equal to "
							+ receiptQtyElement.get(i).getText() + "on field " + field);
					Assert.assertEquals(expectedValue, receiptQtyElement.get(i).getText());
					e.getMessage().toString();

				}
			}
			break;
		}
	}

	@Then("^the user validates quantity for \"([^\"]*)\" on \"([^\"]*)\" is equal to \"([^\"]*)\" on field Old \"([^\"]*)\"$")
	public void the_User_Validates_Qty_Of_Present_On_Field_Old(String invoice, String entry, String value, String field) {
		String field2 = "ReceiveToteDetails_InvoiceQty";
		List<WebElement> receiptQtyElement = receiptQty(field, entry);
		List<WebElement> toteIdQtyElement = toteIdQty(field2, entry);
		int totalQtyInvoice1 = Integer.parseInt(toteIdQtyElement.get(0).getText()) + 1;
		int totalQtyInvoice2 = getReceiptQty() + 1;
		greenHighLight(receiptQtyElement.get(0));
		greenHighLight(toteIdQtyElement.get(0));
		if (invoice.equals("invoice1")) {

			try {

				Assert.assertEquals(String.valueOf(totalQtyInvoice1), receiptQtyElement.get(0).getText());
				System.out.println("Validation Passed.Value " + totalQtyInvoice1 + " is equal to "
						+ receiptQtyElement.get(0).getText() + "on field " + field);
			} catch (Exception e) {
				System.out.println("Validation Passed.Value " + totalQtyInvoice1 + " is equal to "
						+ receiptQtyElement.get(0).getText() + "on field " + field);
				Assert.assertEquals(String.valueOf(totalQtyInvoice1), receiptQtyElement.get(0).getText());
				e.getMessage().toString();

			}
		} else if (invoice.equals("invoice2")) {
			try {

				Assert.assertEquals(String.valueOf(totalQtyInvoice2), receiptQtyElement.get(0).getText());
				System.out.println("Validation Passed.Value " + String.valueOf(totalQtyInvoice2) + " is equal to "
						+ receiptQtyElement.get(0).getText() + "on field " + field);
			} catch (Exception e) {
				System.out.println("Validation Passed.Value " + String.valueOf(totalQtyInvoice2) + " is equal to "
						+ receiptQtyElement.get(0).getText() + "on field " + field);
				Assert.assertEquals(String.valueOf(totalQtyInvoice2), receiptQtyElement.get(0).getText());
				e.getMessage().toString();

			}
		}
	}

	@And("^the user clears \"([^\"]*)\"$")
	public void the_User_Clears_TextBox(String field1) {
		String xpath_productsearchtxt = ReadProperties.readPropertyByName("OR.properties").getProperty(field1);
		driver.findElement(By.xpath(xpath_productsearchtxt)).clear();
	}

	public List<WebElement> toteIdQty(String field, String entry) {
		String xpathToteIdQty = null;

		switch (entry.toLowerCase()) {

		case "sharedproducta":
		case "sharedproductb":
			StringBuilder sb = new StringBuilder("invoiceLine");
			String testData = ReadProperties.readPropertyByName("Testdata.properties").getProperty(entry);
			sb.append(testData);
			String commonXpath = "//div[@id='" + sb + "']";
			xpathToteIdQty = commonXpath + ReadProperties.readPropertyByName("OR.properties").getProperty(field);

			break;
		default:
			xpathToteIdQty = ReadProperties.readPropertyByName("OR.properties")
					.getProperty("ReceiveToteDetails_ToteIdQty_all");

		}

		return driver.findElements(By.xpath(xpathToteIdQty));
	}

	public List<WebElement> receiptQty(String field, String entry) {
		String xpathReceiptQty = null;

		switch (entry.toLowerCase()) {

		case "sharedproducta":
		case "sharedproductb":
			StringBuilder sb = new StringBuilder("invoiceLine");
			String testData = ReadProperties.readPropertyByName("Testdata.properties").getProperty(entry);
			sb.append(testData);
			String commonXpath = "//div[@id='" + sb + "']";

			xpathReceiptQty = commonXpath + ReadProperties.readPropertyByName("OR.properties").getProperty(field);
			System.out.println(xpathReceiptQty);
			break;
		default:
			xpathReceiptQty = ReadProperties.readPropertyByName("OR.properties")
					.getProperty("ReceiveToteDetails_ReceiptQty");
		}
		return driver.findElements(By.xpath(xpathReceiptQty));

	}

	public void greenHighLight(WebElement ele) {
		if (driver instanceof JavascriptExecutor) {
			((JavascriptExecutor) driver).executeScript("arguments[0].style.border='2px solid green'", ele);
		}
	}

	/**********
	 * End of code for Zephyr - 19318 Part1 and Part2
	 *************************************/
	@And("^clear_textBox \"([^\"]*)\"$")
	public void clear_textBox(String arg1) throws Throwable {
		String Xpath = ReadProperties.readPropertyByName("OR.properties").getProperty(arg1);
		driver.findElement(By.xpath(Xpath)).clear();
	}

	@When("^user validates that the \"([^\"]*)\" matches with \"([^\"]*)\"$")
	public void the_user_validates_that_the_InvoiceQty_matches_with_ReceiptQty(String arg1, String arg2)
			throws Throwable {
		// Write code here that turns the phrase above into concrete actions
		String invoiceQtyXpath = ReadProperties.readPropertyByName("OR.properties").getProperty(arg1);
		String ReceiptQtyXpath = ReadProperties.readPropertyByName("OR.properties").getProperty(arg2);
		String XpathRows = ReadProperties.readPropertyByName("OR.properties").getProperty("ReceiveToteDetails_Rows");
		int cnt, invoiceQty, ReceiptQty;

		if (driver.findElements(By.xpath(XpathRows)).size() > 0) {

			cnt = driver.findElements(By.xpath(XpathRows)).size();

			for (int i = 1; i <= cnt; i++) {

				String sToteQtyXpath = "(" + invoiceQtyXpath + ")[" + (i + 1) + "]";
				System.out.println("sToteQtyXpath " + sToteQtyXpath);

				WebElement wInvoiceQty = driver.findElement(By.xpath(sToteQtyXpath));

				if (driver instanceof JavascriptExecutor) {
					((JavascriptExecutor) driver).executeScript("arguments[0].style.border='2px solid green'",
							wInvoiceQty);
				}

				String sInvoiceQty = wInvoiceQty.getText();
				System.out.println("stoteQty " + sInvoiceQty);
				invoiceQty = Integer.parseInt(sInvoiceQty);
				System.out.println("toteQty " + invoiceQty);

				String sReceiptQtyXpath = "(" + ReceiptQtyXpath + ")[" + (i + 1) + "]";
				System.out.println("ToteQtyXpath " + sReceiptQtyXpath);

				WebElement wReceiptQty = driver.findElement(By.xpath(sReceiptQtyXpath));

				if (driver instanceof JavascriptExecutor) {
					((JavascriptExecutor) driver).executeScript("arguments[0].style.border='2px solid green'",
							wReceiptQty);
				}

				String sReceiptQty = wReceiptQty.getText();
				System.out.println("sReceiptQty " + sReceiptQty);

				if (sReceiptQty.isEmpty()) {
					continue;
				}

				ReceiptQty = Integer.parseInt(sReceiptQty);
				System.out.println("ReceiptQty " + ReceiptQty);

				if (invoiceQty == ReceiptQty) {

					System.out.println("ReceiptQty " + ReceiptQty + " matches with toteQty " + invoiceQty);

				} else {
					System.out.println("ReceiptQty " + ReceiptQty + " does not matche with toteQty " + invoiceQty);
					Assert.assertEquals("ReceiptQty does not matche with toteQty",
							"ReceiptQty " + ReceiptQty + " toteQty " + invoiceQty);
				}

			}

		}

	}

	@Then("^user Mouse hovers on \"([^\"]*)\" and validate text \"([^\"]*)\" and \"([^\"]*)\" on \"([^\"]*)\" and \"([^\"]*)\"$")
	public void mouse_hover_on_and_validateText(String arg1, String arg2, String arg3, String arg4, String arg5)
			throws Throwable {
		// Write code here that turns the phrase above into concrete actions
		String StrElement = ReadProperties.readPropertyByName("OR.properties").getProperty(arg1);
		String strText1 = ReadProperties.readPropertyByName("Testdata.properties").getProperty(arg2);
		String strText2 = ReadProperties.readPropertyByName("Testdata.properties").getProperty(arg3);
		String StrToolTipElement1 = ReadProperties.readPropertyByName("OR.properties").getProperty(arg4);
		String StrToolTipElement2 = ReadProperties.readPropertyByName("OR.properties").getProperty(arg5);
		WebElement element = driver.findElement(By.xpath(StrElement));
		System.out.println("tooltip1 from td " + strText1);
		System.out.println("tooltip2 from td " + strText2);
		System.out.println("tooltip 1 xpath " + StrToolTipElement1);
		System.out.println("tooltip 2 xpath " + StrToolTipElement2);

		Actions toolAct = new Actions(driver);
		toolAct.moveToElement(element).build().perform();
		CommonFunctions.waitForParticularTime("4000");
		WebElement toolTipElement1 = driver.findElement(By.xpath(StrToolTipElement1));
		String toolTipText1 = toolTipElement1.getText();
		System.out.println(toolTipText1);
		((JavascriptExecutor) driver).executeScript("arguments[0].style.border='2px solid green'", toolTipElement1);
		WebElement toolTipElement2 = driver.findElement(By.xpath(StrToolTipElement2));
		String toolTipText2 = toolTipElement2.getText();
		System.out.println(toolTipText2);
		((JavascriptExecutor) driver).executeScript("arguments[0].style.border='2px solid green'", toolTipElement2);
		if (arg5.equals("Prod_Details_Tooltip2") || arg5.equals("Prod_Details_BetterOppPage_Tooltip2")
				|| arg5.equals("Prod_Details_BetterOppPage_Tooltip1") || arg5.equals("Find_Alt_OriginalProd_tooltip2")
				|| arg5.equals("Prod_Details_BetterOppPage_Tooltip12")) {
			System.out.println("Inside first if");
			String Xpath = "(" + StrToolTipElement2 + "/span" + ")";
			String price = driver.findElement(By.xpath(Xpath)).getText();
			System.out.println(" price is " + price);
			heroIndicatorPrice = price;
			strText2 = strText2 + price;

		} else if (arg5.equals("Cart_Details_Tooltip2") || arg5.equals("Product_DetailsPage_Tooltip2")) {
			System.out.println("Inside second if");
			arrOfStr = toolTipText2.split(":");
			System.out.println("strrrr is " + arrOfStr[1]);
			heroIndicatorPrice = arrOfStr[1];
			toolTipText2 = arrOfStr[0] + ":" + heroIndicatorPrice;
			strText2 = strText2 + heroIndicatorPrice;

		}
		System.out.println("strtest1 is " + strText1);
		System.out.println("strtest2 is " + strText2);
		System.out.println("toolTipText1 is " + toolTipText1);
		System.out.println("toolTipText2 is " + toolTipText2);

		if (strText1.equalsIgnoreCase(toolTipText1) && strText2.equalsIgnoreCase(toolTipText2)) {
			System.out.println("ToolTip Text validated - " + toolTipText1 + "," + toolTipText2);
		} else {
			System.out.println("ToolTip Text validation failed Actual- " + toolTipText1 + ". Expected -" + strText1);
			Assert.assertEquals("ToolTip Text validation failed -" + toolTipText1, " Expected -" + strText1);
		}

	}

	@And("^The user validates if \"([^\"]*)\" is enabled$")
	public void validatePOAutomation(String arg1) {
		String StrElement = ReadProperties.readPropertyByName("OR.properties").getProperty(arg1);
		WebElement element = driver.findElement(By.xpath(StrElement));
		String toggleAttribute = driver.findElement(By.xpath(StrElement)).getAttribute("style");
		if (toggleAttribute.equalsIgnoreCase("background-color: rgb(153, 153, 153); display: block;"))
			element.click();
		else
			((JavascriptExecutor) driver).executeScript("arguments[0].style.border='2px solid blue'", element);

	}

	@And("^User clicks on \"([^\"]*)\" and Places The Order for \"([^\"]*)\"$")
	public void placeOrder(String arg1, String arg2) throws Throwable {
		ReUsableCode obj = new ReUsableCode();
		CommonFunctions.waitForParticularTime("6000");
		String buyNow_button = ReadProperties.readPropertyByName("OR.properties").getProperty(arg1);
		driver.findElement(By.xpath(buyNow_button)).click();
		String reOrderElementMsg = ReadProperties.readPropertyByName("OR.properties").getProperty("Modal_Window_Msg");
		String reOrderElementMsgProdDescPage = ReadProperties.readPropertyByName("OR.properties")
				.getProperty("Modal_Window_Msg_ProdDetails");
		String buyNow_Msg = ReadProperties.readPropertyByName("OR.properties").getProperty("Modal_Window_Msg");
		String orderProduct = ReadProperties.readPropertyByName("OR.properties").getProperty("Order_Product");
		String reOrderBtnYes = ReadProperties.readPropertyByName("OR.properties")
				.getProperty("cart_details_submit_modal_yes_button");
		if (arg2.equalsIgnoreCase("standard_Product")) {
			String Product_OrderedXpath = ReadProperties.readPropertyByName("OR.properties")
					.getProperty("Product_Ordered");
			if (driver.findElements(By.xpath(Product_OrderedXpath)).size() > 0)
				buyNowOrderCount++;
			System.out.println("order count is " + buyNowOrderCount);
			if ((driver.findElements(By.xpath(reOrderElementMsg)).size() > 0)
					|| (driver.findElements(By.xpath(reOrderElementMsgProdDescPage)).size() > 0)) {
				// Common_Functions.waitForParticularTime("2000");
				if (driver.findElements(By.xpath(orderProduct)).size() > 0) {
					WebElement modalWindowElement = driver.findElement(By.xpath(buyNow_Msg));
					greenHighLight(modalWindowElement);
					driver.findElement(By.xpath(orderProduct)).click();
				} else {
					greenHighLight(driver.findElement(By.xpath(reOrderElementMsgProdDescPage)));
					driver.findElement(By.xpath(reOrderBtnYes)).click();
				}
				obj.wait_until_element_present_for_seconds("Product_Ordered", 20);
				String Xpath = ReadProperties.readPropertyByName("OR.properties").getProperty("Product_Ordered");
				if (driver.findElements(By.xpath(Xpath)).size() > 0)
					buyNowOrderCount++;
				System.out.println("order count is " + buyNowOrderCount);
			}
		} else {
			CommonFunctions.waitForParticularTime("2000");
			WebElement modalWindowElement = driver.findElement(By.xpath(buyNow_Msg));
			greenHighLight(modalWindowElement);
			driver.findElement(By.xpath(orderProduct)).click();
			obj.wait_until_element_present_for_seconds("Product_Ordered", 30);
			String Xpath = ReadProperties.readPropertyByName("OR.properties").getProperty("Product_Ordered");
			if (driver.findElements(By.xpath(Xpath)).size() > 0)
				buyNowOrderCount++;
			System.out.println("order count is " + buyNowOrderCount);
		}
	}

	@And("^User creates the Note for the product \"([^\"]*)\" with \"([^\"]*)\" comments$")
	public void user_Creates_The_Note(String arg1, String arg2) throws Throwable {
		WebDriverWait wait1 = new WebDriverWait(driver, Duration.ofSeconds(20));
		String product = ReadProperties.readPropertyByName("Testdata.properties").getProperty(arg1);
		String noteComment = ReadProperties.readPropertyByName("Testdata.properties").getProperty(arg2);
		String Add_New_Note = ReadProperties.readPropertyByName("OR.properties").getProperty("Add_New_Note");
		String CreateNote_prod_IDTxtfield = ReadProperties.readPropertyByName("OR.properties")
				.getProperty("CreateNote_prod_IDTxtfield");
		String CreateNote_AddBtn = ReadProperties.readPropertyByName("OR.properties").getProperty("CreateNote_AddBtn");
		String Tools_commentsTextarea = ReadProperties.readPropertyByName("OR.properties")
				.getProperty("Tools_commentsTextarea");
		String Tools_Create_NoteBtn = ReadProperties.readPropertyByName("OR.properties")
				.getProperty("Tools_Create_NoteBtn");
		String Update_Note_Button = ReadProperties.readPropertyByName("OR.properties")
				.getProperty("Update_Note_Button");

		driver.findElement(By.xpath(Add_New_Note)).click();
		driver.findElement(By.xpath(CreateNote_prod_IDTxtfield)).sendKeys(product);
		driver.findElement(By.xpath(CreateNote_AddBtn)).click();
		WebElement Tools_commentsTextareaElement = wait1
				.until(ExpectedConditions.elementToBeClickable(By.xpath(Tools_commentsTextarea)));
		Tools_commentsTextareaElement.clear();
		Tools_commentsTextareaElement.sendKeys(noteComment);
		if (driver.findElements(By.xpath(Update_Note_Button)).size() > 0)
			driver.findElement(By.xpath(Update_Note_Button)).click();
		else
			driver.findElement(By.xpath(Tools_Create_NoteBtn)).click();
	}

	@And("^user fetched the order number$")
	public void user_fetches_The_order() {
		String orderNumberXpath = ReadProperties.readPropertyByName("OR.properties").getProperty("First_DummyOrder");
		String orderNumber = driver.findElement(By.xpath(orderNumberXpath)).getText();
		dummyOrderNumber = Long.parseLong(orderNumber);
		System.out.println("order no is " + dummyOrderNumber);
		greenHighLight(driver.findElement(By.xpath(orderNumberXpath)));

	}

	@And("^Validate the Orders are Confirmed$")
	public void validate_Order_Confirmation() throws Throwable {
		CommonFunctions.waitForParticularTime("10000");
		driver.navigate().refresh();
		long firstOrde = dummyOrderNumber;
		System.out.println("dummyOrderNumber is " + dummyOrderNumber);
		int cnt = buyNowOrderCount - 1;
		System.out.println("count of last step is " + cnt);
		List<Long> orderNumbers = new ArrayList<>();
		for (int i = 0; i < cnt; i++) {
			firstOrde = firstOrde + 1;
			System.out.println("order is " + firstOrde);
			orderNumbers.add(firstOrde);
		}
		List<String> orderNumbersString = orderNumbers.stream().map(s -> String.valueOf(s))
				.collect(Collectors.toList());
		System.out.println("String list is" + orderNumbersString);

		String xpath = "//span[contains(@class,'order-list-id')]";
		String buyNowStatus = "//span[contains(@class,'_ljs_status')]";
		for (int i = 0; i < cnt; i++) {
			xpath = "//span[contains(@class,'order-list-id')]";
			buyNowStatus = "//span[contains(@class,'_ljs_status')]";
			xpath = "(" + xpath + ")[" + (i + 1) + "]";
			String lisele = orderNumbersString.get(orderNumbersString.size() - i - 1);
			System.out.println("kisele is " + lisele);
			WebElement listno = driver
					.findElement(By.xpath("//span[contains(@class,'order-list-id') and text()='" + lisele + "']"));
			greenHighLight(listno);
			System.out.println("no xpath is " + xpath);
			String orderno = driver.findElement(By.xpath(xpath)).getText();
			System.out.println("order no is " + orderno);
			if (orderNumbersString.contains(orderno)) {
				System.out.println("contains " + orderno);
				buyNowStatus = "(" + buyNowStatus + ")[" + (i + 1) + "]";
				System.out.println("status xpath is " + buyNowStatus);
				String status = driver.findElement(By.xpath(buyNowStatus)).getText();
				Assert.assertEquals("Confirmed", status);
				greenHighLight(driver.findElement(By.xpath(buyNowStatus)));
				JavascriptExecutor js = (JavascriptExecutor) driver;
				js.executeScript("arguments[0].scrollIntoView();", listno);
				CommonFunctions.waitForParticularTime("1000");
			}
		}
		scrollup();
	}

	@And("^Validate if Note Exists for Product \"([^\"]*)\"$")
	public void validate_is_noteExists(String arg1) throws Throwable {
		String noteproduct = ReadProperties.readPropertyByName("Testdata.properties").getProperty(arg1);
		CommonFunctions.waitForParticularTime("3000");
		WebElement noteProductElement = driver.findElement(By.xpath(
				"//input[@value='" + noteproduct + "']/ancestor::label/parent::div/following-sibling::textarea"));
		greenHighLight(noteProductElement);

	}
	
	@When("^the user validated if element \"([^\"]*)\" is enabled \"([^\"]*)\"$")
	public void is_element_enabled_disabled(String arg1, String arg2) throws Throwable {
		if(CommonFunctions.isElementPresent(blankNumber)) {
			if(CommonFunctions.getAttribute(blankNumberText, "value").isEmpty()) {
				driver.findElement(blankNumberText).sendKeys("123456789");
			}
			
		}
		 String Xpath = ReadProperties.readPropertyByName(TESTDATA).getProperty(arg1);
		CommonFunctions.isButtonEnabled(By.xpath(Xpath), arg2);
	}
	
	@And("^User validates \"([^\"]*)\" mesage is \"([^\"]*)\"$")
	public void isElementPresent(String error,String condition) throws InterruptedException {  
		if(error.equalsIgnoreCase("PO Format Error")) {
		if(condition.equalsIgnoreCase("Present")) {
		CommonFunctions.validate_is_exists(POErrorMsg, "yes");
		CommonFunctions.compareText(POErrorMsg, "PO format must follow three numbers, one dash and six alphanumeric characters (e.g. ###-CCCCCC )");
		String color = driver.findElement(POErrorMsg).getCssValue("color");
		String hex=Color.fromString(color).asHex();
		Assert.assertEquals("#e22f00", hex);
		CommonFunctions.greenHighLightBy(POErrorMsg);
		}
		}
		else if(error.equalsIgnoreCase("PO Duplicate Error")) {
			if(condition.equalsIgnoreCase("Present")) {
				CommonFunctions.validate_is_exists(POErrorMsg, "yes");
				CommonFunctions.compareText(POErrorMsg,"P.O.Number Already Exists");
				String color = driver.findElement(POErrorMsg).getCssValue("color");
				String hex=Color.fromString(color).asHex();
				Assert.assertEquals("#e22f00", hex);
				CommonFunctions.greenHighLightBy(POErrorMsg);
				}
		}
		else if(condition.equalsIgnoreCase("not present")) {
			CommonFunctions.validate_is_exists(POErrorMsg, "no");
			}
		CommonFunctions.waitForParticularTime("5000");
		}
	
	@Then("User clear all critical messages")
	public void clearCriticalMessages() throws Throwable {
		CommonFunctions.waitForPageLoad();
		//CommonFunctions.waitForParticularTime("5000");
		if(CommonFunctions.isElementPresent(criticalAlertMsg)) {
			for(int i=1;i<=driver.findElements(criticalAlertMsg).size();i++) {
				CommonFunctions.waitForParticularTime("2000");
				CommonFunctions.clickUsingJavaScript(By.xpath("(//div[@class='warning-card']//div[contains(@class,'showDismiss')])[last()]"));
			}
			}
		else {
			System.out.println("Critical Messages not present");
		}
 
	}

}