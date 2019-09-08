package com.stt.ownSpring.myioc.bean;

import com.stt.ownSpring.myioc.context.BeanPostProcessor;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

public abstract class AbstractBeanFactory implements BeanFactory{

    private Map<String,BeanDefinition> beanDefinitionMap = new ConcurrentHashMap<>();

    private final List<String> beanDefinitionNames = new ArrayList<>();

    private List<BeanPostProcessor> beanPostProcessors = new CopyOnWriteArrayList<>();


    public  Object getBean(String name) throws Exception{
        BeanDefinition beanDefinition = this.beanDefinitionMap.get(name);
        if(beanDefinition == null){
            throw new IllegalArgumentException("No bean named " + name + " is defined");
        }
        Object bean = beanDefinition.getBean();
        if(bean == null){
            //加载，初始化类
            bean = doCreateBean(beanDefinition);
            //加载定制化
            bean = initializeBean(bean, name);
            beanDefinition.setBean(bean);
        }
        return bean;
    }

    protected Object initializeBean(Object bean, String name) throws Exception{
        for(BeanPostProcessor beanPostProcessor : beanPostProcessors){
            bean = beanPostProcessor.postProcessBeforeInitialization(bean, name);
            bean = beanPostProcessor.postProcessAfterInitialization(bean,name);
        }
        return bean;
    }


    protected Object doCreateBean(BeanDefinition beanDefinition)throws Exception{
        Object bean = createBeanInstance(beanDefinition);
        beanDefinition.setBean(bean);
        applyPropertyValues(bean,beanDefinition);
        return bean;
    }

    protected void applyPropertyValues(Object bean,BeanDefinition beanDefinition)throws Exception {

    }

    protected Object createBeanInstance(BeanDefinition beanDefinition)throws Exception {
        return beanDefinition.getBeanClass().newInstance();
    }


    public List<Object> getBeanForType(Class type) throws Exception{
        List<Object> beans = new ArrayList<>();
        for(String beanDefinitionName : beanDefinitionNames){
            if(type.isAssignableFrom(beanDefinitionMap.get(beanDefinitionName).getBeanClass())){
                beans.add(getBean(beanDefinitionName));
            }
        }
        return beans;
    }
}
