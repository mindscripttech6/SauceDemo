package com.saucedemo.utils;

import org.testng.annotations.DataProvider;

public class TestDataProvider {
	public static final String VALID_USERNAME = "standard_user";
	public static final String VALID_PASSWORD = "secret_sauce";

	// Each row is one login scenario: username, password, expected result type,
	// expected message.
	@DataProvider(name = "loginScenarios")
	public static Object[][] loginScenarios() {
		return new Object[][] {
				// Valid credentials should open the inventory page.
				{ "standard_user", "secret_sauce", "SUCCESS", "Products" },
				// A wrong password should show the invalid-credentials message.
				{ "standard_user", "wrong_password", "ERROR", "Username and password do not match" },
				// Empty fields should trigger username validation.
				{ "", "", "ERROR", "Username is required" },
				// The locked account should be rejected.
				{ "locked_out_user", "secret_sauce", "ERROR", "locked out" } };
	}
}
