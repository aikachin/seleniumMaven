package com.aikachin.pageObject;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * @Author: Aikachin
 * @Description: 收件箱页面
 * @Date: Created in 14:43 2017/10/23 0023.
 * @Modified by :
 */
public class ReceivedMessagePage {
    WebDriver driver;
    WebElement selectAllBox;
    WebElement deleteSelectedMessageBtn;

    public ReceivedMessagePage(WebDriver driver) {
        this.driver = driver;
        selectAllBox = driver.findElement(By.id("chkSelAll"));
        deleteSelectedMessageBtn = driver.findElement(By.id("btnBatDel"));
    }

    // 删除选择的短消息
    public void deleteAllMessage() {
        if (!selectAllBox.isSelected()) {
            selectAllBox.click();
        }
        deleteSelectedMessageBtn.click();
        WebDriverWait wait = new WebDriverWait(driver, 300);
        wait.until(ExpectedConditions.alertIsPresent());
        Alert confirmDeletePrompt = driver.switchTo().alert();
        confirmDeletePrompt.accept();
    }

}
