package com.berna.global.common.util;

import com.ibm.icu.util.ChineseCalendar;
import org.apache.tomcat.jni.Local;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class DateUtil {

    /**
     * 양력날짜를 음력날짜로 변환
     */
    private static LocalDate converSolarToLunar(String paramDate) {
        ChineseCalendar cc = new ChineseCalendar();
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, Integer.parseInt(paramDate.substring(0, 4)));
        cal.set(Calendar.MONTH, Integer.parseInt(paramDate.substring(4, 6)) - 1);
        cal.set(Calendar.DAY_OF_MONTH, Integer.parseInt(paramDate.substring(6)));
        cc.setTimeInMillis(cal.getTimeInMillis());
        int y = cc.get(ChineseCalendar.EXTENDED_YEAR) - 2637;
        int m = cc.get(ChineseCalendar.MONTH) + 1;
        int d = cc.get(ChineseCalendar.DAY_OF_MONTH);
        return LocalDate.of(y, m, d);
    }


    public static List<LocalDate> getHolidayOfThisYear() {
        LocalDate currentDate = LocalDate.now();
        int yyyy = currentDate.getYear();

        List<LocalDate> holidaysList = new ArrayList<>();
        holidaysList.add(LocalDate.of(yyyy , 1,1));
        holidaysList.add(converSolarToLunar(yyyy + "0101").minusDays(1));
        holidaysList.add(converSolarToLunar(yyyy + "0101").plusDays(1));
        holidaysList.add(LocalDate.of(yyyy , 3,1));
        holidaysList.add(converSolarToLunar(yyyy + "0408"));
        holidaysList.add(LocalDate.of(yyyy , 5,5));
        holidaysList.add(LocalDate.of(yyyy , 6,6));
        holidaysList.add(LocalDate.of(yyyy , 8,15));
        holidaysList.add(converSolarToLunar(yyyy + "0815"));
        holidaysList.add(converSolarToLunar(yyyy + "0815").minusDays(1));
        holidaysList.add(converSolarToLunar(yyyy + "0815").plusDays(1));
        holidaysList.add(LocalDate.of(yyyy , 10,3));
        holidaysList.add(LocalDate.of(yyyy , 10,9));
        holidaysList.add(LocalDate.of(yyyy , 12,25));

        return holidaysList;
    }
    /*
    * 0이상이면 휴일
    *
    * */
    public static boolean isHolidayCheck(LocalDate date){
        List<LocalDate> holidayList = getHolidayOfThisYear();
        long holidayCheckCount = holidayList.stream().filter(holiday -> holiday.equals(date)).count();
     return holidayCheckCount>0;
    }

}
