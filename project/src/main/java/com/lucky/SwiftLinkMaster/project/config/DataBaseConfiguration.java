package com.lucky.SwiftLinkMaster.project.config;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author: lcl
 * @CreateTime: 2025/2/28 10:27
 * @Description: TODO
 * @Position: com.lucky.SwiftLinkMaster.project.config
 * @CurrentVersion: 1.0
 * @VersionHistory: 1.0
 */
@Configuration
public class DataBaseConfiguration {
    /**
     * 分页插件
     * @return
     */
    @Bean
    public MybatisPlusInterceptor mybatisPlusInterceptor(){
        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
        interceptor.addInnerInterceptor(new PaginationInnerInterceptor(DbType.MYSQL));
        return interceptor;
    }
}
