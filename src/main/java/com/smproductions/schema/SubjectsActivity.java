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

public class SubjectsActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private ListView listView;
    private String[] subjects;

    public static SharedPreferences subjectPreferences;
    public static String SUB_PREF;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subjects);

        setUpUI();
        initToolbar();
        setUpListView();
    }

    private void setUpUI()
    {
        toolbar = (Toolbar) findViewById(R.id.subjectToolbar);
        listView = (ListView)findViewById(R.id.subject_listView);
        subjectPreferences = getSharedPreferences("Subjects", MODE_PRIVATE);
    }

    private void initToolbar()
    {
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Subjects");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void setUpListView()
    {
        subjects = getResources().getStringArray(R.array.Subjects);

        SubjectsAdapter subjectsAdapter = new SubjectsAdapter(this, R.layout.subject_item_layout, subjects);
        listView.setAdapter(subjectsAdapter);
        /**listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position)
                {
                    case 0: {
                        subjectPreferences.edit().putString(SUB_PREF, "MaterialsChemistry").apply();
                        Intent intent = new Intent(SubjectsActivity.this, SubjectDetailsActivity.class);
                        startActivity(intent);
                        break;
                    }
                    case 1: {
                        subjectPreferences.edit().putString(SUB_PREF, "LinearAlgebraII").apply();
                        Intent intent = new Intent(SubjectsActivity.this, SubjectDetailsActivity.class);
                        startActivity(intent);
                        break;
                    }
                    case 2: {
                        subjectPreferences.edit().putString(SUB_PREF, "ProbabilityStatistics").apply();
                        Intent intent = new Intent(SubjectsActivity.this, SubjectDetailsActivity.class);
                        startActivity(intent);
                        break;
                    }
                    case 3: {
                        subjectPreferences.edit().putString(SUB_PREF, "CalculusII").apply();
                        Intent intent = new Intent(SubjectsActivity.this, SubjectDetailsActivity.class);
                        startActivity(intent);
                        break;
                    }
                    case 4: {
                        subjectPreferences.edit().putString(SUB_PREF, "CityInLiterature").apply();
                        Intent intent = new Intent(SubjectsActivity.this, SubjectDetailsActivity.class);
                        startActivity(intent);
                        break;
                    }
                }
            }
        });*/
    }

    public class SubjectsAdapter extends ArrayAdapter {

        private int resource;
        private LayoutInflater layoutInflater;
        private String[] subjects = new String[]{};

        public SubjectsAdapter(Context context, int resource, String[] objects) {
            super(context, resource, objects);
            this.resource = resource;
            this.subjects = objects;
            layoutInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            SubjectsActivity.SubjectsAdapter.ViewHolder holder;
            if (convertView == null)
            {
                holder = new ViewHolder();
                convertView = layoutInflater.inflate(resource, null);
                holder.ivLogo = (LetterImageView)convertView.findViewById(R.id.subject_letterImage);
                holder.txtSubject = (TextView)convertView.findViewById(R.id.subjectItem_title);
                holder.click = (ImageView)convertView.findViewById(R.id.subjectItem_click);
                convertView.setTag(holder);
            }
            else
            {
                holder = (ViewHolder)convertView.getTag();
            }

            holder.ivLogo.setOval(true);
            holder.ivLogo.setLetter(subjects[position].charAt(6));
            holder.txtSubject.setText(subjects[position]);

            return convertView;
        }

        class ViewHolder{
            private LetterImageView ivLogo;
            private TextView txtSubject;
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