package com.aikachin.pageFactory;

import org.junit.Assert;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.Select;

import java.io.File;
import java.io.IOException;

import static com.aikachin.pageFactory.testBugfreeWithPageFactory.takeScreenShot;

/**
 * @Author: Aikachin
 * @Description: 新建BUG
 * @Date: Created in 14:21 2017/10/24 0024.
 * @Modified by :
 */
public class NewBUG {
    WebDriver driver;
    @FindBy(how = How.ID, id = "BugInfoView_title")
    WebElement bugtitle;
    @FindBy(how = How.ID, id = "BugInfoView_assign_to_name")
    WebElement touser;
    @FindBy(how = How.ID, id = "BugInfoView_severity")
    WebElement serverity;
    @FindBy(how = How.ID, id = "Custom_BugType")
    WebElement bugtype;
    @FindBy(how = How.ID, id = "Custom_HowFound")
    WebElement howfind;
    @FindBy(how = How.ID, css = "#Custom_OpenedBuild")
    WebElement build;
    @FindBy(how = How.ID, name = "yt0")
    WebElement savebtn;
    @FindBy(how = How.ID, css = "#logo > a > img")
    WebElement backtomainpage;

    File srcFile;

    public NewBUG(WebDriver driver) {
        this.driver = driver;
        srcFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
    }

    /**
     * @Description:
     * @param: [title, toUser, serverityLevel, bugType, howFind, buildVer]
     * @return: void
     * @Author: Aikachin
     * @Date: 2017/10/26 0026
     */
    public void createBug(int count, String title, String toUser, int serverityLevel, String bugType,
                          String howFind, String buildVer) throws IOException{
        bugtitle.sendKeys(title);
        // 新建指派者对象下拉列表select对象
        touser.sendKeys(toUser);
        Select select2 = new Select(serverity);
        Select select3 = new Select(bugtype);
        Select select4 = new Select(howfind);
        build.sendKeys(buildVer);

        // 判断select下拉列表是否是多选列表

        Assert.assertFalse(select2.isMultiple());
        Assert.assertFalse(select3.isMultiple());
        Assert.assertFalse(select4.isMultiple());
        // 将下拉列表第serverityLevel+1个选中
        select2.selectByIndex(serverityLevel);
        // 将下拉列表value等于参数bugType的选项选中
        select3.selectByValue(bugType);
        // 将下拉列表显示内容为参数howFind的选项选中
        select4.selectByValue(howFind);


        // 输出下拉列表选中内容
        System.out.println(select2.getFirstSelectedOption().getText());


        takeScreenShot(driver);

        savebtn.click();
        System.out.println("createBug() method is over...");
        // backtomainpage.click();

        try {
            Thread.sleep(2000);
        } catch (InterruptedException ie) {
            ie.printStackTrace();
        }
        takeScreenShot(driver);
    }
}
