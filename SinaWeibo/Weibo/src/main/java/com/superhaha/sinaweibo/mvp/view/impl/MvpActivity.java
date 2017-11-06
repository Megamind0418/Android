package com.superhaha.sinaweibo.mvp.view.impl;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.superhaha.sinaweibo.mvp.presenter.MvpPresenter;
import com.superhaha.sinaweibo.mvp.view.MvpView;

/**
 * Created by Administrator on 2017/11/6.
 */

public abstract  class MvpActivity<P extends MvpPresenter> extends AppCompatActivity implements MvpView {

    private P presenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.presenter = bindPresenter();
        if (this.presenter != null) {
            this.presenter.attachView(this);
        }
    }

    public abstract P bindPresenter();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (this.presenter != null) {
            this.presenter.detachView();
            this.presenter = null;
        }
    }
}
