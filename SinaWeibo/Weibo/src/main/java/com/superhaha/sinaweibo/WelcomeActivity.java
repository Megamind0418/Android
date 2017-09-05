package com.superhaha.sinaweibo;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.media.Image;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;

import static com.superhaha.sinaweibo.R.id.rl_bg;

/**
 * 程序入口
 * Created by superhaha on 2017/9/4.
 */

public class WelcomeActivity extends Activity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
//        1.准备资源图片
//        2.编写布局文件
//        3.实现功能
        initWelcomeAnimator();
        initLogoAnimator();
    }

    private void initWelcomeAnimator() {
//        属性动画实现
        ImageView iv_slogan = (ImageView) findViewById(R.id.iv_slogan);
//        第一个参数---target：代表我们要给哪个布局添加动画
//        第二个参数---propertyName：代表动画类型
//        第三个参数---动画开始时候的位置,或者说状态
//        第四个参数---动画结束时候的位置,或者说状态
        ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(iv_slogan, "alpha", 0.2f, 1.0f);
//        动画的时间
        objectAnimator.setDuration(4000);
//        启动动画
        objectAnimator.start();
    }

    private void initLogoAnimator(){
//        给logo添加动画
        ImageView iv_logo = (ImageView) findViewById(R.id.iv_logo);
//        scaleX:X轴缩放   scaleY:Y轴缩放
//        后三个参数：一开始的大小->需要缩放到的大小->最后呈现的大小
        ObjectAnimator objectAnimatorX = ObjectAnimator.ofFloat(iv_logo, "scaleX", 1.0f, 1.2f, 1.0f);
        ObjectAnimator objectAnimatorY = ObjectAnimator.ofFloat(iv_logo, "scaleY", 1.0f, 1.2f, 1.0f);
//        通过动画集合组合并且执行动画
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.setDuration(4000);
//        这两个动画同时执行
        animatorSet.play(objectAnimatorX).with(objectAnimatorY);
        animatorSet.start();
    }

}
