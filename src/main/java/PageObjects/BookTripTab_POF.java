package PageObjects;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import com.deltaAirlines.delta_automation.Util.WebUtil;

public class BookTripTab_POF {
	static int days = 14;
	static boolean booleanFlag = false;
	static boolean ifDisplayed = false;

	public static void verifyFindFlightSubmitButton(WebDriver driver) {
		// To verify if Find Flight button is displayed

		WebUtil.waitForElementVisible(driver, By.xpath(".//*[@id='findFlightsSubmit']"));
		try {
			ifDisplayed = WebUtil.ifElementDisplayed(driver, By.xpath(".//*[@id='findFlightsSubmit']"));
		} catch (Exception e) {
		}
		if (ifDisplayed == false) {
			// Click on Book a Trip tab
			WebUtil.click(driver, By.xpath(".//*[@id='navBookTrip']"));
			ifDisplayed = WebUtil.ifElementDisplayed(driver, By.xpath(".//*[@id='findFlightsSubmit']"));
			// Verify if user is navigated to Book a Trip module
			Assert.assertTrue(ifDisplayed = false, "Couldn't get Find Flight page. Please verify what's wrong.");
		}
	}

	// Entering From and To destinations
	public static void enterFromToDestinations(WebDriver driver) throws InterruptedException {
		WebUtil.input(driver, "SFO", By.xpath(".//*[@id='originCity']"));
		WebUtil.clearTextfield(driver, By.xpath(".//*[@id='destinationCity']"));
		WebUtil.input(driver, "BOM", By.xpath(".//*[@id='destinationCity']"));

	}

	public static void enterFlightDates(WebDriver driver) throws InterruptedException {
		WebUtil.input(driver, WebUtil.randDate(days, 0), By.xpath(".//*[@id='departureDate']"));
		Thread.sleep(1000);
		WebUtil.input(driver, WebUtil.randDate(days, days), By.xpath(".//*[@id='returnDate']"));
		Thread.sleep(1000);
	}

	// Verifying if search results page is displayed if not then start
	// searching again
	// public static boolean ifSearchResultsPageDisplayed(WebDriver driver) {
	// try {
	// ifDisplayed = WebUtil.ifElementDisplayed(driver,
	// By.xpath(".//*[@id='_revenueSearchHeader_tmplHolder']/div[1]/h2"));
	// try {
	// ifDisplayed = WebUtil.ifElementDisplayed(driver,
	// By.xpath(".//*[@id='errorTextParent']"));
	// } catch (Exception e) {
	// booleanFlag = true;
	// }
	// } catch (Exception e) {
	// booleanFlag = true;
	// }
	//
	// if (ifDisplayed == true) {
	// WebUtil.click(driver, By.xpath(".//*[@id='brand']/a[1]"));
	// }
	// return booleanFlag;
	// }

	public static int verifyFlightsSearchResult(WebDriver driver) throws InterruptedException {
		int totalSearchResults = 0;
		Thread.sleep(2000);
		String searchResults = driver.findElement(By.xpath(".//*[@id='_paginationBottom_tmplHolder']/ul/li/span"))
				.getText();
		Pattern pattern = Pattern.compile("of (.*?) flight");
		Matcher matcher = pattern.matcher(searchResults);
		while (matcher.find()) {
			totalSearchResults = Integer.parseInt(matcher.group(1));
		}
		return totalSearchResults;
	}

	// Get Find Flights button visible and click on it
	public static void clickFindFlightsButton(WebDriver driver) throws Exception {
		WebUtil.getElementVisible(driver, By.xpath(".//*[@id='findFlightsSubmit']"));
		WebUtil.click(driver, By.xpath(".//*[@id='findFlightsSubmit']"));
	}
}