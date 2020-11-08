package com.springboot.springboot.controller;

import com.springboot.springboot.service.ActivityConsumerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by daixn on 2019/6/15 16:04
 */
@RestController
//@DataSource(value = DataSourceType.ACTIVITY)
public class ActivityController {
    @Autowired
    ActivityConsumerService activityConsumerService;


    @GetMapping("/hello")

    String  hello(){
        activityConsumerService.startActivityDemo();
        return "hello";
    }

    @ExceptionHandler
    public String doError(Exception ex) throws Exception{
        System.out.println("异常铺货");
        ex.printStackTrace();
        return ex.getMessage();
    }
}
