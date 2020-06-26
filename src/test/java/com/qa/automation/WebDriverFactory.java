/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.qa.automation;

import org.openqa.selenium.WebDriver;
import java.net.MalformedURLException;
import java.net.URL;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import io.github.bonigarcia.wdm.WebDriverManager;
import static com.qa.automation.utils.ConfigPropertyReader.getProperty;

public class WebDriverFactory {
	
	public static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<WebDriver>();
	
	public static synchronized WebDriver getDriver() {
		return tlDriver.get();
	}

	public WebDriver getDriver(String browserName) {
		if (browserName.equalsIgnoreCase("chrome")) {
			WebDriverManager.chromedriver().setup();
			if (Boolean.parseBoolean(getProperty("remote"))) {
				init_remoteWebDriver(browserName);
			}
			else {
				tlDriver.set(new ChromeDriver());
			}

		} else if (browserName.equalsIgnoreCase("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			if (Boolean.parseBoolean(getProperty("remote"))) {
				init_remoteWebDriver(browserName);
			}
			else {
				tlDriver.set(new FirefoxDriver());
				
			}
		}
		return getDriver();
	}
	
	private void init_remoteWebDriver(String browserName) {
		if (browserName.equalsIgnoreCase("chrome")) {
			DesiredCapabilities cap = DesiredCapabilities.chrome();
			cap.setCapability(ChromeOptions.CAPABILITY, false);
			try {
				tlDriver.set(new RemoteWebDriver(new URL(getProperty("huburl")), cap));
			} catch (MalformedURLException e) {
				e.printStackTrace();
			}
		}

		else if (browserName.equalsIgnoreCase("firefox")) {
			DesiredCapabilities cap = DesiredCapabilities.firefox();
			cap.setCapability(FirefoxOptions.FIREFOX_OPTIONS, false);
			try {
				tlDriver.set(new RemoteWebDriver(new URL(getProperty("huburl")), cap));
			} catch (MalformedURLException e) {
				e.printStackTrace();
			}
		}
	}
}

		
//		public String getScreenshot() {
//
//			File src = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.FILE);
//			String path = System.getProperty("user.dir") + "/screenshots/" + System.currentTimeMillis() + ".png";
//			File destination = new File(path);
//			try {
//				FileUtils.copyFile(src, destination);
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
//
//			return path;
//
//		}