package com.aikachin.pageObject;

import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Date;
import java.util.StringTokenizer;

/**
 * @Author: Aikachin
 * @Description: 用cookies来登录
 * @Date: Created in 10:21 2017/10/23 0023.
 * @Modified by :
 */
public class LoginByCookie {
    WebDriver driver;
    BufferedReader br;
    // String filePath = "./src/test/java/com/aikachin/bakcup/cnblogs.cookies.txt";
    // String urlCnblogs = "https://www.cnblogs.com/";

    public LoginByCookie(WebDriver driver) {
        this.driver = driver;
    }

    // 打开页面，利用cookies登录
    public void login(String filePath){
        // driver.get(url);
        loadCookies(filePath);

        // driver.get(url);
        // try {
        //     Thread.sleep(3000);
        // } catch (InterruptedException ie) {
        //     ie.printStackTrace();
        // }
        // driver.quit();
    }

    // 读取cookies信息
    public void loadCookies(String filePath) {
        try {
            File cookieFile = new File(filePath);
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
    }
}
