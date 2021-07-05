**简介：订单微服务-避免重复下单tokne令牌机制处理**

* 问题
    * 前端下单按钮重复点击导致订单创建多次
    * 前端有限制，后端也需要有限制
    * 任何提交表单的时候，都可以采用token令牌机制避免重复点击

* token令牌机制开发
    * 下单前先获取令牌-存储redis
    * 下单时一并把token提交并检验和删除-lua脚本

```
String script = "if redis.call('get',KEYS[1]) == ARGV[1] then return redis.call('del',KEYS[1]) else return 0 end";
```

