package com.xm.oppo;

import android.app.Activity;

import com.xm.oppo.view.ADCallback;
import com.xm.oppo.view.XMCallback;

/**
 * @author : STM
 * @description : 描述
 * @date : 2019/10/16/016
 * @modifier : STM
 * @date : 2019/10/16/016
 */
public class XMOPPO {
    private static XMOPPO xmoppo = new XMOPPO();
    private boolean isInit = true;
    private boolean isTimer=true;

    public static XMOPPO getInstance() {
        return xmoppo;
    }

    public void init(Activity activity, XMCallback xmCallback) {
        if (isInit) {
            isInit = false;
            OPPOVideoSDK.getInstance().init(activity, xmCallback);
        }
    }

    public void startShowAd(Activity activity, String videoId, ADCallback adCallback,XMCallback xmCallback) {
        OPPOVideoSDK.getInstance().startShowAd(activity, videoId, adCallback,xmCallback);
    }

    public void timerAd(Activity activity,String videoId){
        if (isTimer){
            OPPOVideoSDK.getInstance().timerAd(activity,videoId);
            isTimer=false;
        }
    }

    public void residentAd(Activity activity,String videoId,boolean screen,ADCallback adCallback){
        OPPOVideoSDK.getInstance().residentAd(activity,videoId,screen,adCallback);
    }

    public boolean dataVerification(){
        return OPPOVideoSDK.getInstance().dataVerification();
    }

    public int residentVariable(){
        return OPPOVideoSDK.getInstance().residentVariable();
    }
}
