package com.example.demoSpringJDBC.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

//@Aspect
//@Component
//@Order(1)
@Slf4j
public class AllAspectManagement
{
    @Pointcut(value = "execution(* com.example.demoSpringJDBC.controller.*.get*ById(..))")
    private void getByIdPointcut()
    {
    }

    @Before("getByIdPointcut()") public void beforeGetByIdPointcut()
    {
        System.out.println("==> before get by id yapılıyor");
    }

    @After( "getByIdPointcut()") public void afterGetByIdPointcut()
    {
        System.out.println("==> after get by id yapıldı");
    }

    @Pointcut(value = "within(com.example.demoSpringJDBC.controller.*)")
    private void allMethodsInsideControllerPackage()
    {
    }

    @Before("allMethodsInsideControllerPackage()") public void beforeAllMethodsInsideControllerPackage()
    {
        System.out.println("==> before herhangi bir controller 'da herhangi bir işlem yapılıyor");
    }

    @After( "allMethodsInsideControllerPackage()") public void afterAllMethodsInsideControllerPackage()
    {
        System.out.println("==> after herhangi bir controller 'da herhangi bir işlem yapılıyor");
    }

    // all implementations of interface
    // within(EmployeeManagerImpl+)

    // Overrides before and after
    @org.aspectj.lang.annotation.Around("getByIdPointcut()")
    public void aroundGetByIdPointcut(ProceedingJoinPoint point) throws Throwable
    {
        long startTime = System.currentTimeMillis();
        Object object = point.proceed();
        long endtime = System.currentTimeMillis();
        log.info("Class Name: "+ point.getSignature().getDeclaringTypeName() +". Method Name: "+ point.getSignature().getName() + ". Time taken for Execution is : " + (endtime-startTime) +"ms");
    }
}