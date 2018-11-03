package Yahoo;

import ReusableObjects.Reusable_Methods;
import ReusableObjects.Reusable_Methods_Loggers;
import Utilities.Abstract_Class;
import Utilities.Abstract_Class_Parallel;
import com.relevantcodes.extentreports.LogStatus;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.List;

public class ActionItem_YahooSearch extends Abstract_Class_Parallel{


  @Test
  public void yahooSearchResult() throws IOException, InterruptedException {

      //navigate to yahoo homepage
      Reusable_Methods_Loggers.navigate(logger,driver,"https://www.yahoo.com");
      //verify the page title is 'Yahoo'
      String actualTitle = driver.getTitle();
      if(actualTitle.equals("Yahoo")){
          logger.log(LogStatus.PASS,"Title matches as Yahoo");
      } else {
          logger.log(LogStatus.FAIL,"Title doesn't --- " + actualTitle);
      }

      //display the count of the links
      Thread.sleep(2000);
      List<WebElement> listCount = driver.findElements(By.xpath("//li[contains(@class,'D(ib) Mstart(21px) Mend(13px)')]"));
      logger.log(LogStatus.INFO,"Link count is " + listCount.size());
      System.out.println("Link count is " + listCount.size());

      //enter keyword 'Nutrition' on search bar
      PageObjects.yahoo.Yahoo_Home_Page_Reusables.yahoo_search_Field("Nutrition");
      //click on search button
      Reusable_Methods_Loggers.clickMethod(logger,driver,"//*[@id='uh-search-button']","Search Button");
      //wait few seconds and then scroll down
      Thread.sleep(2000);
      jse.executeScript("scroll(0,5000)");

      //display the search count
      String searchresult = Reusable_Methods_Loggers.captureText(driver,"//*[@class='compPagination']",0,"Search Result");
      String[] arraySearch = searchresult.split("Next");
      logger.log(LogStatus.INFO,"Search count is " + arraySearch[1].trim());

      //scroll up to sign in and then click on it
      Thread.sleep(1000);
      jse.executeScript("scroll(0,-5000)");
      Reusable_Methods_Loggers.clickMethod(logger,driver,"//*[@id='yucs-login_signIn']","Sign In");

      //verify by default the checkbox is selected
      Thread.sleep(2000);
      boolean checkBoxState = driver.findElement(By.xpath("//*[@id='persistent']")).isSelected();
      if(checkBoxState == true){
          logger.log(LogStatus.PASS,"Checkbox is selected by default");
      } else {
          logger.log(LogStatus.FAIL,"Checkbox is not selected by default");
          Reusable_Methods_Loggers.getScreenshot(driver,logger,"Check Box State");
      }

      //enter invalid email
      Reusable_Methods_Loggers.sendKeysMethod(logger,driver,"//*[@name='username']","Abc123","Username Field");
      //click on Next button
      Reusable_Methods_Loggers.clickMethod(logger,driver,"//*[@name='signin']","Next Button");

      //compare error message
      Thread.sleep(2000);
      Reusable_Methods_Loggers.compareMessages(logger,driver,"//*[@id='username-error']",0,"Sorry, we don't recognize this email.","Error Message");


  }//end of test class


}//end of parent
