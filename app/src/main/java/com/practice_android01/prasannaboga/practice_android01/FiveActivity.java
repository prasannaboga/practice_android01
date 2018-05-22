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

        InputStream inputStream = getResources().openRawResource(R.raw.mock_transaction_data);
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
            JSONArray transactions = new JSONArray(byteArrayOutputStream.toString());

            Map<String, List<TransactionDetails>> transactionsGroupByDate = new HashMap<>();

            for (int i = 0; i < transactions.length(); i++) {
                JSONObject transactionDetail = transactions.getJSONObject(i);

                SimpleDateFormat formatInputDate = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
                Date createdAtDateObject = formatInputDate.parse(transactionDetail.getString("created_at"));
                SimpleDateFormat formatOutputDate = new SimpleDateFormat("dd-MMM-YYYY");
                String createdAt = formatOutputDate.format(createdAtDateObject);

                if (transactionsGroupByDate.get("createdAt") != null) {
                    transactionsGroupByDate.get("createdAt").add(new TransactionDetails(transactionDetail.getString("transaction_id"),
                            transactionDetail.getString("created_at"), transactionDetail.getString("amount"), transactionDetail.getString("status")));
                } else {
                    transactionsGroupByDate.put(createdAt, new ArrayList<TransactionDetails>());
                }
            }

            ArrayList<String> sortedByCreatedAt = new ArrayList<>(transactionsGroupByDate.keySet());
            Collections.sort(sortedByCreatedAt);

            for (String createdAt : sortedByCreatedAt) {
                HeaderRecyclerViewSection sectionByDate = new HeaderRecyclerViewSection(createdAt, transactionsGroupByDate.get("createdAt"));
                sectionAdapter.addSection(sectionByDate);
            }


        } catch (JSONException e) {
            e.printStackTrace();
        } catch (ParseException e) {
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
