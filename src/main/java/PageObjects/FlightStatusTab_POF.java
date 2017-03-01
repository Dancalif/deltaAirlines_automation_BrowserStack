package PageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.deltaAirlines.delta_automation.Util.WebUtil;

public class FlightStatusTab_POF {

	public static void clickFlightStatusTab(WebDriver driver) throws InterruptedException {
		WebUtil.click(driver, By.xpath(".//*[@id='navFlightStatus']"));
		Thread.sleep(1000);
	}

	public static void clickFlightDateOptionMenu(WebDriver driver) {
		WebUtil.click(driver, By.xpath(".//*[@id='FLIFO_flightDate-button']/span[1]"));
	}

	public static List<WebElement> createFlightDateOptionsList(WebDriver driver) {
		List<WebElement> flightDateOptions = WebUtil.createListOfElements(driver,
				By.xpath(".//*[@id='FLIFO_flightDate-menu'] //li"));
		return flightDateOptions;
	}

	public static void enterFlightNumber(WebDriver driver, String flightNumber) {
		WebUtil.input(driver, flightNumber, By.xpath(".//*[@id='FLIFO_flightNumber']"));
	}

	public static void clickViewStatusButton(WebDriver driver) throws InterruptedException {
		WebUtil.click(driver, By.xpath(".//*[@id='FLIFO_go']"));
		Thread.sleep(1000);
	}

	public static String parseFlightNumber(WebDriver driver) {
		String foundFlightNumber = WebUtil.myParser(driver,
				By.xpath(".//*[@id='maincontent']/div/div[2]/div/div[1]/h2"), "Flight (.*) on");
		return foundFlightNumber;
	}

	public static String parseSelectedFlightDates(WebDriver driver) {
		String mySelectedDate = WebUtil.myParser(driver, By.xpath(".//*[@id='FLIFO_flightDate-button']/span[2]"),
				".*, (.*)");
		return mySelectedDate;
	}

	public static String parseFlightDates(WebDriver driver) {
		String flightDate = WebUtil.myParser(driver, By.xpath(".//*[@id='tmOutboundDate']"), ".*, (.*).*");
		return flightDate;
	}
}
