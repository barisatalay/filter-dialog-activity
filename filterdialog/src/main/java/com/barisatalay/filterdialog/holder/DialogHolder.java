package com.barisatalay.filterdialog.holder;

import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.barisatalay.filterdialog.FilterAdapter;
import com.barisatalay.filterdialog.R;
import com.barisatalay.filterdialog.model.AdapterListener;
import com.barisatalay.filterdialog.model.DialogListener;
import com.barisatalay.filterdialog.model.FilterItem;

import java.util.List;

/**
 * Created by Barış ATALAY on 12.01.2018.
 */

public class DialogHolder extends RecyclerView.ViewHolder implements TextWatcher, AdapterListener {
    private EditText searchEdt;
    private RecyclerView filterRecycler;
    private TextView toolbar_clear;
    private TextView toolbat_title;
    private LinearLayout toolbar_back;
    private DialogListener listener;

    public DialogHolder(View itemView) {
        super(itemView);

        initUi(itemView);

        LinearLayoutManager layoutManager = new LinearLayoutManager(itemView.getContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        layoutManager.scrollToPosition(0);

        filterRecycler.setLayoutManager(layoutManager);
        filterRecycler.setHasFixedSize(true);
        filterRecycler.setItemAnimator(new DefaultItemAnimator());

        searchEdt.addTextChangedListener(this);
    }

    private void initUi(View itemView) {
        searchEdt = itemView.findViewById(R.id.searchEdt);
        filterRecycler = itemView.findViewById(R.id.filterList);
        toolbar_clear = itemView.findViewById(R.id.toolbar_clear);
        toolbar_back = itemView.findViewById(R.id.toolbar_back);
        toolbat_title = itemView.findViewById(R.id.toolbat_title);

        toolbar_clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                searchEdt.setText("");
            }
        });
    }

    public void setListener(DialogListener listener) {
        this.listener = listener;
    }

    public DialogListener getListener() {
        return listener;
    }

    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        ((FilterAdapter)filterRecycler.getAdapter()).filter(charSequence.toString());
    }

    @Override
    public void afterTextChanged(Editable editable) {}

    public void setFilterList(List<FilterItem> filterList) {
        FilterAdapter adapter = new FilterAdapter(filterList);
        adapter.setListener(this);
        filterRecycler.setAdapter(adapter);
    }

    @Override
    public void onItemClick(View view, int position) {
        if(listener != null)
            listener.onResult(((FilterAdapter)filterRecycler.getAdapter()).getItemFromPosition(position));
    }

    public void setOnCloseListener(View.OnClickListener onCloseListener) {
        toolbar_back.setOnClickListener(onCloseListener);
    }

    public DialogHolder setToolbarTitle(String text) {
        toolbat_title.setText(text);
        return this;
    }

    public DialogHolder setSearchBoxHint(String text) {
        searchEdt.setHint(text);
        return this;
    }
}
