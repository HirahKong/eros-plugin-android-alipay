package com.benmu.erospluginalipay.event;

import android.content.Context;
import android.text.TextUtils;

import com.benmu.erospluginalipay.model.AliPayModel;
import com.benmu.erospluginalipay.utils.AliPayTools;

import com.eros.framework.adapter.router.RouterTracker;
import com.eros.framework.manager.ManagerFactory;
import com.eros.framework.manager.impl.ParseManager;
import com.eros.framework.model.WeexEventBean;
import com.eros.wxbase.EventGate;
import com.taobao.weex.bridge.JSCallback;

/**
 * Created by haorui on 2018/6/6.
 * Des:
 */
public class EventPay extends EventGate {

    @Override
    public void perform(Context context, WeexEventBean weexEventBean) {
        String params = weexEventBean.getJsParams();
        if (TextUtils.isEmpty(params)) return;
        pay(params, context, weexEventBean.getJscallback());
    }

    public void pay(String params, Context context, JSCallback callback) {
        ParseManager parseManager = ManagerFactory.getManagerService(ParseManager.class);
        AliPayModel aliPayModel = parseManager.parseObject(params, AliPayModel.class);
        //入参类型为Activity
        AliPayTools.aliPay(RouterTracker.peekActivity(),aliPayModel,callback);
    }
}
