package com.practice_android01.prasannaboga.practice_android01;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import io.github.luizgrp.sectionedrecyclerviewadapter.SectionedRecyclerViewAdapter;

public class FiveActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();
    private RecyclerView sectionHeader;
    private SectionedRecyclerViewAdapter sectionAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_five);

        sectionHeader = (RecyclerView)findViewById(R.id.testRecyclerView);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(FiveActivity.this);
        sectionHeader.setLayoutManager(linearLayoutManager);
        sectionHeader.setHasFixedSize(true);

        HeaderRecyclerViewSection firstSection = new HeaderRecyclerViewSection("2018-05-18", getDataSource());
        HeaderRecyclerViewSection secondSection = new HeaderRecyclerViewSection("2018-05-19", getDataSource());
        HeaderRecyclerViewSection thirdSection = new HeaderRecyclerViewSection("2018-05-20", getDataSource());

        sectionAdapter = new SectionedRecyclerViewAdapter();
        sectionAdapter.addSection(firstSection);
        sectionAdapter.addSection(secondSection);
        sectionAdapter.addSection(thirdSection);
        sectionHeader.setAdapter(sectionAdapter);
    }

    private List<ItemObject> getDataSource(){
        List<ItemObject> data = new ArrayList<ItemObject>();
        data.add(new ItemObject("This is the item content in the first position"));
        data.add(new ItemObject("This is the item content in the second position"));
        return data;
    }
}
