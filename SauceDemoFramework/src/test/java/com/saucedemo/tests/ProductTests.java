package com.saucedemo.tests;

import com.saucedemo.base.BaseTest;
import com.saucedemo.pages.InventoryPage;
import com.saucedemo.pages.LoginPage;
import com.saucedemo.utils.TestDataProvider;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ProductTests extends BaseTest {
	private void loginAsStandardUser() {
		new LoginPage(driver).login(TestDataProvider.VALID_USERNAME, TestDataProvider.VALID_PASSWORD);
	}

	@Test(description = "Verify product list")
	public void verifyProductList() {
		loginAsStandardUser();
		InventoryPage inventoryPage = new InventoryPage(driver);
		Assert.assertEquals(inventoryPage.getTitle(), "Products");
		Assert.assertEquals(inventoryPage.getProductCount(), 6);
		Assert.assertEquals(inventoryPage.getProductNames().size(), inventoryPage.getProductCount());
		Assert.assertEquals(inventoryPage.getProductPrices().size(), inventoryPage.getProductCount());
		Assert.assertEquals(inventoryPage.getProductImageCount(), inventoryPage.getProductCount());
	}

	@Test(description = "Sort products by name A to Z")
	public void sortProductsAZ() {
		loginAsStandardUser();
		InventoryPage inventoryPage = new InventoryPage(driver);
		inventoryPage.sortProducts("Name (A to Z)");
		List<String> actual = inventoryPage.getProductNames();
		List<String> expected = new ArrayList<>(actual);
		Collections.sort(expected);
		Assert.assertEquals(actual, expected);
	}

	@Test(description = "Sort products by name Z to A")
	public void sortProductsZA() {
		loginAsStandardUser();
		InventoryPage inventoryPage = new InventoryPage(driver);
		inventoryPage.sortProducts("Name (Z to A)");
		List<String> actual = inventoryPage.getProductNames();
		List<String> expected = new ArrayList<>(actual);
		expected.sort(Collections.reverseOrder());
		Assert.assertEquals(actual, expected);
	}

	@Test(description = "Sort products by price low to high")
	public void sortPriceLowToHigh() {
		loginAsStandardUser();
		InventoryPage inventoryPage = new InventoryPage(driver);
		inventoryPage.sortProducts("Price (low to high)");
		List<Double> actual = inventoryPage.getProductPrices();
		List<Double> expected = new ArrayList<>(actual);
		Collections.sort(expected);
		Assert.assertEquals(actual, expected);
	}

	@Test(description = "Sort products by price high to low")
	public void sortPriceHighToLow() {
		loginAsStandardUser();
		InventoryPage inventoryPage = new InventoryPage(driver);
		inventoryPage.sortProducts("Price (high to low)");
		List<Double> actual = inventoryPage.getProductPrices();
		List<Double> expected = new ArrayList<>(actual);
		expected.sort(Collections.reverseOrder());
		Assert.assertEquals(actual, expected);
	}
}
