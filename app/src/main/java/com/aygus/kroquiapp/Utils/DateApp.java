package com.aygus.kroquiapp.Utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;


public class DateApp {
    public long d;
    public int days;
    public String dateStart;
    public String dateEnd;
    private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);

    public DateApp(String dateStart, String dateEnd) {
        this.dateStart = dateStart;
        this.dateEnd = dateEnd;
        this.d = getChronus();
    }

    public long getSeconds() {
        return d/1000;
    }

    public long getMinutes() {
        return getSeconds()/60;
    }

    public long getHours() {
        return getMinutes()/60;
    }

    public long getDays() {
        return getHours()/24;
    }

    public static String getDate(){
        Date date = new Date();
        return sdf.format(date);
    }

    public static long getChronus(String dateStart, String dateEnd){
        Date dataBase;
        Date vencimento;
        try {
            dataBase = sdf.parse(dateStart);
            vencimento = sdf.parse(dateEnd);
        } catch (ParseException e) {
            return 0;
        }

        return  vencimento.getTime() - dataBase.getTime();
    }

    public long getChronus(){
        Date dataBase;
        Date vencimento;
        try {
            dataBase = sdf.parse(this.dateStart);
            vencimento = sdf.parse(this.dateEnd);
            return  vencimento.getTime() - dataBase.getTime();

        } catch (ParseException e) {
           return 0;
        }
    }

    public static String getDateEnd(String dateInicial, int days){
        Date dateEnd = new Date();

        try {
            dateEnd = sdf.parse(dateInicial);
            Calendar c = Calendar.getInstance();
            c.setTime(dateEnd);
            c.add(Calendar.DATE, +days);

            dateEnd = c.getTime();

        } catch (ParseException e) {
            e.printStackTrace();
        }

        return sdf.format(dateEnd);
    }
}
