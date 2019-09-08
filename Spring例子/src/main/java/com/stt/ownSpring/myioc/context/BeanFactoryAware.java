package com.stt.ownSpring.myioc.context;

import com.stt.ownSpring.myioc.bean.BeanFactory;

public interface BeanFactoryAware {

    void setBeanFactory(BeanFactory beanFactory) throws Exception;

}
