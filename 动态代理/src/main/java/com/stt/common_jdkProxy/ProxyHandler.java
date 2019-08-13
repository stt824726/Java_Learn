package com.stt.common_jdkProxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @program: Java_Learn
 * @description: 业务处理类
 * @author: shaotongtong
 * @create: 2019-08-13 13:30
 **/
public class ProxyHandler implements InvocationHandler {

    private Person person;

    public ProxyHandler(Person person){
        this.person = person;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        before();
        method.invoke(person,null);
        after();
        return null;
    }

    public void before(){
        System.out.println("处理前");
    }

    public void after(){
        System.out.println("处理后");
    }
}
