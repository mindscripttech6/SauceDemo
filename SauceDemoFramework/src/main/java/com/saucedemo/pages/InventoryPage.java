package com.saucedemo.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import java.util.ArrayList;
import java.util.List;

public class InventoryPage {

	private final WebDriver driver;

	private final By title = By.cssSelector(".title");
	private final By items = By.cssSelector(".inventory_item");
	private final By names = By.cssSelector(".inventory_item_name");
	private final By prices = By.cssSelector(".inventory_item_price");
	private final By productImages = By.cssSelector(".inventory_item_img img");
	private final By sortDropdown = By.className("product_sort_container");
	private final By cartLink = By.className("shopping_cart_link");
	private final By cartBadge = By.className("shopping_cart_badge");
	private final By menuButton = By.id("react-burger-menu-btn");
	private final By logoutLink = By.id("logout_sidebar_link");

	public InventoryPage(WebDriver driver) {

		this.driver = driver;
	}

	public String getTitle() {
		return driver.findElement(title).getText();
	}

	public int getProductCount() {
		return driver.findElements(items).size();
	}

	public int getProductImageCount() {
		return driver.findElements(productImages).size();
	}

	public List<String> getProductNames() {
		List<String> result = new ArrayList<>();
		for (WebElement e : driver.findElements(names))
			result.add(e.getText());
		return result;
	}

	public List<Double> getProductPrices() {
		List<Double> result = new ArrayList<>();
		for (WebElement e : driver.findElements(prices))
			result.add(Double.parseDouble(e.getText().replace("$", "")));
		return result;
	}

	public void sortProducts(String option) {
		new Select(driver.findElement(sortDropdown)).selectByVisibleText(option);
	}

	public void addProduct(String productName) {
		WebElement card = findProductCard(productName);
		card.findElement(By.cssSelector("button")).click();
	}

	public void removeProduct(String productName) {
		WebElement card = findProductCard(productName);
		card.findElement(By.cssSelector("button")).click();
	}

	private WebElement findProductCard(String productName) {
		for (WebElement card : driver.findElements(items)) {
			if (card.findElement(names).getText().equals(productName))
				return card;
		}
		throw new IllegalArgumentException("Product not found on page: " + productName);
	}

	public String getCartBadgeCount() {
		List<WebElement> badges = driver.findElements(cartBadge);
		return badges.isEmpty() ? "0" : badges.get(0).getText();
	}

	public void openCart() {
		driver.findElement(cartLink).click();
	}

	public void openProductDetails(String productName) {
		findProductCard(productName).findElement(names).click();
	}

	public void logout() {
		driver.findElement(menuButton).click();
		driver.findElement(logoutLink).click();
	}
}
