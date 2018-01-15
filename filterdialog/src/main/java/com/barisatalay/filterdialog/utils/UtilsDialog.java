package com.barisatalay.filterdialog.utils;

import android.app.Activity;
import android.app.AlertDialog;

import com.barisatalay.filterdialog.R;

/**
 * Created by Barış ATALAY on 12.01.2018.
 */

public class UtilsDialog {
    public static AlertDialog.Builder createAlertDialog(Activity mActivity){
        return new AlertDialog.Builder(mActivity, R.style.AppTheme_Dialog);
    }
}
