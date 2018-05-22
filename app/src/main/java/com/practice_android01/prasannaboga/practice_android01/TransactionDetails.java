package com.practice_android01.prasannaboga.practice_android01;

public class TransactionDetails {
    public String transaction_id;
    public String created_at;
    public String amount;
    public String status;

    public TransactionDetails(String transaction_id, String created_at, String amount, String status) {
        this.transaction_id = transaction_id;
        this.created_at = created_at;
        this.amount = amount;
        this.status = status;
    }

    public String getContents() {
        return transaction_id;
    }
}