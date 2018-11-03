package Cucumber_Test.Google;

import cucumber.api.CucumberOptions;
import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.concurrent.TimeUnit;

import static ReusableObjects.Reusable_Methods.sendKeysMethod;
import static ReusableObjects.Reusable_Methods.submitMethod;

public class Google_Search_Gherkin_Test{
    WebDriver driver;
    @Given("^I have navigated to Google home page$")
    public void i_have_navigated_to_Google_home_page() throws Throwable {
        System.setProperty("webdriver.chrome.driver", "src\\main\\resources\\chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized", "--incognito");
        driver = new ChromeDriver(options);
        //timeout using implicit wait
        driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
        throw new PendingException();
    }

    @When("^I enter verify google title matches with expected$")
    public void i_enter_verify_google_title_matches_with_expected() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }

    @When("^I enter a keyword and click on google search$")
    public void i_enter_a_keyword_and_click_on_google_search() throws Throwable {
        System.out.println("Navigating to google home page");
        driver.navigate().to("https://www.google.com");
        //enter a keyword in google search
        sendKeysMethod(driver,"//*[@name='q']","Brooklyn","Search Field");
        //submit on google search
        submitMethod(driver,"//*[@value='Google Search']","Google Search");
        throw new PendingException();
    }

    @Then("^I can see and capture search result on search page$")
    public void i_can_see_the_search_result_on_home_page() throws Throwable {
        try{
            String searchResult = driver.findElement(By.xpath("//*[@id='resultStats']")).getText();
            String[] searchNumber = searchResult.split(" ");
            System.out.println("My search number is " + searchNumber[1]);
        } catch (Exception err){
            System.out.println("Unable to capture text for Search Result");
        }
    }

}
