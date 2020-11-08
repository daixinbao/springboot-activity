package com.springboot.springboot.datasource;

import java.lang.annotation.*;

/**
 * Created by daixn on 2020/11/7 14:13
 */
/*6、自定义多数据源切换注解
    设置拦截数据源的注解，可以设置在具体的类上，或者在具体的方法上
7、AOP拦截类的实现
    通过拦截上面的注解，在其执行之前处理设置当前执行SQL的数据源的信息，
            CONTEXT_HOLDER.set(dataSourceType)这里的数据源信息从我们设置的注解上面获取信息，如果没
    有设置就是用默认的数据源的信息。
    加入aspectj的依赖*/
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface DataSourceAnnotation {
    /**
     * 切换数据源名称
     */
    DataSourceType value() default DataSourceType.DS1;
}