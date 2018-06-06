package com.benmu.erospluginalipay.utils;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.widget.Toast;

import com.alipay.sdk.app.EnvUtils;
import com.alipay.sdk.app.PayTask;
import com.benmu.erospluginalipay.model.AliPayModel;
import com.benmu.erospluginalipay.model.AliPayResultModel;
import com.taobao.weex.bridge.JSCallback;

import java.util.Map;


/**
 * Created by haorui on 2018/6/6.
 * Des:
 */
public class AliPayTools {

    private static final int SDK_PAY_FLAG = 1;

    private static JSCallback mCallback;

    @SuppressLint("HandlerLeak")
    private static Handler mHandler = new Handler() {
        @SuppressWarnings("unused")
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case SDK_PAY_FLAG: {
                    @SuppressWarnings("unchecked") AliPayResultModel payResult = new AliPayResultModel((Map<String, String>) msg.obj);
                    //对于支付结果，请商户依赖服务端的异步通知结果。同步通知结果，仅作为支付结束的通知。
                    mCallback.invoke(payResult);
                    break;
                }
                default:
                    break;
            }
        }
    };

    public static void aliPay(final Activity activity, AliPayModel aliPayModel, JSCallback callback) {
        mCallback = callback;

        //沙箱调试模式
        if(aliPayModel.getSandbox()!=null&&aliPayModel.getSandbox()) {
            EnvUtils.setEnv(EnvUtils.EnvEnum.SANDBOX);
        }

        String orderInfo = aliPayModel.getAuthInfo();
        if(TextUtils.isEmpty(orderInfo)) {
            String privateKey = aliPayModel.getPrivateKey();
            String sign  = aliPayModel.getSign();

            if(TextUtils.isEmpty(sign)&&TextUtils.isEmpty(privateKey)) {
                Toast.makeText(activity, "支付参数不能为空！", Toast.LENGTH_SHORT).show();
                return;
            }

            Map<String, String> params = AliPayOrderInfoUtil.buildOrderParamMap(aliPayModel.getAppId(),
                    aliPayModel.getRsa2(), aliPayModel.getOutTradeNo(),
                    aliPayModel.getName(), aliPayModel.getMoney(),
                    aliPayModel.getDetail(),aliPayModel.getTimestamp());

            String orderParam = AliPayOrderInfoUtil.buildOrderParam(params);



            if(TextUtils.isEmpty(sign)) {
                if(TextUtils.isEmpty(privateKey)) {
                    //sign 和 privateKey 必须有一个不为空
                    Toast.makeText(activity, "支付密钥不能为空！", Toast.LENGTH_SHORT).show();
                    return;
                }
                sign = AliPayOrderInfoUtil.getSign(params, privateKey, aliPayModel.getRsa2());
            }
            orderInfo = orderParam + "&" + sign;
        }

        final String finalOrderInfo = orderInfo;
        Runnable payRunnable = new Runnable() {

            @Override
            public void run() {
                PayTask alipay = new PayTask(activity);
                Map<String, String> result = alipay.payV2(finalOrderInfo, true);

                Message msg = new Message();
                msg.what = SDK_PAY_FLAG;
                msg.obj = result;
                mHandler.sendMessage(msg);
            }
        };

        Thread payThread = new Thread(payRunnable);
        payThread.start();
    }

}
