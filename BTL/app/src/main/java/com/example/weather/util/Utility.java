package com.example.weather.util;

import android.content.Context;
import com.example.weather.R;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Utility {
    public static String toTitleCase(String str) {

        if (str == null) {
            return null;
        }

        boolean space = true;
        StringBuilder builder = new StringBuilder(str);
        final int len = builder.length();

        for (int i = 0; i < len; ++i) {
            char c = builder.charAt(i);
            if (space) {
                if (!Character.isWhitespace(c)) {
                    // Convert to title case and switch out of whitespace mode.
                    builder.setCharAt(i, Character.toTitleCase(c));
                    space = false;
                }
            } else if (Character.isWhitespace(c)) {
                space = true;
            } else {
                builder.setCharAt(i, Character.toLowerCase(c));
            }
        }

        return builder.toString();
    }
    public static String getFormattedWind(Context context, float windSpeed) {
        int windFormat;
        windFormat = R.string.format_wind_kmh;

        return String.format(context.getString(windFormat), windSpeed);
    }



    public static String formatDate() {

        Date date = new Date();
        String[] monthNames = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};

        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);
        String dayOfWeek = new SimpleDateFormat("EEEE").format(date);
        String result = dayOfWeek + ", "+ monthNames[month-1] + " "+ String.valueOf(day);

        System.out.println(result );
        return result;
    }
}
