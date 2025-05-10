package com.saucelabs.mobile.tests;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentTest;
import com.saucelabs.mobile.base.BaseTest;
import com.saucelabs.mobile.pages.CartPage;
import com.saucelabs.mobile.pages.CheckoutCompletePage;
import com.saucelabs.mobile.pages.CheckoutPage;
import com.saucelabs.mobile.pages.HomePage;
import com.saucelabs.mobile.pages.LoginPage;
import com.saucelabs.mobile.pages.MenuPage;
import com.saucelabs.mobile.pages.PaymentPage;
import com.saucelabs.mobile.pages.ProductPage;
import com.saucelabs.mobile.utils.ConfigReader;
import com.saucelabs.mobile.utils.ExcelUtil;
import com.saucelabs.mobile.utils.ExtentReportManager;

public class EndToEndTest extends BaseTest {

	private ExtentTest test;
	private List<Map<String, String>> testData;

   @Test
    public void sauceLabsEndToEndTest() {
    	 //test = ExtentReportManager.createTest("Sauce Labs End to End Workflow Test");
    	String methodName = new Object(){}.getClass().getEnclosingMethod().getName();
    	test=ExtentReportManager.createTest(methodName, "Validate full application flow");
    
    	CheckAndCreateBrowser();
    	 // Initialize Page Objects
        homePage = new HomePage(driver,test);
         menuPage = new MenuPage(driver,test);
         loginPage = new LoginPage(driver,test);
         productPage = new ProductPage(driver,test);
        cartPage = new CartPage(driver,test);
         checkoutPage = new CheckoutPage(driver,test);
         paymentPage = new PaymentPage(driver,test);
         checkoutCompletePage = new CheckoutCompletePage(driver,test); 
		
		testData=excelUtil.getTestData("Checkout",methodName);
    	//ExtentTest test = ExtentReportManager.createTest("testSauceDemo", data.get("TestCaseId") + " - Sauce Labs End to End Workflow Test");
    	
    	// System.out.println("Running test: " + data.get("TestCaseId"));
    	 
		for (Map<String, String> data1 : testData) {
            if (methodName.equals(data1.get("TestMethodName"))) {
            	
            	// Checkout details
                String fullName = data1.get("FullName");
                String address1 = data1.get("Addressline1");
                String address2 = data1.get("Addressline2");
                String city = data1.get("City");
                String state = data1.get("State");
                String zipCode = data1.get("ZipCode");
                String country = data1.get("Country");
                
                //Payment details
                String fullName1 = data1.get("FullName1");
                String cardNumber = data1.get("CardNumber");
                String expirationDate = data1.get("ExpirationDate");
                String securityCode = data1.get("SecurityCode");
                
               // Execute the test flow
               menuPage.openMenu();
               loginPage.login(ConfigReader.getProperty("username"), ConfigReader.getProperty("password"));
               productPage.selectProduct();
               productPage.incrementProductCount();
               productPage.addToCart();
               cartPage.openCart();
               cartPage.proceedToCheckout();
               checkoutPage.fillCheckoutInformation(fullName, address1, address2, city, state, zipCode, country);
               checkoutPage.proceedToPayment();
               paymentPage.fillPaymentInformation(fullName1, cardNumber, expirationDate, securityCode);
               paymentPage.reviewOrder();
               paymentPage.placeOrderButton();
               // Validate thank you message
               boolean isThankYouMessageDisplayed = checkoutCompletePage.isThankYouMessageDisplayed();
               assert isThankYouMessageDisplayed : "Thank you message was not displayed.";

               checkoutCompletePage.continueShopping();

               // Validate the user is returned to the Home Screen
               assert homePage.isAtHomePage();
            	
            	
            }
		} 
         
    }
    
   
}
