package com.saucelabs.mobile.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

import com.aventstack.extentreports.ExtentTest;
import com.saucelabs.mobile.utils.ExtentReportManager;


public class LoginPage extends Actions{

    private AndroidDriver driver;
    ExtentTest test;

    @AndroidFindBy(id = "com.saucelabs.mydemoapp.android:id/nameET")
    private WebElement usernameField;

    @AndroidFindBy(id = "com.saucelabs.mydemoapp.android:id/passwordET")
    private WebElement passwordField;

    @AndroidFindBy(id = "com.saucelabs.mydemoapp.android:id/loginBtn")
    private WebElement loginButton;

    public LoginPage(AndroidDriver driver, ExtentTest test) {
    	super(driver,test);
        this.driver = driver;
        this.test=test;
        //PageFactory.initElements(driver, this);
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
        
    }

   
	public void login(String username, String password) {
        try {
        	performSendKeys(usernameField, username, "Username");
            //usernameField.sendKeys(username);
            //test.info("Entered username");
        	performSendKeys(passwordField, password, "Password");
            //passwordField.sendKeys(password);
            //test.info("Entered password");
        	performClick(loginButton,"Login");
            //loginButton.click();
            //test.info("Clicked on login button");
        } catch (Exception e) {
            test.fail("Login failed: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
