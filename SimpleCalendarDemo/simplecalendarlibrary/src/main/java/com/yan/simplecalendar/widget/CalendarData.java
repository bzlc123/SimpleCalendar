package com.yan.simplecalendar.widget;

import java.util.Calendar;

/**
 * @author yanweiqiang.
 * @since 2015/10/8 0008.
 */
public class CalendarData {
    private int type;
    private int monthLabel;
    private Calendar[] daysOfWeek;

    public CalendarData(int type, int monthLabel, Calendar[] daysOfWeek) {
        this.type = type;
        this.monthLabel = monthLabel;
        this.daysOfWeek = daysOfWeek;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getMonthLabel() {
        return monthLabel;
    }

    public void setMonthLabel(int monthLabel) {
        this.monthLabel = monthLabel;
    }

    public Calendar[] getDaysOfWeek() {
        return daysOfWeek;
    }

    public void setDaysOfWeek(Calendar[] daysOfWeek) {
        this.daysOfWeek = daysOfWeek;
    }
}
