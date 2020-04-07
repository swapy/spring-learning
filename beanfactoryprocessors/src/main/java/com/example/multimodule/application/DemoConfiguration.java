package com.example.multimodule.application;

import java.util.Arrays;
import java.util.List;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DemoConfiguration {

    @Bean
    public static BeanFactoryPostProcessor iAmprocessor(){
        return new CustomBeanFactoryPostProcessor();
    }

    @Bean
    public BeanPostProcessor iAmBeanPostprocessor(){
        return new CustomBeanPostProcessor();
    }

    @Bean(initMethod = "init", destroyMethod = "destroy")
    public Player playerBean(){
        return new Player(1001,"Hero");
    }
    @Bean
    public List<Player> playerListBean(){
        return
            Arrays.asList(
            new Player(1,"Swapnil"),
            new Player(2,"Neha"),
            new Player(3,"Siddhesh"),
            new Player(4,"Pranita"));
    }

    @Bean(initMethod = "init", destroyMethod = "destroy")
    public Game gameBean(){
        return new Game(1,playerListBean());
    }
}
