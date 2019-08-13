# 动态代理
## 实现方案
- jdk 动态代理 
- cglib 动态代理
- javassist 动态代理

## jdk动态代理
> JDK动态代理是利用反射机制在运行时创建代理类的。

> 核心是InvocationHandler。每一个代理的实例都会有一个关联的调用处理程序(InvocationHandler)。对待代理实例进行调用时，将对方法的调用进行编码并指派到它的调用处理器(InvocationHandler)的invoke方法。所以对代理对象实例方法的调用都是通过InvocationHandler中的invoke方法来完成的，而invoke方法会根据传入的代理对象、方法名称以及参数决定调用代理的哪个方法。
                           
### 1. 使用步骤
1. 定义业务接口Interface，及实现逻辑target(普通业务逻辑)
2. 根据接口，自定义业务处理类handler，集成InvocationHandler接口。(动态增加逻辑)
3. 调用Proxy类在内存中生成代理类，并用classLoader加载进JVM

### 2. 实际流程
1. Proxy类通过传递的参数Interface和InvocationHandler,生成代理类$Proxy0
> Class<?> cl = getProxyClass0(loader, intfs);
2. Proxy类通过传入的参数,来加载生成的代理类$Proxy0的字节码文件。
> final Constructor<?> cons = cl.getConstructor(constructorParams);
> cons.newInstance(new Object[]{h});
3. 当代理对象生成时，最后由InvocationHandler的invoke()方法调用目标方法: 

流程图参照如下

![动态代理流程图](https://github.com/stt824726/Java_Learn/blob/master/image/src/resources/%E5%8A%A8%E6%80%81%E4%BB%A3%E7%90%86%E5%9B%BE%E7%89%87.png)

##  Javassist 