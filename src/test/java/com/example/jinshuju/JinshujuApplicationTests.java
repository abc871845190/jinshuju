package com.example.jinshuju;

import com.alibaba.fastjson.JSON;
import com.example.jinshuju.pojo.DataBean;
import com.example.jinshuju.utils.Constants;
import com.example.jinshuju.utils.EasyExcelUtils.EasyExcelUtils;
import com.example.jinshuju.utils.QRCodeUtils;
import com.example.jinshuju.utils.RedisUtils;
import com.example.jinshuju.utils.TextUtils;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.google.zxing.WriterException;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.UUID;

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

        String test = null;
        String[] result = TextUtils.splitString(test, ",");
        for (String i : result) {
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

        List<Map<Integer, String>> data = EasyExcelUtils.syncRead(fileName, 0, 0);
        for (Map<Integer, String> map : data) {
            for (Integer i : map.keySet()) {
                if (i > 1) {
                    log.info(map.get(i));
                }

            }
        }
    }

    @Test
    public void testDate() {
        log.info(String.valueOf(new Timestamp(System.currentTimeMillis())));
    }

    @Test
    public void testUUID() {
        log.info(String.valueOf(UUID.randomUUID()));
    }

    @Test
    public void testQRCode() {
        try {
            String filePath = QRCodeUtils.createImage("https://www.baidu.com");
            log.info(filePath);
        } catch (WriterException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testPath() {
        //获取项目classes/static的地址
        //String path = ClassUtils.getDefaultClassLoader().getResource("static").getPath();
        //log.info(path);
    }

    @Test
    public void testToken() {
        String token = (String) redisUtils.get(Constants.User.KEY_TOKEN + "e72bf996f505e5cf73211f8164cf2c1a");
        log.info(token);
    }

    @Test
    public void testText() {
        String idList = "1,2,3,4,5,6";
        String[] idListByString = idList.split(",");

        int[] array = Arrays.asList(idListByString).stream().mapToInt(Integer::parseInt).toArray();
        for (int i : array) {
            log.info(String.valueOf(i));
        }
    }

    @Test
    public void testSecret() {
        String pw = "123456";
        log.info(bCryptPasswordEncoder.encode(pw));
    }

    @Test
    public void testArray() {
//        String s = "asd\"]";
////        String[] a = TextUtils.splitJsonString(s,",");
//////        String[] a = TextUtils.splitString(s,",");
////        for (String x : a){
////            log.info(x);
////        }
//
//        log.info(String.valueOf(TextUtils.indexOfStr(s,"[",0)));
//        List<String> stringList = new ArrayList<>();
//        stringList.add("1");
//        stringList.add("5");
//        String[] array = stringList.toArray(new String[0]);
//        for (String s : array){
//            log.info(s);
//        }

// 多选  ==>  [{key:"0",value:"xx"},{key:"1",value:"xxx"},{key:"2",value:"啊啊啊"}]
// 单选  ==>  {key:"0",value:"xx"}
        String checkbox = "[{key:\"0\",value:\"xx\"},{key:\"1\",value:\"xxx\"},{key:\"2\",value:\"啊啊啊\"}]";
        String radio = "{key:\"0\",value:\"xx\"}";

        List<DataBean> dataBeanList = JSON.parseArray(checkbox,DataBean.class);
        log.info(dataBeanList.toString());
        log.info(String.valueOf(dataBeanList.size()));
        for (DataBean db : dataBeanList){
            log.info("key = "+db.getKey()+", value = "+db.getValue());
        }

        DataBean db = JSON.parseObject(radio,DataBean.class);
        log.info("key = "+db.getKey()+", value = "+db.getValue());
    }
}
