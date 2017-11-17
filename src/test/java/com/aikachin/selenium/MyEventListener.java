package com.aikachin.selenium;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.events.AbstractWebDriverEventListener;

/**
 * @Author: AIkachin
 * @Description:
 * @Date: Created in 17:21 2017/10/18 0018.
 * @Modified by :
 */
public class MyEventListener extends AbstractWebDriverEventListener {
    @Override
    public void afterNavigateTo(String url, WebDriver driver) {
        System.out.println("After navigate to : " + url);
    }

    @Override
    public void afterNavigateBack(WebDriver driver) {
        System.out.println("After navigate back to : " + driver.getCurrentUrl());
    }

    @Override
    public void afterClickOn(WebElement element, WebDriver driver) {
        System.out.println("After click on : " + element.getText());
    }
}
