package com.aikachin.selenium;

import junit.framework.TestCase;
import net.lightbody.bmp.BrowserMobProxyServer;
import net.lightbody.bmp.client.ClientUtil;
import net.lightbody.bmp.core.har.Har;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;

/**
 * @Author: Aikachin
 * @Description:
 * @Date: Created in 11:01 2017/10/31 0031.
 * @Modified by :
 */
public class testBrowserMobProxy extends TestCase {
    public void testBaidu() throws Exception {
        BrowserMobProxyServer server = new BrowserMobProxyServer();
        server.start();

        Proxy proxy = ClientUtil.createSeleniumProxy(server);

        DesiredCapabilities dc = new DesiredCapabilities();
        dc.setCapability(CapabilityType.PROXY, proxy);

        WebDriver driver = new FirefoxDriver(dc);

        server.newHar("https://www.baidu.com");
        driver.get("https://www.baidu.com");
        Har har = server.getHar();
        har.writeTo(new File("./src/test/baidu.com.har.json"));
        server.stop();
        driver.close();
    }
}
