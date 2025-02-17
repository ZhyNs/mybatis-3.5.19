mapping（映射）
=======================
BoundSql
---------------
传入数据库的sql映射

BoundSql是SqlSource类创建的，SqlSource是在解析mapper.xml的sql语句时LanguageDriver创建。

BoundSql包含有占位符?的sql语句、固定顺序的参数列表，也包括一些动态语言的参数（比如loop和bind）。


SqlSource
------------------
创建BoundSql的接口，将具体的创建过程和数据库的sql映射解耦。

ParameterMapping/ParameterMap
--------------
参数类型映射及集合

ParameterMapping是在解析Mybatis标签时构建的，调用入口有：
- `org.apache.ibatis.builder.xml.XMLMapperBuilder.parameterMapElement()`
- `org.apache.ibatis.builder.SqlSourceBuilder.ParameterMappingTokenHandler.handleToken()` 

