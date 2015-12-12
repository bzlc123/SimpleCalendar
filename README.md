# SimpleCalendar
简单的日历控件，界面优美，比系统灵活，可根据需求自行修改源码。

# 使用方法

和使用普通View控件相同，在layout文件中加入控件，然后在activity中初始化日历开始和结束时间，最后设置监听事件即可。

# 代码示例

layout代码示例：

<?xml version="1.0" encoding="utf-8"?>
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:orientation="vertical"
    android:paddingBottom="@dimen/activity_vertical_margin"
    android:paddingLeft="@dimen/activity_horizontal_margin"
    android:paddingRight="@dimen/activity_horizontal_margin"
    android:paddingTop="@dimen/activity_vertical_margin"
    tools:context=".MainActivity">

    <include layout="@layout/item_week_title" />

    <com.yan.simplecalendar.widget.SimpleCalendarView
        android:id="@+id/main_simple_calendar"
        android:layout_width="match_parent"
        android:layout_height="match_parent" />
</LinearLayout>

activity代码示例：

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