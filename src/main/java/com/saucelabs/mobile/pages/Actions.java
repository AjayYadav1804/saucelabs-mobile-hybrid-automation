package com.saucelabs.mobile.pages;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.saucelabs.mobile.utils.LoggingUtils;

public class Actions {

    protected WebDriver driver;
    static ExtentTest test;

    public Actions(WebDriver driver,ExtentTest test) {
        this.driver = driver;
        this.test=test;
    }

    protected void performSendKeys(WebElement ele, String text, String description) {
        try {
        	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
            WebElement element = wait.until(ExpectedConditions.visibilityOf(ele));
            element.clear();
            element.sendKeys(text);
            logAction(driver, "Entered '" + text + "' into the " + description + " text box.");
        } catch (NoSuchElementException e) {
            logAction(driver, "Element not found for sendKeys operation at locator: " + description);
            logAction(driver, "Fail_");
        }catch (Exception e) {
            logAction(driver, "An unexpected error occurred during click operation: " + description);
        }
    }

    protected void performClick(WebElement menuButton, String description) {
        try {
        	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        	WebElement element = wait.until(ExpectedConditions.elementToBeClickable(menuButton));
            element.click();
            logAction(driver, "Clicked on element: " + description);
        } catch (NoSuchElementException e) {  
            logAction(driver, "Element not found for click operation: " + description);
        } catch (TimeoutException e) {
            logAction(driver, "Timeout waiting for click operation: " + description);
        } catch (Exception e) {
            logAction(driver, "An unexpected error occurred during click operation: " + description);
        }
        
        
    }

    protected void clickWebElement(WebElement element, String description) {
        try {
        	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
            wait.until(ExpectedConditions.elementToBeClickable(element)); // Wait for element to be clickable
            element.click();
            logAction(driver, "Clicked on element: " + description);
        } catch (NoSuchElementException e) {
            
            logAction(driver, "Element not available for click operation");
            throw new RuntimeException("Element not available for click operation");
        } catch (Exception e) {
           
            logAction(driver, "An error occurred during click operation");
            throw new RuntimeException("Failed to click element: " + e.getMessage());
        }
    }

    protected void selectByVisibleText(By locator, String text) {
        try {
            Select dropdown = new Select(driver.findElement(locator));
            dropdown.selectByVisibleText(text);
          
            logAction(driver, "Selected '" + text + "' from dropdown with locator: " + locator);
        } catch (NoSuchElementException e) {
           
           logAction(driver, "Element not found for selectByVisibleText operation at locator: " + locator);
            throw new RuntimeException("Element not found for select operation");
        } catch (IllegalArgumentException e) {
           
            logAction(driver, "Invalid value for select operation: " + text);
            throw new RuntimeException("Invalid value for select operation");
        }
    }
    
    public static void logAction(WebDriver driver, String message) {
        
        try {
			if (test != null) {
			    try {
			        // Capture Screenshot
			        String screenshotPath = captureScreenshot(driver, message);
			        test.log(Status.INFO, message, MediaEntityBuilder.createScreenCaptureFromPath(screenshotPath).build());
			    } catch (Exception e) {
			    	test.fail("Error adding screenshot to Extent Report: " + e.getMessage());
			    }
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    private static String captureScreenshot(WebDriver driver, String screenshotName) {
        // Clean the screenshot name to remove any illegal characters
        String sanitizedScreenshotName = screenshotName.replaceAll("[^a-zA-Z0-9_-]", "_");
        String dateName = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());

        TakesScreenshot ts = (TakesScreenshot) driver;
        File source = ts.getScreenshotAs(OutputType.FILE);

        // Ensure the screenshots folder exists
        String screenshotsDir = System.getProperty("user.dir") + "/reports/screenshots";
        new File(screenshotsDir).mkdirs(); // Create the directory if it does not exist

        String dest = screenshotsDir + "/" + sanitizedScreenshotName + dateName + ".png";
        File destination = new File(dest);

        try {
            FileUtils.copyFile(source, destination);
        } catch (IOException e) {
            test.fail("Failed to capture screenshot: " + e.getMessage());
            return null; // Handle as per your application's error strategy
        }
        return dest;
    }
}