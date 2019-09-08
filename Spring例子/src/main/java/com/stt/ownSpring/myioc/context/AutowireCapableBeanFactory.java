package com.stt.ownSpring.myioc.context;

import com.stt.ownSpring.myioc.bean.AbstractBeanFactory;
import com.stt.ownSpring.myioc.bean.BeanDefinition;
import com.stt.ownSpring.myioc.core.PropertyValue;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class AutowireCapableBeanFactory extends AbstractBeanFactory {

    protected void applyPropertyValues(Object bean, BeanDefinition beanDefinition) throws Exception {
        if(bean instanceof  BeanFactoryAware){
            ((BeanFactoryAware)bean).setBeanFactory(this);
        }
        for(PropertyValue propertyValue : beanDefinition.getPropertyValues().getPropertyValues()){
            Object value = propertyValue.getValue();
            if (value instanceof BeanReference) {
                BeanReference beanReference = (BeanReference) value;
                value = getBean(beanReference.getName());
            }

            try{
                Method declaredMethod = bean.getClass().getDeclaredMethod("set" + propertyValue.getKey().substring(0, 1).toUpperCase()
                        + propertyValue.getKey().substring(1), value.getClass());
                declaredMethod.setAccessible(true);
                declaredMethod.invoke(bean, value);
            }catch (NoSuchMethodException e){
                Field field = bean.getClass().getDeclaredField(propertyValue.getKey());
                field.setAccessible(true);
                field.set(bean,value);
            }
        }
    }
}
