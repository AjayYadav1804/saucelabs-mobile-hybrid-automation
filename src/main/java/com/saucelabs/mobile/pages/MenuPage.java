package com.saucelabs.mobile.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

import com.aventstack.extentreports.ExtentTest;
import com.saucelabs.mobile.utils.ExtentReportManager;

public class MenuPage extends Actions{

    private AndroidDriver driver;
    ExtentTest test;
    
    @AndroidFindBy(id = "com.saucelabs.mydemoapp.android:id/menuIV")
    private WebElement menuButton;

    @AndroidFindBy(xpath = "//android.widget.TextView[@content-desc='Login Menu Item']")
    private WebElement loginOption;

    public MenuPage(AndroidDriver driver, ExtentTest test) {
    	super(driver,test);
        this.driver = driver;
        this.test=test;
        //PageFactory.initElements(driver, this);
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);   
    }

    public void openMenu() {
        try {
        	performClick(menuButton,"Menu Options");
            //menuButton.click();
            //test.info("Clicked on the Menu button");
        	performClick(loginOption,"Login Options");
            //loginOption.click();
            //test.info("Clicked on the Login option");
        } catch (Exception e) {
            test.fail("Failed to open the menu: " + e.getMessage());
        }
    }
}