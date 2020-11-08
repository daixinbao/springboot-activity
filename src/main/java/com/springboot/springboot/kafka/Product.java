package com.springboot.springboot.kafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

/**
 * Created by daixn on 2020/10/19 14:38
 */
//@Component
public class Product {
   // @Autowired
    private KafkaTemplate kafkaTemplate;
    public void send(String name){
        kafkaTemplate.send("user", "java  kafaka tetss");
    }
}
