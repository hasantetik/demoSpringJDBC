package com.example.demoSpringJDBC.demo;

import lombok.AllArgsConstructor;
import org.springframework.beans.BeansException;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

//@Component
public class DemoComp implements ApplicationContextAware
{
    @Override public void setApplicationContext(ApplicationContext applicationContext) throws BeansException
    {
        System.out.println("-------->");
        Person p = applicationContext.getBean(Person.class);
        Car c = applicationContext.getBean(Car.class);
        System.out.println(c.getRenk());
        System.out.println(p.getAraba().getRenk());
        c.setRenk("Yeşil araba");
        System.out.println(c.getRenk());
        System.out.println(p.getAraba().getRenk());
        System.out.println("-------->");
    }
}