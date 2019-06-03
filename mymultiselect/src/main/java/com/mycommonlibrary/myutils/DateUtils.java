package com.mycommonlibrary.myutils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class DateUtils {

    public static final String DATE_FORMAT = "dd/MM/yyyy";
    public static final String DATE_FORMAT1 = "MM-dd-yyyy";


    public static Date convertStringToDate(String s, String dateFormate) {


        try {
            return new SimpleDateFormat(dateFormate).parse(s);
        } catch (ParseException e) {
//            e.printStackTrace();
            return null;

        }


    }

    public static String convertDateToString(Date date, String dateFormat) {

        DateFormat df = new SimpleDateFormat(dateFormat);
        return df.format(date);
    }

}
