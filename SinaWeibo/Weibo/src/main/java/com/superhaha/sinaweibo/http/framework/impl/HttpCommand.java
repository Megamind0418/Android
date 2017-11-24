package com.superhaha.sinaweibo.http.framework.impl;

import com.superhaha.sinaweibo.http.framework.IHttpCommand;
import com.superhaha.sinaweibo.http.framework.IRequestParam;

/**
 * Created by Dream on 16/6/2.
 */
public abstract class HttpCommand<T> implements IHttpCommand<T> {

    @Override
    public String execute(String url, IRequestParam<T> requestParam) {
        return null;
    }
}
