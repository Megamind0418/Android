package com.superhaha.sinaweibo.pro.base.view.navigation.impl;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import com.superhaha.sinaweibo.R;

/**
 * 当前默认的导航条
 * Created by Administrator on 2017/11/28.
 */

public class DefaultNavigation extends AbsNavigation<DefaultNavigation.Builder.DefaultNavigationParams> {

    public DefaultNavigation(Builder.DefaultNavigationParams params) {
        super(params);
    }

    @Override
    public int getLayoutId() {
        return R.layout.navigation_default;
    }

    public static class Builder extends AbsNavigation.Builder {

        protected DefaultNavigationParams params;

        public Builder(Context context, ViewGroup parent) {
            this.params = new DefaultNavigationParams(context, parent);
        }

        public DefaultNavigation.Builder setLeftText(int leftText) {
            this.params.leftText = this.params.getString(leftText);
            return this;
        }

        public DefaultNavigation.Builder setLeftText(String leftText) {
            this.params.leftText = leftText;
            return this;
        }

        @Override
        public AbsNavigation create() {
            return null;
        }

        public static class DefaultNavigationParams extends NavigationParams {

            //扩展属性
            //左边文本
            public String leftText;
            //中间的文本
            public String centerText;
            //右边的文本
            public String rightText;
            //左边字体颜色
            public int leftTextColor;
            //右边字体颜色
            public int rightTextColor;
            //左边的视图点击事件
            public View.OnClickListener leftOnClickListener;
            //中间的视图点击事件
            public View.OnClickListener centerOnClickListener;
            //右边的视图点击事件
            public View.OnClickListener rightOnClickListener;

            public DefaultNavigationParams(Context context, ViewGroup parent) {
                super(context, parent);
            }

            public String getString(int id) {
                return context.getResources().getString(id);
            }

        }


    }

}
