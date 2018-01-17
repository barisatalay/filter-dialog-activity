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
    private List<Class> simpleDialogFields;

    public FilterDialog(Activity mActivity) {
        this.mActivity = mActivity;
        this.alertDialogBuilder = UtilsDialog.createAlertDialog(mActivity);
        this.filterList = new ArrayList<>();
        this.toolbarTitle = "";
        this.searchBoxHint = "";
        createSimpleDialogDefination();
    }

    private void createSimpleDialogDefination() {
        simpleDialogFields = new ArrayList<>();
        simpleDialogFields.add(String.class);
        simpleDialogFields.add(Integer.class);
        simpleDialogFields.add(Double.class);
        simpleDialogFields.add(Float.class);
        simpleDialogFields.add(Boolean.class);
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
        createDialogHolder();
        dialogHolder.setListener(dialogListener);
        setDefaultParameters();
        dialogHolder.setFilterList(prepareFilterList(idField, nameField));

        alertDialog = alertDialogBuilder.show();
    }

    /**
     * When you have List<String,Integer,Boolean,Double,Float> should be use this method
     * */
    public void show(DialogListener dialogListener){
        createDialogHolder();
        dialogHolder.setListener(dialogListener);
        setDefaultParameters();

        dialogHolder.setFilterList(prepareFilterList("",""));
        alertDialog = alertDialogBuilder.show();
    }

    private void setDefaultParameters() {
        dialogHolder.setToolbarTitle(toolbarTitle);
        dialogHolder.setSearchBoxHint(searchBoxHint);
        dialogHolder.setOnCloseListener(this);
    }


    private void createDialogHolder() {
        LayoutInflater inflater = mActivity.getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.activity_filter_dialog, null);

        dialogHolder = new DialogHolder(dialogView);
        alertDialogBuilder.setView(dialogHolder.itemView);
    }

    public List<FilterItem> prepareFilterList(String idFieldDef, String nameFieldDef) {
        List<FilterItem> result = new ArrayList<>();
        Iterator iterator = this.filterList.iterator();

        while(iterator.hasNext()) {
            Object item = iterator.next();

            try {
                Class aClass = item.getClass();
                if(!simpleDialogFields.contains(aClass)) {
                    //If idFieldDef was not set, nameFieldDef takes its place
                    if (!idFieldDef.isEmpty())
                        idFieldDef = nameFieldDef;

                    Field idField = getFieldFromName(aClass, idFieldDef);
                    Field nameField = getFieldFromName(aClass, nameFieldDef);
                    if (idField == null) {
                        //Id field not found!
                        Log.e(TAG, "Id field not found!");
                        continue;
                    }

                    if (nameField == null) {
                        //Name field not found!
                        Log.e(TAG, "Name field not found!");
                        continue;
                    }

                    Object idFieldObject = idField.get(item);
                    Object nameFieldObject = nameField.get(item);

                    //Value null control
                    String code = idFieldObject != null ? idFieldObject.toString() : "";
                    String name = nameFieldObject != null ? nameFieldObject.toString() : "";

                    result.add(new FilterItem.Builder()
                            .code(code)
                            .name(name)
                            .build());
                }else{
                    /**
                     * For ArrayList<String,Integer,Boolean,Double,Float>
                     */
                    result.add(new FilterItem.Builder()
                            .code(String.valueOf(item))
                            .name(String.valueOf(item))
                            .build());
                }
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
