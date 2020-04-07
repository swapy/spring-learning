package com.example.multimodule.application;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.lang.Nullable;

@Log4j2
public class CustomBeanPostProcessor implements BeanPostProcessor {

    @Nullable
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        log.info("logcheck : I am in before OF CustomBeanPostProcessor for bean named "+ beanName );
        return bean;
    }

    @Nullable
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        log.info("logcheck : I am in after OF CustomBeanPostProcessor "+ beanName );
        return bean;
    }
}
