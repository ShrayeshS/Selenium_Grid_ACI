package utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import io.cucumber.java.Scenario;

public class ReadProperties {

	static String env1 = System.getProperty("env");

	public static Properties readPropertyByName(String propertyName) {

		Properties prop = new Properties();
		File dir1 = new File(".");

		if (env1 == null || env1.isEmpty()){
			env1 = "WCM";
		}

		if (env1.equals("STG") && propertyName.equals("Testdata.properties")) {
			propertyName = "TestdataSTG.properties";
		}
		if (env1.equals("QCM") && propertyName.equals("Testdata.properties")) {
			propertyName = "TestdataQCM.properties";
		}
		if (env1.equals("VCM") && propertyName.equals("Testdata.properties")) {
			propertyName = "TestdataVCM.properties";
		}
		if (env1.equals("RCM") && propertyName.equals("Testdata.properties")) {
			propertyName = "TestdataRCM.properties";
		}
		if (env1.equals("WCM") && propertyName.equals("Testdata.properties")) {
			propertyName = "TestdataWCM.properties";
		}

		try {
			
			String folder = "";
			
			if(propertyName.equalsIgnoreCase("Config.properties"))
				//folder = "src\\test\\resources\\config";
				folder = "src/test/resources/config";
			else
				//folder = "src\\test\\resources\\testData";
				folder = "src/test/resources/testData";
			
			prop.load(new FileInputStream(
					dir1.getCanonicalPath() + File.separator + folder + File.separator + propertyName));
		} catch (FileNotFoundException e) {
			System.out.println(e.getMessage());
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
		return prop;

	}

	public static void display(Scenario scenario, String abc) {
		scenario.log("order Number is : " + abc);
	}
	
	public static String getEnvironmentname()
	{
		return env1;
	}
}