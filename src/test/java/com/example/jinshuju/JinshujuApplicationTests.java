package com.example.jinshuju;

import com.example.jinshuju.utils.EasyExcelUtils.EasyExcelUtils;
import com.example.jinshuju.utils.RedisUtils;
import com.example.jinshuju.utils.TextUtils;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

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

        String test  = null;
        String[] result = TextUtils.splitString(test,",");
        for (String i:result){
            log.info(i);
        }
        log.info(String.valueOf(result.length));
        //log.info();
    }

    @Test
    public void testExcel() {
        String fileName = "C:\\Users\\huangsongzhang\\Desktop\\未命名报名_20201103203506.xlsx";
        //调用工具类
//        List<Map<Integer,String>> data = EasyExcelUtils.syncRead(fileName,0,0);
//        for (Iterator it = data.iterator(); it.hasNext();){
//            Map<Integer,String> obj = (Map<Integer, String>) it.next();
//            log.info(String.valueOf(obj));
//        }
//        log.info(data.toString());

       // log.info(String.valueOf("未命名报名_20201103203506.xlsx".lastIndexOf(".")));

        List<Map<Integer,String>> data = EasyExcelUtils.syncRead(fileName,0,0);
        for (Map<Integer,String> map : data){
            for (Integer i : map.keySet()){
                if (i>1){
                    log.info(map.get(i));
                }

            }
        }
    }

    @Test
    public void testDate(){
        log.info(String.valueOf(new Timestamp(System.currentTimeMillis())));
    }
}
