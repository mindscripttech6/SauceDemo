package com.saucedemo.tests;

import com.saucedemo.base.BaseTest;
import com.saucedemo.pages.CartPage;
import com.saucedemo.pages.CheckoutPage;
import com.saucedemo.pages.InventoryPage;
import com.saucedemo.pages.LoginPage;
import com.saucedemo.utils.TestDataProvider;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

public class EndToEndTests extends BaseTest {
	@Test(description = "Complete end-to-end purchase flow")
	public void endToEndTest() {
		new LoginPage(driver).login(TestDataProvider.VALID_USERNAME, TestDataProvider.VALID_PASSWORD);
		InventoryPage inventoryPage = new InventoryPage(driver);
		inventoryPage.sortProducts("Name (A to Z)");
		inventoryPage.addProduct("Sauce Labs Backpack");
		inventoryPage.openCart();
		new CartPage(driver).clickCheckout();
		CheckoutPage checkout = new CheckoutPage(driver);
		checkout.enterCustomerDetails("Test", "User", "411001");
		checkout.clickContinue();
		checkout.clickFinish();
		Assert.assertEquals(checkout.getConfirmationMessage(), "Thank you for your order!");
		driver.findElement(By.id("back-to-products")).click();
		new InventoryPage(driver).logout();
		Assert.assertTrue(new LoginPage(driver).isLoginButtonDisplayed());
	}
}
