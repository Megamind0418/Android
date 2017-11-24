package com.superhaha.sinaweibo.pro.base.presenter;

import android.content.Context;

import com.superhaha.sinaweibo.mvp.presenter.impl.MvpBasePresenter;
import com.superhaha.sinaweibo.mvp.view.MvpView;

/**
 * Created by Administrator on 2017/11/7.
 */

public abstract class BasePresenter<V extends MvpView> extends MvpBasePresenter<V> {
    private Context context;

    public BasePresenter(Context context) {
        this.context = context;
    }

    public Context getContext() {
        return context;
    }

    public interface OnUIThreadListener<T>{
        public void onResult(T result, String errorMessage);
    }

}
