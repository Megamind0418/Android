package com.superhaha.sinaweibo.pro.user.view.presenter;

import android.content.Context;

import com.superhaha.sinaweibo.http.framework.utils.HttpUtils;
import com.superhaha.sinaweibo.pro.base.presenter.BasePresenter;
import com.superhaha.sinaweibo.pro.user.view.LoginView;
import com.superhaha.sinaweibo.pro.user.view.model.LoginModel;

/**
 * Created by superhaha on 2017/11/24.
 */

public class LoginPresenter extends BasePresenter<LoginView> {

    private LoginModel loginModel;

    public LoginPresenter(Context context) {
        super(context);
        loginModel = new LoginModel(context);

    }

    public void login(String username,String password){
            loginModel.login(username, password, new HttpUtils.OnHttpResultListener() {
                @Override
                public void onResult(String result) {
                        getView().onResult(result);
//                    getView()是因为继承了BasePresenter又继承了MvpBasePresenter，所以才可以调用
//                    onResult回调是使用了LoginView泛型
                }
            });
    }

}
