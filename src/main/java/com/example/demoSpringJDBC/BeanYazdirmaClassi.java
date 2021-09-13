package com.example.demoSpringJDBC;

import com.example.demoSpringJDBC.demo.Car;
import com.example.demoSpringJDBC.demo.Person;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component public class BeanYazdirmaClassi implements ApplicationContextAware
{
    @Override public void setApplicationContext(ApplicationContext applicationContext) throws BeansException
    {
        /*String[] beanNames = applicationContext.getBeanDefinitionNames();
        Arrays.sort(beanNames);
        System.err.println(beanNames.length);
        for (String beanName : beanNames)
        {
            System.err.println(beanName + " - " + applicationContext.getBean(beanName).getClass());
        }*/
    }
}