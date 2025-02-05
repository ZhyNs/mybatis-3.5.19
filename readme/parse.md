parsing（解析）
=========
解析模块作为mybatis的底层模块。主要提供两个功能：xml解析、sql语句解析

xml解析
---------------
核心类为：`XPathParser`、`XNode`
XPathParser底层使用jdk自带的XPath包，将xml语言转化为基于XNode的Mybatis语言。

sql语句解析
----------------
核心类：`GenericTokenParser`  
调用入口：org.apache.ibatis.builder.SqlSourceBuilder  
主要作用：处理mapper的sql语句中不符合sql标准的部分
