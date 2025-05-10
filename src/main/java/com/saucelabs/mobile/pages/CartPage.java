package com.saucelabs.mobile.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

import com.aventstack.extentreports.ExtentTest;
import com.saucelabs.mobile.utils.ExtentReportManager;

public class CartPage extends Actions{

    private AndroidDriver driver;
    private ExtentTest test;

    @AndroidFindBy(id = "com.saucelabs.mydemoapp.android:id/cartIV")
    private WebElement cartBadge;

    @AndroidFindBy(id = "com.saucelabs.mydemoapp.android:id/cartBt")
    private WebElement proceedToCheckoutButton;

    public CartPage(AndroidDriver driver, ExtentTest test) {
    	super(driver,test);
        this.driver = driver;
        this.test=test;
        //PageFactory.initElements(driver, this);
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
        //test = ExtentReportManager.createTest("Cart Page Test");
    }

    public void openCart() {
        try {
        	performClick(cartBadge,"Open Cart");
            //cartBadge.click();
            //test.info("Opened cart");
        } catch (Exception e) {
            test.fail("Failed to open cart: " + e.getMessage());
        }
    }

    public void proceedToCheckout() {
        try {
        	performClick(proceedToCheckoutButton,"Proceed To Checkout");
            //proceedToCheckoutButton.click();
            //test.info("Clicked on proceed to checkout button");
        } catch (Exception e) {
            test.fail("Failed to proceed to checkout: " + e.getMessage());
        }
    }
}
