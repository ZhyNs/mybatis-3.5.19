datasource
=======

datasource是jdbc的javax.sql.DataSource接口。 

Mybatis内置三大数据源：jndi、pooled、unpooled，但实际开发中，一般不使用内置数据源。

和Spring配合使用时，Spring会根据相关sql配置提供DataSource。