package com.barisatalay.filterdialog.model;

import java.util.List;

/**
 * Created by Barış ATALAY on 12.01.2018.
 */

public class DialogListener {
    public interface Single{
        void onResult(FilterItem selectedItem);
    }

    public interface Multiple{
        void onResult(List<FilterItem> selectedItems);
    }

}
