package com.qa.automation.getpageobjects;

import static com.qa.automation.utils.DataReadWrite.getProperty;
import java.util.Set;
import org.openqa.selenium.WebDriver;
import org.testng.Reporter;
import com.qa.automation.utils.SeleniumWait;

public class BaseUi {

	protected WebDriver driver;
	protected SeleniumWait wait;
	private int timeout;
	private Set<String> windows;
	private String wins[];

	protected BaseUi(WebDriver driver) {
		this.driver = driver;
		timeout = Integer.parseInt(getProperty("./Config.properties", "timeout"));
		this.wait = new SeleniumWait(driver, timeout);
	}

	public void logMessage(String string) {
		Reporter.setEscapeHtml(false);
		Reporter.log(string, true);
	}

	public String getCurrentURL() {
		return driver.getCurrentUrl();
	}

	public void hardWait(int seconds) {
		try {
			Thread.sleep(seconds * 1000);
		} catch (InterruptedException ex) {
			ex.printStackTrace();
		}
	}

	public void changeWindow(int i) {
		windows = driver.getWindowHandles();
		if (i > 0) {
			for (int j = 0; j < 9; j++) {
				System.out.println("Windows: " + windows.size());
				if (windows.size() >= 2) {
					try {
						Thread.sleep(5000);
					} catch (Exception ex) {
						ex.printStackTrace();
					}
					break;
				}
				windows = driver.getWindowHandles();
				System.out.println("Windows After: " + windows.size());
			}
		}
		wins = windows.toArray(new String[windows.size()]);
		driver.switchTo().window(wins[i]);
		System.out.println("Title: " + driver.switchTo().window(wins[i]).getTitle());
	}

	public String switchWindow() {
		String currentWindow = driver.getWindowHandle();
		Set<String> windows = driver.getWindowHandles();
		System.out.println("Windows:" + driver.getWindowHandles().size());
		for (String winHandle : windows) {
			driver.switchTo().window(winHandle);
			System.out.println("Window Switched." + driver.getTitle() + " handle window :" + driver.getWindowHandle());
		}
		return currentWindow;
	}

	public void moveToDefaultWindow(String defaultWIndow) {
		driver.close();
		driver.switchTo().window(defaultWIndow);
	}

	public void closeWindow() {
		driver.close();
	}
}