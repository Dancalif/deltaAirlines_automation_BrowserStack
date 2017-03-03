package PageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import com.deltaAirlines.delta_automation.Util.WebUtil;

public class HotelSearch_POF {
	static int days = 14;

	static By shopDropdownMenu = By.xpath(".//*[@id='dropDownMenubar']/ul/li[1]/div[1]/a");
	static By hotelOption = By.xpath("//a[contains(@href, '/content/www/en_US/shop/hotels.html')]");
	static By hotelLocationTextfield = By.xpath(".//*[@id='hotelLocation']");
	static By checkInDateTextfield = By.xpath(".//*[@id='hotelCheckInDate']");
	static By checkOutDateTextfield = By.xpath(".//*[@id='hotelCheckOutDate']");
	static By roomsDropdownMenu = By.xpath(".//*[@id='hotelNumberOfRooms-button']/span[1]");
	static By adultsDropdownMenu = By.xpath(".//*[@id='hotelNumberOfAdults-button']/span[1]");
	static By findHotelsButton = By.xpath(".//*[@id='btnSubmit']");
	static By numberOfRoomsDropdown = By.xpath(".//*[@id='hotelNumberOfRooms-menu'] //li");
	static By totalNumberOfHotels = By.xpath(".//*[@id='totalProducts']");
	static By hotelSearchResultsTitle = By.xpath(".//*[@id='searchHotelForm']/h2");
	static By hotelLocationsDropdownMenu = By.xpath(".//*[@id='ui-id-3']//li");

	// Mouse over on shop tab
	public static void mouseOverOnShopTab(WebDriver driver) {
		WebUtil.mouseOver(driver, shopDropdownMenu);
	}

	// Click Hotels Option
	public static void clickHotelsOption(WebDriver driver) {
		WebUtil.click(driver, hotelOption);
	}

	// Enter Hotel Location
	public static void enterHotelLocation(WebDriver driver) throws InterruptedException {
		Thread.sleep(1000);
		WebUtil.clearTextfield(driver, hotelLocationTextfield);
		WebUtil.input(driver, "BOM", hotelLocationTextfield);
	}

	// Enter Check In and Out Dates
	public static void enterCheckInOutDate(WebDriver driver) throws InterruptedException {
		WebUtil.input(driver, WebUtil.randDate(days, 0), checkInDateTextfield);
		Thread.sleep(1000);
		WebUtil.input(driver, WebUtil.randDate(days, days), checkOutDateTextfield);
		Thread.sleep(1000);
	}

	// Click Hotel Number Of Rooms
	public static void clickHotelNumberOfRooms(WebDriver driver) {
		WebUtil.click(driver, roomsDropdownMenu);
	}

	// Select Number OF Adults
	public static void selectNumberOFAdults(WebDriver driver) {
		try {
			WebUtil.click(driver, adultsDropdownMenu);
			List<WebElement> adults = WebUtil.createListOfElements(driver, adultsDropdownMenu);
			int totalAdults = 1 + WebUtil.randNumber(7);
			adults.get(totalAdults).click();
		} catch (Exception e) {
		}
	}

	// Click Find Hotels Button
	public static void clickFindHotelsButton(WebDriver driver) throws InterruptedException {
		Thread.sleep(2000);
		WebUtil.click(driver, findHotelsButton);
	}

	// Create Hotel Locations List
	public static void createHotelLocationsList(WebDriver driver) {
		List<WebElement> HotelNumberOfRoomsLis = WebUtil.createListOfElements(driver, hotelLocationsDropdownMenu);
		for (int i = 0; i < HotelNumberOfRoomsLis.size(); i++) {
			String location = HotelNumberOfRoomsLis.get(i).getText();
			if (location.equalsIgnoreCase("Mumbai, India, IN (BOM)")) {
				HotelNumberOfRoomsLis.get(i).click();
			}
		}
	}

	// Click Random Element From List
	public void clickRandomElementFromList(WebDriver driver) {
		List<WebElement> HotelNumberOfRoomsLis = WebUtil.createListOfElements(driver, numberOfRoomsDropdown);
		int roomNum = WebUtil.randNumber(7);
		HotelNumberOfRoomsLis.get(roomNum).click();
	}

	// Wait For Hotel Search Results Title
	public void waitForHotelSearchResultsTitle(WebDriver driver) {
		WebUtil.waitForElementVisible(driver, hotelSearchResultsTitle);
		Assert.assertEquals(driver.findElement(hotelSearchResultsTitle).getText().toLowerCase(),
				"hotel search results");
	}

	// Get String Parse To Int
	public int getStringParseToInt(WebDriver driver) {
		int totalNumHotels = WebUtil.convertStringToInt(driver, totalNumberOfHotels);
		return totalNumHotels;
	}
}
