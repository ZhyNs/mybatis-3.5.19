executor（执行器）
================
Mybatis真正执行sql语句的地方。提供sql查询、更新、事务处理等方法。

Executor的实现类：
- SimpleExecutor（默认）：`Configuration#newExecutor()`
- CacheExecutor（缓存）
- BatchExecutor（批量）
- ReuseExecutor

sql执行器的方法被调用入口是在`DefaultSqlSession`。比如selectList()/update()/commit()/rollback()/close()。

statement
-------------
StatementHandler是sql语句处理器，有以下实现类：
- SimpleStatementHandler：处理Statement语句
- PreparedStatementHandler：处理PreparedStatement语句
- CallableStatementHandler：处理存储过程语句
- RoutingStatementHandler（默认）：sql语句处理器路由类。除了根据sql语句类型，路由转发到对应的处理器，没有其他功能。
  - `Configuration#newStatementHandler()`

StatementHandler持有`ParameterHandler`和`ResultSetHandler`。

parameter
--------------------
ParameterHandler：负责设置PreparedStatement语句的参数。默认使用`DefaultParameterHandler`。

调用入口：`org.apache.ibatis.executor.statement.BaseStatementHandler.BaseStatementHandler()`


resultset
--------------------

调用入口：`org.apache.ibatis.executor.statement.BaseStatementHandler.BaseStatementHandler()`