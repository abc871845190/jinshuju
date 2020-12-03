package com.example.jinshuju.config;

import com.example.jinshuju.filter.LoginFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.CacheControl;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.concurrent.TimeUnit;

@Configuration
public class InterceptorConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
//        //添加自定义拦截器
//        InterceptorRegistration registration = registry.addInterceptor(loginFilter());
//        //所有路径都被拦截
//        registration.addPathPatterns("/**");
//        //添加不拦截路径
//        registration.excludePathPatterns(
//                "/UserController/User",
//                "/UserController/loginUser",
//                "/RootController/**",
//                "/TestController/**",
//                "/swagger-resources/**",
//                "/webjars/**",
//                "/v2/**",
//                "/swagger-ui.html/**",
//                "/**/*.html",
//                "/**/*.js",
//                "/**/*.jpg",
//                "/**/*.vue",
//                "/**/*.css");
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("swagger-ui.html")
                .addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");
        //图片
        //registry.addResourceHandler("/img/**").addResourceLocations("file:/" + Constants.FilePath.FILE_IMG + File.separator);
        //静态文件
        registry.addResourceHandler("/static/**")
                .addResourceLocations("classpath:/static/").setCacheControl(CacheControl.maxAge(5, TimeUnit.HOURS).cachePublic());
    }

    @Bean
    public LoginFilter loginFilter() {
        return new LoginFilter();
    }

    /**
     * 跨域
     *
     * @param registry
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        //设置允许跨域的路径
        registry.addMapping("/**")
                //设置允许跨域请求的域名
                //当**Credentials为true时，**Origin不能为星号，需为具体的ip地址【如果接口不带cookie,ip无需设成具体ip】
                .allowedHeaders("*")
                .allowedMethods("*")
                .allowedOrigins("http://localhost:8080")
                //支持安全证书。跨域携带cookie需要配置这个
                .allowCredentials(true)
                //预检请求的有效期，单位为秒。设置maxage，可以避免每次都发出预检请求
                .maxAge(3600);
    }

}

