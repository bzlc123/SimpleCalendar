package com.yan.simplecalendar.widget;

import android.widget.TextView;

import java.util.Calendar;

public interface CalendarDecorator {
    void decorate(TextView calendarTV, TextView decoratorTV, Calendar calendar);
}
