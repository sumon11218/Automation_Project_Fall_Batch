package Ecommerce_Automation;

import ReusableObjects.Reusable_Methods_Loggers;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;
import sun.awt.windows.ThemeReader;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class Action_Item {


    WebDriver driver;

    ExtentReports report;

    ExtentTest logger1;

    ExtentTest logger2;

    JavascriptExecutor jse;

    @BeforeSuite
    public void openBrowser() {

        System.setProperty("webdriver.chrome.driver", "src\\main\\resources\\chromedriver.exe");

        ChromeOptions options = new ChromeOptions();

        options.addArguments("start-maximized", "incognito","disable-infobars");

        driver = new ChromeDriver(options);

        driver.manage().timeouts().implicitlyWait(7, TimeUnit.SECONDS);

        report = new ExtentReports("src\\main\\java\\Report_Folder\\ExtentReport.html", true);

        //define the javascript
        jse = (JavascriptExecutor)driver;

    }

    @AfterSuite
    public void closeBrowser() {

        report.endTest(logger1);
        report.endTest(logger2);

        report.flush();

        report.close();

        //driver.quit();

    }

    @Test(priority = 1)
    public void TShirts () throws IOException, InterruptedException {

        logger1 = report.startTest("Proceed to Check out for Tshirts");

        Reusable_Methods_Loggers.navigate(logger1, driver, "http://automationpractice.com/index.php");

        String title = driver.getTitle();

        if (title.equalsIgnoreCase("My-Store")) {

            logger1.log(LogStatus.PASS, "The title matches");

        } else {

            logger1.log(LogStatus.FAIL, "The title doesn't match " + title);

        }

        Reusable_Methods_Loggers.mouseHover(logger1, driver, "//*[@title='Women']", "Women Tab");

        Thread.sleep(2000);

        Reusable_Methods_Loggers.clickMethodByIndex(logger1, driver, "//*[@title='T-shirts']", 0,"T Shirt Link");

        //scrolling to the page
        jse.executeScript("scroll(0,350)");

        Thread.sleep(1000);

        Reusable_Methods_Loggers.mouseHover(logger1, driver, "//*[@title='Faded Short Sleeve T-shirts']", "Picture");

        Reusable_Methods_Loggers.clickMethod(logger1, driver,"//*[@title='Add to cart']", "Cart");

        Thread.sleep(1500);

        Reusable_Methods_Loggers.compareMessages(logger1,driver,"//*[@id='layer_cart']/div[1]/div[1]/h2",0,"Product successfully added to your shopping cart","Check point");

        Reusable_Methods_Loggers.clickMethod(logger1, driver, "//*[@title='Proceed to checkout']", "Checkout");

        Thread.sleep(1500);
        Reusable_Methods_Loggers.clearMethod(logger1, driver, "//*[@class='cart_quantity_input form-control grey']", "Quantity Field");

        Reusable_Methods_Loggers.sendKeysMethod(logger1, driver, "//*[@class='cart_quantity_input form-control grey']", "3","Increase Quantity Field");

        Thread.sleep(1500);
        Reusable_Methods_Loggers.clickMethod(logger1, driver, "//*[text()='Proceed to checkout']", "Checkout2");
    }

    @Test(priority = 2)
    public void SummerDresses () throws IOException, InterruptedException {

        logger2 = report.startTest("Proceed to Check out for Summer Dresses");

        Reusable_Methods_Loggers.navigate(logger2, driver, "http://automationpractice.com/index.php");
        // jse.executeScript("scroll(0,-600)");
        Thread.sleep(1500);
        Reusable_Methods_Loggers.mouseHoverByIndex(logger2, driver, "//*[@class='sf-with-ul']", 3,"Dresses");

        Thread.sleep(2000);

        Reusable_Methods_Loggers.clickMethodByIndex(logger2, driver, "//*[text()='Summer Dresses']",1, "Summer Dresses");

        jse.executeScript("scroll(0,300)");

        Thread.sleep(1000);

        Reusable_Methods_Loggers.mouseHoverByIndex(logger2, driver, "//*[@title='Printed Summer Dress']", 2,"Printed Summer Dress");

        Reusable_Methods_Loggers.clickMethodByIndex(logger2, driver, "//*[@title='View']", 0,"More Btn");

        Thread.sleep(2500);
        //clearing the qty
        Reusable_Methods_Loggers.clearMethod(logger2, driver, "//*[@name='qty']", "Quantity Field");
        Thread.sleep(1000);
        //entering new qty
        Reusable_Methods_Loggers.sendKeysMethod(logger2, driver, "//*[@name='qty']", "3","Increase Quantity Field");

        Reusable_Methods_Loggers.selectByText(logger2,driver,"//*[@name='group_1']","M","Sizes");

        Reusable_Methods_Loggers.clickMethod(logger2, driver, "//*[@name='Submit']", "Add to cart");

        Thread.sleep(2000);
        Reusable_Methods_Loggers.compareMessages(logger2,driver,"//*[@id=\"layer_cart\"]/div[1]/div[1]/h2",0,"Product successfully added to your shopping cart","Check point");

        Reusable_Methods_Loggers.clickMethodByIndex(logger2, driver, "//*[@title='Proceed to checkout']", 0,"Checkout");

        //delete first item
        Reusable_Methods_Loggers.clickMethodByIndex(logger2, driver, "//*[@title='Delete']", 0,"Trash Icon");
        //delete second item
        Reusable_Methods_Loggers.clickMethodByIndex(logger2, driver, "//*[@title='Delete']", 0,"Trash Icon");

        Thread.sleep(1500);
        Reusable_Methods_Loggers.compareMessages(logger2,driver,"//*[@class='alert alert-warning']",0,"Your shopping cart is empty","Empty Message");

    }//end of test case 2

}//end of class
