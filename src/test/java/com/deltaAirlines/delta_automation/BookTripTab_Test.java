package com.deltaAirlines.delta_automation;

import org.openqa.selenium.By;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.deltaAirlines.delta_automation.Util.WebUtil;

import PageObjects.BookTripTab_POF;;

public class BookTripTab_Test extends Main_Test {
	@Test(enabled = true)
	public void verifyFlightSearchResuts() throws Exception {
		boolean booleanFlag = false;
		BookTripTab_POF BookTripTab = PageFactory.initElements(driver, BookTripTab_POF.class);
		// To verify if Find Flight button is displayed
		BookTripTab_POF.verifyFindFlightSubmitButton(driver);
		// Entering From and To destinations
		BookTripTab_POF.enterFromToDestinations(driver);
		// Entering dates since dates fields are required ones
		// Getting random dates based on random number method.
		// Departure date = current date + certain number of days. Return
		// date = departure date + some number of days.
		BookTripTab_POF.enterFlightDates(driver);
		// Get Find Flights button visible and click on it
		BookTripTab_POF.clickFindFlightsButton(driver);
		WebUtil.waitForElementVisible(driver, By.xpath(".//*[@id='_tripSummaryHeader_tmplHolder']/div[1]/h1"));
		// Verify if search results are displayed
		int totalSearchResults = BookTripTab.verifyFlightsSearchResult(driver);
		Assert.assertTrue(totalSearchResults > 0, "Number of flights is less than 0");
	}
}
