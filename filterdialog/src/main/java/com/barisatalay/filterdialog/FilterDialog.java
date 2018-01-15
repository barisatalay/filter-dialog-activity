package com.barisatalay.filterdialog;

import android.app.Activity;
import android.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;

import com.barisatalay.filterdialog.holder.DialogHolder;
import com.barisatalay.filterdialog.model.DialogListener;
import com.barisatalay.filterdialog.model.FilterItem;
import com.barisatalay.filterdialog.utils.UtilsDialog;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Barış ATALAY on 12.01.2018.
 */

public class FilterDialog<T> implements View.OnClickListener {
    private String TAG = this.getClass().getSimpleName();
    private Activity mActivity;
    private AlertDialog.Builder alertDialogBuilder;
    private AlertDialog alertDialog;
    private List<T> filterList;
    private DialogHolder dialogHolder;

    public FilterDialog(Activity mActivity) {
        this.mActivity = mActivity;
        alertDialogBuilder = UtilsDialog.createAlertDialog(mActivity);
        this.filterList = new ArrayList<>();
    }

    public void setList(List<T> filterList){
        this.filterList.clear();
        if(filterList != null)
            this.filterList.addAll(filterList);
    }

    /**
     * @param nameField : model's is the part that will appear on the screen.
     * @param idField : id section in the model.
     * @param dialogListener : when any row item selected, selected item will be return from interface
     * */
    public void show(String idField, String nameField, DialogListener dialogListener){
        LayoutInflater inflater = mActivity.getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.activity_filter_dialog, null);

        dialogHolder = new DialogHolder(dialogView);
        dialogHolder.setListener(dialogListener);
        dialogHolder.setFilterList(prepareFilterList(idField, nameField));
        dialogHolder.setOnCloseListener(this);

        alertDialogBuilder.setView(dialogHolder.itemView);

        alertDialog = alertDialogBuilder.show();
    }

    public List<FilterItem> prepareFilterList(String idField, String nameField) {
        List<FilterItem> result = new ArrayList<>();
        Iterator iterator = this.filterList.iterator();

        while(iterator.hasNext()) {
            Object item = iterator.next();

            try {
                Class aClass = item.getClass();
                Field field1 = aClass.getDeclaredField(idField);
                Field field2 = aClass.getDeclaredField(nameField);
                field1.setAccessible(true);
                field2.setAccessible(true);
                String code = field1.get(item).toString();
                String name = field2.get(item).toString();
                result.add(new FilterItem.Builder()
                        .code(code)
                        .name(name)
                        .build());
            } catch (NoSuchFieldException e) {
                if(e.getMessage() != null)
                    Log.e(TAG, e.getMessage());
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                if(e.getMessage() != null)
                    Log.e(TAG, e.getMessage());
                e.printStackTrace();
            }
        }
        return result;
    }


    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.toolbar_back){
            if(alertDialog != null) {
                alertDialog.dismiss();
                alertDialog = null;
                alertDialogBuilder = null;
            }
        }
    }
}
