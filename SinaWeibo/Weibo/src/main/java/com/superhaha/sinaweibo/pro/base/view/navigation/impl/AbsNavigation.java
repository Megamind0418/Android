package com.superhaha.sinaweibo.pro.base.view.navigation.impl;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import com.superhaha.sinaweibo.pro.base.view.navigation.INavigation;

/**
 * 泛型：规范规定数据类型
 * 设计模式
 * Created by superhaha on 2017/11/25.
 */

public abstract class AbsNavigation<P extends AbsNavigation.Builder.NavigationParams> implements INavigation {

    private P params;
    private View contentView;

    public AbsNavigation(P params ) {
        this.params = params;
    }

   public P getParams(){
       return params;
   }

    @Override
    public void createAndBind() {
//        创建和绑定布局
        contentView = LayoutInflater.from(params.context).inflate(getLayoutId(), params.parent, false);
//        判断当前视图是否在父容器中存在(一个视图不能够同时绑定两个父容器)
        ViewParent viewParent = contentView.getParent();
        if (viewParent != null) {
            ViewGroup viewGroup = (ViewGroup) viewParent;
            viewGroup.removeView(contentView);
        }
//        绑定视图
        params.parent.addView(contentView,0);

    }

    public static abstract class Builder {
        public  abstract AbsNavigation create();

        //      定义参数类
        public static class NavigationParams {
            //    有两个必备的参数
            public Context context;
            public ViewGroup parent;

            public NavigationParams(Context context, ViewGroup parent) {
                this.context = context;
                this.parent = parent;
            }
        }
    }
}
