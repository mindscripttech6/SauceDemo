package com.saucedemo.tests;

import com.saucedemo.base.BaseTest;
import com.saucedemo.pages.CartPage;
import com.saucedemo.pages.InventoryPage;
import com.saucedemo.pages.LoginPage;
import com.saucedemo.utils.TestDataProvider;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CartTests extends BaseTest {
	private void loginAsStandardUser() {
		new LoginPage(driver).login(TestDataProvider.VALID_USERNAME, TestDataProvider.VALID_PASSWORD);
	}

	@Test(description = "Add item to cart")
	public void addItemToCart() {
		loginAsStandardUser();
		InventoryPage inventoryPage = new InventoryPage(driver);
		inventoryPage.addProduct("Sauce Labs Backpack");
		Assert.assertEquals(inventoryPage.getCartBadgeCount(), "1");
		inventoryPage.openCart();
		Assert.assertTrue(new CartPage(driver).getItemNames().contains("Sauce Labs Backpack"));
	}

	@Test(description = "Remove item from cart")
	public void removeItemFromCart() {
		loginAsStandardUser();
		InventoryPage inventory = new InventoryPage(driver);
		inventory.addProduct("Sauce Labs Backpack");
		inventory.openCart();
		CartPage cart = new CartPage(driver);
		cart.removeItem("Sauce Labs Backpack");
		Assert.assertEquals(cart.getItemNames().size(), 0);
	}

	@Test(description = "Verify cart badge with two products")
	public void verifyCartBadgeCount() {
		loginAsStandardUser();
		InventoryPage inventory = new InventoryPage(driver);
		inventory.addProduct("Sauce Labs Backpack");
		inventory.addProduct("Sauce Labs Bike Light");
		Assert.assertEquals(inventory.getCartBadgeCount(), "2");
		inventory.openCart();
		Assert.assertEquals(new CartPage(driver).getItemNames().size(), 2);
	}
}
