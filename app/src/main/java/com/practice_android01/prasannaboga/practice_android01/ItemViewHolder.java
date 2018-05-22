package com.practice_android01.prasannaboga.practice_android01;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

public class ItemViewHolder extends RecyclerView.ViewHolder{
    public TextView transactionId;
//    public TextView createAt;
//    public TextView amount;
//    public TextView status;

    public ItemViewHolder(View itemView) {
        super(itemView);
        transactionId = itemView.findViewById(R.id.transactionId);
//        createAt = itemView.findViewById(R.id.createdAt);
//        amount = itemView.findViewById(R.id.amount);
//        status = itemView.findViewById(R.id.status);
    }
}