package com.webapp.test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class TestNgAssertionAPITest {

	// hard assert : Hard Assert throws an AssertException immediately when an
	// assert statement fails and test suite continues with next @Test
	
	@Test
	void hardAssertTest() {
		System.out.println("-- Hard Assert methods started --");
		assertEquals(true, false);  // Assertion Error
		System.out.println("-- Hard Assert methods completed --");
		assertTrue(true);
	}
	
	// soft assert :- Soft Assert collects errors during @Test.
	// Soft Assert does not throw an exception when an assert fails
	@Test
	void softAssertTest() {
		SoftAssert softAssert = new SoftAssert();
		System.out.println("-- Soft Assert methods started --");
		softAssert.assertEquals(true, false);  // No Assertion Error
		System.out.println("-- Soft Assert methods completed --");
		softAssert.assertTrue(true);
	}
	
}
