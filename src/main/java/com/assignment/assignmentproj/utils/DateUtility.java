package com.assignment.assignmentproj.utils;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;
/* an utility  class to help with the date related class*/
public class DateUtility {

    public static String[] getLastSixMonths(){
        String [] dates = new String[6];
        Calendar cal = Calendar.getInstance();
        dates [0] = getFormttedDate(new Date());
        for(int i =1;i<dates.length;i++) {
            cal.add(Calendar.MONTH,-1);
            Date result = cal.getTime();
            dates[i]= getFormttedDate(result);
        }

        return dates;

    }

    public static int getCurrentMonth(){
        Date date = new Date();
        LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        int month = localDate.getMonthValue();
        return month;
    }
    /*
        returns date in US format i.e yyyy/mm/dd used further for fetching data from API.
        @Date date object
        @return formatted String
    **/
    private static String getFormttedDate(Date date){
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String format = formatter.format(date);
        return format;
    }
}
