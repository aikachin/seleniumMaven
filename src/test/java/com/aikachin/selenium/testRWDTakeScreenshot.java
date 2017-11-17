package com.aikachin.selenium;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.URL;

import static com.aikachin.bakcup.testVariablesSetting.URL_OF_DOUBAN;

/**
 * @Author: AIkachin
 * @Description: 使用seleniumGrid 测试
 * @Date: Created in 16:56 2017/10/18 0018.
 * @Modified by :
 */
public class testRWDTakeScreenshot {
    WebDriver driver;

    @Before
    public void setUp() throws Exception {
        // 远程控制浏览器，ip为远程目标主机的ip
        // DesiredCapabilities dc = DesiredCapabilities.firefox();
        // driver = new RemoteWebDriver(new URL("http://192.168.0.125:4444/wd/hub"), dc);

        // 对于执行模式为WebDriver的Node，使用DesiredCapabilities和RometeWebDriver。
        DesiredCapabilities dc = DesiredCapabilities.chrome();
        dc.setBrowserName("chrome");
        dc.setVersion("61");
        dc.setPlatform(Platform.WINDOWS);

        driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), dc);

        // 对于执行模式为Remote Control 的Node，使用DefaultSelenium
        // Selenium selenium = new DefaultSelenium(
        //         "localhost", 4444, "*firefox", "https://www.baidu.com");


    }

    @After
    public void tearDown() throws Exception {
        driver.close();
        driver.quit();
    }

    @Test
    public void testRWDTakeScreenshot() throws Exception {
        driver.get(URL_OF_DOUBAN);
        // WebDriver augmentedDriver = new Augmenter().augment(driver);

        // File screenshot = ((TakesScreenshot)augmentedDriver).getScreenshotAs(OutputType.FILE);
        // FileUtils.copyFile(screenshot, new File("./src/test/Selenium 2/remotewebdriver_screenshot.png"));
    }
}
