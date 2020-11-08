package com.springboot.springboot.datasource;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;


@Aspect
@Order(1)
@Component
public class DataSourceAspect{
    @Pointcut("@annotation(com.springboot.springboot.datasource.DataSourceAnnotation)")
    public void dsPointCut() {
        System.out.println("qierudianaaaa");
    }

    @Around("dsPointCut()")
    public Object around(ProceedingJoinPoint point) throws Throwable {
        MethodSignature signature = (MethodSignature) point.getSignature();
        Method method = signature.getMethod();
        DataSourceAnnotation dataSourceAnnotation = method.getAnnotation(DataSourceAnnotation.class);

        if (dataSourceAnnotation != null) {
            DynamicDataSourceContextHolder.setDataSourceType(dataSourceAnnotation.value().name());
        }
        try {
            System.out.println("执行方法前");
            Object result = point.proceed();
            System.out.println("执行方法后");
            return result;
        } finally {
// 销毁数据源 在执行方法之后
            DynamicDataSourceContextHolder.clearDataSourceType();
        }
    }
}