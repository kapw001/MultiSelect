package com.mycommonlibrary.myutils;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.widget.DatePicker;
import android.widget.TimePicker;
import com.mycommonlibrary.commoninterface.DatePickerListener;
import com.mycommonlibrary.commoninterface.TimePickerListener;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class DateAndTimePicker {

    public static final String DATE_FORMAT = "MM-dd-yyyy";


    public static void datePicker(Context context, final String dateformat, final DatePickerListener datePickerListener) {

        final Calendar myCalendar = Calendar.getInstance();
//        myCalendar.add(Calendar.DATE, -1);
        final DatePickerDialog datePickerDialog = new DatePickerDialog(context, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, month);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                SimpleDateFormat sdf = new SimpleDateFormat(dateformat, Locale.US);

                if (datePickerListener != null)
                    datePickerListener.onDateSelected(sdf.format(myCalendar.getTime()));

            }
        }, myCalendar
                .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                myCalendar.get(Calendar.DAY_OF_MONTH));

        datePickerDialog.getDatePicker().setMinDate(myCalendar.getTimeInMillis());
        datePickerDialog.show();

    }


    public static void timePicker(Context context, final TimePickerListener timePickerListener) {


        // TODO Auto-generated method stub
//        Calendar mcurrentTime = Calendar.getInstance();
        int hour = 10;//mcurrentTime.get(Calendar.HOUR_OF_DAY);
        int minute = 0;//mcurrentTime.get(Calendar.MINUTE);
        TimePickerDialog mTimePicker;
        mTimePicker = new TimePickerDialog(context, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {

//                String AM_PM;
//                if (selectedHour < 12) {
//                    AM_PM = "AM";
//                } else {
//                    AM_PM = "PM";
//                }

//                editText.setText(String.format("%d:%d %s", selectedHour, selectedMinute, AM_PM));

                Calendar calendar = Calendar.getInstance();
                calendar.set(Calendar.HOUR_OF_DAY, selectedHour);
                calendar.set(Calendar.MINUTE, selectedMinute);

                String st = getTime(calendar);
                if (timePickerListener != null) timePickerListener.onTimeSelected(st);


            }
        }, hour, minute, false);//Yes 24 hour time
        mTimePicker.setTitle("Select Time");
        mTimePicker.show();

    }


    public static String getTime(Calendar datetime) {

        String am_pm = "";

//        Calendar datetime = Calendar.getInstance();
//        datetime.set(Calendar.HOUR_OF_DAY, hourOfDay);
//        datetime.set(Calendar.MINUTE, minute);

        if (datetime.get(Calendar.AM_PM) == Calendar.AM)
            am_pm = "AM";
        else if (datetime.get(Calendar.AM_PM) == Calendar.PM)
            am_pm = "PM";

        String strHrsToShow = (datetime.get(Calendar.HOUR) == 0) ? "12" : Integer.toString(datetime.get(Calendar.HOUR));


        return strHrsToShow + ":" + datetime.get(Calendar.MINUTE) + " " + am_pm;


    }

}
