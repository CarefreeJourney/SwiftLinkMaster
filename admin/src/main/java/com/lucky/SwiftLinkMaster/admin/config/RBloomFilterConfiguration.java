package com.lucky.SwiftLinkMaster.admin.config;

import org.redisson.api.RBloomFilter;
import org.redisson.api.RedissonClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author: lcl
 * @CreateTime: 2025/2/15 10:12
 * @Description: 布隆过滤器，用于解决判断用户名是否存在时的缓存穿透问题
 * @Position: com.lucky.SwiftLinkMaster.admin.config
 * @CurrentVersion: 1.0
 * @VersionHistory: 1.0
 */
@Configuration
public class RBloomFilterConfiguration {
    /**
     * 防止用户注册查询数据库的布隆过滤器
     */
    @Bean
    public RBloomFilter<String> userRegisterCachePenetrationBloomFilter(RedissonClient redissonClient){
        RBloomFilter<String> cachePenetrationBloomFilter = redissonClient.getBloomFilter("userRegisterCachePenetrationBloomFilter");
        cachePenetrationBloomFilter.tryInit(100000000L,0.001); // 初始化，核心参数：预估存储布隆过滤器存储的元素长度（位数组长度）和运行的和误判率（散列函数个数和位数组长度）
        return cachePenetrationBloomFilter;
    }

}
