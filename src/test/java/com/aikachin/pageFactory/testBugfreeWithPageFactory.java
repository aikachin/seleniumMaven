package com.aikachin.pageFactory;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.PageFactory;

import java.io.File;
import java.io.IOException;

/**
 * @Author: Aikachin
 * @Description: 调用PageFactory对象来完成bugfree的测试
 * @Date: Created in 17:29 2017/10/24 0024.
 * @Modified by :
 */
public class testBugfreeWithPageFactory {

    public static File srcFile;
    public static int count = 0;
    public static String screenShotPath;
    public static String initialPath = "./src/test/screenShot/bugfree";

    public static void main(String args[]) throws IOException, InterruptedException{
        WebDriver driver = new FirefoxDriver();

        String url = "http://localhost:80/bugfree/";

        MainPage2 mainPage2 = PageFactory.initElements(driver, MainPage2.class);
        BugListPage bugListPage = PageFactory.initElements(driver, BugListPage.class);

        // 打开bugfree页面并登录
        mainPage2.openMainPage(url);
        takeScreenShot(driver);

        mainPage2.login2("admin", "123456");
        takeScreenShot(driver);

        String pWindowId = driver.getWindowHandle();
        // 创建新bug
        System.out.println("开始创建新bug...");
        bugListPage.newBug(count, pWindowId,"pageFactoryTest00", "小明", 1, "代码错误", "功能测试", "1.0");
        takeScreenShot(driver);
        // System.out.println("开始创建新bug2...");
        // bugListPage.newBug(count, pWindowId,"pageFactoryTest2", "小明", 2, "其他", "随机测试", "1.0");

        Thread.sleep(3000L);
        // 退出登录
        mainPage2.logout2();
        takeScreenShot(driver);

        // 重新登录
        mainPage2.openMainPage(url);
        takeScreenShot(driver);
        mainPage2.login2("admin", "123456");
        takeScreenShot(driver);

        // 修改bug
        System.out.println("开始修改bug...");
        bugListPage.modify(count, pWindowId, "4", "1.1", "Fixed");
        takeScreenShot(driver);

        // 退出登录
        mainPage2.logout2();
        takeScreenShot(driver);

        Thread.sleep(3000L);
        driver.close();
        driver.quit();

    }

    public static void takeScreenShot(WebDriver driver) throws IOException{
        srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        screenShotPath = initialPath + String.valueOf(count) + ".png";
        FileUtils.copyFile(srcFile, new File(screenShotPath));
        System.out.println("-----------" + count++);
    }
}
