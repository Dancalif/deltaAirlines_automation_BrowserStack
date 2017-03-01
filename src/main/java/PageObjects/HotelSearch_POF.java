package PageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.deltaAirlines.delta_automation.Util.WebUtil;

public class HotelSearch_POF {
	static int days = 14;

	public static void mouseOverOnShopTab(WebDriver driver) {
		WebUtil.mouseOver(driver, By.xpath(".//*[@id='dropDownMenubar']/ul/li[1]/div[1]/a"));
	}

	public static void clickHotelsOption(WebDriver driver) {
		WebUtil.click(driver, By.xpath("//a[contains(@href, '/content/www/en_US/shop/hotels.html')]"));
	}

	public static void enterHotelLocation(WebDriver driver) throws InterruptedException {
		Thread.sleep(1000);
		WebUtil.clearTextfield(driver, By.xpath(".//*[@id='hotelLocation']"));
		WebUtil.input(driver, "BOM", By.xpath(".//*[@id='hotelLocation']"));

	}

	public static void enterCheckInOutDate(WebDriver driver) throws InterruptedException {
		WebUtil.input(driver, WebUtil.randDate(days, 0), By.xpath(".//*[@id='hotelCheckInDate']"));
		Thread.sleep(1000);
		WebUtil.input(driver, WebUtil.randDate(days, days), By.xpath(".//*[@id='hotelCheckOutDate']"));
		Thread.sleep(1000);
	}

	public static void clickHotelNumberOfRooms(WebDriver driver) {
		WebUtil.click(driver, By.xpath(".//*[@id='hotelNumberOfRooms-button']/span[1]"));
	}

	public static List<WebElement> createHotelNumberOfRoomsList(WebDriver driver) {
		List<WebElement> HotelNumberOfRoomsLis = WebUtil.createListOfElements(driver,
				By.xpath(".//*[@id='hotelNumberOfRooms-menu'] //li"));
		return HotelNumberOfRoomsLis;
	}

	public static void selectNumberOFAdults(WebDriver driver) {
		try {
			WebUtil.click(driver, By.xpath(".//*[@id='hotelNumberOfAdults-button']/span[1]"));
			List<WebElement> adults = WebUtil.createListOfElements(driver,
					By.xpath(".//*[@id='hotelNumberOfAdults-menu'] // li"));
			int totalAdults = 1 + WebUtil.randNumber(7);
			adults.get(totalAdults).click();
		} catch (Exception e) {
		}

	}

	public static void clickFindHotelsButton(WebDriver driver) throws InterruptedException {
		Thread.sleep(2000);
		WebUtil.click(driver, By.xpath(".//*[@id='btnSubmit']"));
	}

	public static void createHotelLocationsList(WebDriver driver) {
		List<WebElement> HotelNumberOfRoomsLis = WebUtil.createListOfElements(driver,
				By.xpath(".//*[@id='ui-id-3']//li"));
		for (int i = 0; i < HotelNumberOfRoomsLis.size(); i++) {
			String location = HotelNumberOfRoomsLis.get(i).getText();
			if (location.equalsIgnoreCase("Mumbai, India, IN (BOM)")) {
				HotelNumberOfRoomsLis.get(i).click();
			}
		}

	}
}
