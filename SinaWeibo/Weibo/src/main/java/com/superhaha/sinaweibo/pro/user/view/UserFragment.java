package com.superhaha.sinaweibo.pro.user.view;

import android.view.View;

import com.superhaha.sinaweibo.R;
import com.superhaha.sinaweibo.pro.base.view.BaseFragment;
import com.superhaha.sinaweibo.pro.discover.presenter.DiscoverPresenter;

/**
 * 用户页面
 * Created by Administrator on 2017/11/7.
 */

public class UserFragment extends BaseFragment<DiscoverPresenter> {

    private DiscoverPresenter discoverPresenter;

//    创建对象
    @Override
    public DiscoverPresenter bindPresenter() {
        discoverPresenter = new DiscoverPresenter(getContext());
        return discoverPresenter;
    }

    @Override
    public int bindLayoutId() {
        return R.layout.fragment_user;
    }

    @Override
    public void initContentView(View contentView) {

    }


}
