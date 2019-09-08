package com.stt.ownSpring.myioc.bean;

import com.stt.ownSpring.myioc.core.PropertyValues;
import lombok.Data;

/**
 * 保存bean元数据
 */
@Data
public class BeanDefinition {

    private Object bean;

    private Class beanClass;

    private String beanClassName;

    private PropertyValues propertyValues;

    public BeanDefinition(){

    }

    public void setBeanClassName(String beanClassName){
        this.beanClassName = beanClassName;
        try{
            this.bean = Class.forName(beanClassName);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
