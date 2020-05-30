/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.qa.automation;

import static com.qa.automation.utils.ConfigPropertyReader.getProperty;
import static com.qa.automation.utils.YamlReader.getYamlValue;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.testng.Reporter;

public class TestSessionInitiator {

	protected WebDriver driver;
	private WebDriverFactory wdfactory;
	private int timeout;

	public TestSessionInitiator() {
		wdfactory = new WebDriverFactory();
		configureBrowser();
	}

	private static Map<String, String> _getSessionConfig() {
		String[] configKeys = { "browser", "timeout", "chromeDubugMode" };
		Map<String, String> config = new HashMap<String, String>();
		for (String string : configKeys) {
			config.put(string, getProperty(string));
		}
		return config;
	}
	
	public boolean getDebugMode() {
		String value = _getSessionConfig().get("chromeDubugMode");
		if (value == null || Boolean.parseBoolean(value) == false)
			return false;
		else
			return true;
	}

	protected void configureBrowser() {
		driver = wdfactory.getDriver(_getSessionConfig().get("browser"));
		driver.manage().window().maximize();
		timeout = Integer.parseInt(_getSessionConfig().get("timeout"));
		driver.manage().timeouts().implicitlyWait(timeout, TimeUnit.SECONDS);
	}

	public String getBrowser() {
		String browser =  _getSessionConfig().get("browser");
		if(browser.equalsIgnoreCase("ff")||browser.equalsIgnoreCase("firefox"))
			return "Firefox";
		else 
			return "Chrome";
	}

	public String getTakeScreenshot() {
		return _getSessionConfig().get("takeScreenshot");
	}

	public void launchApplication() {
		launchApplication(getYamlValue("app_url"));
	}

	public void launchAchieveApplication() {
		launchApplication(getYamlValue("achieve_url"));
	}

	public void launchLMSRequestForm() {
		launchApplication(getYamlValue("form_url"));
	}

	public void launchApplicationForSapling() {
		launchApplication(getYamlValue("sap_app_url"));
	}

	public void launchApplication(String applicationpath) {
		Reporter.log("Application url is :- " + applicationpath, true);
		Reporter.log("Test browser is :- " + getBrowser(), true);
		driver.manage().deleteAllCookies();
     	driver.manage().window().maximize();
		driver.get(applicationpath);
	}

	public void getURL(String url) {
		driver.get(url);
	}

	public void setWindowSize(int width, int height) {
		driver.manage().window().setSize(new Dimension(width, height));
	}

	public void closebrowserSession() {
		driver.quit();
	}

	public String getPageTitle() {
		return driver.getTitle();
	}

	public void stepStartMessage(String testStepName) {
		Reporter.log(" ", true);
		Reporter.log("***** STARTING TEST STEP:- " + testStepName.toUpperCase() + " *****", true);
		Reporter.log(" ", true);
	}

	public void refreshPage() {
		driver.navigate().refresh();
	}

	public void closeWindow() {
		driver.close();
	}
}