package com.qa.automation.getpageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.UnhandledAlertException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import static com.qa.automation.utils.DataReadWrite.getProperty;
import static com.qa.automation.utils.JavaScriptExecutor.flashAndClick;
import static com.qa.automation.utils.JavaScriptExecutor.flashInputBox;
import static com.qa.automation.utils.JavaScriptExecutor.scrollIntoView;
import static com.qa.automation.utils.JavaScriptExecutor.clickElementByJS;
import static com.qa.automation.utils.JavaScriptExecutor.flashAndVerify;

public class GetPage extends BaseUi {

	protected WebDriver driver;

	public GetPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
	}

	protected WebElement element(By locator) {
		return driver.findElement(locator);
	}

	protected List<WebElement> elements(By locator) {
		return driver.findElements(locator);
	}

	protected String getText(WebElement element) {
		String text= null;
		try {
			return element.getText();
		}catch(Exception e) {
			logMessage("[Exception]: "+e.getMessage()+" while fecthing the element's text");
		}
		return text;
	}

	protected boolean isElementDisplayed(WebElement element) {
		wait.waitForPageToLoadCompletely();
		wait.resetImplicitTimeout(10);
		wait.waitForElementToBeVisible(element);
		try {
		if(getProperty("Config.properties", "highlightElement").equals("true"))	
			flashAndVerify(element, driver);
		return element.isDisplayed();
		}
		catch(NoSuchElementException e) {
			logMessage("[Info]: No such element present in the DOM");
		}
		wait.resetImplicitTimeout(wait.getTimeout());
		return false;
	}
	
	protected void fillText(WebElement element, String inputResults) {
		try {
		wait.waitForElementToBeVisible(element);
		if(getProperty("Config.properties", "highlightElement").equals("true"))	
			flashInputBox(element, driver);
		element.clear();
		element.sendKeys(inputResults);
		}catch(Exception e) {
			logMessage("[Exception]: "+e.getMessage()+" while filling element's text");
		}
	}
	
	public void click(WebElement element) {
		wait.waitForPageToLoadCompletely();
		wait.waitForElementToBeVisible(element);
		try {
			if(getProperty("Config.properties", "highlightElement").equals("true"))	
			flashAndClick(element, driver);
			element.click();
		} catch (StaleElementReferenceException ex1) {
			scrollIntoView(element, driver);
			flashAndClick(element, driver);
			element.click();
			logMessage("Clicked Element " + element + " after catching Stale Element Exception");
		} catch (UnhandledAlertException u) {
			logMessage("Alert present after the Click Event");
		} catch (ElementClickInterceptedException e) {
			clickElementByJS(element, driver);
		}
	}
	
	protected void selectByValueFromDropDown(WebElement el, String value) {
		wait.hardWait(1);
		wait.waitForElementToBeVisible(el);
		flashInputBox(el, driver);
		Select sel = new Select(el);
		sel.selectByValue(value);
	}

	protected void selectByTextFromDropDown(WebElement el, String text) {
		hardWait(1);
		wait.waitForElementToBeVisible(el);
		flashInputBox(el, driver);
		Select sel = new Select(el);
		sel.selectByVisibleText(text);
	}
	
	protected void hitParticularKey(By element, Keys enter) {
		element(element).sendKeys(Keys.ENTER);
	}
	
	public void hoverOverElement(WebElement ele) {
		Actions hoverOver = new Actions(driver);
		hoverOver.moveToElement(ele).build().perform();
	}

	public boolean verifyPageTitle(String title) {
		return wait.waitForPageTitleToBeExact(title);
	}
	
	public void navigateBack() {
		wait.waitForPageToLoadCompletely();
		hardWait(1);
		driver.navigate().back();
		logMessage("Page has navigated back in the browser's history");
	}

	public void navigateForward() {
		driver.navigate().forward();
		logMessage("Page has navigated forward in the browser's history");
	}
	
	public void refreshPage() {
		driver.navigate().refresh();
		logMessage("Page refreshed by Webdriver");
		wait.waitForPageToLoadCompletely();
	}

	protected void doubleClick(WebElement element) {
		Actions act = new Actions(driver);
		flashAndClick(element, driver);
		act.doubleClick(element).perform();
	}
	
	protected void selectText(WebElement element) {
		Actions action = new Actions(driver);
		action.clickAndHold(element).build().perform();
		action.release().perform();
		action.clickAndHold(element).build().perform();
		action.moveToElement(element, 0, 0).build().perform();
		action.release().build().perform();
	}
	
	protected void hover(WebElement element) {
		Actions hoverOver = new Actions(driver);
		hoverOver.moveToElement(element).build().perform();
		logMessage("Hovered on element:" + element);
	}

	protected void hoverClick(WebElement element) {
		Actions hoverClick = new Actions(driver);
		hoverClick.moveToElement(element).click().build().perform();
		logMessage("Clicked on PublishModule");
	}
	
	public void DragAndDropUsingAction(WebElement fromElem, WebElement toElem) {
		Actions builder = new Actions(driver);
		Action dragAndDrop = builder.clickAndHold(fromElem)
				.moveToElement(toElem)
				.release(toElem)
				.build();
		dragAndDrop.perform();
	}
	
	public boolean isElementNotThere(WebElement elementToken) {
		boolean present;
		try {
			isElementDisplayed(elementToken);
			present = false;
		} catch (NoSuchElementException e) {
			present = true;
		}
		return present;
	}
}