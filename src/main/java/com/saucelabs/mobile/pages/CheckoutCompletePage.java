package com.saucelabs.mobile.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import io.appium.java_client.android.AndroidDriver;
import com.aventstack.extentreports.ExtentTest;
import com.saucelabs.mobile.utils.ExtentReportManager;

import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class CheckoutCompletePage extends Actions{

    private AndroidDriver driver;
    ExtentTest test;

    @AndroidFindBy(xpath = "//android.widget.TextView[contains(@text, 'Thank you for your order')]")
    private WebElement thankYouMessage;

    @AndroidFindBy(id = "com.saucelabs.mydemoapp.android:id/shoopingBt")
    private WebElement continueShoppingButton;

    public CheckoutCompletePage(AndroidDriver driver,ExtentTest test) {
    	super(driver,test);
        this.driver = driver;
        this.test =test;
        //PageFactory.initElements(driver, this);
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
        //test = ExtentReportManager.createTest("Checkout Complete Page Test");
    }

    public boolean isThankYouMessageDisplayed() {
        try {
        	Thread.sleep(3000);
            boolean isDisplayed = thankYouMessage.isDisplayed();
            test.info("Verified 'Thank you for your order' message is displayed: " + isDisplayed);
            return isDisplayed;
        } catch (Exception e) {
            test.fail("Failed to verify thank you message: " + e.getMessage());
            return false;
        }
    }

    public void continueShopping() {
        try {
        	performClick(continueShoppingButton,"Continue Shopping");
            //continueShoppingButton.click();
            //test.info("Clicked on Continue Shopping button");
        } catch (Exception e) {
            test.fail("Failed to continue shopping: " + e.getMessage());
        }
    }
}