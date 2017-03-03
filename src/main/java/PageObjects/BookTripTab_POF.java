package PageObjects;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import com.deltaAirlines.delta_automation.Util.WebUtil;

public class BookTripTab_POF {
	static int days = 14;
	static boolean booleanFlag = false;
	static boolean ifDisplayed = false;

	static By findFlightsButton = By.id("findFlightsSubmit");
	static By bookATripTab = By.xpath(".//*[@id='navBookTrip']");
	static By fromCityTextfield = By.id("originCity");
	static By toCityTextfield = By.xpath(".//*[@id='destinationCity']");
	static By departureDateTextfield = By.xpath(".//*[@id='departureDate']");
	static By returnDateTextfield = By.xpath(".//*[@id='returnDate']");
	static By paginationFlightResultsBottom = By.xpath(".//*[@id='_paginationBottom_tmplHolder']/ul/li[1]/span");
	static By bookATripTitle = By.xpath(".//*[@id='_tripSummaryHeader_tmplHolder']/div[1]/h1");

	public static void verifyFindFlightSubmitButton(WebDriver driver) {
		// To verify if Find Flight button is displayed
		WebUtil.waitForElementVisible(driver, findFlightsButton);
		try {
			ifDisplayed = WebUtil.ifElementDisplayed(driver, findFlightsButton);
		} catch (Exception e) {
		}
		if (ifDisplayed == false) {
			// Click on Book a Trip tab
			WebUtil.click(driver, bookATripTab);
			ifDisplayed = WebUtil.ifElementDisplayed(driver, findFlightsButton);
			// Verify if user is navigated to Book a Trip module
			Assert.assertTrue(ifDisplayed = false, "Couldn't get Find Flight page. Please verify what's wrong.");
		}
	}

	// Entering From and To destinations
	public static void enterFromToDestinations(WebDriver driver) throws InterruptedException {
		WebUtil.input(driver, "SFO", fromCityTextfield);
		WebUtil.clearTextfield(driver, toCityTextfield);
		WebUtil.input(driver, "BOM", toCityTextfield);

	}

	// Enter flight dates
	public static void enterFlightDates(WebDriver driver) throws InterruptedException {
		WebUtil.input(driver, WebUtil.randDate(days, 0), departureDateTextfield);
		Thread.sleep(1000);
		WebUtil.input(driver, WebUtil.randDate(days, days), returnDateTextfield);
		Thread.sleep(1000);
	}

	// Verify flight search results
	public static int verifyFlightsSearchResult(WebDriver driver) throws InterruptedException {
		int totalSearchResults = 0;
		Thread.sleep(2000);
		String searchResults = driver.findElement(paginationFlightResultsBottom).getText();
		Pattern pattern = Pattern.compile("of (.*?) flight");
		Matcher matcher = pattern.matcher(searchResults);
		while (matcher.find()) {
			totalSearchResults = Integer.parseInt(matcher.group(1));
		}
		return totalSearchResults;
	}

	// Get Find Flights button visible and click on it
	public static void clickFindFlightsButton(WebDriver driver) throws Exception {
		WebUtil.getElementVisible(driver, findFlightsButton);
		WebUtil.click(driver, findFlightsButton);
	}

	// Wait for Book A Trip Title to be visible
	public void waitForBookTripTitleVisible(WebDriver driver) {
		WebUtil.waitForElementVisible(driver, bookATripTitle);

	}
}