package com.example.jinshuju;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication(scanBasePackages = "com.example.jinshuju")
@MapperScan("com.example.jinshuju.mapper")
//开启swagger2自动配置
@EnableSwagger2
public class JinshujuApplication {

    public static void main(String[] args) {
        SpringApplication.run(JinshujuApplication.class, args);
    }
}
