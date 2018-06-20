package com.benmu.erospluginalipay.model;

/**
 * Created by haorui on 2018/6/6.
 * Des:
 */
public class AliPayModel {
    private String appId;//支付宝分配的APP_ID

    private String sign;//签名后数据，建议加签过程放在服务端进行。 注：privateKey和sign二选一即可，sign优先级更高
    private String privateKey;//RSA 或 RSA2 私钥字符串，不为空则客户端进行加签。

    private Boolean isRsa2;//是否是 RSA2 加密。如服务端进行加签，需要询问服务端加签方式。默认为false
    private String outTradeNo;//订单ID (必须唯一)。
    private String money;//价格
    private String name;//商品名称
    private String detail;//商品描述详情 (用于显示在 支付宝 的交易记录里)
    private String timestamp;//发送请求的时间，格式"yyyy-MM-dd HH:mm:ss"
    private Boolean isSandbox;//是否为沙箱模式，调试时使用

    private String authInfo;//服务器签名后拼接的订单数据

    public AliPayModel() {
    }

    public AliPayModel(String outTradeNo, String money, String name, String detail) {
        this.outTradeNo = outTradeNo;
        this.money = money;
        this.name = name;
        this.detail = detail;
    }

    public AliPayModel(String appid, Boolean isRsa2, String privateKey, String outTradeNo, String money, String name, String detail, String timestamp) {
        this.appId = appid;
        this.isRsa2 = isRsa2;
        this.privateKey = privateKey;
        this.outTradeNo = outTradeNo;
        this.money = money;
        this.name = name;
        this.detail = detail;
        this.timestamp = timestamp;
    }

    public String getOutTradeNo() {
        return outTradeNo;
    }

    public void setOutTradeNo(String outTradeNo) {
        this.outTradeNo = outTradeNo;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public Boolean getRsa2() {
        return isRsa2;
    }

    public void setRsa2(Boolean rsa2) {
        isRsa2 = rsa2;
    }

    public String getPrivateKey() {
        return privateKey;
    }

    public void setPrivateKey(String privateKey) {
        this.privateKey = privateKey;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public Boolean getSandbox() {
        return isSandbox;
    }

    public void setSandbox(Boolean sandbox) {
        isSandbox = sandbox;
    }

    public String getAuthInfo() {
        return authInfo;
    }

    public void setAuthInfo(String authInfo) {
        this.authInfo = authInfo;
    }
}
