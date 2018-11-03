package TestSuite;

import ReusableObjects.Reusable_Methods_Loggers;
import Utilities.Abstract_Class;
import Utilities.Abstract_Class_Parallel;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import org.testng.annotations.Test;

import java.io.IOException;

public class Ecommerce_Test extends Abstract_Class_Parallel {

    @Test()
    public void TShirts () throws IOException, InterruptedException {

        Reusable_Methods_Loggers.navigate(logger, driver, "http://automationpractice.com/index.php");

        String title = driver.getTitle();

        if (title.equalsIgnoreCase("My-Store")) {

            logger.log(LogStatus.PASS, "The title matches");

        } else {

            logger.log(LogStatus.FAIL, "The title doesn't match " + title);

        }

        Reusable_Methods_Loggers.mouseHover(logger, driver, "//*[@title='Women']", "Women Tab");

        Thread.sleep(2000);

        Reusable_Methods_Loggers.clickMethodByIndex(logger, driver, "//*[@title='T-shirts']", 0,"T Shirt Link");

        //scrolling to the page
        jse.executeScript("scroll(0,350)");

        Thread.sleep(1000);

        Reusable_Methods_Loggers.mouseHover(logger, driver, "//*[@title='Faded Short Sleeve T-shirts']", "Picture");

        Reusable_Methods_Loggers.clickMethod(logger, driver,"//*[@title='Add to cart']", "Cart");

        Thread.sleep(1500);

        Reusable_Methods_Loggers.compareMessages(logger,driver,"//*[@id='layer_cart']/div[1]/div[1]/h2",0,"Product successfully added to your shopping cart","Check point");

        Reusable_Methods_Loggers.clickMethod(logger, driver, "//*[@title='Proceed to checkout']", "Checkout");

        Thread.sleep(1500);
        Reusable_Methods_Loggers.clearMethod(logger, driver, "//*[@class='cart_quantity_input form-control grey']", "Quantity Field");

        Reusable_Methods_Loggers.sendKeysMethod(logger, driver, "//*[@class='cart_quantity_input form-control grey']", "3","Increase Quantity Field");

        Thread.sleep(1500);
        Reusable_Methods_Loggers.clickMethod(logger, driver, "//*[text()='Proceed to checkout']", "Checkout2");
    }



}
