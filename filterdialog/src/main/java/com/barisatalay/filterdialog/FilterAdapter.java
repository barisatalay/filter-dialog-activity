package com.barisatalay.filterdialog;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.barisatalay.filterdialog.holder.FilterHolder;
import com.barisatalay.filterdialog.model.AdapterListener;
import com.barisatalay.filterdialog.model.FilterItem;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Barış ATALAY on 12.01.2018.
 */

public class FilterAdapter extends RecyclerView.Adapter<FilterHolder>{
    private List<FilterItem> cacheData;
    private List<FilterItem> allData;
    private AdapterListener listener;

    public FilterAdapter(List<FilterItem> allData) {
        this.allData = new ArrayList<>();
        this.cacheData = new ArrayList<>();
        if(allData != null) {
            this.allData.addAll(allData);
            this.cacheData.addAll(allData);
        }
    }

    @Override
    public FilterHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_filter_item, parent, false);
        return new FilterHolder(view);
    }

    @Override
    public void onBindViewHolder(FilterHolder holder, final int position) {
        holder.bind(allData.get(position));

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(listener != null)
                    listener.onItemClick(view, position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return (null != allData ? allData.size() : 0);
    }

    public FilterItem getItemFromPosition(int position) {
        if (allData == null || allData.size() == 0) return null;
        return allData.get(position);
    }

    public void filter(String text) {
        allData.clear();
        if(text != null && !text.isEmpty()){
            text = text.toLowerCase();
            for(FilterItem item: cacheData){
                if(item.getName().toLowerCase().contains(text)){
                    allData.add(item);
                }
            }
        }else{
            allData.addAll(cacheData);
        }
        notifyDataSetChanged();
    }

    public void setListener(AdapterListener listener) {
        this.listener = listener;
    }
}
