plugin（插件机制）
================
拦截器的添加入口：`org.apache.ibatis.session.Configuration.addInterceptor()`

拦截器的调用入口有多个：
- `org.apache.ibatis.session.Configuration.newExecutor()`
- `org.apache.ibatis.session.Configuration.newParameterHandler()`
- `org.apache.ibatis.session.Configuration.newResultSetHandler()`
- `org.apache.ibatis.session.Configuration.newStatementHandler()`

通过拦截sql执行的各个环节，拦截器插件基本可实现在sql执行过程中想处理的工作。

基本原理：通过动态代理的方式，将拦截器的业务逻辑封装进一个新生成的代理类中。如果代理类特别多，可能会影响性能。

备注：拦截器在方法中通过目标对象的类型，判断是否它的作用范围。如果不是，直接跳过即可。