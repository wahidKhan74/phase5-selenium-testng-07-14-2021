package com.webapp.test.facebook;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;

import static org.testng.Assert.assertEquals;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;

public class FaceboookLoginTest {

	final String siteURL = "https://www.facebook.com/";
	final String driverPath = "drivers/chromedriver";
	WebDriver driver;
	WebDriverWait wait;

	@BeforeMethod
	public void beforeMethod() {
		System.setProperty("webdriver.chrome.driver", driverPath);
		driver = new ChromeDriver();
		wait = new WebDriverWait(driver, 50);
		driver.get(siteURL);
	}

	@AfterMethod
	public void afterMethod() {
		driver.close();
	}

	@Test
	public void testFacebookLoginFailure() {
		driver.findElement(By.cssSelector("#email")).sendKeys("abcd@gmail.com");
		driver.findElement(By.xpath("//*[@id=\"pass\"]")).sendKeys("abcd@123");
		driver.findElement(By.name("login")).submit();	
		
		WebElement errorMsg = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#error_box > div.fsl.fwb.fcb")));
		assertEquals("Wrong Credentials", errorMsg.getText());
		
		WebElement errorMsg2 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"error_box\"]/div[2]")));
		assertEquals("Invalid username or password", errorMsg2.getText());
	}

}
