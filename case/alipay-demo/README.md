#### 第1集 常用的第三方支付和聚合支付平台介绍

**简介：介绍常用的第三方支付和聚合支付**

* 什么是第三方支付

    * 第三方支付是指具备一定实力和信誉保障的独立机构，采用与各大银行签约的方式，通过与银行支付结算系统接口对接而促成交易双方进行交易的网络支付模式
    * 通俗的例子：
        * 支付宝，微信支付，百度钱包，PayPal（主要是欧美国家）
        * 拉卡拉(中国最大线下便民金融服务提供商)
    * 优点
        * 支付平台降低了政府、企业、事业单位直连银行的成本，满足了企业专注发展在线业务的收付要求。
        * 使用方便。对支付者而言，他所面对的是友好的界面，不必考虑背后复杂的技术操作过程

    * 缺点
        * 风险问题，在电子支付流程中，资金都会在第三方支付服务商处滞留即出现所谓的资金沉淀，如缺乏有效的流动性管理，则可能存在资金安全和支付的风险
        * 电子支付经营资格的认知、保护和发展问题

* 什么是聚合支付也叫第四方支付
    * 聚合支付是相对之前的第三方支付而言的，作为对第三方支付平台服务的拓展，第三方支付是介于银行和商户之间的，而聚合支付是介于第三方支付和商户之间
    * 出现的场景
        * 一堆第三方支付出现，并通过大量的钱补贴线上商家使用它们的支付，导致商户收银台堆满各种，POS机器，扫码设备，商户还需要去各家支付公司申请账号，结算等
        * 聚合支付产品，其实聚合的是一种支付能力（支付宝支付、微信支付、百度钱包、ApplePay……），将这些收款能力聚合在一起，统一打包提供给电商网站或一些线下商家
    * 解决的问题
        * 聚合支付公司提供的二维码，支付多种方式支付，不再是一种，各个公司的竞争，就是支付渠道和方式的支持

#### 第2集 蚂蚁金服开放平台介绍和支付宝支付应用申请

**简介：蚂蚁金服开放平台介绍和支付宝申请**

* 蚂蚁金服开放平台
    * 地址：https://openhome.alipay.com/docCenter/docCenter.htm
    * 介绍：https://opendocs.alipay.com/open/200/105304
    * 支付宝扫码登录即可

* 网页移动应用开发指南
    * 地址：https://opendocs.alipay.com/open/200
    * 申请应用：https://openhome.alipay.com/platform/developerIndex.htm
    * 核心是获取APPID

#### 第3集 支付宝沙箱环境介绍和应用权限申请

**简介：支付宝沙箱环境介绍和应用权限申请**

* 支付宝沙箱环境介绍

```
蚂蚁沙箱环境 (Beta) 是协助开发者进行接口功能开发及主要功能联调的辅助环境

 在开发者应用上线审核前，开发者可以根据自身需求，先在沙箱环境中了解、组合和调试各种开放接口，进行开发调试工作，从而帮助开发者在应用上线审核完成后，能更快速、更顺利的完成线上调试和验收
```

* 文档地址：https://opendocs.alipay.com/open/200/105311
* 沙箱地址：https://openhome.alipay.com/platform/appDaily.htm?tab=info

* 支付接入：有时不稳定，或者一直报错等等，一般就是支付宝沙箱环境问题

```
Beta 测试阶段每周日中午 12 点至每周一中午 12 点为维护时间，在此时间内沙箱环境部分功能可能不可用，敬请谅解。
```

* APPID: 2016092000555936
* 沙箱支付宝网关：https://openapi.alipaydev.com/gateway.do

* 买家信息

```
买家账号mqvsso5323@sandbox.com
登录密码111111
支付密码111111
用户名称沙箱环境
证件类型身份证(IDENTITY_CARD)
证件号码285405199408130839
```

* 商家信息

```
商家账号bqnism8671@sandbox.com
商户UID2088102176491744
登录密码111111
```

#### 第5集 应用对接支付宝里面的非对称加密流程梳理

**简介：支付宝开里面的非对称加密通讯流程梳理**

* 应用对接支付宝加密流程

![image-20210304130526547](http://assets.processon.com/chart_image/60e2b7e2f346fb04d2da71bc.png?_=1625470984526)



* 手机网站支付文档地址:

    *  https://opendocs.alipay.com/apis/api_1/alipay.trade.wap.pay?scene=API002020081300013628
    *  参数介绍
    *  流程介绍

* 项目依赖包添加和样例代码

    * https://opendocs.alipay.com/open/54/cyz7do

    * ```
    <!-- https://mvnrepository.com/artifact/com.alipay.sdk/alipay-sdk-java -->
    <dependency>
        <groupId>com.alipay.sdk</groupId>
        <artifactId>alipay-sdk-java</artifactId>
        <version>4.10.218.ALL</version>
    </dependency>
    
    ```

#### 第7集 手机网站支付宝支付样例代码+单例设计模式应用

**简介：手机网站支付宝支付样例代码编写测试**

* 编写样例代码
* 测试参数配置使用

```
      //商户订单号,64个字符以内、可包含字母、数字、下划线；需保证在商户端不重复
        String no = UUID.randomUUID().toString();
        log.info("订单号:{}",no);
        content.put("out_trade_no", no);
        content.put("product_code", "FAST_INSTANT_TRADE_PAY");
        //订单总金额，单位为元，精确到小数点后两位
        content.put("total_amount", String.valueOf("111.99"));
        //商品标题/交易标题/订单标题/订单关键字等。 注意：不可使用特殊字符，如 /，=，&amp; 等。
        content.put("subject", "杯子");
        //商品描述，可空
        content.put("body", "好的杯子");
        // 该笔订单允许的最晚付款时间，逾期将关闭交易。取值范围：1m～15d。m-分钟，h-小时，d-天，1c-当天（1c-当天的情况下，无论交易何时创建，都在0点关闭）。 该参数数值不接受小数点， 如 1.5h，可转换为 90m。
        content.put("timeout_express", "5m");

```



  
    









