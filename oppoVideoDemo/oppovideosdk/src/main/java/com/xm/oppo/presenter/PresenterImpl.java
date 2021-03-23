package com.xm.oppo.presenter;

import android.app.Activity;

import com.xm.oppo.view.OPPOView;
import com.xm.oppo.model.ModelImpl;
import com.xm.oppo.model.OPPOModel;
import com.xm.oppo.model.OnLoadOPPOListener;

import java.util.HashMap;

/**
 * @author : STM
 * @description : 描述
 * @date : 2019/10/14/014
 * @modifier : STM
 * @date : 2019/10/14/014
 */
public class PresenterImpl implements OPPOPresenter, OnLoadOPPOListener {
    private Activity activity;
    private OPPOModel oppoModel;
    private OPPOView oppoView;

    public PresenterImpl(Activity activity, OPPOView oppoView) {
        this.activity = activity;
        this.oppoView = oppoView;
        oppoModel = new ModelImpl();
    }

    @Override
    public void loadOPPO() {
        oppoModel.loadOPPO(activity, activity.getPackageName(), this);
    }

    @Override
    public void onSuccess(HashMap<String, String> hashMap,String appletId) {
        oppoView.addOPPO(hashMap,appletId);
    }
}
