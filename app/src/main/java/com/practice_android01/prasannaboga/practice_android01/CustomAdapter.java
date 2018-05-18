package com.practice_android01.prasannaboga.practice_android01;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {

    ArrayList<String> transactionIds;
    ArrayList<String> transactionCreatedAts;
    ArrayList<String> transactionAmounts;
    ArrayList<String> transactionStatuses;
    Context context;

    public CustomAdapter(Context context, ArrayList<String> transactionIds, ArrayList<String> transactionCreatedAts, ArrayList<String> transactionAmounts, ArrayList<String> transactionStatuses) {
        this.context = context;
        this.transactionIds = transactionIds;
        this.transactionCreatedAts = transactionCreatedAts;
        this.transactionAmounts = transactionAmounts;
        this.transactionStatuses = transactionStatuses;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // inflate the item Layout
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.transaction_row, parent, false);
        MyViewHolder vh = new MyViewHolder(v); // pass the view to View Holder
        return vh;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        // set the data in items
        holder.transactionId.setText(transactionIds.get(position));
        holder.createdAt.setText(transactionCreatedAts.get(position));
        holder.Amount.setText(transactionAmounts.get(position));
        holder.status.setText(transactionStatuses.get(position));
        // implement setOnClickListener event on item view.
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // display a toast with person name on item click
                Toast.makeText(context, transactionIds.get(position), Toast.LENGTH_SHORT).show();
            }
        });

    }


    @Override
    public int getItemCount() {
        return transactionIds.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView transactionId, createdAt, Amount, status;// init the item view's

        public MyViewHolder(View itemView) {
            super(itemView);

            // get the reference of item view's
            transactionId = (TextView) itemView.findViewById(R.id.transactionId);
            createdAt = (TextView) itemView.findViewById(R.id.createdAt);
            Amount = (TextView) itemView.findViewById(R.id.amount);
            status = (TextView) itemView.findViewById(R.id.status);

        }
    }
}