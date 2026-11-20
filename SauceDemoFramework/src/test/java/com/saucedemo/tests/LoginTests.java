package com.saucedemo.tests;

import com.saucedemo.base.BaseTest;
import com.saucedemo.pages.InventoryPage;
import com.saucedemo.pages.LoginPage;
import com.saucedemo.utils.TestDataProvider;
import org.testng.Assert;
import org.testng.annotations.Test;

// TestNG runs this test once for each row supplied by the loginScenarios DataProvider.
public class LoginTests extends BaseTest {
	
	
	@Test(dataProvider = "loginScenarios", dataProviderClass = TestDataProvider.class, description = "Validate valid, invalid, empty, and locked-account login scenarios")
	public void loginScenarios(String username, String password, String expectedResult, String expectedText) {
		LoginPage login = new LoginPage(driver);
		login.login(username, password);
		if ("SUCCESS".equals(expectedResult)) {
			Assert.assertEquals(new InventoryPage(driver).getTitle(), expectedText);
		} else {
			Assert.assertTrue(login.getErrorMessage().contains(expectedText));
		}
	}
}
