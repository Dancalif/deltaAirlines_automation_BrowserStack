package com.deltaAirlines.delta_automation;

import org.testng.Assert;
import org.testng.annotations.Test;

import PageObjects.HotelSearch_POF;

public class HotelSearch_Test extends Main_Test {
	@Test(enabled = true)
	public void verifyHotelSearch() throws InterruptedException {
		HotelSearch_POF hotelSearch = new HotelSearch_POF();
		// Getting drop down under shop upper tab
		hotelSearch.mouseOverOnShopTab(driver);
		// Clicking Hotels option
		hotelSearch.clickHotelsOption(driver);
		// Enter hotel location
		hotelSearch.enterHotelLocation(driver);
		hotelSearch.createHotelLocationsList(driver);
		// Enter check in and out dates
		hotelSearch.enterCheckInOutDate(driver);
		// Selecting number of rooms
		hotelSearch.clickHotelNumberOfRooms(driver);
		hotelSearch.clickRandomElementFromList(driver);
		// Selecting number of adults
		hotelSearch.selectNumberOFAdults(driver);
		// Clicking find hotels button
		hotelSearch.clickFindHotelsButton(driver);
		// Asserting of user was navigated to the expected page
		hotelSearch.waitForHotelSearchResultsTitle(driver);
		// Double verify - if the number of hotels foud is greater than 0
		int totalNumHotels = hotelSearch.getStringParseToInt(driver);
		Assert.assertTrue(totalNumHotels > 0);
	}
}
