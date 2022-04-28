package com.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import com.efounder.JEnterprise.BESApplication;

@RunWith(SpringRunner.class)
@SpringBootTest(classes=BESApplication.class)
public class RedisTest {
    
    
//     @Autowired
//     private RedisTemplate<Object, Object> redisTemplate;
//     
    @Test
    public void RedisTests(){
        
        try {
//            redisTemplate.opsForValue().set("name1", "张三");
//            Object object = redisTemplate.opsForValue().get("name");
            System.out.println("测试object");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}