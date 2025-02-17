configuration（Mybatis配置类，核心）
=====================

调用入口：org.apache.ibatis.session.SqlSessionFactoryBuilder.build()  
实际被调用位置：org.apache.ibatis.builder.xml.XMLConfigBuilder.parseConfiguration()

创建对象方法：
- newExecutor()：创建sql执行器
- newParameterHandler()：创建参数处理器
- newResultSetHandler()：创建结果集处理器
- newStatementHandler()：创建sql语句处理器

StatementHandler和ResultSetHandler是StatementHandler的一部分，分别用来处理参数和返回结果集。  

为什么要在Configuration中创建？  
猜测和配置拦截器插件有关。

解析方法：
- parsePendingStatements()：解析所有mapper.xml文件的sql语句映射
- 