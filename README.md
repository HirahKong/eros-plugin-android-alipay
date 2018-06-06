# eros-plugin-android-alipay
> Eros Android AliPay Plugin

####使用说明
```
 var bmAliPay = weex.requireModule('bmAliPay');

                bmAliPay.pay({
                    // 支付宝支付所需必要参数
                    appId: '2016091***510533',//支付宝分配的APP_ID
                    isRsa2: true,//是否是 RSA2 加密。如服务端进行加签，需要询问服务端加签方式。
                    //sign:"",//签名后数据，建议加签过程放在服务端进行。 注：privateKey和sign二选一即可，sign优先级更高
                    //RSA 或 RSA2 私钥字符串，不为空则客户端进行加签。
                    privateKey: '***',
                    outTradeNo: '123332121243',//订单ID (必须唯一)。
                    money: '10',//金额
                    name: 'test',//商品名称
                    detail: 'test detail',//商品描述详情 (用于显示在 支付宝 的交易记录里)
                    timestamp: '2018-06-05 17:51:21',//发送请求的时间，格式"yyyy-MM-dd HH:mm:ss"
                    isSandbox: true,//是否为沙箱调试模式
                }, function (resData) {
                    /**
                     * 支付状态
                     * 9000	订单支付成功
                     * 8000	正在处理中，支付结果未知（有可能已经支付成功），请查询商户订单列表中订单的支付状态
                     * 4000	订单支付失败
                     * 5000	重复请求
                     * 6001	用户中途取消
                     * 6002	网络连接出错
                     * 6004	支付结果未知（有可能已经支付成功），请查询商户订单列表中订单的支付状态
                     * 其它	其它支付错误
                     *
                     * resData.resCode;//结果码(类型为字符串)
                     * resData.status;//处理结果(类型为json结构字符串)
                     * resData.msg;//描述信息
                     */
                    console.log("支付返回数据："+JSON.stringify(resData));
                })
```