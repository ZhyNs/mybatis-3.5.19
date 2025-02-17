script（脚本语言）
==================
在mybatis可以使用各种脚本语言来定义在mapper.xml文件中的动态sql，因此提供`scripting`包支持。

核心类：
- `org.apache.ibatis.scripting.LanguageDriver`
- `org.apache.ibatis.scripting.LanguageDriverRegistry`

Configuration类初始化时，会设置默认的LanguageDriver，即`XMLLanguageDriver`。

在解析mapper.xml的sql语句时LanguageDriver创建SqlSource，SqlSource创建BoundSql。  
调用入口：`org.apache.ibatis.builder.xml.XMLStatementBuilder.parseStatementNode()`