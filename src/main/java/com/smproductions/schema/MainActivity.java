package com.smproductions.schema;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setUpUI();
        initToolbar();
        setUpListView();
    }

    private void setUpUI()
    {
        toolbar = findViewById(R.id.mainToolbar);
        listView = findViewById(R.id.main_listView);
    }

    private void initToolbar()
    {
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setTitle("Schema");
    }

    private void setUpListView()
    {
        String[] title = getResources().getStringArray(R.array.Main);
        String[] description = getResources().getStringArray(R.array.Description);

        SimpleAdapter simpleAdapter = new SimpleAdapter(this, title, description);
        listView.setAdapter(simpleAdapter);

        listView.setOnItemClickListener((parent, view, position, id) -> {
            switch (position)
            {
                case 0: {
                    Intent intent = new Intent(MainActivity.this, WeekActivity.class);
                    startActivity(intent);
                    break;
                }
                case 1: {
                    Intent intent = new Intent(MainActivity.this, SubjectsActivity.class);
                    startActivity(intent);
                    break;
                }
                case 2: {
                    Toast.makeText(MainActivity.this, "Will be updated soon", Toast.LENGTH_SHORT).show();
                    break;
                }
                case 3: {
                    Toast.makeText(MainActivity.this, "Will be updated soon", Toast.LENGTH_SHORT).show();
                    break;
                }
            }
        });
    }

    public static class SimpleAdapter extends BaseAdapter {

        private Context mContext;
        private final LayoutInflater layoutInflater;
        private TextView title, description;
        private final String[] titleArray;
        private String[] descriptionArray;
        private ImageView imageView, click;

        public SimpleAdapter(Context context, String[] title, String[] description){
            mContext = context;
            titleArray = title;
            descriptionArray = description;
            layoutInflater = LayoutInflater.from(context);
        }

        @Override
        public int getCount() {
            return titleArray.length;
        }

        @Override
        public Object getItem(int position) {
            return titleArray[position];
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @SuppressLint("InflateParams")
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null)
            {
                convertView = layoutInflater.inflate(R.layout.main_item_layout, null);
            }

            title = convertView.findViewById(R.id.mainItem_title);
            description = convertView.findViewById(R.id.mainItem_description);
            imageView = convertView.findViewById(R.id.mainItem_imageView);
            click = convertView.findViewById(R.id.mainItem_btnClick);

            title.setText(titleArray[position]);
            description.setText(descriptionArray[position]);

            if (titleArray[position].equalsIgnoreCase("Schedule"))
            {
                imageView.setImageResource(R.drawable.schedule);
            }
            else if (titleArray[position].equalsIgnoreCase("Subjects"))
            {
                imageView.setImageResource(R.drawable.books);
            }
            else if (titleArray[position].equalsIgnoreCase("Faculty"))
            {
                imageView.setImageResource(R.drawable.faculty);
            }
            else
            {
                imageView.setImageResource(R.drawable.settings);
            }

            return convertView;
        }
    }
}