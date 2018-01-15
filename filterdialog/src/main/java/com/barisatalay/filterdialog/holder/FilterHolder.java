package com.barisatalay.filterdialog.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.barisatalay.filterdialog.R;
import com.barisatalay.filterdialog.model.FilterItem;


/**
 * Created by Barış ATALAY on 12.01.2018.
 */

public class FilterHolder extends RecyclerView.ViewHolder {
    private TextView titleTxt;
    public FilterHolder(View itemView) {
        super(itemView);

        initUi(itemView);
    }

    private void initUi(View itemView) {
        titleTxt = itemView.findViewById(R.id.titleTxt);
    }

    public void bind(FilterItem filterItem) {
        titleTxt.setText(filterItem.getName());
    }
}
