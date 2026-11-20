package com.saucedemo.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import java.util.ArrayList;
import java.util.List;

public class CartPage {
	private final WebDriver driver;
	private final By cartItems = By.className("cart_item");
	private final By itemNames = By.className("inventory_item_name");
	private final By removeButton = By.cssSelector("button");
	private final By checkoutButton = By.id("checkout");

	public CartPage(WebDriver driver) {
		this.driver = driver;
	}

	public List<String> getItemNames() {
		List<String> names = new ArrayList<>();
		for (WebElement item : driver.findElements(itemNames)) {
			names.add(item.getText());
		}
		return names;
	}

	public void removeItem(String productName) {
		for (WebElement item : driver.findElements(cartItems)) {
			String currentProductName = item.findElement(itemNames).getText();
			if (currentProductName.equals(productName)) {
				item.findElement(removeButton).click();
				return;
			}
		}
		throw new IllegalArgumentException("Product not found in cart: " + productName);
	}

	public void clickCheckout() {
		driver.findElement(checkoutButton).click();
	}
}
