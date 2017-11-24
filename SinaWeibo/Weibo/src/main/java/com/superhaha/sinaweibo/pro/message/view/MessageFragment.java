package com.superhaha.sinaweibo.pro.message.view;

import android.view.View;

import com.superhaha.sinaweibo.R;
import com.superhaha.sinaweibo.pro.base.view.BaseFragment;
import com.superhaha.sinaweibo.pro.base.view.BaseView;
import com.superhaha.sinaweibo.pro.discover.presenter.DiscoverPresenter;

/**
 * 消息页面
 * Created by Administrator on 2017/11/7.
 */

public class MessageFragment extends BaseFragment<DiscoverPresenter,BaseView> {

    private DiscoverPresenter discoverPresenter;

//    创建对象
    @Override
    public DiscoverPresenter bindPresenter() {
        discoverPresenter = new DiscoverPresenter(getContext());
        return discoverPresenter;
    }

    @Override
    public int bindLayoutId() {
        return R.layout.fragment_message;
    }

    @Override
    public void initContentView(View contentView) {

    }


}
