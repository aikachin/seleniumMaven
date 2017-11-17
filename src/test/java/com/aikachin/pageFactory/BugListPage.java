package com.aikachin.pageFactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import java.io.IOException;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * @Author: Aikachin
 * @Description: BUG列表页面
 * @Date: Created in 11:34 2017/10/24 0024.
 * @Modified by :
 */
public class BugListPage {
    WebDriver driver;
    @FindBy(how = How.PARTIAL_LINK_TEXT, partialLinkText = "新建")
    WebElement newbug;
    @FindBy(how = How.CLASS_NAME, className = "add_search_button")
    WebElement addSearchBtn;
    @FindBy(how = How.ID, id = "BugFreeQuery_value1")
    WebElement searchValue1;
    @FindBy(how = How.ID, id = "PostQuery")
    WebElement searchBtn;
    @FindBy(how = How.XPATH, xpath = "//*[@id=\"SearchResultDiv\"]/table/tbody/tr/td[5]/span/a")
    WebElement targetBug;
    @FindBy(how = How.XPATH, xpath = ".//*[@id='SearchResultDiv']/table/tbody/tr[1]/td[9]")
    WebElement solution;

    int num = 1;

    public BugListPage(WebDriver driver) {
        this.driver = driver;
    }

    /**
     * @Description: 根据id检索bug
     * @Param: [id]
     * @return: void
     * @Author: Aikachin
     * @Date: 2017/10/26 0026
     */
    public void searchBugById(String id){
        searchValue1.clear();
        searchValue1.sendKeys(id);
        searchBtn.click();

        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }

    /**
     * @Description: 创建新bug
     * @Param: [title, toUser, serverityLevel, bugType, howFind, buildVer]
     * @return: void
     * @Author: Aikachin
     * @Date: 2017/10/26 0026
     */
    public void newBug(int count, String pWindowId, String title, String toUser, int serverityLevel,
                       String bugType, String howFind, String buildVer) throws IOException{
        // String parentWindowId = pWindowId;
        String windowIdtemp =null;

        newbug.click();
        // System.out.println("打开新建bug窗口...");
        try {
            Thread.sleep(3000);
        } catch (InterruptedException ie) {
            ie.printStackTrace();
        }

        Set <String> allWindowsId = driver.getWindowHandles();
        for (String windowid : allWindowsId ) {
            if (driver.switchTo().window(windowid).getTitle().contains("新建Bug")) {
                windowIdtemp = windowid;
                driver.switchTo().window(windowIdtemp);
                // System.out.println(num + "切换窗口：" + driver.getTitle());
                num++;
                break;
            }
        }
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        NewBUG newBUG = PageFactory.initElements(driver, NewBUG.class);
        newBUG.createBug(count, title, toUser, serverityLevel, bugType, howFind, buildVer);

            System.out.println(num + "开始关闭窗口：" + driver.getTitle());
            num++;
            driver.switchTo().window(windowIdtemp).close();
            // WebDriverWait wait = new WebDriverWait(driver, 30);
            // wait.until(ExpectedConditions.alertIsPresent());
            // driver.switchTo().alert().accept();

        driver.switchTo().window(pWindowId);
        System.out.println(num + "切换窗口：" + driver.getTitle());
        num++;
    }

    /**
     * @Description: 查看bug
     * @Param: [id]
     * @return: void
     * @Author: Aikachin
     * @Date: 2017/10/26 0026
     */
    public void viewBug(int count, String id) throws IOException{
        searchBugById(id);
        targetBug.click();
    }

    /**
     * @Description: 调用修改BUG的Modify类的modify方法，修改BUG
     * @Param: [id, resoledbuild, bugsolution]
     * @return: void
     * @Author: Aikachin
     * @Date: 2017/10/26 0026
     */
    public void modify(int count, String pWindowId, String id, String resoledbuild, String bugsolution) throws IOException{
        addSearchBtn.click();
        viewBug(count, id);
        String windowIdtemp = null;
        try {
            Thread.sleep(3000);
        } catch (InterruptedException ie) {
            ie.printStackTrace();
        }

        Set<String> windowsId = driver.getWindowHandles();
        for (String windowId : windowsId) {
            // 根据URL后缀判断页面，来切换到指定窗口进行操作
            if(driver.switchTo().window(windowId).getCurrentUrl().endsWith("/bug/" + id)) {
                windowIdtemp = windowId;
                driver.switchTo().window(windowIdtemp);

                System.out.println("切换窗口：" + driver.getTitle());
                break;
            }
        }
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        ModifyBug modifyBug = PageFactory.initElements(driver, ModifyBug.class);
        modifyBug.modify(count, resoledbuild, bugsolution);


            System.out.println("关闭窗口：" + driver.getTitle());
            driver.switchTo().window(windowIdtemp).close();

        driver.switchTo().window(pWindowId);

        driver.navigate().refresh();
        System.out.println("targetBug after modify:" + targetBug);
        searchBugById(id);

        Assert.assertEquals(solution.getText(), bugsolution);

    }

}
