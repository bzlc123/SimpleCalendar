package com.yanwq.simplecalendarlibrary.utils;

import com.yanwq.simplecalendarlibrary.model.CalendarData;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

/**
 * @author yanweiqiang.
 * @since 2015/10/8 0008.
 */
public class CalendarDataUtils {

    public static List<CalendarData> getLines(Calendar originStart, Calendar end) {
        Calendar start = new GregorianCalendar(originStart.get(Calendar.YEAR), originStart.get(Calendar.MONTH), originStart.get(Calendar.DAY_OF_MONTH));
        List<CalendarData> list = new ArrayList<>();
        Calendar[] strings = new Calendar[7];

        if (start.get(Calendar.DAY_OF_MONTH) != 1) {
            list.add(new CalendarData(0, start.get(Calendar.MONTH), null));
        }

        boolean firstIs1 = start.get(Calendar.DAY_OF_WEEK) == 1;

        while (start.compareTo(end) < 0) {
            int dayOfMoth = start.get(Calendar.DAY_OF_MONTH);
            int dayOfWeek = start.get(Calendar.DAY_OF_WEEK);

            if (!firstIs1 && (dayOfWeek == 1 || dayOfMoth == 1)) {
                list.add(new CalendarData(1, 0, strings));
                if (dayOfMoth == 1) {
                    list.add(new CalendarData(0, start.get(Calendar.MONTH), null));
                }
                strings = new Calendar[7];
            }

            if (firstIs1) {
                firstIs1 = false;
            }

            strings[dayOfWeek - 1] = new GregorianCalendar(start.get(Calendar.YEAR), start.get(Calendar.MONTH), start.get(Calendar.DAY_OF_MONTH));

            start.add(Calendar.DAY_OF_MONTH, 1);

            if (start.compareTo(end) == 0) {
                if (dayOfMoth == 1) {
                    list.add(new CalendarData(0, start.get(Calendar.MONTH), null));
                }
                list.add(new CalendarData(1, 0, strings));
            }
        }

        return list;
    }

    public static String getMonth(int month) {
        switch (month) {
            case 0:
                return "一月";
            case 1:
                return "二月";
            case 2:
                return "三月";
            case 3:
                return "四月";
            case 4:
                return "五月";
            case 5:
                return "六月";
            case 6:
                return "七月";
            case 7:
                return "八月";
            case 8:
                return "九月";
            case 9:
                return "十月";
            case 10:
                return "十一月";
            case 11:
                return "十二月";
            default:
                return "";
        }
    }

    public static void main(String[] args) {
        Calendar now = Calendar.getInstance();
        Calendar start = new GregorianCalendar(now.get(Calendar.YEAR), now.get(Calendar.MONTH), now.get(Calendar.DAY_OF_MONTH));
        start.add(Calendar.DAY_OF_MONTH, 35);
        Calendar end = new GregorianCalendar(now.get(Calendar.YEAR), now.get(Calendar.MONTH), now.get(Calendar.DAY_OF_MONTH));
        end.add(Calendar.DAY_OF_MONTH, 70);
        List<CalendarData> list = CalendarDataUtils.getLines(start, end);

        for (CalendarData data : list) {
            System.out.println();

            if (data.getType() == 0) {
                System.out.print(CalendarDataUtils.getMonth(data.getMonthLabel()));
            }

            if (data.getType() == 1) {
                for (Calendar calendar : data.getDaysOfWeek()) {
                    if (calendar == null) {
                        System.out.print("  ");
                    } else {
                        System.out.print(calendar.get(Calendar.DAY_OF_MONTH) + " ");
                    }
                }
            }
        }
    }
}
