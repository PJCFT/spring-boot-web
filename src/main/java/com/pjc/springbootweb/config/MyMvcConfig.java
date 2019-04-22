package com.pjc.springbootweb.config;/*
 *@author: PJC
 *@time: 2019/4/9
 *@description: null
 */


import com.pjc.springbootweb.component.LoginInterceptor;
import com.pjc.springbootweb.component.MyLocalResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.*;

@Configuration
public class MyMvcConfig implements WebMvcConfigurer{

    /*
    * 视图映射
    * */
    @Override
    public void addViewControllers(ViewControllerRegistry registry){
        registry.addViewController("/").setViewName("login");
        registry.addViewController("/index.html").setViewName("login");
        registry.addViewController("/main.html").setViewName("dashboard");

    }
    /*
    * 拦截器
    * */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginInterceptor()).addPathPatterns("/**").excludePathPatterns("/index.html","/","/user/login");
    }
    /*
    * 处理拦截器拦截静态资源的办法
    * */
//    @Override
//    public void addResourceHandlers(ResourceHandlerRegistry registry) {
//        registry.addResourceHandler("/static/asserts/**").addResourceLocations("classpath:/static/asserts/");
//    }
    /*
    * 国际化配置
    * */
    @Bean
    public LocaleResolver localeResolver(){
        return new MyLocalResolver();
    }

}
