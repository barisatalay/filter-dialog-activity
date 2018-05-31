package com.barisatalay.filterdialog.holder;

import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.widget.TextView;

import com.barisatalay.filterdialog.R;
import com.barisatalay.filterdialog.base.BaseHolder;
import com.barisatalay.filterdialog.model.FilterItem;

/**
 * Created by Barış ATALAY on 12.01.2018.
 */

public class FilterHolder extends BaseHolder<FilterItem> implements View.OnClickListener{
    private TextView titleTxt;

    public FilterHolder(View itemView) {
        super(itemView);
        titleTxt = itemView.findViewById(R.id.titleTxt);
    }

    @Override
    public void bind(FilterItem model) {
        titleTxt.setText(model.getName());
        int padding = titleTxt.getPaddingLeft();
        if (getListener() != null) {
            boolean selected = getListener().isSelected(model);
            if (selected)
                titleTxt.setBackgroundResource(R.color.filterdialog_row_selected);
            else{
                TypedValue typedValue = new TypedValue();
                getContext().getTheme().resolveAttribute(R.attr.selectableItemBackground, typedValue, true);
                titleTxt.setBackgroundResource(typedValue.resourceId);
            }
        }

        titleTxt.setPadding(padding,padding,padding,padding);

        titleTxt.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        Log.i(TAG, "Row clicked!");
        if (getListener() != null)
            getListener().onClickListener(view, getLayoutPosition());
    }
}
