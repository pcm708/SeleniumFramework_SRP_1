package com.qa.automation.utils;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;

public class SeleniumWait {

	WebDriver driver;
	WebDriverWait wait;
	public int timeout;

	public SeleniumWait(WebDriver driver, int timeout) {
		this.driver = driver;
		this.wait = new WebDriverWait(driver, timeout);
		this.timeout = timeout;
	}

	public int getTimeout() {
		return timeout;
	}

	public void hardWait(int seconds) {
		try {
			Thread.sleep(seconds * 1000);
		} catch (InterruptedException ex) {
			ex.printStackTrace();
		}
	}

	public boolean waitForPageTitleToBeExact(String expectedPagetitle) {
		boolean flag = wait.until(ExpectedConditions.titleIs(expectedPagetitle));
		return flag;
	}

	public void waitForPageToLoadCompletely() {
		wait.until(new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver wdriver) {
				return ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete");
			}
		});
		Reporter.log("Page loaded completely");
	}

	public boolean waitForElementToBeInVisible(By locator) {
		return wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
	}

	public WebElement waitForElementToBeClickable(WebElement element) {
        return wait.until(ExpectedConditions.elementToBeClickable(element));
    }
	
	public WebElement waitForElementToBeVisible(WebElement element) {
		try {
			return wait.until(ExpectedConditions.visibilityOf(element));
		} catch (StaleElementReferenceException e) {
			return wait.until(ExpectedConditions.visibilityOf(element));
		}
	}
	
	public boolean isAlertPresent(){
		try{
			wait.until(ExpectedConditions.alertIsPresent());
            return true;
        }
        catch(Exception e){
            return false;
        }
	}

	public void resetImplicitTimeout(int newTimeOut) {
		try {
			driver.manage().timeouts().implicitlyWait(newTimeOut, TimeUnit.SECONDS);
		} catch (Exception e) {
		}
	}
}