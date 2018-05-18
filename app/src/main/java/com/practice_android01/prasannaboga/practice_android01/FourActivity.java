package com.practice_android01.prasannaboga.practice_android01;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.os.Bundle;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;

public class FourActivity extends AppCompatActivity {

    ArrayList<String> transactionIds = new ArrayList<>();
    ArrayList<String> transactionCreatedAts = new ArrayList<>();
    ArrayList<String> transactionAmounts = new ArrayList<>();
    ArrayList<String> transactionStatuses = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_four);

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
        //Log.v("Text Data", byteArrayOutputStream.toString());

        try {
            JSONArray transactions = new JSONArray(byteArrayOutputStream.toString());

            for (int i = 0; i < transactions.length(); i++) {
                JSONObject transactionDetail = transactions.getJSONObject(i);
                //Log.v("transactionIds", transactionDetail.getString("transaction_id"));
                transactionIds.add(transactionDetail.getString("transaction_id"));
                transactionCreatedAts.add(transactionDetail.getString("created_at"));
                transactionAmounts.add(transactionDetail.getString("amount"));
                transactionStatuses.add(transactionDetail.getString("status"));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(linearLayoutManager);

        CustomAdapter customAdapter = new CustomAdapter(FourActivity.this, transactionIds, transactionCreatedAts, transactionAmounts, transactionStatuses);
        recyclerView.setAdapter(customAdapter);
    }
}
