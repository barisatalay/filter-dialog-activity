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
import java.util.Arrays;
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
    private String toolbarTitle;
    private String searchBoxHint;

    public FilterDialog(Activity mActivity) {
        this.mActivity = mActivity;
        this.alertDialogBuilder = UtilsDialog.createAlertDialog(mActivity);
        this.filterList = new ArrayList<>();
        this.toolbarTitle = "";
        this.searchBoxHint = "";
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
        dialogHolder.setToolbarTitle(toolbarTitle);
        dialogHolder.setSearchBoxHint(searchBoxHint);
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
                Field field1 = getFieldFromName(aClass, idField);
                Field field2 = getFieldFromName(aClass, nameField);
                if (field1 == null) {
                    //Id field not found!
                    Log.e(TAG, "Id field not found!");
                    continue;
                }

                if (field2 == null) {
                    //Name field not found!
                    Log.e(TAG, "Name field not found!");
                    continue;
                }

                Object field1Object = field1.get(item);
                Object field2Object = field2.get(item);

                //Value null control
                String code = field1Object!=null?field1Object.toString():"";
                String name = field2Object!=null?field2Object.toString():"";

                result.add(new FilterItem.Builder()
                        .code(code)
                        .name(name)
                        .build());
            } catch (IllegalAccessException e) {
                if(e.getMessage() != null)
                    Log.e(TAG, e.getMessage());
                e.printStackTrace();
            }
        }
        return result;
    }

    private Field getFieldFromName(Class<?> mClass, String name){
        List<Field> allFields = getAllFields(mClass);

        for(Field item : allFields)
            if(item.getName().equals(name)){
                item.setAccessible(true);
                return item;
            }
        return null;
    }

    public static List<Field> getAllFields(Class<?> type) {
        List<Field> result = new ArrayList<>();
        result.addAll(Arrays.asList(type.getDeclaredFields()));

        if (type.getSuperclass() != null) {
            result.addAll(getAllFields(type.getSuperclass()));
        }

        return result;
    }

    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.toolbar_back){
            dispose();

        }
    }

    public void dispose(){
        clear();
    }

    private void clear() {
        if(alertDialog != null) {
            alertDialog.dismiss();
            alertDialog = null;
            alertDialogBuilder = null;
        }
    }

    public String getToolbarTitle() {
        return toolbarTitle;
    }

    public void setToolbarTitle(String toolbarTitle) {
        this.toolbarTitle = toolbarTitle;
    }

    public String getSearchBoxHint() {
        return searchBoxHint;
    }

    public void setSearchBoxHint(String searchBoxHint) {
        this.searchBoxHint = searchBoxHint;
    }
}
