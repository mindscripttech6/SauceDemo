package com.saucedemo.tests;

import com.saucedemo.base.BaseTest;
import com.saucedemo.pages.CartPage;
import com.saucedemo.pages.CheckoutPage;
import com.saucedemo.pages.InventoryPage;
import com.saucedemo.pages.LoginPage;
import com.saucedemo.utils.TestDataProvider;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CheckoutTests extends BaseTest {
	private void openCheckout() {
		new LoginPage(driver).login(TestDataProvider.VALID_USERNAME, TestDataProvider.VALID_PASSWORD);
		new InventoryPage(driver).addProduct("Sauce Labs Backpack");
		new InventoryPage(driver).openCart();
		new CartPage(driver).clickCheckout();
	}

	@Test(description = "Checkout with valid customer information")
	public void checkoutWithValidData() {
		openCheckout();
		CheckoutPage checkout = new CheckoutPage(driver);
		checkout.enterCustomerDetails("Test", "User", "411001");
		checkout.clickContinue();
		checkout.clickFinish();
		Assert.assertEquals(checkout.getConfirmationMessage(), "Thank you for your order!");
	}

	@Test(description = "Checkout validation when details are empty")
	public void checkoutWithoutDetails() {
		openCheckout();
		CheckoutPage checkout = new CheckoutPage(driver);
		checkout.clickContinue();
		Assert.assertTrue(checkout.getErrorMessage().contains("First Name is required"));
	}
}
