package PageObjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import com.deltaAirlines.delta_automation.Util.WebUtil;

public class FlightStatusTab_POF {

	@FindBy(how = How.XPATH, using = ".//*[@id='navFlightStatus']")
	WebElement flightStatusTab;
	@FindBy(how = How.XPATH, using = ".//*[@id='FLIFO_flightDate-button']/span[1]")
	WebElement flightDateDropdown;
	@FindBy(how = How.XPATH, using = ".//*[@id='FLIFO_flightDate-menu'] //li")
	List<WebElement> flightDateOptionsElements;
	@FindBy(how = How.XPATH, using = ".//*[@id='FLIFO_flightNumber']")
	WebElement flightNumberTextfield;
	@FindBy(how = How.XPATH, using = ".//*[@id='FLIFO_go']")
	WebElement viewStatusButton;
	@FindBy(how = How.XPATH, using = ".//*[@id='maincontent']/div/div[2]/div/div[1]/h2")
	WebElement flightNumberTitle;
	@FindBy(how = How.XPATH, using = ".//*[@id='FLIFO_flightDate-button']/span[2]")
	WebElement selectedFlightDates;
	@FindBy(how = How.XPATH, using = ".//*[@id='tmOutboundDate']")
	WebElement returnedFlightDates;

	// Click Flight Status Tab
	public void clickFlightStatusTab(WebDriver driver) throws InterruptedException {
		WebUtil.click(driver, flightStatusTab);
		Thread.sleep(1000);
	}

	// Click Flight Status Tab
	public void clickFlightDateOptionMenu(WebDriver driver) {
		WebUtil.click(driver, flightDateDropdown);
	}

	// Enter Flight Number
	public void enterFlightNumber(WebDriver driver, String flightNumber) {
		WebUtil.input(driver, flightNumber, flightNumberTextfield);
	}

	// Click View Status Button
	public void clickViewStatusButton(WebDriver driver) throws InterruptedException {
		WebUtil.click(driver, viewStatusButton);
		Thread.sleep(1000);
	}

	// Parse Flight Number
	public String parseFlightNumber(WebDriver driver) {
		String foundFlightNumber = WebUtil.myParser(driver, flightNumberTitle, "Flight (.*) on");
		return foundFlightNumber;
	}

	// Parse Selected Flight Dates
	public String parseSelectedFlightDates(WebDriver driver) {
		String mySelectedDate = WebUtil.myParser(driver, selectedFlightDates, ".*, (.*)");
		return mySelectedDate;
	}

	// Parse Flight Dates
	public String parseFlightDates(WebDriver driver) {
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
