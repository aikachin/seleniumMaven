package com.aikachin.selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static com.aikachin.bakcup.testVariablesSetting.CHROME_DRIVER_PATH;

/**
 * Created by Administrator on 2017/9/27 0027.
 */
public class demoTest01 {
    @BeforeClass
    public void beforeClass()  {
        System.out.println("用例执行前打印...");
        System.out.println("每条TestCase用例是互不相干的");
        System.out.println("用例开始执行...");
    }

    @AfterClass
    public void afterClass() {
        System.out.println("用例结束...");
    }

    @Test
    public void action() throws InterruptedException{
        // 设置驱动所在位置
        System.setProperty("webdriver.chrome.driver", CHROME_DRIVER_PATH);
        // 设置浏览器驱动
        WebDriver driver = new ChromeDriver();
        // 打开禅道界面
        driver.get("http://www.zentao.net/user-login.html");
        // 窗口最大化
        driver.manage().window().maximize();
        // 选择输入框输入用户名
        driver.findElement(By.id("account")).sendKeys("wkfldlk@126.com");
        // 输入密码
        driver.findElement(By.id("password")).sendKeys("123456");
        // 点击登录
        driver.findElement(By.cssSelector("#submit")).click();
        // 设置10秒内如果元素还未出现就抛出异常
        new WebDriverWait(driver, 10).until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".panel-heading>strong")));
        // 设置隐式等待
        //driver.manage().timeouts(5000);

        // 抓取成功登录后的用户名信息
        String text = driver.findElement((By.cssSelector("#siteNav>a:nth-child(4)"))).getText();
        Thread.sleep(3000);
        Assert.assertEquals(text, "科林");

        String title = driver.getTitle();
        Assert.assertEquals(title, "用户中心 - 禅道开源项目管理软件");

        String text2 = driver.findElement(By.cssSelector(".panel-heading>strong")).getText();
        Thread.sleep(3000);
        System.out.println(text + "123\r\n" + text2);
        // 断言——校验是否登录成功
        Assert.assertEquals(text2, "fredlicColin");

        try {
            // 页面等待
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // 关闭浏览器进程及驱动
        driver.quit();

    }
}
