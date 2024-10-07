package utils;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.DirectoryNotEmptyException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardWatchEventKinds;
import java.nio.file.WatchEvent;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.function.Function;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;

import pageobjects.BasePage;

/**
 * 
 * @author - Automation Team This class consist of all common functions
 *
 */
public class CommonFunctions extends BasePage {
	static WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
	public static String windowHandler = null;
	public static String localDownloadPath = ReadProperties.readPropertyByName("Config.properties").getProperty("localDownloadPath");

	public static void enterText(WebDriver driver, String findElementBy, String expr, String value) {

		if (findElementBy.equalsIgnoreCase("xpath")) {
			try {

				driver.findElement(By.xpath(expr)).clear();
				driver.findElement(By.xpath(expr)).sendKeys(value);

			} catch (Exception e) {

				logger.debug("Not able to Enter {}", value);
				logger.error("Exception occured: {}", e.getMessage());
				Assert.assertEquals("Not able to Enter", value);

			}
		} else if (findElementBy.equalsIgnoreCase("id")) {
			try {
				driver.findElement(By.id(expr)).sendKeys(value);
			} catch (Exception e) {
				logger.debug("Not able to Enter {}", value);
				logger.error("Exception occured: {}", e.getMessage());
				Assert.assertEquals("Not able to Enter", value);

			}
		}

	}

	public static void enterTextJavascript(WebDriver driver, String findElementBy, String expr, String value) {

		if (findElementBy.equalsIgnoreCase("xpath")) {
			try {
				WebElement element = driver.findElement(By.xpath(expr));
				JavascriptExecutor executor = (JavascriptExecutor) driver;
				executor.executeScript("arguments[0].click();", element);
			} catch (Exception e) {
				logger.debug("Not able to Enter {}", value);
				logger.error("Exception occured: {}", e.getMessage());
				Assert.assertEquals("Not able to Enter", value);

			}
		} else if (findElementBy.equalsIgnoreCase("id")) {
			try {
				driver.findElement(By.id(expr)).sendKeys(value);
			} catch (Exception e) {
				logger.debug("Not able to Enter {}", value);
				logger.error("Exception occured: {}", e.getMessage());
				Assert.assertEquals("Not able to Enter", value);

			}
		}

	}

	public static void click(WebDriver driver, String findElementBy, String expr, String value) {

		if (findElementBy.equalsIgnoreCase("xpath")) {
			try {
				List<WebElement> element = driver.findElements(By.xpath(expr));

				element.get(0).click();

			} catch (Exception e) {

				logger.debug("Failed to click on {}", expr);
				logger.error("Exception occured: {}", e.getMessage());
				Assert.assertEquals("Not Clickable", expr);

			}
		} else if (findElementBy.equalsIgnoreCase("id")) {
			try {
				driver.findElement(By.id(expr)).click();
			} catch (Exception e) {
				logger.error("Exception occured: {}", e.getMessage());
				Assert.assertEquals("Not Clickable", expr);

			}
		}

	}

	/**
	 * It waits for a particular time interval you supply for the web element
	 * 
	 * @param timeInMILISecond
	 * @throws InterruptedException
	 */
	public static void waitForParticularTime(String timeInMILISecond) throws InterruptedException {

		Thread.sleep(Integer.parseInt(timeInMILISecond));

	}

