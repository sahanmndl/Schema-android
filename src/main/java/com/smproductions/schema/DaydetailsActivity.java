package com.smproductions.schema;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.widget.Toolbar;

import com.smproductions.schema.Utils.LetterImageView;

public class DaydetailsActivity extends AppCompatActivity {

    private ListView listView;
    private Toolbar toolbar;

    private String[] PreferredDay;
    private String[] PreferredDates;
    private String[] PreferredTimings;

    public static String[] Monday;
    public static String[] Tuesday;
    public static String[] Wednesday;
    public static String[] Thursday;
    public static String[] Friday;
    public static String[] Saturday;
    public static String[] Sunday;
    public static String[] MondayDates;
    public static String[] TuesdayDates;
    public static String[] WednesdayDates;
    public static String[] ThursdayDates;
    public static String[] FridayDates;
    public static String[] SaturdayDates;
    public static String[] SundayDates;
    public static String[] MondayTimings;
    public static String[] TuesdayTimings;
    public static String[] WednesdayTimings;
    public static String[] ThursdayTimings;
    public static String[] FridayTimings;
    public static String[] SaturdayTimings;
    public static String[] SundayTimings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daydetails);

        setUpUI();
        initToolbar();
        setUpListView();
    }

    private void setUpUI()
    {
        toolbar = (Toolbar) findViewById(R.id.dayDetailsToolbar);
        listView = (ListView)findViewById(R.id.dayDetails_listView);
    }

    private void initToolbar()
    {
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(WeekActivity.sharedPreferences.getString(WeekActivity.SEL_DAY, null));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void setUpListView()
    {
        Monday = getResources().getStringArray(R.array.Monday);
        Tuesday = getResources().getStringArray(R.array.Tuesday);
        Wednesday = getResources().getStringArray(R.array.Wednesday);
        Thursday = getResources().getStringArray(R.array.Thursday);
        Friday = getResources().getStringArray(R.array.Friday);
        Saturday = getResources().getStringArray(R.array.Saturday);
        Sunday = getResources().getStringArray(R.array.Sunday);

        MondayDates = getResources().getStringArray(R.array.MondayDates);
        TuesdayDates = getResources().getStringArray(R.array.TuesdayDates);
        WednesdayDates = getResources().getStringArray(R.array.WednesdayDates);
        ThursdayDates = getResources().getStringArray(R.array.ThursdayDates);
        FridayDates = getResources().getStringArray(R.array.FridayDates);
        SaturdayDates = getResources().getStringArray(R.array.SaturdayDates);
        SundayDates = getResources().getStringArray(R.array.SundayDates);

        MondayTimings = getResources().getStringArray(R.array.MondayTimings);
        TuesdayTimings = getResources().getStringArray(R.array.TuesdayTimings);
        WednesdayTimings = getResources().getStringArray(R.array.WednesdayTimings);
        ThursdayTimings = getResources().getStringArray(R.array.ThursdayTimings);
        FridayTimings = getResources().getStringArray(R.array.FridayTimings);
        SaturdayTimings = getResources().getStringArray(R.array.SaturdayTimings);
        SundayTimings = getResources().getStringArray(R.array.SundayTimings);

        String selected_day = WeekActivity.sharedPreferences.getString(WeekActivity.SEL_DAY, null);

        if (selected_day.equalsIgnoreCase("Monday"))
        {
            PreferredDay = Monday;
            PreferredDates = MondayDates;
            PreferredTimings = MondayTimings;
        }
        else if (selected_day.equalsIgnoreCase("Tuesday"))
        {
            PreferredDay = Tuesday;
            PreferredDates = TuesdayDates;
            PreferredTimings = TuesdayTimings;
        }
        else if (selected_day.equalsIgnoreCase("Wednesday"))
        {
            PreferredDay = Wednesday;
            PreferredDates = WednesdayDates;
            PreferredTimings = WednesdayTimings;
        }
        else if (selected_day.equalsIgnoreCase("Thursday"))
        {
            PreferredDay = Thursday;
            PreferredDates = ThursdayDates;
            PreferredTimings = ThursdayTimings;
        }
        else if (selected_day.equalsIgnoreCase("Friday"))
        {
            PreferredDay = Friday;
            PreferredDates = FridayDates;
            PreferredTimings = FridayTimings;
        }
        else if (selected_day.equalsIgnoreCase("Saturday"))
        {
            PreferredDay = Saturday;
            PreferredDates = SaturdayDates;
            PreferredTimings = SaturdayTimings;
        }
        else
        {
            PreferredDay = Sunday;
            PreferredDates = SundayDates;
            PreferredTimings = SundayTimings;
        }

        SimpleAdapter simpleAdapter = new SimpleAdapter(this, PreferredDay, PreferredTimings, PreferredDates);
        listView.setAdapter(simpleAdapter);
    }

    public class SimpleAdapter extends BaseAdapter {

        private Context mContext;
        private LayoutInflater layoutInflater;
        private TextView courseTitle, courseTimings, courseDates;
        private String[] courseArray;
        private String[] timingsArray;
        private String[] datesArray;
        private LetterImageView letterImageView;

        public SimpleAdapter(Context context, String[] courseArray, String[] timingsArray, String[] datesArray){
            mContext = context;
            this.courseArray = courseArray;
            this.timingsArray = timingsArray;
            this.datesArray = datesArray;
            layoutInflater = LayoutInflater.from(context);
        }

        @Override
        public int getCount() {
            return courseArray.length;
        }

        @Override
        public Object getItem(int position) {
            return courseArray[position];
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null)
            {
                convertView = layoutInflater.inflate(R.layout.day_item_layout, null);
            }

            courseTitle = (TextView)convertView.findViewById(R.id.dayItem_courseTitle);
            courseTimings = (TextView)convertView.findViewById(R.id.dayItem_courseTiming);
            courseDates = (TextView)convertView.findViewById(R.id.dayItem_courseDuration);
            letterImageView = (LetterImageView) convertView.findViewById(R.id.dayItem_courseLogo);

            courseTitle.setText(courseArray[position]);
            courseTimings.setText(timingsArray[position]);
            courseDates.setText(datesArray[position]);

            letterImageView.setOval(true);
            letterImageView.setLetter(courseArray[position].charAt(6));

            return convertView;
        }
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId())
        {
            case android.R.id.home : {
                onBackPressed();
            }
        }
        return super.onOptionsItemSelected(item);
    }
}