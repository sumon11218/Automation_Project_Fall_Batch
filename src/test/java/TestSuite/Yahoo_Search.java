package TestSuite;

import ReusableObjects.Reusable_Methods_Loggers;
import Utilities.Abstract_Class;
import Utilities.Abstract_Class_Parallel;
import com.relevantcodes.extentreports.LogStatus;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.List;

import static ReusableObjects.Reusable_Methods.sendKeysMethod;
import static ReusableObjects.Reusable_Methods.submitMethod;

public class Yahoo_Search extends Abstract_Class_Parallel {

    @Test
    public void YahooSearchResult() throws InterruptedException, IOException {
        //navigate to yahoo homepage
        Reusable_Methods_Loggers.navigate(logger,driver,"https://www.yahoo.com");
        //verify the home page title
        String yahooTitle = driver.getTitle();
        if(yahooTitle.equalsIgnoreCase("Yahoo")){
            logger.log(LogStatus.PASS,"The yahoo title matches");
        } else {
            logger.log(LogStatus.FAIL,"The yahoo title doesn't match " + yahooTitle);
        }

        //verify the list count for the link
        List<WebElement> linkCount = driver.findElements(By.xpath("//*[contains(@class,'Mstart(')]"));
        logger.log(LogStatus.INFO,"The link count is " + linkCount.size());
        //report.endTest(logger);

        //enter a keyword on a search field
        Reusable_Methods_Loggers.sendKeysMethod(logger,driver,"//*[@name='p']","Brooklyn","Search Field");

        //click on search icon
        Reusable_Methods_Loggers.clickMethod(logger,driver,"//*[@id='uh-search-button']","Search Icon");

        //define javascrip executor
        JavascriptExecutor jse = (JavascriptExecutor)driver;
        //scroll to the bottom of the page
        logger.log(LogStatus.INFO,"Scrolling to the bottom of the search result page");
        Thread.sleep(1500);
        jse.executeScript("scroll(0,2000)");
    }//end of test
}