package com.springboot.springboot.controller;

import com.springboot.springboot.datasource.DataSourceAnnotation;
import com.springboot.springboot.datasource.DataSourceAspect;
import com.springboot.springboot.datasource.DataSourceType;
import com.springboot.springboot.datasource.DynamicDataSourceContextHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * Created by daixn on 2020/11/7 13:04
 */
@RestController
public class UserController {
    @Autowired
    JdbcTemplate jdbcTemplate;

    @GetMapping("/userlist")
    @DataSourceAnnotation(value = DataSourceType.DS1)
    public List<Map<String, Object>> userList() {
        String sql = "select * from user";
        List<Map<String, Object>> maps = jdbcTemplate.queryForList(sql);
        //可在方法内切换注解
        DynamicDataSourceContextHolder.setDataSourceType(DataSourceType.ACTIVITY.name());
        List<Map<String, Object>> maps2 = jdbcTemplate.queryForList(sql);
        return maps;
    }
    @GetMapping("/userlist2")
    @DataSourceAnnotation(value = DataSourceType.ACTIVITY)
    public List<Map<String, Object>> userList2() {
        String sql = "select * from user";
        List<Map<String, Object>> maps = jdbcTemplate.queryForList(sql);
        return maps;
    }
    @GetMapping("/addUser")
    public String addUser() {
        Random random = new Random();
        int no = random.nextInt(99999);
        String sql = "insert into user(name) values('test" + no + "')";
        jdbcTemplate.update(sql);
        return "success";
    }
    @GetMapping("/updateUser/{id}")
    public String updateUser(@PathVariable("id") Integer id) {
        String sql = "update user set name=? where id = " + id;
        String name = "list";
        jdbcTemplate.update(sql, name);
        return "update success";
    }
    @GetMapping("/deleteUser/{id}")
    public String deleteUser(@PathVariable("id") Integer id) {
        String sql = "delete from user where id = " + id;
        jdbcTemplate.update(sql);
        return "delete success";
    }

}
