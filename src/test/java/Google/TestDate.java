package Google;

import org.testng.annotations.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class TestDate {


    @Test
    public void date() throws ParseException {
        //let say you store that date you capture from getText into String variable
        String strDate = "2018/10/17";
        //format you can match it based on whatever you the way it shows on ur getText
        SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");

        //currentDay for curent month and day and year
        Calendar currentDay = Calendar.getInstance();
        //futureMonth for 9 month and day and year
        Calendar futureMonth = Calendar.getInstance();
        //current day need to be added to calendar
        currentDay.add(Calendar.MONTH, 0);
        //nine months from current day need to be added to Calendar
        futureMonth.add(Calendar.MONTH, 9);

        //string data need to be formatted
        Date date = format.parse(strDate);
        //now create calendar instance for this
        Calendar vacationDate = Calendar.getInstance();

        //now get time for currentDay & futureMonth
        currentDay.getTime();
        futureMonth.getTime();
        //now set time for string date
        vacationDate.setTime(date);

        //use the if condition to verify and then click if the conditions are true
        if(currentDay.before(futureMonth) && vacationDate.before(futureMonth)){
            //if current day and vacationdate is before ninemonths
            System.out.println("Click on vacation");
        } else{
            //if it is over nine months
            System.out.println("Don't click or do whatever if you need else statement");
        }


    }



}
