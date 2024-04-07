package com.steps;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;

public class Test {
    public static void main(String[] args) {
        EdgeOptions options = new EdgeOptions();
        options.setCapability("InPrivate", true);

        // Create a new instance of EdgeDriver with EdgeOptions
        WebDriver driver = new EdgeDriver(options);

        // Navigate to the Google homepage
        driver.get("http://www.google.com");

        // Close the browser
        driver.quit();
    }
}