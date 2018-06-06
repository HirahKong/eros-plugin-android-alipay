# eros-plugin-android-alipay

> Eros Android AliPay Plugin
>
> Eros Android端 支付宝支付插件

#### 使用示例

android plugin 提供两种调用形式

- 服务端远程加签 (正式接入时使用)，签名生成规则参照：[使用应用私钥生成请求签名](https://docs.open.alipay.com/291/105974) 

  ```js
   var bmAliPay = weex.requireModule('bmAliPay');
  
  bmAliPay.pay({
          authInfo: '****'//服务器签名后的订单数据
      }, function (resData) {
          console.log("支付返回数据："+JSON.stringify(resData));
      })       
  ```

  

- 客户端本地加签 (测试接入时使用)

  > 真实App里，privateKey等数据严禁放在客户端，加签过程务必要放在服务端完成；**防止商户私密数据泄露，造成不必要的资金损失，及面临各种安全风险；**

  ```js
   var bmAliPay = weex.requireModule('bmAliPay');
  
  bmAliPay.pay({
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
          console.log("支付返回数据："+JSON.stringify(resData));
      })       
  ```

#### 返回值 

> 具体见：[App支付同步通知参数说明](https://docs.open.alipay.com/204/105302) 

##### resData.resCode 结果码含义

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

##### resData.status 返回结果参数

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

 