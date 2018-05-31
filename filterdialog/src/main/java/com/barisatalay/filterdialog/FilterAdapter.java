package com.barisatalay.filterdialog;

import android.view.View;

import com.barisatalay.filterdialog.base.BaseRecyclerAdapter;
import com.barisatalay.filterdialog.holder.FilterHolder;
import com.barisatalay.filterdialog.model.FilterItem;

import java.util.List;

/**
 * Created by Barış ATALAY on 12.01.2018.
 */

public class FilterAdapter extends BaseRecyclerAdapter<FilterHolder, FilterItem> {

    public FilterAdapter(List<FilterItem> cacheData) {
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
        return true;
    }

    @Override
    public void onClickListener(View view, int position) {
        selectEvent(position);
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
