package com.deltaAirlines.delta_automation;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.deltaAirlines.delta_automation.Util.WebUtil;

import PageObjects.FlightStatusTab_POF;

public class FlightStatusTab_Test extends Main_Test {
	@Test(enabled = true)
	public void verifyFlightStatusResuts() throws ParseException, InterruptedException {
		// Valid flight number, given by requirements
		String flightNumber = "2049";
		// Navigate to Flight Status tab page
		FlightStatusTab_POF.clickFlightStatusTab(driver);
		// To click on flight date drop down menu
		FlightStatusTab_POF.clickFlightDateOptionMenu(driver);
		// Logic to randomly select flight date option
		// Collect the web elements to select: Flight Date Options
		List<WebElement> flightDateOptions = FlightStatusTab_POF.createFlightDateOptionsList(driver);
		int randomNumber = WebUtil.randNumber(2);
		// Selecting Flight Date Option
		WebElement myFlightDateOption = flightDateOptions.get(randomNumber);
		// Retrieving the selected flight date
		String mySelectedDate = FlightStatusTab_POF.parseSelectedFlightDates(driver);
		myFlightDateOption.click();
		// Entering flight number
		FlightStatusTab_POF.enterFlightNumber(driver, flightNumber);
		// Click View Status Button
		FlightStatusTab_POF.clickViewStatusButton(driver);
		// Retrieving the flight number to verify
		String foundFlightNumber = FlightStatusTab_POF.parseFlightNumber(driver);
		// Asserting if flight number is correct
		Assert.assertTrue(foundFlightNumber.equalsIgnoreCase(flightNumber), "The flight number is wrong, please check");
		// Retrieving the flight date to assert
		String flightDate = FlightStatusTab_POF.parseFlightDates(driver);
		// Formating the dates and compare them to assert
		DateFormat format = new SimpleDateFormat("MMMM dd yyyy", Locale.ENGLISH);
		DateFormat format1 = new SimpleDateFormat("dd MMM yyyy", Locale.ENGLISH);
		Date date = format.parse(mySelectedDate);
		Date date1 = format1.parse(flightDate);
		Assert.assertEquals(date, date1, "The dates are wrong, please verify.");
		System.out.print("<<<<<<<<<<<FlightStatusTab_Test!>>>>>>>>>>");
	}
}
