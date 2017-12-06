package com.superhaha.sinaweibo.pro.home.view;

import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import com.superhaha.sinaweibo.R;
import com.superhaha.sinaweibo.pro.base.view.BaseFragment;
import com.superhaha.sinaweibo.pro.base.view.navigation.impl.DefaultNavigation;
import com.superhaha.sinaweibo.pro.discover.presenter.DiscoverPresenter;
import com.superhaha.sinaweibo.pro.user.view.LoginView;
import com.superhaha.sinaweibo.pro.user.view.presenter.LoginPresenter;
import com.superhaha.sinaweibo.utils.ToastUtil;

/**
 * 主页页面
 * Created by Administrator on 2017/11/7.
 */

public class HomeFragment extends BaseFragment<LoginPresenter, LoginView> implements LoginView {

    private LoginPresenter loginPresenter;

    //    创建对象
    @Override
    public LoginPresenter bindPresenter() {
        loginPresenter = new LoginPresenter(getContext());
        return loginPresenter;
    }

    @Override
    public LoginView bindView() {
        return this;
    }

    @Override
    public int bindLayoutId() {
        return R.layout.fragment_home;
    }

    @Override
    public void initContentView(View contentView) {
        initNavigation(contentView);
        contentView.findViewById(R.id.tv_login).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        });
    }

    private void initNavigation(View contentView) {
        DefaultNavigation.Builder builder = new DefaultNavigation.Builder(getContext(), (ViewGroup) contentView);
        builder.setLeftText(R.string.register_text)
                .setCenterText(R.string.tabbar_home_text)
                .setRightText(R.string.login_text)
                .setLeftTextColor(R.color.app_text_orange_color)
                .setRightTextColor(R.color.app_text_orange_color)
                .setLeftOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ToastUtil.showToast(getContext(), "点击了注册");
                    }
                })
                .setRightOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ToastUtil.showToast(getContext(), "点击了登陆");
                    }
                }).create();

    }

    private void login() {
        loginPresenter.login("zhang", "123456");
    }

    @Override
    public void onResult(String data) {
        if (TextUtils.isEmpty(data)) {
            ToastUtil.showToast(getContext(), "Login Failed");
        } else {
            ToastUtil.showToast(getContext(), data);
        }
    }
}
