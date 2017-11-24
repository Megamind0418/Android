package com.superhaha.sinaweibo.mvp.view.impl;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import com.superhaha.sinaweibo.mvp.presenter.MvpPresenter;
import com.superhaha.sinaweibo.mvp.view.MvpView;

/**
 * Created by Administrator on 2017/11/6.
 */

public abstract class MvpFragment<P extends MvpPresenter,V extends MvpView>extends Fragment  {
    private P presenter;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        this.presenter = bindPresenter();
        if (this.presenter != null) {
            this.presenter.attachView(bindView());
        }
    }

    public abstract P bindPresenter();

    public abstract V bindView();

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (this.presenter != null) {
            this.presenter.detachView();
            this.presenter = null;
        }
    }
}
