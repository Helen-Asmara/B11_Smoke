package com.steps;

import org.junit.After;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;

import static org.junit.Assert.assertEquals;

public class LoginStep2 {

    private WebDriver driver;
    private WebDriverWait wait;

    public LoginStep2() {
        // Set up EdgeOptions to enable InPrivate browsing mode (equivalent to incognito mode)
        //EdgeOptions options = new EdgeOptions();
        //options.setCapability("InPrivate", true);

        // Setup Edge WebDriver using WebDriverManager for Edge (Chromium-based)
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver(); // Pass options to EdgeDriver constructor
        wait = new WebDriverWait(driver, 30);
    }

    @Given("User navigates to login page {string}")
    public void user_navigates_to_login_page(String url) {
        driver.get(url);
    }

    @When("User enters username {string}")
    public void user_enters_username(String username) {
        WebElement usernameInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("username")));
        usernameInput.sendKeys(username);
    }

    @When("User enters password {string}")
    public void user_enters_password(String password) {
        WebElement passwordInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("password")));
        passwordInput.sendKeys(password);
    }

    @When("User clicks login button")
    public void user_clicks_login_button() {
        WebElement loginButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("loginButton")));
        loginButton.click();

        // Handle JavaScript alert
        handleAlert();
    }

    private void handleAlert() {
        try {
            // Switch to the alert
            Alert alert = wait.until(ExpectedConditions.alertIsPresent());
            
            // Get the text of the alert (for debugging/logging)
            String alertText = alert.getText();
            System.out.println("Alert Text: " + alertText);

            // Accept the alert (click OK)
            alert.accept();
        } catch (Exception e) {
            // Alert not present or timeout reached, continue with the test
        }
    }

    @Then("System redirects to dashboard page {string}")
    public void system_redirects_to_dashboard_page(String expectedPage) {
        // Wait for the dashboard element to be visible (you can adjust this based on your actual application)
        WebElement dashboardElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("dashboard")));
        String actualPageTitle = driver.getTitle();
        assertEquals(expectedPage, actualPageTitle);
    }

    @Then("System redirects to dashboard pages {string}")
    public void system_redirects_to_dashboard_pages(String expectedPage) {
        // You can add specific logic here to handle the failure case
        // For example, check for an error message or verify the absence of dashboard elements
        // This will depend on how the application behaves upon login failure
        WebElement errorMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("errorMessage")));
        assertEquals("Login failed", errorMessage.getText());
    }

    @After
    public void closeBrowser() {
        if (driver != null) {
            driver.quit();
        }
    }
}