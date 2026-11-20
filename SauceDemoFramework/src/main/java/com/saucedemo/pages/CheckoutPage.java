package com.saucedemo.pages;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.UnhandledAlertException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class CheckoutPage {
	private final WebDriver driver;
	private final WebDriverWait wait;
	private final By firstName = By.id("first-name");
	private final By lastName = By.id("last-name");
	private final By postalCode = By.id("postal-code");
	private final By continueButton = By.id("continue");
	private final By error = By.cssSelector("[data-test='error']");
	private final By finishButton = By.id("finish");
	private final By confirmation = By.className("complete-header");

	public CheckoutPage(WebDriver driver) {
		this.driver = driver;
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(5));
	}

	public void enterCustomerDetails(String first, String last, String zip) {
		driver.findElement(firstName).sendKeys(first);
		driver.findElement(lastName).sendKeys(last);
		driver.findElement(postalCode).sendKeys(zip);
	}

	public void clickContinue() {
		acceptUnexpectedAlertIfPresent();
		try {
			driver.findElement(continueButton).click();
		} catch (UnhandledAlertException e) {
			acceptUnexpectedAlertIfPresent();
		}
		acceptUnexpectedAlertIfPresent();
	}

	public String getErrorMessage() {
		return wait.until(ExpectedConditions.visibilityOfElementLocated(error)).getText();
	}

	public void clickFinish() {
		acceptUnexpectedAlertIfPresent();
		try {
			wait.until(ExpectedConditions.elementToBeClickable(finishButton)).click();
		} catch (UnhandledAlertException e) {
			acceptUnexpectedAlertIfPresent();
		}
		acceptUnexpectedAlertIfPresent();
	}

	public String getConfirmationMessage() {
		acceptUnexpectedAlertIfPresent();
		return wait.until(ExpectedConditions.visibilityOfElementLocated(confirmation)).getText();
	}

	public void acceptUnexpectedAlertIfPresent() {
		try {
			Alert alert = driver.switchTo().alert();
			alert.accept();
		} catch (NoAlertPresentException ignored) {
		}
	}
}
