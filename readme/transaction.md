transaction
==================
`org.apache.ibatis.transaction.Transaction`是Mybatis对jdb的connection的包装类。

数据库虽然有事务的概念，但jdbc本身没有对事务抽象封装。Mybatis将Connection包装成事务，提供常用的sql命令、事务管理、生命周期管理等方法。
