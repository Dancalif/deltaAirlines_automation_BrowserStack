package com.deltaAirlines.delta_automation;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import com.deltaAirlines.delta_automation.Util.WebUtil;

import PageObjects.HotelSearch_POF;

public class HotelSearch_Test extends Main_Test {
	@Test(enabled = true)
	public void verifyHotelSearch() throws InterruptedException {
		// Getting drop down under shop upper tab
		HotelSearch_POF.mouseOverOnShopTab(driver);
		// Clicking Hotels option
		HotelSearch_POF.clickHotelsOption(driver);
		// Enter hotel location
		HotelSearch_POF.enterHotelLocation(driver);
		HotelSearch_POF.createHotelLocationsList(driver);

		// Enter check in and out dates
		HotelSearch_POF.enterCheckInOutDate(driver);
		// Selecting number of rooms
		HotelSearch_POF.clickHotelNumberOfRooms(driver);
		List<WebElement> HotelNumberOfRoomsLis = HotelSearch_POF.createHotelNumberOfRoomsList(driver);
		int roomNum = WebUtil.randNumber(7);
		HotelNumberOfRoomsLis.get(roomNum).click();
		// Selecting number of adults
		HotelSearch_POF.selectNumberOFAdults(driver);

		// Clicking find hotels button
		HotelSearch_POF.clickFindHotelsButton(driver);
		WebUtil.waitForElementVisible(driver, By.xpath(".//*[@id='searchHotelForm']/h2"));
		// Asserting of user was navigated to the expected page
		org.testng.Assert.assertEquals(
				driver.findElement(By.xpath(".//*[@id='searchHotelForm']/h2")).getText().toLowerCase(),
				"hotel search results");
		// Double verify - if the number of hotels foud is greater than 0
		String totalHotels = driver.findElement(By.xpath(".//*[@id='totalProducts']")).getText();
		int totalNumHotels = Integer.parseInt(totalHotels);
		org.testng.Assert.assertTrue(totalNumHotels > 0);
	}
}
