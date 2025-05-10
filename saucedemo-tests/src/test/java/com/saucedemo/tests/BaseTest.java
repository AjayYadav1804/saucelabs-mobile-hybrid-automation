package com.saucedemo.tests;

import com.saucedemo.pageobjects.CartPage;
import com.saucedemo.pageobjects.CheckoutPage;
import com.saucedemo.pageobjects.InventoryPage;
import com.saucedemo.pageobjects.LoginPage;
import com.saucedemo.pageobjects.LogoutPage;
import com.saucedemo.utils.ExtentReportManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.ITestContext;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class BaseTest {
    protected WebDriver driver;
    protected Properties properties;

    protected LoginPage loginPage;
    protected InventoryPage inventoryPage;
    protected CartPage cartPage;
    protected CheckoutPage checkoutPage;
    protected LogoutPage logoutPage;

    protected static final Logger logger = LogManager.getLogger(BaseTest.class);

    @BeforeMethod
    public void setUp(ITestContext context) throws IOException {
        ExtentReportManager.initializeReport();

        properties = new Properties();
        FileInputStream fileInputStream = new FileInputStream("src/main/java/resources/config.properties");
        properties.load(fileInputStream);
        logger.info("Configuration loaded from properties file.");

        String browser = properties.getProperty("browser").toLowerCase();
        switch (browser) {
            case "chrome":
                System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "/libs/chromedriver.exe");
                driver = new ChromeDriver();
                break;
            case "firefox":
                System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir") + "/libs/geckodriver.exe");
                driver = new FirefoxDriver();
                break;
            case "edge":
                System.setProperty("webdriver.edge.driver", System.getProperty("user.dir") + "/libs/msedgedriver.exe");
                driver = new EdgeDriver();
                break;
            default:
                throw new IllegalStateException("Unsupported browser type: " + browser);
        }

        driver.manage().window().maximize();
        logger.info("{} browser launched and maximized.", browser);

        driver.get("https://www.saucedemo.com/v1/");
        logger.info("Navigated to SauceDemo website.");

        // Initialize the page objects
        loginPage = new LoginPage(driver);
        inventoryPage = new InventoryPage(driver);
        cartPage = new CartPage(driver);
        checkoutPage = new CheckoutPage(driver);
        logoutPage = new LogoutPage(driver);
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
            logger.info("Browser closed.");
        }
        ExtentReportManager.endReport();
    }

    public String getUsername() {
        return properties.getProperty("username");
    }

    public String getPassword() {
        return properties.getProperty("password");
    }
}