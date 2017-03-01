package com.deltaAirlines.delta_automation;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

public class Main_Test {
	WebDriver driver;

	@Parameters({ "browser", "url" })
	@BeforeTest
	public void getWebDriver(String browser, String url) {

		// Launch the FireFox browser
		if (browser.equalsIgnoreCase("firefox")) {
			driver = new FirefoxDriver();
			driver.manage().deleteAllCookies();
		}
		// Navigate to AUT, managing the browser window
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get(url);
	}

	@AfterTest
	public void tearDown() throws InterruptedException {
		// Clean up environment
		driver.quit();
	}
}