package com.yan.simplecalendar.widget;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.yan.simplecalendar.R;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

/**
 * @author yanweiqiang.
 * @since 2015/10/8 0008.
 */
public class CalendarAdapter extends BaseAdapter {
    private List<CalendarData> mList;
    private CalendarDecorator mDecorator;
    private Calendar mToday;
    private Context mContext;
    private int mTodayColor;
    private int mPastColor;
    private int mAfterColor;

    private OnDatePickerListener mListener;

    @SuppressWarnings("deprecation")
    public CalendarAdapter(Context context, List<CalendarData> list) {
        mContext = context;
        mList = list;
        Calendar today = Calendar.getInstance();
        mToday = new GregorianCalendar(today.get(Calendar.YEAR), today.get(Calendar.MONTH), today.get(Calendar.DAY_OF_MONTH));

        mTodayColor = mContext.getResources().getColor(R.color.font_color_today);
        mPastColor = mContext.getResources().getColor(R.color.font_color_past);
        mAfterColor = mContext.getResources().getColor(R.color.font_color_after);
    }

    public void setOnDatePickerListener(OnDatePickerListener listener) {
        mListener = listener;
    }

    public void setDecorator(CalendarDecorator decorator) {
        mDecorator = decorator;
    }

    @Override
    public int getItemViewType(int position) {
        return mList.get(position).getType();
    }

    @Override
    public int getViewTypeCount() {
        return 2;
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public Object getItem(int position) {
        return mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        MonthHolder monthHolder = null;
        WeekHolder weekHolder = null;

        if (convertView == null) {
            switch (getItemViewType(position)) {
                case 0:
                    monthHolder = new MonthHolder();
                    convertView = LayoutInflater.from(mContext).inflate(R.layout.item_month, parent, false);
                    monthHolder.title = (TextView) convertView.findViewById(R.id.month_title);
                    convertView.setTag(monthHolder);
                    break;
                case 1:
                    weekHolder = new WeekHolder();
                    convertView = LayoutInflater.from(mContext).inflate(R.layout.item_week, parent, false);
                    weekHolder.mContainerList.add(convertView.findViewById(R.id.week_1_container));
                    weekHolder.mContainerList.add(convertView.findViewById(R.id.week_2_container));
                    weekHolder.mContainerList.add(convertView.findViewById(R.id.week_3_container));
                    weekHolder.mContainerList.add(convertView.findViewById(R.id.week_4_container));
                    weekHolder.mContainerList.add(convertView.findViewById(R.id.week_5_container));
                    weekHolder.mContainerList.add(convertView.findViewById(R.id.week_6_container));
                    weekHolder.mContainerList.add(convertView.findViewById(R.id.week_7_container));

                    weekHolder.mCalendarList.add((TextView) convertView.findViewById(R.id.week_1));
                    weekHolder.mCalendarList.add((TextView) convertView.findViewById(R.id.week_2));
                    weekHolder.mCalendarList.add((TextView) convertView.findViewById(R.id.week_3));
                    weekHolder.mCalendarList.add((TextView) convertView.findViewById(R.id.week_4));
                    weekHolder.mCalendarList.add((TextView) convertView.findViewById(R.id.week_5));
                    weekHolder.mCalendarList.add((TextView) convertView.findViewById(R.id.week_6));
                    weekHolder.mCalendarList.add((TextView) convertView.findViewById(R.id.week_7));

                    weekHolder.mDecoratorList.add((TextView) convertView.findViewById(R.id.week_1_dec));
                    weekHolder.mDecoratorList.add((TextView) convertView.findViewById(R.id.week_2_dec));
                    weekHolder.mDecoratorList.add((TextView) convertView.findViewById(R.id.week_3_dec));
                    weekHolder.mDecoratorList.add((TextView) convertView.findViewById(R.id.week_4_dec));
                    weekHolder.mDecoratorList.add((TextView) convertView.findViewById(R.id.week_5_dec));
                    weekHolder.mDecoratorList.add((TextView) convertView.findViewById(R.id.week_6_dec));
                    weekHolder.mDecoratorList.add((TextView) convertView.findViewById(R.id.week_7_dec));
                    convertView.setTag(weekHolder);
                    break;
            }
        } else {
            switch (getItemViewType(position)) {
                case 0:
                    monthHolder = (MonthHolder) convertView.getTag();
                    break;
                case 1:
                    weekHolder = (WeekHolder) convertView.getTag();
                    break;
            }
        }

        switch (getItemViewType(position)) {
            case 0:
                handleMonth(monthHolder, position);
                break;
            case 1:
                handleWeek(weekHolder, position);
                break;
        }

        if (convertView != null) {
            convertView.setOnClickListener(null);
        }

        return convertView;
    }

    private void handleMonth(MonthHolder monthHolder, int position) {
        CalendarData data = mList.get(position);
        monthHolder.title.setText(CalendarDataUtils.getMonth(data.getMonthLabel()));
    }

    @SuppressWarnings("deprecation")
    private void handleWeek(WeekHolder weekHolder, int position) {
        final CalendarData data = mList.get(position);
        for (int i = 0; i < 7; i++) {
            final Calendar calendar = data.getDaysOfWeek()[i];
            TextView calendarV = weekHolder.mCalendarList.get(i);
            calendarV.setText(calendar == null ? "" : "" + calendar.get(Calendar.DAY_OF_MONTH));
            View containerV = weekHolder.mContainerList.get(i);
            containerV.setOnClickListener(new View.OnClickListener() {
                                              @Override
                                              public void onClick(View v) {
                                                  if (calendar == null) {
                                                      return;
                                                  }

                                                  if (mListener != null) {
                                                      mListener.onPicked(calendar);
                                                  }
                                              }
                                          }

            );
        }
        setToday(weekHolder, position);
    }

    @SuppressWarnings("deprecation")
    private void setToday(WeekHolder weekHolder, int position) {
        final CalendarData data = mList.get(position);

        for (int i = 0; i < 7; i++) {
            final Calendar calendar = data.getDaysOfWeek()[i];
            TextView calendarV = weekHolder.mCalendarList.get(i);
            TextView decoratorV = weekHolder.mDecoratorList.get(i);
            calendarV.setText(calendar == null ? "" : "" + calendar.get(Calendar.DAY_OF_MONTH));

            if (calendar != null) {

                if (mToday.compareTo(calendar) == 0) {
                    calendarV.setTextColor(mTodayColor);
                } else if (mToday.compareTo(calendar) > 0) {
                    calendarV.setTextColor(mPastColor);
                } else {
                    calendarV.setTextColor(mAfterColor);
                }

                if (mDecorator != null) {
                    mDecorator.decorate(calendarV, decoratorV, calendar);
                }
            } else {
                calendarV.setText(null);
                decoratorV.setText(null);
            }
        }
    }

    private class MonthHolder {
        TextView title;
    }

    private class WeekHolder {
        public List<View> mContainerList;
        public List<TextView> mCalendarList;
        public List<TextView> mDecoratorList;

        public WeekHolder() {
            mContainerList = new ArrayList<>();
            mCalendarList = new ArrayList<>();
            mDecoratorList = new ArrayList<>();
        }
    }
}
