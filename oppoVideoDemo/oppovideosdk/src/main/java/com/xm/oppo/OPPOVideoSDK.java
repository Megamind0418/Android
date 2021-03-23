package com.xm.oppo;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Handler;
import android.util.Log;

import com.xm.oppo.ad.RewardVideo;
import com.xm.oppo.ad.TimerRewardVideo;
import com.xm.oppo.ad.ResidentTimerRewardVideo;
import com.xm.oppo.presenter.OPPOPresenter;
import com.xm.oppo.presenter.PresenterImpl;
import com.xm.oppo.utils.ActivityForegroundUtil;
import com.xm.oppo.view.ADCallback;
import com.xm.oppo.view.OPPOView;
import com.xm.oppo.view.XMCallback;

import java.util.HashMap;

/**
 * @author : STM
 * @description : 描述
 * @date : 2019/10/14/014
 * @modifier : STM
 * @date : 2019/10/14/014
 */
public class OPPOVideoSDK implements OPPOView {
    private static HashMap<String, String> hashMap = new HashMap<>();
    private RewardVideo rewardVideo = new RewardVideo();
    private TimerRewardVideo timerRewardVideo = new TimerRewardVideo();
    private ResidentTimerRewardVideo residentTimerRewardVideo = new ResidentTimerRewardVideo();
    private boolean isSpecial = false;
    @SuppressLint("StaticFieldLeak")
    private static OPPOVideoSDK oppoVideoSDK = new OPPOVideoSDK();
    private XMCallback xmCallback;
    private boolean isOpenAd = true;
    public boolean isPlayVideo = false;
    public boolean isResident = false;

    public static OPPOVideoSDK getInstance() {
        return oppoVideoSDK;
    }

    public int residentVariable() {
        if (hashMap != null && hashMap.size() > 2 && hashMap.containsKey("D")) {

            int s = Integer.valueOf(hashMap.get("D")) / 60;
            if (s <= 0) {
                ++s;
            }
            if (Integer.valueOf(hashMap.get("D")) % 60 >= 30) {
                ++s;
            }
            return s;
        } else {
            return 1;
        }
    }

    public void init(Activity activity, XMCallback xmCallback) {
        this.xmCallback = xmCallback;
        OPPOPresenter oppoPresenter = new PresenterImpl(activity, this);
        oppoPresenter.loadOPPO();
    }

    public boolean dataVerification() {
        if (hashMap != null) {
            if (hashMap.size() < 2) {
                return false;
            }
        } else {
            return false;
        }
        return true;
    }

    public void startShowAd(Activity activity, String videoId, ADCallback adCallback, XMCallback
            xmCallback) {
        Log.e("xm123456", "DD广告开起");
        if (!isOpenAd) {
            Log.e("xm123456", "广告未开启");
            if (xmCallback != null) {
                xmCallback.onOff("广告未开启");
            }
            return;
        }
        if (hashMap != null && hashMap.size() > 0) {
            if (isSpecial) {
                //特殊广告id
                rewardVideo.init(activity, hashMap.get("id"), hashMap, adCallback);
            } else {
                //默认广告id
                rewardVideo.init(activity, videoId, hashMap, adCallback);
            }
        } else {
            Log.e("xm123456", "获取参数未成功:" + hashMap.size());
            if (xmCallback != null) {
                xmCallback.onOff("获取参数未成功");
            }
        }
    }

    @Override
    public void addOPPO(HashMap<String, String> hashMap, String appletId) {
        if (hashMap != null && hashMap.size() > 0) {
            this.hashMap = hashMap;
        } else {
            xmCallback.onOff("后台已关闭");
        }

        if (!appletId.equals("OPPO")) {
            isSpecial = true;
        }

        if (hashMap != null && hashMap.containsKey("I") && hashMap.get("I").equals("0")) {
            isOpenAd = false;
            xmCallback.onOff("后台手动关闭");
        }
    }

    public void timerAd(final Activity activity, final String videoId) {
        Log.e("xm123456", "DS广告开起");
        if (!isOpenAd) {
            Log.e("xm123456", "广告未开启");
            return;
        }

        if (hashMap != null && hashMap.containsKey("E") && hashMap.get("E").equals("0")) {
            Log.e("xm123456", "M为0");
            return;
        }

        if (isResident) {
            Log.e("xm123456", "DS转为ZL");
            return;
        }

        if (hashMap != null && hashMap.size() > 0) {
            if (isSpecial) {
                //特殊广告id
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        loopTimerVideo(activity, hashMap.get("id"));
                    }
                }, Integer.valueOf(hashMap.get("A")) * 1000);
            } else {
                //默认广告id
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        loopTimerVideo(activity, videoId);
                    }
                }, Integer.valueOf(hashMap.get("A")) * 1000);
            }
        } else {
            Log.e("xm123456", "参数未获取成功:" + hashMap.size());
            xmCallback.onOff("参数未获取成功");
        }
    }

    private void loopTimerVideo(final Activity activity, final String videoId) {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                //打点广告和驻留广告是否在播放视频,应用是否在前台
                if (isPlayVideo || isResident || !ActivityForegroundUtil.isForeground(activity)) {
                    Log.e("xm123456", "播放中");
                    timerAd(activity, videoId);
                } else {
                    Log.e("xm123456", "展示DS广告");
                    timerRewardVideo.init(activity, videoId, hashMap);
                }
            }
        }, Integer.valueOf(hashMap.get("E")) * 1000);
    }


    public void residentAd(final Activity activity, final String videoId, final boolean screen,
                           final ADCallback
            adCallback) {
        Log.e("xm123456", "ZL广告开起");
        if (!isOpenAd) {
            Log.e("xm123456", "广告未开启");
            return;
        }

        if (hashMap != null && hashMap.containsKey("D") && hashMap.get("D").equals("0")) {
            Log.e("xm123456", "N为0");
            return;
        }

        if (hashMap != null && hashMap.containsKey("B") && residentTimerRewardVideo
                .residentPlayLimit >= Integer.valueOf(hashMap.get("B"))) {
            Log.e("xm123456", "已达上限：" + hashMap.get("B"));
            return;
        }

        isResident = true;
        if (hashMap != null && hashMap.size() > 0) {
            if (isSpecial) {
                //特殊广告id
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        residentTimerRewardVideo.init(activity, hashMap.get("id"), hashMap, screen,
                                adCallback);
                    }
                }, Integer.valueOf(hashMap.get("A")) * 1000);
            } else {
                //默认广告id
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        residentTimerRewardVideo.init(activity, videoId, hashMap, screen,
                                adCallback);
                    }
                }, Integer.valueOf(hashMap.get("A")) * 1000);
            }


        } else {
            Log.e("xm123456", "参数未获取成功:" + hashMap.size());
        }

    }
}
