package com.saucelabs.mobile.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

import com.aventstack.extentreports.ExtentTest;
import com.saucelabs.mobile.utils.ExtentReportManager;


public class PaymentPage extends Actions {

    private AndroidDriver driver;
    ExtentTest test;

    @AndroidFindBy(id = "com.saucelabs.mydemoapp.android:id/nameET")
    private WebElement paymentFullNameField;

    @AndroidFindBy(id = "com.saucelabs.mydemoapp.android:id/cardNumberET")
    private WebElement cardNumberField;

    @AndroidFindBy(id = "com.saucelabs.mydemoapp.android:id/expirationDateET")
    private WebElement expirationDateField;

    @AndroidFindBy(id = "com.saucelabs.mydemoapp.android:id/securityCodeET")
    private WebElement securityCodeField;

    @AndroidFindBy(id = "com.saucelabs.mydemoapp.android:id/paymentBtn")
    private WebElement reviewOrderButton;
    
    @AndroidFindBy(xpath = "//android.widget.Button[@text,'Place Order']")
    private WebElement placeOrderButton;

    public PaymentPage(AndroidDriver driver,ExtentTest test) {
    	super(driver,test);
        this.driver = driver;
        this.test=test;
        //PageFactory.initElements(driver, this);
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
        //test = ExtentReportManager.createTest("Payment Page Test");
    }

    public void fillPaymentInformation(String fullName, String cardNumber, 
                                       String expirationDate, String securityCode) {
    	
    	performSendKeys(paymentFullNameField, fullName, "Full Name");
    	
        //paymentFullNameField.sendKeys(fullName);
        //test.info("Entered payment full name: " + fullName);
    	performSendKeys(cardNumberField, cardNumber, "Card Number");
        //cardNumberField.sendKeys(cardNumber);
        //test.info("Entered card number: " + cardNumber);
    	performSendKeys(expirationDateField, expirationDate, "Expiration Date");
        //expirationDateField.sendKeys(expirationDate);
        //test.info("Entered expiration date: " + expirationDate);
    	performSendKeys(securityCodeField, securityCode, "Security Code");
        //securityCodeField.sendKeys(securityCode);
       //test.info("Entered security code: " + securityCode);
    }

    public void reviewOrder() {
    	performClick(reviewOrderButton,"Review Order");
        //reviewOrderButton.click();
        //test.info("Clicked on Review Order button");
    }
    
    public void placeOrderButton() {
    	performClick(placeOrderButton,"Place Order");
    	//placeOrderButton.click();
        //test.info("Clicked on Place Order button");
    }
}