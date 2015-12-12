package com.yan.simplecalendar.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.Calendar;
import java.util.List;

/**
 * @author yanweiqiang.
 * @since 2015/10/8 0008.
 */
public class SimpleCalendarView extends ListView {
    private CalendarAdapter mAdapter;

    public SimpleCalendarView(Context context) {
        super(context);
    }

    public SimpleCalendarView(Context context, AttributeSet attrs) {
        super(context, attrs);
        setDividerHeight(0);
    }

    public void setOnDatePickerListener(OnDatePickerListener listener) {
        if (mAdapter != null) {
            mAdapter.setOnDatePickerListener(listener);
        }
    }

    public void setDecorator(CalendarDecorator decorator) {
        mAdapter.setDecorator(decorator);
        mAdapter.notifyDataSetChanged();
    }

    public void init(Calendar start, Calendar end) {
        List<CalendarData> list = CalendarDataUtils.getLines(start, end);
        mAdapter = new CalendarAdapter(getContext(), list);
        setAdapter(mAdapter);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
    }

    public void requestRealHeight() {
        post(new Runnable() {
            @Override
            public void run() {
                getLayoutParams().height = (int) (mAdapter.getCount() * 50 * getResources().getDisplayMetrics().density);
                requestLayout();
            }
        });
    }
}
