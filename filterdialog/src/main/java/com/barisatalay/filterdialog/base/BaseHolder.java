package com.barisatalay.filterdialog.base;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;

public abstract class BaseHolder<T> extends RecyclerView.ViewHolder {
    public String TAG = this.getClass().getSimpleName();
    private Context mContext;
    private Activity mActivity;
    private HolderListener listener;
    /**
     * İçerisinde herhangi birşey stringe çevrilip saklanabilir.
     * */
    private String tempData;

    public BaseHolder(View itemView) {
        super(itemView);
        setContext(itemView.getContext());
    }

    private BaseHolder setContext(Context mContext){
        this.mContext = mContext;

        return this;
    }

    public Context getContext() {
        return mContext;
    }

    public BaseHolder setActivity(Activity mActivity) {
        this.mActivity = mActivity;
        return this;
    }

    public String getString(int resourceId){
        return getContext().getString(resourceId);
    }

    public Activity getActivity() {
        return mActivity;
    }

    public HolderListener getListener() {
        return listener;
    }

    public void setListener(HolderListener listener) {
        this.listener = listener;
    }

    public void setTempData(String tempData) {
        this.tempData = tempData;
    }

    public String getTempData() {
        if (tempData == null)
            tempData = "";
        return tempData;
    }

    public interface HolderListener<M>{
        void onClickListener(View view, int position);
        boolean isSelected(M model);
    }

    public abstract void bind(T model);
}
