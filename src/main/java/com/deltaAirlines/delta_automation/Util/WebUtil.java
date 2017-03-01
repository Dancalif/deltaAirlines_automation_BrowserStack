package com.deltaAirlines.delta_automation.Util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WebUtil {

	// to wait for element to be visible
	public static void waitForElementVisible(WebDriver driver, By locator) {
		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
	}

	// to verify if element is displayed
	public static boolean ifElementDisplayed(WebDriver driver, By locator) {
		boolean ifDisplayed = driver.findElement(locator).isDisplayed();
		return ifDisplayed;
	}

	// to click
	public static void click(WebDriver driver, By locator) {
		WebElement clickableElement = driver.findElement(locator);
		clickableElement.click();
	}

	// to enter
	public static void input(WebDriver driver, String input, By locator) {
		WebElement inputField = driver.findElement(locator);
		inputField.clear();
		inputField.sendKeys(input);
	}

	// to get hidden element visible to perform the actions with it
	public static void getElementVisible(WebDriver driver, By locator) throws Exception {
		WebElement elmentToBeVisible = driver.findElement(locator);
		elmentToBeVisible.sendKeys(Keys.PAGE_DOWN);
		Thread.sleep(1000);
		try {
			WebDriverWait wait = new WebDriverWait(driver, 30);
			wait.until(ExpectedConditions.elementToBeClickable(locator));
		} catch (Exception e) {
			elmentToBeVisible.sendKeys(Keys.PAGE_UP);
			Thread.sleep(1000);
			WebDriverWait wait = new WebDriverWait(driver, 30);
			wait.until(ExpectedConditions.elementToBeClickable(locator));
		}
	}

	// to generate random date and convert it to String
	public static String randDate(int daysNum, int days) {
		Date myDate = new Date();
		Calendar cal = Calendar.getInstance();
		// cal.setTime(myDate);
		cal.add(Calendar.DATE, (days + 1 + randNumber(daysNum)));
		myDate = cal.getTime();
		SimpleDateFormat sdfr = new SimpleDateFormat("MM/dd/yyyy");
		String departureDate = sdfr.format(myDate);
		return departureDate;
	}

	// to generate random number
	public static int randNumber(int maxNumber) {
		Random rand = new Random();
		int randomNumber = rand.nextInt(maxNumber);
		return randomNumber;
	}

	// to verify if element exists
	public static void verifyIfElementExists(WebDriver driver, By locator, String message) {
		WebElement elementToBeFound = driver.findElement(locator);
		boolean ifExists = elementToBeFound.isDisplayed();
		org.testng.Assert.assertTrue(ifExists, message);
	}

	public static List<WebElement> createListOfElements(WebDriver driver, By locator) {
		List<WebElement> listOfElements = driver.findElements(locator);
		return listOfElements;

	}

	public static String myParser(WebDriver driver, By locator, String myPattern) {
		String parsedItem = driver.findElement(locator).getText();
		Pattern pattern = Pattern.compile(myPattern);
		Matcher matcher = pattern.matcher(parsedItem);
		while (matcher.find()) {
			parsedItem = matcher.group(1);
		}
		return parsedItem;
	}

	public static void mouseOver(WebDriver driver, By locator) {
		Actions action = new Actions(driver);
		action.moveToElement(driver.findElement(locator)).build().perform();

	}

	public static void clearTextfield(WebDriver driver, By locator) {
		WebElement elementToClear = driver.findElement(locator);
		elementToClear.clear();
	}
}