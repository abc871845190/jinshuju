package com.example.jinshuju;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.server.ConfigurableWebServerFactory;
import org.springframework.boot.web.server.ErrorPage;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.HashSet;
import java.util.Set;

@SpringBootApplication(scanBasePackages = "com.example.jinshuju")
@MapperScan("com.example.jinshuju.mapper")
//开启swagger2自动配置
@EnableSwagger2
public class JinshujuApplication {

    /**
     * 程序主入口
     *
     * @param args
     */
    public static void main(String[] args) {
        SpringApplication.run(JinshujuApplication.class, args);
    }

    /**
     * Spring boot 使用嵌入式应用服务器时，
     * 如果希望对 WebServer 进行某些个性化配置，
     * 可以通过创建 WebServerFactoryCustomizer子类的 实例并注册为 Bean 的方式实现
     *
     * @return
     */
    @Bean
    public WebServerFactoryCustomizer<ConfigurableWebServerFactory> webServerFactoryCustomizer() {
        return factory -> {
//                factory.setPort(8081);
            //自定义错误页面    -- not_found --自动跳转到index.html页面
            ErrorPage error404Page = new ErrorPage(HttpStatus.NOT_FOUND, "/index.html");
            Set<ErrorPage> errorPages = new HashSet<>();
            errorPages.add(error404Page);
            factory.setErrorPages(errorPages);
        };
    }
}
