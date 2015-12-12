package com.yanwq.simplecalendarlibrary.widget;

import android.widget.TextView;

import java.util.Calendar;

public interface CalendarDecorator {
    void decorate(TextView calendarTV, TextView decoratorTV, Calendar calendar);
}
