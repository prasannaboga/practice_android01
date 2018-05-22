package com.practice_android01.prasannaboga.practice_android01;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import java.util.List;

import io.github.luizgrp.sectionedrecyclerviewadapter.SectionParameters;
import io.github.luizgrp.sectionedrecyclerviewadapter.StatelessSection;

public class HeaderRecyclerViewSection extends StatelessSection {
    private String title;
    private List<TransactionDetails> list;

    public HeaderRecyclerViewSection(String title, List<TransactionDetails> list) {
        super(SectionParameters.builder()
                .itemResourceId(R.layout.transcation_item)
                .headerResourceId(R.layout.transcation_header)
                .build());
        this.title = title;
        this.list = list;
    }

    @Override
    public int getContentItemsTotal() {
        return list.size();
    }

    @Override
    public RecyclerView.ViewHolder getItemViewHolder(View view) {
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindItemViewHolder(RecyclerView.ViewHolder holder, int position) {
        ItemViewHolder iHolder = (ItemViewHolder) holder;
        iHolder.transactionId.setText(list.get(position).transaction_id);
    }

    @Override
    public RecyclerView.ViewHolder getHeaderViewHolder(View view) {
        return new HeaderViewHolder(view);
    }

    @Override
    public void onBindHeaderViewHolder(RecyclerView.ViewHolder holder) {
        HeaderViewHolder hHolder = (HeaderViewHolder) holder;
        hHolder.headerTitle.setText(title);
    }
}