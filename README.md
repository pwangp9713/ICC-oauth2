# icc-springboot2

%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%




１首先启动注册中心


２　启动oas这个项目, oas就是oauth2的authorization server，　端口９８９８


3  启动givemetoken这个项目, givemetoken就好比oauth2的resource server，　端口９９９８


4  启动ICC-geteway, 网关，　端口　８０８４


５　启动testzuul这个项目, testzuul就好比我们要保护的微服务，　里面都是些简单的接口，　返回helloworld这种的，　端口　无所谓


６　http://localhost:9898/oauth/authorize?client_id=test&response_type=code&redirect_uri=http://localhost:9998/givemetoken　用户名　user 密码　password


7  先测试一下不经过gateway的，　http://localhost:9998/plzseparate?access_token=219d8aa1-9c32-4286-9c02-ad3bf7c3f454
应该就返回文字　＂plz separate＂


８　再测试一下经过gateway的　http://localhost:8084/plzseparate?access_token=219d8aa1-9c32-4286-9c02-ad3bf7c3f454
应该就返回文字　＂plz separate＂



９　再测试一下经过gateway请求我们受保护testzuul微服务　
http://localhost:8084/hellozuul　注：如果不加access_token会报错，　因为我还没做givemetoken返回ICC-geteway　４０１的处理
http://localhost:8084/hellozuul?access_token=219d8aa1-9c32-4286-9c02-ad3bf7c3f454
应该就返回文字　＂helloZuul!!＂




![输入图片说明](https://images.gitee.com/uploads/images/2019/0220/151658_ecc88394_4785688.png "gfilter.png")





%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%



#### 介绍
基于springcloud.Finchley.SR2、springboot2搭建，eureka做为服务发现与注册，微服务之间使用feign调用，
hystrix做熔断处理，统一对外入口使用springcloud-gateway，数据层使用tk.mybatis、pagehelper，连接池使用druid，多数据源事务使用Atomikos，
缓存使用radis，队列服务使用rabbitmq，日志使用slf4j，打印输出使用logstash发送到ELK，webservice使用axis和cxf，API管理页面使用swagger2
#### 软件架构
1. ICC-comm-----------------公共组件root
2. ICC-geteWay--------------统一对外入口服务（未完善，差限流，聚合swagger2，权限控制）
3. ICC-Micro-Services-------微服务层（所有服务都这层）
4. ICC-registry-server------微服务注册中心
5. ICC-rabbitMQ-server------队列服务（未加）
6. docs---------------------文档
#### 安装使用说明

1. 找到上述1~5个包分别右击 run as maven project 会变成maven项目
2. 每个服务都可以单独执行，查看配置文件的端口
3. 找到com.sinosoft.main中的包 执行main方法，可启动服务并访问

#### 结构说明

1. ICC-comm 只放公共使用的包，不涉及业务的，其它服务用来做依赖使用
     > ICC-BaseDao是单数据源引用包，支持注解、加入了AOP事务支持，统一切入com.sinosoft.XXX.service.XXX
     
     > ICC-BaseDao-Atomikos是多数据源引用包，支持注解、加入了AOP事务支持，统一切入com.sinosoft.XXX.service.XXX
     
     > ICC-BaseService为微服务中业务层的基类包
     
     > ICC-parent为所有引用包的版本配置和公用的jar包引用
     
     > ICC-utils为公用工具类
     
     > mybatis-generator为生成通用mybatis的xml与dao文件的工具，参照docs中的文档配置使用
     
2. ICC-Micro-Services 放所有微服务
     > 所有的微务分为service与dao两个工程包，其中service包继承ICC-BaseService，
dao包可继承ICC-BaseDao或ICC-BaseDao-Atomikos，看业务是否需要分布式数据源。
service包需只放业务代码，所有对数据库操作和sql语句都放入dao包中，serivce包通过引用
dao的包进行数据库操作。

3. 多数据源示例为TEST-user-Atomikos，单数据源示例为TEST-user,服务之间的互调参考TEST-order，
现微服务之间使用Feign和FallbackFactory来实现熔断。微服务的接口都需要加上swagger2

#### 代码要求

1. 所有类 都加上 
 >private Logger logger = LoggerFactory.getLogger(getClass()); 
 
 >用来打印信息，使用 slf4j 包的。
2. 在所有使用if语句通过判断状态写分支时，都需要加上log打印状态值
3. 一个类中的方法控在30行之内
4. 每个方法都要写上简单注释
5. 对数据循环操作时，需要log打印消耗时间
6. 使用集合操作时，使用JDK8中的Stream流来操作
 

#### 码云特技

1. 使用 Readme\_XXX.md 来支持不同的语言，例如 Readme\_en.md, Readme\_zh.md
2. 码云官方博客 [blog.gitee.com](https://blog.gitee.com)
3. 你可以 [https://gitee.com/explore](https://gitee.com/explore) 这个地址来了解码云上的优秀开源项目
4. [GVP](https://gitee.com/gvp) 全称是码云最有价值开源项目，是码云综合评定出的优秀开源项目
5. 码云官方提供的使用手册 [https://gitee.com/help](https://gitee.com/help)
6. 码云封面人物是一档用来展示码云会员风采的栏目 [https://gitee.com/gitee-stars/](https://gitee.com/gitee-stars/)