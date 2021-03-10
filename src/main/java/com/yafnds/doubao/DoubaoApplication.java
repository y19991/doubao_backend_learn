package com.yafnds.doubao;

import com.yafnds.doubao.jwt.JwtAuthenticationFilter;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;

import javax.servlet.FilterRegistration;

@SpringBootApplication
@MapperScan("com.yafnds.doubao.mapper")
public class DoubaoApplication extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(DoubaoApplication.class);
    }

    /**
     * 注册自定义的过滤器
     * @return FilterRegistrationBean对象
     */
    @Bean
    public FilterRegistrationBean jwtFilter() {
        // 新建注册bean的对象
        final FilterRegistrationBean registrationBean = new FilterRegistrationBean();
        // 新建自定义过滤器对象
        JwtAuthenticationFilter filter = new JwtAuthenticationFilter();
        // 把过滤器注册到bean中
        registrationBean.setFilter(filter);
        return registrationBean;
    }


    public static void main(String[] args) {
        SpringApplication.run(DoubaoApplication.class, args);
    }

}
