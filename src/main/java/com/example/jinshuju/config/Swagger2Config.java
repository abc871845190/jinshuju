package com.example.jinshuju.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
public class Swagger2Config {

    /**
     * @Api 描述类/接口的主要用途
     * @ApiOperation 描述方法用途
     * @ApiImplicitParam 描述方法的参数
     * @ApiImplicitParams 描述方法的参数(Multi - Params)
     * @ApiIgnore 忽略某类/方法/参数的文档
     */

    public static final String VERSION = "1.1.0";

    /**
     * 配置docket以配置Swagger具体参数
     *
     * @return
     */
    @Bean
    public Docket UserApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                // 通过.select()方法，去配置扫描接口
                .apiInfo(UserApiInfo())
                // 配置是否启用Swagger，如果是false，在浏览器将无法访问
                .enable(true)
                .select()
                // RequestHandlerSelectors配置如何扫描接口
                .apis(RequestHandlerSelectors.basePackage("com.example.jinshuju.controller"))
                //根据url路径设置哪些请求加入文档，忽略哪些请求
                //any()  任何请求都扫描
                //none()  任何请求都不扫描
                //regex(final String pathRegex)  通过正则表达式控制，返回true扫描，false不扫描
                //ant(final String antPattern)  通过ant()表达式控制，返回true扫描，false不扫描
                .paths(PathSelectors.any())
                .build();
                //给文档分组
                //.groupName("用户接口");
    }

    private ApiInfo UserApiInfo() {
        return new ApiInfoBuilder()
                //设置文档标题
                .title("金数据用户接口文档")
                //设置文档描述
                .description("用户接口...")
                //设置文档版本
                .version(VERSION)
                .build();
    }

}
