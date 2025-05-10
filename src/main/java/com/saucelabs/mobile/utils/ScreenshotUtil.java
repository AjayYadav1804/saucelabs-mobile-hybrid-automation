package com.saucelabs.mobile.utils;

import org.apache.commons.io.FileUtils;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ScreenshotUtil {

    public static String captureScreenshot(AndroidDriver driver, String testName) {
    	String destFile = null;
        try {
        	String dateName = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
            TakesScreenshot ts = (TakesScreenshot) driver;
            File srcFile = ts.getScreenshotAs(OutputType.FILE);
            destFile = System.getProperty("user.dir") + "/reports/screenshots/" + testName + dateName + ".png";
            FileUtils.copyFile(srcFile, new File(destFile));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return destFile;
    }
}