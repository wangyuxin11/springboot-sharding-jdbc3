# springboot-sharding-jdbc3


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
