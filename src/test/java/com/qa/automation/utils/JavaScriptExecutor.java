package com.qa.automation.utils;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.automation.getpageobjects.BaseUi;


public class JavaScriptExecutor extends BaseUi {

	public JavaScriptExecutor(WebDriver driver) {
		super(driver);
	}

	public static void drawBorder(WebElement element, WebDriver driver) {
		JavascriptExecutor js = ((JavascriptExecutor) driver);
		js.executeScript("arguments[0].style.border='3px solid red'", element);
	}

	public static void generateAlert(WebDriver driver, String message) {
		JavascriptExecutor js = ((JavascriptExecutor) driver);
		js.executeScript("alert('" + message + "')");

	}

	public static void clickElementByJS(WebElement element, WebDriver driver) {
		JavascriptExecutor js = ((JavascriptExecutor) driver);
		js.executeScript("arguments[0].click();", element);

	}

	public static void refreshBrowserByJS(WebDriver driver) {
		JavascriptExecutor js = ((JavascriptExecutor) driver);
		js.executeScript("history.go(0)");
	}

	public static String getTitleByJS(WebDriver driver) {
		JavascriptExecutor js = ((JavascriptExecutor) driver);
		String title = js.executeScript("return document.title;").toString();
		return title;
	}

	public static String getPageInnerText(WebDriver driver) {
		JavascriptExecutor js = ((JavascriptExecutor) driver);
		String pageText = js.executeScript("return document.documentElement.innerText;").toString();
		return pageText;
	}

	public static void scrollPageDown(WebDriver driver) {
		JavascriptExecutor js = ((JavascriptExecutor) driver);
		js.executeScript("window.scrollTo(0,document.body.scrollHeight)");
	}

	public static void scrollIntoView(WebElement element, WebDriver driver) {
		JavascriptExecutor js = ((JavascriptExecutor) driver);
		js.executeScript("arguments[0].scrollIntoView(true);", element);
		js.executeScript("window.scrollBy(0,-350)");
	}

	protected void scrollDown(WebElement element, WebDriver driver) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0, -100000)");
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
		js.executeScript("window.scrollBy(0,-350)");
	}

	public static void flashAndClick(WebElement element, WebDriver driver) {
		String bgcolor = element.getCssValue("backgroundColor");
		changeColor("rgb(255,165,0)", element, driver);
		changeColor(bgcolor, element, driver);
	}

	public static void flashInputBox(WebElement element, WebDriver driver) {
		String bgcolor = element.getCssValue("backgroundColor");
		changeColor("rgb(113,173,226)", element, driver);
		changeColor(bgcolor, element, driver);
	}

	public static void flashAndVerify(WebElement element, WebDriver driver) {
		String bgcolor = element.getCssValue("backgroundColor");
		for (int i = 0; i < 3; i++) {
			changeColor("rgb(255,255,0)", element, driver);
			changeColor(bgcolor, element, driver);
		}
	}

	public static void navigateBackUsingJS(WebDriver driver) {
		JavascriptExecutor js = ((JavascriptExecutor) driver);
		js.executeScript("window.history.back();");
	}
	
	public static void changeColor(String color, WebElement element, WebDriver driver) {
		JavascriptExecutor js = ((JavascriptExecutor) driver);
		js.executeScript("arguments[0].style.backgroundColor = '" + color + "'", element);

		try {
			Thread.sleep(75);
		} catch (InterruptedException e) {
		}
	}
}