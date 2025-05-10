package com.saucelabs.mobile.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

import com.aventstack.extentreports.ExtentTest;
import com.saucelabs.mobile.utils.ExtentReportManager;

public class ProductPage extends Actions{

    private AndroidDriver driver;
    ExtentTest test;

    @AndroidFindBy(xpath = "(//android.widget.ImageView[@content-desc='Product Image'])[1]")
    private WebElement productImage;

    @AndroidFindBy(id = "com.saucelabs.mydemoapp.android:id/plusIV")
    private WebElement plusButton;

    @AndroidFindBy(id = "com.saucelabs.mydemoapp.android:id/cartBt")
    private WebElement addToCartButton;

    public ProductPage(AndroidDriver driver, ExtentTest test) {
    	super(driver,test);
        this.driver = driver;
        this.test=test;
        //PageFactory.initElements(driver, this);
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
       
    }

    public void selectProduct() {
        try {
        	performClick(productImage,"Selected product");
            //productImage.click();
            //test.info("Selected product");
        } catch (Exception e) {
            test.fail("Failed to select product: " + e.getMessage());
        }
    }

    public void incrementProductCount() {
        try {
        	performClick(plusButton,"Product Count + Icon");
            //plusButton.click();
            //test.info("Incremented product count");
        } catch (Exception e) {
            test.fail("Failed to increment product count: " + e.getMessage());
        }
    }

    public void addToCart() {
        try {
        	performClick(addToCartButton,"add to cart button");
            //addToCartButton.click();
            //test.info("Clicked on add to cart button");
        } catch (Exception e) {
            test.fail("Failed to add to cart: " + e.getMessage());
        }
    }
}