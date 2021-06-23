package com.codegym.aspect;

import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class Logger {
//    @Before("execution(public * com.codegym.service.customer.CustomerService.findAll(..))")
//    @After("execution(public * com.codegym.service.customer.CustomerService.findAll(..))")
//    @AfterReturning("execution(public * com.codegym.service.customer.CustomerService.findAll(..))")
    @AfterThrowing("execution(public * com.codegym.service.customer.CustomerService.findAll(..))")
    public void log(){
        System.err.println("After Throwing Advice For Service");
    }
}
