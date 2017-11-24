package com.superhaha.sinaweibo.pro.base.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.superhaha.sinaweibo.mvp.presenter.MvpPresenter;
import com.superhaha.sinaweibo.mvp.view.MvpView;
import com.superhaha.sinaweibo.mvp.view.impl.MvpFragment;
import com.superhaha.sinaweibo.pro.base.presenter.BasePresenter;

/**
 * 将视图进行缓存
 * Created by Administrator on 2017/11/7.
 */

public abstract class BaseFragment<P extends BasePresenter,V extends MvpView> extends MvpFragment<P,V> {
    //我们自己的Fragment需要缓存视图
    private View contentView;//缓存视图
    private boolean isInit;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (contentView == null){
            contentView = inflater.inflate(bindLayoutId(),container,false);
            initContentView(contentView);
        }

        //判断Fragment对应的Activity是否存在这个视图
        ViewGroup parent = (ViewGroup) contentView.getParent();
        if (parent != null){
            //如果存在,那么我就干掉,重写添加,这样的方式我们就可以缓存视图
            parent.removeView(contentView);
        }
        return contentView;
    }

    public View getContentView() {
        return contentView;
    }

    protected void resetContentView(View contentView){
        ViewGroup viewGroup = (ViewGroup) contentView;
        viewGroup.removeAllViews();
    }

    protected void loadLayout(int layoutId, View v){
        ViewGroup viewGroup = (ViewGroup) v;
        View view = LayoutInflater.from(getContext()).inflate(layoutId,viewGroup,false);
        //判断Fragment对应的Activity是否存在这个视图
        ViewGroup parent = (ViewGroup) view.getParent();
        if (parent != null){
            //如果存在,那么我就干掉,重写添加,这样的方式我们就可以缓存视图
            parent.removeView(view);
        }
        viewGroup.addView(view);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (!isInit){
            this.isInit = true;
            initData();
        }
    }

    @Override
    public V bindView() {
        return null;
    }

    public abstract int bindLayoutId();

    public void initData(){

    }

    public abstract void initContentView(View contentView);


}
