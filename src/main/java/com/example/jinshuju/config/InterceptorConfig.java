package com.example.jinshuju.config;

import com.example.jinshuju.filter.LoginFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.*;

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
    }

    @Bean
    public LoginFilter loginFilter() {
        return new LoginFilter();
    }

    /**
     * 跨域
     * @param registry
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {

        registry.addMapping("/**")
                .allowedHeaders("*")
                .allowedMethods("*")
                .allowedOrigins("*")
                .allowCredentials(true)
                .maxAge(3600);
    }
}

