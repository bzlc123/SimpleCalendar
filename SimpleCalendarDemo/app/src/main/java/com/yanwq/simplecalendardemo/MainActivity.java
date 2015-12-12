package com.yanwq.simplecalendardemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.yan.simplecalendar.widget.OnDatePickerListener;
import com.yan.simplecalendar.widget.SimpleCalendarView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SimpleCalendarView calendarView = (SimpleCalendarView) findViewById(R.id.main_simple_calendar);
        Calendar calendar = Calendar.getInstance();

        //日历开始时间
        Calendar start = new GregorianCalendar(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
        start.add(Calendar.DAY_OF_MONTH, -10);

        //日历结束时间
        Calendar end = new GregorianCalendar(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
        end.add(Calendar.DAY_OF_MONTH, 180);

        //初始化日历
        calendarView.init(start, end);

        //设置日历选取回调
        calendarView.setOnDatePickerListener(new OnDatePickerListener() {
            @Override
            public void onPicked(Calendar calendar) {
                Toast.makeText(MainActivity.this, new SimpleDateFormat("yyyy-MM-dd", Locale.CHINA).format(calendar.getTime()), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
