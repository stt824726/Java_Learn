### 1. 使用步骤
1. 定义业务接口Interface，及实现逻辑target(普通业务逻辑)
2. 根据接口，自定义业务处理类handler，集成InvocationHandler接口。(动态增加逻辑)
3. 调用Proxy类在内存中生成代理类，并用classLoader加载进JVM

### 2. 实际流程
1. Proxy类通过传递的参数Interface和InvocationHandler,生成代理类$Proxy0
2. Proxy类通过传入的参数,来加载生成的代理类$Proxy0的字节码文件。
![动态代理流程图](https://github.com/stt824726/Java_Learn/blob/master/image/src/resources/%E5%8A%A8%E6%80%81%E4%BB%A3%E7%90%86%E5%9B%BE%E7%89%87.png)
##$