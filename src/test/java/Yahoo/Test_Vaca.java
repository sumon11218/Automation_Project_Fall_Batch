package Yahoo;

import com.relevantcodes.extentreports.ExtentReports;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class Test_Vaca {

    WebDriver driver;

    @BeforeSuite
    public void openBrowser(){
        //define the driver path
        System.setProperty("webdriver.chrome.driver","src\\main\\resources\\chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized", "--incognito");
        driver = new ChromeDriver(options);
        //timeout using implicit wait
        driver.manage().timeouts().implicitlyWait(15,TimeUnit.SECONDS);
    }//end of before suite


    @Test
     public void depositrestore() throws InterruptedException, ParseException, IOException {

        driver.navigate().to("https://qab.rci.com/");
        driver.findElement(By.xpath("//*[@id='username']")).sendKeys("edgrider");
        driver.findElement(By.xpath("//*[@name='password']")).sendKeys("resort1");
        driver.findElement(By.xpath("//*[text()='SIGN IN']")).click();
        driver.findElement(By.xpath("//*[text()='MY ACCOUNT']")).click();
        //clicking on available deposit by first index 0 for dashboard
        driver.findElements(By.xpath("//*[@class='account-dashboard-grid-cell']")).get(0).click();
        //remember the date is dynamic so i'm using findElements get(3) to click on November 2018 date in table 4
        //for some reason the class id for start date doesn't work so i'm using entire xpath for table[4]
        //that means i also have to click on 4th child for  Deposit Now using get(3) since array starts from 0
        JavascriptExecutor jse = (JavascriptExecutor)driver;
        Thread.sleep(2000);
        //you also need to scroll so that table is within frame if it is not then your getText will fail to capture
        jse.executeScript("scroll(0,800)");
        Thread.sleep(1000);

        String startdate = driver.findElements(By.xpath("//*[@id='managedeposit-section-main']/div[2]/div[1]/div[1]/form/table[4]/tbody/tr/td/table/tbody/tr/td[2]")).get(0).getText();
        System.out.println("Start date is " + startdate);
        //String startdate = "21-Jul-2019";
        String[] arraySplit = startdate.split("-");
        String monthNum = null;

        String[] monthArray = new String[]{"Jan","Feb","Mar","Apr","May","Jun","Jul","Aug","Sep","Oct","Nov","Dec"};
        String[] monthNumArray = new String[]{"01","02","03","04","05","06","07","08","09","10","11","12"};
        for(int i = 0;i < monthArray.length;i++){
            if(arraySplit[1].equalsIgnoreCase(monthArray[i])){
                monthNum = monthNumArray[i];
                break;
            }
        }
        //new formatted date after changing month from letter to number using loop and if
        String newStartDate = arraySplit[0]+"-"+ monthNum + "-" + arraySplit[2];

        SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
        Calendar currentDay = Calendar.getInstance();
        Calendar futureMonth = Calendar.getInstance();
        currentDay.add(Calendar.MONTH, 0);
        futureMonth.add(Calendar.MONTH, 9);
        //converting vaca start date to date format to compare with calendar month
        Date VacaDate = format.parse(newStartDate);


        if (currentDay.getTime().before(futureMonth.getTime()) && VacaDate.before(futureMonth.getTime())) {
            //clicking on fourth deposit now since i chose fourth start date using findElements()
            driver.findElements(By.xpath("//*[@type='button' and contains(@id,'depNow')]")).get(3).click();
            System.out.println("Vacation Start Date is within 9 months.. Current Date is " + currentDay.getTime() + " & Vacatation Date is " + VacaDate);
        } else {
            System.out.println("Vacation Start Date is Greater than 9 Month.. Current Date is " + currentDay.getTime() + " & Vacatation Date is " + VacaDate);
        }

    }


}
