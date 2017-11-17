package com.aikachin.pageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * @Author: Aikachin
 * @Description: 发消息页面
 * @Date: Created in 14:35 2017/10/23 0023.
 * @Modified by :
 */
public class SendMessagePage {
    WebDriver driver;
    WebElement toUser;
    WebElement messageTitle;
    WebElement messageContent;
    WebElement sendBtn;

    public SendMessagePage(WebDriver driver) {
        this.driver = driver;
        toUser = driver.findElement(By.id("txtIncept"));
        messageTitle = driver.findElement(By.id("txtTitle"));
        messageContent = driver.findElement(By.id("txtContent"));
        sendBtn = driver.findElement(By.id("btnSend"));
    }

    public void sendNewMessage(String to, String title, String content) {
        toUser.sendKeys(to);
        messageTitle.sendKeys(title);
        messageContent.sendKeys(content);
        sendBtn.click();
    }

}
