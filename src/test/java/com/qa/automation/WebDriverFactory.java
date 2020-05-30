/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.qa.automation;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import io.github.bonigarcia.wdm.WebDriverManager;

public class WebDriverFactory {

	public WebDriver getDriver(String browserName) {
			switch (browserName) {
			case "FF":
			case "ff":
			case "firefox":
			case "FIREFOX":
				WebDriverManager.firefoxdriver().setup();
				System.setProperty(FirefoxDriver.SystemProperty.BROWSER_LOGFILE, "null");
				System.out.println("[Info]: Browser Launched = Firefox");
				return new FirefoxDriver();
				
			case "chrome":
			case "CHROME":
				WebDriverManager.chromedriver().setup();
				System.out.println("[Info]: Browser Launched = Google Chrome");
				System.setProperty("webdriver.chrome.silentOutput", "true");
				return new ChromeDriver();
				
			default:
				WebDriverManager.chromedriver().setup();
				System.out.println("[Info]: Browser Launched = Google Chrome");
				System.setProperty("webdriver.chrome.silentOutput", "true");
				return new ChromeDriver();
			}
		}
}