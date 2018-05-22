package com.practice_android01.prasannaboga.practice_android01;

import org.json.JSONException;
import org.json.JSONObject;

public class TransactionDetails {
    public JSONObject transactionDetails;

    public TransactionDetails(JSONObject transactionDetails) {
        this.transactionDetails = transactionDetails;
    }

    public String transactionID() {
        String transactionID = new String();
        try {
            transactionID = transactionDetails.getString("transaction_id");
        } catch (JSONException e) {
        }
        return transactionID;
    }
}