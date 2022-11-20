package com.project.ppp;

import com.project.ppp.dates.DateUtilJava8;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class DateUtilJava8Impl implements DateUtilJava8 {
    @Override
    public String between(LocalDate date1, LocalDate date2) {
        StringBuilder stringBuilder = new StringBuilder();
        Period period = Period.between(date1, date2);
        String years = String.valueOf(Math.abs(period.getYears()));
        String months = String.valueOf(Math.abs(period.getMonths()));
        String days = String.valueOf(Math.abs(period.getDays()));

        if (Integer.parseInt(years) != 1) {
            years = years + " years ";
        } else {
            years = years + " year ";
        }
        if (Integer.parseInt(months) != 1) {
            months = months + " months ";
        } else {
            months = months + " month ";
        }
        if (Integer.parseInt(days) != 1) {
            days = days + " days ";
        } else {
            days = days + " day ";
        }
        if (Math.abs(period.getYears()) > 0) {
            stringBuilder.append(years);
        }
        if (Math.abs(period.getMonths()) > 0) {
            stringBuilder.append(months);
        }
        if (Math.abs(period.getDays()) > 0) {
            stringBuilder.append(days);
        }

        return stringBuilder.toString();
    }

    @Override
    public LocalDate[] mondays(Instant instant) {
        if (instant == null) {
            return null;
        }
        LocalDate localDate = LocalDate.ofInstant(instant, ZoneId.systemDefault());
        StringBuilder stringBuilder = new StringBuilder();
        Calendar calendar = Calendar.getInstance();

        calendar.set(Calendar.MONTH, localDate.getMonthValue() - 1);
        calendar.set(Calendar.YEAR, localDate.getYear());
        for (int d = 1; d <= calendar.getActualMaximum(Calendar.DAY_OF_MONTH); d++) {
            calendar.set(Calendar.DAY_OF_MONTH, d);
            int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
            if (Calendar.MONDAY == dayOfWeek) {
                stringBuilder.append(d + "/" + (calendar.get(Calendar.MONTH) + 1) +
                        "/" + calendar.get(Calendar.YEAR) + ".");
            }
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy");
        String[] result = String.valueOf(stringBuilder).split("\\.");
        LocalDate[] localDate1 = new LocalDate[result.length];
        for (int i = 0; i < result.length; i++) {
            LocalDate localDate2 = LocalDate.parse(result[i], formatter);
            localDate1[i] = localDate2;
        }

        return localDate1;
    }

    @Override
    public boolean isFridays13(LocalDate date) {
        String stroka = String.valueOf(date);
        String stroka2 = stroka.replace("-", "/");
        String[] array1 = stroka2.split("/");
        String[] array2 = new String[array1.length];
        int count = 0;
        for (int i = array1.length - 1; i >= 0; i--) {
            array2[count] = array1[i];
            count++;
        }
        String res = String.join("/", array2);
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date mydate = null;
        try {
            mydate = dateFormat.parse(res);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Calendar calendar = Calendar.getInstance();
        assert mydate != null;
        calendar.setTime(mydate);

        return calendar.get(Calendar.DAY_OF_WEEK) == 6 && calendar.get(Calendar.DAY_OF_MONTH) == 13;
    }

    @Override
    public String formatFullJava8(ZonedDateTime date, String language) {
        if (language != null) {
            return date.format(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.FULL, FormatStyle.FULL)
                    .withLocale(Locale.forLanguageTag(language)).withZone(ZoneId.systemDefault()));
        }

        return null;
    }
}
