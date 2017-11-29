package com.superhaha.sinaweibo.pro.user.view.model;

import android.content.Context;

import com.superhaha.sinaweibo.http.framework.impl.RequestParam;
import com.superhaha.sinaweibo.http.framework.impl.system.SystemHttpCommand;
import com.superhaha.sinaweibo.http.framework.utils.HttpTask;
import com.superhaha.sinaweibo.http.framework.utils.HttpUtils;
import com.superhaha.sinaweibo.pro.base.model.BaseModel;

/**
 * Created by superhaha on 2017/11/24.
 */

public class LoginModel extends BaseModel {
    public LoginModel(Context context) {
        super(context);
    }

//    服务器地址---接口地址
    private String getServerUrl(){
//        return "http://192.168.1.102:8080/Dream_6_2_LoginServer/LoginServlet";
        return "http://192.168.1.94:8080/Dream_6_2_LoginServer/LoginServlet";

    }

    public void login(String username, String password, HttpUtils.OnHttpResultListener onHttpResultListener) {
        RequestParam requestParam = new RequestParam();
        requestParam.put("username",username);
        requestParam.put("password",password);
        HttpTask httpTask = new HttpTask(
                getServerUrl(),
                requestParam,
                new SystemHttpCommand(),
                onHttpResultListener);
        httpTask.execute();
    }
}
