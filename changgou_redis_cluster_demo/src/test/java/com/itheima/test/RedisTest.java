package com.itheima.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author Steven
 * @version 1.0
 * @description com.itheima.test
 * @date 2020-1-10
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class RedisTest {
    @Autowired
    private RedisTemplate redisTemplate;

    @Test
    public void testRedist(){
        redisTemplate.boundValueOps("name").set("Steven");
        String name = (String) redisTemplate.boundValueOps("name").get();
        System.out.println(name);
    }
}
