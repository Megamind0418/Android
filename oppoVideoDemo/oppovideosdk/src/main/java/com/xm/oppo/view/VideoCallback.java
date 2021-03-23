package com.xm.oppo.view;

/**
 * @author : STM
 * @description : 描述
 * @date : 2019/10/23/023
 * @modifier : STM
 * @date : 2019/10/23/023
 */

public interface VideoCallback {
    void onAdFailed(String msg);
    void onVideoPlayError(String msg);
    void onAdSuccess();
    void onVideoPlayClose();
    void onLandingPageClose();
    void onVideoPlayComplete();
    void getRewards();
    void rewardAcquisitionFailed();
    void onVideoPlayStart();
}
