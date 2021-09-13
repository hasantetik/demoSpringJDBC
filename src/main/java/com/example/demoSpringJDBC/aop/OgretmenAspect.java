package com.example.demoSpringJDBC.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Aspect
@Component
@Order(2)
public class OgretmenAspect
{
    @Pointcut(value = "execution(* com.example.demoSpringJDBC.controller.OgretmenResource.*(..))") private void ogretmenPointcut()
    {
    }

    @Before("ogretmenPointcut()") public void beforeOgretmenPointcut()
    {
        System.out.println("==> before ogretmen işlemi");
    }

    @After("ogretmenPointcut()") public void afterOgretmenPointcut()
    {
        System.out.println("==> after ogretmen işlemi");
    }

    @Before(value = "execution(* com.example.demoSpringJDBC.controller.OgretmenResource.getAll())")
    public void beforeOgretmenGetAll(JoinPoint point)
    {
        System.out.println("==> before ogretmen getall işlemi");
        System.out.println("==> " + point.getSignature().getName());
        System.out.println("==> " + point.getSignature().toLongString());
        System.out.println("==> " + Arrays.toString(point.getArgs()));
    }
}