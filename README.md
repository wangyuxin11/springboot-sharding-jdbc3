# springboot-sharding-jdbc3-dangdang  完成了分库分表操作。。。。。


# springboot-sharding-jdbc3  

# 项目源码地址： https://github.com/shardingjdbc/sharding-jdbc


# 常用的分库分表中间件 https://www.cnblogs.com/jshen/p/7682502.html
	# 简单易用的组件：
		当当sharding-jdbc
		蘑菇街TSharding
	强悍重量级的中间件：
		sharding
		TDDL Smart Client的方式（淘宝）
		Atlas(Qihoo 360)
		alibaba.cobar(是阿里巴巴（B2B）部门开发)
		MyCAT（基于阿里开源的Cobar产品而研发）
		Oceanus(58同城数据库中间件)
		OneProxy(支付宝首席架构师楼方鑫开发)
		vitess（谷歌开发的数据库中间件）


# Twitter的分布式自增ID算法Snowflake
在分布式系统中，需要生成全局UID的场合还是比较多的，twitter的snowflake解决了这种需求，实现也还是很简单的，除去配置信息，核心代码就是毫秒级时间41位 机器ID 10位 毫秒内序列12位。

* 10---0000000000 0000000000 0000000000 0000000000 0 --- 00000 ---00000 ---000000000000
在上面的字符串中，第一位为未使用（实际上也可作为long的符号位），接下来的41位为毫秒级时间，然后5位datacenter标识位，5位机器ID（并不算标识符，实际是为线程标识），然后12位该毫秒内的当前毫秒内的计数，加起来刚好64位，为一个Long型。

这样的好处是，整体上按照时间自增排序，并且整个分布式系统内不会产生ID碰撞（由datacenter和机器ID作区分），并且效率较高，经测试，snowflake每秒能够产生26万ID左右，完全满足需要。





# 9、分库数量
分库数量首先和单库能处理的记录数有关，一般来说，Mysql 单库超过5000万条记录，Oracle单库超过1亿条记录，DB压力就很大(当然处理能力和字段数量/访问模式/记录长度有进一步关系)。

在满足上述前提下，如果分库数量少，达不到分散存储和减轻DB性能压力的目的；如果分库的数量多，好处是每个库记录少，单库访问性能好，但对于跨多个库的访问，应用程序需要访问多个库，如果是并发模式，要消耗宝贵的线程资源；如果是串行模式，执行时间会急剧增加。

最后分库数量还直接影响硬件的投入，一般每个分库跑在单独物理机上，多一个库意味多一台设备。所以具体分多少个库，要综合评估，一般初次分库建议分4-8个库。



# 11、使用框架还是自主研发
目前市面上的分库分表中间件相对较多，其中基于代理方式的有MySQL Proxy和Amoeba，基于Hibernate框架的是Hibernate Shards，基于jdbc的有当当sharding-jdbc，基于mybatis的类似maven插件式的有蘑菇街的蘑菇街TSharding，通过重写spring的ibatis template类是Cobar Client，这些框架各有各的优势与短板，架构师可以在深入调研之后结合项目的实际情况进行选择，但是总的来说，我个人对于框架的选择是持谨慎态度的。一方面多数框架缺乏成功案例的验证，其成熟性与稳定性值得怀疑。另一方面，一些从成功商业产品开源出框架（如阿里和淘宝的一些开源项目）是否适合你的项目是需要架构师深入调研分析的。当然，最终的选择一定是基于项目特点、团队状况、技术门槛和学习成本等综合因素考量确定的。

