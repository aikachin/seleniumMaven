package com.aikachin.pageFactory;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;

import static com.aikachin.pageFactory.testBugfreeWithPageFactory.takeScreenShot;

/**
 * @Author: Aikachin
 * @Description: 修改Bug页面
 * @Date: Created in 17:08 2017/10/24 0024.
 * @Modified by :
 */
public class ModifyBug {
    WebDriver driver;
    @FindBy(how = How.XPATH, xpath = ".//*[@id='buttonlist']/input[4]")
    WebElement resolve;
    @FindBy(how = How.ID, id = "Custom_ResolvedBuild")
    WebElement resolvedbuild;
    @FindBy(how = How.ID, id = "BugInfoView_solution")
    WebElement solution;
    @FindBy(how = How.CSS, css = "#buttonlist > input:nth-child(1)")
    WebElement savebtn;
    @FindBy(how = How.CSS, css = "#logo > a > img")
    WebElement backtomainpage;
    @FindBy(how = How.CSS, css = "#buttonlist > input:nth-child(2)")
    WebElement modifybtn;

    File srcFile;

    public ModifyBug(WebDriver driver) {
        this.driver = driver;
        srcFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
    }

    /**
     * @Description: 修改bug
     * @Param: [resoledBuild, bugSolution]
     * @return: void
     * @Author: Aikachin
     * @Date: 2017/10/26 0026
     */
    public void modify(int count, String resoledBuild, String bugSolution) throws IOException{


        WebDriverWait wait = new WebDriverWait(driver, 30);
        // 等待“解决”按钮出现
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(".//*[@id='buttonlist']/input[4]")));

        takeScreenShot(driver);
        resolve.click();

        while (!resolvedbuild.isEnabled()){
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ie) {
                ie.printStackTrace();
            }
        }

        takeScreenShot(driver);

        resolvedbuild.sendKeys(resoledBuild);
        Select select = new Select(solution);
        select.selectByValue(bugSolution);

        try {
            Thread.sleep(5000);
        } catch (InterruptedException ie) {
            ie.printStackTrace();
        }

        takeScreenShot(driver);
        savebtn.click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(".//*[@id='buttonlist']/input[4]")));
        takeScreenShot(driver);

    }
}
