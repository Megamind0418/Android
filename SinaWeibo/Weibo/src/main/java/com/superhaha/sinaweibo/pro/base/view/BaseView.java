package com.superhaha.sinaweibo.pro.base.view;

import com.superhaha.sinaweibo.mvp.view.MvpView;
import com.superhaha.sinaweibo.mvp.view.impl.MvpBaseLceView;

/**
 * Created by Administrator on 2017/11/7.
 */

public  interface BaseView<M> extends MvpView {
    public void onResult(M data);
}
