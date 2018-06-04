package com.barisatalay.filterdialog.adapter;

import android.view.View;

import com.barisatalay.filterdialog.R;
import com.barisatalay.filterdialog.holder.FilterHolder;
import com.barisatalay.filterdialog.model.FilterItem;

import java.util.List;

public class SingleFilterAdapter extends FilterAdapter {

    public SingleFilterAdapter(List<FilterItem> cacheData) {
        super(cacheData);
    }

    @Override
    public int getLayoutResource() {
        return R.layout.item_filter_item;
    }

    @Override
    public void onCustomBindViewHolder(FilterHolder holder, FilterItem model, int position) {
        holder.bind(model);
    }

    @Override
    public FilterHolder onCustomCreateViewHolder(View view) {
        return new FilterHolder(view);
    }

    @Override
    public boolean isMainListenerDisable() {
        return false;
    }

    @Override
    public boolean isSelectable() {
        return false;
    }

    public void filter(String text) {
        getCacheData().clear();
        if(text != null && !text.isEmpty()){
            text = text.toLowerCase();
            for(FilterItem item: getAllData()){
                if(item.getName().toLowerCase().contains(text)){
                    getCacheData().add(item);
                }
            }
        }else{
            getCacheData().addAll(getAllData());
        }
        notifyDataSetChanged();
    }
}