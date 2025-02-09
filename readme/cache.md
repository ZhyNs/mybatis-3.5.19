cache（缓存机制）
=================
Mybatis缓存官网地址：https://mybatis.org/mybatis-3/sqlmap-xml.html#cache

MyBatis 提供了两级缓存机制，用于提高数据库查询的性能。这两级缓存分别是 一级缓存（Local Cache） 和 二级缓存（Global Cache）。

一级缓存（本地缓存）
--------------
本地缓存的入口在`org.apache.ibatis.executor.BaseExecutor#localCache`。

一级缓存会默认开启，创建执行器的构造函数时会自动创建一个本地缓存。

二级缓存
------------------
二级缓存支持跨sqlSession共享缓存，需要手动开启，具体配置可参考官网介绍。

支持缓存策略：
- lru
- fifo
- soft
- weak
- 自定义

二级缓存的入口：`MapperAnnotationBuilder#parseCache()`和`XMLMapperBuilder#cacheElement(XNode context)`  
共同底层方法：`org.apache.ibatis.builder.MapperBuilderAssistant.useNewCache`