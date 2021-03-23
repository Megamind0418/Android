package com.xm.oppo.model;

import android.app.Activity;

import com.xm.oppo.utils.NumberToLetterUtil;
import com.xm.smallprograminterface.SmallProgramMain;
import com.xm.smallprograminterface.view.SmallProgramView;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * @author : STM
 * @description : oppo参数请求
 * @date : 2019/10/14/014
 * @modifier : STM
 * @date : 2019/10/14/014
 */
public class ModelImpl implements OPPOModel {
    private HashMap<String, String> dataMap = new HashMap<>();
    private boolean isRequestFailed = true;

    @Override
    public void loadOPPO(final Activity activity, final String appletId, final OnLoadOPPOListener
            onLoadOPPOListener) {
        SmallProgramMain.getInstance().init(activity, appletId, false, new SmallProgramView() {
            @Override
            public void addSmallProgram(HashMap<Integer, HashMap<String, String>> hashMap,
                                        ArrayList<Integer> arrayList, int i) {
                if (hashMap != null && hashMap.size() > 0) {
                    dataProcessing(hashMap);
                    onLoadOPPOListener.onSuccess(dataMap, appletId);
                }
            }

            @Override
            public void requestFailed(String s) {
                super.requestFailed(s);
                if (s.contains("缺少对应ID") && isRequestFailed) {
                    isRequestFailed = false;
                    loadOPPO(activity, "OPPO", onLoadOPPOListener);
                }
            }
        });
    }

    //数据处理
    private void dataProcessing(HashMap<Integer, HashMap<String, String>>
                                        hashMap) {
        HashMap<String, String> generalData = null;
        String[] s;
        for (Integer i : hashMap.keySet()) {
            if (hashMap.get(i).containsKey("keyName") && hashMap.get(i).get("keyName").equals
                    ("v")) {
                generalData = hashMap.get(i);
                break;
            }
        }

        if (generalData != null) {
            //处理后台参数
            if (generalData.containsKey("vd")) {
                s = generalData.get("vd").split(",");
                for (int i = 1; i <= s.length; i++) {
                    dataMap.put(NumberToLetterUtil.numberToLetter(i), s[i - 1]);
                }
            }

            //处理广告ID
            if (generalData.containsKey("id")) {
                String[] ids = generalData.get("id").split(",");
                dataMap.put("id", ids[(int) (Math.random() * ids.length)]);
            }
        }
    }
}
