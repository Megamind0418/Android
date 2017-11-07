package com.superhaha.sinaweibo.pro.discover.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.superhaha.sinaweibo.pro.base.view.BaseFragment;
import com.superhaha.sinaweibo.pro.discover.presenter.DiscoverPresenter;

/**
 * 发现页面
 * Created by Administrator on 2017/11/7.
 */

public class DiscoverFragment extends BaseFragment<DiscoverPresenter> {

    private DiscoverPresenter discoverPresenter;

//    创建对象
    @Override
    public DiscoverPresenter bindPresenter() {
        discoverPresenter = new DiscoverPresenter(getContext());
        return discoverPresenter;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
}
