interceptor（拦截器）
================
责任链模式：
- InterceptorChain（洋葱模型，一层包一层）
```
for(Interceptor interceptor : list) { 
    proxy = interceptor.plugin(proxy) = new Proxy(inteceptor); 
}
```
- Interceptor
- Plugin：接口（MethodHandler）、字段（interceptor）、工厂方法（`plugin#wrap(target, interceptor, signatureMap)`）

调用场景：  
Configuration对象中四个创建方法：newParameterHandler、newResultSetHandler、newStatementHandler、newExecutor。这些方法体内都通过interceptorChain.pluginAll(target)包装一层后再返回。
