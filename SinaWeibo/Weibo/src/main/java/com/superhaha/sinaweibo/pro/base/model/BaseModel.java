package com.superhaha.sinaweibo.pro.base.model;

import android.content.Context;

import com.superhaha.sinaweibo.mvp.model.impl.MvpBaseModel;

/**
 * Created by Administrator on 2017/11/7.
 */

public abstract class BaseModel extends MvpBaseModel {
    private Context context;

    public BaseModel(Context context) {
        this.context = context;
    }

    public Context getContext() {
        return context;
    }
}
