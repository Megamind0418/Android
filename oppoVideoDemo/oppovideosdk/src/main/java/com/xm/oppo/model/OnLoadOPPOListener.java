package com.xm.oppo.model;

import java.util.HashMap;

/**
 * @author : STM
 * @description : OPPO数据加载侦听器
 * @date : 2019/10/14/014
 * @modifier : STM
 * @date : 2019/10/14/014
 */
public interface OnLoadOPPOListener {
    void onSuccess(HashMap<String,String> hashMap,String appletId);
}
