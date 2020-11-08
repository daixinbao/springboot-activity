package com.springboot.springboot.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.springboot.springboot.datasource.DataSourceType;
import com.springboot.springboot.datasource.DynamicDataSource;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by daixn on 2020/11/7 14:09
 */
@Configuration
public class DataSourceConfig {
    @Bean
    @ConfigurationProperties("spring.datasource.ds1")
    public DataSource ds1() {
        //return DataSourceBuilder.create().build();
        return new DruidDataSource();
    }

    @Bean
    @ConfigurationProperties("spring.datasource.ds2")
    public DataSource ds2() {
        //return DataSourceBuilder.create().build();
        return new DruidDataSource();
    }

    @Bean
    @ConfigurationProperties("spring.datasource.activity")
    public DataSource activity() {
        //return DataSourceBuilder.create().build();
        return new DruidDataSource();
    }

    @Bean(name = "dynamicDataSource")
    @Primary
    public DynamicDataSource dataSource(DataSource ds1, DataSource ds2,DataSource activity) {
        Map<Object, Object> targetDataSources = new HashMap<>();
        targetDataSources.put(DataSourceType.DS1.name(), ds1);
        targetDataSources.put(DataSourceType.DS2.name(), ds2);
        targetDataSources.put(DataSourceType.ACTIVITY.name(), activity);
        return new DynamicDataSource(ds1, targetDataSources);
    }
}
