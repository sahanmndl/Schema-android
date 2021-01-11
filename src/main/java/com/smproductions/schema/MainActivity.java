package com.smproductions.schema;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;

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
        toolbar = (Toolbar) findViewById(R.id.mainToolbar);
        listView = (ListView) findViewById(R.id.main_listView);
    }

    private void initToolbar()
    {
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Schema");
    }

    private void setUpListView()
    {
        String[] title = getResources().getStringArray(R.array.Main);
        String[] description = getResources().getStringArray(R.array.Description);

        SimpleAdapter simpleAdapter = new SimpleAdapter(this, title, description);
        listView.setAdapter(simpleAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
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
            }
        });
    }

    public class SimpleAdapter extends BaseAdapter {

        private Context mContext;
        private LayoutInflater layoutInflater;
        private TextView title, description;
        private String[] titleArray;
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

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null)
            {
                convertView = layoutInflater.inflate(R.layout.main_item_layout, null);
            }

            title = (TextView)convertView.findViewById(R.id.mainItem_title);
            description = (TextView)convertView.findViewById(R.id.mainItem_description);
            imageView = (ImageView)convertView.findViewById(R.id.mainItem_imageView);
            click = (ImageView)convertView.findViewById(R.id.mainItem_btnClick);

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