package com.aikachin.pageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.concurrent.TimeUnit;

/**
 * @Author: Aikachin
 * @Description: 短消息首页
 * @Date: Created in 14:49 2017/10/23 0023.
 * @Modified by :
 */
public class MessagePage {
    WebDriver driver;
    WebElement messageBox;
    WebElement newMessage;
    WebElement receivedMessage;

    public MessagePage(WebDriver driver) {
        this.driver = driver;

    }

    // 进入收件箱
    public void enterMsgBox() {
        messageBox = driver.findElement(By.partialLinkText("短消息"));
        messageBox.click();
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
    }

    // 发送短消息
    public void sendMessage(String toUser, String msgTitle, String msgContent) {
        enterMsgBox();
        newMessage = driver.findElement(By.partialLinkText("撰写新短消息"));
        newMessage.click();
        SendMessagePage sendMsgPage = new SendMessagePage(driver);
        sendMsgPage.sendNewMessage(toUser, msgTitle, msgContent);
    }

    // 删除所有短消息
    public void deleteAllMsg() {
        enterMsgBox();
        receivedMessage = driver.findElement(By.linkText("收件箱"));
        receivedMessage.click();
        ReceivedMessagePage receivedMsgPage = new ReceivedMessagePage(driver);
        receivedMsgPage.deleteAllMessage();
   }
}
