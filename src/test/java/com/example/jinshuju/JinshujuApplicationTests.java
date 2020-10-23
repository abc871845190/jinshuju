package com.example.jinshuju;

import com.example.jinshuju.utils.JwtUtils;
import com.example.jinshuju.utils.RedisUtils;
import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.HashMap;
import java.util.Map;

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
        Map<String, Object> map = new HashMap<>();
        map.put("id", "123456");
        map.put("name", "yaozefan");
        map.put("status", "isLogin");

        String token = JwtUtils.createJWT(map);
        log.info(token);

        Claims claims = JwtUtils.parseJWT(token);
//        log.info(claims.getId());
//        log.info(claims.getSubject());
//        log.info(claims.getIssuer());
//        log.info(claims.getIssuedAt().toString());
//        log.info(claims.getExpiration().toString());

        log.info((String) claims.get("id"));



    }
}
