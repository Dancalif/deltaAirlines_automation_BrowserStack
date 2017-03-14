package PageObjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.testng.Assert;

import com.deltaAirlines.delta_automation.Util.WebUtil;

public class HotelSearch_POF {

	@FindBy(how = How.XPATH, using = ".//*[@id='dropDownMenubar']/ul/li[1]/div[1]/a")
	WebElement shopDropdownMenu;
	@FindBy(how = How.XPATH, using = "//a[contains(@href, '/content/www/en_US/shop/hotels.html')]")
	WebElement hotelOption;
	@FindBy(how = How.XPATH, using = ".//*[@id='hotelLocation']")
	WebElement hotelLocationTextfield;
	@FindBy(how = How.XPATH, using = ".//*[@id='hotelCheckInDate']")
	WebElement checkInDateTextfield;
	@FindBy(how = How.XPATH, using = ".//*[@id='hotelCheckOutDate']")
	WebElement checkOutDateTextfield;
	@FindBy(how = How.XPATH, using = ".//*[@id='hotelNumberOfRooms-button']/span[1]")
	WebElement roomsDropdownMenu;
	@FindBy(how = How.XPATH, using = ".//*[@id='hotelNumberOfAdults-button']/span[1]")
	List<WebElement> adultsDropdownMenu;
	@FindBy(how = How.XPATH, using = ".//*[@id='btnSubmit']")
	WebElement findHotelsButton;
	@FindBy(how = How.XPATH, using = ".//*[@id='hotelNumberOfRooms-menu'] //li")
	List<WebElement> numberOfRoomsDropdown;
	@FindBy(how = How.XPATH, using = ".//*[@id='totalProducts']")
	WebElement totalNumberOfHotels;
	@FindBy(how = How.XPATH, using = ".//*[@id='searchHotelForm']/h2")
	WebElement hotelSearchResultsTitle;
	@FindBy(how = How.XPATH, using = ".//*[@id='ui-id-3']//li")
	List<WebElement> hotelLocationsDropdownMenu;
	@FindBy(xpath = ".//*[@id='hotelNumberOfAdults-button']/span[1]")
	WebElement numberOfAdultsButton;

	// Mouse over on shop tab
	public void mouseOverOnShopTab(WebDriver driver) {
		WebUtil.mouseOver(driver, shopDropdownMenu);
	}

	// Click Hotels Option
	public void clickHotelsOption(WebDriver driver) {
		WebUtil.click(driver, hotelOption);
	}

	// Enter Hotel Location
	public void enterHotelLocation(WebDriver driver, String inputLocation) throws InterruptedException {
		Thread.sleep(1000);
		WebUtil.clearTextfield(driver, hotelLocationTextfield);
		WebUtil.input(driver, inputLocation, hotelLocationTextfield);
	}

	// Enter Check In and Out Dates
	public void enterCheckInOutDate(WebDriver driver, int daysFromCurrentDate) throws InterruptedException {
		WebUtil.input(driver, WebUtil.randDate(daysFromCurrentDate, 0), checkInDateTextfield);
		Thread.sleep(1000);
		WebUtil.input(driver, WebUtil.randDate(daysFromCurrentDate, daysFromCurrentDate), checkOutDateTextfield);
		Thread.sleep(1000);
	}

	// Click Hotel Number Of Rooms
	public void clickHotelNumberOfRooms(WebDriver driver) {
		WebUtil.click(driver, roomsDropdownMenu);
	}

	// Select Number of Adults
	public void selectNumberOFAdults(WebDriver driver) {
		try {
			WebUtil.click(driver, numberOfAdultsButton);
			List<WebElement> adults = WebUtil.createListOfElements(driver, adultsDropdownMenu);
			int totalAdults = 1 + WebUtil.randNumber(7);
			adults.get(totalAdults).click();
		} catch (Exception e) {
		}
	}

	// Click Find Hotels Button
	public void clickFindHotelsButton(WebDriver driver) throws InterruptedException {
		Thread.sleep(2000);
		WebUtil.click(driver, findHotelsButton);
	}

	// Create Hotel Locations List
	public void createHotelLocationsList(WebDriver driver, String locationInDropdown) {
		List<WebElement> HotelNumberOfRoomsLis = WebUtil.createListOfElements(driver, hotelLocationsDropdownMenu);
		for (int i = 0; i < HotelNumberOfRoomsLis.size(); i++) {
			String location = HotelNumberOfRoomsLis.get(i).getText();
			if (location.equalsIgnoreCase(locationInDropdown)) {
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
		Assert.assertEquals(hotelSearchResultsTitle.getText().toLowerCase(), "hotel search results");
	}

	// Get String Parse To Int
	public int getStringParseToInt(WebDriver driver) {
		int totalNumHotels = WebUtil.convertStringToInt(driver, totalNumberOfHotels);
		return totalNumHotels;
	}
}
