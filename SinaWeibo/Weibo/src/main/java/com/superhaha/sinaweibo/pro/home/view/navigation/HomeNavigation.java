package com.superhaha.sinaweibo.pro.home.view.navigation;

import android.content.Context;
import android.view.ViewGroup;

import com.superhaha.sinaweibo.pro.base.view.navigation.impl.AbsNavigation;

/**
 * Created by Administrator on 2017/12/5.
 */

public class HomeNavigation extends AbsNavigation<HomeNavigation.Builder.HomeNavigationParams> {

    public HomeNavigation(Builder.HomeNavigationParams params) {
        super(params);
    }


    @Override
    public int getLayoutId() {
        return 0;
    }

    public static class Builder extends AbsNavigation.Builder{
        @Override
        public AbsNavigation create() {
            return null;
        }

        public static class HomeNavigationParams extends AbsNavigation.Builder.NavigationParams {
            public HomeNavigationParams(Context context, ViewGroup parent) {
                super(context,parent);
            }
        }

    }

}
