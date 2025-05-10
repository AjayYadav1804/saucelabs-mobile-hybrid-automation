package com.saucelabs.mobile.utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentReportManager {
	private static ExtentReports extentReports;
    private static ThreadLocal<ExtentTest> extentTest = new ThreadLocal<>();
    private static ExtentTest test;
   
    public static void initializeReport() {
    	try {
    	if (extentReports == null) {
            extentReports = new ExtentReports();
        String path = System.getProperty("user.dir") + "/reports/testReport.html";
        ExtentSparkReporter sparkReporter = new ExtentSparkReporter(path);
        //sparkReporter.config().setTheme(Theme.DARK);
        sparkReporter.config().setDocumentTitle("Sauce Lab Test Automation Report");
        sparkReporter.config().setReportName("Sauce Lab Test Execution Report");

        extentReports = new ExtentReports();
        extentReports.attachReporter(sparkReporter);
        extentReports.setSystemInfo("Environment", "QA");
        extentReports.setSystemInfo("Tester", "Ajay Yadav");
    }
    	}catch(Exception e) {
    	e.printStackTrace();
    	
    }
   }	

    
    public static synchronized ExtentTest createTest(String testName, String description) {
        test = extentReports.createTest(testName, description);
        extentTest.set(test);  // Set the test in ThreadLocal
        return test;
    }
   
    
    public static synchronized ExtentTest getTest() {
    	System.out.println(extentTest.get());
        return extentTest.get();
    }


    public static void flushReports() {
        if (extentReports != null) {
        	extentReports.flush();
        }
    }
}