package com.superhaha.sinaweibo.mvp.view.impl;

import com.superhaha.sinaweibo.mvp.view.MvpLceView;

/**
 * 实现类
 * 适配器模式
 * Created by Administrator on 2017/11/6.
 */

public abstract class MvpBaseLceView<M> implements MvpLceView<M> {

    @Override
    public void showData(M data) {

    }

    @Override
    public void showError(Exception e, boolean isPullToRefresh) {

    }

    @Override
    public void showLoading(boolean isPullToRefresh) {

    }

    @Override
    public void showContent() {

    }
}
