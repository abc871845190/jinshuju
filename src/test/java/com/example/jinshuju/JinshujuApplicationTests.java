package com.example.jinshuju;

import com.example.jinshuju.utils.RedisUtils;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootTest
@Slf4j
class JinshujuApplicationTests {

    @Autowired
    RedisTemplate redisTemplate;

    @Autowired
    RedisUtils redisUtils;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;
    @Test
    public void test() throws JsonProcessingException {
//        User user = new User();
//        user.setUserName("yaozefan");
//        user.setUserEmail("871845190@qq.com");
//        user.setUserImg("////////123456");
//        user.setUserCreateTime(new Timestamp(System.currentTimeMillis()));
//        log.info(JSON.toJSONString(user));
//
//        ObjectMapper om = new ObjectMapper();
//        om.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,false);
//        om.configure(DeserializationFeature.READ_DATE_TIMESTAMPS_AS_NANOSECONDS,false);
//        log.info(om.writeValueAsString(user));

        log.info(String.valueOf(bCryptPasswordEncoder.matches("123456","$2a$10$W4Aiv0gOq/tOAzF4rptXw.q4QXERFL9nclJgAraYacRT4JDA4Y5Hu")));
    }

    @Test
    public void testToken() {

    }
}
