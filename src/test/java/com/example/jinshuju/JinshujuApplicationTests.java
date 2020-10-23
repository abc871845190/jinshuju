package com.example.jinshuju;

import com.example.jinshuju.utils.RedisUtils;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

@SpringBootTest
@Slf4j
class JinshujuApplicationTests {

    @Autowired
    RedisTemplate redisTemplate;

    @Autowired
    RedisUtils redisUtils;

    @Test
    public void test(){
        redisUtils.set("gaga",123456,10000);
    }

    @Test
    public void testToken() {



    }
}
