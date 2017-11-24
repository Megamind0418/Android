package com.superhaha.sinaweibo.pro.home.view;

import android.text.TextUtils;
import android.util.Log;
import android.view.View;

import com.superhaha.sinaweibo.R;
import com.superhaha.sinaweibo.pro.base.view.BaseFragment;
import com.superhaha.sinaweibo.pro.discover.presenter.DiscoverPresenter;
import com.superhaha.sinaweibo.pro.user.view.LoginView;
import com.superhaha.sinaweibo.pro.user.view.presenter.LoginPresenter;
import com.superhaha.sinaweibo.utils.ToastUtil;

/**
 * 主页页面
 * Created by Administrator on 2017/11/7.
 */

public class HomeFragment extends BaseFragment<LoginPresenter,LoginView> implements LoginView {

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
        contentView.findViewById(R.id.tv_login).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        });
    }

    private void login(){
            loginPresenter.login("zhang","123456");
    }

    @Override
    public void onResult(String data) {
        if (TextUtils.isEmpty(data)) {
            ToastUtil.showToast(getContext(), "Login Failed");
            Log.i("zhang", data);
        } else {
            ToastUtil.showToast(getContext(),data);
        }
    }
}
