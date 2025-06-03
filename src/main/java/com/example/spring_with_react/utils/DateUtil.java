package com.example.spring_with_react.utils;


import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {

    public static String convertTimeStampToDDMMMYYYY(Date inputDate){
        String formattedDate="";

        try {
            SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy");
             formattedDate = formatter.format(inputDate);

        }catch (Exception exception){
            throw new RuntimeException();
        }
       return formattedDate;
    }
}
