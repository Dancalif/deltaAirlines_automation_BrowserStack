package com.deltaAirlines.delta_automation;

import org.testng.Assert;
import org.testng.annotations.Test;

import PageObjects.BookTripTab_POF;;

public class BookTripTab_Test extends Main_Test {
	@Test(enabled = true)
	public void verifyFlightSearchResuts() throws Exception {
		boolean booleanFlag = false;
		BookTripTab_POF bookTripTab = new BookTripTab_POF();
		// To verify if Find Flight button is displayed
		bookTripTab.verifyFindFlightSubmitButton(driver);
		// Entering From and To destinations
		bookTripTab.enterFromToDestinations(driver);
		// Getting random dates based on random number method.
		// Departure date = current date + certain number of days. Return
		// date = departure date + some number of days.
		bookTripTab.enterFlightDates(driver);
		// Get Find Flights button visible and click on it
		bookTripTab.clickFindFlightsButton(driver);
		bookTripTab.waitForBookTripTitleVisible(driver);
		// Verify if search results are displayed
		int totalSearchResults = bookTripTab.verifyFlightsSearchResult(driver);
		Assert.assertTrue(totalSearchResults > 0, "Number of flights is less than 0");
	}
}
