# Sauce Labs Mobile App Automation Framework

## 📱 Overview
Automated testing framework for mobile hybrid(non-native) Sauce Labs application using Appium. The framework implements Page Object Model design pattern and supports end-to-end testing of the shopping flow.

## 🚀 Tech Stack
- Java
- Appium
- TestNG
- Maven
- Extent Reports
- Apache POI (Excel Operations)
- Log4j (Logging)

## 🧰 Features Automated

### End-to-End Shopping Flow
1. Application Launch
2. User Authentication
3. Product Selection
4. Cart Management
5. Checkout Process
6. Payment Processing
7. Order Confirmation

### Test Scenarios Coverage
- Login functionality
- Product catalog navigation
- Cart operations
- Checkout process
- Payment processing
- Order confirmation
- Navigation flow validation

## 📁 Project Structure
```
SauceLabsAutomationBrowserStack/
├── src/
│   ├── main/java/
│   │   └── com.saucelabs.mobile/
│   │       ├── base/
│   │       │   └── BaseTest.java
│   │       ├── pages/
│   │       │   ├── Actions.java
│   │       │   ├── CartPage.java
│   │       │   ├── CheckoutCompletePage.java
│   │       │   ├── CheckoutPage.java
│   │       │   ├── HomePage.java
│   │       │   ├── LoginPage.java
│   │       │   ├── MenuPage.java
│   │       │   ├── PaymentPage.java
│   │       │   └── ProductPage.java
│   │       └── utils/
│   │           ├── ConfigReader.java
│   │           ├── ExcelUtil.java
│   │           ├── ExtentReportManager.java
│   │           ├── LoggingUtils.java
│   │           └── ScreenshotUtil.java
│   └── test/java/
│       └── com.saucelabs.mobile.tests/
│           └── EndToEndTest.java
├── reports/
│   ├── screenshots/
│   └── testReport.html
├── resources/
├── logs/
├── test-output/
└── pom.xml
```

## ⚙️ Setup Requirements

### Prerequisites
1. Java JDK 8 or higher
2. Maven
3. Appium
4. Andriod Studio


## 🏃‍♂️ Running Tests

### Local Execution
```bash
mvn clean test
```

### Running Specific Test
```bash
mvn test -Dtest=EndToEndTest
```

## 📝 Test Case: E2E Shopping Flow

### Steps Automated
1. Application Launch
   - Open Sauce Labs mobile application
2. Navigation & Login
   - Click on Menu
   - Navigate to Login
   - Authenticate with valid credentials
3. Product Selection
   - Choose product
   - Adjust quantity using counter
   - Add to cart
4. Checkout Process
   - Access cart
   - Initiate checkout
   - Fill shipping information
   - Complete payment details
5. Order Completion
   - Place order
   - Verify order confirmation
   - Return to home screen

## 📊 Reporting

### Extent Reports
- Detailed test execution reports
- Screenshot capture on failure
- Step-by-step execution logs

Here is the Extent report overview:
![Image](https://github.com/user-attachments/assets/d7e54384-13f7-4514-abd7-75462038443a)
![Image](https://github.com/user-attachments/assets/87c788a6-afb7-4138-92b0-0b84fe37c68e)


## 🔍 Logging
- Implemented using Log4j
- Separate logs for:
  - Test execution
  - API calls
  - Error tracking

## 🛠️ Troubleshooting

### Common Issues
1. BrowserStack Connection
   ```java
   Solution: Check credentials and network connectivity
   ```

2. Test Failures
   ```java
   Solution: Check screenshot directory and logs for details
   ```

## 📖 Best Practices

### Framework Design
- Page Object Model
- Data-driven approach
- Reusable components
- Proper error handling
- Comprehensive logging

### Test Development
- Meaningful test names
- Independent test cases
- Proper assertions
- Clear documentation
- Regular maintenance


## ✍️ Authors
[Ajay Yadav Gelloboyina]

