package PageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.deltaAirlines.delta_automation.Util.WebUtil;

public class FlightStatusTab_POF {

	static By flightStatusTab = By.xpath(".//*[@id='navFlightStatus']");
	static By flightDateDropdown = By.xpath(".//*[@id='FLIFO_flightDate-button']/span[1]");
	static By flightDateOptionsElements = By.xpath(".//*[@id='FLIFO_flightDate-menu'] //li");
	static By flightNumberTextfield = By.xpath(".//*[@id='FLIFO_flightNumber']");
	static By viewStatusButton = By.xpath(".//*[@id='FLIFO_go']");
	static By flightNumberTitle = By.xpath(".//*[@id='maincontent']/div/div[2]/div/div[1]/h2");
	static By selectedFlightDates = By.xpath(".//*[@id='FLIFO_flightDate-button']/span[2]");
	static By returnedFlightDates = By.xpath(".//*[@id='tmOutboundDate']");

	// Click Flight Status Tab
	public static void clickFlightStatusTab(WebDriver driver) throws InterruptedException {
		WebUtil.click(driver, flightStatusTab);
		Thread.sleep(1000);
	}

	// Click Flight Status Tab
	public static void clickFlightDateOptionMenu(WebDriver driver) {
		WebUtil.click(driver, flightDateDropdown);
	}

	// Enter Flight Number
	public static void enterFlightNumber(WebDriver driver, String flightNumber) {
		WebUtil.input(driver, flightNumber, flightNumberTextfield);
	}

	// Click View Status Button
	public static void clickViewStatusButton(WebDriver driver) throws InterruptedException {
		WebUtil.click(driver, viewStatusButton);
		Thread.sleep(1000);
	}

	// Parse Flight Number
	public static String parseFlightNumber(WebDriver driver) {
		String foundFlightNumber = WebUtil.myParser(driver, flightNumberTitle, "Flight (.*) on");
		return foundFlightNumber;
	}

	// Parse Selected Flight Dates
	public static String parseSelectedFlightDates(WebDriver driver) {
		String mySelectedDate = WebUtil.myParser(driver, selectedFlightDates, ".*, (.*)");
		return mySelectedDate;
	}

	// Parse Flight Dates
	public static String parseFlightDates(WebDriver driver) {
		String flightDate = WebUtil.myParser(driver, returnedFlightDates, ".*, (.*).*");
		return flightDate;
	}

	// Click Random Element From List
	public WebElement clickRandomElementFromList(WebDriver driver) {
		List<WebElement> flightDateOptions = WebUtil.createListOfElements(driver, flightDateOptionsElements);
		int randomNumber = WebUtil.randNumber(2);
		// Selecting Flight Date Option
		WebElement myFlightDateOption = flightDateOptions.get(randomNumber);
		myFlightDateOption.click();
		return myFlightDateOption;
	}
}
