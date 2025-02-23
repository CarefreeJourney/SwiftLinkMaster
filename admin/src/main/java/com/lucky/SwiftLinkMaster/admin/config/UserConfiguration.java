package com.lucky.SwiftLinkMaster.admin.config;

/**
 * @Author: lcl
 * @CreateTime: 2025/2/23 21:02
 * @Description: TODO
 * @Position: com.lucky.SwiftLinkMaster.admin.config
 * @CurrentVersion: 1.0
 * @VersionHistory: 1.0
 */

import com.lucky.SwiftLinkMaster.admin.common.biz.user.UserTransmitFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.StringRedisTemplate;

/**
 * 用户配置自动装配
 */
@Configuration
public class UserConfiguration {

    /**
     * 用户信息传递过滤器
     */
    @Bean
    public FilterRegistrationBean<UserTransmitFilter> globalUserTransmitFilter(StringRedisTemplate stringRedisTemplate) {
        FilterRegistrationBean<UserTransmitFilter> registration = new FilterRegistrationBean<>();
        registration.setFilter(new UserTransmitFilter(stringRedisTemplate));
        registration.addUrlPatterns("/*");
        registration.setOrder(0);
        return registration;
    }
}

