package com.aikachin.selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.io.*;
import java.util.Date;
import java.util.StringTokenizer;

import static com.aikachin.bakcup.testVariablesSetting.*;

/**
 * @Author: Aikachin
 * @Description:
 * @Date: Created in 15:11 2017/10/19 0019.
 * @Modified by :
 */
public class testGetCookies {
    // static WebDriver driver;
    static BufferedReader br;
    public static void main(String args[]) {
        // = new FirefoxDriver();
        getCookies();
        // testBaidu();
        // testCnblogs();
    }

    public static void saveCookies() {
        WebDriver driver = new FirefoxDriver();
        driver.get(URL_OF_BUGFREE);
        driver.findElement(By.id("LoginForm_username")).sendKeys("admin");
        driver.findElement(By.id("LoginForm_password")).sendKeys("123456");

        if (!driver.findElement(By.id("ForRememberMe")).isSelected()) {
            driver.findElement(By.id("ForRememberMe")).click();
        }
        driver.findElement(By.id("SubmitLoginBTN")).click();

        File cookieFile = new File("./src/test/java/com/aikachin/backup");

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
        driver.quit();
    }

    // 获取博客园登录cookies来登录
    public static void getCookies() {
        WebDriver driver = new FirefoxDriver();
        driver.get(URL_OF_BOKEYUAN);
        // driver.manage().deleteAllCookies();//删除cookie里的内容
        try {
            File cookieFile = new File("./src/test/java/com/aikachin/bakcup/cookies2.txt");
            FileReader fr = new FileReader(cookieFile);
            br = new BufferedReader(fr);
            String line;
            while ((line = br.readLine()) != null) {
                StringTokenizer st = new StringTokenizer(line, ";");
                while (st.hasMoreElements()) {
                    String name = st.nextToken();
                    String value = st.nextToken();
                    String domain = st.nextToken();
                    String path = st.nextToken();
                    Date expiry = null;
                    String dt;
                    if (!(dt = st.nextToken()).equals("null")) {
                        expiry = new Date(dt);
                    }
                    boolean isSecure = new Boolean(st.nextToken()).booleanValue();
                    Cookie cookie = new Cookie(name,
                            value,
                            domain,
                            path,
                            expiry,
                            isSecure);
                    driver.manage().addCookie(cookie);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        driver.get(URL_OF_BOKEYUAN);
        try {
            Thread.sleep(3000);
        } catch (InterruptedException ie) {
            ie.printStackTrace();
        }

        driver.quit();
    }

    public static void testBaidu() {
        System.setProperty("webdriver.chrome.driver", CHROME_DRIVER_PATH);
        WebDriver driver = new ChromeDriver();
        driver.get(URL_OF_BAIDU);
        // driver.manage().deleteAllCookies();
        Cookie c1 = new Cookie("BAIDUID", "fredlicCo");
        Cookie c2 = new Cookie("BDUSS", "baidu.123");
        driver.manage().addCookie(c1);
        driver.manage().addCookie(c2);

        // Set<Cookie> cookies = driver.manage().getCookies();

        // for (Cookie cookie : driver.manage().getCookies()) {
        //     System.out.println("name: " + cookie.getName());
        //     System.out.println("value: " + cookie.getValue());
        //     System.out.println("domain: " + cookie.getDomain());
        //     System.out.println("path: " + cookie.getPath());
        //     System.out.println("expiry: " + cookie.getExpiry());
        //     System.out.println("isSecure: " + cookie.isSecure());
        // }
            //         + cookie.getValue() + ";"
            //         + cookie.getDomain() + ";"
            //         + cookie.getPath() + ";"
            //         + cookie.getExpiry() + ";"
            //         + cookie.isSecure()
            // ););


        driver.get(URL_OF_BAIDU);
    }

    // 手动登录博客园然后保存登录cookies信息到文件
    public static void testCnblogs() {
        WebDriver wd = new FirefoxDriver();
        wd.get(URL_OF_BOKEYUAN);
        wd.findElement(By.xpath(".//*[@id='span_userinfo']/a[1]")).click();
        wd.findElement(By.id("input1")).sendKeys("aikachin");
        wd.findElement(By.id("input2")).sendKeys("aptx.1648");
        // if (wd.findElement(By.id("remember_me")).isSelected()) {
        //
        // }
        wd.findElement(By.id("signin")).click();

        try {
            //不停的检测，一旦当前页面URL不是登录页面URL，就说明浏览器已经进行了跳转
            while (true) {
                Thread.sleep(500L);
                if (!wd.getCurrentUrl().startsWith(URL_OF_BOKEYUAN_LOGIN)) {
                    break;
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        File cookieFile = new File("./src/test/java/com/aikachin/bakcup/cookies2.txt");

        try {
            cookieFile.delete();
            cookieFile.createNewFile();
            FileWriter fw = new FileWriter(cookieFile);
            BufferedWriter bufferedWriter = new BufferedWriter(fw);

            for (Cookie cookie : wd.manage().getCookies()) {
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

        //获取cookie，上面一跳出循环我认为就登录成功了，当然上面的判断不太严格，可以再进行修改
        // Set<Cookie> cookies = wd.manage().getCookies();
        // String cookieStr = "";
        // for (Cookie cookie : cookies) {
        //     cookieStr += cookie.getName() + "=" + cookie.getValue() + "; ";
        // }

        // Cookie ck1 = new Cookie("ASP.NET_SessionId", "xhqe1gol5cy1toafvwnrwolp");
        // Cookie ck2 = new Cookie("SERVERID", "227b087667da6f8e99a1165002db93f6|1508464475|1508464455");
        // // Set<Cookie> cookies = wd.manage().getCookies();
        // wd.manage().addCookie(ck1);
        // wd.manage().addCookie(ck2);
        // System.out.println("添加cookies");
        // try {
        //     Thread.sleep(3000);
        // } catch (InterruptedException ie) {
        //     ie.printStackTrace();
        // }

        wd.get(URL_OF_BOKEYUAN);
    }

}