	/**
	 * It waits for web element till time limit which is set
	 * 
	 * @param driver
	 */
	public static void waitForJSJQuery(WebDriver driver) {

		JavascriptExecutor js = (JavascriptExecutor) driver;

		int limit = 1000;

		for (int i = 0; i < limit; i++) {
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
			}

			// To check page ready state.
			if ((Boolean) js.executeScript("return jQuery.active == 0")
					&& js.executeScript("return document.readyState").equals("complete")) {
				break;
			}
		}
	}

	/**
	 * It is used to upload a file in a particular location
	 * 
	 * @param fileLocation-
	 *            location is sent in string format
	 */
	public static void uploadFile(String fileLocation) {

		try {
			StringSelection sel = new StringSelection(fileLocation);
			Thread.sleep(10000);
			// Copy to clipboard
			Toolkit.getDefaultToolkit().getSystemClipboard().setContents(sel, null);
			System.out.println("selection" + sel);



			// Create object of Robot class
			Robot robot = new Robot();
			Thread.sleep(1000);

			// Press Enter
			robot.keyPress(KeyEvent.VK_ENTER);

			// Release Enter
			robot.keyRelease(KeyEvent.VK_ENTER);



			// Press CTRL+V
			robot.keyPress(KeyEvent.VK_CONTROL);
			robot.keyPress(KeyEvent.VK_V);



			// Release CTRL+V
			robot.keyRelease(KeyEvent.VK_V);
			robot.keyRelease(KeyEvent.VK_CONTROL);
			
			Thread.sleep(1000);



			robot.keyPress(KeyEvent.VK_ENTER);
			robot.keyRelease(KeyEvent.VK_ENTER);
			Thread.sleep(4000);
			} catch (Exception e) {
			System.out.println("Error -->" + e.getMessage());
			Assert.assertEquals("Error -->", e.getMessage());



			}



			}

	/**
	 * It is used to scroll to top of the page
	 * 
	 * @throws InterruptedException
	 */
	public static void scrollToPageTop() throws InterruptedException {
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("window.scrollTo(0, 0)");
		waitForParticularTime("2000");
	}

	/**
	 * It is used to scroll to the bottom of the page
	 * 
	 * @throws InterruptedException
	 */
	public static void scrollToPageBottom() throws InterruptedException {
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("window.scrollTo(0, document.body.scrollHeight)");
		waitForParticularTime("2000");
	}

	/**
	 * It is used to compare the text of a given WebElement with given Value
	 * 
	 * @param expr
	 * @param value
	 * @throws TimeoutException
	 * @throws InterruptedException
	 */
	public static void compareText(By expr, String value) {

		try {
			wait_until_element_present_for_seconds(expr, 30);
		} catch (TimeoutException e1) {
			logger.error("Element {} not present.", expr);
			System.out.println(e1.getMessage());
		}
		WebElement element = driver.findElement(expr);

		if (driver instanceof JavascriptExecutor) {
			((JavascriptExecutor) driver).executeScript("arguments[0].style.border='1px solid green'", element);

		}
		String actualMsg = element.getText().trim();

		logger.debug("Text present: {}", actualMsg);
		try {
			if (actualMsg.equalsIgnoreCase(value.trim())) {
				logger.debug("Text Validation Passed: {} ", actualMsg);
				Assert.assertTrue(true);

			}
			if (driver instanceof JavascriptExecutor) {
				((JavascriptExecutor) driver).executeScript("arguments[0].style.border='1px solid green'", element);
			}
		} catch (AssertionError e) {
			logger.error("Text Validation Failed {}", actualMsg);
			Assert.fail(e.getMessage());

		}

	}

	/**
	 * It is used to enter text on the Web element
	 * 
	 * @param expr
	 * @param value-
	 *            value to be entered
	 */
	public static void enterText(By expr, String value) {

		try {
			waitFor_Element_ToBe_Clickable(expr);
			WebElement element = driver.findElement(expr);
			element.clear();
			element.sendKeys(Keys.CONTROL + "a");
			element.sendKeys(Keys.DELETE);
			element.sendKeys(value);
			//takeScreenshot(driver);
		} catch (Exception e) {
			logger.debug("Not able to Enter {}", value);
			logger.debug("Exception occured: {}", e.getMessage());
			Assert.fail("Not able to Enter value - "+value+" to locator : "+expr+ ". Exception caught - "+e.getMessage() );

		}

	}

	/**
	 * It is used to enter text on the Web element using Javascriptexecutor
	 * 
	 * @param expr
	 *            - By value
	 * @param value
	 *            - value to be entered
	 */
	public static void enterTextUsingJavaScript(By expr, String value) {

		try {
			WebElement element = driver.findElement(expr);
			JavascriptExecutor jse = (JavascriptExecutor) driver;
			jse.executeScript("arguments[0].value='" + value + "';", element);
		} catch (Exception e) {
			logger.debug("Not able to Enter {}", value);
			logger.error("Exception occured: {}", e.getMessage());
			Assert.fail("Not able to Enter value - "+value+" to locator : "+expr+ ". Exception caught - "+e.getMessage() );
		}

	}

	/**
	 * It is used to enter text on the Web element using action class
	 * 
	 * @param expr
	 * @param value-
	 *            value to be entered
	 * @throws TimeoutException
	 * @throws InterruptedException
	 */
	public static void enterTextUsingAction(By expr, String value) {

		try {
			waitFor_Element_ToBe_Clickable(expr);
			WebElement element = driver.findElement(expr);
			Actions builder = new Actions(driver);
			builder.moveToElement(element).click().sendKeys(value).build().perform();
			//takeScreenshot(driver);
		} catch (Exception e) {
			logger.debug("Not able to Enter {}", value);
			logger.error("Exception occured: {}", e.getMessage());
			Assert.fail("Not able to Enter " + value);

		}
	}

	/**
	 * It is used to click the web element
	 * 
	 * @param expr
	 *            - web element
	 * @throws InterruptedException
	 */
	public static void click(By expr) {

		try {
			//waitFor_Element_ToBe_Clickable(expr);
			WebElement elementActionPerform = driver.findElement(expr);
			elementActionPerform.click();
			//takeScreenshot(driver);
			logger.info("Element {} clicked", expr);

		} catch (StaleElementReferenceException e) {
			waitFor_Element_ToBe_Clickable(expr);
			driver.findElement(expr).click();
			logger.info("Element {} clicked", expr);

		} catch (ElementClickInterceptedException e) {

			// to check if pre-load is running on the background
			if (!driver.findElements(By.id("preload-background")).isEmpty()) {
				waitUntillElementIsNotPresent(By.id("preload-background"), 30);

				// to check if cboxOverlay exist - Formulary page
			} else if (!driver.findElements(By.id("cboxOverlay")).isEmpty()) {
				waitUntillElementIsNotPresent(By.id("cboxOverlay"), 30);

				// to check if toast message interrupts the click
			} else if (!driver.findElements(By.className("confirmed_exceptions toast-info")).isEmpty()) {
				waitUntillElementIsNotPresent(By.className("confirmed_exceptions toast-info"), 30);

				// to check if toast message interrupts the click
			} else if (!driver.findElements(By.className("toast-svg-icon checkmark-svg")).isEmpty()) {
				waitUntillElementIsNotPresent(By.className("toast-svg-icon checkmark-svg"), 30);
			} else if (!driver.findElements(By.id("buyNowToastMessage")).isEmpty()) {
				waitUntillElementIsNotPresent(By.id("buyNowToastMessage"), 30);
			}

			// buyNowToastMessage
			try {
				waitFor_Element_ToBe_Clickable(expr);
				driver.findElement(expr).click();
				logger.info("Element {} clicked", expr);
			} catch (ElementClickInterceptedException e1) {
				try {
					CommonFunctions.waitForParticularTime("5000");
					driver.findElement(expr).click();
					logger.info("Element {} clicked", expr);
				} catch (InterruptedException e2) {
					logger.error("Thread sleep Exception occured: {}", e.getMessage());
				} catch (Exception e3) {
					logger.error("Exception occured: {}", e3.getMessage());
					Assert.fail("Element Not Clickable " + e3.getMessage());
				}
				
			}

			catch (Exception ex) {
				logger.error("Exception occured: {}", ex.getMessage());
				Assert.fail("Element Not Clickable " + ex.getMessage());
			}

		} catch (NoSuchElementException e) {
			logger.error("Exception occured: {}", e.getMessage());
			Assert.fail("Element not found by locator : " + expr);

		} catch (Exception e) {
			logger.error("Exception occured: {}", e.getMessage());
			Assert.fail("Element Not Clickable " + expr + ". Error:" + e);

		}
		waitForLoaderToClose();
	}

	/**
	 * It checks the button is displayed on the page
	 * 
	 * @param expr
	 *            - the web element
	 * @param value
	 *            - the value to be validated
	 * @throws TimeoutException
	 * @throws InterruptedException
	 */
	public static void isButtonDisplayed(By expr, String value) {

		WebElement element = driver.findElement(expr);
		boolean b = false;

		try {

			if ((element.isDisplayed() && value.equalsIgnoreCase("yes"))
					|| (element.isDisplayed() && value.equalsIgnoreCase("no"))) {
				b = true;
			}
			if (b) {
				Assert.assertTrue(
						"Assertion Passed. Expected Displayed = " + value.toUpperCase() + " for Element " + expr, true);
				//takeScreenshot(driver);
			} else {
				Assert.fail("Assertion Failed. Expected Displayed = " + value.toUpperCase() + " for Element " + expr);
			}

		} catch (NoSuchElementException e) {
			logger.error("Exception occured: {}", e.getMessage());

		}

	}

	/**
	 * It checks the button is enabled
	 * 
	 * @param expr
	 *            - web element
	 * @param value
	 *            - value to be validated
	 */
	public static void isButtonEnabled(By expr, String value) {

		try {

			WebElement element = driver.findElement(expr);
			boolean b = false;
			boolean classAttrDisabled;
			boolean attributeDisabled = false;
			boolean enabled = element.isEnabled();

			try {

				classAttrDisabled = element.getAttribute("class").contains("disabled");

			} catch (NullPointerException npe) {
				classAttrDisabled = false;
			}

			try {
				if (!element.getAttribute("disabled").isEmpty())
					attributeDisabled = true;

			} catch (NullPointerException npe) {
				attributeDisabled = false;
			}

			if ((enabled && !classAttrDisabled && value.equalsIgnoreCase("yes") && !attributeDisabled)
					|| ((!enabled || classAttrDisabled || attributeDisabled) && value.equalsIgnoreCase("no"))) {
				b = true;
			}
			if (b) {
				Assert.assertTrue(
						"Assertion Passed. Expected Enabled = " + value.toUpperCase() + " for Element " + expr, true);
				if (driver instanceof JavascriptExecutor) {
					((JavascriptExecutor) driver).executeScript("arguments[0].style.border='2px solid green'", element);
				}
			} else {
				Assert.fail("Assertion Failed. Expected Enabled = " + value.toUpperCase() + " for Element " + expr);
			}

		} catch (NoSuchElementException e) {
			logger.error("Exception occured: {}", e.getMessage());
		}

	}

	/**
	 * It is used to scroll element by value
	 * 
	 * @param By
	 */
	public static void scroll_to(By value) {
		try {
			WebElement ele = driver.findElement(value);
			// This will scroll the page till the element is found
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("arguments[0].scrollIntoView(false);", ele);

			logger.debug("Scroll to element {} ", value);
			waitForParticularTime("2000");
		} catch (Exception e) {
			// need better exception handling here
			logger.error("Exception occured: {}", e.getMessage());
			//Assert.fail(e.getMessage());

		}
	}
	

	/**
	 * It is used to scroll to Web Element using java script executor
	 * 
	 * @param element
	 * @throws Throwable
	 */
	public static void scroll_to(WebElement element) {

		try {

			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("arguments[0].scrollIntoView(false);", element);

			logger.debug("Scroll to element {} ", element);
			waitForParticularTime("2000");
		} catch (Exception e) {
			// need better exception handling here
			logger.error("Exception occured: {}", e.getMessage());
			Assert.fail(e.getMessage());

		}

	}

	/**
	 * It is used to check if the radio button is in selected mode
	 * 
	 * @param radioButtonName
	 *            -name of the radio button
	 * @param condition
	 *            -condition either yes or no
	 * @throws Throwable
	 */
	public static void isRadioButtonSelected(String radioButtonName, String condition) {

		By radioBtn = By.xpath("//*[contains(text(),'" + radioButtonName + "')]/preceding::input[1][@type='radio']");
		WebElement element = driver.findElement(radioBtn);
		boolean b = false;

		try {

			if ((element.isSelected() && condition.equalsIgnoreCase("yes"))
					|| (!element.isSelected() && condition.equalsIgnoreCase("no"))) {
				b = true;
			}
			if (b) {
				Assert.assertTrue("Assertion Passed : Expected Selection " + condition + " . Element :" + radioBtn,
						true);
			} else {
				Assert.fail("Assertion Failed : Expected Selection " + condition + " . Element :" + radioBtn);
			}

		} catch (NoSuchElementException e) {
			logger.error("Exception occured: {}", e.getMessage());
		}

	}

	/**
	 * It is used to check if radio button is enabled
	 * 
	 * @param radioButtonName-
	 *            name of radio button
	 * @param condition
	 *            can be yes / no
	 * @throws Throwable
	 */
	public static void isRadioButtonEnabled(String radioButtonName, String condition) {
		By radioBtn = By.xpath("//*[contains(text(),'" + radioButtonName + "')]/preceding::input[1][@type='radio']");
		WebElement element = driver.findElement(radioBtn);
		boolean b = false;

		try {

			if ((element.isEnabled() && condition.equalsIgnoreCase("yes"))
					|| (!element.isEnabled() && condition.equalsIgnoreCase("no"))) {
				b = true;
			}
			if (b) {
				Assert.assertTrue(
						"Assertion Passed : Expected Radio Button Enabled " + condition + " . Element :" + radioBtn,
						true);
			} else {
				Assert.fail(
						"Assertion Failed : Expected Radio Button Enabled " + condition + " . Element :" + radioBtn);
			}

		} catch (NoSuchElementException e) {
			logger.error("Exception occured: {}", e.getMessage());
		}
	}

	/**
	 * It refreshes the current url
	 * 
	 * @throws Throwable
	 */
	public static void pageRefresh() {
		driver.navigate().refresh();
	}

	/**
	 * It is used to wait for the element present in seconds
	 * 
	 * @param arg1-
	 *            it is the web element to be searched
	 * @param arg2
	 *            -it is the time in seconds , we want to wait for
	 */
	public static void wait_until_element_present_for_seconds(By arg1, int arg2) throws TimeoutException {

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(arg2));
		wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(arg1));
	}

	/**
	 * It checks if the elements exists on the page using condition
	 * 
	 * @param by-element
	 * @param condition-
	 *            Yes or No
	 * @throws TimeoutException
	 * 
	 */
	public static void validate_is_exists(By by, String condition) {

		// this function needs update
		applyImplicitWait(3);
		if (condition.equalsIgnoreCase("no")) {
			waitUntillElementIsNotPresent(by, 60);
		} else {
			try {
				wait_until_element_present_for_seconds(by, 60);
			} catch (TimeoutException e) {
				logger.error("Element {} not present.", by);
				Assert.fail("Element not present." + by);
			}
		}

		List<WebElement> elements = driver.findElements(by);
		boolean b = false;
		try {

			if ((!elements.isEmpty() && condition.equalsIgnoreCase("yes"))
					|| elements.isEmpty() && condition.equalsIgnoreCase("no")) {
				b = true;
			}

			if (b) {
				Assert.assertTrue("Assertion Passed : Expected element present " + condition + " . Element :" + by,
						true);
			} else {
				Assert.fail("Assertion Failed : Expected element present " + condition + " . Element :" + by);
			}

		} catch (NoSuchElementException e) {
			Assert.fail("Exception : Expected element not found " + by);
		} catch (Exception e) {

			logger.error("Exception occured: {}", e.getMessage());

			Assert.fail("Exception caught : " + e.getMessage());
		}

	}

	/**
	 * Its basically used to highlight the element in green color , especially for
	 * demo purpose
	 * 
	 * @param ele
	 *            - web element to highlight
	 */
	public static void greenHighLight(WebElement ele) {
		if (driver instanceof JavascriptExecutor) {
			((JavascriptExecutor) driver).executeScript("arguments[0].style.border='2px solid green'", ele);
		}
	}

	/**
	 * Its basically used to highlight the element in green color , especially for
	 * demo purpose
	 * 
	 * @param ele
	 *            - web element to highlight
	 * @throws InterruptedException
	 */
	public static void greenHighLightBy(By arg1) throws InterruptedException {
		WebElement el = driver.findElement(arg1);
		if (driver instanceof JavascriptExecutor) {
			((JavascriptExecutor) driver).executeScript("arguments[0].style.border='2px solid green'", el);
		}
		CommonFunctions.waitForParticularTime("500");
	}

	/**
	 * It validates the date format
	 * 
	 * @param arg1-
	 *            the web element
	 */
	public static void validate_Date_Format(By arg1) {

		try {
			WebElement date = driver.findElement(arg1);
			String dateText = date.getText();
			String startdate[] = dateText.split("/");
			logger.info("Start date[0]>>>> {}", startdate[0]);
			logger.info("Start date[1]>>>> {}", startdate[1]);
			logger.info("Start date[2]>>>> {}", startdate[2]);

			Assert.assertEquals(3, startdate.length);
			greenHighLight(date);
		} catch (Exception e) {

			logger.error("Exception occured: {}", e.getMessage());
		}

	}

	/**
	 * It validates the amount format using regex matching
	 * 
	 * @param arg1-
	 *            web element
	 */
	public static void validate_Amount_Format(By arg1) {
		try {
			WebElement amount = driver.findElement(arg1);
			String amountText = amount.getText();
			if (!(amountText.matches("[$]{1}[0-9]*[,][0-9]*[.][0-9]*")))
				Assert.assertEquals("Not Matched", amountText);
			greenHighLight(amount);
		} catch (Exception e) {

			logger.error("Exception occured: {}", e.getMessage());
		}
	}

	/**
	 * It selects the start data and end date from calendar
	 * 
	 * @param arg1-
	 *            start date
	 * @param arg2-
	 *            end date
	 * @param page-
	 *            page
	 * @throws Throwable
	 */
	public static void select_the_start_date_from_and_the_end_date_to(String arg1, String arg2, String page) {

		setCalendarDate(arg1, 0, page);
		setCalendarDate(arg2, 1, page);

	}

	/**
	 * It sets the calendar date
	 * 
	 * @param calendarDate-
	 *            date needs to be set
	 * @param k-
	 *            o/1 0- stands for source and 1 stands for to calendar date
	 * @param page
	 *            - page
	 * @throws Throwable
	 * @throws InterruptedException
	 */
	public static void setCalendarDate(String calendarDate, int k, String page) {
		String dateYearXpath;
		String dateMonthXpath;
		String dateDayXpath = null;

		if (page.equalsIgnoreCase("Receiving")) {
			dateYearXpath = "//select[@name='year']";
			dateMonthXpath = "//select[@name='month']";
			if (k == 0)
				dateDayXpath = "//div[text()='FROM']/parent::div//div[@aria-disabled='false']";
			else if (k == 1)
				dateDayXpath = "//div[text()='TO']/parent::div//div[@aria-disabled='false']";

		} else {
			dateYearXpath = "//select[@class='yearselect']";
			dateMonthXpath = "//select[@class='monthselect']";

			if (k == 0)
				dateDayXpath = "//div[@class='calendar left']/div[@class='calendar-table']//td[not(contains(@class,'off'))]";
			else if (k == 1)
				dateDayXpath = "//div[@class='calendar right']/div[@class='calendar-table']//td[not(contains(@class,'off'))]";
		}

		try {
			List<WebElement> yearlist = driver.findElements(By.xpath(dateYearXpath));

			String givenDate[] = calendarDate.split("/");

			logger.info("Start date[0]>>>> Month {}", givenDate[0]);
			logger.info("Start date[1]>>>> Date {}", givenDate[1]);
			logger.info("Start date[2]>>>> Year {}", givenDate[2]);
			
			String regex = "^0+(?!$)";
			givenDate[1] = givenDate[1].replaceAll(regex, "");	 
	        System.out.println(givenDate[1]);

			Select year = new Select(yearlist.get(k));
			year.selectByVisibleText(givenDate[2].trim());
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
			wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath(dateMonthXpath)));

			List<WebElement> monthlist = driver.findElements(By.xpath(dateMonthXpath));
			Select month = new Select(monthlist.get(k));
			month.selectByIndex(Integer.parseInt(givenDate[0].trim()) - 1);

			wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath(dateDayXpath)));
			List<WebElement> date1 = driver.findElements(By.xpath(dateDayXpath));
			for (WebElement d : date1) {

				if (d.getText().trim().equals(givenDate[1].trim())) {

					logger.info("print the extracted date>>> {} ", d.getText());
					d.click();
					CommonFunctions.waitForParticularTime("1000");
					break;
				}

			}
		} catch (Exception e) {

			logger.error("Exception occured: {}", e.getMessage());
		}
	}

	/**
	 * It is used to do mouse hover and validate tool tip text
	 * 
	 * @param primaryElement
	 * @param expectedToolTipText
	 * @param toolTipElement
	 */
	public static void mousehoverElementAndValidateToolTipText(By primaryElement, String expectedToolTipText,
			By toolTipElement) {

		WebElement element = driver.findElement(primaryElement);

		try {
			Actions toolAct = new Actions(driver);
			toolAct.moveToElement(element).build().perform();
			wait_until_element_present_for_seconds(toolTipElement, 30);
			WebElement toolTipEle = driver.findElement(toolTipElement);
			String toolTipText = toolTipEle.getText();
			logger.info("tooltip from UI is {} ", toolTipText);

			greenHighLight(driver.findElement(toolTipElement));

			if (expectedToolTipText.equalsIgnoreCase(toolTipText)) {
				logger.info("ToolTip Text validated - {} ", toolTipText);
			} else {
				logger.debug("ToolTip Text validation failed Actual- {} . Expected - {}  ", toolTipText,
						expectedToolTipText);

				Assert.assertEquals(expectedToolTipText, toolTipText);
			}
		} catch (Exception e) {

			logger.error("Exception occured: {}", e.getMessage());
			Assert.fail(e.getMessage());
		}

	}

	/**
	 * It closes all open pop ups with close button
	 */
	public static void closeAdditionalScreens() {
		try {
			CommonFunctions.waitForParticularTime("500");
			if (driver.getPageSource().contains("Welcome to ABC Order!")) {
				driver.findElement(By.xpath("//span[text()='Close']")).click();
			}
		} catch (InterruptedException e) {

			logger.error("Exception occured: {}", e.getMessage());
			Thread.currentThread().interrupt();
		}
	}

	/**
	 * It is used to validate text for an element with value
	 * 
	 * @param arg1-
	 *            value
	 * @param arg2-
	 *            web element
	 * @throws Throwable
	 */
	public static void validate_text(String arg1, By arg2) {
		try {

			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
			wait.until(ExpectedConditions.visibilityOfElementLocated(arg2));
			WebElement element = driver.findElement(arg2);
			String actualText = element.getText();
			actualText = actualText.toLowerCase();
			arg1 = arg1.toLowerCase();

			if (actualText.contains(arg1.trim())) {
				logger.info("Text Validation Passed: {}  ", actualText);

			} else {
				logger.debug("Text Validation Failed: {}  ", arg2);
				Assert.assertEquals(arg1, actualText);

			}
		} catch (Exception e) {

			logger.error("Exception occured: {}", e.getMessage());
		}
	}

	/**
	 * It is used to clear text box to wipe off its content
	 * 
	 * @param arg1-
	 *            web element
	 */
	public static void clearTextBox(By arg1) {

		try {
			WebElement toClear = driver.findElement(arg1);
			toClear.clear();
			toClear.sendKeys(Keys.chord(Keys.CONTROL + "a", Keys.DELETE));
		} catch (Exception e) {

			logger.error("Exception occured: {}", e.getMessage());
		}
	}

	/**
	 * It is used to generate Random String
	 * 
	 * @param argLength
	 *            - length in integer
	 * @return
	 */
	public static String generateRandomString(int argLength) {
		String randomChars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
		StringBuilder randomString = new StringBuilder();
		Random rnd = new Random();
		while (randomString.length() < argLength) {
			int index = (int) (rnd.nextFloat() * randomChars.length());
			randomString.append(randomChars.charAt(index));
		}
		String finalString = randomString.toString();
		return finalString;
	}

	/**
	 * It is used to compare text of 2 string values
	 * 
	 * @param expectedText
	 *            -string 1
	 * @param actualText-
	 *            string 2
	 */
	public static void compareText(String expectedText, String actualText) {
		try {
			logger.info("\nValidation Passed: [Expected Text: {} ] # [Actual Text: {} ] ", expectedText, actualText);
			Assert.assertEquals(expectedText.trim(), actualText.trim());

		} catch (Exception ex) {
			logger.error("Exception occured: {}", ex.getMessage());
			Assert.assertNotEquals(expectedText, actualText);
			Assert.fail("\nValidation Failed:" + "[Expected Text: " + expectedText + "] # [Actual Text: " + actualText
					+ " ]");

		}
	}

	/**
	 * It is used to compare 2 map values based on their indexes comparison is done
	 * 
	 * @param expectedText-
	 *            map 1
	 * @param actualText-
	 *            map 2
	 */
	public static void compare(Map<String, String> expectedText, Map<String, String> actualText) {
		try {

			Assert.assertEquals(expectedText, actualText);
			logger.info("\nValidation Passed: [Expected Text: {} ] # [Actual Text: {} ] ", expectedText, actualText);

		} catch (Exception ex) {
			logger.error("Exception occured: {}", ex.getMessage());
			Assert.assertNotEquals(expectedText, actualText);
			Assert.fail("\nValidation Failed:" + "[Expected Text: " + expectedText + "] # [Actual Text: " + actualText
					+ " ]");

		}
	}

	/**
	 * It is used to do mouse hover and click using actions class
	 * 
	 * @param element_MoveTo-
	 *            web element to hover over
	 * @param elementToClick
	 *            - web element to click onto
	 * @throws TimeoutException
	 * @throws InterruptedException
	 */
	public static void mousehoverAndClick(By element_MoveTo, By elementToClick)
			throws TimeoutException, InterruptedException {
		try {
			WebElement element = driver.findElement(element_MoveTo);
			Actions toolAct = new Actions(driver);
			toolAct.moveToElement(element).build().perform();
			click(elementToClick);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * It is used to validate page Url
	 * 
	 * @param urlContent-
	 *            content to match with url
	 */
	public static void validatePageUrl(String urlContent) {
		Assert.assertEquals(true, driver.getCurrentUrl().contains(urlContent));
	}

	/**
	 * It is used to get attribute value from a web element
	 * 
	 * @param element-
	 *            web element
	 * @param attribute
	 *            - attribute to be searched
	 * @return - returns the value in form of string
	 * @throws Throwable
	 */
	public static String getAttribute(By element, String attribute) {

		return driver.findElement(element).getAttribute(attribute);
	}

	/**
	 * It is used to check if text box is disabled/not via attribute validation
	 * 
	 * @param txtbox-
	 *            web element
	 * @param attribute-
	 *            attribute name
	 * @return- returns true/false
	 * @throws TimeoutException
	 * @throws InterruptedException
	 */
	public static boolean is_TextBox_Disabled(By txtbox, String attribute) {
		boolean result = true;
		WebElement element = driver.findElement(txtbox);
		String attributeValueBeforeChange = element.getAttribute(attribute);
		CommonFunctions.greenHighLight(driver.findElement(txtbox));
		try {
			if (attributeValueBeforeChange.contains("disabled"))
				result = true;
			else {
				CommonFunctions.enterText(txtbox, "Test123");
				CommonFunctions.clearTextBox(txtbox);
				result = false;
			}
		} catch (Exception e) {
			return result;
		}
		return result;
	}

	/**
	 * It is used to check if text box is blank
	 * 
	 * @param txtbox-
	 *            web element
	 * @param attribute-
	 *            attribute name
	 * @return- returns true/false
	 */
	public static boolean is_TextBox_Blank(By txtBox) {
		boolean result = true;
		WebElement elTxtBox = driver.findElement(txtBox);
		String value = elTxtBox.getText();
		if (value.equalsIgnoreCase("")) {
			result = true;
		} else {
			result = false;
		}

		return result;

	}

	/**
	 * It is used to check if toggle button is disable / not using attribute name
	 * 
	 * @param toogleButton-
	 *            web element
	 * @param attribute-
	 *            name of the attribute
	 * @return- returns true/false
	 * @throws TimeoutException
	 * @throws InterruptedException
	 */
	public static boolean is_ToggleButton_Disabled(By toogleButton, String attribute) {
		boolean result = true;
		try {
			CommonFunctions.greenHighLight(driver.findElement(toogleButton));
			WebElement element = driver.findElement(toogleButton);
			String attributeValueBefore = element.getAttribute(attribute);
			CommonFunctions.click(toogleButton);
			CommonFunctions.waitForParticularTime("2000");// added hard wait to
															// wait for
															// attributs to
															// change after
															// clicking
			String attributeValueAfter = element.getAttribute(attribute);
			if (attributeValueBefore.equalsIgnoreCase(attributeValueAfter))
				result = true;
			else
				result = false;

		} catch (Exception e) {
			return result;
		}
		return result;
	}

	/**
	 * It is used to do zoom page out and then click onto element when direct click
	 * is not working
	 * 
	 * @param element
	 * @throws TimeoutException
	 * @throws InterruptedException
	 */
	public static void zoomPageOutAndClickElement(By element) throws InterruptedException {
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		executor.executeScript("document.body.style.zoom = '0.85'");
		waitForParticularTime("1000");// Added hard wait of 1 second to get Ui stable after zooming the page
		executor.executeScript("arguments[0].click();", driver.findElement(element));
	}

	/**
	 * It is used to move to default page view once the zoomPageOutAnd_ClickElement
	 * has been called
	 * 
	 * @throws TimeoutException
	 * @throws InterruptedException
	 */
	public static void zoomPageDefault() throws InterruptedException {
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		executor.executeScript("document.body.style.zoom = '1'");
		waitForParticularTime("1000");// Added hard wait of 1 second to get Ui stable after zooming the page
	}
	
	/**
	 * It is used to Zoom out based on input provided by User
	 * 
	 * 
	 * @throws TimeoutException
	 * @throws InterruptedException
	 */
	public static void zoomOut(double value) throws InterruptedException {
		double v = value/100;
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		executor.executeScript("document.body.style.zoom = '"+v+"'");
		waitForParticularTime("1000");// Added hard wait of 1 second to get Ui stable after zooming the page
	}

	/**
	 * It is used to wait for the element to be click able
	 * 
	 * @param element-
	 *            web element
	 */
	public static void waitFor_Element_ToBe_Clickable(By element) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(120));
			wait.until(ExpectedConditions.visibilityOfElementLocated(element));
			wait.until(ExpectedConditions.elementToBeClickable(element));
		} catch (Exception e) {
			logger.error("Exception occured: {}", e.getMessage());
			Assert.fail("Element not found" + element);

		}
	}

	/**
	 * It is used to wait for page to load using Java Script executor until the page
	 * document is ready
	 */
	public static void waitForPageLoad() {

		Wait<WebDriver> wait = new WebDriverWait(driver, Duration.ofSeconds(60));
		wait.until(new Function<WebDriver, Boolean>() {
			public Boolean apply(WebDriver driver) {
				logger.info("Current Window State       : {} ",
						String.valueOf(((JavascriptExecutor) driver).executeScript("return document.readyState")));

				return String.valueOf(((JavascriptExecutor) driver).executeScript("return document.readyState"))
						.equals("complete");
			}
		});
	}

	/**
	 * It is used to click element from a list of items using primary customer id
	 * 
	 * @param byLocator-
	 *            web element
	 * @param text
	 *            - string to match and click
	 * @throws Throwable
	 */
	public static void clickElementWithText(By byLocator, String text) throws Throwable {

		List<WebElement> elements = driver.findElements(byLocator);

		for (WebElement ele : elements) {

			if (ele.getText().trim().equalsIgnoreCase(text.trim())) {
				CommonFunctions.scroll_to(ele);
				ele.click();
			}

		}

	}

	/**
	 * It click on link, opens in new Tab and switches control to it
	 * 
	 * @param link
	 * @param tabIndex
	 * @throws InterruptedException
	 */
	public static void openHyperLinkInNewTab(By link, int tabIndex) throws InterruptedException {
		String selectLinkOpeninNewTab = Keys.chord(Keys.CONTROL, Keys.RETURN);

		driver.findElement(link).sendKeys(selectLinkOpeninNewTab);
		driver.findElement(link).sendKeys(Keys.CONTROL, Keys.TAB);
		ArrayList<String> tabs = new ArrayList<>(driver.getWindowHandles());
		driver.switchTo().window(tabs.get(tabIndex));
	}

	/**
	 * It is used to wait until element is not present
	 * 
	 * @param element-
	 *            web element
	 */
	public static void waitUntillElementIsNotPresent(By element, int secondsTowait) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(secondsTowait));
			if (!(driver.findElements(element).isEmpty()))
				wait.until(ExpectedConditions.invisibilityOf(driver.findElement(element)));
		} catch (NoSuchElementException e) {
			logger.info("Element not present {} ", element);
		} catch (Exception e) {
			logger.error("Exception Occured : {}", e.getMessage());
			Assert.fail("Exception Occured : " + e.getMessage());
		}
	}

	/**
	 * It is used to wait until element is not present
	 * 
	 * @param element-
	 *            web element
	 */
	public static void waitUntillElementIsNotPresentPolling(By element, int secondsTowait) {
		long startTime =TimeUnit.MILLISECONDS.toSeconds( System.currentTimeMillis() );
		long endTime = TimeUnit.MILLISECONDS.toSeconds( System.currentTimeMillis() );

		
		while ((endTime - startTime)< secondsTowait)
		{
		try {
			if (!(driver.findElements(element).isEmpty()))
			{
				waitForParticularTime("5000");
				endTime = TimeUnit.MILLISECONDS.toSeconds( System.currentTimeMillis() );
			}
			else {
				break;
			}
		} catch (NoSuchElementException e) {
			logger.info("Element not present {} ", element);
		} catch (Exception e) {
			logger.error("Exception Occured : {}", e.getMessage());
			Assert.fail("Exception Occured : " + e.getMessage());
		}
		}
	}
	
	public static void waitForLoaderToClose()
	{
		By loader = By.xpath("//div[@id='preload-message']");
		for(int i=0;i<=60;i++)
		 {
			try {
				waitForParticularTime("1000");
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			String attribute = getAllAttributesAndValuesifElementExist(loader);
			if(attribute != null && attribute.contains("data-gtm-vis-total-visible-time"))
			{
				//System.out.println("Attribute found");
				try {
					waitForParticularTime("1000");
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			else {
				break;
			}
		 }
	}

	/**
	 * It is used to check if file got downloaded on download path matching it with
	 * expected file name
	 * 
	 * @param downloadPath-
	 *            the path where the file should be checked
	 * @param fileName
	 *            - name of the file name
	 * @return boolean value of true -once found and false-once not found
	 */
	public static boolean isFileDownloaded(String downloadPath, String fileName) {
		boolean flag = false;
		try {
			File dir = new File(downloadPath);
			File[] dirContents = dir.listFiles();

			for (int i = 0; i < dirContents.length; i++) {
				if (dirContents[i].getName().equals(fileName))
					flag = true;
			}
		} catch (Exception e) {
			logger.error("Exception occured: {}", e.getMessage());
			Assert.fail("File Path Not Found " + downloadPath);
		}
		return flag;

	}

	/**
	 * It checks the file from a specific directory with extension
	 * 
	 * @param dirPath-
	 *            path where downloaded file gets placed
	 * @param ext-
	 *            extension
	 * @return -true/false
	 */
	public static boolean isFileDownloaded_Ext(String dirPath, String ext) {
		boolean flag = false;
		try {
			File dir = new File(dirPath);
			File[] files = dir.listFiles();
			for (int i = 0; i < files.length; i++) {
				if (files[i].getName().contains(ext)) {
					return flag = true;
				}
			}

		} catch (Exception e) {
			logger.error("Exception occured: {}", e.getMessage());
			Assert.fail("File Path Not Found " + dirPath);
		}
		return flag;
	}
	

	/**
	 * It gets the latest file from a specific directory
	 * 
	 * @param dirPath
	 *            -path
	 * @return
	 */
	public static File getLatestFilefromDir(String dirPath) {
		File dir = new File(dirPath);
		File[] files = dir.listFiles();
		if (files == null || files.length == 0) {
			return null;
		}

		File lastModifiedFile = files[0];
		for (int i = 1; i < files.length; i++) {
			if (lastModifiedFile.lastModified() < files[i].lastModified()) {
				lastModifiedFile = files[i];
			}
		}
		return lastModifiedFile;
	}

	/**
	 * It gets downloaded document name
	 * 
	 * @param downloadDir-
	 *            path
	 * @param fileExtension-
	 *            extension
	 * @return -document name
	 */
	public static String getDownloadedDocumentName(String downloadDir, String fileExtension) {
		String downloadedFileName = null;
		boolean valid = true;
		boolean found = false;

		// default timeout in seconds
		long timeOut = 60;
		try {
			Path downloadFolderPath = Paths.get(downloadDir);
			logger.info("Watching Directory {} ", downloadFolderPath);
			WatchService watchService = FileSystems.getDefault().newWatchService();
			downloadFolderPath.register(watchService, StandardWatchEventKinds.ENTRY_CREATE);
			long startTime = System.currentTimeMillis();
			do {
				WatchKey watchKey;
				watchKey = watchService.poll(timeOut, TimeUnit.SECONDS);
				long currentTime = (System.currentTimeMillis() - startTime) / 1000;
				if (currentTime > timeOut) {
					logger.error("Download operation timed out.. Expected file was not downloaded");
					return downloadedFileName;
				}

				for (WatchEvent<?> event : watchKey.pollEvents()) {
					WatchEvent.Kind<?> kind = event.kind();
					if (kind.equals(StandardWatchEventKinds.ENTRY_CREATE)) {
						String fileName = event.context().toString();
						logger.info("New File Created: {}", fileName);
						if (fileName.endsWith(fileExtension)) {
							downloadedFileName = fileName;
							logger.info("Downloaded file found with extension {}. File name is {} ", fileExtension,
									fileName);
							Thread.sleep(500);
							found = true;
							break;
						}
					}
				}
				if (found) {
					return downloadedFileName;
				} else {
					currentTime = (System.currentTimeMillis() - startTime) / 1000;
					if (currentTime > timeOut) {
						logger.debug("Failed to download expected file {} ", downloadedFileName);
						return downloadedFileName;
					}
					valid = watchKey.reset();
				}
			} while (valid);
		}

		catch (InterruptedException e) {
			logger.error("Interrupted error - {} ", e.getMessage());
			e.printStackTrace();
			// Restore interrupted state...
			Thread.currentThread().interrupt();
		} catch (NullPointerException e) {
			logger.error("Download operation timed out.. Expected file was not downloaded - Null");
		} catch (Exception e) {
			logger.error("Exception occured: {}", e.getMessage());
			e.printStackTrace();
		}
		return downloadedFileName;
	}

	/**
	 * It deletes the file based on its location and name
	 * 
	 * @param filePath-
	 *            location of the file
	 * @param fileName
	 *            -name of the file
	 * @return
	 */
	public static boolean deleteFile(String filePath, String fileName) {

		try {
			Files.deleteIfExists(Paths.get(filePath + "\\" + fileName));
		} catch (NoSuchFileException e) {
			logger.error("No such file/directory exists");
		} catch (DirectoryNotEmptyException e) {
			logger.error("Directory is not empty.");
		} catch (IOException e) {
			logger.error("Invalid permissions.");
		}

		logger.info("Deletion successful.");
		return true;

	}

	public static String generateRandomNumber(int argLength) {
		String randomChars = "1234567890";
		StringBuilder randomString = new StringBuilder();
		Random rnd = new Random();
		while (randomString.length() < argLength) {
			int index = (int) (rnd.nextFloat() * randomChars.length());
			randomString.append(randomChars.charAt(index));
		}
		String finalString = randomString.toString();
		return finalString;
	}

	public static void zoomInZoomOut(String percentage) throws InterruptedException {
		JavascriptExecutor jsezoomout = (JavascriptExecutor) driver;
		jsezoomout.executeScript("document.body.style.zoom = '" + percentage + "';");
		CommonFunctions.waitForLoad(driver);
		Thread.sleep(1000);
	}

	public static void waitForLoad(WebDriver driver) {
		ExpectedCondition<Boolean> pageLoadCondition = new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver driver) {
				return ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete");
			}
		};
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		wait.until(pageLoadCondition);
	}

	public static void getUrl(String url) {
		driver.get(url);
	}

	/**
	 * @author Macdaline Fernando This function is used to compare two maps
	 * @param expectedText-
	 *            expected Text to be compared with
	 * @param actualText
	 *            -actual Text to be compared with
	 **/
	public static void commonVerify(Map<Integer, String> expectedText, Map<Integer, String> actualText) {
		try {
			logger.info("\nValidation Passed: [Expected Text: {} ] # [Actual Text: {} ] ", expectedText, actualText);
			Assert.assertEquals(expectedText, actualText);

		} catch (Exception ex) {
			logger.error("Exception occured: {}", ex.getMessage());
			Assert.assertNotEquals(expectedText, actualText);
			Assert.fail("\n\nValidation Failed:" + "[Expected Text: " + expectedText + "] # [Actual Text: " + actualText
					+ " ]");
		}
	}

	/**
	 * @author Macdaline Fernando This function is used to compare two string
	 * @param expectedText-
	 *            expected Text to be compared with
	 * @param actualText
	 *            -actual Text to be compared with
	 **/
	public static void commonVerify(String expectedText, String actualText) {
		try {
			logger.info("\nValidation Passed: [Expected Text: {} ] # [Actual Text: {} ] ", expectedText, actualText);
			Assert.assertEquals(expectedText, actualText);

		} catch (Exception ex) {
			logger.error("Exception occured: {}", ex.getMessage());
			Assert.assertNotEquals(expectedText, actualText);
			Assert.fail("\nValidation Failed:" + "[Expected Text: " + expectedText + "] # [Actual Text: " + actualText
					+ " ]");
		}
	}

	/**
	 * @author Macdaline Fernando This functions is used to wait for visibility of a
	 *         specific element
	 * @param expr
	 *            - locator of the element
	 **/
	public static void applyVisiblityOfElements(By expr) {

		wait.until(ExpectedConditions.visibilityOfElementLocated(expr));
	}

	/**
	 * @author Macdaline Fernando This functions is used to wait for invisibility of
	 *         a specific element
	 * @param expr
	 *            - locator of the element
	 **/
	public static void applyInVisiblityOfElements(By expr) {

		wait.until(ExpectedConditions.invisibilityOfElementLocated(expr));
	}

	/**
	 * @author Macdaline Fernando This function is used to wait for specified time
	 *         given in seconds
	 * @param unit
	 *            - timeunit in seconds
	 **/
	public static void applyImplicitWait(long unit) {

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(unit));
	}

	/**
	 * @author Macdaline Fernando This function is used to wait for a specific
	 *         element after polling for every 5 seconds
	 * @param expr
	 *            - locator of the element
	 **/
	public static void applyFluentWait(By expr) throws TimeoutException {

		FluentWait<WebDriver> wait = new FluentWait<WebDriver>(driver);

		wait.withTimeout(Duration.ofSeconds(30));
		wait.pollingEvery(Duration.ofSeconds(5));
		wait.ignoring(NoSuchElementException.class);
		wait.ignoring(StaleElementReferenceException.class);
		Function<WebDriver, WebElement> function = new Function<WebDriver, WebElement>() {
			public WebElement apply(WebDriver driver) {
				return driver.findElement(expr);
			}
		};
		wait.until(function);

	}

	/**
	 * @author Akash This function is used to wait for a specific
	 *         element after polling for every 5 seconds for specified Timeout seconds
	 * @param expr
	 *            - locator of the element
	 **/
	public static void applyFluentWait(By expr, int seconds) throws TimeoutException {

		FluentWait<WebDriver> wait = new FluentWait<WebDriver>(driver);

		wait.withTimeout(Duration.ofSeconds(seconds));
		wait.pollingEvery(Duration.ofSeconds(5));
		wait.ignoring(NoSuchElementException.class);
		wait.ignoring(StaleElementReferenceException.class);
		Function<WebDriver, WebElement> function = new Function<WebDriver, WebElement>() {
			public WebElement apply(WebDriver driver) {
				return driver.findElement(expr);
			}
		};
		wait.until(function);

	}
	/**
	 * @author Macdaline Fernando This function is used to wait for the page to be
	 *         fully loaded after polling for every 5 seconds
	 **/
	public static void waitForFullPageLoad() {

		FluentWait<WebDriver> wait = new FluentWait<WebDriver>(driver);

		wait.withTimeout(Duration.ofSeconds(120));
		wait.pollingEvery(Duration.ofSeconds(5));
		wait.ignoring(NoSuchElementException.class);

		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[@id='preload-wrapper']")));

	}

	/**
	 * @author Macdaline Fernando
	 * @param errorMessage
	 *            - Message thrown if comparison fails
	 * @param partialText
	 *            - Text to be compared with
	 * @param expr
	 *            - locator of the element
	 **/
	public static void partialTextVerify(String errorMessage, String partialText, By expr) {
		String fullText = driver.findElement(expr).getText();

		try {
			logger.info("\nValidation Passed: [Expected Text: {} ] # [Actual Text: {} ] ", partialText, fullText);
			Assert.assertTrue(errorMessage, fullText.contains(partialText));

		} catch (Exception ex) {
			logger.error("Exception occured: {}", ex.getMessage());
			Assert.fail("\n\nValidation Failed:" + "[Expected Text: " + fullText
					+ "] # [Actual Text should contain partial of Expected Text: " + partialText + " ]");
		}
	}

	/**
	 * @author Macdaline Fernando This function comparison between the two strings
	 * @param errorMessage
	 *            - Message thrown if comparison fails
	 * @param expectedText
	 *            - Text to be compared with
	 * @param expr-
	 *            locator of the element
	 **/
	public static void exactTextVerify(String errorMessage, String expectedText, By expr) {
		String actualText = driver.findElement(expr).getText().trim();
		try {
			logger.info("\nValidation Passed: [Expected Text: {} ] # [Actual Text: {} ] ", expectedText, actualText);
			Assert.assertTrue(errorMessage, expectedText.equals(actualText));

		} catch (Exception ex) {
			logger.error("Exception occured: {}", ex.getMessage());
			Assert.fail("\n\nValidation Failed:" + "[Expected Text: " + expectedText + "] # [Actual Text : "
					+ actualText + " ]");
		}
	}

	/**
	 * @author Macdaline Fernando This function validates whether the element should
	 *         exist or not based upon the condition passed
	 * @param expr
	 *            - locator of the element
	 * @param condition
	 *            - yes/no value
	 **/
	public static void validateIsExists(By expr, String condition) {

		if (condition.equalsIgnoreCase("no")) {
			try {
				logger.info("Element {} does not exists - expected", expr);
				Assert.assertFalse(expr + " Still present- Expected conditon Failed", isElementPresent(expr));

			}

			catch (Exception ex) {
				logger.debug("Element {} does exists - not expected", expr);
				logger.error("Exception occured: {}", ex.getMessage());
				Assert.fail("Element " + expr + " does  exists - not expected");

			}
		}

		else if (condition.equalsIgnoreCase("yes")) {

			try {
				logger.info("Element {} does not exists - expected", expr);
				Assert.assertTrue(expr + " not present- Expected conditon Failed", isElementPresent(expr));

			}

			catch (Exception ex) {
				logger.debug("Element {} does not exists - not expected", expr);
				logger.error("Exception occured: {}", ex.getMessage());
				Assert.fail("Element " + expr + " does not exists - not expected");

			}
		}

	}

	/**
	 * @author Macdaline Fernando This function returns true or false depending upon
	 *         the presence of the element
	 * @param expr-locator
	 *            of the element
	 * @return boolean value true or false
	 **/
	public static boolean isElementPresent(By expr) {
		try {
			driver.findElement(expr);
			return true;
		} catch (org.openqa.selenium.NoSuchElementException e) {
			return false;
		}
	}

	/**
	 * @author Macdaline Fernando This function is used for click a element
	 * 
	 * @param expr-locator
	 *            of the element
	 **/
	public static void altClick(By expr) {

		try {
			waitForLoad(driver);

			WebElement elementActionPerform = driver.findElement(expr);
			elementActionPerform.click();

		} catch (Exception e) {
			logger.debug("Failed to click on {} ", expr);
			logger.error("Exception occured: {}", e.getMessage());
			Assert.assertEquals("Not Clickable", expr);

		}

	}

	public static void forceClicks(By expr) {

		try {
			waitForLoad(driver);
			WebElement elementActionPerform = driver.findElement(expr);

			if (driver instanceof JavascriptExecutor) {
				((JavascriptExecutor) driver).executeScript("arguments[0].style.border='2px solid red'",
						elementActionPerform);

				((JavascriptExecutor) driver).executeScript("arguments[0].style.border=''", elementActionPerform);
				Actions act = new Actions(driver);

				act.moveToElement(elementActionPerform).click().build().perform();

			}
		} catch (Exception e) {

			logger.debug("Failed to click on {} ", expr);
			logger.error("Exception occured: {}", e.getMessage());
			Assert.assertEquals("Not Clickable", expr);
		}

	}

	public static boolean isElementDisabled(By element, String attribute) {
		boolean result = false;
		WebElement webElement;
		try {
			webElement = driver.findElement(element);
			String attributeValue = webElement.getAttribute(attribute);

			if (attributeValue.contains("disabled") || attributeValue.contains("inactive"))
				result = true;
			else {
				result = false;
			}
		} catch (Exception e) {
			logger.error("Exception occured : {}",e.getMessage());
			Assert.fail("Failed due to exception : "+e.getMessage());
		}
		
		return result;
	}

	public static String getAllAttributesAndValues(By element) {
		WebElement webElement = driver.findElement(element);
		return webElement.getAttribute("outerHTML");

	}
	
	public static String getAllAttributesAndValuesifElementExist(By element) {
		if(driver.findElements(element).size()>0)
		{
			WebElement webElement = driver.findElement(element);
			return webElement.getAttribute("outerHTML");
		}
		else 
		{
			return null;
		}

	}

	public static void pageUrlDoesNotContains(String value) {
		Assert.assertFalse("the title of the page contains value ", driver.getTitle().contains("value"));
	}

	/**
	 * @author Vipin Thonithodiyil This validates the presence of element with a
	 *         certain text under DOM
	 * @param by
	 *            locator
	 * @param text
	 *            to be validated
	 */
	public static void validateElementWithTextExist(By locator, String text) {

		List<WebElement> elements = driver.findElements(locator);
		boolean elementFound = false;

		if (!elements.isEmpty()) {

			for (WebElement ele : elements) {

				if (ele.getText().trim().equalsIgnoreCase(text.trim())) {
					logger.info("Element found with text : {} ", text);
					elementFound = true;
					break;
				}
			}

			if (!elementFound) {
				Assert.fail("Element not found with text " + text);
			}

		} else {
			Assert.fail("Element not found " + locator);
		}

	}

	public static void clickUsingJavaScript(By element) throws InterruptedException {

		WebElement ele = findElement(element);

		int limit = 60;
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		outer: for (int j = 0; j < limit; j++) {
			if (element != null) {
				try {
					executor.executeScript("arguments[0].click();", ele);
					break;
				} catch (Exception e) {
					Thread.sleep(100);
					if (j != (limit - 1)) {
						continue outer;
					} else {

						Assert.fail("Can not be force Click on ::" + ele);
					}
				}
			}
		}
	}

	public static void forceEnterText(By expr, String value) {

		try {
			WebElement element = driver.findElement(expr);
			Actions act = new Actions(driver);
			act.moveToElement(element).sendKeys(value).build().perform();

		} catch (Exception e) {

			logger.debug("Not able to Enter {} ", value);
			logger.error("Exception occured: {} ", e.getMessage());
			Assert.fail("Not able to Enter " + value);
		}

	}

	public static String getText(By expr) {
		return driver.findElement(expr).getText().trim();

	}

	public static void maximizeWindow() {
		driver.manage().window().maximize();
	}

	public static void forceClick(By expr) throws InterruptedException {

		WebElement element = findElement(expr);

		int limit = 60;

		outer: for (int j = 0; j < limit; j++) {
			if (element != null) {
				try {
					wait.until(ExpectedConditions.elementToBeClickable(expr));

					element = driver.findElement(expr);
					element.click();

					element = findElement(expr);
					break;
				} catch (Exception e) {
					Thread.sleep(100);
					if (j != (limit - 1)) {
						continue outer;
					} else {

						Assert.fail("Can not be force Click on ::" + expr);
					}
				}
			}
		}

	}

	public static WebElement findElement(final By expr) {
		try {

			CommonFunctions.waitForJSJQuery(driver);
			Wait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(Duration.ofSeconds(60))
					.pollingEvery(Duration.ofSeconds(5)).ignoring(NoSuchElementException.class)
					.ignoring(WebDriverException.class);

			return wait.until(new Function<WebDriver, WebElement>() {
				public WebElement apply(WebDriver driver) {

					return driver.findElement(expr);
				}
			});

		} catch (Exception e) {
			logger.debug("**Field is not found on the page with name --> {}", expr);
			logger.error("Exception occured: {}", e.getMessage());
			return null;
		}
	}

	public static String getTitlePage() {
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		String actual = (String) jse.executeScript("return document.title");
		return actual;
	}

	public static int getListSize(By element) {
		return driver.findElements(element).size();
	}

	public static List<WebElement> findElements(final By expr) {
		List<WebElement> element = null;
		try {

			CommonFunctions.waitForJSJQuery(driver);
			return driver.findElements(expr);

		} catch (Exception e) {

			logger.error("Field is not found on the page with name -->" + expr);

			return element;
		}
	}

	public static void navigateBack() {
		driver.navigate().back();
	}

	public static int getListElementIndex(By element, String value) throws Throwable {
		System.out.println(driver.findElements(element).size());
		int i = 0;
		while (i < driver.findElements(element).size()) {
			System.out.println(driver.findElements(element).get(i).getText());
			if (driver.findElements(element).get(i).getText().contains(value)) {
				break;
			} else
				i = i + 1;
		}
		return i + 1;
	}

	public static void openNewTab() throws AWTException, InterruptedException {
		waitForParticularTime("3000");
		Robot robot = new Robot();
		robot.keyPress(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_T);
		robot.keyRelease(KeyEvent.VK_T);
		robot.keyRelease(KeyEvent.VK_CONTROL);
	}

	public static void waitForElementToBeDisabled(WebDriver driver, By expr) {
		Function<WebDriver, Boolean> notEnabled = new Function<WebDriver, Boolean>() {
			public Boolean apply(WebDriver driver) {
				return !driver.findElement(expr).isEnabled();
			}
		};
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		wait.until(notEnabled);
	}

	/**
	 * @author Macdaline Fernando This function is used to compare two List
	 * @param expectedText-
	 *            expected Text to be compared with
	 * @param actualText
	 *            -actual Text to be compared with
	 **/
	public static void commonVerify(List<String> expectedText, List<String> actualText) {
		try {			
			Assert.assertEquals(expectedText, actualText);
			logger.info("\nValidation Passed: [Expected Text: {} ] # [Actual Text: {} ] ", expectedText, actualText);

		} catch (Exception ex) {
			logger.error("Exception occured: {}", ex.getMessage());
			Assert.assertNotEquals(expectedText, actualText);
			Assert.fail("\n\nValidation Failed:" + "[Expected Text: " + expectedText + "] # [Actual Text: " + actualText
					+ " ]");
		}
	}

	public static void scrollDown(String value) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0," + value + ")");

	}

	/**
	 * @author Akash This method can be used to double click on an Element
	 * @param by
	 *            locator
	 */

	public static void doubleClick(By expr) {
		Actions a = new Actions(driver);
		a.moveToElement(driver.findElement(expr)).doubleClick().build().perform();
	}

	/**
	 * This method is used to set focus to a scrolling window and scroll the element
	 * in that window
	 * 
	 * @author Abhijit
	 * @param by
	 *            locaterType- Javascript locater like classname, id, etc. of the
	 *            element to set focus on
	 * @param by
	 *            locatervalue - value for locater type of element to set focus
	 * @param by
	 *            scrollToElement - Element to scroll to
	 */
	public static void scrollToWithFocus(String locaterType, String locatervalue, By scrollToElement) {
		if (locaterType.equalsIgnoreCase("classname"))
			locaterType = "getElementsByClassName";
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.document." + locaterType + "('" + locatervalue + "')[0].focus");

		WebElement e = driver.findElement(scrollToElement);
		js.executeScript("arguments[0].scrollIntoView();", e);
	}

	/**
	 * This method returns the current Window Handler
	 * 
	 */
	public static String getWindowHandler() {
		return windowHandler;
	}

	/**
	 * This method sets the current Window Handler value
	 * 
	 */
	public static void setWindowHandler(String windowHandler) {
		CommonFunctions.windowHandler = windowHandler;
	}

	/**
	 * This method will return the selected dropdown value
	 */
	public static String selectedDropdownValue(WebElement el) {
		Select value = new Select(el);
		return value.getFirstSelectedOption().getText();
	}

	/**
	 * This method is used to navigate to the given URL
	 */
	public static void navigatesToUrl(String url) {
		driver.navigate().to(url);
	}

	/**
	 * This method is used to deletes log4j logs files older than 30 days
	 */
	public static void deleteOldLogs() {
		String logsPath = System.getProperty("user.dir");
		logsPath = logsPath + "\\logs";
		System.out.println("logs path is " + logsPath);
		File directory = new File(logsPath);
		if (directory.exists()) {

			File[] listFiles = directory.listFiles();
			long purgeTime = System.currentTimeMillis() - (30 * 24 * 60 * 60 * 1000);
			for (File listFile : listFiles) {
				if (listFile.lastModified() < purgeTime) {
					if (!listFile.delete()) {
						logger.info("Unable to delete file: {}", listFile);
					}
				}
			}
		}

	}
	
	public static void mouseHoverElement(By by) {

		Actions act = new Actions(driver);
		act.moveToElement(driver.findElement(by)).build().perform();

	}
	
	public static void takeScreenshot(WebDriver driver) {
		
		  try { 
	      String remoteExecutionFlag =
		  ReadProperties.readPropertyByName("Config.properties")
		 .getProperty("remoteExecutionFlag"); if
		  (remoteExecutionFlag.equalsIgnoreCase("no")) {
		  CommonFunctions.waitForParticularTime("1500"); final byte[] screenshot =
		  ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
		  scenario.attach(screenshot, "image/png", "After Scenario Error");
		  //System.out.println("no need to capture screenshot"); 
		  } 
		 } catch (Throwable th)
		  { th.printStackTrace(); }
		 

	}
	
	/**
	 * It is used to verify page Url
	 * 
	 * @param urlContent-
	 *            content to match with url
	 */
	public static boolean verifyPageUrl(String urlContent) {
      boolean flag=driver.getCurrentUrl().contains(urlContent);
		return flag;
	}
	
	/**
	 * It is used to do mouse hover and click using actions class
	 * 
	 * @param element_MoveTo-
	 *            web element to hover over
	 * @param elementToClick
	 *            - web element to click onto
	 * @throws TimeoutException
	 * @throws InterruptedException
	 */
	public static void mousehoverClick(By element_MoveTo, By elementToClick)
			throws TimeoutException, InterruptedException {
		try {
			WebElement elementmove = driver.findElement(element_MoveTo);
			WebElement elementclick = driver.findElement(elementToClick);
			Actions toolAct = new Actions(driver);
			toolAct.moveToElement(elementmove).click(elementclick).build().perform();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	/**
	 * It is used to wait for the element to be click able
	 * 
	 * @param element-
	 *            web element
	 */
	public static void waitFor_Element_ToBe_Clickable_Export(By element) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(120));
			wait.until(ExpectedConditions.visibilityOfElementLocated(element));
			wait.until(ExpectedConditions.elementToBeClickable(element));
		} catch (Exception e) {
			logger.error("Exception occured: {}", e.getMessage());
			Assert.fail("Element not found" + element);

		}
	}
	
	/**
	 * It checks the file from a specific directory with extension and delete
	 * 
	 * @param dirPath-
	 *            path where downloaded file gets placed
	 * @param ext-
	 *            extension
	 * @return -true/false
	 */
	public static boolean verifyFileDeleted(String dirPath, String ext) {
		boolean flag = false;
		try {
			File dir = new File(dirPath);
			File[] files = dir.listFiles();
			for (int i = 0; i < files.length; i++) {
				if (files[i].getName().contains(ext)) {
					files[i].delete();
					return flag = true;
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}
	
	/**
	 * It checks the file from a specific directory with extension
	 * 
	 * @param dirPath-
	 *            path where downloaded file gets placed
	 * @param ext-
	 *            extension
	 * @return -true/false
	 */
	public static boolean verifyFileDownloaded(String dirPath, String ext) {
		boolean flag = false;
		try {
			File dir = new File(dirPath);
			File[] files = dir.listFiles();
			if (files == null || files.length == 0) {
				flag = false;
			}

			for (int i = 0; i < files.length; i++) {
				if (files[i].getName().contains(ext)) {
					flag = true;
					break;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}
	
	/**
	 * It is used to enter text on the Web element using action class by clearing textbox
	 * 
	 * @param expr
	 * @param value-
	 *            value to be entered
	 * @throws TimeoutException
	 * @throws InterruptedException
	 */
	public static void enterClearTextUsingAction(By expr, String value) {

		try {
			WebElement toClear = driver.findElement(expr);
			toClear.sendKeys(Keys.chord(Keys.CONTROL + "a", Keys.DELETE));
			waitFor_Element_ToBe_Clickable(expr);
			WebElement element = driver.findElement(expr);
			Actions builder = new Actions(driver);
			builder.moveToElement(element).click().sendKeys(value).build().perform();
		} catch (Exception e) {
			logger.debug("Not able to Enter {}", value);
			logger.error("Exception occured: {}", e.getMessage());
			Assert.fail("Not able to Enter " + value);

		}
	}
	
	public static void pause() {
		try {
			String waitTimeMsecs = ReadProperties.readPropertyByName("Config.properties").getProperty("sleep_interval_msecs");
			CommonFunctions.waitForParticularTime(waitTimeMsecs);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}
	
	public static void clearElement(By element, String attribute) throws Throwable {
		driver.findElement(element).clear();
	}

	public static void pressEnter(By expr) throws Throwable {

		try {
			CommonFunctions.waitForParticularTime("2000");
			WebElement ElementActionPerform = driver.findElement(expr);

			ElementActionPerform.sendKeys(Keys.ENTER);
			CommonFunctions.waitForParticularTime("500");
		} catch (Exception e) {
			logger.info("Failed to click on" + expr);
			Assert.assertEquals("Not Clickable", expr);
			e.getMessage().toString();
		}
	}
	 public static boolean checkPageUrl( String value ) {
			
			logger.info(driver.getCurrentUrl());
			return driver.getCurrentUrl().contains(value);
		}
	 
	 public static void commonVerify(Boolean expression) {
			try {

				Assert.assertTrue(expression);
				logger.info("\n\nExpression validation Passed:" + "[Expected: " + expression.toString()
						+ "] # [Actual: " + expression.toString() + " ]\n");

			} catch (Exception ex) {
				Assert.assertFalse(expression);
				Assert.fail("\n\nExpression validation Failed:" + "[Expected: " + expression.toString() + "] # [Actual: "
						+ expression.toString() + " ]\n");
			}
		}
	 
	 public static void verifyPdfOpenedOnNewTab(String urltext) {

			try {
				Set<String> windows = driver.getWindowHandles();
				for (String window : windows) {
					if (!window.equalsIgnoreCase(CommonFunctions.getWindowHandler())) {
						driver.switchTo().window(window);
						CommonFunctions.validatePageUrl(urltext);
						CommonFunctions.waitForParticularTime("1000");
						driver.close();
						break;
					}
				}

				driver.switchTo().window(CommonFunctions.getWindowHandler());

			} catch (Exception e) {
				e.printStackTrace();
				driver.switchTo().window(CommonFunctions.getWindowHandler());
			}
		}
	 
	 /**
		 * It is used to validate calendar mm/dd/yyyy
		 *  @param date
		 * */
	 
	 public static void validate_Calendar_Format(String date) {
			try {
				
				if (!(date.matches("^[0-3]?[0-9]/[0-3]?[0-9]/(?:[0-9]{2})?[0-9]{2}$")))
					Assert.assertEquals("Not Matched", date);
			} catch (Exception e) {

				logger.error("Exception occured: {}", e.getMessage());
			}
		}
	 
		/**
		 * It is used to read csv file
		 * 
		 * @param filename
		 */
	 
		public static int readCSVFile(String fileName) throws IOException, CsvException {
			int actuallistsize = 0;
			String expectedfilepath = localDownloadPath + fileName;
			try {
				CSVReader reader = new CSVReader(new FileReader(expectedfilepath));
				List<String[]> actuallist = reader.readAll();
				actuallistsize = actuallist.size();
				if (actuallist.size() != 0) {
					Iterator<String[]> al = actuallist.iterator();
					while (al.hasNext()) {
						String[] str = al.next();
						System.out.print(" Values are ");
						for (int i = 0; i < str.length; i++) {

							System.out.print(" " + str[i]);
						}
						System.out.println("   ");
					}
				}
				System.out.println("Total rows in CSV file is " + actuallistsize);
			} catch (Exception e) {
				logger.error("Exception occured: {}", e.getMessage());
				Assert.fail("File Path Not Found " + expectedfilepath);
			}
			return actuallistsize;
		}
		
		/**
		 * It is used to return filename
		 * 
		 * @param dirPath-
		 *            path where downloaded file gets placed
		 * @param ext-
		 *            extension
		 * @return -true/false
		 */
		public static String returnFilenameDownloaded(String dirPath, String ext) {
			String filename = null;
			try {
				File dir = new File(dirPath);
				File[] files = dir.listFiles();
				for (int i = 0; i < files.length; i++) {
					if (files[i].getName().contains(ext)) {
						filename = files[i].getName();
					}
				}
			} catch (Exception e) {
				logger.error("Exception occured: {}", e.getMessage());
				Assert.fail("File Path Not Found " + dirPath);
			}
			return filename;
		}
		
		/**
		 * It is used to validate url link opened on new tab
		 * 
		 * @param urltext to be validated
		 *           
		 */
		
		public static void verifyLinkOpenedOnNewTab(String urltext) {
			String mainwindowhandle = driver.getWindowHandle();
			try {
				Set<String> windows = driver.getWindowHandles();
				for (String window : windows) {
					if (!window.equalsIgnoreCase(mainwindowhandle)) {
						driver.switchTo().window(window);
						CommonFunctions.validatePageUrl(urltext);
						CommonFunctions.waitForParticularTime("1000");
						driver.close();
						break;
					}
				}

				driver.switchTo().window(mainwindowhandle);

			} catch (Exception e) {
				e.printStackTrace();
				driver.switchTo().window(mainwindowhandle);
			}
		}
	}