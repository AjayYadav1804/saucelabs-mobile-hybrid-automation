package com.saucelabs.mobile.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

import com.aventstack.extentreports.ExtentTest;
import com.saucelabs.mobile.utils.ExtentReportManager;


public class CheckoutPage extends Actions{

    private AndroidDriver driver;
    ExtentTest test;

    @AndroidFindBy(id = "com.saucelabs.mydemoapp.android:id/fullNameET")
    private WebElement fullNameField;

    @AndroidFindBy(id = "com.saucelabs.mydemoapp.android:id/address1ET")
    private WebElement addressLine1Field;

    @AndroidFindBy(id = "com.saucelabs.mydemoapp.android:id/address2ET")
    private WebElement addressLine2Field;

    @AndroidFindBy(id = "com.saucelabs.mydemoapp.android:id/cityET")
    private WebElement cityField;

    @AndroidFindBy(id = "com.saucelabs.mydemoapp.android:id/stateET")
    private WebElement stateField;

    @AndroidFindBy(id = "com.saucelabs.mydemoapp.android:id/zipET")
    private WebElement zipCodeField;

    @AndroidFindBy(id = "com.saucelabs.mydemoapp.android:id/countryET")
    private WebElement countryField;

    @AndroidFindBy(id = "com.saucelabs.mydemoapp.android:id/paymentBtn")
    private WebElement toPaymentButton;

    public CheckoutPage(AndroidDriver driver, ExtentTest test) {
    	super(driver,test);
        this.driver = driver;
        this.test=test;
        //PageFactory.initElements(driver, this);
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
        //test = ExtentReportManager.createTest("Checkout Page Test");
    }

    public void fillCheckoutInformation(String fullName, String address1, String address2, 
                                        String city, String state, String zipCode, String country) {
        try {
        	performSendKeys(fullNameField, fullName, "Username");
            //fullNameField.sendKeys(fullName);
            //test.info("Entered full name: " + fullName);
        	performSendKeys(addressLine1Field, address1, "Address Line 1");
            //addressLine1Field.sendKeys(address1);
            //test.info("Entered address line 1: " + address1);
			performSendKeys(addressLine2Field, address2, "Address Line 2");
            //addressLine2Field.sendKeys(address2);
            //test.info("Entered address line 2: " + address2);
			performSendKeys(cityField, city, "City");
            //cityField.sendKeys(city);
            //test.info("Entered city: " + city);
			performSendKeys(stateField, state, "State/Region");
            //stateField.sendKeys(state);
            //test.info("Entered state/region: " + state);
			performSendKeys(zipCodeField, zipCode, "Zip Code");
            //zipCodeField.sendKeys(zipCode);
            //test.info("Entered zip code: " + zipCode);
			performSendKeys(countryField, country, "Country");
            //countryField.sendKeys(country);
            //test.info("Entered country: " + country);
        } catch (Exception e) {
            test.fail("Failed to fill checkout information: " + e.getMessage());
        }
    }

    public void proceedToPayment() {
        try {
        	performClick(toPaymentButton,"To Payments");
            //toPaymentButton.click();
            //test.info("Clicked on To Payment button");
        } catch (Exception e) {
            test.fail("Failed to proceed to payment: " + e.getMessage());
        }
    }
}