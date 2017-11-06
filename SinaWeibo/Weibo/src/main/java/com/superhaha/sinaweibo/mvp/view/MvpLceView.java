package com.superhaha.sinaweibo.mvp.view;



/**
 * 请求网络,需要等待
 * Created by Administrator on 2017/11/6.
 */

public interface MvpLceView<M> extends MvpView{

    //    实现方法 例如：加载
//    返回结果数据
//    显示view
//    加载错误：网络异常

//    用于显示进度条(下拉刷新，上拉加载更多)
    public void showLoading(boolean isPullToRefresh);

//    显示视图
    public void showContent();

//    处理异常接口方法
    public void showError(Exception e, boolean isPullToRefresh);

//        绑定数据
    public void showData(M data);
}
