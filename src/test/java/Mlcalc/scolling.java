package Mlcalc;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class scolling {
  WebDriver driver;
    @Test
    public void testScenario() throws InterruptedException {

        //define the driver path
        System.setProperty("webdriver.chrome.driver","src\\main\\resources\\chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized", "--incognito");
        driver = new ChromeDriver(options);
        //timeout using implicit wait
        driver.manage().timeouts().implicitlyWait(15,TimeUnit.SECONDS);

        //navigate to site
        driver.navigate().to("https://www.mortgagecalculator.net/");
        //Defining the javascriptexecutor
        JavascriptExecutor jse = (JavascriptExecutor)driver;
        //scroll into the calculate element
        WebElement calculateBtn = driver.findElement(By.xpath("//*[@value='Calculate Now']"));
        jse.executeScript("arguments[0].scrollIntoView(true);",calculateBtn);
        Thread.sleep(1500);
        //scroll number of times going down
        jse.executeScript("scroll(0,700)");
        Thread.sleep(1500);
        //going up
        jse.executeScript("scroll(0,-700)");



    }



}
