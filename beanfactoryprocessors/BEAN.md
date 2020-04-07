`org.springframework.beans.factory.config.BeanFactoryPostProcessor (interface)`
eg:

```java
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
```
<p>
 The semantics of this interface are similar to the BeanPostProcessor, with one major difference: BeanFactoryPostProcessors operate on the bean configuration metadata; that is, the Spring IoC container allows BeanFactoryPostProcessors to read the configuration metadata and potentially change it before the container instantiates any beans other than BeanFactoryPostProcessors.
 
 If you want to change the actual bean instances (the objects that are created from the configuration metadata), then you instead need to use a BeanPostProcessor.
 

 </p>
and

`org.springframework.beans.factory.config.BeanPostProcessor;(interface)`

```java
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

```

are extension points available for extending Spring IoC container

A bean factory post-processor is executed automatically when it is declared inside of an ApplicationContext, in order to apply changes to the configuration metadata that defines a container. Spring includes a number of pre-existing bean factory post-processors, such as PropertyOverrideConfigurer and PropertyPlaceholderConfigurer. A custom BeanFactoryPostProcessor can also be used, for example, to register custom property editors.

The PropertyPlaceholderConfigurer does not look for properties only in the Properties file you specify, but also checks against the Java System properties if it cannot find a property you are trying to use. You can customize this behavior by setting the systemPropertiesMode property of the configurer. It has three values that specify configurer behavior: always override, never override, and override only if the property is not found in the properties file specified. Consult the Javadoc for the PropertyPlaceholderConfigurer for more information.


So in case we have following

A bean class Player with (id,name)
 + constructor
 + postconstruct(init method)
 + destroy method
 
As well as we have custom BeanPostProcessor and BeanFactoryPostProcessor defined...

we will get output like this

```logs
2020-04-06 21:19:41.697 ERROR 5936 --- [main] c.e.m.a.CustomBeanFactoryPostProcesor    : logcheck : I am in CustomBeanFactoryPostProcesor ... executing..... 


2020-04-06 21:19:42.117 DEBUG 5936 --- [main] o.s.b.f.s.DefaultListableBeanFactory     : Creating shared instance of singleton bean 'playerBean'
2020-04-06 21:19:42.117  INFO 5936 --- [main] c.e.multimodule.application.Player       : logcheck : in constructor of Player class 
2020-04-06 21:19:42.117  INFO 5936 --- [main] c.e.m.a.CustomBeanPostProcessor          : logcheck : I am in before OF CustomBeanPostProcessor for bean named playerBean
2020-04-06 21:19:42.118  INFO 5936 --- [main] c.e.multimodule.application.Player       : logcheck : In init method of Player
2020-04-06 21:19:42.118  INFO 5936 --- [main] c.e.m.a.CustomBeanPostProcessor          : logcheck : I am in after OF CustomBeanPostProcessor playerBean
```

Order is
1. CustomBeanFactoryPostProcesor (executed only once in whole lifecycle) 
2. Constructor executed of bean
3. before method of CustomBeanPostProcessor
4. init or PostConstruct method 
5. after method of CustomBeanPostProcessor
6. destroy or PreDestroy of bean.

Note:
*CustomBeanPostProcessor applies to all the beans registered in applicationcontext.*


Both PostProcessors can be ordered. We can have N such postProcessors.

FactoryBean
You implement the org.springframework.beans.factory.FactoryBean interface for objects that are themselves factories.

