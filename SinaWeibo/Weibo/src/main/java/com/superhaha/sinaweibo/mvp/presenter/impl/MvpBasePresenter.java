package com.superhaha.sinaweibo.mvp.presenter.impl;

import com.superhaha.sinaweibo.mvp.presenter.MvpPresenter;
import com.superhaha.sinaweibo.mvp.view.MvpView;

/**
 * Created by Administrator on 2017/11/6.
 */

public abstract class MvpBasePresenter<V extends MvpView> implements MvpPresenter<V>{

    private V view;

    @Override
    public void attachView(V View) {
        this.view = view;
    }

    @Override
    public void detachView() {
        this.view = null;
    }
}
