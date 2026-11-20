package com.saucedemo.tests;

import com.saucedemo.base.BaseTest;
import com.saucedemo.pages.InventoryPage;
import com.saucedemo.pages.LoginPage;
import com.saucedemo.utils.TestDataProvider;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

public class NavigationTests extends BaseTest {
	@Test(description = "Logout functionality")
	public void logoutFunctionality() {
		new LoginPage(driver).login(TestDataProvider.VALID_USERNAME, TestDataProvider.VALID_PASSWORD);
		new InventoryPage(driver).logout();
		Assert.assertTrue(driver.findElement(By.id("login-button")).isDisplayed());
	}

	@Test(description = "Open product details")
	public void openProductDetails() {
		new LoginPage(driver).login(TestDataProvider.VALID_USERNAME, TestDataProvider.VALID_PASSWORD);

		new InventoryPage(driver).openProductDetails("Sauce Labs Backpack");
		Assert.assertEquals(driver.findElement(By.className("inventory_details_name")).getText(),
				"Sauce Labs Backpack");
	}
}
