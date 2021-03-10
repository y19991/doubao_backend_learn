package com.yafnds.doubao.common.mybatisplus;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.autoconfigure.ConfigurationCustomizer;
import com.baomidou.mybatisplus.core.MybatisConfiguration;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @program: doubao
 * @description: Mybatis-plus的分页组件拦截器，拦截包含 Page<T> 参数的请求
 * @author: y19991
 * @create: 2021-03-10 11:32
 **/

@Configuration
@MapperScan("com.yafnds.doubao.mapper")
public class MybatisPlusConfig {

    @Bean
    public MybatisPlusInterceptor mybatisPlusInterceptor() {
        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
        interceptor.addInnerInterceptor(new PaginationInnerInterceptor(DbType.MYSQL));
        return interceptor;
    }

    @Bean
    public ConfigurationCustomizer configurationCustomizer() {
        //setUseDeprecatedExecutor这个方法据官网说明，下个版本会删除，现在为了避免缓存出现问题不得不配置
        return configuration -> configuration.setUseDeprecatedExecutor(false);
    }
}
