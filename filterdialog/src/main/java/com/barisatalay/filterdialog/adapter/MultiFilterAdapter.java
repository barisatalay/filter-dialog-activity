package com.barisatalay.filterdialog.adapter;

import android.view.View;

import com.barisatalay.filterdialog.R;
import com.barisatalay.filterdialog.holder.FilterHolder;
import com.barisatalay.filterdialog.model.FilterItem;

import java.util.List;

public class MultiFilterAdapter extends FilterAdapter{

    private int selectDrawable;

    public MultiFilterAdapter(List<FilterItem> cacheData) {
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
        FilterHolder filterHolder = new FilterHolder(view);
        filterHolder.setSelectDrawable(selectDrawable);
        return filterHolder;
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

    public void setSelectDrawable(int selectDrawable) {
        this.selectDrawable = selectDrawable;
    }

    public int getSelectDrawable() {
        return selectDrawable;
    }
}