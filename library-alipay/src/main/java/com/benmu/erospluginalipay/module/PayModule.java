package com.benmu.erospluginalipay.module;

import com.alibaba.weex.plugin.annotation.WeexModule;

import com.eros.framework.manager.ManagerFactory;
import com.eros.framework.manager.impl.dispatcher.DispatchEventManager;
import com.eros.framework.model.WeexEventBean;
import com.taobao.weex.annotation.JSMethod;
import com.taobao.weex.bridge.JSCallback;
import com.taobao.weex.common.WXModule;


/**
 * Created by haorui on 2018/6/6.
 * Des:
 */
@WeexModule(name = "bmAliPay", lazyLoad = true)
public class PayModule extends WXModule {

    public static final String EVENT_PAYBYALI= "com.benmu.erospluginalipay.event.EventPay";

    private JSCallback mCallback;

    @JSMethod(uiThread = true)
    public void pay(String params, JSCallback callback) {
        WeexEventBean eventBean = new WeexEventBean();
        eventBean.setContext(mWXSDKInstance.getContext());
        eventBean.setKey(EVENT_PAYBYALI);
        eventBean.setJsParams(params);
        eventBean.setJscallback(callback);
        ManagerFactory.getManagerService(DispatchEventManager.class).getBus().post(eventBean);
    }
}

