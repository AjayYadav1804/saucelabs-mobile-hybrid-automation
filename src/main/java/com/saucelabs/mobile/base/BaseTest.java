package com.saucelabs.mobile.base;

import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;

import io.appium.java_client.AppiumClientConfig;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import java.io.File;
import java.net.MalformedURLException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
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

public class BaseTest {
    public AndroidDriver driver;
    public AppiumDriverLocalService service;
	//public static ExtentReports extent;
	public static List <ExtentTest> cTest;
	//public static ExtentTest test;

    // Page Objects
    protected HomePage homePage;
    protected MenuPage menuPage;
    protected LoginPage loginPage;
    protected ProductPage productPage;
    protected CartPage cartPage;
    protected CheckoutPage checkoutPage;
    protected PaymentPage paymentPage;
    protected CheckoutCompletePage checkoutCompletePage; 

    // Excel Utility
    public ExcelUtil excelUtil;
    
//    @BeforeSuite
//    public void setup_suite_initials() {
//    	startReport();
//
//    }
    
    
    
    
    @BeforeSuite
    public void createExtentTestObjects() {
    	ExtentReportManager.initializeReport();
    }

    @BeforeClass
    public void setup() throws MalformedURLException {
        try {
        	 
        	// Load configuration properties
            ConfigReader.loadProperties(System.getProperty("user.dir")+"/resources/config.properties");
            
            // Excel Utility initialization
            excelUtil = new ExcelUtil(System.getProperty("user.dir")+"/resources/testdata.xlsx");
            
            
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    
    public void CheckAndCreateBrowser(){
    	String ipAddress = System.getProperty("ipAddress")!=null ? System.getProperty("ipAddress") : ConfigReader.getProperty("ipAddress");
		System.out.println(ipAddress);
		String port = ConfigReader.getProperty("port");
		service = startAppiumServer(ipAddress,Integer.parseInt(port));
        
        // Initialize DesiredCapabilities and driver
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("deviceName", ConfigReader.getProperty("AndroidDeviceName"));
        capabilities.setCapability("platformName", ConfigReader.getProperty("platformName"));
        capabilities.setCapability("automationName", "UiAutomator2");
        capabilities.setCapability("app", System.getProperty("user.dir")+"/resources/mda-2.2.0-25.apk");

        System.out.println(service.getUrl());
        AppiumClientConfig clientConfig = AppiumClientConfig.defaultConfig()
                .baseUrl(service.getUrl());

        driver = new AndroidDriver(clientConfig, capabilities);
		 driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));


       
    }
    
 
    

    @AfterClass
    public void teardown() {
        try {
            if (driver != null) {
                driver.quit();
            }if (service != null) {
            	service.stop();
            }
            ExtentReportManager.flushReports();
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    

    public AppiumDriverLocalService startAppiumServer(String ipAddress, int port) {
        // Create an instance of the AppiumServiceBuilder
        AppiumServiceBuilder builder = new AppiumServiceBuilder()
                .withAppiumJS(new File("C:\\Users\\agelloboyina\\AppData\\Roaming\\npm\\node_modules\\appium\\build\\lib\\main.js"))
                .withIPAddress(ipAddress)
                .usingPort(port);

        // Start the service
        AppiumDriverLocalService service = builder.build();
        service.start();

        // Ensure the service started successfully
        if (!service.isRunning()) {
            throw new IllegalStateException("Appium Server did not start!");
        }

        return service;
    }
    
//    @BeforeTest
// 	public void startReport() {
// 		
// 		try {
// 			
// 			extent = new ExtentReports();
// 	        String path = System.getProperty("user.dir") + "/reports/testReport.html";
// 	        ExtentSparkReporter sparkReporter = new ExtentSparkReporter(path);
// 	        //sparkReporter.config().setTheme(Theme.DARK);
// 	        sparkReporter.config().setDocumentTitle("Sauce Lab Test Automation Report");
// 	        sparkReporter.config().setReportName("Sauce Lab Test Execution Report");
//
// 	       extent = new ExtentReports();
// 	      extent.attachReporter(sparkReporter);
// 	     extent.setSystemInfo("Environment", "QA");
// 	    extent.setSystemInfo("Tester", "Ajay Yadav");
// 	       
// 	    	}catch(Exception e) {
// 	    	e.printStackTrace();
// 	    	
// 	    }
// 	}
    
}
