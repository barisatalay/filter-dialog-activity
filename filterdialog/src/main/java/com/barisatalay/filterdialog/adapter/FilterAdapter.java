package com.barisatalay.filterdialog.adapter;

import android.view.View;

import com.barisatalay.filterdialog.base.BaseRecyclerAdapter;
import com.barisatalay.filterdialog.holder.FilterHolder;
import com.barisatalay.filterdialog.model.FilterItem;

import java.util.List;

public class FilterAdapter extends BaseRecyclerAdapter<FilterHolder, FilterItem>{
    public FilterAdapter(List<FilterItem> cacheData) {
        super(cacheData);
    }

    @Override
    public int getLayoutResource() {
        return 0;
    }

    @Override
    public void onCustomBindViewHolder(FilterHolder holder, FilterItem model, int position) {}

    @Override
    public FilterHolder onCustomCreateViewHolder(View view) {
        return null;
    }

    @Override
    public boolean isMainListenerDisable() {
        return false;
    }

    @Override
    public boolean isSelectable() {
        return false;
    }

    @Override
    public void onClickListener(View view, int position) {
        selectEvent(position);
        if (getListener() != null)
            getListener().onItemClick(view, position);
    }

    public void filter(String text) {
        getAllData().clear();
        if(text != null && !text.isEmpty()){
            text = text.toLowerCase();
            for(FilterItem item: getCacheData()){
                if(item.getName().toLowerCase().contains(text)){
                    getAllData().add(item);
                }
            }
        }else{
            getAllData().addAll(getCacheData());
        }
        notifyDataSetChanged();
    }
}
