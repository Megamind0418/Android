package com.superhaha.sinaweibo.pro.discover.presenter;

import android.content.Context;

import com.superhaha.sinaweibo.pro.base.presenter.BasePresenter;
import com.superhaha.sinaweibo.pro.base.view.BaseView;
import com.superhaha.sinaweibo.pro.discover.model.DiscoverModel;

/**
 * Created by Administrator on 2017/11/7.
 */

public class DiscoverPresenter extends BasePresenter<BaseView> {
    private DiscoverModel discoverModel;
    public DiscoverPresenter(Context context) {
        super(context);
        this.discoverModel = new DiscoverModel(context);
    }
}
