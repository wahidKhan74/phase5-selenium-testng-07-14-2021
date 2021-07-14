package com.webapp.test;

import static org.testng.Assert.assertEquals;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

public class BrowserBasedGroupTest {
	
	final String amazonURL = "https://www.amazon.in/";
	final String facebookURL ="https://www.facebook.com/";
	
	final String chromePath = "drivers/chromedriver";
	final String firefoxPath ="drivers/geckodriver";
	
	WebDriver driverOne;
	WebDriver driverTwo;
	WebDriverWait wait;
	
	@Test(groups="ChromeOnly")
	public void lauchChromeTest() {
		System.setProperty("webdriver.chrome.driver", chromePath);
		driverOne = new ChromeDriver();
		driverOne.get(amazonURL);
	}
	
	@Test(groups="ChromeOnly", dependsOnMethods="lauchChromeTest",priority=0)
	public void testAmazonHomepageTitle() {
		String expectedTitle = "Online Shopping site in India: Shop Online for Mobiles, Books, Watches, Shoes and More - Amazon.in";
		String actualTitle = driverOne.getTitle();
		assertEquals(actualTitle, expectedTitle);
	}
	
	@Test(groups="ChromeOnly",dependsOnMethods="lauchChromeTest",priority=1)
	public void testAmazonHomeSourceURL() {
		assertEquals(driverOne.getCurrentUrl(), amazonURL);
		driverOne.close();
	}
	
	
	@Test(groups="FirfoxOnly")
	public void launchFireFoxTest() {
		System.setProperty("webdriver.gecko.driver", firefoxPath);
		driverTwo = new FirefoxDriver();
		wait = new WebDriverWait(driverTwo, 50);
		driverTwo.get(facebookURL);
	}
	
	@Test(groups = "FirefoxOnly", dependsOnMethods = "launchFireFoxTest", priority=0)
	public void testFaceBookHomePage() {
		String expected ="Facebook - Login or sign up";
		assertEquals(driverTwo.getTitle(), driverTwo.getTitle());
	
	}
	
	@Test(groups="FirfoxOnly", dependsOnMethods="launchFireFoxTest", priority=1)
	public void testFacebookLoginFailure() {
		driverTwo.findElement(By.cssSelector("#email")).sendKeys("abcd@gmail.com");
		driverTwo.findElement(By.xpath("//*[@id=\"pass\"]")).sendKeys("abcd@123");
		driverTwo.findElement(By.name("login")).submit();	
		
		WebElement errorMsg = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#error_box > div.fsl.fwb.fcb")));
		assertEquals("Wrong Credentials", errorMsg.getText());
		
		WebElement errorMsg2 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"error_box\"]/div[2]")));
		assertEquals("Invalid username or password", errorMsg2.getText());
		driverTwo.close();
	}
	

}
