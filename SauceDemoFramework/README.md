# SauceDemo Selenium TestNG Beginner Framework

This Maven project automates SauceDemo using Java, Selenium WebDriver, TestNG, and the Page Object Model (POM).

## Test data

Test data is stored directly in Java in `src/test/java/com/saucedemo/utils/TestDataProvider.java`. The TestNG `@DataProvider` named `loginScenarios` supplies valid credentials, invalid credentials, empty credentials, and locked-account credentials to `LoginTests`. There is **no Excel workbook or external test-data file**. Tests that need a successful login reuse the `VALID_USERNAME` and `VALID_PASSWORD` constants in the same class.

## Project structure

- `src/main/java/com/saucedemo/base/BaseTest.java`: starts and closes the browser for each test, with comments for each setup/teardown step.
- `src/main/java/com/saucedemo/pages/`: Page Object classes and class-level locators for login, inventory, cart, and checkout actions.
- `src/test/java/com/saucedemo/tests/`: login, product, cart, checkout, navigation, and end-to-end test scenarios.
- `src/test/java/com/saucedemo/utils/TestDataProvider.java`: Java-based test data and TestNG `@DataProvider`.
- `testng.xml`: TestNG suite configuration. No listeners are configured.

## Requirements

- JDK 17 or later
- Maven 3.8 or later
- Chrome browser (Selenium Manager can resolve a compatible driver in supported environments)

## Run the tests

From the project root, run:

```bash
mvn clean test
```

The suite runs the TestNG tests listed in `testng.xml`. Each login data row is executed as a separate TestNG invocation.
