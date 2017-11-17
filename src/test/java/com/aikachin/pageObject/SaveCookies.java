package com.aikachin.pageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.io.*;
import java.util.Properties;

import static com.aikachin.bakcup.testVariablesSetting.URL_OF_BOKEYUAN_COOKIE2;

/**
 * @Author: Aikachin
 * @Description: 保存页面登录信息cookies
 * @Date: Created in 10:25 2017/10/23 0023.
 * @Modified by :
 */
public class SaveCookies {
    //取得配置文件路径
    public static String filePath = "./src/test/java/com/aikachin/bakcup/setting.properties";

    public static void mian(String args[]) throws IOException{
        WebDriver driver = new FirefoxDriver();
        String url = getProperties(filePath, "url.cnblogs");
        String url2 = getProperties(filePath, "url.cnblogsLogin");
        getCookies(driver, url, url2);
    }

    //获取目标URL
    public static String getProperties(String filePath, String name) throws IOException{
        InputStream is = new BufferedInputStream(new FileInputStream(filePath));
        Properties p = new Properties();
        p.load(is);
        return p.getProperty(name);
    }

    // public SaveCookies(WebDriver driver) {
    //     this.driver = driver;
    // }

    // 获取登录cookies
    public static void getCookies(WebDriver driver, String url, String loginPageURL) {
        driver.get(url);
        driver.findElement(By.linkText("登录")).click();
        driver.findElement(By.xpath(".//*[@id='span_userinfo']/a[1]")).click();
        driver.findElement(By.id("input1")).sendKeys("fredlic");
        driver.findElement(By.id("input2")).sendKeys("aptx_1412");
        driver.findElement(By.id("signin")).click();

        try {
            //不停的检测，一旦当前页面URL不是登录页面URL，就说明浏览器已经进行了跳转
            while (true) {
                Thread.sleep(500L);
                try {
                    if (!driver.getCurrentUrl().startsWith(getProperties(filePath, loginPageURL))) {
                        break;
                    }
                } catch (IOException ioe) {
                    ioe.printStackTrace();
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        File cookieFile = new File(URL_OF_BOKEYUAN_COOKIE2);

        try {
            cookieFile.delete();
            cookieFile.createNewFile();
            FileWriter fw = new FileWriter(cookieFile);
            BufferedWriter bufferedWriter = new BufferedWriter(fw);

            for (Cookie cookie : driver.manage().getCookies()) {
                bufferedWriter.write((cookie.getName() + ";"
                        + cookie.getValue() + ";"
                        + cookie.getDomain() + ";"
                        + cookie.getPath() + ";"
                        + cookie.getExpiry() + ";"
                        + cookie.isSecure()
                ));
                bufferedWriter.newLine();
            }
            bufferedWriter.flush();
            bufferedWriter.close();
            fw.close();

        } catch (IOException ioe) {
            ioe.printStackTrace();
        }

        driver.close();
        driver.quit();
    }
}
