package com.aikachin.selenium;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.io.File;
import java.io.IOException;

/**
 * @Author: Aikachin
 * @Description:
 * @Date: Created in 14:46 2017/10/27 0027.
 * @Modified by :
 */
public class testTakeScreenShot {
    public static void main(String args[]) throws IOException{
        WebDriver driver = new FirefoxDriver();

        driver.get("https://www.baidu.com/");
        File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(srcFile, new File("F:\\test\\screenShot\\testTakeScreenshot1.png"));

        driver.close();
    }
}
