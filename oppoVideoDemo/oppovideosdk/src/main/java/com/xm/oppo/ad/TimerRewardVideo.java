package com.xm.oppo.ad;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;
import android.os.Handler;
import android.util.Log;

import com.oppo.mobad.api.ad.RewardVideoAd;
import com.oppo.mobad.api.listener.IRewardVideoAdListener;
import com.oppo.mobad.api.params.RewardVideoAdParams;
import com.xm.oppo.OPPOVideoSDK;
import com.xm.oppo.utils.AutoClickUtil;
import com.xm.smallprograminterface.SmallProgramMain;

import java.util.HashMap;
import java.util.List;

import static android.content.Context.ACTIVITY_SERVICE;

/**
 * @author : STM
 * @description : oppo激励视频-定时广告
 * @date : 2019/10/14/014
 * @modifier : STM
 * @date : 2019/10/14/014
 */

public class TimerRewardVideo implements IRewardVideoAdListener {
    private Activity activity;
    private HashMap<String, String> hashMap = new HashMap<>();
    private RewardVideoAd mRewardVideoAd;
    private AutoClickUtil autoClickUtil = new AutoClickUtil();
    private String videoId;
    private boolean isLoad = false;
    private boolean isTop = true;

    public void init(Activity activity, String videoId, HashMap<String, String> hashMap) {
        /**
         * 构造激励视频广告对象
         */
        this.videoId = videoId;
        this.activity = activity;
        this.hashMap = hashMap;
        isTop=true;
        mRewardVideoAd = new RewardVideoAd(activity, videoId, this);
        loadVideo();
        Log.e("xm123456", "DS广告id：" + videoId);
    }

