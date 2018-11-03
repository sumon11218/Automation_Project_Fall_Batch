package PageObjects.yahoo;

import ReusableObjects.Reusable_Methods_Loggers;
import Utilities.Abstract_Class_Parallel;

import java.io.IOException;

public class Yahoo_Home_Page_Reusables extends Abstract_Class_Parallel {

    public static void yahoo_search_Field(String userInput) throws IOException {

        Reusable_Methods_Loggers.sendKeysMethod(logger,driver,"//*[@name='p']",userInput,"Search Field");
    }




}
