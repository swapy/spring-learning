package com.example.multimodule.application;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.core.Ordered;

@Log4j2
public class CustomBeanFactoryPostProcessor implements BeanFactoryPostProcessor,Ordered {

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory configurableListableBeanFactory)
        throws BeansException {
        log.error("logcheck : I am in CustomBeanFactoryPostProcessor ... executing..... ");
    }

    @Override
    public int getOrder() {
        return 1999;
    }
}
