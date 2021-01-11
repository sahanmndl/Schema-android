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

public class SubjectDetailsActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subject_details);

        setUpUI();
        initToolbar();
        setUpListView();
    }

    private void setUpUI()
    {
        toolbar = (Toolbar) findViewById(R.id.subDetailsToolbar);
        listView = (ListView)findViewById(R.id.subDetails_listView);
    }

    private void initToolbar()
    {
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Course Details");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void setUpListView()
    {
        String subjectSelected = SubjectsActivity.subjectPreferences.getString(SubjectsActivity.SUB_PREF, null);

        String[] content = new String[]{};

        if (subjectSelected.equalsIgnoreCase("MaterialsChemistry"))
        {
            content = getResources().getStringArray(R.array.MaterialsChemistry);
        }
        else if (subjectSelected.equalsIgnoreCase("LinearAlgebraII"))
        {
            content = getResources().getStringArray(R.array.LinearAlgebraII);
        }
        else if (subjectSelected.equalsIgnoreCase("ProbabilityStatistics"))
        {
            content = getResources().getStringArray(R.array.ProbabilityStatistics);
        }
        else if (subjectSelected.equalsIgnoreCase("CalculusII"))
        {
            content = getResources().getStringArray(R.array.CalculusII);
        }
        else
        {
            content = getResources().getStringArray(R.array.CityInLiterature);
        }

        SubjectDetailsAdapter subjectDetailsAdapter = new SubjectDetailsAdapter(this, content);
        listView.setAdapter(subjectDetailsAdapter);
    }

    public class SubjectDetailsAdapter extends BaseAdapter {

        private Context mContext;
        private LayoutInflater layoutInflater;
        private TextView content;
        private String[] contentArray;

        public SubjectDetailsAdapter(Context context, String[] content){
            mContext = context;
            contentArray = content;
            layoutInflater = LayoutInflater.from(context);
        }

        @Override
        public int getCount() {
            return contentArray.length;
        }

        @Override
        public Object getItem(int position) {
            return contentArray[position];
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null)
            {
                convertView = layoutInflater.inflate(R.layout.subdetails_item_layout, null);
            }

            content = (TextView)convertView.findViewById(R.id.subDetailsItem_content);

            content.setText(contentArray[position]);

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