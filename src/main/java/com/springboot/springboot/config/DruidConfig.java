package com.springboot.springboot.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Servlet;
import javax.sql.DataSource;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by daixn on 2020/11/7 13:45
 */
@Configuration
public class DruidConfig {

    @Bean
    public ServletRegistrationBean druidServletRegistrationBean() {
        ServletRegistrationBean<Servlet> servletRegistrationBean = new
                ServletRegistrationBean<>(new StatViewServlet(), "/druid/*");
        Map<String, String> initParams = new HashMap<>();
        initParams.put("loginUsername", "admin");
        initParams.put("loginPassword", "123456");
//后台允许谁可以访问


//initParams.put("allow", "localhost")：表示只有本机可以访问
//initParams.put("allow", "")：为空或者为null时，表示允许所有访问
        initParams.put("allow", "");
   /*deny：Druid 后台拒绝谁访问
        三、springboot配置多数据源并动态切换
        DataSource是和线程绑定的，动态数据源的配置主要是通过继承AbstractRoutingDataSource类实现
        的，实现在AbstractRoutingDataSource类中的 protected Object determineCurrentLookupKey()方
        法来获取数据源，所以我们需要先创建一个多线程线程数据隔离的类来存放DataSource，然后在
        determineCurrentLookupKey()方法中通过这个类获取当前线程的DataSource，在
        AbstractRoutingDataSource类中，DataSource是通过Key-value的方式保存的，我们可以通过
        ThreadLocal来保存Key，从而实现数据源的动态切换。
        1、修改配置文件类
        application-mult.properties
        2、创建数据源枚举类*/
//initParams.put("roc", "127.0.0.1");表示禁止此ip访问
        servletRegistrationBean.setInitParameters(initParams);
        return servletRegistrationBean;
    }
    //配置 Druid 监控 之 web 监控的 filter
//WebStatFilter：用于配置Web和Druid数据源之间的管理关联监控统计
    @Bean
    public FilterRegistrationBean webStatFilter() {
        FilterRegistrationBean bean = new FilterRegistrationBean();
        bean.setFilter(new WebStatFilter());
//exclusions：设置哪些请求进行过滤排除掉，从而不进行统计
        Map<String, String> initParams = new HashMap<>();
        initParams.put("exclusions", "*.js,*.css,/druid/*");
        bean.setInitParameters(initParams);
//"/*" 表示过滤所有请求
        bean.setUrlPatterns(Arrays.asList("/*"));
        return bean;
    }
}
