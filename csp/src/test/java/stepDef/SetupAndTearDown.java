package stepDef;

import java.awt.Color;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.time.Duration;

import javax.imageio.ImageIO;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import utils.CommonFunctions;
import utils.DriverFactory;
import utils.ReadProperties;

public class SetupAndTearDown {

	public static Scenario scenario;
	public static WebDriver driver;
	By usernameInput = By.xpath("//input[@id='logonuidfield']");
	String driverResetCount = ReadProperties.readPropertyByName("Config.properties").getProperty("driverResetCount");
	public static int i = 0;

	@Before
	public void startdriver(Scenario scenario) throws Exception {
		SetupAndTearDown.scenario = scenario;
		if (i == Integer.parseInt(driverResetCount)) {
			i = 0;
			driver.close();
			driver.quit();
			driver = null;
		}
		if (driver == null) {
			driver = DriverFactory.createDriver();
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		}
		i++;
	}

	public static WebDriver getDriver() {
		return driver;
	}

	@After
	public void afterScenario(Scenario scenario) {
		System.out.println("scenarios failed -->" + scenario.isFailed());

		try {
			if (scenario.isFailed()) {
				final byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
				byte[] bytes = convertImage(screenshot);
				scenario.attach(bytes, "image/jpeg", "Scenario Failed Img");
				driver.close();
				driver.quit();
				driver = null;
				i = 0;
			}
		} catch (Exception e) {
		}

		finally {
			if (!scenario.isFailed()) {
				if (CommonFunctions.isElementPresent(usernameInput)) {
					driver.close();
					driver.quit();
					driver = null;
					i = 0;
				}
			}
		}
	}

	private static byte[] convertImage(byte[] pngBytes) throws IOException {
		double redFactor = 0.6;
		ByteArrayInputStream bais = new ByteArrayInputStream(pngBytes);
		BufferedImage bufferedImage = ImageIO.read(bais);

		int width = (int) (bufferedImage.getWidth() * redFactor);
		int height = (int) (bufferedImage.getHeight() * redFactor);
		Image scaledImage = bufferedImage.getScaledInstance(width, height, Image.SCALE_SMOOTH);
		BufferedImage imageBuff = new BufferedImage(width, height, BufferedImage.TYPE_BYTE_INDEXED);
		imageBuff.getGraphics().drawImage(scaledImage, 0, 0, new Color(0, 0, 0), null);

		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		ImageIO.write(imageBuff, "JPG", baos);
		return baos.toByteArray();
	}
}