package com.stt.common_jdkProxy;

import sun.misc.ProxyGenerator;

import java.io.FileOutputStream;
import java.lang.reflect.Proxy;



/**
 * @program: Java_Learn
 * @description: 测试类
 * @author: shaotongtong
 * @create: 2019-08-13 13:34
 **/

public class ProxyTest {

    public static void main(String[] args) {
        Person person = new Student();
        ProxyHandler proxyHandler = new ProxyHandler(person);
        Person proxyPerson  = (Person)Proxy.newProxyInstance(person.getClass().getClassLoader(),new Class[]{Person.class},proxyHandler);
        System.out.println(proxyPerson.getClass().getName());
        proxyPerson.doJob();
        createProxyClassFile(Person.class);
    }

    /**
     * 输出类文件
     * @param clazz
     */
    private static void createProxyClassFile(Class clazz){
        byte[] bytes = ProxyGenerator.generateProxyClass("$Proxy0",new Class[]{clazz});
        try{
            FileOutputStream fileOutputStream = new FileOutputStream("$Proxy0.class");
            fileOutputStream.write(bytes);
            fileOutputStream.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
