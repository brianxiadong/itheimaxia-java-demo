#### 第1集  接口压测和常用压力测试工具对比

**简介：目前用的常用测试工具对比**

- LoadRunner
    - 性能稳定，压测结果及细粒度大，可以自定义脚本进行压测，但是太过于重大，功能比较繁多

- Apache AB(单接口压测最方便)
    - 模拟多线程并发请求,ab命令对发出负载的计算机要求很低，既不会占用很多CPU，也不会占用太多的内存，但却会给目标服务器造成巨大的负载, 简单DDOS攻击等

- Webbench
    - webbench首先fork出多个子进程，每个子进程都循环做web访问测试。子进程把访问的结果通过pipe告诉父进程，父进程做最终的统计结果。

- Jmeter

    - 开源免费，功能强大，在互联网公司普遍使用
    - 压测不同的协议和应用
        - 1) Web - HTTP, HTTPS (Java, NodeJS, PHP, ASP.NET, …)
        - 2) SOAP / REST Webservices
        - 3) FTP
        - 4) Database via JDBC
        - 5) LDAP 轻量目录访问协议
        - 6) Message-oriented middleware (MOM) via JMS
        - 7) Mail - SMTP(S), POP3(S) and IMAP(S)
        - 8) TCP等等
    - 使用场景及优点
        - 1）功能测试
        - 2）压力测试
        - 3）分布式压力测试
        - 4）纯java开发
        - 5）上手容易，高性能
        - 4）提供测试数据分析
        - 5）各种报表数据图形展示



*  压测工具本地快速安装Jmeter5.x
    * 需要安装JDK8 以上
    * 建议安装JDK环境，虽然JRE也可以，但是压测https需要JDK里面的 keytool工具
    * 快速下载 https://jmeter.apache.org/download_jmeter.cgi
    * 文档地址：http://jmeter.apache.org/usermanual/get-started.html

















#### 第2集  Jmeter5.x目录文件讲解+汉化操作

简介:讲解jmeter解压文件里面的各个目录，文件等

- 目录

  ```
  bin:核心可执行文件，包含配置
          jmeter.bat: windows启动文件(window系统一定要配置显示文件拓展名)
          jmeter: mac或者linux启动文件
          jmeter-server：mac或者Liunx分布式压测使用的启动文件
          jmeter-server.bat：window分布式压测使用的启动文件
          jmeter.properties: 核心配置文件   
  extras：插件拓展的包
  
  lib:核心的依赖包
  ```

- Jmeter语言版本中英文切换

    - 控制台修改 menu -> options -> choose language

- 配置文件修改

    - bin目录 -> jmeter.properties
    - 默认 #language=en
    - 改为 language=zh_CN



















####  第3集 Jmeter5.X基础功能组件介绍+线程组和Sampler

**简介：讲解Jmeter里面GUI菜单栏主要组件**

- 添加->threads->线程组（控制总体并发）

  ```
  线程数：虚拟用户数。一个虚拟用户占用一个进程或线程
  
  准备时长（Ramp-Up Period(in seconds)）：全部线程启动的时长，比如100个线程，20秒，则表示20秒内 100个线程都要启动完成，每秒启动5个线程
  
  循环次数：每个线程发送的次数，假如值为5，100个线程，则会发送500次请求，可以勾选永远循环
  ```



- 线程组->添加-> Sampler(采样器) -> Http （一个线程组下面可以增加几个Sampler）

  ```
  名称：采样器名称
  注释：对这个采样器的描述
  web服务器：
    默认协议是http
    默认端口是80
    服务器名称或IP ：请求的目标服务器名称或IP地址
  
  路径：服务器URL
  ```



- 查看测试结果

  ```
  线程组->添加->监听器->察看结果树
  线程组->添加->监听器->聚合报告
  ```



















#### 第4集 Jmeter5.x实战之优惠券列表接口压测+聚合报告分析

**简介：优惠券列表接口压测+结果聚合报告**

- 优惠券列表接口压测

- 新增聚合报告：线程组->添加->监听器->聚合报告（Aggregate Report）

```
  lable: sampler的名称
  Samples: 一共发出去多少请求,例如10个用户，循环10次，则是 100
  Average: 平均响应时间
  Median: 中位数，也就是 50％ 用户的响应时间


  90% Line : 90％ 用户的响应不会超过该时间 （90% of the samples took no more than this time.     The remaining samples at least as long as this）
  95% Line : 95％ 用户的响应不会超过该时间
  99% Line : 99％ 用户的响应不会超过该时间
  min : 最小响应时间
  max : 最大响应时间

  

  Error%：错误的请求的数量/请求的总数
  Throughput： 吞吐量——默认情况下表示每秒完成的请求数（Request per Second) 可类比为qps、tps
  KB/Sec: 每秒接收数据量
```

















####  第5集 优惠券领劵接口-Jmeter压测扣超发优惠券问题暴露

**简介：Jmeter压测领劵接口-超发优惠券问题暴露**

* 新建接口压测计划

* 压测领劵接口

* 完成xml编写

  ```
  <!--扣减库存-->
      <update id="reduceStock">
          update coupon set stock=stock-1 where id = #{couponId}
      </update>
  ```

* 问题

    * 扣减存储为负数，超发优惠券
    * 给公司造成资损

























####  第6集 高并发下怎样优雅的保证扣减库存数据的正确性

**简介：高并发下扣减库存的常见解决方案介绍**


