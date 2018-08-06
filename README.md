# eros-plugin-android-alipay

> Eros Android AliPay Plugin
>
> Eros Android端 支付宝支付插件

[![](https://jitpack.io/v/HirahKong/eros-plugin-android-alipay.svg)](https://jitpack.io/#HirahKong/eros-plugin-android-alipay)

#### 依赖

* 库依赖方式
```java
dependencies {
       implementation 'com.github.HirahKong:eros-plugin-android-alipay:1.0.1'
}
```
* 源码依赖方式<br/>

1、进入Android目录工程目录`/platforms/android/WeexFrameworkWrapper/` 目录下 clone 对应的插件。
```java
       git clone https://github.com/bmfe/eros-plugin-android-alipay.git 'erospluginalipay'
```

2、打开Android目录工程目录`/platforms/android/WeexFrameworkWrapper/`,编辑`settings.gradle`,添加引入,在`settings.gradle` 中 添加如下代码。

```java
//这里只需要在最后添加 ,  ':erospluginalipay'
include ':app',':sdk',':nexus', ':wxframework',':erospluginalipay'

// 支付宝支付
project(':erospluginalipay').projectDir = new File(settingsDir,'/erospluginalipay/library-alipay')
```

3、打开Android目录工程目录`/platforms/android/WeexFrameworkWrapper/app`,编辑app目录下`build.gradle `文件 `dependencies` 下添对应 插件引用。

```java
dependencies {
		....
       //支付宝支付
       compile project(':erospluginalipay')
	}
```

4、修改`/platforms/android/WeexFrameworkWrapper/erospluginalipay/library-alipay`目录下`build.gradle` 文件将`implementation 'com.github.bmfe.eros-nexus:nexus:1.0.3'` 修改成 `implementation project(':nexus')`

```java
dependencies {
    implementation fileTree(include: ['*.jar'], dir: 'libs')
    implementation 'com.android.support:appcompat-v7:25.3.1'
    testImplementation 'junit:junit:4.12'
    //implementation 'com.github.bmfe.eros-nexus:nexus:1.0.3'
    implementation project(':nexus')
    //支付宝依赖包
    implementation files('libs/alipaySdk-20180403.jar')
}
```

#### 使用示例

> 服务端远程加签 ，签名生成规则参照：[使用应用私钥生成请求签名](https://docs.open.alipay.com/291/105974) 

```js
   var bmAliPay = weex.requireModule('bmAliPay');
  
  bmAliPay.pay({
          authInfo: '****'//服务器签名后的订单数据
      }, function (resData) {
          console.log("支付返回数据："+JSON.stringify(resData));
      })       
```

#### 返回值

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

 
