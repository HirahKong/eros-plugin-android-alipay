# eros-plugin-android-alipay

> Eros Android AliPay Plugin
>
> Eros Android端 支付宝支付插件

[![](https://jitpack.io/v/HirahKong/eros-plugin-android-alipay.svg)](https://jitpack.io/#HirahKong/eros-plugin-android-alipay)

### 依赖

```java
dependencies {
       implementation 'com.github.HirahKong:eros-plugin-android-alipay:1.0.1'
}
```

### 使用示例

> 服务端远程加签 ，签名生成规则参照：[使用应用私钥生成请求签名](https://docs.open.alipay.com/291/105974) 

```js
   var bmAliPay = weex.requireModule('bmAliPay');
  
  bmAliPay.pay({
          authInfo: '****',//服务器签名后的订单数据
	      isSandbox: true,//是否为沙箱调试模式
      }, function (resData) {
          console.log("支付返回数据："+JSON.stringify(resData));
      })       
```



###  支付调试

1. 下载支付宝沙箱版客户端 [下载](https://sandbox.alipaydev.com/user/downloadWallet.htm)

2. 获取沙箱账号，登录支付宝客户端 

   1. 进入支付宝[开发者中心](https://openhome.alipay.com/platform/appDaily.htm?tab=account) - 沙箱环境 - 沙箱账号
   2. 找到买家信息 - 登录支付宝客户端

   ![image-20180928113831602](https://ws3.sinaimg.cn/large/006tNc79gy1fvp49gn945j31jo19a44l.jpg)


3. 后台同学使用上传到支付宝的密钥生成签名后的数据

   > 调试模式下则使用沙箱应用配置信息即可
   >
   > ![image-20180928115454972](https://ws3.sinaimg.cn/large/006tNc79gy1fvp49ihd97j31fm0vs43n.jpg)

   1. 生成RSA密钥 [文档](https://docs.open.alipay.com/291/105971)
   2. 上传公钥 [文档](https://docs.open.alipay.com/291/105972/)
   3. 使用应用私钥生成请求签名 [文档](https://docs.open.alipay.com/291/105974/)

4. 调用支付宝支付接口时设置属性isSandbox为true![image-20180928114034792](https://ws2.sinaimg.cn/large/006tNc79gy1fvp49hk1xyj311u0bewgi.jpg)


### 返回值

> 具体见：[App支付同步通知参数说明](https://docs.open.alipay.com/204/105302) 

##### resData.status 结果码含义

| 返回码 | 含义                                                         |
| ------ | ------------------------------------------------------------ |
| 9000   | 订单支付成功                                                 |
| 8000   | 正在处理中，支付结果未知（有可能已经支付成功），请查询商户订单列表中订单的支付状态 |
| 4000   | 订单支付失败                                                 |
| 5000   | 重复请求                                                     |
| 6001   | 用户中途取消                                                 |
| 6002   | 网络连接出错                                                 |
| 6004   | 支付结果未知（有可能已经支付成功），请查询商户订单列表中订单的支付状态 |
| 其它   | 其它支付错误                                                 |

##### resData.data 返回结果参数

| 参数         | 类型   | 是否必填 | 最大长度 | 描述                                                         | 示例值                                               |
| ------------ | ------ | -------- | -------- | ------------------------------------------------------------ | ---------------------------------------------------- |
| out_trade_no | String | 是       | 64       | 商户网站唯一订单号                                           | 70501111111S001111119                                |
| trade_no     | String | 是       | 64       | 该交易在支付宝系统中的交易流水号。最长64位。                 | 2014112400001000340011111118                         |
| app_id       | String | 是       | 32       | 支付宝分配给开发者的应用Id。                                 | 2014072300007148                                     |
| total_amount | Price  | 是       | 9        | 该笔订单的资金总额，单位为RMB-Yuan。取值范围为[0.01,100000000.00]，精确到小数点后两位。 | 9.00                                                 |
| seller_id    | String | 是       | 16       | 收款支付宝账号对应的支付宝唯一用户号。以2088开头的纯16位数字 | 2088111111116894                                     |
| msg          | String | 是       | 16       | 处理结果的描述，信息来自于code返回结果的描述                 | success                                              |
| charset      | String | 是       | 16       | 编码格式                                                     | utf-8                                                |
| timestamp    | String | 是       | 32       | 时间                                                         | 2016-10-11 17:43:36                                  |
| code         | String | 是       | 16       | 结果码                                                       | [具体见](https://docs.open.alipay.com/common/105806) |

#### 

