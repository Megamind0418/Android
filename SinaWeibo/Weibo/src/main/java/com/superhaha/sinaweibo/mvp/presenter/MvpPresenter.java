package com.superhaha.sinaweibo.mvp.presenter;

import com.superhaha.sinaweibo.mvp.view.MvpView;

/**
 * P层
 * Created by Administrator on 2017/11/6.
 */

public interface MvpPresenter<V extends MvpView> {

//    绑定view层
    public void attachView(V View);

//    解除绑定
    public void detachView();
}
