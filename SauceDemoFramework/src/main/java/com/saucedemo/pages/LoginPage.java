package com.saucedemo.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class LoginPage {
	private final WebDriver driver;
	private final WebDriverWait wait;

	private final By username = By.id("user-name");
	private final By password = By.id("password");
	private final By loginButton = By.id("login-button");
	private final By error = By.cssSelector("[data-test='error']");

	public LoginPage(WebDriver driver) {
		this.driver = driver;
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(8));
	}

	public void login(String user, String pass) {
		wait.until(ExpectedConditions.visibilityOfElementLocated(username)).clear();
		driver.findElement(username).sendKeys(user);
		driver.findElement(password).clear();
		driver.findElement(password).sendKeys(pass);
		driver.findElement(loginButton).click();
	}

	public void clickLoginWithoutCredentials() {
		driver.findElement(loginButton).click();
	}

	public String getErrorMessage() {
		return wait.until(ExpectedConditions.visibilityOfElementLocated(error)).getText();
	}

	public boolean isLoginButtonDisplayed() {
		return driver.findElement(loginButton).isDisplayed();
	}
}
