package com.superhaha.sinaweibo.pro.base.view;

import com.superhaha.sinaweibo.mvp.presenter.MvpPresenter;
import com.superhaha.sinaweibo.mvp.view.impl.MvpFragment;
import com.superhaha.sinaweibo.pro.base.presenter.BasePresenter;

/**
 * Created by Administrator on 2017/11/7.
 */

public abstract class BaseFragment<P extends BasePresenter> extends MvpFragment<P> {
    @Override
    public P bindPresenter() {
        return null;
    }


}
