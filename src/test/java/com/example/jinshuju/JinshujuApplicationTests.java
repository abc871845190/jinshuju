package com.example.jinshuju;

import com.alibaba.fastjson.JSON;
import com.example.jinshuju.pojo.DataBean;
import com.example.jinshuju.utils.*;
import com.example.jinshuju.utils.EasyExcelUtils.EasyExcelUtils;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.google.zxing.WriterException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.binary.Base64;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.util.FileCopyUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.List;
import java.util.Map;
import java.util.Random;

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
        //log.info(String.valueOf(UUID.randomUUID()).replace("-",""));
        char[] digits = {'0', '1', '2', '3', '4', '5', '6', '7', '8',
                '9', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l',
                'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y',
                'z', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L',
                'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y',
                'Z'};
        Random random = new Random();
        char[] cs = new char[12];
        for (int i = 0; i < cs.length; i++) {
            cs[i] = digits[random.nextInt(digits.length)];
        }
        log.info(new String(cs));
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
//        String idList = "1,2,3,4,5,6";
//        String[] idListByString = idList.split(",");
//
//        int[] array = Arrays.asList(idListByString).stream().mapToInt(Integer::parseInt).toArray();
//        for (int i : array) {
//            log.info(String.valueOf(i));
//        }
        String img = Constants.Url.host + "/img/head/default_img.jpg";
        String subImg = img.substring(img.lastIndexOf("/") + 1);
        log.info(subImg);

        String oldImg = "/img/head/43ed875f22d49cf788765f5420ef4ea.jpg";
        String imgName = null;
        if (!oldImg.equals("/img/head/default_img.jpg")) {
            //截取图片名字
            log.info("图片不是默认图片");
            imgName = oldImg.substring(oldImg.lastIndexOf("/") + 1);
            log.info(imgName);
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
//        String checkbox = "[{key:\"0\",value:\"xx\"},{key:\"1\",value:\"xxx\"},{key:\"2\",value:\"啊啊啊\"}]";
//        String radio = "{key:\"0\",value:\"xx\"}";
        String emptyCheckBox = "[]";
        String emptyRadio = "";
//        List<DataBean> dataBeanList = JSON.parseArray(checkbox,DataBean.class);
//        log.info(dataBeanList.toString());
//        log.info(String.valueOf(dataBeanList.size()));
//        for (DataBean db : dataBeanList){
//            log.info("key = "+db.getKey()+", value = "+db.getValue());
//        }

//        DataBean db = JSON.parseObject(radio,DataBean.class);
//        log.info("key = "+db.getKey()+", value = "+db.getValue());
//
//        String listTOJson = JSON.toJSONString(dataBeanList);
//        log.info(listTOJson);
//        String beanTOJson = JSON.toJSONString(db);
//        log.info(beanTOJson);

//        log.info(String.valueOf(ArrayUtils.isHaveByInt(2, Constants.Array.MultiSelect)));
        List<DataBean> dataBeanList = JSON.parseArray(emptyCheckBox, DataBean.class);
        log.info(dataBeanList.toString());
        log.info(String.valueOf(dataBeanList.size()));
        DataBean dataBean = JSON.parseObject(emptyRadio, DataBean.class);
        log.info(dataBeanList.toString());
        log.info(String.valueOf(dataBean != null));
    }

    @Test
    public void testImg2String() throws IOException {
        File file = new File(Constants.FilePath.FILE_IMG_HEAD + File.separator + "default_img.jpg");
        FileInputStream is = new FileInputStream(file);
        byte[] buffer = new byte[(int) file.length()];
        is.read(buffer);
        is.close();
        String code = new Base64().encodeToString(buffer);
        //log.info(code);
        String uncode = new String(Base64.decodeBase64(code));
        log.info(uncode);
    }

    @Test
    public void testFile() {
        //[{"key":1,"value":"http://jinshuju.fast2.fgnwctvip.com/img/TemplateImg/d2359ffed0e04e9298bb712be69473f6.png"}]
        String formImgUrl = "[{\"key\":1,\"value\":\"http://jinshuju.fast2.fgnwctvip.com/img/TemplateImg/bd2c9996b7aa49329108ee42b2114a46.gif\"}]";
        List<DataBean> dataBeanList = JSON.parseArray(formImgUrl, DataBean.class);
        if (dataBeanList != null && dataBeanList.size() != 0) {
            for (DataBean db : dataBeanList) {
                String fileUrl = db.getValue();
                String fileName = fileUrl.substring(fileUrl.lastIndexOf("/") + 1);
                log.info(fileName);
                String fileType = fileName.substring(fileName.lastIndexOf(".") + 1);
                log.info(fileType);
                String newFileName = UUIDUtils.getUUID().replace("-", "") + "." + fileType;
                File oldFile = new File(Constants.FilePath.FILE_IMG_TEMPLATE + File.separator + fileName);
                File newFile = new File(Constants.FilePath.FILE_IMG_TEMPLATE + File.separator + newFileName);
                try {
                    FileCopyUtils.copy(oldFile, newFile);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                log.info(newFileName);
            }
        }
    }
}
