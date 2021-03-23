package com.xm.oppo.utils;

import android.app.Activity;
import android.util.Log;
import android.view.KeyEvent;

import java.io.IOException;

/**
 * Created by Administrator on 2018/10/9.
 * 类描述:自动点击屏幕某个坐标
 */

public class AutoClickUtil {
    private int width = 0;
    private int height = 0;

    /**
     * 传入在屏幕中的比例位置，坐标左上角为基准
     * @param act 传入Activity对象
     * @param ratioX 需要点击的x坐标在屏幕中的比例位置
     * @param ratioY 需要点击的y坐标在屏幕中的比例位置
     */
    public void autoClickRatio(Activity act, final double ratioX, final double ratioY) {
        width = act.getWindowManager().getDefaultDisplay().getWidth();
        height = act.getWindowManager().getDefaultDisplay().getHeight();
        new Thread(new Runnable() {
            @Override
            public void run() {
                // 线程睡眠0.3s
                try {
                    Thread.sleep(300);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    Log.e("xm12345","input error："+e);
                }
                // 生成点击坐标
                int x = (int) (width * ratioX);
                int y = (int) (height * ratioY);

                Log.i("edlog","device width:" + width + ",device height:" + height);
                Log.i("edlog","click x:" + x + ",click y:" + y);

                // 利用ProcessBuilder执行shell命令
                String[] order = { "input", "tap", "" + x, "" + y };
                try {
                    new ProcessBuilder(order).start();
                } catch (IOException e) {
                    e.printStackTrace();
                    Log.e("xm12345","input error："+e);
                }
            }
        }).start();
    }

    /**
     * 传入在屏幕中的坐标，坐标左上角为基准
     * @param act 传入Activity对象
     * @param x 需要点击的x坐标
     * @param y 需要点击的x坐标
     */
    public void autoClickPos(Activity act, final double x, final double y) {
        width = act.getWindowManager().getDefaultDisplay().getWidth();
        height = act.getWindowManager().getDefaultDisplay().getHeight();
        new Thread(new Runnable() {
            @Override
            public void run() {
                // 线程睡眠0.3s
                try {
                    Thread.sleep(300);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                // 利用ProcessBuilder执行shell命令
                String[] order = { "input", "tap", "" + x, "" + y };
                try {
                    new ProcessBuilder(order).start();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    public void keycobeBack(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                // 利用ProcessBuilder执行shell命令
                String[] order = {"input", "keyevent", "4"};
                try {
                    new ProcessBuilder(order).start();
                } catch (IOException e) {
                    e.printStackTrace();
                    Log.i("xm123456","返回键错误："+e);
                }
            }
        }).start();
    }


    public void keyHome(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                // 利用ProcessBuilder执行shell命令
                String[] order = {"input", "keyevent", "3"};
                try {
                    new ProcessBuilder(order).start();
                } catch (IOException e) {
                    e.printStackTrace();
                    Log.i("xm123456","返回键错误："+e);
                }
            }
        }).start();
    }

    public void keycobeBack(Activity activity){
        activity.onKeyDown(KeyEvent.KEYCODE_BACK, null);
    }


}
