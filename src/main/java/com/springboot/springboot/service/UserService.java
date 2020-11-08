package com.springboot.springboot.service;

import com.springboot.springboot.datasource.DataSourceAnnotation;
import com.springboot.springboot.datasource.DataSourceType;
import com.springboot.springboot.datasource.DynamicDataSourceContextHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.Map;

/**
 * Created by daixn on 2020/11/7 23:42
 */
//或者直接加载类上 ,
public class UserService {
    @Autowired
    JdbcTemplate jdbcTemplate;
    //方法内多数据源时，暂不支持事务
    @DataSourceAnnotation(value = DataSourceType.DS1)
    public List<Map<String, Object>> userList() {
        String sql = "select * from user";
        List<Map<String, Object>> maps = jdbcTemplate.queryForList(sql);
        //可在方法内切换注解
        DynamicDataSourceContextHolder.setDataSourceType(DataSourceType.ACTIVITY.name());
        List<Map<String, Object>> maps2 = jdbcTemplate.queryForList(sql);
        return maps;
    }
    @DataSourceAnnotation(value = DataSourceType.ACTIVITY)
    public List<Map<String, Object>> userList2() {
        String sql = "select * from user";
        List<Map<String, Object>> maps = jdbcTemplate.queryForList(sql);
        return maps;
    }
}
