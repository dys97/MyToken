# MyToken

### 开启顺序
1. config
2. eureka
3. zipkin
4. admin
5. zuul

### 项目布局

``` lua
parent -- 父项目
├── common -- 项目所共有的部分
├── common-service -- 服务提供者所共有的部分
├── common-api -- 服务消费者所共有的部分
├── config-respo -- 云配置文件仓库(仅dev阶段)
├── service-admin -- 管理员服务提供者
├── service-sso -- 单点登录服务提供者
├── service-redis -- 数据缓存服务提供者
├── service-posts -- 文章服务提供者
├── service-upload -- 分布式文件上传服务提供者
├── api-admin -- 管理员服务消费者
└── api-posts -- 文章服务消费者
```