* 我们谈下怎么用锁来保证数据正确，防止超发导致库存是负数，你能下想几种方式

  先看下面的是精简版的时序图

  ![image-20210206232900188](../../../Library/Application Support/typora-user-images/image-20210206232900188.png)

    * 同步代码块synchronized ,lock

  ```
  public synchronized void reduceCouponStock(long couponId ,Integer num) {
  //业务逻辑
  }
  
  问题：synchronized 作用范围是单个jvm实例, 如果做了集群分布式等，就失效了,且单机JVM加锁后就是串行等待问题
  ```



* 分布式锁 zookeeper,redis  (后续会讲到分布式锁的知识)

  ```
  可以解决问题
  
  问题：过于笨重，性能有所下降
  ```



* 直接数据库更新扣减

  ```
  update coupon set stock=stock - #{num} where id = #{couponId} and stock>0
  //测试如果num大于已有库存，则会变负数
  update coupon set stock=stock - #{num} where id = #{couponId} and （stock - #{num})>=0
  或者
  update coupon set stock=stock - #{num} where id = #{couponId} and stock >= #{num} 
  //修复了负数问题
  
  ```



* 如果扣减最多1个，则直接使用这种就行

  ```
  update coupon set stock=stock-1 where id = #{couponId} and stock>0 
  
  延伸
  update coupon set stock=stock-1 where id = #{couponId} and stock = #{oldStock}
  问题：扣减库存，如果别人补充库存，就存在ABA问题，看业务是否有这个限制，大课采用上面那种
  比如
  C线程查出来是10个
  A线程扣减1个，剩9个
  B线程更新了库存，变回10个
  C更新的时候发现还是10个，则更新成功， 所以避免这个问题，要求不管谁修改了库存，一定要加个version递增版本号
  
  update coupon set stock=stock-1,version=version+1 where id = #{couponId} and stock>0 and versoin=#{oldVersion}
  ```


* 代码编写

```
<!--扣减库存，如果别人补充库存，则可能存在ABA问题-->
    <update id="reduceStock">
        update coupon set stock=stock-1 where id = #{couponId} and stock>0
    </update>
```



​















#### 第7集 大厂面试热身赛-天猫超市-二面面试题-P7技术专家岗

**简介：大厂面试题，高并发库存扣减超卖问题解决，多种sql适合场景**

* 题目：高并发库存扣减超卖问题，很多人加了乐观锁版本号去解决，那下面三种有什么区别，分别适合哪些场景使用

```
1）update product set stock=stock-1 where id = 1 and stock>0

2）update product set stock=stock-1 where stock=#{原先查询的库存}  and id = 1 and stock>0

3）update product set stock=stock-1,versioin = version+1 where  id = 1 and stock>0 and version=#{原先查询的版本号} 
```



* 答案 : 核心是解决超卖的问题，就是防止库存为负数

```
方案一：id是主键索引的前提下，如果每次只是减少1个库存，则可以采用上面的方式，只做数据安全
校验，可以有效减库存，性能更高，避免大量无用sql，只要有库存就也可以操作成功.
场景：高并发场景下的取号器，优惠券发放扣减库存等


方案二：使用业务自身的条件做为乐观锁，但是存在ABA问题，对比方案三的好处是不用增加version版本字段。如果只是扣减库存且不在意ABA问题时，则可以采用上面的方式，但业务性能相对方案一就差了点，因为库存变动后sql就会无效


方案三：增加版本号主要是为了解决ABA问题，数据读取后，更新前数据被别人篡改过，version只能做递增
场景：商品秒杀、优惠券方法，需要记录库存操作前后的业务


三个方案各有利弊，看业务场景而定
```















**<img src="./img/logo.png" alt="logo" style="zoom: 50%;" />   愿景："让编程不再难学，让技术与生活更加有趣"**

### 第十五章  原生分布式锁-Redisson分布式锁防止个人超领优惠券



#### 第1集 一行代码10个bug-单用户超领优惠券问题介绍

**简介：讲解单用户优惠券超领业务问题和效果演示**

* 什么单用户超领优惠券
    * 优惠券限制1人限制1张，有些人却领了2张
    * 优惠券限制1人限制2张，有些人却领了3或者4张

* 案例举例和问题来源

```
前面解决了，优惠券超发的问题，但是这个个人领取的时候，有张数限制，

有个生发洗发水100元，有个10元优惠券，每人限制领劵1张

老王，使用时间暂停思维来发现问题，并发领劵

A线程原先查询出来没有领劵，要再插入领劵记录前暂停
然后B线程原先查询出来也没有领劵，则插入领劵记录，然后A线程也插入领劵记录
老王就有了两个优惠券

问题来源核心：对资源的修改没有加锁，导致多个线程可以同时操作，从而导致数据不正确

解决问题：分布式锁 或者 细粒度分布式锁
```


















#### 第2集 分布式核心技术-关于高并发下分布式锁你知道多少？

**简介：分布式锁核心知识介绍和注意事项**

* 避免单人超领劵
    * 加锁
        * 本地锁：synchronize、lock等，锁在当前进程内，集群部署下依旧存在问题
        * 分布式锁：redis、zookeeper等实现，虽然还是锁，但是多个进程共用的锁标记，可以用Redis、Zookeeper、Mysql等都可以

![image-20210208224458995](img/image-20210208224458995.png)

* 设计分布式锁应该考虑的东西
    * 排他性
        *  在分布式应用集群中，同一个方法在同一时间只能被一台机器上的一个线程执行
    * 容错性
        * 分布式锁一定能得到释放，比如客户端奔溃或者网络中断
    * 满足可重入、高性能、高可用
    * 注意分布式锁的开销、锁粒度
    

