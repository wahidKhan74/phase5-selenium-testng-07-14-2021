package com.webapp.test.amazon;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;

public class AmazonNavLinkTest {

	final String siteURL = "https://www.amazon.in/";
	final String driverPath = "drivers/chromedriver";
	WebDriver driver;

	@BeforeMethod
	public void beforeMethod() {
		System.setProperty("webdriver.chrome.driver", driverPath);
		driver = new ChromeDriver();
		driver.get(siteURL);
	}

	@AfterMethod
	public void afterMethod() {
		driver.close();
	}

	@Test
	public void testAmazonMobileLinkNavigation() throws InterruptedException {
		// find mobile link
		WebElement mobileLink = driver.findElement(By.cssSelector("#nav-xshop > a:nth-child(3)"));

		// test existence
		assertTrue(mobileLink.isDisplayed());
		assertTrue(mobileLink.isEnabled());

		// click on mobile
		mobileLink.click();
		String expected = "Mobile Phones: Buy New Mobiles Online at Best Prices in India | Buy Cell Phones Online - Amazon.in";
		assertEquals(expected, driver.getTitle());

		Thread.sleep(3000);
	}

	@Test
	public void testAmazonFashionLinkNavigation() throws InterruptedException {
		// find mobile link
		WebElement fashionLink = driver.findElement(By.cssSelector("#nav-xshop > a:nth-child(4)"));

		// test existence
		assertTrue(fashionLink.isDisplayed());
		assertTrue(fashionLink.isEnabled());

		// click on mobile
		fashionLink.click();
		String expected = "Amazon Fashion: Clothing, Footwear and Accessories online for Men, Women and Kids";
		assertEquals(expected, driver.getTitle());

		Thread.sleep(3000);
	}

}
