package com.stt.ownSpring.beanDemo;

import com.stt.ownSpring.component.SimpleIoc;

public class SimpleIOCTest {

    public static void main(String[] args) throws Exception{
        String location = SimpleIOCTest.class.getClassLoader().getResource("ioc.xml").getFile();
        SimpleIoc bf = new SimpleIoc(location);
        Wheel wheel = (Wheel) bf.getBean("wheel");
        System.out.println(wheel);
        Car car = (Car) bf.getBean("car");
        System.out.println(car);
    }
}
