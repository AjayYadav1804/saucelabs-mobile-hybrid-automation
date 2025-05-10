package com.saucelabs.mobile.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

import com.aventstack.extentreports.ExtentTest;
import com.saucelabs.mobile.utils.ExtentReportManager;

public class HomePage extends Actions{

    private AndroidDriver driver;
    ExtentTest test;

    @AndroidFindBy(id = "com.saucelabs.mydemoapp.android:id/mTvTitle")
    private WebElement homeTitle;

    public HomePage(AndroidDriver driver,ExtentTest test) {
    	super(driver,test);
        this.driver = driver;
        this.test =test;
        //PageFactory.initElements(driver, this);
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
        
    }

    public boolean isAtHomePage() {
        try {
        	Thread.sleep(3000);
            boolean isAtHome = homeTitle.isDisplayed();
            test.pass("Validated home page is displayed: " + isAtHome);
            return isAtHome;
        } catch (Exception e) {
            test.fail("Failed to validate home page: " + e.getMessage());
            return false;
        }
    }
}
