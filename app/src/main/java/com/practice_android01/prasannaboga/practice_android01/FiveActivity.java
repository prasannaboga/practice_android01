package com.practice_android01.prasannaboga.practice_android01;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Date;


import io.github.luizgrp.sectionedrecyclerviewadapter.SectionedRecyclerViewAdapter;

public class FiveActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();
    private RecyclerView sectionHeader;
    private SectionedRecyclerViewAdapter sectionAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_five);

        sectionHeader = (RecyclerView) findViewById(R.id.testRecyclerView);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(FiveActivity.this);
        sectionHeader.setLayoutManager(linearLayoutManager);
        sectionHeader.setHasFixedSize(true);

        InputStream inputStream = getResources().openRawResource(R.raw.mock_transaction_by_data);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        int ctr;
        try {
            ctr = inputStream.read();
            while (ctr != -1) {
                byteArrayOutputStream.write(ctr);
                ctr = inputStream.read();
            }
            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        sectionAdapter = new SectionedRecyclerViewAdapter();

        try {
            JSONObject transactionsByDate = new JSONObject(byteArrayOutputStream.toString());

            Iterator<?> transactionDates = transactionsByDate.keys();

            while (transactionDates.hasNext()) {
                String key = (String) transactionDates.next();
                JSONArray transactionOnDate = (JSONArray) transactionsByDate.get(key);
                HeaderRecyclerViewSection sectionByDate = new HeaderRecyclerViewSection(key, transactionOnDate);
                sectionAdapter.addSection(sectionByDate);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        sectionHeader.setAdapter(sectionAdapter);
    }

    private List<ItemObject> getDataSource() {
        List<ItemObject> data = new ArrayList<ItemObject>();
        data.add(new ItemObject("This is the item content in the first position"));
        data.add(new ItemObject("This is the item content in the second position"));
        return data;
    }
}
