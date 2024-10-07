package pageobjects;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.Color;

import java.util.concurrent.TimeoutException;
import utils.CommonFunctions;
import utils.Constants;
import utils.ReadProperties;

public class LandingPage_PageObject extends BasePage {

	By firstValue=By.xpath("//input[contains(@id,'value1')]");
	By secondvalue=By.xpath("//input[contains(@id,'value2')]");
	By result=By.xpath("//input[contains(@id,'result')]");
	public void enterValues(String value1, String value2) {
		WebElement ele1=driver.findElement(firstValue);
		WebElement ele2=driver.findElement(secondvalue);
		ele1.sendKeys(value1);
		ele2.sendKeys(value2);
	}
	public void clearValues() {
		WebElement ele1=driver.findElement(firstValue);
		WebElement ele2=driver.findElement(secondvalue);
		WebElement results=driver.findElement(result);
		ele1.clear();
		ele2.clear();
		results.clear();
	}
	public void mathematicOperations(String operation ) {
		if(operation.equalsIgnoreCase("add")) {
			CommonFunctions.greenHighLight(driver.findElement(By.xpath("//input[contains(@value,'"+operation+"')]")));
		driver.findElement(By.xpath("//input[contains(@value,'"+operation+"')]")).click();
		}else if(operation.equalsIgnoreCase("sub")) {
			CommonFunctions.greenHighLight(driver.findElement(By.xpath("//input[contains(@value,'"+operation+"')]")));
			driver.findElement(By.xpath("//input[contains(@value,'"+operation+"')]")).click();
		}
		
	
	   else if(operation.equalsIgnoreCase("mul")) {
		   CommonFunctions.greenHighLight(driver.findElement(By.xpath("//input[contains(@value,'"+operation+"')]")));
		   driver.findElement(By.xpath("//input[contains(@value,'"+operation+"')]")).click();
      }
       else if(operation.equalsIgnoreCase("divi")) {
    	   CommonFunctions.greenHighLight(driver.findElement(By.xpath("//input[contains(@value,'"+operation+"')]")));
    	   driver.findElement(By.xpath("//input[contains(@value,'"+operation+"')]")).click();
       }

}
	public void validateOutput(String output) {
		try {
			CommonFunctions.waitForParticularTime("3000");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		String resultTxt=driver.findElement(result).getAttribute("value");
		if(resultTxt.equals(output)) {
			Assert.assertTrue(true);
		//	driver.quit();
		}
		else {
			Assert.fail("expected output is different from actual");
		}
		
		
	}

	}