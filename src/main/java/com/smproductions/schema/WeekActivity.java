package com.smproductions.schema;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.widget.Toolbar;

import com.smproductions.schema.Utils.LetterImageView;

public class WeekActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private ListView listView;

    public static SharedPreferences sharedPreferences;
    public static String SEL_DAY;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_week);

        setUpUI();
        initToolbar();
        setUpListView();
    }

    private void setUpUI()
    {
        toolbar = (Toolbar) findViewById(R.id.weekToolbar);
        listView = (ListView)findViewById(R.id.week_listView);
        sharedPreferences = getSharedPreferences("MY_DAY", MODE_PRIVATE);
    }

    private void initToolbar()
    {
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Weekly Schedule");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void setUpListView()
    {
        String[] week = getResources().getStringArray(R.array.Week);

        WeekAdapter adapter = new WeekAdapter(this, R.layout.week_item_layout, week);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position)
                {
                    case 0: {
                        startActivity(new Intent(WeekActivity.this, DaydetailsActivity.class));
                        sharedPreferences.edit().putString(SEL_DAY, "Monday").apply();
                        break;
                    }
                    case 1: {
                        startActivity(new Intent(WeekActivity.this, DaydetailsActivity.class));
                        sharedPreferences.edit().putString(SEL_DAY, "Tuesday").apply();
                        break;
                    }
                    case 2: {
                        startActivity(new Intent(WeekActivity.this, DaydetailsActivity.class));
                        sharedPreferences.edit().putString(SEL_DAY, "Wednesday").apply();
                        break;
                    }
                    case 3: {
                        startActivity(new Intent(WeekActivity.this, DaydetailsActivity.class));
                        sharedPreferences.edit().putString(SEL_DAY, "Thursday").apply();
                        break;
                    }
                    case 4: {
                        startActivity(new Intent(WeekActivity.this, DaydetailsActivity.class));
                        sharedPreferences.edit().putString(SEL_DAY, "Friday").apply();
                        break;
                    }
                    case 5: {
                        startActivity(new Intent(WeekActivity.this, DaydetailsActivity.class));
                        sharedPreferences.edit().putString(SEL_DAY, "Saturday").apply();
                        break;
                    }
                    case 6: {
                        startActivity(new Intent(WeekActivity.this, DaydetailsActivity.class));
                        sharedPreferences.edit().putString(SEL_DAY, "Sunday").apply();
                        break;
                    }

                    default:break;
                }
            }
        });
    }

    public class WeekAdapter extends ArrayAdapter{

        private int resource;
        private LayoutInflater layoutInflater;
        private String[] week = new String[]{};

        public WeekAdapter(Context context, int resource, String[] objects) {
            super(context, resource, objects);
            this.resource = resource;
            this.week = objects;
            layoutInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            ViewHolder holder;
            if (convertView == null)
            {
                holder = new ViewHolder();
                convertView = layoutInflater.inflate(resource, null);
                holder.ivLogo = (LetterImageView)convertView.findViewById(R.id.week_letterImage);
                holder.txtWeek = (TextView)convertView.findViewById(R.id.weekItem_title);
                holder.click = (ImageView)convertView.findViewById(R.id.weekItem_click);
                convertView.setTag(holder);
            }
            else
            {
                holder = (ViewHolder)convertView.getTag();
            }

            holder.ivLogo.setOval(true);
            holder.ivLogo.setLetter(week[position].charAt(0));
            holder.txtWeek.setText(week[position]);

            return convertView;
        }

        class ViewHolder{
            private LetterImageView ivLogo;
            private TextView txtWeek;
            private ImageView click;
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