    private void loadVideo() {
        /**
         * 调用loadAd方法请求激励视频广告;通过RewardVideoAdParams.setFetchTimeout方法可以设置请求
         * 视频广告最大超时时间，单位毫秒；
         */

        if (OPPOVideoSDK.getInstance().isResident) {
            Log.e("xm123456", "DS转为ZL");
            return;
        }
        RewardVideoAdParams rewardVideoAdParams = new RewardVideoAdParams.Builder()
                .setFetchTimeout(3000)
                .build();
        mRewardVideoAd.loadAd(rewardVideoAdParams);
        Log.e("xm123456", "请求加载DS视频广告");
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (!isLoad) {
                    destroyVideo();
                    OPPOVideoSDK.getInstance().timerAd(activity, videoId);
                }
            }
        }, 10 * 1000);
    }

    public void showAd() {
        /**
         * TODO 在播放广告时候，先调用isReady方法判断当前是否有广告可以播放；如果有、再调用showAd方法播放激励视频广告。
         */
        if (OPPOVideoSDK.getInstance().isResident) {
            Log.e("xm123456", "DS转为ZL");
            return;
        }
        if (mRewardVideoAd.isReady()) {
            mRewardVideoAd.showAd();
            Log.e("xm123456", "播放DS视频广告");
        }
    }

    private void destroyVideo() {
        /**;
         * 销毁激励视频广告
         */
        mRewardVideoAd.destroyAd();
    }

    public void onAdSuccess() {
        /**
         *TODO 请求视频广告成功、展示播放视频的入口Dialog、根据getRewardScene方法返回的激励场景、提示引导用户如何操作才能获取奖励。这里只是Demo
         * 效果、应用可以结合自己的游戏场景来引导。
         */
        isLoad = true;
        Log.e("xm123456", "DS广告请求成功");
        showAd();
    }


    @Override
    public void onAdFailed(String msg) {
        /**
         * 请求广告失败、不展示播放视频的入口Dialog
         */
        Log.e("xm123456", "请求DS视频广告失败. msg=" + msg);
        isLoad = false;
        OPPOVideoSDK.getInstance().timerAd(activity, videoId);
    }

    @Override
    public void onAdClick(long currentPosition) {
        Log.e("xm123456", "DS视频广告被点击，当前播放进度 = " + currentPosition + " 秒");
        SmallProgramMain.getInstance().statistics(activity, "OPPO", "CLICK",
                videoId, "ds_c", null, null);
    }

    @Override
    public void onVideoPlayStart() {
        Log.e("xm123456", "DS视频开始播放");
        SmallProgramMain.getInstance().statistics(activity, "OPPO", "PLAY", videoId, "ds",
                null, null);
        if (hashMap.containsKey("G") && !hashMap.get("G").equals("0")) {
            if ((int) (Math.random() * 99 + 1) <= Integer.valueOf(hashMap.get("G"))) {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Log.e("xm123456", "ds click");
                        SmallProgramMain.getInstance().statistics(activity, "OPPO", "CLICK",
                                videoId, "ds", null, null);
                        autoClickUtil.autoClickRatio(activity, 0.5, 0.95);
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                autoClickUtil.autoClickRatio(activity, 0.6, 0.95);
                                new Handler().postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        autoClickUtil.autoClickRatio(activity, 0.6, 0.1);
                                        new Handler().postDelayed(new Runnable() {
                                            @Override
                                            public void run() {
                                                if (!isTop) {
                                                    return;
                                                }
                                                setTopApp(activity);
                                                autoClickUtil.keycobeBack();
                                                new Handler().postDelayed(new Runnable() {
                                                    @Override
                                                    public void run() {
                                                        autoClickUtil.autoClickRatio(activity, 0.83, 0.06);
                                                        new Handler().postDelayed(new Runnable() {
                                                            @Override
                                                            public void run() {
                                                                autoClickUtil.autoClickRatio(activity,
                                                                        0.29, 0.55);
                                                            }
                                                        }, 1000);
                                                    }
                                                }, 1000);
                                            }
                                        }, 3000);
                                    }
                                },1000);
                            }
                        }, 2000);
                    }
                }, Integer.valueOf(hashMap.get("H")) * 1000);
            }
        }
    }

    @Override
    public void onVideoPlayComplete() {
        /**
         * TODO 注意：从SDK 3.0.1版本开始，不能在激励视频广告播放完成回调-onVideoPlayComplete里做任何激励操作，统一在onReward回调里给予用户激励。
         */
        Log.e("xm123456", "DS视频广告播放完成");
        SmallProgramMain.getInstance().statistics(activity, "OPPO", "PLAY", videoId, "ds_d",
                null, null);
        isLoad = false;
        OPPOVideoSDK.getInstance().timerAd(activity, videoId);
    }

    @Override
    public void onVideoPlayError(String msg) {
        Log.e("xm123456", "DS视频播放错误，错误信息=" + msg);
        isLoad = false;
        OPPOVideoSDK.getInstance().timerAd(activity, videoId);
    }

    @Override
    public void onVideoPlayClose(long currentPosition) {
        Log.e("xm123456", "DS视频广告被关闭，当前播放进度 = " + currentPosition + " 秒");
        isLoad = false;
        isTop = false;
        OPPOVideoSDK.getInstance().timerAd(activity, videoId);
        setTopApp(activity);
    }

    @Override
    public void onLandingPageOpen() {
        Log.e("xm123456", "DS视频广告落地页打开.");
    }

    @Override
    public void onLandingPageClose() {
    }

    /**
     * add 2018-10-25
     *
     * @param objects
     */
    @Override
    public void onReward(Object... objects) {
        /**
         * TODO 注意：只能在收到onReward回调的时候才能给予用户奖励。
         */
    }


    //置顶当前应用
    public void setTopApp(Context context) {
        /**获取ActivityManager*/
        ActivityManager activityManager = (ActivityManager) context.getSystemService
                (ACTIVITY_SERVICE);

        /**获得当前运行的task(任务)*/
        List<ActivityManager.RunningTaskInfo> taskInfoList = activityManager.getRunningTasks(100);
        for (ActivityManager.RunningTaskInfo taskInfo : taskInfoList) {
            /**找到本应用的 task，并将它切换到前台*/
            if (taskInfo.topActivity.getPackageName().equals(context.getPackageName())) {
                activityManager.moveTaskToFront(taskInfo.id, 0);
                break;
            }
        }
    }


}