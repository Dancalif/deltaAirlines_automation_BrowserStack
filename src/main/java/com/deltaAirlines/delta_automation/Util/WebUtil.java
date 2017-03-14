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
	public static void waitForElementVisible(WebDriver driver, WebElement element) {
		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.visibilityOf(element));
	}

	// to verify if element is displayed
	public static boolean ifElementDisplayed(WebDriver driver, WebElement element) {
		boolean ifDisplayed = element.isDisplayed();
		return ifDisplayed;
	}

	// to click
	public static void click(WebDriver driver, WebElement element) {
		element.click();
	}

	// to enter
	public static void input(WebDriver driver, String input, WebElement element) {
		element.clear();
		element.sendKeys(input);
	}

	// to get hidden element visible to perform the actions with it
	public static void getElementVisible(WebDriver driver, WebElement element) throws Exception {
		element.sendKeys(Keys.PAGE_DOWN);
		Thread.sleep(1000);
		try {
			WebDriverWait wait = new WebDriverWait(driver, 30);
			wait.until(ExpectedConditions.elementToBeClickable(element));
		} catch (Exception e) {
			element.sendKeys(Keys.PAGE_UP);
			Thread.sleep(1000);
			WebDriverWait wait = new WebDriverWait(driver, 30);
			wait.until(ExpectedConditions.elementToBeClickable(element));
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
	public static void verifyIfElementExists(WebDriver driver, By element, String message) {
		WebElement elementToBeFound = driver.findElement(element);
		boolean ifExists = elementToBeFound.isDisplayed();
		org.testng.Assert.assertTrue(ifExists, message);
	}

	public static List<WebElement> createListOfElements(WebDriver driver, List<WebElement> element) {
		List<WebElement> listOfElements = element;
		return listOfElements;
	}

	public static String myParser(WebDriver driver, WebElement element, String myPattern) {
		String parsedItem = element.getText();
		Pattern pattern = Pattern.compile(myPattern);
		Matcher matcher = pattern.matcher(parsedItem);
		while (matcher.find()) {
			parsedItem = matcher.group(1);
		}
		return parsedItem;
	}

	public static void mouseOver(WebDriver driver, WebElement element) {
		Actions action = new Actions(driver);
		action.moveToElement(element).build().perform();
	}

	public static void clearTextfield(WebDriver driver, WebElement element) {
		element.clear();
	}

	public static int convertStringToInt(WebDriver driver, WebElement element) {
		String totalHotels = element.getText();
		int totalNumHotels = Integer.parseInt(totalHotels);
		return totalNumHotels;
	}
}